---
title: "Códigos de respuesta HTTP"
linkTitle: "Códigos de respuesta HTTP"
weight: 3
aliases: ["/documentation/es/worst_practices/http_response_codes/"]
---


Para algunas configuraciones de navegadores en Selenium RC, Selenium actuaba como
proxy entre el navegador y el sitio web que iba a ser automatizado.
Esto significaba que todo el trafico que pasaba a través de Selenium podía ser
capturado o manipulado.
El método `captureNetworkTraffic()` pretendía capturar todo el trafico de red
entre el navegador y el sitio que estaba siendo automatizado, incluyendo los 
códigos de respuestas HTTP.

El WebDriver de Selenium parte de una aproximación completamente diferente respecto
a la automatización de los navegadores, prefiriendo así actuar mas como un usuario
y esto se representa en la forma en la que escribes los tests con el WebDriver.
Para los tests funcionales automatizados comprobar el código de respuesta no es un
detalle particularmente importante en un test fallido, los pasos que se han realizado
lo son mucho mas.


El navegador siempre representará el código de estado de respuesta HTTP, imagina
por ejemplo una pagina de error para los códigos 404 o 500.
Un forma simple de hacer fallar el test cuando encuentras una de estas paginas de
error es validar los títulos de las paginas o algún contenido de confianza (ej.
la etiqueta `<h1>`) después de que se haya cargado cada pagina.
Si estas usando un modelo basado en el patrón _page object_ puedes incluir esta
validación en el constructor de la clase o en puntos similares donde se espere
la carga de una pagina.
En algunas ocasiones el código de respuesta HTTP puede llegar a ser representado
en la pagina de error en el navegador e incluso podrías usar el WebDriver para 
leerlo y facilitar la depuración en tus tests.

Comprobar la pagina web es la linea de trabajo ideal del WebDriver representando
y validando así la vista de los usuarios de los sitios web.

Si aun así necesitases una forma de capturar los códigos de respuesta HTTP una
forma avanzada es replicar el comportamiento de Selenium RC usando un proxy.
El API del WebDriver provee la habilidad de configurar un proxy para un navegador,
existen una gran cantidad de proxies que te permiten programaticamente manipular
los contenidos de las peticiones enviadas y recibidas desde los servidores web.
Usar un proxy te permite decidir como actuar frente a redirecciones.
Ademas, no todos los navegadores hacen que los códigos de respuesta estén 
disponibles para el WebDriver, es por eso que optar por un proxy te permitirá 
disponer de una solución común para todos los navegadores.
