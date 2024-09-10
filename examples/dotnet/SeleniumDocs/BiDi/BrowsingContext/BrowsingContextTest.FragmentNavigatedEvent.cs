using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules.BrowsingContext;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task FragmentNavigatedEvent()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        TaskCompletionSource<NavigationInfo> tcs = new();

        await browsingContext.OnFragmentNavigatedAsync(tcs.SetResult);

        await browsingContext.NavigateAsync("https://www.selenium.dev/selenium/web/linked_image.html#linkToAnchorOnThisPage", new() { Wait = ReadinessState.Complete });

        var navigationInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

        Assert.IsNotNull(navigationInfo);
        Console.WriteLine(navigationInfo);
    }
}
