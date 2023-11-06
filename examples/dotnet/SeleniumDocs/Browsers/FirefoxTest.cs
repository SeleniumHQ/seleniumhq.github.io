using System;
using System.IO;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class FirefoxTest
    {
        private FirefoxDriver driver;
        private string _logLocation;
        private string _tempPath;

        [TestCleanup]
        public void Cleanup()
        {
            if (_logLocation != null && File.Exists(_logLocation))
            {
                File.Delete(_logLocation);
            }
            if (_tempPath != null && File.Exists(_tempPath))
            {
                File.Delete(_tempPath);
            }
            driver.Quit();
        }

        [TestMethod]
        public void BasicOptions()
        {
            var options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }

        [TestMethod]
        public void Arguments()
        {
            var options = new FirefoxOptions();

            options.AddArgument("-headless");

            driver = new FirefoxDriver(options);
        }

        [TestMethod]
        public void SetBinary()
        {
            var options = new FirefoxOptions();

            options.BinaryLocation = GetFirefoxLocation();

            driver = new FirefoxDriver(options);
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void LogsToFile()
        {
            var service = FirefoxDriverService.CreateDefaultService();
            //service.LogFile = _logLocation

            driver = new FirefoxDriver(service);
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("geckodriver	INFO	Listening on")));
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void LogsToConsole()
        {
            var stringWriter = new StringWriter();
            var originalOutput = Console.Out;
            Console.SetOut(stringWriter);

            var service = FirefoxDriverService.CreateDefaultService();
            //service.LogToConsole = true;

            driver = new FirefoxDriver(service);
            Assert.IsTrue(stringWriter.ToString().Contains("geckodriver	INFO	Listening on"));
            Console.SetOut(originalOutput);
            stringWriter.Dispose();
        }

        [TestMethod]
        [Ignore("You can set it, just can't see it")]
        public void LogsLevel()
        {
            var service = FirefoxDriverService.CreateDefaultService();
            //service.LogFile = _logLocation

            service.LogLevel = FirefoxDriverLogLevel.Debug;

            driver = new FirefoxDriver(service);
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("Marionette\tDEBUG")));
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void StopsTruncatingLogs()
        {
            var service = FirefoxDriverService.CreateDefaultService();
            //service.TruncateLogs = false;

            service.LogLevel = FirefoxDriverLogLevel.Debug;

            driver = new FirefoxDriver(service);
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNull(lines.FirstOrDefault(line => line.Contains(" ... ")));
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void SetProfileLocation()
        {
            var service = FirefoxDriverService.CreateDefaultService();
            // service.ProfileRoot = GetTempDirectory();

            driver = new FirefoxDriver(service);

            string profile = (string)driver.Capabilities.GetCapability("moz:profile");
            string[] directories = profile.Split("/");
            var dirName = directories.Last();
            Assert.AreEqual(GetTempDirectory() + "/" + dirName, profile);
        }

        [TestMethod]
        public void InstallAddon()
        {
            SetWaitingDriver();
            string baseDir = AppDomain.CurrentDomain.BaseDirectory;
            string extensionFilePath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example.xpi");

            driver.InstallAddOnFromFile(Path.GetFullPath(extensionFilePath));

            driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
            IWebElement injected = driver.FindElement(By.Id("webextensions-selenium-example"));
            Assert.AreEqual("Content injected by webextensions-selenium-example", injected.Text);
        }

        [TestMethod]
        public void UnInstallAddon()
        {
            driver = new FirefoxDriver();
            string baseDir = AppDomain.CurrentDomain.BaseDirectory;
            string extensionFilePath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example.xpi");
            string extensionId = driver.InstallAddOnFromFile(Path.GetFullPath(extensionFilePath));

            driver.UninstallAddOn(extensionId);

            driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
            Assert.AreEqual(driver.FindElements(By.Id("webextensions-selenium-example")).Count, 0);
        }

        [TestMethod]
        public void InstallUnsignedAddon()
        {
            SetWaitingDriver();
            string baseDir = AppDomain.CurrentDomain.BaseDirectory;
            string extensionDirPath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example/");

            driver.InstallAddOnFromDirectory(Path.GetFullPath(extensionDirPath), true);

            driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
            IWebElement injected = driver.FindElement(By.Id("webextensions-selenium-example"));
            Assert.AreEqual("Content injected by webextensions-selenium-example", injected.Text);
        }
        
        private string GetLogLocation()
        {
            if (_logLocation != null && !File.Exists(_logLocation))
            {
                _logLocation = Path.GetTempFileName();
            }

            return _logLocation;
        }

        private string GetTempDirectory()
        {
            if (_tempPath != null && !File.Exists(_tempPath))
            {
                _tempPath = Path.GetTempPath();
            }

            return _tempPath;
        }

        private void SetWaitingDriver()
        {
            driver = new FirefoxDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(2);
        }

        private static string GetFirefoxLocation()
        {
            var options = new FirefoxOptions()
            {
                BrowserVersion = "stable"
            };
            DriverFinder.FullPath(options);
            return options.BinaryLocation;
        }
    }
}