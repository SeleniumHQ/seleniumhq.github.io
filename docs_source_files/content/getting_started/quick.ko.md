---
title: "퀵 투어"
weight: 1
---

Selenium는 하나의 툴이나 API가 아닌
여러 툴로 구성되어있습니다.

## WebDriver

만약 여러분이 데스크탑 웹사이트 테스트 자동화를 통해 시작한다면 WebDriver API들을 사용하게 될 것입니다. _[WebDriver]({{< ref "/webdriver/_index.md" >}})_ 
는 브라우저 공급자가 제공하는 브라우저 자동화 API를 사용하여 브라우저를 제어하고 테스트합니다. 이는 실제 사용자가 브라우저를 작동하는 것과 같습니다. WebDriver는 API를 애플리케이션 코드로 컴파일할 필요가 없기 때문에, 기존의 것을 거스르지 않습니다. 따라서, 실시간으로 진행하는 것과 동일한 애플리케이션을 테스트하는 것입니다.


## IDE

_[IDE](https://selenium.dev/selenium-ide)_ (Integrated Development Environment) 
는 Selenium 여러분의 테스트 케이스를 개발하기 위한 도구입니다. 사용이 간편한 크롬과 파이어폭스 확장이며 일반적으로 테스트 케이스를 개발하는 가장 효율적인 방법입니다. 기존 Selenium 명령을 사용하여 해당 요소의 컨텍스트에 의해 정의된 파라미터를 통해 사용자의 브라우저에서의 행위를 기록합니다. 이것은 시간 절약뿐만 아니라 Selenium 스크립트 문법을 배우는 훌륭한 방법입니다.

## Grid

WebDriver 테스트 이후, 여러분은 여러 브라우저와 운영체제 조합에서 테스트의 필요성을 느낄 수도 있습니다.
이는 _[Grid]({{< ref "/grid/_index.md" >}})_ 를 통해 해결할 수 있습니다.
