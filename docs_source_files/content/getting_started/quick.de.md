---
title: "Kurze Einführung"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> Diese Seite wird von Englisch 
auf Deutsch übersetzt. Sprichst Du Deutsch? Hilf uns die Seite 
zu übersetzen indem Du uns einen Pull Reqeust schickst!
 {{% /notice %}}
Selenium is not just one tool or API
but it composes many tools.

## WebDriver

If you are beginning with desktop website test automation then you
are going to be using WebDriver APIs. _[WebDriver]({{< ref "/webdriver/_index.md" >}})_ 
uses browser automation APIs provided by browser vendors to control browser and
run tests. This is as if a real user is operating the browser. Since
WebDriver does not require its API to be compiled with application
code, it is not intrusive in nature. Hence, you are testing the
same application which you push live.


## IDE

_[IDE](https://selenium.dev/selenium-ide)_ (Integrated Development Environment) 
is the tool you use to develop your Selenium test cases. It’s an easy-to-use Chrome 
and Firefox extension and is generally the most efficient way to develop 
test cases. It records the users actions in the browser for you, using 
existing Selenium commands, with parameters defined by the context of 
that element. This is not only a time-saver, but also an excellent way 
of learning Selenium script syntax.

## Grid

Soon after development of WebDriver tests, you may face a need of
running your tests on multiple browser and operating system combinations.
This is where _[Grid]({{< ref "/grid/_index.md" >}})_ comes to the rescue.
