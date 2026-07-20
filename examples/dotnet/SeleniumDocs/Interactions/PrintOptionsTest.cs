using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocumentation.SeleniumInteractions
{
    [TestClass]
    public class PrintOptionsTest
    {
        [TestMethod]
        public void TestOrientation()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://selenium.dev");
            PrintOptions printOptions  = new PrintOptions();
            printOptions.Orientation = PrintOrientation.Landscape;
            PrintOrientation currentOrientation = printOptions.Orientation;
            driver.Quit();
        }

        [TestMethod]
        public void TestRange()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://selenium.dev");
            PrintOptions printOptions  = new PrintOptions();
            printOptions.AddPageRangeToPrint("1-3"); // add range of pages
            printOptions.AddPageToPrint(5); // add individual page
            driver.Quit();
        }

        [TestMethod]
        public void TestSize()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://www.selenium.dev/");
            PrintOptions printOptions = new PrintOptions();
            PrintOptions.PageSize currentDimensions = printOptions.PageDimensions;
            driver.Quit();
        }

        [TestMethod]
        public void TestBackgrounds()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://www.selenium.dev/");
            PrintOptions printOptions = new PrintOptions();
            printOptions.OutputBackgroundImages = true;
            bool currentBackgrounds = printOptions.OutputBackgroundImages;
            driver.Quit();
        }

        [TestMethod]
        public void TestMargins()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://www.selenium.dev/");
            PrintOptions printOptions = new PrintOptions();
            PrintOptions.Margins currentMargins = printOptions.PageMargins;
            driver.Quit();
        }


        [TestMethod]
        public void TestScale()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://www.selenium.dev/");
            PrintOptions printOptions = new PrintOptions();
            printOptions.ScaleFactor = 0.5;
            double currentScale = printOptions.ScaleFactor;
            driver.Quit();
        }

        [TestMethod]
        public void TestShrinkToFit()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://www.selenium.dev/");
            PrintOptions printOptions = new PrintOptions();
            printOptions.ShrinkToFit = true;
            bool currentShrinkToFit = printOptions.ShrinkToFit;
            driver.Quit();
        }

        [TestMethod]
        public void PrintWithPrintsPageTest()
        {
            WebDriver driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://www.selenium.dev/");
            PrintOptions printOptions = new PrintOptions();
            PrintDocument printedPage = driver.Print(printOptions);
            Assert.IsTrue(printedPage.AsBase64EncodedString.StartsWith("JVBER"));
            driver.Quit();
        }
    }
}
