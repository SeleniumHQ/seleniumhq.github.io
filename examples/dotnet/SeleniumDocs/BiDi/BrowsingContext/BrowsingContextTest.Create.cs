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
        await using var bidi = await driver.AsBiDirectionalAsync();

        var context = await bidi.CreateContextAsync(ContextType.Tab);

        Assert.IsNotNull(context);
    }

    [TestMethod]
    public async Task OpenNewWindow()
    {
        await using var bidi = await driver.AsBiDirectionalAsync();

        var context = await bidi.CreateContextAsync(ContextType.Window);

        Assert.IsNotNull(context);
    }

    [TestMethod]
    public async Task OpenTabWithReferenceBrowsingContext()
    {
        var context1 = await driver.AsBiDirectionalContextAsync();

        var context2 = await context1.BiDi.CreateContextAsync(ContextType.Tab, new() { ReferenceContext = context1 });

        Assert.IsNotNull(context2);
    }

    [TestMethod]
    public async Task OpenWindowWithReferenceBrowsingContext()
    {
        var context1 = await driver.AsBiDirectionalContextAsync();

        var context2 = await context1.BiDi.CreateContextAsync(ContextType.Window, new() { ReferenceContext = context1 });

        Assert.IsNotNull(context2);
    }

    [TestMethod]
    public async Task UseExistingWindowHandle()
    {
        var context = await driver.AsBiDirectionalContextAsync();

        Assert.IsNotNull(context);
    }
}
