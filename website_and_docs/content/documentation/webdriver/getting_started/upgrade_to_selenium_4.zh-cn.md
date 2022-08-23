---
title:  "升级到Selenium 4"
linkTitle:  "升级到Selenium 4"
weight:  10
description:  >
  对Selenium 4感兴趣? 查看本指南, 它将帮助您升级到最新版本!
aliases: [
"/zh-cn/documentation/getting_started/how_to_upgrade_to_selenium_4/"
]
---


如果您使用的是官方支持的语言
(Ruby、JavaScript、C#、Python和Java), 
那么升级到Selenium 4应该是一个轻松的过程.
在某些情况下可能会出现一些问题, 
本指南将帮助您解决这些问题.
我们将完成升级项目依赖项的步骤, 
并了解版本升级带来的主要反对意见和更改.

请按照以下步骤升级到Selenium 4: 
* 准备我们的测试代码
* 升级依赖
* 潜在错误和弃用消息

注意：在开发Selenium 3.x版本的同时, 
实现了对W3C WebDriver标准的支持. 
此新协议和遗留JSON Wire协议均受支持. 
在3.11版前后, Selenium代码与W3C 1级规范兼容. 
最新版本的Selenium 3中的W3C兼容代码将在Selenium 4中正常工作.


## 准备测试代码

Selenium 4 移除了对遗留协议的支持, 
并在底层实现上默认使用 W3C WebDriver 标准.  
对于大多数情况, 这种实现不会影响终端用户.  
主要的例外是 `Capabilities` 和 `Actions` 类.

### Capabilities
如果测试capabilities的结构不符合 W3C标准, 
可能会导致会话无法正常开启.  
以下是 W3C WebDriver 标准capabilities列表: 
* `browserName`
* `browserVersion` (替代 `version`)
* `platformName` (替代 `platform`)
* `acceptInsecureCerts`
* `pageLoadStrategy`
* `proxy`
* `timeouts`
* `unhandledPromptBehavior`

可以在以下位置找到标准capabilities的最新列表
[W3C WebDriver](https://www.w3.org/TR/webdriver1/#capabilities).

上面列表中未包含的任何capability,
都需要包含供应商前缀. 
这适用于浏览器特定capability
以及云供应商特定capability. 
例如, 如果您的云供应商为您的测试
使用 `build` 和 `name` capability, 
您需要将它们包装在一个 `cloud: options` 块中
(请与您的云供应商联系以获取适当的前缀). 

#### Before 
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
DesiredCapabilities caps = DesiredCapabilities.firefox();
caps.setCapability("platform", "Windows 10");
caps.setCapability("version", "92");
caps.setCapability("build", myTestBuild);
caps.setCapability("name", myTestName);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), caps);
{{< /tab >}}
{{< tab header="JavaScript" >}}
caps = {};
caps['browserName'] = 'Firefox';
caps['platform'] = 'Windows 10';
caps['version'] = '92';
caps['build'] = myTestBuild;
caps['name'] = myTestName;
{{< /tab >}}
{{< tab header="CSharp" >}}
DesiredCapabilities caps = new DesiredCapabilities();
caps.SetCapability("browserName", "firefox");
caps.SetCapability("platform", "Windows 10");
caps.SetCapability("version", "92");
caps.SetCapability("build", myTestBuild);
caps.SetCapability("name", myTestName);
var driver = new RemoteWebDriver(new Uri(CloudURL), caps);
{{< /tab >}}
{{< tab header="Ruby" >}}
caps = Selenium: : WebDriver: : Remote: : Capabilities.firefox
caps[: platform] = 'Windows 10'
caps[: version] = '92'
caps[: build] = my_test_build
caps[: name] = my_test_name
driver = Selenium: : WebDriver.for : remote, url:  cloud_url, desired_capabilities:  caps
{{< /tab >}}
{{< tab header="Python" >}}
caps = {}
caps['browserName'] = 'firefox'
caps['platform'] = 'Windows 10'
caps['version'] = '92'
caps['build'] = my_test_build
caps['name'] = my_test_name
driver = webdriver.Remote(cloud_url, desired_capabilities=caps)
{{< /tab >}}
{{< /tabpane >}}

