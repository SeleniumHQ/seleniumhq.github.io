---
title: "Empezando"
linkTitle: "Empezando"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}


La configuración de Selenium es bastante diferente de la configuración
de otras herramientas comerciales.
Para usar Selenium en tu proyecto de automatización,
necesitas instalar las librerías de enlace de tu lenguaje de preferencia.
Además necesitarás los binarios de WebDriver para los navegadores 
en los que deseas automatizar y ejecutar pruebas.

Intalling Selenium can be divided in three steps:

1. Installing the Selenium library for your desired programming language
2. Set up the browser driver to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

## Instalando las librerías de Selenium

Primero debes instalar las librerías de enlace Selenium para tu
proyecto de automatización.
El proceso de instalación de las librerías depende del lenguaje 
que elijas usar.

### Java
La instalación de las librerías Selenium para Java se puede hacer usando Maven.
Agrega la dependencia de _selenium-java_ en el pom.xml de tu proyecto:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.X</version>
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
  <version>4.X</version>
</dependency>
```

De igual manera, si quieres ejecutar las pruebas solamente
en Chrome, debes agregar la siguiente dependencia:   

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>4.X</version>
</dependency>
```

### Python
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

### C#
La instalación de las librerías de Selenium para C# se puede
hacer usando NuGet:

```shell
# Usando el manejador de paquetess
Install-Package Selenium.WebDriver
# o usando el CLI de .Net
dotnet add package Selenium.WebDriver
```

### Ruby
La instalación de las librerías de Selenium para Ruby se puede
hacer usando gem:

```shell
gem install selenium-webdriver
```

### JavaScript
La instalación de las librerías de Selenium para JavaScript se puede
hacer usando npm:

```shell
npm install selenium-webdriver
```

### Kotlin
Debido a la falta de librerías de enlace de idioma nativas para Kotlin, 
debe usar las de Java, p. ej. con Maven [Java](#java)


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