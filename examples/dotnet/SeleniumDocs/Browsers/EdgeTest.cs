using System;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chromium;
using OpenQA.Selenium.Edge;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class EdgeTest
    {
        private EdgeDriver driver;
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
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");
            driver = new EdgeDriver(options);
        }

        [TestMethod]
        public void Arguments()
        {
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");

            options.AddArgument("--start-maximized");

            driver = new EdgeDriver(options);
        }

        [TestMethod]
        public async Task SetBrowserLocation()
        {
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");

            options.BinaryLocation = await GetEdgeLocationAsync();
    
            driver = new EdgeDriver(options);
        }

        [TestMethod]
        public void InstallExtension()
        {
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");
            var baseDir = AppDomain.CurrentDomain.BaseDirectory;
            var extensionFilePath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example.crx");

            options.AddExtension(extensionFilePath);

            driver = new EdgeDriver(options);

            driver.Url = "https://www.selenium.dev/selenium/web/blank.html";

            IWebElement injected = driver.FindElement(By.Id("webextensions-selenium-example"));
            Assert.AreEqual("Content injected by webextensions-selenium-example", injected.Text);
        }

        [TestMethod]
        public void ExcludeSwitch()
        {
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");

            options.AddExcludedArgument("disable-popup-blocking");

            driver = new EdgeDriver(options);
        }

        [TestMethod]
        public void LogsToFile()
        {
            var service = EdgeDriverService.CreateDefaultService();
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");

            service.LogPath = GetLogLocation();

            driver = new EdgeDriver(service, options);
            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("Starting Microsoft Edge WebDriver")));
        }

        [TestMethod]
        public void LogsLevel()
        {
            var service = EdgeDriverService.CreateDefaultService();
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");
            service.LogPath = GetLogLocation();

            service.LogLevel = ChromiumDriverLogLevel.Debug;

            driver = new EdgeDriver(service, options);

            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("[DEBUG]:")));
        }

        [TestMethod]
        public void ConfigureDriverLogs()
        {
            var service = EdgeDriverService.CreateDefaultService();
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");
            service.LogPath = GetLogLocation();
            service.EnableVerboseLogging = true;

            service.EnableAppendLog = true;
            service.ReadableTimestamp = true;

            driver = new EdgeDriver(service, options);

            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            var regex = new Regex(@"\[\d\d-\d\d-\d\d\d\d \d\d:\d\d:\d\d\.\d+\]");
            Assert.IsNotNull(lines.FirstOrDefault(line => regex.Matches(line).Count > 0));
        }

        [TestMethod]
        public void DisableBuildCheck()
        {
            var service = EdgeDriverService.CreateDefaultService();
            var options = new EdgeOptions();
            options.AddArgument("--no-sandbox");
            service.LogPath = GetLogLocation();
            service.EnableVerboseLogging = true;

            service.DisableBuildCheck = true;

            driver = new EdgeDriver(service, options);
            driver.Quit(); // Close the Service log file before reading
            var expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check";
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains(expected)));
        }

        private string GetLogLocation()
        {
            if (string.IsNullOrEmpty(_logLocation) && !File.Exists(_logLocation))
            {
                _logLocation = Path.GetTempFileName();
            }

            return _logLocation;
        }

        private static async Task<string> GetEdgeLocationAsync()
        {
            var options = new EdgeOptions
            {
                BrowserVersion = "stable"
            };
            return await new DriverFinder(options).GetBrowserPathAsync();
        }
    }
}
