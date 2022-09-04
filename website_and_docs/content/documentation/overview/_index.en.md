---
title: "Selenium Overview"
linkTitle: "Overview"
weight: 2
description: >
  Is Selenium for you? See an overview of the different project components.
aliases: ["/documentation/en/introduction/"]
---


Selenium is not just one tool or API
but it composes many tools.

## WebDriver

If you are beginning with desktop website or mobile website test automation, then you
are going to be using WebDriver APIs. [WebDriver](/documentation/webdriver)
uses browser automation APIs provided by browser vendors to control the browser and
run tests. This is as if a real user is operating the browser. Since
WebDriver does not require its API to be compiled with application
code; It is not intrusive. Hence, you are testing the
same application which you push live.


## IDE

[IDE](//selenium.dev/selenium-ide) (Integrated Development Environment) 
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
the need to run your tests on multiple browsers and 
operating system combinations.
This is where [Grid](/documentation/grid) comes into the picture.
