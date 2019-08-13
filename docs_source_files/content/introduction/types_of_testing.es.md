---
title: "Tipos de pruebas"
weight: 3
---


## Prueba de aceptación
Este tipo de prueba se realiza para determinar si la característica de un producto cumple con sus requisitos. Esto generalmente implica la retroalimentación o especificación del cliente.

Para aplicaciones web, la automatización de este tipo de prueba se puede hacer directamente con Selenium simulando el comportamiento esperado del usuario. Esta simulación se puede realizar mediante grabación / reproducción o mediante los diferentes lenguajes de programación admitidos, tal como se explica en esta documentación.
Nota: Las pruebas de aceptación son un subtipo de **_pruebas funcionales_**, a las que algunas personas también pueden referirse.

### Prueba funcional
Este tipo de prueba se realiza para determinar si la característica de un producto funciona correctamente, sin problemas.
             
Esto generalmente incluye: que las pruebas funcionen sin errores (páginas 404, excepciones ...), de manera utilizable (redirecciones correctas), de manera accesible y con sus especificaciones (ver **_pruebas de aceptación_** más arriba).

Para aplicaciones web, la automatización de estas pruebas se puede hacer directamente con Selenium simulando los retornos esperados. Esta simulación podría realizarse mediante grabación / reproducción o mediante los diferentes lenguajes admitidos, tal como se explica en esta documentación.

### Pruebas de rendimiento o desempeño
Como su nombre lo indica, se realizan pruebas de rendimiento para medir el rendimiento de una aplicación.

Hay dos subtipos principales para las pruebas de rendimiento:

#### Prueba de carga
La prueba de carga se realiza para verificar qué tan bien funciona la aplicación bajo diferentes cargas definidas (generalmente un número particular de usuarios conectados a la vez).

#### Pruebas de estrés o esfuerzo
La prueba de esfuerzo se realiza para verificar qué tan bien funciona la aplicación bajo estrés (o por encima de la carga máxima soportada).

En general, las pruebas de rendimiento se realizan mediante la ejecución de una serie de pruebas escritas de Selenium que simulan a diferentes usuarios que acceden a una función particular en la aplicación web y recuperan algunas mediciones significativas.

En general, esto lo hacen otras herramientas que recuperan las métricas. Una de esas herramientas es **_JMeter_**.

Para una aplicación web, los detalles a medir incluyen: rendimiento (_throughput_), latencia, pérdida de datos, tiempos de carga de componentes individuales ...

Nota: Todos los navegadores tienen una pestaña de rendimiento en la sección de herramientas de desarrolladores (accesible presionando F12).

Nota 2: es un subtipo de **_pruebas no funcionales_** ya que generalmente se mide por sistema y no por función / característica.

### Pruebas de regresión
Esta prueba generalmente se realiza después de un cambio, corrección o adición de características.

Para garantizar que el cambio no haya roto ninguna de las funciones existentes, algunas pruebas ya ejecutadas se ejecutan nuevamente.
            
El conjunto de pruebas ejecutadas de nuevo puede ser completo o parcial y puede incluir varios tipos diferentes, según la aplicación y el equipo de desarrollo.

### Desarrollo guiado por pruebas (TDD)
En lugar de un tipo de prueba per se, TDD es una metodología de desarrollo iterativa en la que las pruebas guían el diseño de una característica. Cada ciclo comienza creando un conjunto de pruebas unitarias que la característica debe pasar (que debe fallar la primera vez que se ejecuta).Después de esto, se lleva a cabo el desarrollo para que las pruebas pasen. Las pruebas se ejecutan nuevamente comenzando otro ciclo y este proceso continúa hasta que todas las pruebas hayan pasado.

El objetivo es acelerar el desarrollo de una aplicación basada en el hecho de que los defectos son menos costosos cuanto antes se encuentran.

### Desarrollo impulsado por el comportamiento (BDD)
BDD es también una metodología de desarrollo iterativa basada en el anterior (TDD) en la que el objetivo es involucrar a todas las partes en el desarrollo de una aplicación.

Cada ciclo comienza creando alguna especificación (que debería fallar). Luego se crean las pruebas unitarias fallidas (que también deberían fallar) y luego se crea el desarrollo. Este ciclo se repite hasta que pase todo tipo de pruebas.

Para hacerlo, se utiliza un lenguaje de especificación. Debe ser entendible por todas las partes y simple, estándar y explícito. La mayoría de las herramientas usan **_Gherkin_** como este lenguaje.

El objetivo es ser capaz de detectar incluso más errores que TDD al enfocarse también en posibles errores de aceptación y hacer que la comunicación entre las partes sea más fluida.

Actualmente hay un conjunto de herramientas disponibles para escribir las especificaciones y combinarlas con funciones de código, como **_Cucumber_** o **_SpecFlow_**.

Se ha creado un conjunto de herramientas sobre Selenium para que este proceso sea aún más rápido al transformar directamente las especificaciones de BDD en código ejecutable. Algunos de estos son: **_JBehave, Capybara y Robot Framework_**.

