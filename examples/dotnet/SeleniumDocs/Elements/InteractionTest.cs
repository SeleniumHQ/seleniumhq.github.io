using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Elements
{
    [TestClass]
    public class InteractionTest
    {
        [TestMethod]
        public void TestInteractionCommands()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            // Navigate to Url
            driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");
            // Click on the element 
            IWebElement checkInput = driver.FindElement(By.Name("checkbox_input"));
            checkInput.Click();

            //Verify
            Boolean isChecked = checkInput.Selected;
            Assert.AreEqual(isChecked, false);

            //SendKeys
            // Clear field to empty it from any previous data
            IWebElement emailInput = driver.FindElement(By.Name("email_input"));
            emailInput.Clear();
            //Enter Text
            String email = "admin@localhost.dev";
            emailInput.SendKeys(email);

            //Verify
            String data = emailInput.GetAttribute("value");
            Assert.AreEqual(data, email);


            //Clear Element
            // Clear field to empty it from any previous data
            emailInput.Clear();
            data = emailInput.GetAttribute("value");
            
            //Verify
            Assert.AreEqual(data, "");

            //Quit the browser
            driver.Quit();
        }
    }
}