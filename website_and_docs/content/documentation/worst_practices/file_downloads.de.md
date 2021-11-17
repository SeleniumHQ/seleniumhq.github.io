---
title: "Datei-Downloads"
linkTitle: "File downloads"
weight: 2
aliases: ["/documentation/de/worst_practices/file_downloads/"]
---

Es ist zwar möglich, ein Download zu starten, indem man mit einem 
von Selenium gesteuerten Browser auf einen Link klickt, aber die 
API zeigt den Download-Fortschritt nicht an, was sie für das Testen 
heruntergeladener Dateien nicht gerade ideal macht. Dies liegt daran, 
dass das Herunterladen von Dateien nicht als ein wichtiger Aspekt der 
Emulation der Benutzerinteraktion mit der Webplattform angesehen wird. 
Stattdessen sollten Sie den Link mit Selenium (und den erforderlichen Cookies) 
finden und ihn an eine HTTP-Anforderungsbibliothek wie [libcurl](//curl.haxx.se/libcurl/) übergeben.

Der [HtmlUnit-Treiber](https://github.com/SeleniumHQ/htmlunit-driver) kann 
Anhänge herunterladen, indem er auf sie als Eingabestrom zugreift, indem 
er die [AttachmentHandler-Schnittstelle](https://htmlunit.sourceforge.io/apidocs/com/gargoylesoftware/htmlunit/attachment/AttachmentHandler.html) 
implementiert. Der AttachmentHandler kann dem [HtmlUnit](https://htmlunit.sourceforge.io/) 
WebClient hinzugefügt werden.
