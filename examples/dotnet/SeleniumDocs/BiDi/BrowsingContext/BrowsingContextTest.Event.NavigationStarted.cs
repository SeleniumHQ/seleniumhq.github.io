using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task NavigationStartedEvent()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        TaskCompletionSource<NavigationInfo> tcs = new();

        await browsingContext.OnNavigationStartedAsync(tcs.SetResult);

        await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        var info = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(info);
    }
}
