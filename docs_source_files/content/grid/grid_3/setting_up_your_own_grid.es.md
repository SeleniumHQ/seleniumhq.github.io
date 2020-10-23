---
title: "Configurando tu propio Grid"
weight: 2
---

Para usar Selenium Grid, necesitas mantener tu propia infraestructura para los
nodos.
Como esto puede suponer un engorro y suponer un gran esfuerzo de tiempo,
muchas organizaciones usan proveedores de IaaS (Infraestructura como servicio) 
como Amazon EC2 y Google Compute para proveer esta infraestructura.

Otras opciones incluyen usar proveedores como Sauce Labs or Testing Bot los 
cuales proveen Selenium Grid como servicio en la nueve.
Ciertamente, también es posible ejecutar los nodos en tu propio hardware.
Esta capitulo abordara en detalle la opción de ejecutar tu propio Grid completo 
con su propia infraestructura de nodos.

## Inicio rápido

Este ejemplo te enseñará como poner en marcha el Grid Hub de Selenium 2 y registrar
un nodo WebDriver y un nodo heredado de Selenium 1 RC.
También te enseñaremos como llamar al Grid desde Java.
El hub y los nodos se muestran aquí ejecutándose en la misma maquina, pero por
supuesto puedes copiar `selenium-server-standalone` en múltiples maquinas.


