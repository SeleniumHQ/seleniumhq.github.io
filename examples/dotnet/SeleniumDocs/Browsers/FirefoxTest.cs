using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Internal.Logging;

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
            if (!String.IsNullOrEmpty(_logLocation) && File.Exists(_logLocation))
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
        public void LogsToFile()
        {
            var service = FirefoxDriverService.CreateDefaultService();
            service.LogPath = GetLogLocation();

            driver = new FirefoxDriver(service);
            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("geckodriver	INFO	Listening on")));
        }

        [TestMethod]
        public void LogsToConsole()
        {
            TestLogHandler testLogHandler = new TestLogHandler();
            ResetGlobalLog();
            try
            {
                Log.SetLevel(LogEventLevel.Trace).Handlers.Add(testLogHandler);
                var service = FirefoxDriverService.CreateDefaultService();
                driver = new FirefoxDriver(service);
                Assert.IsTrue(testLogHandler.Events.Count >= 1);
                Assert.IsTrue(testLogHandler.Events.Any(e => e.Message.Contains("geckodriver")));
            }
            finally
            {
                ResetGlobalLog();
            }
        }

        [TestMethod]
        public void LogsLevel()
        {
            var service = FirefoxDriverService.CreateDefaultService();
            service.LogPath = GetLogLocation();
            service.LogLevel = FirefoxDriverLogLevel.Debug;

            driver = new FirefoxDriver(service);
            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("Marionette\tDEBUG")));
        }

        [TestMethod]
        public void StopsTruncatingLogs()
        {
            var service = FirefoxDriverService.CreateDefaultService();
            service.LogTruncate = false;

            service.LogLevel = FirefoxDriverLogLevel.Debug;

            driver = new FirefoxDriver(service);
            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(GetLogLocation());
            Assert.IsNull(lines.FirstOrDefault(line => line.Contains(" ... ")));
        }

        [TestMethod]
        public void SetProfileLocation()
        {
            var service = FirefoxDriverService.CreateDefaultService();
            service.ProfileRoot = GetTempDirectory();

            driver = new FirefoxDriver(service);

            string profile = (string)driver.Capabilities.GetCapability("moz:profile");
            string[] directories = profile.Split("/");
            var dirName = directories.Last();
            Assert.AreEqual(GetTempDirectory() + dirName, profile);
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
            if (string.IsNullOrEmpty(_logLocation) && !File.Exists(_logLocation))
            {
                _logLocation = Path.GetTempFileName();
            }

            return _logLocation;
        }

        private string GetTempDirectory()
        {
            if (string.IsNullOrEmpty(_tempPath) && !File.Exists(_tempPath))
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
            return new DriverFinder(options).GetBrowserPath();
        }

        private void ResetGlobalLog()
        {
            Log.SetLevel(LogEventLevel.Info);
            Log.Handlers.Clear().Handlers.Add(new TextWriterHandler(Console.Error));
        }
    }
}

class TestLogHandler : ILogHandler
{
    public ILogHandler Clone()
    {
        return this;
    }

    public void Handle(LogEvent logEvent)
    {
        Events.Add(logEvent);
    }

    public IList<LogEvent> Events { get; internal set; } = new List<LogEvent>();
}