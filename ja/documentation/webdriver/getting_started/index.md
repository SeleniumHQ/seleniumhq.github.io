# 入門

> Seleniumを初めて使用する場合は、すぐに習得するのに役立つリソースがいくつかあります。

URL: https://www.selenium.dev/ja/documentation/webdriver/getting_started/

---

Seleniumは市場で主要なブラウザの全てを _WebDriver_ を使うことでサポートしています。
WebDriverとはAPI群とプロトコルです。これらはウェブブラウザの動作をコントロールするための言語中立なインターフェイスを定義しています。
それぞれのブラウザは特定のWebDriverの実装を持っており、これらは *driver* と呼ばれます。
driverはブラウザに委譲する責務を持つコンポーネントであり、Seleniumとブラウザ間の通信を処理します。

この分離は、ブラウザベンダーに自分たちのブラウザでの実装の責任を持たせるための意図的な努力のひとつです。
Seleniumは可能な場合これらのサードパーティ製のdriverを使いますが、それが現実的でない場合のためにプロジェクトでメンテナンスしているdriverも提供しています。

Seleniumフレームワークはこれら全ての要素をユーザ向けのインターフェイスを通して結びつけます。このインターフェイスは異なるブラウザバックエンドを透過的に使えるようにし、クロスブラウザ・クロスプラットフォームの自動化を可能にします。

Seleniumのセットアップは、他の商用ツールのセットアップとはかなり異なります。
Seleniumコードの記述を開始する前に、次のことを行う必要があります
選択した言語、つまりブラウザーの言語バインディングライブラリをインストールします
使用したい、そのブラウザのドライバ。

***以下のリンクをたどって、Selenium WebDriverを起動してください。***

ローコード/録音および再生ツールから始めたい場合は、確認してください
[Selenium IDE](https://selenium.dev/selenium-ide)

物事がうまくいったら、テストをスケールアップしたい場合は、[Selenium Grid](https://www.selenium.dev/ja/documentation/grid/index.md).

---

Section pages:

- [Seleniumライブラリのインストール](https://www.selenium.dev/ja/documentation/webdriver/getting_started/install_library/index.md): お気に入りのプログラミング言語用にSeleniumライブラリを設定します。
- [最初のSeleniumスクリプトを書く](https://www.selenium.dev/ja/documentation/webdriver/getting_started/first_script/index.md): Seleniumスクリプトを作成するための段階的な説明
- [Seleniumコードの整理と実行](https://www.selenium.dev/ja/documentation/webdriver/getting_started/using_selenium/index.md): IDEとテストランナーライブラリを使用したSelenium実行のスケーリング
