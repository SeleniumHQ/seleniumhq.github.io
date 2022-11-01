using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace SeleniumDocs.TestSupport
{
    // Solution based on — https://matt.kotsenas.com/posts/ignoreif-mstest
    public class TestClassCustomAttribute : TestClassAttribute
    {
        public override TestMethodAttribute GetTestMethodAttribute(TestMethodAttribute testMethodAttribute)
        {
            if (testMethodAttribute is TestMethodCustomAttribute)
            {
                return testMethodAttribute;
            }
            return new TestMethodCustomAttribute();
        }
    }
}