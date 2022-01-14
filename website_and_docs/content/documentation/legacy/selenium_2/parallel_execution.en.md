---
title: "Limitations of scaling up tests in Selenium 2"
linkTitle: "Parallel Execution"
weight: 11
description: >
  Summary of additional constraints that arise when running Selenium2 in parallel.
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Scaling-WebDriver)

## Running parallel Selenium2

This page tries to summarize additional constraints that arise when running Selenium2 in parallel.

### WebDriver instantiation
While an individual WebDriver instance cannot be shared among threads, it is easy to create multiple WebDriver instances.

### Ephemeral sockets
There is a general problem of TCP/IP v4, where the TCP/IP stack uses ephemeral ports when making a connection between two sockets. The typical symptom of this is that connection failures start appearing after a short time of running, often a minute or two. The message will vary somewhat but it always appears after some time, and if you reduce the number of browsers it will eventually work fine.

[Wikipedia on Ephemeral ports](http://en.wikipedia.org/wiki/Ephemeral_port) or a quick google of "ephemeral sockets <your os name>" will tell you what your current OS delivers and how to set it.

Currently (2.13.0) it seems like a firefox running at full blast consumes something in the range of 2000 ephemeral ports per firefox; your mileage will vary here. This means you can
run out of ephemeral port on Windows XP with as litttle as 2 browsers, maybe even 1 if you for instance iterate extermly quickly .


#### Will it be fixed ?
The solution to the ephemeral socket problem is HTTP1.1 keep alive on the connections. Firefox does not support keep-alive as of version 2.13.0.

#### Things that are fixed
* The java client.
* Selenium server ("rc").
* Selenium grid hub & nodes
* The ruby bindings (see notes in [RubyBindings](RubyBindings.md)).
* The IE driver.
* ChromeDriver

The means you can use the java client to scale out to remote boxes running selenium server and never have any problems on the central build server. You may need to solve socket problems on the remote boxes though.

#### Microsoft Windows
If you are using the old versions of Windows (<=2003, inc XP) you should not be
waiting for port usage to get low enough to fit in this space. That may simply never happen, although some combinations probably will. See http://support.microsoft.com/kb/196271 on how to adjust it.

If you for technical reasons cannot adjust the port range on your Windows machine you will not be able to run more than 2-3 firefox browsers.

### Avoiding the socket lock
Starting new browsers between each test class/test method is slow, and the socket lock also uses Ephemeral sockets, worsening the problem described above.

If you're using a suite-less test setup (like many JUnit4 users), you often start/stop the browsers in @BeforeClass/@AfterClass methods. Another option is to start the browsers in @BeforeClass and use something like JUnit/TestNG run listeners to shut down all the browsers at the end of the test run.  Maven surefire supports run listeners for both JUnit and TestNG.

(TODO: Strategies to disable the socket lock and manage the ports yourself)

### Native events

Due to a shared file in the native events logic, the firefox driver should probably not be using native events when running concurrently. (Watch [this issue](http://code.google.com/p/selenium/issues/detail?id=1326)).
