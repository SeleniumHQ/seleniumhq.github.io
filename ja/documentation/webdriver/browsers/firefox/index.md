# Firefox特有の機能

> これらは、Mozilla Firefoxブラウザに特有の機能と機能です。

URL: https://www.selenium.dev/ja/documentation/webdriver/browsers/firefox/

---

Selenium 4 には Firefox 78 以降が必要です。 
常に最新バージョンの geckodriver を使用することをお勧めします。

## オプション

全ブラウザに共通のCapabilityについては、[オプションページ](https://www.selenium.dev/ja/documentation/webdriver/drivers/options/index.md)で説明しています。

Firefox に固有のCapabilityは、Mozilla のページの [firefoxOptions](https://developer.mozilla.org/en-US/docs/Web/WebDriver/Capabilities/firefoxOptions) にあります。

基本的な定義済みのオプションを使用して Firefox セッションを開始すると、以下のようになります。

**Java**

```java
  driver = new FirefoxDriver(options);
}
```

**Python**

```python
options = webdriver.FirefoxOptions()
driver = webdriver.Firefox(options=options)
```

**CSharp**

```csharp
public void BasicOptions()
{
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
@driver = Selenium::WebDriver.for :firefox, options: options
```

**JavaScript**

```javascript
driver = new Builder()
  .forBrowser(Browser.FIREFOX)
  .setFirefoxOptions(options)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

さまざまなCapabilityを備えた一般的な使用例をいくつか示します。

### 引数

`args` パラメータは、ブラウザの起動時に使用するコマンドラインスイッチのリストです。
一般的に使用される引数には、 `-headless` と `"-profile"` 、`"/path/to/profile"` が含まれます。

オプションに引数を追加します。

**Java**

```java

```

**Python**

```python
options.add_argument("-headless")
```

**CSharp**

```csharp
var options = new FirefoxOptions();
```

**Ruby**

```ruby
options.args << '-headless'
```

**JavaScript**

```javascript
.setFirefoxOptions(options.addArguments('--headless'))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 指定したロケーションでブラウザを起動する

`binary` パラメーターは、使用するブラウザーの別のロケーションのパスを取ります。
たとえば、このパラメーターを使用すると、geckodriver を使用して、製品版とFirefox Nightlyの両方がコンピューターに存在する場合、
製品版の代わりに Firefox Nightly を駆動できます 。

オプションにブラウザーのロケーションを追加します。

**Java**

```java

```

**Python**

```python
options.binary_location = firefox_bin
```

**CSharp**

```csharp
var options = new FirefoxOptions();
```

**Ruby**

```ruby
options.binary = firefox_location
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### プロファイル

Firefoxプロファイルを操作するにはいくつかの方法があります。

<div>

**Java**

```java
    profile.setPreference("javascript.enabled", "False");
    options.setProfile(profile);

    driver = new FirefoxDriver(options);
  }
}
```

**Python**

```python
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile

options = Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile

driver = webdriver.Firefox(options=options)
```

**CSharp**

```csharp
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
  profile = Selenium::WebDriver::Firefox::Profile.new
  profile['browser.download.dir'] = '/tmp/webdriver-downloads'
  options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
  ```

**JavaScript**

```javascript
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
let profile = '/path to custom profile';
options.setProfile(profile);
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();

```

**Kotlin**

```kotlin
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)

```

</div>

**Note**: Whether you create an empty `FirefoxProfile` or point it to the directory of your own profile, Selenium
will create a temporary directory to store either the data of the new profile or a copy of your existing one. Every
time you run your program, a different temporary directory will be created. These directories are not cleaned up
explicitly by Selenium, they should eventually get removed by the operating system. However, if you want to remove
the copy manually (e.g. if your profile is large in size), the path of the copy is exposed by the `FirefoxProfile`
object. Check the language specific implementation to see how to retrieve that location.

If you want to use an existing Firefox profile, you can pass in the path to that profile. Please refer to the official
[Firefox documentation](https://support.mozilla.org/en-US/kb/profiles-where-firefox-stores-user-data#w_how-do-i-find-my-profile)
for instructions on how to find the directory of your profile.

## サービス

すべてのブラウザに共通するサービス設定は、[Service page](https://www.selenium.dev/ja/documentation/webdriver/drivers/service/index.md)に記載されています。

### ログ出力

ドライバーログを取得することは、さまざまな問題のデバッグに役立ちます。サービスクラスを使用すると、ログの保存先を指定できます。ログ出力は、ユーザーがどこかに指定しない限り無視されます。

#### ファイル出力

特定のファイルにログ出力を保存するには:

**Java**

```java
new GeckoDriverService.Builder().withLogFile(logLocation).build();
```

**注**: Java では、システムプロパティによってファイル出力を設定することもできます。<br>
プロパティキー:`GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`<br>
プロパティ値: ログファイルへのパスを表す文字列

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.FirefoxService(log_output=log_path, service_args=['--log', 'debug'])
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = file_name
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### コンソール出力

ログ出力をコンソールに表示するには、以下のようにします:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
new GeckoDriverService.Builder().withLogOutput(System.out).build();
```

**注意**: Javaは、システムプロパティを使用してコンソール出力を設定することもできます;<br>
プロパティキー: `GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`<br>
プロパティ値: `DriverService.LOG_STDOUT` または `DriverService.LOG_STDERR`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.FirefoxService(log_output=subprocess.STDOUT)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = $stdout
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ログレベル
利用可能なログレベルは7つあります: `fatal`, `error`, `warn`, `info`, `config`, `debug`, `trace`。
ロギングが指定されている場合、デフォルトのレベルは `info`になります。

