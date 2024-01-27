using System;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class ChromeTest
    {
        private ChromeDriver driver;
        private string _logLocation;

        [TestCleanup]
        public void Cleanup()
        {
            if (_logLocation != null && File.Exists(_logLocation))
            {
                File.Delete(_logLocation);
            }
            driver.Quit();
        }

        [TestMethod]
        public void BasicOptions()
        {
            var options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }

        [TestMethod]
        public void Arguments()
        {
            var options = new ChromeOptions();

            options.AddArgument("--start-maximized");

            driver = new ChromeDriver(options);
        }

        [TestMethod]
        public void SetBrowserLocation()
        {
            var options = new ChromeOptions();

            options.BinaryLocation = GetChromeLocation();

            driver = new ChromeDriver(options);
        }

        [TestMethod]
        public void InstallExtension()
        {
            var options = new ChromeOptions();
            var baseDir = AppDomain.CurrentDomain.BaseDirectory;
            var extensionFilePath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example.crx");

            options.AddExtension(extensionFilePath);

            driver = new ChromeDriver(options);

            driver.Url = "https://www.selenium.dev/selenium/web/blank.html";

            IWebElement injected = driver.FindElement(By.Id("webextensions-selenium-example"));
            Assert.AreEqual("Content injected by webextensions-selenium-example", injected.Text);
        }

        [TestMethod]
        public void ExcludeSwitch()
        {
            var options = new ChromeOptions();

            options.AddExcludedArgument("disable-popup-blocking");

            driver = new ChromeDriver(options);
        }

        [TestMethod]
        public void LogsToFile()
        {
            var service = ChromeDriverService.CreateDefaultService();

            service.LogPath = GetLogLocation();

            driver = new ChromeDriver(service);
            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("Starting ChromeDriver")));
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void LogsToConsole()
        {
            var stringWriter = new StringWriter();
            var originalOutput = Console.Out;
            Console.SetOut(stringWriter);

            var service = ChromeDriverService.CreateDefaultService();

            //service.LogToConsole = true;

            driver = new ChromeDriver(service);

            Assert.IsTrue(stringWriter.ToString().Contains("Starting ChromeDriver"));
            Console.SetOut(originalOutput);
            stringWriter.Dispose();
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void LogsLevel()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.LogPath = GetLogLocation();

            // service.LogLevel = ChromiumDriverLogLevel.Debug 

            driver = new ChromeDriver(service);

            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("[DEBUG]:")));
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void ConfigureDriverLogs()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.LogPath = GetLogLocation();
            service.EnableVerboseLogging = true;

            service.EnableAppendLog = true;
            // service.readableTimeStamp = true;

            driver = new ChromeDriver(service);

            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            var regex = new Regex(@"\[\d\d-\d\d-\d\d\d\d");
            Assert.IsNotNull(lines.FirstOrDefault(line => regex.Matches("").Count > 0));
        }

        [TestMethod]
        public void DisableBuildCheck()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.LogPath = GetLogLocation();
            service.EnableVerboseLogging = true;

            service.DisableBuildCheck = true;

            driver = new ChromeDriver(service);
            driver.Quit(); // Close the Service log file before reading
            var expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check";
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains(expected)));
        }

        private string GetLogLocation()
        {
            if (_logLocation == null || !File.Exists(_logLocation))
            {
                _logLocation = Path.GetTempFileName();
            }

            return _logLocation;
        }

        private static string GetChromeLocation()
        {
            var options = new ChromeOptions
            {
                BrowserVersion = "stable"
            };
            DriverFinder.FullPath(options);
            return options.BinaryLocation;
        }
    }
}