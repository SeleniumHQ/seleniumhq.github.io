# 驱动服务类

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/service/

---

服务类用于管理驱动程序的启动和停止.
它们不能与远程 WebDriver 会话一起使用.

服务类允许您指定有关驱动程序的信息,
诸如位置和要使用的端口.
它们还允许您指定传递哪些参数到命令行.
大多数有用的参数都与日志记录有关.

## 默认服务实例

使用默认服务实例启动驱动程序:

**Java**

```java
ChromeDriverService service = new ChromeDriverService.Builder().build();
driver = new ChromeDriver(service);
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService()
driver = webdriver.Chrome(service=service)
```

**CSharp**

```csharp
var service = ChromeDriverService.CreateDefaultService();
driver = new ChromeDriver(service);
```

**Ruby**

```ruby
service = Selenium::WebDriver::Service.chrome
@driver = Selenium::WebDriver.for :chrome, service: service
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 驱动程序位置

**注意:** 如果您使用的是 Selenium 4.6 或更高版本, 
则无需设置驱动程序位置.
如果您无法更新 Selenium 或有高阶用法需求, 
以下是指定驱动程序位置的方法:

**Java**

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().usingDriverExecutable(driverPath).build();
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(executable_path=chromedriver_bin)
```

**CSharp**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```csharp
var service = ChromeDriverService.CreateDefaultService(await GetDriverLocationAsync(options));
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.executable_path = driver_path
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 驱动程序端口

如果希望驱动程序在特定端口上运行, 
您可以在启动时指定端口号, 如下所示:

**Java**

```java
ChromeDriverService service = new ChromeDriverService.Builder().usingPort(1234).build();
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(port=1234)
```

**CSharp**

```csharp
service.Port = 1234;
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.port = 1234
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

<span id="setting-log-output"></span>
## 日志

日志记录功能因浏览器而异.
大多数浏览器都允许您指定日志的位置和级别.
请查看相应的浏览器页面:
* [Chrome](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/chrome/index.md#service)
* [Edge](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/edge/index.md#service)
* [Firefox](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/firefox/index.md#service)
* [Internet Explorer](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/internet_explorer/index.md#service)
* [Safari](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/safari/index.md#service)
