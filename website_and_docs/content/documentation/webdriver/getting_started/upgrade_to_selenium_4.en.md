---
title: "Upgrade to Selenium 4"
linkTitle: "Upgrade to Selenium 4"
weight: 10
description: >
  Are you still using Selenium 3? This guide will help you upgrade to the latest release!
aliases: [
"/documentation/getting_started/how_to_upgrade_to_selenium_4/"
]
---

Upgrading to Selenium 4 should be a painless process if you are using one of the officially 
supported languages (Ruby, JavaScript, C#, Python, and Java). There might be some cases where 
a few issues can happen, and this guide will help you to sort them out. We will go through 
the steps to upgrade your project dependencies and understand the major deprecations and 
changes the version upgrade brings.

These are the steps we will follow to upgrade to Selenium 4:
* Preparing our test code
* Upgrading dependencies
* Potential errors and deprecation messages

Note: while Selenium 3.x versions were being developed, support for the W3C WebDriver standard 
was implemented. Both this new protocol and the legacy JSON Wire Protocol were supported. Around 
version 3.11, Selenium code became compliant with the level W3C 1 specification. The W3C compliant 
code in the latest version of Selenium 3 will work as expected in Selenium 4.


## Preparing our test code

Selenium 4 removes support for the legacy protocol and uses the W3C WebDriver standard by 
default under the hood. For most things, this implementation will not affect end users. 
The major exceptions are `Capabilities` and the `Actions` class.

### Capabilities
If the test capabilities are not structured to be W3C compliant, may cause a session to not 
be started. Here is the list of W3C WebDriver standard capabilities:
* `browserName`
* `browserVersion` (replaces `version`)
* `platformName` (replaces `platform`)
* `acceptInsecureCerts`
* `pageLoadStrategy`
* `proxy`
* `timeouts`
* `unhandledPromptBehavior`

An up-to-date list of standard capabilities can be found at 
[W3C WebDriver](https://www.w3.org/TR/webdriver1/#capabilities).

Any capability that is not contained in the list above, needs to include a vendor prefix. 
This applies to browser specific capabilities as well as cloud vendor specific capabilities. 
For example, if your cloud vendor uses `build` and `name` capabilities for your tests, you need
to wrap them in a `cloud:options` block (check with your cloud vendor for the appropriate prefix).

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
caps = Selenium::WebDriver::Remote::Capabilities.firefox
caps[:platform] = 'Windows 10'
caps[:version] = '92'
caps[:build] = my_test_build
caps[:name] = my_test_name
driver = Selenium::WebDriver.for :remote, url: cloud_url, desired_capabilities: caps
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
browserOptions.setCapability("cloud:options", cloudOptions);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), browserOptions);
{{< /tab >}}
{{< tab header="JavaScript" >}}
capabilities = {
  browserName: 'firefox',
  browserVersion: '92',
  platformName: 'Windows 10',
  'cloud:options': {
     build: myTestBuild,
     name: myTestName,
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
browserOptions.AddAdditionalOption("cloud:options", cloudOptions);
var driver = new RemoteWebDriver(new Uri(CloudURL), browserOptions);
{{< /tab >}}
{{< tab header="Ruby" >}}
options = Selenium::WebDriver::Options.firefox
options.browser_version = 'latest'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options[:build] = my_test_build
cloud_options[:name] = my_test_name
options.add_option('cloud:options', cloud_options)
driver = Selenium::WebDriver.for :remote, url: cloud_url, capabilities: options
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options as FirefoxOptions
options = FirefoxOptions()
options.browser_version = '92'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options['build'] = my_test_build
cloud_options['name'] = my_test_name
options.set_capability('cloud:options', cloud_options)
driver = webdriver.Remote(cloud_url, options=options)
{{< /tab >}}
{{< /tabpane >}}

### Find element(s) utility methods in Java
The utility methods to find elements in the Java bindings (`FindsBy` interfaces) have been removed 
as they were meant for internal use only. The following code samples explain this better.

Finding a single element with `findElement*`

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


Finding a multiple elements with `findElements*`

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


## Upgrading dependencies

Check the subsections below to install Selenium 4 and have your project dependencies upgraded.

### Java

The process of upgrading Selenium depends on which build tool is being used. We will cover the 
most common ones for Java, which are [Maven](https://maven.apache.org/) and 
[Gradle](https://gradle.org/). The minimum Java version required is still 8.

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

After making the change, you could execute `mvn clean compile` on the same directory where the 
`pom.xml` file is.

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
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '3.141.59'
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
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '4.4.0'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< /cardpane >}}

After making the change, you could execute `./gradlew clean build`
on the same directory where the `build.gradle` file is.

To check all the Java releases, you can head to [MVNRepository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java).

### C#

The place to get updates for Selenium 4 in C# is [NuGet](https://www.nuget.org/). Under the 
[`Selenium.WebDriver`](https://www.nuget.org/packages/Selenium.WebDriver/4.4.0) package you 
can get the instructions to update to the latest version. Inside of Visual Studio, through the 
NuGet Package Manager you can execute:

```shell
PM> Install-Package Selenium.WebDriver -Version 4.4.0
```

### Python

The most important change to use Python is the minimum required version. Selenium 4 will 
require a minimum Python 3.7 or higher. More details can be found at the 
[Python Package Index](https://pypi.org/project/selenium/4.4.3/). To upgrade from the 
command line, you can execute:

```shell
pip install selenium==4.4.3
```

### Ruby

The update details for Selenium 4 can be seen at the 
[selenium-webdriver](https://rubygems.org/gems/selenium-webdriver/versions/4.4.0) 
gem in RubyGems. To install the latest version, you can execute:

```shell
gem install selenium-webdriver
```

To add it to your Gemfile:

```shell
gem 'selenium-webdriver', '~> 4.4.0'
```

### JavaScript

The selenium-webdriver package can be found at the Node package manager, 
[npmjs](https://www.npmjs.com). Selenium 4 can be found 
[here](https://www.npmjs.com/package/selenium-webdriver/v/4.4.0). To install it, you 
could either execute:

```shell
npm install selenium-webdriver
```

Or, update your package.json and run` npm install`:

```json
{
  "name": "selenium-tests",
  "version": "1.0.0",
  "dependencies": {
    "selenium-webdriver": "^4.4.0"
  }
}
```

## Potential errors and deprecation messages

Here is a set of code examples that will help to overcome the deprecation messages you might 
encounter after upgrading to Selenium 4.

### Java

#### Waits and Timeout

The parameters received in Timeout have switched from expecting `(long time, TimeUnit unit)` to 
expect `(Duration duration)`.

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

Waits are also expecting different parameters now. `WebDriverWait` is now expecting a `Duration` 
instead of a `long` for timeout in seconds and milliseconds. The `withTimeout` and `pollingEvery` 
utility methods from `FluentWait` have switched from expecting `(long time, TimeUnit unit)` to 
expect `(Duration duration)`.

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

#### Merging capabilities is no longer changing the calling object

It was possible to merge a different set of capabilities into another set, and it was 
mutating the calling object. Now, the result of the merge operation needs to be assigned.

{{< cardpane >}}
{{< card header="Before" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options.merge(capabilities);
```
As a result, the `options` object was getting modified.
{{< /card >}}
{{< card header="After" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options = options.merge(capabilities);
```
The result of the `merge` call needs to be assigned to an object.
{{< /card >}}
{{< /cardpane >}}

#### Firefox Legacy

Before GeckoDriver was around, the Selenium project had a driver implementation to automate 
Firefox (version <48). However, this implementation is not needed anymore as it does not work 
in recent versions of Firefox. To avoid major issues when upgrading to Selenium 4, the `setLegacy` 
option will be shown as deprecated. The recommendation is to stop using the old implementation 
and rely only on GeckoDriver. The following code will show the `setLegacy` line deprecated after 
upgrading.

```java
FirefoxOptions options = new FirefoxOptions();
options.setLegacy(true);
```

#### `BrowserType`
The `BrowserType` interface has been around for a long time, however it is getting 
deprecated in favour of the new `Browser` interface.

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

#### `AddAdditionalCapability` is deprecated

Instead of it, `AddAdditionalOption` is recommended. Here is an example showing this:

{{< cardpane >}}
{{< card header="Before" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalCapability("cloud:options", cloudOptions, true);
```
{{< /card >}}
{{< card header="After" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalOption("cloud:options", cloudOptions);
```
{{< /card >}}
{{< /cardpane >}}

### Python

#### `executable_path has been deprecated, please pass in a Service object`

In Selenium 4, you'll need to set the driver's ``executable_path`` from a Service object to prevent deprecation warnings. (Or don't set the path and instead make sure that the driver you need is on the System PATH.)

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

## Summary

We went through the major changes to be taken into consideration when upgrading to Selenium 4. 
Covering the different aspects to cover when test code is prepared for the upgrade, including 
suggestions on how to prevent potential issues that can show up when using the new version of 
Selenium. To finalize, we also covered a set of possible issues that you can bump into after 
upgrading, and we shared potential fixes for those issues.

*This was originally posted at  https://saucelabs.com/resources/articles/how-to-upgrade-to-selenium-4*
