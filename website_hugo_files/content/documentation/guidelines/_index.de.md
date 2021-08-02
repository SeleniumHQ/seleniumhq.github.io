---
title: "Anleitungen und Empfehlungen"
linkTitle: "Anleitungen"
weight: 7
description: >
  Some guidelines and recommendations on testing from the Selenium project.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to German. Do you speak German? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Eine Bemerkung zu "Best Practices": Wir vermeiden absichtlich die Phrase 
"Best Practices" in dieser Dokumentation. Es gibt schlicht keinen Ansatz
der für alle Situationen die beste Wahl ist. Wir bevorzugen die Formulierung
"Anleitungen und Empfehlungen". Wir ermutigen Euch gewissenhaft zu entscheiden
welche die besten Ansätze für Euer Projekt sind, nachdem die Dokumentation 
gelesen wurde.

Funktionales Testen ist ohnehin schon schwierig genug.  Anwendungsstatus, 
Komplexität und Abhängigkeiten machen das Testen schon schwierig genug. Dazu kommt
das der Umgang mit Browsern (insbesondere mit Cross-Browser-Inkompatiblitäten)
das Schreiben guter Tests zu einer Herausforderung macht.

Selenium stellt Werkzeuge zur Verfügung, die die funktionale Benutzerinteraktion
erleichtern. Allerdings kann Selenium nicht dabei helfen gut aufgebaute Testsuiten
zu schreiben. In diesem Kapitel haben wir Ratschläge, Richtlinien und Empfehlungen
zusammengefasst, zur funktionalen Automatisierung von Webseiten.

Dieses Kapitel verwendet viele bewährte Software-Patterns die sich für viele 
Selenium Nutzer als erfolgreich erwiesen haben.
