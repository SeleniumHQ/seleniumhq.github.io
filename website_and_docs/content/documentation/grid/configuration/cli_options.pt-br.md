---
title: "CLI Options"
linkTitle: "CLI Options"
weight: 2
description: All Grid components configuration CLI options in detail.
aliases: [
"/pt-br/documentation/grid/configuring_components/cli_options/"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i>
   Page being translated from
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Different sections are available to configure a Grid. Each section has options can be configured
through command line arguments.

A complete description of the component to section mapping can be seen below.

{{% pageinfo color="primary" %}}
Note that this documentation could be outdated if an option was modified or added
but has not been documented yet. In case you bump into this situation, please check
the ["Config help"]({{< ref "help.md" >}}) section and feel free to send us a
pull request updating this page.
{{% /pageinfo %}}


## Sections

<table class="table table-bordered text-md-center d-md-table-cell">
<thead>
  <tr>
    <th></th>
    <th>Standalone</th>
    <th>Hub</th>
    <th>Node</th>
    <th>Distributor</th>
    <th>Router</th>
    <th>Sessions</th>
    <th>SessionQueue</th>
  </tr>
</thead>
<tbody>
  <tr>
    <th><a href="#distributor">Distributor</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#docker">Docker</a></th>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#events">Events</a></th>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
  </tr>
  <tr>
    <th><a href="#logging">Logging</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
  </tr>
  <tr>
    <th><a href="#network">Network</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#node">Node</a></th>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#router">Router</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#relay">Relay</a></th>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#server">Server</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
  </tr>
  <tr>
    <th><a href="#sessionqueue">SessionQueue</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
  </tr>
  <tr>
    <th><a href="#sessions">Sessions</a></th>
    <td></td>
    <td></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
  </tr>
</tbody>
</table>

### Distributor

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--healthcheck-interval` | int | `120` | How often, in seconds, will the health check run for all Nodes. This ensures the server can ping all the Nodes successfully. |
| ``--distributor`` | uri | `http://localhost:5553` | Url of the distributor. |
| `--distributor-host` | string | `localhost` | Host on which the distributor is listening. |
| `--distributor-implementation` | string | `org.openqa.selenium.grid.distributor.local.LocalDistributor` | Full class name of non-default distributor implementation |
| `--distributor-port` | int | `5553` | Port on which the distributor is listening. |
| `--reject-unsupported-caps` | boolean | `false` | Allow the Distributor to reject a request immediately if the Grid does not support the requested capability. Rejecting requests immediately is suitable for a Grid setup that does not spin up Nodes on demand. |
| `--slot-matcher` | string | `org.openqa.selenium.grid.data.DefaultSlotMatcher` | Full class name of non-default slot matcher to use. This is used to determine whether a Node can support a particular session. |
| `--slot-selector` | string | `org.openqa.selenium.grid.distributor.selector.DefaultSlotSelector` | Full class name of non-default slot selector. This is used to select a slot in a Node once the Node has been matched. |

### Docker

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--docker-assets-path` | string | `/opt/selenium/assets` | Absolute path where assets will be stored |
| `--docker-` | string[] | `selenium/standalone-firefox:latest '{"browserName": "firefox"}'` | Docker configs which map image name to stereotype capabilities (example `-D selenium/standalone-firefox:latest '{"browserName": "firefox"}') |
| `--docker-devices` | string[] | `/dev/kvm:/dev/kvm` | Exposes devices to a container. Each device mapping declaration must have at least the path of the device in both host and container separated by a colon like in this example: /device/path/in/host:/device/path/in/container |
| `--docker-host` | string | `localhost` | Host name where the Docker daemon is running |
| `--docker-port` | int | `2375` | Port where the Docker daemon is running |
| `--docker-url` | string | `http://localhost:2375` | URL for connecting to the Docker daemon |
| `--docker-video-image` | string | `selenium/video:latest` | Docker image to be used when video recording is enabled |

### Events

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--bind-bus` | boolean | `false` | Whether the connection string should be bound or connected. <br> When true, the component will be bound to the Event Bus (as in the Event Bus will also be started by the component, typically by the Distributor and the Hub). <br> When false, the component will connect to the Event Bus. |
| `--events-implementation` | string | `org.openqa.selenium.events.zeromq.ZeroMqEventBus` | Full class name of non-default event bus implementation |
| `--publish-events` | string | `tcp://*:4442` | Connection string for publishing events to the event bus |
| `--subscribe-events` | string | `tcp://*:4443` | Connection string for subscribing to events from the event bus |

