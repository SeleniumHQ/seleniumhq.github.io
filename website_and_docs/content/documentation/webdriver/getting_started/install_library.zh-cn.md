---
title: "安装浏览器驱动"
linkTitle: "安装类库"
weight: 2
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
## 支持的 .NET 版本
确保使用与.NET SDK版本兼容的相关[Selenium包](https://www.nuget.org/packages/Selenium.WebDriver).
检查依赖的部分用以找出[支持的 .NET 版本](https://dotnet.microsoft.com/en-us/download/dotnet).
在本次升级时, .NET 5.0 (Visual Studio 2019) 是已知的被支持的版本, 并且 .NET 6.0 并未支持.
您可以下载 [MSBuild Tools 2019 于此](https://docs.microsoft.com/en-us/visualstudio/install/create-an-offline-installation-of-visual-studio?view=vs-2019)
以安装所需的组件和依赖项, 例如 .NET SDK 和 NuGet 包管理器.

## 使用 Visual Studio Code (vscode) 以及 C#
这是一个快速指南, 可帮助您开始使用 vscode 和 C#, 但可能需要进行更多调研.
按照上一节安装兼容的 .NET SDK.
还要安装适用于 C# 和 NuGet 的 vscode 扩展 (Ctrl-Shift-X).
参考 [此处指令](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0) 
创建并且使用C#运行 "Hello World" 的控制台项目.
您也可以使用命令行 `dotnet new NUnit`创建一个 NUnit 初创项目.
确保文件 `%appdata%\NuGet\nuget.config` 已正确配置, 
因为某些开发人员报告说, 
由于某些问题, 该文件将为空.
如果 `nuget.config` 为空,
或者未正确配置, 
则Selenium项目的.NET构建将失败.
将以下部分添加到文件 `nuget.config` (如果为空) :
```
<configuration>
  <packageSources>
    <add key="nuget.org" value="https://api.nuget.org/v3/index.json" protocolVersion="3" />
    <add key="nuget.org" value="https://www.nuget.org/api/v2/" />   
  </packageSources>
...
```
有关 `nuget.config` 的更多信息[点此](https://docs.microsoft.com/en-us/nuget/reference/nuget-config-file).
你也可以自定义 `nuget.config` 以满足所需.

现在, 返回 vscode, 按 Ctrl-Shift-P, 
然后键入"NuGet Add Package", 
然后输入所需的 Selenium 包, 
如"Selenium.WebDriver".

按 Enter 并选择版本.

现在, 您可以通过vscode与文档中 C# 相关示例结合使用.

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

## 下一步
[安装浏览器驱动]({{< ref "install_drivers.md" >}})
