---
title: "Open and close a browser with Selenium"
linkTitle: "Open Browser"
weight: 6
description: >
  Code examples for starting and stopping a session with each browser.
---

Once you have a [Selenium library installed]({{< ref "install_library.md" >}}),
and your [desired browser driver]({{< ref "install_library.md" >}}),
you can start and stop a session with a browser.

Typically, browsers are started with specific options that describe
which capabilities the browser must support, and how the browser should
behave during the session. Some capabilities are 
[shared by all browsers]({{< ref "/documentation/webdriver/capabilities/shared.md" >}}), and
some will be specific to the browser being used.
This page will show examples of starting a browser with the default capabilities.

After learning how to start a session, check out the next session on how to 
[write your first Selenium script]({{< ref "first_script.md" >}})

## Chrome

By default, Selenium 4 is compatible with Chrome v75 and greater. Note that the version of 
the Chrome browser and the version of chromedriver must match the major version. 

In addition to the [shared capabilities]({{< ref "/documentation/webdriver/capabilities/shared.md" >}}),
there are specific [Chrome capabilities]({{< ref "/documentation/webdriver/capabilities/chromium.md" >}})
that can be used.

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

Microsoft Edge is implemented with Chromium, with the earliest supported version of v79. Similar to Chrome,
the major version number of edgedriver must match the major version of the Edge browser.

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

Selenium 4 requires Firefox 78 or greater. It is recommended to always use the latest version of geckodriver.

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

The IE Driver is the only driver maintained by the Selenium Project directly.
While binaries for both the 32-bit and 64-bit
versions of Internet Explorer are available, there are some
[limitations](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
with the 64-bit driver. As such it is recommended to use the 32-bit driver.

### Legacy 
The Selenium project aims to support the same releases that
[Microsoft considers current](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer).
Older releases may work, but will not be supported. Note that Internet Explorer 11 will end support for certain 
operating systems, including Windows 10 on June 15, 2022.

It should be noted that as Internet Explorer
preferences are saved against the logged-in user's account, some additional setup is required.

Additional information about using Internet Explorer can be found
[on the Selenium wiki](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver)

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

### Compatibility Mode
Microsoft Edge can be used in IE compatibility mode using the IE Driver.

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

Since the opera driver does not support w3c syntax, but is based on Chromium, it is recommended
to drive Opera browser with the chromedriver. Like all Chromium implementations, 
make sure that the browser version matches the driver version.

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
Unlike Chromium and Firefox drivers, the safaridriver is installed with the Operating System.
To enable automation on Safari, run the following command from the terminal:

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

### Mobile
Those looking to automate Safari on iOS should look to the [Appium project](//appium.io/).

## Next Step
[Create your first Selenium script]({{< ref "first_script.md" >}})
