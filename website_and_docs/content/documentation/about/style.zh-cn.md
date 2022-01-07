---
title: "Style guide for Selenium documentation"
linkTitle: "Style"
needsTranslation: true
description: >-
    Conventions for contributing to the Selenium documenation and code examples
weight: 3
---

## 标题大写

应该避免标题完全大写, 例如 _A Very Fine Heading_, 
应该书写为 _A very fine heading_ .
没有意义的大写字母或者无视拼写协议的标题, 通常会带来误解.
我们更倾向于使用句子首字母大写的 _sentence case_ 的方式.

## 行的长度

在编辑以plain HTML格式编写的文档来源时, 请将行的长度限制在72个字符以内.

部分先进的贡献者, 使用了
[_semantic linefeeds_](//rhodesmill.org/brandon/2012/one-sentence-per-line),
这是一种不以HTML源码换行为基础的技术, 通过这种技术, 
公众看到的内容将会在文章中以“自然断开”的方式进行分割.
换句话说, 句子之间在更符合语义的地方被分割.
不必多虑每个段落的行, 强迫它们都以明确的边距结尾, 
而是可以将换行符添加到语义有断开的任何地方. 
(译者注：具体区别，对比本网页与原始md后，即可了解本段想表达的意思) 

通过git进行协作时, 
这种技术会使提交的差异更显而易见, 
但这不是我们强制贡献者使用的内容.

## Translations

本文档基于英语内容, 被翻译成多种语言. 
更改文件时, 请 **确保** 也对所有其他翻译文件进行更改. 
这可能会略有不同, 例如:
 
* 如果将代码示例添加到 `browser_manipulation.en.md` 文件中后, 
则还需将其添加到 `browser_manipulation.es.md`, 
`browser_manipulation.ef.md`, 
`browser_manipulation.ja.md` 以及所有其他的已翻译文件.

* 如果您发现可以改进的翻译, 请仅更改翻译的文件. 

* 如果要添加新的语言翻译, 请添加具有适当后缀的新文件. 
无需翻译所有内容即可提交PR, 可以迭代完成. 
不要忘记在 `config.toml` 文件中检查一些必要的配置. 

* 如果您用英语版本更改了文本, 
请用您的更改替换翻译文件中的同一部分(是的, 用英语), 
然后在文件顶部添加以下注意事项. 

```
{{%/* pageinfo color="warning" */%}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
   it by sending us pull requests!
</p>
{{%/* /pageinfo */%}}
```

## Code examples

All references to code should be language independent,
and the code itself should be placed inside code tabs.

### Default Code Tabs

The Docsy code tabs look like this:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
WebDriver driver = new ChromeDriver();
driver.get("https://selenium.dev");
driver.quit();
{{< /tab >}}
{{< tab header="Python" >}}
driver = webdriver.Chrome()
driver.get("http://selenium.dev")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" >}}
var driver = new ChromeDriver();
driver.Navigate().GoToUrl("https://selenium.dev");
driver.Quit();
{{< /tab >}}
{{< tab header="Ruby" >}}
driver = Selenium::WebDriver.for :chrome
driver.get 'https://selenium.dev'
driver.quit
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await new Builder().forBrowser('chrome').build();
await driver.get('https://selenium.dev');
await driver.quit();
{{< /tab >}}
{{< tab header="Kotlin" >}}
val driver = ChromeDriver()
driver.get("https://selenium.dev")
driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}

To generate the above tabs, this is what you need to write.
Note that the tabpane includes `langEqualsHeader=true`.
This auto-formats the code in each tab to match the header name.

    {{</* tabpane langEqualsHeader=true */>}}
      {{</* tab header="Java" */>}}
        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.dev");
        driver.quit();
      {{</* /tab */>}}
      {{</* tab header="Python" */>}}
        driver = webdriver.Chrome()
        driver.get("http://selenium.dev")
        driver.quit()
      {{</* /tab */>}}
      {{</* tab header="CSharp" */>}}
        var driver = new ChromeDriver();
        driver.Navigate().GoToUrl("https://selenium.dev");
        driver.Quit();
      {{</* /tab */>}}
      {{</* tab header="Ruby" */>}}
        driver = Selenium::WebDriver.for :chrome
        driver.get 'https://selenium.dev'
        driver.quit
      {{</* /tab */>}}
      {{</* tab header="JavaScript" */>}}
        let driver = await new Builder().forBrowser('chrome').build();
        await driver.get('https://selenium.dev');
        await driver.quit();
      {{</* /tab */>}}
      {{</* tab header="Kotlin" */>}}
        val driver = ChromeDriver()
        driver.get("https://selenium.dev")
        driver.quit()
      }
      {{</* /tab */>}}
    {{</* /tabpane */>}}

### Disabling Code Block

If you want your example to include both text and code, you
need to disable the default of everything being put in a code block

Maybe you want something like this:

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
1. Start the driver
  ```java
    WebDriver driver = new ChromeDriver();
  ```
2. Navigate to a page
  ```java
  driver.get("https://selenium.dev");
  ```
3. Quit the driver
  ```java
  driver.quit();
  ``` 
{{< /tab >}}
{{< /tabpane >}}

For this you need to use `disableCodeBlock=true` instead of `langEqualsHeader=true`

You need to specify which parts are code and which are not yourself now, like this:

    {{</* tabpane disableCodeBlock=true */>}}
    {{</* tab header="Java" */>}}
    1. Start the driver
      ```java
        WebDriver driver = new ChromeDriver();
      ```
    2. Navigate to a page
      ```java
      driver.get("https://selenium.dev");
      ```
    3. Quit the driver
      ```java
      driver.quit();
      ``` 
    {{</* /tab */>}}
    < ... >
    {{</* /tabpane */>}}

### Code Comments

Minimize code comments because they are difficult to translate.
Further, try to avoid over-explaining each line of code unless it is
directly related to the page you are writing.
