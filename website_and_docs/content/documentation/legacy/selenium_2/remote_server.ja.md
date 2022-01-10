---
title: "リモートWebDriverサーバー"
linkTitle: "リモートWebDriverサーバー"
weight: 6
aliases: [
"/documentation/ja/legacy_docs/remote_webdriver_server/",
"/ja/documentation/legacy/remote_webdriver_server/"
]
---

サーバーは、テストするブラウザーがインストールされたマシンで常に実行されます。
サーバーは、コマンドラインから、またはコード設定を通じて使用できます。

## コマンドラインからサーバーを起動する

一旦、`selenium-server-standalone-{VERSION}.jar`をダウンロードしたら、テストしたいブラウザーのあるコンピューターに配置します。
次に、jarを含むディレクトリから、次のコマンドを実行します。

```shell
java -jar selenium-server-standalone-{VERSION}.jar
```

## サーバーを実行するにあたって考慮すること

呼び出し元は、`Selenium#stop()`または`WebDriver#quit`を呼び出して、各セッションを適切に終了すべきです。

selenium-serverは、進行中の各セッションのメモリ内ログを保持します。
これらのログは、`Selenium#stop()`または`WebDriver#quit`が呼び出されるとクリアされます。
これらのセッションの終了を忘れると、サーバーでメモリリークが発生する可能性があります。
非常に長時間実行されるセッションを維持する場合は、時々停止または終了する必要があります（または-Xmx jvmオプションでメモリを増やします）。

## タイムアウト (version 2.21以降)

サーバーには2つの異なるタイムアウトがあり、次のように設定できます。

```shell
java -jar selenium-server-standalone-{VERSION}.jar -timeout=20 -browserTimeout=60
```

* browserTimeout
  * ブラウザーのハングを許可する時間を制御します（値は秒単位）。
* timeout
  * セッションが回収されるまでにクライアントがいなくなる時間を制御します（値は秒単位）。

システムプロパティ`selenium.server.session.timeout`は、2.21からサポートされなくなりました。

`browserTimeout`は、通常のタイムアウトメカニズムが失敗した場合の予備のタイムアウトメカニズムであることに注意してください。これは主にグリッド/サーバー環境で使用され、クラッシュ/失われたプロセスが長く滞留、ランタイム環境を汚染しないようにします。

## プログラムでサーバーを構成する

理論的には、プロセスは`DriverServlet`をURLにマッピングするのと同じくらい簡単ですが、ページを全体的にコードで構成されたJettyなどの軽量コンテナでホストすることもできます。これを行う手順は次のとおりです。

`selenium-server.zip`をダウンロードして解凍します。
JARをCLASSPATHに配置します。
`AppServer`という新しいクラスを作成します。
ここでは、Jettyを使用しているので、それも[ダウンロード](//www.eclipse.org/jetty/download.html)する必要があります。

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
