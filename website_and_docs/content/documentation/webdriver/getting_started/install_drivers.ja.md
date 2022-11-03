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

Selenium Manager helps you to get a working environment to run Selenium out of the box. Beta 1
of Selenium Manager will configure the drivers for Chrome, Firefox, and Edge if they are not 
found on the `PATH`. No extra configuration is needed. Future releases of Selenium Manager 
will eventually even download browsers if necessary.

Read more at the blog announcement for Selenium Manager (link here).

### 2. ドライバー管理ソフトウェア

ほとんどのマシンはブラウザを自動的に更新しますが、ドライバは更新しません。
ブラウザに適切なドライバを確実に入手するために、多くのサードパーティライブラリが役立ちます。

{{< tabpane code=false langEqualsHeader=true >}}
{{% tab header="Java" %}}
**Important:** This package does not currently work for IEDriverServer v4+

1. Import [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
```java
import io.github.bonigarcia.wdm.WebDriverManager;
```
2. Calling `setup()` automatically puts the correct browser driver where the code will see it:
```java
WebDriverManager.chromedriver().setup();
```
3. Just initialize the driver as you normally would:
```java 
ChromeDriver driver = new ChromeDriver();
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/InstallDriversTest.java">
See full example on GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="Python" %}}

1. Import [WebDriver Manager for Python](https://github.com/SergeyPirogov/webdriver_manager)

```py
from webdriver_manager.chrome import ChromeDriverManager
```

2. Use `install()` to get the location used by the manager and pass it into service class

```py
service = Service(executable_path=ChromeDriverManager().install())
```

3. Use `Service` instance when initializing the driver: 
```py
driver = webdriver.Chrome(service=service)
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_install_drivers.py">
See full example on GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="CSharp" %}}
1. Import [WebDriver Manager Package](https://github.com/rosolko/WebDriverManager.Net)

```csharp
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;
```

2. Use the `SetUpDriver()` which requires a config class:

```csharp
new DriverManager().SetUpDriver(new ChromeConfig());
```

3. Initialize your driver as you normally would:

```csharp
var driver = new ChromeDriver()
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/InstallDriversTest.cs">
See full example on GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="Ruby" %}}
1. Add [webdrivers gem](https://github.com/titusfortner/webdrivers) to Gemfile:

```rb
gem 'webdrivers', '~> 5.0'
```

2. Require webdrivers in your project:
```rb
require 'webdrivers'
```

3. Initialize driver as you normally would:
```rb
driver = Selenium::WebDriver.for :chrome
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/install_drivers_spec.rb">
See full example on GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="JavaScript" %}}
 *There is not a recommended driver manager for JavaScript at this time*
{{% /tab %}}
{{% tab header="Kotlin" %}}

1. Import [WebDriver Manager](https://github.com/bonigarcia/webdrivermanager)
```java
import io.github.bonigarcia.wdm.WebDriverManager;
```
2. Call the setup method before initializing the driver as you normally would:
```java
fun chrome(): WebDriver {
    WebDriverManager.chromedriver().setup()
    return ChromeDriver()
}
```

{{% /tab %}}
{{< /tabpane >}}

### 3. `PATH` 環境変数
このオプションでは、最初に手動でドライバーをダウンロードする必要があります
（リンクについては[クイックリファレンス](#クイックリファレンス)を参照してください）。

これは、コードを更新せずにドライバーの場所を変更するための柔軟なオプションであり、各マシンがドライバーを同じ場所に配置する必要なく、複数のマシンで機能します。

`PATH` にすでにリストされているディレクトリにドライバを配置するか、ディレクトリに配置して `PATH` に追加することができます。

* すでに `PATH` にあるディレクトリを確認するには、コマンドプロンプト/ターミナルを開いて次のように入力します。

{{< tabpane code=false persistLang=false >}}
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
* ドライバを起動することで、正しく追加されているかどうかをテストできます。
```shell
chromedriver
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
* ドライバを起動することで、正しく追加されているかどうかをテストできます。
```shell
chromedriver
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
* ドライバを起動することで、正しく追加されているかどうかをテストできます。
```shell
chromedriver.exe
```
{{% /tab %}}
{{< /tabpane >}}

<br />

* If your `PATH` is configured correctly,
* `PATH` が正しく構成されている場合、ドライバーの起動に関連する出力が表示されます。

```
Starting ChromeDriver 95.0.4638.54 (d31a821ec901f68d0d34ccdbaea45b4c86ce543e-refs/branch-heads/4638@{#871}) on port 9515
Only local connections are allowed.
Please see https://chromedriver.chromium.org/security-considerations for suggestions on keeping ChromeDriver safe.
ChromeDriver was started successfully.
```

 <kbd>Ctrl+C</kbd> を押して、コマンドプロンプトの制御を取り戻すことができます。

### 4. ハードコードされた場所

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
