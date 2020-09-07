---
title: "ページ読み込み戦略"
weight: 8
---

現在のセッションのページ読み込み戦略を定義します。
デフォルトでは、Selenium WebDriverがページを読み込む場合、 pageLoadStrategy は _normal_ となります。
ページの読み込みに時間がかかる場合は、追加のリソース（画像、CSS、JSなど）のダウンロードを停止することを常にお勧めします。

ドキュメントの `document.readyState` プロパティは、現在のドキュメントの読み込み状態を記述します。
デフォルトでは、WebDriverは、ドキュメントの準備完了状態が `complete` になるまで、 `driver.get()`（または）`driver.navigate().to()` の呼び出しへの応答を保留します。

SPAアプリケーション（Angular、React、Emberなど）では、動的コンテンツが既にロードされている（つまり、一度 pageLoadStrategy のステータスがCOMPLETEになっている）場合、リンクをクリックするか、ページ内で何らかのアクションを実行しても、コンテンツは、プルページの更新なしでクライアント側で動的に読み込まれるので、サーバーに新しいリクエストは行われません。

SPAアプリケーションはサーバーのリクエストなしで多くのビューを動的にロードできるため、新たに `driver.get()` および  `driver.navigate().to()` を実行するまで、pageLoadStrategyは常に `COMPLETE` ステータスを表示します。

WebDriverの _pageLoadStrategy_ は以下の値をサポートします。

## normal

この値は、Selenium WebDriverはページ全体がロードされるまで待機します。
**normal** に設定すると、Selenium WebDriverは、[ロード](https://developer.mozilla.org/ja/docs/Web/API/Window/load_event)イベントの発生が返却されるまで待機します。

何も指定されていない場合、デフォルトでは、 **normal** がブラウザに設定されます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            // Navigate to Url
            driver.get("https://google.com");
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
# Navigate to url
driver.get("http://www.google.com")
driver.quit()

  {{< / code-panel >}}
  {{< code-panel language="c#" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='normal'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("normal");
(async function example() {
    let driver = await new Builder().
                withCapabilities(caps).
                forBrowser('chrome').
                build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
    val chromeOptions = ChromeOptions()
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL)
    val driver = ChromeDriver(chromeOptions)
    try {
        driver.get("https://www.google.com")
    }
    finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}

## eager

この値は、Selenium WebDriverは最初のHTMLドキュメントが完全に読み込まれて解析されるまで待機し、スタイルシート、画像、およびサブフレームの読み込みを破棄します。

**eager** に設定すると、Selenium WebDriverは [DOMContentLoaded](https://developer.mozilla.org/ja/docs/Web/API/Document/DOMContentLoaded_event) イベントの発生が返却されるまで待機します。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            // Navigate to Url
            driver.get("https://google.com");
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
# Navigate to url
driver.get("http://www.google.com")
driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="c#" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='eager'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("eager");
(async function example() {
    let driver = await new Builder().
                withCapabilities(caps).
                forBrowser('chrome').
                build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
    val chromeOptions = ChromeOptions()
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER)
    val driver = ChromeDriver(chromeOptions)
    try {
        driver.get("https://www.google.com")
    }
    finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}

## none

**none** に設定すると、Selenium WebDriverは最初のページがダウンロードされるまで待機します。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            // Navigate to Url
            driver.get("https://google.com");
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
# Navigate to url
driver.get("http://www.google.com")
driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="c#" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.None;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='none'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();
caps.setPageLoadStrategy("none");
(async function example() {
    let driver = await new Builder().
                withCapabilities(caps).
                forBrowser('chrome').
                build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
    val chromeOptions = ChromeOptions()
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE)
    val driver = ChromeDriver(chromeOptions)
    try {
        driver.get("https://www.google.com")
    }
    finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}
