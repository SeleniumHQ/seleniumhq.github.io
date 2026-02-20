using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using SeleniumDocs.TestSupport;

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

        [TestMethodCustom]
        [EnabledOnOs("OSX")]
        public async Task DriverLocation()
        {
            var options = GetLatestChromeOptions();
            var service = ChromeDriverService.CreateDefaultService(await GetDriverLocationAsync(options));

            driver = new ChromeDriver(service, options);
        }

        [TestMethod]
        public void DriverPort()
        {
            var service = ChromeDriverService.CreateDefaultService();
            service.Port = 1234;

            driver = new ChromeDriver(service);
        }
        
        private static async Task<string> GetDriverLocationAsync(ChromeOptions options)
        {
            return await new DriverFinder(options).GetDriverPathAsync();
        }

        private static ChromeOptions GetLatestChromeOptions()
        {
            return new ChromeOptions
            {
                BrowserVersion = "stable"
            };
        }
    }
}