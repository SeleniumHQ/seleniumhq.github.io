# 组织和执行Selenium代码

> 使用IDE和Test Runner库组织Selenium的执行

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/using_selenium/

---

如果你不仅仅只是想执行一小撮的一次性脚本，你需要能组织并编排好你的代码。
本章会启发你如何真正地使用 Selenium 代码做高效的事情。

## 常见用法

大部分人使用 Selenium 执行针对 Web 应用的自动化测试，但是 Selenium 其实可以支持任何场景的浏览器自动化。

### 重复性任务

有时候你需要往网站记录日志或者下载一些东西，或者提交一个表单，
你可以在预设的时间创建一个 Selenium 脚本去执行一个服务。

### 网页爬虫

你是否期望从一个不提供 API 的网站收集数据？Selenium 可以满足你，
但是请确保你了解该网站的服务条例，
因为有些网站不允许你这样做，甚至有些网站会屏蔽 Selenium。

### 测试

使用 Selenium 做测试需要在 Selenium 执行操作后进行断言，所以一个好的断言类库是很有必要的。
至于组织测试用例结构的一些额外特性则需要[Test Runner](#test-runner)来完成。

## IDEs

不管你要用 Selenium 来做什么，没有一个好的集成开发环境，你的工作肯定不会高效。以下是一些常见的 IDE 选择：

- [Eclipse](https://www.eclipse.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [PyCharm](https://www.jetbrains.com/pycharm/)
- [RubyMine](https://www.jetbrains.com/ruby/)
- [Rider](https://www.jetbrains.com/rider/)
- [WebStorm](https://www.jetbrains.com/webstorm/)
- [VS Code](https://code.visualstudio.com/)

## Test Runner

即使不使用 Selenium 做测试，如果你有高级用例，使用一个 test runner 去更好地组织你的代码是很有意义的。
学会使用 before/after hooks 和分组执行或者并行执行将会非常有用。

### 候选

有非常多不同的 test runner 可供选择。

这个教程中所有使用到 test runner 的代码示例都可以在我们的示例目录中找到（或者正在被迁移过去），
而且这些示例在每一次发版都会被执行，以确保代码是正确的和最新的。
下面是一份包含对应链接的 test runner 清单，其中第一项是被这个仓库和本页所有用例所使用的。

**Java**

- [JUnit](https://junit.org/junit5/) - 一个广泛使用的用于基于 Java 的 Selenium 测试的测试框架。

- [TestNG](https://testng.org/) - 提供诸如并行测试执行和参数化测试等额外功能。

**Python**

- [pytest](https://pytest.org/) - 由于其简单性和强大的插件，它成为许多人的首选。

- [unittest](https://docs.python.org/3/library/unittest.html) - Python 的标准测试库

**CSharp**

- [NUnit](https://nunit.org/) - .NET的流行单元测试框架

- [MS Test](https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing?view=vs-2019) - 微软自己的单元测试框架

**Ruby**

- [RSpec](https://rspec.info/) - Ruby中运行Selenium测试最广泛使用的测试库

- [Minitest](https://github.com/seattlerb/minitest) - 一个随Ruby标准库附带的轻量级测试框架

**JavaScript**

- [Jest](https://jestjs.io/) - 主要作为React的测试框架而闻名，但也可以用于Selenium测试

- [Mocha](https://mochajs.org/) -最常用的运行Selenium测试的JavaScript库。

**Kotlin**

- [Kotest](https://kotest.io/) - 一个灵活且全面的测试框架，专为 Kotlin 设计。

- [JUnit5](https://junit.org/junit5/) - 标准的 Java 测试框架，完全兼容 Kotlin。

### 安装

在[安装 Selenium 类库](https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/install_library/index.md)一节中详细说明了需要哪些东西。
这里的代码只展示在我们的文档示例项目中用到的示例。

**Java**

**Maven**

**Gradle**

**Python**

To use it in a project, add it to the `requirements.txt` file:

**CSharp**

in the project’s `csproj` file, specify the dependency as a `PackageReference` in `ItemGroup`:

**Ruby**

Add to project’s gemfile

**JavaScript**

In your project’s `package.json`, add requirement to `dependencies`:

**Kotlin**

### 断言

**Java**

```java
String title = driver.getTitle();
assertEquals("Web form", title);
```

**Python**

```python
title = driver.title
assert title == "Web form"
```

**CSharp**

```csharp
var title = driver.Title;
Assert.AreEqual("Web form", title);
```

**Ruby**

```ruby
title = @driver.title
expect(title).to eq('Web form')
```

**JavaScript**

```javascript
let title = await driver.getTitle();
assert.equal("Web form", title);
```

**Kotlin**

```kotlin
val title = driver.title
assertEquals("Web form", title)
```

### Setting Up and Tearing Down

**Java**

<h3 id="set-up">Set Up</h3>

```java
@BeforeEach
public void setup() {
	driver = new ChromeDriver();
}
```
<h3 id="tear-down">Tear Down</h3>

```java
@AfterEach
public final void teardown() {
	if (driver != null) {
		driver.quit();
```

**Python**

<h3 id="set-up">Set Up</h3>

```python
def setup():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/web-form.html")
    return driver
```
<h3 id="tear-down">Tear Down</h3>

```python
def teardown(driver):
    driver.quit()
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

<h3 id="set-up">Set Up</h3>

```ruby
before do
  @driver = Selenium::WebDriver.for :chrome
end
```
<h3 id="tear-down">Tear Down</h3>

```ruby
config.after { @driver&.quit }
```

**JavaScript**

### Set Up

```javascript
before(async function () {
  driver = await new Builder().forBrowser('chrome').build();
});
```

### Tear Down

```javascript
after(async () => await driver.quit());
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 执行

**Java**

<h3 id="maven">Maven</h3>

```shell
mvn clean test
```
<h3 id="gradle">Gradle</h3>

```shell
gradle clean test
```

**Python**

```markdown
pytest
```

**CSharp**

```markdown

```

**Ruby**

```markdown
bundle exec rspec
```

**JavaScript**

<h3 id="mocha">Mocha</h3>

```shell
mocha runningTests.spec.js
```
<h3 id="npx">npx</h3>

```shell
npx mocha runningTests.spec.js
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 示例

在[第一个脚本](https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/first_script/index.md)一节中，我们了解了 Selenium 脚本的每一个组件。
这里是使用 test runner 重新组织那个脚本的一个示例：

**Java**

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

**Python**

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

**CSharp**

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

**Ruby**

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

**JavaScript**

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

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 下一步

使用你目前所学到的知识构建你自己的 Selenium 代码吧！

想要了解更多的功能特性，
请继续阅读我们接下来的[WebDriver 教程](https://www.selenium.dev/zh-cn/documentation/webdriver/index.md)
