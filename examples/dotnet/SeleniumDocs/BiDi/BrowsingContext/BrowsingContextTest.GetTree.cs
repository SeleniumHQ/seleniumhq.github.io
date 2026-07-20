using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task GetBrowsingContextTree()
    {
        await context.NavigateAsync("https://www.selenium.dev/selenium/web/iframes.html", new() { Wait = ReadinessState.Complete });

        var contexts = (await context.GetTreeAsync()).Contexts;

        Assert.AreEqual(1, contexts.Length);
        Assert.IsNotNull(contexts[0].Children);
        Assert.IsTrue(contexts[0].Children.Value.Length >= 1, "Context should contain iframes as children");
    }

    [TestMethod]
    public async Task GetBrowsingContextTreeWithDepth()
    {
        await context.NavigateAsync("https://www.selenium.dev/selenium/web/iframes.html", new() { Wait = ReadinessState.Complete });

        var contexts = (await context.GetTreeAsync(new() { MaxDepth = 0 })).Contexts;

        Assert.AreEqual(1, contexts.Length);
        Assert.IsNull(contexts[0].Children, "Context should not contain iframes as children since depth is 0");
    }

    [TestMethod]
    public async Task GetAllTopLevelBrowsingContexts()
    {
        var window = (await bidi.BrowsingContext.CreateAsync(ContextType.Window)).Context;

        var contexts = (await bidi.BrowsingContext.GetTreeAsync()).Contexts;

        Assert.AreEqual(2, contexts.Length);
        Assert.AreEqual(contexts[1].Context, window);
    }
}
