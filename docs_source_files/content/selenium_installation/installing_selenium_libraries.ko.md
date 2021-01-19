---
title: "Selenium 라이브러리 설치"
weight: 1
---


당신의 자동화 프로젝트를 위해서 첫 번째로 Selenium 바인딩을 설치해야 합니다.
당신이 사용할 언어에 따른 라이브러리 설치 절차입니다.

## _Java_
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
  <version>3.X</version>
</dependency>
```
 
이와 비슷하게, 만약 크롬에서만 테스트하길 원한다면, 다음과 같은 의존성을 추가해야 합니다.

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>3.X</version>
</dependency>
```

## _Python_
파이썬을 위한 Selenium 라이브러리는 pip로 설치할 수 있습니다.
```shell
pip install selenium
```
또는,  [PyPI source archive](https://pypi.org/project/selenium/#files)를 다운로드해서 _setup.py_ 를 통해 설치할 수 있습니다.


```shell
python setup.py install
```

## _C#_
C#을 위한 Selenium 라이브러리는 NuGet으로 설치할 수 있습니다.

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

## _Ruby_
Ruby를 위한 Selenium 라이브러리는 gem으로 설치할 수 있습니다.

```shell
gem install selenium-webdriver
```

## _JavaScript_
자바스크립트를 위한 Selenium 라이브러리는 npm으로 설치할 수 있습니다.

```shell
npm install selenium-webdriver
```

## _Kotlin_
Kotlin을 위한 네이티브 언어 바인딩이 없기 때문에, 자바 바인딩을 사용해야 합니다. e.g. 메이븐 [Java](#java)

