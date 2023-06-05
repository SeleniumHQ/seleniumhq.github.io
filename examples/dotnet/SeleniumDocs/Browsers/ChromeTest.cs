using System;
using System.IO;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Browsers {
  [TestClass]
  public class ChromeTest : BaseTest {
    [TestMethod]
    public void BasicOptions() {
      var options = new ChromeOptions();
      driver = new ChromeDriver(options);
    }

    [TestMethod]
    public void HeadlessOptions() {
      var options = new ChromeOptions();
      options.AddArgument("--headless=new");
      driver = new ChromeDriver(options);
    }
    
    [TestMethod]
    public void InstallAddon()
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
  }
}