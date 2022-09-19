---
title: "Using Java 11+ HTTP Client in Selenium 4.5.0 and beyond"
linkTitle: "Using Java 11+ HTTP Client in Selenium 4.5.0 and beyond"
date: 2022-09-16
tags: ["selenium"]
categories: ["releases"]
author: Puja Jagani ([@pujagani](https://twitter.com/pujagani))
description: >
   We’re happy to share that Selenium 4.5.0 supports Java 11+ HttpClient
---

### Current HTTP client used in Selenium
Selenium uses an HTTP client and associated WebSocket client for multiple purposes
* To send commands to the WebDriver 
* To send commands from the Selenium client library to the Grid 
* For various Grid components to communicate with each other depending on the Grid mode
* To create ChromeDevTools protocol and BiDi protocol sessions

Currently, Selenium uses [AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client). AsyncHttpClient is an open-source library built on top of Netty. It allows the execution of HTTP requests and responses asynchronously. Additionally it also provides WebSocket support. Hence it is a good fit. 

### Why does Selenium want to move to Java 11+ HTTP Client?
While AsyncHttpClient provides the required functionality, the open-source project is not been actively maintained since June 2021. It coincides with the fact that Java 11+ provides a built-in HTTP and WebSocket client. Selenium can utilize it to replace AsyncHttpClient.
Currently, Selenium Java supports Java 8. Selenium has plans to upgrade the minimum version supported to Java 11. However, it is a sizeable effort. Aligning it with major releases and accompanied announcements  is crucial to ensure the user experience is intact.

The Selenium server runs great on Java 11+ already, so while we plan to make that the minimum version in a future release, for now we plan to introduce optional components that can take advantage of modern Java releases.

So as a first step towards this move, Selenium 4.5.0 client supports the Java 11+ HTTP client.

### Using Java 11+ HTTP Client in Selenium 4.5.0
#### Prerequisites: 
* Project configured to use Java 11+
* Using Selenium 4.5.0 

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

##### Set the system property

Set the system property to indicate that Java 11+ Http client needs to be used.
By default, it uses the AsyncHttpClient.

```
 System.setProperty("webdriver.http.factory", "jdk-http-client");

```
You are all set up to leverage the newly supported client.
The user-experience remains the unchanged. Everything else works as expected.

Huge thanks to Simon Stewart ([@shs96c](https://twitter.com/shs96c)) for making this possible with his contribution! 


