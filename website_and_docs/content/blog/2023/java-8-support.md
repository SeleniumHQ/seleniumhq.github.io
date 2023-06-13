---
title: "Java 8 support in Selenium"
linkTitle: "Java 8 support in Selenium"
date: 2023-06-09
tags: ["selenium", "java"]
categories: ["releases"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
   On September 30, 2023, Java 11 will be the minimum version supported by Selenium.
---


“If it ain’t broke, don’t fix it” is a saying you may have heard, but sometimes 
it’s necessary to move on from our old favorites. That’s why we’re announcing that
**Selenium will stop supporting Java 8 on September 30, 2023**. This applies for
both the Java bindings and the Selenium Grid.

Selenium has long supported Java 8, but as technology evolves, so must we. One of 
the primary reasons for this change is that Java 8 reached the end of active support 
[over a year ago](https://endoflife.date/java). In addition, our default HTTP 
Client has not had a major release in several years, and a
[bug](https://github.com/SeleniumHQ/selenium/issues/9528) has been found that we can not fix.
We have decided to [move to the native Java HTTP Client](/blog/2022/using-java11-httpclient/),
but it requires using Java 11 or greater. The sooner we make this change, the sooner 
we can avoid dealing with this issue.

Our new minimum version will be Java 11. September 30, 2023 is also the end of 
active support for Java 11. However, we want to take a cautious and conservative path 
forward, and not force our users to make the big jump from Java 8 to Java 17, as we 
understand the community might need longer to move to that version. We will revisit 
this topic in the future to announce the plan to support Java 17 as a minimum version.

We understand that this change may require some of our users to make adjustments, but 
we believe that it’s a necessary step for the continued growth of Selenium. Please 
take some time to check your infrastructure and ensure you’re running on Java 11 or 
higher. We understand that some may be hesitant or may find it difficult to make 
the switch, but we believe it will pay off in the long run.

Please let us know your questions, concerns, and feedback through our 
[community chat](https://www.selenium.dev/support/#ChatRoom).

Happy testing!
