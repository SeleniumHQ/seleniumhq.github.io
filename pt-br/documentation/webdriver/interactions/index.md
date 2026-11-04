# Browser interactions

URL: https://www.selenium.dev/pt-br/documentation/webdriver/interactions/

---

## Get browser information

### Coletar título

Você pode ler o título da página atual no navegador:

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

### Coletar a URL atual

Você pode ler a URL atual na barra de endereço do navegador usando:

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

- [Browser navigation](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/navigation/index.md)
- [Alertas, prompts e confirmações JavaScript](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/alerts/index.md)
- [Trabalhando com cookies](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/cookies/index.md)
- [Working with IFrames and frames](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/frames/index.md)
- [Print Page](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/print_page/index.md)
- [Working with windows and tabs](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/windows/index.md)
- [Virtual Authenticator](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/virtual_authenticator/index.md): Uma representação do modelo Web Authenticator.
