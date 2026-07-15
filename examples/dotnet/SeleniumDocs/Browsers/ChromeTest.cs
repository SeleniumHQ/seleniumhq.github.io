using System;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.WebExtension;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Chromium;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class ChromeTest
    {
        private ChromeDriver driver;
        private string _logLocation;

        [TestInitialize]
        public void Initialize()
        {
            _logLocation = Path.GetTempFileName();
        }

        [TestCleanup]
        public void Cleanup()
        {
            driver?.Quit();
            if (_logLocation != null && File.Exists(_logLocation))
            {
                File.Delete(_logLocation);
            }
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
        public async Task SetBrowserLocation()
        {
            string userDataDir = Path.Combine(Path.GetTempPath(), Path.GetRandomFileName());
            Directory.CreateDirectory(userDataDir);
            var options = new ChromeOptions();
            options.AddArgument($"--user-data-dir={userDataDir}");
            options.AddArgument("--no-sandbox");
            options.AddArgument("--disable-dev-shm-usage");

            options.BinaryLocation = await GetChromeLocationAsync();

            driver = new ChromeDriver(options);
        }

        [TestMethod]
        public async Task InstallExtension()
        {
            if (RuntimeInformation.IsOSPlatform(OSPlatform.Windows))
            {
                Assert.Inconclusive("Extension install via BiDi is not supported on Windows");
                return;
            }

            var options = new ChromeOptions();
            options.UseWebSocketUrl = true;
            options.AddArgument("--remote-debugging-pipe");
            options.AddArgument("--enable-unsafe-extension-debugging");

            driver = new ChromeDriver(options);

            var baseDir = AppDomain.CurrentDomain.BaseDirectory;
            var extensionDir = Path.GetFullPath(Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example"));

            var bidi = await driver.AsBiDiAsync();
            await bidi.WebExtension.InstallAsync(new ExtensionPath(extensionDir));

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

            service.LogPath = _logLocation;

            driver = new ChromeDriver(service);
            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(_logLocation);
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("Starting ChromeDriver")));
        }

        [TestMethod]
        public void LogsLevel()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.LogPath = _logLocation;

            service.LogLevel = ChromiumDriverLogLevel.Debug;

            driver = new ChromeDriver(service);

            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(_logLocation);
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains("[DEBUG]:")));
        }

        [TestMethod]
        public void ConfigureDriverLogs()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.LogPath = _logLocation;
            service.EnableVerboseLogging = true;

            service.EnableAppendLog = true;
            service.ReadableTimestamp = true;

            driver = new ChromeDriver(service);

            driver.Quit(); // Close the Service log file before reading
            var lines = File.ReadLines(_logLocation);
            var regex = new Regex(@"\[\d\d-\d\d-\d\d\d\d \d\d:\d\d:\d\d\.\d+\]");
            Assert.IsNotNull(lines.FirstOrDefault(line => regex.Matches(line).Count > 0));
        }

        [TestMethod]
        public void DisableBuildCheck()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.LogPath = _logLocation;
            service.EnableVerboseLogging = true;

            service.DisableBuildCheck = true;

            driver = new ChromeDriver(service);
            driver.Quit(); // Close the Service log file before reading
            var expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check";
            var lines = File.ReadLines(_logLocation);
            Assert.IsNotNull(lines.FirstOrDefault(line => line.Contains(expected)));
        }

        private static async Task<string> GetChromeLocationAsync()
        {
            var options = new ChromeOptions
            {
                BrowserVersion = "stable"
            };
            return await new DriverFinder(options).GetBrowserPathAsync();
        }
    }
}
