using System;
using System.Runtime.InteropServices;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace SeleniumDocs.TestSupport
{
    // Solution based on — https://matt.kotsenas.com/posts/ignoreif-mstest
    [AttributeUsage(AttributeTargets.Class | AttributeTargets.Method)]
    public class DisabledOnOsAttribute : Attribute
    {
        public OSPlatform DisabledOnOs { get; set; }
        
        public DisabledOnOsAttribute(String platform)
        {
            DisabledOnOs = OSPlatform.Create(platform);
        }

        internal bool ShouldIgnore(ITestMethod testMethod)
        {
            return RuntimeInformation.IsOSPlatform(this.DisabledOnOs);
        }
    }
}