using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task NavigateToUrl()
    {
        var info = await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

        Assert.IsNotNull(info);
        Assert.IsNotNull(info.Navigation);
        StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
    }

    [TestMethod]
    public async Task NavigateToUrlWithReadinessState()
    {
        var info = await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        Assert.IsNotNull(info);
        Assert.IsNotNull(info.Navigation);
        StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
    }

    [TestMethod]
    public async Task NavigateBack()
    {
        await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        await context.TraverseHistoryAsync(-1);

        var url = await WaitForUrlAsync("about:blank");

        Assert.AreEqual("about:blank", url);
    }

    [TestMethod]
    public async Task NavigateForward()
    {
        await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        await context.TraverseHistoryAsync(-1);
        await WaitForUrlAsync("about:blank");

        await context.TraverseHistoryAsync(1);
        var url = await WaitForUrlAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

        Assert.AreEqual("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", url);
    }

    private async Task<string> WaitForUrlAsync(string expectedUrl)
    {
        var deadline = DateTime.UtcNow.AddSeconds(5);
        string url;
        do
        {
            url = (await context.GetTreeAsync()).Contexts[0].Url;
            if (url == expectedUrl) return url;
            await Task.Delay(100);
        } while (DateTime.UtcNow < deadline);

        return url;
    }

    [TestMethod]
    public async Task TraverseHistory()
    {
        await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        await context.TraverseHistoryAsync(-1);

        var url = await WaitForUrlAsync("about:blank");

        Assert.AreEqual("about:blank", url);
    }
}
