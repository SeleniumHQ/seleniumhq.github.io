using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task CaptureScreenshot()
    {
        var screenshot = await context.CaptureScreenshotAsync();

        Assert.IsNotNull(screenshot);
        Assert.IsNotNull(screenshot.Data);
        Assert.IsNotNull(screenshot.ToByteArray());
    }

    [TestMethod]
    public async Task CaptureViewportScreenshot()
    {
        var screenshot = await context.CaptureScreenshotAsync(new() { Clip = new BoxClipRectangle(5, 5, 10, 10) });

        Assert.IsNotNull(screenshot);
        Assert.IsNotNull(screenshot.Data);
    }

    [TestMethod]
    public async Task CaptureElementScreenshot()
    {
        driver.Url = "https://www.selenium.dev/selenium/web/formPage.html";

        var nodes = (await context.LocateNodesAsync(new CssLocator("#checky"))).Nodes;

        Assert.IsTrue(nodes.Length > 0, "Expected to locate #checky");

        var screenshot = await context.CaptureScreenshotAsync(new() { Clip = new ElementClipRectangle(nodes[0]) });

        Assert.IsNotNull(screenshot);
        Assert.IsNotNull(screenshot.Data);
    }
}
