using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;

namespace SeleniumDocs.ChromeDevTools
{
    [TestClass]
    public class NetworkInterceptorTest : BaseTest
    {
        [TestMethod]
        public void InterceptNetworkForAuthentication()
        {
            var handler = new NetworkAuthenticationHandler()
            {
                UriMatcher = _ => true,
                Credentials = new PasswordCredentials("admin", "admin")
            };
            INetwork networkInterceptor = driver.Manage().Network;
            networkInterceptor.AddAuthenticationHandler(handler);
            networkInterceptor.StartMonitoring().Wait();
            driver.Navigate().GoToUrl("https://the-internet.herokuapp.com/basic_auth");
            networkInterceptor.StopMonitoring().Wait();

            Assert.AreEqual("Congratulations! You must have the proper credentials.", driver.FindElement(By.TagName("p")).Text);

        }

        [TestMethod]
        public void InterceptNetworkResponse()
        {
            var handler = new NetworkResponseHandler();
            handler.ResponseMatcher = httpresponse => true;
            handler.ResponseTransformer = http =>
            {
                var response = new HttpResponseData();
                response.StatusCode = 200;
                response.Body = "Creamy, delicious cheese!";
                return response;
            };

            INetwork networkInterceptor = driver.Manage().Network;
            networkInterceptor.AddResponseHandler(handler);
            networkInterceptor.StartMonitoring().Wait();
            driver.Navigate().GoToUrl("http://google.com");
            networkInterceptor.StopMonitoring().Wait();

            Assert.IsTrue(driver.PageSource.Contains("delicious cheese"));
        }

        [TestMethod]
        public void InterceptNetworkRequest()
        {
            var handler = new NetworkRequestHandler();
            handler.RequestMatcher = httprequest => true;
            handler.ResponseSupplier = http =>
                    {
                        var response = new HttpResponseData();
                        response.StatusCode = 200;
                        response.Body = "Creamy, delicious cheese!";
                        return response;
                    };

            INetwork networkInterceptor = driver.Manage().Network;
            networkInterceptor.AddRequestHandler(handler);

            networkInterceptor.StartMonitoring().Wait();
            driver.Navigate().GoToUrl("https://google.com");
            networkInterceptor.StopMonitoring().Wait();

            Assert.IsTrue(driver.PageSource.Contains("delicious cheese"));
        }

    }
}