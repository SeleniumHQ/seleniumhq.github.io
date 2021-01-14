---
title: "Instalando o servidor Standalone"
weight: 3
---

Se você planeja usar [Grid]({{<ref "/grid/_index.md">}}), você deve baixar o
arquivo [selenium-server-standalone JAR](//selenium.dev/downloads/).
  Todos os componentes estão disponíveis via
  [selenium-server](//repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-server/).
  O JAR Standalone contém tudo, incluindo o servidor Selenium remoto
  e as ligações do lado do cliente.
  Isso significa que se você usar o jar selenium-server-standalone
  em seu projeto, você não precisa adicionar selenium-java
  ou um jar específico do navegador.

 ```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-server</artifactId>
  <version>3.X</version>
</dependency>
```
