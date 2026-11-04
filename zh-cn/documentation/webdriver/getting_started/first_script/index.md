# 编写第一个Selenium脚本

> 逐步构建一个Selenium脚本的说明

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/first_script/

---

当你完成 [Selenium安装](https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/install_library/index.md)  后, 便可以开始书写Selenium脚本了.

## 八个基本组成部分

Selenium所做的一切, 
就是发送给浏览器命令,
用以执行某些操作或为信息发送请求.
您将使用Selenium执行的大部分操作,
都是以下基本命令的组合

点击 "View full example on GitHub" 的链接以查看上下文中的代码.

### 1. 使用驱动实例开启会话

关于如何启动会话，请浏览我们的文档 [驱动会话](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/index.md)

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

### 2. 在浏览器上执行操作

在本例中, 我们
[导航](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/navigation/index.md) 
到一个网页. 

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

### 3. 请求 [浏览器信息](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/index.md)

您可以请求一系列关于[浏览器的信息](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/index.md) , 
包括窗口句柄、浏览器尺寸/位置、cookie、警报等.

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

### 4. 建立等待策略

将代码与浏览器的当前状态同步
是Selenium面临的最大挑战之一, 
做好它是一个高级主题. 

基本上, 您希望在尝试定位元素之前, 
确保该元素位于页面上, 
并且在尝试与该元素交互之前, 
该元素处于可交互状态.

隐式等待很少是最好的解决方案, 
但在这里最容易演示, 
所以我们将使用它作为占位符. 

阅读更多关于[等待策略](https://www.selenium.dev/zh-cn/documentation/webdriver/waits/index.md)
的信息. 

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

### 5. 发送命令 [查找元素](https://www.selenium.dev/zh-cn/documentation/webdriver/elements/index.md)
大多数Selenium会话中的主要命令都与元素相关, 
如果不先[找到元素](https://www.selenium.dev/zh-cn/documentation/webdriver/elements/index.md), 
就无法与之交互.

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

### 6. 操作元素
对于一个元素, 
只有少数几个[操作](https://www.selenium.dev/zh-cn/documentation/webdriver/elements/interactions/index.md)可以执行, 
但您将经常使用它们. 

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

### 7. 获取元素信息
元素存储了很多[被请求的信息](https://www.selenium.dev/zh-cn/documentation/webdriver/elements/information/index.md).

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

### 8. 结束会话 

这将结束驱动程序进程, 
默认情况下, 该进程也会关闭浏览器. 
无法向此驱动程序实例发送更多命令. 

详见 [Quitting Sessions](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/index.md#quitting-sessions).

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

## 运行 Selenium 文件

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

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 接下来的步骤

大多数 Selenium 用户执行许多会话，
需要组织它们以最大限度地减少重复并维持代码更易于维护.
请继续阅读，了解如何将此代码放入您用例的上下文中
[使用 Selenium](https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/using_selenium/index.md).
