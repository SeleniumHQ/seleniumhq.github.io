using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task NavigateToUrl()
    {
        var context = await driver.AsBiDirectionalContextAsync();

        var info = await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

        Assert.IsNotNull(info);
        Assert.IsNotNull(info.Navigation);
        StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
    }

    [TestMethod]
    public async Task NavigateToUrlWithReadinessState()
    {
        var context = await driver.AsBiDirectionalContextAsync();

        var info = await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        Assert.IsNotNull(info);
        Assert.IsNotNull(info.Navigation);
        StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
    }

    [TestMethod]
    public async Task NavigateBack()
    {
        var context = await driver.AsBiDirectionalContextAsync();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        await context.NavigateBackAsync();

        var url = (await context.GetTreeAsync())[0].Url;

        Assert.AreEqual("about:blank", url);
    }

    [TestMethod]
    public async Task NavigateForward()
    {
        var context = await driver.AsBiDirectionalContextAsync();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        await context.NavigateBackAsync();

        await context.NavigateForwardAsync();

        var url = (await context.GetTreeAsync())[0].Url;

        Assert.AreEqual("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", url);
    }

    [TestMethod]
    public async Task TraverseHistory()
    {
        var context = await driver.AsBiDirectionalContextAsync();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        await context.TraverseHistoryAsync(-1);

        var url = (await context.GetTreeAsync())[0].Url;

        Assert.AreEqual("about:blank", url);
    }
}
