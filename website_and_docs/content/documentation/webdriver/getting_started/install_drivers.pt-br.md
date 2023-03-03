---
title: "Instalando drivers de navegadores"
linkTitle: "Instalando drivers de navegadores"
weight: 4
needsTranslation: true
description: >
 Configurando seu navegador para ficar preparado para ser automatizado.
aliases: [
"/documentation/pt-br/selenium_installation/installing_webdriver_binaries/",
"/documentation/pt-br/webdriver/driver_requirements/",
"/pt-br/documentation/getting_started/installing_browser_drivers/"
]
---

Através do WebDriver, o Selenium suporta todos os principais navegadores do mercado
como Chrome/Chromium, Firefox, Internet Explorer, Edge e Safari.
Sempre que possível, o WebDriver conduz o navegador
usando o suporte integrado do navegador para automação.

Como todas as implementações do driver, exceto a do Internet Explorer, são fornecidas pelos próprios
desenvolvedores dos navegadores, elas não estão incluídas na distribuição padrão do Selenium.
Esta seção explica os requisitos básicos para você começar a usar os diferentes navegadores.

Leia mais sobre opções avançadas para iniciar um driver
 na nossa documentação de [configuração de driver]({{< ref "/documentation/webdriver/drivers/" >}}).

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from English to Portuguese. 
   Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

## Quatro maneiras diferentes de usar os drivers

### 1. Gerenciador Selenium <small>(Beta)</small>

{{< badge-version version="4.6" >}}

O Gerenciador Selenium te ajuda a ter um ambiente de desenvolvimento rodando Selenium mais facilmente.
O primeiro Beta irá configurar os drivers para Chrome, Firefox e Edge se eles não forem
encontrados no `PATH`. Nem uma configuração extra será necessária. Versões futuras do Gerenciador Selenium
irão eventualmente poder realizar o download dos browsers se necessário.

Leia mais no anúncio feito no blog sobre o [Gerenciador Selenium](/blog/2022/introducing-selenium-manager/).


### 2. Software de gerenciamento de Driver 

A maioria das máquinas atualiza automaticamente o navegador, mas não o driver. Para certificar de obter 
o driver correto para o seu navegador de internet, existem diversas bibliotecas de terceiros para auxiliá-lo.

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
**Important:** This package does not currently work for IEDriverServer v4+

