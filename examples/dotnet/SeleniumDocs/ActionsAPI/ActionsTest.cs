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
            DateTime start = DateTime.Now;

            ActionBuilder actionBuilder = new ActionBuilder();
            PointerInputDevice mouse = new PointerInputDevice(PointerKind.Mouse, "default mouse");
            KeyInputDevice keyboard = new KeyInputDevice("default keyboard");
            actionBuilder.AddAction(mouse.CreatePause(TimeSpan.FromSeconds(1)));
            actionBuilder.AddAction(keyboard.CreatePause(TimeSpan.FromSeconds(1)));
            ((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());

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
                .KeyDown(Keys.Alt)
                .SendKeys("a")
                .Perform();

            ((WebDriver)driver).ResetInputState();

            new Actions(driver).SendKeys("a").Perform();
            var value = clickable.GetAttribute("value");
            Assert.AreEqual("Å", value[..1]);
            Assert.AreEqual("a", value.Substring(1, 1));
        }
    }
}