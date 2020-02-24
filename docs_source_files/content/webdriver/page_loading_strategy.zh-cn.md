---
title: "Page loading strategy"
weight: 8
---

Defines the current session's page loading strategy. 
By default, when Selenium WebDriver loads a page, 
it follows the _normal_ pageLoadStrategy. 
It is always recommended to stop downloading additional 
resources (like images, css, js) when the page loading takes lot of time.

`document.readyState` property of a document describes the loading state of current document.
By default, webdriver will hold off on responding to a `driver.get()` (or) `driver.navigate().to()` 
call until the document ready state is `complete`

### Note: 
In SPA (Single Page Applications) as once they reach the document ready state of complete, 
unless you do a `driver.get()` (or) `driver.navigate().to()` call, 
the ready state will always be complete after initially being set.
if the website itself is redirecting you from one view to another, 
the ready state will be complete that entire time.

WebDriver _pageLoadStrategy_ supports the following values:

## normal

This will make Selenium WebDriver to wait for the entire page is loaded. 
When set to **normal**, Selenium WebDriver waits until the 
[load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event) event fire is returned.

By default **normal** is set to browser if none is provided.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="c#" >}}
 // Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='normal'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("normal");
(async function example() {
    let driver = await new Builder().
                withCapabilities(caps).
                forBrowser('chrome').
                build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
  {{< / code-panel >}}
{{< / code-tab >}}

## eager

This will make Selenium WebDriver to wait until the 
initial HTML document has been completely loaded and parsed, 
and discards loading of stylesheets, images and subframes.

When set to **eager**, Selenium WebDriver waits until 
[DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event) event fire is returned.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="c#" >}}
 // Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='eager'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("eager");
(async function example() {
    let driver = await new Builder().
                withCapabilities(caps).
                forBrowser('chrome').
                build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
  {{< / code-panel >}}
{{< / code-tab >}}

## none

When set to **none** Selenium WebDriver only waits until the initial page is downloaded.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="c#" >}}
 // Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='none'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("none");
(async function example() {
    let driver = await new Builder().
                withCapabilities(caps).
                forBrowser('chrome').
                build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
  {{< / code-panel >}}
{{< / code-tab >}}

### Note: 
For most of the forms that involves testing in a browser, one should always use 
[_explicit waits_](https://www.selenium.dev/documentation/zh-cn/webdriver/waits/#explicit-wait) 
to wait for the DOM to settle. 
