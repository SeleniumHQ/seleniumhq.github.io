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


## Remote Control

[_Remote Control_](https://www.seleniumhq.org/docs/05_selenium_rc.jsp)
is also known as Selenium 1.
Selenium RC was the most prominent Selenium tool
before the advent of Selenium WebDriver. Selenium RC would use a
proxy server and inject JavaScript into a browser to be able to control
it. Given the intrusive nature Selenium RC had on a browser,
you could never be sure if what you were testing was the same as
the application you wanted to push live. Selenium 2 APIs yet contain Selenium RC APIs but
Selenium 3 would completely get rid of Selenium RC APIs. If you are
still using Selenium RC then you must
[_migrate_](https://www.seleniumhq.org/docs/03_webdriver.jsp#migrating-from-selenium-1-0)
to Selenium WebDriver.


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


## HTML Runner

This tool allows you to run Test Suites from the command
line. Test Suites are HTML exports from Selenium IDE or compatible
tools. _[HTML Runner]({{< ref "/getting_started/html-runner.ja.md" >}})_
