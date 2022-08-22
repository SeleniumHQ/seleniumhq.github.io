---
title: "Como atualizar para Selenium 4"
linkTitle: "Como atualizar para Selenium 4"
weight: 10
description: >
  Interessado no Selenium 4? Veja este guia para realizar o upgrade para a ultima versão!
aliases: [
"/pt-br/documentation/getting_started/how_to_upgrade_to_selenium_4/"
]
---

Atualizar para o Selenium 4 deve ser um processo sem dificuldades se você estiver usando uma das linguagens oficialmente suportadas
(Ruby, JavaScript, C#, Python, and Java). Pode haver alguns casos em que alguns problemas podem acontecer,
este guia irá ajudar você a resolvê-los. Vamos passar as etapas para atualizar as dependências do seu
projeto e entender as depreciações e também as mudanças trazidas pela versão atualizada.

Estas são as etapas que seguiremos para atualizar para o Selenium 4:
* Preparando nosso código de teste
* Atualizando as dependências
* Possíveis erros e mensagens de suspensão de uso

Nota: enquanto as versões do Selenium 3.x estavam sendo desenvolvidas, foi implementado o suporte padrão para W3C WebDriver.
Este novo protocolo e o legado JSON Wire Protocol foram suportados. Através da versão 3.11, o código do Selenium passou a ser compátivel com o nível 1 da especificação W3C.
A compatibilidade do código W3C na ultima versão do Selenium 3 irá funcionar como esperado na versão 4.

## Preparando nosso código de teste

Selenium 4 remove suporte para protocolos legados e usa o W3C Webdriver por padrão.
Para a maioria das coisas, essa implementação não irá afetar usuários finais.
As maiores exeções são `Capabilities` e a classe `Actions`.

### Recursos

Se os recursos de teste não forem estruturados para serem compatíveis com W3C, pode fazer com que uma sessão não
seja iniciada. Aqui está a lista de recursos padrão do W3C WebDriver:

* `browserName`
* `browserVersion` (replaces `version`)
* `platformName` (replaces `platform`)
* `acceptInsecureCerts`
* `pageLoadStrategy`
* `proxy`
* `timeouts`
* `unhandledPromptBehavior`

Uma lista atualizada de recursos padrão pode ser encontrada aqui: 
[W3C WebDriver](https://www.w3.org/TR/webdriver1/#capabilities).

Qualquer recurso que não esteja incluido na lista acima, precisa ser incluido um prefixo de fornecedor.
Isso se aplica aos recursos específicos do navegador, bem como aos recursos específicos do fornecedor da nuvem.
Por exemplo, se o seu fornecedor de nuvem usa os recursos `build` e `name` para seus testes, você precisa 
envolvê-los em um bloco `cloud: options` (verifique com seu fornecedor de nuvem o prefixo apropriado).

#### Antes 
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
DesiredCapabilities caps = DesiredCapabilities.firefox();
caps.setCapability("platform", "Windows 10");
caps.setCapability("version", "92");
caps.setCapability("build", myTestBuild);
caps.setCapability("name", myTestName);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), caps);
{{< /tab >}}
{{< tab header="JavaScript" >}}
caps = {};
caps['browserName'] = 'Firefox';
caps['platform'] = 'Windows 10';
caps['version'] = '92';
caps['build'] = myTestBuild;
caps['name'] = myTestName;
{{< /tab >}}
{{< tab header="CSharp" >}}
DesiredCapabilities caps = new DesiredCapabilities();
caps.SetCapability("browserName", "firefox");
caps.SetCapability("platform", "Windows 10");
caps.SetCapability("version", "92");
caps.SetCapability("build", myTestBuild);
caps.SetCapability("name", myTestName);
var driver = new RemoteWebDriver(new Uri(CloudURL), caps);
{{< /tab >}}
{{< tab header="Ruby" >}}
caps = Selenium::WebDriver::Remote::Capabilities.firefox
caps[:platform] = 'Windows 10'
caps[:version] = '92'
caps[:build] = my_test_build
caps[:name] = my_test_name
driver = Selenium::WebDriver.for :remote, url: cloud_url, desired_capabilities: caps
{{< /tab >}}
{{< tab header="Python" >}}
caps = {}
caps['browserName'] = 'firefox'
caps['platform'] = 'Windows 10'
caps['version'] = '92'
caps['build'] = my_test_build
caps['name'] = my_test_name
driver = webdriver.Remote(cloud_url, desired_capabilities=caps)
{{< /tab >}}
{{< /tabpane >}}

