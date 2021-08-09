---
title: "Arbeiten mit Farben"
linkTitle: "Arbeiten mit Farben"
weight: 1
aliases: ["/documentation/de/support_packages/working_with_colours/"]
---


Vielleicht möchten Sie im Rahmen Ihrer Tests die Farbe von etwas überprüfen. 
Das Problem ist, dass die Farbdefinitionen im Web nicht einheitlich sind. 
Wäre es nicht schön, wenn es einen einfachen Vergleich gäbe eine 
HEX-Darstellung einer Farbe mit einer RGB-Darstellung einer Farbe, 
oder eine RGBA-Darstellung einer Farbe mit der einer HSLA-Darstellung einer Farbe? 

Mach dir keine Sorgen. Es gibt eine Lösung: die _Color_ Klasse! 

Zuerst müssen Sie die Klasse importieren:


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.support.Color;
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.color import Color
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
include Selenium::WebDriver::Support
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}import org.openqa.selenium.support.Color{{< /tab >}}
{{< /tabpane >}}

Sie können nun mit dem Erstellen von Farbobjekten beginnen.
Jedes Farbobjekt muss aus einem string erstellt werden der 
deine Farbe repräsentiert.
Unterstützte Farbdarstellungen sind:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
private final Color HEX_COLOUR = Color.fromString("#2F7ED8");
private final Color RGB_COLOUR = Color.fromString("rgb(255, 255, 255)");
private final Color RGB_COLOUR = Color.fromString("rgb(40%, 20%, 40%)");
private final Color RGBA_COLOUR = Color.fromString("rgba(255, 255, 255, 0.5)");
private final Color RGBA_COLOUR = Color.fromString("rgba(40%, 20%, 40%, 0.5)");
private final Color HSL_COLOUR = Color.fromString("hsl(100, 0%, 50%)");
private final Color HSLA_COLOUR = Color.fromString("hsla(100, 0%, 50%, 0.5)");
  {{< /tab >}}
  {{< tab header="Python" >}}
HEX_COLOUR = Color.from_string('#2F7ED8')
RGB_COLOUR = Color.from_string('rgb(255, 255, 255)')
RGB_COLOUR = Color.from_string('rgb(40%, 20%, 40%)')
RGBA_COLOUR = Color.from_string('rgba(255, 255, 255, 0.5)')
RGBA_COLOUR = Color.from_string('rgba(40%, 20%, 40%, 0.5)')
HSL_COLOUR = Color.from_string('hsl(100, 0%, 50%)')
HSLA_COLOUR = Color.from_string('hsla(100, 0%, 50%, 0.5)')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
HEX_COLOUR = Color.from_string('#2F7ED8')
RGB_COLOUR = Color.from_string('rgb(255, 255, 255)')
RGB_COLOUR = Color.from_string('rgb(40%, 20%, 40%)')
RGBA_COLOUR = Color.from_string('rgba(255, 255, 255, 0.5)')
RGBA_COLOUR = Color.from_string('rgba(40%, 20%, 40%, 0.5)')
HSL_COLOUR = Color.from_string('hsl(100, 0%, 50%)')
HSLA_COLOUR = Color.from_string('hsla(100, 0%, 50%, 0.5)')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
private val HEX_COLOUR = Color.fromString("#2F7ED8")
private val RGB_COLOUR = Color.fromString("rgb(255, 255, 255)")
private val RGB_COLOUR_PERCENT = Color.fromString("rgb(40%, 20%, 40%)")
private val RGBA_COLOUR = Color.fromString("rgba(255, 255, 255, 0.5)")
private val RGBA_COLOUR_PERCENT = Color.fromString("rgba(40%, 20%, 40%, 0.5)")
private val HSL_COLOUR = Color.fromString("hsl(100, 0%, 50%)")
private val HSLA_COLOUR = Color.fromString("hsla(100, 0%, 50%, 0.5)")
  {{< /tab >}}
{{< /tabpane >}}

