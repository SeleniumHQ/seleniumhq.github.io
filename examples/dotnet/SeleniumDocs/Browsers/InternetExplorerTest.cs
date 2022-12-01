using System;
using System.IO;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.IE;
using SeleniumDocs.TestSupport;

namespace SeleniumDocs.Browsers
{
    [TestClassCustom]
    [EnabledOnOs("WINDOWS")]
    public class InternetExplorerTest
    {
        [TestMethod]
        public void BasicOptions()
        {
            var options = new InternetExplorerOptions();
            var driver = new InternetExplorerDriver(options);

            driver.Quit();
        }
    }
}