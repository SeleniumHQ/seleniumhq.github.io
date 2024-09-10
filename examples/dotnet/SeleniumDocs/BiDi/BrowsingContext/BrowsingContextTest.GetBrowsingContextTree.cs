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
        await using var bidi = await driver.AsBidirectionalAsync();

        var parentBrowsingContext = await driver.AsBidirectionalContextAsync();

        var browsingContext = await bidi.CreateBrowsingContextAsync(BrowsingContextType.Tab, new() { ReferenceContext = parentBrowsingContext });

        var tree = await bidi.GetBrowsingContextTreeAsync(new() { Root = parentBrowsingContext });

        Assert.IsNotNull(tree);
        Assert.AreEqual(1, tree.Count);
        Assert.AreEqual(parentBrowsingContext, tree[0].Context);
        Assert.IsNotNull(tree[0].Children);
        Assert.AreEqual(1, tree[0].Children.Count);
        Assert.AreEqual(browsingContext, tree[0].Children[0].Context);
    }
}
