using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task PrintPage()
    {
        var browsingContext = await driver.AsBidirectionalContextAsync();

        var pdf = await browsingContext.PrintAsync(new() { PageRanges = [1, 2, new(3, 5)] });

        Assert.IsNotNull(pdf);
        Assert.IsNotNull(pdf.Data);
        Assert.IsNotNull(pdf.ToByteArray());
    }
}
