# Browser navigation

URL: https://www.selenium.dev/documentation/webdriver/interactions/navigation/
Site index: https://www.selenium.dev/llms.txt

---

## Navigate to

The first thing you will want to do after launching a browser is to
open your website. This can be achieved in a single line:

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

## Back

Pressing the browser's back button:

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

## Forward
Pressing the browser's forward button:

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

## Refresh

Refresh the current page:

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
