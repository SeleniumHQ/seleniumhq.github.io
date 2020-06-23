---
title: "Chrome Devtools"
weight: 10
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Chinese. Do you speak Chinese? Help us to translate
it by sending us pull requests!
{{% /notice %}}

在Selenium 4 alpha版本通过对Chrome开发协议（Chrome Dev Protocol）的支持添加了大家期待已久的源生Chrome开发工具“DevTools”调用。这将帮助我们获取Chrome开发属性集例如：应用程序缓存、获取、网络、性能、探查器、资源计时、安全性和目标CDP域等。

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

