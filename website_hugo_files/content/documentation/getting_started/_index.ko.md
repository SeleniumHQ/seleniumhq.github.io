---
title: "시작하기"
linkTitle: "시작하기"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Korean. Do you speak Korean? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}


Selenium 설치는 다른 프로젝트들과는 다를 수 있습니다. Selenium을 자동화 프로젝트에 사용하기 위해서는 당신이 선택한 언어로 바인딩된 라이브러리 설치가 필요합니다. 자동화와 테스트를 실행하기 위한 브라우저와 웹드라이버 바이너리가 추가적으로 필요합니다.

Intalling Selenium can be divided in three steps:

1. Installing the Selenium library for your desired programming language
2. Set up the browser driver to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

## Selenium 라이브러리 설치

### Java
Maven을 사용한 Java용 Selenium 라이브러리 설치입니다.
프로젝트안에 있는 pom.xml에 _selenium-java_ 의존 라이브러리를 추가하세요.

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>3.X</version>
</dependency>
```

_selenium-java_ 의존성 라이브러리는 Selenium이 자동화할 수 있는 모든 브라우저의 자동화를 지원합니다. 만약 당신이 특정 브라우저에서만 테스트를 실행하기 원한다면, 원하는 브라우저를 _pom.xml_ 파일에 의존성을 추가합니다. 예를 들어, Firefox에서 테스트를 실행하고 싶다면 다음과 같은 의존성을 _pom.xml_ 파일에 추가해야 합니다.
```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>4.X</version>
</dependency>
```
 
이와 비슷하게, 만약 크롬에서만 테스트하길 원한다면, 다음과 같은 의존성을 추가해야 합니다.

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>4.X</version>
</dependency>
```

### Python
파이썬을 위한 Selenium 라이브러리는 pip로 설치할 수 있습니다.
```shell
pip install selenium
```
또는,  [PyPI source archive](https://pypi.org/project/selenium/#files)를 다운로드해서 _setup.py_ 를 통해 설치할 수 있습니다.


```shell
python setup.py install
```

### C#
C#을 위한 Selenium 라이브러리는 NuGet으로 설치할 수 있습니다.

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

### Ruby
Ruby를 위한 Selenium 라이브러리는 gem으로 설치할 수 있습니다.

```shell
gem install selenium-webdriver
```

### JavaScript
자바스크립트를 위한 Selenium 라이브러리는 npm으로 설치할 수 있습니다.

```shell
npm install selenium-webdriver
```

### Kotlin
Kotlin을 위한 네이티브 언어 바인딩이 없기 때문에, 자바 바인딩을 사용해야 합니다. e.g. 메이븐 [Java](#java)


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
[starting page](/ko/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.