using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task BrowsingContextCreatedEvent()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        BrowsingContextInfo info = null;

        await bidi.OnBrowsingContextCreatedAsync(e => info = e);

        driver.SwitchTo().NewWindow(OpenQA.Selenium.WindowType.Window);

        Assert.IsNotNull(info);
        Console.WriteLine(info);
    }
}
