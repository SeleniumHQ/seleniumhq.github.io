---
title: "Requerimientos de los controladores"
weight: 2
---


A través del WebDriver, Selenium es capaz de soportar los navegadores mas usados 
en el mercado como Chrom(ium), Firefox, Internet Explorer, Opera y Safari.
WebDriver maneja los navegadores, cuando es posible,  apoyándose en las propias 
funciones que el navegador incorpora para la automatización.

La finalidad del WebDriver es emular las interacciones de los usuarios reales.
Esto es posible en diversos niveles en diferentes navegadores.
Para mas detalles sobre las diferentes comportamientos de los controladores, 
ver 
[Comportamientos del controlador.]({{< ref "/driver_idiosyncrasies/_index.md" >}})

Aunque todos los controladores comparten una única interfaz orientada al usuario 
para manejar los navegadores, todos ellos tienen diferentes formas de establecer 
las sesiones. Ya que muchas de estas implementaciones son realizadas por terceras 
personas y no están incluidas en la distribución estándar de Selenium.

La instanciación del controlador, el tratamiento de perfiles y algunos ajustes 
específicos de cada navegador son ejemplos de parámetros que tienen diferentes 
requisitos dependiendo del navegador.

Esta sección explica los requisitos básicos para comenzar a trabajar con diferentes
navegadores.

### Añadiendo los ejecutables al PATH del sistema
La gran mayoría de controladores necesitan de un ejecutable extra para que 
Selenium pueda comunicarse con el navegador.
Puedes especificar manualmente donde esta ubicado el ejecutable antes lanzar el 
WebDriver, pero esto hará que tus tests sean menos portables, ya que los 
ejecutables necesitan estar en el mismo lugar en todas las maquinas, o que este 
incluido en el repositorio.

Añadir una carpeta que contenga los binarios del WebDriver a tu sistema, 
permitirá a Selenium localizar los binarios necesarios adicionales sin la 
necesidad de tener que incluir en el código de los tests la ruta exacta. 

* Crea un directorio para almacenar los ejecutables en el, como
_C:\WebDriver\bin_ o _/opt/WebDriver/bin_
* Añade el directorio al `PATH` del sistema:
  * En Windows - Abre una terminal de comando como administrador
     y ejecuta el siguiente comando para añadir
      permanentemente el directorio a tu `PATH`
     para todos los usuarios de tu maquina:

```shell
setx /m path "%path%;C:\WebDriver\bin\"
```
  * En macOS y Linux ejecutar el siguiente comando en una terminal:

```shell
export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
```

* Ahora puedes probar los cambios.
  Para ello cierra todas las terminales de comando y abre una nueva
  Escribe el nombre de uno de los binarios que has añadido en la carpeta
  en el paso previo.
  p. ej.: 


  ```shell
  chromedriver
  ```
* Si tu `PATH` ha sido configurado correctamente,
verás una salida relacionada con la puesta en marcha del controlador:

```text
Starting ChromeDriver 2.25.426935 (820a95b0b81d33e42712f9198c215f703412e1a1) on port 9515
Only local connections are allowed.
```

Puedes recuperar el control de la consola de comandos pulsando <kbd>Ctrl + C</kbd>


### Referencia Rápida

