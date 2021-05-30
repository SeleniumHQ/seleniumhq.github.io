---
title: "Requerimentos do driver"
weight: 2
---

Por meio do WebDriver, o Selenium oferece suporte a todos os principais navegadores do mercado
como Chrom(ium), Firefox, Internet Explorer, Opera e Safari.
Sempre que possível, o WebDriver dirige o navegador
usando o suporte integrado do navegador para automação,
embora nem todos os navegadores tenham suporte oficial para controle remoto.

O objetivo do WebDriver é emular a interação de um usuário real
com o navegador o mais próximo possível.
Isso é possível em vários níveis em diferentes navegadores.
Para obter mais detalhes sobre as diferentes idiossincrasias do driver,
consulte _[Idiossincrasias do driver]({{<ref "/driver_idiosyncrasies/_index.md">}})_.

Mesmo que todos os drivers compartilhem uma única interface voltada para o usuário
para controlar o navegador,
eles têm maneiras ligeiramente diferentes de configurar sessões do navegador.
Uma vez que muitas das implementações de driver são fornecidas por terceiros,
elas não estão incluídas na distribuição padrão do Selenium.

Instanciação do driver, gerenciamento de perfil e várias configurações específicas do navegador
são exemplos de parâmetros que têm requisitos diferentes dependendo do navegador.
Esta seção explica os requisitos básicos
para começar com os diferentes navegadores.

### Adicionando executáveis ao seu *path*
A maioria dos drivers requerem um executável extra para o Selenium se comunicar
com o navegador. Você pode especificar manualmente onde o executável reside
antes de iniciar o WebDriver, mas isso pode tornar seus testes menos portáveis
já que os executáveis precisarão estar no mesmo lugar em todas as máquinas,
ou inclua o executável em seu repositório de código de teste.

Ao adicionar uma pasta contendo os binários do WebDriver aos do seu *path*,
o Selenium será capaz de localizar os binários adicionais sem
exigir seu código de teste para localizar exatamente o driver.

* Crie um diretório para colocar os executáveis, como
_C:\WebDriver\bin_ ou _/opt/WebDriver/bin_
* Adicione o diretório ao seu PATH:
   * No Windows - Abra um prompt de comando como administrador
      e execute o seguinte comando
      para adicionar permanentemente o diretório ao seu PATH
      para todos os usuários em sua máquina:

```shell
setx /m path "%path%;C:\WebDriver\bin\"
```
  * Usuários do Bash no macOS e Linux - Em um terminal:

```shell
export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
```

* Agora você está pronto para testar suas alterações.
   Feche todos os prompts de comando abertos e abra um novo.
   Digite o nome de um dos binários
   na pasta que você criou na etapa anterior,
   por exemplo:

  ```shell
  chromedriver
  ```
* Se o seu `PATH` estiver configurado corretamente,
você verá alguma saída relacionada à inicialização do driver:

```text
Starting ChromeDriver 2.25.426935 (820a95b0b81d33e42712f9198c215f703412e1a1) on port 9515
Only local connections are allowed.
```

Você pode recuperar o controle do seu prompt de comando pressionando <kbd>Ctrl+C</kbd>


### Quick reference

