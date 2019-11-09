---
title: "Quick tour"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

Selenium is not just one tool or API
but it composes many tools.

## WebDriver

_[WebDriver]({{< ref "/webdriver/_index.md" >}})_ is also known as Selenium 2.
If you are beginning with desktop website test automation then you
are going to be using WebDriver APIs. WebDriver uses browser
automation APIs provided by browser vendors to control browser and
run tests. This is as if a real user is operating the browser. Since
WebDriver does not require its API to be compiled with application
code, it is not intrusive in nature. Hence, you are testing the
same application which you push live.


## IDE

_[IDE](https://www.seleniumhq.org/selenium-ide)_ is a
Firefox plugin which can be used to record test steps in Firefox itself.
Selenium IDE can be used to generate _quick and dirty_
 test code in a variety of programming languages (i.e. C#,
Java, Python, and Ruby). Given the maintainability of code generated
through Selenium IDE, it is not recommended to use it for anything
more than getting acquainted with element locators or generating
_throw away code_. We're sure that once you get used to the
WebDriver API, you will never use Selenium IDE.


## Grid

Soon after development of WebDriver tests, you may face a need of
running your tests on multiple browser and operating system combinations.
This is where _[Grid]({{< ref "/grid/_index.md" >}})_ comes to the rescue.
