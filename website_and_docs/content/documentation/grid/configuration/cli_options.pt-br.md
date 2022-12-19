---
title: "Opções CLI"
linkTitle: "Opções CLI"
weight: 2
description: Todas os detalhes das opções CLI de cada componente Grid.
aliases: [
"/pt-br/documentation/grid/configuring_components/cli_options/"
]
---

Diferentes secções estão disponíveis para configurar uma Grid. Cada secção tem opções que podem ser configuradas
através de opções CLI.

Pode ver abaixo um mapeamento entre o componente e a secção respectiva.



{{% pageinfo color="primary" %}}
Note que esta documentação pode estar desactualizada se uma opção foi adicionada ou modificada,
mas ainda não ter havido oportunidade de actualizar a documentação.
Caso depare com esta situação, verifique a secção ["ajuda de configuração"]({{< ref "help.md" >}})
e esteja à vontade para nos enviar um pull request com alterações a esta página.
{{% /pageinfo %}}


## Secções

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

| Opção | Tipo | Valor/Exemplo | Descrição |
|---|---|---|---|
| `--healthcheck-interval` | int | `120` | Tempo em segundos em que se verifica o estado dos Nodes. Isto garante que o servidor consecontactar cada um dos Nodes com sucesso. |
| ``--distributor`` | uri | `http://localhost:5553` | Url do Distributor. |
| `--distributor-host` | string | `localhost` | Host onde o Distributor está à escuta. |
| `--distributor-implementation` | string | `org.openqa.selenium.grid.distributor.local.LocalDistributor` | Nome completo da class para uma implementação não padrão  do Distributor. |
| `--distributor-port` | int | `5553` | Porta onde o Distributor está à escuta. |
| `--reject-unsupported-caps` | boolean | `false` | Permitir que o Distributor rejeite imediatamente um pedido de sessão se a Grid não suportar a capacidade pedida. Esta configuração é a ideal para Grid que não inicie Nodes a pedido. |
| `--slot-matcher` | string | `org.openqa.selenium.grid.data.DefaultSlotMatcher` | Nome completo da class para uma implementação não padrão do comparador de slots. Isto é usado para determinar se um Node pode suportar uma sessão em particular. |
| `--slot-selector` | string | `org.openqa.selenium.grid.distributor.selector.DefaultSlotSelector` | Nome completo da class para uma implementação não padrão do selector de slots. Isto é usado para selecionar um slot no Node caso tenha sido "matched". |

### Docker

