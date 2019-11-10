---
title: "Guía rápida"
weight: 1
---


Selenium no es solo una herramienta o API, sino que compone muchas herramientas.


## WebDriver

Si está comenzando con la automatización de pruebas de sitios web de escritorio, 
entonces va a utilizar las API de WebDriver. _[WebDriver]({{< ref "/webdriver/_index.md" >}})_ 
utiliza las API de automatización 
del navegador proporcionadas por los desarrolladores de los navegadores para controlar el 
navegador y ejecutar pruebas. Esto es como si un usuario real estuviera manipulando el navegador. 
Dado que el WebDriver no requiere que su API se compile con el código de la aplicación que va 
a probar, no es de naturaleza intrusiva. Por lo tanto, está probando la misma aplicación que está en vivo.


## IDE

_[IDE](https://selenium.dev/selenium-ide)_ (Integrated Development Environment) 
is the tool you use to develop your Selenium test cases. It’s an easy-to-use Chrome 
and Firefox extension and is generally the most efficient way to develop 
test cases. It records the users actions in the browser for you, using 
existing Selenium commands, with parameters defined by the context of 
that element. This is not only a time-saver, but also an excellent way 
of learning Selenium script syntax.



## Grid

Poco después del desarrollo de las pruebas de WebDriver, es posible que deba ejecutar sus 
pruebas en múltiples combinaciones de navegador y sistema operativo. Aquí es donde 
_[Grid]({{< ref "/grid/_index.md" >}})_ viene al rescate.


