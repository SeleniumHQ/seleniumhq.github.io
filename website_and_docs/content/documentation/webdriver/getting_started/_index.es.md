---
title: "Empezando"
linkTitle: "Empezando"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
aliases: 
        [
            "/documentation/es/getting_started/", 
            "/documentation/es/getting_started/quick/",
            "/documentation/es/selenium_installation/",
            "/documentation/es/getting_started_with_webdriver/",
            "/es/documentation/getting_started/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium permite la automatización de todos los principales
navegadores del mercado mediante el uso de _WebDriver_.
WebDriver es una API y un protocolo que define una interfaz de idioma neutral
para controlar el comportamiento de los navegadores web.
Cada navegador está respaldado por una implementación
específica de WebDriver, llamada *controlador*.
El controlador es el componente responsable de delegar en el navegador,
y maneja la comunicación hacia y desde Selenium y el navegador.

Esta separación es parte de un esfuerzo consciente para hacer
que los proveedores de navegadores asuman la responsabilidad de la
implementación para sus navegadores.
Selenium utiliza estos controladores de terceros cuando es posible,
pero también proporciona sus propios controladores mantenidos por el proyecto
para los casos en que esto no es una realidad.

El framework de Selenium unifica todas estas piezas
a través de una interfaz orientada al usuario que habilita que
los diferentes backends de los navegadores sean utilizados de forma 
transparente, permitiendo la automatización cruzada entre navegadores
y plataformas diferentes.

La configuración de Selenium es bastante diferente de la configuración
de otras herramientas comerciales.
Para usar Selenium en tu proyecto de automatización,
necesitas instalar las librerías de enlace de tu lenguaje de preferencia.
Además necesitarás los binarios de WebDriver para los navegadores 
en los que deseas automatizar y ejecutar pruebas.

Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "/install_selenium_library" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "/install_browser_drivers" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/es/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.
