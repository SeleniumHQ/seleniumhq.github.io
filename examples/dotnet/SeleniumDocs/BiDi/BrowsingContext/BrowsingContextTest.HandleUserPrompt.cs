using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.BrowsingContext;
using OpenQA.Selenium.Firefox;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task HandleUserPrompt()
    {
        // temporary use firefox because of chrome automatically handle prompts
        using var driver = new FirefoxDriver(new FirefoxOptions() { UseWebSocketUrl = true, UnhandledPromptBehavior = UnhandledPromptBehavior.Ignore });

        var bidi = await driver.AsBiDiAsync();

        var context = (await bidi.BrowsingContext.GetTreeAsync()).Contexts[0].Context;

        driver.Url = "https://www.selenium.dev/selenium/web/alerts.html";

        TaskCompletionSource<UserPromptOpenedEventArgs> promptOpened = new();

        await bidi.BrowsingContext.UserPromptOpened.SubscribeAsync(promptOpened.SetResult);

        driver.FindElement(By.Id("prompt-with-default")).Click();

        await promptOpened.Task.WaitAsync(TimeSpan.FromSeconds(5));

        await context.HandleUserPromptAsync(new() { Accept = true, UserText = "Selenium automates browsers" });
    }
}
