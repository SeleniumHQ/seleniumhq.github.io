---
title: "구성 요소 이해"
linkTitle: "구성 요소 이해"
weight: 1
aliases: ["/documentation/ko/webdriver/understanding_the_components/"]
---

WebDriver를 사용하여 테스트 스위트를 빌드하려면 이해하고 이해해야 합니다.
다양한 구성 요소를 효과적으로 사용합니다. 모든 것과 마찬가지로
소프트웨어, 다른 사람들은 동일한 아이디어에 대해 다른 용어를 사용합니다. 아래는
이 설명에서 용어가 사용되는 방식에 대한 분석입니다.

### 술어

* **API:** _Application Programming Interface_. 이것은 "명령"세트입니다
WebDriver를 조작하는 데 사용합니다.
* **Library:** API와 필요한 코드를 포함하는 코드 모듈
그것들을 구현하기 위해. 라이브러리는 각 언어 바인딩에 따라 다릅니다 (예 : .jar).
Java 용 파일, .NET 용 .dll 파일 등
* **Driver:** 실제 브라우저를 제어합니다. 대부분의 운전자
브라우저 공급 업체 자체에 의해 생성됩니다. 드라이버는 일반적으로
브라우저 자체를 사용하여 시스템에서 실행되는 실행 가능 모듈
테스트 스위트를 실행하는 시스템에는 없습니다.
참고 : _일부 사람들은 드라이버를 프록시라고합니다._
* **Framework:** WebDriver 지원으로 사용되는 추가 라이브러리
WebDriver 제품군에 대한 지원으로 사용되는 추가 라이브러리입니다. 이러한 프레임워크는 JUnit 또는 
NUnit과 같은 테스트 프레임워크일 수 있습니다. 그것들은 또한 Cucumber나 Robotium과 같은 자연어 
특징을 지원하는 틀일 수도 있습니다. 프레임워크는 또한 테스트 대상 시스템의 조작 또는 구성, 
데이터 생성, 테스트 신탁 등과 같은 용도로 작성 및 사용될 수 있습니다.


### 부품과 조각
웹드라이버는 최소한 드라이버를 통해 브라우저와 대화합니다. 통신은 두 가지 방법이 있는데, 
WebDriver는 드라이버를 통해 브라우저에 명령을 전달하고 동일한 경로를 통해 정보를 다시 수신합니다.

{{< figure src="/images/documentation/webdriver/basic_comms.png" class="img-responsive text-center" alt="기본 커뮤니케이션">}}

이 드라이버는 구글의 크롬/크로미엄용 크롬 드라이버, 모질라의 파이어폭스용 게코 드라이버 등과 같은 
브라우저에만 한정됩니다. 드라이버는 브라우저와 동일한 시스템에서 실행됩니다. 이는 테스트 자체가 실행 
중인 시스템과 동일하거나 동일하지 않을 수 있습니다.

위의 간단한 예는 직접 커뮤니케이션입니다. 브라우저와의 통신은 Selenium Server 또는 RemoteWebDriver를 통한 
원격 통신일 수도 있습니다. RemoteWebDriver는 드라이버 및 브라우저와 동일한 시스템에서 실행됩니다.

{{< figure src="/images/documentation/webdriver/remote_comms.png" class="img-responsive text-center" alt="원격 커뮤니케이션">}}

원격 통신은 Selenium Server 또는 Selenium Grid를 사용하여 수행될 수 있으며, 둘 다 호스트 시스템의 드라이버와 통신할 수 있습니다.

{{< figure src="/images/documentation/webdriver/remote_comms_server.png" class="img-responsive text-center" alt="그리드와의 원격 통신">}}

## 프레임 워크가 적합한 곳

WebDriver는 하나의 작업과 하나의 작업만을 가지고 있습니다: 
위의 방법들 중 하나를 통해 브라우저와 통신합니다. WebDriver는 테스트에 대해 아무것도 모릅니다: 
그것은 사물을 비교하고, 합격 또는 불합격을 주장하는 방법을 알지 못합니다. 그리고 확실히 보고나 
주어진/언제/그럼 문법에 대해서는 아무것도 알지 못합니다.

여기서 다양한 프레임워크가 작용합니다. 최소한 NUnit과 같은 언어 바인딩과 일치하는 테스트 프레임워크가 
필요합니다.(NET, Java용 JUnit, Ruby용 RSpec 등)

테스트 프레임워크는 웹 드라이버 및 테스트의 관련 단계를 실행하고 실행하는 역할을 합니다. 
따라서 다음 이미지와 비슷하다고 생각할 수 있습니다.

{{< figure src="/images/documentation/webdriver/test_framework.png" class="img-responsive text-center" alt="테스트 프레임 워크">}}
Cucumber와 같은 자연어 프레임워크/툴은 위 그림의 테스트 프레임워크 상자의 일부로 존재하거나 테스트 프레임워크 전체를 자체 구현으로 포장할 수 다.
