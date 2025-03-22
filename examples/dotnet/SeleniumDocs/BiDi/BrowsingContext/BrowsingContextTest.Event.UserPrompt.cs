using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task UserPromptOpenedEvent()
    {
        TaskCompletionSource<UserPromptOpenedEventArgs> tcs = new();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

        //TODO; THhis event can be a part of context
        await bidi.BrowsingContext.OnUserPromptOpenedAsync(tcs.SetResult);

        driver.FindElement(By.Id("prompt")).Click();

        var userPromptOpenedEventArgs = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(userPromptOpenedEventArgs);
        Console.WriteLine(userPromptOpenedEventArgs);
    }

    [TestMethod]
    public async Task UserPromptClosedEvent()
    {
        TaskCompletionSource<UserPromptClosedEventArgs> tcs = new();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

        //TODO; THhis event can be a part of context
        await bidi.BrowsingContext.OnUserPromptClosedAsync(tcs.SetResult);
        
        driver.FindElement(By.Id("prompt")).Click();

        //await context.HandleUserPromptAsync();

        var userPromptClosedEventArgs = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(userPromptClosedEventArgs);
        Console.WriteLine(userPromptClosedEventArgs);
    }
}
