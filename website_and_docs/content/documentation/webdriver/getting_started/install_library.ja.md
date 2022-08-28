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

{{< tabpane disableCodeBlock=true >}}
  {{< tab header="Java" >}}

Installation of Selenium libraries for Java is accomplished using a build tool.
You can see all available versions on
[Maven Repository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)

### JavaへのSeleniumライブラリのインストールはMavenを使います。
プロジェクトのpom.xmlに _selenium-java_ の依存関係を追加してください。

1. specify the version in `properties`:

{{< gh-codeblock path="examples/java/pom.xml#L11-L12" >}}

2. specify the dependency in `dependencies`

{{< gh-codeblock path="examples/java/pom.xml#L30-L35" >}}

### Gradle
in the project `build.gradle` file:

1. specify the version in extra properties (`ext`):

{{< gh-codeblock path="examples/java/build.gradle#L11-L12" >}}

2. specify the dependency as `testImplementation` in `dependencies`

{{< gh-codeblock path="examples/java/build.gradle#L17-L18" >}}

  {{< /tab >}}
  {{< tab header="Python" >}}

### pipを使います。

```shell
pip install selenium
```

### ダウンロード

また、[PyPI source archive](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz)をダウンロードして、 _setup.py_ でインストールすることもできます。

```shell
python setup.py install
```

### Require in project

To use it in a project, add it to the `requirements.txt` file:
{{< gh-codeblock path="examples/python/requirements.txt#L1" >}}

  {{< /tab >}}
  {{< tab header="CSharp" >}}
### Packet Manager

```shell
Install-Package Selenium.WebDriver
```

### .NET CLI

```shell
dotnet add package Selenium.WebDriver
```

### CSProj

in the project's `csproj` file, specify the dependency as a `PackageReference` in `ItemGroup`:

{{< gh-codeblock language="xml" path="examples/dotnet/SeleniumDocs/SeleniumDocs.csproj#L13" >}}

### Additional considerations

Further items of note for using Selenium with .NET:

#### Supported .NET Versions
Make sure to use the .NET SDK version compatible with relevant [Selenium package](https://www.nuget.org/packages/Selenium.WebDriver).
Check the dependencies section to find out the [supported .NET version](https://dotnet.microsoft.com/en-us/download/dotnet).
At the time of this update, .NET 5.0 (Visual Studio 2019) is known to be supported, and .NET 6.0 is not supported.
You can download [MSBuild Tools 2019 from here](https://docs.microsoft.com/en-us/visualstudio/install/create-an-offline-installation-of-visual-studio?view=vs-2019) to install the needed components and dependencies such as .NET SDK and NuGet Package Manager.

#### Using Visual Studio Code (vscode) and C#
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
 
  {{< /tab >}}
  {{< tab header="Ruby" >}}

### Install locally

```shell
gem install selenium-webdriver
```

### Add to project

In your project's `Gemfile`:

{{< gh-codeblock language="ruby" path="examples/ruby/Gemfile#L10" >}}

  {{< /tab >}}
  {{< tab header="JavaScript" >}}

### Install locally

```shell
npm install selenium-webdriver
```

### Add to project

In your project's `package.json`, add requirement to `dependencies`:

{{< gh-codeblock path="examples/javascript/package.json#L9-L10" >}}

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  Kotlinのネイティブ言語バインディングが欠落しているため、Javaバインディングを使用する必要があります。
  例えば、 maven [Java](#java) を使用します。
  {{< /tab >}}
{{< /tabpane >}}

## Next Step
[Install the browser drivers]({{< ref "install_drivers.md" >}})
