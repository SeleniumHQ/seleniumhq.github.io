using System.Collections.Generic;
using System.Reflection;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace SeleniumDocs.TestSupport
{
    // Solution based on — https://matt.kotsenas.com/posts/ignoreif-mstest
    public class TestMethodCustomAttribute : TestMethodAttribute
    {
        public override TestResult[] Execute(ITestMethod testMethod)
        {
            var enabledOsAttributes = FindEnabledAttributes(testMethod);

            foreach (var enabledOsAttribute in enabledOsAttributes)
            {
                if (enabledOsAttribute.ShouldIgnore(testMethod))
                {
                    var message = $"Test not executed. Only enabled on '{enabledOsAttribute.EnabledOnOs}' OS.";
                    return new[]
                    {
                        new TestResult
                        {
                            Outcome = UnitTestOutcome.Inconclusive,
                            TestFailureException = new AssertInconclusiveException(message)
                        }
                    };
                }
            }
            return base.Execute(testMethod);
        }

        private IEnumerable<EnabledOnOsAttribute> FindEnabledAttributes(ITestMethod testMethod)
        {
            var enabledOsAttributes = new List<EnabledOnOsAttribute>();
            enabledOsAttributes.AddRange(testMethod.GetAttributes<EnabledOnOsAttribute>(inherit: true));

            var type = testMethod.MethodInfo.DeclaringType;
            while (type != null)
            {
                enabledOsAttributes.AddRange(type.GetCustomAttributes<EnabledOnOsAttribute>(inherit: true));
                type = type.DeclaringType;
            }
            return enabledOsAttributes;
        }
    }
}