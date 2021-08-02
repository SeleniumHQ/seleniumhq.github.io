---
title: "Selenium 1 (Selenium RC)"
linkTitle: "Selenium 1 (Selenium RC)"
weight: 1
---

## Introducción

Selenium RC fue el principal proyecto de Selenium durante mucho tiempo,
antes de que la union de WebDriver/Selenium resultaran en Selenium 2,
una herramienta más poderosa.
Vale la pena resaltar que Selenium 1 ya no es soportado.

## Como funciona Selenium RC

Primero, describiremos cómo funcionan los componentes de Selenium RC
y el papel que desempeña cada uno en la ejecución tus scripts de prueba.

### Componentes de RC

Los componentes de Selenium RC son:

* El servidor Selenium que inicia y finaliza navegadores,
interpreta y ejecuta los comandos Selenese pasados del programa
de prueba, y actúa como un *proxy HTTP*, interceptando y verificando
los mensajes HTTP pasados entre el navegador y el AUT.
* Librerías de clientes que proporcionan la interfaz entre cada lenguaje de programación y Selenium RC Server.

Aquí hay un diagrama simplificado de la arquitectura:

![Architecture Diagram Simple](/images/legacy_docs/selenium_rc_architecture_diagram_simple.png)

El diagrama muestra que las librerías del cliente se comunican con el
el servidor pasando cada comando Selenium para su ejecución.
Entonces el servidor pasa el comando Selenium al navegador utilizando
los comandos JavaScript de Selenium-Core.
El navegador, utilizando su intérprete de JavaScript, ejecuta el comando Selenium. Esta ejecuta la acción o verificación de Selenese que
especificas en tu script de prueba.

### Selenium Server

Selenium Server recibe comandos de Selenium de tu programa
de prueba, los interpreta e informa a tu programa los
resultados de ejecutar esas pruebas.

El servidor RC agrupa Selenium Core y automáticamente
lo inyecta en el navegador. Esto ocurre cuando tu
programa de prueba abre el navegador (utilizando una
función del API de la librería del cliente). Selenium-Core es un
programa de JavaScript, en realidad un conjunto de funciones
JavaScript que interpretan y ejecutan comandos
Selenese utilizando el intérprete de JavaScript incorporado
en el navegador.

El servidor recibe los comandos Selenese de tu programa de
prueba utilizando simples solicitudes HTTP GET/POST.
Esto significa que puedes usar cualquier lenguaje de programación
que pueda enviar solicitudes HTTP para automatizar pruebas
de Selenium en el navegador.

### Librerías de Clientes

Las librerías del cliente proporcionan el soporte de
programación que te permiten ejecutar comandos Selenium desde
un programa de tu propio diseño. Hay una librería cliente
diferente para cada lenguaje compatible. Una librería
cliente de Selenium proporciona una interfaz de
programación (API), es decir, un conjunto de funciones,
que ejecuta comandos de Selenium desde tu propio programa.
Dentro de cada interfaz, hay una función de programación
que soporta cada comando de Selenese.

La librería del cliente toma un comando Selenese y lo
pasa al servidor Selenium para procesar una acción o prueba
específica contra la aplicación bajo prueba (AUT). La
librería del cliente también recibe el resultado de ese
comando y lo devuelve a tu programa. Tu programa puede
recibir el resultado y almacenarlo en una variable del
programa e informarlo como un éxito o un fracaso, o
posiblemente tomar medidas correctivas si fue un error
inesperado.

Entonces, para crear un programa de prueba, simplemente
escribe un programa que ejecute un conjunto de comandos
de Selenium utilizando una API de la librería del cliente.
Y, opcionalmente, si ya tienes un script de prueba Selenese
creado en Selenium-IDE, puedes *generar el código Selenium RC*.
Selenium-IDE puede traducir (utilizando los elementos del
menú  Exportar) tus comandos Selenium en las llamadas
a la función API de un cliente-controlador. Consulta el capítulo
Selenium-IDE para obtener información específica sobre la
exportación de código RC de Selenium-IDE.

## Instalación

