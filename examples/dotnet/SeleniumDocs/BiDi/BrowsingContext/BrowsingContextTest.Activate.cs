using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task Activate()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var tab = await bidi.CreateContextAsync(ContextType.Tab);

        await tab.ActivateAsync();
    }
}
