---
title: "HTTP response codes"
linkTitle: "HTTP response codes"
weight: 3
aliases: ["/documentation/de/worst_practices/http_response_codes/"]
---

Bei einigen Browser-Konfigurationen in Selenium RC 
arbeitet Selenium als Proxy zwischen dem Browser und 
der Website, die automatisiert werden soll. Das heißt, 
dass der gesamte Browser-Traffic, der durch Selenium 
geleitet wurde, aufgezeichnet oder manipuliert werden konnte. 
Die Methode `captureNetworkTraffic()` sollte den gesamten 
Netzwerkverkehr zwischen dem Browser und der zu 
automatisierenden Website erfassen, einschließlich der 
HTTP Response-Codes.

Selenium WebDriver ist ein völlig anderer Ansatz zur 
Browser-Automatisierung, der sich mehr wie ein Benutzer 
verhält. Das zeigt sich vor allem dadurch, wie Sie Tests 
mit WebDriver schreiben. Bei automatisierten Funktionstests 
ist die Überprüfung des Statuscodes kein besonders wichtiges 
Detail beim Scheitern eines Tests - wichtiger sind die
Schritte, die dem vorausgehen.

Der Browser zeigt immer den HTTP-Statuscode an. Stellen Sie 
sich zum Beispiel eine 404 oder 500 Fehlerseite vor. Es gibt 
eine einfache Möglichkeit, schnell zu reagieren, wenn Sie auf 
eine dieser Fehlerseiten stoßen: Sie können den Seitentitel 
oder den Inhalt eines zuverlässigen Punktes (z.B. den `<h1>` Tag) 
nach jedem Laden der Seite überprüfen. Wenn Sie das 
Page Object Model (POM) verwenden, können Sie diese Prüfung 
in Ihren Klassenkonstruktor oder einen ähnlichen Punkt aufnehmen, 
an dem das Laden der Seite erwartet wird. Gelegentlich 
kann der HTTP-Code sogar in der Fehlerseite des Browsers 
dargestellt werden. Sie könnten dann WebDriver nutzen, um sie 
auszulesen und Ihre Debugging-Ausgabe zu verbessern.

Die Überprüfung der Webseite selbst läuft nach der idealen 
Praxis von WebDriver, um die Ansicht des Benutzers auf der 
Webseite darzustellen und zu überprüfen.

Wenn Sie darauf bestehen, gibt es noch eine erweiterte Lösung 
zur Erfassung von HTTP-Statuscodes: Das Verhalten von Selenium RC 
durch Verwendung eines Proxys replizieren. Die WebDriver-API 
bietet die Möglichkeit, einen Proxy für den Browser festzulegen. 
Es gibt dann verschiedene Proxys, die es Ihnen ermöglichen, 
programmgesteuert den Inhalt der Anfragen an den Webserver und 
vom Webserver zu manipulieren. Bei der Verwendung eines Proxys 
können Sie entscheiden, wie auf Response-Codes für die Weiterleitung 
reagiert werden soll. Außerdem stellt nicht jeder Browser die 
Response-Codes für WebDriver zur Verfügung. Mit einem Proxy 
haben Sie also eine Lösung, die für jeden Browser funktioniert.
