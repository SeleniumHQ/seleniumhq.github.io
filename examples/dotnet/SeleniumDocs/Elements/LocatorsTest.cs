using System;
using System.Drawing;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Elements
{
    [TestClass]
    public class LocatorsTest
    {
        [TestMethod]
        public void TestLocatorCommands(){
            WebDriver driver = new ChromeDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            // Navigate to Url
            driver.Url= "https://www.selenium.dev/selenium/web/locators_tests/locators.html";
            
            IWebElement element = driver.FindElement(By.ClassName("information"));
            Assert.AreEqual("input", element.GetTagName());
   
            //Find by css
            IWebElement element = driver.FindElement(By.CSSSelector("#fname"));
            Assert.AreEqual("Jane", element.GetAttribute("value"));
   
            //Find by id
            IWebElement element = driver.FindElement(By.ID("lname"));
            Assert.AreEqual("Doe", element.GetAttribute("value"));
   
            //Find by name
            IWebElement element = driver.FindElement(By.Name("newsletter"));
            Assert.AreEqual("input", element.GetTagName());
 
            //Find by link text
            IWebElement element = driver.FindElement(By.LinkText("Selenium Official Page"));
            Assert.AreEqual(element);
   
            //Find by partial link text
            IWebElement element = driver.FindElement(By.PartialLinkText("Official Page"));
            Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
  
            //Find by tag name
            IWebElement element = driver.FindElement(By.TagName("a"));
            Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
   
            //Find by xpath
            IWebElement element = driver.FindElement(By.XPath("//input[@value='f']"));
            Assert.AreEqual("radio", element.GetAttribute("type"));
   
            //find all
            driver.get("https://www.selenium.dev/selenium/web/login.html");
            By locator = new ByAll(By.ID("password-field"), By.ID("username-field"));
            List<IWebElement> loginInputs = driver.FindElements(locator);
            Assert.AreEqual(2, loginInputs.size());

            //chained
            driver.get("https://www.selenium.dev/selenium/web/login.html");
            By locator = new ByChained(By.ID("login-form"), By.TagName("input"));
            IWebElement usernameInput = driver.FindElement(locator);
            Assert.AreEqual("Username", usernameInput.GetAttribute("placeholder"));
            
            //Quit the driver
            driver.Quit();
        }
    }
}