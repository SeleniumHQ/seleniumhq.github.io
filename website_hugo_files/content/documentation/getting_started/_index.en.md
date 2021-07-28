---
title: "Getting Started"
linkTitle: "Getting Started"
weight: 3
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

Selenium setup is quite different from the setup of other commercial tools.
To use Selenium in your automation project you need to install the language
bindings libraries for your language of choice. In addition you will need
WebDriver binaries for the browsers you want to automate and run test on. 

Intalling Selenium can be divided in three steps:

1. Installing the Selenium library for your desired programming language
2. Set up the browser driver to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{</* ref "/grid/_index.md" */>}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)


## Installing Selenium libraries

First you need to install the Selenium bindings for your automation project.
The installation process for libraries depends on the language you choose to use.

### Java
Installation of Selenium libraries for Java can be done using Maven.
Add the _selenium-java_ dependency in your project pom.xml:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.X</version>
</dependency>
```

The _selenium-java_ dependency supports running your automation
project with all Selenium supported browsers. If you want to run tests
only in a specific browser, you can add the dependency for that browser
in your _pom.xml_ file.
For example, you should add following dependency in your _pom.xml_
file to run your tests only in Firefox:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>4.X</version>
</dependency>
```
   
In a similar manner, if you want to run tests only in Chrome,
you should add the following dependency:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>4.X</version>
</dependency>
```

### Python
Installation of Selenium libraries for Python can be done using pip:

```shell
pip install selenium
```

Alternatively you can download the [PyPI source archive](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) and install it using _setup.py_:

```shell
python setup.py install
```

### C#
Installation of Selenium libraries for C# can be done using NuGet:

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

### Ruby
Installation of Selenium libraries for Ruby can be done using gem:

```shell
gem install selenium-webdriver
```

### JavaScript
Installation of Selenium libraries for JavaScript can be done using npm:

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
[starting page](/documentation) in our docs. Then head to the 
[WebDriver](/documentation/webdriver) section to learn more about
browser automation with Selenium.