### Logging

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--http-logs` | boolean |  `false` | Enable http logging. Tracing should be enabled to log http logs. |
| `--log-encoding` | string |  `UTF-8` | Log encoding |
| `--log` | string | Windows path example : <br>`'\path\to\file\gridlog.log'` <br> or <br> `'C:\path\path\to\file\gridlog.log'`<br><br>Linux/Unix/MacOS path example :<br> `'/path/to/file/gridlog.log'` | File to write out logs. Ensure the file path is compatible with the operating system's file path. |
| `--log-level` | string | `“INFO”` | Log level. Default logging level is INFO. Log levels are described here https://docs.oracle.com/javase/7/docs/api/java/util/logging/Level.html |
| `--plain-logs` | boolean | `true` | Use plain log lines |
| `--structured-logs` | boolean | `false` | Use structured logs |
| `--tracing` | boolean | `true` | Enable trace collection |
| `--log-timestamp-format` | string | `HH:mm:ss.SSS` | Allows the configure log timestamp format |

### Network

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--relax-checks` | boolean | `false` | Relax checks on origin header and content type of incoming requests, in contravention of strict W3C spec compliance. |

### Node

| Option | Type | Value/Example | Description |
|---|---|---|---|---|
| `--detect-drivers` | boolean | `true` | Autodetect which drivers are available on the current system, and add them to the Node. |
| `--driver-configuration` | string[] | `display-name="Firefox Nightly" max-sessions=2 webdriver-path="/usr/local/bin/geckodriver" stereotype='{"browserName": "firefox", "browserVersion": "86", "moz:firefoxOptions": {"binary":"/Applications/Firefox Nightly.app/Contents/MacOS/firefox-bin"}}'` | List of configured drivers a Node supports. It is recommended to provide this type of configuration through a toml config file to improve readability |
| `--driver-factory` | string[] | `org.openqa.selenium.example.LynxDriverFactory '{"browserName": "lynx"}'` | Mapping of fully qualified class name to a browser configuration that this matches against. |
| `--driver-implementation` | string[] | `"firefox"` | Drivers that should be checked. If specified, will skip autoconfiguration. |
| `--node-implementation` | string | `"org.openqa.selenium.grid.node.local.LocalNodeFactory"` | Full classname of non-default Node implementation. This is used to manage a session's lifecycle. |
| `--grid-url` | string | `https://grid.example.com` | Public URL of the Grid as a whole (typically the address of the Hub or the Router) |
| `--heartbeat-period` | int | `60` | How often, in seconds, will the Node send heartbeat events to the Distributor to inform it that the Node is up. |
| `--max-sessions` | int | `8` | Maximum number of concurrent sessions. Default value is the number of available processors. |
| `--override-max-sessions` | boolean | `false` | The # of available processors is the recommended max sessions value (1 browser session per processor). Setting this flag to true allows the recommended max value to be overwritten. Session stability and reliability might suffer as the host could run out of resources. |
| `--register-cycle` | int | `10` | How often, in seconds, the Node will try to register itself for the first time to the Distributor. |
| `--register-period` | int | `120` | How long, in seconds, will the Node try to register to the Distributor for the first time. After this period is completed, the Node will not attempt to register again. |
| `--session-timeout` | int | `300` | Let X be the session-timeout in seconds. The Node will automatically kill a session that has not had any activity in the last X seconds. This will release the slot for other tests. |
| `--vnc-env-var`| string | `START_XVFB` | Environment variable to check in order to determine if a vnc stream is available or not. |
| `--no-vnc-port`| int | `7900` | If VNC is available, sets the port where the local noVNC stream can be obtained |
| `--drain-after-session-count`| int | `1` | Drain and shutdown the Node after X sessions have been executed. Useful for environments like Kubernetes. A value higher than zero enables this feature. |
| `--hub`| string | `http://localhost:4444` | The address of the Hub in a Hub-and-Node configuration. Can be a hostname or IP address (`hostname`), in which case the Hub will be assumed to be `http://hostname:4444`, the `--grid-url` will be the same `--publish-events` will be `tcp://hostname:4442` and `--subscribe-events` will be `tcp://hostname:4443`. If `hostname` contains a port number, that will be used for `--grid-url` but the URIs for the event bus will remain the same. Any of these default values may be overridden but setting the correct flags. If the hostname has  a protocol (such as `https`) that will be used too. |
| `--enable-cdp`| boolean | `true` | Enable CDP proxying in Grid. A Grid admin can disable CDP if the network doesnot allow websockets. True by default. |

### Relay

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--service-url` | string | `http://localhost:4723` | URL for connecting to the service that supports WebDriver commands like an Appium server or a cloud service. |
| `--service-host` | string | `localhost` | Host name where the service that supports WebDriver commands is running |
| `--service-port` | int | `4723` | Port where the service that supports WebDriver commands is running |
| `--service-status-endpoint` | string | `/status` | Optional, endpoint to query the WebDriver service status, an HTTP 200 response is expected |
| `--service-configuration` | string[] | `max-sessions=2 stereotype='{"browserName": "safari", "platformName": "iOS", "appium:platformVersion": "14.5"}}'` | Configuration for the service where calls will be relayed to. It is recommended to provide this type of configuration through a toml config file to improve readability. |

