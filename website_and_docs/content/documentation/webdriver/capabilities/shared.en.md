---
title: "Shared capabilities"
linkTitle: "Shared"
weight: 2
description: >-
  These capabilities are shared by all browsers.
aliases: [
"/documentation/en/driver_idiosyncrasies/shared_capabilities/",
"/documentation/webdriver/capabilities/shared_capabilities/",
"/documentation/en/webdriver/http_proxies/",
"/documentation/webdriver/http_proxies/",
"/documentation/webdriver/capabilities/http_proxies/",
"/documentation/en/webdriver/page_loading_strategy/",
"/documentation/webdriver/page_loading_strategy/",
"/documentation/webdriver/capabilities/page_loading_strategy/"
]
---

In order to create a new session by Selenium WebDriver, 
the local end should provide the basic capabilities to the remote end. 
The remote end uses the same set of capabilities to 
create a session and describes the current session features. 

WebDriver provides capabilities that each remote 
end will/should support the implementation. 
The following capabilities are supported by WebDriver:

## browserName:

This capability is used to set the `browserName` for a given session. 
If the specified browser is not installed at the 
remote end, the session creation will fail.

## browserVersion: 

This capability is optional, this is used to 
set the available browser version at remote end. 
For Example, if ask for Chrome version 75 on a system that 
only has 80 installed, the session creation will fail.

## pageLoadStrategy:

When navigating to a new page via URL, by default Selenium will wait
until the Document's Ready State is "complete." 
The `document.readyState` property of a document describes the loading state of the current document.
By default, WebDriver will hold off on completing a navigation method (e.g., `driver.navigate().get()`)
until the document ready state is `complete`. This does not
necessarily mean that the page has finished loading, especially for sites
like Single Page Applications that use a lot of JavaScript to dynamically load content
after the Ready State returns complete. Note also that this behavior does not apply to navigation
that is a result of clicking an element or submitting a form.

If a page takes a long time to load as a result of downloading assets (e.g., images, css, js) 
that aren't important to the automation, 
you can change from the default parameter of `normal` to
`eager` or `none` to speed up the session. This value applies to the entire
session, so make sure that your [waiting strategy]({{< ref "/documentation/webdriver/waits.md" >}}) is sufficient
to minimize flakiness.


The page load strategy queries the
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
as described in the table below:

| Strategy | Ready State | Notes |
| -------- | ----------- | ----- |
| normal | complete | Used by default, waits for all resources to download |
| eager | interactive | DOM access is ready, but other resources like images may still be loading |
| none | Any | Does not block WebDriver at all |

### normal

This will make Selenium WebDriver to wait for the entire page is loaded.
When set to **normal**, Selenium WebDriver waits until the
[load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event) event fire is returned.

By default **normal** is set to browser if none is provided.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
public static void main(String[] args) {
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
WebDriver driver = new ChromeDriver(chromeOptions);
try {
// Navigate to Url
driver.get("https://google.com");
} finally {
driver.quit();
}
}
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()

