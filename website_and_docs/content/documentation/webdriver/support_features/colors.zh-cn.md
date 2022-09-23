---
title: "同颜色一起工作"
linkTitle: "颜色"
weight: 1
aliases: [
"/documentation/zh-cn/support_packages/working_with_colours/",
"/zh-cn/documentation/support_packages/working_with_colours/",
"/zh-cn/documentation/webdriver/additional_features/working_with_colours/",
]
---
 
在测试中, 您偶尔会需要验证某事物的颜色；问题是网络上的颜色定义不是个常量.
如果有一种简单的方法可以比较颜色的十六进制与RGB呈现, 或者颜色的RGBA与HSLA呈现, 岂不美哉?

不用担心有一个解决方案：_Color_ 类!

首先, 您需要导入该类:

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

您现在可以开始创建颜色对象. 每个颜色对象都需要使用您颜色的字符串定义来创建. 支持的颜色定义如下:

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

Color类还支持在以下网址中指定的所有基本颜色定义
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

如果元素上未设置颜色, 则有时浏览器会返回“透明”的颜色值. Color类也支持此功能:

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

现在, 您可以安全地查询元素以获取其颜色/背景色, 任何响应都将被正确解析并转换为有效的Color对象:

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

然后, 您可以直接比较颜色对象:


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

或者, 您可以将颜色转换为以下格式之一并执行静态验证:

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

颜色不再是问题.
