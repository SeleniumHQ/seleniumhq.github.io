---
title: "Entendiendo los componentes"
linkTitle: "Entendiendo los componentes"
weight: 1
aliases: ["/documentation/es/webdriver/understanding_the_components/"]
---

Construir una suite de test usando WebDriver requerirá que entiendas y 
uses de forma efectiva diferentes componentes. Como con todo en
el desarrollo de software, la gente usa diferentes términos para la misma idea.
A continuación hay un desglose de cómo los términos son usados en esa descripción.

### Terminología

* **API:** Interfaz de Programación de Aplicaciones. Es un conjunto de "comandos" 
que se utilizan para manipular el WebDriver.
* **Library:** Un módulo de código que contiene las APIs y el código necesario
para implementarlos. Las librerías son específicas para cada lenguaje, por ejemplo
ficheros .jar en Java, ficheros .dll para .NET, etc.
* **Driver:** El responsable de controlar el navegador actual. La mayoría de los drivers
son creados por los _vendors_ del navegador. Los Drivers son generalmente
módulos ejecutables que corren en el sistema con el propio navegador,
no en el sistema ejecutando la suite de test. (Aunque esos pueden ser el
mismo sistema.) _NOTE: Algunas personas se refieren a los drivers como proxies._
* **Framework:** Una librería adicional usada como un soporte para la suites de WebDriver.
Estos _frameworks_ pueden ser _test frameworks_ como JUnit o NUnit.
También pueden ser _frameworks_ soportando lenguaje natural como 
Cucumber o Robotium. Los _frameworks_ también pueden ser escritos y usados para
cosas como la manipulación o configuración del sistema bajo la prueba, creación
de datos, _test oracles_, etc

### Las Partes y las Piezas

Como mínimo, el WebDriver habla con un navegador a través del driver. La comunicación
es bidireccional: el WebDriver pasa comandos al navegador a través del driver, y
recibe la información de vuelta por la misma ruta.

{{< figure src="/images/documentation/webdriver/basic_comms.png" class="img-responsive text-center" alt="Basic Communication">}}

El driver es específico para el navegador, como es ChromeDriver para Chrome/Chromium
de Google, GeckoDriver para Mozilla Firefox, etc. El driver corre en el 
mismo sistema que el browser. Esto puede, o no puede ser, el mismo sistema donde
los tests se están ejecutando.

Este simple ejemplo anterior es de comunicación directa. La comunicación con el
navegador puede ser remota a través de Selenium Server o RemoteWebdriver. Éste 
último corre en el mismo sistema que el driver y el browser. 

{{< figure src="/images/documentation/webdriver/remote_comms.png" class="img-responsive text-center" alt="Remote Communication">}}

La comunicación remota puede también hacerse usando Selenium Server o Selenium
Grid, ambos a su vez hablan con el driver en el sistema anfitrión.

{{< figure src="/images/documentation/webdriver/remote_comms_server.png" class="img-responsive text-center" alt="Remote Communication with Grid">}}

## Dónde encaja el Framework

El WebDriver tiene un trabajo y solo un trabajo: comunicarse con el navegador a 
través de uno de los métodos nombrados. El WebDriver no tiene que saber nada sobre 
testing: no sabe cómo comparar cosas, asegurar un _pass_ o _fail_, y ciertamente
no sabe nada acerca de reportes o sobre la gramática _Given/When/Then_.

Aquí es donde varios _frameworks_ entran en juego. Como mínimo necesitarás un
_framework_ de test que compare los enlaces de idiomas, por ejempolo NUnit para .NET,
JUnit para Java, RSpec para Ruby, etc.

El _framework_ de test es responsable de correr y ejecutar tu WebDriver
y los pasos de tus tests. Como tal, puedes pensar que se parece 
a la siguiente imagen.

{{< figure src="/images/documentation/webdriver/test_framework.png" class="img-responsive text-center" alt="Test Framework">}}

Los _frameworks_ o herramientas de lenguage natural como Cucumber pueden existir
como parte de la caja de _Test Framework_ de la figura de arriba, o envolver totalmente
el _Test Framework_ en su propia implementación.