{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
class pageLoadStrategy {
public static void Main(string[] args) {
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
IWebDriver driver = new ChromeDriver(chromeOptions);
try {
driver.Navigate().GoToUrl("https://example.com");
} finally {
driver.Quit();
}
}
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='normal'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" disableCodeBlock=true >}}
  {{< gh-codeblock path="/examples/javascript/capabilities/normal.js">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
val chromeOptions = ChromeOptions()
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL)
val driver = ChromeDriver(chromeOptions)
try {
driver.get("https://www.google.com")
}
finally {
driver.quit()
}
}
{{< /tab >}}
{{< /tabpane >}}

### eager

This will make Selenium WebDriver to wait until the
initial HTML document has been completely loaded and parsed,
and discards loading of stylesheets, images and subframes.

When set to **eager**, Selenium WebDriver waits until
[DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event) event fire is returned.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
public static void main(String[] args) {
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
WebDriver driver = new ChromeDriver(chromeOptions);
try {
// Navigate to Url
driver.get("https://google.com");
} finally {
driver.quit();
}
}
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
class pageLoadStrategy {
public static void Main(string[] args) {
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
IWebDriver driver = new ChromeDriver(chromeOptions);
try {
driver.Navigate().GoToUrl("https://example.com");
} finally {
driver.Quit();
}
}
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='eager'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" disableCodeBlock=true >}}
  {{< gh-codeblock path="/examples/javascript/capabilities/eager.js">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
val chromeOptions = ChromeOptions()
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER)
val driver = ChromeDriver(chromeOptions)
try {
driver.get("https://www.google.com")
}
finally {
driver.quit()
}
}
{{< /tab >}}
{{< /tabpane >}}

### none

When set to **none** Selenium WebDriver only waits until the initial page is downloaded.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
public static void main(String[] args) {
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
WebDriver driver = new ChromeDriver(chromeOptions);
try {
// Navigate to Url
driver.get("https://google.com");
} finally {
driver.quit();
}
}
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
class pageLoadStrategy {
public static void Main(string[] args) {
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.None;
IWebDriver driver = new ChromeDriver(chromeOptions);
try {
driver.Navigate().GoToUrl("https://example.com");
} finally {
driver.Quit();
}
}
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='none'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" disableCodeBlock=true >}}
  {{< gh-codeblock path="/examples/javascript/capabilities/none.js">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
val chromeOptions = ChromeOptions()
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE)
val driver = ChromeDriver(chromeOptions)
try {
driver.get("https://www.google.com")
}
finally {
driver.quit()
}
}
{{< /tab >}}
{{< /tabpane >}}


## platformName

This identifies the operating system at the remote-end, 
fetching the `platformName` returns the OS name. 

In cloud-based providers, 
setting `platformName` sets the OS at the remote-end.

## acceptInsecureCerts

This capability checks whether an expired (or) 
invalid `TLS Certificate` is used while navigating 
during a session.

If the capability is set to `false`, an 
[insecure certificate error](//developer.mozilla.org/en-US/docs/Web/WebDriver/Errors/InsecureCertificate) 
will be returned as navigation encounters any domain 
certificate problems. If set to `true`, invalid certificate will be 
trusted by the browser.

All self-signed certificates will be trusted by this capability by default. 
Once set, `acceptInsecureCerts` capability will have an 
effect for the entire session.

## timeouts

A WebDriver `session` is imposed with a certain `session timeout`
interval, during which the user can control the behaviour
of executing scripts or retrieving information from the browser.

Each session timeout is configured with
combination of different `timeouts` as described below:

### Script Timeout:
Specifies when to interrupt an executing script in
a current browsing context. The default timeout **30,000**
is imposed when a new session is created by WebDriver.

### Page Load Timeout:
Specifies the time interval in which web page
needs to be loaded in a current browsing context.
The default timeout **300,000** is imposed when a
new session is created by WebDriver. If page load limits
a given/default time frame, the script will be stopped by
_TimeoutException_.

### Implicit Wait Timeout
This specifies the time to wait for the
implicit element location strategy when
locating elements. The default timeout **0**
is imposed when a new session is created by WebDriver.

## unhandledPromptBehavior

Specifies the state of current session's `user prompt handler`. 
Defaults to **dismiss and notify state**

### User Prompt Handler

This defines what action must take when a 
user prompt encounters at the remote-end. This is defined by 
`unhandledPromptBehavior` capability and has the following states:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

## setWindowRect

Indicates whether the remote end supports all of the [resizing and repositioning](https://w3c.github.io/webdriver/#resizing-and-positioning-windows) [commands](https://w3c.github.io/webdriver/#dfn-commands).

## strictFileInteractability

This new capability indicates if strict interactability checks 
should be applied to _input type=file_ elements. As strict interactability 
checks are off by default, there is a change in behaviour 
when using _Element Send Keys_ with hidden file upload controls.


## proxy

A proxy server acts as an intermediary for
requests between a client and a server. In simple,
the traffic flows through the proxy server
on its way to the address you requested and back.

A proxy server for automation scripts
with Selenium could be helpful for:

* Capture network traffic
* Mock backend calls made by the website
* Access the required website under complex network
  topologies or strict corporate restrictions/policies.

If you are in a corporate environment, and a
browser fails to connect to a URL, this is most
likely because the environment needs a proxy to be accessed.

Selenium WebDriver provides a way to proxy settings:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class proxyTest {
public static void main(String[] args) {
Proxy proxy = new Proxy();
proxy.setHttpProxy("<HOST:PORT>");
ChromeOptions options = new ChromeOptions();
options.setCapability("proxy", proxy);
WebDriver driver = new ChromeDriver(options);
driver.get("https://www.google.com/");
driver.manage().window().maximize();
driver.quit();
}
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver

PROXY = "<HOST:PORT>"
webdriver.DesiredCapabilities.FIREFOX['proxy'] = {
"httpProxy": PROXY,
"ftpProxy": PROXY,
"sslProxy": PROXY,
"proxyType": "MANUAL",

}

with webdriver.Firefox() as driver:
driver.get("https://selenium.dev")

{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

public class ProxyTest{
public static void Main() {
ChromeOptions options = new ChromeOptions();
Proxy proxy = new Proxy();
proxy.Kind = ProxyKind.Manual;
proxy.IsAutoDetect = false;
proxy.SslProxy = "<HOST:PORT>";
options.Proxy = proxy;
options.AddArgument("ignore-certificate-errors");
IWebDriver driver = new ChromeDriver(options);
driver.Navigate().GoToUrl("https://www.selenium.dev/");
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}

proxy = Selenium::WebDriver::Proxy.new(http: '<HOST:PORT>')
cap   = Selenium::WebDriver::Remote::Capabilities.chrome(proxy: proxy)

driver = Selenium::WebDriver.for(:chrome, capabilities: cap)
driver.get('http://google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
let webdriver = require('selenium-webdriver');
let chrome = require('selenium-webdriver/chrome');
let proxy = require('selenium-webdriver/proxy');
let opts = new chrome.Options();

(async function example() {
opts.setProxy(proxy.manual({http: '<HOST:PORT>'}));
let driver = new webdriver.Builder()
.forBrowser('chrome')
.setChromeOptions(opts)
.build();
try {
await driver.get("https://selenium.dev");
}
finally {
await driver.quit();
}
}());
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class proxyTest {
fun main() {

        val proxy = Proxy()
        proxy.setHttpProxy("<HOST:PORT>")
        val options = ChromeOptions()
        options.setCapability("proxy", proxy)
        val driver: WebDriver = ChromeDriver(options)
        driver["https://www.google.com/"]
        driver.manage().window().maximize()
        driver.quit()
    }
}
{{< /tab >}}
{{< /tabpane >}}
