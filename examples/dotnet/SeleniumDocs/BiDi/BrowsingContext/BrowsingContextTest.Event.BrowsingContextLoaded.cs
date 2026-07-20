using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task BrowsingContextLoadedEvent()
    {
        TaskCompletionSource<LoadEventArgs> tcs = new();

        await context.Load.SubscribeAsync(tcs.SetResult);

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        var navigationInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(navigationInfo);
        Console.WriteLine(navigationInfo);
    }
}
