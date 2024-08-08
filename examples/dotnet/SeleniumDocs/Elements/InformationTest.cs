using System;
using System.Drawing;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;


namespace SeleniumDocs.Elements
{
    [TestClass]
    public class InformationTest 
    {
        [TestMethod]
        public void TestInformationCommands(){
            WebDriver driver = new ChromeDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            // Navigate to Url
            driver.Url= "https://www.selenium.dev/selenium/web/inputs.html";
            // isDisplayed        
            // Get boolean value for is element display
            bool isEmailVisible = driver.FindElement(By.Name("email_input")).Displayed;
            Assert.AreEqual(isEmailVisible, true);

            //isEnabled
            //returns true if element is enabled else returns false
            bool isEnabledButton = driver.FindElement(By.Name("button_input")).Enabled;
            Assert.AreEqual(isEnabledButton, true);

            //isSelected
            //returns true if element is checked else returns false
            bool isSelectedCheck = driver.FindElement(By.Name("checkbox_input")).Selected;
            Assert.AreEqual(isSelectedCheck, true);

            //TagName
            //returns TagName of the element
            string tagNameInp = driver.FindElement(By.Name("email_input")).TagName;
            Assert.AreEqual(tagNameInp, "input");

            //Get Location and Size
            //Get Location
            IWebElement rangeElement = driver.FindElement(By.Name("range_input"));
            Point point = rangeElement.Location;
            Assert.IsNotNull(point.X);
            //Get Size
            int height=rangeElement.Size.Height;
            Assert.IsNotNull(height);

            // Retrieves the computed style property 'font-size' of field
            string cssValue = driver.FindElement(By.Name("color_input")).GetCssValue("font-size");
            Assert.AreEqual(cssValue, "13.3333px");

            //GetText
            // Retrieves the text of the element
            string text = driver.FindElement(By.TagName("h1")).Text;
            Assert.AreEqual(text, "Testing Inputs");

            //FetchAttributes
            //identify the email text box
            IWebElement emailTxt = driver.FindElement(By.Name("email_input"));
            //fetch the value property associated with the textbox
            string valueInfo = emailTxt.GetAttribute("value");
            Assert.AreEqual(valueInfo, "admin@localhost");
            
            //Quit the driver
            driver.Quit();
        }
    }
}