using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task BrowsingContextDestroyedEvent()
    {
        TaskCompletionSource<BrowsingContextInfo> tcs = new();

        await bidi.BrowsingContext.OnContextDestroyedAsync(tcs.SetResult);

        await context.CloseAsync();

        var contextInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(contextInfo);
        Assert.AreEqual(context, contextInfo.Context);
        Console.WriteLine(contextInfo);
    }
}
