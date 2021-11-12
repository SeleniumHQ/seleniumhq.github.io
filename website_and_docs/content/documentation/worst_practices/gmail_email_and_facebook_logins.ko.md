---
title: "Gmail, 이메일과 Facebook logins"
linkTitle: "Gmail, 이메일과 페이스북"
weight: 4
aliases: ["/documentation/ko/worst_practices/gmail_email_and_facebook_logins/"]
---

여러 가지 이유로, WebDriver를 사용하여 Gmail이나 Facebook과 같은 사이트에 로그인하는 
것은 권장되지 않습니다.
이러한 사이트의 사용 조건에 반대하는 것 외에도(계정 종료의 위험이 있는 경우) 느리고 
신뢰할 수 없습니다.

가장 좋은 방법은 이메일 제공자들이 제공하는 API를 사용하는 것입니다. 또는 Facebook의 
경우 시험 계정, 친구 등을 만들기 위한 API를 공개하는 개발자 도구 서비스를 이용하는 
것입니다. 비록 API를 사용하는 것이 조금 더 힘든 일처럼 보일 수는 있어도 속도, 신뢰성,
그리고 안정성에 대한 보장을 받을 것입니다. 반면 웹 페이지 및 HTMLlocators가 종종 당신의
시험 프레임워크를 업데이트하는 것을 요구하는 API은 변경될 것 같지 않습니다.

테스트의 어느 지점에서 WebDriver를 사용하여 제3자 사이트에 로그인하면 테스트가 더 길어지기 때문에 
테스트 실패의 위험이 증가합니다. 일반적인 경험의 법칙은 긴 테스트가 더 취약하고 신뢰할 수 없다는 것입니다.

WebDriver 구현[W3C conformant]//w3c.github.io/webdriver/webdriver-spec.html)
또한 서비스 거부 공격이 완화될 수 있도록 `WebDriver` 속성으로 `navigator` 물체에 주석을 꼭 달아야 다.
