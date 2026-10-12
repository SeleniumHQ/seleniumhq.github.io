---
title: "Outdated Selenium patterns and their current replacements"
linkTitle: "Outdated Patterns"
weight: 2
description: >-
  Selenium 2 and 3 idioms that coding agents and old tutorials still produce, with the
  current form of each.
---

Coding agents and old tutorials reproduce Selenium 2 and Selenium 3 idioms,
because that is what most of the published material describes.
Each section below shows one outdated pattern,
when it stopped being the right way to write Selenium,
why, and the current form.
The outdated form is always marked with an `Outdated — do not use` comment.

This page is the reference behind the rules in
[Using AI coding agents with Selenium]({{< ref "/documentation/ai_agents/" >}}).
Use it to recognise bad output in a review.

## DesiredCapabilities

**Outdated since Selenium 4**, which uses the W3C WebDriver standard by default and removes
support for the legacy protocol.
Java Grid also stopped accepting session requests that use the `desiredCapabilities` keyword in 4.17.

Free-form capability maps predate the W3C standard.
Non-standard capabilities now have to be vendor-prefixed,
and an unprefixed one can stop the session from starting.
The browser Options classes produce a valid payload by construction.
See the [upgrade guide]({{< ref "/documentation/webdriver/troubleshooting/upgrade_to_selenium_4.md" >}})
and [Browser options]({{< ref "/documentation/webdriver/drivers/options.md" >}}).

{{< tabpane text=true >}}
{{% tab header="Java" %}}
```java
// Outdated — do not use
DesiredCapabilities caps = DesiredCapabilities.chrome();
caps.setCapability("pageLoadStrategy", "eager");
WebDriver driver = new ChromeDriver(caps);
```
{{% /tab %}}
{{% tab header="Python" %}}
```python
# Outdated — do not use
caps = DesiredCapabilities.CHROME.copy()
caps["pageLoadStrategy"] = "eager"
driver = webdriver.Chrome(desired_capabilities=caps)
```
{{% /tab %}}
{{< /tabpane >}}

Current form:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/drivers/OptionsTest.java#L22-L24" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_options.py#L7-L9" >}}
{{< /tab >}}
{{< /tabpane >}}

## Manual driver downloads and driver managers

**Superseded in Selenium 4.6**, when Selenium Manager began shipping with every Selenium release.
Since 4.11 Selenium Manager can also download the browser.

Setting a path to `chromedriver`, putting a driver on the `PATH` by hand,
or adding a third-party driver manager adds a second, slower source of truth
for which driver version to use.
There is nothing to write:
start the driver and Selenium Manager finds or downloads the matching one.
See [Selenium Manager]({{< ref "/documentation/selenium_manager.md" >}}).

{{< tabpane text=true >}}
{{% tab header="Java" %}}
```java
// Outdated — do not use
WebDriverManager.chromedriver().setup();
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
WebDriver driver = new ChromeDriver();
```
{{% /tab %}}
{{% tab header="Python" %}}
```python
# Outdated — do not use
from webdriver_manager.chrome import ChromeDriverManager

driver = webdriver.Chrome(ChromeDriverManager().install())
```
{{% /tab %}}
{{< /tabpane >}}

Current form:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/selenium_manager/SeleniumManagerUsageDemo.java#L21" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/selenium_manager/usage.py#L11" >}}
{{< /tab >}}
{{< /tabpane >}}

## sleep()

**Never good practice.**
Fixed sleeps were bad advice before Selenium 4 and are bad advice now.

A fixed sleep is either too short, and the test fails,
or too long, and the suite crawls.
Wait for the condition the next line depends on instead.
See [Waiting strategies]({{< ref "/documentation/webdriver/waits.md" >}}).

{{< tabpane text=true >}}
{{% tab header="Java" %}}
```java
// Outdated — do not use
driver.findElement(By.id("adder")).click();
Thread.sleep(5000);
WebElement added = driver.findElement(By.id("box0"));
```
{{% /tab %}}
{{% tab header="Python" %}}
```python
# Outdated — do not use
driver.find_element(By.ID, "adder").click()
time.sleep(5)
added = driver.find_element(By.ID, "box0")
```
{{% /tab %}}
{{< /tabpane >}}

