---
title: "History of Grid Platforms"
linkTitle: "Grid Platforms"
weight: 10
description: >
    Information for working with platform names in Grid 2.
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Grid-Platforms) \
You can read more about [Grid 2]({{< ref "grid_2.md" >}})

## Selenium Grid Platforms

This section describes the PLATFORM option used in configuring Selenium Grid Nodes and [[DesiredCapabilities](DesiredCapabilities)] object.

### History of Platforms

When requesting a new WebDriver session from the Grid, user can specify the [[DesiredCapabilities](DesiredCapabilities)] of the remote browser. Things such as the browser name, version, and platform are among the list of options that can be specified by the test. Specifying desired.

The following code demonstrates the DesiredCapability of Internet Explorer, version 9, on Windows XP platform:

```
	[[DesiredCapabilities]] capability = DesiredCapabilities.internetExplorer();
	capability.setVersion("8");
	capability.setPlatform(Platform.XP);
	WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
```

The request for a new session with specified DesiredCapability is sent to the Grid Hub, which will look through all of the registered nodes to see if any of them match the specification given by the test. If no node matches the specification, a CapabilityNotPresentOnTheGridException will be returned.

It is a common misconception that the PLATFORM determines the ability to choose the Operating System on which the new session will be created. In this situation, platform and operating system are not the same, thus specifying the platform to "Windows 2003 Server" will not allow you to choose between a Windows XP, Vista, and 2003 server. This misconception can be born from platforms such as Mac OSX and Linux, where the name of the platform matches the name of the Operating System.

In case of Selenium Grid, platform refers to the underlying interactions between the Driver Atoms and the web browser. Mac OSX and Linux based Operating Systems (Centos, Ubuntu, Debian, etc..) have a relatively stable communication with the web browsers such as Firefox and Chrome. Thus the platform names are simple to understand, as seen in the example bellow:

```
   capability.setPlatform(Platform.MAC);   //Set platform to OSX
   capability.setPlatform(Platform.LINUX); // Set platform to Linux based systems
```

The prior to release of Vista, Windows based Operating Systems only had one platform, shown here:

```
	capability.setPlatform(Platform.WINDOWS); //Set platform to Windows
```

However, with the introduction of UAC in Windows Vista, there were major changes done to the underlying interactions between WebDriver and Internet Explorer. To work around the UAC constrains a new platform was added to nodes with Windows based Operating systems:

```
	capability.setPlatform(Platform.VISTA); //Set platform to VISTA
```

With the release of Windows 8, another major overhaul happened in how the WebDriver communicates with Internet Explorer, thus a new platform was added for Windows 8 based nodes:

```
	capability.setPlatform(Platform.WIN8); //Set platform to Windows 8
```

Similar story happened with introduction of Windows 8.1, in this example the platform is set to Windows 8.1:

```
	capability.setPlatform(Platform.WIN8_1); //Set platform to Windows 8.1
```


### Operating System Platforms
The following list demonstrates some of the Operating Systems, and what Platform they are part of:

**MAC****All OSX Operating Systems** LINUX
Centos
Ubuntu
**UNIX****Solaris****BSD** XP
Windows Server 2003
Windows XP
Windows NT
**VISTA****Windows Vista****Windows 2008 Server****Windows 7** WIN8
Windows 2012 Server
Windows 8
**WIN8\_1****Windows 8.1**

### Families
Different platforms are grouped into "Families" of platform. For example, Win8 and XP platforms are a part of the WINDOWS family. Similarly ANDROID and LINUX are part of the UNIX family.

### Choosing Platform and Platform Family
When setting a platform on the [[DesiredCapabilities](DesiredCapabilities)] object, we can set an individual platform or family of platforms. For example:

```
  	capability.setPlatform(Platform.VISTA); //Will return a node with Windows Vista or 2008 Server or Windows 7 Operating System.
  	capability.setPlatform(Platform.XP);   //Will return a node with Windows XP or 2003 Server or Windows 2000 Professional Operating System.   
  	capability.setPlatform(Platform.WINDOWS); //Will return a node with ANY Windows Operating System
```


### More Information
For more information on the latest platforms, please view this file:

org.openqa.selenium.Platform.java
