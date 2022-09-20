---
title: "与iFrames和frames一起工作"
linkTitle: "Frames"
weight: 6
aliases: [
"/zh-cn/documentation/webdriver/browser/frames/"
]
---

框架是一种现在已被弃用的方法，用于从同一域中的多个文档构建站点布局。除非你使用的是 HTML5
之前的 webapp，否则你不太可能与他们合作。内嵌框架允许插入来自完全不同领域的文档，并且仍然经常使用。


如果您需要使用框架或 iframe, WebDriver 允许您以相同的方式使用它们。考虑 iframe 中的一个按钮。
如果我们使用浏览器开发工具检查元素，我们可能会看到以下内容:

```html
<div id="modal">
  <iframe id="buttonframe"name="myframe"src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

如果不是 iframe，我们可能会使用如下方式点击按钮:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// 这不会工作
driver.findElement(By.tagName("button")).click();
{{< /tab >}}
{{< tab header="Python" >}}
    # 这不会工作
driver.find_element(By.TAG_NAME, 'button').click()
{{< /tab >}}
{{< tab header="CSharp" >}}
// 这不会工作
driver.FindElement(By.TagName("button")).Click();
{{< /tab >}}
{{< tab header="Ruby" >}}
    # 这不会工作
driver.find_element(:tag_name,'button').click
{{< /tab >}}
{{< tab header="JavaScript" >}}
// 这不会工作
await driver.findElement(By.css('button')).click();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// 这不会工作
driver.findElement(By.tagName("button")).click()
{{< /tab >}}
{{< /tabpane >}}

但是，如果 iframe 之外没有按钮，那么您可能会得到一个 _no such element 无此元素_ 的错误。
这是因为 Selenium 只知道顶层文档中的元素。为了与按钮进行交互，我们需要首先切换到框架，
这与切换窗口的方式类似。WebDriver 提供了三种切换到帧的方法。

## 使用 WebElement

使用 WebElement 进行切换是最灵活的选择。您可以使用首选的选择器找到框架并切换到它。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// 存储网页元素
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

// 切换到 frame
driver.switchTo().frame(iframe);

// 现在可以点击按钮
driver.findElement(By.tagName("button")).click();
{{< /tab >}}
{{< tab header="Python" >}}
    # 存储网页元素
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

    # 切换到选择的 iframe
driver.switch_to.frame(iframe)

    # 单击按钮
driver.find_element(By.TAG_NAME, 'button').click()
{{< /tab >}}
{{< tab header="CSharp" >}}
// 存储网页元素
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

// 切换到 frame
driver.SwitchTo().Frame(iframe);

// 现在可以点击按钮
driver.FindElement(By.TagName("button")).Click();
{{< /tab >}}
{{< tab header="Ruby" >}}
    # Store iframe web element
iframe = driver.find_element(:css,'#modal> iframe')

    # 切换到 frame
driver.switch_to.frame iframe

    # 单击按钮
driver.find_element(:tag_name,'button').click
{{< /tab >}}
{{< tab header="JavaScript" >}}
// 存储网页元素
const iframe = driver.findElement(By.css('#modal> iframe'));

// 切换到 frame
await driver.switchTo().frame(iframe);

// 现在可以点击按钮
await driver.findElement(By.css('button')).click();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// 存储网页元素
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

// 切换到 frame
driver.switchTo().frame(iframe)

// 现在可以点击按钮
driver.findElement(By.tagName("button")).click()
{{< /tab >}}
{{< /tabpane >}}

## 使用 name 或 id

如果您的 frame 或 iframe 具有 id 或 name 属性，则可以使用该属性。如果名称或 id 在页面上不是唯一的，
那么将切换到找到的第一个。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// 使用 ID
driver.switchTo().frame("buttonframe");

// 或者使用 name 代替
driver.switchTo().frame("myframe");

// 现在可以点击按钮
driver.findElement(By.tagName("button")).click();
{{< /tab >}}
{{< tab header="Python" >}}
    # 通过 id 切换框架
driver.switch_to.frame('buttonframe')

    # 单击按钮
driver.find_element(By.TAG_NAME, 'button').click()
{{< /tab >}}
{{< tab header="CSharp" >}}
// 使用 ID
driver.SwitchTo().Frame("buttonframe");

// 或者使用 name 代替
driver.SwitchTo().Frame("myframe");

// 现在可以点击按钮
driver.FindElement(By.TagName("button")).Click();
{{< /tab >}}
{{< tab header="Ruby" >}}
    # Switch by ID
driver.switch_to.frame 'buttonframe'

    # 单击按钮
driver.find_element(:tag_name,'button').click
{{< /tab >}}
{{< tab header="JavaScript" >}}
// 使用 ID
await driver.switchTo().frame('buttonframe');

// 或者使用 name 代替
await driver.switchTo().frame('myframe');

// 现在可以点击按钮
await driver.findElement(By.css('button')).click();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// 使用 ID
driver.switchTo().frame("buttonframe")

// 或者使用 name 代替
driver.switchTo().frame("myframe")

// 现在可以点击按钮
driver.findElement(By.tagName("button")).click()
{{< /tab >}}
{{< /tabpane >}}

## 使用索引

还可以使用frame的索引，
例如可以使用JavaScript中的
_window.frames_ 进行查询.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// 切换到第 2 个框架
driver.switchTo().frame(1);
{{< /tab >}}
{{< tab header="Ruby" >}}
    # 切换到第 2 个框架
driver.switch_to.frame(1)
{{< /tab >}}
{{< tab header="CSharp" >}}
// 切换到第 2 个框架
driver.SwitchTo().Frame(1);
{{< /tab >}}
{{< tab header="Python" >}}
    # 基于索引切换到第 2 个 iframe
iframe = driver.find_elements(By.TAG_NAME,'iframe')[1]

    # 切换到选择的 iframe
driver.switch_to.frame(iframe)
{{< /tab >}}
{{< tab header="JavaScript" >}}
// 切换到第 2 个框架
await driver.switchTo().frame(1);
{{< /tab >}}
{{< tab header="Kotlin" >}}
// 切换到第 2 个框架
driver.switchTo().frame(1)
{{< /tab >}}
{{< /tabpane >}}


## 离开框架

离开 iframe 或 frameset，切换回默认内容，如下所示:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// 回到顶层
driver.switchTo().defaultContent();
{{< /tab >}}
{{< tab header="Python" >}}
    # 切回到默认内容
driver.switch_to.default_content()
{{< /tab >}}
{{< tab header="CSharp" >}}
// 回到顶层
driver.SwitchTo().DefaultContent();
{{< /tab >}}
{{< tab header="Ruby" >}}
    # 回到顶层
driver.switch_to.default_content
{{< /tab >}}
{{< tab header="JavaScript" >}}
// 回到顶层
await driver.switchTo().defaultContent();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// 回到顶层
driver.switchTo().defaultContent()
{{< /tab >}}
{{< /tabpane >}}
