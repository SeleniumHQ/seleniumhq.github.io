---
title: "Descarga de archivos"
weight: 2
---

Mientras que es posible empezar una descarga haciendo clic en el enlace, con el
navegador que este siendo controlado por Selenium, el API no expone el progreso 
de la descarga, haciéndolo poco ideal para probar la descarga de archivos.
Esto es debido a que descargar archivos no es considerado un aspecto importante 
de la emulación de las interacciones de los usuarios con las plataformas web.
En lugar de ello, se recomienda encontrar el enlace con Selenium (y cualquier
Cookie requerida) y pasarselo a una librería que permita hacer peticiones HTTP 
como [libcurl](//curl.haxx.se/libcurl/).