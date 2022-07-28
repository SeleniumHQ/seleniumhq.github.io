---
title: "Recursos compartilhados"
linkTitle: "Recursos compartilhados"
weight: 2
needsTranslation: true
description: >-
  These capabilities are shared by all browsers.
aliases: [
"/documentation/pt-br/driver_idiosyncrasies/shared_capabilities/",
"/pt-br/documentation/webdriver/capabilities/shared_capabilities/",
"/documentation/pt-br/webdriver/http_proxies/",
"/pt-br/documentation/webdriver/http_proxies/",
"/pt-br/documentation/webdriver/capabilities/http_proxies/",
"/documentation/pt-br/webdriver/page_loading_strategy/",
"/pt-br/documentation/webdriver/page_loading_strategy/",
"/pt-br/documentation/webdriver/capabilities/page_loading_strategy/"
]
---

Para criar uma nova sessão por Selenium WebDriver,
a extremidade local deve fornecer os recursos básicos para a extremidade remota.
A extremidade remota usa o mesmo conjunto de recursos para
cria uma sessão e descreve os recursos da sessão atual.

WebDriver fornece recursos que cada controle extremidade
remota irá/deverá apoiar a implementação.
A seguir estão os recursos que o WebDriver suporta:

## browserName:

Este recurso é usado para definir o `browserName` para uma determinada sessão.
Se o navegador especificado não estiver instalado na
extremidade remota, a criação da sessão irá falhar

## browserVersion: 

Este recurso é opcional, usado para
definir a versão do navegador disponível na extremidade remota.
Por exemplo, se pedir o Chrome versão 75 em um sistema que
tem apenas a versão 80 instalada, a criação da sessão irá falhar

## pageLoadStrategy:

Ao navegar para uma nova página via URL, por padrão o Selenium irá esperar
até que a página esteja totalmente carregada antes de responder. Isso funciona bem para
iniciantes, mas pode causar longos tempos de espera em páginas que carregam um grande
número de recursos de terceiros. Usar uma estratégia não padrão pode tornar a
execução de testes mais rápida em casos como este, mas também pode apresentar instabilidade
onde os elementos na página mudam de posição conforme os elementos são carregados e mudam
de tamanho.

A estratégia de carregamento da página consulta o
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
conforme descrito na tabela abaixo:

| Estrtégia | Ready State | Notas |
| -------- | ----------- | ----- |
| normal | complete | Usado por padrão, aguarda o download de todos os recursos |
| eager | interactive | O acesso ao DOM está pronto, mas outros recursos, como imagens, ainda podem estar carregando |
| none | Any | Não bloqueia o WebDriver de nenhuma forma |

## platformName

Isso identifica o sistema operacional na extremidade remota e 
buscar o `platformName` retorna o nome do sistema operacional.

Em provedores baseados em nuvem,
definir `platformName` define o sistema operacional na extremidade remota.

## acceptInsecureCerts

Este recurso verifica se um `Certificado TLS`
expirado (ou) inválido é usado durante a navegação
durante uma sessão.

