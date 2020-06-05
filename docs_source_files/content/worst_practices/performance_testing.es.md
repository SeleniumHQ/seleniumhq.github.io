---
title: "Pruebas de rendimiento"
weight: 6
---

Las pruebas de rendimiento usando Selenium y el WebDriver generalmente no son
aconsejables.
No porque Selenium sea incapaz de ello si no porque no esta optimizado para ello
y es poco probable que se obtengan buenos resultados.

Puede parecer ideal realizar pruebas de rendimiento desde el contexto de un 
usuario pero una suite de tests del WebDriver esta sujeta a demasiados puntos
fragiles tanto internamente como externamente los cuales están fuera de nuestro
control.
Por ejemplo, la velocidad de puesta en marcha de los navegadores, la velocidad de
los servidores HTTP, las respuestas servidores externos que alojan JavaScript o 
CSS, y la propia penalización debida a la instrumentación de la propia 
implementación del WebDriver.
Cualquier cambio en alguno de estos puntos causará una variación en los 
resultados.
Es difícil separar la diferencia entre el rendimiento de tu sitio web y el
rendimiento de tus recursos externos, y también es difícil de definir el impacto
negativo en el rendimiento debido al uso del WebDriver en el navegador 
especialmente si estas inyectando scripts.

Otra de las principales atracciones es el "ahorrarse tiempo" llevando a cabo las 
pruebas funcionales y de rendimiento al mismo tiempo.
Sin embargo los tests funcionales y de rendimiento tienen objetivos totalmente
opuestos.
A la hora de probar funcionalidades, debemos de ser pacientes y esperar a que
finalice la carga esto enturbiará los resultados de las pruebas de rendimiento y 
viceversa.

Para mejorar el rendimiento de tu pagina web, necesitarás ser capaz de analizar
el rendimiento general, independientemente de las diferencias entre entornos, 
de identificar malas practicas en el código, de descomponer el rendimiento de de 
los diferentes recursos (ej. CSS en JavaScript) con el fin de saber donde mejorar.
Existen herramientas de pruebas de rendimiento disponibles que ya pueden hacer 
este trabajo y las cuales pueden proveer de informes y análisis que incluso
pueden hacer sugerencias de mejora.

Este es un ejemplo de un paquete (de código libre) que se puede usar, 
[JMeter](//jmeter.apache.org/).
