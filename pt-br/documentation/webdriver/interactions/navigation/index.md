# Browser navigation

URL: https://www.selenium.dev/pt-br/documentation/webdriver/interactions/navigation/

---

## Navegar para

A primeira coisa que você vai querer fazer depois de iniciar um navegador é
abrir o seu site. Isso pode ser feito em uma única linha, utilize o seguinte comando:

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

## Voltar

Pressionando o botão Voltar do navegador:

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

## Avançar
Pressionando o botão Avançar do navegador:

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

## Atualizar

Atualizando a página atual:

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
