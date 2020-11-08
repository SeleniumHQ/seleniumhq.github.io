---
title: "파일 다운로드"
weight: 2
---

Selenium의 제어 하에 브라우저로 링크를 클릭하면 다운로드를 시작할 수 있지만, API는 다운로드 진행 상황을 노출시키지 않아 다운로드 파일 테스트에 이상적이지 않다.
이것은 파일 다운로드가 웹 플랫폼과의 사용자 상호작용을 모방하는 중요한 측면으로 여겨지지 않기 때문이다.
대신 Selenium(및 필요한 쿠키)을 사용하여 링크를 찾아 HTTP 요청 라이브러리 like [libcurl](//curl.haxx.se/libcurl/).에 전달하라.

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Korean. Do you speak Korean? Help us to translate
it by sending us pull requests!
{{% /notice %}}

The [HtmlUnit driver](https://github.com/SeleniumHQ/htmlunit-driver) can download attachments by accessing them as input streams by implementing the [AttachmentHandler](https://htmlunit.sourceforge.io/apidocs/com/gargoylesoftware/htmlunit/attachment/AttachmentHandler.html) interface. The AttachmentHandler can the be added to the [HtmlUnit](https://htmlunit.sourceforge.io/) WebClient.
