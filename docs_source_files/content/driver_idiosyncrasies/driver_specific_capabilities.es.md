---
title: "Capacidades especificas de los controladores"
weight: 2
---

## Firefox

### Definir Capacidades (_Capabilities_) usando `FirefoxOptions`

`FirefoxOptions` es la nueva forma de definir capacidades para el navegador Firefox y, por lo general, debe usarse con preferencia a las capacidades deseadas.

{{< code-tab >}}
  {{< code-panel language="java" >}}
FirefoxOptions options = new FirefoxOptions();
options.addPreference("network.proxy.type", 0);
driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.firefox.options import Options
options = Options()
options.headless = True
driver = webdriver.Firefox(options=options)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new FirefoxOptions();
options.Proxy.Kind = ProxyKind.Direct;
var driver = new FirefoxDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Todavía no tenemos una muestra de código Ruby: ayúdenos y genere un PR (_pull request_)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Todavía no tenemos una muestra de código JavaScript: ayúdenos y genere un PR (_pull request_)
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = new FirefoxOptions()
options.addPreference("network.proxy.type", 0)
driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}


### Configuración de un perfil personalizado

Es posible crear un perfil personalizado para Firefox como se muestra a continuación.

{{< code-tab >}}
  {{< code-panel language="java" >}}
FirefoxProfile profile = new FirefoxProfile();
FirefoxOptions options = new FirefoxOptions();
options.setProfile(profile);
driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile
options=Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Todavía no tenemos una muestra de código Ruby: ayúdenos y genere un PR (_pull request_)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Todavía no tenemos una muestra de código JavaScript: ayúdenos y genere un PR (_pull request_)
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}

## Internet Explorer

### fileUploadDialogTimeout

En algunos entornos, Internet Explorer puede agotar el tiempo de espera al abrir el cuadro de diálogo Cargar archivo. IEDriver tiene un tiempo de espera predeterminado de 1000 ms, pero puede aumentar el tiempo de espera usando la _capability_ fileUploadDialogTimeout.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// We don't have a Java code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# We don't have a Python code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new InternetExplorerOptions();
options.FileUploadDialogTimeout = TimeSpan.FromMilliseconds(2000);
var driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}
