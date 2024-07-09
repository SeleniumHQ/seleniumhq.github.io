using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.IdentityModel.Tokens;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.Drivers
{
    [TestClass]
    public class RemoteWebDriverTest : BaseTest
    {
        [TestInitialize]
        public async Task Setup()
        {
            await StartServer();
        }

        [TestMethod]
        public void RunRemote()
        {
            var options = new ChromeOptions();
            driver = new RemoteWebDriver(GridUrl, options);

            Assert.IsInstanceOfType(driver, typeof(IHasDownloads));
        }

        [TestMethod]
        public void Uploads()
        {
            var options = new ChromeOptions();
            driver = new RemoteWebDriver(GridUrl, options);

            driver.Url = "https://the-internet.herokuapp.com/upload";

            string baseDirectory = AppContext.BaseDirectory;
            string relativePath = "../../../TestSupport/selenium-snapshot.png";

            string uploadFile = Path.GetFullPath(Path.Combine(baseDirectory, relativePath));

            ((RemoteWebDriver)driver).FileDetector = new LocalFileDetector();
            IWebElement fileInput = driver.FindElement(By.CssSelector("input[type=file]"));
            fileInput.SendKeys(uploadFile);
            driver.FindElement(By.Id("file-submit")).Click();

            IWebElement fileName = driver.FindElement(By.Id("uploaded-files"));
            Assert.AreEqual("selenium-snapshot.png", fileName.Text);
        }

        [TestMethod]
        public void Downloads()
        {
            ChromeOptions options = new ChromeOptions
            {
                EnableDownloads = true
            };

            driver = new RemoteWebDriver(GridUrl, options);

            driver.Url = "https://selenium.dev/selenium/web/downloads/download.html";
            driver.FindElement(By.Id("file-1")).Click();
            driver.FindElement(By.Id("file-2")).Click();
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
            wait.Until(d => ((RemoteWebDriver)d).GetDownloadableFiles().Contains("file_2.jpg"));

            IReadOnlyList<string> names = ((RemoteWebDriver)driver).GetDownloadableFiles();

            Assert.IsTrue(names.Contains("file_1.txt"));
            Assert.IsTrue(names.Contains("file_2.jpg"));
            string downloadableFile = names.First(f => f == "file_1.txt");
            string targetDirectory = Path.Combine(Path.GetTempPath(), Guid.NewGuid().ToString());

            ((RemoteWebDriver)driver).DownloadFile(downloadableFile, targetDirectory);

            string fileContent = File.ReadAllText(Path.Combine(targetDirectory, downloadableFile));
            Assert.AreEqual("Hello, World!", fileContent.Trim());

            ((RemoteWebDriver)driver).DeleteDownloadableFiles();

            Assert.IsTrue(((RemoteWebDriver)driver).GetDownloadableFiles().IsNullOrEmpty());
            Directory.Delete(targetDirectory, recursive: true);
        }

        [TestMethod]
        public void CustomExecutor()
        {
            driver = new RemoteWebDriver(GridUrl, new FirefoxOptions());
            driver.Navigate().GoToUrl("https://www.selenium.dev/");

            var customCommandDriver = driver as ICustomDriverCommandExecutor;
            customCommandDriver.RegisterCustomDriverCommands(FirefoxDriver.CustomCommandDefinitions);

            var screenshotResponse = customCommandDriver
                .ExecuteCustomDriverCommand(FirefoxDriver.GetFullPageScreenshotCommand, null);

            Screenshot image = new Screenshot((string)screenshotResponse);

            string targetDirectory = Path.Combine(Path.GetTempPath(), Guid.NewGuid().ToString());
            Directory.CreateDirectory(targetDirectory);
            string targetFile = Path.GetFullPath(Path.Combine(targetDirectory, "fullPage.png"));

            using (var memoryStream = new MemoryStream(image.AsByteArray))
            using (var fileStream = new FileStream(targetFile, FileMode.Create))
            {
                memoryStream.WriteTo(fileStream);
            }

            Assert.IsTrue(File.Exists(targetFile));
            
            Directory.Delete(targetDirectory, true);
        }
    }
}