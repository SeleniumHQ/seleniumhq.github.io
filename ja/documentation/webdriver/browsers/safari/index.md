# Safari特有の機能

> これらは、Apple Safariブラウザ固有の機能です。

URL: https://www.selenium.dev/ja/documentation/webdriver/browsers/safari/

---

Chromium および Firefox のドライバーとは異なり、safaridriver はオペレーティングシステムとともに
インストールされています。
Safari で自動化を有効にするには、ターミナルで次のコマンドを実行します。

```shell
safaridriver --enable
```

## オプション

すべてのブラウザに共通する機能は、[オプションページ](https://www.selenium.dev/ja/documentation/webdriver/drivers/options/index.md)で説明しています。

Safari 固有の機能については、Apple の
[About WebDriver for Safari](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari#2957227)
のページを参照してください。

基本的な定義済みオプションで Safari セッションを開始する場合は、次のようになります。

**Java**

```java
SafariOptions options = new SafariOptions();
driver = new SafariDriver(options);
```

**Python**

```python
options = webdriver.SafariOptions()
driver = webdriver.Safari(options=options)
```

**CSharp**

```csharp
var options = new SafariOptions();
driver = new SafariDriver(options);
```

**Ruby**

```ruby
it 'basic options' do
  options = Selenium::WebDriver::Options.safari
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.SAFARI)
  .setSafariOptions(options)
  .build();
```

**Kotlin**

```kotlin
val options = SafariOptions()
val driver = SafariDriver(options)
```

### モバイル

iOS 上の Safari を自動化する場合は、[Appium プロジェクト](//appium.io/)を参照してください。

<span id="service"></span>
## サービス

すべてのブラウザに共通するサービス設定は、[サービスページ](https://www.selenium.dev/ja/documentation/webdriver/drivers/service/index.md)で
説明しています。

### ログ出力

Safari では、他のブラウザとは異なり、ログの出力先やログレベルを選択できません。利用できるのは、
ログを有効または無効にする設定だけです。ログを有効にした場合は、
`~/Library/Logs/com.apple.WebDriver/` に保存されます。

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
.withLogging(true)
```

**注**: Java では、システムプロパティでコンソール出力も設定できます。<br>
プロパティキー: `SafariDriverService.SAFARI_DRIVER_LOGGING`<br>
プロパティ値: `"true"` または `"false"`

**Python**

_Requires [Selenium v4.26](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.26.0) or later._

```python
service = webdriver.SafariService(enable_logging=True)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby

```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Safari Technology Preview

Apple は、ブラウザの開発版である [Safari Technology Preview](https://developer.apple.com/safari/technology-preview/) を
提供しています。
コードでこのバージョンを使用するには：

**Java**

```java
options.setUseTechnologyPreview(true);
driver = new SafariDriver(options);
```

**Python**

```python
options = webdriver.SafariOptions()
options.use_technology_preview = True
service = webdriver.SafariService(
    executable_path='/Applications/Safari Technology Preview.app/Contents/MacOS/safaridriver'
)
driver = webdriver.Safari(options=options, service=service)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
                                                'have no support for Safari Technology Preview' do
it 'sets the technology preview' do
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._
