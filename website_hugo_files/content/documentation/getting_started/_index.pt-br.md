---
title: "Começando"
linkTitle: "Começando"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

A configuração do Selenium é bastante diferente da configuração de outras ferramentas comerciais.
Para usar Selenium em seu projeto de automação, você precisa instalar as
bibliotecas de linguagem para sua linguagem de escolha. Além disso, você precisará dos
binários WebDriver para os navegadores que você deseja automatizar e executar testes.

Intalling Selenium can be divided in three steps:

1. Installing the Selenium library for your desired programming language
2. Set up the browser driver to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)



## Instalando bibliotecas do Selenium

Primeiro você precisa instalar as ligações Selenium para seu projeto de automação.
O processo de instalação de bibliotecas depende da linguagem que você escolher usar.

### Java
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

### Python
A instalação de bibliotecas Selenium para Python pode ser feita usando pip:

```shell
pip install selenium
```

Como alternativa, você pode baixar o [arquivo de origem do PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) e instale-o usando _setup.py_:

```shell
python setup.py install
```

### C#
A instalação de bibliotecas Selenium para C# pode ser feita usando NuGet:

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

### Ruby
A instalação de bibliotecas Selenium para Ruby pode ser feita usando gem:

```shell
gem install selenium-webdriver
```

### JavaScript
A instalação de bibliotecas Selenium para JavaScript pode ser feita usando npm:

```shell
npm install selenium-webdriver
```

### Kotlin
Devido à ausência de vínculos de linguagem nativo para Kotlin, você deve usar vínculos Java, por exemplo, com Maven [Java](#java)


## Set up the browser driver

Instructions to set up the browser driver differ between browsers, you can check 
the following links to read the instructions for your desired browser.

- Firefox ([Mozilla GeckoDriver](https://github.com/mozilla/geckodriver/))
- Edge ([Microsoft EdgeDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/))
- Chrome ([Google ChromeDriver](https://chromedriver.chromium.org/))
- Opera ([Opera ChromiumDriver](https://github.com/operasoftware/operachromiumdriver))
- Safari ([Apple SafariDriver](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari))
- Internet Explorer ([Internet Explorer Driver Server](https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver))

After completing the setup, you can run the code snippet shown at the 
[starting page](/de/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.