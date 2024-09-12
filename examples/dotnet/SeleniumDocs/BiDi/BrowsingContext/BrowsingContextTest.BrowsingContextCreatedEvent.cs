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

        TaskCompletionSource<BrowsingContextInfo> tcs = new();

        await bidi.OnBrowsingContextCreatedAsync(tcs.SetResult);

        driver.SwitchTo().NewWindow(OpenQA.Selenium.WindowType.Window);

        var info = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(info);
        Console.WriteLine(info);
    }
}
