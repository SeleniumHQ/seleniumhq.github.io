using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task SetViewport()
    {
        await context.SetViewportAsync(new() { Viewport = new Viewport(Width: 250, Height: 300), DevicePixelRatio = 5 });
    }
}
