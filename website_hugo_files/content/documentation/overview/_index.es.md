---
title: "Overview"
linkTitle: "Overview"
weight: 1
description: >
  Is Selenium for you? See an overview of the different project components.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium no es solo una herramienta o API, sino que compone muchas herramientas.


## WebDriver

Si está comenzando con la automatización de pruebas de sitios web de escritorio, 
entonces va a utilizar las API de [WebDriver]({{< ref "/webdriver.md" >}})
utiliza las API de automatización del navegador proporcionadas por los desarrolladores 
de los navegadores para controlar el navegador y ejecutar pruebas. Esto es como si un 
usuario real estuviera manipulando el navegador. Dado que el _WebDriver_ no requiere 
que su API se compile con el código de la aplicación que va a probar, no es de naturaleza 
intrusiva. Por lo tanto, está probando la misma aplicación que está en vivo.


## IDE

[IDE](//selenium.dev/selenium-ide) (Integrated Development Environment) 
es la herramienta que usas para desarrollar tus casos de prueba con Selenium.
Es una extensión para Chrome y Firefox muy sencilla de usar y es generalmente la 
forma mas eficiente de desarrollar casos de prueba. Esta graba las acciones del
usuario en el navegador, usando los comandos existentes en Selenium, con parámetros
definidos por el contexto de cada elemento. No solo sirve para ahorrar tiempo sino
que también es una forma excelente de aprender la sintaxis de scripts de Selenium.

## Grid

Selenium Grid te permite ejecutar casos de prueba en diferentes maquinas a través
de diferentes plataformas. La gestión que desencadena las ejecuciones de los
casos de prueba se realiza en la parte local, y cuando los casos de prueba se
hayan disparado, automáticamente serán ejecutados en la parte remota.

Poco después del desarrollo de las pruebas de WebDriver, es posible que deba ejecutar sus 
pruebas en múltiples combinaciones de navegador y sistema operativo. Aquí es donde 
[Grid]({{</* ref "/grid/_index.md" */>}}) viene al rescate.