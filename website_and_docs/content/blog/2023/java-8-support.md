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


As exciting as new technology is, sometimes it’s necessary to move on from our old favorites. 
That’s why we’re announcing that **Selenium will stop supporting Java 8 on September 30, 2023**.

Selenium has supported Java 8 for a while now, but as technology evolves, so must we. One of 
the primary reasons for this change is the end-of-life support for Java 8, which has already 
[taken place](https://endoflife.date/java). In addition, a 
[bug](https://github.com/SeleniumHQ/selenium/issues/9528) while using a third party Java 8 
HTTP client motivated us to [implement a Java 11 HTTP client](blog/2022/using-java11-httpclient/).

Our new minimum version will be Java 11. We understand that September 30, 2023 is also the
end of active support for Java 11. However, we want to take the sensible path and not force our
users to make the big jump from Java 8 to Java 17, as we understand the community might need
longer to move to that version. We will revisit this topic in the future to announce the plan
to support Java 17 as a minimum version.

We understand that this change may require some of our users to make adjustments, but we believe 
that it’s a necessary step for the continous growth of Selenium. Please take some time to check 
your infrastructure and ensure Java 11 or higher is running. We understand that some may be 
hesitant to make the switch, we believe it will pay off in the long run. 

Please let us know your questions, concerns, and feedback through our 
[community chat](https://www.selenium.dev/support/#ChatRoom).

Happy testing!
