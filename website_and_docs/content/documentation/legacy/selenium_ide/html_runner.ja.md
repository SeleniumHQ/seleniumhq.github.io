---
title: "HTMLランナー"
linkTitle: "HTMLランナー"
weight: 2
description: >
    Execute HTML Selenium IDE exports from command line
aliases: [
"/documentation/ja/legacy_docs/html-runner/",
"/ja/documentation/legacy/html_runner/"
]
---


_Selenium HTMLランナー_ を使用すると、コマンドラインからテストスイートを実行できます。
テストスイートは、Selenium IDEまたは互換性ツールからのHTMLエクスポートです。

## 共通情報

* geckodriver / firefox / selenium-html-runnerのリリースの組み合わせが重要です。
どこかにソフトウェア互換性マトリックスがあるかもしれません。
* selenium-html-runnerはテストスイートのみを実行します（テストケースではなく、Monitis Transaction Monitorからのエクスポートなど）。
必ずこれを順守してください。
* DISPLAYのないLinuxユーザーの場合-仮想ディスプレイでhtml-runnerを起動する必要があります（xvfbを検索）。

## Linux環境の例
次のソフトウェアパッケージをインストール/ダウンロードします。

```shell
[user@localhost ~]$ cat /etc/redhat-release
CentOS Linux release 7.4.1708 (Core)

[user@localhost ~]$ rpm -qa | egrep -i "xvfb|java-1.8|firefox"
xorg-x11-server-Xvfb-1.19.3-11.el7.x86_64
firefox-52.4.0-1.el7.centos.x86_64
java-1.8.0-openjdk-1.8.0.151-1.b12.el7_4.x86_64
java-1.8.0-openjdk-headless-1.8.0.151-1.b12.el7_4.x86_64
```

テストスイートの例

```html
[user@localhost ~]$ cat testsuite.html
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <meta content="text/html; charset=UTF-8" http-equiv="content-type" />
  <title>Test Suite</title>
</head>
<body>
<table id="suiteTable" cellpadding="1" cellspacing="1" border="1" class="selenium"><tbody>
<tr><td><b>Test Suite</b></td></tr>
<tr><td><a href="YOUR-TEST-SCENARIO.html">YOUR-TEST-SCENARIO</a></td></tr>
</tbody></table>
</body>
</html>
```


## selenium-html-runnerをヘッドレスで実行する方法

さて、最も重要な部分、selenium-html-runnerの実行方法の例！
経験によってソフトウェアの組み合わせ、- geckodriver / FF / html-runnerリリースによって異なる場合があります。

```shell
xvfb-run java -Dwebdriver.gecko.driver=/home/mmasek/geckodriver.0.18.0 -jar selenium-html-runner-3.7.1.jar -htmlSuite "firefox" "https://YOUR-BASE-URL" "$(pwd)/testsuite.html" "results.html" ; grep result: -A1 results.html/firefox.results.html
```

```shell
[user@localhost ~]$ xvfb-run java -Dwebdriver.gecko.driver=/home/mmasek/geckodriver.0.18.0 -jar selenium-html-runner-3.7.1.jar -htmlSuite "*firefox" "https://YOUR-BASE-URL" "$(pwd)/testsuite.html" "results.html" ; grep result: -A1 results.html/firefox.results.html
Multi-window mode is longer used as an option and will be ignored.
1510061109691   geckodriver     INFO    geckodriver 0.18.0
1510061109708   geckodriver     INFO    Listening on 127.0.0.1:2885
1510061110162   geckodriver::marionette INFO    Starting browser /usr/bin/firefox with args ["-marionette"]
1510061111084   Marionette      INFO    Listening on port 43229
1510061111187   Marionette      WARN    TLS certificate errors will be ignored for this session
Nov 07, 2017 1:25:12 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: W3C
2017-11-07 13:25:12.714:INFO::main: Logging initialized @3915ms to org.seleniumhq.jetty9.util.log.StdErrLog
2017-11-07 13:25:12.804:INFO:osjs.Server:main: jetty-9.4.z-SNAPSHOT
2017-11-07 13:25:12.822:INFO:osjsh.ContextHandler:main: Started o.s.j.s.h.ContextHandler@87a85e1{/tests,null,AVAILABLE}
2017-11-07 13:25:12.843:INFO:osjs.AbstractConnector:main: Started ServerConnector@52102734{HTTP/1.1,[http/1.1]}{0.0.0.0:31892}
2017-11-07 13:25:12.843:INFO:osjs.Server:main: Started @4045ms
Nov 07, 2017 1:25:13 PM org.openqa.selenium.server.htmlrunner.CoreTestCase run
INFO: |open | /auth_mellon.php |  |
Nov 07, 2017 1:25:14 PM org.openqa.selenium.server.htmlrunner.CoreTestCase run
INFO: |waitForPageToLoad | 3000 |  |
.
.
.etc

<td>result:</td>
<td>PASS</td>


```
