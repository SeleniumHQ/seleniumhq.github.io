---
title: "Remote WebDriver Server"
linkTitle: "Remote WebDriver Server"
weight: 5
aliases: ["/documentation/de/legacy_docs/remote_webdriver_server/"]
---

Der Server wird immer auf der Maschine ausgeführt, der den gewünschten 
Browser installiert hat, der für den Test genutzt werden soll. Der Server
kann entweder mittels Eingabebeaufforderung oder mittels Programmcode
gestartet werden.

## Starten des Servers über die Eingabebeaufforderung

Nachdem `selenium-server-standalone-{VERSION}.jar` heruntergeladen wurde,
platzieren Sie die Datei auf dem Rechner mit dem entsprechenden Browser.
Führen Sie folgenden Befehl in dem Order aus, der die jar-Datei beinhaltet:

```shell
java -jar selenium-server-standalone-{VERSION}.jar
```

## Überlegungen zum Betrieb des Servers

Vom aufrufenden Programm wird erwartet, dass jede Session ordnungsgemäß
beendet wird, entweder mit `Selenium#stop()` oder `WebDriver#quit`.

Der Selenium-Server speichert die Logs für jede laufende Sitzung im 
Hauptspeicher, diese werden gelöscht sobald `Selenium#stop()` oder `WebDriver#quit` 
aufgerufen wird. Wenn vergessen wird, die Sitzungen zu schließen, kann dies ein 
Memoryleak verursachen. Falls extrem lange Testdurchführungen
geplant sind, kann es notwendig sein, von Zeit zu Zeit den Server zu stoppen und
erneut zu starten (oder den zugewiesenen Speicher mit der jvm Option -Xmx zu erhöhen).

## Timeouts (ab Version 2.21)

Der Server hat zwei unterschiedliche Timeouts, die wie folgt festgelegt werden können:

```shell
java -jar selenium-server-standalone-{VERSION}.jar -timeout=20 -browserTimeout=60
```

* browserTimeout
  * Legt fest wie lange es dem Browser ermöglicht wird zu reagieren (in Sekunden) 
* timeout
  * Gibt an wie lange es dem Client erlaubt ist inaktiv zu sein, bevor die Session
  wieder freigegeben wird. (in Sekunden).

Die Systemeigenschaft (system property) `selenium.server.session.timeout`
wird ab Version 2.21 nicht mehr unterstützt.

Zu Beachten ist, dass das `browserTimeout` als Backup Timeout
Mechanismus gedacht ist, die hauptsächlich in Grid/Server Umgebungen genutzt 
werden sollten, falls herkömmliche Timeout-Mechanismen scheitern. Dies dient
dazu das abgestürzte oder "vergessene" Prozesse nicht ewig aktiv bleiben
und Prozesse geschlossen werden.

## Konfiguration des Servers mit Programmcode

Theoretisch muss lediglich das `DriverServlet` auf eine URL verlinkt werden, es 
ist jedoch auch möglich, dies in einem schlanken Container (z.B. Jetty) zu verwenden,
der vollständig im Code konfiguriert wird.

* Downloade `selenium-server.zip` und extrahiere die Datei.
* Füge die JARs dem CLASSPATH hinzu.
* Erstelle eine eine Klasse mit dem Namen `AppServer`.

Im folgenden Beispiel wird Jetty verwendet, diesen kann man hier 
[downloaden](//www.eclipse.org/jetty/download.html).
 

```java
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.security.SslSocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import javax.servlet.Servlet;
import java.io.File;

import org.openqa.selenium.remote.server.DriverServlet;

public class AppServer {
  private Server server = new Server();

  public AppServer() throws Exception {
    WebAppContext context = new WebAppContext();
    context.setContextPath("");
    context.setWar(new File("."));
    server.addHandler(context);

    context.addServlet(DriverServlet.class, "/wd/*");

    SelectChannelConnector connector = new SelectChannelConnector();
    connector.setPort(3001);
    server.addConnector(connector);

    server.start();
  }
}
```

