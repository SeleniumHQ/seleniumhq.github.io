---
title: "WebDriver remoto - servidor"
weight: 5
---

El servidor siempre se ejecutará en la máquina con el navegador que deseas
probar. El servidor se puede usar desde la línea de comandos o mediante 
configuración de código.


## Iniciar el servidor desde la línea de comando

Una vez que hayas descargado `selenium-server-standalone-{VERSION}.jar`,
colócalo en la computadora con el navegador que deseas probar. Entonces,
desde el directorio con el jar, ejecuta lo siguiente:

```shell
java -jar selenium-server-standalone-{VERSION}.jar
```

## Consideraciones para ejecutar el servidor

Se espera que quien inicie la sesión termine cada sesión correctamente, llamando
ya sea `Selenium#stop()` o `WebDriver#quit`.

El servidor de Selenium mantiene registros en memoria
para cada sesión en curso, que se borran cuando se invoca
`Selenium#stop()` o `WebDriver#quit`.
Si olvidas finalizar estas sesiones, tu servidor puede perder memoria. 
Si mantienes sesiones extremadamente largas, 
probablemente necesitarás detener/salir de vez en cuando 
(o también aumentar la memoria con la opción -Xmx jvm).


## Timeouts (desde la version 2.21)

El servidor tiene dos timeouts diferentes, 
que se pueden configurar de la siguiente manera:

```shell
java -jar selenium-server-standalone-{VERSION}.jar -timeout=20 -browserTimeout=60
```

* browserTimeout
  * Controla cuánto tiempo se permite colgar el navegador (valor en segundos).
* timeout
  * Controla cuánto tiempo se permite que el cliente se vaya
   antes de reclamar la sesión (valor en segundos).

La propiedad del sistema `selenium.server.session.timeout`
ya no es compatible a partir de 2.21.

Ten en cuenta que el `browserTimeout`
está pensado como un mecanismo de timeout de respaldo
cuando falla el mecanismo de timeout ordinario,
que debe usarse principalmente en entornos de red/servidor
para garantizar que los procesos bloqueados/perdidos no permanezcan 
por mucho tiempo,contaminando el entorno de ejecución.


## Configuración del servidor de manera programática

En teoría, el proceso es tan simple como mapear el `DriverServlet` a
una URL, pero también es posible alojar la página desde en un contenedor ligero,
como Jetty configurado completamente en código. Pasos para hacer esto
a continuación.

* Descargar `selenium-server.zip` y descomprimirlos
* Poner los JAR en el CLASSPATH
* Crear una nueva clase llamada `AppServer`. Estoy usando
Jetty, por lo que deberás [descargar](//www.eclipse.org/jetty/download.html)
eso también:

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

