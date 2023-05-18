---
title: "ブラウザーのドライバーをインストールする"
linkTitle: "ブラウザーのドライバーをインストールする"
weight: 4
needsTranslation: true
description: >
  自動化するブラウザを設定する。
aliases: [
"/documentation/ja/selenium_installation/installing_webdriver_binaries/",
"/documentation/ja/webdriver/driver_requirements/",
"/ja/documentation/getting_started/installing_browser_drivers/"
]
---

Seleniumは、WebDriverを介して、Chrome/Chromium、Firefox、Internet Explorer、Edge、Safari
などの市場にあるすべての主要なブラウザーをサポートします。 
可能な場合、WebDriverは、ブラウザーに組み込まれている自動化のサポートを使用してブラウザーを動かします。

Internet Explorerを除くすべてのドライバーの実装は、ブラウザーベンダー自身によって提供されているため、
標準のSeleniumディストリビューションには含まれていません。 
この章では、さまざまなブラウザを使い始めるための基本的な要件について説明します。

Read about more advanced options for starting a driver
in our [driver configuration]({{< ref "/documentation/webdriver/drivers/" >}}) documentation.

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from English to Japanese. 
   Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

## Four Ways to Use Drivers

### 1. Selenium Manager <small>(Beta)</small>

{{< badge-version version="4.6" >}}

Selenium Manager helps you to get a working environment to run Selenium out of the box
(no additional downloads! no additional configurations!).
Selenium Manager attempts to obtain the most correct driver for any browser
supported by Selenium in a performant way.
Selenium Manager is currently "opt-in," which means
that it is only used if code would otherwise fail.
That means if you manage drivers by one of the approaches below, Selenium Manager
will not be used.

### 2. ドライバー管理ソフトウェア

Before Selenium Manager was created, many users turned to other projects to automatically
manage their drivers. Most of the functionality of these libraries exists natively in
the latest version of Selenium.

If you can't use Selenium Manager because you are using
an older version of Selenium (please upgrade),
or need an advanced feature not yet implemented by Selenium Manager,
you might try one of these tools:

* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) (Java)
* [WebDriver Manager](https://github.com/SergeyPirogov/webdriver_manager) (Python)
* [WebDriver Manager Package](https://github.com/rosolko/WebDriverManager.Net) (.NET)
* [webdrivers gem](https://github.com/titusfortner/webdrivers) (Ruby)

### 3. `PATH` 環境変数
Note: we highly recommend removing drivers from `PATH` and using [Selenium Manager](#1-selenium-manager--beta-) if possible.

このオプションでは、最初に手動でドライバーをダウンロードする必要があります
（リンクについては[クイックリファレンス](#クイックリファレンス)を参照してください）。

これは、コードを更新せずにドライバーの場所を変更するための柔軟なオプションであり、各マシンがドライバーを同じ場所に配置する必要なく、複数のマシンで機能します。

`PATH` にすでにリストされているディレクトリにドライバを配置するか、ディレクトリに配置して `PATH` に追加することができます。

* すでに `PATH` にあるディレクトリを確認するには、コマンドプロンプト/ターミナルを開いて次のように入力します。

{{< tabpane text=true persistLang=false >}}
{{% tab header="Bash" %}}
To see what directories are already on `PATH`, open a Terminal and execute:
```shell
echo $PATH
```
If the location to your driver is not already in a directory listed,
you can add a new directory to PATH:
```shell
echo 'export PATH=$PATH:/path/to/driver' >> ~/.bash_profile
source ~/.bash_profile
```
* You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver --version
```
{{% /tab %}}
{{% tab header="Zsh" %}}
To see what directories are already on `PATH`, open a Terminal and execute:
```shell
echo $PATH
```
If the location to your driver is not already in a directory listed,
you can add a new directory to PATH:
```shell
echo 'export PATH=$PATH:/path/to/driver' >> ~/.zshenv
source ~/.zshenv
```
* You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver --version
```
{{% /tab %}}
{{% tab header="Windows" %}}
To see what directories are already on `PATH`, open a Command Prompt and execute:
```shell
echo %PATH%
```
If the location to your driver is not already in a directory listed,
you can add a new directory to PATH:
```shell
setx PATH "%PATH%;C:\WebDriver\bin"
```
* You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver.exe --version
```
{{% /tab %}}
{{< /tabpane >}}

<br />

* If your `PATH` is configured correctly, you will see the version printed like:

```shell
ChromeDriver 111.0.5563.64 (c710e93d5b63b7095afe8c2c17df34408078439d-refs/branch-heads/5563@{#995})
```

If it is not found, you'll see:
```shell
chromedriver.exe : The term 'chromedriver.exe' is not recognized as the name of a cmdlet, function, script file, or operable program
```

### 4. ハードコードされた場所
Note: we highly recommend not directly referencing the drivers and using [Selenium Manager](#1-selenium-manager--beta-) if possible.

上記のオプション3と同様に、ドライバーを手動でダウンロードする必要があります。
（リンクについては [クイックリファレンス](#クイックリファレンス) を参照してください）。 
コードそのものに場所を指定することには、システム上の環境変数を把握する必要がないという利点がありますが、
コードの柔軟性が大幅に低下するという欠点があります。

{{< tabpane langEqualsHeader=true >}}

{{< tab header="Java" >}}

System.setProperty("webdriver.chrome.driver","/opt/WebDriver/bin/chromedriver");
ChromeDriver driver = new ChromeDriver();

{{< /tab >}}

{{< tab header="Python" >}}

from selenium.webdriver.chrome.service import Service
from selenium import webdriver

service = Service(executable_path="/opt/WebDriver/bin/chromedriver")
driver = webdriver.Chrome(service=service)

{{< /tab >}}

{{< tab header="CSharp" >}}

var driver = new ChromeDriver(@"C:\WebDriver\bin");

{{< /tab >}}

{{< tab header="Ruby" >}}

service = Selenium::WebDriver::Service.chrome(path: '/opt/WebDriver/bin/chromedriver')
driver = Selenium::WebDriver.for :chrome, service: service

{{< /tab >}}

{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');

const service = new chrome.ServiceBuilder('/opt/WebDriver/bin/chromedriver');
const driver = new Builder().forBrowser('chrome').setChromeService(service).build();
{{< /tab >}}

{{< tab header="Kotlin" >}}

// Please raise a PR to add code sample

{{< /tab >}}

{{< /tabpane >}}


## クイックリファレンス

| ブラウザー | サポートするOS | 維持管理機関 | ダウンロード | イシュートラッカー |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Downloads](//chromedriver.chromium.org/downloads) | [Issues](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [Downloads](//github.com/mozilla/geckodriver/releases) | [Issues](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows/macOS/Linux | Microsoft | [Downloads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](https://github.com/MicrosoftDocs/edge-developer/issues) |
| Internet Explorer | Windows | Selenium Project | [Downloads](/downloads) | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS High Sierra and newer | Apple | Built in | [Issues](//bugreport.apple.com/logon) |

Note: The Opera driver no longer works with the latest functionality of Selenium and is currently officially unsupported.

## Next Step
[Create your first Selenium script]({{< ref "first_script.md" >}})
