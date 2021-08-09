---
title: "Travailler avec les couleurs"
linkTitle: "Travailler avec les couleurs"
weight: 1
aliases: ["/documentation/fr/support_packages/working_with_colours/"]
---

Vous voudrez parfois valider la couleur de quelque 
chose dans le cadre de vos tests; le problème est que 
les définitions de couleurs sur le Web ne sont pas constantes.
Ne serait-ce pas bien s'il y avait un moyen facile de comparer
une représentation HEX d'une couleur avec une représentation 
RGB d'une couleur, ou une représentation RGBA d'une couleur 
avec une représentation HSLA d'une couleur?

Ne t'inquiètes pas. Il y a une solution: la classe _Color_!

Tout d'abord, vous devrez importer la classe:

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

Vous pouvez maintenant commencer à créer des objets de couleur.
Chaque objet de couleur devra être créé à partir d'une 
représentation sous forme de chaîne de votre couleur.
Les représentations de couleurs prises en charge sont:

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

La classe Color prend également en charge toutes les 
définitions de couleurs de base spécifié dans
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

Parfois, les navigateurs renvoient une valeur de couleur "transparente"
si aucune couleur n'a été définie sur un élément.
La classe Color prend également en charge ceci:

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

Vous pouvez désormais interroger un élément en toute sécurité
pour obtenir sa couleur / couleur de fond sachant que
toute réponse sera correctement analysée
et converti en un objet Color valide:

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

Vous pouvez ensuite comparer directement les objets de couleur:

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

Ou vous pouvez convertir la couleur dans l'un des formats suivants
et effectuer une validation statique:

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

Les couleurs ne sont plus un problème.
