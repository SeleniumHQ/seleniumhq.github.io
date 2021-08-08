---
title: "Erklärung der Komponenten"
linkTitle: "Erklärung der Komponenten"
weight: 1
aliases: ["/documentation/de/webdriver/understanding_the_components/"]
---

Um eine Testsuite mit Hilfe des WebDrivers zu erstellen, ist es notwendig
verschiedeneste Komponenten des WebDrivers zu verstehen und richtig 
einzusetzten. Wie bei fast allen in der Softwareentwicklung, gibt es
viele Lösungsmöglichkeiten um eine Idee umzusetzten. Folgend ein paar 
Begriffsdefinitionen die in der Beschreibung genutzt werden.
 
### Begriffsdefinitionen

* **API:** Application Programming Interface (= Programmschnittstelle). Beschreibt
eine Sammlung von Kommandos, die verwendet werden um den WebDriver zu 
konfigurieren.
* **Bibliothek:** (= Libary) Ein Modul welches die APIs und den erforderlichen
Programmcode beinhaltet, der notwendig ist für die Implementierung der 
Schnittstellen. Die Bibliotheken sind programmiersprachenspezifisch, 
z.B. .jar Dateien für Java, .dll Dateien für .NET, etc. 
* **Driver:** Der Driver ist verantwortlich für das Steuern des Browsers. 
Die meisten WebDriver werden von den Browserherstellern selbst erstellt. 
Driver sind die ausführen Module die auf dem gleichen System ausgeführt werden
auf denen der Browser gestartet wird, nicht auf den Systemen auf denen die 
Testsuiten laufen. (Natürlich kann das auch auf dem gleichen System ausgeführt werden.)
Bemerkung: _Manche bezeichnen den Driver als Proxy._
* **Framework:** Eine zusätzliche Bibliothek die als Unterstützung für
WebDriver Suites dient. Diese Bibliotheken entsprechen Testframeworks
sowie JUnit oder NUnit. Weiters existieren Frameworks die mit naürlicher 
Sprache Anforderungen beschreiben wie z.B. Cucumber oder Robotium. Frameworks 
können auch für Tätigkeiten wie z.B. Konfiguration des Testsystems, 
für Datenmanipulationen, als Testorakel, etc. verwendet werden.

### Die einzelnen Komponenten im Detail
Der WebDriver kommuniziert mit dem Browser zumindest mit Hilfe des Drivers. 
Die Kommunikation erfolgt bidirektional: Der WebDriver sendet die Kommandos
an den Browser, mit Hilfe des Drivers und erhält Informationen zurück über 
den gleichen Weg.

{{< figure src="/images/documentation/webdriver/basic_comms.png" class="img-responsive text-center" alt="Basic Communication">}}

Der Driver is browserspezifisch, z.B. der ChromeDriver ist für Google's
Chrome/Chromium, der GeckoDriver für Mozilla's Firefox, etc. verantwortlich.
Der Driver wird auf dem selben System wie der Browser ausgeführt. Dies
kann aber muss nicht das gleiche System sein, auf dem die Tests ausgeführt 
werden.

Das einfache Beispiel weiter oben zeigt eine _direkte_ Kommunikation. 
Die Kommunikation zum Browser kann auch über eine _remote_ Verbindung mit einem 
Selenium Server oder einem RemoteWebDriver erfolgen. Der RemoteWebDriver
läuft am selben System wie der Driver und der Browser.

{{< figure src="/images/documentation/webdriver/remote_comms.png" class="img-responsive text-center" alt="Remote Communication">}}

Die remote Kommunikation kann ebenfalls mit Hilfe eines Selenium Servers 
oder einem Selenium Grid realisiert werden. Beide Möglichkeiten kommunizieren
mit dem driver auf dem Zielsystem (=host).

{{< figure src="/images/documentation/webdriver/remote_comms_server.png" class="img-responsive text-center" alt="Remote Communication with Grid">}}

## Einbinden eines Frameworks

Der WebDriver hat einen einzigen Job: Kommunikation mit dem Browser mittels
WebDriver, egal welche der oben beschriebenen Wege genutzt wird. WebDriver selbst 
hat keine Testfunktionen: Dieser kann nicht Werte vergleichen, 
feststellen ob ein Test ok ist oder fehlschlägt und er besitzt keine Funktionen
bezüglich Reporting oder kennt auch nicht die Angenommen/Wenn/Dann Grammatik.

Das ist der Punkt an dem verschiedenste Frameworks ins Spiel kommen. Zumindest
ist es notwendig ein Testframework für die entsprechende Programmiersprache
zu verwenden wie z.B. NUnit für .NET, JUnit für Java, RSpec für Ruby, etc.

Das Testframework ist verwantwortlich für die Ausführung des WebDrivers und 
der entsprechenden Schritte in den Tests. Eine schematische Darstellung ist in
der folgenden Grafik zu finden.

{{< figure src="/images/documentation/webdriver/test_framework.png" class="img-responsive text-center" alt="Test Framework">}}

Frameworks oder Tools die natürliche Sprache verwenden wie z.B. Cucucmber
können in ein solches Testframework eingebunden werden wie in der Grafik 
dargestellt ist, oder sie können eine eigene Implementierung anbieten 
und ein anderes Testframework wrappen.
