# ブラウザー ナビゲーション

URL: https://www.selenium.dev/ja/documentation/webdriver/interactions/navigation/

---

## ナビゲート

ブラウザーを起動した後に最初に行うことは、Webサイトを開くことです。これは1行で実現できます。

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
//Convenient
driver.get("https://selenium.dev")

//Longer way
driver.navigate().to("https://selenium.dev")

```

## 戻る

ブラウザーの戻るボタンを押す。

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

## 次へ

ブラウザーの次へボタンを押す。

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

## 更新

現在のページを更新する。

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