| Opção | Tipo | Valor/Exemplo | Descrição |
|---|---|---|---|
| `--docker-assets-path` | string | `/opt/selenium/assets` | Absolute path where assets will be stored |
| `--docker-` | string[] | `selenium/standalone-firefox:latest '{"browserName": "firefox"}'` | Docker configs which map image name to stereotype capabilities (example `-D selenium/standalone-firefox:latest '{"browserName": "firefox"}') |
| `--docker-devices` | string[] | `/dev/kvm:/dev/kvm` | Exposes devices to a container. Each device mapping declaration must have at least the path of the device in both host and container separated by a colon like in this example: /device/path/in/host:/device/path/in/container |
| `--docker-host` | string | `localhost` | Host name where the Docker daemon is running |
| `--docker-port` | int | `2375` | Port where the Docker daemon is running |
| `--docker-url` | string | `http://localhost:2375` | URL for connecting to the Docker daemon |
| `--docker-video-image` | string | `selenium/video:latest` | Docker image to be used when video recording is enabled |

### Events

| Opção | Tipo | Valor/Exemplo | Descrição |
|---|---|---|---|
| `--bind-bus` | boolean | `false` | Whether the connection string should be bound or connected. <br> When true, the component will be bound to the Event Bus (as in the Event Bus will also be started by the component, typically by the Distributor and the Hub). <br> When false, the component will connect to the Event Bus. |
| `--events-implementation` | string | `org.openqa.selenium.events.zeromq.ZeroMqEventBus` | Full class name of non-default event bus implementation |
| `--publish-events` | string | `tcp://*:4442` | Connection string for publishing events to the event bus |
| `--subscribe-events` | string | `tcp://*:4443` | Connection string for subscribing to events from the event bus |

### Logging

| Opção | Tipo | Valor/Exemplo | Descrição |
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

| Opção | Tipo | Valor/Exemplo | Descrição |
|---|---|---|---|
| `--relax-checks` | boolean | `false` | Relax checks on origin header and content type of incoming requests, in contravention of strict W3C spec compliance. |

### Node

| Opção | Tipo | Valor/Exemplo | Descrição |
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
| `--downloads-path`| string | `/usr/downloads` | The default location wherein all browser triggered file downloads would be available to be retrieved from. This is usually the directory that you configure in your browser as the default location for storing downloaded files. |


### Relay

| Opção | Tipo | Valor/Exemplo | Descrição |
|---|---|---|---|
| `--service-url` | string | `http://localhost:4723` | URL for connecting to the service that supports WebDriver commands like an Appium server or a cloud service. |
| `--service-host` | string | `localhost` | Host name where the service that supports WebDriver commands is running |
| `--service-port` | int | `4723` | Port where the service that supports WebDriver commands is running |
| `--service-status-endpoint` | string | `/status` | Optional, endpoint to query the WebDriver service status, an HTTP 200 response is expected |
| `--service-configuration` | string[] | `max-sessions=2 stereotype='{"browserName": "safari", "platformName": "iOS", "appium:platformVersion": "14.5"}}'` | Configuration for the service where calls will be relayed to. It is recommended to provide this type of configuration through a toml config file to improve readability. |

### Router

| Opção | Tipo | Valor/Exemplo | Descrição |
|---|---|---|---|
| `--password` | string | `myStrongPassword` | Password clients must use to connect to the server. Both this and the username need to be set in order to be used. |
| `--username` | string | `admin` | User name clients must use to connect to the server. Both this and the password need to be set in order to be used. |


### Server

| Opção | Tipo | Valor/Exemplo | Descrição |
|---|---|---|---|
| `--allow-cors` | boolean | `true` | Whether the Selenium server should allow web browser connections from any host |
| `--host` | string | `localhost` | Server IP or hostname: usually determined automatically. |
| `--bind-host` | boolean | `true` | Whether the server should bind to the host address/name, or only use it to" report its reachable url. Helpful in complex network topologies where the server cannot report itself with the current IP/hostname but rather an external IP or hostname (e.g. inside a Docker container) |
| `--https-certificate` | path | `/path/to/cert.pem` | Server certificate for https. Get more detailed information by running "java -jar selenium-server.jar info security" |
| `--https-private-key` | path | `/path/to/key.pkcs8` | Private key for https. Get more detailed information by running "java -jar selenium-server.jar info security" |
| `--max-threads` | int | `24` | Maximum number of listener threads. Default value is: (available processors) * 3. |
| `--port` | int | `4444` | Port to listen on. There is no default as this parameter is used by different components, for example, Router/Hub/Standalone will use 4444 and Node will use 5555. |

### SessionQueue

| Opção | Tipo | Valor/Exemplo | Descrição |
|---|---|---|---|
| `--sessionqueue` | uri | `http://localhost:1237` | Address of the session queue server. |
| `-sessionqueue-host` | string | `localhost` | Host on which the session queue server is listening. |
| `--sessionqueue-port` | int | `1234` | Port on which the session queue server is listening. |
| `--session-request-timeout` | int | `300` | Timeout in seconds. A new incoming session request is added to the queue. Requests sitting in the queue for longer than the configured time will timeout. |
| `--session-retry-interval` | int | `5` | Retry interval in seconds. If all slots are busy, new session request will be retried after the given interval. |

### Sessions

| Opção | Tipo | Valor/Exemplo | Descrição |
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


#### Specifying path from where downloaded files can be retrieved

At times a test may need to access files that were downloaded by it on the Node. To retrieve 
such files, following can be done.

##### Start the Hub
```
java -jar selenium-server-<version>.jar hub
```

##### Start the Node with downloads path specified
```
java -jar selenium-server-<version>.jar node --downloads-path /usr/downloads
```
##### Important information when dowloading a file:

* The endpoint to `GET` from is `/session/<sessionId>/se/file?filename=`
* The session needs to be active in order for the command to work.
* The response blob contains two keys,
    * `filename` - Same as what was specified in the request.
    * `contents` - Base64 encoded zipped contents of the file.
* The file contents are Base64 encoded and they need to be unzipped.

##### Sample that retrieves the downloaded file

Assuming the downloaded file is named `my_file.pdf`, and using `curl`, the 
file could be downloaded with the following command:

```bash
curl -X GET "http://localhost:4444/session/<sessionId>/se/file?filename=my_file.pdf"
```

Below is an example in Java that shows how to download a file named `my_file.pdf`.

```java
import static org.openqa.selenium.remote.http.Contents.string;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;

public class DownloadsSample {

  public static void main(String[] args) throws InterruptedException, IOException {
    // Make sure the following directory exists on your machine
    File dirToCopyTo = new File("/usr/downloads/file");
    // Assuming the Grid is running locally.
    URL gridUrl = new URL("http://localhost:4444");
    RemoteWebDriver driver = new RemoteWebDriver(gridUrl, firefoxOptions());
    // This public website resets the available files for dowload on a daily basis, 
    // check the name of the file that will be downloaded and replace it below.
    driver.get("http://the-internet.herokuapp.com/download");
    WebElement element = driver.findElement(By.cssSelector(".example a"));
    element.click();

    // The download happens in a remote Node, which makes difficult to know when the file 
    // has been completely downloaded. For demonstration purposes, this example uses a 
    // 10 second sleep which should be enough time for a file to be downloaded. 
    // We strongly recommend to avoid hardcoded sleeps, and ideally, to modify your 
    // application under test so it offers a way to know when the file has been completely 
    // downloaded. 
    TimeUnit.SECONDS.sleep(10);

    HttpRequest request = new HttpRequest(HttpMethod.GET, String.format("/session/%s/se/file", driver.getSessionId()));
    request.addQueryParameter("filename", "my_file.pdf");
    try (HttpClient client = HttpClient.Factory.createDefault().createClient(gridUrl)) {
      HttpResponse response = client.execute(request);
      Map<String, Object> map = new Json().toType(string(response), Json.MAP_TYPE);
      // The returned map would contain 2 keys,
      // filename - This represents the name of the file (same as what was provided by the test)
      // contents - Base64 encoded String which contains the zipped file.
      String encodedContents = map.get("contents").toString();

      //The file contents would always be a zip file and has to be unzipped.
      Zip.unzip(encodedContents, dirToCopyTo);
    } finally {
      driver.quit();
    }
  }

  private static FirefoxOptions firefoxOptions() {
    FirefoxOptions options = new FirefoxOptions();
    // Options specific for Firefox to avoid prompting a dialog for downloads. They might 
    // change in the future, so please refer to the Firefox documentation for up to date details.
    options.addPreference("browser.download.manager.showWhenStarting", false);
    options.addPreference("browser.helperApps.neverAsk.saveToDisk",
        "images/jpeg, application/pdf, application/octet-stream");
    options.addPreference("pdfjs.disabled", true);
    return options;
  }
}
```

