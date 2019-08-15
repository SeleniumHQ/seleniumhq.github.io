---
title: "Instalación de las librerías Selenium"
weight: 1
---


Primero debe instalar las librerías de Selenium para su proyecto de automatización. El proceso de instalación de las librerías depende del lenguaje que elija usar.

## _Java_
La instalación de las bibliotecas Selenium para Java se puede hacer usando Maven.
Agregue la dependencia _selenium-java_ en el archivo *pom.xml* de su proyecto:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>3.X</version>
</dependency>
```

La dependencia de _selenium-java_ admite la ejecución de su proyecto de automatización con todos los navegadores compatibles con Selenium. Si desea ejecutar pruebas solo en un navegador específico, puede agregar la dependencia para ese navegador en su archivo _pom.xml_. Por ejemplo, debe agregar la siguiente dependencia en su archivo _pom.xml_ para ejecutar sus pruebas solo en Firefox:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>3.X</version>
</dependency>
```
   
De manera similar, si desea ejecutar pruebas solo en Chrome, debe agregar la siguiente dependencia:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>3.X</version>
</dependency>
```

## _Python_
La instalación de las librerías de Selenium en Python se puede hacer usando pip:

```shell
pip install selenium
```

Alternativamente, puede descargar el [archivo fuente PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) e instalarlo usando _setup.py_:

```shell
python setup.py install
```

## _C#_
La instalación de las librerías de Selenium para C# se puede hacer usando NuGet:

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

## _Ruby_
La instalación de las librerías de Selenium para Ruby se puede hacer usando gem:

```shell
gem install selenium-webdriver
```

## _JavaScript_
La instalación de las librerías de Selenium para JavaScript se puede hacer usando npm:

```shell
npm install selenium-webdriver
```

