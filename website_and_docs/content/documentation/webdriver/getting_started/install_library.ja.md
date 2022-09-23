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

{{< tabpane code=false langEqualsHeader=true >}}
  {{% tab header="Java" %}}
JavaへのSeleniumライブラリのインストールはMavenを使います。
プロジェクトのpom.xmlに _selenium-java_ の依存関係を追加してください。

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.4.0</version>
</dependency>
```

For Gradle, add the _selenium-java_ dependency in your project `build.gradle` file:

```text
dependencies {
    compile group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '4.4.0'
```

Gradle 7.0 and above:

```text
dependencies {
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '4.4.0'
```

## Supported Minimum Version

Make sure to use the Java version greater than or equal to the minimum supported version by Selenium. 
Java 8 is currently the minimum supported version by Selenium.
View the updates in the minimum version supported [here](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L13). 

  {{% /tab %}}
  {{% tab header="Python" %}}
  PythonへのSeleniumライブラリのインストールはpipを使います。

```shell
pip install selenium
```

また、[PyPI source archive](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz)をダウンロードして、 _setup.py_ でインストールすることもできます。

```shell
python setup.py install
```
## Supported Minimum Version

Make sure to use the Python version greater than or equal to the minimum supported version by Selenium. 
Python 3.7 is currently the minimum supported version by Selenium.
View the updates in the minimum version supported [here](https://github.com/SeleniumHQ/selenium/blob/trunk/py/setup.py#L41).

  {{% /tab %}}
  {{% tab header="CSharp" %}}
  C#へのSeleniumライブラリのインストールはNuGetを使います。

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```
## Supported .NET Versions
Make sure to use the .NET SDK version compatible with relevant [Selenium package](https://www.nuget.org/packages/Selenium.WebDriver).
Check the dependencies section to find out the [supported .NET version](https://dotnet.microsoft.com/en-us/download/dotnet).
At the time of this update, .NET 5.0 (Visual Studio 2019) is known to be supported, and .NET 6.0 is not supported.
You can download [MSBuild Tools 2019 from here](https://docs.microsoft.com/en-us/visualstudio/install/create-an-offline-installation-of-visual-studio?view=vs-2019) to install the needed components and dependencies such as .NET SDK and NuGet Package Manager.

## Using Visual Studio Code (vscode) and C#
This is a quick guide to help you get started with vscode and C#, however, more research may be required.
Install the compatible .NET SDK as per the section above.
Also install the vscode extensions (Ctrl-Shift-X) for C# and NuGet.
Follow the [instruction here](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0) 
to create and run the "Hello World" console project using C#.
You may also create a NUnit starter project using the command line `dotnet new NUnit`.
Make sure the file `%appdata%\NuGet\nuget.config` is configured properly as some developers reported that it will be empty due to some issues.
If `nuget.config` is empty, or not configured properly, then .NET builds will fail for Selenium Projects.
Add the following section to the file `nuget.config` if it is empty:
```
<configuration>
  <packageSources>
    <add key="nuget.org" value="https://api.nuget.org/v3/index.json" protocolVersion="3" />
    <add key="nuget.org" value="https://www.nuget.org/api/v2/" />   
  </packageSources>
...
```
For more info about `nuget.config` [click here](https://docs.microsoft.com/en-us/nuget/reference/nuget-config-file).
You may have to customize `nuget.config` to meet you needs.

Now, go back to vscode, press Ctrl-Shift-P, and type "NuGet Add Package", and enter the required Selenium packages such as `Selenium.WebDriver`.
Press Enter and select the version.
Now you can use the examples in the documentation related to C# with vscode.
 
  {{% /tab %}}
  {{% tab header="Ruby" %}}
  RubyへのSeleniumライブラリのインストールはgemを使います。

```shell
gem install selenium-webdriver
```
## Supported Minimum Version

Make sure to use the Ruby version greater than or equal to the minimum supported version by Selenium. 
Ruby 2.7 is currently the minimum supported version by Selenium.
View the updates in the minimum version supported [here](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/selenium-webdriver.gemspec#L32).

  {{% /tab %}}
  {{% tab header="JavaScript" %}}
  JavaScriptへのSeleniumライブラリのインストールはnpmを使います。

```shell
npm install selenium-webdriver
```
## Supported Minimum Version

Make sure to use the Node version greater than or equal to the minimum supported version by Selenium. 
Node 14.20.0 is currently the minimum supported version by Selenium.
View the updates in the minimum version supported [here](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/package.json#L23).

  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  Kotlinのネイティブ言語バインディングが欠落しているため、Javaバインディングを使用する必要があります。
  例えば、 maven [Java](#java) を使用します。
  {{< /tab >}}
{{< /tabpane >}}

## Next Step
[Install the browser drivers]({{< ref "install_drivers.md" >}})
