using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;

namespace SeleniumDocs.ActionsAPI
{
    [TestClass]
    public class KeysTest : BaseFirefoxTest
    {
        [TestMethod]
        public void KeyDown()
        {
            driver.Url = "https://selenium.dev/selenium/web/single_text_input.html";

            new Actions(driver)
                .KeyDown(Keys.Shift)
                .SendKeys("a")
                .Perform();

            IWebElement textField = driver.FindElement(By.Id("textInput"));
            Assert.AreEqual("A", textField.GetAttribute("value"));
        }

        [TestMethod]
        public void KeyUp()
        {
            driver.Url = "https://selenium.dev/selenium/web/single_text_input.html";

            new Actions(driver)
                .KeyDown(Keys.Shift)
                .SendKeys("a")
                .KeyUp(Keys.Shift)
                .SendKeys("b")
                .Perform();

            IWebElement textField = driver.FindElement(By.Id("textInput"));
            Assert.AreEqual("Ab", textField.GetAttribute("value"));
        }
        
        [TestMethod]
        public void SendKeysToActiveElement()
        {
            driver.Url = "https://selenium.dev/selenium/web/single_text_input.html";

            new Actions(driver)
                .SendKeys("abc")
                .Perform();

            IWebElement textField = driver.FindElement(By.Id("textInput"));
            Assert.AreEqual("abc", textField.GetAttribute("value"));
        }
        
        [TestMethod]
        public void SendKeysToDesignatedElement()
        {
            driver.Url = "https://selenium.dev/selenium/web/single_text_input.html";
            driver.FindElement(By.TagName("body")).Click();
            
            IWebElement textField = driver.FindElement(By.Id("textInput"));
            new Actions(driver)
                .SendKeys(textField, "abc")
                .Perform();

            Assert.AreEqual("abc", textField.GetAttribute("value"));
        }

        [TestMethod]
        public void CopyAndPaste()
        {
            driver.Url = "https://selenium.dev/selenium/web/single_text_input.html";

            var capabilities = ((WebDriver)driver).Capabilities;
            String platformName = (string)capabilities.GetCapability("platformName");

            String cmdCtrl = platformName.Contains("mac") ? Keys.Command : Keys.Control;

            new Actions(driver)
                .SendKeys("Selenium!")
                .SendKeys(Keys.ArrowLeft)
                .KeyDown(Keys.Shift)
                .SendKeys(Keys.ArrowUp)
                .KeyUp(Keys.Shift)
                .KeyDown(cmdCtrl)
                .SendKeys("xvv")
                .KeyUp(cmdCtrl)
                .Perform();

            IWebElement textField = driver.FindElement(By.Id("textInput"));
            Assert.AreEqual("SeleniumSelenium!", textField.GetAttribute("value"));
        }
    }
}