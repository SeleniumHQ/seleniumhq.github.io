---
title: "Arbeiten mit Farben"
weight: 2
---


Vielleicht möchten Sie im Rahmen Ihrer Tests die Farbe von etwas überprüfen. 
Das Problem ist, dass die Farbdefinitionen im Web nicht einheitlich sind. 
Wäre es nicht schön, wenn es einen einfachen Vergleich gäbe eine 
HEX-Darstellung einer Farbe mit einer RGB-Darstellung einer Farbe, 
oder eine RGBA-Darstellung einer Farbe mit der einer HSLA-Darstellung einer Farbe? 

Mach dir keine Sorgen. Es gibt eine Lösung: die _Color_ Klasse! 

Zuerst müssen Sie die Klasse importieren:


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
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}import org.openqa.selenium.support.Color{{< / code-panel >}}
{{< / code-tab >}}

Sie können nun mit dem Erstellen von Farbobjekten beginnen.
Jedes Farbobjekt muss aus einem string erstellt werden der 
deine Farbe repräsentiert.
Unterstützte Farbdarstellungen sind:

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
// This feature is not implemented - Help us by sending a pr to implement this feature
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

Die Color-Klasse unterstützt auch alle Basisfarbdefinitionen die spezifiziert sind in
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
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
private val BLACK = Color.fromString("black")
private val CHOCOLATE = Color.fromString("chocolate")
private val HOTPINK = Color.fromString("hotpink")
  {{< / code-panel >}}
{{< / code-tab >}}

Manchmal geben Browser den Farbwert "transparent" zurück 
wenn für ein Element keine Farbe festgelegt wurde. 
Die Color-Klasse unterstützt dies auch:

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
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
private val TRANSPARENT = Color.fromString("transparent")
  {{< / code-panel >}}
{{< / code-tab >}}

Sie können jetzt ein Element sicher abfragen 
um seine Farbe/Hintergrundfarbe zu erhalten, 
Mit dem Wissen, dass jede Antwort korrekt 
geparst wird und in ein gültiges Color-Objekt umgewandelt wird:

{{< code-tab >}}
  {{< code-panel language="java" >}}
Color loginButtonColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("color"));

Color loginButtonBackgroundColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("background-color"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
login_button_colour = Color.from_string(driver.find_element(By.ID,'login').value_of_css_property('color'))

login_button_background_colour = Color.from_string(driver.find_element(By.ID,'login').value_of_css_property('background-color'))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
login_button_colour = Color.from_string(driver.find_element(id: 'login').css_value('color'))

login_button_background_colour = Color.from_string(driver.find_element(id: 'login').css_value('background-color'))
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val loginButtonColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("color"))

val loginButtonBackgroundColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("background-color"))
  {{< / code-panel >}}
{{< / code-tab >}}

Anschließend können Sie Farbobjekte direkt vergleichen:

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
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
assert(loginButtonBackgroundColour.equals(HOTPINK))
  {{< / code-panel >}}
{{< / code-tab >}}

Or you can convert the colour into one of the following formats
and perform a static validation:

Oder Sie können die Farbe in eines der folgenden Formate konvertieren 
und eine statische Validierung durchführen:

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
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
assert(loginButtonBackgroundColour.asHex().equals("#ff69b4"))
assert(loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)"))
assert(loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)"))
  {{< / code-panel >}}
{{< / code-tab >}}

Farben sind kein Problem mehr.
