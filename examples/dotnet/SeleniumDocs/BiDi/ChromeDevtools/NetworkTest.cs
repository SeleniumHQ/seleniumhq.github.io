using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.DevTools;
using System.Linq;
using OpenQA.Selenium.DevTools.V126.Network;
using OpenQA.Selenium.DevTools.V126.Performance;


namespace SeleniumDocs.BiDi.ChromeDevTools
{
    [TestClass]
    public class NetworkTest : BaseTest
    {
        [TestInitialize]
        public void Startup()
        {
            StartDriver("126");
        }

        [TestMethod]
        public async Task BasicAuthentication()
        {
            var handler = new NetworkAuthenticationHandler()
            {
                UriMatcher = uri => uri.AbsoluteUri.Contains("herokuapp"),
                Credentials = new PasswordCredentials("admin", "admin")
            };

            var networkInterceptor = driver.Manage().Network;
            networkInterceptor.AddAuthenticationHandler(handler);

            await networkInterceptor.StartMonitoring();
            driver.Navigate().GoToUrl("https://the-internet.herokuapp.com/basic_auth");
            await networkInterceptor.StopMonitoring();

            Assert.AreEqual("Congratulations! You must have the proper credentials.",
                driver.FindElement(By.TagName("p")).Text);
        }
        
        [TestMethod]
        public async Task RecordNetworkResponse()
        {
            var contentType = new List<string>();

            INetwork networkInterceptor = driver.Manage().Network;
            networkInterceptor.NetworkResponseReceived += (_, e)  =>
            {
                contentType.Add(e.ResponseHeaders["content-type"]);
            };

            await networkInterceptor.StartMonitoring();
            driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/blank.html");
            await networkInterceptor.StopMonitoring();

            Assert.AreEqual("text/html; charset=utf-8", contentType[0]);
        }

        [TestMethod]
        public async Task TransformNetworkResponse()
        {
            var handler = new NetworkResponseHandler()
            {
                ResponseMatcher = _ => true,
                ResponseTransformer = _ => new HttpResponseData
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

            var body = driver.FindElement(By.TagName("body"));
            Assert.AreEqual("Creamy, delicious cheese!", body.Text);
        }

        [TestMethod]
        public async Task TransformNetworkRequest()
        {
            var handler = new NetworkRequestHandler
            {
                RequestMatcher = request => request.Url.Contains("one.js"),
                RequestTransformer = request =>
                {
                    request.Url = request.Url.Replace("one", "two");

                    return request;
                }
            };

            INetwork networkInterceptor = driver.Manage().Network;
            networkInterceptor.AddRequestHandler(handler);

            await networkInterceptor.StartMonitoring();
            driver.Url = "https://www.selenium.dev/selenium/web/devToolsRequestInterceptionTest.html";
            driver.FindElement(By.TagName("button")).Click();
            await networkInterceptor.StopMonitoring();

            Assert.AreEqual("two", driver.FindElement(By.Id("result")).Text);
        }
        
        [TestMethod]
        public async Task SetCookie()
        {
            var session = ((IDevTools)driver).GetDevToolsSession();
            var domains = session.GetVersionSpecificDomains<OpenQA.Selenium.DevTools.V126.DevToolsSessionDomains>();
            await domains.Network.Enable(new OpenQA.Selenium.DevTools.V126.Network.EnableCommandSettings());

            var cookieCommandSettings = new SetCookieCommandSettings
            {
                Name = "cheese",
                Value = "gouda",
                Domain = "www.selenium.dev",
                Secure = true
            };

            await domains.Network.SetCookie(cookieCommandSettings);

            driver.Url = "https://www.selenium.dev";
            OpenQA.Selenium.Cookie cheese = driver.Manage().Cookies.GetCookieNamed("cheese");
            Assert.AreEqual("gouda", cheese.Value);
        }

        [TestMethod]
        public async Task PerformanceMetrics()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/frameset.html";

            var session = ((IDevTools)driver).GetDevToolsSession();
            var domains = session.GetVersionSpecificDomains<OpenQA.Selenium.DevTools.V126.DevToolsSessionDomains>();
            await domains.Performance.Enable(new OpenQA.Selenium.DevTools.V126.Performance.EnableCommandSettings());

            var metricsResponse =
                await session.SendCommand<GetMetricsCommandSettings, GetMetricsCommandResponse>(
                    new GetMetricsCommandSettings()
                );

            var metrics = metricsResponse.Metrics.ToDictionary(
                dict => dict.Name,
                dict => dict.Value
            );

            Assert.IsTrue(metrics["DevToolsCommandDuration"] > 0);
            Assert.AreEqual(12, metrics["Frames"]);
        }

    }
}