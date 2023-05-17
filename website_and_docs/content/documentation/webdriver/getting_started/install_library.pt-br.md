---
title: "Instalando bibliotecas do Selenium"
linkTitle: "Instalando bibliotecas do Selenium"
weight: 2
needsTranslation: true
description: >
  Configurando a biblioteca Selenium para sua linguagem de programação favorita.
aliases: [
"/documentation/pt-br/selenium_installation/installing_selenium_libraries/",
"/pt-br/documentation/getting_started/installing_selenium_libraries/",
"/pt-br/documentation/getting_started/install_selenium_library/"
]
---

Primeiro você precisa instalar as bibliotecas Selenium para seu projeto de automação.
O processo de instalação de bibliotecas depende da linguagem que você escolher usar.

## Requisitos por linguagem

{{< tabpane text=true langEqualsHeader=true >}}
  {{% tab header="Java" %}}
Veja a mínima versão do Java suportada [aqui](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L13).

A instalação da biblioteca Selenium para Java é feita a partir de uma build tool.


### Maven
Especifique a dependência no `pom.xml` do seu projeto.

{{< gh-codeblock path="examples/java/pom.xml#L32-L36" >}}

### Gradle
Especifique a dependência no `build.gradle` do seu projeto como `testImplementation`:

{{< gh-codeblock path="examples/java/build.gradle#L13" >}}

  {{% /tab %}}
  {{% tab header="Python" %}}
A mínima versão suportada do Python para cada versão do Selenium pode ser encontrada 
em `Supported Python Versions` no [PyPi](https://pypi.org/project/selenium/)

Existe muitas formas diferentes de instalar Selenium.

### Pip

```shell
pip install selenium
```

### Download

Como uma alternativa você pode baixar o [código fonte PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) e instalar usando _setup.py_:

```shell
python setup.py install
```

### Exigir em um projeto

Para usar em um projeto, adicione no arquivo `requirements.txt`.
{{< gh-codeblock path="examples/python/requirements.txt#L1" >}}

  {{% /tab %}}
  {{% tab header="CSharp" %}}
Uma lista com todos os frameworks suportados para cada versão do Selenium
pode ser encontrada em [Nuget](https://www.nuget.org/packages/Selenium.WebDriver)

Existe algumas opções para instalar o Selenium.

### Packet Manager

```shell
Install-Package Selenium.WebDriver
```

### .NET CLI

```shell
dotnet add package Selenium.WebDriver
```

### CSProj

No arquivo `csproj` do seu projeto, especifique a dependência como `PackageReference` no `ItemGroup`:

{{< gh-codeblock language="xml" path="examples/dotnet/SeleniumDocs/SeleniumDocs.csproj#L14" >}}

### Considerações adicionais

Outras observações para usar o Visual Studio Code (vscode) e C#

Instale a versão compatível do .NET SDK conforme a seção acima.
Instale também as extensões do vscode (Ctrl-Shift-X) para C# e NuGet.
Siga as instruções [aqui ](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0)para criar e rodar o seu projeto de "Hello World" no console usando C#.

Você também pode criar um projeto inicial do NUnit usando a linha de comando `dotnet new NUnit`.
Certifique-se de que o arquivo `%appdata%\NuGet\nuget.config` esteja configurado corretamente, pois alguns desenvolvedores relataram que ele estará vazio devido a alguns problemas.
Se o `nuget.config` estiver vazio ou não estiver configurado corretamente, as compilações .NET falharão para projetos que estiverem usando Selenium.
Adicione a seguinte seção ao arquivo `nuget.config` se esse estiver vazio:

```
<configuration>
  <packageSources>
    <add key="nuget.org" value="https://api.nuget.org/v3/index.json" protocolVersion="3" />
    <add key="nuget.org" value="https://www.nuget.org/api/v2/" />   
  </packageSources>
...
```
Para mais informações sobre `nuget.config` [clique aqui](https://docs.microsoft.com/en-us/nuget/reference/nuget-config-file).
Você pode ter que customizar `nuget.config` para atender às suas necessidades.

Agora, volte para o vscode, aperte Ctrl-Shift-P, e digite "NuGet Add Package", e adicione os pacotes necessários para
o Selenium como o `Selenium.WebDriver`.
Aperte Enter e selecione a versão.
Agora você pode usar os exemplos da documentação relacionados ao C# com o vscode.

  {{% /tab %}}
  {{% tab header="Ruby" %}}
Você pode ver a minima versão suportada do Ruby para cada versão do Selenium em 
[rubygems.org](https://rubygems.org/gems/selenium-webdriver/)

O Selenium pode ser instalado de duas formas diferentes.

### Instalação manual

```shell
gem install selenium-webdriver
```

### Adicione para o gemfile do projeto

{{< gh-codeblock language="ruby" path="examples/ruby/Gemfile#L10" >}}

  {{% /tab %}}
  {{% tab header="JavaScript" %}}
Você pode encontrar a mínima versão suportada do Node para cada versão do Selenium 
na seção `Node Support Policy` no site [npmjs](https://www.npmjs.com/package/selenium-webdriver)

Selenium é normalmente instalado usando npm.

### Instalação local

```shell
npm install selenium-webdriver
```

### Adicione ao seu projeto

No `package.json` do seu projeto, adicione os requisitos em `dependencies`:

{{< gh-codeblock path="examples/javascript/package.json#L14" >}}

  {{% /tab %}}
  {{< tab header="Kotlin" >}}   
    Use as ligações Java para Kotlin.
  {{< /tab >}}
{{< /tabpane >}}

## Próximo passo
[Instale os drivers do navegador]({{< ref "install_drivers.md" >}})
