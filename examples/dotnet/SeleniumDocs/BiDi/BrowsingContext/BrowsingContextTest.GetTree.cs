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
        var context = await driver.AsBidirectionalContextAsync();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/iframes.html", new() { Wait = ReadinessState.Complete });

        var contexts = await context.GetTreeAsync();

        Assert.AreEqual(1, contexts.Count);
        Assert.IsNotNull(contexts[0].Children);
        Assert.IsTrue(contexts[0].Children.Count >= 1, "Context should contain iframes as children");
    }

    [TestMethod]
    public async Task GetBrowsingContextTreeWithDepth()
    {
        var context = await driver.AsBidirectionalContextAsync();

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/iframes.html", new() { Wait = ReadinessState.Complete });

        var contexts = await context.GetTreeAsync(new() { MaxDepth = 0 });

        Assert.AreEqual(1, contexts.Count);
        Assert.IsNull(contexts[0].Children, "Context should not contain iframes as children since depth is 0");
    }

    [TestMethod]
    public async Task GetAllTopLevelBrowingContexts()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var window = await bidi.CreateContextAsync(ContextType.Window);

        var contexts = await bidi.GetTreeAsync();

        Assert.AreEqual(2, contexts.Count);
        Assert.AreEqual(contexts[1].Context, window);
    }
}
