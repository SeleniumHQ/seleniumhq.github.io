using System.Globalization;
using System.IO;
using System.Reflection;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;

namespace SeleniumDocs.Bidirectional
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

            INetwork networkInterceptor = driver.Manage().Network;
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
            driver.Url = "https://www.selenium.dev/selenium/web/javascriptPage.html";

            JavaScriptEngine engine = new JavaScriptEngine(driver);
            PinnedScript isDisplayed = await engine.PinScript(IsDisplayedScript());

            IWebElement hidden = driver.FindElement(By.Id("hiddenlink"));
            IWebElement visible = driver.FindElement(By.Id("visibleSubElement"));

            var isVisibleDisplayed = ((WebDriver)driver).ExecuteScript(isDisplayed, visible);
            var isHiddenDisplayed = ((WebDriver)driver).ExecuteScript(isDisplayed, hidden);

            Assert.IsTrue((bool)isVisibleDisplayed);
            Assert.IsFalse((bool)isHiddenDisplayed);
        }

        [TestMethod]
        public async Task InterceptNetworkResponse()
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

            StringAssert.Contains(driver.PageSource, "delicious cheese");
        }

        [TestMethod]
        public async Task InterceptNetworkRequest()
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

        private string IsDisplayedScript()
        {
            string atom = string.Empty;
            Assembly webdriverDll = typeof(IWebDriver).Assembly;
            
            using (Stream atomStream = webdriverDll.GetManifestResourceStream("is-displayed.js"))
            {
                using (StreamReader atomReader = new StreamReader(atomStream))
                {
                    atom = atomReader.ReadToEnd();
                }
            }

            string wrappedAtom = string.Format(CultureInfo.InvariantCulture,
                "/* is-displayed */return ({0}).apply(null, arguments);", atom);
            return wrappedAtom;
        }
    }
}