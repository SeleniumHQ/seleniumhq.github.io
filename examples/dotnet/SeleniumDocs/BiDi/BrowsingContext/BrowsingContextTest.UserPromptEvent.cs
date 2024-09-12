using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task UserPromptOpenedEvent()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var browsingContext = await driver.AsBidirectionalContextAsync();

        TaskCompletionSource<UserPromptOpenedEventArgs> tcs = new();

        await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

        await bidi.OnUserPromptOpenedAsync(tcs.SetResult);

        driver.FindElement(By.Id("prompt")).Click();

        var userPromptOpenedEventArgs = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(userPromptOpenedEventArgs);
        Console.WriteLine(userPromptOpenedEventArgs);
    }

    [TestMethod]
    public async Task UserPromptClosedEvent()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var browsingContext = await driver.AsBidirectionalContextAsync();

        TaskCompletionSource<UserPromptClosedEventArgs> tcs = new();

        await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

        await bidi.OnUserPromptClosedAsync(tcs.SetResult);

        driver.FindElement(By.Id("prompt")).Click();

        //await browsingContext.HandleUserPromptAsync();

        var userPromptClosedEventArgs = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(userPromptClosedEventArgs);
        Console.WriteLine(userPromptClosedEventArgs);
    }
}