### Router

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--password` | string | `myStrongPassword` | Password clients must use to connect to the server. Both this and the username need to be set in order to be used. |
| `--username` | string | `admin` | User name clients must use to connect to the server. Both this and the password need to be set in order to be used. |


### Server

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--allow-cors` | boolean | `true` | Whether the Selenium server should allow web browser connections from any host |
| `--host` | string | `localhost` | Server IP or hostname: usually determined automatically. |
| `--bind-host` | boolean | `true` | Whether the server should bind to the host address/name, or only use it to" report its reachable url. Helpful in complex network topologies where the server cannot report itself with the current IP/hostname but rather an external IP or hostname (e.g. inside a Docker container) |
| `--https-certificate` | path | `/path/to/cert.pem` | Server certificate for https. Get more detailed information by running "java -jar selenium-server.jar info security" |
| `--https-private-key` | path | `/path/to/key.pkcs8` | Private key for https. Get more detailed information by running "java -jar selenium-server.jar info security" |
| `--max-threads` | int | `24` | Maximum number of listener threads. Default value is: (available processors) * 3. |
| `--port` | int | `4444` | Port to listen on. There is no default as this parameter is used by different components, for example, Router/Hub/Standalone will use 4444 and Node will use 5555. |

### SessionQueue

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--sessionqueue` | uri | `http://localhost:1237` | Address of the session queue server. |
| `-sessionqueue-host` | string | `localhost` | Host on which the session queue server is listening. |
| `--sessionqueue-port` | int | `1234` | Port on which the session queue server is listening. |
| `--session-request-timeout` | int | `300` | Timeout in seconds. A new incoming session request is added to the queue. Requests sitting in the queue for longer than the configured time will timeout. |
| `--session-retry-interval` | int | `5` | Retry interval in seconds. If all slots are busy, new session request will be retried after the given interval. |

### Sessions

| Option | Type | Value/Example | Description |
|---|---|---|---|
| `--sessions` | uri | `http://localhost:1234` | Address of the session map server. |
| `--sessions-host` | string | `localhost` | Host on which the session map server is listening. |
| `--sessions-port` | int | `1234` | Port on which the session map server is listening. |


## Configuration examples

All the options mentioned above can be used when starting the Grid components. They are a good
way of exploring the Grid options, and trying out values to find a suitable configuration.

{{% pageinfo color="primary" %}}
We recommend the use of [Toml files]({{< ref "toml_options.md" >}}) to configure a Grid.
Configuration files improve readability, and you can also check them in source control.

When needed, you can combine a Toml file configuration with CLI arguments.
{{% /pageinfo %}}


### Command-line flags

To pass config options as command-line flags, identify the valid options for the component
and follow the template below.

```
java -jar selenium-server-<version>.jar <component> --<option> value
```

#### Standalone, setting max sessions and main port

```
java -jar selenium-server-<version>.jar standalone --max-sessions 4 --port 4444
```

#### Hub, setting a new session request timeout, a main port, and disabling tracing

```
java -jar selenium-server-<version>.jar hub --session-request-timeout 500 --port 3333 --tracing false
```

#### Node, with 4 max sessions, with debug(fine) log, 7777 as port, and only with Firefox and Edge

```
java -jar selenium-server-<version>.jar node --max-sessions 4 --log-level "fine" --port 7777 --driver-implementation "firefox" --driver-implementation "edge"
```

#### Distributor, setting Session Map server url, Session Queue server url, and disabling bus

```
java -jar selenium-server-<version>.jar distributor --sessions http://localhost:5556 --sessionqueue http://localhost:5559 --bind-bus false
```

#### Setting custom capabilities for matching specific Nodes
**Important:** Custom capabilities need to be set in the configuration in all Nodes. They also
need to be included always in every session request.

##### Start the Hub
```
java -jar selenium-server-<version>.jar hub
```

##### Start the Node A with custom cap set to `true`
```
java -jar selenium-server-<version>.jar node --detect-drivers false --driver-configuration display-name="Chrome (custom capability true)" max-sessions=1 stereotype='{"browserName":"chrome","gsg:customcap":true}' --port 6161
```

##### Start the Node B with custom cap set to `false`
```
java -jar selenium-server-<version>.jar node --detect-drivers false --driver-configuration display-name="Chrome (custom capability true)" max-sessions=1 stereotype='{"browserName":"chrome","gsg:customcap":false}' --port 6262
```

##### Matching Node A
```java
ChromeOptions options = new ChromeOptions();
options.setCapability("gsg:customcap", true);
WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
driver.get("https://selenium.dev");
driver.quit();
```

Set the custom capability to `false` in order to match the Node B.
