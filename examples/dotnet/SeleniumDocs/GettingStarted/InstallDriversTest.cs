using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

namespace SeleniumDocs.GettingStarted
{
    [TestClass]
    public class InstallDriversTest
    {
        [TestMethod]
        public void ChromeSession()
        {
            new DriverManager().SetUpDriver(new ChromeConfig());

            var driver = new ChromeDriver();

            driver.Quit();
        }

        [Ignore] // WebDriverManager.Net does not resolve msedgedriver in Linux
        [TestMethod]
        public void EdgeSession()
        {
            new DriverManager().SetUpDriver(new EdgeConfig());

            var driver = new EdgeDriver();

            driver.Quit();
        }

        [TestMethod]
        public void FirefoxSession()
        {
            new DriverManager().SetUpDriver(new FirefoxConfig());

            var driver = new FirefoxDriver();

            driver.Quit();
        }

        [Ignore] // Only runs on Windows
        [TestMethod]
        public void InternetExplorerSession()
        {
            new DriverManager().SetUpDriver(new InternetExplorerConfig());

            var driver = new InternetExplorerDriver();

            driver.Quit();
        }
    }
}