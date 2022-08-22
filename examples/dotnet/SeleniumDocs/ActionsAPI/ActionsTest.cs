using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;

namespace SeleniumDocs.ActionsAPI
{
    [TestClass]
    public class ActionsTest : BaseTest
    {
        [TestMethod]
        public void Pause()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";

            DateTime start = DateTime.Now;

            IWebElement clickable = driver.FindElement(By.Id("clickable"));
            new Actions(driver)
                .MoveToElement(clickable)
                .Pause(TimeSpan.FromSeconds(1))
                .ClickAndHold()
                .Pause(TimeSpan.FromSeconds(1))
                .SendKeys("abc")
                .Perform();

            TimeSpan duration = DateTime.Now - start;
            Assert.IsTrue(duration > TimeSpan.FromSeconds(2));
            Assert.IsTrue(duration < TimeSpan.FromSeconds(3));
        }

        [TestMethod]
        public void ReleaseAll()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement clickable = driver.FindElement(By.Id("clickable"));
            var actions = new Actions(driver);
            actions.ClickAndHold(clickable)
                .KeyDown(Keys.Shift)
                .SendKeys("a")
                .Perform();

            ((WebDriver)driver).ResetInputState();

            actions.SendKeys("a").Perform();
            var value = clickable.GetAttribute("value");
            Assert.AreEqual("A", value[..1]);
            Assert.AreEqual("a", value.Substring(1, 1));
        }
    }
}