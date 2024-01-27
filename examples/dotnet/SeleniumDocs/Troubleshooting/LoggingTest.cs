using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Internal.Logging;
using OpenQA.Selenium.Remote;
using System;
using System.IO;

namespace SeleniumDocs.Troubleshooting
{
    [TestClass]
    public class LoggingTest
    {
        private const string filePath = "Selenium.log";

        [TestMethod]
        public void Logging()
        {
            Log.SetLevel(LogEventLevel.Trace);

            Log.Handlers.Add(new FileLogHandler(filePath));

            Log.SetLevel(typeof(RemoteWebDriver), LogEventLevel.Debug);
            Log.SetLevel(typeof(SeleniumManager), LogEventLevel.Info);

            Warn("this is a warning");
            Info("this is useful information");
            Debug("this is detailed debug information");

            using (var fileStream = File.Open(filePath, FileMode.Open, FileAccess.Read, FileShare.ReadWrite))
            {
                using (var streamReader = new StreamReader(fileStream))
                {
                    var fileLogContent = streamReader.ReadToEnd();

                    StringAssert.Contains(fileLogContent, "this is a warning");
                    StringAssert.Contains(fileLogContent, "this is useful information");
                    StringAssert.Contains(fileLogContent, "this is detailed debug information");
                }
            }
        }

        [TestCleanup]
        public void TestCleanup()
        {
            // reset log to default
            Log.SetLevel(LogEventLevel.Info)
                .Handlers.Clear()
                .Handlers.Add(new ConsoleLogHandler());
        }

        // logging is only for internal usage
        // hacking it via reflection

        private void Debug(string message)
        {
            LogMessage("Debug", message);
        }

        private void Warn(string message)
        {
            LogMessage("Warn", message);
        }

        private void Info(string message)
        {
            LogMessage("Info", message);
        }

        private void LogMessage(string methodName, string message)
        {
            var getLoggerMethod = typeof(Log).GetMethod("GetLogger", System.Reflection.BindingFlags.Static | System.Reflection.BindingFlags.NonPublic, new Type[] { typeof(Type) });

            var logger = getLoggerMethod.Invoke(null, new object[] { typeof(LoggingTest) });

            var emitMethod = logger.GetType().GetMethod(methodName);

            emitMethod.Invoke(logger, new object[] { message });
        }
    }
}
