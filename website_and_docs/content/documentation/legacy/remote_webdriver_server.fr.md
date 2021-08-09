---
title: "Le serveur Remote WebDriver"
linkTitle: "Le serveur Remote WebDriver"
weight: 5
aliases: ["/documentation/fr/legacy_docs/remote_webdriver_server/"]
---

Le serveur fonctionnera toujours sur la machine avec le navigateur que vous souhaitez
tester. Le serveur peut être utilisé à partir de la ligne de commande ou via du code
configuration.

## Démarrage du serveur à partir de la ligne de commande

Une fois que vous avez téléchargé `selenium-server-standalone-{VERSION}.jar`,
placez-le sur l'ordinateur avec le navigateur que vous souhaitez tester. Ensuite, à partir de
le répertoire avec le pot, exécutez ce qui suit:

```shell
java -jar selenium-server-standalone-{VERSION}.jar
```

## Considérations pour exécuter le serveur

L’appelant doit terminer correctement chaque session, appelant
soit `Selenium#stop()` ou `WebDriver#quit`.

Le 'Selenium-Server' conserve des journaux en mémoire pour chaque session en cours,
qui sont effacés lorsque `Selenium#stop()` ou `WebDriver#quit` est appelé. Si
vous oubliez de mettre fin à ces sessions, votre serveur peut perdre de la mémoire. Si
vous gardez des sessions extrêmement longues, vous devrez probablement
arrêtez/quittez de temps en temps (ou augmentez la mémoire avec l'option -Xmx jvm).

## Timeouts (à partir de la version 2.21)

Le serveur a deux délais d'expiration différents, qui peuvent être définis comme suit:

```shell
java -jar selenium-server-standalone-{VERSION}.jar -timeout=20 -browserTimeout=60
```

* browserTimeout
  * Contrôle la durée pendant laquelle le navigateur est autorisé à se bloquer (valeur en secondes).
* timeout
  * Contrôle la durée pendant laquelle le client est autorisé à partir
      avant la récupération de la session (valeur en secondes).

La propriété système `selenium.server.session.timeout`
n'est plus pris en charge à partir de la version 2.21.

Veuillez noter que le `browserTimeout`
est conçu comme un mécanisme de temporisation de sauvegarde
lorsque le mécanisme de temporisation ordinaire échoue,
qui devrait être utilisé principalement dans des environnements de grille/serveur
pour garantir que les processus bloqués/perdus ne restent pas trop longtemps,
polluant l'environnement d'exécution.

## Configuration du serveur par programmation

En théorie, le processus est aussi simple que de mapper le `DriverServlet` à
une URL, mais il est également possible d'héberger la page dans un format léger
conteneur, tel que Jetty configuré entièrement en code. Étapes à suivre pour ce faire
suivre.

* Téléchargez `selenium-server.zip` et décompressez. 
* Placez les JAR sur le CLASSPATH. 
* Créez une nouvelle classe appelée `AppServer`. Ici, j'utilise
Jetty, vous devrez donc [télécharger](//www.eclipse.org/jetty/download.html)
cela aussi:

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