#### Depois
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
FirefoxOptions browserOptions = new FirefoxOptions();
browserOptions.setPlatformName("Windows 10");
browserOptions.setBrowserVersion("92");
Map<String, Object> cloudOptions = new HashMap<>();
cloudOptions.put("build", myTestBuild);
cloudOptions.put("name", myTestName);
browserOptions.setCapability("cloud:options", cloudOptions);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), browserOptions);
{{< /tab >}}
{{< tab header="JavaScript" >}}
capabilities = {
  browserName: 'firefox',
  browserVersion: '92',
  platformName: 'Windows 10',
  'cloud:options': {
     build: myTestBuild,
     name: myTestName,
  }
}
{{< /tab >}}
{{< tab header="CSharp" >}}
var browserOptions = new FirefoxOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "92";
var cloudOptions = new Dictionary<string, object>();
cloudOptions.Add("build", myTestBuild);
cloudOptions.Add("name", myTestName);
browserOptions.AddAdditionalOption("cloud:options", cloudOptions);
var driver = new RemoteWebDriver(new Uri(CloudURL), browserOptions);
{{< /tab >}}
{{< tab header="Ruby" >}}
options = Selenium::WebDriver::Options.firefox
options.browser_version = 'latest'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options[:build] = my_test_build
cloud_options[:name] = my_test_name
options.add_option('cloud:options', cloud_options)
driver = Selenium::WebDriver.for :remote, url: cloud_url, capabilities: options
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options as FirefoxOptions
options = FirefoxOptions()
options.browser_version = '92'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options['build'] = my_test_build
cloud_options['name'] = my_test_name
options.set_capability('cloud:options', cloud_options)
driver = webdriver.Remote(cloud_url, options=options)
{{< /tab >}}
{{< /tabpane >}}

### Utilitário para encontrar elemento (s) no Java
O utilitário para localizar elementos no Java (interfaces `FindsBy`) foram removidos
visto que se destinavam apenas a uso interno. Os exemplos de código a seguir explicam isso melhor.

Encontrando um único elemento com `findElement*`

{{< cardpane >}}
{{< card header="Antes" >}}
```java
driver.findElementByClassName("className");
driver.findElementByCssSelector(".className");
driver.findElementById("elementId");
driver.findElementByLinkText("linkText");
driver.findElementByName("elementName");
driver.findElementByPartialLinkText("partialText");
driver.findElementByTagName("elementTagName");
driver.findElementByXPath("xPath");
```
{{< /card >}}
{{< card header="Depois" >}}
```java
driver.findElement(By.className("className"));
driver.findElement(By.cssSelector(".className"));
driver.findElement(By.id("elementId"));
driver.findElement(By.linkText("linkText"));
driver.findElement(By.name("elementName"));
driver.findElement(By.partialLinkText("partialText"));
driver.findElement(By.tagName("elementTagName"));
driver.findElement(By.xpath("xPath"));
```
{{< /card >}}
{{< /cardpane >}}

Encontrando multiplos elementos com `findElements*`

{{< cardpane >}}
{{< card header="Antes" >}}
```java
driver.findElementsByClassName("className");
driver.findElementsByCssSelector(".className");
driver.findElementsById("elementId");
driver.findElementsByLinkText("linkText");
driver.findElementsByName("elementName");
driver.findElementsByPartialLinkText("partialText");
driver.findElementsByTagName("elementTagName");
driver.findElementsByXPath("xPath");
```
{{< /card >}}
{{< card header="Depois" >}}
```java
driver.findElements(By.className("className"));
driver.findElements(By.cssSelector(".className"));
driver.findElements(By.id("elementId"));
driver.findElements(By.linkText("linkText"));
driver.findElements(By.name("elementName"));
driver.findElements(By.partialLinkText("partialText"));
driver.findElements(By.tagName("elementTagName"));
driver.findElements(By.xpath("xPath"));
```
{{< /card >}}
{{< /cardpane >}}


## Atualizando as dependências
Verifique as subseções abaixo para isntalar o Selenium 4 e atualizar as dependências do seu projeto

### Java

