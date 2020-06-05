---
title: "빠르게 살펴보기"
weight: 1
---
셀레니움은 그저 하나의 도구(tool)나 API가 아니라, 여러 도구들로 구성되어 있습니다.

## 웹 드라이버(WebDriver)

컴퓨터나 모바일 웹사이트 테스트를 자동화하는 것부터 시작하신다면,
아마 웹 드라이버 APIs부터 쓰게 될 겁니다.  _[웹 드라이버WebDriver]({{< ref "/webdriver/_index.md" >}})_
는 브라우저를 조종하고 테스트를 진행할 수 있도록 제작사에서 제공하는 브라우저 자동화 API들을 사용합니다.
이는 진짜 사용자가 브라우저를 이용하는 것과 비슷합니다.
웹 드라이버 API를 애플리케이션의 코드로 컴파일할 필요가 없기 때문에, 뒤섞이지 않습니다(not intrusive).
더 나아가, 실시간으로 사용하는 것과 같은 애플리케이션을 테스트합니다.

## 통합개발환경 (IDE)

_[IDE](https://selenium.dev/selenium-ide)_ (통합개발환경 Integrated Development Environment) 
은 자신만의 셀레니움 테스트 케이스를 개발하기 위해 사용할 도구입니다.
크롬이나 파이어폭스 확장으로 사용하기 쉽고, 이는 보통 테스트 케이스를 개발하는데 가장 효율적인 방법이기도 합니다.
이 도구는 여러분을 위해 브라우저 상에서 사용자의 행동을 기록합니다.
기존의 셀레니움 커맨드를 이용해서, 각 요소의 문맥에 따라 정의된 매개변수로 말이죠.
이는 시간을 절약해줄 뿐만 아니라 셀레니움 스크립트 문법을 배우는데 탁월한(excellent) 방법입니다.

## 조합 격자 (Grid)

Selenium Grid allows you to run test cases in different 
machines across different platforms. The control of 
triggering the test cases is on the local end, and 
when the test cases are triggered, they are automatically 
executed by the remote end.

After the development of the WebDriver tests, you may face 
the need of running your tests on multiple browser and 
operating system combinations.
This is where _[Grid]({{< ref "/grid/_index.md" >}})_ comes into the picture.