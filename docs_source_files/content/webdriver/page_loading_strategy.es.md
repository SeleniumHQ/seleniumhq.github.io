---
title: 'Estrategia de carga de página'
weight: 8
---

Define la estrategia de carga de la página en la sesión actual. Por
defecto, cuando Selenium WebDriver carga una página, sigue la
pageLoadStrategy _normal_. Siempre se recomienda detener la descarga
de más recursos adicionales (como imágenes, css, js) cuando la carga
de la página lleva mucho tiempo.

La propiedad `document.readyState` de un documento describe el estado
de carga del documento actual. Por defecto, WebDriver esperará responder
a una llamada `driver.get()` (o) `driver.navigate().to()` hasta que el
estado de documento listo esté `completo`

En aplicaciones SPA (como Angular, React, Ember) una vez que el
contenido dinámico ya está cargado (es decir, una vez que el estado
de pageLoadStrategy es COMPLETO), hacer clic en un enlace o realizar
alguna acción dentro de la página no hará una nueva solicitud al
servidor ya que el contenido se carga dinámicamente en el lado del
cliente sin una actualización de la página.

Las aplicaciones de SPA pueden cargar muchas vistas dinámicamente sin
ninguna solicitud del servidor, por lo que pageLoadStrategy siempre mostrará
el estado 'COMPLETO' hasta que hagamos un nuevo `driver.get()` y `driver.navigate().to()`

WebDriver _pageLoadStrategy_ permite los siguientes valores:

## normal

Esto hará que Selenium WebDriver espere a que se cargue toda la página.
Cuando se establece en **normal**, Selenium WebDriver espera hasta que se
dispare el evento [load](https://developer.mozilla.org/es/docs/Web/Events/load) y sea retornado.

Por defecto **normal** se establece en el navegador si no se proporciona ninguno.

{{< code-tab >}}
{{< code-panel language="java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
public static void main(String[] args) {
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
WebDriver driver = new ChromeDriver(chromeOptions);
try {
// Navigate to Url
driver.get("https://google.com");
} finally {
driver.quit();
}
}
}
{{< / code-panel >}}
{{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)

# Navigate to url

driver.get("http://www.google.com")
driver.quit()

{{< / code-panel >}}
{{< code-panel language="c#" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
class pageLoadStrategy {
public static void Main(string[] args) {
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
IWebDriver driver = new ChromeDriver(chromeOptions);
try {
driver.Navigate().GoToUrl("https://example.com");
} finally {
driver.Quit();
}
}
}
}
{{< / code-panel >}}
{{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='normal'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< / code-panel >}}
{{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("normal");
(async function example() {
let driver = await new Builder().
withCapabilities(caps).
forBrowser('chrome').
build();
try {
// Navigate to Url
await driver.get('https://www.google.com');
}
finally {
await driver.quit();
}
})();
{{< / code-panel >}}
{{< code-panel language="kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
val chromeOptions = ChromeOptions()
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL)
val driver = ChromeDriver(chromeOptions)
try {
driver.get("https://www.google.com")
}
finally {
driver.quit()
}
}
{{< / code-panel >}}
{{< / code-tab >}}

## eager

Esto hará que Selenium WebDriver espere hasta que
el documento HTML inicial se haya cargado y analizado por completo,
y descarta la carga de hojas de estilo, imágenes y sub marcos.

Cuando se establece en **eager**, Selenium WebDriver espera
hasta que se dispare el evento
[DOMContentLoaded](https://developer.mozilla.org/es/docs/Web/API/Document/DOMContentLoaded_event) y sea retornado.

{{< code-tab >}}
{{< code-panel language="java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
public static void main(String[] args) {
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
WebDriver driver = new ChromeDriver(chromeOptions);
try {
// Navigate to Url
driver.get("https://google.com");
} finally {
driver.quit();
}
}
}
{{< / code-panel >}}
{{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)

# Navigate to url

driver.get("http://www.google.com")
driver.quit()
{{< / code-panel >}}
{{< code-panel language="c#" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
class pageLoadStrategy {
public static void Main(string[] args) {
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
IWebDriver driver = new ChromeDriver(chromeOptions);
try {
driver.Navigate().GoToUrl("https://example.com");
} finally {
driver.Quit();
}
}
}
}
{{< / code-panel >}}
{{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='eager'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< / code-panel >}}
{{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("eager");
(async function example() {
let driver = await new Builder().
withCapabilities(caps).
forBrowser('chrome').
build();
try {
// Navigate to Url
await driver.get('https://www.google.com');
}
finally {
await driver.quit();
}
})();
{{< / code-panel >}}
{{< code-panel language="kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
val chromeOptions = ChromeOptions()
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER)
val driver = ChromeDriver(chromeOptions)
try {
driver.get("https://www.google.com")
}
finally {
driver.quit()
}
}
{{< / code-panel >}}
{{< / code-tab >}}

## none

Cuando se establece en **none** Selenium WebDriver solo espera hasta que se descargue la página inicial.

{{< code-tab >}}
{{< code-panel language="java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
public static void main(String[] args) {
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
WebDriver driver = new ChromeDriver(chromeOptions);
try {
// Navigate to Url
driver.get("https://google.com");
} finally {
driver.quit();
}
}
}
{{< / code-panel >}}
{{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)

# Navigate to url

driver.get("http://www.google.com")
driver.quit()
{{< / code-panel >}}
{{< code-panel language="c#" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
class pageLoadStrategy {
public static void Main(string[] args) {
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.None;
IWebDriver driver = new ChromeDriver(chromeOptions);
try {
driver.Navigate().GoToUrl("https://example.com");
} finally {
driver.Quit();
}
}
}
}
{{< / code-panel >}}
{{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='none'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< / code-panel >}}
{{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("none");
(async function example() {
let driver = await new Builder().
withCapabilities(caps).
forBrowser('chrome').
build();
try {
// Navigate to Url
await driver.get('https://www.google.com');
}
finally {
await driver.quit();
}
})();
{{< / code-panel >}}
{{< code-panel language="kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
val chromeOptions = ChromeOptions()
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE)
val driver = ChromeDriver(chromeOptions)
try {
driver.get("https://www.google.com")
}
finally {
driver.quit()
}
}
{{< / code-panel >}}
{{< / code-tab >}}
