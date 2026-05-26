using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.BiDi.W3C
{
    [TestClass]
    public class BrowsingContextTest : BaseFirefoxTest
    {
        [TestMethod]
        public void CreateBrowsingContextForGivenId()
        {
            string id = driver.CurrentWindowHandle;
            var browsingContext = new BrowsingContext(driver, id);
            Assert.AreEqual(id, browsingContext.Id);
        }

        [TestMethod]
        public void CreateWindow()
        {
            var browsingContext = new BrowsingContext(driver, WindowType.Window);
            Assert.IsNotNull(browsingContext.Id);
        }

        [TestMethod]
        public void CreateTab()
        {
            var browsingContext = new BrowsingContext(driver, WindowType.Tab);
            Assert.IsNotNull(browsingContext.Id);
        }

        [TestMethod]
        public void NavigateToUrl()
        {
            var browsingContext = new BrowsingContext(driver, WindowType.Tab);
            
            var navigationInfo = browsingContext.Navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            
            Assert.IsNotNull(browsingContext.Id);
            Assert.IsNotNull(navigationInfo.NavigationId);
            Assert.IsTrue(navigationInfo.Url.Contains("/bidi/logEntryAdded.html"));
        }

        [TestMethod]
        public void NavigateToUrlWithReadinessState()
        {
            var browsingContext = new BrowsingContext(driver, WindowType.Tab);
            
            var navigationInfo = browsingContext.Navigate(
                "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html",
                ReadinessState.Complete
            );
            
            Assert.IsNotNull(browsingContext.Id);
            Assert.IsNotNull(navigationInfo.NavigationId);
        }

        [TestMethod]
        public void GetTreeWithChildren()
        {
            string referenceContextId = driver.CurrentWindowHandle;
            var browsingContext = new BrowsingContext(driver, referenceContextId);
            
            browsingContext.Navigate("https://www.selenium.dev/selenium/web/iframes.html");
            
            var tree = browsingContext.GetTree();
            
            Assert.IsNotNull(tree);
            Assert.IsTrue(tree.Count > 0);
        }

        [TestMethod]
        public void GetTreeWithDepth()
        {
            string referenceContextId = driver.CurrentWindowHandle;
            var browsingContext = new BrowsingContext(driver, referenceContextId);
            
            browsingContext.Navigate("https://www.selenium.dev/selenium/web/iframes.html");
            
            var tree = browsingContext.GetTree(maxDepth: 1);
            
            Assert.IsNotNull(tree);
        }

        [TestMethod]
        public void GetAllTopLevelContexts()
        {
            var contexts = BrowsingContext.GetAllTopLevelContexts(driver);
            
            Assert.IsTrue(contexts.Count > 0);
        }

        [TestMethod]
        public void CloseWindow()
        {
            var browsingContext = new BrowsingContext(driver, WindowType.Window);
            
            browsingContext.Close();
            // If no exception, close was successful
        }

        [TestMethod]
        public void CloseTab()
        {
            var browsingContext = new BrowsingContext(driver, WindowType.Tab);
            
            browsingContext.Close();
            // If no exception, close was successful
        }

        [TestMethod]
        public void ActivateBrowsingContext()
        {
            var browsingContext = new BrowsingContext(driver, WindowType.Tab);
            
            browsingContext.Activate();
            // If no exception, activate was successful
        }

        [TestMethod]
        public void ReloadBrowsingContext()
        {
            var browsingContext = new BrowsingContext(driver, WindowType.Tab);
            browsingContext.Navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            
            var navigationInfo = browsingContext.Reload();
            
            Assert.IsNotNull(navigationInfo);
        }

        [TestMethod]
        public void PrintToPdf()
        {
            var browsingContext = new BrowsingContext(driver, driver.CurrentWindowHandle);
            browsingContext.Navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            
            var pdfData = browsingContext.Print();
            
            Assert.IsNotNull(pdfData);
            Assert.IsTrue(pdfData.Length > 0);
        }
    }
}
