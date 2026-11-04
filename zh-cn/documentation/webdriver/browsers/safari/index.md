# Safari 特定功能

> 这些是特定于Apple Safari浏览器的功能和特性.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/safari/

---

与Chromium和Firefox驱动不同, safari驱动随操作系统安装.
要在 Safari 上启用自动化, 请从终端运行以下命令:

```shell
safaridriver --enable
```

## 选项

所有浏览器通用的Capabilities在[选项页](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md).

Safari独有的Capabilities可以在Apple的页面[关于Safari的WebDriver](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari#2957227) 上找到

使用基本定义的选项启动 Safari 会话如下所示:

**Java**

```java
SafariOptions options = new SafariOptions();
driver = new SafariDriver(options);
```

**Python**

```python
options = webdriver.SafariOptions()
driver = webdriver.Safari(options=options)
```

**CSharp**

```csharp
var options = new SafariOptions();
driver = new SafariDriver(options);
```

**Ruby**

```ruby
it 'basic options' do
  options = Selenium::WebDriver::Options.safari
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.SAFARI)
  .setSafariOptions(options)
  .build();
```

**Kotlin**

```kotlin
val options = SafariOptions()
val driver = SafariDriver(options)
```

### 移动端
那些希望在iOS上自动化Safari的人可以参考 [Appium 项目](//appium.io/).

## 服务

所有浏览器通用的服务设置在 [服务页面](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/service/index.md).

### 日志

与其他浏览器不同,
Safari 浏览器不允许您选择日志的输出位置或更改级别.
一个可用选项是关闭或打开日志.
如果日志处于打开状态，
则可以在以下位置找到它们: `~/Library/Logs/com.apple.WebDriver/`.

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
.withLogging(true)
```

**注意**: Java也允许使用环境变量进行设置;<br>
属性键: `SafariDriverService.SAFARI_DRIVER_LOGGING`<br>
属性值: `"true"` 或 `"false"`

**Python**

_Requires [Selenium v4.26](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.26.0) or later._

```python
service = webdriver.SafariService(enable_logging=True)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby

```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Safari Technology Preview

Apple 提供了其浏览器的开发版本 — [Safari Technology Preview](https://developer.apple.com/safari/technology-preview/)

在代码中使用此版本:

**Java**

```java
options.setUseTechnologyPreview(true);
driver = new SafariDriver(options);
```

**Python**

```python
options = webdriver.SafariOptions()
options.use_technology_preview = True
service = webdriver.SafariService(
    executable_path='/Applications/Safari Technology Preview.app/Contents/MacOS/safaridriver'
)
driver = webdriver.Safari(options=options, service=service)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
                                                'have no support for Safari Technology Preview' do
it 'sets the technology preview' do
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._
