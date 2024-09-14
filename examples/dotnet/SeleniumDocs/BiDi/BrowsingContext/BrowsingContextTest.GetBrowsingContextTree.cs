using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task GetBrowsingContextTree()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/iframes.html", new() { Wait = ReadinessState.Complete });

        var contexts = await browsingContext.GetTreeAsync();

        Assert.AreEqual(1, contexts.Count);
        Assert.IsNotNull(contexts[0].Children);
        Assert.IsTrue(contexts[0].Children.Count >= 1, "Context should contain iframes as children");
    }

    [TestMethod]
    public async Task GetBrowsingContextTreeWithDepth()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/iframes.html", new() { Wait = ReadinessState.Complete });

        var contexts = await browsingContext.GetTreeAsync(new() { MaxDepth = 0 });

        Assert.AreEqual(1, contexts.Count);
        Assert.IsNull(contexts[0].Children, "Context should not contain iframes as children since depth is 0");
    }

    [TestMethod]
    public async Task GetAllTopLevelBrowingContexts()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var window2 = await bidi.CreateBrowsingContextAsync(BrowsingContextType.Window);

        var contexts = await bidi.GetBrowsingContextTreeAsync();

        Assert.AreEqual(2, contexts.Count);
        Assert.AreEqual(contexts[1].Context, window2);
    }
}
