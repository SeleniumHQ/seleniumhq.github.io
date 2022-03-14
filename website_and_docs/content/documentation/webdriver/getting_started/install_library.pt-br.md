---
title: "Instalando bibliotecas do Selenium"
linkTitle: "Instalando bibliotecas do Selenium"
weight: 2
needsTranslation: true
description: >
  Setting up the Selenium library for your favourite programming language.
aliases: [
"/documentation/pt-br/selenium_installation/installing_selenium_libraries/",
"/pt-br/documentation/getting_started/installing_selenium_libraries/",
"/pt-br/documentation/getting_started/install_selenium_library/"
]
---

Primeiro você precisa instalar as ligações Selenium para seu projeto de automação.
O processo de instalação de bibliotecas depende da linguagem que você escolher usar.

{{< tabpane disableCodeBlock=true >}}
  {{< tab header="Java" >}}
A instalação de bibliotecas Selenium para Java pode ser feita usando Maven.
Adicione a dependência selenium-java em seu pom.xml:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.X</version>
</dependency>
```

A dependência _selenium-java_ suporta a execução de sua automação com todos os navegadores
com suporte Selenium. Se você quiser fazer testes
apenas em um navegador específico, você pode adicionar a dependência para esse navegador
em seu arquivo _pom.xml_.
Por exemplo, você deve adicionar a seguinte dependência em seu _pom.xml_
arquivo para executar seus testes apenas no Firefox:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>4.X</version>
</dependency>
```

De maneira semelhante, se você deseja executar testes apenas no Chrome,
você deve adicionar a seguinte dependência:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>4.X</version>
</dependency>
```
  {{< /tab >}}
  {{< tab header="Python" >}}
  A instalação de bibliotecas Selenium para Python pode ser feita usando pip:

```shell
pip install selenium
```

Como alternativa, você pode baixar o [arquivo de origem do PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) e instale-o usando _setup.py_:

```shell
python setup.py install
```
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  A instalação de bibliotecas Selenium para C# pode ser feita usando NuGet:

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
Install the compatible .NET SDK as per the seciton above.
Also install the vscode extensions (Ctrl-Shift-X) for C# and NuGet.
Follow the [instruction here](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0) to create and run the "Hello World" console project using C#.
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

Now, go back to vscode, press Ctrl-Shift-P, and type "NuGet Add Package", and enter the required Selenium packages such as `Selenium.WebDrive`.
Press Entery and select the version.
Now you can use the examples in the documentation related to C# with vscode.

  {{< /tab >}}
  {{< tab header="Ruby" >}}
  A instalação de bibliotecas Selenium para Ruby pode ser feita usando gem:

```shell
gem install selenium-webdriver
```

  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  A instalação de bibliotecas Selenium para JavaScript pode ser feita usando npm:

```shell
npm install selenium-webdriver
```

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  Devido à ausência de vínculos de linguagem nativo para Kotlin, você deve usar vínculos Java, por exemplo, com Maven [Java](#java)
  {{< /tab >}}
{{< /tabpane >}}

## Next Step
[Install the browser drivers]({{< ref "install_drivers.md" >}})
