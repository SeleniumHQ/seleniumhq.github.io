using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

[TestClass]
public partial class BrowsingContextTest : BaseChromeTest
{
    private OpenQA.Selenium.BiDi.BiDi bidi;

    private OpenQA.Selenium.BiDi.Modules.BrowsingContext.BrowsingContext context;

    [TestInitialize]
    public async Task InitializeBidi()
    {
        bidi = await driver.AsBiDiAsync();

        context = (await bidi.BrowsingContext.GetTreeAsync())[0].Context;
    }
}
