using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task CloseWindowOrTab()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var window = await bidi.CreateBrowsingContextAsync(BrowsingContextType.Window);

        var tab = await bidi.CreateBrowsingContextAsync(BrowsingContextType.Tab);

        await window.CloseAsync();

        await tab.CloseAsync();
    }
}
