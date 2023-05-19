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

## Quatro maneiras diferentes de usar os drivers

### 1. Gerenciador Selenium <small>(Beta)</small>

{{< badge-version version="4.6" >}}

Selenium Manager helps you to get a working environment to run Selenium out of the box
(no additional downloads! no additional configurations!).
Selenium Manager attempts to obtain the most correct driver for any browser
supported by Selenium in a performant way.
Selenium Manager is currently "opt-in," which means
that it is only used if code would otherwise fail.
That means if you manage drivers by one of the approaches below, Selenium Manager
will not be used.


### 2. Software de gerenciamento de Driver 

Before Selenium Manager was created, many users turned to other projects to automatically
manage their drivers. Most of the functionality of these libraries exists natively in
the latest version of Selenium.

If you can't use Selenium Manager because you are using
an older version of Selenium (please upgrade),
or need an advanced feature not yet implemented by Selenium Manager,
you might try one of these tools:

* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) (Java)
* [WebDriver Manager](https://github.com/SergeyPirogov/webdriver_manager) (Python)
* [WebDriver Manager Package](https://github.com/rosolko/WebDriverManager.Net) (.NET)
* [webdrivers gem](https://github.com/titusfortner/webdrivers) (Ruby)

### 3. A variável de ambiente  `PATH`
Note: we highly recommend removing drivers from `PATH` and using [Selenium Manager](#1-selenium-manager--beta-) if possible.

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
You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver --version
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
You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver --version
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
You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver.exe --version
```
{{% /tab %}}
{{< /tabpane >}}

Se o seu `PATH` estiver configurado corretamente como acima,
you will see the version printed like:

```shell
ChromeDriver 111.0.5563.64 (c710e93d5b63b7095afe8c2c17df34408078439d-refs/branch-heads/5563@{#995})
```

If it is not found, you'll see:
```shell
chromedriver.exe : The term 'chromedriver.exe' is not recognized as the name of a cmdlet, function, script file, or operable program
```

### 4. Localização definida no código
Note: we highly recommend not directly referencing the drivers and using [Selenium Manager](#1-selenium-manager--beta-) if possible.

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

Nota: O Opera driver já não inclui as funcionalidades mais recentes do Selenium e oficialmente deixou de ser suportado.


## Próximo Passo
[Programando o seu primeiro script Selenium]({{< ref "first_script.md" >}})
