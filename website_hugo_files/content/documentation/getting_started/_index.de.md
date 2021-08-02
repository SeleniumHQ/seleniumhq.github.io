---
title: "Erste Schritte"
linkTitle: "Erste Schritte"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Diese Seite wird von Englisch 
   auf Deutsch übersetzt. Sprichst Du Deutsch? Hilf uns die Seite 
   zu übersetzen indem Du uns einen Pull Reqeust schickst!
</p>
{{% /pageinfo %}}


Die Installation von Selenium unterscheidet sich grundlegend von 
der Installation kommerzieller Tools. Um Selenium in Deinem Automationsprojekt
zu verwenden, ist es notwendig zuerst die entsprechenden Bibliotheken der 
verwendeten Programmiersprache zu installieren. Weiters benötigst Du die passenden
WebDriver Dateien entsprechend des verwendeten Browsers, der für die Automation 
benötigt, und auf dem die Tests ausgeführt werden.

Intalling Selenium can be divided in three steps:

1. Installing the Selenium library for your desired programming language
2. Set up the browser driver to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{</* ref "/grid/_index.md" */>}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)



## Bibliotheken installieren

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