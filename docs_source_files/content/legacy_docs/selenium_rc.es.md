---
title: "Selenium 1 (Selenium RC)"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Spanish. Do you speak Spanish? Help us to translate
it by sending us pull requests!
{{% /notice %}}

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
Puede hacer algunas condiciones incrustando javascript en los parámetros
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

### Executing JavaScript from Your Test

JavaScript comes very handy in exercising an application which is not directly supported
by selenium. The **getEval** method of Selenium API can be used to execute JavaScript from
Selenium RC. 

Consider an application having check boxes with no static identifiers. 
In this case one could evaluate JavaScript from Selenium RC to get ids of all 
check boxes and then exercise them. 

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

To count number of images on a page:

```java   
   selenium.getEval("window.document.images.length;");
```

Remember to use window object in case of DOM expressions as by default selenium
window is referred to, not the test window.

## Server Options

When the server is launched, command line options can be used to change the
default server behaviour.

Recall, the server is started by running the following.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar
``` 

To see the list of options, run the server with the ``-h`` option.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -h
``` 

You'll see a list of all the options you can use with the server and a brief
description of each. The provided descriptions will not always be enough, so we've
provided explanations for some of the more important options.


### Proxy Configuration

If your AUT is behind an HTTP proxy which requires authentication then you should 
configure http.proxyHost, http.proxyPort, http.proxyUser and http.proxyPassword
using the following command. 

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -Dhttp.proxyHost=proxy.com -Dhttp.proxyPort=8080 -Dhttp.proxyUser=username -Dhttp.proxyPassword=password
``` 

### Multi-Window Mode

If you are using Selenium 1.0 you can probably skip this section, since multiwindow mode is 
the default behavior.  However, prior to version 1.0, Selenium by default ran the 
application under test in a sub frame as shown here.

![Single window mode](/images/legacy_docs/selenium_rc_single_window_mode.png)

Some applications didn't run correctly in a sub frame, and needed to be 
loaded into the top frame of the window. The multi-window mode option allowed
the AUT to run in a separate window rather than in the default 
frame where it could then have the top frame it required.

![Multiwindow Mode](/images/legacy_docs/selenium_rc_multi_window_mode.png)

For older versions of Selenium you must specify multiwindow mode explicitly
with the following option:

```bash   
   -multiwindow 
``` 

As of Selenium RC 1.0, if you want to run your test within a
single frame (i.e. using the standard for earlier Selenium versions) 
you can state this to the Selenium Server using the option

```bash   
   -singlewindow 
``` 

### Specifying the Firefox Profile

Firefox will not run two instances simultaneously unless you specify a 
separate profile for each instance. Selenium RC 1.0 and later runs in a 
separate profile automatically, so if you are using Selenium 1.0, you can 
probably skip this section.  However, if you're using an older version of 
Selenium or if you need to use a specific profile for your tests
(such as adding an https certificate or having some addons installed), you will 
need to explicitly specify the profile. 

First, to create a separate Firefox profile, follow this procedure.
Open the Windows Start menu, select "Run", then type and enter one of the 
following:

```bash   
   firefox.exe -profilemanager 
``` 

```bash   
   firefox.exe -P 
``` 

Create the new profile using the dialog. Then when you run Selenium Server, 
tell it to use this new Firefox profile with the server command-line option 
*\-firefoxProfileTemplate* and specify the path to the profile using its filename 
and directory path.

```bash   
   -firefoxProfileTemplate "path to the profile" 
``` 

**Warning**:  Be sure to put your profile in a new folder separate from the default!!! 
   The Firefox profile manager tool will delete all files in a folder if you 
   delete a profile, regardless of whether they are profile files or not. 
   
More information about Firefox profiles can be found in [Mozilla's Knowledge Base](http://support.mozilla.com/en/kb/Managing+profiles)

### Run Selenese Directly Within the Server Using -htmlSuite

You can run Selenese html files directly within the Selenium Server
by passing the html file to the server's command line.  For instance:

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -htmlSuite "*firefox" 
   "http://www.google.com" "c:\absolute\path\to\my\HTMLSuite.html" 
   "c:\absolute\path\to\my\results.html"
``` 

This will automatically launch your HTML suite, run all the tests and save a
nice HTML report with the results.

