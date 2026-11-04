# 浏览器交互

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/

---

## 获取浏览器信息

### 获取标题

从浏览器中读取当前页面的标题:

**Java**

```java
String title = driver.getTitle();
```

**Python**

```python
title = driver.title
```

**CSharp**

```csharp
String title = driver.Title;
```

**Ruby**

```ruby
it 'gets the current title' do
```

**JavaScript**

```javascript
let title = await driver.getTitle();
```

**Kotlin**

```kotlin
driver.title
```

### 获取当前 URL

您可以从浏览器的地址栏读取当前的 URL，使用:

**Java**

```java
String url = driver.getCurrentUrl();
```

**Python**

```python
url = driver.current_url
```

**CSharp**

```csharp
String url = driver.Url;
```

**Ruby**

```ruby
it 'gets the current url' do
```

**JavaScript**

```javascript
let currentUrl = await driver.getCurrentUrl();
```

**Kotlin**

```kotlin
driver.currentUrl
```

---

Section pages:

- [浏览器导航](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/navigation/index.md)
- [JavaScript 警告框,提示框和确认框](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/alerts/index.md)
- [同cookies一起工作](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/cookies/index.md)
- [与IFrames和frames一起工作](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/frames/index.md)
- [打印页面](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/print_page/index.md)
- [同窗口和标签一起工作](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/windows/index.md)
- [虚拟身份验证器](https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/virtual_authenticator/index.md): 一种Web身份验证器模型的表示形式.
