---
title: "Instalando bibliotecas do Selenium"
weight: 1
---

Primeiro você precisa instalar as ligações Selenium para seu projeto de automação.
O processo de instalação de bibliotecas depende da linguagem que você escolher usar.

## _Java_
A instalação de bibliotecas Selenium para Java pode ser feita usando Maven.
Adicione a dependência selenium-java em seu pom.xml:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>3.X</version>
</dependency>
```

A dependência _selenium-java_ suporta a execução de sua automação com todos os navegadores com suporte Selenium. Se você quiser fazer testes
apenas em um navegador específico, você pode adicionar a dependência para esse navegador
em seu arquivo _pom.xml_.
Por exemplo, você deve adicionar a seguinte dependência em seu _pom.xml_
arquivo para executar seus testes apenas no Firefox:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>3.X</version>
</dependency>
```
   
De maneira semelhante, se você deseja executar testes apenas no Chrome,
você deve adicionar a seguinte dependência:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>3.X</version>
</dependency>
```

## _Python_
A instalação de bibliotecas Selenium para Python pode ser feita usando pip:

```shell
pip install selenium
```

Como alternativa, você pode baixar o [arquivo de origem do PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) e instale-o usando _setup.py_:

```shell
python setup.py install
```

## _C#_
A instalação de bibliotecas Selenium para C# pode ser feita usando NuGet:

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

## _Ruby_
A instalação de bibliotecas Selenium para Ruby pode ser feita usando gem:

```shell
gem install selenium-webdriver
```

## _JavaScript_
A instalação de bibliotecas Selenium para JavaScript pode ser feita usando npm:

```shell
npm install selenium-webdriver
```

## _Kotlin_
Devido à ausência de vínculos de linguagem nativo para Kotlin, você deve usar vínculos Java, por exemplo, com Maven [Java](#java)

