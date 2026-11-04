# 同颜色一起工作

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/colors/

---

在测试中, 您偶尔会需要验证某事物的颜色；问题是网络上的颜色定义不是个常量.
如果有一种简单的方法可以比较颜色的十六进制与RGB呈现, 或者颜色的RGBA与HSLA呈现, 岂不美哉?

不用担心有一个解决方案：_Color_ 类!

首先, 您需要导入该类:

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

您现在可以开始创建颜色对象. 每个颜色对象都需要使用您颜色的字符串定义来创建. 支持的颜色定义如下:

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
// This feature is not implemented - Help us by sending a pr to implement this feature

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

Color类还支持在以下网址中指定的所有基本颜色定义
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
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Kotlin**

```kotlin
private val BLACK = Color.fromString("black")
private val CHOCOLATE = Color.fromString("chocolate")
private val HOTPINK = Color.fromString("hotpink")

```

如果元素上未设置颜色, 则有时浏览器会返回“透明”的颜色值. Color类也支持此功能:

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
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Kotlin**

```kotlin
private val TRANSPARENT = Color.fromString("transparent")

```

现在, 您可以安全地查询元素以获取其颜色/背景色, 任何响应都将被正确解析并转换为有效的Color对象:

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
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Kotlin**

```kotlin
val loginButtonColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("color"))

val loginButtonBackgroundColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("background-color"))

```

然后, 您可以直接比较颜色对象:

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
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Kotlin**

```kotlin
assert(loginButtonBackgroundColour.equals(HOTPINK))

```

或者, 您可以将颜色转换为以下格式之一并执行静态验证:

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
// This feature is not implemented - Help us by sending a pr to implement this feature

```

**Kotlin**

```kotlin
assert(loginButtonBackgroundColour.asHex().equals("#ff69b4"))
assert(loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)"))
assert(loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)"))

```

颜色不再是问题.
