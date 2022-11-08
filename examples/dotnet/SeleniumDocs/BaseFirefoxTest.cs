using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Firefox;

namespace SeleniumDocs
{
    public class BaseFirefoxTest : BaseTest
    {
        [TestInitialize]
        public void CreateDriver()
        {
            driver = new FirefoxDriver();
        }
    }
}