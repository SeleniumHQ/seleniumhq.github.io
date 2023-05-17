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

## Requirements by language

{{< tabpane text=true langEqualsHeader=true >}}
  {{% tab header="Java" %}}
View the minimum supported Java version [here](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L13).

Installation of Selenium libraries for Java is accomplished using a build tool.

### Maven
Specify the dependency in the project's `pom.xml` file:

{{< gh-codeblock path="examples/java/pom.xml#L32-L36" >}}

### Gradle
Specify the dependency in the project `build.gradle` file as `testImplementation`:

{{< gh-codeblock path="examples/java/build.gradle#L13" >}}

  {{% /tab %}}
  {{% tab header="Python" %}}
The minimum supported Python version for each Selenium version can be found
in `Supported Python Versions` on [PyPi](https://pypi.org/project/selenium/)

There are a couple different ways to install Selenium.

### Pip

```shell
pip install selenium
```

### Download

Alternatively you can download the [PyPI source archive](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) and install it using _setup.py_:

```shell
python setup.py install
```

### Require in project

To use it in a project, add it to the `requirements.txt` file:
{{< gh-codeblock path="examples/python/requirements.txt#L1" >}}

  {{% /tab %}}
  {{% tab header="CSharp" %}}
A list of all supported frameworks for each version of Selenium 
is available on [Nuget](https://www.nuget.org/packages/Selenium.WebDriver)

There are a few options for installing Selenium.

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

{{< gh-codeblock language="xml" path="examples/dotnet/SeleniumDocs/SeleniumDocs.csproj#L14" >}}

### Additional considerations

Further items of note for using Visual Studio Code (vscode) and C#

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
You can see the minimum required version of Ruby for any given Selenium version 
on [rubygems.org](https://rubygems.org/gems/selenium-webdriver/)

Selenium can be installed two different ways.

### Install manually

```shell
gem install selenium-webdriver
```

### Add to project's gemfile

{{< gh-codeblock language="ruby" path="examples/ruby/Gemfile#L10" >}}

  {{% /tab %}}
  {{% tab header="JavaScript" %}}
You can find the minimum required version of Node for any given version of Selenium in the
`Node Support Policy` section on [npmjs](https://www.npmjs.com/package/selenium-webdriver)

Selenium is typically installed using npm.

### Install locally

```shell
npm install selenium-webdriver
```

### Add to project

In your project's `package.json`, add requirement to `dependencies`:

{{< gh-codeblock path="examples/javascript/package.json#L14" >}}

  {{% /tab %}}
  {{< tab header="Kotlin" >}}
    Use the Java bindings for Kotlin.
  {{< /tab >}}
{{< /tabpane >}}

## Next Step
[Install the browser drivers]({{< ref "install_drivers.md" >}})
