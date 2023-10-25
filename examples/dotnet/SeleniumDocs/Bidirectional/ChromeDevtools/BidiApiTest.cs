using System;
using System.Collections;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.IdentityModel.Tokens;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.Bidirectional.ChromeDevTools
{
    [TestClass]
    public class BidiApiTest : BaseChromeTest
    {
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
        public async Task PinScript()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/xhtmlTest.html";
            var element = driver.FindElement(By.Id("id1"));

            var key = await new JavaScriptEngine(driver).PinScript("return arguments;");

            var arguments = ((WebDriver)driver).ExecuteScript(key, 1, true, element);

            var expected = new List<object>
            {
                1L,
                true,
                element
            };
            CollectionAssert.AreEqual(expected, (ICollection)arguments);
        }

        [TestMethod]
        public async Task MutatedElements()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";

            var mutations = new List<IWebElement>();
            using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
            monitor.DomMutated += (_, e) =>
            {
                var locator = By.CssSelector($"*[data-__webdriver_id='{e.AttributeData.TargetId}']");
                mutations.Add(driver.FindElement(locator));
            };

            await monitor.StartEventMonitoring();
            await monitor.EnableDomMutationMonitoring();

            driver.FindElement(By.Id("reveal")).Click();
            
            new WebDriverWait(driver, TimeSpan.FromSeconds(5)).Until(_ => !mutations.IsNullOrEmpty());
            await monitor.DisableDomMutationMonitoring();
            monitor.StopEventMonitoring();

            var revealed = driver.FindElement(By.Id("revealed")); 
            Assert.AreEqual(revealed, mutations[0]);
        }

        [TestMethod]
        public async Task ConsoleLogs()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";

            using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
            var messages = new List<string>();
            monitor.JavaScriptConsoleApiCalled += (_, e) =>
            {
                messages.Add(e.MessageContent);
            };

            await monitor.StartEventMonitoring();
            driver.FindElement(By.Id("consoleLog")).Click();
            driver.FindElement(By.Id("consoleError")).Click();
            new WebDriverWait(driver, TimeSpan.FromSeconds(5)).Until(_ => messages.Count > 1);
            monitor.StopEventMonitoring();

            Assert.IsTrue(messages.Contains("Hello, world!"));
            Assert.IsTrue(messages.Contains("I am console error"));
        }
        
        [TestMethod]
        public async Task JsErrors()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";

            using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
            var messages = new List<string>();
            monitor.JavaScriptExceptionThrown += (_, e) =>
            {
                messages.Add(e.Message);
            };

            await monitor.StartEventMonitoring();
            driver.FindElement(By.Id("jsException")).Click();
            new WebDriverWait(driver, TimeSpan.FromSeconds(5)).Until(_ => !messages.IsNullOrEmpty());
            monitor.StopEventMonitoring();

            Assert.IsTrue(messages.Contains("Uncaught"));
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
    }
}