---
title: "Personalizando um Nó"
linkTitle: "Personalizando um Nó"
weight: 4
---
## Como personalizar um Nó

Há momentos em que gostaríamos de personalizar um Nó de acordo com nossas necessidades.

Por exemplo, podemos desejar fazer alguma configuração adicional antes que uma sessão comece a ser executada e executar alguma limpeza após o término de uma sessão.

Os seguintes passos podem ser seguidos para isso:

- Crie uma classe que estenda `org.openqa.selenium.grid.node.Node`.
- Adicione um método estático (este será nosso método de fábrica) à classe recém-criada, cuja assinatura se parece com esta:

  `public static Node create(Config config)`. Here:

    * `Node` é do tipo `org.openqa.selenium.grid.node.Node`
    * `Config` é do tipo `org.openqa.selenium.grid.config.Config`
* Dentro deste método de fábrica, inclua a lógica para criar sua nova classe..
* TPara incorporar esta nova lógica personalizada no hub, inicie o nó e passe o nome da classe totalmente qualificado da classe acima como argumento. `--node-implementation`

Vamos ver um exemplo de tudo isso:

### Node personalizado como um uber jar

1. Crie um projeto de exemplo usando sua ferramenta de construção favorita. (**Maven**|**Gradle**).
2. Adicione a seguinte dependência ao seu projeto de exemplo..
    * [org.seleniumhq.selenium/selenium-grid](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-grid)
