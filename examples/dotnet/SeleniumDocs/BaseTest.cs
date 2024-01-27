using System;
using System.Diagnostics;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Net.Sockets;
using System.Runtime.InteropServices;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs
{
    public class BaseTest
    {
        protected IWebDriver driver;
        protected Uri GridUrl;
        private Process _webserverProcess;
        private const string ServerJarName = "selenium-server-4.16.1.jar";
        private static readonly string BaseDirectory = AppContext.BaseDirectory;
        private const string RelativePathToGrid = "../../../../../";
        private readonly string _examplesDirectory = Path.GetFullPath(Path.Combine(BaseDirectory, RelativePathToGrid));

        [TestCleanup]
        public void Cleanup()
        {
            driver?.Quit();

            if (_webserverProcess != null)
            {
                StopServer();
            }
        }

        protected void StartDriver(string browserVersion = "stable")
        {
            ChromeOptions options = new ChromeOptions
            {
                BrowserVersion = browserVersion
            };
            driver = new ChromeDriver(options);
        }

        protected async Task StartServer()
        {
            if (_webserverProcess == null || _webserverProcess.HasExited)
            {
                _webserverProcess = new Process();
                _webserverProcess.StartInfo.FileName =
                    RuntimeInformation.IsOSPlatform(OSPlatform.Windows) ? "java.exe" : "java";
                string port = GetFreeTcpPort().ToString();
                GridUrl = new Uri("http://localhost:" + port + "/wd/hub");
                _webserverProcess.StartInfo.Arguments = " -jar " + ServerJarName +
                                                        " standalone --port " + port +
                                                        " --selenium-manager true --enable-managed-downloads true";
                _webserverProcess.StartInfo.WorkingDirectory = _examplesDirectory;
                _webserverProcess.Start();
                await EnsureGridIsRunningAsync();
            }
        }

        private void StopServer()
        {
            if (_webserverProcess != null && !_webserverProcess.HasExited)
            {
                _webserverProcess.Kill();
                _webserverProcess.Dispose();
                _webserverProcess = null;
            }
        }

        private static int GetFreeTcpPort()
        {
            TcpListener l = new TcpListener(IPAddress.Loopback, 0);
            l.Start();
            int port = ((IPEndPoint)l.LocalEndpoint).Port;
            l.Stop();
            return port;
        }

        private async Task EnsureGridIsRunningAsync()
        {
            DateTime timeout = DateTime.Now.Add(TimeSpan.FromSeconds(30));
            bool isRunning = false;
            HttpClient client = new HttpClient();

            while (!isRunning && DateTime.Now < timeout)
            {
                try
                {
                    HttpResponseMessage response = await client.GetAsync(GridUrl + "/status");
                    if (response.IsSuccessStatusCode)
                    {
                        isRunning = true;
                    }
                    else
                    {
                        await Task.Delay(500);
                    }
                }
                catch (HttpRequestException)
                {
                    await Task.Delay(500);
                }
            }

            if (!isRunning)
            {
                throw new TimeoutException("Could not confirm the remote selenium server is running within 30 seconds");
            }
        }
    }
}