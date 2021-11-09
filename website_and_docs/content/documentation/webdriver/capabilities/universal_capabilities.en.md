---
title: "Universal Capabilities"
linkTitle: "Universal"
weight: 1
aliases: 
        [
            "/documentation/en/driver_idiosyncrasies/shared_capabilities/", 
            "documentation/webdriver/http_proxies/", 
            "/documentation/en/webdriver/http_proxies/",
            "/documentation/en/webdriver/page_load_strategy/"
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
until the page has fully loaded before responding. This works well for
beginners, but can cause long wait times on pages that load a large
number of third party resources. Using a non default strategy can make
test execution faster in cases like this, but can also introduce flakiness
where elements on the page change position as elements load in and change
size.

The page load strategy queries the
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
as described in the table below:

| Strategy | Ready State | Notes |
| -------- | ----------- | ----- |
| normal | complete | Used by default, waits for all resources to download |
| eager | interactive | DOM access is ready, but other resources like images may still be loading |
| none | Any | Does not block WebDriver at all |

By default, WebDriver will hold off on responding to a `driver.get()` (or) `driver.navigate().to()`
call until the document ready state matches the provided value.

In SPA applications (like Angular, React, Ember) once the dynamic content
is already loaded (I.e once the pageLoadStrategy status is COMPLETE),
clicking on a link or performing some action within the page will not make a new request
to the server as the content is dynamically loaded at the client side without a full page refresh.

SPA applications can load many views dynamically
without any server requests, So pageLoadStrategy
will always show `COMPLETE` status until
we do a new `driver.get()` and `driver.navigate().to()`

WebDriver _pageLoadStrategy_ supports the following values:
* normal
* eager
* none

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
# Navigate to url
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
{{< tab header="JavaScript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("normal");
(async function example() {
let driver = await new Builder().
withCapabilities(caps).
forBrowser('chrome').
build();
try {
// Navigate to Url
await driver.get('https://www.google.com');
}
finally {
await driver.quit();
}
})();
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

## Session timeouts

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

This command alters the size and position of the current 
browsing context window. This command acts as setter 
to `getWindowRect` command which accepts **width**, **height**,
**x**, **y** as _optional_ arguments.

During automation, the current browsing context will be associated 
with window states, which describe the visibility 
of the browser window. The window states are

* maximized
* minimized
* normal
* fullscreen

Setting _Width_ or _Height_ does not guaranteed that the resulting 
window size will exactly match that which was requested. This is because 
some drivers may not be able to resize in single-pixel increments.
Due to this, fetching the window state/details by `getWindowRect` 
may not match the values set in the browser.

## strictFileInteractability

This new capability indicates if strict interactability checks 
should be applied to _input type=file_ elements. As strict interactability 
checks are off by default, there is a change in behaviour 
when using _Element Send Keys_ with hidden file upload controls.

## Proxy

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
# Open URL
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
# this code was written with Selenium 4

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
