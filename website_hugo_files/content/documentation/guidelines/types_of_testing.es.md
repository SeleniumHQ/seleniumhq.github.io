---
title: "Tipos de pruebas"
linkTitle: "Tipos de pruebas"
weight: 2
---

### Pruebas de aceptación 
Este tipo de prueba se realiza para determinar si una funcionalidad
o un sistema cumple con las expectativas y requerimientos del cliente.
Este tipo de pruebas generalmente implican la cooperación o retroalimentación del cliente,
siendo una actividad de validación que responde la pregunta: 
>¿Estamos construyendo el producto **_correcto_**?.

Para aplicaciones web, la automatización de esta prueba se puede 
hacer directamente con Selenium simulando el comportamiento esperado 
del usuario. Esta simulación podría hacerse mediante grabación/
reproducción o mediante los diferentes lenguajes soportados como se explica 
en esta documentación. Nota: Las pruebas de aceptación son un subtipo 
de **_pruebas funcionales_**, a lo que algunas personas también 
podrían referirse.  

### Pruebas funcionales
Este tipo de prueba se realiza para determinar si una
funcionalidad o sistema funciona correctamente y sin problemas. 
Se comprueba el sistema en diferentes niveles para garantizar que
todos los escenarios están cubiertos y que el sistema hace _lo que_
se supone que debe de hacer. Es una actividad de verificación que
responde la pregunta:
>¿Estamos construyendo el producto **_correctamente?_**.

Para aplicaciones web, la automatización de esta prueba puede ser 
hecha directamente con Selenium simulando los retornos esperados. 
Esta simulación podría hacerse mediante grabación/reproducción o 
mediante los diferentes lenguajes soportados como se explica en esta 
documentación. 

### Pruebas de rendimiento 
Como su nombre indica, se realizan pruebas 
de rendimiento para medir qué tan bien está funcionando una 
aplicación. 

Hay dos subtipos principales para las pruebas de rendimiento: 

#### Pruebas de carga 
La prueba de carga se realiza para verificar qué 
tan bien la aplicación funciona bajo diferentes cargas definidas (
generalmente un número particular de usuarios conectados a la vez). 

#### Pruebas de estrés 
Se realizan pruebas de estrés para verificar 
qué tan bien la aplicación funciona bajo estrés (o por encima de la 
carga máxima soportada). 

En general, las pruebas de rendimiento se realizan ejecutando algunas 
pruebas de Selenium que simulan diferentes usuarios golpeando
una función particular en la aplicación web y obteniendo algunas 
medidas significativas. 

En general, esto lo hacen otras herramientas que obtienen las 
métricas. Una de esas herramientas es **_JMeter_**. 

Para una aplicación web, los detalles a medir incluyen rendimiento, 
latencia, pérdida de datos, tiempos de carga de componentes 
individuales... 

Nota 1: Todos los navegadores tienen una pestaña de rendimiento en su 
sección de herramientas para desarrolladores (accesible presionando 
F12) 

Nota 2: es un subtipo de **_pruebas no funcionales_** ya que esto 
generalmente se mide por sistema y no por función/funcionalidad. 

### Pruebas de regresión 
Esta prueba generalmente se realiza después 
de un cambio, corrección o adición de funcionalidad. 

Para garantizar que el cambio no ha roto ninguna de las funcionalidades 
existentes, algunas pruebas ya ejecutadas se ejecutan nuevamente.  
El conjunto de pruebas ejecutadas nuevamente puede ser total o 
parcial, y puede incluir varios tipos diferentes, dependiendo del 
equipo de desarrollo y la aplicación.  

### Desarrollo guiado por pruebas (TDD) 
En lugar de un tipo de prueba _per se_, TDD es una
metodología iterativa de desarrollo en la que las pruebas guían el 
diseño de una funcionalidad. 

Cada ciclo comienza creando un conjunto de pruebas unitarias que la 
funcionalidad debería pasar finalmente (deberían fallar la primera 
vez que se ejecuta). 

Después de esto, se lleva a cabo el desarrollo para pasar las 
pruebas. Las pruebas se ejecutan nuevamente, comenzando otro ciclo y 
este proceso continúa hasta que todas las pruebas pasen. 

El objetivo es acelerar el desarrollo de una aplicación basado en el 
hecho de que los defectos son menos costosos cuanto más antes se 
encuentran. 

### Desarrollo guiado por comportamiento (BDD) 
BDD es también una metodología de desarrollo iterativo basado en 
el TDD anterior, en el que el objetivo es involucrar todas las 
partes en el desarrollo de una aplicación. 

Cada ciclo comienza creando algunas especificaciones (que deberían 
fallar). Luego creando las pruebas unitarias fallidas (que también deberían 
fallar) y luego hacer el desarrollo. 

Este ciclo se repite hasta que pasan todos los tipos de pruebas. 

Para realizar esto, se usa un lenguaje de especificación. Debe ser 
simple y entendible por todas las partes, estándar y explícito. La 
mayoría de las herramientas usan **_Gherkin_** como este 
lenguaje. 

El objetivo es poder detectar aún más errores que TDD, apuntando 
también a posibles errores de aceptación y a facilitar la comunicación 
entre las partes. 

Actualmente hay un conjunto de herramientas disponibles para 
escribir las especificaciones y relacionarlas con funciones de 
código, como **Cucumber** o **_SpecFlow._** 

Un conjunto de herramientas se han construido encima de Selenium para realizar 
este proceso aún más rápido al transformar directamente las 
especificaciones de BDD en código ejecutable. Algunas de estas son
 **_JBehave, Capybara y Robot Framework_**.