Se o recurso for definido como `false`, um
[erro de certificado inseguro](//developer.mozilla.org/en-US/docs/Web/WebDriver/Errors/InsecureCertificate)
será retornado quando a navegação encontrar qualquer
problema de certificado de domínio. Se definido como `verdadeiro`, o certificado inválido será
confiável para o navegador.

Todos os certificados autoassinados serão considerados confiáveis por esse recurso por padrão.
Uma vez definido, o recurso `acceptInsecureCerts` terá um
efeito para toda a sessão.

## timeout

Uma `session` do WebDriver é imposta com um certo intervalo `session timeout`
durante o qual o usuário pode controlar o comportamento
de executar scripts ou recuperar informações do navegador.

Cada tempo limite de sessão é configurado com
combinação de diferentes `timeouts`, conforme descrito abaixo:

### Timeout de Script:
Especifica quando interromper um script em execução em
um contexto de navegação atual. O tempo limite padrão **30.000**
é imposto quando uma nova sessão é criada pelo WebDriver.

### Timeout de Carregamento de Página:
Especifica o intervalo de tempo em que a página da web
precisa ser carregado em um contexto de navegação atual.
O tempo limite padrão **300.000** é imposto quando uma
nova sessão é criada pelo WebDriver. Se os carregamento da página delimitar
um determinado período de tempo, o script será interrompido por
_TimeoutException_.

### Timeout de Espera Implícita:
Isso especifica o tempo de espera pela
estratégia de implicit element location quando
localizando de elementos. O tempo limite padrão **0**
é imposto quando uma nova sessão é criada pelo WebDriver.

## unhandledPromptBehavior

Especifica o estado do `user prompt handler` da sessão atual.
O padrão é **dismiss and notify state** (**dispensar e notificar estado**, em português)

### User Prompt Handler

Isso define qual ação deve ser tomada quando um
o prompt do usuário se encontra na extremidade remota. Isso é definido pelo
recurso `unhandledPromptBehavior` e tem os seguintes estados:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

## setWindowRect

Indicates whether the remote end supports all of the [resizing and repositioning](https://w3c.github.io/webdriver/#resizing-and-positioning-windows) [commands](https://w3c.github.io/webdriver/#dfn-commands).

## strictFileInteractability

O novo recurso indica se as verificações estritas de interatividade
devem ser aplicadas aos elementos _input type = file_. Como as verificações de
interatividade estrita estão desativadas por padrão, há uma mudança no comportamento
ao usar _Element Send Keys_ com controles de upload de arquivos ocultos.

## proxy

Um servidor proxy atua como intermediário para
solicitações entre um cliente e um servidor. De forma simples,
o tráfego flui através do servidor proxy
a caminho do endereço que você solicitou e de volta.

Um servidor proxy para scripts de automação
com Selenium pode ser útil para:

* Capturar o tráfego da rede
* Simular chamadas de back-end feitas pelo site
* Acessar o site necessário em uma rede complexa
  topologias ou restrições / políticas corporativas estritas.

Se você estiver em um ambiente corporativo, e um
navegador não consegue se conectar a um URL, isso é
provavelmente porque o ambiente precisa de um proxy para ser acessado.

O Selenium WebDriver fornece uma maneira de configurações de proxy:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class proxyTest {
public static void main(String[] args) {
Proxy proxy = new Proxy();
proxy.setHttpProxy("<HOST:PORT>");
ChromeOptions options = new ChromeOptions();
options.setCapability("proxy", proxy);
WebDriver driver = new ChromeDriver(options);
driver.get("https://www.google.com/");
driver.manage().window().maximize();
driver.quit();
}
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver

PROXY = "<HOST:PORT>"
webdriver.DesiredCapabilities.FIREFOX['proxy'] = {
"httpProxy": PROXY,
"ftpProxy": PROXY,
"sslProxy": PROXY,
"proxyType": "MANUAL",

}

with webdriver.Firefox() as driver:
# Open URL
driver.get("https://selenium.dev")

{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

public class ProxyTest{
public static void Main() {
ChromeOptions options = new ChromeOptions();
Proxy proxy = new Proxy();
proxy.Kind = ProxyKind.Manual;
proxy.IsAutoDetect = false;
proxy.SslProxy = "<HOST:PORT>";
options.Proxy = proxy;
options.AddArgument("ignore-certificate-errors");
IWebDriver driver = new ChromeDriver(options);
driver.Navigate().GoToUrl("https://www.selenium.dev/");
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}
# este código foi escrito com Selenium 4

proxy = Selenium::WebDriver::Proxy.new(http: '<HOST:PORT>')
cap   = Selenium::WebDriver::Remote::Capabilities.chrome(proxy: proxy)

driver = Selenium::WebDriver.for(:chrome, capabilities: cap)
driver.get('http://google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
let webdriver = require('selenium-webdriver');
let chrome = require('selenium-webdriver/chrome');
let proxy = require('selenium-webdriver/proxy');
let opts = new chrome.Options();

(async function example() {
opts.setProxy(proxy.manual({http: '<HOST:PORT>'}));
let driver = new webdriver.Builder()
.forBrowser('chrome')
.setChromeOptions(opts)
.build();
try {
await driver.get("https://selenium.dev");
}
finally {
await driver.quit();
}
}());
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class proxyTest {
fun main() {

        val proxy = Proxy()
        proxy.setHttpProxy("<HOST:PORT>")
        val options = ChromeOptions()
        options.setCapability("proxy", proxy)
        val driver: WebDriver = ChromeDriver(options)
        driver["https://www.google.com/"]
        driver.manage().window().maximize()
        driver.quit()
    }
}
{{< /tab >}}
{{< /tabpane >}}
