---
title: "Servidor do WebDriver remoto"
linkTitle: "Servidor do WebDriver remoto"
weight: 6
aliases: [
"/documentation/pt-br/legacy_docs/remote_webdriver_server/",
"/pt-br/documentation/legacy/remote_webdriver_server/"
]
---

O servidor sempre será executado na máquina com o navegador que você deseja
testar. O servidor pode ser usado a partir da linha de comando ou por meio de configuração de código.


## Iniciando o servidor a partir da linha de comando

Depois de fazer o download do `selenium-server-standalone-{VERSION}.jar`,
coloque-o no computador com o navegador que deseja testar. Então, a partir
do diretório com o jar, execute o seguinte:

```shell
java -jar selenium-server-standalone-{VERSION}.jar
```

## Considerações para executar o servidor

O chamador deve encerrar cada sessão adequadamente, chamando
ou `Selenium#stop()` ou `WebDriver#quit`.

O selenium-server mantém registros na memória para cada sessão em andamento,
que são apagados quando `Selenium#stop()` ou `WebDriver#quit` é chamado. E se
você se esquecer de encerrar essas sessões, seu servidor pode vazar memória. E se
você mantém sessões de duração extremamente longa, você provavelmente precisará
parar / sair de vez em quando (ou aumentar a memória com a opção -Xmx jvm).


## Timeouts (a partir da versão 2.21)

O servidor tem dois timeouts diferentes, que podem ser definidos da seguinte forma:

```shell
java -jar selenium-server-standalone-{VERSION}.jar -timeout=20 -browserTimeout=60
```

* browserTimeout
   * Controla por quanto tempo o navegador pode travar (valor em segundos).
* timeout
   * Controla por quanto tempo o cliente pode ficar fora
   antes que a sessão seja recuperada (valor em segundos).

A propriedade do sistema `selenium.server.session.timeout`
não é mais compatível a partir da versão 2.21.

Observe que o `browserTimeout`
destina-se a ser um mecanismo de timeout de backup
quando o mecanismo de timeout comum falha,
e deve ser usado principalmente em ambientes de Grid / servidor
para garantir que processos travados / perdidos não permaneçam por muito tempo
poluindo o ambiente de execução.


## Configurando o servidor programaticamente

Em teoria, o processo é tão simples quanto mapear o `DriverServlet` para
uma URL, mas também é possível hospedar a página em um formato leve de
container, como Jetty, configurado inteiramente em código.

* Baixe o `selenium-server.zip` e descompacte.
* Coloque os JARs no CLASSPATH.
* Crie uma nova classe chamada `AppServer`.
Aqui, estamos usando Jetty, então você precisará [baixar](//www.eclipse.org/jetty/download.html)
isso também:

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

