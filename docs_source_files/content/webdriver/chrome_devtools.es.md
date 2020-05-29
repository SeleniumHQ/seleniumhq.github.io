---
title: "Chrome Devtools"
weight: 10
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Spanish. Do you speak Spanish? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Selenium 4 alpha versions have much awaited native support for Chrome Dev Protocol through "DevTools" interface. This helps us getting Chrome Development properties such as Application Cache, Fetch, Network, Performance, Profiler, Resource Timing, Security and Target CDP domains etc.

Chrome DevTools is a set of web developer tools built directly into the Google Chrome browser. DevTools can help you edit pages on-the-fly and diagnose problems quickly, which ultimately helps you build better websites, faster.

## Emulate Geo Location:

Some applications have different features and functionalities across different locations. Automating such applications is difficult because it is hard to emulate the geo locations in the browser using Selenium. But with the help of Devtools, we can easily emulate them. Below code snippet demonstrates that.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public void geoLocationTest(){
  ChromeDriver driver = new ChromeDriver();
  Map coordinates = new HashMap()
  {{
      put("latitude", 50.2334);
      put("longitude", 0.2334);
      put("accuracy", 1);
  }};    
  driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
  driver.get("<your site url>");
}  
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR to add code sample  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR to add code sample  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR to add code sample  
  {{< / code-panel >}}
{{< / code-tab >}}


