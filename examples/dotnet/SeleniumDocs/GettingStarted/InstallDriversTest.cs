using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using SeleniumDocs.TestSupport;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

namespace SeleniumDocs.GettingStarted
{
    [TestClassCustom]
    public class InstallDriversTest
    {
        [TestMethod]
        public void ChromeSession()
        {
            new DriverManager().SetUpDriver(new ChromeConfig());

            var driver = new ChromeDriver();

            driver.Quit();
        }

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

        [EnabledOnOs("WINDOWS")]
        [TestMethod]
        public void InternetExplorerSession()
        {
            new DriverManager().SetUpDriver(new InternetExplorerConfig());

            var driver = new InternetExplorerDriver();

            driver.Quit();
        }
    }
}