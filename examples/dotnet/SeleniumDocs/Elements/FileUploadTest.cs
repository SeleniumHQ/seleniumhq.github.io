using System;
using System.IO;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;

namespace SeleniumDocs.Elements
{
    [TestClass]
    public class FileUploadTest : BaseChromeTest
    {
        [TestMethod]
        public void Uploads()
        {
            driver.Url = "https://the-internet.herokuapp.com/upload";

            string baseDirectory = AppContext.BaseDirectory;
            string relativePath = "../../../TestSupport/selenium-snapshot.png";

            string uploadFile = Path.GetFullPath(Path.Combine(baseDirectory, relativePath));

            IWebElement fileInput = driver.FindElement(By.CssSelector("input[type=file]"));
            fileInput.SendKeys(uploadFile);
            driver.FindElement(By.Id("file-submit")).Click();

            IWebElement fileName = driver.FindElement(By.Id("uploaded-files"));
            Assert.AreEqual("selenium-snapshot.png", fileName.Text);
        }
    }
}