#### After
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
FirefoxOptions browserOptions = new FirefoxOptions();
browserOptions.setPlatformName("Windows 10");
browserOptions.setBrowserVersion("92");
Map<String, Object> cloudOptions = new HashMap<>();
cloudOptions.put("build", myTestBuild);
cloudOptions.put("name", myTestName);
browserOptions.setCapability("cloud: options", cloudOptions);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), browserOptions);
{{< /tab >}}
{{< tab header="JavaScript" >}}
capabilities = {
  browserName:  'firefox',
  browserVersion:  '92',
  platformName:  'Windows 10',
  'cloud: options':  {
     build:  myTestBuild,
     name:  myTestName,
  }
}
{{< /tab >}}
{{< tab header="CSharp" >}}
var browserOptions = new FirefoxOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "92";
var cloudOptions = new Dictionary<string, object>();
cloudOptions.Add("build", myTestBuild);
cloudOptions.Add("name", myTestName);
browserOptions.AddAdditionalOption("cloud: options", cloudOptions);
var driver = new RemoteWebDriver(new Uri(CloudURL), browserOptions);
{{< /tab >}}
{{< tab header="Ruby" >}}
options = Selenium: : WebDriver: : Options.firefox
options.browser_version = 'latest'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options[: build] = my_test_build
cloud_options[: name] = my_test_name
options.add_option('cloud: options', cloud_options)
driver = Selenium: : WebDriver.for : remote, url:  cloud_url, capabilities:  options
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options as FirefoxOptions
options = FirefoxOptions()
options.browser_version = '92'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options['build'] = my_test_build
cloud_options['name'] = my_test_name
options.set_capability('cloud: options', cloud_options)
driver = webdriver.Remote(cloud_url, options=options)
{{< /tab >}}
{{< /tabpane >}}

### 在 Java 中查找元素工具方法
在 Java 绑定(`FindsBy` 接口)中
查找元素的工具方法已被删除
因为它们仅供内部使用.  
以下代码示例更好地解释了这一点. 

使用 `findElement*` 查找单个元素