O processo de atualização do Selenium depende de  qual ferramenta de compilação está sendo usada. Vamos mostrar as mais comuns para Java, como  [Maven](https://maven.apache.org/) e [Gradle](https://gradle.org/). A versão minínma do Java ainda é 8.

#### Maven

{{< cardpane >}}
{{< card header="Antes" >}}
```xml
<dependencies>
  <!-- more dependencies ... -->
  <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.141.59</version>
  </dependency>
  <!-- more dependencies ... -->
</dependencies>
```
{{< /card >}}
{{< card header="Depois" >}}
```xml
<dependencies>
    <!-- more dependencies ... -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.4.0</version>
    </dependency>
    <!-- more dependencies ... -->
</dependencies>
```
{{< /card >}}
{{< /cardpane >}}
Após realizar a mudança, você pode executar `mvn clean compile` no mesmo diretório, onde o 
arquivo `pom.xml` está.

#### Gradle

{{< cardpane >}}
{{< card header="Antes" >}}
```jsonpath
plugins {
    id 'java'
}
group 'org.example'
version '1.0-SNAPSHOT'
repositories {
    mavenCentral()
}
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '3.141.59'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< card header="Depois" >}}
```jsonpath
plugins {
    id 'java'
}
group 'org.example'
version '1.0-SNAPSHOT'
repositories {
    mavenCentral()
}
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '4.4.0'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< /cardpane >}}

Após realizar a mudança, você pode executar `./gradlew clean build` no mesmo diretório onde o arquivo `build.gradle`está.

Para verifica todas as versões do Java, você pode ir até [MVNRepository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java).

### C#

