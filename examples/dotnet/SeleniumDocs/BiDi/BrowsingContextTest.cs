using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.BiDi.W3C
{
    [TestClass]
    public class BrowsingContextTest : BaseFirefoxTest
    {
        [TestMethod]
        public void CreateWindow()
        {
            var newHandle = driver.SwitchTo().NewWindow(WindowType.Window).CurrentWindowHandle;
            Assert.IsNotNull(newHandle);
        }

        [TestMethod]
        public void CreateTab()
        {
            var newHandle = driver.SwitchTo().NewWindow(WindowType.Tab).CurrentWindowHandle;
            Assert.IsNotNull(newHandle);
        }

        [TestMethod]
        public void NavigateToUrl()
        {
            var originalHandle = driver.CurrentWindowHandle;
            driver.SwitchTo().NewWindow(WindowType.Tab);

            driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

            Assert.IsTrue(driver.Url.Contains("/bidi/logEntryAdded.html"));
            driver.Close();
            driver.SwitchTo().Window(originalHandle);
        }

        [TestMethod]
        public void GetAllWindowHandles()
        {
            var handle1 = driver.CurrentWindowHandle;
            driver.SwitchTo().NewWindow(WindowType.Window);
            var handle2 = driver.CurrentWindowHandle;

            var handles = driver.WindowHandles;

            Assert.IsTrue(handles.Count >= 2);

            driver.Close();
            driver.SwitchTo().Window(handle1);
        }

        [TestMethod]
        public void CloseWindow()
        {
            var originalHandle = driver.CurrentWindowHandle;
            driver.SwitchTo().NewWindow(WindowType.Window);

            driver.Close();
            driver.SwitchTo().Window(originalHandle);

            Assert.AreEqual(originalHandle, driver.CurrentWindowHandle);
        }

        [TestMethod]
        public void CloseTab()
        {
            var originalHandle = driver.CurrentWindowHandle;
            driver.SwitchTo().NewWindow(WindowType.Tab);

            driver.Close();
            driver.SwitchTo().Window(originalHandle);

            Assert.AreEqual(originalHandle, driver.CurrentWindowHandle);
        }

        [TestMethod]
        public void SwitchBetweenWindows()
        {
            var handle1 = driver.CurrentWindowHandle;
            driver.SwitchTo().NewWindow(WindowType.Window);
            var handle2 = driver.CurrentWindowHandle;

            driver.SwitchTo().Window(handle1);
            Assert.AreEqual(handle1, driver.CurrentWindowHandle);

            driver.SwitchTo().Window(handle2);
            driver.Close();
            driver.SwitchTo().Window(handle1);
        }

        [TestMethod]
        public void ReloadPage()
        {
            driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

            driver.Navigate().Refresh();

            Assert.IsTrue(driver.Url.Contains("/bidi/logEntryAdded.html"));
        }
    }
}
