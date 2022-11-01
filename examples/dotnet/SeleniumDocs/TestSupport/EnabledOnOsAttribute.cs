using System;
using System.Runtime.InteropServices;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace SeleniumDocs.TestSupport
{
    // Solution based on — https://matt.kotsenas.com/posts/ignoreif-mstest
    [AttributeUsage(AttributeTargets.Class | AttributeTargets.Method)]
    public class EnabledOnOsAttribute : Attribute
    {
        public OSPlatform EnabledOnOs { get; set; }
        
        public EnabledOnOsAttribute(String platform)
        {
            EnabledOnOs = OSPlatform.Create(platform);
        }

        internal bool ShouldIgnore(ITestMethod testMethod)
        {
            return !RuntimeInformation.IsOSPlatform(this.EnabledOnOs);
        }
    }
}