O local para obter atualizações para Selenium 4 em C# é [NuGet](https://www.nuget.org/)
Dentro do pacaote [`Selenium.WebDriver`](https://www.nuget.org/packages/Selenium.WebDriver/4.4.0) você pode seguir as instruções para atualizar para ultima versão.
Dentro do Visual Studio, através do NuGet Package Manager você pode executar:

```shell
PM> Install-Package Selenium.WebDriver -Version 4.4.0
```

### Python
A mudança mais importante para usar o Python é a versão minima requerida. Para Selenium 4 a versão miníma requerida será Python3.7 ou superior.
Mais detalhes podem ser encontrados aqui:[Python Package Index](https://pypi.org/project/selenium/4.4.3/).
Para atualizar através da linha de comando, você pode executar:

```shell
pip install selenium==4.4.3
```

### Ruby

Detalhes para atualizar para o Selenium 4 podem ser vistos aqui:
[selenium-webdriver](https://rubygems.org/gems/selenium-webdriver/versions/4.4.0)  gem in RubyGems
Para instalar a ultima versão, você pode executar:

```shell
gem install selenium-webdriver
```
Para adicioná-lo ao seu Gemfile:

```shell
gem 'selenium-webdriver', '~> 4.4.0'
```

### JavaScript

O pacote selenium-webdriver pode ser encontrado pelo Node package manager, 
[npmjs](https://www.npmjs.com). Selenium 4 pode ser encontrado [aqui](https://www.npmjs.com/package/selenium-webdriver/v/4.4.0).
Para instalar, você pode executar:

```shell
npm install selenium-webdriver
```

Ou, atualize o seu package.json e execute `npm install`:

```json
{
  "name": "selenium-tests",
  "version": "1.0.0",
  "dependencies": {
    "selenium-webdriver": "^4.4.0"
  }
}
```

## Possíveis erros e mensagens de descontinuação

Aqui temos um conjunto de exemplos de código que o ajudarão a superar as mensagens de descontinuação, que você pode
encontrar após atualizar para o Selenium 4.

### Java

#### Waits e Timeout
Os parametros que eram esperados de ser recebidos em um Timeout trocaram de `(long time, TimeUnit unit)` para
o `(Duration duration)`.

{{< cardpane >}}
{{< card header="Antes" >}}
```java
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().timeouts().setScriptTimeout(2, TimeUnit.MINUTES);
driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
```
{{< /card >}}
{{< card header="Depois" >}}
```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
```
{{< /card >}}
{{< /cardpane >}}
As esperas(waits) também esperam parâmetros diferentes agora. O `WebDriverWait` 
agora espera uma `Duration` em vez de um tempo limite `long` em segundos e milissegundos. 
Os métodos utilitários `withTimeout` e `pollingEvery` do `FluentWait` passaram do 
`(long time, TimeUnit unit)` para o `(Duration duration)`.

{{< cardpane >}}
{{< card header="Antes" >}}
```java
new WebDriverWait(driver, 3)
.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));

Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(30, TimeUnit.SECONDS)
  .pollingEvery(5, TimeUnit.SECONDS)
  .ignoring(NoSuchElementException.class);
```
{{< /card >}}
{{< card header="Depois" >}}
```java
new WebDriverWait(driver, Duration.ofSeconds(3))
  .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));

  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);
```
{{< /card >}}
{{< /cardpane >}}

#### A fusão de recursos não estã mais alterando o objeto de invocação
Antes era possível fundir um conjunto diferente de recursos em outro counjunto, e isso 
alterava o objeto de chamada. Agora, o resultado da operação de fusão precisa ser atribuído.

{{< cardpane >}}
{{< card header="Antes" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options.merge(capabilities);
```
Como resultado, o objeto `options` estava sendo modificado.
{{< /card >}}
{{< card header="Depois" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options = options.merge(capabilities);
```
O resultado da chamada `merge`  precisa ser atribuído a um objeto.
{{< /card >}}
{{< /cardpane >}}

#### Firefox Legacy
Antes do GeckoDriver existir, o projeto Selenium tinha uma implementação de driver para automatizar 
o Firefox(versão<48). Entretanto, esta implementação não é mais necessária, pois não funciona
nas versões mais recentes do Firefox. Para evitar graves problemas ao atualizar para o Selenium 4, 
a opção `setLegacy` será mostrada como obsoleta. A recomendação é parar de utilizar a implementação 
antiga e depender apenas do GeckoDriver. O código a seguir mostrará a linha `setLegacy` obsoleta após 
atualizar.

```java
FirefoxOptions options = new FirefoxOptions();
options.setLegacy(true);
```

#### `BrowserType`
A interface `BrowserType` existe há um bom tempo, más ela está ficando 
obsoleta a favor da nova interface `Browser`.

{{< cardpane >}}
{{< card header="Antes" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("browserVersion", "92");
capabilities.setCapability("browserName", BrowserType.FIREFOX);
```
{{< /card >}}
{{< card header="Depois" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("browserVersion", "92");
capabilities.setCapability("browserName", Browser.FIREFOX);
```
{{< /card >}}
{{< /cardpane >}}

### C#

#### `AddAdditionalCapability` está descontinuada

Em vez dela, `AddAdditionalOption` é recomendada. Aqui está um exemplo mostrando isso:

{{< cardpane >}}
{{< card header="Antes" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalCapability("cloud:options", cloudOptions, true);
```
{{< /card >}}
{{< card header="Depois" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalOption("cloud:options", cloudOptions);
```
{{< /card >}}
{{< /cardpane >}}

### Python

#### `executable_path foi descontinuada, por favor, passe um Service object`
No Selenium 4, você precisara definir o ``executable_path`` a partir de um objeto Service para evitar avisos de depreciação.
(Ou não defina o caminho e, em vez disso, certifique-se de que o driver que você precisa esteja no System PATH.)

{{< cardpane >}}
{{< card header="Antes" >}}
```python
from selenium import webdriver
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-automation"])
options.add_experimental_option("useAutomationExtension", False)
driver = webdriver.Chrome(executable_path=CHROMEDRIVER_PATH, options=options)
```
{{< /card >}}
{{< card header="Depois" >}}
```python
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-automation"])
options.add_experimental_option("useAutomationExtension", False)
service = ChromeService(executable_path=CHROMEDRIVER_PATH)
driver = webdriver.Chrome(service=service, options=options)
```
{{< /card >}}
{{< /cardpane >}}

## Resumo
Passamos pelas principais mudanças a serem levadas em consideração ao atualizar para o Selenium 4.
Cobrimos os diferentes aspectos a serem cobertos quando o código de teste é preparado para a atualização, incluindo
sugestões sobre como evitar possíveis problemas que podem aparecer ao usar a nova versão do
Selenium. Para finalizar, também abordamos um conjunto de possíveis problemas com os quais você pode se deparar depois
da atualização e compartilhamos possíveis correções para esses problemas.

*Este tópico foi originalmente postado no site  https://saucelabs.com/resources/articles/how-to-upgrade-to-selenium-4*
