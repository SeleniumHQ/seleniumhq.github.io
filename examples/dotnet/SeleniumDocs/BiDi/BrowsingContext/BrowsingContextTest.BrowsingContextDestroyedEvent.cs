using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task BrowsingContextDestroyedEvent()
    {
        await using var bidi = await driver.AsBidirectionalAsync();

        var browsingContext = await driver.AsBidirectionalContextAsync();

        TaskCompletionSource<BrowsingContextInfo> tcs = new();

        await bidi.OnBrowsingContextDestroyedAsync(tcs.SetResult);

        await browsingContext.CloseAsync();

        var browsingContextInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(browsingContextInfo);
        Assert.AreEqual(browsingContext, browsingContextInfo.Context);
        Console.WriteLine(browsingContextInfo);
    }
}
