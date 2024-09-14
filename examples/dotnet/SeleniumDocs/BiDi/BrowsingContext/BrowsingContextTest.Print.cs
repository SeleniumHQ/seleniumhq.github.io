using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.BiDi;
using System.Threading.Tasks;

namespace SeleniumDocs.BiDi.BrowsingContext;

partial class BrowsingContextTest
{
    [TestMethod]
    public async Task PrintPage()
    {
        var context = await driver.AsBidirectionalContextAsync();

        var pdf = await context.PrintAsync(new() { PageRanges = [1, 2, 3..5, new(3, 5), 7..] });

        Assert.IsNotNull(pdf);
        Assert.IsNotNull(pdf.Data);
        Assert.IsNotNull(pdf.ToByteArray());
    }
}
