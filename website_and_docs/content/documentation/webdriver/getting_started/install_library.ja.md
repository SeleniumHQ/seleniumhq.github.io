---
title: "Seleniumライブラリのインストール"
linkTitle: "ライブラリのインストール"
weight: 2
description: >
  お気に入りのプログラミング言語用にSeleniumライブラリを設定します。
aliases: [
"/documentation/ja/selenium_installation/installing_selenium_libraries/",
"/ja/documentation/getting_started/installing_selenium_libraries/",
"/ja/documentation/getting_started/install_selenium_library/"
]
---

最初にあなたの自動化プロジェクトにSeleniumのバインディングをインストールする必要があります。
インストールの方法は選択した言語によって異なります。

## 言語別の要件

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
サポートされている最小のJavaバージョンを表示する [ここ](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L13).

Java用のSeleniumライブラリのインストールは、ビルドツールを使用して行います。

### Maven
プロジェクトの 'pom.xml' ファイルで依存関係を指定します:

{{< gh-codeblock path="examples/java/pom.xml#L30-L34" >}}

### Gradle
プロジェクトの 'build.gradle' ファイル内の依存関係を 'testImplementation' として指定します:

{{< gh-codeblock path="examples/java/build.gradle#L13-L14" >}}

  {{% /tab %}}
  {{% tab header="Python" %}}
各 Selenium バージョンでサポートされている最小 Python バージョンについては、次の場所にあります `サポートされている Python バージョン` オン [PyPi](https://pypi.org/project/selenium/)。

Seleniumをインストールするには、いくつかの方法があります。

### Pip

```shell
pip install selenium
```
<br>

### ダウンロード

または、ダウンロードすることもできます[PyPI ソースアーカイブ](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) を使用してインストールします _setup.py_

```shell
python setup.py install
```
<br>

### プロジェクトで必要

プロジェクトで使用するには、requirements.txt ファイルに追加します:
{{< gh-codeblock path="examples/python/requirements.txt#L1" >}}

  {{% /tab %}}
  {{% tab header="CSharp" %}}
Seleniumの各バージョンでサポートされているすべてのフレームワークのリスト
で利用可能です[Nuget](https://www.nuget.org/packages/Selenium.WebDriver)

Seleniumのインストールにはいくつかのオプションがあります。

### パケットマネージャー

```shell
Install-Package Selenium.WebDriver
```
<br>

### .NET CLI

```shell
dotnet add package Selenium.WebDriver
```
<br>

### CSProj

プロジェクトの `csproj`ファイルで、`ItemGroup` の `PackageReference`として依存関係を指定します。:

{{< gh-codeblock language="xml" path="examples/dotnet/SeleniumDocs/SeleniumDocs.csproj#L14" >}}

### その他の考慮事項

その他、使用上の注意点 Visual Studio Code (vscode) そして C#

上記のセクションに従って、互換性のある .NET SDK をインストールします。
また、C# と NuGet の vscode 拡張機能 (Ctrl-Shift-X) もインストールします。に従ってください[指示はこちら](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0)
C# を使用して "Hello World" コンソール プロジェクトを作成および実行します。
コマンドラインを使用してNUnitスタータープロジェクトを作成することもできます `dotnet new NUnit`.
ファイルを確認してください `%appdata%\NuGet\nuget.config`一部の開発者がいくつかの問題のために空になると報告したため、適切に構成されています。
もし`nuget.config`が空であるか、正しく構成されていない場合、Selenium プロジェクトの .NET ビルドは失敗します。
次のセクションをファイルに追加します`nuget.config` 空の場合:
```
<configuration>
  <packageSources>
    <add key="nuget.org" value="https://api.nuget.org/v3/index.json" protocolVersion="3" />
    <add key="nuget.org" value="https://www.nuget.org/api/v2/" />   
  </packageSources>
...
```
詳細については、`nuget.config` [ここをクリック](https://docs.microsoft.com/en-us/nuget/reference/nuget-config-file).
カスタマイズする必要があるかもしれません `nuget.config` あなたのニーズを満たすために。

さて、戻ってください vscode、プレス Ctrl-Shift-P、およびタイプ "NuGet Add Package"をクリックし、必要な Selenium パッケージ `Selenium.WebDriver`.
Enter キーを押して、バージョンを選択します。
これで、C# と vscode に関連するドキュメントの例を使用できるようになりました。

  {{% /tab %}}
  {{% tab header="Ruby" %}}
特定の Selenium バージョンに対して最低限必要な Ruby のバージョンを確認できます
オン [rubygems.org](https://rubygems.org/gems/selenium-webdriver/)

Seleniumは2つの異なる方法でインストールできます。

### 手動でインストールする

```shell
gem install selenium-webdriver
```
<br>

### プロジェクトの gemfile に追加

{{< gh-codeblock language="ruby" path="examples/ruby/Gemfile#L10" >}}

  {{% /tab %}}
  {{% tab header="JavaScript" %}}
Seleniumの特定のバージョンに最低限必要なNodeのバージョンは、`Node Support Policy` 節 オン [npmjs](https://www.npmjs.com/package/selenium-webdriver)

Seleniumは通常、npmを使用してインストールされます。

### ローカルにインストールする

```shell
npm install selenium-webdriver
```
<br>

### プロジェクトに加える

プロジェクトの `package.json`で、要件を `dependencies`:

{{< gh-codeblock path="examples/javascript/package.json#L14" >}}

  {{% /tab %}}
  {{< tab header="Kotlin" >}}
    Kotlin の Java バインディングを使用します。
  {{< /tab >}}
{{< /tabpane >}}

## 次のステップ
[初めてのSeleniumスクリプトを作成する]({{< ref "first_script.md" >}})