*Note:*  When using this option, the server will start the tests and wait for a
   specified number of seconds for the test to complete; if the test doesn't 
   complete within that amount of time, the command will exit with a non-zero 
   exit code and no results file will be generated.

This command line is very long so be careful when 
you type it. Note this requires you to pass in an HTML 
Selenese suite, not a single test. Also be aware the -htmlSuite option is incompatible with ``-interactive``
You cannot run both at the same time.

### Selenium Server Logging

#### Server-Side Logs

When launching Selenium server the **-log** option can be used to record
valuable debugging information reported by the Selenium Server to a text file.

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -log selenium.log
``` 
   
This log file is more verbose than the standard console logs (it includes DEBUG 
level logging messages). The log file also includes the logger name, and the ID
number of the thread that logged the message. For example:   

```bash   
   20:44:25 DEBUG [12] org.openqa.selenium.server.SeleniumDriverResourceHandler - 
   Browser 465828/:top frame1 posted START NEW
``` 
   
The message format is 

```bash   
   TIMESTAMP(HH:mm:ss) LEVEL [THREAD] LOGGER - MESSAGE
``` 
   
This message may be multiline.

#### Browser-Side Logs

JavaScript on the browser side (Selenium Core) also logs important messages; 
in many cases, these can be more useful to the end-user than the regular Selenium 
Server logs. To access browser-side logs, pass the **-browserSideLog**
argument to the Selenium Server.


```bash   
   java -jar selenium-server-standalone-<version-number>.jar -browserSideLog
``` 
   
**-browserSideLog** must be combined with the **-log** argument, to log 
browserSideLogs (as well as all other DEBUG level logging messages) to a file.


## Specifying the Path to a Specific Browser 

You can specify to Selenium RC a path to a specific browser. This is useful if 
you have different versions of the same browser and you wish to use a specific
one. Also, this is used to allow your tests to run against a browser not 
directly supported by Selenium RC. When specifying the run mode, use the 
\*custom specifier followed by the full path to the browser's executable:

```bash   
   *custom <path to browser> 
