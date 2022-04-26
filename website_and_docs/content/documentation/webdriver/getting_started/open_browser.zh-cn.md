---
title: "使用Selenium打开和关闭浏览器"
linkTitle: "打开浏览器"
weight: 6
description: >
  使用每种浏览器启动和停止会话的代码示例.
---

当你已经完成 [安装Selenium类库]({{< ref "install_library.md" >}}),
以及 [浏览器所需的驱动]({{< ref "install_library.md" >}}),
您可以使用浏览器启动和停止会话.

通常, 浏览器都是根据特定的选项启动, 
这些选项描述浏览器必须支持哪些功能, 
以及浏览器在会话期间应如何运行.
有些功能由[所有浏览器共享]({{< ref "/documentation/webdriver/capabilities/shared.md" >}}),
有些功能特定于所使用的浏览器.
此页面将显示使用默认功能启动浏览器的示例.

在学习如何开启一个会话后, 
请查看下一段内容关于如何
[编写第一个Selenium脚本的会话]({{< ref "first_script.md" >}}) 

## Chrome

默认情况下, Selenium 4与Chrome v75及更高版本兼容.
请注意, Chrome浏览器和chromedriver的版本必须与主版本匹配. 

除了[共享功能]({{< ref "/documentation/webdriver/capabilities/shared.md" >}})外,
还可以使用特定的[Chrome功能]({{< ref "/documentation/webdriver/capabilities/chromium.md" >}}).

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L27-L30" >}}
  ChromeOptions options = new ChromeOptions();
  driver = new ChromeDriver(options);
  
  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L9-L12" >}}
  options = ChromeOptions()
  driver = webdriver.Chrome(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L18-L21" >}}
  var options = new ChromeOptions();
  var driver = new ChromeDriver(options);

  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L6-L9" >}}
  options = Selenium::WebDriver::Options.chrome
  driver = Selenium::WebDriver.for :chrome, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/getting_started/openChromeTest.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = ChromeOptions()
  val driver = ChromeDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Edge

微软Edge是用Chromium实现的, 
是最早支持的v79版本.
与Chrome类似, 
edgedriver的主要版本号必须与Edge浏览器的主要版本匹配.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L35-L38">}}
  EdgeOptions options = new EdgeOptions();
  driver = new EdgeDriver(options);

  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L16-L19" >}}
  options = EdgeOptions()
  driver = webdriver.Edge(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L27-L30" >}}
  var options = new EdgeOptions();
  var driver = new EdgeDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L13-L16" >}}
  options = Selenium::WebDriver::Options.edge
  driver = Selenium::WebDriver.for :edge, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/getting_started/openEdgeTest.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = EdgeOptions()
  val driver = EdgeDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Firefox

Selenium 4需要Firefox 78或更高版本.
建议始终使用geckodriver的最新版本.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L43-L46">}}
  FirefoxOptions options = new FirefoxOptions();
  driver = new FirefoxDriver(options);

  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L23-L26" >}}
  options = FirefoxOptions()
  driver = webdriver.Firefox(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L36-L39" >}}
  var options = new FirefoxOptions();
  var driver = new FirefoxDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L20-L23" >}}
  options = Selenium::WebDriver::Options.firefox
  driver = Selenium::WebDriver.for :firefox, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/getting_started/openFirefoxTest.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = FirefoxOptions()
  val driver = FirefoxDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Internet Explorer

IE驱动程序是Selenium项目直接维护的唯一驱动程序.
虽然32位和64位版本的Internet Explorer都有可执行文件, 
但64位驱动程序存在一些[限制](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html).
因此, 建议使用32位驱动程序.

### Legacy 
Selenium项目旨在支持[微软认为最新的版本](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer).
旧版本可能会工作, 但不受支持.
请注意, 
Internet Explorer 11将于2022年6月15日终止对包括Windows 10在内的某些操作系统的支持.

需要注意的是, 由于Internet Explorer首选项是针对登录用户的帐户保存的, 
因此需要进行一些额外的设置.

有关使用Internet Explorer的更多信息, 
请访问[Selenium wiki](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver)

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L52-L55">}}
  InternetExplorerOptions options = new InternetExplorerOptions();
  driver = new InternetExplorerDriver(options);

  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L31-L34" >}}
  options = IEOptions()
  driver = webdriver.Ie(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L46-L49" >}}
  var options = new InternetExplorerOptions();
  var driver = new InternetExplorerDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L29-L32" >}}
  options = Selenium::WebDriver::Options.ie
  driver = Selenium::WebDriver.for :ie, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const { Builder } = require("selenium-webdriver");
  const ie = require('selenium-webdriver/ie');

  let options = new ie.Options();
  let driver = await new Builder()
    .forBrowser('internetExplorer')
    .setIeOptions(options)
    .build();

  await driver.quit();
 {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = InternetExplorerOptions()
  val driver = InternetExplorerDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

### 兼容模式
可以使用IE驱动程序在IE兼容模式下使用微软Edge.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L61-L67">}}
  InternetExplorerOptions options = new InternetExplorerOptions();
  options.attachToEdgeChrome();
  options.withEdgeExecutablePath("/path/to/edge/browser");
  
  driver = new InternetExplorerDriver(options);
  
  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L39-L44" >}}
  options = IEOptions()
  options.attach_to_edge_chrome = True
  options.edge_executable_path = "/path/to/edge/browser"
  driver = webdriver.Ie(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L56-L63" >}}
  var options = new InternetExplorerOptions
  {
    AttachToEdgeChrome = true,
    EdgeExecutablePath = "/path/to/edge/browser"
  };
  var driver = new InternetExplorerDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L38-L43" >}}
  options = Selenium::WebDriver::Options.ie
  options.attach_to_edge_chrome = true
  options.edge_executable_path = "/path/to/edge/browser"
  driver = Selenium::WebDriver.for :ie, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  let options = new ie.Options();
  options.setEdgeChromium(true);
  options.setEdgePath("/path/to/edge/browser);

  let driver = await new Builder()
    .forBrowser('internet explorer')
    .setIEOptions(options)
    .build();

  await driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = InternetExplorerOptions()
  options.attachToEdgeChrome()
  options.withEdgeExecutablePath("/path/to/edge/browser")
  val driver = InternetExplorerDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Opera

由于opera驱动程序不支持w3c语法, 
但基于Chrome, 因此建议使用chromedriver驱动opera浏览器.
与所有Chromium实现一样, 确保浏览器版本与驱动程序版本匹配.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L73-L78">}}
  ChromeOptions options = new ChromeOptions();
  options.setBinary("/path/to/opera/browser");
  
  driver = new ChromeDriver(options);
  
  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L49-L53" >}}
  options = ChromeOptions()
  options.binary_location = "path/to/opera/browser"
  driver = webdriver.Chrome(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L70-L76" >}}
  var options = new ChromeOptions
  {
    BinaryLocation = "/path/to/opera/browser"
  };
  var driver = new ChromeDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L49-L53" >}}
  options = Selenium::WebDriver::Options.chrome
  options.binary = '/path/to/opera/browser'
  driver = Selenium::WebDriver.for :chrome, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const { Builder } = require("selenium-webdriver");
  const chrome = require('selenium-webdriver/chrome');

  let options = new chrome.Options();
  options.setChromeBinaryPath("/path/to/opera/browser");

  let driver = await new Builder()
    .forBrowser('chrome')
    .setChromeOptions(options)
    .build();

  await driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = ChromeOptions()
  options.setBinary("/path/to/opera/browser")
  val driver = ChromeDriver(options)
  
  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

## Safari

### Desktop
与Chromium和Firefox驱动程序不同, 
safaridriver是随操作系统一起安装的.
要在Safari上启用自动化, 请从命令行运行以下命令:

```shell
safaridriver --enable
```

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L84-L88">}}
  SafariOptions options = new SafariOptions();
  driver = new SafariDriver(options);
  
  driver.quit();
{{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L58-L60" >}}
  driver = webdriver.Safari()

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L83-L86" >}}
  var options = new SafariOptions();
  var driver = new SafariDriver(options);

  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L59-L62" >}}
  options = Selenium::WebDriver::Options.safari
  driver = Selenium::WebDriver.for :safari, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const { Builder } = require("selenium-webdriver");
  const safari = require('selenium-webdriver/safari');

  let options = new safari.Options();
  let driver = await new Builder()
    .forBrowser('safari')
    .setSafariOptions(options)
    .build();

  await driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = SafariOptions()
  val driver = SafariDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

### 移动端
那些希望在iOS上实现Safari自动化的人
应该看看[Appium项目](//appium.io/).

## 下一步
[创建你的第一个Selenium脚本]({{< ref "first_script.md" >}})
