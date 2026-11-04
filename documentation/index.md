# The Selenium Browser Automation Project

URL: https://www.selenium.dev/documentation/
Site index: https://www.selenium.dev/llms.txt

---

Selenium is an umbrella project for a range of tools and libraries 
that enable and support the automation of web browsers. 

It provides extensions to emulate user interaction with browsers,
a distribution server for scaling browser allocation,
and the infrastructure for implementations of the 
[W3C WebDriver specification](//www.w3.org/TR/webdriver/)
that lets you write interchangeable code for all major web browsers.

This project is made possible by volunteer contributors
who have put in thousands of hours of their own time,
and made the source code 
[freely available](https://www.selenium.dev/documentation/about/copyright/index.md#license)
for anyone to use, enjoy, and improve.

Selenium brings together browser vendors, engineers, and enthusiasts
to further an open discussion around automation of the web platform.
The project organises [an annual conference](//seleniumconf.com/)
to teach and nurture the community.

At the core of Selenium is [WebDriver](https://www.selenium.dev/documentation/webdriver/index.md), 
an interface to write instruction sets that can be run interchangeably in many 
browsers. Once you've installed everything, only a few lines of code get you inside
a browser. You can find a more comprehensive example in [Writing your first Selenium script](https://www.selenium.dev/documentation/webdriver/getting_started/index.md)

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

See the [Overview](https://www.selenium.dev/documentation/overview/index.md) to check the different project 
components and decide if Selenium is the right tool for you.

You should continue on to [Getting Started](https://www.selenium.dev/documentation/webdriver/getting_started/index.md)
to understand how you can install Selenium and successfully use it as a test 
automation tool, and scaling simple tests like this to run in large, distributed 
environments on multiple browsers, on several different operating systems.

---

Section pages:

- [Selenium Overview](https://www.selenium.dev/documentation/overview/index.md): Is Selenium for you? See an overview of the different project components.
- [WebDriver](https://www.selenium.dev/documentation/webdriver/index.md): WebDriver drives a browser natively; learn more about it.
- [Guides](https://www.selenium.dev/documentation/guides/index.md): Task-based guides: start from the problem you have and follow the links into the reference pages that solve it.
- [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/index.md): Selenium Manager is a command-line tool implemented in Rust that provides automated driver and browser management for Selenium. Selenium bindings use this tool by default, so you do not need to download it or add anything to your code or do anything else to use it.
- [Grid](https://www.selenium.dev/documentation/grid/index.md): Want to run tests in parallel across multiple machines? Then, Grid is for you.
- [Selenium IDE](https://www.selenium.dev/documentation/ide/index.md): The Selenium IDE is a standalone application that records and plays back a user's actions.
- [Using AI coding agents with Selenium](https://www.selenium.dev/documentation/ai_agents/index.md): How to get coding agents and large language models to write Selenium code that is current, correct, and not flaky.
- [Test Practices](https://www.selenium.dev/documentation/test_practices/index.md): Some guidelines and recommendations on testing from the Selenium project.
- [Selenium release notes](https://www.selenium.dev/documentation/release_notes/index.md): What changed in each recent Selenium release, newest first, with links to the announcement and to the full changelog of every binding.
- [Warnings](https://www.selenium.dev/documentation/warnings/index.md): Explanations for the diagnostics, annotations, and IDE warnings Selenium surfaces.
- [Legacy](https://www.selenium.dev/documentation/legacy/index.md): Documentation related to the legacy components of Selenium. Meant to be kept purely for historical reasons and not as a incentive to use deprecated components.
- [About this documentation](https://www.selenium.dev/documentation/about/index.md)