``` 

   
## Selenium RC Architecture

*Note:* This topic tries to explain the technical implementation behind 
   Selenium RC. It's not fundamental for a Selenium user to know this, but 
   could be useful for understanding some of the problems you might find in the
   future.
   
To understand in detail how Selenium RC Server works  and why it uses proxy injection
and heightened privilege modes you must first understand `the same origin policy`_.
   
### The Same Origin Policy

The main restriction that Selenium faces is the 
Same Origin Policy. This security restriction is applied by every browser
in the market and its objective is to ensure that a site's content will never
be accessible by a script from another site.  The Same Origin Policy dictates that
any code loaded within the browser can only operate within that website's domain.
It cannot perform functions on another website.  So for example, if the browser
loads JavaScript code when it loads www.mysite.com, it cannot run that loaded code
against www.mysite2.com--even if that's another of your sites. If this were possible, 
a script placed on any website you open would be able to read information on 
your bank account if you had the account page
opened on other tab. This is called XSS (Cross-site Scripting).

To work within this policy, Selenium-Core (and its JavaScript commands that
make all the magic happen) must be placed in the same origin as the Application
Under Test (same URL). 

Historically, Selenium-Core was limited by this problem since it was implemented in
JavaScript.  Selenium RC is not, however, restricted by the Same Origin Policy.  Its 
use of the Selenium Server as a proxy avoids this problem.  It, essentially, tells the 
browser that the browser is working on a single "spoofed" website that the Server
provides. 

*Note:* You can find additional information about this topic on Wikipedia
   pages about Same Origin Policy and XSS. 


### Proxy Injection

The first method Selenium used to avoid the The Same Origin Policy was Proxy Injection.
In Proxy Injection Mode, the Selenium Server acts as a client-configured **HTTP 
proxy**[^1], that sits between the browser and the Application Under Test[^2].
It then masks the AUT under a fictional URL (embedding
Selenium-Core and the set of tests and delivering them as if they were coming
from the same origin). 

[^1]: The proxy is a third person in the middle that passes the ball between the two parts. It acts as a "web server" that delivers the AUT to the browser. Being a proxy gives Selenium Server the capability of "lying" about the AUT's real URL.  
   
[^2]: The browser is launched with a configuration profile that has set localhost:4444 as the HTTP proxy, this is why any HTTP request that the browser does will pass through Selenium server and the response will pass through it and not from the real server.

Here is an architectural diagram. 

![Architectural Diagram 1](/images/legacy_docs/selenium_rc_architecture_diagram_1.png)

As a test suite starts in your favorite language, the following happens:

1. The client/driver establishes a connection with the selenium-RC server.
2. Selenium RC server launches a browser (or reuses an old one) with a URL 
   that injects Selenium-Core's JavaScript into the browser-loaded web page.
3. The client-driver passes a Selenese command to the server.
4. The Server interprets the command and then triggers the corresponding 
   JavaScript execution to execute that command within the browser.
   Selenium-Core instructs the browser to act on that first instruction, typically opening a page of the
   AUT.
5. The browser receives the open request and asks for the website's content from
   the Selenium RC server (set as the HTTP proxy for the browser to use).
6. Selenium RC server communicates with the Web server asking for the page and once
   it receives it, it sends the page to the browser masking the origin to look
   like the page comes from the same server as Selenium-Core (this allows 
   Selenium-Core to comply with the Same Origin Policy).
7. The browser receives the web page and renders it in the frame/window reserved
   for it.
   
### Heightened Privileges Browsers

This workflow in this method is very similar to Proxy Injection but the main
difference is that the browsers are launched in a special mode called *Heightened
Privileges*, which allows websites to do things that are not commonly permitted
(as doing XSS_, or filling file upload inputs and pretty useful stuff for 
Selenium). By using these browser modes, Selenium Core is able to directly open
the AUT and read/interact with its content without having to pass the whole AUT
through the Selenium RC server.

Here is the architectural diagram. 

![Architectural Diagram 1](/images/legacy_docs/selenium_rc_architecture_diagram_2.png)

As a test suite starts in your favorite language, the following happens:

1. The client/driver establishes a connection with the selenium-RC server.
2. Selenium RC server launches a browser (or reuses an old one) with a URL 
   that will load Selenium-Core in the web page.
3. Selenium-Core gets the first instruction from the client/driver (via another 
   HTTP request made to the Selenium RC Server).
4. Selenium-Core acts on that first instruction, typically opening a page of the
   AUT.
5. The browser receives the open request and asks the Web Server for the page.
   Once the browser receives the web page, renders it in the frame/window reserved
   for it.

## Handling HTTPS and Security Popups 

Many applications switch from using HTTP to HTTPS when they need to send 
encrypted information such as passwords or credit card information. This is 
common with many of today's web applications. Selenium RC supports this. 

To ensure the HTTPS site is genuine, the browser will need a security 
certificate. Otherwise, when the browser accesses the AUT using HTTPS, it will
assume that application is not 'trusted'. When this occurs the browser
displays security popups, and these popups cannot be closed using Selenium RC. 

When dealing with HTTPS in a Selenium RC test, you must use a run mode that supports this and handles
the security certificate for you. You specify the run mode when your test program
initializes Selenium. 

In Selenium RC 1.0 beta 2 and later use \*firefox or \*iexplore for the run 
mode. In earlier versions, including Selenium RC 1.0 beta 1, use \*chrome or 
\*iehta, for the run mode. Using these run modes, you will not need to install
any special security certificates; Selenium RC will handle it for you.

In version 1.0 the run modes \*firefox or \*iexplore are 
recommended. However, there are additional run modes of \*iexploreproxy and 
\*firefoxproxy. These are provided for backwards compatibility only, and 
should not be used unless required by legacy test programs. Their use will 
present limitations with security certificate handling and with the running 
of multiple windows if your application opens additional browser windows. 

In earlier versions of Selenium RC, \*chrome or \*iehta were the run modes that 
supported HTTPS and the handling of security popups. These were considered ‘experimental
modes although they became quite stable and many people used them.  If you are using
Selenium 1.0 you do not need, and should not use, these older run modes.

### Security Certificates Explained

Normally, your browser will trust the application you are testing
by installing a security certificate which you already own. You can 
check this in your browser's options or Internet properties (if you don't 
know your AUT's security certificate ask your system administrator). 
When Selenium loads your browser it injects code to intercept 
messages between the browser and the server. The browser now thinks 
untrusted software is trying to look like your application.  It responds by alerting you with popup messages. 

To get around this, Selenium RC, (again when using a run mode that support 
this) will install its own security certificate, temporarily, to your 
client machine in a place where the browser can access it. This tricks the 
browser into thinking it's accessing a site different from your AUT and effectively suppresses the popups.  

Another method used with earlier versions of Selenium was to 
install the Cybervillians security certificate provided with your Selenium 
installation. Most users should no longer need to do this however; if you are
running Selenium RC in proxy injection mode, you may need to explicitly install this
security certificate. 


## Supporting Additional Browsers and Browser Configurations

The Selenium API supports running against multiple browsers in addition to 
Internet Explorer and Mozilla Firefox.  See the https://selenium.dev website for
supported browsers.  In addition, when a browser is not directly supported,
you may still run your Selenium tests against a browser of your choosing by
using the "\*custom" run-mode (i.e. in place of \*firefox or \*iexplore) when 
your test application starts the browser.  With this, you pass in the path to
the browsers executable within the API call. This can also be done from the 
Server in interactive mode.

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\MyBrowser.exe&2=http://www.google.com
```


