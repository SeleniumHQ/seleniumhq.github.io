using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task CloseTab()
    {
        var bidi = await driver.AsBiDiAsync();

        var context = await bidi.CreateContextAsync(ContextType.Tab);

        await context.CloseAsync();
    }

    [TestMethod]
    public async Task CloseWindow()
    {
        var bidi = await driver.AsBiDiAsync();

        var context = await bidi.CreateContextAsync(ContextType.Window);

        await context.CloseAsync();
    }
}
