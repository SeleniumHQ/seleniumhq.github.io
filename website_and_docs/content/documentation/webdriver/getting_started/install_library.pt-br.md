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

Primeiro você precisa instalar as bibliotecas Selenium para seu projeto de automação.
O processo de instalação de bibliotecas depende da linguagem que você escolher usar.

{{< tabpane disableCodeBlock=true >}}
  {{< tab header="Java" >}}
Installation of Selenium libraries for Java is accomplished using a build tool.
You can see all available versions on
[Maven Repository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)

### Maven
in the project's `pom.xml` file:

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
  
### pip

```shell
pip install selenium
```

### você pode baixar

Como alternativa, você pode baixar o [arquivo de origem do PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) e instale-o usando _setup.py_:

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

#### Versões Suportadas .NET
Tenha certeza de utilizar a versão .NET SDK compatível com os [Pacotes Selenium](https://www.nuget.org/packages/Selenium.WebDriver) relevantes.
Veja a seção de dependências em [Versões suportadas .NET](https://dotnet.microsoft.com/en-us/download/dotnet).
Até esta atualização, .NET 5.0 (Visual Studio 2019) é suportada e .NET 6.0 não é suportada.
<br>Você pode fazer o download [MSBuild Tools 2019 aqui](https://docs.microsoft.com/en-us/visualstudio/install/create-an-offline-installation-of-visual-studio?view=vs-2019) e instalar os componentes e dependências necessárias, como .NET SDK e NuGet Package Manager.

#### Usando Visual Studio Code (vscode) e C#
Este é um guia rápido para você iniciar com VSCode e C#, no entanto, mais pesquisas podem ser necessárias.
<br>Instale o .NET SDK compativel como mostrado na seção acima.
Além disso instale as extensões (Ctrl-Shift-X) C# e NuGet no VSCode.
<br>Siga as [instruções aqui](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0) para criar e executar um projeto "Hello World" no console usando C#. Além disso crie um projeto inicial NUnit usando o comando `dotnet new NUnit`.
<br>Certifique-se de que o arquivo `%appdata%\NuGet\nuget.config` está configurado corretamente, pois alguns desenvolvedores reportaram que estará vazio devido alguns problemas.
Se o arquivo `nuget.config` estiver vazio, ou não configurado corretamente, então o build .NET irá falhar para projetos Selenium.
<br>Adicione a seguinte seção ao arquivo `nuget.config` se ele estiver vazio:
```
<configuration>
  <packageSources>
    <add key="nuget.org" value="https://api.nuget.org/v3/index.json" protocolVersion="3" />
    <add key="nuget.org" value="https://www.nuget.org/api/v2/" />   
  </packageSources>
...
```
Para mais informações sobre o arquivo `nuget.config` [clique aqui](https://docs.microsoft.com/en-us/nuget/reference/nuget-config-file).
Você pode ter de personalizar o arquivo `nuget.config` atender suas necessidades.

Agora, volte ao VSCode, pressione Ctrl-Shift-P e digite "NuGet Add Package" e adicione os pacotes requeridos para Selenium, como o pacote `Selenium.WebDriver`. Pressione enter e selecione a versão.
Agora você pode utilizar os exemplos na documentação relacionada para C# com VSCode.

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
  Devido à ausência de vínculos de linguagem nativo para Kotlin, você deve usar vínculos Java, por exemplo, com Maven [Java](#java)
  {{< /tab >}}
{{< /tabpane >}}

## Próximo passo
[Instale os drivers do navegador]({{< ref "install_drivers.md" >}})
