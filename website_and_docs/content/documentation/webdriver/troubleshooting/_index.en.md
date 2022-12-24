---
title: "Troubleshooting Assistance"
linkTitle: "Troubleshooting"
weight: 20
description: >
  How to get manage WebDriver problems.
---

It is not always obvious the root cause of errors in Selenium.

1. The most common Selenium-related error is a result of poor synchronization. 
Read about [Waiting Strategies]({{< ref "../waits" >}}). If you aren't sure if it
is a synchronization strategy you can try *temporarily* hard coding a large sleep
where you see the issue, and you'll know if adding an explicit wait can help.

2. Note that many errors that get reported to the project are actually caused by
issues in the underlying drivers that Selenium sends the commands to. You can rule
out a driver problem by executing the command in multiple [browsers]({{< ref "../browsers/" >}}).

3. If you have questions about how to do things, check out the [Support options](/support/)
for ways get assistance.

4. If you think you've found a problem with Selenium code, go ahead and file a 
[Bug Report](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+)
on GitHub.


