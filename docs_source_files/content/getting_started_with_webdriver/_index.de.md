---
title: "Erste Schritte mit WebDriver"
chapter: true
weight: 4
---

# Erste Schritte mit WebDriver


Selenium unterstützt das Automatisieren aller wichtigsten Webbrowser 
durch die Verwendung von _WebDriver_.

WebDriver ist eine API und ein Protokoll, das eine programmiersprachen 
unabhängige Schnittstelle definiert um den Webrowser zu steuern.
Jeder Browser ist mit einer spezifischen Implementierung des Webdriver
ausgestattet, auch *driver* genannt. Dieser driver ist die Komponente
die verantwortlich ist um den Browser fernzusteuern, weiters handabt dieser 
die Kommunikation zwischen Selenium und dem Webbrowser.

Diese Trennung wurde bewusst gemacht, um die Verantwortung der 
browserspezifischen Implementierung in die Hände der Browserhersteller
zu legen. Selenium ermöglicht es diese Drittanbieter driver zu verwenden.
Weiters werden auch eigene driver zur Verfügung gestellt die durch
das Projekt gewartet werden, für den Fall das der Browserhersteller
keine zur Verfügung stellt.

Das Seleniumframework verbindet diese einzelnen Komponenten zu einer
benutzerfreundlichen Schnittstelle die es ermöglicht, die verschiedenen 
Browserbackends einheitlich und browser- als auch plattform-unabhängig 
zu automatisieren.

Weitere Details zu den drivers können unter 
[Driver Eigenheiten]({{< ref "/driver_idiosyncrasies/_index.md" >}}) 
aufgerufen werden.