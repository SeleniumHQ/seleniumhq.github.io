---
title: "시작하기"
linkTitle: "시작하기"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
aliases: 
        [
          "/documentation/ko/getting_started/", 
          "/documentation/ko/getting_started/quick/",
          "/documentation/ko/selenium_installation/",
          "/documentation/ko/getting_started_with_webdriver/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Korean. Do you speak Korean? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium은 _WebDriver_ 의 사용을 통해 대부분의 모든 브라우저의 자동화를 지원합니다.   
_WebDriver_ 는 웹 브라우저의 동작을 제어하기 위한 언어 중립 인터페이스를 정의하는 API와 프로토콜 입니다.  
각 브라우저는 *드라이버* 라고 불리는 특정한 웹 드라이버 구현에 의해 작동됩니다.    
드라이버는 브라우저로의 위임을 담당하는 구성 요소이며, Selenium과 브라우저와의 통신을 처리합니다.  

이러한 분리는 브라우저 공급 업체가 브라우저 구현을 책임지게하려는 의식적인 노력의 일부입니다.  
Selenium은 가능한 한 이러한 타사의 드라이버를 사용하지만, 현실적으로 불가능할 경우 프로젝트에서 유지&관리하는 자체 드라이버도 제공합니다.  

Selenium 프레임워크는 이 모든 부분을 하나로 묶습니다.    
서로 다른 브라우저 백엔드를 투명하게 사용할 수 있는   
사용자 대면 인터페이스를 통해 브라우저 간 및 플랫폼 간의 자동화가 가능합니다. 

Selenium 설치는 다른 프로젝트들과는 다를 수 있습니다. Selenium을 자동화 프로젝트에 사용하기 위해서는 당신이 선택한 언어로 바인딩된 라이브러리 설치가 필요합니다. 자동화와 테스트를 실행하기 위한 브라우저와 웹드라이버 바이너리가 추가적으로 필요합니다.

Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "/installing_selenium_libraries.md" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "/installing_browser_drivers.md" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/ko/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.
