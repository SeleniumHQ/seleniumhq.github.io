---
title: "Funcionalidade específica do Firefox"
linkTitle: "Firefox"
weight: 6
description: >-
    Estas capacidades e características são específicas ao navegador Mozilla Firefox.
aliases: [
"/pt-br/documentation/capabilities/firefox"
]
---

Por omissão, Selenium 4 é compatível com Firefox 78 ou superior. Recomendamos que use sempre a versão mais recente do geckodriver.

## Opções

Capacidades comuns a todos os navegadores estão descritas na [página Opções]({{< ref "../drivers/options.md" >}}).

Capacidades únicas ao Firefox podem ser encontradas na página da Mozilla para [firefoxOptions](https://developer.mozilla.org/en-US/docs/Web/WebDriver/Capabilities/firefoxOptions)

Este é um exemplo de como iniciar uma sessão Firefox com um conjunto de opções básicas:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L36-L37" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L10-L11" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L34-L35" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L10-L11" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openFirefoxTest.spec.js#L10-L13">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

Alguns exemplos de uso com capacidades diferentes:

### Argumentos

O parametro `args` é usado para indicar uma lista de opções ao iniciar o navegador. 
Opções mais frequentes incluem `-headless` e `"-profile", "/path/to/profile"`

Adicione uma opção:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L44" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L19" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L43" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.js#L12-L14">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Iniciar navegador numa localização específica

O parametro `binary` é usado contendo o caminho para uma localização específica do navegador. 
Como exemplo, pode usar este parametro para indicar ao geckodriver a versão Firefox Nightly ao invés da
versão de produção, quando ambas versões estão presentes no seu computador.

Adicionar uma localização:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L54" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L28" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L53" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Perfis

Existem várias formas de trabalhar com perfis Firefox

<div>
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
FirefoxProfile profile = new FirefoxProfile();
FirefoxOptions options = new FirefoxOptions();
options.setProfile(profile);
driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile
options=Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
profile = Selenium::WebDriver::Firefox::Profile.new
profile['browser.download.dir'] = "/tmp/webdriver-downloads"
options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
driver = Selenium::WebDriver.for :firefox, options: options
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
let profile = '/path to custom profile';
options.setProfile(profile);
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}
</div>


## Service

Service settings common to all browsers are described on the [Service page]({{< ref "../drivers/service.md" >}}).

### Log output

Getting driver logs can be helpful for debugging various issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L62-L63" >}}
**Note**: Java also allows setting file output by System Property:\
Property key: `GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`\
Property value: String representing path to log file
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L36" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L43" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### Console output

To change the logging output to display in the console:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L76-L77" >}}
**Note**: Java also allows setting console output by System Property;\
Property key: `GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`\
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L48" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Log level
There are 7 available log levels: `fatal`, `error`, `warn`, `info`, `config`, `debug`, `trace`.
If logging is specified the level defaults to `info`.

Note that `-v` is equivalent to `-log debug` and `-vv` is equivalent to `log trace`,
so this examples is just for setting the log level generically:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L90-L91" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY`\
Property value: String representation of `FirefoxDriverLogLevel` enum
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L59" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L63" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Truncated Logs

The driver logs everything that gets sent to it, including string representations of large binaries, so
Firefox truncates lines by default. To turn off truncation:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L106-L107" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `GeckoDriverService.GECKO_DRIVER_LOG_NO_TRUNCATE`\
Property value: `"true"` or `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L70" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L72" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Profile Root

The default directory for profiles is the system temporary directory. If you do not have access to that directory,
or want profiles to be created some place specific, you can change the profile root directory:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L118-L119" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `GeckoDriverService.GECKO_DRIVER_PROFILE_ROOT`\
Property value: String representing path to profile root directory
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_firefox.py#L81" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L81" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Special Features

### Extras

Ao invés do Chrome, os extras do Firefos não são adicionados como parte das capacidades, mas sim após iniciar o driver.

Unlike Chrome, Firefox extensions are not added as part of capabilities as mentioned in
[this issue](https://github.com/mozilla/geckodriver/issues/1476),
they are created after starting the driver.

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

#### Instalação

Um arquivo xpi que pode ser obtido da [página Mozilla Extras](https://addons.mozilla.org/en-US/firefox/) 

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L132" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L94" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L137" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L95" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.js#L22-L24">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### Desinstalação

Desinstalar uma extensão implica saber o seu id que pode ser obtido como valor de retorno durante a instalação.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L146" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L106" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L152" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L106" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.js#L22-L25">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### Instalação de extensões não assinadas

Quando trabalhar em uma extensão não terminada ou não publicada, provavelmente ela não estará assinada.
Desta forma, só pode ser instalada como "temporária". Isto pode ser feito passando uma arquivo ZIP ou
uma pasta, este é um exemplo com uma pasta:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L157" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L115" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L165" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L115" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Captura de tela inteira

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Contexto

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
