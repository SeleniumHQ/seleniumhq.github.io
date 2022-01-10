---
title: "Selenium RC (Selenium 1)"
linkTitle: "Selenium 1"
weight: 2
description: >
    The original version of Selenium
aliases: [
"/documentation/pt-br/legacy_docs/selenium_rc/",
"/pt-br/documentation/legacy/selenium_rc/"
]
---

## Introdução
Selenium RC foi o principal projeto da Selenium por muito tempo, antes da
fusão WebDriver / Selenium trazer o Selenium 2, uma ferramenta mais poderosa.
Vale ressaltar que não há mais suporte para Selenium 1.

## Como o Selenium RC funciona
Primeiro, vamos descrever como os componentes do Selenium RC operam e o papel que cada um desempenha na execução
de seus scripts de teste.

### Componentes do RC

Os componentes do Selenium RC são:

* O servidor Selenium que inicia e mata navegadores, interpreta e executa os comandos em Selenese passados do programa de teste e atua como um *proxy HTTP*, interceptando e verificando mensagens HTTP passadas entre o navegador e a aplicação sendo testada.
* Bibliotecas de cliente que fornecem a interface entre cada linguagem de programação e o Selenium RC Server.

Aqui está um diagrama de arquitetura simplificado:

![Architecture Diagram Simple](/images/documentation/legacy/selenium_rc_architecture_diagram_simple.png) 

O diagrama mostra que as bibliotecas cliente se comunicam com o
servidor passando cada comando Selenium para execução. Então o servidor passa o
comando Selenium para o navegador usando comandos Selenium-Core JavaScript. O
navegador, usando seu interpretador JavaScript, executa o comando Selenium. Este
executa a ação em Selenese ou verificação que você especificou em seu script de teste.

### Servidor Selenium

O servidor Selenium recebe comandos Selenium do seu programa de teste,
os interpreta e reporta ao seu programa os resultados da
execução desses testes.

O servidor RC agrupa o Selenium Core e o injeta automaticamente
no navegador. Isso ocorre quando seu programa de teste abre o
navegador (usando uma função API de biblioteca cliente).
Selenium-Core é um programa JavaScript, na verdade um conjunto de funções JavaScript que interpretam e executam comandos em Selenese usando o
interpretador de JavaScript embutido do navegador.

O servidor recebe os comandos em Selenese do seu programa de teste
usando solicitações HTTP GET/POST simples. Isso significa que você pode usar qualquer
linguagem de programação que pode enviar solicitações HTTP para automatizar
os testes Selenium no navegador.

### Bibliotecas Cliente

As bibliotecas cliente fornecem suporte de programação que permite que você
execute comandos Selenium a partir de um programa de seu próprio projeto. Existe um
biblioteca cliente diferente para cada linguagem compatível. Um cliente Selenium
biblioteca fornece uma interface de programação (API), ou seja, um conjunto de funções,
que executam comandos Selenium de seu próprio programa. Dentro de cada interface,
existe uma função de programação que suporta cada comando em Selenese.

A biblioteca cliente pega um comando em Selenese e o passa para o servidor Selenium
para processar uma ação específica ou teste no aplicativo em teste
(AUT). A biblioteca cliente
também recebe o resultado desse comando e o devolve ao seu programa.
Seu programa pode receber o resultado e armazená-lo em uma variável de programa e
relatá-lo como um sucesso ou fracasso,
ou possivelmente executar uma ação corretiva se for um erro inesperado.

Então, para criar um programa de teste, você simplesmente escreve um programa que executa
um conjunto de comandos Selenium usando uma API de biblioteca cliente. E, opcionalmente, se
você já tem um script de teste em Selenese criado na Selenium-IDE, você pode
*gerar o código Selenium RC*. A Selenium-IDE pode traduzir (usando seu item de menu
Exportar) seus comandos Selenium em chamadas de função de uma API de driver de cliente.
Consulte o capítulo Selenium-IDE para obter detalhes sobre a exportação de código RC a partir da
Selenium-IDE.

## Instalação

