---
title: "브라우저"
weight: 1
---

## 일반적인 브라우저

Selenium 프레임워크는 공식적으로 다음과 같은 브라우저를 지원합니다.

| 브라우저 | 관리자 | 지원 버전 |
| -------- | ---------- | ------------------ |
| Chrome | [Chromium](//sites.google.com/a/chromium.org/chromedriver/) | 모든 버전 |
| Firefox | [Mozilla](//github.com/mozilla/geckodriver/) | 54 이상 |
| Edge | [Microsoft](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | 84 이상 |
| Internet Explorer | Selenium | 6 이상 |
| Opera | [Opera Chromium](//github.com/operasoftware/operachromiumdriver/) / [Presto](//github.com/operasoftware/operaprestodriver) | 10.5 이상 |
| Safari | [Apple](//webkit.org/blog/6900/webdriver-support-in-safari-10/) | 10 이상 |

## 전문화된 브라우저

개발 환경에서 전형적으로 사용되는 특수 브라우저 세트도 있습니다.  
우리는 자동화 목적으로 이러한 브라우저를 사용할 수 있으며, Selenium은 다음과 같은 전문화된 드라이버를 지원합니다.  

| 드라이버명 | 목적 | 관리자 |
| -------- | ---------- | ------------------ |
| HtmlUnitDriver | Headless browser emulator backed by Rhino | Selenium 프로젝트 |
