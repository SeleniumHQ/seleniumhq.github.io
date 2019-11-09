---
title: "Guía rápida"
weight: 1
---


Selenium no es solo una herramienta o API, sino que compone muchas herramientas.


## WebDriver

_[WebDriver]({{< ref "/webdriver/_index.md" >}})_ también se conoce como Selenium 2. Si está comenzando con la automatización de pruebas de sitios web de escritorio, entonces va a utilizar las API de WebDriver. WebDriver utiliza las API de automatización del navegador proporcionadas por los desarrolladores de los navegadores para controlar el navegador y ejecutar pruebas. Esto es como si un usuario real estuviera manipulando el navegador. Dado que el WebDriver no requiere que su API se compile con el código de la aplicación que va a probar, no es de naturaleza intrusiva. Por lo tanto, está probando la misma aplicación que está en vivo.


## IDE

_[IDE](https://www.seleniumhq.org/selenium-ide)_ es un complemento de Firefox que se puede utilizar para grabar los pasos de prueba en el propio Firefox. Selenium IDE se puede utilizar para generar código de prueba rápido y sucio (_quick and dirty_) en una variedad de lenguajes de programación (es decir, C #, Java, Python y Ruby).
Dada la mantenibilidad del código generado a través de Selenium IDE, no se recomienda usarlo para nada más que familiarizarse con los localizadores (_locators_) de elementos o generar código descartable (_throw away code_). Estamos seguros de que una vez que se acostumbre a la API WebDriver, nunca usará Selenium IDE.


## Grid

Poco después del desarrollo de las pruebas de WebDriver, es posible que deba ejecutar sus pruebas en múltiples combinaciones de navegador y sistema operativo. Aquí es donde _[Grid]({{< ref "/grid/_index.md" >}})_ viene al rescate.


