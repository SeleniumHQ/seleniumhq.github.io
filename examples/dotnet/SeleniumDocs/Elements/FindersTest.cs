using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;

namespace SeleniumDocs.Elements
{
    [TestClass]
    public class FindersTest : BaseTest
    {
        private const string LocatorsPage = "https://www.selenium.dev/selenium/web/locators_tests/locators.html";

        [TestMethod]
        public void FindsFirstMatchingElement()
        {
            StartDriver();
            driver.Url = LocatorsPage;
            IWebElement firstInput = driver.FindElement(By.ClassName("information"));

            Assert.AreEqual("fname", firstInput.GetAttribute("id"));
        }

        [TestMethod]
        public void FindsElementWithinASubsetOfTheDom()
        {
            StartDriver();
            driver.Url = LocatorsPage;
            IWebElement form = driver.FindElement(By.TagName("form"));
            IWebElement input = form.FindElement(By.ClassName("information"));

            Assert.AreEqual("fname", input.GetAttribute("id"));
        }

        [TestMethod]
        public void UsesAnOptimizedLocator()
        {
            StartDriver();
            driver.Url = LocatorsPage;
            IWebElement input = driver.FindElement(By.CssSelector("form .information"));

            Assert.AreEqual("fname", input.GetAttribute("id"));
        }

        [TestMethod]
        public void FindsAllMatchingElements()
        {
            StartDriver();
            driver.Url = LocatorsPage;
            var inputs = driver.FindElements(By.TagName("input"));

            Assert.IsTrue(inputs.Count > 1);
        }

        [TestMethod]
        public void GetsElementFromACollection()
        {
            StartDriver();
            driver.Url = LocatorsPage;

            var elements = driver.FindElements(By.TagName("p"));
            foreach (var element in elements)
            {
                System.Console.WriteLine("Paragraph text:" + element.Text);
            }

            Assert.IsTrue(elements.Count > 0);
        }

        [TestMethod]
        public void FindsElementsFromElement()
        {
            StartDriver();
            driver.Url = LocatorsPage;

            IWebElement form = driver.FindElement(By.TagName("form"));
            var elements = form.FindElements(By.TagName("input"));
            foreach (var e in elements)
            {
                System.Console.WriteLine(e.GetAttribute("value"));
            }

            Assert.IsTrue(elements.Count > 0);
        }

        [TestMethod]
        public void GetsActiveElement()
        {
            StartDriver();
            driver.Url = LocatorsPage;

            driver.FindElement(By.CssSelector("#fname")).SendKeys("webElement");
            string attr = driver.SwitchTo().ActiveElement().GetAttribute("name");

            Assert.AreEqual("fname", attr);
        }
    }
}
