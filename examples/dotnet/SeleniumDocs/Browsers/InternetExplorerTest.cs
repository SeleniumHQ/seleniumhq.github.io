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
        [TestInitialize]
        public void ReferenceHardCodedDriver()
        {
            var hardCodedPath = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "../../../");
            var path = Path.GetFullPath(hardCodedPath);
            Environment.SetEnvironmentVariable("EDGE_DRIVER_PATH", path);
        }

        [TestMethod]
        public void BasicOptions()
        {
            var driverPath = Environment.GetEnvironmentVariable("EDGE_DRIVER_PATH");
            var service = InternetExplorerDriverService.CreateDefaultService(driverPath);
            var options = new InternetExplorerOptions
            {
                IgnoreZoomLevel = true,
                AttachToEdgeChrome = true,
                EdgeExecutablePath = Environment.GetEnvironmentVariable("EDGE_PATH")
            };
            var driver = new InternetExplorerDriver(service, options);

            driver.Quit();
        }
    }
}