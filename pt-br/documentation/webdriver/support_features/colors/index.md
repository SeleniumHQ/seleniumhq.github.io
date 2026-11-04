# Trabalhando com cores

URL: https://www.selenium.dev/pt-br/documentation/webdriver/support_features/colors/

---

Ocasionalmente, você desejará validar a cor de algo como parte de seus testes;
o problema é que as definições de cores na web não são constantes.
Não seria bom se houvesse uma maneira fácil de comparar
uma representação HEX de uma cor com uma representação RGB de uma cor,
ou uma representação RGBA de uma cor com uma representação HSLA de uma cor?

Não se preocupe. Existe uma solução: a classe _Color_!

Em primeiro lugar, você precisará importar a classe:

**Java**

```java
import org.openqa.selenium.support.Color;

```

**Python**

```python
from selenium.webdriver.support.color import Color

```

**CSharp**

```csharp
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Ruby**

```ruby
include Selenium::WebDriver::Support

```

**JavaScript**

```javascript
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Kotlin**

```kotlin
import org.openqa.selenium.support.Color
```

Agora você pode começar a criar objetos coloridos.
Cada objeto de cor precisará ser criado a partir de uma representação de string de
sua cor.
As representações de cores com suporte são:

**Java**

```java
private final Color HEX_COLOUR = Color.fromString("#2F7ED8");
private final Color RGB_COLOUR = Color.fromString("rgb(255, 255, 255)");
private final Color RGB_COLOUR = Color.fromString("rgb(40%, 20%, 40%)");
private final Color RGBA_COLOUR = Color.fromString("rgba(255, 255, 255, 0.5)");
private final Color RGBA_COLOUR = Color.fromString("rgba(40%, 20%, 40%, 0.5)");
private final Color HSL_COLOUR = Color.fromString("hsl(100, 0%, 50%)");
private final Color HSLA_COLOUR = Color.fromString("hsla(100, 0%, 50%, 0.5)");

```

**Python**

```python
HEX_COLOUR = Color.from_string('#2F7ED8')
RGB_COLOUR = Color.from_string('rgb(255, 255, 255)')
RGB_COLOUR = Color.from_string('rgb(40%, 20%, 40%)')
RGBA_COLOUR = Color.from_string('rgba(255, 255, 255, 0.5)')
RGBA_COLOUR = Color.from_string('rgba(40%, 20%, 40%, 0.5)')
HSL_COLOUR = Color.from_string('hsl(100, 0%, 50%)')
HSLA_COLOUR = Color.from_string('hsla(100, 0%, 50%, 0.5)')

```

**CSharp**

```csharp
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Ruby**

```ruby
HEX_COLOUR = Color.from_string('#2F7ED8')
RGB_COLOUR = Color.from_string('rgb(255, 255, 255)')
RGB_COLOUR = Color.from_string('rgb(40%, 20%, 40%)')
RGBA_COLOUR = Color.from_string('rgba(255, 255, 255, 0.5)')
RGBA_COLOUR = Color.from_string('rgba(40%, 20%, 40%, 0.5)')
HSL_COLOUR = Color.from_string('hsl(100, 0%, 50%)')
HSLA_COLOUR = Color.from_string('hsla(100, 0%, 50%, 0.5)')

```

**JavaScript**

```javascript
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade

```

**Kotlin**

```kotlin
private val HEX_COLOUR = Color.fromString("#2F7ED8")
private val RGB_COLOUR = Color.fromString("rgb(255, 255, 255)")
private val RGB_COLOUR_PERCENT = Color.fromString("rgb(40%, 20%, 40%)")
private val RGBA_COLOUR = Color.fromString("rgba(255, 255, 255, 0.5)")
private val RGBA_COLOUR_PERCENT = Color.fromString("rgba(40%, 20%, 40%, 0.5)")
private val HSL_COLOUR = Color.fromString("hsl(100, 0%, 50%)")
private val HSLA_COLOUR = Color.fromString("hsla(100, 0%, 50%, 0.5)")