3. Adicione o seu nó personalizado ao projeto.
4. Construir algo. [uber jar](https://imagej.net/develop/uber-jars) Para ser capaz de iniciar o Node usando o comando `java -jar`.
5. Agora inicie o nó usando o comando:

```bash
java -jar custom_node-server.jar node \
--node-implementation org.seleniumhq.samples.DecoratedLoggingNode
```
**Observação:** Se estiver usando o Maven como ferramenta de construção, é preferível usar o [maven-shade-plugin](https://maven.apache.org/plugins/maven-shade-plugin) em vez do [maven-assembly-plugin](https://maven.apache.org/plugins/maven-assembly-plugin) porque o plugin maven-assembly parece ter problemas para mesclar vários arquivos de Service Provider Interface (`META-INF/services`).


### Node personalizado como jar

1. Crie um projeto de exemplo usando a sua ferramenta de construção favorita (**Maven**|**Gradle**).
2. Adicione a seguinte dependência ao seu projeto de exemplo:
   * [org.seleniumhq.selenium/selenium-grid](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-grid)
3. Adicione o seu Node personalizado ao projeto.
4. Construa um arquivo JAR do seu projeto usando a sua ferramenta de construção.
5. Agora, inicie o Node usando o seguinte comando:


```bash
java -jar selenium-server-4.6.0.jar \
--ext custom_node-1.0-SNAPSHOT.jar node \
--node-implementation org.seleniumhq.samples.DecoratedLoggingNode
```
Aqui está um exemplo que apenas imprime algumas mensagens no console sempre que houver uma atividade de interesse (sessão criada, sessão excluída, execução de um comando do webdriver, etc.) no Node.

<details>
<summary>Sample customized node</summary>

```java
package org.seleniumhq.samples;

import java.net.URI;
import java.util.UUID;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.grid.config.Config;
import org.openqa.selenium.grid.data.CreateSessionRequest;
import org.openqa.selenium.grid.data.CreateSessionResponse;
import org.openqa.selenium.grid.data.NodeId;
import org.openqa.selenium.grid.data.NodeStatus;
import org.openqa.selenium.grid.data.Session;
import org.openqa.selenium.grid.log.LoggingOptions;
import org.openqa.selenium.grid.node.HealthCheck;
import org.openqa.selenium.grid.node.Node;
import org.openqa.selenium.grid.node.local.LocalNodeFactory;
import org.openqa.selenium.grid.security.Secret;
import org.openqa.selenium.grid.security.SecretOptions;
import org.openqa.selenium.grid.server.BaseServerOptions;
import org.openqa.selenium.internal.Either;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.tracing.Tracer;

public class DecoratedLoggingNode extends Node {

  private Node node;

  protected DecoratedLoggingNode(Tracer tracer, NodeId nodeId, URI uri, Secret registrationSecret) {
	super(tracer, nodeId, uri, registrationSecret);
  }

  public static Node create(Config config) {
    LoggingOptions loggingOptions = new LoggingOptions(config);
    BaseServerOptions serverOptions = new BaseServerOptions(config);
    URI uri = serverOptions.getExternalUri();
    SecretOptions secretOptions = new SecretOptions(config);

    // Refer to the foot notes for additional context on this line.
    Node node = LocalNodeFactory.create(config);

    DecoratedLoggingNode wrapper = new DecoratedLoggingNode(loggingOptions.getTracer(),
				node.getId(),
				uri,
				secretOptions.getRegistrationSecret());
    wrapper.node = node;
    return wrapper;
  }

  @Override
  public Either<WebDriverException, CreateSessionResponse> newSession(
      CreateSessionRequest sessionRequest) {
    System.out.println("Before newSession()");
    try {
      return this.node.newSession(sessionRequest);
    } finally {
      System.out.println("After newSession()");
    }
  }

  @Override
  public HttpResponse executeWebDriverCommand(HttpRequest req) {
    try {
      System.out.println("Before executeWebDriverCommand(): " + req.getUri());
      return node.executeWebDriverCommand(req);
    } finally {
      System.out.println("After executeWebDriverCommand()");
    }
  }

  @Override
  public Session getSession(SessionId id) throws NoSuchSessionException {
    try {
      System.out.println("Before getSession()");
      return node.getSession(id);
    } finally {
      System.out.println("After getSession()");
    }
  }

  @Override
  public HttpResponse uploadFile(HttpRequest req, SessionId id) {
    try {
      System.out.println("Before uploadFile()");
      return node.uploadFile(req, id);
    } finally {
      System.out.println("After uploadFile()");
    }
  }

  @Override
  public void stop(SessionId id) throws NoSuchSessionException {
    try {
      System.out.println("Before stop()");
      node.stop(id);
    } finally {
      System.out.println("After stop()");
    }
  }

  @Override
  public boolean isSessionOwner(SessionId id) {
    try {
      System.out.println("Before isSessionOwner()");
      return node.isSessionOwner(id);
    } finally {
      System.out.println("After isSessionOwner()");
    }
  }

  @Override
  public boolean isSupporting(Capabilities capabilities) {
    try {
      System.out.println("Before isSupporting");
      return node.isSupporting(capabilities);
    } finally {
      System.out.println("After isSupporting()");
    }
  }

  @Override
  public NodeStatus getStatus() {
    try {
      System.out.println("Before getStatus()");
      return node.getStatus();
    } finally {
      System.out.println("After getStatus()");
    }
  }

  @Override
  public HealthCheck getHealthCheck() {
    try {
      System.out.println("Before getHealthCheck()");
      return node.getHealthCheck();
    } finally {
      System.out.println("After getHealthCheck()");
    }
  }

  @Override
  public void drain() {
    try {
      System.out.println("Before drain()");
      node.drain();
    } finally {
      System.out.println("After drain()");
    }

  }

  @Override
  public boolean isReady() {
    try {
      System.out.println("Before isReady()");
      return node.isReady();
    } finally {
      System.out.println("After isReady()");
    }
  }
}
```
</details>

**_Notas de Rodapé:_**

No exemplo acima, a linha `Node node = LocalNodeFactory.create(config);` cria explicitamente um `LocalNode`.

Basicamente, existem 2 tipos de implementações *visíveis para o usuário* de `org.openqa.selenium.grid.node.Node` disponíveis. 

Essas classes são bons pontos de partida para aprender como criar um Node personalizado e também para compreender os detalhes internos de um Node.

* `org.openqa.selenium.grid.node.local.LocalNode` - Usado para representar um Node de execução contínua e é a implementação padrão que é usada quando você inicia um `node`. 
    * Pode ser criado chamando `LocalNodeFactory.create(config);`, onde:
      * `LocalNodeFactory` pertence a `org.openqa.selenium.grid.node.local`
      * `Config` pertence a `org.openqa.selenium.grid.config`
* `org.openqa.selenium.grid.node.k8s.OneShotNode` - Esta é uma implementação de referência especial em que o Node encerra-se graciosamente após atender a uma sessão de teste. Esta classe atualmente não está disponível como parte de nenhum artefato Maven pré-construído.
  * Você pode consultar o código-fonte [aqui](https://github.com/SeleniumHQ/selenium/blob/trunk/java/src/org/openqa/selenium/grid/node/k8s/OneShotNode.java) para entender seus detalhes internos.
  * Para construí-lo localmente, consulte [aqui](https://github.com/SeleniumHQ/selenium/blob/trunk/deploys/k8s/README.md). 
  * Pode ser criado chamando `OneShotNode.create(config)`, onde:
      * `OneShotNode` pertence a `org.openqa.selenium.grid.node.k8s`
      * `Config` pertence a `org.openqa.selenium.grid.config`
