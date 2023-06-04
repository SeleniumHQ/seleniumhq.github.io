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
        private readonly string driverLocation = Environment.GetEnvironmentVariable("CHROMEWEBDRIVER") + "/chromedriver";

        [TestMethod]
        public void BasicService()
        {
            var service = ChromeDriverService.CreateDefaultService();
            driver = new ChromeDriver(service);
        }

        [TestMethod]
        public void DriverLocation()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.DriverServicePath = driverLocation;

            driver = new ChromeDriver(service);
        }

        [TestMethod]
        public void DriverPort()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.Port = 1234;

            driver = new ChromeDriver(service);
        }
    }
}