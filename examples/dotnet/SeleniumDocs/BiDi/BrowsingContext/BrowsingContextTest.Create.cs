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

        var browsingContext = await bidi.CreateBrowsingContextAsync(BrowsingContextType.Tab);

        Assert.IsNotNull(browsingContext);
    }

    [TestMethod]
    public async Task OpenNewWindow()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var browsingContext = await bidi.CreateBrowsingContextAsync(BrowsingContextType.Window);

        Assert.IsNotNull(browsingContext);
    }

    [TestMethod]
    public async Task OpenTabWithReferenceBrowsingContext()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var browsingContext1 = await driver.AsBidirectionalContextAsync();

        var browsingContext2 = await bidi.CreateBrowsingContextAsync(BrowsingContextType.Tab, new() { ReferenceContext = browsingContext1 });

        Assert.IsNotNull(browsingContext2);
    }

    [TestMethod]
    public async Task OpenWindowWithReferenceBrowsingContext()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var browsingContext1 = await driver.AsBidirectionalContextAsync();

        var browsingContext2 = await bidi.CreateBrowsingContextAsync(BrowsingContextType.Window, new() { ReferenceContext = browsingContext1 });

        Assert.IsNotNull(browsingContext2);
    }

    [TestMethod]
    public async Task UseExistingWindowHandle()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        Assert.IsNotNull(browsingContext);
    }
}