A instalação é um nome impróprio para Selenium. Selenium tem um conjunto de bibliotecas disponíveis
na linguagem de programação de sua escolha. Você pode baixá-los na [página de downloads](https://selenium.dev/downloads/).

Depois de escolher uma linguagem para trabalhar, você só precisa:

* Instalar o Selenium RC Server.
* Configurar um projeto de programação usando um driver cliente específico de linguagem.

### Instalando o servidor Selenium

O servidor Selenium RC é simplesmente um arquivo Java *jar* (*selenium-server-standalone-<número da versão>.jar*), que não
requer qualquer instalação especial. Basta baixar o arquivo zip e extrair o
servidor no diretório desejado. 

### Executando o servidor Selenium

Antes de iniciar qualquer teste, você deve iniciar o servidor. Vá para o diretório
onde o servidor Selenium RC está localizado e execute o seguinte a partir da linha de comando.

```shell
    java -jar selenium-server-standalone-<version-number>.jar
```

Isso pode ser simplificado criando
um arquivo executável em lote ou shell (.bat no Windows e .sh no Linux) contendo o comando
acima. Em seguida, faça um atalho para esse executável em seu
desktop e simplesmente clique duas vezes no ícone para iniciar o servidor.

Para o servidor funcionar, você precisa do Java instalado
e a variável de ambiente PATH configurada corretamente para executá-lo a partir do console.
Você pode verificar se o Java está instalado corretamente executando o seguinte
em um console.

```shell
       java -version
```

Se você obtiver um número de versão (que precisa ser 1.5 ou posterior), você está pronto para começar a usar o Selenium RC.

### Usando o driver cliente para Java

* Baixe o zip do driver do cliente Selenium Java na [página de downloads do SeleniumHQ](https://selenium.dev/downloads/).
* Extraia o arquivo selenium-java-<version-number>.jar
* Abra a IDE Java desejada (Eclipse, NetBeans, IntelliJ, Netweaver, etc.)
* Crie um projeto Java.
* Adicione os arquivos selenium-java-<version-number>.jar ao seu projeto como referências.
* Adicione ao classpath do projeto o arquivo selenium-java-<version-number>.jar.
* Na Selenium-IDE, exporte um script para um arquivo Java e inclua-o em seu projeto Java
ou escreva seu teste Selenium em Java usando a API selenium-java-client.
  A API é apresentada posteriormente neste capítulo. Você pode usar JUnit ou TestNg
  para executar seu teste, ou você pode escrever seu próprio programa main() simples. Esses conceitos são
  explicados mais para frente nesta seção.
* Execute o servidor Selenium a partir do console.
* Execute seu teste na Java IDE ou na linha de comando.

Para obter detalhes sobre a configuração do projeto de teste Java, consulte as seções do Apêndice
Configurando Selenium RC com Eclipse e Configurando Selenium RC com Intellij.

### Usando o driver cliente para Python 

* Instale o Selenium via pip, instruções no link da [página de downloads do SeleniumHQ](https://selenium.dev/downloads/)
* Escreva seu teste Selenium em Python ou exporte
   um script da Selenium-IDE para um arquivo python.
* Execute o servidor Selenium a partir do console
* Execute seu teste em um console ou IDE Python

Para obter detalhes sobre a configuração do driver cliente Python, consulte o apêndice Configuração do driver cliente Python.

### Usando o driver cliente para .NET

* Baixe o Selenium RC na [página de downloads do SeleniumHQ](https://selenium.dev/downloads/)
* Extraia a pasta
* Baixe e instale o [NUnit](https://nunit.org/download/) (
  Nota: você pode usar o NUnit como seu mecanismo de teste. Se você ainda não está familiarizado com
  NUnit, você também pode escrever uma função main() simples para executar seus testes;
  no entanto, o NUnit é muito útil como um mecanismo de teste.)
* Abra a IDE .Net desejado (Visual Studio, SharpDevelop, MonoDevelop)
* Crie uma biblioteca de classes (.dll)
* Adicione referências às seguintes DLLs: nmock.dll, nunit.core.dll, nunit.framework.dll, ThoughtWorks.Selenium.Core.dll, ThoughtWorks.Selenium.IntegrationTests.dll
  e ThoughtWorks.Selenium.UnitTests.dll
* Escreva seu teste Selenium em uma linguagem .Net (C#, VB.Net) ou exporte
  um script da Selenium-IDE para um arquivo C# e copie este código para o arquivo de classe
  você acabou de criar.
* Escreva seu próprio programa main() simples ou você pode incluir NUnit em seu projeto
  para executar seu teste. Esses conceitos são explicados posteriormente neste capítulo.
* Execute o servidor Selenium a partir do console
* Execute seu teste a partir da IDE, da GUI do NUnit ou da linha de comando

Para obter detalhes específicos sobre a configuração do driver cliente .NET com Visual Studio, consulte o apêndice
Configuração do driver cliente .NET. 

### Usando o driver cliente para Ruby

* Se você ainda não tem RubyGems, instale-o do RubyForge.
* Execute ``gem install selenium-client``
* No topo do seu script de teste, adicione ``require "selenium / client"``
* Escreva seu script de teste usando qualquer função de teste Ruby (por exemplo, Test::Unit,
   Mini::Test ou RSpec).
* Execute o servidor Selenium RC a partir do console.
* Execute seu teste da mesma forma que você executaria qualquer outro script Ruby.


Para obter detalhes sobre a configuração do driver do cliente Ruby, consulte a `documentação do Selenium-Client`

## Do Selenese ao Programa

A principal tarefa para usar o Selenium RC é converter seu Selenese em uma linguagem de programação. Nesta seção, fornecemos vários exemplos específicos de linguagens diferentes.

### Exemplo de script de teste

Vamos começar com um exemplo de script de teste em Selenese. Imagine gravar
o seguinte teste com Selenium-IDE.

|                    |                               |             |
| --------           | ----------------------------  | ----------- |
| open               | /                             |             |
| type               | q                             | selenium rc |
| clickAndWait       | btnG                          |             |
| assertTextPresent  | Results * for selenium rc     |             |


Observação: este exemplo funcionaria com a página de pesquisa do Google http://www.google.com

### Selenese como código

Aqui está o script de teste exportado (via Selenium-IDE) para cada uma das
linguagens de programação. Se você tem pelo menos conhecimento básico de linguagem de programação orientada a objetos (OOP), você vai entender como o Selenium
executa comandos em Selenese lendo um destes
exemplos. Para ver um exemplo em uma linguagem específica, selecione um desses botões.

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

Na próxima seção, explicaremos como construir um programa de teste usando o código gerado.

## Programando seu teste

Agora vamos ilustrar como programar seus próprios testes usando exemplos em cada uma das
linguagens de programação suportadas.
Existem essencialmente duas tarefas:

*Gerar seu script em uma
   linguagem de programação a partir da Selenium-IDE, opcionalmente modificando o resultado.
*Escrever um programa principal muito simples que execute o código gerado.

Opcionalmente, você pode adotar uma plataforma de mecanismo de teste como JUnit ou TestNG para Java,
ou NUnit para .NET se você estiver usando uma dessas linguagens.

Aqui, mostramos exemplos específicos de cada linguagem. As APIs específicas do idioma tendem a
diferir de um para o outro, então você encontrará uma explicação separada para cada um.  

* Java
* C#
* Python
* Ruby
* Perl, PHP


### Java

Para Java, as pessoas usam JUnit ou TestNG como mecanismo de teste.
Alguns ambientes de desenvolvimento como o Eclipse têm suporte direto para eles via
plug-ins. Isso torna tudo ainda mais fácil. Ensinar JUnit ou TestNG está além do escopo de
este documento, no entanto, os materiais podem ser encontrados online e há publicações
acessíveis. Se você já é uma "loja de Java", é provável que seus desenvolvedores
já tem alguma experiência com uma dessas estruturas de teste.

Você provavelmente vai querer renomear a classe de teste de "NewTest" para algo
de sua própria escolha. Além disso, você precisará alterar os
parâmetros abertos pelo navegador na declaração:

```java
    selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://www.google.com/");
``` 

O código gerado pela Selenium-IDE terá a seguinte aparência. Este exemplo
tem comentários adicionados manualmente para maior clareza.

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

O driver do cliente .NET funciona com o Microsoft.NET.
Pode ser usado com qualquer framework de teste .NET
como o NUnit ou o Visual Studio 2005 Team System.

Selenium-IDE assume que você usará NUnit como sua estrutura de teste.
Você pode ver isso no código gerado abaixo. Inclui a declaração *using*
para NUnit junto com os atributos NUnit correspondentes que identificam
o papel de cada função-membro da classe de teste.

Você provavelmente terá que renomear a classe de teste de "NewTest" para
algo de sua própria escolha. Além disso, você precisará alterar os 
parâmetros abertos pelo navegador na declaração

```csharp
    selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://www.google.com/");
```

O código gerado será semelhante a este.

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

Você pode permitir que o NUnit gerencie a execução
de seus testes. Ou, alternativamente, você pode escrever um programa `main()` simples que
instancia o objeto de teste e executa cada um dos três métodos, `SetupTest()`,
`TheNewTest()` e `TeardownTest()` por sua vez.


### Python

Pyunit é a estrutura de teste a ser usada para Python.

A estrutura básica do teste é:

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


Versões antigas (pré 2.0) da Selenium-IDE geram código Ruby que requer o *gem* antigo do Selenium. Portanto, é aconselhável atualizar todos os scripts Ruby gerados pela
IDE da seguinte forma:

1. Na linha 1, altere ``require "selenium"`` para ``require "selenium/client"``

2. Na linha 11, altere ``Selenium::SeleniumDriver.new`` para
``Selenium::Client::Driver.new``

Você provavelmente também deseja alterar o nome da classe para algo mais
informativo do que "Untitled" e alterar o nome do método de teste para
algo diferente de "test_untitled."

Aqui está um exemplo simples criado pela modificação do código Ruby gerado
pela Selenium IDE, conforme descrito acima.

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

Os membros da equipe de documentação
não usaram Selenium RC com Perl ou PHP. Se você estiver usando Selenium RC com qualquer um desses dois idiomas, entre em contato com a Equipe de Documentação (consulte o capítulo sobre Contribuições).
Gostaríamos muito de incluir alguns exemplos seus e de suas experiências, para oferecer suporte a usuários Perl e PHP.


## Aprendendo a API

A API Selenium RC usa convenções de nomenclatura
que, supondo que você entenda Selenese, será em grande parte autoexplicativo.
Aqui, no entanto, explicamos os aspectos mais críticos e
possivelmente menos óbvios.

### Iniciando o navegador

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

Cada um desses exemplos abre o navegador e representa esse navegador
atribuindo uma "instância do navegador" a uma variável de programa. Esta variável de programa é então usada para chamar métodos do navegador.
Esses métodos executam os comandos Selenium, ou seja, como *open* ou *type* ou *verify*.

Os parâmetros necessários ao criar a instância do navegador
são: 

* **host**
    Especifica o endereço IP do computador onde o servidor está localizado. Normalmente, esta é
     a mesma máquina em que o cliente está sendo executado, portanto, neste caso, *localhost* é passado. Em alguns clientes, este é um parâmetro opcional.
	
* **port**
    Especifica o socket TCP/IP onde o servidor está escutando, esperando
     para o cliente estabelecer uma conexão. Isso também é opcional em alguns
     drivers do cliente.
	
* **browser**
    O navegador no qual você deseja executar os testes. Este é um parâmetro obrigatório.
	
* **url**
    A url base do aplicativo em teste. Isso é exigido por todas as
     libs de cliente e é uma informação integral para iniciar a comunicação entre navegador-proxy-aplicação.

Observe que algumas das bibliotecas cliente exigem que o navegador seja iniciado explicitamente chamando
seu método `start ()`.

### Executando comandos 

Depois de ter o navegador inicializado e atribuído a uma variável (geralmente
chamada "selenium"), você pode fazê-lo executar comandos Selenese chamando os respectivos
métodos a partir da variável do navegador. Por exemplo, para chamar o método *type*
do objeto selenium:

```
    selenium.type("field-id", "string to type")
```

Em segundo plano, o navegador realmente realizará uma operação *type*,
essencialmente idêntico a um usuário digitando uma entrada no navegador,
usando o localizador e a string que você especificou durante a chamada do método.

## Reportando resultados

O Selenium RC não tem seu próprio mecanismo para relatar os resultados. Em vez disso, 
você pode construir seus relatórios personalizados de acordo com suas necessidades, usando recursos de sua
linguagem de programação escolhida. Isso é ótimo, mas e se você simplesmente quiser algo
rápido que já foi feito para você? Muitas vezes, uma biblioteca existente ou estrutura de teste pode
atender às suas necessidades mais rapidamente do que desenvolver seu próprio código de relatório de teste.

### Ferramentas de reporte dos frameworks de teste 

Frameworks de teste estão disponíveis para muitas linguagens de programação. Estes, junto com
sua função principal de fornecer um mecanismo de teste flexível para executar seus testes,
incluem o código da biblioteca para relatar os resultados. Por exemplo, Java tem dois
frameworks de teste comumente usados, JUnit e TestNG. .NET também tem seu próprio, NUnit.

Não ensinaremos os frameworks aqui; e que está além do escopo deste
guia de usuário. Vamos simplesmente apresentar os recursos do framework relacionados ao Selenium
junto com algumas técnicas que você pode aplicar. Existem bons livros disponíveis sobre estas
estruturas de teste, juntamente com informações na Internet.

### Bibliotecas de relatórios de teste 

Também estão disponíveis bibliotecas de terceiros criadas especificamente para reportar
os resultados dos testes na linguagem de programação escolhida. Estes geralmente suportam uma
variedade de formatos, como HTML ou PDF.

### Qual a melhor técnica? 

A maioria das pessoas novas no uso dos frameworks de teste começarão com os
recursos de relatórios integrados no framework. A partir daí, a maioria examinará todas as bibliotecas disponíveis
pois isso consome menos tempo do que desenvolver a sua própria. Quando você começa a usar o
Selenium, sem dúvida, você vai começar a colocar seus próprios "prints" para
relatar o progresso. Isso pode levá-lo gradualmente a desenvolver seus próprios
relatórios, possivelmente em paralelo ao uso de uma biblioteca ou estrutura de teste. Independentemente,
após a curta curva de aprendizado inicial você desenvolverá naturalmente o que funciona
melhor para sua própria situação.

### Exemplos de relatórios de teste

Para ilustrar, iremos direcioná-lo para algumas ferramentas específicas em algumas das outras linguagens
apoiadas pelo Selenium. As listadas aqui são comumente usadas e têm sido usadas
extensivamente (e portanto recomendadas) pelos autores deste guia.

#### Relatórios de teste em Java

* Se os casos de teste Selenium forem desenvolvidos usando JUnit, então o relatório JUnit pode ser usado
   para gerar relatórios de teste.

* Se os casos de teste Selenium forem desenvolvidos usando TestNG, então nenhuma tarefa externa
   é necessária para gerar relatórios de teste. A estrutura TestNG gera um
   Relatório HTML que lista os detalhes dos testes.

* ReportNG é um plug-in de relatório HTML para a estrutura TestNG.
   Destina-se a substituir o relatório HTML padrão do TestNG.
   O ReportNG fornece uma visualização simples e codificada por cores dos resultados do teste. 
  
##### Registrando os comandos Selenese

* O Logging Selenium pode ser usado para gerar um relatório de todos os comandos Selenese
   em seu teste junto com o sucesso ou fracasso de cada um. Logging Selenium estende
   o driver do cliente Java para adicionar esta capacidade de registro do Selenese.

#### Relatórios de teste em Python

* Ao usar o driver de cliente para Python, HTMLTestRunner pode ser usado para
   gerar um relatório de teste.

#### Relatórios de teste em Ruby

* Se o framework RSpec for usado para escrever Casos de Teste Selenium em Ruby
   então seu relatório HTML pode ser usado para gerar um relatório de teste.


## Adicionando algum tempero aos seus testes

Agora veremos toda a razão de usar Selenium RC, adicionando lógica de programação aos seus testes.
É o mesmo que para qualquer programa. O fluxo do programa é controlado por meio de declarações de condição
e iteração. Além disso, você pode relatar informações de progresso usando I/O. Nesta secção
vamos mostrar alguns exemplos de como construções de linguagem de programação podem ser combinadas com
Selenium para resolver problemas de teste comuns.

Você vai descobrir ao fazer a transição dos testes simples da existência de
elementos de página para testes de funcionalidade dinâmica envolvendo várias páginas da web e
dados variáveis que você exigirá lógica de programação para verificar
resultados. Basicamente, a Selenium-IDE não suporta iteração e
declarações de condição padrão. Você pode fazer algumas condições incorporando JavaScript
em parâmetros Selenese, no entanto
iteração é impossível, e a maioria das condições será muito mais fácil em uma
linguagem de programação. Além disso, você pode precisar de tratamento de exceção para
recuperação de erros. Por essas e outras razões, escrevemos esta seção
para ilustrar o uso de técnicas de programação comuns para
dar a você maior 'poder de verificação' em seus testes automatizados.

Os exemplos nesta seção são escritos
em C# e Java, embora o código seja simples e possa ser facilmente adaptado às demais
linguagens. Se você tem algum conhecimento básico
de uma linguagem de programação orientada a objetos, você não deve ter dificuldade em entender esta seção.

### Iteração

A iteração é uma das coisas mais comuns que as pessoas precisam fazer em seus testes.
Por exemplo, você pode querer executar uma pesquisa várias vezes. Ou, talvez
para verificar os resultados do teste, você precisa processar um "conjunto de resultados" retornado de um banco de dados.

Usando o mesmo exemplo de pesquisa do Google que usamos anteriormente, vamos
verificar os resultados da pesquisa Selenium. Este teste pode usar o Selenese:

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


O código foi repetido para executar as mesmas etapas 3 vezes. Mas ter múltiplas
cópias do mesmo código não é uma boa prática de programação porque é mais
trabalhoso para manter. Usando uma linguagem de programação, podemos iterar
sobre os resultados da pesquisa para uma solução mais flexível e sustentável. 

#### In `C#`   
   
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

### Declarações de condição

Para ilustrar o uso de condições em testes, começaremos com um exemplo.
Um problema comum encontrado durante a execução de testes Selenium ocorre quando
o elemento esperado não está disponível na página. Por exemplo, ao executar a
seguinte linha:

```
   selenium.type("q", "selenium " +s);
```
   
Se o elemento 'q' não estiver na página, então uma exceção é
lançada:

```java
   com.thoughtworks.selenium.SeleniumException: ERROR: Element q not found
```

Isso pode fazer com que seu teste seja interrompido. Para alguns testes, é isso que você deseja. Mas
frequentemente isso não é desejável, pois seu script de teste tem muitos outros testes subsequentes para realizar.

Uma abordagem melhor é primeiro validar se o elemento está realmente presente
e então escolher alternativas quando não estiver. Vejamos isso usando Java.

```java
   // If element is available on page then perform type operation.
   if(selenium.isElementPresent("q")) {
       selenium.type("q", "Selenium rc");
   } else {
       System.out.printf("Element: " +q+ " is not available on page.")
   }
```
   
A vantagem desta abordagem é continuar com a execução do teste, mesmo se alguns elementos de IU não estão disponíveis na página.


### Executando JavaScript a partir do seu teste

JavaScript é muito útil para exercitar uma aplicação que não é diretamente suportada
por Selenium. O método **getEval** da API Selenium pode ser usado para executar JavaScript a partir de
Selenium RC.

Considere um aplicativo com caixas de seleção sem identificadores estáticos.
Neste caso, pode-se avaliar o JavaScript do Selenium RC para obter ids de todas
caixas de seleção e, em seguida, exercitá-las. 

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

Para contar as imagens em uma página 

```java   
   selenium.getEval("window.document.images.length;");
```

Lembre-se de usar o objeto window no caso de expressões DOM já que por padrão a janela Selenium é referida, não a janela de teste.

## Opções do servidor

Quando o servidor é iniciado, as opções de linha de comando podem ser usadas para alterar o
comportamento padrão do servidor.

Lembre-se de que o servidor é iniciado executando o seguinte.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar
``` 

Para ver a lista de opções, execute o servidor com a opção ``-h``.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -h
``` 

Você verá uma lista de todas as opções que pode usar com o servidor e uma breve
descrição de cada. As descrições fornecidas nem sempre serão suficientes, então
fornecemos explicações para algumas das opções mais importantes.


### Configuração do Proxy

Se o seu aplicação estiver atrás de um proxy HTTP que requer autenticação, você deve
configurar http.proxyHost, http.proxyPort, http.proxyUser e http.proxyPassword
usando o seguinte comando.

```bash   
   $ java -jar selenium-server-standalone-<version-number>.jar -Dhttp.proxyHost=proxy.com -Dhttp.proxyPort=8080 -Dhttp.proxyUser=username -Dhttp.proxyPassword=password
``` 

### Modo multi-janela

Se você estiver usando Selenium 1.0, você provavelmente pode pular esta seção, uma vez que o modo multijanela é
o comportamento padrão. No entanto, antes da versão 1.0, o Selenium executava por padrão o
aplicativo em teste em um subquadro, conforme mostrado aqui.

![Single window mode](/images/documentation/legacy/selenium_rc_single_window_mode.png)

Alguns aplicativos não funcionavam corretamente em um subquadro e precisavam ser
carregados no quadro superior da janela. A opção de modo multi-janela permitida
a aplicação testada ser executada em uma janela separada, em vez do quadro
padrão onde poderia então ter o quadro superior necessário.

![Multiwindow Mode](/images/documentation/legacy/selenium_rc_multi_window_mode.png)

Para versões mais antigas do Selenium você deve especificar o modo multijanela explicitamente
com a seguinte opção:

```bash   
   -multiwindow 
``` 

A partir do Selenium RC 1.0, se você deseja executar seu teste dentro de um
quadro único (ou seja, usando o padrão para versões anteriores do Selenium)
você pode declarar isso ao servidor Selenium usando a opção

```bash   
   -singlewindow 
``` 

### Especificando o perfil do Firefox

O Firefox não executará duas instâncias simultaneamente, a menos que você especifique um
perfil separado para cada instância. Selenium RC 1.0 e posterior é executado em um perfil separado automaticamente, então se você estiver usando Selenium 1.0, você pode
provavelmente pular esta seção. No entanto, se você estiver usando uma versão mais antiga do
Selenium ou se você precisar usar um perfil específico para seus testes
(como adicionar um certificado https ou ter alguns complementos instalados), você
precisa especificar explicitamente o perfil.

Primeiro, para criar um perfil separado do Firefox, siga este procedimento.
Abra o menu Iniciar do Windows, selecione "Executar", digite e entre um dos
seguintes:

```bash   
   firefox.exe -profilemanager 
``` 

```bash   
   firefox.exe -P 
``` 

Crie o novo perfil usando a caixa de diálogo. Então, quando você executar o Selenium Server,
diga a ele para usar este novo perfil do Firefox com a opção de linha de comando do servidor
*\-firefoxProfileTemplate* e especifique o caminho para o perfil usando seu nome de arquivo
e o caminho do diretório.

```bash   
   -firefoxProfileTemplate "path to the profile" 
``` 

**Aviso**: certifique-se de colocar seu perfil em uma nova pasta separada da padrão!!!
    A ferramenta gerenciadora de perfil do Firefox irá deletar todos os arquivos em uma pasta se você
    excluir um perfil, independentemente de serem arquivos de perfil ou não.
   
Mais informações sobre os perfis do Firefox podem ser encontradas em [Mozilla's Knowledge Base](http://support.mozilla.com/en/kb/Managing+profiles)

### Execute Selenese diretamente dentro do servidor usando -htmlSuite

Você pode executar arquivos Selenese html diretamente no servidor Selenium
passando o arquivo html para a linha de comando do servidor. Por exemplo:

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -htmlSuite "*firefox" 
   "http://www.google.com" "c:\absolute\path\to\my\HTMLSuite.html" 
   "c:\absolute\path\to\my\results.html"
``` 

Isso iniciará automaticamente seu pacote HTML, executará todos os testes e salvará um
bom relatório HTML com os resultados.

*Nota:* ao usar esta opção, o servidor irá iniciar os testes e aguardar um
    número especificado de segundos para o teste ser concluído; se o teste não
    completar dentro desse período de tempo, o comando sairá com um código de saída diferente de zero
     e nenhum arquivo de resultados será gerado.

Esta linha de comando é muito longa, então tome cuidado com o que
você digita. Observe que isso requer que você passe uma suíte de arquivos HTML Selenese, não um único teste. Também esteja ciente de que a opção -htmlSuite é incompatível com ``-interactive``.
Você não pode executar os dois ao mesmo tempo.

### Logging do servidor Selenium

#### logs do lado do servidor

Ao iniciar o servidor Selenium, a opção **-log** pode ser usada para gravar
informações valiosas de depuração relatadas pelo servidor Selenium em um arquivo de texto.

```bash   
   java -jar selenium-server-standalone-<version-number>.jar -log selenium.log
``` 
   
Este arquivo de log é mais detalhado do que os logs do console padrão (inclui mensagens de registro de nível DEBUG
). O arquivo de log também inclui o nome do registrador e o
número do thread que registrou a mensagem. Por exemplo:   

```bash   
   20:44:25 DEBUG [12] org.openqa.selenium.server.SeleniumDriverResourceHandler - 
   Browser 465828/:top frame1 posted START NEW
``` 
   
O formato da mensagem é 

```bash   
   TIMESTAMP(HH:mm:ss) LEVEL [THREAD] LOGGER - MESSAGE
``` 
   
Esta mensagens pode ter múltiplas linhas.

#### Logs do lado do navegador

O JavaScript no lado do navegador (Selenium Core) também registra mensagens importantes;
em muitos casos, eles podem ser mais úteis para o usuário final do que os
Logs normais do servidor. Para acessar os registros do lado do navegador, passe o argumento **-browserSideLog** para o servidor Selenium.


```bash   
   java -jar selenium-server-standalone-<version-number>.jar -browserSideLog
``` 
   
**-browserSideLog** deve ser combinado com o argumento **-log**, para registrar
browserSideLogs (bem como todas as outras mensagens de log de nível DEBUG) em um arquivo.


## Especificando o caminho para um navegador específico

Você pode especificar para o Selenium RC o caminho para um navegador. Isto
é útil se você possui diferentes versões do mesmo navegador e você deseja usar
uma em específico. Isto também pode ser usado para executar seus testes em um navegador
que não é suportado diretamente pelo Selenium RC. Quando especificar o modo
de execução, use o especificador \*custom seguido do caminho completo para o
executável do navegador.

```bash   
   *custom <path to browser> 
``` 

   
## Arquitetura do Selenium RC

*Nota:* este tópico tenta explicar a implementação técnica por trás do Selenium RC.
    Não é fundamental para um usuário Selenium saber disso, mas
    pode ser útil para entender alguns dos problemas que você pode encontrar no
    futuro.
   
Para entender em detalhes como o Selenium RC Server funciona e porque ele usa injeção de proxy
e modos de privilégio elevado você deve primeiro entender _`the same origin policy`_.
   
### A política de mesma origem (Same Origin Policy)

A principal restrição que o Selenium enfrenta é a
política de mesma origem. Esta restrição de segurança é aplicada por todos os navegadores
no mercado e seu objetivo é garantir que o conteúdo de um site nunca
esteja acessível por um script de outro site. A política da mesma origem determina que
qualquer código carregado no navegador só pode operar dentro do domínio desse site.
Ele não pode executar funções em outro site. Por exemplo, se o navegador
carrega o código JavaScript quando carrega www.mysite.com, ele não pode executar esse código carregado
em www.mysite2.com - mesmo que seja outro de seus sites. Se isso fosse possível,
um script colocado em qualquer site que você abrir seria capaz de ler informações sobre
sua conta bancária se você tivesse a página da conta
aberto em outra guia. Isso é chamado de XSS (Cross-site Scripting).

Para trabalhar dentro desta política, Selenium-Core (e seus comandos JavaScript que
fazem toda a mágica acontecer) deve ser colocado na mesma origem do aplicativo testado (mesmo URL).

Historicamente, Selenium-Core era limitado por este problema, uma vez que foi implementado em
JavaScript. O Selenium RC não é, entretanto, restringido pela Política da Mesma Origem.
Seu uso do Selenium Server como proxy evita esse problema. Essencialmente, diz ao
navegador que o navegador está funcionando em um único site "falsificado" que o servidor
fornece. 

*Nota:* você pode encontrar informações adicionais sobre este tópico nas páginas da Wikipedia
sobre a política da mesma origem e XSS. 


### Injeção de Proxy

O primeiro método que o Selenium usou para evitar a Política de Mesma Origem foi a injeção de proxy.
No modo de injeção de proxy, o servidor Selenium atua como um **HTTP configurado pelo cliente
proxy** [^1], que fica entre o navegador e o aplicativo em teste [^2].
Em seguida, ele mascara a aplicação testada sob uma URL fictícia (incorporação
Selenium-Core e o conjunto de testes e entregando-os como se estivessem chegando
da mesma origem). 

[^1]: O proxy é uma terceira pessoa no meio que passa a bola entre as duas partes. Ele atua como um "servidor da web" que entrega a aplicação ao navegador. Ser um proxy dá ao Selenium Server a capacidade de "mentir" sobre a URL real da aplicação.
   
[^2]: O navegador é iniciado com um perfil de configuração que definiu localhost:4444 como o proxy HTTP, é por isso que qualquer solicitação HTTP que o navegador fizer passará pelo servidor Selenium e a resposta passará por ele e não pelo servidor real.

Aqui está um diagrama da arquitetura. 

![Architectural Diagram 1](/images/documentation/legacy/selenium_rc_architecture_diagram_1.png)

Quando um conjunto de testes começa em sua linguagem favorita, acontece o seguinte:

1. O cliente/driver estabelece uma conexão com o servidor selenium-RC.
2. O servidor Selenium RC inicia um navegador (ou reutiliza um antigo) com uma URL
   que injeta o JavaScript do Selenium-Core na página da web carregada pelo navegador.
3. O driver do cliente passa um comando Selenese para o servidor.
4. O servidor interpreta o comando e então aciona a execução correspondente
   de JavaScript para executar esse comando no navegador.
   Selenium-Core instrui o navegador a agir sobre a primeira instrução, normalmente abrindo uma página da
   aplicação testada.
5. O navegador recebe a solicitação de abertura e pede o conteúdo do site do
   servidor Selenium RC (definido como o proxy HTTP para o navegador usar).
6. O servidor Selenium RC se comunica com o servidor Web solicitando a página e uma vez que
   recebe, envia a página para o navegador mascarando a origem para parecer
   que a página vem do mesmo servidor que Selenium-Core (isso permite
   Selenium-Core para cumprir a Política da Mesma Origem).
7. O navegador recebe a página da web e a renderiza no quadro/janela reservado
   para isso.
   
### Navegadores com privilégio elevado

Este fluxo de trabalho neste método é muito semelhante à injeção de proxy, mas a principal
diferença é que os navegadores são iniciados em um modo especial chamado de *Privilégios Aumentados*, que permite que os sites façam coisas que normalmente não são permitidas
(como fazer _XSS_, ou preencher entradas de upload de arquivos e coisas muito úteis para o
Selenium). Ao usar esses modos de navegador, o Selenium Core é capaz de abrir diretamente
a aplicação testada e ler/interagir com seu conteúdo sem ter que passar a aplicação inteira
através do servidor Selenium RC.

Aqui está um diagrama da arquitetura. 

![Architectural Diagram 1](/images/documentation/legacy/selenium_rc_architecture_diagram_2.png)

Quando um conjunto de testes começa em sua linguagem favorita, acontece o seguinte:

1. O cliente/driver estabelece uma conexão com o servidor selenium-RC.
2. O servidor Selenium RC inicia um navegador (ou reutiliza um antigo) com uma URL
    que irá carregar o Selenium-Core na página da web.
3. Selenium-Core obtém a primeira instrução do cliente/driver (através de outra
    solicitação HTTP feita ao servidor Selenium RC).
4. Selenium-Core atua na primeira instrução, normalmente abrindo uma página da aplicação.
5. O navegador recebe a solicitação de abertura e solicita a página ao servidor da Web.
    Assim que o navegador recebe a página da web, a renderiza no quadro / janela reservado
    para isso.

## Lidando com HTTPS e Popups de segurança 

Muitos aplicativos mudam de HTTP para HTTPS quando precisam enviar
informações criptografadas, como senhas ou informações de cartão de crédito. Isto é
comum com muitos dos aplicativos da web de hoje. Selenium RC apoia isso. 

Para garantir que o site HTTPS seja genuíno, o navegador precisará de um certificado de segurança.
Caso contrário, quando o navegador acessar a aplicação testada usando HTTPS, ele irá
presumir que o aplicativo não é 'confiável'. Quando isso ocorre, o navegador
exibe pop-ups de segurança e esses pop-ups não podem ser fechados usando o Selenium RC.

Ao lidar com HTTPS em um teste Selenium RC, você deve usar um modo de execução que suporte isso e controle
o certificado de segurança para você. Você especifica o modo de execução quando seu programa de teste
inicializa o Selenium. 

No Selenium RC 1.0 beta 2 e posterior, use \*firefox ou \*iexplore para o modo de execução.
Em versões anteriores, incluindo Selenium RC 1.0 beta 1, use \*chrome ou
\*iehta, para o modo de execução. Usando esses modos de execução, você não precisará instalar
quaisquer certificados de segurança especiais; Selenium RC cuidará disso para você.

Na versão 1.0, os modos de execução \*firefox ou \*iexplore são
recomendados. No entanto, existem modos de execução adicionais de \*iexploreproxy e
\*firefoxproxy. Eles são fornecidos apenas para compatibilidade com versões anteriores, e
não devem ser usados, a menos que exigido por programas de teste legados. Seu uso vai
apresentar limitações com o manuseio do certificado de segurança e com o funcionamento
de várias janelas se seu aplicativo abrir janelas adicionais do navegador. 

Em versões anteriores do Selenium RC, \*chrome ou \*iehta eram os modos de execução que suportavam
HTTPS suportado e o tratamento de popups de segurança. Estes foram considerados 'modos experimentais', embora tenham se tornado bastante estáveis e muitas pessoas os usaram. Se você estiver usando
Selenium 1.0 você não precisa, e não deve usar, esses modos de execução mais antigos.

### Certificados de Segurança explicados

Normalmente, seu navegador confiará no aplicativo que você está testando
instalando um certificado de segurança que você já possui. Você pode
verificar isso nas opções do seu navegador ou propriedades da Internet (se você não
conheça o certificado de segurança da sua aplicação, pergunte ao administrador do sistema).
Quando o Selenium carrega seu navegador, ele injeta um código para interceptar
mensagens entre o navegador e o servidor. O navegador agora pensa que algum
software não confiável está tentando se parecer com o seu aplicativo. Ele responde alertando você com mensagens pop-up.

Para contornar isso, Selenium RC, (novamente ao usar um modo de execução que suporta
isso) instalará seu próprio certificado de segurança, temporariamente, em sua
máquina cliente em um local onde o navegador possa acessá-lo. Isso engana
o navegador a pensar que está acessando um site diferente da sua aplicação e suprime efetivamente os pop-ups.

Outro método usado com versões anteriores do Selenium era
instalar o certificado de segurança Cybervillians fornecido com sua instalação do Selenium.
A maioria dos usuários não deve mais precisar fazer isso; se você está
executando o Selenium RC no modo de injeção de proxy, você pode precisar instalar
explicitamente este certificado de segurança.


## Suportando navegadores e configurações adicionais

A API Selenium suporta a execução em vários navegadores, além de
Internet Explorer e Mozilla Firefox. Veja o site https://selenium.dev para
navegadores compatíveis. Além disso, quando um navegador não é diretamente compatível,
você ainda pode executar seus testes Selenium em um navegador de sua escolha
usando o modo de execução "\*custom" (ou seja, no lugar de \*firefox ou \*iexplore) quando
seu aplicativo de teste inicia o navegador. Com isso, você passa no caminho para
os navegadores executáveis na chamada de API. Isso também pode ser feito a partir do
servidor em modo interativo.

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\MyBrowser.exe&2=http://www.google.com
```


### Executando testes com diferentes configurações de navegador

Normalmente o Selenium RC configura automaticamente o navegador, mas se você iniciar
o navegador usando o modo de execução "\*custom", você pode forçar o Selenium RC
a iniciar o navegador como está, sem usar uma configuração automática.

Por exemplo, você pode iniciar o Firefox com uma configuração personalizada como esta:

```bash
   cmd=getNewBrowserSession&1=*custom c:\Program Files\Mozilla Firefox\firefox.exe&2=http://www.google.com
```

Observe que ao iniciar o navegador desta forma, você deve manualmente
configurar o navegador para usar o servidor Selenium como proxy. Normalmente, isso apenas
significa abrir as preferências do navegador e especificar "localhost: 4444" como
um proxy HTTP, mas as instruções para isso podem diferir radicalmente de navegador para
navegador. Consulte a documentação do seu navegador para obter detalhes.

Esteja ciente de que os navegadores Mozilla podem variar em como eles iniciam e param.
Pode ser necessário definir a variável de ambiente MOZ_NO_REMOTE para fazer com que os navegadores Mozilla
se comportem de maneira mais previsível. Os usuários Unix devem evitar iniciar o navegador usando
um script de shell; geralmente é melhor usar o executável binário (por exemplo, firefox-bin) diretamente.

   
## Resolução de problemas comuns

Ao começar a usar o Selenium RC, há alguns problemas potenciais
que são comumente encontrados. Nós os apresentamos junto com suas soluções aqui.

### Incapaz de conectar ao servidor

Quando seu programa de teste não pode se conectar ao servidor Selenium, o Selenium lança uma exceção em seu programa de teste.
Ele deve exibir esta mensagem ou outra semelhante:

```bash
    "Unable to connect to remote server (Inner Exception Message: 
	No connection could be made because the target machine actively 
	refused it )"
    
	(using .NET and XP Service Pack 2) 
```

Se você vir uma mensagem como esta, certifique-se de iniciar o servidor Selenium. E se
então, há um problema com a conectividade entre a biblioteca cliente Selenium e o servidor Selenium.

Ao começar com Selenium RC, a maioria das pessoas começa executando seu programa de teste
(com uma biblioteca de cliente Selenium) e o servidor Selenium na mesma máquina. Para
fazer isso use "localhost" como parâmetro de conexão.
Recomendamos começar dessa forma, pois reduz a influência de possíveis problemas de rede
que você está começando. Supondo que seu sistema operacional tenha uma rede típica
e configurações TCP/IP, você deve ter pouca dificuldade. Na verdade, muitas pessoas
optam por executar os testes desta forma.

Se, no entanto, você deseja executar o Selenium Server
em uma máquina remota, a conectividade deve ser boa,
supondo que você tenha uma conexão TCP/IP válida entre as duas máquinas.    

Se tiver dificuldade para se conectar, você pode usar ferramentas de rede comuns como *ping*,
*telnet*, *ifconfig (Unix) / ipconfig* (Windows), etc para garantir que você tenha uma
conexão de rede. Se não estiver familiarizado com eles, o administrador do sistema pode ajudá-lo.
 
### Incapaz de carregar o navegador 

Ok, não é uma mensagem de erro amigável, desculpe, mas se o servidor Selenium não pode carregar o navegador
você provavelmente verá este erro.

```bash
    (500) Internal Server Error
```

Isso pode ser causado por

* O Firefox (anterior ao Selenium 1.0) não pode iniciar porque o navegador já está aberto e você o fez
   não especificar um perfil separado. Consulte a seção sobre perfis do Firefox em Opções do servidor.
* O modo de execução que você está usando não corresponde a nenhum navegador em sua máquina. Verifique os parâmetros que você
   passou para o Selenium quando seu programa abre o navegador.
* Você especificou o caminho para o navegador explicitamente (usando "\*custom" - veja acima), mas o caminho é
   incorreto. Verifique se o caminho está correto. Verifique também o grupo de usuários para ter certeza de que há
   nenhum problema conhecido com seu navegador e os parâmetros "\*custom".

### Selenium não consegue achar a aplicação testada

Se o seu programa de teste iniciar o navegador com sucesso, mas o navegador não
exibir o site que você está testando, a causa mais provável é que o seu programa de teste não está usando a URL correta.

Isso pode acontecer facilmente. Quando você usa Selenium-IDE para exportar seu script,
ela insere uma URL fictícia. Você deve alterar manualmente a URL para a correta
para que seu aplicativo seja testado. 

### O Firefox recusou o desligamento ao preparar um perfil

Isso ocorre com mais frequência quando você executa o programa de teste Selenium RC no Firefox,
mas você já tem uma sessão do navegador Firefox em execução e não especificou
um perfil separado quando você iniciou o servidor Selenium. O erro do
programa de teste tem a seguinte aparência:

```bash
    Error:  java.lang.RuntimeException: Firefox refused shutdown while 
    preparing a profile 
```

Esta é a mensagem de erro completa do servidor:

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

Para resolver isso, consulte a seção Especificando um perfil separado do Firefox

### Problemas de versionamento 

Certifique-se de que sua versão do Selenium é compatível com a versão do seu navegador. Por
exemplo, Selenium RC 0.92 não suporta Firefox 3. Às vezes você pode ter sorte
(eu tive). Mas não se esqueça de verificar quais
versões do navegador são compatíveis com a versão do Selenium que você está usando. Quando em
dúvida, use a versão mais recente do Selenium com a versão mais usada
do seu navegador.

### Mensagem de erro: "(Unsupported major.minor version 49.0)" ao inicializar o servidor

Este erro diz que você não está usando uma versão correta do Java.
O Selenium Server requer Java 1.5 ou superior.

Para verificar novamente sua versão java, execute na linha de comando:

```bash
   java -version
```

Você deve ver uma mensagem mostrando a versão do Java.

```bash
   java version "1.5.0_07"
   Java(TM) 2 Runtime Environment, Standard Edition (build 1.5.0_07-b03)
   Java HotSpot(TM) Client VM (build 1.5.0_07-b03, mixed mode)
```

Se você vir um número de versão inferior, pode ser necessário atualizar o JRE,
ou você pode simplesmente precisar adicioná-lo à sua variável de ambiente PATH.


### Erro 404 ao executar o comando getNewBrowserSession

Se você receber um erro 404 ao tentar abrir uma página em
"http://www.google.com/selenium-server/", então deve ser porque o servidor Selenium não foi configurado corretamente como proxy. O diretório "selenium-server"
não existe no google.com; só parece existir quando o proxy é
configurado corretamente. A configuração do proxy depende muito de como o navegador é
lançado com firefox, iexplore, opera ou custom.

* iexplore: se o navegador for iniciado usando \*iexplore, você pode estar
   tendo um problema com as configurações de proxy do Internet Explorer.
   O servidor Selenium tenta definir as configurações globais de proxy no painel de controle Opções da Internet.
   Você deve se certificar de que elas estão corretamente
   configuradas quando o servidor Selenium inicia o navegador. Tente olhar para
   seu painel de controle Opções da Internet. Clique na guia "Conexões"
   e clique em "Configurações da LAN".
   * Se você precisar usar um proxy para acessar o aplicativo que deseja testar,
     você precisará iniciar o Selenium Server com "-Dhttp.proxyHost";
     veja _`Configuração de Proxy`_ para mais detalhes.
   * Você também pode tentar configurar seu proxy manualmente e, em seguida, iniciar
     o navegador com \*custom ou com o iniciador de navegador \*iehta.
      	   
* custom: ao usar \*custom, você deve configurar o proxy corretamente (manualmente),
   caso contrário, você obterá um erro 404. Verifique novamente se você configurou seu proxy corretamente.
   Verificar se você configurou o proxy corretamente é configurar intencionalmente o navegador de forma incorreta. Tente configurar o
   navegador para usar o nome de host do servidor proxy errado ou a porta errada. Se você tinha
   configurado com sucesso as configurações de proxy do navegador incorretamente, então o
   navegador não conseguirá se conectar à Internet, o que é uma maneira de
   certificar-se de que está ajustando as configurações relevantes.
  
* Para outros navegadores (\*firefox, \*opera), codificamos automaticamente
   o proxy para você e, portanto, não há problemas conhecidos com essa funcionalidade.
   Se você estiver encontrando erros 404 e tiver seguido este guia do usuário cuidadosamente
   publique seus resultados no grupo de usuários para obter ajuda da comunidade de usuários.
      
### Erro de permissão negada

O motivo mais comum para esse erro é que sua sessão está tentando violar
a política de mesma origem cruzando os limites do domínio (por exemplo, acessa uma página de
http://domínio1 e, em seguida, acessa uma página de http://domínio2) ou troca de protocolos
(passando de http://domainX para https://domainX).

Este erro também pode ocorrer quando o JavaScript tenta encontrar objetos de IU
que ainda não estão disponíveis (antes que a página seja completamente carregada), ou
não estão mais disponíveis (após a página
começar a ser descarregada). Isso é mais comumente encontrado com páginas AJAX
que estão trabalhando com seções de uma página ou subframes que carregam e / ou recarregam
independentemente da página maior. 

Este erro pode ser intermitente. Muitas vezes é impossível reproduzir o problema
com um depurador porque o problema decorre de condições de corrida que
não são reproduzíveis quando a sobrecarga do depurador é adicionada ao sistema.
As questões de permissão são abordadas com alguns detalhes no tutorial. Leia a seção
sobre a _`Política da Mesma Origem`_, _`Injeção de Proxy`_ com cuidado. 


### Gerenciando janelas pop-up do navegador

Existem vários tipos de "Popups" que você pode obter durante um teste Selenium.
Você pode não conseguir fechar esses pop-ups executando comandos do Selenium se
eles são iniciados pelo navegador e não pela aplicação testada. Você pode
precisar saber como gerenciá-los. Cada tipo de pop-up precisa ser tratado de maneira diferente.

* Diálogos de autenticação básica de HTTP: esses diálogos solicitam um
   nome de usuário / senha para fazer o login no site. Para fazer login em um site que requer
   autenticação básica HTTP, use um nome de usuário e senha no URL, como
   descrito em _`RFC 1738`_, assim: open("http://myusername:myuserpassword@myexample.com/blah/blah/blah").

* Avisos de certificado SSL: Selenium RC tenta automaticamente falsificar certificados SSL
   quando está habilitado como proxy; veja mais sobre isso
   na seção HTTPS. Se o seu navegador estiver configurado corretamente,
   você nunca deve ver avisos de certificado SSL, mas pode ser necessário
   configurar seu navegador para confiar em nossa perigosa CA SSL "CyberVillains".
   Novamente, consulte a seção HTTPS para saber como fazer isso.

* caixas de diálogo de alerta / confirmação / prompt de JavaScript modais: Selenium tenta ocultar
   essas caixas de diálogo de você (substituindo window.alert, window.confirm e
   window.prompt) para que não parem a execução da sua página. Se você está
   vendo um pop-up de alerta, provavelmente é porque ele disparou durante o processo de carregamento da página,
   o que geralmente é muito cedo para protegermos a página. Selenese contém comandos
   para afirmar ou verificar pop-ups de alerta e confirmação. Veja as seções sobre estes
   tópicos no Capítulo 4.

      
### No Linux, por que minha sessão do navegador Firefox não está fechando?

No Unix / Linux, você deve invocar "firefox-bin" diretamente, então certifique-se de que este
executável está no *path*. Se estiver executando o Firefox por meio de um
script de shell, quando chegar a hora de encerrar o navegador, o Selenium RC irá encerrar
o script de shell, deixando o navegador em execução. Você pode especificar o caminho
para o firefox-bin diretamente, assim:
      
```bash
   cmd=getNewBrowserSession&1=*firefox /usr/local/firefox/firefox-bin&2=http://www.google.com
```

### Firefox \*chrome não funciona com perfil personalizado

Verifique a pasta de perfil do Firefox -> prefs.js -> user_pref ("browser.startup.page", 0);
Comente esta linha assim: "//user_pref("browser.startup.page", 0); " e tente novamente.


### Posso carregar um pop-up personalizado enquanto a página pai está carregando (ou seja, antes que a função javascript window.onload() da página principal seja executada)?

Não. O Selenium depende de interceptadores para determinar os nomes das janelas à medida que são carregadas.
Esses interceptores funcionam melhor na captura de novas janelas se as janelas forem carregadas DEPOIS
a função onload(). O Selenium pode não reconhecer as janelas carregadas antes da função onload.
  
### Firefox no Linux 

No Unix / Linux, versões do Selenium anteriores a 1.0 precisavam invocar "firefox-bin"
diretamente, então, se você estiver usando uma versão anterior, certifique-se de que o real
executável está no caminho.

Na maioria das distribuições Linux, o verdadeiro *firefox-bin* está localizado em:

```bash
   /usr/lib/firefox-x.x.x/ 
```

Onde x.x.x é o número da versão que você possui atualmente. Então, para adicionar esse caminho
no *path* do usuário. você terá que adicionar o seguinte ao seu arquivo .bashrc:

```bash
   export PATH="$PATH:/usr/lib/firefox-x.x.x/"
```

Se necessário, você pode especificar o caminho para o firefox-bin diretamente em seu teste,
assim:

```bash
   "*firefox /usr/lib/firefox-x.x.x/firefox-bin"
```

### IE e atributos de estilo

Se você estiver executando seus testes no Internet Explorer e não conseguir localizar
elementos usando seu atributo `style`.
Por exemplo:

```bash
    //td[@style="background-color:yellow"]
```

Isso funcionaria perfeitamente no Firefox, Opera ou Safari, mas não com o IE.
O IE interpreta as chaves em `@style` como maiúsculas. Então, mesmo que o
o código-fonte está em letras minúsculas, você deve usar:

```bash
    //td[@style="BACKGROUND-COLOR:yellow"]
```

Isso é um problema se o seu teste se destina a funcionar em vários navegadores, mas
você pode facilmente codificar seu teste para detectar a situação e tentar o localizador alternativo
que só funciona no IE.

### Erro encontrado - "Cannot convert object to primitive value" no delsigamento do navegador \*googlechrome

Para evitar esse erro, você deve iniciar o navegador com uma opção que desativa as verificações da política de mesma origem: 

```bash
   selenium.start("commandLineFlags=--disable-web-security");
```
   

### Erro encontrado no IE - "Couldn't open app window; is the pop-up blocker enabled?"

Para evitar esse erro, você deve configurar o navegador: desative o bloqueador de pop-ups
e desmarque a opção 'Ativar modo protegido' em Ferramentas >> Opções >> Segurança.
