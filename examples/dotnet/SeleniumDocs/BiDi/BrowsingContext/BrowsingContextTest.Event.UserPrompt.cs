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
        var context = await driver.AsBiDirectionalContextAsync();

        TaskCompletionSource<UserPromptOpenedEventArgs> tcs = new();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

        //TODO; THhis event can be a part of context
        await context.BiDi.OnUserPromptOpenedAsync(tcs.SetResult);

        driver.FindElement(By.Id("prompt")).Click();

        var userPromptOpenedEventArgs = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(userPromptOpenedEventArgs);
        Console.WriteLine(userPromptOpenedEventArgs);
    }

    [TestMethod]
    public async Task UserPromptClosedEvent()
    {
        var context = await driver.AsBiDirectionalContextAsync();

        TaskCompletionSource<UserPromptClosedEventArgs> tcs = new();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

        //TODO; THhis event can be a part of context
        await context.BiDi.OnUserPromptClosedAsync(tcs.SetResult);

        driver.FindElement(By.Id("prompt")).Click();

        //await context.HandleUserPromptAsync();

        var userPromptClosedEventArgs = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(userPromptClosedEventArgs);
        Console.WriteLine(userPromptClosedEventArgs);
    }
}
