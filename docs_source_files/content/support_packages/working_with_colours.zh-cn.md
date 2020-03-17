---
title: "同颜色一起工作"
weight: 2
---
 
在测试中, 您偶尔会需要验证某事物的颜色；问题是网络上的颜色定义不是个常量.
如果有一种简单的方法可以比较颜色的十六进制与RGB呈现, 或者颜色的RGBA与HSLA呈现, 岂不美哉?

不用担心有一个解决方案：_Color_ 类!

首先, 您需要导入该类:

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

您现在可以开始创建颜色对象. 每个颜色对象都需要使用您颜色的字符串定义来创建. 支持的颜色定义如下:

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

Color类还支持在以下网址中指定的所有基本颜色定义
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

如果元素上未设置颜色, 则有时浏览器会返回“透明”的颜色值. Color类也支持此功能:

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

现在, 您可以安全地查询元素以获取其颜色/背景色, 任何响应都将被正确解析并转换为有效的Color对象:

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

然后, 您可以直接比较颜色对象:


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

或者, 您可以将颜色转换为以下格式之一并执行静态验证:

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

颜色不再是问题.