### Running Tests with Different Browser Configurations

Normally Selenium RC automatically configures the browser, but if you launch 
the browser using the "\*custom" run mode, you can force Selenium RC
to launch the browser as-is, without using an automatic configuration.

For example, you can launch Firefox with a custom configuration like this:

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\firefox.exe&2=http://www.google.com
```

Note that when launching the browser this way, you must manually 
configure the browser to use the Selenium Server as a proxy. Normally this just 
means opening your browser preferences and specifying "localhost:4444" as 
an HTTP proxy, but instructions for this can differ radically from browser to 
browser.  Consult your browser's documentation for details.

Be aware that Mozilla browsers can vary in how they start and stop. 
One may need to set the MOZ_NO_REMOTE environment variable to make Mozilla browsers 
behave a little more predictably. Unix users should avoid launching the browser using 
a shell script; it's generally better to use the binary executable (e.g. firefox-bin) directly.

   
## Troubleshooting Common Problems

When getting started with Selenium RC there's a few potential problems
that are commonly encountered.  We present them along with their solutions here.

### Unable to Connect to Server 

When your test program cannot connect to the Selenium Server, Selenium throws an exception in your test program. 
It should display this message or a similar one:

```bash
    "Unable to connect to remote server (Inner Exception Message: 
	No connection could be made because the target machine actively 
	refused it )"
    
	(using .NET and XP Service Pack 2) 
```

If you see a message like this, be sure you started the Selenium Server. If 
so, then there is a problem with the connectivity between the Selenium Client 
Library and the Selenium Server. 

When starting with Selenium RC, most people begin by running their test program
(with a Selenium Client Library) and the Selenium Server on the same machine.  To
do this use "localhost" as your connection parameter.
We recommend beginning this way since it reduces the influence of potential networking problems
which you're getting started.  Assuming your operating system has typical networking
and TCP/IP settings you should have little difficulty.  In truth, many people
choose to run the tests this way.  

If, however, you do want to run Selenium Server
on a remote machine, the connectivity should be fine assuming you have valid TCP/IP
connectivity between the two machines.    

If you have difficulty connecting, you can use common networking tools like *ping*,
*telnet*, *ifconfig(Unix)/ipconfig* (Windows), etc to ensure you have a valid 
network connection.  If unfamilar with these, your system administrator can assist you.
 
### Unable to Load the Browser 

Ok, not a friendly error message, sorry, but if the Selenium Server cannot load the browser 
you will likely see this error.

```bash
    (500) Internal Server Error
```

This could be caused by

* Firefox (prior to Selenium 1.0) cannot start because the browser is already open and you did 
  not specify a separate profile.   See the section on Firefox profiles under Server Options.
* The run mode you're using doesn't match any browser on your machine.  Check the parameters you 
  passed to Selenium when you program opens the browser. 
* You specified the path to the browser explicitly (using "\*custom"--see above) but the path is 
  incorrect.  Check to be sure the path is correct.  Also check the user group to be sure there are
  no known issues with your browser and the "\*custom" parameters.

### Selenium Cannot Find the AUT 

If your test program starts the browser successfully, but the browser doesn't
display the website you're testing, the most likely cause is your test 
program is not using the correct URL. 

This can easily happen. When you use Selenium-IDE to export your script,
it inserts a dummy URL. You must manually change the URL to the correct one
for your application to be tested. 

### Firefox Refused Shutdown While Preparing a Profile 

This most often occurs when you run your Selenium RC test program against Firefox,
but you already have a Firefox browser session running and, you didn't specify
a separate profile when you started the Selenium Server. The error from the 
test program looks like this:

```bash
    Error:  java.lang.RuntimeException: Firefox refused shutdown while 
    preparing a profile 
