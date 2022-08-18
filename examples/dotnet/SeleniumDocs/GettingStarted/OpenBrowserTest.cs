using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using OpenQA.Selenium.Safari;

namespace SeleniumDocs.GettingStarted
{
    [TestClass]
    public class OpenBrowserTest
    {

        [TestMethod]
        public void ChromeSession()
        {
            var options = new ChromeOptions();
            var driver = new ChromeDriver(options);

            driver.Quit();
        }

        [Ignore] // WebDriverManager.Net does not resolve msedgedriver in Linux
        [TestMethod]
        public void EdgeSession()
        {
            var options = new EdgeOptions();
            var driver = new EdgeDriver(options);

            driver.Quit();
        }

        [TestMethod]
        public void FirefoxSession()
        {
            var options = new FirefoxOptions();
            var driver = new FirefoxDriver(options);

            driver.Quit();
        }

        [Ignore] // Only runs on Windows
        [TestMethod]
        public void InternetExplorerSession()
        {
            var options = new InternetExplorerOptions();
            var driver = new InternetExplorerDriver(options);

            driver.Quit();
        }

        [Ignore] // Only runs on Windows
        [TestMethod]
        public void InternetExplorerCompatibilitySession()
        {
            var options = new InternetExplorerOptions
            {
                AttachToEdgeChrome = true,
                EdgeExecutablePath = "/path/to/edge/browser"
            };
            var driver = new InternetExplorerDriver(options);

            driver.Quit();
        }

        [Ignore] // Non-standard browser
        [TestMethod]
        public void OperaSession()
        {
            var options = new ChromeOptions
            {
                BinaryLocation = "/path/to/opera/browser"
            };
            var driver = new ChromeDriver(options);

            driver.Quit();
        }

        [Ignore] // Only runs on Mac
        [TestMethod]
        public void SafariSession()
        {
            var options = new SafariOptions();
            var driver = new SafariDriver(options);

            driver.Quit();
        }
    }
}
