# Working With Colors

URL: https://www.selenium.dev/documentation/webdriver/support_features/colors/
Site index: https://www.selenium.dev/llms.txt

---

You will occasionally want to validate the colour of something as part of your tests;
the problem is that colour definitions on the web are not constant.
Would it not be nice if there was an easy way to compare
a HEX representation of a colour with a RGB representation of a colour,
or a RGBA representation of a colour with a HSLA representation of a colour?

Worry not. There is a solution: the _Color_ class!

First of all, you will need to import the class:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
import org.openqa.selenium.support.Color;

```

**Python**

```python
from selenium.webdriver.support.color import Color

```

**CSharp**

_Not available in this language._

**Ruby**

```ruby
include Selenium::WebDriver::Support

```

**JavaScript**

_Not available in this language._

**Kotlin**

```kotlin
import org.openqa.selenium.support.Color
```

You can now start creating colour objects.
Every colour object will need to be created from a string representation of
your colour.
Supported colour representations are:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

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

_Not available in this language._

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

_Not available in this language._

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

The Color class also supports all of the base colour definitions
specified in
[http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4).

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

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

_Not available in this language._

**Ruby**

```ruby
BLACK = Color.from_string('black')
CHOCOLATE = Color.from_string('chocolate')
HOTPINK = Color.from_string('hotpink')

```

**JavaScript**

_Not available in this language._

**Kotlin**

```kotlin
private val BLACK = Color.fromString("black")
private val CHOCOLATE = Color.fromString("chocolate")
private val HOTPINK = Color.fromString("hotpink")

```

Sometimes browsers will return a colour value of "transparent"
if no colour has been set on an element.
The Color class also supports this:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
private final Color TRANSPARENT = Color.fromString("transparent");

```

**Python**

```python
TRANSPARENT = Color.from_string('transparent')

```

**CSharp**

_Not available in this language._

**Ruby**

```ruby
TRANSPARENT = Color.from_string('transparent')

```

**JavaScript**

_Not available in this language._

**Kotlin**

```kotlin
private val TRANSPARENT = Color.fromString("transparent")

```

You can now safely query an element
to get its colour/background colour knowing that
any response will be correctly parsed
and converted into a valid Color object:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

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

_Not available in this language._

**Ruby**

```ruby
login_button_colour = Color.from_string(driver.find_element(id: 'login').css_value('color'))

login_button_background_colour = Color.from_string(driver.find_element(id: 'login').css_value('background-color'))

```

**JavaScript**

_Not available in this language._

**Kotlin**

```kotlin
val loginButtonColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("color"))

val loginButtonBackgroundColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("background-color"))

```

You can then directly compare colour objects:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
assert loginButtonBackgroundColour.equals(HOTPINK);

```

**Python**

```python
assert login_button_background_colour == HOTPINK

```

**CSharp**

_Not available in this language._

**Ruby**

```ruby
assert(login_button_background_colour == HOTPINK)

```

**JavaScript**

_Not available in this language._

**Kotlin**

```kotlin
assert(loginButtonBackgroundColour.equals(HOTPINK))

```

Or you can convert the colour into one of the following formats
and perform a static validation:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

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

_Not available in this language._

**Ruby**

```ruby
assert(login_button_background_colour.hex == '#ff69b4')
assert(login_button_background_colour.rgba == 'rgba(255, 105, 180, 1)')
assert(login_button_background_colour.rgb == 'rgb(255, 105, 180)')

```

**JavaScript**

_Not available in this language._

**Kotlin**

```kotlin
assert(loginButtonBackgroundColour.asHex().equals("#ff69b4"))
assert(loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)"))
assert(loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)"))

```

Colours are no longer a problem.
