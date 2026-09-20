using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task OpenNewTab()
    {
        var bidi = await driver.AsBiDiAsync();

        var context = (await bidi.BrowsingContext.CreateAsync(ContextType.Tab)).Context;

        Assert.IsNotNull(context);
    }

    [TestMethod]
    public async Task OpenNewWindow()
    {
        var bidi = await driver.AsBiDiAsync();

        var context = (await bidi.BrowsingContext.CreateAsync(ContextType.Window)).Context;

        Assert.IsNotNull(context);
    }

    [TestMethod]
    public async Task OpenTabWithReferenceBrowsingContext()
    {
        var context1 = context;

        var context2 = (await context1.BiDi.BrowsingContext.CreateAsync(ContextType.Tab, new() { ReferenceContext = context1 })).Context;

        Assert.IsNotNull(context2);
    }

    [TestMethod]
    public async Task OpenWindowWithReferenceBrowsingContext()
    {
        var context1 = context;

        var context2 = (await context1.BiDi.BrowsingContext.CreateAsync(ContextType.Window, new() { ReferenceContext = context1 })).Context;

        Assert.IsNotNull(context2);
    }

    [TestMethod]
    public async Task UseExistingWindowHandle()
    {
        var context = (await bidi.BrowsingContext.GetTreeAsync()).Contexts[0].Context;

        Assert.IsNotNull(context);
    }
}
