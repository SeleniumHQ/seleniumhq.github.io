---
title: "Remote WebDriver"
chapter: true
weight: 6
---

# Remote WebDriver

Der WebDriver kann über eine Remoteverbindung genau so verwendet werden
wie bei einer lokalen Verbindung. Der Hauptunterschied ist das der
remote WebDriver konfiguriert werden muss, sodass die Testdurchführung
auf einem anderen Rechner gestartet werden kann.

Der remote WebDriver besteht aus zwei Komponenten: Einem Client und
einem Server. Der Client besteht aus dem WebDriver-Test und der Server 
ist ein einfaches Java Servlet, welches auf jedem modernen JEE App Server
gehostet werden kann.