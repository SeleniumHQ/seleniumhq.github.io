using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs
{
    public class BaseChromeTest : BaseTest
    {
        [TestInitialize]
        public void AutoStartDriver()
        {
            StartDriver();
        }
    }
}