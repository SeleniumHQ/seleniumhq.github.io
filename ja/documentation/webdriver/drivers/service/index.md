# ドライバーサービスクラス

URL: https://www.selenium.dev/ja/documentation/webdriver/drivers/service/

---

サービスクラスは、ドライバーの起動と停止を管理するためのものです。リモートWebDriverセッションでは使用できません。

サービスクラスを使用すると、ドライバーに関する情報（場所や使用するポートなど）を指定できます。また、コマンドラインに渡される引数を指定することもできます。便利な引数のほとんどは、ログに関連しています。

## デフォルトサービスインスタンス

デフォルトサービスインスタンスを使用してドライバーを起動するには：

**Java**

```java
ChromeDriverService service = new ChromeDriverService.Builder().build();
driver = new ChromeDriver(service);
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService()
driver = webdriver.Chrome(service=service)
```

**CSharp**

```csharp
var service = ChromeDriverService.CreateDefaultService();
driver = new ChromeDriver(service);
```

**Ruby**

```ruby
service = Selenium::WebDriver::Service.chrome
@driver = Selenium::WebDriver.for :chrome, service: service
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## ドライバーの場所

**注意:** Selenium 4.6以上を使用している場合、ドライバーの場所を設定する必要はありません。Seleniumを更新できない場合や、特別な使用ケースがある場合は、ドライバーの場所を指定する方法は次のとおりです：

**Java**

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().usingDriverExecutable(driverPath).build();
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(executable_path=chromedriver_bin)
```

**CSharp**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```csharp
var service = ChromeDriverService.CreateDefaultService(await GetDriverLocationAsync(options));
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.executable_path = driver_path
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## ドライバーのポート

ドライバーを特定のポートで実行したい場合は、次のように指定できます：

**Java**

```java
ChromeDriverService service = new ChromeDriverService.Builder().usingPort(1234).build();
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(port=1234)
```

**CSharp**

```csharp
service.Port = 1234;
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.port = 1234
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

<span id="setting-log-output"></span>
## ログ記録

ログ記録機能はブラウザによって異なります。ほとんどのブラウザでは、ログの場所とレベルを指定できます。各ブラウザのページを確認してください：
* [Chrome](https://www.selenium.dev/ja/documentation/webdriver/browsers/chrome/index.md#service)
* [Edge](https://www.selenium.dev/ja/documentation/webdriver/browsers/edge/index.md#service)
* [Firefox](https://www.selenium.dev/ja/documentation/webdriver/browsers/firefox/index.md#service)
* [Internet Explorer](https://www.selenium.dev/ja/documentation/webdriver/browsers/internet_explorer/index.md#service)
* [Safari](https://www.selenium.dev/ja/documentation/webdriver/browsers/safari/index.md#service)
