---
title: "Selenium4にアップグレードする方法"
linkTitle: "Selenium4にアップグレードする方法"
weight: 10
description: >
  Selenium 4に興味がありますか？ 最新リリースへのアップグレードに役立つこのガイドを確認してください。
aliases: [
"/ja/documentation/getting_started/how_to_upgrade_to_selenium_4/"
]
---

公式にサポートされている言語（Ruby、JavaScript、C＃、Python、およびJava）のいずれかを使用している場合、
Selenium4へのアップグレードは簡単なプロセスです。 
いくつかの問題が発生する可能性がある場合があるかもしれません。このガイドは、それらを整理するのに役立ちます。 
プロジェクトの依存関係をアップグレードする手順を実行し、バージョンのアップグレードによってもたらされる主な非推奨と変更を理解します。

これが、Selenium4にアップグレードするために実行する手順です。
* テストコードの準備
* 依存関係のアップグレード
* 潜在的なエラーと非推奨メッセージ

注：Selenium 3.xバージョンの開発中に、W3CWebDriver標準のサポートが実装されました。 
この新しいプロトコルと従来のJSONワイヤープロトコルの両方がサポートされました。 
バージョン3.11の前後で、SeleniumコードはレベルW3C1仕様に準拠するようになりました。 
Selenium 3の最新バージョンのW3C準拠のコードは、Selenium4で期待どおりに機能します。

## テストコードの準備
Selenium 4は、レガシープロトコルのサポートを削除し、内部でデフォルトでW3CWebDriver標準を使用します。 
ほとんどの場合、この実装はエンドユーザーに影響を与えません。 
主な例外は、`Capabilities`と `アクション` クラスです。

### Capabilities
テスト機能がW3Cに準拠するように構成されていない場合、セッションが開始されない可能性があります。 
W3CWebDriverの標準機能のリストは次のとおりです。
* `browserName`
* `browserVersion` (`version` に変更)
* `platformName` (`platform` に変更)
* `acceptInsecureCerts`
* `pageLoadStrategy`
* `proxy`
* `timeouts`
* `unhandledPromptBehavior`

標準Capabilitiesの最新リストは、 [W3C WebDriver](https://www.w3.org/TR/webdriver1/#capabilities) にあります。

上記のリストに含まれていないCapabilitiesには、ベンダープレフィックスを含める必要があります。 
これは、ブラウザ固有のCapabilitiesとクラウドベンダー固有のCapabilitiesに適用されます。 
たとえば、クラウドベンダーがテストに `build` Capabilities と `name` Capabilitiesを使用している場合は、
それらを `cloud:options` ブロックでラップする必要があります（適切なプレフィックスについては、クラウドベンダーに確認してください）。

#### Before 
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
DesiredCapabilities caps = DesiredCapabilities.firefox();
caps.setCapability("platform", "Windows 10");
caps.setCapability("version", "92");
caps.setCapability("build", myTestBuild);
caps.setCapability("name", myTestName);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), caps);
{{< /tab >}}
{{< tab header="JavaScript" >}}
caps = {};
caps['browserName'] = 'Firefox';
caps['platform'] = 'Windows 10';
caps['version'] = '92';
caps['build'] = myTestBuild;
caps['name'] = myTestName;
{{< /tab >}}
{{< tab header="CSharp" >}}
DesiredCapabilities caps = new DesiredCapabilities();
caps.SetCapability("browserName", "firefox");
caps.SetCapability("platform", "Windows 10");
caps.SetCapability("version", "92");
caps.SetCapability("build", myTestBuild);
caps.SetCapability("name", myTestName);
var driver = new RemoteWebDriver(new Uri(CloudURL), caps);
{{< /tab >}}
{{< tab header="Ruby" >}}
caps = Selenium::WebDriver::Remote::Capabilities.firefox
caps[:platform] = 'Windows 10'
caps[:version] = '92'
caps[:build] = my_test_build
caps[:name] = my_test_name
driver = Selenium::WebDriver.for :remote, url: cloud_url, desired_capabilities: caps
{{< /tab >}}
{{< tab header="Python" >}}
caps = {}
caps['browserName'] = 'firefox'
caps['platform'] = 'Windows 10'
caps['version'] = '92'
caps['build'] = my_test_build
caps['name'] = my_test_name
driver = webdriver.Remote(cloud_url, desired_capabilities=caps)
{{< /tab >}}
{{< /tabpane >}}

