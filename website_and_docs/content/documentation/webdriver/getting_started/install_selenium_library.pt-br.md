---
title: "Instalando bibliotecas do Selenium"
linkTitle: "Instalando bibliotecas do Selenium"
weight: 1
needsTranslation: true
description: >
  Setting up the Selenium library for your favourite programming language.
aliases: [
"/documentation/pt-br/selenium_installation/installing_selenium_libraries/",
"/pt-br/documentation/getting_started/installing_selenium_libraries/"
]
---

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

