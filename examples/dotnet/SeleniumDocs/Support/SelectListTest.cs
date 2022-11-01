using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.Support
{
    [TestClass]
    public class SelectListTest : BaseTest
    {
        [TestInitialize]
        public void Navigate()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/formPage.html";
        }

        [TestMethod]
        public void SelectOption()
        {
            var selectElement = driver.FindElement(By.Name("selectomatic"));
            var select = new SelectElement(selectElement);

            var twoElement = driver.FindElement(By.CssSelector("option[value=two]"));
            var fourElement = driver.FindElement(By.CssSelector("option[value=four]"));
            var countElement = driver.FindElement(By.CssSelector("option[value='still learning how to count, apparently']"));

            select.SelectByText("Four");
            Assert.IsTrue(fourElement.Selected);

            select.SelectByValue("two");
            Assert.IsTrue(twoElement.Selected);

            select.SelectByIndex(3);
            Assert.IsTrue(countElement.Selected);

        }

        [TestMethod]
        public void SelectMultipleOption()
        {
            var selectElement = driver.FindElement(By.Name("multi"));
            var select = new SelectElement(selectElement);

            var hamElement = driver.FindElement(By.CssSelector("option[value=ham]"));
            var gravyElement = driver.FindElement(By.CssSelector("option[value='onion gravy']"));
            var eggElement = driver.FindElement(By.CssSelector("option[value=eggs]"));
            var sausageElement = driver.FindElement(By.CssSelector("option[value='sausages']"));

            IList<IWebElement> optionList = select.Options;
            IWebElement[] optionElements = selectElement.FindElements(By.TagName("option")).ToArray();
            CollectionAssert.AreEqual(optionElements, optionList.ToArray());

            IList<IWebElement> selectedOptionList = select.AllSelectedOptions;
            IWebElement[] expectedSelection = { eggElement, sausageElement };
            CollectionAssert.AreEqual(expectedSelection, selectedOptionList.ToArray());

            select.SelectByValue("ham");
            select.SelectByValue("onion gravy");
            Assert.IsTrue(hamElement.Selected);
            Assert.IsTrue(gravyElement.Selected);

            select.DeselectByValue("eggs");
            select.DeselectByValue("sausages");
            Assert.IsFalse(eggElement.Selected);
            Assert.IsFalse(sausageElement.Selected);
        }

        [TestMethod]
        public void DisabledOption()
        {
            var selectElement = driver.FindElement(By.Name("single_disabled"));
            var select = new SelectElement(selectElement);

            Assert.ThrowsException<WebDriverArgumentException>(() => select.SelectByValue("disabled"));
        }
    }
}