La instalación es más bien un nombre inapropiado para Selenium.
Selenium tiene un conjunto de librerías disponibles
en el lenguaje de programación que elijas. Puedes descargarlos desde la
[pagina de descarga](https://selenium.dev/downloads/).

Una vez que hayas elegido un lenguaje para trabajar, simplemente necesitas:

* Instalar el servidor Selenium RC.
* Configurar un proyecto de programación utilizando un controlador de cliente específico del lenguaje.

### Instalando Selenium Server

El servidor Selenium RC es simplemente un archivo Java *jar*
(*selenium-server-standalone-<version-number>.jar*), que no
requiere una instalación especial. Simplemente descargando el archivo zip y extrayendo el servidor en el directorio deseado es suficiente.

### Ejecutando Selenium Server

Antes de comenzar cualquier prueba, debes iniciar el servidor.
Ve al directorio donde se encuentra el servidor de Selenium RC 
y ejecuta lo siguiente desde una consola línea de comandos.

```shell
    java -jar selenium-server-standalone-<version-number>.jar
```

Esto se puede simplificar creando un archivo ejecutable
batch o shell (.bat en Windows y .sh en Linux) que contiene el comando
anterior. Luego haz un acceso directo a ese ejecutable en tu
escritorio y simplemente haz doble clic en el icono para iniciar el servidor.

Para que el servidor se ejecute, necesitarás Java instalado
y la variable de entorno PATH configurada correctamente para ejecutarla desde la consola.
Puedes verificar que tienes Java instalado correctamente ejecutando lo siguiente
en una consola

```shell
       java -version
```

Si obtienes un número de versión (que debe ser 1.5 o posterior), estás listo para comenzar a usar Selenium RC.

### Utilizando el controlador de cliente Java

* Descarga el zip del controlador del cliente Java de Selenium desde
SeleniumHQ [página de descargas](https://selenium.dev/downloads/).
* Extrae el archivo selenium-java-<version-number>.jar
* Abra tu IDE de Java deseado (Eclipse, NetBeans, IntelliJ, Netweaver, etc.)
* Crea un proyecto java.
* Agregaa los archivos selenium-java-<version-number>.jar a tu proyecto como referencias.
* Agrega a tu classpath de proyecto el archivo selenium-java-<version-number>jar.
* Desde Selenium-IDE, exporta un script a un archivo Java e inclúyelo en tu
proyecto Java, o escribe tu prueba de Selenium en Java usando la API selenium-java-client. La API se presenta más adelante en este capítulo.
Puedes usar JUnit o TestNg para ejecutar tu prueba, o puedes escribir tu propio programa main() simple. Estos conceptos son explicados más adelante en esta sección.
* Ejecuta el servidor Selenium desde la consola.
* Ejecuta tu prueba desde el IDE de Java o desde la línea de comandos.

Para obtener detalles sobre la configuración del proyecto de prueba Java, consulta las secciones del Apéndice: Configuración de Selenium RC con Eclipse y Configuración de Selenium RC con IntelliJ.

### Utilizando el controlador de cliente Python

* Instala Selenium via PIP, las instrucciones están en SeleniumHQ
[pagina de descargas](https://selenium.dev/downloads/) 
* Escribe tu prueba de Selenium en Python o exporta
un script de Selenium-IDE a un archivo de Python.
* Ejecuta el servidor Selenium desde la consola.
* Ejecuta tu prueba desde una consola o tu IDE de Python.

Para obtener detalles sobre la configuración del controlador del cliente Python, consulte el Apéndice: Configuración del controlador del cliente Python.

### Utlizando el Controlador de Cliente .NET

* Descarga Selenium RC desde SeleniumHQ [página de descargas](https://selenium.dev/downloads/)
* Extrae la carpeta
* Descargua e instala [NUnit](https://nunit.org/download/) 
(Nota: Puedes usar NUnit como tu motor de prueba. Si aún no estás familiarizado con NUnit, también puedes escribir una función main() simple para ejecutar tus pruebas; sin embargo, NUnit es muy útil como motor de prueba).
* Abre tu IDE .Net deseado (Visual Studio, SharpDevelop, MonoDevelop)
* Crea una biblioteca de clases (.dll)
* Agrega referencias a las siguientes DLL: nmock.dll, nunit.core.dll, nunit.
framework.dll, ThoughtWorks.Selenium.Core.dll, ThoughtWorks.Selenium.IntegrationTests.dll y ThoughtWorks.Selenium.UnitTests.dll
* Escribe tu prueba de Selenium en un lenguaje .Net (C #, VB.Net), o exporta
un script de Selenium-IDE a un archivo C# y copia este código en el archivo de clase acabas de crear.
* Escribe tu  propio programa main() simple o puedes incluir NUnit en tu proyecto para ejecutar tu prueba. Estos conceptos se explican más adelante en este capítulo.
* Ejecuta el servidor Selenium desde la consola
* Ejecuta tu prueba desde el IDE, desde la GUI de NUnit o desde la línea de comandos.

Para obtener detalles específicos sobre la configuración del controlador del cliente .NET con Visual Studio, consulte el apéndice
Configuración del controlador del cliente .NET

### Utilizando el Controlador de Cliente Ruby

* Si aún no tiene RubyGems, instálalo desde RubyForge.
* Ejecuta ``gem install selenium-client``
* En la parte superior de tu script de prueba, agrega ``require "selenium/client"``
* Escribe tu script de prueba usando cualquier arnés de prueba Ruby (por ejemplo, Test::Unit Mini::Test o RSpec).
* Ejecuta el servidor Selenium RC desde la consola.
* Ejecuta tu prueba de la misma manera que ejecutarías cualquier otro script de Ruby.

Para obtener detalles sobre la configuración del controlador del cliente Ruby, consulta la `Documentación de Selenium-Client`_

## De Selenese a un Programa

La tarea principal para usar Selenium RC es convertir tu Selenese en un lenguaje de programación. En esta sección, proporcionamos varios ejemplos
lenguaje-específico diferentes.

### Muestra de un Script de prueba

Comencemos con un ejemplo de un script de prueba de Selenese. Imagina grabar
la siguiente prueba con Selenium-IDE.

|                    |                               |             |
| --------           | ----------------------------  | ----------- |
| open               | /                             |             |
| type               | q                             | selenium rc |
| clickAndWait       | btnG                          |             |
| assertTextPresent  | Results * for selenium rc     |             |

Nota: este ejemplo funcionaría con la página de búsqueda de Google <http://www.google.com>

### Selenese Como Código de Programación

Aquí está el script de prueba exportado (a través de Selenium-IDE) a cada uno de los lenguajes de programación soportados. Si tienes al menos un conocimiento básico de un lenguaje de programación orientado a objetos, comprenderás cómo Selenium ejecuta comandos Selenese leyendo uno de estos ejemplos. Para ver un ejemplo en un lenguaje específico, seleccione uno de estos botones.

#### CSharp
``` csharp

        using System;
        using System.Text;
        using System.Text.RegularExpressions;
        using System.Threading;
        using NUnit.Framework;
        using Selenium;

        namespace SeleniumTests
        {
            [TestFixture]
            public class NewTest
            {
                private ISelenium selenium;
                private StringBuilder verificationErrors;
                
                [SetUp]
                public void SetupTest()
                {
                    selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com/");
                    selenium.Start();
                    verificationErrors = new StringBuilder();
                }
                
                [TearDown]
                public void TeardownTest()
                {
                    try
                    {
                        selenium.Stop();
                    }
                    catch (Exception)
                    {
                        // Ignore errors if unable to close the browser
                    }
                    Assert.AreEqual("", verificationErrors.ToString());
                }
                
                [Test]
                public void TheNewTest()
                {
                    selenium.Open("/");
                    selenium.Type("q", "selenium rc");
                    selenium.Click("btnG");
                    selenium.WaitForPageToLoad("30000");
                    Assert.AreEqual("selenium rc - Google Search", selenium.GetTitle());
                }
            }
        }

```

#### Java

```java 
      
	  /** Add JUnit framework to your classpath if not already there 
	   *  for this example to work
	  */
      package com.example.tests;

      import com.thoughtworks.selenium.*;
      import java.util.regex.Pattern;

      public class NewTest extends SeleneseTestCase {
          public void setUp() throws Exception {
              setUp("http://www.google.com/", "*firefox");
          }
            public void testNew() throws Exception {
                selenium.open("/");
                selenium.type("q", "selenium rc");
                selenium.click("btnG");
                selenium.waitForPageToLoad("30000");
                assertTrue(selenium.isTextPresent("Results * for selenium rc"));
          }
      }

```

#### Php

```php
      <?php

      require_once 'PHPUnit/Extensions/SeleniumTestCase.php';

      class Example extends PHPUnit_Extensions_SeleniumTestCase
      {
        function setUp()
        {
          $this->setBrowser("*firefox");
          $this->setBrowserUrl("http://www.google.com/");
        }

        function testMyTestCase()
        {
          $this->open("/");
          $this->type("q", "selenium rc");
          $this->click("btnG");
          $this->waitForPageToLoad("30000");
          $this->assertTrue($this->isTextPresent("Results * for selenium rc"));
        }
      }
      ?>

```

#### Python

```python

     from selenium import selenium
      import unittest, time, re

      class NewTest(unittest.TestCase):
          def setUp(self):
              self.verificationErrors = []
              self.selenium = selenium("localhost", 4444, "*firefox",
                      "http://www.google.com/")
              self.selenium.start()
         
          def test_new(self):
              sel = self.selenium
              sel.open("/")
              sel.type("q", "selenium rc")
              sel.click("btnG")
              sel.wait_for_page_to_load("30000")
              self.failUnless(sel.is_text_present("Results * for selenium rc"))
         
          def tearDown(self):
              self.selenium.stop()
              self.assertEqual([], self.verificationErrors)

```

#### Ruby

```ruby

      require "selenium/client"
      require "test/unit"

      class NewTest < Test::Unit::TestCase
        def setup
          @verification_errors = []
          if $selenium
            @selenium = $selenium
          else
            @selenium =  Selenium::Client::Driver.new("localhost", 4444, "*firefox", "http://www.google.com/", 60);
            @selenium.start
          end
          @selenium.set_context("test_new")
        end

        def teardown
          @selenium.stop unless $selenium
          assert_equal [], @verification_errors
        end

        def test_new
          @selenium.open "/"
          @selenium.type "q", "selenium rc"
          @selenium.click "btnG"
          @selenium.wait_for_page_to_load "30000"
          assert @selenium.is_text_present("Results * for selenium rc")
        end
      end

```

En la siguiente sección explicaremos cómo construir un programa de prueba usando el código generado.

## Programando Tu Prueba

Ahora ilustraremos cómo programar tus propias pruebas usando
ejemplos en cada uno de las lenguajes de programación
compatibles. Hay esencialmente dos tareas:

* Generar tu script a un lenguaje de programación desde Selenium-IDE,
opcionalmente modificando el resultado.
* Escribir un programa principal muy simple que ejecute el código generado.

Opcionalmente, puedes adoptar una plataforma de prueba
como JUnit o TestNG para Java, o NUnit para .NET si estás
utilizando uno de esos lenguajes.

Aquí, mostramos ejemplos específicos del lenguaje. Las API
específicas del lenguaje tienden a diferir uno de otro, por lo
que encontrarás una explicación separada para cada uno.

* Java
* C#
* Python
* Ruby
* Perl, PHP

### Java

Para Java, las personas utilizan JUnit o TestNG como plataforma de
prueba.
Algunos entornos de desarrollo como Eclipse tienen
soporte directo para estos a través de complementos.
Esto lo hace aún más fácil. Enseñar JUnit o TestNG está más allá del alcance
de este documento, sin embargo, los materiales se pueden
encontrar en línea y hay publicaciones disponibles. Si ya eres
una "tienda de Java", es probable que tus desarrolladores lo
hagan ya tengan algo de experiencia con uno de estos frameworks de
prueba.

Probablemente desees cambiar el nombre de la clase de prueba de
"NewTest" a algo de tu propia elección. Además, deberás cambiar
los parámetros abrir-navegador en la declaración:

```java
    selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://www.google.com/");
``` 

El código generado por Selenium-IDE se verá así. Este ejemplo
tiene comentarios agregados manualmente para mayor claridad.

```java
   package com.example.tests;
   // We specify the package of our tests

   import com.thoughtworks.selenium.*;
   // This is the driver's import. You'll use this for instantiating a
   // browser and making it do what you need.

   import java.util.regex.Pattern;
   // Selenium-IDE add the Pattern module because it's sometimes used for 
   // regex validations. You can remove the module if it's not used in your 
   // script.

   public class NewTest extends SeleneseTestCase {
   // We create our Selenium test case

         public void setUp() throws Exception {
           setUp("http://www.google.com/", "*firefox");
                // We instantiate and start the browser
         }

         public void testNew() throws Exception {
              selenium.open("/");
              selenium.type("q", "selenium rc");
              selenium.click("btnG");
              selenium.waitForPageToLoad("30000");
              assertTrue(selenium.isTextPresent("Results * for selenium rc"));
              // These are the real test steps
        }
   }
```


### `C#`

El .NET Client Driver funciona con Microsoft.NET. Se puede usar con
cualquier framework de prueba .NET como NUnit o Visual Studio 2005
Team System.

Selenium-IDE asume que usará NUnit como framework de prueba. Puedes
ver esto en el código generado a continuación. Incluye la
declaración *using* para NUnit junto con los atributos de
NUnit correspondientes que identifican el rol de cada función
miembro de la clase de prueba.

Probablemente desees cambiar el nombre de la clase de prueba de
"NewTest" a algo de tu propia elección. Además, deberás cambiar
los parámetros abrir-navegador en la declaración:

```csharp
    selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://www.google.com/");
```

El codigo generado se vera similar a esto.

```csharp

    using System;
    using System.Text;
    using System.Text.RegularExpressions;
    using System.Threading;
    using NUnit.Framework;
    using Selenium;
    
    namespace SeleniumTests

    {
        [TestFixture]

        public class NewTest

        {
        private ISelenium selenium;

        private StringBuilder verificationErrors;

        [SetUp]

        public void SetupTest()

        {
            selenium = new DefaultSelenium("localhost", 4444, "*iehta",
            "http://www.google.com/");

            selenium.Start();

            verificationErrors = new StringBuilder();
        }

        [TearDown]

        public void TeardownTest()
        {
            try
            {
            selenium.Stop();
            }

            catch (Exception)
            {
            // Ignore errors if unable to close the browser
            }

            Assert.AreEqual("", verificationErrors.ToString());
        }
        [Test]

        public void TheNewTest()
        {
            // Open Google search engine.        
            selenium.Open("http://www.google.com/"); 
            
            // Assert Title of page.
            Assert.AreEqual("Google", selenium.GetTitle());
            
            // Provide search term as "Selenium OpenQA"
            selenium.Type("q", "Selenium OpenQA");
            
            // Read the keyed search term and assert it.
            Assert.AreEqual("Selenium OpenQA", selenium.GetValue("q"));
            
            // Click on Search button.
            selenium.Click("btnG");
            
            // Wait for page to load.
            selenium.WaitForPageToLoad("5000");
            
            // Assert that "www.openqa.org" is available in search results.
            Assert.IsTrue(selenium.IsTextPresent("www.openqa.org"));
            
            // Assert that page title is - "Selenium OpenQA - Google Search"
            Assert.AreEqual("Selenium OpenQA - Google Search", 
                         selenium.GetTitle());
        }
        }
    }
```

Puedes permitir que NUnit gestione la ejecución
de tus pruebas. O, alternativamente, puedes escribir un simple programa
`main()` que crea una instancia del objeto de prueba y ejecuta
cada uno de los tres métodos, `SetupTest()`,
`TheNewTest()` y `TeardownTest()` a su vez.

### Python

Pyunit es el framework de prueba para usar en Python.

La estructura de una prueba básica es:

```python

   from selenium import selenium
   # This is the driver's import.  You'll use this class for instantiating a
   # browser and making it do what you need.

   import unittest, time, re
   # This are the basic imports added by Selenium-IDE by default.
   # You can remove the modules if they are not used in your script.

   class NewTest(unittest.TestCase):
   # We create our unittest test case

       def setUp(self):
           self.verificationErrors = []
           # This is an empty array where we will store any verification errors
           # we find in our tests

           self.selenium = selenium("localhost", 4444, "*firefox",
                   "http://www.google.com/")
           self.selenium.start()
           # We instantiate and start the browser

       def test_new(self):
           # This is the test code.  Here you should put the actions you need
           # the browser to do during your test.
            
           sel = self.selenium
           # We assign the browser to the variable "sel" (just to save us from 
           # typing "self.selenium" each time we want to call the browser).
            
           sel.open("/")
           sel.type("q", "selenium rc")
           sel.click("btnG")
           sel.wait_for_page_to_load("30000")
           self.failUnless(sel.is_text_present("Results * for selenium rc"))
           # These are the real test steps

       def tearDown(self):
           self.selenium.stop()
           # we close the browser (I'd recommend you to comment this line while
           # you are creating and debugging your tests)

           self.assertEqual([], self.verificationErrors)
           # And make the test fail if we found that any verification errors
           # were found
```

### Ruby

Las versiones anteriores (anteriores a 2.0) de Selenium-IDE generan
código Ruby que requiere el antiguo gem de Selenium.
Por lo tanto, es recomendable actualizar cualquier script Ruby 
generado por IDE de la siguiente manera:

1. En la línea 1, cambia ``require "selenium"`` a ``require
"selenium/client"``

2. En la línea 11, cambia ``Selenium::SeleniumDriver.new`` a
``Selenium::Client::Driver.new``

Probablemente también quieras cambiar el nombre de la clase a algo más
informativo que "Sin título", y cambiar el nombre del método de prueba a
algo diferente de "test_untitled".

Aquí hay un ejemplo simple creado al modificar el código Ruby generado
por Selenium IDE, como se describió anteriormente.

```ruby

   # load the Selenium-Client gem
   require "selenium/client"

   # Load Test::Unit, Ruby's default test framework.
   # If you prefer RSpec, see the examples in the Selenium-Client
   # documentation.
   require "test/unit"

   class Untitled < Test::Unit::TestCase

     # The setup method is called before each test.
     def setup

       # This array is used to capture errors and display them at the
       # end of the test run.
       @verification_errors = []

       # Create a new instance of the Selenium-Client driver.
       @selenium = Selenium::Client::Driver.new \
         :host => "localhost",
         :port => 4444,
         :browser => "*chrome",
         :url => "http://www.google.com/",
         :timeout_in_second => 60

       # Start the browser session
       @selenium.start

       # Print a message in the browser-side log and status bar
       # (optional).
       @selenium.set_context("test_untitled")

     end

     # The teardown method is called after each test.
     def teardown

       # Stop the browser session.
       @selenium.stop

       # Print the array of error messages, if any.
       assert_equal [], @verification_errors
     end

     # This is the main body of your test.
     def test_untitled
     
       # Open the root of the site we specified when we created the
       # new driver instance, above.
       @selenium.open "/"

       # Type 'selenium rc' into the field named 'q'
       @selenium.type "q", "selenium rc"

       # Click the button named "btnG"
       @selenium.click "btnG"

       # Wait for the search results page to load.
       # Note that we don't need to set a timeout here, because that
       # was specified when we created the new driver instance, above.
       @selenium.wait_for_page_to_load

       begin

          # Test whether the search results contain the expected text.
	  # Notice that the star (*) is a wildcard that matches any
	  # number of characters.
	  assert @selenium.is_text_present("Results * for selenium rc")
	  
       rescue Test::Unit::AssertionFailedError
       
          # If the assertion fails, push it onto the array of errors.
	  @verification_errors << $!

       end
     end
   end

```

### Perl, PHP

Los miembros del equipo de documentación.
no han utilizado Selenium RC con Perl o PHP. Si estás utilizando Selenium RC con cualquiera de estos dos lenguajes, póngase en contacto con el equipo de documentación (consulta el capítulo sobre contribuciones).
Nos encantaría incluir algunos ejemplos tuyos y de tus experiencias, para apoyar a los usuarios de Perl y PHP.


## Aprendiendo el API

La API de Selenium RC utiliza convenciones de nomenclatura
suponiendo que entiendes a Selenese, gran parte de la interfaz
se explicará por sí misma. Aquí, sin embargo, explicamos los
aspectos más críticos y posiblemente menos obvios.

### Iniciando el Navegador

#### CSharp
```csharp
      selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com/");
      selenium.Start();
```

#### Java
```java

      setUp("http://www.google.com/", "*firefox");
```

#### Perl
```perl
      my $sel = Test::WWW::Selenium->new( host => "localhost", 
                                          port => 4444, 
                                          browser => "*firefox", 
                                          browser_url => "http://www.google.com/" );
```

#### Php
```php
      $this->setBrowser("*firefox");
      $this->setBrowserUrl("http://www.google.com/");
```

#### Python
```python
      self.selenium = selenium("localhost", 4444, "*firefox",
                               "http://www.google.com/")
      self.selenium.start()
```

#### Ruby
```ruby
      @selenium = Selenium::ClientDriver.new("localhost", 4444, "*firefox", "http://www.google.com/", 10000);
      @selenium.start
```

Cada uno de estos ejemplos abre el navegador y representa ese
navegador asignando una "instancia del navegador" a una variable
de programa. Esta variable de programa se utiliza entonces para
llamar a los métodos desde el navegador. Estos métodos ejecutan los
comandos de Selenium, es decir, comandos como *open* o *type*
o *verify*.

Los parámetros necesarios al crear la instancia del navegador
son:

* **host** Especifica la dirección IP de la computadora
donde se encuentra el servidor. Por lo general, esta es la misma
máquina donde se ejecuta el cliente, por lo que en este caso se
pasa *localhost*. En algunos clientes, este es un parámetro
opcional.

* **port** Especifica el socket TCP/IP donde el servidor
está escuchando y esperando que el cliente establezca una
conexión. Esto también es opcional en algunos controladores de
clientes.

* **browser** El navegador en el que deseas ejecutar las
pruebas. Este es un parámetro requerido.

* **url** La url base de la aplicación bajo prueba. Esto es
requerido por todos las librerías de cliente y es información
integral para iniciar la comunicación navegador-proxy-AUT.

Ten en cuenta que algunas de las librerías de cliente
requieren que el navegador se inicie explícitamente llamando a
su método `start()`.

### Ejecutando Comandos

Una vez que tienes el navegador inicializado y asignado a una variable (generalmente llamada "Selenium") puedes hacer que ejecute comandos Selenese llamando los métodos respectivos de la variable del navegador. Por ejemplo, para llamar al método *type* del objeto Selenium:

```
    selenium.type("field-id","string to type")
```

En segundo plano, el navegador realmente realizará una operación *type*,
esencialmente idéntica a la entrada de un usuario escribiendo en el navegador,
utilizando el localizador y la cadena que especificaste durante la llamada al método.

## Reportando Resultados

Selenium RC no tiene su propio mecanismo para reportar
resultados. Más bien, te permite crear tus reportes
personalizados según tus necesidades utilizando las funciones de
tu lenguaje de programación elegido. Eso es genial, pero ¿y si
simplemente quieres algo rápido que ya está hecho para ti? A
menudo, una librería existente o un framework de prueba puede
satisfacer tus necesidades más rápido que desarrollar tu propio
código de reportes de prueba.

### Herramientas de Reportes del Framework de Prueba

Los frameworks de prueba están disponibles para muchos lenguajes de
programación. Estos, junto con su función principal de
proporcionar un motor de prueba flexible para ejecutar tus
pruebas, tambien incluyen código de librerías para reportar resultados.
Por ejemplo, Java tiene dos frameworks de prueba de uso común, JUnit
y TestNG. .NET también tiene el suyo, NUnit.

Aquí no enseñaremos los frameworks por sí mismos; eso está más allá
del alcance de esta guía del usuario. Simplemente presentaremos
las características del framework que se relacionan con Selenium
junto con algunas técnicas que puedes aplicar. Hay buenos libros
disponibles sobre estos frameworks prueba junto con su
información en internet.

### Librerías de Reportes de Pruebas

También están disponibles las librerías de terceros creadas
específicamente para generar los reportes de las pruebas en el
lenguaje de programación elegido. Estos a menudo sportan una
variedad de formatos como HTML o PDF.

### Cuál Es El Mejor Enfoque?

La mayoría de las personas cuando son nuevas en los frameworks
de prueba comenzarán con las funciones de reporteria integradas
en el framework.
A partir de ahí, la mayoría examinará las
librerías disponibles, ya que requiere menos tiempo que
desarrollar una propia.
Cuando comiences a usar Selenium, sin duda
comenzarás a poner tus propias "declaraciones impresas" para
reportar sobre el progreso.
Eso puede llevarte gradualmente a desarrollar tus propios reportes, posiblemente en paralelo al uso de una librería o framework de prueba.
De todos modos, después de la curva de aprendizaje inicial, naturalmente
desarrollarás lo que funciona mejor para tu propia situación.

### Ejemplos de Reportes de Pruebas

Para ilustrarlte, te guiaremos a algunas herramientas específicas
en algunos de los lenguajes soportados por Selenium. Los que se enumeran
aquí son de uso común y los autores de esta guía los han usado ampliamente
(y, por lo tanto, los recomiendan).

#### Reportes de Pruebas en Java

* Si los casos de prueba de Selenium se desarrollan utilizando JUnit,
se puede utilizar JUnit Report para generar reportes de prueba.

* Si los casos de prueba de Selenium se desarrollan usando TestNG,
entonces no se necesita ninguna tarea externa para generar reportes
de prueba.
El framework de TestNG genera un reporte HTML que enumera los detalles
de las pruebas.

* ReportNG es un complemento de reportes HTML para el framework de TestNG.
Está destinado a reemplazar el reporte HTML predeterminado de TestNG.
ReportNG proporciona una vista simple y codificada por colores de los resultados de las pruebas.
  
##### Registrar los Comandos Selenese

* Logging Selenium se puede utilizar para generar un reporte de todos los comandos de Selenese en tu prueba junto con el resultado exitoso o no
de cada uno. Logging Selenium extiende el controlador del cliente Java para agregar esta capacidad de registro de Selenese.

#### Reportes de Pruebas para Python

* Al utilizar Python Client Driver, HTMLTestRunner se puede utilizar para
generar un reporte de prueba.

#### Reportes de Pruebas para Ruby

* Si el framework RSpec se utiliza para escribir los casos de prueba de
Selenium en Ruby, entonces su reporte HTML se puede utilizar para generar
un reporte de prueba.

## Agregando Sabor a Tus Pruebas

Ahora llegaremos a la razón de por que utilizar Selenium RC,
agregando lógica de programación a tus pruebas. Es lo mismo que
para cualquier programa.
El flujo del programa se controla mediante declaraciones de condición e iteración.
Además, puedes reportar la información de progreso mediante I/O.
En esta sección mostraremos algunos ejemplos de cómo las construcciones
del lenguaje de programación se pueden combinar con Selenium para
resolver problemas comunes en las pruebas.

Encontrarás que a medida que transicionas de pruebas simples
como la existencia de elementos en una página, a pruebas de funcionalidad
dinámica que involucran múltiples páginas web y datos variables, que
vas a necesitar la lógica de programación para verificar los resultados
esperados.
Básicamente, Selenium-IDE no soporta declaraciones estándar de iteracion
y de condición.
Puede hacer algunas condiciones incrustando JavaScript en los parámetros
Selenese, sin embargo la iteración es imposible, y la mayoría
de las condiciones serán mucho más fáciles en un lenguaje de
programación.
Además, es posible que necesites manejo de excepciones para
errores de recuperación.
Por esta y otras razones, hemos escrito esta sección para ilustrar el uso de técnicas comunes de programación para brindarte mayor 'poder de verificación' en tus pruebas automatizadas.

Los ejemplos en esta sección están escritos en C # y Java,
aunque el código es simple y se puede adaptar fácilmente otros
lenguajes soportados. Si tienes algún conocimiento básico de un
lenguaje de programación orientado a objetos no deberías tener
dificultades para comprender esta sección.

### Iteración

La iteración es una de las cosas más comunes que las personas
necesitan hacer en sus pruebas.
Por ejemplo, es posible que desees ejecutar una búsqueda varias veces.
O tal vez para verificar los resultados de las pruebas debs procesar un "conjunto de resultados" retornado por una base de datos.

Utilizando el mismo ejemplo anterior de la búsqueda en Google, vamos a verificar los resultados de la búsqueda de Selenium. Esta prueba podría usar Selenese:

|                    |                               |               |
| --------           | ----------------------------  | ------------- |
| open               | /                             |               |
| type               | q                             | selenium rc   |
| clickAndWait       | btnG                          |               |
| assertTextPresent  | Results * for selenium rc     |               |
| type               | q                             | selenium ide  |
| clickAndWait       | btnG                          |               |
| assertTextPresent  | Results * for selenium ide    |               |
| type               | q                             | selenium grid |
| clickAndWait       | btnG                          |               |
| assertTextPresent  | Results * for selenium grid   |               |

El código se ha repetido para ejecutar 3 veces los mismos pasos. Pero varias copias del mismo código no es una buena práctica de programacion ya que es más difícil de mantener. Al usar un lenguaje de programación, podemos iterar sobre los resultados de las búsquedas para obtener una solución más flexible y fácil de mantener.

#### En `C#`   
   
```csharp
   // Collection of String values.
   String[] arr = {"ide", "rc", "grid"};    
        
   // Execute loop for each String in array 'arr'.
   foreach (String s in arr) {
       sel.open("/");
       sel.type("q", "selenium " +s);
       sel.click("btnG");
       sel.waitForPageToLoad("30000");
       assertTrue("Expected text: " +s+ " is missing on page."
       , sel.isTextPresent("Results * for selenium " + s));
    }
```

### Declaraciones de Condición

Para ilustrar el uso de condiciones en las pruebas comenzaremos con un
ejemplo. Un problema común encontrado al ejecutar las pruebas de Selenium
ocurre cuando un elemento esperado no está disponible en la página. Por
ejemplo, al ejecutar la siguiente línea:

```
   selenium.type("q", "selenium " +s);
```
   
Si el elemento 'q' no está en la pagina entonces se lanza una excepción:

```java
   com.thoughtworks.selenium.SeleniumException: ERROR: Element q not found
```

Esto puede hacer que tu prueba sea abortada. Para algunas pruebas eso es lo que quieres. Pero a menudo eso no es deseable ya que tu script de prueba tiene que ejecutar posteriormente muchas otras pruebas.

Un mejor enfoque es validar primero si el elemento está realmente presente
y luego tomar alternativas cuando no lo está. Observemos esto utilizando Java.

```java
   // If element is available on page then perform type operation.
   if(selenium.isElementPresent("q")) {
       selenium.type("q", "Selenium rc");
   } else {
       System.out.printf("Element: " +q+ " is not available on page.")
   }
```

La ventaja de este enfoque es que continua con la ejecución de la prueba, incluso si algunos elementos de la interfaz de usuario no están disponibles en la página.

### Ejecutando JavaScript Desde tus Pruebas

JavaScript es muy útil para utilizar una aplicación que no es directamente compatible con Selenium. El método **getEval** de Selenium API se puede utilizar para ejecutar JavaScript desde Selenium RC.

Considera una aplicación que tiene casillas de verificación sin identificadores estáticos.
En este caso, uno podría evaluar JavaScript desde Selenium RC para obtener los identificadores de todas las casillas de verificación y luego utilizarles.

```java
   public static String[] getAllCheckboxIds () { 
		String script = "var inputId  = new Array();";// Create array in java script.
		script += "var cnt = 0;"; // Counter for check box ids.  
		script += "var inputFields  = new Array();"; // Create array in java script.
		script += "inputFields = window.document.getElementsByTagName('input');"; // Collect input elements.
		script += "for(var i=0; i<inputFields.length; i++) {"; // Loop through the collected elements.
		script += "if(inputFields[i].id !=null " +
		"&& inputFields[i].id !='undefined' " +
		"&& inputFields[i].getAttribute('type') == 'checkbox') {"; // If input field is of type check box and input id is not null.
		script += "inputId[cnt]=inputFields[i].id ;" + // Save check box id to inputId array.
		"cnt++;" + // increment the counter.
		"}" + // end of if.
		"}"; // end of for.
		script += "inputId.toString();" ;// Convert array in to string.			
		String[] checkboxIds = selenium.getEval(script).split(","); // Split the string.
		return checkboxIds;
    }
```

Para contar el número de imágenes en una página:

```java   
   selenium.getEval("window.document.images.length;");
```

Recuerde usar un objeto window en caso de expresiones DOM ya que por
defecto se hace referencia a la ventana de Selenium,
no a la ventana de prueba.

## Opciones del Servidor

Cuando se inicia el servidor,se pueden utilizar opciones de línea
de comando para cambiar el comportamiento predeterminado del servidor.

Recuerda, el servidor se inicia ejecutando lo siguiente.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar
``` 

To see the list of options, run the server with the ``-h`` option.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -h
``` 

Verás una lista de todas las opciones que puedes utilizar con el servidor
y un breve descripción de cada una. Las descripciones proporcionadas no
siempre serán suficientes, así que hemos proporcionado explicaciones
para algunas de las opciones más importantes.

### Configuración del Proxy

Si tu AUT está detrás de un proxy HTTP que requiere autenticación, entonces deberías configurar http.proxyHost, http.proxyPort, http.proxyUser y
http.proxyPassword usando el siguiente comando.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -Dhttp.proxyHost=proxy.com -Dhttp.proxyPort=8080 -Dhttp.proxyUser=username -Dhttp.proxyPassword=password
``` 

### Modo Multi-Window 

Si estás utilizando Selenium 1.0, probablemente puedas omitir esta sección,
ya que el modo de ventanas múltiples es el comportamiento predeterminado.
Sin embargo, antes de la versión 1.0, Selenium ejecutaba la aplicación bajo prueba en un sub-marco como se muestra aquí:

![Single window mode](/images/legacy_docs/selenium_rc_single_window_mode.png)

Algunas aplicaciones no se ejecutaban correctamente en un sub-marcoo y debían ser cargadas en el marco superior de la ventana. La opción modo de ventana múltiple permitia al AUT ejecutarse en una ventana separada en lugar de en el marco predeterminado donde entonces podía tener el marco superior requerido.

![Multiwindow Mode](/images/legacy_docs/selenium_rc_multi_window_mode.png)

Para versiones anteriores de Selenium, debes especificar explícitamente el modo de ventanas múltiples con la siguiente opción:

```bash   
   -multiwindow 
``` 

A partir de Selenium RC 1.0, si deseas ejecutar tu prueba dentro de un
marco único (es decir, utilizando el estándar para versiones anteriores de Selenium) puedes indicar esto al servidor Selenium utilizando la opción

```bash   
   -singlewindow 
``` 

### Especificando el Perfil de Firefox

Firefox no ejecutará dos instancias simultáneamente a menos que especifiques un
perfil separado para cada instancia. Selenium RC 1.0 y posteriores se ejecutan en un perfil separado automáticamente, por lo que si estás utilizando Selenium 1.0, probablemente puedes omitir esta sección. Sin embargo, si estás utilizando una versión anterior de Selenium o si necesitas usar un perfil específico para tus pruebas (como agregar un certificado https o tener algunos complementos instalados), necesitas especificar explícitamente el perfil.

Primero, para crear un perfil separado de Firefox, sigue este procedimiento.
Abre el menú Inicio de Windows, seleccione "Ejecutar", luego escriba e ingrese uno de los siguientes:

```bash   
   firefox.exe -profilemanager 
``` 

```bash   
   firefox.exe -P 
``` 

Crea el nuevo perfil utilizando el cuadro de diálogo. Luego, cuando ejecutes Selenium Server, especificale que use este nuevo perfil de Firefox con la opción de línea de comandos del servidor *\-firefoxProfileTemplate* y especifique la ruta del perfil utilizando el nombre del archivo y la ruta del directorio.

```bash   
   -firefoxProfileTemplate "ruta del perfil" 
``` 

**Advertencia**: Asegúrate de poner tu perfil en una nueva carpeta separada de la predeterminada. La herramienta de administrador de perfiles de Firefox eliminará todos los archivos de una carpeta si eliminas un perfil, independientemente de si son archivos de perfil o no.

Puedes encontrar más información sobre los perfiles de Firefox en la [Base de conocimiento de Mozilla](http://support.mozilla.com/en/kb/Managing+profiles)

### Ejecuta Selenese Directamente Dentro del Servidor Utilizando -htmlSuite

Puedes ejecutar archivos html de Selenese directamente dentro del servidor Selenium pasando el archivo html a la línea de comandso del servidor. Por ejemplo:

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -htmlSuite "*firefox" 
   "http://www.google.com" "c:\absolute\path\to\my\HTMLSuite.html" 
   "c:\absolute\path\to\my\results.html"
``` 

Esto iniciará automáticamente tu suite HTML, ejecutará todas las pruebas y guardará un bonito reporte HTML con los resultados.

  *Nota:* Al usar esta opción, el servidor comenzará las pruebas y
   esperará un número especificado de segundos a que la prueba se
   complete; si la prueba no se completa dentro de ese período de
   tiempo, el comando saldrá con un código distinto de cero y no se
   generará ningún reporte de resultados.

Esta línea de comandos es muy larga, así que ten cuidado cuando
la escribas. Ten en cuenta que esto requiere que pases una suite
HTML Selenese, no una sola prueba. También ten en cuenta que la
opción -htmlSuite es incompatible con ``-interactive`` No puedes
ejecutar ambas al mismo tiempo.

### Registros de Selenium Server

#### Registros del Lado del servidor

Al iniciar el servidor de Selenium, la opción **-log** puede usarse para
grabar informaciónes valiosas de depuración reportadas por el servidor
Selenium a un archivo de texto.

```bash
   java -jar selenium-server-standalone-<version-number>.jar -log selenium.log
```

Este archivo de registro es más detallado que los registros de
consola estándar(incluye mensajes de registro de nivel DEBUG).
El archivo de registro también incluye el nombre del registrador
y la ID del número del hilo que registró el mensaje. Por ejemplo:

```bash   
   20:44:25 DEBUG [12] org.openqa.selenium.server.SeleniumDriverResourceHandler - 
   Browser 465828/:top frame1 posted START NEW
``` 
   
El formato del mensaje es

```bash   
   TIMESTAMP(HH:mm:ss) LEVEL [THREAD] LOGGER - MESSAGE
``` 
   
Este mensaje puede ser de multiples lineas.

#### Registros del Lado del Navegador

JavaScript en el lado del navegador (Selenium Core) también
registra mensajes importantes; en muchos casos, estos pueden ser
más útiles para el usuario final que los registros normales de
Selenium Server. Para acceder a los registros del lado del
navegador, pase el argumento **-browserSideLog** al servidor
Selenium.


```bash
   java -jar selenium-server-standalone-<version-number>.jar -browserSideLog
```

**-browserSideLog** debe combinarse con el argumento **-log**,
para registrar browserSideLogs (así como todos los demás
mensajes de registro de nivel DEBUG) en un archivo.


## Especificando la Ruta a un Navegador Específico

Puede especificarle a Selenium RC una ruta a un navegador
específico.
Esto es útil si tienes diferentes versiones del
mismo navegador y deseas utilizar una en específico.
Además, esto se utiliza para permitir que tus pruebas se ejecuten en un
navegador no directamente apoyado por Selenium RC.
Al especificar el modo de ejecución, use el especificador \*custom
seguido de la ruta completa al ejecutable del navegador:

```bash
   *custom <ruta al navegador>
```

## Arquitectura de Selenium RC

*Nota:* Este tema trata de explicar la implementación técnica
   detrás de Selenium RC. No es fundamental para un usuario de
   Selenium saber esto, pero podría ser útil para comprender
   algunos de los problemas que pueda encontrar en el futuro.

Para comprender en detalle cómo funciona Selenium RC Server y
por qué utiliza la inyección proxy y modos aumentados de
privilegios, primero debes entender `la politica del mismo
origen`_.

### La Política del Mismo Origen

La principal restricción que enfrenta Selenium es la politica del
mismo origen.
Todos los navegadores en el mercado aplican esta
restricción de seguridad y su objetivo es asegurar que el
contenido de un sitio nunca sera accesible por un script desde
otro sitio.
La Política del Mismo Origen dicta que cualquier
código cargado dentro de un navegador solo puede operar dentro
del dominio de ese sitio web.
No puede realizar funciones en otro sitio web.
Entonces, por ejemplo, si el navegador carga el código JavaScript
cuando carga www.mysite.com, no puede ejecutar ese código cargado
contra www.mysite2.com, incluso si ese es otro de tus sitios.
Si esto fuera posible, un script colocado en cualquier sitio web
que abras podría leer información sobre tu cuenta bancaria
si tuviera la página de la cuenta abierta en otra pestaña.
Esto se llama XSS (Cross-site Scripting).

Para trabajar dentro de esta política, Selenium-Core (y sus
comandos JavaScript que hacen que ocurra toda la magia) debe
colocarse en el mismo origen que la Aplicación Bajo prueba
(misma URL).

Históricamente, Selenium-Core estaba limitado por este problema
ya que se implementó en JavaScript Sin embargo, Selenium RC no
está restringido por la Política del Mismo Origen. Su uso de
Selenium Server como proxy evita este problema. Esencialmente,
le dice al navegador quq está trabajando en un único sitio web
"falso" que el servidor proporciona.

*Nota:* Puedes encontrar información adicional sobre este tema
   en paginas Wikipedia sobre la Política del mismo origen y XSS.

### Inyección de Proxy

El primer método utilizado por Selenium para evitar la Política
del Mismo Origen fue la Inyección de Proxy.
En el modo de inyección de proxy, el servidor Selenium actúa como un
**proxy HTTP** configurado por el cliente[^1], que se encuentra
entre el navegador y la aplicación bajo prueba [^2].
Luego enmascara el AUT bajo una URL ficticia (incrustando Selenium-
Core y el conjunto de pruebas y entregandolas como si vinieran del
mismo origen).

[^1]: El proxy es una tercera persona situada en el medio que pasa la
pelota entre las dos partes. Actúa como un "servidor web" que
entrega el AUT al navegador. Ser un proxy le da al Selenium
Server la capacidad de "mentir" sobre la URL real del AUT.  

[^2]: El navegador se inicia con un perfil de configuración que
establece localhost:4444 como el proxy HTTP, es por eso que
cualquier solicitud HTTP que haga el navegador pasará a través
del servidor Selenium y la respuesta pasará a través de él y no
desde el servidor real.

Aquí hay un diagrama arquitectónico.

![Architectural Diagram 1](/images/legacy_docs/selenium_rc_architecture_diagram_1.png)

Cuando comienza un conjunto de pruebas en tu lenguaje favorito,
sucede lo siguiente:

1. El cliente/controlador establece una conexión con el
   servidor selenium-RC.
2. El servidor Selenium RC inicia un navegador (o reutiliza uno antiguo)
   con una URL que inyecta el JavaScript de Selenium-Core en la página webcargada por el navegador.
3. El controlador de cliente pasa un comando Selenese al servidor.
4. El servidor interpreta el comando y luego activa la ejecucion
   JavaScript correspondiente para ejecutar ese comando dentro del
   navegador. Selenium-Core indica al navegador que actúe según esa
   primera instrucción, normalmente abriendo una página del AUT.
5. El navegador recibe la solicitud abierta y solicita el contenido
   proxy HTTP para que lo use el navegador).
6. El servidor Selenium RC se comunica con el servidor web
   solicitando la página y una vez lo recibe, envía la página al
   navegador enmascarando el origen para buscar como si la página
   viniera del mismo servidor que Selenium-Core (esto permite
   a Selenium-Core cumplir con la Política del mismo origen).  
7. El navegador recibe la página web y la muestra en el marco/ventana
   reservada para ello.
   
### Navegadores con Privilegios Aumentados

Este flujo de trabajo en este método es muy similar a la
Inyección de Proxy pero la principal diferencia es que los
navegadores se inician en un modo especial llamado *Privilegios
Aumentados*, que permite a los sitios web hacer cosas que
normalmente no están permitidas (como hacer XSS_ o llenar
entradas de carga de archivos y cosas bastante útiles para
Selenium). Al usar estos modos de navegador, Selenium Core puede
abrir directamente el AUT y leer/interactúar con su contenido
sin tener que pasar todo el AUT a través del servidor Selenium
RC.

Aquí hay un diagrama arquitectónico.

![Architectural Diagram 1](/images/legacy_docs/selenium_rc_architecture_diagram_2.png)

Cuando comienza un conjunto de pruebas en tu lenguaje favorito,
sucede lo siguiente:

1. El cliente/controlador establece una conexión con el servidor
   Selenium-RC.
2. El servidor Selenium RC inicia un navegador (o reutiliza uno antiguo)
   conuna URL eso cargará Selenium-Core en la página web.
3. Selenium-Core obtiene la primera instrucción del cliente/controlador
   (a través de otra solicitud HTTP realizada al servidor Selenium RC)
4. Selenium-Core actúa en esa primera instrucción, generalmente abriendo una
   página del AUT.
5. El navegador recibe la solicitud de apertura y le pide al servidor web
   página. Una vez que el navegador recibe la página web, la muestra en el marco/ventana reservada para ello.

## Manejo de HTTPS y Ventanas Emergentes de Seguridad

Muchas aplicaciones cambian de HTTP a usar HTTPS cuando
necesitan enviar información encriptada como contraseñas o
informaciónes de tarjetas de crédito. Esto es común con muchas de
las aplicaciones web actuales. Selenium RC apoya esto.

Para garantizar que el sitio HTTPS sea genuino, el navegador
necesitará un certificado de seguridad .De lo contrario, cuando el
navegador acceda al AUT usando HTTPS, lo hará suponiendo que la
aplicación no es "confiable". Cuando esto ocurre, el navegador
muestra ventanas emergentes de seguridad, y estas ventanas
emergentes no se pueden cerrar con Selenium RC.

Cuando se trata de HTTPS en una prueba de Selenium RC, debes usar
un modo de ejecución que lo admita y maneje el certificado de
seguridad por ti. Especificas el modo de ejecución
cuando tu programa de prueba inicializa Selenium.

En Selenium RC 1.0 beta 2 y posteriores, utiliza \*firefox o
\*iexplore para el modo de ejecución. En versiones anteriores,
incluida Selenium RC 1.0 beta 1, utiliza \*chrome o \*iehta,
para el modo de ejecución. Con estos modos de ejecución, no
necesitarás instalar ningun certificado de seguridad especial;
Selenium RC lo manejará por ti.

En la versión 1.0, los modos de ejecución \*firefox o
\*iexplore son recomendados. Sin embargo, hay modos de ejecución
adicionales de \*iexploreproxy y \*firefoxproxy.
Estos se proporcionan solo para compatibilidad con versiones anteriores,
y no deben usarse a menos que lo requieran los programas de
prueba heredados.
Su uso lo presentará limitaciones con el manejo del certificado de
seguridad y con la ejecución de varias ventanas si tu aplicación abre
ventanas adicionales del navegador.

En versiones anteriores de Selenium RC, \*chrome o \*iehta
eran los modos de ejecución que soportaban HTTPS y el manejo de
ventanas de seguridad emergentes. Estos fueron considerados
modos ‘experimentales aunque se volvieron bastante estables y
muchas personas los usaron. Si estas usando Selenium 1.0 no
necesitas, y no debes usar, estos modos de ejecución más antiguos.

### Certificados de Seguridad Explicados

Normalmente, tu navegador confiará en la aplicación que está
probando instalando un certificado de seguridad que ya posee.
Puedes verificar esto en las opciones de tu navegador o en
las propiedades de Internet (si no conoces el certificado de
seguridad de tu AUT, consulta al administrador del sistema).
Cuando Selenium carga tu navegador, inyecta código para interceptar
mensajes entre el navegador y el servidor.
El navegador ahora piensa el software no confiable está tratando de
parecerse a su aplicación. Responde alertándote con ventanas emergentes.

Para evitar esto, Selenium RC, (nuevamente cuando se utiliza un
modo de ejecución que permite esto) instalará su propio
certificado de seguridad, temporalmente, en tu máquina cliente
en un lugar donde el navegador pueda acceder a ella. Esto engaña
al navegador haciendole creer que está accediendo a un sitio
diferente de su AUT y suprime efectivamente las ventanas
emergentes.

Otro método utilizado con versiones anteriores de Selenium era
instalar el certificado de seguridad de Cybervillians incluido
con tu instalación de Selenium. Sin embargo, la mayoría de los
usuarios ya no deberían de necesitar hacer esto; si estas
ejecutando Selenium RC en modo de inyección proxy, es posible
que debas instalar explícitamente este certificado de seguridad.


## Soportando Navegadores y Configuraciones de Navegador Adicionales

La API de Selenium permite la ejecución en múltiples navegadores
además de Internet Explorer y Mozilla Firefox.
Consulta el sitio web https://selenium.dev para ver los navegadores compatibles.
Además, cuando un navegador no es directamente compatible, aún puede
ejecutar tus pruebas de Selenium en un navegador de tu elección
usando el modo de ejecución "\*custom" (es decir, en lugar de \*firefox
o \*iexplore) cuando tu aplicación de prueba inicia el navegador.
Con esto,pasas la ruta a los navegadores ejecutables dentro de la llamada
al API. Esto también se puede hacer desde el Servidor en modo
interactivo.

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\MyBrowser.exe&2=http://www.google.com
```

### Ejecución de Pruebas con Diferentes Configuraciones de Navegador

Normalmente, Selenium RC configura automáticamente el navegador,
pero si inicias el navegador que utilizando modo de ejecución
"\*custom", puedes forzar a Selenium RC a iniciar el navegador
tal cual, sin usar una configuración automática.

Por ejemplo, puedes iniciar Firefox con una configuración
personalizada como esta:

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\firefox.exe&2=http://www.google.com
```

Ten en cuenta que al iniciar el navegador de esta manera, debes
configurar el navegador para que use el servidor Selenium como
un proxy. Normalmente esto solo significa abrir las preferencias
del navegador y especificar "localhost:4444" como un proxy
HTTP, pero las instrucciones para esto pueden diferir
radicalmente de un navegador a otro. Consulte la
documentación de tu navegador para más detalles.

Ten en cuenta que los navegadores Mozilla pueden variar en
cómo se inician y se detienen. Es posible que sea necesario
configurar la variable de entorno MOZ_NO_REMOTE para hacer
que los navegadores Mozilla se comporten un poco más previsiblemente.
Los usuarios de Unix deben evitar iniciar el navegador usando un
script de shell; generalmente es mejor usar el ejecutable
binario (por ejemplo, firefox-bin) directamente.


## Solucionando Problemas Comunes

Al comenzar con Selenium RC, potencialmente hay algunos problemas
que comúnmente se pueden encontrar. Aquí los presentamos junto con sus
soluciones .

### Incapaz de Conectarse al Servidor

Cuando tu programa de prueba no puede conectarse al servidor
Selenium, Selenium lanza una excepción en tu programa de prueba.
Debería mostrar este mensaje o uno similar:

```bash
    "Unable to connect to remote server (Inner Exception Message: 
	No connection could be made because the target machine actively 
	refused it )"
    
	(using .NET and XP Service Pack 2) 
```

Si ves un mensaje como este, asegúrate de haber iniciado Selenium
Server. Si es asi, entonces hay un problema con la conectividad entre
la Librería Selenium Client y el servidor Selenium.

Al comenzar con Selenium RC, la mayoría de las personas
comienzan ejecutando su programa de prueba (con una Librería
Cliente de Selenium) y el servidor Selenium en la misma máquina.
Para hacer esto utiliza "localhost" como parámetro de conexión.
Recomendamos comenzar de esta manera, ya que reduce la influencia
de posibles problemas de red  ya que estás empezando. Asumiendo que tu
sistema operativo tiene una red típica y la configuración de TCP/IP
debería tener poca dificultad. En verdad, mucha gente elige
ejecutar las pruebas de esta manera.

Sin embargo, si desea ejecutar Selenium Server en una máquina
remota, la conectividad debería estar bien suponiendo que tenga
una conectividad TCP/IP válida entre las dos máquinas.

Si tiene dificultades para conectarse, puedes usar herramientas
de red comunes como *ping*, *telnet*, *ifconfig(Unix)/ipconfig*
(Windows), etc. para garantizar que tenga una conexión de red.
Si no estás familiarizado con estos, el administrador del sistema
puede ayudarte.
 
### Incapaz de Cargar el Navegador 

Ok, no es un mensaje de error amigable, lo siento, pero si el
servidor Selenium no puede cargar el navegador es probable que
veas este error.

```bash
    (500) Internal Server Error
```

Esto podria ser causado por

* Firefox (anterior a Selenium 1.0) no puede iniciarse porque el
  navegador ya está abierto y no espicificaste un perfil separado.
  Consulte la sección sobre perfiles de Firefox en Opciones del
  Servidor.
* El modo de ejecución que está utilizando no coincide con ningún
  navegador en su máquina. Comprueba los parámetros que pasó a Selenium
  cuando tu programa abre el navegador.
* Espicificaste la ruta al navegador explícitamente (usando
  "\*custom" -ver arriba) pero la ruta es incorrecta. Verifique
  que la ruta sea correcta.Tambien comprueba el grupo de usuarios
  para asegurarte de que no haya ningun problema conocids con tu
  navegador y los parámetros "\*custom".

### Selenium no Puede Encontrar el AUT

Si tu programa de prueba inicia el navegador con éxito, pero el
navegador no muestra el sitio web que está probando, la causa
más probable es que tu programa de prueba no está utilizando la URL
correcta.

Esto puede suceder fácilmente. Cuando uitlizas Selenium-IDE para
exportar tu script, este inserta una URL ficticia. Debes cambiar
manualmente la URL a la correcta para que tu aplicación sea
probada.

### Firefox Rechazó Apagarse Mientras Preparaba un Perfil

Esto ocurre con mayor frecuencia cuando ejecutas tu programa de
pruebas de Selenium RC contra Firefox, pero ya tienes una sesión del
navegador Firefox ejecutándose y no especificaste un perfil
separado cuando inició el servidor Selenium. El error del
programa de prueba se parece a esto:

```bash
    Error:  java.lang.RuntimeException: Firefox refused shutdown while 
    preparing a profile 
```

Aqui está el error completo del servidor:

```bash
    16:20:03.919 INFO - Preparing Firefox profile... 
    16:20:27.822 WARN - GET /selenium-server/driver/?cmd=getNewBrowserSession&1=*fir 
    efox&2=http%3a%2f%2fsage-webapp1.qa.idc.com HTTP/1.1 
    java.lang.RuntimeException: Firefox refused shutdown while preparing a profile 
            at org.openqa.selenium.server.browserlaunchers.FirefoxCustomProfileLaunc 
    her.waitForFullProfileToBeCreated(FirefoxCustomProfileLauncher.java:277) 
    ... 
    Caused by: org.openqa.selenium.server.browserlaunchers.FirefoxCustomProfileLaunc 
    her$FileLockRemainedException: Lock file still present! C:\DOCUME~1\jsvec\LOCALS 
    ~1\Temp\customProfileDir203138\parent.lock 
```

Para resolver esto, ve a la sección Especificando el Perfil de Firefox

### Problemas de Versionamiento

Asegúrate de que tu versión de Selenium sea compatible con la
versión de tu navegador. Por ejemplo, Selenium RC 0.92 no es
compatible con Firefox 3. A veces puedes ser afortunado (Yo lo era).
Pero no olvides comprobar qué las versiones del navegador son
compatibles con la versión de Selenium que estás utilizando.
Cuando estes en duda, utiliza la última versión de lanzamiento de Selenium
con la versión más utilizada de su navegador

### Mensaje de Error: "(Unsupported major.minor version 49.0)" Iniciando el Servidor

Este error dice que no estás utilizando una versión correcta de Java.
El servidor Selenium requiere Java 1.5 ó superior.

Para verificar tu versión de Java, ejecuta esto desde la línea
de comandos.

```bash
   java -version
```

Deberias ver un mensaje mostrando la version de Java.

```bash
   java version "1.5.0_07"
   Java(TM) 2 Runtime Environment, Standard Edition (build 1.5.0_07-b03)
   Java HotSpot(TM) Client VM (build 1.5.0_07-b03, mixed mode)
```

Si ves un número de versión inferior, es posible que debas actualizar
el JRE, o simplemente necesitas agregarlo a tu variable de entorno PATH.

### Error 404 ejecutando el comando getNewBrowserSession

Si recibes un error 404 al intentar abrir una página en
"http://www.google.com/selenium-server/", entonces debe ser porque el
Selenium Server no se configuró correctamente como proxy.
El directorio “selenium-server” no existe en google.com; solo
parece existir cuando el proxy es debidamente configurado.
La configuración del proxy depende en gran medida de cómo es
lanzado el navegador con Firefox, iexplore, Opera o custom.

* iexplore: si el navegador se inicia usando \*iexplore, podrías
tener un problema con la configuración del proxy de Internet
Explorer.
Selenium Server intenta configurar la configuración
global del proxy en el Panel de Control de Opciones de Internet.
Debes asegurarte de que estén correctamente configurados cuando
Selenium Server inicia el navegador. Intenta mirar en tu panel
de control de Opciones de Internet. Haz clic en la pestaña
"Conexiones" y haz clic en "Configuración de LAN".

  * Si necesitas usar un proxy para acceder a la aplicación que
  deseas probar, deberás iniciar Selenium Server con
  "-Dhttp.proxyHost"; consulta la `Configuración de proxy`_ para
  obtener más detalles.
  * También puedes intentar configurar tu proxy manualmente y luego
  iniciar el navegador con \*custom, o con el lanzador de navegadores \*iehta.

* custom: cuando se usa \*custom, debes configurar el proxy
correctamente (manualmente), de lo contrario, obtendrás un error 404.
Verifica que hayas configuraste tus ajustes de proxy
correctamente. Para verificar si se ha configurado el proxy
correctamente intenta configurar intencionalmente el navegador
de manera incorrecta. Intenta configurar el navegador para
utilizar el nombre de host del servidor proxy incorrecto o el
puerto incorrecto. Si configuraste correctamente la
configuración del proxy incorrecto del navegador, entonces el
navegador no podrá conectarse a Internet, lo que es una de las
formas de asegurarse de que uno está ajustando las
configuraciones relevantes.

* Para otros navegadores (\*firefox, \*opera) automáticamente
codificamos el proxy por ti, por lo que no hay problemas
conocidos con esta funcionalidad. Si encuentras errores 404 y
has seguido cuidadosamente esta guía del usuario publica tus
resultados en el grupo de usuarios para obtener ayuda de la
comunidad de usuarios.

### Error Permiso Denegado

La razón más común para este error es que tu sesión intenta
violar la política del mismo origen al cruzar los límites
del dominio (por ejemplo, accede a una página desde
http://dominio1 y luego accede a una página desde http://dominio2)
o al cambiar de protocolos (pasar de http://dominioX a https:// dominioX).

Este error también puede ocurrir cuando JavaScript intenta
encontrar objetos de la IU que aún no están disponibles (antes de
que la página haya cargado completamente), o ya no están
disponibles (después de que la página ha comenzado a descargarse).
Esto se encuentra con mayor frecuencia con páginas AJAX que
trabajan con secciones de una página o subtramas que se cargan y/o
recargan independientemente de la página.

Este error puede ser intermitente. A menudo es imposible
reproducir el problema con un depurador porque el problema surge
por condiciones de carrera que no son reproducibles cuando la
sobrecarga del depurador se agrega al sistema. Problemas de
permisos están cubiertos con algún detalle en el tutorial.
Lee la sección sobre la `La Politica del Mismo Origen`_,
y tambien la sección `Inyección de proxy`_ cuidadosamente.


### Manejo de Ventanas Emergentes del Navegador

Hay varios tipos de "ventanas emergentes" que puedse obtener
durante una prueba de Selenium. Es posible que no puedas cerrar
estas ventanas emergentes ejecutando comandos de Selenium si son
iniciados por el navegador y no por tu AUT. Puedes necesitar
saber cómo gestionarlos. Cada tipo de ventana emergente debe
abordarse de manera diferente.

* Diálogos de autenticación básica HTTP: estos diálogos solicitan
  un nombre de usuario/contraseña para iniciar sesión en el sitio.
  Para iniciar sesión en un sitio que requiere autenticación HTTP básica,
  use un nombre de usuario y contraseña en la URL, como esta descrito en
  `RFC 1738`_, así: open("http://myusername:myuserpassword@myexample.com/blah/blah/blah").

* Advertencias del certificado SSL: Selenium RC intenta
  falsificar automáticamente los certificados SSL cuando está
  habilitado como proxy; ver más sobre esto en la sección sobre
  HTTPS. Si tu navegador está configurado correctamente, nunca
  deberías ver las advertencias del certificado SSL, pero es
  posible que debas configursr tu navegador para confiar en nuestra
  autoridad peligrosa de certificado SSL "CyberVillains".
  Nuevamente, consulte la sección HTTPS para saber cómo hacer esto.

* cuadros de diálogo de alerta/confirmación/aviso modal de
  JavaScript: Selenium intenta ocultarte esos diálogos
  (reemplazando window.alert, window.confirm y window.prompt)
  para que no detengan la ejecución de tu página. Si ves una
  ventana de alerta emergente, probablemente se deba a que se
  disparó durante el proceso de carga de la página, que suele ser
  demasiado pronto para que podamos proteger la página. Selenese
  contiene comandos para afirmar o verificar alertas y ventanas
  emergentes de confirmación. Ver las secciones sobre estos temas
  en el Capítulo 4.

### En Linux, ¿por qué no se cierra la sesión de mi navegador Firefox?

En Unix/Linux debes invocar "firefox-bin" directamente, así que
asegúrate de que el ejecutable está en la ruta. Si ejecutas
Firefox a través de un script de shell, cuando llega el momento
de matar el navegador Selenium RC matará el script de shell,
dejando el navegador en ejecución. Puedes especificar la ruta a
firefox-bin directamente, de esta forma.

```bash
   cmd=getNewBrowserSession&1=*firefox /usr/local/firefox/firefox-bin&2=http://www.google.com
```

### Firefox \*chrome no funciona con un perfil personalizado

Verifica el folder del perfil Firefox ->
prefs.js -> user_pref("browser.startup.page", 0);
Comenta esta linea de esta forma:
"//user_pref("browser.startup.page", 0);"
e intenta de nuevo.

### Está bien cargar una ventana emergente personalizada mientras se carga la página principal (es decir, antes de que se ejecute la función JavaScript window.onload() de la página principal)?

No. Selenium depende de los interceptores para determinar los
nombres de las ventanas a medida que se cargan. Estos
interceptores funcionan mejor en la captura de nuevas ventanas
si las ventanas se cargan DESPUÉS la función onload(). Selenium
pueda que no reconozca ventanas cargadas antes de la función onload.
  
### Firefox en Linux

En Unix/Linux, las versiones de Selenium anteriores a la 1.0
necesitaban invocar "firefox-bin" directamente, por lo que si
estás utilizando una versión anterior, asegúrate de que el
ejecutable verdadero está en la ruta.

En la mayoría de las distribuciones de Linux, el verdadero
*firefox-bin* se encuentra en:

```bash
   /usr/lib/firefox-x.x.x/
```

Donde x.x.x es el número de la versión que tiene actualmente.
Entonces, para agregar esa ruta a la ruta del usuario, deberás
agregar lo siguiente a tu archivo .bashrc:

```bash
   export PATH="$PATH:/usr/lib/firefox-x.x.x/"
```

Si es necesario, puedes especificar la ruta a firefox-bin
directamente en tu prueba, de esta forma:

```bash
   "*firefox /usr/lib/firefox-x.x.x/firefox-bin"
```

### IE y Atributos de Estilo

Si estás ejecutando tus pruebas en Internet Explorer y no puedes
localizar elementos que usan tu atributo `style`. Por ejemplo:

```bash
    //td[@style="background-color:yellow"]
```

Esto funcionaría perfectamente en Firefox, Opera o Safari
pero no con IE. IE interpreta las teclas en `@style` como
mayúsculas. Entonces, incluso si el código fuente está en
minúsculas, debes usar:

```bash
    //td[@style="BACKGROUND-COLOR:yellow"]
```

Esto es un problema si tu prueba está diseñada para funcionar en
múltiples navegadores, pero puedes codificar fácilmente tu prueba
para detectar la situación y probar el localizador alternativo
que solo funciona en IE.

### Error encontrado - "Cannot convert object to primitive value" con el apagado del navegador *googlechrome

Para evitar este error, debes iniciar el navegador con una opción
que deshabilite las mismas verificaciones de políticas de origen:

```bash
   selenium.start("commandLineFlags=--disable-web-security");
```
   

### Error encontrado en IE - "Couldn't open app window; is the pop-up blocker enabled?"

Para evitar este error, debes configurar el navegador: desactiva
el bloqueador de ventanas emergentes Y desmarque la opción
'Habilitar modo protegido' en Herramientas >> Opciones >>
Seguridad.
