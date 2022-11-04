using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs
{
    public class BaseFirefoxTest : BaseTest
    {
        [TestInitialize]
        public void CreateDriver()
        {
            driver = new ChromeDriver();
        }
    }
}