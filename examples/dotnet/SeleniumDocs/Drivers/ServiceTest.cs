using System;
using System.IO;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;

namespace SeleniumDocs.Drivers
{
    [TestClass]
    public class ServiceTest : BaseTest
    {
        [TestMethod]
        public void BasicService()
        {
            var service = ChromeDriverService.CreateDefaultService();
            driver = new ChromeDriver(service);
        }

        [TestMethod]
        public void DriverLocation()
        {
            var service = ChromeDriverService.CreateDefaultService(GetDriverLocation());

            driver = new ChromeDriver(service);
        }

        [TestMethod]
        public void DriverPort()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.Port = 1234;

            driver = new ChromeDriver(service);
        }
        
        private string GetDriverLocation()
        {
            return DriverFinder.FullPath(new ChromeOptions());
        }
    }
}