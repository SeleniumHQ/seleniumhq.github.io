---
title: "Iniciando con WebDriver"
chapter: true
weight: 4
---

# Iniciando con WebDriver

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

Se pueden encontrar más detalles sobre los controladores en
[Idiosincrasias del controlador]({{< ref "/driver_idiosyncrasies/_index.md" >}}).
