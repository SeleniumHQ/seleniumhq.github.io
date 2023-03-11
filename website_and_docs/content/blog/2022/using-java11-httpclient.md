---
title: "Using Java 11+ HTTP Client in Selenium 4.5.0 and beyond"
linkTitle: "Using Java 11+ HTTP Client in Selenium 4.5.0 and beyond"
date: 2022-09-16
tags: ["selenium"]
categories: ["releases"]
author: Puja Jagani ([@pujagani](https://twitter.com/pujagani))
description: >
   We’re happy to share that starting from Selenium 4.5.0, a Java 11+ HttpClient is supported
---

### Current HTTP client used in Selenium
Selenium uses an HTTP client and associated WebSocket client for multiple purposes
* To send commands to the WebDriver 
* To send commands from the Selenium client library to the Grid 
* For various Grid components to communicate with each other depending on the Grid mode
* To create ChromeDevTools protocol and BiDi protocol sessions

Currently, Selenium uses [AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client). 
AsyncHttpClient is an open-source library built on top of Netty. It allows the execution of HTTP 
requests and responses asynchronously. Additionally it also provides WebSocket support. Hence it 
is a good fit. 

### Why does Selenium want to move to Java 11+ HTTP Client?
While AsyncHttpClient provides the required functionality, the open-source project is not been 
actively maintained since June 2021. It coincides with the fact that Java 11+ provides a built-in 
HTTP and WebSocket client. Selenium can utilize it to replace AsyncHttpClient.

Currently, Selenium Java supports Java 8. Selenium has plans to upgrade the minimum version 
supported to Java 11. However, it is a sizeable effort. Aligning it with major releases and accompanied 
announcements  is crucial to ensure the user experience is intact.

The Selenium server runs great on Java 11+ already, so while we plan to make that the minimum 
version in a future release, for now we plan to introduce optional components that can take advantage 
of modern Java releases.

So as a first step towards this move, the Java 11+ HTTP client from Selenium 4.5.0 and above.

### Using Java 11+ HTTP Client in Selenium

#### Prerequisites: 
* Project configured to use Java 11+
* Using Selenium 4.5.0 as a minumum version, find the latest in the [downloads](/downloads) page.

#### Integrating the Java 11+ client
Java 11+ HTTP client sits in its own artifact. It can be imported into projects that use Java 11+.

##### Add the artifact the POM 

Add the follow dependencies to your pom.xml

```
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.5.0</version>
</dependency>
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-http-jdk-client</artifactId>
  <version>4.5.0</version>
</dependency>
```

**NOTE**: In the dependencies above version `4.5.0` is shown, however we recommend you to check the
[downloads](/downloads) page to use the latest version released. Make sure the versions used are
matching.

##### Set the system property

Set the system property to indicate that Java 11+ Http client needs to be used.
By default, it uses the AsyncHttpClient.

```
 System.setProperty("webdriver.http.factory", "jdk-http-client");

```
You are all set up to leverage the newly supported client.
The user-experience remains the unchanged. Everything else works as expected.

#### Using the Java 11+ client in Grid
To do that, you will need to download the `selenium-http-jdk-client` jar file and
use the `--ext` flag to make it available in the Grid jar's classpath.

The jar file can be downloaded directly from [repo1.maven.org](https://repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-http-jdk-client/4.5.0/)
and then start the Grid in the following way:

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-4.5.0.jar -—ext selenium-http-jdk-client-4.5.0.jar standalone
```

An alternative to downloading the `selenium-http-jdk-client` jar file is to use [Coursier](https://get-coursier.io/docs/cli-installation).

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-4.5.0.jar —-ext $(coursier fetch -p org.seleniumhq.selenium:selenium-http-jdk-client:4.5.0) standalone
```

If you are using the Hub/Node(s) mode or the Distributed mode, setting the `-Dwebdriver.http.factory=jdk-http-client` and `—-ext` flags 
needs to be done for each one of the components.

**NOTE**: In the dependencies above version `4.5.0` is shown, however we recommend you to check the
[downloads](/downloads) page to use the latest version released. Make sure the versions used are
matching.



Huge thanks to Simon Stewart ([@shs96c](https://twitter.com/shs96c)) for making this possible with his contribution! 