Navegador | SO Soportados | Mantenido por | Descargas | Issues |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Descargas](//chromedriver.storage.googleapis.com/index.html) | [Incidentes](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [Descargas](//github.com/mozilla/geckodriver/releases) | [Incidentes](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows 10 | Microsoft | [Descargas](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Incidentes](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&amp;q=webdriver) |
| Internet Explorer | Windows | Proyecto de Selenium | [Descargas](//selenium-release.storage.googleapis.com/index.html) | [Incidentes](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS El Capitan and newer | Apple | Integrado | [Incidentes](//bugreport.apple.com/logon) |
| Opera | Windows/macOS/Linux | Opera | [Descargas](//github.com/operasoftware/operachromiumdriver/releases) | [Incidentes](//github.com/operasoftware/operachromiumdriver/issues) |


### Chromium/Chrome

Para controlar Chrome o Chromium, tienes que descargar
[chromedriver](//sites.google.com/a/chromium.org/chromedriver/downloads)
y almacenarlo en una carpeta que esté en el `PATH` del sistema.

En Linux y en macOS esto significa que tienes que modificar la variable de entorno
`PATH`.

Puedes ver que directorios están incluidos en esta variable, los directorios se 
separan mediante dos puntos.

```shell
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```

Para incluir el chromedriver en el `PATH` si no lo estuviera, hay que asegurarse 
que incluimos la ruta donde se almacena el binario del chromedriver.
Recuerda que puedes fijar la ruta al ejecutable del chromedriver usando la siguiente 
linea, esto te permitirá añadir el contenido actual del `PATH` mas una ruta adicional 
después de los dos puntos:

```shell
$ export PATH="$PATH:/path/to/chromedriver"
```

Cuando el chromedriver este en el `PATH` este podrá ser ejecutado desde cualquier 
directorio.
Para instanciar una sesión de Chrome/Chromium puedes hacer lo siguiente:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

WebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Asignación simple
from selenium.webdriver import Chrome

driver = Chrome()

#Como gestor de contexto
from selenium.webdriver import Chrome

with Chrome() as driver:
    #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

IWebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :chrome
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
    let driver = await new Builder().forBrowser('chrome').build();
    //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

val driver: WebDriver = ChromeDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Recuerda que tienes que fijar la ruta al ejecutable del chromedriver.
Lo puedes hacer de la siguiente forma:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Chrome(executable_path='/path/to/chromedriver')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new ChromeDriver("/path/to/chromedriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Chrome.driver_path = "/path/to/chromedriver"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
chrome.setDefaultService(new chrome.ServiceBuilder('path/to/chromedriver').build());
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver")
  {{< / code-panel >}}
{{< / code-tab >}}

El chromedriver esta implementado como un servidor remoto el cual expone la interfaz del 
proxy para la automatización de chrome, enseñando asi al navegador que hacer.


### Firefox

Con la salida de Selenium 3, Mozilla se ha encargado de la implementación del 
controlador de Firefox, a través del [geckodriver](//github.com/mozilla/geckodriver).
Este nuevo controlador se llama geckodriver y funciona a partir de la versión 48 
de Firefox.
Como este controlador sigue en desarrollo, cuanto mas nueva sea la versión de Firefox 
mas respaldo tendrá por parte de Mozilla.

Como geckodriver es la nueva forma por defecto de lanzar Firefox, puedes instanciar 
Firefox de la misma forma en Selenium 2:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

WebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Asignación simple
from selenium.webdriver import Firefox

driver = Firefox()
#Como gestor de contexto
from selenium.webdriver import Firefox

with Firefox() as driver:
   #El código con este nivel de tabulación
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

IWebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :firefox
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('firefox').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Firefox.FirefoxDriver

val driver: WebDriver = FirefoxDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Si no quisieras fijar la ruta del geckodriver en el `PATH`, puedes fijar la ruta del
ejecutable como propiedad del sistema:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Firefox(executable_path='/path/to/geckodriver')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new FirefoxDriver("/path/to/geckodriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Firefox.driver_path = "/path/to/geckodriver"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const firefox = require('selenium-webdriver/firefox');

const serviceBuilder = new firefox.ServiceBuilder("/path/to/geckodriver");

(async function myFunction() {
    let driver = await new Builder()
        .forBrowser('firefox')
        .setFirefoxService(serviceBuilder)
        .build();
        //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver")
  {{< / code-panel >}}
{{< / code-tab >}}

En ciertos lenguajes de programación también es posible fijar la propiedad en 
tiempo de ejecución:

```shell
mvn test -Dwebdriver.gecko.driver=/path/to/geckodriver
```

Actualmente  también es posible revertir al controlador antiguo de Firefox, el 
cual es un controlador mas completo, instalando la versión [47.0.1](//ftp.mozilla.org/pub/firefox/releases/47.0.1/) o 
[45 ESR](//ftp.mozilla.org/pub/firefox/releases/45.0esr/)
y especificando la capacidad deseada del controlador **marionette** como **false**.
Las ultimas versiones de Firefox ya no son compatibles con este controlador.


### Edge

Edge es el navegador mas nuevo de Microsoft, incluido en Windows 10 y Microsoft 
Server 2016.
Las actualizaciones de Edge están incluidas en las actualizaciones principales 
de Windows, es por eso que necesitaras descargar el binario que coincida con la 
versión que tengas en ese momento instalada de Windows.
[La web de desarrolladores de Edge](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) 
contiene los enlaces a todos los binarios disponibles. 

Los bugs de la implementación del EdgeDriver se pueden encontrar en 
[Microsoft](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&q=webdriver). 

Si deseas lanzar los tests contra Edge pero tu sistema operativo no es Windows 10, 
Microsoft ofrece maquinas virtuales en la [web de desarrolladores de Edge](//developer.microsoft.com/en-us/microsoft-edge/tools/vms/).

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

WebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Asignación simple
from selenium.webdriver import Edge

driver = Edge()
#Como gestor de contexto:
from selenium.webdriver import Edge

with Edge() as driver:
   #El código con este nivel de tabulación
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Edge;

IWebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :edge
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('MicrosoftEdge').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver

val driver: WebDriver = EdgeDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Si el controlador de Edge no esta presente en el `PATH`, puedes añadirlo con el 
siguiente comando:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Edge(executable_path='/path/to/MicrosoftWebDriver.exe')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new EdgeDriver("/path/to/MicrosoftWebDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Edge.driver_path = "C:/path/to/MicrosoftWebDriver.exe"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const edge = require('selenium-webdriver/edge');
let service = new edge.ServiceBuilder("/path/to/msedgedriver.exe");
(async function test() {
    let driver = await new Builder()
                .setEdgeService(service)
                .forBrowser('MicrosoftEdge')
                .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe")
  {{< / code-panel >}}
{{< / code-tab >}}

### Internet Explorer
Internet Explorer era el navegador por defecto hasta la salida de Windows 10, 
aunque todavía esta incluido en Windows 10. El controlador de Internet Explorer 
es el único que Selenium tiene como objetivo admitir las mismas versiones que 
[Microsoft considera como actuales](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer). 
Las versiones anteriores pueden funcionar pero no serán mantenidas.

A pesar de que Selenium proporciona los binarios para las versiones de 32 y 64 
bits de Internet Explorer existen algunas [limitaciones](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
respecto a las versiones 10 y 11 para el controlador de 64-bits, en cambio el 
controlador de 32-bits funciona correctamente. Hay que tener en cuenta que las 
preferencias de Internet Explorer se guardan en la cuenta del usuario conectado, 
ademas es [necesario realizar configuraciones adicionales](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver#required-configuration)


{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

WebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Asignación simple
from selenium.webdriver import Ie

driver = Ie()
#Como gestor de contexto
from selenium.webdriver import Ie

with Ie() as driver:
   #El código con este nivel de tabulación
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

IWebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :internet_explorer
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('internet explorer').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.ie.InternetExplorerDriver

val driver: WebDriver = InternetExplorerDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Si el controlador de Internet Explorer no esta presente en el `PATH`, puedes 
añadirlo usando la siguiente linea:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Ie(executable_path='/path/to/IEDriverServer.exe')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new InternetExplorerDriver("C:/path/to/IEDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::IE.driver_path = "C:/path/to/IEDriver.exe"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const ie = require('selenium-webdriver/ie');
let service = new ie.ServiceBuilder("/path/to/IEDriverServer.exe");
(async function test() {
    let driver = await new Builder()
                .setIeService(service)
                .forBrowser('internet explorer')
                .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe")
  {{< / code-panel >}}
{{< / code-tab >}}

Microsoft tambien ofrece un binario para [Internet Explorer 11 en Windows 
7 y 8.1](//www.microsoft.com/en-gb/download/details.aspx?id=44069).
 El cual no ha sido actualizado desde 2014 y está basado en una versión preeliminar 
 de la especificación del W3C.
[Jim Evans](//jimevansmusic.blogspot.co.uk/2014/09/using-internet-explorer-webdriver.html) 
hizo un informe excelente sobre la implementación del controlador.


### Opera

Las versiones actuales de Opera están construidas con el motor de Chromium, y 
WebDriver ahora es soportado [a través del controlador de código propietario 
de Opera]((//github.com/operasoftware/operachromiumdriver/releases)) el cual puede 
ser [añadido al PATH](#añadiendo-los-ejecutables-al-path-del-sistema) o configurado como propiedad del sistema.

Instanciar una sesión del controlador es similar a como se hace el Firefox y Chromium:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

WebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Asignación simple
from selenium.webdriver import Opera

driver = Opera()
#Como gestor de contexto
from selenium.webdriver import Opera

with Opera() as driver:
   #El código con este nivel de tabulación
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Opera;

IWebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :opera
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const opera = require('selenium-webdriver/opera');
(async function test() {
    let driver = await new Builder()
        .forBrowser('opera')
        .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.opera.OperaDriver

val driver: WebDriver = OperaDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

### Safari

Para las versiones High Sierra y superiores:
* Al Lanzar el siguiente comando desde la terminal por primera vez se debe 
escribir la contraseña para autorizar al WebDriver

```shell
safaridriver --enable
```

Para El Capitan y Sierra:

* Activar el menú desarrollador desde las preferencias de Safari
* Seleccionar la opción _Permitir automatización_ desde el menú del desarrollador
* Ejecutar el siguiente comando desde la terminal y escribir la contraseña para 
autorizar al WebDriver

```shell
/usr/bin/safaridriver -p 1337</
```

Una vez realizar podrás comenzar una sesión usando:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

WebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Asignación simple
from selenium.webdriver import Safari

driver = Safari()
#Como gestor de contexto
from selenium.webdriver import Safari

with Safari() as driver:
   #El código con este nivel de tabulación
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Safari;

IWebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :safari
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('safari').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.safari.SafariDriver

val driver: WebDriver = SafariDriver()
  {{< / code-panel >}}
{{< / code-tab >}}


Aquellos que busquen automatizar Safari en iOS deberían echarle un vistazo al 
proyecto de [Appium](//appium.io/)
Aunque antes Safari estuviera disponible en Windows, Apple hace tiempo que dejo 
de mantenerlo, convirtiendo así a Windows en una pobre elección para la 
automatización de pruebas sobre Safari


## Navegadores simulados


### HtmlUnit

HtmlUnit es un navegador sin interfaz grafica para programas basados en Java.
Modela documentos HTML y proporciona un API que permite invocar las paginas, 
rellanar formularios, hacer clic en enlaces, etc.
Soporta JavaScript y es capaz de funcionar con librerías AJAX, simulando Chrome, 
Firefox o Internet Explorer dependiendo de la configuración usada.
Se ha migrado a una [nueva web](http://htmlunit.sourceforge.net/gettingStarted.html). 
El código fuente es mantenido con de snv.


### PhantomJS

PhantomJS is un navegador sin interfaz grafica basado en Webkit, aunque la 
versión en la que se basa es mucho mas antigua que las usadas por Chrome o 
Safari. A pesar de que históricamente ha sido una elección popular actualmente 
no es una elección muy sabia. 
Ya que el proyecto esta sin soporte desde el [5 de 
gosto](//groups.google.com/forum/#!topic/phantomjs/9aI5d-LduNE), cuando Google 
anunció que Chrome tendría la capacidad de ser un navegador sin interfaz grafica, 
algo que también ha ofrecido Firefox.


