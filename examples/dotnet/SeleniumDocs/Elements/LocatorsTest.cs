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
            
            driver.Url= "https://www.selenium.dev/selenium/web/locators_tests/locators.html";
            
            IWebElement element = driver.FindElement(By.ClassName("information"));
            Assert.AreEqual("input", element.TagName);
            
            element = driver.FindElement(By.CssSelector("#fname"));
            Assert.AreEqual("Jane", element.GetAttribute("value"));
           
            element = driver.FindElement(By.Id("lname"));
            Assert.AreEqual("Doe", element.GetAttribute("value"));
   
            element = driver.FindElement(By.Name("newsletter"));
            Assert.AreEqual("input", element.TagName);
 
            element = driver.FindElement(By.LinkText("Selenium Official Page"));
            Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
            
            element = driver.FindElement(By.PartialLinkText("Official Page"));
            Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
            
            element = driver.FindElement(By.TagName("a"));
            Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
   
            element = driver.FindElement(By.XPath("//input[@value='f']"));
            Assert.AreEqual("radio", element.GetAttribute("type"));
   
            driver.Quit();
        }
    }
}