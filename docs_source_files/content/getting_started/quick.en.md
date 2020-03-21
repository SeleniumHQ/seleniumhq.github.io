---
title: "Quick tour"
weight: 1
---

Selenium is not just one tool or API
but it composes many tools.

## WebDriver

If you are beginning with desktop website or mobile website test automation, then you
are going to be using WebDriver APIs. _[WebDriver]({{< ref "/webdriver/_index.md" >}})_ 
uses browser automation APIs provided by browser vendors to control browser and
run tests. This is as if a real user is operating the browser. Since
WebDriver does not require its API to be compiled with application
code, it is not intrusive. Hence, you are testing the
same application which you push live.

## IDE

_[IDE](https://selenium.dev/selenium-ide)_ (Integrated Development Environment) 
is the tool you use to develop your Selenium test cases. It’s an easy-to-use Chrome 
and Firefox extension and is generally the most efficient way to develop 
test cases. It records the users' actions in the browser for you, using 
existing Selenium commands, with parameters defined by the context of 
that element. This is not only a time-saver but also an excellent way 
of learning Selenium script syntax.

## Grid
Selenium Grid allows you to run test cases in different 
machines across different platforms. The control of 
triggering the test cases is on the local end, and 
when the test cases are triggered, they are automatically 
executed by the remote end.

After the development of the WebDriver tests, you may face 
the need of running your tests on multiple browser and 
operating system combinations.
This is where _[Grid]({{< ref "/grid/_index.md" >}})_ comes into the picture.
