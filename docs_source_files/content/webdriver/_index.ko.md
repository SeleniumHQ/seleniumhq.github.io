---
title: "WebDriver"
chapter: true
weight: 5
---

# 웹 드라이버(Web Driver)

최근 셀레니움의 가장 큰 변화는 WebDriver API가 내장된 점입니다. 사용자가 로컬 또는 Selenium 서버를 사용하여 원격 컴퓨터에서 브라우저를 기본적으로 구동하는 것으로 브라우저 자동화에 큰 발전을 가져왔습니다.

Selenium WebDriver는 RC(Selenium Remote Control)와 같은 역할에 적합하며, 원래의 1.x 바인딩을 통합하였다.
언어 바인딩과 개별 브라우저 제어 코드의 구현을 모두 말한다.
이것은 일반적으로 단지 _WebDriver_ 라고 부르거나 때로는 _Selenium 2_ 라고 부른다.

Selenium 1.0 + WebDriver = Selenium 2.0

* WebDriver는 Selenium-RC API의 일부 제한사항과 함께 보다 단순하고 간결한 프로그래밍 인터페이스로 설계되었다.

* WebDriver는 Selenium 1.0과 비교하면 간결한 객체 지향형 API입니다.

* 브라우저를 훨씬 효과적으로 구동하고 파일 업로드 또는 다운로드, 팝업, 대화 상자 도착자 등 기능 테스트 범위에 영향을 준 Selenium 1의 단점을 해결하였습니다.


* WebDriver는 Selenium RC의
[Single-host 오리진 policy](//en.wikipedia.org/wiki/Same-origin_policy).의 단점을 해결하였습니다.

