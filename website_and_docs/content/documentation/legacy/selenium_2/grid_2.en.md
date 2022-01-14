---
title: "Selenium grid 2"
linkTitle: "Grid 2"
weight: 8
description: >
  Selenium Grid 2 supported WebDriver and Selenium RC. 
  It was replaced by Grid 3 which removed RC code.
  Grid 3 was completely rewritten for the new Grid 4.
aliases: [
"/documentation/legacy/grid2/"
]
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Grid2) \
You can read our documentation for more information about [Grid 4]({{< ref "/documentation/grid" >}})

## Introduction

Grid allows you to :

* scale by distributing tests on several machines ( parallel execution )
* manage multiple environments from a central point, making it easy to run the tests against a vast combination of browsers / OS.
* minimize the maintenance time for the grid by allowing you to implement custom hooks to leverage virtual  infrastructure for instance.

## Quick Start

> This example will show you how to start the Selenium 2 Hub, and register both a WebDriver node and a Selenium 1 RC legacy node.  We’ll also show you how to call the grid from Java. The hub and nodes are shown here running on the same machine, but of course you can copy the selenium-server-standalone to multiple machines.
Note: The selenium-server-standalone package includes the Hub, WebDriver, and legacy RC needed to run the grid. Ant is not required anymore. You can download the selenium-server-standalone-`*`.jar from http://selenium-release.storage.googleapis.com/index.html
This walk-through assumes you already have Java installed.

**Step 1: Start the hub**

The Hub is the central point that will receive all the test request and distribute them the the right nodes.

Open a command prompt and navigate to the directory where you copied the selenium-server-standalone file. Type the following command:
```
java -jar selenium-server-standalone-<version>.jar -role hub
```
The hub will automatically start-up using port 4444 by default. To change the default port, you can add the optional parameter -port when you run the command.
You can view the status of the hub by opening a browser window and navigating to:
http://localhost:4444/grid/console

**Step 2: Start the nodes**

Regardless on whether you want to run a grid with new WebDriver functionality, or a grid with Selenium 1 RC functionality, or both at the same time, you use the same selenium-server-standalone jar file to start the nodes.

```
java -jar selenium-server-standalone-<version>.jar -role node  -hub http://localhost:4444/grid/register
```

Note: The port defaults to 5555 if not specified whenever the "-role" option is provided and is not hub.

For backwards compatibility "wd" and "rc" roles are still a valid subset of the "node" role. But those roles limit the types of remote connections to their corresponding API, while "node" allows both RC and WebDriver remote connections.

### Using grid to run tests
( using java as an example )
Now that the grid is in-place, we need to access the grid from our test cases. For the Selenium 1 RC nodes, you can continue to use the DefaultSelenium object and pass in the hub information:
```
Selenium selenium = new DefaultSelenium(“localhost”, 4444, “*firefox”, “http://www.google.com”);
```

For WebDriver nodes, you will need to use the **RemoteWebDriver** and the **DesiredCapabilities** object to define which browser, version and platform you wish to use.
Create the target browser capabilities you want to run the tests against:
```
DesiredCapabilities capability = DesiredCapabilities.firefox();
```
Pass that into the RemoteWebDriver object:

```
WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
```


The hub will then assign the test to a matching node.

A node matches if all the requested capabilities are met. To request specific capabilities on the grid, specify them before passing it into the WebDriver object.

```
capability.setBrowserName();
capability.setPlatform();
capability.setVersion()
capability.setCapability(,);
```

Example: A node registered with the setting:
```
 -browser  browserName=firefox,version=3.6,platform=LINUX
```
> will be a match for:
```
capability.setBrowserName(“firefox” ); 
capability.setPlatform(“LINUX”);  
capability.setVersion(“3.6”);
```
and would also be a match for

```
capability.setBrowserName(“firefox” ); 
capability.setVersion(“3.6”);
```

The capabilities that are not specified will be ignored.
If you specify capabilities that do not exist on your grid (for example, your test specifies Firefox version 4.0, but have no Firefox 4 instance) then there will be no match and the test will fail to run.

## Configuring the nodes

The node can be configured in 2 different ways; one is by specifying
command-line parameters, the other is by specifying a JSON file.

### Configuring the nodes by command line
By default, starting the node allows for concurrent use of 11
browsers...: 5 Firefox, 5 Chrome, 1 Internet Explorer.  The maximum number of concurrent tests is set to 5 by default. To change this and other browser settings, you can pass in parameters to each -browser switch (each switch represents a node based on your parameters). If you use the -browser parameter, the default browsers will be ignored and only what you specify command line will be used.
```
-browser browserName=firefox,version=3.6,maxInstances=5,platform=LINUX
```
This setting starts 5 Firefox 3.6 nodes on a Linux machine.

If your remote machine has multiple versions of Firefox you’d like to use, you can map the location of each binary to a particular version on the same machine:
```
-browser browserName=firefox,version=3.6,firefox_binary=/home/myhomedir/firefox36/firefox,maxInstances=3,platform=LINUX -browser browserName=firefox,version=4,firefox_binary=/home/myhomedir/firefox4/firefox,maxInstances=4,platform=LINUX
```
Tip: If you need to provide a space somewhere in your browser parameters, then surround the parameters with quotation marks:

```
-browser “browserName=firefox,version=3.6,firefox_binary=c:\Program Files\firefox ,maxInstances=3, platform=WINDOWS”
```