`-v` iは `-log debug` と同等であり、`-vv` は `log trace`と同等です。
したがって、この例は一般的にログレベルを設定するためのものです:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
new GeckoDriverService.Builder().withLogLevel(FirefoxDriverLogLevel.DEBUG).build();
```

**注意**: Javaは、システムプロパティによってログレベルの設定も可能です:<br>
プロパティキー: `GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY`<br>
プロパティ値:`FirefoxDriverLogLevel`列挙型の文字列表現

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.FirefoxService(log_output=log_path, service_args=['--log', 'debug'])
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.args += %w[--log debug]
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### トランケートログ

ドライバーは、大きなバイナリの文字列表現を含む、送信されたすべてのものをログに記録します。そのため、Firefoxではデフォルトで行が切り捨てられます。切り捨てを無効にするには:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
new GeckoDriverService.Builder().withTruncatedLogs(false).build();
```

**注意**: Javaでは、システムプロパティによってログレベルを設定することもできます。<br>
プロパティキー: `GeckoDriverService.GECKO_DRIVER_LOG_NO_TRUNCATE`<br>
プロパティ値: `"true"` または `"false"`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.FirefoxService(service_args=['--log-no-truncate', '--log', 'debug'], log_output=log_path)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.args << '--log-no-truncate'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### プロファイルルート

プロファイルのデフォルトディレクトリは、システムの一時ディレクトリです。そのディレクトリにアクセスできない場合や、特定の場所にプロファイルを作成したい場合は、プロファイルルートディレクトリを変更できます:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
new GeckoDriverService.Builder().withProfileRoot(profileDirectory).build();
```

**注意**: Javaでは、システムプロパティを使用してログレベルを設定することもできます：  <br>
プロパティキー: `GeckoDriverService.GECKO_DRIVER_PROFILE_ROOT`<br>
プロパティ値: プロファイルルートディレクトリへのパスを表す文字列

**Python**

```python
service = webdriver.FirefoxService(service_args=['--profile-root', temp_dir])
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.args += ['--profile-root', root_directory]
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 特別な機能

### アドオン

Chromeとは異なり、Firefoxの拡張機能はCapabilityの一部として追加されるのではなく、ドライバーの起動後に作成されます。

Chromeとは異なり、Firefoxの拡張機能は[この問題](https://github.com/mozilla/geckodriver/issues/1476)に記載されているように、機能の一部として追加されるのではなく、ドライバーの起動後に作成されます。

T以下の例はローカルWebDriver用です。リモートWebDriverについては、[Remote WebDriver](https://www.selenium.dev/ja/documentation/webdriver/drivers/remote_webdriver/index.md)ページを参照してください。

#### インストール

[Mozilla Add-Onsページ](https://addons.mozilla.org/ja/firefox/) から取得する署名付きxpiファイル

**Java**

```java

```

**Python**

```python
driver.install_addon(addon_path_xpi)
```

**CSharp**

```csharp
string extensionFilePath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example.xpi");
```

**Ruby**

```ruby
driver.install_addon(extension_file_path)
```

**JavaScript**

```javascript
let id = await driver.installAddon(xpiPath);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### アンインストール

アドオンをアンインストールするには、そのIDを知る必要があります。
IDはアドオンインストール時の戻り値から取得できます。

**Java**

```java

```

**Python**

```python
driver.uninstall_addon(id)
```

**CSharp**

_Requires [Selenium v4.5](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0) or later._

```csharp
string extensionId = driver.InstallAddOnFromFile(Path.GetFullPath(extensionFilePath));
```

**Ruby**

```ruby
driver.uninstall_addon(extension_id)
```

**JavaScript**

```javascript
await driver.uninstallAddon(id);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### 署名なしのインストール

未完成または未公開の拡張機能を使用する場合、署名されていない可能性があります。
そのため、"一時的なもの" としてのみインストールできます。
これは、zipファイルまたはディレクトリを渡すことで実行できます。ディレクトリの例を次に示します。

**Java**

```java

```

**Python**

```python
driver.install_addon(addon_path_dir, temporary=True)
```

**CSharp**

_Requires [Selenium v4.5](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0) or later._

```csharp
string extensionDirPath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example/");
```

**Ruby**

_Requires [Selenium v4.5](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0) or later._

```ruby
driver.install_addon(extension_dir_path, true)
```

**JavaScript**

```javascript
let id = await driver.installAddon(xpiPath, true);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ページ全体のスクリーンショット

以下の例はローカルWebDriver用です。リモートWebDriverについては、[Remote WebDriver](https://www.selenium.dev/ja/documentation/webdriver/drivers/remote_webdriver/index.md)ページを参照してください。

**Java**

```java

```

**Python**

```python
driver.save_full_page_screenshot("full_page_screenshot.png")
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
screenshot = driver.save_full_page_screenshot(File.join(dir, 'screenshot.png'))
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### コンテキスト

以下の例はローカルWebDriver用です。リモートWebDriverについては、[Remote WebDriver](https://www.selenium.dev/ja/documentation/webdriver/drivers/remote_webdriver/index.md)ページを参照してください。

**Java**

```java
driver.setContext(FirefoxCommandContext.CHROME);
driver.executeScript("console.log('Inside Chrome context');");
```

**Python**

```python
with driver.context(driver.CONTEXT_CHROME):
    driver.execute_script("console.log('Inside Chrome context');")
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
driver.context = 'content'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Note**: As of Firefox 138, geckodriver needs to be started with the argument `--allow-system-access` to switch the context to `CHROME`.
