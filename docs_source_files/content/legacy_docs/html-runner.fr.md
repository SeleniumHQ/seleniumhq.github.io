---
title: "Runner HTML"
weight: 3
---

_Selenium HTML-runner_ permet d'exécuter des Test Suites depuis la ligne de commande.
Les Tests Suites sont des exports HTML depuis Selenium IDE ou autre outil compatible.

## Informations générales

* Les combinaisons de version de geckodriver / firefox /
selenium-html-runner sont importantes. Il doit y avoir
une matrice de compatibilité quelque part.
* selenium-html-runner exécute seulement des Test Suites (et non pas des Test Case, comme un export de Monitis Transaction Monitor). Assurez-vous de vous y conformer.
* Pour les utilisateurs Linux sans affichage - vous devez exécuter html-runner 
  avec un affichage virtuel (cherchez xvfb)

## Exemple sur environnement Linux
Installer / télécharger les packages suivants:

```shell
[user@localhost ~]$ cat /etc/redhat-release
CentOS Linux release 7.4.1708 (Core)

[user@localhost ~]$ rpm -qa | egrep -i "xvfb|java-1.8|firefox"
xorg-x11-server-Xvfb-1.19.3-11.el7.x86_64
firefox-52.4.0-1.el7.centos.x86_64
java-1.8.0-openjdk-1.8.0.151-1.b12.el7_4.x86_64
java-1.8.0-openjdk-headless-1.8.0.151-1.b12.el7_4.x86_64
```

Example de Test Suite:

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


## Comment exécuter selenium-html-runner en mode headless

Pour la partie la plus importante, voici un exemple d'exécution de selenium-html-runner !
Votre expérience peut varier selon les combinaisons logicielles - versions de geckodriver/FF/html-runner.

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
