---
title: "Configure Grid"
linkTitle: "Configure Grid"
weight: 1
aliases: ["/documentation/en/grid/grid_4/configuring_components/configure_grid/"]
---

Selenium Grid configuration is divided into sections. Each section has options. Each option has a value. 

The Grid can be run in Standalone, Hub-Node, or fully distributed mode. 
Based on each component's role in the Grid, it has a set of valid configuration sections. 

Component to section mapping is described in the table below.

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
    <th>Sessionqueue</th>
  </tr>
</thead>
<tbody>
  <tr>
    <th><a href="#distributor">Distributor</th>
    <td>Y</td>
    <td>Y</td>
    <td></td>
    <td>Y</td>
    <td>Y</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#docker">Docker</th>
    <td>Y</td>
    <td></td>
    <td>Y</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#events">Events</th>
    <td></td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td></td>
    <td>Y</td>
    <td>Y</td>
  </tr>
  <tr>
    <th><a href="#logging">Logging</th>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
  </tr>
  <tr>
    <th><a href="#network">Network</th>
    <td>Y</td>
    <td>Y</td>
    <td></td>
    <td></td>
    <td>Y</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#node">Node</th>
    <td>Y</td>
    <td></td>
    <td>Y</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#router">Router</th>
    <td>Y</td>
    <td>Y</td>
    <td></td>
    <td></td>
    <td>Y</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#server">Server</th>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
  </tr>
  <tr>
    <th><a href="#sessionqueue">Sessionqueue</th>
    <td>Y</td>
    <td>Y</td>
    <td></td>
    <td>Y</td>
    <td>Y</td>
    <td></td>
    <td>Y</td>
  </tr>
  <tr>
    <th><a href="#sessions">Sessions</th>
    <td></td>
    <td></td>
    <td></td>
    <td>Y</td>
    <td>Y</td>
    <td>Y</td>
    <td></td>
  </tr>
</tbody>
</table>

### Distributor

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| grid-model | string | "org.openqa.selenium.grid.distributor.GridModel" | Full classname of non-default grid model. This is used to store the states of all the registered Nodes. |
| healthcheck-interval | int | 120 | How often, in seconds, will the health check run for all Nodes. This ensures the server can ping all the Nodes successfully. |
| host | uri | "http://localhost:5553" | Url of the distributor. |
| hostname | string | "localhost" | Host on which the distributor is listening. |
| implementation | string | "org.openqa.selenium.grid.distributor.local.LocalDistributor" | Full class name of non-default distributor implementation |
| port | int | 5553 | Port on which the distributor is listening. |
| reject-unsupported-caps | boolean | false | Allow the Distributor to reject a request immediately if the Grid does not support the requested capability. Rejecting requests immediately is suitable for a Grid setup that does not spin up Nodes on demand. |
| slot-matcher | string | "org.openqa.selenium.grid.data.DefaultSlotMatcher" | Full class name of non-default slot matcher to use. This is used to determine whether a Node can support a particular session. |
| slot-selector | string | "org.openqa.selenium.grid.distributor.selector.DefaultSlotSelector" | Full class name of non-default slot selector. This is used to select a slot in a Node once the Node has been matched. |