```

Here's the complete error message from the server:

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

To resolve this, see the section on Specifying a Separate Firefox Profile

### Versioning Problems 

Make sure your version of Selenium supports the version of your browser. For
example, Selenium RC 0.92 does not support Firefox 3. At times you may be lucky
(I was). But don't forget to check which
browser versions are supported by the version of Selenium you are using. When in
doubt, use the latest release version of Selenium with the most widely used version
of your browser.

### Error message: "(Unsupported major.minor version 49.0)" while starting server

This error says you're not using a correct version of Java. 
The Selenium Server requires Java 1.5 or higher. 

To check double-check your java version, run this from the command line.

```bash
   java -version
```

You should see a message showing the Java version.

```bash
   java version "1.5.0_07"
   Java(TM) 2 Runtime Environment, Standard Edition (build 1.5.0_07-b03)
   Java HotSpot(TM) Client VM (build 1.5.0_07-b03, mixed mode)
```

If you see a lower version number, you may need to update the JRE,
or you may simply need to add it to your PATH environment variable.


### 404 error when running the getNewBrowserSession command

If you're getting a 404 error while attempting to open a page on 
"http://www.google.com/selenium-server/", then it must be because the Selenium
Server was not correctly configured as a proxy. The "selenium-server" directory 
doesn't exist on google.com; it only appears to exist when the proxy is 
properly configured. Proxy Configuration highly depends on how the browser is 
launched with firefox, iexplore, opera, or custom.

* iexplore: If the browser is launched using \*iexplore, you could be 
  having a problem with Internet Explorer's proxy settings.  Selenium
  Server attempts To configure the global proxy settings in the Internet
  Options Control Panel. You must make sure that those are correctly
  configured when Selenium Server launches the browser. Try looking at
  your Internet Options control panel. Click on the "Connections" tab
  and click on "LAN Settings".     
  * If you need to use a proxy to access the application you want to test,
    you'll need to start Selenium Server with "-Dhttp.proxyHost"; 
    see the `Proxy Configuration`_ for more details.
  * You may also try configuring your proxy manually and then launching
    the browser with \*custom, or with \*iehta browser launcher.
      	   
* custom: When using \*custom you must configure the proxy correctly(manually),
  otherwise you'll get a 404 error. Double-check that you've configured your proxy
  settings correctly. To check whether you've configured the proxy correctly is to
  attempt to intentionally configure the browser incorrectly. Try configuring the
  browser to use the wrong proxy server hostname, or the wrong port.  If you had
  successfully configured the browser's proxy settings incorrectly, then the
  browser will be unable to connect to the Internet, which is one way to make
  sure that one is adjusting the relevant settings.
  
* For other browsers (\*firefox, \*opera) we automatically hard-code
  the proxy for you, and so there are no known issues with this functionality.
  If you're encountering 404 errors and have followed this user guide carefully 
  post your results to user group for some help from the user community.
      
### Permission Denied Error

The most common reason for this error is that your session is attempting to violate
the same-origin policy by crossing domain boundaries (e.g., accesses a page from 
http://domain1 and then accesses a page from http://domain2) or switching protocols 
(moving from http://domainX to https://domainX).

This error can also occur when JavaScript attempts to find UI objects 
which are not yet available (before the page has completely loaded), or 
are no longer available (after the page has started 
to be unloaded). This is most typically encountered with AJAX pages
which are working with sections of a page or subframes that load and/or reload 
independently of the larger page. 

This error can be intermittent. Often it is impossible to reproduce the problem 
with a debugger because the trouble stems from race conditions which 
are not reproducible when the debugger's overhead is added to the system.
Permission issues are covered in some detail in the tutorial. Read the section 
about the `The Same Origin Policy`_, `Proxy Injection`_ carefully. 


### Handling Browser Popup Windows

There are several kinds of "Popups" that you can get during a Selenium test.
You may not be able to close these popups by running Selenium commands if 
they are initiated by the browser and not your AUT.  You may
need to know how to manage these.  Each type of popup needs to be addressed differently.

* HTTP basic authentication dialogs: These dialogs prompt for a 
  username/password to login to the site. To login to a site that requires 
  HTTP basic authentication, use a username and password in the URL, as 
  described in `RFC 1738`_, like this: open("http://myusername:myuserpassword@myexample.com/blah/blah/blah").

* SSL certificate warnings: Selenium RC automatically attempts to spoof SSL 
  certificates when it is enabled as a proxy; see more on this 
  in the section on HTTPS. If your browser is configured correctly,
  you should never see SSL certificate warnings, but you may need to 
  configure your browser to trust our dangerous "CyberVillains" SSL certificate 
  authority. Again, refer to the HTTPS section for how to do this.

* modal JavaScript alert/confirmation/prompt dialogs: Selenium tries to conceal
  those dialogs from you (by replacing window.alert, window.confirm and 
  window.prompt) so they won't stop the execution of your page. If you're 
  seeing an alert pop-up, it's probably because it fired during the page load process,
  which is usually too early for us to protect the page.  Selenese contains commands
  for asserting or verifying alert and confirmation popups. See the sections on these
  topics in Chapter 4.

      
### On Linux, why isn't my Firefox browser session closing?

On Unix/Linux you must invoke "firefox-bin" directly, so make sure that
executable is on the path. If executing Firefox through a 
shell script, when it comes time to kill the browser Selenium RC will kill
the shell script, leaving the browser running.   You can specify the path
to firefox-bin directly, like this.
      
```bash
   cmd=getNewBrowserSession&1=*firefox /usr/local/firefox/firefox-bin&2=http://www.google.com
