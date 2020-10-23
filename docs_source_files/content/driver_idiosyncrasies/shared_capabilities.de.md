---
title: "Allgemeine capabilities"
weight: 1
---

Um eine neue Sitzung mit dem Selenium WebDriver zu erstellen,
ist es notwendig das die Grundlegenden capabilites zur Verfügung 
gestellt werden.

Das Zielsystem benutzt dabei die gleichen Einstellungen in den 
capabilities um eine Sitzung zu erstellen und ermöglich Details 
dieser Sitzung abzufragen.
 
WebDriver stellt capabilites zur Verfügung und jeder Adapter
sollte diese Implementierung unterstützen. Folgende capabilites 
werden von WebDriver unterstützt:

## browserName:

Diese Capability wird verwendet um den `browserName` für eine 
Sitzung zu definieren. Ist der Browser auf dem Zielsystem nicht 
installiert, schlägt die Erstellung der Sitzung fehl.

## browserVersion: 

Diese capability ist optional, sie dient dazu die Version
des Webbrowsers am Zielsystem zu definieren. Wird versucht 
eine Sitzung z.B. mit Chrome in der Version 75 zu initialisieren, 
auf dem lediglich Version 80 installiert ist, wird dies fehlschlagen.

## Strategien das Websitenladehaltens betreffend (pageLoadingStrategy)

Standarmäßig wartet Selenium bis eine Seite vollständig geladen ist wenn 
zu einer neuen Seiten via URL navigiert wird. Dies funktioniert wunderbar
für Anfänger, kann jedoch lange Wartezeiten verursachen, für Webseiten die
mehrere Drittanbieter Ressourcen beinhalten. Dies kann jedoch in einem 
solchen Fall beschleunigt werden, wenn nicht die Standardstrategie verwendet 
wird. Jedoch können dadurch die Tests instabil werden, da Elemente auf der Website sowohl
 die Position, als auch die Größe, während des Ladens verändern können. 
 
 Die definierte Wartestrategie prüft den 
 [document.readyState](//developer.mozilla.org/de/docs/Web/API/Document/readyState)
wie in der Tabelle angeführt:

| Strategie | Status  | Bemerkung |
| -------- | ----------- | ----- |
| normal | komplett | Standardwert, wartet bis alle Ressourcen geladen wurden |
| eager | interaktiv | DOM Zugriff ist bereit, allerdings können andere Elemente wie Bilder noch unvollständig geladen sein |
| none | beliebig | Blockiert den WebDriver gar nicht |


## platformName

Dient zur Definition des Betriebssystems am Zielsystem. Wird
die capability abgefragt erhält man den Namen des Betriebssystems.

Auf cloud-basierten Systemen wird mittels `platformName` das
Betriebssystem des Zielsystems definiert.

## acceptInsecureCerts

Diese capability legt fest ob abgelaufene oder ungültige 
`TLS Certificate` verwendet werden benutzt werden können.

Wenn die capability den Wert `false` gesetzt hat, wird ein
[insecure certificate error](//developer.mozilla.org/de/docs/Web/WebDriver/Errors/InsecureCertificate) 
als Rückgabewert geliefert falls Domain Zertifikatsprobleme 
auftauchen. Ist der Wert auf `true` gesetzt, werden ungültige
 Zertifikate vom Webbrowser akzeptiert.
 
Allen selbstsignierten Zertifikaten werden standardmässig vertraut
mit dieser capability. Ist die capability `acceptInsecureCerts`
gesetzt, hat dies für die gesamte Sitzung Auswirkungen.

## Sitzungstimeouts (Session timeouts)

Einer WebDriver `session` ist ein bestimmtes `session timeout`
Intervall zugeordnet, während diesem kann der Benutzer 
kontrollieren ob Scripts ausgeführen werden oder Informationen
vom Webbrowser empfangen werden sollen.

Jedes Session Timeout is mittels einer Kombination
der folgenden unterschiedlichen `timeouts` definiert:

### Script Timeout:
Spezifiziert wann die Ausführung eines Scriptes im aktuellen
Browserkontext abgebrochen werden soll. Standardmäßig ist das
Timeout auf **30** Sekunden festgelegt und wird festgelegt
sobald eine neue Sitzung des WebDrivers erstellt wird.

### Seitenlade Timeout:
Damit wird die Zeitspanne festgelegt in dem die Webseite
geladen werden muss im aktuellen Webbrowser Kontext.
Der Standardwert liegt bei **300** Sekunden und wird 
beim Erstellen einer WebDriver Sitzung festgelegt. Wird das 
festgelegte Zeitfenster überschritten wird das Script mit einer
_TimeoutException_ gestoppt.

### Impliziertes Wait Timeout

Spezifiziert die Zeitspanne die gewartet wird um
bei der implizierten Suche nach Elementen. Der Standard 
Wert ist **0** und wird festgelegt bei der Initialisierung
des WebDrivers.

## unhandledPromptBehavior

Legt den Status der aktullen Sitzung fest `user prompt handler`.
Standardwert ist **dismiss and notify state** (=Verwerfen und Status melden)

### unhandledPromptBehaviour

Definiert welche Aktion wenn eine Anfrage auf dem Zielsystem
eine Aktion erfordert. Dies wird mittels `unhandledPromptBehavior` 
capability definiert und hat einen der folgenden Stati:

* dismiss (=verwerfen)
* accept (=akzeptieren)
* dismiss and notify (=verwerfen und benachrichtigen)
* accept and notify (=akzeptieren und benachrichtigen)
* ignore (=ignorieren)
 
## setWindowRect

Mit Hilfe dieses Kommandos wird die Größe und die Position
des Webbrowserfensters verändert. Der Befehl agiert als setter
für `getWindowRect` und hat als Parameter **breite**, **höhe**, 
**x**, **y** als _optionale_ Parameter.


Während der Automatisierung, wird der aktuelle Webbrowserkontext
mit Status des Fensters assoziert, welcher die Sichtbarkeit des
Webbrowserfensters beschreibt. Folgende Statis des Fensters gibt es:

* maximized (= maximiert)
* minimized (= minimiert)
* normal
* fullscreen (=Vollbild)

Allerdings kann das festlegen der _Breite_ oder _Höhe_ des 
Fensters nicht garantieren das dieses die exakte Größe aufweist.
Grund dafür ist, dass manche driver es nicht ermöglichen 
Größenänderungen für einzelne Pixel vorzunehmen. Dadurch können
auch Ungenauigkeiten beim Abrufen der Fensterdetails mit `getWindowRect`
auftreten, wenn diese Werte zuvor gesetzt wurden.

## strictFileInteractability

Mit dieser neuen Capability kann festgelegt werden ob die 
strikte Methode für Eingabefelder die das Tag _input type=file_ haben 
angewandt wird. Die strike Methode ist standardmäßig nicht aktiv. 
Das Verhalten hat sich disbezüglich geändert wenn die _Element Send Keys_ 
Funktion auf versteckte Dateiuploads Kontrollfelder angewandt wird.