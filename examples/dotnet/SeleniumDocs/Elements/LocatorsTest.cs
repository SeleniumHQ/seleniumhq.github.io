using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support;

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
            Assert.AreEqual("input", element.TagName);
   
            //Find by css
            element = driver.FindElement(By.CssSelector("#fname"));
            Assert.AreEqual("Jane", element.GetAttribute("value"));
   
            //Find by id
            element = driver.FindElement(By.Id("lname"));
            Assert.AreEqual("Doe", element.GetAttribute("value"));
   
            //Find by name
            element = driver.FindElement(By.Name("newsletter"));
            Assert.AreEqual("input", element.TagName);
 
            //Find by link text
            element = driver.FindElement(By.LinkText("Selenium Official Page"));
            Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
   
            //Find by partial link text
            element = driver.FindElement(By.PartialLinkText("Official Page"));
            Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
  
            //Find by tag name
            element = driver.FindElement(By.TagName("a"));
            Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
   
            //Find by xpath
            element = driver.FindElement(By.XPath("//input[@value='f']"));
            Assert.AreEqual("radio", element.GetAttribute("type"));
   
            //find all
            //driver.Url=("https://www.selenium.dev/selenium/web/login.html");
           // By locator = new ByAll(By.Id("password-field"), By.Id("username-field"));
            //List<IWebElement> loginInputs = driver.FindElements(locator);
           // Assert.AreEqual(2, loginInputs.size());

            //chained
           // driver.Url=("https://www.selenium.dev/selenium/web/login.html");
           // locator = new ByChained(By.Id("login-form"), By.TagName("input"));
           // IWebElement usernameInput = driver.FindElement(locator);
           // Assert.AreEqual("Username", usernameInput.GetAttribute("placeholder"));
            
            //Quit the driver
            driver.Quit();
        }
    }
}