| Navegador | OS Suportado | Mantido por | Download | Problemas |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Downloads](//chromedriver.storage.googleapis.com/index.html) | [Problemas](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [Downloads](//github.com/mozilla/geckodriver/releases) | [Problemas](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows 10 | Microsoft | [Downloads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Problemas](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&amp;q=webdriver) |
| Internet Explorer | Windows | Selenium Project | [Downloads](//selenium-release.storage.googleapis.com/index.html) | [Problemas](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS El Capitan ou mais novo | Apple | Embutido | [Problemas](//bugreport.apple.com/logon) |
| Opera | Windows/macOS/Linux | Opera | [Downloads](//github.com/operasoftware/operachromiumdriver/releases) | [Problemas](//github.com/operasoftware/operachromiumdriver/issues) |


### Chromium/Chrome

Para conduzir o Chrome ou Chromium, você deve fazer o download do
[chromedriver](//sites.google.com/a/chromium.org/chromedriver/downloads)
e colocá-lo em uma pasta que está no PATH do seu sistema.

No Linux ou macOS, isso significa modificar
a variável de ambiente `PATH`.
Você pode ver quais diretórios, separados por dois pontos,
constituem o PATH do seu sistema executando o seguinte comando:

```shell
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```

Para incluir o chromedriver no PATH, se ainda não estiver,
certifique-se de incluir o diretório pai do binário chromedriver.
A linha a seguir irá definir a variável de ambiente `PATH`
com seu conteúdo atual, mais um caminho adicional adicionado após os dois pontos:

```shell
$ export PATH="$PATH:/path/to/chromedriver"
```

Quando o chromedriver estiver disponível em seu PATH,
você deve ser capaz de executar o executável _chromedriver_ de qualquer diretório.

Para instanciar uma sessão do Chrome/Chromium, você pode fazer o seguinte:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

WebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Chrome

driver = Chrome()

#Or use the context manager
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

Remember that you have to set the path to the chromedriver executable.
This is possible using the following line:

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

O chromedriver é implementado como um servidor remoto WebDriver
que instrui o navegador sobre o que fazer ao expor o
interface de proxy de automação interna.


### Firefox

Começando com Selenium 3, a Mozilla assumiu a implementação do
Driver do Firefox, com [geckodriver](//github.com/mozilla/geckodriver).
O novo driver para Firefox é chamado geckodriver e funciona com Firefox
48 e mais recentes. Como o Firefox WebDriver está em desenvolvimento,
quanto mais nova for a versão do Firefox, melhor será o suporte.

Como geckodriver é a nova forma padrão de iniciar o Firefox, você pode
instanciar o Firefox da mesma forma que o Selenium 2:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

WebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Firefox

driver = Firefox()
#Or use the context manager
from selenium.webdriver import Firefox

with Firefox() as driver:
   #your code inside this indent
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

Se você preferir não definir a localização do geckodriver usando PATH,
defina a localização do binário geckodriver programaticamente:

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

Também é possível definir a propriedade em tempo de execução:

```shell
mvn test -Dwebdriver.gecko.driver=/path/to/geckodriver
```

Atualmente é possível reverter para o driver mais antigo, mais completo
do Firefox, instalando o Firefox [47.0.1](//ftp.mozilla.org/pub/firefox/releases/47.0.1/)
ou [45 ESR](//ftp.mozilla.org/pub/firefox/releases/45.0esr/)
e especificando o recurso desejado de **marionette** como
**false**. Versões posteriores do Firefox não são mais compatíveis.


### Edge

Edge é o navegador mais recente da Microsoft, incluído no Windows 10 e Server 2016.
As atualizações do Edge são agrupadas com as principais atualizações do Windows,
então você precisará baixar um binário que corresponda ao número da compilação da sua
compilação do Windows atualmente instalada.
O [site do Edge Developer](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)
contém links para todos os binários disponíveis. Bugs na implementação do EdgeDriver podem ser levantados com
[Microsoft](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&q=webdriver).
Se você gostaria de executar testes no Edge, mas não está executando o Windows 10, a Microsoft
oferece VMs gratuitas para testadores no [site do Edge Developer](//developer.microsoft.com/en-us/microsoft-edge/tools/vms/).

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

WebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Edge

driver = Edge()
#Or use the context manager
from selenium.webdriver import Edge

with Edge() as driver:
   #your code inside this indent
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

Se o driver Edge não estiver presente em seu PATH, você pode definir o PATH usando
a seguinte linha:

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
O Internet Explorer era o navegador padrão da Microsoft até o Windows 10, embora
ainda esteja incluído no Windows 10. O driver do Internet Explorer é o único driver
que o projeto Selenium visa apoiar os mesmos lançamentos que a
[Microsoft considera atual](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer).
Versões mais antigas podem funcionar, mas não serão suportadas.

Embora o projeto Selenium forneça binários tanto para 32 bits quanto para 64 bits, existem algumas
[limitações](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
com o Internet Explorer 10 e 11 com o driver de 64 bits, mas usando o de 32 bits
o driver continua a funcionar bem. Deve-se notar que, como as preferências do Internet Explorer são salvas na conta do usuário conectado, alguma
[configuração adicional é necessária](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver#required-configuration).

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

WebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Ie

driver = Ie()
#Or use the context manager
from selenium.webdriver import Ie

with Ie() as driver:
   #your code inside this indent
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

Se o driver do Internet Explorer não estiver presente em seu PATH, você pode definir o PATH
usando a seguinte linha:

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

A Microsoft também oferece um binário WebDriver para
[Internet Explorer 11 no Windows 7 e 8.1](//www.microsoft.com/en-gb/download/details.aspx?id=44069).
Não é atualizado desde 2014 e é baseado em uma versão preliminar da
Especificação W3. [Jim Evans](//jimevansmusic.blogspot.co.uk/2014/09/using-internet-explorer-webdriver.html)
tem um excelente artigo sobre a implementação da Microsoft.


### Opera

As versões atuais do Opera são construídas com base no mecanismo Chromium,
e WebDriver agora é compatível com o código fechado
[Driver Opera Chromium](//github.com/operasoftware/operachromiumdriver/releases),
que pode ser [adicionado ao seu PATH](#add-executables-to-your-path) ou como um
propriedade do sistema.

Instanciar uma sessão de driver é semelhante a Firefox e Chromium:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

WebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Opera

driver = Opera()
#Or use the context manager
from selenium.webdriver import Opera

with Opera() as driver:
   #your code inside this indent
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

High Sierra e posterior:
* Execute o seguinte comando do terminal na primeira
vez e digite sua senha no prompt para autorizar o WebDriver
```shell
safaridriver --enable
```

El Capitan e Sierra:

* Ative o menu Desenvolvedor nas preferências do Safari
* Marque a opção _Permitir automação remota_ com
o menu Desenvolver
* Execute o seguinte comando do terminal na primeira
vez e digite sua senha no prompt para autorizar WebDriver

```shell
/usr/bin/safaridriver -p 1337</
```

Você pode então iniciar uma sessão de driver usando:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

WebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Safari

driver = Safari()
#Or use the context manager
from selenium.webdriver import Safari

with Safari() as driver:
   #your code inside this indent
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


Aqueles que procuram automatizar o Safari no iOS devem olhar para o
[Projeto Appium](//appium.io/). Enquanto o Safari era anteriormente
disponível para Windows, a Apple há muito abandonou o suporte, tornando-o
uma má escolha de plataforma de teste.


## Navegadores simulados


### HtmlUnit

HtmlUnit é um "navegador sem interface gráfica para programas Java". Ele modela documentos HTML
e fornece uma API que permite invocar páginas, preencher formulários, clicar
links, etc. Possui suporte a JavaScript e é capaz de trabalhar com bibliotecas AJAX,
simulando Chrome, Firefox ou Internet Explorer dependendo da configuração
usada. Foi movido para um
[novo local](http://htmlunit.sourceforge.net/gettingStarted.html).
A fonte é mantida em svn.


### PhantomJS

PhantomJS é um navegador sem cabeçalho baseado em Webkit, embora seja uma versão muito mais antiga
do que o usado pelo Google Chrome ou Safari. Embora historicamente seja uma popular
escolha, agora seria sábio evitar o PhantomJS. O projeto está
sem manutenção
[desde 5 de agosto de 2017](//groups.google.com/forum/#!topic/phantomjs/9aI5d-LDuNE),
portanto, embora a web continue a mudar, o PhantomJS não será atualizado.
Isso foi depois que o Google anunciou a capacidade de executar o Chrome sem cabeçalho,
algo também agora oferecido pelo Firefox da Mozilla.

