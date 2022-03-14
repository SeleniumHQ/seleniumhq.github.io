---
title: "安装浏览器驱动"
linkTitle: "安装类库"
weight: 2
needsTranslation: true
description: >
  配置自动化的浏览器.
aliases: [
"/documentation/zh-cn/selenium_installation/installing_selenium_libraries/",
"/zh-cn/documentation/getting_started/installing_selenium_libraries/",
"/zh-cn/documentation/getting_started/install_selenium_library/"
]
---

首先，您需要为自动化项目安装 Selenium 绑定库。
库的安装过程取决于您选择使用的语言。

{{< tabpane disableCodeBlock=true >}}
  {{< tab header="Java" >}}
可以使用 Maven 安装 Java 的 Selenium 库。
在项目 pom.xml 中添加 _selenium-java_ 依赖项：

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.X</version>
</dependency>
```

_selenium-java_ 依赖项支持在所有 Selenium 支持的浏览器中运行自动化项目。
如果只想在特定的浏览器中运行测试，可以在 _pom.xml_ 文件中添加该浏览器的依赖项。
例如，您应该在 _pom.xml_ 文件中添加以下依赖项，以便于只在 Firefox 中运行测试：

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>4.X</version>
</dependency>
```

同样，如果您只想在 Chrome 上运行测试，您应该添加以下依赖项：

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>4.X</version>
</dependency>
```

  {{< /tab >}}
  {{< tab header="Python" >}}
  可以使用 pip 安装 Python 的 Selenium 库：

```shell
pip install selenium
```

或者，您也可以下载 [PyPI source archive](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) 并使用 _setup.py_ 进行安装：

```shell
python setup.py install
```
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  可以使用 NuGet 安装 C# 的 Selenium 库：

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
You can download [MSBuild Tools 2019 from here](https://docs.microsoft.com/en-us/visualstudio/install/create-an-offline-installation-of-visual-studio?view=vs-2019) 
to install the needed components and dependencies such as .NET SDK and NuGet Package Manager.

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

  {{< /tab >}}
  {{< tab header="Ruby" >}}
  可以使用 gem 安装 Ruby 的 Selenium 库：

```shell
gem install selenium-webdriver
```

  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  可以使用 npm 安装 JavaScript 的 Selenium 库

```shell
npm install selenium-webdriver
```
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  由于缺少Kotlin的原生语言的绑定, 您不得不借助Java的生态环境, 例如Maven [Java](#java)
  {{< /tab >}}
{{< /tabpane >}}

## Next Step
[Install the browser drivers]({{< ref "install_drivers.md" >}})
