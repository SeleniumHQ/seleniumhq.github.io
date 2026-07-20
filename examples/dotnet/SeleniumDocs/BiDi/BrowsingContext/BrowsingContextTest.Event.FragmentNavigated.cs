using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi.BrowsingContext;
using System;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task FragmentNavigatedEvent()
    {
        await context.NavigateAsync("https://www.selenium.dev/selenium/web/linked_image.html", new() { Wait = ReadinessState.Complete });

        TaskCompletionSource<FragmentNavigatedEventArgs> tcs = new();

        await context.FragmentNavigated.SubscribeAsync(tcs.SetResult);

        await context.NavigateAsync("https://www.selenium.dev/selenium/web/linked_image.html#linkToAnchorOnThisPage", new() { Wait = ReadinessState.Complete });

        var navigationInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(navigationInfo);
        Console.WriteLine(navigationInfo);
    }
}
