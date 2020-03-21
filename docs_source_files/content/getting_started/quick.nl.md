---
title: "Korte uitleg"
weight: 1
---

Selenium is niet enkel een instrument of API,
het bestaat uit verschillende instrumenten.

## WebDriver

Als je begint met het automatiseren van websites, dan zul je de WebDriver
API's gebruiken. _[WebDriver]({{< ref "/webdriver/_index.nl.md" >}})_ 
gebruikt automatisatie API's die door de browsers zelf
worden aangeboden. Laatstgenoemden worden gebruikt om de browser aan te sturen. Op die 
manier word een echte gebruiker gesimuleerd.WebDriver staat los van de applicatieve 
code en is daarom niet-intrusief. Zo test je dezelfde applicatie als diegene die live staat.

## IDE

_[IDE](https://selenium.dev/selenium-ide)_ (Integrated Development Environment) 
is the tool you use to develop your Selenium test cases. It’s an easy-to-use Chrome 
and Firefox extension and is generally the most efficient way to develop 
test cases. It records the users actions in the browser for you, using 
existing Selenium commands, with parameters defined by the context of 
that element. This is not only a time-saver, but also an excellent way 
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