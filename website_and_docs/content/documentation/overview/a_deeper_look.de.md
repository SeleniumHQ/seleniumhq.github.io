---
title: "A Deeper Look"
linkTitle: "A Deeper Look"
weight: 2
description: >
  Selenium ist ein Überbegriff für eine Sammlung von Werkzeugen und Programmbibliotheken 
  die es ermöglichen Webbrowser zu automatisieren.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Diese Seite wird von Englisch 
   auf Deutsch übersetzt. Sprichst Du Deutsch? Hilf uns die Seite 
   zu übersetzen indem Du uns einen Pull Reqeust schickst!
</p>
{{% /pageinfo %}}

### Selenium steuert Webbrowsers

_Selenium_ , ist vieles, aber im Kern ist es eine Sammlung von Werkzeugen um Webbrowser
zu automatisieren. Es nutzt die besten Mechanismen die verfügbar sind um
Browserinstanzen zu steuern und Benutzereingaben zu simulieren.

Es ermöglicht Aktionen von Benutzern zu simulieren;
Texteingaben,
Auswahl von Optionen in Drop-Downs, aktivieren von Checkboxen und
anklicken von Links auf Webseiten.
Es ermöglicht auch viele andere Möglichkeiten wie zum 
Beispiel Mausbewegungen, beliebige JavaScript Codeausführungen
und vieles mehr.

In erster Linie wird Selenium für Front-End Testen von Websiten verwendet.
Selenium ist im Grunde eine _'user agent' Bibliothek_. 
Es stehen Schnittstellen zur Verfügung, die es ermöglichen mit anderen
Bibliotheken zu kommunizieren um Deinen Anforderungen gerecht zu werden.


### Ein Interface für sämtliche Anforderungen

Ein Leitsatz des Projektes ist es eine Schnittstelle zur Verfügung
zu stellen um alle (relevanten) Browser zu unterstützen.
Webbrowser sind sehr komplexe und hoch entwickelte Applikationen.
Das Ausführen von Aktionen funktioniert in jedem Browser anders, auch
wenn der sichtbare Teil der Aktionen bei allen gleich aussieht.
Obwohl Texte mit der gleichen Schriftart gerendert werden,
Bilder an der gleichen Stelle dargestellt werden
und Links auf die gleichen Ziele verweisen.
Was im Hintergrund passiert ist so unterschiedlich wie Tag und Nacht.
Selenium "abstrahiert" diese Unterschiede und verbirgt diese Details
und Feinheiten vor der Person die den Programmcode schreibt.
Dadurch wird ermöglicht Programmcode zu schreiben der einen komplexen Workflow
abbildet und dann auf Firefox, Internet Explorer, Chrome und allen anderen
unterstützen Browsern lauffähig sind.

### Werkzeuge und Support

Dieser minimalistische Designansatz verleiht Selenium die Vielseitigkeit,
um als Komponenten in größeren Applikationen eingesetzt zu werden.
Weitere Werkzeuge die unter der Schirmherrschaft von Selenium stehen
bieten Werkzeuge um [grid of browsers]({{< ref "/grid.md" >}}) zu 
erstellen.
Somit können Tests auf unterschiedlichen Browsern und unterschiedlichen
Betriebssystemen verteilt auf mehrere Rechner ausgeführt werden.

Stell Dir vor mehrere Computer in Deinem Serverraum oder Datencenter
starten gleichzeitig ihre Browser, klicken den Link zu Deiner Webseite,
Formulare und Tabellen; Testen Deine Applikation rund um die Uhr.
Auf Grund des simplen Programminterface welches für die meist
genutzten Programmiersprachen zur Verfügung gestellt wird, können
diese Tests unermüdlich parallel laufen und falls Fehler auftreten
werden diese gemeldet.

Um dieses Ziel zu erreichen ist es unser Ziel, Dich dahingehend mit Werkzeugen 
und Dokumentation zu unterstützen um nicht nur den Browser zu steuern,
sondern auch damit Du ein solches skalierendes Grid 
einfach in Betrieb nehmen kannst.

### Wer benutzt Selenium

Viele große Firmen auf der ganzen Welt verwenden Selenium
um ihre Browsertests zu realisieren, oft nachdem jahrelanger Aufwand
für propritäre Werkzeuge aufgewendet wurde.
Mit der wachsenden Popularität sind auch die Anforderungen und 
Herausforderungen gestiegen.

Die Komplexität von Webseiten nimmt ständig zu und neue Technologien
werden zu diesen fortlaufend hinzugefügt. Ziel dieses Projektes ist 
es wenn möglich Schritt zu halten mit der Entwicklung des Web.
Da dies ein Opensource Projekt ist, ist es abhängig von 
all den Freiwilligen die in Ihrer Freizeit das Projekt unterstützen. 

Ein weiteres Ziel des Projektes ist es, weitere Freiwillige zu ermutigen
sich aktiv an den Projekt zu beteiligen, die Community zu stärken, so
dass das Projekt mit neuen Technologien Schritt halten kann. Auf diese Weise kann
es weiterhin als eine der führenden Plattformen für funktionale
Testautomatisierung etabliert werden.
