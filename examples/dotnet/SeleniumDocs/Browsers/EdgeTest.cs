using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Edge;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class EdgeTest
    {
        [TestMethod]
        public void BasicOptions()
        {
            var options = new EdgeOptions();
            var driver = new EdgeDriver(options);
            driver.Quit();
        }

        [TestMethod]
        public void HeadlessOptions() 
        {
            var options = new EdgeOptions();
            options.AddArgument("--headless=new");
            var driver = new EdgeDriver(options);
            driver.Quit();
        }
    }
}