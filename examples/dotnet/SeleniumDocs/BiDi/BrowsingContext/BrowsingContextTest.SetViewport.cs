using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task SetViewport()
    {
        var context = await driver.AsBiDirectionalContextAsync();

        await context.SetViewportAsync(new() { Viewport = new(Width: 250, Height: 300), DevicePixelRatio = 5 });
    }
}
