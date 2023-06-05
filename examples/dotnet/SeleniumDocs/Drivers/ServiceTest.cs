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
            var service = FirefoxDriverService.CreateDefaultService();
            driver = new FirefoxDriver(service);
        }

        [TestMethod]
        public void DriverLocation()
        {
            var path = Environment.GetEnvironmentVariable("GECKOWEBDRIVER") + "/geckodriver";
            var service = ChromeDriverService.CreateDefaultService(path);

            driver = new ChromeDriver(service);
        }

        [TestMethod]
        public void DriverPort()
        {
            var path = Environment.GetEnvironmentVariable("GECKOWEBDRIVER") + "/geckodriver";
            var service = FirefoxDriverService.CreateDefaultService();
            service.Port = 1234;

            driver = new FirefoxDriver(service);
        }

        [TestMethod]
        public void LogsToFile()
        {
            var service = ChromeDriverService.CreateDefaultService();
            var file = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "../../../selenium.log");

            service.LogPath = file;

            driver = new ChromeDriver(service);
            driver.Url = "https://www.selenium.dev/";
            
            var lines = File.ReadLines(file);
            Assert.IsTrue(lines.First().Contains("Starting ChromeDriver")); 
        }
    }
}