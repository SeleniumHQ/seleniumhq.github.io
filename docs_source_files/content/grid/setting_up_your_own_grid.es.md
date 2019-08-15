---
title: "Configurando tu propio Grid"
weight: 3
---


Para usar Selenium Grid, debe mantener su propia infraestructura para los nodos. Como esto puede ser un esfuerzo demandante e intenso, muchas organizaciones utilizan proveedores de IaaS como Amazon EC2 y Google Compute para proporcionar esta infraestructura.

Otras opciones incluyen el uso de proveedores como Sauce Labs o Testing Bot que proporcionan un Selenium Grid como servicio en la nube. Ciertamente, también es posible ejecutar nodos en su infraestructura propia. Este capítulo entrará en detalles sobre la opción de ejecutar su propia grid, de manera completa con su propia infraestructura de nodos.


## Inicio rápido

Este ejemplo le mostrará cómo iniciar Selenium 2 Grid Hub y registrar un nodo WebDriver y un nodo heredado Selenium 1 RC. También le mostraremos cómo llamar a la grid desde Java. El concentrador (_hub_) y los nodos se muestran aquí ejecutándose en la misma máquina, pero, por supuesto, puede copiar el servidor independiente de Selenium (_selenium-server-standalone_) en varias máquinas.

El paquete `selenium-server-standalone` incluye el hub, WebDriver y el RC heredado necesario para ejecutar la Grid, _ant_ ya no es necesario. Puede descargar el `selenium-server-standalone-.jar` desde [http://www.seleniumhq.org/download/](http://www.seleniumhq.org/download/).


### Paso 1: Inicie el Hub

El Hub es el punto central que recibirá las solicitudes de prueba y las distribuirá a los nodos correctos. La distribución se realiza en función de las capacidades, lo que significa que una prueba que requiere un conjunto de capacidades (_capabilities_) solo se distribuirá a los nodos que ofrecen ese conjunto o subconjunto de capacidades.

Debido a que las capacidades deseadas de una prueba son exactamente lo que el nombre implica "deseadas", el concentrador no puede garantizar que localizará un nodo que coincida completamente con el conjunto de capacidades deseadas solicitado.

Abra una consola del sistema y navegue hasta el directorio donde copió el archivo `selenium-server-standalone.jar`. Inicie el concentrador pasando el indicador `-role hub` al servidor independiente:

```shell
java -jar selenium-server-standalone.jar -role hub
```

El Hub escuchará el puerto 4444 por defecto. Puede ver el estado del concentrador abriendo una ventana del navegador y navegando a [http://localhost:4444/grid/console](http://localhost:4444/grid/console).

Para cambiar el puerto predeterminado, puede agregar el indicador opcional `-port` con un número entero que representa el puerto para escuchar cuando ejecuta el comando. Además, todas las otras opciones que ve en el archivo de configuración JSON (que se ve a continuación) son posibles indicadores de línea de comandos.

Ciertamente puede hacerlo solo con el comando simple que se muestra arriba, pero si necesita una configuración más avanzada, también puede especificar un archivo de configuración de formato JSON para configurar el concentrador cuando lo inicie. Puede hacerlo así:

```shell
java -jar selenium-server-standalone.jar -role hub -hubConfig hubConfig.json -debug
```

A continuación verá un ejemplo de un archivo `hubConfig.json`. Entraremos en más detalles sobre cómo proporcionar archivos de configuración de nodos en el paso 2.

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


### Step 2: Start the Nodes

Independientemente de si desea ejecutar una grid con la nueva funcionalidad WebDriver, o una grid con la funcionalidad Selenium 1 RC, o ambas al mismo tiempo, utilice el mismo archivo `selenium-server-standalone.jar` para iniciar los nodos:

```shell
java -jar selenium-server-standalone.jar -role node -hub http://localhost:4444
```

Si no se especifica un puerto a través de la argumento `-port`, se elegirá un puerto libre. Puede ejecutar múltiples nodos en una máquina, pero si lo hace, debe conocer los recursos de memoria de su sistema y los problemas con las capturas de pantalla si sus pruebas los toman.


#### Configuración de Nodo con opciones

Como se mencionó, para la compatibilidad con versiones anteriores, los roles "wd" y "rc" siguen siendo un subconjunto válido del rol "node". Pero esos roles limitan los tipos de conexiones remotas a su API correspondiente, mientras que "node" permite conexiones remotas RC y WebDriver.

Al pasar las propiedades de JVM (usando el indicador `-D` _antes del argumento -jar_) también en la línea de comando, estas serán recogidas y propagadas a los nodos:

`-Dwebdriver.chrome.driver=chromedriver.exe`


#### Configuración de nodo con JSON

También puede iniciar nodos de grid configurados con un archivo de configuración JSON

```shell
java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone.jar -role node -nodeConfig node1Config.json
```

Y este es un ejemplo del archivo `nodeConfig.json`:

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

Una nota sobre la argumento `-host`

Tanto para el concentrador como para el nodo, si no se especifica el indicador `-host`, se usará` 0.0.0.0` por defecto. Esto se unirá a todas las interfaces IPv4 públicas (sin loopback) de la máquina. Si tiene una configuración de red especial o algún componente que cree interfaces de red adicionales, se recomienda establecer el indicador `-host` con un valor que permita que se pueda acceder al concentrador / nodo desde una máquina diferente.

#### Especificando el puerto

El puerto TCP / IP predeterminado utilizado por el concentrador es 4444. Si necesita cambiar el puerto, utilice las configuraciones mencionadas anteriormente.

## Solución de problemas

### Uso del archivo de registro
Para la resolución de problemas avanzada, puede especificar el archivo de registro para almacenar los mensajes del sistema. Para eso, inicie Selenium GRID hub o nodo con el argumento -log. Por favor vea el siguiente ejemplo:

```shell
java -jar selenium-server-standalone.jar -role hub -log log.txt
```

Use su editor de texto favorito para abrir el archivo de registro (log.txt en el ejemplo anterior) para encontrar registros de "ERROR" si tiene problemas.

### Usando el argumento `-debug`
También puede usar el argumento `-debug` para imprimir registros de depuración en la consola. Para eso, inicie Selenium Grid Hub o Node con el argumento `-debug`. Por favor vea el siguiente ejemplo

```shell
java -jar selenium-server-standalone.jar -role hub -debug
```


## Advertencia

Selenium Grid debe protegerse del acceso externo mediante los permisos de firewall adecuados. Si no protege su Grid, podría ocurrir uno o más de los siguientes casos:
* Usted proporciona acceso abierto a su infraestructura Grid
* Permitir que terceros accedan a aplicaciones y archivos web internos
* Permites que terceros ejecuten binarios personalizados

Consulte esta publicación de blog en [Detectify](//labs.detectify.com) que ofrece una buena visión general de cómo se puede utilizar incorrectamente una grid expuesta públicamente: [Inglés: No deje la grid abierta](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/).


## Docker Selenium

[Docker](//www.docker.com/) proporciona una forma conveniente de aprovisionar y escalar la infraestructura de Selenium Grid en una unidad conocida como contenedor. Los contenedores son unidades de software estandarizadas que contienen todo lo necesario para ejecutar la aplicación deseada, incluidas todas las dependencias de manera confiable y repetible en diferentes máquinas.

El proyecto Selenium mantiene un conjunto de imágenes de Docker que puede descargar y ejecutar para que una grid de trabajo funcione rápidamente. Los nodos están disponibles para Firefox y Chrome. Los detalles completos sobre cómo aprovisionar una grid se pueden encontrar en el repositorio [Docker Selenium](//github.com/SeleniumHQ/docker-selenium).

### Requisito previo
El único requisito para ejecutar un Grid es tener Docker instalado y funcionando. [Instalar Docker](//www.docker.com/products/docker-desktop).

