---
title: "Chrome デベロッパーツール"
weight: 10
---

Selenium 4 alphaバージョンには、"DevTools" インターフェースを介した
Chrome Dev Protocolのネイティブサポートが待望されています。 
これにより、アプリケーションキャッシュ、フェッチ、ネットワーク、パフォーマンス、
プロファイラー、リソースタイミング、セキュリティ、
ターゲットCDPドメインなどのChrome開発プロパティを取得できます。

Chrome デベロッパーツールは、Google Chromeブラウザに直接組み込まれた一連のWeb開発ツールです。
DevToolsは、ページをすぐに編集して問題をすばやく診断するのに役立ち、
最終的にはより優れたWebサイトをより速く構築するのに役立ちます。

## ジオロケーションをエミュレート

一部のアプリケーションには、場所によって特徴や機能性が異なります。
このようなアプリケーションの自動化は、ブラウザでSeleniumを使用して
地理的位置をエミュレートすることが難しいため、困難です。
しかし、デベロッパーツールの助けを借りて、それらを簡単にエミュレートできます。
以下のコードスニペットはそのことを示しています。

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
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.devtools.DevTools

fun main() {
    val driver =  ChromeDriver()
    val coordinates : HashMap<String, Any> = HashMap<String, Any> ()
    coordinates.put("latitude", 50.2334)
    coordinates.put("longitude", 0.2334)
    coordinates.put("accuracy", 1)
    driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates)
    driver.get("https://www.google.com")
} 
  {{< / code-panel >}}
{{< / code-tab >}}


