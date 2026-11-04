# Seleniumブラウザー自動化プロジェクト

URL: https://www.selenium.dev/ja/documentation/

---

Seleniumは、ブラウザーの自動化を可能にし、それを支えるさまざまなツールとライブラリーから成るプロジェクトです。

Seleniumは、ユーザーとブラウザーのやり取りをエミュレートするための拡張機能、ブラウザーの割り当てを拡張するための分散サーバー、そしてすべての主要なブラウザーで
同じコードを使えるようにする[W3C WebDriver仕様](//www.w3.org/TR/webdriver/)の実装のためのインフラストラクチャーを提供します。

このプロジェクトは、何千時間もの時間を費やしてきたボランティアの貢献者によって支えられています。
ソースコードは、誰でも利用し、楽しみ、改善できるよう[自由に公開](https://www.selenium.dev/ja/documentation/about/copyright/index.md#license)されています。

Seleniumは、ウェブプラットフォームの自動化について開かれた議論を促進するために、ブラウザーベンダー、エンジニア、愛好家を結び付けています。
このプロジェクトは、コミュニティを育成・支援するために[年次カンファレンス](//seleniumconf.com/)を開催しています。

Seleniumの中核となる[WebDriver](https://www.selenium.dev/ja/documentation/webdriver/index.md)は、多くのブラウザーで同じように実行できる命令セットを記述するためのインターフェースです。
以下に、最も簡単な例の一つを示します。

**Java**

```java
package dev.selenium.hello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://selenium.dev");

        driver.quit();
    }
}
```

**Python**

```python
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://selenium.dev")

driver.quit()
```

**CSharp**

```csharp
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Hello;

public static class HelloSelenium
{
    public static void Main()
    {
        var driver = new ChromeDriver();

        driver.Navigate().GoToUrl("https://selenium.dev");

        driver.Quit();
    }
}
```

**Ruby**

```ruby
# frozen_string_literal: true

require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://selenium.dev'

driver.quit
```

**JavaScript**

```javascript
const {Builder, Browser} = require('selenium-webdriver');

(async function helloSelenium() {
  let driver = await new Builder().forBrowser(Browser.CHROME).build();

  await driver.get('https://selenium.dev');

  await driver.quit();
})();
```

**Kotlin**

```kotlin
package dev.selenium.hello

import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://selenium.dev")

    driver.quit()
}
```

[概要](https://www.selenium.dev/ja/documentation/overview/index.md)を参照して、プロジェクトのさまざまなコンポーネントを確認し、Seleniumが適切なツールであるかどうかを判断してください。

[入門](https://www.selenium.dev/ja/documentation/webdriver/getting_started/index.md)に進み、Seleniumのインストール方法と、テスト自動化ツールとして適切に使用する方法を学びましょう。
また、このような単純なテストをスケールさせ、複数のブラウザーと複数のオペレーティングシステムを使用する大規模な分散環境で実行する方法も確認してください。

---

Section pages:

- [概要](https://www.selenium.dev/ja/documentation/overview/index.md): Seleniumはあなたに適していますか？さまざまなプロジェクトのコンポーネントの概要を参照してください。
- [WebDriver](https://www.selenium.dev/ja/documentation/webdriver/index.md): WebDriverはブラウザをネイティブに操作します。詳細については、こちらをご覧ください。
- [Selenium Manager](https://www.selenium.dev/ja/documentation/selenium_manager/index.md): Selenium Manager is a command-line tool implemented in Rust that provides automated driver and browser management for Selenium. Selenium bindings use this tool by default, so you do not need to download it or add anything to your code or do anything else to use it.
- [Grid](https://www.selenium.dev/ja/documentation/grid/index.md): 複数のマシンで並行してテストを実行したいですか？ Grid が手助けします。
- [Selenium IDE](https://www.selenium.dev/ja/documentation/ide/index.md): Selenium IDEは、ユーザーのアクションを記録および再生するスタンドアロンアプリケーションです。
- [テストの実践](https://www.selenium.dev/ja/documentation/test_practices/index.md): Seleniumプロジェクトからのテストに関するいくつかのガイドラインと推奨事項
- [レガシー](https://www.selenium.dev/ja/documentation/legacy/index.md): このセクションでは、Seleniumのレガシーコンポーネントに関連するすべてのドキュメントを見つけることができます。 これは、非推奨コンポーネントを使用する動機としてではなく、純粋に歴史的な理由で保持されることを意図しています。
- [このドキュメントについて](https://www.selenium.dev/ja/documentation/about/index.md)