{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.findElementByClassName("className");
driver.findElementByCssSelector(".className");
driver.findElementById("elementId");
driver.findElementByLinkText("linkText");
driver.findElementByName("elementName");
driver.findElementByPartialLinkText("partialText");
driver.findElementByTagName("elementTagName");
driver.findElementByXPath("xPath");
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.findElement(By.className("className"));
driver.findElement(By.cssSelector(".className"));
driver.findElement(By.id("elementId"));
driver.findElement(By.linkText("linkText"));
driver.findElement(By.name("elementName"));
driver.findElement(By.partialLinkText("partialText"));
driver.findElement(By.tagName("elementTagName"));
driver.findElement(By.xpath("xPath"));
```
{{< /card >}}
{{< /cardpane >}}


使用 `findElements*` 查找多个元素

{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.findElementsByClassName("className");
driver.findElementsByCssSelector(".className");
driver.findElementsById("elementId");
driver.findElementsByLinkText("linkText");
driver.findElementsByName("elementName");
driver.findElementsByPartialLinkText("partialText");
driver.findElementsByTagName("elementTagName");
driver.findElementsByXPath("xPath");
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.findElements(By.className("className"));
driver.findElements(By.cssSelector(".className"));
driver.findElements(By.id("elementId"));
driver.findElements(By.linkText("linkText"));
driver.findElements(By.name("elementName"));
driver.findElements(By.partialLinkText("partialText"));
driver.findElements(By.tagName("elementTagName"));
driver.findElements(By.xpath("xPath"));
```
{{< /card >}}
{{< /cardpane >}}


## 升级依赖

检查下面的小节以安装 Selenium 4
并升级您的项目依赖项.

### Java

升级 Selenium 的过程取决于所使用的构建工具.  
我们将涵盖Java 中最常见的
[Maven](https://maven.apache.org/) 和
[Gradle](https://gradle.org/) .  
所需的最低 Java 版本仍然是 8.

#### Maven

{{< cardpane >}}
{{< card header="Before" >}}
```xml
<dependencies>
  <!-- more dependencies ... -->
  <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.141.59</version>
  </dependency>
  <!-- more dependencies ... -->
</dependencies>
```
{{< /card >}}
{{< card header="After" >}}
```xml
<dependencies>
    <!-- more dependencies ... -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.4.0</version>
    </dependency>
    <!-- more dependencies ... -->
</dependencies>
```
{{< /card >}}
{{< /cardpane >}}

进行更改后, 
您可以在`pom.xml` 文件的同一目录中
执行 `mvn clean compile` .

#### Gradle

{{< cardpane >}}
{{< card header="Before" >}}
```jsonpath
plugins {
    id 'java'
}
group 'org.example'
version '1.0-SNAPSHOT'
repositories {
    mavenCentral()
}
dependencies {
    testImplementation 'org.junit.jupiter: junit-jupiter-api: 5.7.0'
    testRuntimeOnly 'org.junit.jupiter: junit-jupiter-engine: 5.7.0'
    implementation group:  'org.seleniumhq.selenium', name:  'selenium-java', version:  '3.141.59'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< card header="After" >}}
```jsonpath
plugins {
    id 'java'
}
group 'org.example'
version '1.0-SNAPSHOT'
repositories {
    mavenCentral()
}
dependencies {
    testImplementation 'org.junit.jupiter: junit-jupiter-api: 5.7.0'
    testRuntimeOnly 'org.junit.jupiter: junit-jupiter-engine: 5.7.0'
    implementation group:  'org.seleniumhq.selenium', name:  'selenium-java', version:  '4.4.0'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< /cardpane >}}

进行更改后, 
您可以在 build.gradle 文件所在的同一目录中
执行`./gradlew clean build` .

要检查所有 Java 版本, 
您可以前往
[MVNRepository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) . 

### C#

在 C# 中获取 Selenium 4 更新的
地方是 [NuGet](https://www.nuget.org/) .  
在下面包
[`Selenium.WebDriver`](https://www.nuget.org/packages/Selenium.WebDriver/4.4.0) 
你可以获得更新到最新版本的说明.  
在 Visual Studio 内部, 
您可以通过 NuGet 包管理器执行：

```shell
PM> Install-Package Selenium.WebDriver -Version 4.4.0
```

### Python

使用 Python 的最重要变化是所需的最低版本. 
Selenium 4 将至少需要 Python 3.7 或更高版本.  
更多详细信息可以在
[Python 包索引](https://pypi.org/project/selenium/4.4.3/) .  
基于命令行做升级的话, 你可以执行: 

```shell
pip install selenium==4.4.3
```

### Ruby

Selenium 4 的更新细节
可以在RubyGems中的gem发现
[selenium-webdriver](https://rubygems.org/gems/selenium-webdriver/versions/4.4.0) .  
要安装最新版本, 
您可以执行: 

```shell
gem install selenium-webdriver
```

将以下内容添加到你的Gemfile: 

```shell
gem 'selenium-webdriver', '~> 4.4.0'
```

### JavaScript

可以在 Node 包管理器中找到 selenium-webdriver 包, 
[npmjs](https://www.npmjs.com) .  
Selenium 4 可以在
[这里](https://www.npmjs.com/package/selenium-webdriver/v/4.4.0) 找到.  
要安装, 你可以执行: 

```shell
npm install selenium-webdriver
```

或者, 更新你的 package.json 
并运行 `npm install` : 

```json
{
  "name":  "selenium-tests",
  "version":  "1.0.0",
  "dependencies":  {
    "selenium-webdriver":  "^4.4.0"
  }
}
```

## 潜在错误和弃用消息

这是一组代码示例, 
它们将有助于克服
您升级到 Selenium 4 后
可能会遇到的弃用消息.

### Java

#### 等待和超时

Timeout 中接收到的参数
已经从期望 `(long time, TimeUnit unit)` 
切换到期待 `(Duration duration)` .

{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().timeouts().setScriptTimeout(2, TimeUnit.MINUTES);
driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
```
{{< /card >}}
{{< /cardpane >}}

等待现在也期望不同的参数. 
`WebDriverWait` 现在期待一个 `Duration`
而不是以秒和毫秒为单位的 `long` 超时. 
`FluentWait` 的工具方法
`withTimeout` 和 `pollingEvery`
已经从期望 `(long time, TimeUnit unit)` 切换到
期待`(Duration duration)` .

{{< cardpane >}}
{{< card header="Before" >}}
```java
new WebDriverWait(driver, 3)
.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));

Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(30, TimeUnit.SECONDS)
  .pollingEvery(5, TimeUnit.SECONDS)
  .ignoring(NoSuchElementException.class);
```
{{< /card >}}
{{< card header="After" >}}
```java
new WebDriverWait(driver, Duration.ofSeconds(3))
  .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));

  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);
```
{{< /card >}}
{{< /cardpane >}}

#### 合并capabilities不再改变调用对象

曾经可以将一组不同的capabilities合并到另一组中, 
并且改变调用对象.  
现在, 需要分配合并操作的结果.

{{< cardpane >}}
{{< card header="Before" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options.merge(capabilities);
```
作为结果, `options` 对象被修改

{{< /card >}}
{{< card header="After" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options = options.merge(capabilities);
```
`merge` 调用的结果需要分配给一个对象.

{{< /card >}}
{{< /cardpane >}}

#### Firefox 遗留模式

在 GeckoDriver 出现之前, 
Selenium 项目有一个驱动程序实现来自动化
Firefox(版本 <48).  
但是, 不再需要此实现, 
因为在最新版本的 Firefox 中它不起作用.  
为避免升级到 Selenium 4 时出现重大问题, 
`setLegacy`选项将显示为已弃用.  
建议停止使用旧的实现
并且只依赖 GeckoDriver.  
以下代码将显示在升级之后弃用的 `setLegacy` 行.

```java
FirefoxOptions options = new FirefoxOptions();
options.setLegacy(true);
```

#### `BrowserType`
`BrowserType` 接口已经存在很长时间了, 
但是其已变为弃用
且推荐使用新的 `Browser` 接口.

{{< cardpane >}}
{{< card header="Before" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("browserVersion", "92");
capabilities.setCapability("browserName", BrowserType.FIREFOX);
```
{{< /card >}}
{{< card header="After" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("browserVersion", "92");
capabilities.setCapability("browserName", Browser.FIREFOX);
```
{{< /card >}}
{{< /cardpane >}}

### C#

#### `AddAdditionalCapability` 已弃用

推荐使用`AddAdditionalOption`替代.  
以下为一个示例: 

{{< cardpane >}}
{{< card header="Before" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalCapability("cloud: options", cloudOptions, true);
```
{{< /card >}}
{{< card header="After" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalOption("cloud: options", cloudOptions);
```
{{< /card >}}
{{< /cardpane >}}

### Python

#### `executable_path 已弃用, 请传递一个服务对象`

在Selenium 4中,  
您需要从服务对象设置驱动程序的 ``可执行路径`` , 
以防止出现弃用警告. 
(或者不要设置路径, 而是确保所需的驱动程序位于系统路径上.)

{{< cardpane >}}
{{< card header="Before" >}}
```python
from selenium import webdriver
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-automation"])
options.add_experimental_option("useAutomationExtension", False)
driver = webdriver.Chrome(executable_path=CHROMEDRIVER_PATH, options=options)
```
{{< /card >}}
{{< card header="After" >}}
```python
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-automation"])
options.add_experimental_option("useAutomationExtension", False)
service = ChromeService(executable_path=CHROMEDRIVER_PATH)
driver = webdriver.Chrome(service=service, options=options)
```
{{< /card >}}
{{< /cardpane >}}

 
## 总结

我们已经过了升级到 Selenium 4 时要考虑的主要变化. 
涵盖为升级准备测试代码时要涵盖的不同方面, 
包括关于如何避免
使用Selenium新版本时
可能出现的潜在问题的建议.  
最后, 我们还介绍了一系列您可能会遇到的升级问题, 
分享这些问题的潜在修复方案.

*本文最初发布于 https://saucelabs.com/resources/articles/how-to-upgrade-to-selenium-4*
