---
title: "Das Seleniumprojekt und die Tools"
weight: 1
---

### Selenium steuert Webbrowsers

_Selenium_ , ist Vieles, aber im Kern ist eine Sammlung von Werkzeugen um Webbrowser
zu automatisieren. Es nutzt die besten Mechanismen die verfügbar sind um
Browsersinstanzen zu steuern und Benutzereingaben zu simulieren.

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

Eine Leitsatz des Projektes ist es eine Schnittstelle zur Verfügung
zu stellen um alle (relevanten) Browser zu unterstützen.
Webbrowser sind sehr komplexe und hoch entwickelte Applikationen.
Das Ausführen von Aktionen funktioniert in jedem Browser anders, auch
wenn der sichtbare Teil der Aktionen bei allen gleich aussieht.
Obwohl Texte mit der gleichen Schriftart gerendert werden,
Bilder an der gleichen Stelle dargestellt werden
und Links auf die gleichen Ziele verweisen.
Was im Hintergrund passiert ist so unterschiedlich wie Tag und Nacht.
Selenium "abstrahiert" diese Unterschiede und verbirgt diese Details
und Feinheiten von der Person die den Programmcode schreibt.
Dadurch wird ermöglicht Programmcode zu schreiben die einen komplexen Workflow
abbilden und dann auf Firefox, Internet Explorer, Chrome und allen anderen
unterstützen Browsern lauffähig sind.

### Werkzeuge und Support

Selenium's minimalistischer Designansatz verleiht im die Vielseitigkeit
um als Komponenten in größeren Applikationen eingesetzt zu werden.
Weitere Werkzeuge die unter der Schirmherrschaft von Selenium stehen
bieten Werkzeuge um [grid of browsers]({{< ref "/grid/_index.md" >}}) zu 
erstellen.
Somit können Tests auf unterschiedlichen Browsern und unterschiedlichen
Betriebssystemen verteilt auf mehrere Rechner ausgeführt werden.

Stell Dir vor mehrere Computer in Deinem Serverraum or Datencenter
starten gleichzeitig ihre Browser, klicken den Link zu Deiner Webseite,
Formulare und Tabellen; Testen Deine Applikation rund um die Uhr.
Auf Grund des simplen Programminterface welches für die meist
genutzten Programmiersprachen zur Verfügung gestellt wird, können
diese Tests unermüdlich parallel laufen und im Falle das Fehler auftreten
werden diese gemeldet.

Um dieses Ziel zu erreichen ist es unser Ziel dahingehend mit Werkzeugen 
und Dokumentation Dich zu unterstützen um nicht nur den Browser zu steuern
sondern Dich auch dahingehend zu unterstützen ein solches skalierendes Grid 
einfach in Betrieb zu nehmen.

### Wer benutzt Selenium

Viele große Firmen auf der ganzen Welt verwenden Selenium
um ihre Browsertests zu realisieren, oft nachdem jahrelanger Aufwand
für propritäre Werkzeuge aufgewendet wurde.
Mit der wachsenden Popularität haben sich auch die Anforderungen und 
Herausforderungen gestiegen.

Die Komplexität von Webseiten nimmt ständig zu und neue Technologien
werden zu diesen fortlaufend hinzugefügt. Ziel dieses Projektes ist 
es wenn möglich Schritt zu halten mit der Entwicklung des Web.
Der Umstand das dies ein Opensource Projekt ist, ist es abhängig von 
all den Freiwilligen die in Ihrer Freizeit das Projekt unterstützen. 

Ein weiters Ziel des Projektes ist es, weitere Freiwillige zu ermutigen
sich aktiv an den Projekt zu beteiligen, die Community zu stärken
dahingehend, dass das Projekt mit neuen Technologien Schritt halten kann
und es weiterhin als eine der führenden Plattformen für funktionale
Testautomatisierung zu etablieren.

### Geschichte

2014 wurde Selenium 1 veröffentlich, mit dem Ziel, die Dauer
zu verkürzen, die benötigt wird, konsistentes Verhalten der 
Benutzeroberfläche einer Webapplikation zu prüfen. Es wurden verfügbaren
Werkzeuge benutzt, und realisiert wurde dies mit Hilfe von JavaScript, welches auf 
der zu testenden Webseite eingefügt wurde, um Benutzereingaben zu simulieren.

JavaScript ist eine gutes Werkzeug um sich die Eigenschaften des DOM näher zu betrachten,
die sonst nur schwer möglich sind, jedoch eignet es sicht nicht dazu um realistisches
Verhalten von Benutzereingaben zu simulieren, vor allem wenn Tastatur oder Maus verwendet
werden sollen.

Seitdem ist Selenium stark gewachsen und hat sich weiterentwickelt, in ein Werkzeug
das von vielen &mdash;wenn nicht sogar von den meisten&mdash;der größten Organisationen
weltweit verwendet wird. Selenium hat sich von einem selbstgebastelten 
Testautomatisierungswerkzeug, das bei Thoughtworks entwickelt wurde, für einen 
ganz speziellen Anwendungsfall zur _de facto_ Bibliothek für Browserautomation entwickelt, weltweit.
 
So wie Selenium RC die damals zur Verfügung stehenden Werkzeuge nutze, führt
[Selenium WebDriver]({{< ref "/webdriver/_index.md" >}}) die Tradition fort, indem die 
Verantwortung für den Teil der Browserinteraktion an die Hersteller dieser übergeben wird. 
In jüngster Vergangenheit hat sich daraus der W3C Standardisierungsprozess abgeleitet, mit
dem Ziel das die WebDriver Komponenten in Selenium in eine _du jour_ Steuerungsbibliothek
für Useragents wandelt.