# Seleniumコードの整理と実行

> IDEとテストランナーライブラリを使用したSelenium実行のスケーリング

URL: https://www.selenium.dev/ja/documentation/webdriver/getting_started/using_selenium/

---

一握り以上の 1 回限りのスクリプトを実行する場合は、コードを整理して操作できる必要があります。このページでは、Seleniumコードを使用して実際に生産的なことを行う方法についてのアイデアを提供します。

## 一般的な用途

ほとんどの人はSeleniumを使用してWebアプリケーションの自動テストを実行します。
しかし、Seleniumはブラウザ自動化のあらゆるユースケースをサポートします。

### 反復タスク

おそらく、Webサイトにログインして何かをダウンロードするか、フォームを送信する必要があります。
Selenium スクリプトを作成して、あらかじめ設定された時間にサービスと共に実行できます。

### Webスクレイピング

API がないサイトからデータを収集したいとお考えですか？
Selenium では可能ですが、Web サイトの利用規約をよく理解しておいてください。
許可していないサイトもあり、Selenium をブロックするサイトさえあります。

### テスティング

テストでSeleniumを実行するには、Seleniumが実行した操作に対してアサーションを行う必要があります。
そのため、優れたアサーションライブラリが必要です。テストに構造を与える追加機能には、
[テストランナー](#test-runner)を使用する必要があります。

## IDEs

Seleniumコードの使用方法に関係なく、優れた統合開発環境がなければ、Seleniumコードの作成や実行はあまり効果的ではありません。一般的なオプションを次に示します...

- [Eclipse](https://www.eclipse.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [PyCharm](https://www.jetbrains.com/pycharm/)
- [RubyMine](https://www.jetbrains.com/ruby/)
- [Rider](https://www.jetbrains.com/rider/)
- [WebStorm](https://www.jetbrains.com/webstorm/)
- [VS Code](https://code.visualstudio.com/)

## Test Runner

テストにSeleniumを使用していない場合でも、高度なユースケースでは、テストランナーを使うとコードを
より適切に整理できる場合があります。before/afterフックを使用し、処理をグループ単位または並列で
実行できることは、非常に便利です。

### テストランナーの選び方

さまざまなテストランナーが利用可能です。

このドキュメントのすべてのコード例は、テストランナーを使用するサンプルディレクトリに格納されているか、
格納先を移行中です。すべてのコードが正しく更新されていることを確認するため、リリースのたびに実行されます。
以下にテストランナーをリンクとともに示します。各リストの最初の項目は、このリポジトリと
このページのすべての例で使用されているものです。

**Java**

- [JUnit](https://junit.org/junit5/) - JavaベースのSeleniumテストで広く使用されているテストフレームワーク。

- [TestNG](https://testng.org/) - 並列テスト実行やパラメーター化されたテストなどの追加機能を提供します。

**Python**

- [pytest](https://pytest.org/) - そのシンプルさと強力なプラグインのおかげで、多くの人に好まれる選択肢です。

- [unittest](https://docs.python.org/3/library/unittest.html) - Python の標準ライブラリテストフレームワーク。

**CSharp**

- [NUnit](https://nunit.org/) - .NET の一般的な単体テスト フレームワーク。

- [MS Test](https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing?view=vs-2019) - Microsoft 独自の単体テスト フレームワーク。

**Ruby**

- [RSpec](https://rspec.info/) - RubyでSeleniumテストを実行するために最も広く使用されているテストライブラリ。

- [Minitest](https://github.com/seattlerb/minitest) - Ruby標準ライブラリに付属する軽量なテストフレームワークです。

**JavaScript**

- [Jest](https://jestjs.io/) - 主にReactのテストフレームワークとして知られていますが、Seleniumのテストにも使用できます。

- [Mocha](https://mochajs.org/) - Seleniumテストを実行するための最も一般的なJSライブラリ。

**Kotlin**

- [Kotest](https://kotest.io/) - Kotlin専用に設計された、柔軟で包括的なテストフレームワークです。

- [JUnit5](https://junit.org/junit5/) - 標準的なJavaテストフレームワークであり、Kotlinと完全に互換性があります。

### インストール

これは[Seleniumライブラリのインストール](https://www.selenium.dev/ja/documentation/webdriver/getting_started/install_library/index.md)で必要な手順と非常によく似ています。
このコードは、ドキュメント用のサンプルプロジェクトで使用しているものの例を示すだけです。

**Java**

**Maven**

**Gradle**

**Python**

プロジェクトで使用するには、requirements.txt ファイルに追加します:

**CSharp**

プロジェクトの ‘csproj’ ファイルで、依存関係を ‘ItemGroup’ の ‘PackageReference’ として指定します:

**Ruby**

プロジェクトの gemfile に追加

**JavaScript**

プロジェクトの ‘package.json’ で、要件を ‘dependencies’ に追加します。:

**Kotlin**

### アサーション

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

### セットアップとティアダウン

**Java**

<h3 id="セットアップ">セットアップ</h3>

```java
@BeforeEach
public void setup() {
	driver = new ChromeDriver();
}
```
<h3 id="ティアダウン">ティアダウン</h3>

```java
@AfterEach
public final void teardown() {
	if (driver != null) {
		driver.quit();
```

**Python**

<h3 id="セットアップ">セットアップ</h3>

```python
def setup():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/web-form.html")
    return driver
```
<h3 id="ティアダウン">ティアダウン</h3>

```python
def teardown(driver):
    driver.quit()
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

<h3 id="セットアップ">セットアップ</h3>

```ruby
before do
  @driver = Selenium::WebDriver.for :chrome
end
```
<h3 id="ティアダウン">ティアダウン</h3>

```ruby
config.after { @driver&.quit }
```

**JavaScript**

### セットアップ

```javascript
before(async function () {
  driver = await new Builder().forBrowser('chrome').build();
});
```

### ティアダウン

```javascript
after(async () => await driver.quit());
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 実行

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

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 例

[最初のスクリプト](https://www.selenium.dev/ja/documentation/webdriver/getting_started/first_script/index.md)のトピックでは、Seleniumスクリプトの各コンポーネントを見ました。こちらが、テストランナーを使用したそのコードの例です。

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

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 次のステップ

学んだことを活かして、Seleniumコードを構築します!

必要な機能が他にも見つかったら、残りの機能をお読みください
[WebDriver ドキュメント](https://www.selenium.dev/ja/documentation/webdriver/index.md).
