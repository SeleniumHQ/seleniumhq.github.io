---
title: "Chrome开发工具"
weight: 10
---

在Selenium 4 alpha版本通过对Chrome开发工具协议（Chrome DevTools Protocol）的支持添加了大家期待已久的源生Chrome开发工具“DevTools”调用。这将帮助我们获取Chrome开发属性集例如：应用程序缓存、获取、网络、性能、探查器、资源计时、安全性和目标CDP域等。

Chrome开发工具是在谷歌Chrome浏览其中内置的网页开发工具集。开发工具可以帮助你快速编辑页面和诊断问题，最终帮助你更快地建立更好的网站。

## 模拟 Geo location 定位:

有一些应用在不同的定位下有不同的特性和功能。想通过Selenium在浏览器中模拟geo locations定位是从而实现应用的自动化是非常困难的。但是通过开发工具的帮助，我们可以非常简单的模拟它们。下面的代码判断演示了这一点。

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