```

### Firefox \*chrome doesn't work with custom profile

Check Firefox profile folder -> prefs.js -> user_pref("browser.startup.page", 0);
Comment this line like this: "//user_pref("browser.startup.page", 0);" and try again.


### Is it ok to load a custom pop-up as the parent page is loading (i.e., before the parent page's javascript window.onload() function runs)?

No. Selenium relies on interceptors to determine window names as they are being loaded.
These interceptors work best in catching new windows if the windows are loaded AFTER 
the onload() function. Selenium may not recognize windows loaded before the onload function.
  
### Firefox on Linux 

On Unix/Linux, versions of Selenium before 1.0 needed to invoke "firefox-bin" 
directly, so if you are using a previous version, make sure that the real 
executable is on the path. 

On most Linux distributions, the real *firefox-bin* is located on:

```bash
   /usr/lib/firefox-x.x.x/ 
```

Where the x.x.x is the version number you currently have. So, to add that path 
to the user's path. you will have to add the following to your .bashrc file:

```bash
   export PATH="$PATH:/usr/lib/firefox-x.x.x/"
```

If necessary, you can specify the path to firefox-bin directly in your test,
like this:

```bash
   "*firefox /usr/lib/firefox-x.x.x/firefox-bin"
```

### IE and Style Attributes

If you are running your tests on Internet Explorer and you cannot locate
elements using their `style` attribute.
For example:

```bash
    //td[@style="background-color:yellow"]
```

This would work perfectly in Firefox, Opera or Safari but not with IE. 
IE interprets the keys in  `@style` as uppercase. So, even if the
source code is in lowercase, you should use:

```bash
    //td[@style="BACKGROUND-COLOR:yellow"]
```

This is a problem if your test is intended to work on multiple browsers, but
you can easily code your test to detect the situation and try the alternative
locator that only works in IE.

### Error encountered - "Cannot convert object to primitive value" with shut down of  \*googlechrome  browser

To avoid this error you have to start browser with an option that disables same origin policy checks: 

```bash
   selenium.start("commandLineFlags=--disable-web-security");
```
   

### Error encountered in IE - "Couldn't open app window; is the pop-up blocker enabled?"

To avoid this error you have to configure the browser: disable the popup blocker 
AND uncheck 'Enable Protected Mode' option in Tools >> Options >> Security.
