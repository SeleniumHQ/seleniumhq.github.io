---
title: "Customizing a Node"
linkTitle: "Customize Node"
weight: 4
aliases: [
"/documentation/en/grid/grid_4/customize_node/",
"/documentation/grid/advanced_features/customize_node/"
]
---


### How to customize a Node

There are times when we would like a Node to be customized to our needs. 

For e.g., we may like to do some additional setup before a session begins execution and some clean-up after a session runs to completion.

Following steps can be followed for this:

* Create a class that extends `org.openqa.selenium.grid.node.Node`
* Add a static method (this will be our factory method) to the newly created class whose signature looks like this: 

  `public static Node create(Config config)`. Here:

    * `Node` is of type `org.openqa.selenium.grid.node.Node`
    * `Config` is of type `org.openqa.selenium.grid.config.Config`
* Within this factory method, include logic for creating your new Class.
* To wire in this new customized logic into the hub, start the node and pass in the fully qualified class name of the above class to the argument `--node-implementation`

Let's see an example of all this:

1. Create a sample project using your favourite build tool (**Maven**|**Gradle**).
2. Add the below two dependencies to your sample project.
  * [org.seleniumhq.selenium/selenium-java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
  * [org.seleniumhq.selenium/selenium-grid](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-grid)
3. Add your customized Node to the project.
4. Build an [uber jar](https://imagej.net/develop/uber-jars) if you would like to just use `java -jar` to start the Node.
5. Now start the Node using the command:

```bash
java -jar custom_node-1.0-SNAPSHOT.jar node \
--node-implementation org.seleniumhq.samples.CommentatingNode
```

Here's a sample that just prints some messages on to the console whenever there's an activity of interest (session created, session deleted, a webdriver command executed etc.,) on the Node.


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

  protected DecoratedLoggingNode(Tracer tracer, URI uri, Secret registrationSecret) {
    super(tracer, new NodeId(UUID.randomUUID()), uri, registrationSecret);
  }

  public static Node create(Config config) {
    LoggingOptions loggingOptions = new LoggingOptions(config);
    BaseServerOptions serverOptions = new BaseServerOptions(config);
    URI uri = serverOptions.getExternalUri();
    SecretOptions secretOptions = new SecretOptions(config);
    Node node = LocalNodeFactory.create(config);
    DecoratedLoggingNode wrapper = new DecoratedLoggingNode(loggingOptions.getTracer(),
        uri, secretOptions.getRegistrationSecret());
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