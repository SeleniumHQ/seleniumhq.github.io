---
title: "Protecting unsecured Selenium Grids against SeleniumGreed"
linkTitle: "Protecting unsecured Selenium Grids"
date: 2024-07-31
tags: ["selenium"]
categories: ["general"]
author: David Burns [@automatedtester](https://www.linkedin.com/in/theautomatedtester/)
description: >
   With an ongoing attack, called SeleniumGreed, on Selenium Grids, we recommend you keep your grid secure.
---

Over the last week there have been stories going around about crypto miners abusing unsecured Selenium Grids by
injecting code in the session create to download and start up crypto miners. This came out thanks to the work from
Wiz about an attack they have called [SeleniumGreed](https://www.wiz.io/blog/seleniumgreed-cryptomining-exploit-attack-flow-remediation-steps).
This issue can be abused on most versions of Selenium but there appears to be a lot of effort going into abusing
Selenium Grid 3.14. Please upgrade as some of the security items have been added since then.

Selenium Grid by default doesn't have any authentication as the assumption has always been that we want you to put
this behind a secure network to prevent people from abusing your resources. There are ways that you can secure the grid,
and we have documentation available on how to do this in our [help section](https://www.selenium.dev/documentation/grid/configuration/help/#security). You can see more details if you run

```
java -jar selenium-server-<version>.jar info security
```

Another way to combat this is to use a cloud provider to run your Selenium Grid. We have numerous vendors that sponsor us
so have a look at our [sponsors](https://www.selenium.dev/sponsors/) page. If you need help, after reading the [help section](https://www.selenium.dev/documentation/grid/configuration/help/#security)
please come into our [chat rooms](https://www.selenium.dev/support/#ChatRoom) and we will try guide you through making your grid more secure.
