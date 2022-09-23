---
title: "色を扱う"
linkTitle: "色を扱う"
weight: 1
aliases: [
"/documentation/ja/support_packages/working_with_colours/",
"/ja/documentation/support_packages/working_with_colours/",
"/ja/documentation/webdriver/additional_features/working_with_colours/",
]
---


テストの一部として何かの色を検証したい場合があります。
問題は、ウェブ上の色の定義が一定ではないことです。
色のHEX表現を色のRGB表現と比較する簡単な方法、または色のRGBA表現を色のHSLA表現と比較する簡単な方法があったらいいのではないでしょうか？

心配しないでください。解決策があります。:  _Color_ クラスです！

まず、クラスをインポートする必要があります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.support.Color;
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.color import Color
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" >}}
include Selenium::WebDriver::Support
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}import org.openqa.selenium.support.Color{{< /tab >}}
{{< /tabpane >}}

これで、カラーオブジェクトの作成を開始できます。
すべての色オブジェクトは、色の文字列表現から作成する必要があります。
サポートされている色表現は、以下のとおりです。

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
// This feature is not implemented - Help us by sending a pr to implement this feature
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

Colorクラスは、 [http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4) で指定されているすべての基本色定義もサポートしています。

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
// This feature is not implemented - Help us by sending a pr to implement this feature
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

要素に色が設定されていない場合、ブラウザは "透明" の色の値を返すことがあります。
Colorクラスもこれをサポートしています。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
private final Color TRANSPARENT = Color.fromString("transparent");
  {{< /tab >}}
  {{< tab header="Python" >}}
TRANSPARENT = Color.from_string('transparent')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
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

レスポンスが正しく解析され、有効なColorオブジェクトに変換されることを認識して、要素を安全にクエリしてその色/背景色を取得できるようになりました。

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
// This feature is not implemented - Help us by sending a pr to implement this feature
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

そして、色オブジェクトを直接比較できます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
assert loginButtonBackgroundColour.equals(HOTPINK);
  {{< /tab >}}
  {{< tab header="Python" >}}
assert login_button_background_colour == HOTPINK
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
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

または、色を次の形式のいずれかに変換し、静的に検証することができます。

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
// This feature is not implemented - Help us by sending a pr to implement this feature
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

色はもはや問題ではありません。
