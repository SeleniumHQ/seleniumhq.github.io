using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Interactions
{
    [TestClass]
    public class WindowsTest
    {
        [TestMethod]
        public void TestWindowCommands()
        {
            WebDriver driver = new ChromeDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            // Navigate to Url
            driver.Url="https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html";
            //fetch handle of this
            String currHandle = driver.CurrentWindowHandle;
            Assert.IsNotNull(currHandle);

            //click on link to open a new window
            driver.FindElement(By.LinkText("Open new window")).Click();
            //fetch handles of all windows, there will be two, [0]- default, [1] - new window
            IList<string> windowHandles = new List<string>(driver.WindowHandles);
            driver.SwitchTo().Window(windowHandles[1]);
            //assert on title of new window
            String title = driver.Title;
            Assert.AreEqual("Simple Page", title);

            //closing current window
            driver.Close();
            //Switch back to the old tab or window
            driver.SwitchTo().Window(windowHandles[0]);

            //Opens a new tab and switches to new tab
            driver.SwitchTo().NewWindow(WindowType.Tab);
            Assert.AreEqual("", driver.Title);

            //Opens a new window and switches to new window
            driver.SwitchTo().NewWindow(WindowType.Window);
            Assert.AreEqual("", driver.Title);

            //quitting driver
            driver.Quit(); //close all windows
        }
    }
}