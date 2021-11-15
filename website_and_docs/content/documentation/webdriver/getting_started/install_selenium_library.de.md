---
title: "Bibliothek installieren"
linkTitle: "Bibliothek Installieren"
weight: 1
description: >
  Setting up the Selenium library for your favourite programming language.
aliases: ["/documentation/de/selenium_installation/installing_selenium_libraries/"]    
---

Zu Beginn ist es notwendig das die Selenium Bindings für Dein 
Automationsprojekt installiert werden. Der Installationsprozess ist von der
gewählten Programmiersprache abhängig.


### Java
Die Installation der Selenium Bibliotheken für Java kann mit Hilfe von
Maven erfolgen. Füge in Deinem Projekt in die pom.xml die _selenium-java_
dependecy hinzu.

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.X</version>
</dependency>
```

Mit der _selenium-java_ dependency ist es möglich Tests laufen zu lassen 
in allen von Selenium unterstützen Browsern. Falls Du Test nur in einem 
spezifischen Browser ausführen möchtest, ist es möglich auch nur die 
dependecy für den gewählten Browser in der _pom.xml_ hinzuzufügen.

Wenn Du zum Beispiel Tests nur in Firefox ausführen möchtest, füge folgende
dependecy in die _pom.xml_ hinzu:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>4.X</version>
</dependency>
```
   
Analog wenn Du die Tests nur in Chrome ausführen möchtest, musst Du folgende
dependecy hinzufügen:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>4.X</version>
</dependency>
```

### Python
Die Installation der Selenium Bibliotheken for Phython can mittels _pip_ erfolgen:

```shell
pip install selenium
```

Alternativ kannst Du den [PyPI Quellcode](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) downloaden und diesen mittels _steup.py_ installieren:


```shell
python setup.py install
```

### C#
Die Installation der Selenium Bibliotheken für C# kann mittels _NuGet_ erfolgen:

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

### Ruby
Die Selenium Bibliotheken für Ruby können mittels _gem_ installiert werden:

```shell
gem install selenium-webdriver
```

### JavaScript
Die Installation der Selenium Bibliotheken für JavaScript kann mit npm durchgeführt werden:

```shell
npm install selenium-webdriver
```

### Kotlin
Da es noch keine Implementierung für Kotlin gibt, müssen die Java Bibliothken verwendet werden, 
diese können analg zu [Java](#java) mittels maven eingebunden werden.
