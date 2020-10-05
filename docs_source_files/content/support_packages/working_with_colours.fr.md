---
title: "Travailler avec les couleurs"
weight: 2
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

Vous pouvez maintenant commencer à créer des objets de couleur.
Chaque objet de couleur devra être créé à partir d'une 
représentation sous forme de chaîne de votre couleur.
Les représentations de couleurs prises en charge sont:

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

La classe Color prend également en charge toutes les 
définitions de couleurs de base spécifié dans
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

Parfois, les navigateurs renvoient une valeur de couleur "transparente"
si aucune couleur n'a été définie sur un élément.
La classe Color prend également en charge ceci:

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

Vous pouvez désormais interroger un élément en toute sécurité
pour obtenir sa couleur / couleur de fond sachant que
toute réponse sera correctement analysée
et converti en un objet Color valide:

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

Vous pouvez ensuite comparer directement les objets de couleur:

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

Ou vous pouvez convertir la couleur dans l'un des formats suivants
et effectuer une validation statique:

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

Les couleurs ne sont plus un problème.
