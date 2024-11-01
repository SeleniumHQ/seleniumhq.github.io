---
title: "Seleniumコードの整理と実行"
linkTitle: "Seleniumの使用"
weight: 10
description: >
  IDEとテストランナーライブラリを使用したSelenium実行のスケーリング
---

一握り以上の 1 回限りのスクリプトを実行する場合は、コードを整理して操作できる必要があります。このページでは、Seleniumコードを使用して実際に生産的なことを行う方法についてのアイデアを提供します。

## 一般的な用途

ほとんどの人はSeleniumを使用してWebアプリケーションの自動テストを実行します。
しかし、Seleniumはブラウザ自動化のあらゆるユースケースをサポートします。

### 反復タスク

おそらく、Webサイトにログインして何かをダウンロードするか、フォームを送信する必要があります。
Selenium スクリプトを作成して、あらかじめ設定された時間にサービスと共に実行できます。

### Webスクレイピング

APIがないサイトからデータを収集したいとお考えですか?セレン
これを行うことができますが、Webサイトに精通していることを確認してください。
一部のWebサイトでは許可されておらず、他のWebサイトではSeleniumがブロックされることさえあります。

### テスティング

テストのためにSeleniumを実行するには、Seleniumが実行したアクションに対してアサーションを行う必要があります。
したがって、優れたアサーションライブラリが必要です。テストの構造を提供する追加機能
使用する必要があります [Test Runner](#test-runners).

## IDEs

Seleniumコードの使用方法に関係なく、優れた統合開発環境がなければ、Seleniumコードの作成や実行はあまり効果的ではありません。一般的なオプションを次に示します...

- [Eclipse](https://www.eclipse.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [PyCharm](https://www.jetbrains.com/pycharm/)
- [RubyMine](https://www.jetbrains.com/ruby/)
- [Rider](https://www.jetbrains.com/rider/)
- [WebStorm](https://www.jetbrains.com/webstorm/)
- [VS Code](https://code.visualstudio.com/)

## Test Runner

テストにSeleniumを使用していない場合でも、高度なユースケースがある場合は、テストランナーを使用してコードをより適切に整理するのが理にかなっている場合があります。before/after フックを使用して、グループまたは並行して物事を実行できると非常に便利です。

### 卜

さまざまなテストランナーが利用可能です。

このドキュメントのすべてのコード例は、
テストランナーを使用し、すべてのコードが正しく更新されていることを確認するためにリリースごとに実行されるディレクトリの例。
リンク付きのテストランナーのリストを次に示します。最初の項目は、このリポジトリで使用される項目と
このページのすべての例で使用されます。

{{< tabpane text=true >}}
{{% tab header="Java" %}}

- [JUnit](https://junit.org/junit5/) - JavaベースのSeleniumテストで広く使用されているテストフレームワーク。
- [TestNG](https://testng.org/) - 並列テスト実行やパラメーター化されたテストなどの追加機能を提供します。
  {{% /tab %}}

{{% tab header="Python" %}}

- [pytest](https://pytest.org/) - そのシンプルさと強力なプラグインのおかげで、多くの人に好まれる選択肢です。
- [unittest](https://docs.python.org/3/library/unittest.html) - Python の標準ライブラリテストフレームワーク。
  {{% /tab %}}

{{% tab header="CSharp" %}}

- [NUnit](https://nunit.org/) - .NET の一般的な単体テスト フレームワーク。
- [MS Test](https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing?view=vs-2019) - Microsoft 独自の単体テスト フレームワーク。
  {{% /tab %}}

{{% tab header="Ruby" %}}

- [RSpec](https://rspec.info/) - RubyでSeleniumテストを実行するために最も広く使用されているテストライブラリ。
- [Minitest](https://github.com/seattlerb/minitest) - Ruby標準ライブラリに付属する軽量なテストフレームワークです。
  {{% /tab %}}

{{% tab header="JavaScript" %}}

- [Jest](https://jestjs.io/) - 主にReactのテストフレームワークとして知られていますが、Seleniumのテストにも使用できます。
- [Mocha](https://mochajs.org/) - Seleniumテストを実行するための最も一般的なJSライブラリ。
  {{% /tab %}}

{{% tab header="Kotlin" %}}

{{% /tab %}}
{{< /tabpane >}}

### 装着

これは、で必要とされたものと非常によく似ています [Seleniumライブラリのインストール]({{< ref "install_library.md" >}})。このコードは、私たちのドキュメント例プロジェクトで使用されているものの例を示しているだけです。

{{< tabpane text=true >}}
{{% tab header="Java" %}}

**Maven**

**Gradle**

{{% /tab %}}
{{% tab header="Python" %}}

プロジェクトで使用するには、requirements.txt ファイルに追加します:

{{% /tab %}}
{{% tab header="CSharp" %}}
プロジェクトの 'csproj' ファイルで、依存関係を 'ItemGroup' の 'PackageReference' として指定します:

{{% /tab %}}
{{% tab header="Ruby" %}}

プロジェクトの gemfile に追加

{{% /tab %}}
{{% tab header="JavaScript" %}}
プロジェクトの 'package.json' で、要件を 'dependencies' に追加します。:

{{% /tab %}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

### 主張

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java#L30-L31" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py#L8-L9" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/UsingSeleniumTest.cs#L19-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/using_selenium_spec.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js#L14-L15" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### セットアップとティアダウン

{{< tabpane text=true >}}
{{% tab header="Java" %}}

### 並べる

{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java#L19-L22" >}}

### 取り壊す

{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java#L45-L48" >}}

{{% /tab %}}
{{% tab header="Python" %}}

### 並べる

{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py#L25-L28" >}}

### 取り壊す

{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py#L30-31" >}}

{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Ruby" %}}

### 並べる

{{< gh-codeblock path="examples/ruby/spec/getting_started/using_selenium_spec.rb#L7-L9" >}}

### 取り壊す

{{< gh-codeblock path="examples/ruby/spec/spec_helper.rb#L28" >}}
{{% /tab %}}
{{< tab header="JavaScript" >}}

### 並べる

{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js#L7-L9" >}}

### 取り壊す

{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js#L30" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 実行

{{< tabpane text=true >}}
{{% tab header="Java" %}}

### Maven

```shell
mvn clean test
```

### Gradle

```shell
gradle clean test
```

{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/README.md#L35" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="examples/ruby/README.md#L26" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}

### Mocha

```shell
mocha runningTests.spec.js
```

### npx

```shell
npx mocha runningTests.spec.js
```

{{% /tab %}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 例

[最初のスクリプト]({{< ref "first_script.md" >}})のトピックでは、Seleniumスクリプトの各コンポーネントを見ました。こちらが、テストランナーを使用したそのコードの例です。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/UsingSeleniumTest.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/using_selenium_spec.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## 次のステップ

学んだことを活かして、Seleniumコードを構築します!

必要な機能が他にも見つかったら、残りの機能をお読みください
[WebDriver ドキュメント]({{< ref "/documentation/webdriver/" >}}).
