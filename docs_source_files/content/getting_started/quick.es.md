---
title: "Guía rápida"
weight: 1
---


Selenium no es solo una herramienta o API, sino que compone muchas herramientas.


## WebDriver

Si está comenzando con la automatización de pruebas de sitios web de escritorio, 
entonces va a utilizar las API de _WebDriver_. _[WebDriver]({{< ref "/webdriver/_index.md" >}})_ 
utiliza las API de automatización 
del navegador proporcionadas por los desarrolladores de los navegadores para controlar el 
navegador y ejecutar pruebas. Esto es como si un usuario real estuviera manipulando el navegador. 
Dado que el _WebDriver_ no requiere que su API se compile con el código de la aplicación que va 
a probar, no es de naturaleza intrusiva. Por lo tanto, está probando la misma aplicación que está en vivo.


## IDE

_[IDE](https://selenium.dev/selenium-ide)_ (_Integrated Development Environment_) 
es la herramienta que usas para desarrollar tus casos de prueba con Selenium.
Es una extensión para Chrome y Firefox muy sencilla de usar y es generalmente la 
forma mas eficiente de desarrollar casos de prueba. Esta graba las acciones del
usuario en el navegador, usando los comandos existentes en Selenium, con parámetros
definidos por el contexto de cada elemento. No solo sirve para ahorrar tiempo sino
que también es una forma excelente de aprender la sintaxis de scripts de Selenium.

## Grid

_Selenium Grid_ te permite ejecutar casos de prueba en diferentes maquinas a través
de diferentes plataformas. La gestión que desencadena las ejecuciones de los
casos de prueba se realiza en la parte local, y cuando los casos de prueba se
hayan disparado, automáticamente serán ejecutados en la parte remota.

Poco después del desarrollo de las pruebas de _WebDriver_, es posible que deba ejecutar sus 
pruebas en múltiples combinaciones de navegador y sistema operativo. Aquí es donde 
_[Grid]({{< ref "/grid/_index.md" >}})_ viene al rescate.