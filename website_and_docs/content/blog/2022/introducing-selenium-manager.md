---
title: "Introducing Selenium Manager"
linkTitle: "Introducing Selenium Manager"
date: 2022-11-04
tags: ["selenium", "manager", "chromedriver", "geckodriver", "edgedriver"]
categories: ["releases"]
author: Boni García ([@boni_gg](https://twitter.com/boni_gg))
description: >
   Get a working environment to run Selenium out of the box with a new beta feature!
---

Most people's first experience with Selenium ends up with an error message like this one:

```
java.lang.IllegalStateException: The path to the driver executable must be set by the webdriver.chrome.driver 
system property; for more information, see https://chromedriver.chromium.org/. The latest version can be 
downloaded from https://chromedriver.chromium.org/downloads
```

 Then they have to search the web for instructions on what to do with the drivers they download. 

### Selenium: now with batteries included!

The Selenium project wants to improve the user experience, and one of the first steps is to help all users to
simplify how they set up their environment. Configuring browser drivers has been for many years a task which 
users need to perform in order to have a working environment to run Selenium.

Setting up a browser driver once is not that complicated, but as browser release cycles got shorter, and now
we have a new Chrome/Firefox/Edge version every 4-6 weeks, the task of keeping the browser driver in sync with
the browser version is not that easy anymore.

Selenium Manager is a new tool that helps to get a working environment to run Selenium out of the box. Beta 1 
of Selenium Manager will configure the browser drivers for Chrome, Firefox, and Edge if they are not present 
on the `PATH`.

To run a Selenium test with Selenium 4.6, you only need to have Chrome, Firefox, or Edge installed. If you 
already have browser drivers installed, this feature will be ignored. If you'd like to help us test it, though, 
delete your drivers or remove your third party driver manager and things should still "just work." If they don't, 
please file a [bug report](https://github.com/SeleniumHQ/selenium/issues).

Future releases of Selenium Manager will eventually even download browsers if necessary.

### Inspired by Open Source and the Selenium Community

Selenium Manager is not a completely new solution. Over the years, several third-party projects in the Selenium 
ecosystem emerged. Such as: [WebDriverManager](https://bonigarcia.dev/webdrivermanager/) for Java, 
[webdriver-manager](https://pypi.org/project/webdriver-manager/) for Python, 
[webdrivers](https://github.com/titusfortner/webdrivers) for Ruby, and
[WebDriverManager.Net](https://github.com/rosolko/WebDriverManager.Net) for C#. 

All these projects served as an 
[inspiration]({{< ref "../../documentation/webdriver/troubleshooting/errors/driver_location.md#driver-management-libraries" >}}) 
and as a clear sign that the community needed this feature to be 
built-in Selenium. In addition, a [survey](https://www.selenium.dev/blog/2021/selenium-survey-results/) 
done on January 2021 showed that most Selenium users want to get rid of the driver management 
problem. Plus, the fact that the 
[driver installation](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/driver_location/) page is 
by far the most visited one in the Selenium documentation.

### Selenium Manager in detail 

Selenium Manager is a CLI (Command-Line Interface) tool developed in Rust to allow cross platform execution. 
On its beta 1 version, Selenium Manager is invoked transparently by the Selenium bindings when no browser 
driver is detected on the `PATH` or no third party driver manager is being used. 

You can also use Selenium Manager without the Selenium bindings. While we plan to do formal releases in the
future, for now, the binaries can be found directly on the 
[Selenium repository](https://github.com/SeleniumHQ/selenium/tree/trunk/common/manager). Check the different
parameters and options by running the following command:
```
$ ./selenium-manager --help
```

A quick example showing how `ChromeDriver` gets configured can be seen below:
```
$ ./selenium-manager --browser chrome
INFO	/home/boni/.cache/selenium/chromedriver/linux64/106.0.5249.61/chromedriver
```

If you maintain a WebDriver based project and would like to use Selenium Manager as well,
join us in our [community channels](/support/#ChatRoom) and we would be happy to help. 
Also, if you are interested in contributing, for detailed instructions and information 
check the project's [README](https://github.com/SeleniumHQ/selenium/tree/trunk/rust).

### Next steps

Selenium Manager is still under development, features will be added and bugs fixed along with each release.
However, you as a member of the Selenium community are key part on the future success of this new tool. Please
report ideas or bugs through our [issue tracker](https://github.com/SeleniumHQ/selenium/issues) and join
the conversation through our [community channels](/support/#ChatRoom). Looking forward to your feedback!


Happy testing!