### Docker

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| assets-path | string | “/opt/selenium/assets” | Absolute path where assets will be stored |
| configs | list of strings | ["selenium/standalone-firefox:latest", "{\"browserName\": \"firefox\"}"] | Docker configs which map image name to stereotype capabilities (example `-D selenium/standalone-firefox:latest '{"browserName": "firefox"}') |
| host | string | "localhost" | Host name where the docker daemon is running |
| port | int | 2375 | Port where the docker daemon is running |
| url | string | “unix:/var/run/docker.sock” | URL for connecting to the docker daemon |
| video-image | string | "selenium/video:latest" | Docker image to be used when video recording is enabled |

### Events 

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| bind | boolean | false | Whether the connection string should be bound or connected. <br> When true, the component will be bound to the Event Bus (as in the Event Bus will also be started by the component, typically by the Distributor and the Hub). <br> When false, the component will connect to the Event Bus. |
| implementation | string | “org.openqa.selenium.events.zeromq.ZeroMqEventBus” | Full class name of non-default event bus implementation |
| publish | string | "tcp://*:4442" | Connection string for publishing events to the event bus |
| subscribe | string | "tcp://*:4443" | Connection string for subscribing to events from the event bus |

### Logging

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| http-logs | boolean |  false | Enable http logging. Tracing should be enabled to log http logs. |
| log-encoding | string |  “UTF-8” | Log encoding |
| log-file | string | Windows path example : <br>'\\path\to\file\gridlog.log'<br>OR<br>'C:\path\path\to\file\gridlog.log'<br><br>Linux/Unix/MacOS path example :<br>'/path/to/file/gridlog.log' | File to write out logs. Ensure the file path is compatible with the operating system's file path. |
| log-level | string | “INFO” | Log level. Default logging level is INFO. Log levels are described here https://docs.oracle.com/javase/7/docs/api/java/util/logging/Level.html |
| plain-logs | boolean | true | Use plain log lines |
| structured-logs | boolean | false | Use structured logs |
| tracing | boolean | true | Enable trace collection |

### Network 

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| relax-checks | boolean | false | Relax checks on origin header and content type of incoming requests, in contravention of strict W3C spec compliance. |

### Node

| Option | Datatype | Value/Example | Description |
|---|---|---|---|---|
| detect-drivers | boolean | true | Autodetect which drivers are available on the current system, and add them to the Node. |
| drivers-configuration | List of strings | [node.driver-configuration]<br>display-name = "Firefox Nightly"<br>webdriver-executable = "/usr/local/bin/chromedriver"<br>max-sessions = 2<br>stereotype = "{"browserName": "firefox", "browserVersion": "86", "moz:firefoxOptions": {"binary":"/Applications/Firefox Nightly.app/Contents/MacOS/firefox-bin"}}" | List of configured drivers a Node supports. It is recommended to provide this type of configuration through a toml config file to improve readability. Command-line example: --drivers-configuration display-name="Firefox Nightly" max-sessions=2 webdriver-path="/usr/local/bin/geckodriver" stereotype='{"browserName": "firefox", "browserVersion": "86", "moz:firefoxOptions": {"binary":"/Applications/Firefox Nightly.app/Contents/MacOS/firefox-bin"}}' |
| driver-factories | List of strings | ["org.openqa.selenium.example.LynxDriverFactory '{"browserName": "lynx"}'] | Mapping of fully qualified class name to a browser configuration that this matches against. --driver-factory org.openqa.selenium.example.LynxDriverFactory '{"browserName": "lynx"}' | 
| driver-implementation | List of strings | ["firefox", "chrome"] | Drivers that should be checked. If specified, will skip autoconfiguration. Command-line example: -I "firefox" -I "chrome" |
| grid-url | string | "https://grid.example.com" | Public URL of the Grid as a whole (typically the address of the Hub or the Router) |
| heartbeat-period | int | 60 | How often, in seconds, will the Node send heartbeat events to the Distributor to inform it that the Node is up. |
| max-sessions | int | 8 | Maximum number of concurrent sessions. Default value is the number of available processors. |
| override-max-session | boolean | false | The # of available processos is the recommended max sessions value (1 browser session per processor). Setting this flag to true allows the recommended max value to be overwritten. Session stability and reliability might suffer as the host could run out of resources. |
| register-cycle | int | 10 | How often, in seconds, the Node will try to register itself for the first time to the Distributor. |
| register-period | int | 120 | How long, in seconds, will the Node try to register to the Distributor for the first time. After this period is completed, the Node will not attempt to register again. |
| session-timeout | int | 300 | Let X be the session-timeout in seconds. The Node will automatically kill a session that has not had any activity in the last X seconds. This will release the slot for other tests. |
| vnc-env-var| string | "START_XVFB" | Environment variable to check in order to determine if a vnc stream is available or not. |

### Router

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| password | string | “hunter” | Password clients must use to connect to the server. Both this and the username need to be set in order to be used. |
| username | string | “admin” | User name clients must use to connect to the server. Both this and the password need to be set in order to be used. |


### Server 

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| allow-cors | boolean | true | Whether the Selenium server should allow web browser connections from any host |
| host | string | "localhost"<br>"127.0.0.0" | Server IP or hostname: usually determined automatically. |
| https-certificate | path | "/path/to/cert.pem" | Server certificate for https. Get more detailed information by running "java -jar selenium-server.jar info security" |
| https-private-key | path | "/path/to/key.pkcs8" | Private key for https. Get more detailed information by running "java -jar selenium-server.jar info security" |
| max-threads | int | 24 | Maximum number of listener threads. Default value is: (available processors) * 3. |
| port | int | 4444 | Port to listen on. There is no default as this parameter is used by different components, for example, Router/Hub/Standalone will use 4444 and Node will use 5555. |

### Sessionqueue

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| host | uri | "http://localhost:1237" | Address of the session queue server. |
| hostname | string | "localhost" | Host on which the session queue server is listening. |
| port | int | 1234 | Port on which the session queue server is listening. |
| session-request-timeout | int | 300 | Timeout in seconds. A new incoming session request is added to the queue. Requests sitting in the queue for longer than the configured time will timeout. |
| session-retry-interval | int | 5 | Retry interval in seconds. If all slots are busy, new session request will be retried after the given interval. |

### Sessions

| Option | Datatype | Value/Example | Description |
|---|---|---|---|
| host | uri | "http://localhost:1234" | Address of the session map server. |
| hostname | string | "localhost" | Host on which the session map server is listening. |
| port | int | 1234 | Port on which the session map server is listening. |

## Configuring Grid
Selenium Grid can be configured in multiple ways. Either via command-line flags or a config file or both.
 
### Command-line flags 

To pass config options as command-line flags identify the valid options for the component and follow the template below.

```
java -jar selenium-server-4.0.0-<version>.jar <component> --<option> value
```

Example: 

```
java -jar selenium-server-4.0.0-rc-1.jar standalone --max-sessions 4 --port 4444
```

```
java -jar selenium-server-4.0.0-rc-1.jar hub --session-request-timeout 500 --port 3333 --tracing false
```

```
java -jar selenium-server-4.0.0-rc-1.jar node --max-sessions 4 --log-level "fine" --port 4444 --driver-implementation "firefox" --driver-implementation "edge"
```

```
java -jar selenium-server-4.0.0-rc-1.jar distributor --sessions http://localhost:5556 --sessionqueue http://localhost:5559 --bind-bus false
```

Config file will help improve readability if many options need to be passed. 

### Config file

Selenium Grid uses [TOML](https://github.com/toml-lang/toml) format for config files. 
The config file consists of sections and each section has options and its respective value(s).
Refer TOML documentation](https://toml.io/en/) for detailed usage guidance.
In case of parsing errors, validate the config using [TOML linter ](https://www.toml-lint.com/) .

To use a config file for configuration identify the valid section and respective options for the component and follow the template below.

Template
```
[section1]
option1="value"

[section2]
option2=["value1","value2"]
option3=true

```

Example: 

config.toml

```
[server]
port = 4449

[node]
detect-drivers = false
max-sessions = 1

[docker]
configs = ["selenium/standalone-chrome:latest", "{\"browserName\": \"chrome\", \"browserVersion\": \"91\", \"max-sessions\" : \"1\"}", "selenium/standalone-firefox:latest", "{\"browserName\": \"firefox\", \"browserVersion\": \"89\"}"]
host = "localhost"
port = 2375
url = "unix:/var/run/docker.sock"
video-image = "selenium/video:latest"
```
The TOML config file can be passed in the command line while running the server as follows:

```
java -jar selenium-server-4.0.0-<version>.jar <component> --config /path/to/file/<file-name>.toml
```

Example: 

```
java -jar selenium-server-4.0.0-rc-1.jar standalone --config /path/to/file/config.toml
```

Command-line options and a config file can be used together as well.

```
java -jar selenium-server-4.0.0-rc-1.jar standalone --driver-implementation "chrome" --config /path/to/file/config.toml
```
