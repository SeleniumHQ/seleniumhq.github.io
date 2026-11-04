# Getting started

> Install Selenium, write your first script, and run it.

URL: https://www.selenium.dev/documentation/webdriver/getting_started/
Site index: https://www.selenium.dev/llms.txt

---

This page takes you from an empty project to a running Selenium script,
and then shows how to run the same code with a test runner.

## You will learn

* How to install the Selenium library for your programming language
* How to write and run a first script that opens a browser, interacts with a page, and closes it
* How to run the same code with a test runner

## Install

> **No driver downloads**
>
> From Selenium 4.6 onwards you no longer need to download or manage browser drivers.
> [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/index.md) finds the driver that
> matches your browser, and downloads the browser itself if none is installed.

Add the Selenium library to your project.
Check the [downloads page](/downloads/) for the latest version.

**Java**

Add the dependency to your build file.
The minimum supported Java version is listed in the
[Selenium source](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L32).

**Maven** (`pom.xml`)

```xml
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>${selenium.version}</version>
```

**Gradle** (`build.gradle`)

```gradle
testImplementation "org.seleniumhq.selenium:selenium-java:${seleniumVersion}"
```

**Python**

```shell
pip install selenium
```

The minimum supported Python version for each release is listed on
[PyPI](https://pypi.org/project/selenium/).

**CSharp**

```shell
dotnet add package Selenium.WebDriver
```

The supported frameworks for each release are listed on
[NuGet](https://www.nuget.org/packages/Selenium.WebDriver).

**Ruby**

```shell
gem install selenium-webdriver
```

Or add `gem 'selenium-webdriver'` to your `Gemfile`.
The minimum supported Ruby version for each release is listed on
[RubyGems](https://rubygems.org/gems/selenium-webdriver/).

**JavaScript**

```shell
npm install selenium-webdriver
```

The minimum supported Node version for each release is in the “Node Support Policy”
section on [npm](https://www.npmjs.com/package/selenium-webdriver).

**Kotlin**

Kotlin uses the Java bindings.
Add the same Maven or Gradle dependency shown on the Java tab.

## Your first script

Every Selenium call either tells the browser to do something or asks it for information.
This script uses the eight commands you will use in almost every session:

1. Start the [driver session](https://www.selenium.dev/documentation/webdriver/drivers/index.md)
2. [Navigate](https://www.selenium.dev/documentation/webdriver/interactions/navigation/index.md) to a page
3. Request [information about the browser](https://www.selenium.dev/documentation/webdriver/interactions/index.md)
4. Establish a [waiting strategy](https://www.selenium.dev/documentation/webdriver/waits/index.md)
5. [Find elements](https://www.selenium.dev/documentation/webdriver/elements/index.md)
6. [Act on an element](https://www.selenium.dev/documentation/webdriver/elements/interactions/index.md)
7. Request [element information](https://www.selenium.dev/documentation/webdriver/elements/information/index.md)
8. End the session with
   [quit](https://www.selenium.dev/documentation/webdriver/drivers/index.md#quitting-sessions)

**Java**

```java
package dev.selenium.getting_started;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstScript {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.getTitle();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        message.getText();

        driver.quit();
    }
}
```

**Python**

```python
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

driver.get("https://www.selenium.dev/selenium/web/web-form.html")

title = driver.title

driver.implicitly_wait(0.5)

text_box = driver.find_element(by=By.NAME, value="my-text")
submit_button = driver.find_element(by=By.CSS_SELECTOR, value="button")

text_box.send_keys("Selenium")
submit_button.click()

message = driver.find_element(by=By.ID, value="message")
text = message.text

driver.quit()
```

**CSharp**

```csharp
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.GettingStarted;

public static class FirstScript
{
    public static void Main()
    {
        IWebDriver driver = new ChromeDriver();

        driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/web-form.html");

        var title = driver.Title;

        driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

        var textBox = driver.FindElement(By.Name("my-text"));
        var submitButton = driver.FindElement(By.TagName("button"));

        textBox.SendKeys("Selenium");
        submitButton.Click();

        var message = driver.FindElement(By.Id("message"));
        var value = message.Text;

        driver.Quit();
    }
}
```

**Ruby**

```ruby
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get('https://www.selenium.dev/selenium/web/web-form.html')

driver.title

driver.manage.timeouts.implicit_wait = 500

text_box = driver.find_element(name: 'my-text')
submit_button = driver.find_element(tag_name: 'button')

text_box.send_keys('Selenium')
submit_button.click

message = driver.find_element(id: 'message')
message.text

driver.quit
```

**JavaScript**

```javascript
const {By, Builder, Browser} = require('selenium-webdriver');
const assert = require("assert");

(async function firstTest() {
  let driver;

  try {
    driver = await new Builder().forBrowser(Browser.CHROME).build();
    await driver.get('https://www.selenium.dev/selenium/web/web-form.html');

    let title = await driver.getTitle();
    assert.equal("Web form", title);

    await driver.manage().setTimeouts({implicit: 500});

    let textBox = await driver.findElement(By.name('my-text'));
    let submitButton = await driver.findElement(By.css('button'));

    await textBox.sendKeys('Selenium');
    await submitButton.click();

    let message = await driver.findElement(By.id('message'));
    let value = await message.getText();
    assert.equal("Received!", value);
  } catch (e) {
    console.log(e)
  } finally {
    await driver.quit();
  }
}())
```

**Kotlin**

```kotlin
package dev.selenium.getting_started

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstScriptTest {
    private lateinit var driver: WebDriver

    @Test
    fun eightComponents() {
        driver = ChromeDriver()

        driver.get("https://www.selenium.dev/selenium/web/web-form.html")

        val title = driver.title
        assertEquals("Web form", title)

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

        var textBox = driver.findElement(By.name("my-text"))
        val submitButton = driver.findElement(By.cssSelector("button"))

        textBox.sendKeys("Selenium")
        submitButton.click()

        val message = driver.findElement(By.id("message"))
        val value = message.getText()
        assertEquals("Received!", value)

        driver.quit()
    }

}
```

Run the file with your language's usual tooling.
The browser opens, fills in the form, and closes.

## Run it with a test runner

Most people use Selenium to test web applications.
A test runner gives you before and after hooks, assertions, and reporting,
and lets you run tests in groups or in parallel.
Here is the same script structured as a test, with one runner for each language:

**Java**

[JUnit](https://junit.org/junit5/)

```java
package dev.selenium.getting_started;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsingSeleniumTest {

	WebDriver driver;

	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}

	@Test
	public void eightComponents() {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");

		String title = driver.getTitle();
		assertEquals("Web form", title);

		WebElement textBox = driver.findElement(By.name("my-text"));
		WebElement submitButton = driver.findElement(By.cssSelector("button"));

		textBox.sendKeys("Selenium");
		submitButton.click();

		WebElement message = driver.findElement(By.id("message"));
		String value = message.getText();
		assertEquals("Received!", value);

	}

	@AfterEach
	public final void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
```

```shell
mvn test
```

**Python**

[pytest](https://pytest.org/)

```python
from selenium import webdriver
from selenium.webdriver.common.by import By

def test_eight_components():
    driver = setup()

    title = driver.title
    assert title == "Web form"

    driver.implicitly_wait(0.5)

    text_box = driver.find_element(by=By.NAME, value="my-text")
    submit_button = driver.find_element(by=By.CSS_SELECTOR, value="button")

    text_box.send_keys("Selenium")
    submit_button.click()

    message = driver.find_element(by=By.ID, value="message")
    value = message.text
    assert value == "Received!"

    teardown(driver)

def setup():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/web-form.html")
    return driver

def teardown(driver):
    driver.quit()
```

```shell
pytest
```

**CSharp**

[MSTest](https://learn.microsoft.com/en-us/dotnet/core/testing/unit-testing-with-mstest)

```csharp
using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.GettingStarted
{
    [TestClass]
    public class UsingSeleniumTest
    {

        [TestMethod]
        public void EightComponents()
        {
            IWebDriver driver = new ChromeDriver();

            driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/web-form.html");

            var title = driver.Title;
            Assert.AreEqual("Web form", title);

            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            var textBox = driver.FindElement(By.Name("my-text"));
            var submitButton = driver.FindElement(By.TagName("button"));

            textBox.SendKeys("Selenium");
            submitButton.Click();

            var message = driver.FindElement(By.Id("message"));
            var value = message.Text;
            Assert.AreEqual("Received!", value);

            driver.Quit();
        }
    }
}
```

```shell
dotnet test
```

**Ruby**

[RSpec](https://rspec.info/)

```ruby
# frozen_string_literal: true

require 'spec_helper'
require 'selenium-webdriver'

RSpec.describe 'Using Selenium' do
  before do
    @driver = Selenium::WebDriver.for :chrome
  end

  it 'uses eight components' do
    @driver.get('https://www.selenium.dev/selenium/web/web-form.html')

    title = @driver.title
    expect(title).to eq('Web form')

    @driver.manage.timeouts.implicit_wait = 500

    text_box = @driver.find_element(name: 'my-text')
    submit_button = @driver.find_element(tag_name: 'button')

    text_box.send_keys('Selenium')
    submit_button.click

    message = @driver.find_element(id: 'message')
    value = message.text
    expect(value).to eq('Received!')
  end
end
```

```shell
bundle exec rspec
```

**JavaScript**

[Mocha](https://mochajs.org/)

```javascript
const {By, Builder} = require('selenium-webdriver');
const assert = require("assert");

describe('First script', function () {
  let driver;

  before(async function () {
    driver = await new Builder().forBrowser('chrome').build();
  });

  it('First Selenium script with mocha', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/web-form.html');

    let title = await driver.getTitle();
    assert.equal("Web form", title);

    await driver.manage().setTimeouts({implicit: 500});

    let textBox = await driver.findElement(By.name('my-text'));
    let submitButton = await driver.findElement(By.css('button'));

    await textBox.sendKeys('Selenium');
    await submitButton.click();

    let message = await driver.findElement(By.id('message'));
    let value = await message.getText();
    assert.equal("Received!", value);
  });

  after(async () => await driver.quit());
});
```

```shell
npx mocha runningTests.spec.js
```

**Kotlin**

[JUnit](https://junit.org/junit5/)

The Kotlin first script above is already a JUnit test.

```shell
mvn test
```

## Next steps

* [Waits](https://www.selenium.dev/documentation/webdriver/waits/index.md):
  make sure elements are ready before you use them
* [Locators](https://www.selenium.dev/documentation/webdriver/elements/locators/index.md):
  choose the best way to find elements
* [Grid](https://www.selenium.dev/documentation/grid/getting_started/index.md):
  run your tests on many browsers and machines
* [How Selenium works](https://www.selenium.dev/documentation/overview/details/index.md):
  WebDriver, drivers, and the rest of the project