```

A classe Color também suporta todas as definições de cores básicas
especificadas em
[http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4).

**Java**

```java
private final Color BLACK = Color.fromString("black");
private final Color CHOCOLATE = Color.fromString("chocolate");
private final Color HOTPINK = Color.fromString("hotpink");

```

**Python**

```python
BLACK = Color.from_string('black')
CHOCOLATE = Color.from_string('chocolate')
HOTPINK = Color.from_string('hotpink')

```

**CSharp**

```csharp
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Ruby**

```ruby
BLACK = Color.from_string('black')
CHOCOLATE = Color.from_string('chocolate')
HOTPINK = Color.from_string('hotpink')

```

**JavaScript**

```javascript
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade

```

**Kotlin**

```kotlin
private val BLACK = Color.fromString("black")
private val CHOCOLATE = Color.fromString("chocolate")
private val HOTPINK = Color.fromString("hotpink")

```

Às vezes, os navegadores retornam um valor de cor "transparent"
se nenhuma cor foi definida em um elemento.
A classe Color também oferece suporte para isso:

**Java**

```java
private final Color TRANSPARENT = Color.fromString("transparent");

```

**Python**

```python
TRANSPARENT = Color.from_string('transparent')

```

**CSharp**

```csharp
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Ruby**

```ruby
TRANSPARENT = Color.from_string('transparent')

```

**JavaScript**

```javascript
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade

```

**Kotlin**

```kotlin
private val TRANSPARENT = Color.fromString("transparent")

```

Agora você pode consultar com segurança um elemento
para obter sua cor / cor de fundo sabendo que
qualquer resposta será analisada corretamente
e convertido em um objeto Color válido:

**Java**

```java
Color loginButtonColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("color"));

Color loginButtonBackgroundColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("background-color"));

```

**Python**

```python
login_button_colour = Color.from_string(driver.find_element(By.ID,'login').value_of_css_property('color'))

login_button_background_colour = Color.from_string(driver.find_element(By.ID,'login').value_of_css_property('background-color'))

```

**CSharp**

```csharp
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Ruby**

```ruby
login_button_colour = Color.from_string(driver.find_element(id: 'login').css_value('color'))

login_button_background_colour = Color.from_string(driver.find_element(id: 'login').css_value('background-color'))

```

**JavaScript**

```javascript
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade

```

**Kotlin**

```kotlin
val loginButtonColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("color"))

val loginButtonBackgroundColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("background-color"))

```

Você pode então comparar diretamente os objetos coloridos:

**Java**

```java
assert loginButtonBackgroundColour.equals(HOTPINK);

```

**Python**

```python
assert login_button_background_colour == HOTPINK

```

**CSharp**

```csharp
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Ruby**

```ruby
assert(login_button_background_colour == HOTPINK)

```

**JavaScript**

```javascript
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade

```

**Kotlin**

```kotlin
assert(loginButtonBackgroundColour.equals(HOTPINK))

```

Ou você pode converter a cor em um dos seguintes formatos
e realizar uma validação estática:

**Java**

```java
assert loginButtonBackgroundColour.asHex().equals("#ff69b4");
assert loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)");
assert loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)");

```

**Python**

```python
assert login_button_background_colour.hex == '#ff69b4'
assert login_button_background_colour.rgba == 'rgba(255, 105, 180, 1)'
assert login_button_background_colour.rgb == 'rgb(255, 105, 180)'

```

**CSharp**

```csharp
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Ruby**

```ruby
assert(login_button_background_colour.hex == '#ff69b4')
assert(login_button_background_colour.rgba == 'rgba(255, 105, 180, 1)')
assert(login_button_background_colour.rgb == 'rgb(255, 105, 180)')

```

**JavaScript**

```javascript
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade

```

**Kotlin**

```kotlin
assert(loginButtonBackgroundColour.asHex().equals("#ff69b4"))
assert(loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)"))
assert(loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)"))

```

As cores não são mais um problema.
