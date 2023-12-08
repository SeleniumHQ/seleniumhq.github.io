using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Internal.Logging;
using OpenQA.Selenium.Remote;
using System;
using System.Collections.Generic;

namespace SeleniumDocs.Troubleshooting
{
    [TestClass]
    public class LoggingTest
    {
        [TestMethod]
        public void Logging()
        {
            Log.SetLevel(LogEventLevel.Trace);

            // TODO: Replace it with coming FileLogHandler
            var testLogHandler = new TestLogHandler();
            Log.Handlers.Add(testLogHandler);

            Log.SetLevel(typeof(RemoteWebDriver), LogEventLevel.Debug);
            Log.SetLevel(typeof(SeleniumManager), LogEventLevel.Info);

            Warn("this is a warning");
            Info("this is useful information");
            Debug("this is detailed debug information");

            Assert.IsTrue(testLogHandler.Messages.Contains("this is a warning"));
            Assert.IsTrue(testLogHandler.Messages.Contains("this is useful information"));
            Assert.IsTrue(testLogHandler.Messages.Contains("this is detailed debug information"));
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

        class TestLogHandler : ILogHandler
        {
            public ILogHandler Clone()
            {
                return this;
            }

            public void Handle(LogEvent logEvent)
            {
                Messages.Add(logEvent.Message);
            }

            public List<string> Messages { get; } = new List<string>();
        }
    }
}
