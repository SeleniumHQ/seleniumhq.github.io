---
title: "安装Selenium类库"
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

## 请求对应的程序语言

{{< tabpane text=true langEqualsHeader=true >}}
  {{% tab header="Java" %}}
查看该库所支持java的最低版本 [here](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L13).

应熟练掌握build tool以安装支持java的Selenium库

### Maven
具体的依赖位于项目中的 `pom.xml` 文件:

{{< gh-codeblock path="examples/java/pom.xml#L32-L36" >}}

### Gradle
具体的依赖位于项目中的 `build.gradle` 文件中的 `testImplementation`:

{{< gh-codeblock path="examples/java/build.gradle#L13" >}}

  {{% /tab %}}
  {{% tab header="Python" %}}
该库所支持的Python版本最低版本可以在
 `支持的Python版本` 章节中找到 [PyPi](https://pypi.org/project/selenium/)

这里提供了几种不同的方式来安装 Selenium .

### Pip

```shell
pip install selenium
```

### 下载

此外你可以从这里下载 [PyPI source archive](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) 并通过: _setup.py_ 文件安装:

```shell
python setup.py install
```

### 在项目中使用

为了在项目中使用它,需要将它添加到 `requirements.txt` 文件中:
{{< gh-codeblock path="examples/python/requirements.txt#L1" >}}

  {{% /tab %}}
  {{% tab header="CSharp" %}}
Selenium 所支持的所有平台的列表一览
见诸于 [Nuget](https://www.nuget.org/packages/Selenium.WebDriver)

该处阐述了一些安装Selenium的选项.

### 包管理器

```shell
Install-Package Selenium.WebDriver
```

### .NET CLI

```shell
dotnet add package Selenium.WebDriver
```

### CSProj

在 `csproj` 文件里, 具体的依赖 `PackageReference`(包参数) 位于 `ItemGroup` (项目组)中:

{{< gh-codeblock language="xml" path="examples/dotnet/SeleniumDocs/SeleniumDocs.csproj#L14" >}}

### 其他附加思虑事项

更多的注意事项,适用于使用 Visual Studio Code (vscode) 和 C# 

安装兼容的 .NET SDK 作为章节的先决条件
同时安装 vscode 的扩展 (Ctrl-Shift-X)以适配 C# 和 NuGet 
可以遵照此处进行 [操作指南](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0)
创建 C# 控制台项目并运行 "Hello World".
你也可以用命令行 `dotnet new NUnit` 创建NUnit初阶项目.
确保文件 `%appdata%\NuGet\nuget.config` 已经配置完成,就像某位开发者报告的问题一样,它可能因为某种因素被自动清空.
如果 `nuget.config` 是空的,或者未配置的,那么 .NET 创建的Selenium项目可能失败.
加入如下章节到文件 `nuget.config` 如果出现清空的情况:
```
<configuration>
  <packageSources>
    <add key="nuget.org" value="https://api.nuget.org/v3/index.json" protocolVersion="3" />
    <add key="nuget.org" value="https://www.nuget.org/api/v2/" />   
  </packageSources>
...
```
更多关于 `nuget.config` 的信息 [点击](https://docs.microsoft.com/en-us/nuget/reference/nuget-config-file).
你可能需要按照自己的需求配置 `nuget.config` .

现在,返回 vscode ,按下 Ctrl-Shift-P, 然后键入 "NuGet Add Package", 并选择自己需要的 Selenium 包,例如 `Selenium.WebDriver`.
按下回车并选择版本.
现在你可以使用说明文档中关于 C# vscode下的案例了.

  {{% /tab %}}
  {{% tab header="Ruby" %}}
你可以查看 Selenium 对 Ruby 版本支持和最低支持.
具体位于 [rubygems.org](https://rubygems.org/gems/selenium-webdriver/)

Selenium 可以使用两种不同方法安装.

### 手动安装

```shell
gem install selenium-webdriver
```

### 加入项目的 gemfile

{{< gh-codeblock language="ruby" path="examples/ruby/Gemfile#L10" >}}

  {{% /tab %}}
  {{% tab header="JavaScript" %}}
You can find the minimum required version of Node for any given version of Selenium in the
你可以在此查看 Selenium 对 Node 的版本支持情况
位于 `Node Support Policy` 中的相关章节 [npmjs](https://www.npmjs.com/package/selenium-webdriver)

Selenium is typically installed using npm.

### 本地安装

```shell
npm install selenium-webdriver
```

### 加入项目

在你的项目 `package.json`, 必须加入到 `dependencies`:

{{< gh-codeblock path="examples/javascript/package.json#L14" >}}

  {{% /tab %}}
  {{< tab header="Kotlin" >}}
    Use the Java bindings for Kotlin.
  {{< /tab >}}
{{< /tabpane >}}

## 下一步
[安装浏览器驱动]({{< ref "install_drivers.md" >}})