### Optional parameters

* `-port 4444` (4444 is default)
* `-host <IP | hostname>` specify the hostname or IP. usually not needed and determined automatically. For exotic network configuration, network with VPN, specifying the host might be necessary.
* `-timeout 30` (300 is default) The timeout in seconds before the hub automatically releases a node that hasn't received any requests for more than the specified number of seconds. After this time, the node will be released for another test in the queue. This helps to clear client crashes without manual intervention. To remove the timeout completely, specify -timeout 0 and the hub will never release the node.

> Note: This is NOT the WebDriver timeout for all ”wait for WebElement” type of commands.

* `-maxSession 5`	(5 is default) The maximum number of browsers that can run in parallel on the node. This is different from the maxInstance of supported browsers (Example: For a node that supports Firefox 3.6, Firefox 4.0  and Internet Explorer 8, maxSession=1 will ensure that you never have more than 1 browser running. With maxSession=2 you can have 2 Firefox tests at the same time, or 1 Internet Explorer and 1 Firefox test).

* `-browser < params >`	If -browser is not set, a node will start with 5 firefox, 1 chrome, and 1 internet explorer instance (assuming it’s on a windows box). This parameter can be set multiple times on the same line to define multiple types of browsers.
  Parameters allowed for -browser:
  browserName={android, chrome, firefox, htmlunit, internet explorer, iphone, opera}
  version={browser version}
  firefox\_binary={path to executable binary}
  chrome\_binary={path to executable binary}
  maxInstances={maximum number of browsers of this type}
  platform={WINDOWS, LINUX, MAC}

* `-registerCycle N` = how often in ms the node will try to register itself again. Allow to restart the hub without having to restart the nodes.

* Really large (>50 node) Hub installations may need to increase the jetty threads by setting -DPOOL\_MAX=512 (or larger) on the java command line.

### Configuring timeouts (Version 2.21 required)

Timeouts in the grid should normally be handled through webDriver.manage().timeouts(), which will control how the different operations time out.

To preserve run-time integrity of a grid with selenium-servers, there are two other timeout values that can be set.

On the hub, setting the -timeout command line option to "30" seconds will ensure all resources are reclaimed 30 seconds after a client crashes. On the hub you can also set -browserTimeout 60 to make the maximum time a node is willing to hang inside the browser 60 seconds. This will ensure all resources are reclaimed slightly after 60 seconds. All the nodes use these two values from the hub if they are set. Locally set parameters on a single node has precedence, it is generally recommended not to set these timeouts on the node.

The browserTimeout **should** be:
* Higher than the socket lock timeout (45 seconds)
* Generally higher than values used in webDriver.manage().timeouts(), since this mechanism is a "last line of defense".

### Configuring the nodes by JSON

> java -jar selenium-server-standalone.jar -role node -nodeConfig nodeconfig.json

A sample nodeconfig file for server version 3.x.x (>= beta4) can be seen at https://github.com/SeleniumHQ/selenium/blob/selenium-3.141.59/java/server/src/org/openqa/grid/common/defaults/DefaultNodeWebDriver.json

A sample nodeconfig file for server version 2.x.x can be seen at
https://github.com/SeleniumHQ/selenium/blob/selenium-2.53.0/java/server/src/org/openqa/grid/common/defaults/DefaultNode.json

Note: the `configuration { ... }` object in version 2.x.x has been flattened in version 3.x.x (>= beta4)

### Configuring the hub by JSON

> java -jar selenium-server-standalone.jar -role hub -hubConfig hubconfig.json

A sample hubconfig.json file can be seen at https://github.com/SeleniumHQ/selenium/blob/selenium-3.141.59/java/server/src/org/openqa/grid/common/defaults/DefaultHub.json

## Hub diagnostic messages
Upon detecting anomalous usage patterns, the hub can give the following message:
```
Client requested session XYZ that was terminated due to REASON
```

| **Reason** | **Cause/fix** |
|:-----------|:--------------|
| TIMEOUT    | The session timed out because the client did not access it within the timeout. If the client has been somehow suspended, this may happen when it wakes up |
| BROWSER\_TIMEOUT | The node timed out the browser because it was hanging for too long (parameter browserTimeout) |
| ORPHAN     | A client waiting in queue has given up once it was offered a new session |
| CLIENT\_STOPPED\_SESSION | The session was stopped using an ordinary call to stop/quit on the client. Why are you using it again?? |
| CLIENT\_GONE | The client process (_your_ code) appears to have died or otherwise not responded to our requests, intermittent network issues may also cause |
| FORWARDING\_TO\_NODE\_FAILED | The hub was unable to forward to the node. Out of memory errors/node stability issues or network problems |
| CREATIONFAILED | The node failed to create the browser. This can typically happen when there are environmental/configuration problems on the node. Try using the node directly to track problem. |
| PROXY\_REREGISTRATION | The session has been discarded because the node has re-registered on the grid (in mid-test) |

### Tips for running with grid
If your tests are running in parallel, make sure that each thread deallocates its webdriver resource independently of any other tests running on other threads. Starting 1 browser per thread at the start of the test-run and deallocating all browsers at the end is **not** a good idea. (If one test-case decides to consume abnormal amounts of time you may get timeouts on all the other tests because they're waiting for the slow test. This can be very confusing)


## Selenium Grid Platforms
(previously located: https://github.com/SeleniumHQ/selenium/wiki/Grid-Platforms)

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
