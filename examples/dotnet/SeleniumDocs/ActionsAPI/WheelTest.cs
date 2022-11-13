using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;

namespace SeleniumDocs.ActionsAPI
{
    [TestClass]
    public class WheelTest : BaseChromeTest
    {
        [TestMethod]
        public void ShouldAllowScrollingToAnElement()
        {
            driver.Url =
                "https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html";

            IWebElement iframe = driver.FindElement(By.TagName("iframe"));
            new Actions(driver)
                .ScrollToElement(iframe)
                .Perform();

            Assert.IsTrue(IsInViewport(iframe));
        }

        [TestMethod]
        public void ShouldAllowScrollingFromViewportByGivenAmount()
        {
            driver.Url =
                "https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html";

            IWebElement footer = driver.FindElement(By.TagName("footer"));
            int deltaY = footer.Location.Y;
            new Actions(driver)
                .ScrollByAmount(0, deltaY)
                .Perform();

            Assert.IsTrue(IsInViewport(footer));
        }

        [TestMethod]
        public void ShouldScrollFromElementByGivenAmount()
        {
            driver.Url =
                "https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html";

            IWebElement iframe = driver.FindElement(By.TagName("iframe"));
            WheelInputDevice.ScrollOrigin scrollOrigin = new WheelInputDevice.ScrollOrigin
            {
                Element = iframe
            };
            new Actions(driver)
                .ScrollFromOrigin(scrollOrigin, 0, 200)
                .Perform();

            driver.SwitchTo().Frame(iframe);
            IWebElement checkbox = driver.FindElement(By.Name("scroll_checkbox"));
            Assert.IsTrue(IsInViewport(checkbox));
        }

        [TestMethod]
        public void ShouldAllowScrollingFromElementByGivenAmountWithOffset()
        {
            driver.Url =
                "https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html";

            IWebElement footer = driver.FindElement(By.TagName("footer"));
            var scrollOrigin = new WheelInputDevice.ScrollOrigin
            {
                Element = footer,
                XOffset = 0,
                YOffset = -50
            };
            new Actions(driver)
                .ScrollFromOrigin(scrollOrigin, 0, 200)
                .Perform();

            IWebElement iframe = driver.FindElement(By.TagName("iframe"));
            driver.SwitchTo().Frame(iframe);
            IWebElement checkbox = driver.FindElement(By.Name("scroll_checkbox"));
            Assert.IsTrue(IsInViewport(checkbox));
        }

        [TestMethod]
        public void ShouldAllowScrollingFromViewportByGivenAmountFromOrigin()
        {
            driver.Url =
                "https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame.html";

            var scrollOrigin = new WheelInputDevice.ScrollOrigin
            {
                Viewport = true,
                XOffset = 10,
                YOffset = 10
            };
            new Actions(driver)
                .ScrollFromOrigin(scrollOrigin, 0, 200)
                .Perform();

            IWebElement iframe = driver.FindElement(By.TagName("iframe"));
            driver.SwitchTo().Frame(iframe);
            IWebElement checkbox = driver.FindElement(By.Name("scroll_checkbox"));
            Assert.IsTrue(IsInViewport(checkbox));
        }

        private bool IsInViewport(IWebElement element)
        {
            String script =
                "for(var e=arguments[0],f=e.offsetTop,t=e.offsetLeft,o=e.offsetWidth,n=e.offsetHeight;\n"
                + "e.offsetParent;)f+=(e=e.offsetParent).offsetTop,t+=e.offsetLeft;\n"
                + "return f<window.pageYOffset+window.innerHeight&&t<window.pageXOffset+window.innerWidth&&f+n>\n"
                + "window.pageYOffset&&t+o>window.pageXOffset";
            IJavaScriptExecutor javascriptDriver = this.driver as IJavaScriptExecutor;

            return (bool)javascriptDriver.ExecuteScript(script, element);
        }
    }
}