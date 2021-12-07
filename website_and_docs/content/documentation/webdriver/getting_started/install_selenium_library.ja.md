---
title: "Seleniumライブラリのインストール"
linkTitle: "Seleniumライブラリのインストール"
weight: 1
needsTranslation: true
description: >
  Setting up the Selenium library for your favourite programming language.
aliases: [
"/documentation/ja/selenium_installation/installing_selenium_libraries/",
"/ja/documentation/getting_started/installing_selenium_libraries/"
]
---

最初にあなたの自動化プロジェクトにSeleniumのバインディングをインストールする必要があります。
インストールの方法は選択した言語によって異なります。

### Java
JavaへのSeleniumライブラリのインストールはMavenを使います。
プロジェクトのpom.xmlに _selenium-java_ の依存関係を追加してください。

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.X</version>
</dependency>
```

_selenium-java_ 依存関係は、Seleniumがサポートする全てのブラウザを自動化プロジェクトで実行できるようにします。
もし特定のブラウザだけでテストを実行したい場合は、そのブラウザの依存関係を _pom.xml_ ファイルに追加することができます。
例えば、Firefoxのみでテストを実行するためには下記の依存関係を _pom.xml_ ファイルに追加します。

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>4.X</version>
</dependency>
```

同じように、Chromeのみでテストを実行するためには下記の依存関係を追加します。

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>3.X</version>
</dependency>
```

### Python
PythonへのSeleniumライブラリのインストールはpipを使います。

```shell
pip install selenium
```

また、[PyPI source archive](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz)をダウンロードして、 _setup.py_ でインストールすることもできます。

```shell
python setup.py install
```

### C#
C#へのSeleniumライブラリのインストールはNuGetを使います。

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

### Ruby
RubyへのSeleniumライブラリのインストールはgemを使います。

```shell
gem install selenium-webdriver
```

## _JavaScript_
JavaScriptへのSeleniumライブラリのインストールはnpmを使います。

```shell
npm install selenium-webdriver
```

### Kotlin
Due to missing native language bindings for Kotlin, you have to use the 
Java Bindings, e.g. with maven [Java](#java)

