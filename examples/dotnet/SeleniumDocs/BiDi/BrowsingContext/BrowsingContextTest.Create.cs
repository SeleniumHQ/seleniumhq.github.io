using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task OpenNewTab()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var context = await bidi.CreateContextAsync(BrowsingContextType.Tab);

        Assert.IsNotNull(context);
    }

    [TestMethod]
    public async Task OpenNewWindow()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var context = await bidi.CreateContextAsync(BrowsingContextType.Window);

        Assert.IsNotNull(context);
    }

    [TestMethod]
    public async Task OpenTabWithReferenceBrowsingContext()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var context1 = await driver.AsBidirectionalContextAsync();

        var context2 = await bidi.CreateContextAsync(BrowsingContextType.Tab, new() { ReferenceContext = context1 });

        Assert.IsNotNull(context2);
    }

    [TestMethod]
    public async Task OpenWindowWithReferenceBrowsingContext()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var context1 = await driver.AsBidirectionalContextAsync();

        var context2 = await bidi.CreateContextAsync(BrowsingContextType.Window, new() { ReferenceContext = context1 });

        Assert.IsNotNull(context2);
    }

    [TestMethod]
    public async Task UseExistingWindowHandle()
    {
        var context = await driver.AsBidirectionalContextAsync();

        Assert.IsNotNull(context);
    }
}
