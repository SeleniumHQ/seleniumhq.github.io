---
title: "Browser Options"
linkTitle: "Options"
weight: 2
description: >-
  这些capabilities用于所有浏览器.
aliases: [
"/documentation/zh-cn/driver_idiosyncrasies/shared_capabilities/",
"/zh-cn/documentation/webdriver/capabilities/shared_capabilities/",
"/documentation/zh-cn/webdriver/http_proxies/",
"/zh-cn/documentation/webdriver/http_proxies/",
"/zh-cn/documentation/webdriver/capabilities/http_proxies/",
"/documentation/zh-cn/webdriver/page_loading_strategy/",
"/zh-cn/documentation/webdriver/page_loading_strategy/",
"/zh-cn/documentation/webdriver/capabilities/page_loading_strategy/",
"/zh-cn/documentation/capabilitis/shared/",
]
---

In Selenium 3, capabilities were defined in a session by using Desired Capabilities classes.
As of Selenium 4, you must use the browser options classes.
For remote driver sessions, a browser options instance is required as it determines which browser will be used.

These options are described in the w3c specification for [Capabilities](https://w3c.github.io/webdriver/#capabilities).

Each browser has ({{< ref "../browsers/" >}}) that may be defined in addition to the ones defined in the specification.
 
## browserName
 
此功能用于设置既定会话的 `browserName` . 
如果未在远端安装指定的浏览器, 
则会话创建将失败
 
## browserVersion
 
此功能是可选的, 用于在远程端设置可用的浏览器版本. 
例如, 如果在仅安装80版本的系统上询问75版本的Chrome, 
则会话创建将失败
 
## pageLoadStrategy

共有三种类型的页面加载策略.

页面加载策略可以在此链接查询
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState) ,
如下表所述:

| 策略     | 就绪状态        | 备注                            |
|--------|-------------|-------------------------------|
| normal | complete    | 默认值, 等待所有资源下载                 |
| eager  | interactive | DOM 访问已准备就绪, 但诸如图像的其他资源可能仍在加载 |
| none   | Any         | 完全不会阻塞 WebDriver              |

文档的 `document.readyState` 属性描述当前文档的加载状态.

当通过URL导航到新页面时, 
默认情况下, WebDriver将暂缓完成导航方法
(例如, driver.navigate().get())直到文档就绪状态完成. 
这 _并非意味着该页面已完成加载_, 
特别是对于使用 JavaScript 在就绪状态返回完成后
动态加载内容单页应用程序的站点. 
另请注意此行为不适用于单击元素或提交表单后出现的导航行为.

如果由于下载对自动化不重要的资源(例如, 图像、css、js)
而需要很长时间才能加载页面, 
您可以将默认参数`normal`更改为
`eager` 或 `none` 以加快会话加载速度.
此值适用于整个会话, 
因此请确保您的 [等待策略]({{< ref "/documentation/webdriver/waits.md" >}})
足够普适.


### normal (默认值)

WebDriver一直等到 [load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event)
事件触发并返回.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
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
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
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
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='normal'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L27-L33">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
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

WebDriver一直等到 [DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event)
事件触发并返回.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
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
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
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
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='eager'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L7-L13">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
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

WebDriver 仅等待初始页面已下载. 

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
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
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
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
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='none'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L17-L23">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
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

这标识了远端的操作系统, 
获取 `platformName` 将返回操作系统的名称. 

在基于云的供应者中, 
设置 `platformName` 将在远程端设置操作系统.

## acceptInsecureCerts

此功能检查在会话期间导航时
是否使用了过期的 (或) 无效的 `TLS Certificate` .

如果将功能设置为 `false`, 
则页面浏览遇到任何域证书问题时, 
将返回[insecure certificate error](//developer.mozilla.org/zh-CN/docs/Web/WebDriver/Errors/InsecureCertificate) . 
如果设置为 `true`, 则浏览器将信任无效证书.

默认情况下, 此功能将信任所有自签名证书. 
设置后,  `acceptInsecureCerts` 功能将在整个会话中生效.

## timeouts

WebDriver `session` 具有一定的 `session timeout` 间隔, 
在此间隔内, 用户可以控制执行脚本或从浏览器检索信息的行为.

每个会话超时都配置有不同 `timeouts` 的组合, 
如下所述:

### Script Timeout:
指定在当前浏览上下文中, 中断正在执行脚本的时机. 
WebDriver创建新会话时, 
将设置默认的超时时间为 **30,000** .

### Page Load Timeout:
指定在当前浏览上下文中, 加载网页的时间间隔. 
WebDriver创建新会话时, 
默认设置超时时间为 **300,000** . 
如果页面加载限制了给定 (或默认) 的时间范围, 
则该脚本将被 _TimeoutException_ 停止.

### Implicit Wait Timeout
指定在定位元素时, 等待隐式元素定位策略的时间. 
WebDriver创建新会话时, 
将设置默认超时时间为 **0** .

## unhandledPromptBehavior

指定当前会话 `user prompt handler` 的状态. 
默认为 **dismiss and notify state** . 

### User Prompt Handler

这定义了在远端出现用户提示时必须采取的措施. 
该行为由`unhandledPromptBehavior` 功能定义, 
具有以下状态:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

## setWindowRect

用于所有支持 [调整大小和重新定位](https://w3c.github.io/webdriver/#resizing-and-positioning-windows) 
[命令](https://w3c.github.io/webdriver/#dfn-commands) 的远程终端.

## strictFileInteractability

新功能用于是否对 _类型为文件的输入(input type=file)_ 元素进行严格的交互性检查.
默认关闭严格性检查, 
在将 _元素的Send Keys_ 方法作用于隐藏的文件上传时, 
会有控制方面的行为区别.

## proxy

代理服务器充当客户端和服务器之间的请求中介.
简述而言, 流量将通过代理服务器流向您请求的地址, 然后返回.

使用代理服务器用于Selenium的自动化脚本,
可能对以下方面有益:

* 捕获网络流量
* 模拟网站后端响应
* 在复杂的网络拓扑结构或严格的公司限制/政策下访问目标站点.

如果您在公司环境中,
并且浏览器无法连接到URL,
则最有可能是因为环境, 需要借助代理进行访问.

Selenium WebDriver提供了如下设置代理的方法

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
