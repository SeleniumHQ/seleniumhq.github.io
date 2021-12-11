---
title: "安装 Selenium 库"
linkTitle: "安装 Selenium 库"
weight: 1
needsTranslation: true
description: >
  为您喜爱的编程语言设置Selenium库.
aliases: [
"/documentation/zh-cn/selenium_installation/installing_selenium_libraries/",
"/zh-cn/documentation/getting_started/installing_selenium_libraries/"
]
---

首先，您需要为自动化项目安装 Selenium 绑定库。
库的安装过程取决于您选择使用的语言。

{{< tabpane-md  paneID="2" tabCount="6" headerTab1="Java" headerTab2="Python" headerTab3="CSharp" headerTab4="Ruby" headerTab5="JavaScript" headerTab6="Kotlin" >}}
{{< tab-md ID="1" >}}
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

{{< /tab-md >}}
{{< tab-md ID="2" >}}
可以使用 pip 安装 Python 的 Selenium 库：

```shell
pip install selenium
```

或者，您也可以下载 [PyPI source archive](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) 并使用 _setup.py_ 进行安装：

```shell
python setup.py install
```

{{< /tab-md >}}
{{< tab-md ID="3" >}}
可以使用 NuGet 安装 C# 的 Selenium 库：

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

{{< /tab-md >}}
{{< tab-md ID="4" >}}
可以使用 gem 安装 Ruby 的 Selenium 库：

```shell
gem install selenium-webdriver
```

{{< /tab-md >}}
{{< tab-md ID="5" >}}
可以使用 npm 安装 JavaScript 的 Selenium 库

```shell
npm install selenium-webdriver
```

{{< /tab-md >}}
{{< tab-md ID="6" >}}
由于缺少Kotlin的原生语言的绑定, 您不得不借助Java的生态环境, 例如Maven [Java](#java)

{{< /tabpane-md >}}
