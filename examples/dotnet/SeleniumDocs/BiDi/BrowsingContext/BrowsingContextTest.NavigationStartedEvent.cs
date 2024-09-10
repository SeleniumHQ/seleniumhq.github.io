using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task NavigationStartedEvent()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        NavigationInfo info = null;

        await browsingContext.OnNavigationStartedAsync(e => info = e);

        await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

        Assert.IsNotNull(info);
    }
}
