---
title: "Instalando las librerías de Selenium"
weight: 1
---

Primero debes instalar las librerías de enlace Selenium para tu
proyecto de automatización.
El proceso de instalación de las librerías depende del lenguaje 
que elijas usar.

## _Java_
La instalación de las librerías Selenium para Java se puede hacer usando Maven.
Agrega la dependencia de _selenium-java_ en el pom.xml de tu proyecto:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>3.X</version>
</dependency>
```

La dependencia _selenium-java_ permite la ejecución de tu proyecto de
automatización en todos los navegadores compatibles con Selenium. 
Si quieres ejecutar pruebas en un navegador en específico, 
puedes agregar la dependencia para ese navegador
en el archivo _pom.xml_.
Por ejemplo, debes agregar la siguiente dependencia en tu
archivo _pom.xml_ para ejecutar tus pruebas solamente en Firefox:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>3.X</version>
</dependency>
```

De igual manera, si quieres ejecutar las pruebas solamente
en Chrome, debes agregar la siguiente dependencia:   

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>3.X</version>
</dependency>
```

## _Python_
La instalación de las librerías de Selenium en Python
se puede hacer usando pip:

```shell
pip install selenium
```

Alternativamente, puedes descargar el [archivo fuente PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) e instalarlo usando _setup.py_:

```shell
python setup.py install
```

## _C#_
La instalación de las librerías de Selenium para C# se puede
hacer usando NuGet:

```shell
# Usando el manejador de paquetess
Install-Package Selenium.WebDriver
# o usando el CLI de .Net
dotnet add package Selenium.WebDriver
```

## _Ruby_
La instalación de las librerías de Selenium para Ruby se puede
hacer usando gem:

```shell
gem install selenium-webdriver
```

## _JavaScript_
La instalación de las librerías de Selenium para JavaScript se puede
hacer usando npm:

```shell
npm install selenium-webdriver
```

## _Kotlin_
Debido a la falta de librerías de enlace de idioma nativas para Kotlin, debe usar las de Java, p. ej. con Maven [Java](#java)

