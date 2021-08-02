---
title: "Getting Started"
linkTitle: "Getting Started"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to French. Do you speak French? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

La configuration de Selenium est très différente de la 
configuration d'autres outils commerciaux. Pour 
utiliser Selenium dans votre projet d'automatisation, 
vous devez installer les bibliothèques de liaisons 
linguistiques pour la langue de votre choix. De plus, 
vous aurez besoin des fichiers binaires WebDriver pour 
les navigateurs sur lesquels vous souhaitez 
automatiser et exécuter le test.

Intalling Selenium can be divided in three steps:

1. Installing the Selenium library for your desired programming language
2. Set up the browser driver to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{</* ref "/grid/_index.md" */>}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)


## Installer les librairies Selenium

Vous devez d'abord installer les fixations Selenium pour votre projet d'automatisation.
Le processus d'installation des bibliothèques dépend de la langue que vous choisissez d'utiliser.

### Java
L'installation des bibliothèques Selenium pour Java peut être effectuée à l'aide de Maven.
Ajoutez la dépendance _selenium-java_ dans votre projet pom.xml:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>3.X</version>
</dependency>
```

La dépendance _selenium-java_ prend en charge l'exécution de votre automatisation
projet avec tous les navigateurs pris en charge par Selenium. Si vous souhaitez exécuter des tests
uniquement dans un navigateur spécifique, vous pouvez ajouter la dépendance pour ce navigateur
dans votre fichier _pom.xml_.
Par exemple, vous devez ajouter la dépendance suivante dans votre _pom.xml_
fichier pour exécuter vos tests uniquement dans Firefox:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>4.X</version>
</dependency>
```
   
De la même manière, si vous souhaitez exécuter des tests uniquement dans Chrome,
vous devez ajouter la dépendance suivante:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>4.X</version>
</dependency>
```

### Python
L'installation des bibliothèques Selenium pour Python peut être effectuée à l'aide de pip:

```shell
pip install selenium
```


Vous pouvez également télécharger [l'archive source PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) et installez-le à l'aide de _setup.py_:

```shell
python setup.py install
```

### C#
L'installation des bibliothèques Selenium pour C # peut être effectuée à l'aide de NuGet:

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

### Ruby
L'installation des bibliothèques Selenium pour Ruby peut être effectuée à l'aide de gem:

```shell
gem install selenium-webdriver
```

### JavaScript
L'installation des bibliothèques Selenium pour JavaScript peut être effectuée à l'aide de npm:

```shell
npm install selenium-webdriver
```

### Kotlin
Due to missing native language bindings for Kotlin, you have to use the 
Java Bindings, e.g. with maven [Java](#java)


## Set up the browser driver

Instructions to set up the browser driver differ between browsers, you can check 
the following links to read the instructions for your desired browser.

- Firefox ([Mozilla GeckoDriver](https://github.com/mozilla/geckodriver/))
- Edge ([Microsoft EdgeDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/))
- Chrome ([Google ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/))
- Opera ([Opera ChromiumDriver](https://github.com/operasoftware/operachromiumdriver))
- Safari ([Apple SafariDriver](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari))
- Internet Explorer ([Internet Explorer Driver Server](https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver))

After completing the setup, you can run the code snippet shown at the 
[starting page](/fr/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.