using System;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
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
            driver = new EdgeDriver(options);
        }

        [TestMethod]
        public void Arguments()
        {
            var options = new EdgeOptions();

            options.AddArgument("--start-maximized");
    
            driver = new EdgeDriver(options);
        }

        [TestMethod]
        public void SetBrowserLocation()
        {
            var options = new EdgeOptions();

            options.BinaryLocation = GetEdgeLocation();
    
            driver = new EdgeDriver(options);
        }

        [TestMethod]
        public void InstallExtension()
        {
            var options = new EdgeOptions();
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

            options.AddExcludedArgument("disable-popup-blocking");

            driver = new EdgeDriver(options);
        }

        [TestMethod]
        public void LogsToFile()
        {
            var service = EdgeDriverService.CreateDefaultService();

            service.LogPath = GetLogLocation();

            driver = new EdgeDriver(service);
            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("Starting Microsoft Edge WebDriver")));
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void LogsToConsole()
        {
            var stringWriter = new StringWriter();
            var originalOutput = Console.Out;
            Console.SetOut(stringWriter);

            var service = EdgeDriverService.CreateDefaultService();

            //service.LogToConsole = true;

            driver = new EdgeDriver(service);

            Assert.IsTrue(stringWriter.ToString().Contains("Starting Microsoft Edge WebDriver"));
            Console.SetOut(originalOutput);
            stringWriter.Dispose();
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void LogsLevel()
        {
            var service = EdgeDriverService.CreateDefaultService();
            service.LogPath = GetLogLocation();

            // service.LogLevel = ChromiumDriverLogLevel.Debug 

            driver = new EdgeDriver(service);

            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("[DEBUG]:")));
        }

        [TestMethod]
        [Ignore("Not implemented")]
        public void ConfigureDriverLogs()
        {
            var service = EdgeDriverService.CreateDefaultService();
            service.LogPath = GetLogLocation();
            service.EnableVerboseLogging = true;

            service.EnableAppendLog = true;
            // service.readableTimeStamp = true;

            driver = new EdgeDriver(service);

            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            var regex = new Regex(@"\[\d\d-\d\d-\d\d\d\d");
            Assert.IsNotNull(lines.FirstOrDefault(line => regex.Matches("").Count > 0));
        }

        [TestMethod]
        public void DisableBuildCheck()
        {
            var service = EdgeDriverService.CreateDefaultService();
            service.LogPath = GetLogLocation();
            service.EnableVerboseLogging = true;

            service.DisableBuildCheck = true;

            driver = new EdgeDriver(service);
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

        private static string GetEdgeLocation()
        {
            var options = new EdgeOptions
            {
                BrowserVersion = "stable"
            };
            DriverFinder.FullPath(options);
            return options.BinaryLocation;
        }
    }
}