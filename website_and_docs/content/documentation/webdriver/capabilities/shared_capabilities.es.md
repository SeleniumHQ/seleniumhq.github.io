---
title: "Shared capabilities"
linkTitle: "Shared capabilities"
weight: 1
aliases: ["/documentation/es/driver_idiosyncrasies/shared_capabilities/"]
---

Con el fin de crear una nueva sesión del _WebDriver_ de _Selenium_,
la parte local debe proveer las capacidades(_capabilities_) básicas a la parte remota.
La parte remota usa el mismo conjunto de capacidades para crear una sesión y
describir las funcionalidades de la sesión actual.

El _WebDriver_ proporciona unas capacidades que cada parte remota debe apoyar en 
su implementación.
Las siguientes capacidades son las que el _WebDriver_ soporta:

## browserName:

Esta capacidad es usada para fijar el `browserName`(nombre del navegador) para 
una sesión dada.
Si el navegador especificado no esta instado en la parte remota, la creación
la sesión fallará.

## browserVersion: 

Esta capacidad es opcional, es usada para fijar la versión disponible del
navegador en la parte remota.
Por ejemplo, si preguntamos por una versión 75 de Chrome en un sistema que solo
tiene instalada la versión 80 instalada, la creación de la sesión fallará.

## pageLoadStrategy:

Cuando navegamos a una nueva pagina vía URL, por defecto _Selenium_ esperará
hasta que la pagina este cargada completamente antes de responder.
Esto funciona bien para principiantes, pero puede causar largos tiempos de espera
en paginas que cargan una gran cantidad de recursos externos. Usando alguna de 
las estrategias que no están especificadas por defecto puedes hacer que tus tests
se ejecuten mas rápidamente pero también podrías llegar a introducir problemas 
de fiabilidad donde los elementos de la pagina cambian de posición según
estos elementos van cargando.

La estrategia de carga de las paginas consulta el atributo 
[document.readyState](//developer.mozilla.org/es/docs/Web/API/Document/readyState) 
como se describe en la tabla siguiente:

|  Estrategia | Estado Atributo Ready | Notas |
| -------- | ----------- | ----- |
| normal | complete | Usado por defecto, espera a que todos los recursos se descarguen |
| eager | interactive | Acceso al DOM esta listo, pero los otros recursos como imágenes pueden estar cargando |
| none | Any | No bloquea al WebDriver en absoluto |

## platformName

Esto es usado para identificar el sistema operativo en la parte
remota, buscar la capacidad `platformName` devuelve el sistema operativo.
 
En proveedores basados en la nube, fijar la capacidad `platformName` permite
definir el sistema operativo de la parte remota. 

## acceptInsecureCerts

Esta capacidad comprueba si un certificado TLS ha vencido o es invalido esta
siendo usado para navegar durante una sesión.

Si esta capacidad esta fijada a `false`, un 
[error de certificado inseguro](//developer.mozilla.org/es/docs/Web/WebDriver/Errors/InsecureCertificate)
sera devuelto cuando la navegación encuentre cualquier dominio con problemas
de certificado. Si se fija a `true`, los certificados inválidos serán confiados
por el navegador

Todos los certificados auto-firmados serán confiables por esta capacidad por defecto.
Una vez.
Una vez fijado la capacidad `acceptInsecureCerts` tendrá efecto durante la sesión
entera.

## Timeouts de sesión

Una sesión del WebDriver es impuesta con un cierto intervalo de _timeout_ de sesión
durante el cual el usuario puede controlar el comportamiento de ejecutar scripts
o recuperar información del navegador.

Cada _timeout_ de sesión es configurado con una combinación diferente de timeouts
como se describe a continuación:

### Timeout de script (Script Timeout):
Especifica cuando interrumpir un script en ejecución en el contexto actual del
navegador. Por defecto esta configurado a **30,000** cuando una nueva sesión es
creada por el WebDriver.

### Timeout de tiempo de carga (Page Load Timeout):
Especifica el intervalo de tiempo en el cual una pagina de web necesita ser 
cargada en el contexto actual del navegador.
El _timeout_ por defecto es de **300,000** para las nuevas sesiones creadas por 
el WebDriver.
Si el tiempo de carga excede el limite marcado, el script será parado por una 
excepción del tipo _TimeoutException_.

### Timeout de esperas implicitas (Implicit Wait Timeout):
Esto especifica el tiempo de espera para la estrategia de localización de 
elementos implicita. Por defecto el _timeout_ es de **0**, este es impuesto cuando
se crea una nueva sesión a través del WebDriver.

## Gestionar el comportamiento de las popups Prompt (unhandledPromptBehavior)

Especifica la forma en la que el usuario puede manejar las popups `prompt` 
(`user prompt handler`) en la sesión actual.
El valor predeterminado es **dismiss and notify state**

### Manejo de las popups Prompt (User Prompt Handler)
Esto define que acción se debe tomar cuando aparece una ventana `prompt` en la
parte remota. Es definida por la capacidad `unhandledPromptBehavior` y tiene
los siguientes estados:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

## setWindowRect

This command alters the size and position of the current 
browsing context window. This command acts as setter 
to `getWindowRect` command which accepts **width**, **height**,
**x**, **y** as _optional_ arguments.

During automation, the current browsing context will be associated 
with window states, which describe the visibility 
of the browser window. The window states are

* maximized
* minimized
* normal
* fullscreen

Setting _Width_ or _Height_ does not guaranteed that the resulting 
window size will exactly match that which was requested. This is because 
some drivers may not be able to resize in single-pixel increments.
Due to this, fetching the window state/details by `getWindowRect` 
may not match the values set in the browser.

## strictFileInteractability

This new capability indicates if strict interactability checks 
should be applied to _input type=file_ elements. As strict interactability 
checks are off by default, there is a change in behaviour 
when using _Element Send Keys_ with hidden file upload controls.
