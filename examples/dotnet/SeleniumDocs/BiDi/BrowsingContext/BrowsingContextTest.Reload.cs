using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task Reload()
    {
        await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        var info = await context.ReloadAsync();

        Assert.IsNotNull(info);
        Assert.IsNotNull(info.Navigation);
        StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
    }
}
