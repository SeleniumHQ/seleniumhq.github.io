# Selenium 浏览器自动化项目

URL: https://www.selenium.dev/zh-cn/documentation/

---

Selenium 是支持 web 浏览器自动化的一系列工具和库的综合项目。

它提供了扩展来模拟用户与浏览器的交互，用于扩展浏览器分配的分发服务器，
以及用于实现 [W3C WebDriver 规范](//www.w3.org/TR/webdriver/) 的基础结构，
该 规范 允许您为所有主要 Web 浏览器编写可互换的代码。

这个项目是由志愿者贡献者实现的，他们投入了自己数千小时的时间，
并使源代码[免费提供](https://www.selenium.dev/zh-cn/documentation/about/copyright/index.md#许可)给任何人使用、享受和改进。

Selenium 汇集了浏览器供应商，工程师和爱好者，以进一步围绕 Web 平台自动化进行公开讨论。
该项目组织了[一次年度会议](//seleniumconf.com/)，以教学和培养社区。

Selenium 的核心是 [WebDriver](https://www.selenium.dev/zh-cn/documentation/webdriver/index.md)，这是一个编写指令集的接口，可以在许多浏览器中互换运行。
这里有一个最简单的说明：

**Java**

```java
package dev.selenium.hello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://selenium.dev");

        driver.quit();
    }
}
```

**Python**

```python
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://selenium.dev")

driver.quit()
```

**CSharp**

```csharp
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Hello;

public static class HelloSelenium
{
    public static void Main()
    {
        var driver = new ChromeDriver();

        driver.Navigate().GoToUrl("https://selenium.dev");

        driver.Quit();
    }
}
```

**Ruby**

```ruby
# frozen_string_literal: true

require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://selenium.dev'

driver.quit
```

**JavaScript**

```javascript
const {Builder, Browser} = require('selenium-webdriver');

(async function helloSelenium() {
  let driver = await new Builder().forBrowser(Browser.CHROME).build();

  await driver.get('https://selenium.dev');

  await driver.quit();
})();
```

**Kotlin**

```kotlin
package dev.selenium.hello

import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://selenium.dev")

    driver.quit()
}
```

请参阅 [概述](https://www.selenium.dev/zh-cn/documentation/overview/index.md) 
以检查不同的项目组件,
并确定Selenium是否适合您.

您应该继续阅读 [开始](https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/index.md),
以了解如何安装Selenium,
将其成功用作测试自动化工具,
并将这样的简单测试扩展为
在大型分布式环境,
以及不同操作系统上的环境上
运行多个浏览器的测试.

---

Section pages:

- [概述](https://www.selenium.dev/zh-cn/documentation/overview/index.md): Selenium适合你吗? 请参见不同项目组件的概述.
- [WebDriver](https://www.selenium.dev/zh-cn/documentation/webdriver/index.md): WebDriver以原生的方式驱动浏览器; 在此了解更多内容.
- [Selenium Manager](https://www.selenium.dev/zh-cn/documentation/selenium_manager/index.md): Selenium Manager 是一个用 Rust 语言实现的命令行工具, 为 Selenium 提供了自动化的驱动程序和浏览器管理功能. Selenium 默认绑定使用此工具, 因此您无需下载它, 也不需要在代码中添加任何内容或执行其他操作即可使用它.
- [Grid](https://www.selenium.dev/zh-cn/documentation/grid/index.md): 要在多台计算机上并行运行测试吗? 那么, Grid正是为你准备的.
- [Selenium IDE](https://www.selenium.dev/zh-cn/documentation/ide/index.md): Selenium IDE是一个记录和回放用户操作的独立应用程序.
- [鼓励的行为](https://www.selenium.dev/zh-cn/documentation/test_practices/index.md): Selenium项目的一些测试指南和建议.
- [Legacy](https://www.selenium.dev/zh-cn/documentation/legacy/index.md): 在此部分，您可以找到与Selenium的旧组件有关的所有文档. 这样做纯粹是出于历史原因，而不是鼓励使用已废弃的组件.
- [关于这个文档](https://www.selenium.dev/zh-cn/documentation/about/index.md)
