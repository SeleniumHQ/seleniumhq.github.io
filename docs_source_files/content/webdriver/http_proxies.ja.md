---
title: "Http proxies"
weight: 7
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

A proxy acts as an intermediary for requests between client and server.In Simple, a proxy 
lets you go online under different identity masking your original IP. 

The HTTP proxy is a high-performance content filter which examines Web traffic to identify suspicious content, 
malformed content and also protects Web server from attacks.

Sometimes, When you’re in a corporate environment, browsers fails to connect to a host address.
this is most likely because the environment is behind HTTP Proxy.

Selenium provide a way to configure browser with the proxy settings

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Need PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Need pr
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
 # Need PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// need PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// need PR
  {{< / code-panel >}}
{{< / code-tab >}}