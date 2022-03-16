---
title: "Seleniumブラウザー自動化プロジェクト"
linkTitle: "ドキュメント"
cascade:
- type: docs
aliases: ["/documentation/ja/"]
---

Seleniumはブラウザー自動化を可能にし、それを支えるツール群とライブラリー群プロジェクトです。

ユーザーとブラウザーのやり取りのエミュレーション、ブラウザーの割当を増強したり縮減する分散型サーバー、そしてすべてのメジャーなブラウザー用に置換可能なコードの実装を可能にする[W3C WebDriver 仕様](//www.w3.org/TR/webdriver/)インフラの提供します。

このプロジェクトは多くの有志貢献者の何千時間に及ぶ個々の時間を費やした事とソースコード[自由に利用可能]({{< ref "/copyright.md#license" >}})を誰にでも利用、楽しめ、そして改良できることによって実現しました。

Seleniumはウェブプラットフォームの自動化のより開かれた議論をするためブラウザーベンダー、エンジニア、愛好家をまとめます。このプロジェクトはコミュニティーを導きと育成のために[年次カンファレンス](//seleniumconf.com/)開催します。

Seleniumの中核は[WebDriver]({{< ref "/webdriver.md" >}})であり、様々なブラウザーを変えてインストラクション集を実行できるインターフェースです。これは作りえる一番基本的な
インストラクションの一つです:


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" disableCodeBlock=true >}}
    {{< gh-codeblock codeUrl="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/hello/HelloSelenium.java" >}}
{{< /tab >}}
{{< tab header="Python" disableCodeBlock=true >}}
    {{< gh-codeblock codeUrl="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/hello/test_hello_selenium.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

class HelloSelenium {
    static void Main() {
        var driver = new ChromeDriver();

        driver.Navigate().GoToUrl("https://selenium.dev");

        driver.Quit();
    }
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://selenium.dev'

driver.quit
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder, By, Key, until} = require('selenium-webdriver');

(async function helloSelenium() {
    let driver = await new Builder().forBrowser('chrome').build();

    await driver.get('https://selenium.dev');

    await driver.quit();
})();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://selenium.dev")

    driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}



[概要]({{< ref "overview" >}})を参照して、さまざまなプロジェクトコンポーネントを確認し、
Seleniumが適切なツールであるかどうかを判断してください。

[入門]({{< ref "webdriver/getting_started" >}})に進んで、
Seleniumをインストールし、テスト自動化ツールとして正常に使用する方法を理解し、
このような単純なテストをスケーリングして、複数のブラウザー、
複数の異なるオペレーティングシステムの大規模な分散環境で実行する必要があります。