El paquete de `selenium-server-standalone` incluye el hub, el WebDriver y el servidor
RC heredado necesarios para ejecutar el Grid, _ant_ ya no es necesario.
Puedes descargar `selenium-server-standalone.jar` desde
[https://selenium.dev/downloads/](https://selenium.dev/downloads/).

### Paso 1: Iniciar el Hub

El Hub es el punto central que recibirá las peticiones de los tests y las 
distribuirá a los nodos adecuados.
La distribución se hace en función capacidades, esto significa que un test que
necesite un conjunto de capacidades solo sera distribuido a los nodos que 
ofrezcan ese conjunto o subconjunto de capacidades.

Debido a que las capacidades deseadas de una prueba son justo lo que el nombre
implica deseadas, el hub no garantiza que se localice un nodo que coincida
completamente con el conjunto de capacidades deseadas.


Abre una ventana de navegación y navega hasta el directorio donde tienes copiado
el archivo `selenium-server-standalone.jar`.
Puedes iniciar el hub pasandole el parámetro `-role hub` al servidor standalone.

```shell
java -jar selenium-server-standalone.jar -role hub
```

El hub escuchará al puerto 4444 por defecto.
Puedes ver el estado del hub abriendo una ventana del navegador y navegando a 
[http://localhost:4444/grid/console](http://localhost:4444/grid/console).

Para cambiar el puerto por defecto, puedes añadir el parámetro opcional `-port`
asignándole un valor entero que representará el puerto a escuchar cuando se 
ejecute el comando.
Ademas todas las otras opciones que puedes observar en el archivo de configuración
JSON (mostrado a continuación) son posibles parámetros vía linea de comandos.

Ciertamente puedes trabajar solo con el comando simple que se muestra arriba,
pero si necesitases una configuración mas avanzada podrías especificarla en un
archivo de configuración JSON para configurar  el hub con su arranque.
Puedes hacerlo tal que así.

```shell
java -jar selenium-server-standalone.jar -role hub -hubConfig hubConfig.json -debug
```

A continuación puedes ver un ejemplo de un archivo `hubConfig.json`.
Iremos mas en detalle sobre como proveer archivos de configuración a los nodos en
el paso 2.

```json
{
  "_comment" : "Configuration for Hub - hubConfig.json",
  "host": ip,
  "maxSession": 5,
  "port": 4444,
  "cleanupCycle": 5000,
  "timeout": 300000,
  "newSessionWaitTimeout": -1,
  "servlets": [],
  "prioritizer": null,
  "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
  "throwOnCapabilityNotPresent": true,
  "nodePolling": 180000,
  "platform": "WINDOWS"}
```


### Paso 2: Iniciar los nodos

Independientemente de si quieres ejecutar un Grid con una nueva funcionalidad del
WebDriver, un Grid con funcionalidades de Selenium 1 RC, o ambas al 
mismo tiempo se usa el mismo archivo `selenium-server-standalone.jar` para arrancar
los nodos.

```shell
java -jar selenium-server-standalone.jar -role node -hub http://localhost:4444
```

Si no se especifica un puerto a través del parámetro `-port` se elegirá un puerto
libre. Puedes ejecutar múltiples nodos en una maquina pero si lo haces tienes que
tener en cuenta los recursos de memoria de tus sistemas y de los problemas con
las capturas de pantalla si tus tests las realizan.

#### Configuración de un nodo con opciones

Como hemos mencionado, para disponer de compatibilidad con las versiones anteriores
los roles "wd" y "rc" todavía son un subconjunto valido del rol en los "node".
Pero estos roles limitan el tipo de conexiones remotas a sus correspondientes APIs,
mientras que el rol "node" permite conexiones remotas a ambos el RC y al WebDriver.


Pasar propiedades de la JVM (como usar el parámetro `-D` antes del argumento -jar)
vía linea de comando permitirá a las nodos recoger y propagar estos parámetros.

`-Dwebdriver.chrome.driver=chromedriver.exe`


#### Configurando un nodo via JSON

Tambien se pueden ejecutar nodos que hayan sido configurados via archivos de 
configuración JSON.

```shell
java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone.jar -role node -nodeConfig node1Config.json
```

Este es un ejemplo de archivo `nodeConfig.json`:

```json
{
  "capabilities": [
    {
      "browserName": "firefox",
      "acceptSslCerts": true,
      "javascriptEnabled": true,
      "takesScreenshot": false,
      "firefox_profile": "",
      "browser-version": "27",
      "platform": "WINDOWS",
      "maxInstances": 5,
      "firefox_binary": "",
      "cleanSession": true
    },
    {
      "browserName": "chrome",
      "maxInstances": 5,
      "platform": "WINDOWS",
      "webdriver.chrome.driver": "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"
    },
    {
      "browserName": "internet explorer",
      "maxInstances": 1,
      "platform": "WINDOWS",
      "webdriver.ie.driver": "C:/Program Files (x86)/Internet Explorer/iexplore.exe"
    }
  ],
  "configuration": {
    "_comment" : "Configuration for Node",
    "cleanUpCycle": 2000,
    "timeout": 30000,
    "proxy": "org.openqa.grid.selenium.proxy.WebDriverRemoteProxy",
    "port": 5555,
    "host": ip,
    "register": true,
    "hubPort": 4444,
    "maxSession": 5
  }
}
```

Una nota sobre el parametro `-host`:

Para ambos el hub y el nodo, si no se especifica el parámetro `-host` se usará
por defecto la IP `0.0.0.0`.  Este se unirá a todos los interfaces IPv4 públicos 
(sin loopback) de la maquina. Si tienes alguna configuración de red especial o
algún componente que utilice interfaces de red extra es recomendado fijar el
parámetro `-host` con un valor que permita que el hub o los nodos sean accesibles
desde maquinas diferentes.

#### Especificando el puerto

El puerto TCP/IP por defecto usado por el hub es el 4444. Si necesitas cambiar 
el puerto por favor usa las configuraciones mencionadas arriba.

## Solución de problemas

### Usando un archivo log
Para resolución de problemas avanzados puedes especificar un archivo de log que
almacene los mensajes del sistema.
Lanza el Grid de Selenium o el nodo con el argumento -log. A continuación dispone
de un ejemplo:

```shell
java -jar selenium-server-standalone.jar -role hub -log log.txt
```

Usa tu editor de texto favorito para abrir el archivo de log (log.txt en el ejemplo)
para encontrar los logs de "ERROR" si tienes problemas.
### Usando el argumento `-debug`

También puedes usar el argumento `-debug` para imprimir los logs de debug en la
consola. Lanza el Grid de Selenium o el nodo con el argumento `-debug`.
A continuación dispone de un ejemplo:

```shell
java -jar selenium-server-standalone.jar -role hub -debug
```

## Advertencia

El Grid de Selenium debe estar protegido contra accesos externos mediante el uso
apropiado de permisos de firewall.

Fallar a la hora de proteger el Grid puede resultar en uno o mas de los siguientes
problemas:

* Proveer acceso abierto a tu infraestructura del Grid.
* Permitir a terceros el acceso a aplicaciones web y archivos interno.
* Permitir a terceros ejecutar tus ejecutables.

Puedes visitar el blog [Detectify](//labs.detectify.com) el cual te puede aportar
mas información sobre los peligros de exponer tu grid públicamente.
Aquí puedes visitar el articulo 
[_Don't Leave your Grid Wide Open_](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/).

## Selenium Docker

[Docker](//www.docker.com/) provee una forma conveniente de aprovisionar y escalar
la infraestructura de Selenium Grid en unidades conocidas como contenedores.
Los contenedores son unidades estandarizadas de software que contienen todo lo 
necesario para ejecutar la aplicación deseada, incluidas todas las dependencias,
en un entorno confiable y regenerable en diferentes sistemas.

El proyecto de Selenium mantiene un conjunto de imágenes Docker las cuales puedes
descargar y ejecutar para tener un Grid funcionando rápidamente. Los nodos están
disponibles para los navegadores Firefox y Chrome. Todos los detalles de como
abastecer un Grid se encuentran en 
[Docker Selenium](//github.com/SeleniumHQ/docker-selenium).

### Prerequisitos
El único requisito para ejecutar el Grid es tener Docker instalado y funcionando.
Puedes descargar Docker [en este enlace](//www.docker.com/products/docker-desktop).
