---
title: "Driver specifieke capabilities"
weight: 2
---

## Firefox

### Definieer Capabilities met `FirefoxOptions`

`FirefoxOptions` is de nieuwe manier om capabilities te bepalen voor Firefox. Het is aangeraden om FirefoxOptions te gebruiken in plaats van `DesiredCapabilities`.


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
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}


### Setting a custom profile

Het is mogelijk om een eigen Firefox profiel te gebruiken zoals hieronder word getoond.

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
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}

## Internet Explorer

### fileUploadDialogTimeout

Het kan gebeuren dat Internet Explorer een timeout gooit wanneer het File Upload venster geopend word. IEDriver heeft standaard een timeout van 1000ms. Deze kan, in het geval van het File Upload venster, verhoogd worden via de `fileUploadDialogTimeout`-capability.

