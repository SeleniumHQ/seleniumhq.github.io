# Funcionalidade específica do Safari

> Estas capacidades e características são específicas ao navegador Apple Safari.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/browsers/safari/

---

Ao invés dos drivers para Chromium e Firefox, o safaridriver faz parte to sistema Operativo.
Para activar a automação no Safari, execute o seguinte comando no terminal:

```shell
safaridriver --enable
```

## Opções

Capacidades comuns a todos os navegadores estão descritas na [página Opções](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/options/index.md).

Capacidades únicas ao Safari podem ser encontradas na página da Apple [WebDriver para Safari](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari#2957227)

Este é um exemplo de como iniciar uma sessão Safari com um conjunto de opções básicas::

**Java**

```java
SafariOptions options = new SafariOptions();
driver = new SafariDriver(options);
```

**Python**

```python
options = webdriver.SafariOptions()
driver = webdriver.Safari(options=options)
```

**CSharp**

```csharp
var options = new SafariOptions();
driver = new SafariDriver(options);
```

**Ruby**

```ruby
it 'basic options' do
  options = Selenium::WebDriver::Options.safari
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.SAFARI)
  .setSafariOptions(options)
  .build();
```

**Kotlin**

```kotlin
val options = SafariOptions()
val driver = SafariDriver(options)
```

### Mobile (celular)
Se pretende automatizar Safari em iOS, deve olhar para o [Projecto Appium](//appium.io/).

## Service

Service settings common to all browsers are described on the [Service page](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/service/index.md).

### Logging

Unlike other browsers, Safari doesn't let you choose where logs are output, or change levels. The one option
available is to turn logs off or on. If logs are toggled on, they can be found at:`~/Library/Logs/com.apple.WebDriver/`.

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
.withLogging(true)
```

**Note**: Java also allows setting console output by System Property;<br>
Property key: `SafariDriverService.SAFARI_DRIVER_LOGGING`<br>
Property value: `"true"` or `"false"`

**Python**

_Requires [Selenium v4.26](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.26.0) or later._

```python
service = webdriver.SafariService(enable_logging=True)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby

```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Safari Technology Preview

Apple provides a development version of their browser — [Safari Technology Preview](https://developer.apple.com/safari/technology-preview/)
To use this version in your code:

**Java**

```java
options.setUseTechnologyPreview(true);
driver = new SafariDriver(options);
```

**Python**

```python
options = webdriver.SafariOptions()
options.use_technology_preview = True
service = webdriver.SafariService(
    executable_path='/Applications/Safari Technology Preview.app/Contents/MacOS/safaridriver'
)
driver = webdriver.Safari(options=options, service=service)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
                                                'have no support for Safari Technology Preview' do
it 'sets the technology preview' do
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._
