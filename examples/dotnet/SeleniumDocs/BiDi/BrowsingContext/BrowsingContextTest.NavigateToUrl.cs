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
        var browsingContext = await driver.AsBidirectionalContextAsync();

        var info = await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

        Assert.IsNotNull(info);
        Assert.IsNotNull(info.Navigation);
        StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
    }

    [TestMethod]
    public async Task NavigateToUrlWithReadinessState()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        var info = await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        Assert.IsNotNull(info);
        Assert.IsNotNull(info.Navigation);
        StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
    }
}
