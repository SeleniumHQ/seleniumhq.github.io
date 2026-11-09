# Selenium

> Selenium is a browser automation tool that gives you full control over Chrome, Firefox, Edge and Safari. Write your scripts in Java, Python, C#, Ruby, JavaScript or Kotlin using the official W3C WebDriver standard.

URL: https://www.selenium.dev/
Site index: https://www.selenium.dev/llms.txt

---

## Install Selenium and run your first script

Each example below opens Chrome, loads a page and closes the browser again.
Selenium Manager, part of Selenium 4.6 and later, downloads the browser driver,
so there is nothing else to install. Every example is run in CI against current
Selenium releases.

### Java

Add `org.seleniumhq.selenium:selenium-java` to your Maven or Gradle build.

Then run [HelloSelenium.java](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/java/src/test/java/dev/selenium/hello/HelloSelenium.java):

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

### Python

Install the package:

```shell
pip install selenium
```

Then run [hello_selenium.py](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/python/tests/hello/hello_selenium.py):

```python
from selenium import webdriver


driver = webdriver.Chrome()

driver.get("http://selenium.dev")

driver.quit()
```

### C#

Install the package:

```shell
dotnet add package Selenium.WebDriver
```

Then run [HelloSelenium.cs](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/dotnet/HelloSelenium.cs):

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

### Ruby

Install the package:

```shell
gem install selenium-webdriver
```

Then run [hello_selenium.rb](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/ruby/spec/hello/hello_selenium.rb):

```ruby
# frozen_string_literal: true

require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://selenium.dev'

driver.quit
```

### JavaScript

Install the package:

```shell
npm install selenium-webdriver
```

Then run [helloSelenium.js](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/javascript/test/hello/helloSelenium.js):

```javascript
const {Builder, Browser} = require('selenium-webdriver');

(async function helloSelenium() {
  let driver = await new Builder().forBrowser(Browser.CHROME).build();

  await driver.get('https://selenium.dev');

  await driver.quit();
})();
```

### Kotlin

Kotlin uses the Java bindings: add `org.seleniumhq.selenium:selenium-java` to your Gradle or Maven build.

Then run [HelloSelenium.kt](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/kotlin/src/test/kotlin/dev/selenium/hello/HelloSelenium.kt):

```kotlin
package dev.selenium.hello

import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://selenium.dev")

    driver.quit()
}
```

## Show the examples in one language

On selenium.dev, add `?lang=` to a page's URL to show its code examples in one
language, using one of
`java`, `python`, `csharp`, `ruby`, `javascript`, `kotlin`.
For example, https://www.selenium.dev/documentation/webdriver/getting_started/?lang=python opens the Getting Started
guide with the Python examples selected, and the choice carries over to other pages.
The Markdown version of each documentation page (`index.md`) always includes
every language.

## Next steps

- [Getting started](https://www.selenium.dev/documentation/webdriver/getting_started/index.md): Install Selenium, write your first script, and run it.
- [WebDriver](https://www.selenium.dev/documentation/webdriver/index.md): WebDriver drives a browser natively; learn more about it.
- [Grid](https://www.selenium.dev/documentation/grid/index.md): Want to run tests in parallel across multiple machines? Then, Grid is for you.
- [Using AI coding agents with Selenium](https://www.selenium.dev/documentation/ai_agents/index.md): How to get coding agents and large language models to write Selenium code that is current, correct, and not flaky.
