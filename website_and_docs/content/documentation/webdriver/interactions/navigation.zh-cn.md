---
title: "浏览器导航"
linkTitle: "导航"
weight: 1
aliases: [
"/zh-cn/documentation/webdriver/browser/navigation/"
]
---

## 打开网站

启动浏览器后你要做的第一件事就是打开你的网站。这可以通过一行代码实现:


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L14-L18" >}}
{{< /tab >}}
{{< tab header="Python" >}}
driver.get("https://selenium.dev")
{{< /tab >}}
{{< tab header="CSharp" >}}
driver.Navigate().GoToUrl(@"https://selenium.dev");
{{< /tab >}}
{{< tab header="Ruby" >}}
    # 简便的方法
driver.get 'https://selenium.dev'

    # 更长的方法
driver.navigate.to 'https://selenium.dev'
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L16-L20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
// 简便的方法
driver.get("https://selenium.dev")

// 更长的方法
driver.navigate().to("https://selenium.dev")
{{< /tab >}}
{{< /tabpane >}}

## 后退

按下浏览器的后退按钮:
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L22-L23" >}}
{{< /tab >}}
{{< tab header="Python" >}}driver.back(){{< /tab >}}
{{< tab header="CSharp" >}}driver.Navigate().Back();{{< /tab >}}
{{< tab header="Ruby" >}}driver.navigate.back{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L24-L25" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}driver.navigate().back() {{< /tab >}}
{{< /tabpane >}}

## 前进
按下浏览器的前进键:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L27-L28" >}}
{{< /tab >}}
{{< tab header="Python" >}}driver.forward(){{< /tab >}}
{{< tab header="CSharp" >}}driver.Navigate().Forward();{{< /tab >}}
{{< tab header="Ruby" >}}driver.navigate.forward{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L29-L30" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}driver.navigate().forward(){{< /tab >}}
{{< /tabpane >}}

## 刷新
刷新当前页面:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L32-L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}driver.refresh(){{< /tab >}}
{{< tab header="CSharp" >}}driver.Navigate().Refresh();{{< /tab >}}
{{< tab header="Ruby" >}}driver.navigate.refresh{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L34-L35" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}driver.navigate().refresh(){{< /tab >}}
{{< /tabpane >}}