Current form:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L67-L68" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L41-L42" >}}
{{< /tab >}}
{{< /tabpane >}}

## Absolute XPath copied from DevTools

**Never good practice.**
An absolute path describes where an element sits in today's markup,
so it breaks on the next front-end change.

Prefer an `id` or `name`, then a CSS selector on a stable attribute.
When an element has no usable locator but is easy to describe by position,
use a relative locator.
See [Locator strategies]({{< ref "/documentation/webdriver/elements/locators.md" >}}).

{{< tabpane text=true >}}
{{% tab header="Java" %}}
```java
// Outdated — do not use
driver.findElement(By.xpath("/html/body/div[2]/div[1]/form/div[3]/input"));
```
{{% /tab %}}
{{% tab header="Python" %}}
```python
# Outdated — do not use
driver.find_element(By.XPATH, "/html/body/div[2]/div[1]/form/div[3]/input")
```
{{% /tab %}}
{{< /tabpane >}}

Current form, with a stable locator:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/LocatorTest.java#L48" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_locators.py#L28" >}}
{{< /tab >}}
{{< /tabpane >}}

Current form, with a relative locator:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/RelativeLocatorTest.java#L32-L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_relative_locators.py#L8-L9" >}}
{{< /tab >}}
{{< /tabpane >}}

## find_element_by_* methods

**Superseded in Selenium 4.**
The Python bindings announced the removal of `find_element_by_*` and `find_elements_by_*`
after Selenium 4.2.
The Java `findElementBy*` helpers were removed in Selenium 4.

Every one of them is a special case of `find_element` with a `By` strategy.
See the
[Python locators blog post]({{< ref "/blog/2022/python-locators-se4.md" >}})
and the [upgrade guide]({{< ref "/documentation/webdriver/troubleshooting/upgrade_to_selenium_4.md" >}}).

{{< tabpane text=true >}}
{{% tab header="Java" %}}
```java
// Outdated — do not use
driver.findElementById("lname");
```
{{% /tab %}}
{{% tab header="Python" %}}
```python
# Outdated — do not use
driver.find_element_by_id("lname")
```
{{% /tab %}}
{{< /tabpane >}}

Current form:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/LocatorTest.java#L48" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_locators.py#L28" >}}
{{< /tab >}}
{{< /tabpane >}}

## executable_path

**Deprecated in Selenium 4** in favour of a `Service` object.

In Python, the `executable_path` argument to the driver constructor is the outdated form.
A path belongs on a `Service`.
Most projects do not need one at all, because Selenium Manager finds the driver;
see [driver management](#manual-driver-downloads-and-driver-managers) above.
See the [upgrade guide]({{< ref "/documentation/webdriver/troubleshooting/upgrade_to_selenium_4.md" >}})
and [Driver service]({{< ref "/documentation/webdriver/drivers/service.md" >}}).

```python
# Outdated — do not use
driver = webdriver.Chrome(executable_path="/path/to/chromedriver", options=options)
```

Current form, when a specific driver binary is really needed:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_service.py#L15-L17" >}}
{{< /tab >}}
{{< /tabpane >}}

## CDP-only calls

**Temporary by design.**
The [CDP pages]({{< ref "/documentation/webdriver/bidi/cdp/" >}}) describe Selenium's Chrome DevTools Protocol
support as temporary until WebDriver BiDi is implemented.
Firefox CDP support was deprecated in 4.27 and 4.28 and fully removed in 4.29.

The Chrome DevTools Protocol is Chromium-only and has no stable API between browser versions.
[WebDriver BiDi]({{< ref "/documentation/webdriver/bidi/w3c/" >}}) is the W3C standard that replaces it,
and it works across browsers.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
```java
// Outdated — do not use
DevTools devTools = ((HasDevTools) driver).getDevTools();
devTools.createSession();
devTools.send(Log.enable());
devTools.addListener(Log.entryAdded(), entry -> System.out.println(entry.getText()));
```
{{% /tab %}}
{{% tab header="Python" %}}
```python
# Outdated — do not use
driver.execute_cdp_cmd("Log.enable", {})
```
{{% /tab %}}
{{< /tabpane >}}

Current form, listening to console logs with BiDi:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L30-L39" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L6-L15" >}}
{{< /tab >}}
{{< /tabpane >}}
