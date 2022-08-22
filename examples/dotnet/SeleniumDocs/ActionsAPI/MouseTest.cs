using System;
using System.Drawing;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;

namespace SeleniumDocs.ActionsAPI
{
    [TestClass]
    public class MouseTest : BaseTest
    {
        [TestMethod]
        public void ClickAndHold()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement clickable = driver.FindElement(By.Id("clickable"));
            new Actions(driver)
                .ClickAndHold(clickable)
                .Perform();
            
            Assert.AreEqual("focused", driver.FindElement(By.Id("click-status")).Text);
        }
        
        [TestMethod]
        public void ClickAndRelease()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement clickable = driver.FindElement(By.Id("click"));
            new Actions(driver)
                .Click(clickable)
                .Perform();
            
            Assert.IsTrue(driver.Url.Contains("resultPage.html"));
        }

        [TestMethod]
        public void RightClick()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement clickable = driver.FindElement(By.Id("clickable"));
            new Actions(driver)
                .ContextClick(clickable)
                .Perform();
            
            Assert.AreEqual("context-clicked", driver.FindElement(By.Id("click-status")).Text);
        }
        
        [TestMethod]
        public void BackClick()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";

            driver.FindElement(By.Id("click")).Click();
            Assert.AreEqual("We Arrive Here", driver.Title);

            ActionBuilder actionBuilder = new ActionBuilder();
            PointerInputDevice mouse = new PointerInputDevice(PointerKind.Mouse, "default mouse");
            actionBuilder.AddAction(mouse.CreatePointerDown(MouseButton.Back));
            actionBuilder.AddAction(mouse.CreatePointerUp(MouseButton.Back));
            ((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());

            Assert.AreEqual("BasicMouseInterfaceTest", driver.Title);
        }

        [TestMethod]
        public void ForwardClick()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";

            driver.FindElement(By.Id("click")).Click();
            driver.Navigate().Back();
            Assert.AreEqual("BasicMouseInterfaceTest", driver.Title);

            ActionBuilder actionBuilder = new ActionBuilder();
            PointerInputDevice mouse = new PointerInputDevice(PointerKind.Mouse, "default mouse");
            actionBuilder.AddAction(mouse.CreatePointerDown(MouseButton.Forward));
            actionBuilder.AddAction(mouse.CreatePointerUp(MouseButton.Forward));
            ((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());

            Assert.AreEqual("We Arrive Here", driver.Title);
        }

        [TestMethod]
        public void DoubleClick()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement clickable = driver.FindElement(By.Id("clickable"));
            new Actions(driver)
                .DoubleClick(clickable)
                .Perform();
            
            Assert.AreEqual("double-clicked", driver.FindElement(By.Id("click-status")).Text);
        }

        [TestMethod]
        public void Hovers()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement hoverable = driver.FindElement(By.Id("hover"));
            new Actions(driver)
                .MoveToElement(hoverable)
                .Perform();
            
            Assert.AreEqual("hovered", driver.FindElement(By.Id("move-status")).Text);
        }

        [TestMethod]
        [Obsolete("Obsolete")]
        public void MoveByOffsetFromTopLeftOfElement()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement tracker = driver.FindElement(By.Id("mouse-tracker"));
            new Actions(driver)
                .MoveToElement(tracker, 8, 0)
                .Perform();

            string[] result = driver.FindElement(By.Id("relative-location")).Text.Split(", ");
            Assert.IsTrue(Math.Abs(int.Parse(result[0]) - 100 - 8) < 2);
        }

        [TestMethod]
        public void MoveByOffsetFromCenterOfElement()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement tracker = driver.FindElement(By.Id("mouse-tracker"));
            new Actions(driver)
                .MoveToElement(tracker, 8, 0)
                .Perform();

            string[] result = driver.FindElement(By.Id("relative-location")).Text.Split(", ");
            Assert.IsTrue(Math.Abs(int.Parse(result[0]) - 100 - 8) < 2);
        }
        
        [TestMethod]
        public void MoveByOffsetFromViewport()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";

            ActionBuilder actionBuilder = new ActionBuilder();
            PointerInputDevice mouse = new PointerInputDevice(PointerKind.Mouse, "default mouse");
            actionBuilder.AddAction(mouse.CreatePointerMove(CoordinateOrigin.Viewport,
                8, 0, TimeSpan.Zero));
            ((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());
            
            string[] result = driver.FindElement(By.Id("absolute-location")).Text.Split(", ");
            Assert.IsTrue(Math.Abs(int.Parse(result[0]) - 8) < 2);
        }

        [TestMethod]
        public void MoveByOffsetFromCurrentPointerLocation()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";

            ActionBuilder actionBuilder = new ActionBuilder();
            PointerInputDevice mouse = new PointerInputDevice(PointerKind.Mouse, "default mouse");
            actionBuilder.AddAction(mouse.CreatePointerMove(CoordinateOrigin.Viewport,
                8, 12, TimeSpan.Zero));
            ((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());
            
            new Actions(driver)
                .MoveByOffset(13, 15)
                .Perform();

            string[] result = driver.FindElement(By.Id("absolute-location")).Text.Split(", ");
            Assert.IsTrue(Math.Abs(int.Parse(result[0]) - 8 - 13) < 2);
            Assert.IsTrue(Math.Abs(int.Parse(result[1]) - 12 - 15) < 2);
        }

        [TestMethod]
        public void DragToElement()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement draggable = driver.FindElement(By.Id("draggable"));
            IWebElement droppable = driver.FindElement(By.Id("droppable"));
            new Actions(driver)
                .DragAndDrop(draggable, droppable)
                .Perform();
            
            Assert.AreEqual("dropped", driver.FindElement(By.Id("drop-status")).Text);
        }

        [TestMethod]
        public void DragByOffset()
        {
            driver.Url = "https://selenium.dev/selenium/web/mouse_interaction.html";
            
            IWebElement draggable = driver.FindElement(By.Id("draggable"));
            Point start = draggable.Location;
            Point finish = driver.FindElement(By.Id("droppable")).Location;
            new Actions(driver)
                .DragAndDropToOffset(draggable, finish.X - start.X, finish.Y - start.Y)
                .Perform();
            
            Assert.AreEqual("dropped", driver.FindElement(By.Id("drop-status")).Text);
        }
    }
}