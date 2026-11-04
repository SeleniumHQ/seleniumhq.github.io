# 浏览器选项

> 这些capabilities用于所有浏览器.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/

---

在 Selenium 3 中, capabilities是借助"Desired Capabilities"类定义于会话中的. 
从 Selenium 4 开始, 您必须使用浏览器选项类. 
对于远程驱动程序会话, 浏览器选项实例是必需的, 因为它确定将使用哪个浏览器. 

这些选项在 [Capabilities](https://w3c.github.io/webdriver/#capabilities) 的 w3c 规范中进行了描述. 

每个浏览器都有 [自定义选项](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/index.md) , 是规范定义之外的内容. 

## browserName

默认情况下，使用 Options 类实例时会设置浏览器名称.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String name = chromeOptions.getBrowserName();
```

**Python**

```python
options = get_default_chrome_options()
assert options.capabilities['browserName'] == 'chrome'
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## browserVersion

此功能是可选的，用于在远程端设置可用的浏览器版本.
在最新版本的 Selenium 中，如果在系统上找不到该版本,
它将被 [Selenium Manager](https://www.selenium.dev/zh-cn/documentation/selenium_manager/index.md) 自动下载

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String version = "latest";
chromeOptions.setBrowserVersion(version);
```

**Python**

```python
options = get_default_chrome_options()
options.browser_version = 'stable'
assert options.capabilities['browserVersion'] == 'stable'
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options.browser_version = 'latest'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

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
因此请确保您的 [等待策略](https://www.selenium.dev/zh-cn/documentation/webdriver/waits/index.md)
足够普适.

### normal (默认值)

WebDriver一直等到 [load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event)
事件触发并返回.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :normal
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('normal'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
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
```

### eager

WebDriver一直等到 [DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event)
事件触发并返回.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :eager
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('eager'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
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
```

### none

WebDriver 仅等待初始页面已下载. 

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
[TestMethod]
public void SetPageLoadStrategyNone()
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :none
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('none'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
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
```

## platformName

这标识了远端的操作系统, 
获取 `platformName` 将返回操作系统的名称. 

在基于云的供应者中, 
设置 `platformName` 将在远程端设置操作系统.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String platform = "OS X 10.6";
chromeOptions.setPlatformName(platform);
```

**Python**

```python
options = get_default_chrome_options()
options.platform_name = 'any'
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
options.platform_name = 'Windows 10'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## acceptInsecureCerts

此功能检查在会话期间导航时
是否使用了过期的 (或) 无效的 `TLS Certificate` .

如果将功能设置为 `false`, 
则页面浏览遇到任何域证书问题时, 
将返回[insecure certificate error](//developer.mozilla.org/zh-CN/docs/Web/WebDriver/Errors/InsecureCertificate) . 
如果设置为 `true`, 则浏览器将信任无效证书.

默认情况下, 此功能将信任所有自签名证书. 
设置后,  `acceptInsecureCerts` 功能将在整个会话中生效.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setAcceptInsecureCerts(true);
```

**Python**

```python
options = get_default_chrome_options()
options.accept_insecure_certs = True
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.accept_insecure_certs = true
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setAcceptInsecureCerts(true))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## timeouts

WebDriver `session` 具有一定的 `session timeout` 间隔, 
在此间隔内, 用户可以控制执行脚本或从浏览器检索信息的行为.

每个会话超时都配置有不同 `timeouts` 的组合, 
如下所述:

### Script Timeout:
指定在当前浏览上下文中, 中断正在执行脚本的时机. 
WebDriver创建新会话时, 
将设置默认的超时时间为 **30,000** .

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setScriptTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'script': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {script: 40_000}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Page Load Timeout:
指定在当前浏览上下文中, 加载网页的时间间隔. 
WebDriver创建新会话时, 
默认设置超时时间为 **300,000** . 
如果页面加载限制了给定 (或默认) 的时间范围, 
则该脚本将被 _TimeoutException_ 停止.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setPageLoadTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'pageLoad': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {page_load: 400_000}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Implicit Wait Timeout
指定在定位元素时, 等待隐式元素定位策略的时间. 
WebDriver创建新会话时, 
将设置默认超时时间为 **0** .

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setImplicitWaitTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'implicit': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {implicit: 1}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
```

**Python**

```python
options = get_default_chrome_options()
options.unhandled_prompt_behavior = 'accept'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.DismissAndNotify;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.unhandled_prompt_behavior = :accept
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

注意：对于 `beforeunload` 提示框，
除了启用 [BiDi](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/index.md) 外，
ChromeDriver 还需要设置 `ignore` 才能保持提示框打开。

## setWindowRect

用于所有支持 [调整大小和重新定位](https://w3c.github.io/webdriver/#resizing-and-positioning-windows) 
[命令](https://w3c.github.io/webdriver/#dfn-commands) 的远程终端.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setCapability(CapabilityType.SET_WINDOW_RECT, true);
```

**Python**

```python
options = webdriver.FirefoxOptions()
options.set_window_rect = True # Full support in Firefox
driver = webdriver.Firefox(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
options.set_window_rect = true
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## strictFileInteractability

新功能用于是否对 _类型为文件的输入(input type=file)_ 元素进行严格的交互性检查.
默认关闭严格性检查, 
在将 _元素的Send Keys_ 方法作用于隐藏的文件上传时, 
会有控制方面的行为区别.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setCapability(CapabilityType.STRICT_FILE_INTERACTABILITY, true);
```

**Python**

```python
options = get_default_chrome_options()
options.strict_file_interactability = True
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.strict_file_interactability = true
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

**Java**

```java
Proxy proxy = new Proxy();
proxy.setHttpProxy("myproxy.com:8080");
ChromeOptions options = getDefaultChromeOptions();
options.setCapability("proxy", proxy);
```

**Python**

```python
options = get_default_chrome_options()
options.proxy = Proxy({ 'proxyType': ProxyType.MANUAL, 'httpProxy' : 'http.proxy:1234'})
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
ChromeOptions options = new ChromeOptions();
Proxy proxy = new Proxy();
proxy.Kind = ProxyKind.Manual;
proxy.IsAutoDetect = false;
proxy.SslProxy = "myproxy.com:8080";
options.Proxy = proxy;
options.AddArgument("ignore-certificate-errors");
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.proxy = Selenium::WebDriver::Proxy.new(http: 'myproxy.com:8080')
```

**JavaScript**

```javascript
const options = new chrome.Options()
options.setProxy(proxy.manual({ http: 'myproxy.com:8080' }))
```

**Kotlin**

```kotlin
val proxy = Proxy()
proxy.setHttpProxy("myproxy.com:8080")
val options = ChromeOptions()
options.setCapability("proxy", proxy)
```
