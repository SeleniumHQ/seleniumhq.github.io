---
title: "远程WebDriver服务器"
linkTitle: "远程WebDriver服务器"
weight: 6
aliases: [
"/documentation/zh-cn/legacy_docs/remote_webdriver_server/",
"/zh-cn/documentation/legacy/remote_webdriver_server/"
]
---

服务器将始终在安装了待测浏览器的机器上运行. 
可以从命令行或通过代码配置来使用服务器.


## 从命令行启动服务器

下载 `selenium-server-standalone-{VERSION}.jar` 后, 
将其传到具有待测浏览器的电脑上. 
然后, 切换到包含此jar文件的目录中, 运行以下命令:

```shell
java -jar selenium-server-standalone-{VERSION}.jar
```

## 运行服务器的注意事项

调用者应调用 `Selenium#stop()` 或 `WebDriver#quit` 以结束每次会话. 

Selenium服务器在内存中保留每个运行会话的日志, 
这些日志将在调用 `Selenium#stop()` 或 `WebDriver#quit` 时清除. 
如果您忘记终止这些会话, 则可能会造成服务器内存泄漏. 
如果您保持运行时间非常长的会话, 
则可能需要不时执行停止或退出的操作 (或使用-Xmx jvm选项增加内存) .


## 超时 (自2.21版本)

服务器有两种不同的超时, 可以按如下设置:

```shell
java -jar selenium-server-standalone-{VERSION}.jar -timeout=20 -browserTimeout=60
```

* browserTimeout
  * 控制允许浏览器挂起的时间
   (以秒为单位的值) .
* timeout
  * 控制在回收会话之前允许客户端离开的时间
   (以秒为单位的值) .

从2.21版本开始不再支持系统属性
 `selenium.server.session.timeout`.

请注意, 当常规超时机制发生故障时, 
`browserTimeout`旨在用作备份超时机制, 
该机制应主要在网格和服务器的环境中使用, 
以确保崩溃或丢失的进程不会驻留太长时间, 
从而干扰了运行时环境.


## 以编程方式配置服务器

从理论上讲, 此过程就像将 `DriverServlet`映射到URL一样简单, 
但是也可以将页面托管在轻量级容器中, 
例如完全用代码配置的Jetty. 
步骤如下.

* 下载并解压 `selenium-server.zip`. 
* 将这些Jar设置在CLASSPATH中. 
* 创建一个名为 `AppServer`的新类. 
在这里, 我使用Jetty, 
因此您也需要[download](//www.eclipse.org/jetty/download.html):

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

