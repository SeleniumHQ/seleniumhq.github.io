using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Support
{
    [TestClass]
    public class UsageTest : BaseChromeTest
    {
        public void TestWithoutSeleniumManager()
        {
            ChromeDriver driver = new ChromeDriver("path/to/ChromeDriver");
            driver.Navigate().GoToUrl("https://www.selenium.dev/documentation/selenium_manager/");
            driver.Quit();
        }

        public void TestWithSeleniumManager()
        {
            ChromeDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://www.selenium.dev/documentation/selenium_manager/");
            driver.Quit();
        }
    }
}