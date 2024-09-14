using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task SetViewport()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        await browsingContext.SetViewportAsync(new() { Viewport = new(250, 300), DevicePixelRatio = 5 });
    }
}
