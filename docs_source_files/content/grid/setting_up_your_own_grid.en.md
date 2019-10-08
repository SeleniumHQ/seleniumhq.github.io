---
title: "Setting up your own Grid"
weight: 3
---

To use Selenium Grid,
you need to maintain your own infrastructure for the nodes.
As this can be a cumbersome and time intense effort,
many organizations use IaaS providers
such as Amazon EC2 and Google Compute
to provide this infrastructure.

Other options include using providers such as Sauce Labs or Testing Bot
who provide a Selenium Grid as a service in the cloud.
It is certainly possible to also run nodes on your own hardware.
This chapter will go into detail about the option of running your own grid,
complete with its own node infrastructure.


## Quick start

This example will show you how to start the Selenium 2 Grid Hub,
and register both a WebDriver node and a Selenium 1 RC legacy node.
We will also show you how to call the grid from Java.
The hub and nodes are shown here running on the same machine,
but of course you can copy the selenium-server-standalone to multiple machines.

The `selenium-server-standalone` package includes the hub,
WebDriver, and legacy RC needed to run the Grid, 
_ant_ is not required anymore.
You can download the `selenium-server-standalone-.jar` from
[http://www.seleniumhq.org/download/](http://www.seleniumhq.org/download/).


### Step 1: Start the Hub

The Hub is the central point that will receive test requests
and distribute them to the right nodes.
The distribution is done on a capabilities basis,
meaning a test requiring a set of capabilities
will only be distributed to nodes offering that set or subset of capabilities.

Because a test's desired capabilities are just what the name implies, _desired_,
the hub cannot guarantee that it will locate a node
fully matching the requested desired capabilities set.

Open a command prompt
and navigate to the directory where you copied
the `selenium-server-standalone.jar` file.
You start the hub by passing the `-role hub` flag
to the standalone server:

```shell
java -jar selenium-server-standalone.jar -role hub
```

The Hub will listen to port 4444 by default.
You can view the status of the hub by opening a browser window and navigating to
[http://localhost:4444/grid/console](http://localhost:4444/grid/console).

To change the default port,
you can add the optional `-port` flag
with an integer representing the port to listen to when you run the command.
Also, all of the other options you see in the JSON config file (seen below)
are possible command-line flags.

You certainly can get by with only the simple command shown above,
but if you need more advanced configuration,
you can also specify a JSON format config file, for convenience,
to configure the hub when you start it.
You can do it like so:

```shell
java -jar selenium-server-standalone.jar -role hub -hubConfig hubConfig.json -debug
```

Below you will see an example of a `hubConfig.json` file.
We will go into more detail on how to provide node configuration files in step 2.

```json
{
  "_comment" : "Configuration for Hub - hubConfig.json",
  "host": ip,
  "maxSession": 5,
  "port": 4444,
  "cleanupCycle": 5000,
  "timeout": 300000,
  "newSessionWaitTimeout": -1,
  "servlets": [],
  "prioritizer": null,
  "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
  "throwOnCapabilityNotPresent": true,
  "nodePolling": 180000,
  "platform": "WINDOWS"}
```


### Step 2: Start the Nodes

Regardless of whether you want to run a grid with new WebDriver functionality,
or a grid with Selenium 1 RC functionality,
or both at the same time,
you use the same `selenium-server-standalone.jar` file to start the nodes:

```shell
java -jar selenium-server-standalone.jar -role node -hub http://localhost:4444
```

If a port is not specified through the `-port` flag,
a free port will be chosen. You can run multiple nodes on one machine
but if you do so, you need to be aware of your systems memory resources
and problems with screenshots if your tests take them.


#### Configuration of Node with options

As mentioned, for backwards compatibility
“wd” and “rc” roles are still a valid subset of the “node” role.
But those roles limit the types of remote connections to their corresponding API,
while “node” allows both RC and WebDriver remote connections.

Passing JVM properties (using the `-D` flag
_before the -jar argument_)
on the command line as well,
and these will be picked up and propagated to the nodes:

`-Dwebdriver.chrome.driver=chromedriver.exe`


#### Configuration of Node with JSON

You can also start grid nodes that are configured
with a JSON configuration file

```shell
java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone.jar -role node -nodeConfig node1Config.json
```

And here is an example of a `nodeConfig.json` file:

```json
{
  "capabilities": [
    {
      "browserName": "firefox",
      "acceptSslCerts": true,
      "javascriptEnabled": true,
      "takesScreenshot": false,
      "firefox_profile": "",
      "browser-version": "27",
      "platform": "WINDOWS",
      "maxInstances": 5,
      "firefox_binary": "",
      "cleanSession": true
    },
    {
      "browserName": "chrome",
      "maxInstances": 5,
      "platform": "WINDOWS",
      "webdriver.chrome.driver": "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"
    },
    {
      "browserName": "internet explorer",
      "maxInstances": 1,
      "platform": "WINDOWS",
      "webdriver.ie.driver": "C:/Program Files (x86)/Internet Explorer/iexplore.exe"
    }
  ],
  "configuration": {
    "_comment" : "Configuration for Node",
    "cleanUpCycle": 2000,
    "timeout": 30000,
    "proxy": "org.openqa.grid.selenium.proxy.WebDriverRemoteProxy",
    "port": 5555,
    "host": ip,
    "register": true,
    "hubPort": 4444,
    "maxSession": 5
  }
}
```

A note about the `-host` flag

For both hub and node, if the `-host` flag is not specified,
`0.0.0.0` will be used by default. This will bind to all the
public (non-loopback) IPv4 interfaces of the machine. If you have a special
network configuration or any component that creates extra network interfaces,
it is advised to set the `-host` flag with a value that allows the
hub/node to be reachable from a different machine.

#### Specifying the port

The default TCP/IP port used by the hub is 4444. If you need to change the port 
please use above mentioned configurations.

## Troubleshooting

### Using Log file
For advanced troubleshooting you can specify a log file to log system messages.
Start Selenium GRID hub or node with -log argument. Please see the below example:

```shell
java -jar selenium-server-standalone.jar -role hub -log log.txt
```

Use your favorite text editor to open log file (log.txt in the example above) to find 
"ERROR" logs if you get issues.

### Using `-debug` argument

Also you can use `-debug` argument to print debug logs to console.
Start Selenium Grid Hub or Node with `-debug` argument. Please see 
the below example:

```shell
java -jar selenium-server-standalone.jar -role hub -debug
```

## Warning

The Selenium Grid must be protected from external access using appropriate
firewall permissions.

Failure to protect your Grid could result in one or more of the following occurring:

* You provide open access to your Grid infrastructure
* You allow third parties to access internal web applications and files
* You allow third parties to run custom binaries

See this blog post on [Detectify](//labs.detectify.com), which gives a good 
overview of how a publicly exposed Grid could be misused: 
[Don't Leave your Grid Wide Open](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/).


## Docker Selenium
[Docker](//www.docker.com/) provides a convenient way to
provision and scale Selenium Grid infrastructure in a unit known as a container.
Containers are standardised units of software that contain everything required
to run the desired application, including all dependencies, in a reliable and repeatable
way on different machines.

The Selenium project maintains a set of Docker images which you can download
and run to get a working grid up and running quickly. Nodes are available for
both Firefox and Chrome. Full details of how to provision a grid can be found
within the [Docker Selenium](//github.com/SeleniumHQ/docker-selenium) 
repository.

### Prerequisite
The only requirement to run a Grid is to have Docker installed and working.
[Install Docker](//www.docker.com/products/docker-desktop).
