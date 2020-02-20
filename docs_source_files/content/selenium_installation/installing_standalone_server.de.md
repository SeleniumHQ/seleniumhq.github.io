---
title: "Standalone Server installieren"
weight: 3
---

Wenn Du vorhast [Grid]({{< ref "/grid/_index.md" >}}) zu verwenden, 
dann solltest Du Dir 
[selenium-server-standalone JAR](//selenium.dev/downloads/) downloaden. 
Alle benötigten Komponenten sind via 
[selenium-server](//repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-server/)
verfügbar.
Die standalone JAR beinhaltet den Remote Selenium Server als auch die 
Client-side Implementierungen. Das bedeutet wenn die selenium-server-standalone 
JAR in Deinem Projekt verwendet wird, dann ist es nicht notwendig selenium-java 
oder browserspezifische JAR hinzuzufügen.


 ```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-server</artifactId>
  <version>3.X</version>
</dependency>
```
