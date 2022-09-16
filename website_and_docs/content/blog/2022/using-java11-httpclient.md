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

Currently, Selenium uses [AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client). AsyncHttpClient is an open-source library built on top of Netty. It allows the execution of HTTP requests and responses asynchronously. Additionally also provides WebSocket support. Hence it is a good fit. 

### Why does Selenium want to move to Java 11+ HTTP Client?
While AsyncHttpClient provides the required functionality, the open-source project is not been actively maintained since June 2021. It  coincides with the fact that Java 11+ provides a built-in HTTP and WebSocket client. Selenium can leverage it replace AsyncHttpClient.
Currently, Selenium Java supports Java  8. Selenium has plans to upgrade the minimum version supported to Java 11. However, it is a sizeable effort. Aligning it with major releases and accompanied announcements  is crucial to ensure the user experience is intact.

A step towards the move is to leverage the Selenium Java codebase's bifurcation. Selenium Java codebase consists of the Grid code (server) and Selenium client code (client binding). Updating the server code to use Java 11+ HTTP client requires updating the Selenium supported minimum Java version to 11 (which will happen in due time).

So as a first step towards this move, the Selenium 4.5.0 client supports the Java 11+ HTTP client!

### Using Java 11+ HTTP Client in Selenium 4.5.0
#### Prerequisites: 
* Project configured to use Java 11+
* Using Selenium client 4.5.0 

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


