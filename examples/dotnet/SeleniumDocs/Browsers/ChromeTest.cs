using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Browsers {
  [TestClass]
  public class ChromeTest {
    [TestMethod]
    public void BasicOptions() {
      var options = new ChromeOptions();
      var driver = new ChromeDriver(options);
      driver.Quit();
    }

    [TestMethod]
    public void HeadlessOptions() {
      var options = new ChromeOptions();
      options.AddArgument("--headless=new");
      var driver = new ChromeDriver(options);
      driver.Quit();
    }
  }
}