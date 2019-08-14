---
title: "Primeros pasos con WebDriver"
chapter: true
weight: 4
---


# Primeros pasos con WebDriver

Selenium admite la automatización de todos los principales navegadores del mercado mediante el uso del _WebDriver_. WebDriver es una API y un protocolo que define una interfaz de lenguaje neutral para controlar el comportamiento de los navegadores web. Cada navegador está respaldado por una implementación específica de WebDriver, llamada *controlador* (o _driver_). El controlador es el componente responsable de manipular el navegador, y maneja la comunicación hacia y desde Selenium y el navegador.

Esta separación es parte de un esfuerzo consciente para que los proveedores de navegadores se hagan responsables de la implementación de sus navegadores. Selenium hace uso de estos controladores de terceros cuando es posible, pero también proporciona sus propios controladores mantenidos por el proyecto para los casos en que esto no pueda ser realizado.

El marco Selenium une todas estas piezas a través de una interfaz orientada al usuario que permite que los diferentes backends del navegador se utilicen de forma transparente, lo que permite la automatización entre navegadores (_cross-browser_) y plataformas cruzadas (_cross-platform_).

Se pueden encontrar más detalles sobre los controladores en [Driver Idiosyncrasies]({{< ref "/driver_idiosyncrasies/_index.md" >}}).

