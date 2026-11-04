# ドライバーセッション

URL: https://www.selenium.dev/ja/documentation/webdriver/drivers/

---

セッションの開始と停止は、ブラウザーを開いたり閉じたりするためのものです。

## セッションの作成

新しいセッションの作成は、W3C コマンド [New session](https://w3c.github.io/webdriver/#new-session) に対応しています。

セッションは、新しいDriverクラスオブジェクトを初期化することによって自動的に作成されます。

各言語では、次のいずれかのクラス (または同等のもの) の引数を使用してセッションを作成することができます。

* [オプション](https://www.selenium.dev/ja/documentation/webdriver/drivers/options/index.md) 作成を希望するセッションの種類 ; ローカルにはデフォルト値を使用しますが、リモートには必須です。
* 何らかの形の[HTTP Client configuration](https://www.selenium.dev/ja/documentation/webdriver/drivers/http_client/index.md)  (実装は言語によって異なります)
* [リスナー](https://www.selenium.dev/ja/documentation/webdriver/support_features/listeners/index.md)

### ローカルドライバー

ローカルドライバーを起動するための主な一意の引数には、ローカルコンピューターで必要なドライバーサービスを起動するための情報が含まれます。

* [Service](https://www.selenium.dev/ja/documentation/webdriver/drivers/service/index.md)オブジェクトはローカルドライバーにのみ適用され、ブラウザーのドライバーに関する情報を提供します。

**Java**

```java
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
driver = new ChromeDriver(options);
```

**Ruby**

```ruby
driver = Selenium::WebDriver.for :chrome, options: options
```

**JavaScript**

```javascript
driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options)
  .setChromeService(service)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### リモートドライバー

リモートドライバーを起動するための主な一意の引数には、コードを実行する場所に関する情報を含みます。
詳細は、[リモートドライバー](https://www.selenium.dev/ja/documentation/webdriver/drivers/remote_webdriver/index.md)をご覧ください。

## セッションの終了

セッションの終了に対するW3Cコマンドは、[セッションの削除](https://w3c.github.io/webdriver/#delete-session)です。

重要:  `quit` メソッドは `close` メソッドとは異なり、
セッションを終了するには常に `quit` を使用することをお勧めします。

**Java**

```java
driver.quit();
```

**Python**

```python
driver.quit()
```

**CSharp**

```csharp
driver.Quit();
```

**Ruby**

```ruby
driver.quit
```

**JavaScript**

```javascript
await driver.quit();
```

**Kotlin**

```kotlin
driver.quit()
```

---

Section pages:

- [ブラウザーオプション](https://www.selenium.dev/ja/documentation/webdriver/drivers/options/index.md): これらの機能はすべてのブラウザで共有されています。
- [HTTPクライアントの設定](https://www.selenium.dev/ja/documentation/webdriver/drivers/http_client/index.md)
- [ドライバーサービスクラス](https://www.selenium.dev/ja/documentation/webdriver/drivers/service/index.md)
- [リモートWebDriver](https://www.selenium.dev/ja/documentation/webdriver/drivers/remote_webdriver/index.md)