1. Importe o [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
```java
import io.github.bonigarcia.wdm.WebDriverManager;
```
2. Invocar o `setup()`:

{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/InstallDriversTest.java#L17-L19" >}}

{{% /tab %}}
{{% tab header="Python" %}}

1. Importe o [Gerenciador de WebDriver para Python](https://github.com/SergeyPirogov/webdriver_manager)

```py
from webdriver_manager.chrome import ChromeDriverManager
```

2. Use o `install()` para obter a localização usada pelo gerenciador WebDriver e passá-la para a classe de serviço

{{< gh-codeblock path="examples/python/tests/getting_started/test_install_drivers.py#L15-L17" >}}

{{% /tab %}}
{{% tab header="CSharp" %}}
1. Importe o [Pacote Gerenciador do WebDriver](https://github.com/rosolko/WebDriverManager.Net)

```csharp
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;
```

2. Use o `SetUpDriver()` que requer uma classe de configuração:

{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/InstallDriversTest.cs#L19-L21" >}}

{{% /tab %}}
{{% tab header="Ruby" %}}
1. Add [webdrivers gem](https://github.com/titusfortner/webdrivers) to Gemfile:

```rb
gem 'webdrivers', '~> 5.0'
```

2. Requer webdrivers no seu projeto:
```rb
require 'webdrivers'
```

3. Inicialize o seu driver como você normalmente faria:
```rb
driver = Selenium::WebDriver.for :chrome
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/install_drivers_spec.rb">
Veja o exemplo completo no GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="JavaScript" %}}
*Não há um gerenciador de driver recomendado para o JavaScript no momento*
{{% /tab %}}
{{% tab header="Kotlin" %}}

1. Importe o [Gerenciador de WebDriver](https://github.com/bonigarcia/webdrivermanager)
```java
import io.github.bonigarcia.wdm.WebDriverManager;
```
2. Invoque o método de configuração antes de inicializar o driver como faria normalmente:

{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/InstallDriversTest.kt#L17-L18" >}}

{{% /tab %}}
{{< /tabpane >}}

### 3. A variável de ambiente  `PATH`
Esta opção requer primeiro o download manual do driver (Vejá a [sessão de Consulta de referencia rápida](#quick-reference) para links).

Esta é uma opção flexível para alterar a localização dos drivers sem precisar atualizar seu código e funcionará
em várias máquinas sem exigir que cada máquina coloque os drivers no mesmo lugar.

Você pode colocar os drivers em um diretório que já está listado em 
`PATH`, ou você pode colocá-los em um diretório
e acrescenta-lo ao `PATH`.

{{< tabpane text=true persistLang=false >}}
{{% tab header="Bash" %}}
Para ver quais diretórios já estão no `PATH`, abra o Terminal e execute:
```shell
echo $PATH
```
Se o local do seu driver ainda não estiver em um diretório listado,
você pode adicionar um novo diretório ao PATH:
```shell
echo 'export PATH=$PATH:/path/to/driver' >> ~/.bash_profile
source ~/.bash_profile
```
Você pode testar se foi adicionado corretamente iniciando o driver:
```shell
chromedriver
```
{{% /tab %}}
{{% tab header="Zsh" %}}
Para ver quais diretórios já estão no `PATH`, abra o Terminal e execute:
```shell
echo $PATH
```
Se o local do seu driver ainda não estiver em um diretório listado,
você pode adicionar um novo diretório ao PATH:
```shell
echo 'export PATH=$PATH:/path/to/driver' >> ~/.zshenv
source ~/.zshenv
```
Você pode testar se foi adicionado corretamente iniciando o driver:
```shell
chromedriver
```
{{% /tab %}}
{{% tab header="Windows" %}}
Para ver quais diretórios já estão no `PATH`, abra o Prompt de Comando e execute:
```shell
echo %PATH%
```
Se o local do seu driver ainda não estiver em um diretório listado,
você pode adicionar um novo diretório ao PATH:
```shell
setx PATH "%PATH%;C:\WebDriver\bin"
```
Você pode testar se foi adicionado corretamente iniciando o driver:
```shell
chromedriver.exe
```
{{% /tab %}}
{{< /tabpane >}}

Se o seu `PATH` estiver configurado corretamente como acima,
você verá algumas saídas relacionadas à inicialização do driver:

```
Starting ChromeDriver 95.0.4638.54 (d31a821ec901f68d0d34ccdbaea45b4c86ce543e-refs/branch-heads/4638@{#871}) on port 9515
Only local connections are allowed.
Please see https://chromedriver.chromium.org/security-considerations for suggestions on keeping ChromeDriver safe.
ChromeDriver was started successfully.
```

Você pode recuperar o controle do seu prompt de comando pressionando <kbd>Ctrl+C</kbd>

### 4. Localização definida no código

Semelhante à opção 3 acima, você precisará baixar manualmente o driver (Vejá a [sessão de Consulta de referencia rápida](#quick-reference) para links).
Especificar a localização no próprio código tem a vantagem de você não precisar se preocupar em descobrir variáveis de ambiente no
seu sistema, mas tem a desvantagem de tornar o código muito menos flexível.

{{< tabpane langEqualsHeader=true >}}

{{< tab header="Java" >}}

System.setProperty("webdriver.chrome.driver","/path/to/chromedriver");
ChromeDriver driver = new ChromeDriver();

{{< /tab >}}

{{< tab header="Python" >}}

from selenium.webdriver.chrome.service import Service
from selenium import webdriver

service = Service(executable_path="/path/to/chromedriver")
driver = webdriver.Chrome(service=service)

{{< /tab >}}

{{< tab header="CSharp" >}}

var driver = new ChromeDriver(@"C:\WebDriver\bin");

{{< /tab >}}

{{< tab header="Ruby" >}}

service = Selenium::WebDriver::Service.chrome(path: '/path/to/chromedriver')
driver = Selenium::WebDriver.for :chrome, service: service

{{< /tab >}}

{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');

const service = new chrome.ServiceBuilder('/path/to/chromedriver');
const driver = new Builder().forBrowser('chrome').setChromeService(service).build();
{{< /tab >}}

{{< tab header="Kotlin" >}}

import org.openqa.selenium.chrome.ChromeDriver

fun main(args: Array<String>) {
    System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver")
    val driver = ChromeDriver()
}

{{< /tab >}}
{{< /tabpane >}}

## Consulta rápida

| Navegador | OS Suportado | Mantido por | Download | Rastreador de Problemas |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Downloads](//chromedriver.chromium.org/downloads) | [Problemas](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [Downloads](//github.com/mozilla/geckodriver/releases) | [Problemas](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows/macOS/Linux | Microsoft | [Downloads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Problemas](https://github.com/MicrosoftDocs/edge-developer/issues) |
| Internet Explorer | Windows | Projeto Selenium | [Downloads](/downloads) | [Problemas](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS High Sierra e superiores | Apple | Integrado no Sistema  | [Problemas](//bugreport.apple.com/logon) |

Note: The Opera driver no longer works with the latest functionality of Selenium and is currently officially unsupported.

## Próximo Passo
[Programando o seu primeiro script Selenium]({{< ref "first_script.md" >}})
