# IFrame と Frame の操作

URL: https://www.selenium.dev/ja/documentation/webdriver/interactions/frames/

---

Frameは、同じドメイン上の複数のドキュメントからサイトレイアウトを構築する非推奨の手段となりました。
HTML5以前のWebアプリを使用している場合を除き、frameを使用することはほとんどありません。
iFrameは、まったく異なるドメインからのドキュメントの挿入を許可し、今でも一般的に使用されています。

FrameまたはiFrameを使用する必要がある場合、Webdriverを使用して同じ方法で作業できます。 
iFrame内のボタンがある場合を考えてみましょう。ブラウザー開発ツールを使用して要素を検査すると、次のように表示される場合があります。

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

iFrameがなければ、次のようなボタンを使用してボタンをクリックします。

**Java**

```java
//This won't work
driver.findElement(By.tagName("button")).click();

```

**Python**

```python
    # This Wont work
driver.find_element(By.TAG_NAME, 'button').click()

```

**CSharp**

```csharp
//This won't work
driver.FindElement(By.TagName("button")).Click();

```

**Ruby**

```ruby
    # This won't work
driver.find_element(:tag_name,'button').click

```

**JavaScript**

```javascript
// This won't work
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//This won't work
driver.findElement(By.tagName("button")).click()

```

ただし、iFrameの外側にボタンがない場合は、代わりにno such elementエラーが発生する可能性があります。
これは、Seleniumがトップレベルのドキュメントの要素のみを認識するために発生します。
ボタンを操作するには、ウィンドウを切り替える方法と同様に、最初にFrameに切り替える必要があります。
WebDriverは、Frameに切り替える3つの方法を提供します。

## WebElementを使う

WebElementを使用した切り替えは、最も柔軟なオプションです。好みのセレクタを使用してFrameを見つけ、それに切り替えることができます。

**Java**

```java
//switch To IFrame using Web Element
WebElement iframe = driver.findElement(By.id("iframe1"));
//Switch to the frame
driver.switchTo().frame(iframe);
assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
//Now we can type text into email field
WebElement emailE = driver.findElement(By.id("email"));
emailE.sendKeys("admin@selenium.dev");
emailE.clear();
```

**Python**

```python
# --- Switch to iframe using WebElement ---
iframe = driver.find_element(By.ID, "iframe1")
driver.switch_to.frame(iframe)
assert "We Leave From Here" in driver.page_source

email_element = driver.find_element(By.ID, "email")
email_element.send_keys("admin@selenium.dev")
email_element.clear()
driver.switch_to.default_content()
```

**CSharp**

```csharp
//switch To IFrame using Web Element
IWebElement iframe = driver.FindElement(By.Id("iframe1"));
//Switch to the frame
driver.SwitchTo().Frame(iframe);
Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));
//Now we can type text into email field
IWebElement emailE = driver.FindElement(By.Id("email"));
emailE.SendKeys("admin@selenium.dev");
emailE.Clear();
```

**Ruby**

```ruby
iframe = driver.find_element(:id, 'iframe1')
driver.switch_to.frame(iframe)
expect(driver.page_source).to include('We Leave From Here')

email_element = driver.find_element(:id, 'email')
email_element.send_keys('admin@selenium.dev')
email_element.clear
driver.switch_to.default_content

# --- Switch to iframe using name or ID ---
iframe1 = driver.find_element(:name, 'iframe1-name')
```

**JavaScript**

```javascript
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()

```

## nameまたはIDを使う

FrameまたはiFrameにidまたはname属性がある場合、代わりにこれを使うことができます。
名前またはIDがページ上で一意でない場合、最初に見つかったものに切り替えます。

**Java**

```java
//switch To IFrame using name or id
WebElement iframe1=driver.findElement(By.name("iframe1-name"));
//Switch to the frame
driver.switchTo().frame(iframe1);
assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
WebElement email = driver.findElement(By.id("email"));
//Now we can type text into email field
email.sendKeys("admin@selenium.dev");
email.clear();
```

**Python**

```python
# --- Switch to iframe using name or ID ---
iframe1=driver.find_element(By.NAME, "iframe1-name")  # (This line doesn't switch, just locates)
driver.switch_to.frame(iframe1)
assert "We Leave From Here" in driver.page_source

email = driver.find_element(By.ID, "email")
email.send_keys("admin@selenium.dev")
email.clear()
driver.switch_to.default_content()
```

**CSharp**

```csharp
//switch To IFrame using name or id
IWebElement iframe1=driver.FindElement(By.Name("iframe1-name"));
//Switch to the frame
driver.SwitchTo().Frame(iframe1);
Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));
IWebElement email = driver.FindElement(By.Id("email"));
//Now we can type text into email field
email.SendKeys("admin@selenium.dev");
email.Clear();
```

**Ruby**

```ruby
iframe1 = driver.find_element(:name, 'iframe1-name')
driver.switch_to.frame(iframe1)
expect(driver.page_source).to include('We Leave From Here')

email = driver.find_element(:id, 'email')
email.send_keys('admin@selenium.dev')
email.clear
driver.switch_to.default_content
```

**JavaScript**

```javascript
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()

```

## インデックスを使う

JavaScriptの _window.frames_ を使用して照会できるように、Frameのインデックスを使用することもできます。

{

**Java**

```java
//switch To IFrame using index
driver.switchTo().frame(0);
```

**Python**

```python
driver.switch_to.frame(0)
assert "We Leave From Here" in driver.page_source
```

**CSharp**

```csharp
//switch To IFrame using index
driver.SwitchTo().Frame(0);
```

**Ruby**

```ruby
driver.switch_to.frame(0)
expect(driver.page_source).to include('We Leave From Here')
```

**JavaScript**

```javascript
// Switches to the second frame
await driver.switchTo().frame(1);
```

**Kotlin**

```kotlin
// Switches to the second frame
driver.switchTo().frame(1)
```

## Frameを終了する

iFrameまたはFrameセットを終了するには、次のようにデフォルトのコンテンツに切り替えます。

**Java**

```java
//leave frame
driver.switchTo().defaultContent();
```

**Python**

```python
    # switch back to default content
driver.switch_to.default_content()

```

**CSharp**

```csharp
//leave frame
driver.SwitchTo().DefaultContent();
```

**Ruby**

```ruby
driver.switch_to.default_content
expect(driver.page_source).to include('This page has iframes')
```

**JavaScript**

```javascript
// Return to the top level
await driver.switchTo().defaultContent();

```

**Kotlin**

```kotlin
// Return to the top level
driver.switchTo().defaultContent()

```
