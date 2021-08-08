---
title: "A Deeper Look"
linkTitle: "A Deeper Look"
weight: 2
description: >
  Seleniumはブラウザー自動化を可能にし、それを支えるツール群とライブラリー群のプロジェクトです。
aliases: ["/documentation/ja/introduction/the_selenium_project_and_tools/"]  
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

### Selenium controla los navegadores web

_Selenium_ significa muchas cosas pero en su núcleo, es un conjunto
de herramientas para la automatización de navegadores web que utiliza
las mejores técnicas disponibles para controlar remotamente las
instancias de los navegadores y emular la interacción del usuario con
el navegador.

Permite a los usuarios simular interacciones básicas realizadas por
los usuarios finales; insertando texto en los campos, seleccionando
valores de menús desplegables y casillas de verificación, y haciendo
clics en los enlaces de los documentos. También provee muchos otros
controles tales como el movimiento del mouse, la ejecución arbitraria
de JavaScript, y mucho más.

A pesar de que es usado principalmente para pruebas de front-end de
sitios webs, Selenium es en esencia una _librería_ de agente de
usuario para el navegador. Las interfaces son ubicuas a su
aplicación, lo que fomenta la composición con otras librerías para
adaptarse a su propósito.


### Una interfaz para gobernarlos a todos

Uno de los principios fundamentales del proyecto es permitir una
interfaz común para todas las tecnologías de los (principales)
navegadores. Los navegadores web son aplicaciones increíblemente
complejas y de mucha ingeniería, realizando operaciones completamente
diferentes pero que usualmente se ven iguales al hacerlo. Aunque el
texto se presente con las mismas fuentes, las imágenes se muestren en
el mismo lugar y los enlaces te llevan al mismo destino. Lo que
sucede por debajo es tan diferente como la noche y el día. Selenium
abstrae estas diferencias, ocultando sus detalles y complejidades a
la persona que escribe el código. Esto le permite escribir varias
líneas de código para realizar un flujo de trabajo complicado, pero
estas mismas líneas se ejecutarán en Firefox, Internet Explorer,
Chrome y los demás navegadores compatibles.


### Herramientas y soporte

El diseño minimalista de Selenium le da la versatilidad para que se
pueda incluir como un componente en otras aplicaciones. La
infraestructura proporcionada debajo del catálogo de Selenium te da
las herramientas para que puedas ensamblar tu [grid de
navegadores]({{< ref "/grid.md" >}}) de modo que tus pruebas
se puedan ejecutar en diferentes navegadores a través de diferente
sistemas operativos y plataformas.

Imagina un banco de computadores en tu sala de servidores o en un
centro de datos, todos ejecutando navegadores al mismo tiempo e
interactuando con los enlaces en tu sitio web, formularios, y
tablas&mdash;probando tu aplicación 24 horas al día. A través de la
sencilla interfaz de programación proporcionada para los lenguajes
más comunes, estas pruebas se ejecutarán incansablemente en paralelo,
reportando cuando ocurran errores.

Es un objetivo ayudar a que esto sea una realidad para ti,
proporcionando a los usuarios herramientas y documentación para
controlar no solo los navegadores pero también para facilitar la
escalabilidad e implementación de tales grids.


### Quien utiliza Selenium

Muchas de las empresas más importantes del mundo han adoptado
Selenium para sus pruebas basadas en navegador, a menudo reemplazando
esfuerzos de años que involucran otras herramientas propietarias. A
medida que ha crecido en popularidad, también se han multiplicado sus
requisitos y desafíos.

A medida que la web se vuelve más complicada y se agregan nuevas
tecnologías a los sitios web, la misión de este proyecto es
mantenerse al día con ellos siempre que sea posible. Siendo un
proyecto de código abierto, este apoyo se sustenta a través de la
donación generosa de tiempo de muchos voluntarios, cada uno de los
cuales tiene un "trabajo diurno".

Otra misión del proyecto es alentar a más voluntarios a participar en
este esfuerzo, y construir una comunidad fuerte para que el proyecto
pueda seguir el ritmo de las tecnologías emergentes y seguir siendo
una plataforma dominante para la automatización de pruebas
funcionales.