#### After
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
FirefoxOptions browserOptions = new FirefoxOptions();
browserOptions.setPlatformName("Windows 10");
browserOptions.setBrowserVersion("92");
Map<String, Object> cloudOptions = new HashMap<>();
cloudOptions.put("build", myTestBuild);
cloudOptions.put("name", myTestName);
browserOptions.setCapability("cloud:options", cloudOptions);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), browserOptions);
{{< /tab >}}
{{< tab header="JavaScript" >}}
capabilities = {
  browserName: 'firefox',
  browserVersion: '92',
  platformName: 'Windows 10',
  'cloud:options': {
     build: myTestBuild,
     name: myTestName,
  }
}
{{< /tab >}}
{{< tab header="CSharp" >}}
var browserOptions = new FirefoxOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "92";
var cloudOptions = new Dictionary<string, object>();
cloudOptions.Add("build", myTestBuild);
cloudOptions.Add("name", myTestName);
browserOptions.AddAdditionalOption("cloud:options", cloudOptions);
var driver = new RemoteWebDriver(new Uri(CloudURL), browserOptions);
{{< /tab >}}
{{< tab header="Ruby" >}}
options = Selenium::WebDriver::Options.firefox
options.browser_version = 'latest'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options[:build] = my_test_build
cloud_options[:name] = my_test_name
options.add_option('cloud:options', cloud_options)
driver = Selenium::WebDriver.for :remote, url: cloud_url, capabilities: options
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options as FirefoxOptions
options = FirefoxOptions()
options.browser_version = '92'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options['build'] = my_test_build
cloud_options['name'] = my_test_name
options.set_capability('cloud:options', cloud_options)
driver = webdriver.Remote(cloud_url, options=options)
{{< /tab >}}
{{< /tabpane >}}

### Javaで要素ユーティリティメソッドを検索する
Javaバインディング（`FindsBy` インターフェイス）の要素を検索するユーティリティメソッドは、内部使用のみを目的としていたため、削除されました。 
次のコードサンプルは、これを分かりやすく説明しています。

`findElement *` で単一の要素を検索する。

{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.findElementByClassName("className");
driver.findElementByCssSelector(".className");
driver.findElementById("elementId");
driver.findElementByLinkText("linkText");
driver.findElementByName("elementName");
driver.findElementByPartialLinkText("partialText");
driver.findElementByTagName("elementTagName");
driver.findElementByXPath("xPath");
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.findElement(By.className("className"));
driver.findElement(By.cssSelector(".className"));
driver.findElement(By.id("elementId"));
driver.findElement(By.linkText("linkText"));
driver.findElement(By.name("elementName"));
driver.findElement(By.partialLinkText("partialText"));
driver.findElement(By.tagName("elementTagName"));
driver.findElement(By.xpath("xPath"));
```
{{< /card >}}
{{< /cardpane >}}


`findElements *` で複数の要素を検索する。

{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.findElementsByClassName("className");
driver.findElementsByCssSelector(".className");
driver.findElementsById("elementId");
driver.findElementsByLinkText("linkText");
driver.findElementsByName("elementName");
driver.findElementsByPartialLinkText("partialText");
driver.findElementsByTagName("elementTagName");
driver.findElementsByXPath("xPath");
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.findElements(By.className("className"));
driver.findElements(By.cssSelector(".className"));
driver.findElements(By.id("elementId"));
driver.findElements(By.linkText("linkText"));
driver.findElements(By.name("elementName"));
driver.findElements(By.partialLinkText("partialText"));
driver.findElements(By.tagName("elementTagName"));
driver.findElements(By.xpath("xPath"));
```
{{< /card >}}
{{< /cardpane >}}


## 依存関係のアップグレード

以下のサブセクションを確認してSelenium4をインストールし、プロジェクトの依存関係をアップグレードしてください。

### Java

Seleniumをアップグレードするプロセスは、使用されているビルドツールによって異なります。 
Javaで最も一般的なものである[Maven](https://maven.apache.org/)と[Gradle](https://gradle.org/)について説明します。 
必要なJavaの最小バージョンはまだ8です。

#### Maven

{{< cardpane >}}
{{< card header="Before" >}}
```xml
<dependencies>
  <!-- more dependencies ... -->
  <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.141.59</version>
  </dependency>
  <!-- more dependencies ... -->
</dependencies>
```
{{< /card >}}
{{< card header="After" >}}
```xml
<dependencies>
    <!-- more dependencies ... -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.4.0</version>
    </dependency>
    <!-- more dependencies ... -->
</dependencies>
```
{{< /card >}}
{{< /cardpane >}}

変更を加えた後、`pom.xml` ファイルと同じディレクトリで `mvn clean compile` を実行できます。

#### Gradle

{{< cardpane >}}
{{< card header="Before" >}}
```jsonpath
plugins {
    id 'java'
}
group 'org.example'
version '1.0-SNAPSHOT'
repositories {
    mavenCentral()
}
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '3.141.59'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< card header="After" >}}
```jsonpath
plugins {
    id 'java'
}
group 'org.example'
version '1.0-SNAPSHOT'
repositories {
    mavenCentral()
}
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '4.4.0'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< /cardpane >}}

