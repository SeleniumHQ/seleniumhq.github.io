# 浏览器导航

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/navigation/

---

## 打开网站

启动浏览器后你要做的第一件事就是打开你的网站。这可以通过一行代码实现:

**Java**

```java
//Convenient
driver.get("https://selenium.dev");

//Longer way
driver.navigate().to("https://selenium.dev");
```

**Python**

```python
driver.get("https://www.selenium.dev/selenium/web/index.html")
```

**CSharp**

```csharp
//Convenient
driver.Url = "https://selenium.dev";
//Longer
driver.Navigate().GoToUrl("https://selenium.dev");
```

**Ruby**

```ruby

it 'navigates to a page' do
  driver.navigate.to 'https://www.selenium.dev/'
```

**JavaScript**

```javascript
//Convenient
await driver.get('https://www.selenium.dev');

//Longer way
await driver.navigate().to("https://www.selenium.dev/selenium/web/index.html");
```

**Kotlin**

```kotlin
// 简便的方法
driver.get("https://selenium.dev")

// 更长的方法
driver.navigate().to("https://selenium.dev")
```

## 后退

按下浏览器的后退按钮:

**Java**

```java
//Back
driver.navigate().back();
```

**Python**

```python
driver.back()
```

**CSharp**

```csharp
 //Back
 driver.Navigate().Back();
 ```

**Ruby**

```ruby
driver.navigate.to 'https://www.selenium.dev/'
```

**JavaScript**

```javascript
//Back
await driver.navigate().back();
```

**Kotlin**

```kotlin
driver.navigate().back() 
```

## 前进
按下浏览器的前进键:

**Java**

```java
//Forward
driver.navigate().forward();
```

**Python**

```python
driver.forward()
```

**CSharp**

```csharp
 //Forward
 driver.Navigate().Forward();
 ```

**Ruby**

```ruby
driver.navigate.to 'https://www.selenium.dev/selenium/web/inputs.html'
```

**JavaScript**

```javascript
//Forward
await driver.navigate().forward();
```

**Kotlin**

```kotlin
driver.navigate().forward()
```

## 刷新
刷新当前页面:

**Java**

```java
//Refresh
driver.navigate().refresh();
```

**Python**

```python
driver.refresh()
```

**CSharp**

```csharp
 //Refresh
 driver.Navigate().Refresh();
 ```

**Ruby**

```ruby
it 'refreshes the page' do
```

**JavaScript**

```javascript
//Refresh
await driver.navigate().refresh();
```

**Kotlin**

```kotlin
driver.navigate().refresh()
```
