using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task BrowsingContextCreatedEvent()
    {
        TaskCompletionSource<ContextCreatedEventArgs> tcs = new();

        await bidi.BrowsingContext.ContextCreated.SubscribeAsync(args => tcs.TrySetResult(args));

        driver.SwitchTo().NewWindow(OpenQA.Selenium.WindowType.Window);

        var info = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(info);
        Console.WriteLine(info);
    }
}