変更を加えた後、 `build.gradle` ファイルと同じディレクトリで `./gradlew cleanbuild` を実行できます。

すべてのJavaリリースを確認するには、 [MVNRepository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) にアクセスしてください。

### C#

C#でSelenium4の更新を取得する場所は [NuGet](https://www.nuget.org/) です。 
[`Selenium.WebDriver`](https://www.nuget.org/packages/Selenium.WebDriver/4.4.0) パッケージの下で、最新バージョンに更新するための手順を入手できます。 
Visual Studio内では、NuGetパッケージマネージャーを使用して次の操作を実行できます。

```shell
PM> Install-Package Selenium.WebDriver -Version 4.4.0
```

### Python

Pythonを使用するための最も重要な変更は、最低限必要なバージョンです。 
Selenium 4には、Python3.7以降が必要です。 
詳細については、[Python Package Index](https://pypi.org/project/selenium/4.4.3/)を参照してください。 
コマンドラインからアップグレードするには、次のコマンドを実行できます。

```shell
pip install selenium==4.4.3
```

### Ruby

Selenium 4の更新の詳細は、RubyGemsの[selenium-webdriver](https://rubygems.org/gems/selenium-webdriver/versions/4.4.0)で確認できます。 
最新バージョンをインストールするには、次のコマンドを実行できます。

```shell
gem install selenium-webdriver
```

Gemfileには下記のように追加します。

```shell
gem 'selenium-webdriver', '~> 4.4.0'
```

### JavaScript

selenium-webdriverパッケージは、Nodeパッケージマネージャーの[npmjs](https://www.npmjs.com)にあります。 
Selenium4は[here](https://www.npmjs.com/package/selenium-webdriver/v/4.4.0)にあります。 
これをインストールするには、次のいずれかを実行します。

```shell
npm install selenium-webdriver
```

または、package.jsonを更新して、 `npm install` を実行します。

```json
{
  "name": "selenium-tests",
  "version": "1.0.0",
  "dependencies": {
    "selenium-webdriver": "^4.4.0"
  }
}
```

## 潜在的なエラーと非推奨メッセージ

これは、Selenium4にアップグレードした後に発生する可能性のある非推奨メッセージを克服するのに役立つ一連のコード例です。

### Java

#### 待機とタイムアウト

タイムアウトで受信するパラメーターは、期待値 `(long time, TimeUnit unit)` から期待値 `(Duration duration)` に替わりました。

{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().timeouts().setScriptTimeout(2, TimeUnit.MINUTES);
driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
```
{{< /card >}}
{{< /cardpane >}}

現在、待機も異なるパラメーターを期待しています。 
`WebDriverWait`は、秒とミリ秒単位のタイムアウトに、 `long` ではなく`Duration`を期待するようになりました。 
`FluentWait` の `withTimeout` および `pollingEvery` ユーティリティメソッドは、期待値 `(long time, TimeUnit unit)` から `(Duration duration)` に替わりました。

{{< cardpane >}}
{{< card header="Before" >}}
```java
new WebDriverWait(driver, 3)
.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));

Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(30, TimeUnit.SECONDS)
  .pollingEvery(5, TimeUnit.SECONDS)
  .ignoring(NoSuchElementException.class);
```
{{< /card >}}
{{< card header="After" >}}
```java
new WebDriverWait(driver, Duration.ofSeconds(3))
  .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));

  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);
```
{{< /card >}}
{{< /cardpane >}}

#### マージCapabilitiesは、もはや呼び出し元のオブジェクトを変更しなくなりました

以前は、別のCapabilitiesセットを別のセットにマージすることが可能であり、呼び出し元のオブジェクトを変更していました。 
今は、ここで、マージ操作の結果を割り当てる必要があります。

{{< cardpane >}}
{{< card header="Before" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options.merge(capabilities);
```
As a result, the `options` object was getting modified.
{{< /card >}}
{{< card header="After" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options = options.merge(capabilities);
```
The result of the `merge` call needs to be assigned to an object.
{{< /card >}}
{{< /cardpane >}}

#### 古いFirefox

GeckoDriverが登場する前は、SeleniumプロジェクトにはFirefoxを自動化するためのドライバー実装がありました（バージョン<48）。 
ただし、この実装は最近のバージョンのFirefoxでは機能しないため、もう必要ありません。 
Selenium 4にアップグレードする際の大きな問題を回避するために、`setLegacy` オプションは非推奨として表示されます。 
古い実装の使用をやめ、GeckoDriverのみに依存することをお勧めします。 
次のコードは、アップグレード後に非推奨になった`setLegacy` 行を示しています。

```java
FirefoxOptions options = new FirefoxOptions();
options.setLegacy(true);
```

#### `BrowserType`
`BrowserType` インターフェースは長い間使用されてきましたが、新しい `Browser` インターフェースを優先して非推奨になります。

{{< cardpane >}}
{{< card header="Before" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("browserVersion", "92");
capabilities.setCapability("browserName", BrowserType.FIREFOX);
```
{{< /card >}}
{{< card header="After" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("browserVersion", "92");
capabilities.setCapability("browserName", Browser.FIREFOX);
```
{{< /card >}}
{{< /cardpane >}}

### C#

#### `AddAdditionalCapability` は非推奨になりました

その代わりに、 `AddAdditionalOption` をお勧めします。 これを示す例を次に示します。

{{< cardpane >}}
{{< card header="Before" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalCapability("cloud:options", cloudOptions, true);
```
{{< /card >}}
{{< card header="After" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalOption("cloud:options", cloudOptions);
```
{{< /card >}}
{{< /cardpane >}}

### Python

#### `execute_pathは非推奨になりました。Serviceオブジェクトを渡してください`

Selenium 4では、非推奨の警告を防ぐために、Serviceオブジェクトからドライバーの ``executable_path`` を設定する必要があります。 
（または、PATHを設定せず、代わりに必要なドライバーがシステムPATH上にあることを確認してください。）

{{< cardpane >}}
{{< card header="Before" >}}
```python
from selenium import webdriver
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-automation"])
options.add_experimental_option("useAutomationExtension", False)
driver = webdriver.Chrome(executable_path=CHROMEDRIVER_PATH, options=options)
```
{{< /card >}}
{{< card header="After" >}}
```python
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-automation"])
options.add_experimental_option("useAutomationExtension", False)
service = ChromeService(executable_path=CHROMEDRIVER_PATH)
driver = webdriver.Chrome(service=service, options=options)
```
{{< /card >}}
{{< /cardpane >}}

## まとめ

Selenium 4にアップグレードする際に考慮すべき主な変更点を確認しました。
アップグレードのためにテストコードを準備する際にカバーするさまざまな側面について説明します。
これには、新しいバージョンのSeleniumを使用する時に発生する可能性のある潜在的な問題を防ぐ方法の提案も含まれます。
最後に、アップグレード後に発生する可能性のある一連の問題についても説明し、それらの問題に対する潜在的な修正を共有しました。

*これは元々は https://saucelabs.com/resources/articles/how-to-upgrade-to-selenium-4 に投稿されました*
