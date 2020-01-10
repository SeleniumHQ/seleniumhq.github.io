---
title: "安装独立服务器"
weight: 3
---

如果您打算使用 [Grid]({{< ref "/grid/_index.md" >}})，
那么您应该下载 [selenium-server-standalone JAR](https://selenium.dev/downloads/) 文件，
但是所有的组件都可以通过 [selenium-server](//repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-server/) 提供。
standalone JAR 文件包含所有内容，包括远程 Selenium 服务器和客户端绑定。
这意味着，如果在项目中使用 selenium-server-standalone JAR，则不必添加 selenium-java 或浏览器特定的 jar。

 ```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-server</artifactId>
  <version>3.X</version>
</dependency>
```
