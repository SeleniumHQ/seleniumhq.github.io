# 与IFrames和frames一起工作

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/frames/

---

框架是一种现在已被弃用的方法，用于从同一域中的多个文档构建站点布局。除非你使用的是 HTML5
之前的 webapp，否则你不太可能与他们合作。内嵌框架允许插入来自完全不同领域的文档，并且仍然经常使用。

如果您需要使用框架或 iframe, WebDriver 允许您以相同的方式使用它们。考虑 iframe 中的一个按钮。
如果我们使用浏览器开发工具检查元素，我们可能会看到以下内容:

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe" src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

如果不是 iframe，我们可能会使用如下方式点击按钮:

**Java**

```java
// 这不会工作
driver.findElement(By.tagName("button")).click();
```

**Python**

```python
    # 这不会工作
driver.find_element(By.TAG_NAME, 'button').click()
```

**CSharp**

```csharp
// 这不会工作
driver.FindElement(By.TagName("button")).Click();
```

**Ruby**

```ruby
    # 这不会工作
driver.find_element(:tag_name,'button').click
```

**JavaScript**

```javascript
// 这不会工作
await driver.findElement(By.css('button')).click();
```

**Kotlin**

```kotlin
// 这不会工作
driver.findElement(By.tagName("button")).click()
```

但是，如果 iframe 之外没有按钮，那么您可能会得到一个 _no such element 无此元素_ 的错误。
这是因为 Selenium 只知道顶层文档中的元素。为了与按钮进行交互，我们需要首先切换到框架，
这与切换窗口的方式类似。WebDriver 提供了三种切换到帧的方法。

## 使用 WebElement

使用 WebElement 进行切换是最灵活的选择。您可以使用首选的选择器找到框架并切换到它。

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
```

**JavaScript**

```javascript
// 存储网页元素
const iframe = driver.findElement(By.css('#modal> iframe'));

// 切换到 frame
await driver.switchTo().frame(iframe);

// 现在可以点击按钮
await driver.findElement(By.css('button')).click();
```

**Kotlin**

```kotlin
// 存储网页元素
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

// 切换到 frame
driver.switchTo().frame(iframe)

// 现在可以点击按钮
driver.findElement(By.tagName("button")).click()
```

## 使用 name 或 id

如果您的 frame 或 iframe 具有 id 或 name 属性，则可以使用该属性。如果名称或 id 在页面上不是唯一的，
那么将切换到找到的第一个。

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
// 使用 ID
await driver.switchTo().frame('buttonframe');

// 或者使用 name 代替
await driver.switchTo().frame('myframe');

// 现在可以点击按钮
await driver.findElement(By.css('button')).click();
```

**Kotlin**

```kotlin
// 使用 ID
driver.switchTo().frame("buttonframe")

// 或者使用 name 代替
driver.switchTo().frame("myframe")

// 现在可以点击按钮
driver.findElement(By.tagName("button")).click()
```

## 使用索引

还可以使用frame的索引，
例如可以使用JavaScript中的
_window.frames_ 进行查询.

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
// 切换到第 2 个框架
await driver.switchTo().frame(1);
```

**Kotlin**

```kotlin
// 切换到第 2 个框架
driver.switchTo().frame(1)
```

## 离开框架

离开 iframe 或 frameset，切换回默认内容，如下所示:

**Java**

```java
//leave frame
driver.switchTo().defaultContent();
```

**Python**

```python
driver.switch_to.default_content()
assert "This page has iframes" in driver.page_source
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
// 回到顶层
await driver.switchTo().defaultContent();
```

**Kotlin**

```kotlin
// 回到顶层
driver.switchTo().defaultContent()
```
