using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.Firefox;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task HandleUserPrompt()
    {
        // temporary use firefox because of chrome automatically handle prompts
        using var driver = new FirefoxDriver(new FirefoxOptions() { UseWebSocketUrl = true });

        var browsingContext = await driver.AsBidirectionalContextAsync();

        driver.Url = "https://www.selenium.dev/selenium/web/alerts.html";

        driver.FindElement(By.Id("prompt-with-default")).Click();

        await browsingContext.HandleUserPromptAsync(new() { Accept = true, UserText = "Selenium automates browsers" });
    }
}
