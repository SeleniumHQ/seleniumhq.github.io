---
title: "Installing Standalone server"
weight: 3
---

If you plan to use [Grid]({{< ref "/grid/_index.md" >}}) then you should download the
[selenium-server-standalone JAR](//www.seleniumhq.org/download/) file.
 The _selenium-server-standalone_ jar is never uploaded, but all the components are available via
 [selenium-server](//repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-server/).
 The standalone JAR contains everything, including the remote Selenium server
 and the client-side bindings.
 This means that if you use the selenium-server-standalone jar
 in your project, you do not have to add selenium-java
 or a browser specific jar.

 ```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-server</artifactId>
  <version>3.X</version>
</dependency>
```
