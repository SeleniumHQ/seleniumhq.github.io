---
title: "Driver specific capabilities"
weight: 2
---

{{% notice info %}}
<i class="fas fa-language"></i> 页面需要从英语翻译为简体中文。
您熟悉英语与简体中文吗？帮助我们翻译它，通过 pull requests 给我们！
{{% /notice %}}

## Firefox

### Define Capabilities using `FirefoxOptions`

`FirefoxOptions` is the new way to define capabilities for the Firefox
browser and should generally be used in preference to DesiredCapabilities.

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
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = new FirefoxOptions()
options.addPreference("network.proxy.type", 0)
driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}


### Setting a custom profile

It is possible to create a custom profile for Firefox as demonstrated below.

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
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}

## Internet Explorer

### fileUploadDialogTimeout

In some environments, Internet Explorer may timeout when opening the
File Upload dialog. IEDriver has a default timeout of 1000ms, but you
can increase the timeout using the fileUploadDialogTimeout capability.

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
