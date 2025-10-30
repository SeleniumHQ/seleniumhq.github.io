---
title: "自定义Node"
linkTitle: "自定义Node"
weight: 4
---


## 如何自定义节点

有时候我们希望根据自己的需求自定义Node。

例如，我们可能希望在会话开始执行前做一些额外的设置，在会话完成后做一些清理工作。

可以按照以下步骤进行：

* 创建一个类，继承自 `org.openqa.selenium.grid.node.Node`
* 在新建的类中添加一个静态方法（工厂方法），其签名如下：

  `public static Node create(Config config)`。其中：

    * `Node` 类型为 `org.openqa.selenium.grid.node.Node`
    * `Config` 类型为 `org.openqa.selenium.grid.config.Config`
* 在工厂方法中，包含创建新类的逻辑。
* 要将自定义逻辑接入 hub，启动 node 时通过参数 `--node-implementation` 传入上述类的完整类名。

下面我们来看一个完整的示例：

### 作为 uber jar 的自定义 Node

1. 使用你喜欢的构建工具 (**Maven**|**Gradle**) 创建一个示例项目。
2. 在项目中添加如下依赖：
    * [org.seleniumhq.selenium/selenium-grid](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-grid)
3. 将你的自定义 Node 添加到项目中。
4. 构建一个 [uber jar](https://imagej.net/develop/uber-jars)，以便通过 `java -jar` 命令启动 Node。
5. 现在使用如下命令启动 Node：

```bash
java -jar custom_node-server.jar node \
--node-implementation org.seleniumhq.samples.DecoratedLoggingNode
```

**注意：** 如果你使用 Maven 作为构建工具，
建议使用 [maven-shade-plugin](https://maven.apache.org/plugins/maven-shade-plugin) 
而不是 [maven-assembly-plugin](https://maven.apache.org/plugins/maven-assembly-plugin)，
因为 maven-assembly-plugin 在合并多个 Service Provider Interface 文件 (`META-INF/services`) 
时可能存在问题。

### 作为普通 jar 的自定义 Node

1. 使用你喜欢的构建工具 (**Maven**|**Gradle**) 创建一个示例项目。
2. 在项目中添加如下依赖：
    * [org.seleniumhq.selenium/selenium-grid](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-grid)
3. 将你的自定义 Node 添加到项目中。
4. 使用构建工具构建项目 jar 包。
5. 现在使用如下命令启动 Node：

```bash
java -jar selenium-server-4.6.0.jar \
--ext custom_node-1.0-SNAPSHOT.jar node \
--node-implementation org.seleniumhq.samples.DecoratedLoggingNode
```
下面是一个示例：当Node有相关活动（会话创建、会话删除、WebDriver 命令执行等）时，仅在控制台打印一些消息。

<details>
<summary>自定义Node示例</summary>

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

  protected DecoratedLoggingNode(Tracer tracer, NodeId nodeId, URI uri, Secret registrationSecret, Duration sessionTimeout) {
    super(tracer, nodeId, uri, registrationSecret, sessionTimeout);
  }

  public static Node create(Config config) {
    LoggingOptions loggingOptions = new LoggingOptions(config);
    BaseServerOptions serverOptions = new BaseServerOptions(config);
    URI uri = serverOptions.getExternalUri();
    SecretOptions secretOptions = new SecretOptions(config);
    NodeOptions nodeOptions = new NodeOptions(config);
    Duration sessionTimeout = nodeOptions.getSessionTimeout();

    // Refer to the foot notes for additional context on this line.
    Node node = LocalNodeFactory.create(config);

    DecoratedLoggingNode wrapper = new DecoratedLoggingNode(loggingOptions.getTracer(),
        node.getId(),
        uri,
        secretOptions.getRegistrationSecret(),
        sessionTimeout);
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

**_注释：_**

在上述示例中，`Node node = LocalNodeFactory.create(config);` 这一行显式创建了一个 `LocalNode`。

`org.openqa.selenium.grid.node.Node` 主要有两种*面向用户的实现*。

这些类是学习如何构建自定义 Node 以及了解 Node 内部机制的良好起点。

* `org.openqa.selenium.grid.node.local.LocalNode` - 用于表示长时间运行的 Node，也是默认实现。启动 `node` 时会自动接入。
    * 可通过 `LocalNodeFactory.create(config);` 创建，其中：
      * `LocalNodeFactory` 属于 `org.openqa.selenium.grid.node.local`
      * `Config` 属于 `org.openqa.selenium.grid.config`
* `org.openqa.selenium.grid.node.k8s.OneShotNode` - 这是一个特殊的参考实现，节点在服务完一个测试会话后会自动关闭。该类目前未包含在任何预构建的 maven 包中。
  * 你可以在 [这里](https://github.com/SeleniumHQ/selenium/blob/trunk/java/src/org/openqa/selenium/grid/node/k8s/OneShotNode.java) 查看源码。
  * 参考 [这里](https://github.com/SeleniumHQ/selenium/blob/trunk/deploys/k8s/README.md) 了解本地构建方法。
  * 可通过 `OneShotNode.create(config)` 创建，其中：
      * `OneShotNode` 属于 `org.openqa.selenium.grid.node.k8s`
      * `Config` 属于 `org.openqa.selenium.grid.config`
