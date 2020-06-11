---
title: "독립형 서버 설치"
weight: 3
---


[Grid]({{< ref "/grid/_index.md" >}})를 사용하려면 [selenium-server-standalone JAR](//selenium.dev/downloads/) 파일을 다운로드하십시오.
모든 구성요소는 [selenium-server](//repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-server/)를 통해 이용할 수 있습니다.
독립형 JAR에는 원격 Selenium 서버와 클라이언트측 바인딩을 포함한 모든 것이 들어 있습니다.
즉, 프로젝트에서 selenium 서버 독립형 jar을 사용할 경우 selenium-java 또는 브라우저별 jar을 추가하지 않아도 됩니다.

 ```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-server</artifactId>
  <version>3.X</version>
</dependency>
```
