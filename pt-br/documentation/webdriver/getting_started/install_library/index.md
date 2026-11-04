# Instalando bibliotecas do Selenium

> Configurando a biblioteca Selenium para sua linguagem de programação favorita.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/install_library/

---

Primeiro você precisa instalar as bibliotecas Selenium para seu projeto de automação.
O processo de instalação de bibliotecas depende da linguagem que você escolher usar.

## Requisitos por linguagem

**Java**

Veja a mínima versão do Java suportada [aqui](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L32).

A instalação da biblioteca Selenium para Java é feita a partir de uma build tool.

<h3 id="maven">Maven</h3>

Especifique a dependência no `pom.xml` do seu projeto.

```xml
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>${selenium.version}</version>
</dependency>
```
<h3 id="gradle">Gradle</h3>

Especifique a dependência no `build.gradle` do seu projeto como `testImplementation`:

```gradle

dependencies {
```

**Python**

A mínima versão suportada do Python para cada versão do Selenium pode ser encontrada
em “Supported Python Versions” no [PyPi](https://pypi.org/project/selenium/).

Existe muitas formas diferentes de instalar Selenium.

<h3 id="pip">Pip</h3>

```shell
pip install selenium
```
<br>
<h3 id="download">Download</h3>

Como uma alternativa você pode baixar o [código fonte PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.-py3-none-any.whl) e instalar usando _pip_:

```shell
pip install selenium-x.x.x.-py3-none-any.whl
```
<br>
<h3 id="exigir-em-um-projeto">Exigir em um projeto</h3>

Para usar em um projeto, adicione no arquivo `requirements.txt`.

```text
selenium==4.52.0
```

**CSharp**

Uma lista com todos os frameworks suportados para cada versão do Selenium
pode ser encontrada em [Nuget](https://www.nuget.org/packages/Selenium.WebDriver)

Existe algumas opções para instalar o Selenium.

<h3 id="packet-manager">Packet Manager</h3>

```shell
Install-Package Selenium.WebDriver
```
<br>
<h3 id="net-cli">.NET CLI</h3>

```shell
dotnet add package Selenium.WebDriver
```
<br>
<h3 id="csproj">CSProj</h3>

No arquivo `csproj` do seu projeto, especifique a dependência como `PackageReference` no `ItemGroup`:

```xml
<PackageReference Include="Selenium.WebDriver" Version="4.52.0" />
```
<h3 id="considerações-adicionais">Considerações adicionais</h3>

Outras observações para usar o Visual Studio Code (vscode) e C#

Instale a versão compatível do .NET SDK conforme a seção acima.
Instale também as extensões do vscode (Ctrl-Shift-X) para C# e NuGet.
Siga as instruções [aqui ](https://docs.microsoft.com/en-us/dotnet/core/tutorials/with-visual-studio-code?pivots=dotnet-5-0)para criar e rodar o seu projeto de “Hello World” no console usando C#.

Você também pode criar um projeto inicial do NUnit usando a linha de comando `dotnet new NUnit`.
Certifique-se de que o arquivo `%appdata%\NuGet\nuget.config` esteja configurado corretamente, pois alguns desenvolvedores relataram que ele estará vazio devido a alguns problemas.
Se o `nuget.config` estiver vazio ou não estiver configurado corretamente, as compilações .NET falharão para projetos que estiverem usando Selenium.
Adicione a seguinte seção ao arquivo `nuget.config` se esse estiver vazio:

<pre tabindex="0"><code>&lt;configuration&gt;
  &lt;packageSources&gt;
    &lt;add key=&#34;nuget.org&#34; value=&#34;https://api.nuget.org/v3/index.json&#34; protocolVersion=&#34;3&#34; /&gt;
    &lt;add key=&#34;nuget.org&#34; value=&#34;https://www.nuget.org/api/v2/&#34; /&gt;   
  &lt;/packageSources&gt;
...
</code></pre>
Para mais informações sobre `nuget.config` [clique aqui](https://docs.microsoft.com/en-us/nuget/reference/nuget-config-file).
Você pode ter que customizar `nuget.config` para atender às suas necessidades.

Agora, volte para o vscode, aperte Ctrl-Shift-P, e digite “NuGet Add Package”, e adicione os pacotes necessários para
o Selenium como o `Selenium.WebDriver`.
Aperte Enter e selecione a versão.
Agora você pode usar os exemplos da documentação relacionados ao C# com o vscode.

**Ruby**

Você pode ver a minima versão suportada do Ruby para cada versão do Selenium em
[rubygems.org](https://rubygems.org/gems/selenium-webdriver/)

O Selenium pode ser instalado de duas formas diferentes.

<h3 id="instalação-manual">Instalação manual</h3>

```shell
gem install selenium-webdriver
```
<br>
<h3 id="adicione-para-o-gemfile-do-projeto">Adicione para o gemfile do projeto</h3>

```ruby
gem 'selenium-devtools', '= 0.156.0'
```

**JavaScript**

Você pode encontrar a mínima versão suportada do Node para cada versão do Selenium
na seção `Node Support Policy` no site [npmjs](https://www.npmjs.com/package/selenium-webdriver)

Selenium é normalmente instalado usando npm.

<h3 id="instalação-local">Instalação local</h3>

```shell
npm install selenium-webdriver
```
<br>
<h3 id="adicione-ao-seu-projeto">Adicione ao seu projeto</h3>

No `package.json` do seu projeto, adicione os requisitos em `dependencies`:

```json
"mocha": "12.0.3"
```

**Kotlin**

Use as ligações Java para Kotlin.

## Próximo passo
[Programando o seu primeiro script Selenium](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/first_script/index.md)
