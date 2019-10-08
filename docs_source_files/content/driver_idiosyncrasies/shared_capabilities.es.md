---
title: "Capacidades compartidas"
weight: 1
---


## Estrategia de carga de la página (_pageLoadStrategy_)

Al navegar a una nueva página a través de URL, por defecto Selenium esperará hasta que la página se haya cargado completamente antes de responder. Esto funciona bien para principiantes, pero puede causar largos tiempos de espera en páginas que cargan una gran cantidad de recursos de terceros. El uso de una estrategia no predeterminada puede hacer que la ejecución de la prueba sea más rápida en casos como este, pero también puede introducir inestabilidad donde los elementos en la página cambian de posición a medida que los elementos se cargan y cambian de tamaño.

La estrategia de carga de la página consulta el [document.readyState](// developer.mozilla.org/en-US/docs/Web/API/Document/readyState) como se describe en la tabla a continuación:

| Estrategia | Ready State | Notas |
| -------- | ----------- | ----- |
| normal | complete | Usado por defecto, espera a que se descarguen todos los recursos |
| eager | interactive | El acceso DOM está listo, pero otros recursos como las imágenes aún pueden estar cargando |
| none | Any | No bloquea WebDriver en absoluto |

