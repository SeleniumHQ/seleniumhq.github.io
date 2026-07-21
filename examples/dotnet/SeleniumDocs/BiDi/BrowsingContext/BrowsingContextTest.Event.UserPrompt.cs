using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.BrowsingContext;
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

        // TODO: this event can be a part of context
        await bidi.BrowsingContext.UserPromptOpened.SubscribeAsync(args => tcs.TrySetResult(args));

        driver.FindElement(By.Id("prompt")).Click();

        var userPromptOpenedEventArgs = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(userPromptOpenedEventArgs);
        Console.WriteLine(userPromptOpenedEventArgs);
    }

    [TestMethod]
    public async Task UserPromptClosedEvent()
    {
        TaskCompletionSource<UserPromptOpenedEventArgs> opened = new();
        TaskCompletionSource<UserPromptClosedEventArgs> closed = new();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

        // TODO: these events can be a part of context
        await bidi.BrowsingContext.UserPromptOpened.SubscribeAsync(args => opened.TrySetResult(args));
        await bidi.BrowsingContext.UserPromptClosed.SubscribeAsync(args => closed.TrySetResult(args));

        driver.FindElement(By.Id("prompt")).Click();

        await opened.Task.WaitAsync(TimeSpan.FromSeconds(5));

        try
        {
            // Chrome's default unhandled prompt behavior may already have closed the
            // prompt by this point; tolerate that instead of treating it as a failure.
            await context.HandleUserPromptAsync(new() { Accept = true });
        }
        catch (BiDiException ex) when (ex.Message.Contains("no such alert"))
        {
        }

        var userPromptClosedEventArgs = await closed.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(userPromptClosedEventArgs);
        Console.WriteLine(userPromptClosedEventArgs);
    }
}
