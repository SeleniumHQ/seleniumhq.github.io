# Seleniumライブラリのインストール

> お気に入りのプログラミング言語用にSeleniumライブラリを設定します。

URL: https://www.selenium.dev/ja/documentation/webdriver/getting_started/install_library/

---

最初にあなたの自動化プロジェクトにSeleniumのバインディングをインストールする必要があります。
インストールの方法は選択した言語によって異なります。

## 言語別の要件

**Java**

サポートされている最小のJavaバージョンを表示する [ここ](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L32).

Java用のSeleniumライブラリのインストールは、ビルドツールを使用して行います。

<h3 id="maven">Maven</h3>

プロジェクトの ‘pom.xml’ ファイルで依存関係を指定します:

```xml
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>${selenium.version}</version>
</dependency>
```
<h3 id="gradle">Gradle</h3>

プロジェクトの ‘build.gradle’ ファイル内の依存関係を ’testImplementation’ として指定します:

```gradle

dependencies {
```

**Python**

各 Selenium バージョンでサポートされている最小の Python バージョンは、
[PyPI](https://pypi.org/project/selenium/) の「Supported Python Versions」で確認できます。

Seleniumをインストールするには、いくつかの方法があります。

<h3 id="pip">Pip</h3>

```shell
pip install selenium
```
<br>
<h3 id="ダウンロード">ダウンロード</h3>

または、ダウンロードすることもできます[PyPI ソースアーカイブ](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.-py3-none-any.whl) を使用してインストールします _pip_:

```shell
pip install selenium-x.x.x.-py3-none-any.whl
```
<br>
<h3 id="プロジェクトで必要">プロジェクトで必要</h3>

プロジェクトで使用するには、requirements.txt ファイルに追加します:

```text
selenium==4.52.0
```

**CSharp**

Seleniumの各バージョンでサポートされているすべてのフレームワークのリスト
で利用可能です[Nuget](https://www.nuget.org/packages/Selenium.WebDriver)

Seleniumのインストールにはいくつかのオプションがあります。

<h3 id="パケットマネージャー">パケットマネージャー</h3>

```shell
Install-Package Selenium.WebDriver
```
<br>
<h3 id="net-cli">.NET CLI</h3>

```shell
dotnet add package Selenium.WebDriver
```
<br>
<h3 id="csproj">CSProj</h3>

プロジェクトの `csproj`ファイルで、`ItemGroup` の `PackageReference`として依存関係を指定します。:

```xml
<PackageReference Include="Selenium.WebDriver" Version="4.52.0" />
```
<h3 id="その他の考慮事項">その他の考慮事項</h3>

その他、使用上の注意点 Visual Studio Code (vscode) そして C#

上記のセクションに従って、互換性のある .NET SDK をインストールします。
また、C# と NuGet の vscode 拡張機能 (Ctrl-Shift-X) もインストールします。に従ってください[指示はこちら](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0)
C# を使用して “Hello World” コンソール プロジェクトを作成および実行します。
コマンドラインを使用してNUnitスタータープロジェクトを作成することもできます `dotnet new NUnit`.
ファイルを確認してください `%appdata%\NuGet\nuget.config`一部の開発者がいくつかの問題のために空になると報告したため、適切に構成されています。
もし`nuget.config`が空であるか、正しく構成されていない場合、Selenium プロジェクトの .NET ビルドは失敗します。
次のセクションをファイルに追加します`nuget.config` 空の場合:

<pre tabindex="0"><code>&lt;configuration&gt;
  &lt;packageSources&gt;
    &lt;add key=&#34;nuget.org&#34; value=&#34;https://api.nuget.org/v3/index.json&#34; protocolVersion=&#34;3&#34; /&gt;
    &lt;add key=&#34;nuget.org&#34; value=&#34;https://www.nuget.org/api/v2/&#34; /&gt;   
  &lt;/packageSources&gt;
...
</code></pre>
詳細については、`nuget.config` [ここをクリック](https://docs.microsoft.com/en-us/nuget/reference/nuget-config-file).
カスタマイズする必要があるかもしれません `nuget.config` あなたのニーズを満たすために。

さて、戻ってください vscode、プレス Ctrl-Shift-P、およびタイプ “NuGet Add Package&quot;をクリックし、必要な Selenium パッケージ `Selenium.WebDriver`.
Enter キーを押して、バージョンを選択します。
これで、C# と vscode に関連するドキュメントの例を使用できるようになりました。

**Ruby**

特定の Selenium バージョンに必要な最小 Ruby バージョンは、
[rubygems.org](https://rubygems.org/gems/selenium-webdriver/) で確認できます。

Seleniumは2つの異なる方法でインストールできます。

<h3 id="手動でインストールする">手動でインストールする</h3>

```shell
gem install selenium-webdriver
```
<br>
<h3 id="プロジェクトの-gemfile-に追加">プロジェクトの gemfile に追加</h3>

```ruby
gem 'selenium-devtools', '= 0.156.0'
```

**JavaScript**

Selenium の特定のバージョンに必要な最小 Node.js バージョンは、
[npmjs](https://www.npmjs.com/package/selenium-webdriver) の「Node Support Policy」で確認できます。

Seleniumは通常、npmを使用してインストールされます。

<h3 id="ローカルにインストールする">ローカルにインストールする</h3>

```shell
npm install selenium-webdriver
```
<br>
<h3 id="プロジェクトに加える">プロジェクトに加える</h3>

プロジェクトの `package.json`で、要件を `dependencies`:

```json
"mocha": "12.0.3"
```

**Kotlin**

Kotlin の Java バインディングを使用します。

## 次のステップ
[初めてのSeleniumスクリプトを作成する](https://www.selenium.dev/ja/documentation/webdriver/getting_started/first_script/index.md)
