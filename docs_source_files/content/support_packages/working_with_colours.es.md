---
title: "Trabajando con colores"
weight: 2
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Spanish. Do you speak Spanish? Help us to translate
it by sending us pull requests!
{{% /notice %}}

You will occasionally want to validate the colour of something as part of your tests;
the problem is that colour definitions on the web are not constant.
Would it not be nice if there was an easy way to compare
a HEX representation of a colour with a RGB representation of a colour,
or a RGBA representation of a colour with a HSLA representation of a colour?

Worry not. There is a solution: the _Color_ class!

First of all, you will need to import the class:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.support.Color;
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.color import Color
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
include Selenium::WebDriver::Support
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}import org.openqa.selenium.support.Color{{< / code-panel >}}
{{< / code-tab >}}

You can now start creating colour objects.
Every colour object will need to be created from a string representation of 
your colour.
Supported colour representations are:

{{< code-tab >}}
  {{< code-panel language="java" >}}
private final Color HEX_COLOUR = Color.fromString("#2F7ED8");
private final Color RGB_COLOUR = Color.fromString("rgb(255, 255, 255)");
private final Color RGB_COLOUR = Color.fromString("rgb(40%, 20%, 40%)");
private final Color RGBA_COLOUR = Color.fromString("rgba(255, 255, 255, 0.5)");
private final Color RGBA_COLOUR = Color.fromString("rgba(40%, 20%, 40%, 0.5)");
private final Color HSL_COLOUR = Color.fromString("hsl(100, 0%, 50%)");
private final Color HSLA_COLOUR = Color.fromString("hsla(100, 0%, 50%, 0.5)");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
HEX_COLOUR = Color.from_string('#2F7ED8')
RGB_COLOUR = Color.from_string('rgb(255, 255, 255)')
RGB_COLOUR = Color.from_string('rgb(40%, 20%, 40%)')
RGBA_COLOUR = Color.from_string('rgba(255, 255, 255, 0.5)')
RGBA_COLOUR = Color.from_string('rgba(40%, 20%, 40%, 0.5)')
HSL_COLOUR = Color.from_string('hsl(100, 0%, 50%)')
HSLA_COLOUR = Color.from_string('hsla(100, 0%, 50%, 0.5)')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
HEX_COLOUR = Color.from_string('#2F7ED8')
RGB_COLOUR = Color.from_string('rgb(255, 255, 255)')
RGB_COLOUR = Color.from_string('rgb(40%, 20%, 40%)')
RGBA_COLOUR = Color.from_string('rgba(255, 255, 255, 0.5)')
RGBA_COLOUR = Color.from_string('rgba(40%, 20%, 40%, 0.5)')
HSL_COLOUR = Color.from_string('hsl(100, 0%, 50%)')
HSLA_COLOUR = Color.from_string('hsla(100, 0%, 50%, 0.5)')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
private val HEX_COLOUR = Color.fromString("#2F7ED8")
private val RGB_COLOUR = Color.fromString("rgb(255, 255, 255)")
private val RGB_COLOUR_PERCENT = Color.fromString("rgb(40%, 20%, 40%)")
private val RGBA_COLOUR = Color.fromString("rgba(255, 255, 255, 0.5)")
private val RGBA_COLOUR_PERCENT = Color.fromString("rgba(40%, 20%, 40%, 0.5)")
private val HSL_COLOUR = Color.fromString("hsl(100, 0%, 50%)")
private val HSLA_COLOUR = Color.fromString("hsla(100, 0%, 50%, 0.5)")
  {{< / code-panel >}}
{{< / code-tab >}}

The Color class also supports all of the base colour definitions
specified in 
[http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4).

{{< code-tab >}}
  {{< code-panel language="java" >}}
private final Color BLACK = Color.fromString("black");
private final Color CHOCOLATE = Color.fromString("chocolate");
private final Color HOTPINK = Color.fromString("hotpink");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
BLACK = Color.from_string('black')
CHOCOLATE = Color.from_string('chocolate')
HOTPINK = Color.from_string('hotpink')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
BLACK = Color.from_string('black')
CHOCOLATE = Color.from_string('chocolate')
HOTPINK = Color.from_string('hotpink')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
private val BLACK = Color.fromString("black")
private val CHOCOLATE = Color.fromString("chocolate")
private val HOTPINK = Color.fromString("hotpink")
  {{< / code-panel >}}
{{< / code-tab >}}

Sometimes browsers will return a colour value of "transparent"
if no colour has been set on an element.
The Color class also supports this:

{{< code-tab >}}
  {{< code-panel language="java" >}}
private final Color TRANSPARENT = Color.fromString("transparent");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
TRANSPARENT = Color.from_string('transparent')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
TRANSPARENT = Color.from_string('transparent')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
private val TRANSPARENT = Color.fromString("transparent")
  {{< / code-panel >}}
{{< / code-tab >}}

You can now safely query an element
to get its colour/background colour knowing that
any response will be correctly parsed
and converted into a valid Color object:

{{< code-tab >}}
  {{< code-panel language="java" >}}
Color loginButtonColour = driver.findElement(By.id("login")).getCssValue("color");
Color loginButtonBackgroundColour = driver.findElement(By.id("login")).getCssValue("background-color");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
login_button_colour = driver.find_element_by_id('login').value_of_css_property('color')
login_button_background_colour = driver.find_element_by_id('login').value_of_css_property('background-color');
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
login_button_colour = driver.find_element(id: 'login').css_value('color')
login_button_background_colour = driver.find_element(id: 'login').css_value('background-color');
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val loginButtonColour = driver.findElement(By.id("login")).getCssValue("color")
val loginButtonBackgroundColour = driver.findElement(By.id("login")).getCssValue("background-color")
  {{< / code-panel >}}
{{< / code-tab >}}

You can then directly compare colour objects:


{{< code-tab >}}
  {{< code-panel language="java" >}}
assert loginButtonBackgroundColour.equals(HOTPINK);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
assert login_button_background_colour == HOTPINK
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
assert(login_button_background_colour == HOTPINK)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
assert(loginButtonBackgroundColour.equals(HOTPINK))
  {{< / code-panel >}}
{{< / code-tab >}}

Or you can convert the colour into one of the following formats
and perform a static validation:

{{< code-tab >}}
  {{< code-panel language="java" >}}
assert loginButtonBackgroundColour.asHex().equals("#ff69b4");
assert loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)");
assert loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
assert login_button_background_colour.hex == '#ff69b4'
assert login_button_background_colour.rgba == 'rgba(255, 105, 180, 1)'
assert login_button_background_colour.rgb == 'rgb(255, 105, 180)'
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
assert(login_button_background_colour.hex == '#ff69b4')
assert(login_button_background_colour.rgba == 'rgba(255, 105, 180, 1)')
assert(login_button_background_colour.rgb == 'rgb(255, 105, 180)')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
assert(loginButtonBackgroundColour.asHex().equals("#ff69b4"))
assert(loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)"))
assert(loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)"))
  {{< / code-panel >}}
{{< / code-tab >}}

Colours are no longer a problem.
