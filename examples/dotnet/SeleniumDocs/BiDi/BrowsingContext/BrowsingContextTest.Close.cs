using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task CloseTab()
    {
        var context = await bidi.BrowsingContext.CreateAsync(ContextType.Tab);

        await context.CloseAsync();
    }

    [TestMethod]
    public async Task CloseWindow()
    {
        var context = await bidi.BrowsingContext.CreateAsync(ContextType.Window);

        await context.CloseAsync();
    }
}
