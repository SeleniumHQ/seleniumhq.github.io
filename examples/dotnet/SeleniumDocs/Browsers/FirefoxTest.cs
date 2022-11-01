using System;
using System.IO;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class FirefoxTest
    {
        private FirefoxDriver driver;

        [TestCleanup]
        public void QuitDriver()
        {
            driver.Quit();
        }

        [TestInitialize]
        public void SetupDriver()
        {
            new DriverManager().SetUpDriver(new FirefoxConfig());
        }

        [TestMethod]
        public void BasicOptions()
        {
            var options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }

        [TestMethod]
        public void InstallAddon()
        {
            driver = new FirefoxDriver();

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
             string extensionFilePath = Path.Combine(baseDir, "../../../extensions/webextensions-selenium-example.xpi");
             string extensionId = driver.InstallAddOnFromFile(Path.GetFullPath(extensionFilePath));
             driver.UninstallAddOn(extensionId);

             driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
             Assert.AreEqual(driver.FindElements(By.Id("webextensions-selenium-example")).Count, 0);
         }

         [TestMethod]
         public void InstallUnsignedAddon()
         {
             driver = new FirefoxDriver();

             string baseDir = AppDomain.CurrentDomain.BaseDirectory;
             string extensionDirPath = Path.Combine(baseDir, "../../../extensions/webextensions-selenium-example/");
             driver.InstallAddOnFromDirectory(Path.GetFullPath(extensionDirPath), true);

             driver.Url = "https://www.selenium.dev/selenium/web/blank.html";

             IWebElement injected = driver.FindElement(By.Id("webextensions-selenium-example"));
             Assert.AreEqual("Content injected by webextensions-selenium-example", injected.Text);
         }
    }
}