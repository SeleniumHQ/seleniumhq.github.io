using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;

namespace SeleniumDocs.Bidirectional
{
    [TestClass]
    public class BidiApiTest : BaseChromeTest
    {
        [TestMethod]
        public async Task InterceptNetworkForAuthentication()
        {
            var handler = new NetworkAuthenticationHandler()
            {
                UriMatcher = _ => true,
                Credentials = new PasswordCredentials("admin", "admin")
            };

            INetwork networkInterceptor = driver.Manage().Network;
            networkInterceptor.AddAuthenticationHandler(handler);

            await networkInterceptor.StartMonitoring();
            driver.Navigate().GoToUrl("https://the-internet.herokuapp.com/basic_auth");
            await networkInterceptor.StopMonitoring();

            Assert.AreEqual("Congratulations! You must have the proper credentials.", driver.FindElement(By.TagName("p")).Text);

        }

        [TestMethod]
        public async Task InterceptNetworkResponse()
        {
            var handler = new NetworkResponseHandler()
            {
                ResponseMatcher = httpresponse => true,
                ResponseTransformer = http => new()
                {
                    StatusCode = 200,
                    Body = "Creamy, delicious cheese!"
                }
            };

            INetwork networkInterceptor = driver.Manage().Network;
            networkInterceptor.AddResponseHandler(handler);

            await networkInterceptor.StartMonitoring();
            driver.Navigate().GoToUrl("https://www.selenium.dev");
            await networkInterceptor.StopMonitoring();

            StringAssert.Contains(driver.PageSource, "delicious cheese");
        }

        [TestMethod]
        public async Task InterceptNetworkRequest()
        {
            var handler = new NetworkRequestHandler()
            {
                RequestMatcher = httprequest => true,
                ResponseSupplier = http => new()
                {
                    StatusCode = 200,
                    Body = "Creamy, delicious cheese!"
                }
            };

            INetwork networkInterceptor = driver.Manage().Network;
            networkInterceptor.AddRequestHandler(handler);

            await networkInterceptor.StartMonitoring();
            driver.Navigate().GoToUrl("https://www.selenium.dev");
            await networkInterceptor.StopMonitoring();

            StringAssert.Contains(driver.PageSource, "delicious cheese");
        }
    }
}
