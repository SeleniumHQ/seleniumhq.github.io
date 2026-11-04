# 最初のSeleniumスクリプトを書く

> Seleniumスクリプトを作成するための段階的な説明

URL: https://www.selenium.dev/ja/documentation/webdriver/getting_started/first_script/

---

[Seleniumをインストール](https://www.selenium.dev/ja/documentation/webdriver/getting_started/install_library/index.md)し、
すると、Seleniumコードを書く準備が整います。

## 8つの基本コンポーネント

Seleniumが行うことはすべて、ブラウザコマンドを送信して、何かを実行したり、情報の要求を送信したりすることです。 
Seleniumで行うことのほとんどは、次の基本的なコマンドの組み合わせです。

[GitHub で完全な例を表示] へのリンクをクリックして、コンテキスト内のコードを表示します。

### 1. ドライバーインスタンスでセッションを開始します

セッションの開始の詳細については、次のドキュメントをお読みください [driver sessions](https://www.selenium.dev/ja/documentation/webdriver/drivers/index.md)

**Java**

```java
WebDriver driver = new ChromeDriver();
```

**Python**

```python
driver = webdriver.Chrome()
```

**CSharp**

```csharp
IWebDriver driver = new ChromeDriver();
```

**Ruby**

```ruby
driver = Selenium::WebDriver.for :chrome
```

**JavaScript**

```javascript
driver = await new Builder().forBrowser(Browser.CHROME).build();
```

**Kotlin**

```kotlin
driver = ChromeDriver()
```

### 2. Take action on browser
こちらの例では、[ナビゲート](https://www.selenium.dev/ja/documentation/webdriver/interactions/navigation/index.md) してウェブページに移動しています。

**Java**

```java
driver.get("https://www.selenium.dev/selenium/web/web-form.html");
```

**Python**

```python
driver.get("https://www.selenium.dev/selenium/web/web-form.html")
```

**CSharp**

```csharp
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/web-form.html");
```

**Ruby**

```ruby
driver.get('https://www.selenium.dev/selenium/web/web-form.html')
```

**JavaScript**

```javascript
await driver.get('https://www.selenium.dev/selenium/web/web-form.html');
```

**Kotlin**

```kotlin
driver.get("https://www.selenium.dev/selenium/web/web-form.html")
```

### 3. [ブラウザに関する情報](https://www.selenium.dev/ja/documentation/webdriver/interactions/index.md)をリクエストします

ブラウザに関する [情報](https://www.selenium.dev/ja/documentation/webdriver/interactions/index.md) として、ウィンドウハンドル、ブラウザのサイズ/位置、クッキー、アラートなど、さまざまな種類のデータをリクエストできます。

**Java**

```java
driver.getTitle();
```

**Python**

```python
title = driver.title
```

**CSharp**

```csharp
var title = driver.Title;
```

**Ruby**

```ruby
driver.title
```

**JavaScript**

```javascript
let title = await driver.getTitle();
```

**Kotlin**

```kotlin
val title = driver.title
```

### 4. 待機戦略の確立

コードをブラウザの現在の状態と同期させることは、最大の課題の 1 つです
Seleniumを使用して、それをうまく行うことは高度なトピックです。

基本的には、要素を見つける前に、その要素がページ上にあることを確認する必要があります
また、要素は、操作を試みる前に対話可能な状態にあります。

暗黙的な待機が最善の解決策になることはめったにありませんが、ここで示すのが最も簡単なので、
プレースホルダーとして使用します。

[待機戦略](https://www.selenium.dev/ja/documentation/webdriver/waits/index.md)についてさらに読む。

**Java**

```java
driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
```

**Python**

```python
driver.implicitly_wait(0.5)
```

**CSharp**

```csharp
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);
```

**Ruby**

```ruby
driver.manage.timeouts.implicit_wait = 500
```

**JavaScript**

```javascript
await driver.manage().setTimeouts({implicit: 500});
```

**Kotlin**

```kotlin
driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
```

### 5. [要素を検索する](https://www.selenium.dev/ja/documentation/webdriver/elements/index.md)ためのコマンドを送信します
ほとんどのSeleniumセッションにおけるコマンドの大部分は要素に関連しており、[要素を見つける](https://www.selenium.dev/ja/documentation/webdriver/elements/index.md) ことなしにはそれと対話することができません。

**Java**

```java
WebElement textBox = driver.findElement(By.name("my-text"));
WebElement submitButton = driver.findElement(By.cssSelector("button"));
```

**Python**

```python
text_box = driver.find_element(by=By.NAME, value="my-text")
submit_button = driver.find_element(by=By.CSS_SELECTOR, value="button")
```

**CSharp**

```csharp
var textBox = driver.FindElement(By.Name("my-text"));
var submitButton = driver.FindElement(By.TagName("button"));
```

**Ruby**

```ruby
text_box = driver.find_element(name: 'my-text')
submit_button = driver.find_element(tag_name: 'button')
```

**JavaScript**

```javascript
let textBox = await driver.findElement(By.name('my-text'));
let submitButton = await driver.findElement(By.css('button'));
```

**Kotlin**

```kotlin
var textBox = driver.findElement(By.name("my-text"))
val submitButton = driver.findElement(By.cssSelector("button"))
```

### 6. 要素に対してアクションを実行する

要素に対して行う [アクション](https://www.selenium.dev/ja/documentation/webdriver/elements/interactions/index.md)はわずかしかありませんが、それらは頻繁に使用されます。

**Java**

```java
textBox.sendKeys("Selenium");
submitButton.click();
```

**Python**

```python
text_box.send_keys("Selenium")
submit_button.click()
```

**CSharp**

```csharp
textBox.SendKeys("Selenium");
submitButton.Click();
```

**Ruby**

```ruby
text_box.send_keys('Selenium')
submit_button.click
```

**JavaScript**

```javascript
await textBox.sendKeys('Selenium');
await submitButton.click();
```

**Kotlin**

```kotlin
textBox.sendKeys("Selenium")
submitButton.click()
```

### 7. 要素に関する情報をリクエストします

要素には [リクエスト可能な情報](https://www.selenium.dev/ja/documentation/webdriver/elements/information/index.md) が多く保存されています。

**Java**

```java
WebElement message = driver.findElement(By.id("message"));
message.getText();
```

**Python**

```python
message = driver.find_element(by=By.ID, value="message")
text = message.text
```

**CSharp**

```csharp
var message = driver.FindElement(By.Id("message"));
var value = message.Text;
```

**Ruby**

```ruby
message = driver.find_element(id: 'message')
message.text
```

**JavaScript**

```javascript
let message = await driver.findElement(By.id('message'));
let value = await message.getText();
```

**Kotlin**

```kotlin
val message = driver.findElement(By.id("message"))
val value = message.getText()
```

### 8. セッションを終了します 

これにより、ドライバー プロセスが終了し、既定ではブラウザーも閉じます。このドライバー インスタンスにこれ以上コマンドを送信することはできません。

[セッションの終了](https://www.selenium.dev/ja/documentation/webdriver/drivers/index.md#quitting-sessions) を参照.

**Java**

```java
driver.quit();
```

**Python**

```python
driver.quit()
```

**CSharp**

```csharp
driver.Quit();
```

**Ruby**

```ruby
driver.quit
```

**JavaScript**

```javascript
await driver.quit();
```

**Kotlin**

```kotlin
driver.quit()
```

## Seleniumファイルの実行

**Java**

```markdown
mvn exec:java -D"exec.mainClass"="dev.selenium.getting_started.FirstScript" -D"exec.classpathScope"=test
```

**Python**

```markdown
pytest path/to/test_script.py
```

**CSharp**

```markdown

```

**Ruby**

```markdown
ruby example_script.rb
```

**JavaScript**

```markdown
node example_script.spec.js
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 次のステップ

ほとんどのSeleniumユーザーは多くのセッションを実行し、重複を最小限に抑え、コードをより保守しやすくするために整理する必要があります。このコードをユースケースのコンテキストに配置する方法については、以下をお読みください [Seleniumの使用](https://www.selenium.dev/ja/documentation/webdriver/getting_started/using_selenium/index.md)。
