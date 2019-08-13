---
title: "El proyecto Selenium y herramientas"
weight: 1
---

### Selenium controla navegadores web

_Selenium_ es muchas cosas, pero en esencia es un conjunto de herramientas para la automatización del navegador web que utiliza las mejores técnicas disponibles para controlar de forma remota las instancias del navegador y emular la interacción de un usuario con el navegador.

Permite a los _testers_ simular actividades comunes realizadas por los usuarios finales; ingresar texto en los campos, seleccionar valores desplegables y casillas de verificación, y hacer clic en los enlaces de los documentos. También proporciona muchos otros controles, como el movimiento del mouse, la ejecución arbitraria de JavaScript y mucho más.

Aunque se utiliza principalmente para la prueba de front-end de sitios web, Selenium es en esencia una _Librería_ de agente de usuario de navegador. Las interfaces son ubicuas a la aplicación, lo que fomenta la relación con otras librerías para adaptarse a su necesidad.


### Una interfaz para gobernarlos a todos

Uno de los principios rectores del proyecto es admitir una interfaz común para los principales navegadores web.
Los navegadores web son aplicaciones increíblemente complejas y de alta ingeniería, que realizan sus operaciones de formas completamente diferentes, pero que con frecuencia se ven iguales al hacerlo. Aunque el texto se representa en las mismas fuentes, las imágenes se muestran en el mismo lugar y los enlaces lo llevan al mismo destino. Lo que sucede debajo es tan diferente como la noche y el día.
Selenium "resume" estas diferencias, ocultando sus detalles y complejidades a la persona que escribe el código. Esto le permite escribir varias líneas de código para realizar un flujo de trabajo complicado, pero estas mismas líneas se ejecutarán en Firefox, Internet Explorer, Chrome y todos los demás navegadores compatibles.


### Herramientas y soporte

El enfoque de diseño minimalista de Selenium le da versatilidad para ser incluido como componente en aplicaciones más grandes. La infraestructura circundante proporcionada bajo Selenium le brinda las herramientas para armar su propia [_Grid_ de navegadores]({{< ref "/grid/_index.md" >}}) o _grid_ para que las pruebas se puedan ejecutar en diferentes navegadores y múltiples sistemas operativos en una amplia gama de máquinas.

Imagine un banco de computadoras en la sala de servidores o en el centro de datos que activan todos los navegadores al mismo tiempo que hacen clic en los enlaces, formularios y tablas de su sitio, probando su aplicación las 24 horas del día. A través de la interfaz de programación simple provista para los lenguajes más comunes, estas pruebas se ejecutarán incansablemente en paralelo y le informarán cuando ocurran errores.

Es un objetivo ayudar a que esto sea una realidad para usted, proporcionando a los usuarios herramientas y documentación para controlar no solo los navegadores, sino también para facilitar la ejecución a gran escala y la implementación de dichas _Grids_.


### Quién usa Selenium

Muchas de las compañías más importantes del mundo han adoptado Selenium para sus pruebas basadas en navegadores, a menudo reemplazando los esfuerzos de años que involucran otras herramientas propietarias. A medida que ha crecido en popularidad, también se han multiplicado sus requisitos y desafíos.

A medida que la web se vuelve más complicada y se agregan nuevas tecnologías a los sitios web, la misión de este proyecto es mantenerse al día con ellos siempre que sea posible. Al ser un proyecto de código abierto, este apoyo se brinda a través de la generosa donación de tiempo de muchos voluntarios, cada uno de los cuales tiene un "trabajo diario".

Otra misión del proyecto es alentar a más voluntarios a participar en este esfuerzo, y construir una comunidad fuerte para que el proyecto pueda seguir el ritmo de las tecnologías emergentes y seguir siendo una plataforma dominante para la automatización de pruebas funcionales.


### Historia

Cuando Selenium 1 se lanzó en 2004, la necesidad era reducir el tiempo dedicado a verificar manualmente el comportamiento consistente en el front-end de una aplicación web. Se hizo uso de las herramientas disponibles en ese momento y se basó en gran medida en la inyección de JavaScript en la página web bajo prueba para emular la interacción de un usuario. Si bien JavaScript es una buena herramienta para permitirle introspectar las propiedades del DOM y hacer ciertas observaciones del lado del cliente que de otro modo no podría hacer, no tiene la capacidad de replicar de forma natural las interacciones de un usuario como el uso del teclado y ratón.

Desde entonces, Selenium ha crecido y madurado mucho, convirtiéndose en una herramienta ampliamente utilizada por muchas, si no la mayoría, de las organizaciones más grandes del mundo. Selenium ha pasado de ser un kit de herramientas de automatización de pruebas de fabricación casera desarrollado en Thoughtworks para un público especial y un caso de uso específico, a la librería de automatización del navegador _de facto_ en el mundo.

Del mismo modo que Selenium RC hizo uso de las herramientas del mercado disponibles en ese momento, [Selenium WebDriver]({{< ref "/webdriver/_index.md" >}}) impulsa esa tradición al llevar la parte de interacción del navegador al territorio del desarrollador del navegador y pedirles que se responsabilicen de las implementaciones de back-end orientadas al navegador. Recientemente, este trabajo se ha convertido en un proceso de estandarización W3C donde el objetivo es convertir el componente WebDriver en Selenium en la libería de control remoto de uso cumún para las simulaciones de agentes de usuario.