Die Color-Klasse unterstützt auch alle Basisfarbdefinitionen die spezifiziert sind in
[http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4).

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
private final Color BLACK = Color.fromString("black");
private final Color CHOCOLATE = Color.fromString("chocolate");
private final Color HOTPINK = Color.fromString("hotpink");
  {{< /tab >}}
  {{< tab header="Python" >}}
BLACK = Color.from_string('black')
CHOCOLATE = Color.from_string('chocolate')
HOTPINK = Color.from_string('hotpink')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
BLACK = Color.from_string('black')
CHOCOLATE = Color.from_string('chocolate')
HOTPINK = Color.from_string('hotpink')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
private val BLACK = Color.fromString("black")
private val CHOCOLATE = Color.fromString("chocolate")
private val HOTPINK = Color.fromString("hotpink")
  {{< /tab >}}
{{< /tabpane >}}

Manchmal geben Browser den Farbwert "transparent" zurück 
wenn für ein Element keine Farbe festgelegt wurde. 
Die Color-Klasse unterstützt dies auch:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
private final Color TRANSPARENT = Color.fromString("transparent");
  {{< /tab >}}
  {{< tab header="Python" >}}
TRANSPARENT = Color.from_string('transparent')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
TRANSPARENT = Color.from_string('transparent')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
private val TRANSPARENT = Color.fromString("transparent")
  {{< /tab >}}
{{< /tabpane >}}

Sie können jetzt ein Element sicher abfragen 
um seine Farbe/Hintergrundfarbe zu erhalten, 
Mit dem Wissen, dass jede Antwort korrekt 
geparst wird und in ein gültiges Color-Objekt umgewandelt wird:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
Color loginButtonColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("color"));

Color loginButtonBackgroundColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("background-color"));
  {{< /tab >}}
  {{< tab header="Python" >}}
login_button_colour = Color.from_string(driver.find_element(By.ID,'login').value_of_css_property('color'))

login_button_background_colour = Color.from_string(driver.find_element(By.ID,'login').value_of_css_property('background-color'))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
login_button_colour = Color.from_string(driver.find_element(id: 'login').css_value('color'))

login_button_background_colour = Color.from_string(driver.find_element(id: 'login').css_value('background-color'))
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val loginButtonColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("color"))

val loginButtonBackgroundColour = Color.fromString(driver.findElement(By.id("login")).getCssValue("background-color"))
  {{< /tab >}}
{{< /tabpane >}}

Anschließend können Sie Farbobjekte direkt vergleichen:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
assert loginButtonBackgroundColour.equals(HOTPINK);
  {{< /tab >}}
  {{< tab header="Python" >}}
assert login_button_background_colour == HOTPINK
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
assert(login_button_background_colour == HOTPINK)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
assert(loginButtonBackgroundColour.equals(HOTPINK))
  {{< /tab >}}
{{< /tabpane >}}

Or you can convert the colour into one of the following formats
and perform a static validation:

Oder Sie können die Farbe in eines der folgenden Formate konvertieren 
und eine statische Validierung durchführen:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
assert loginButtonBackgroundColour.asHex().equals("#ff69b4");
assert loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)");
assert loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)");
  {{< /tab >}}
  {{< tab header="Python" >}}
assert login_button_background_colour.hex == '#ff69b4'
assert login_button_background_colour.rgba == 'rgba(255, 105, 180, 1)'
assert login_button_background_colour.rgb == 'rgb(255, 105, 180)'
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
assert(login_button_background_colour.hex == '#ff69b4')
assert(login_button_background_colour.rgba == 'rgba(255, 105, 180, 1)')
assert(login_button_background_colour.rgb == 'rgb(255, 105, 180)')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
assert(loginButtonBackgroundColour.asHex().equals("#ff69b4"))
assert(loginButtonBackgroundColour.asRgba().equals("rgba(255, 105, 180, 1)"))
assert(loginButtonBackgroundColour.asRgb().equals("rgb(255, 105, 180)"))
  {{< /tab >}}
{{< /tabpane >}}

Farben sind kein Problem mehr.
