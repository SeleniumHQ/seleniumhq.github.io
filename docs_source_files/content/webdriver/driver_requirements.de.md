---
title: "Driver Vorraussetzungen"
weight: 2
---

Mit Hilfe des WebDrivers unterstützt Selenium alle wichtigen Webbrowser
wie Chrom(ium), Firefox, Internet Explorer, Opera und Safari. Wenn möglich
wird der Browser mit Hilfe des integrierten Support für Automation gesteuert, 
nicht für alle Browser existiert diese Möglichkeit der externe Steuerung.

Das Ziel von WebDriver ist es Interaktionen eines echten Benutzers
so gut wie möglich mit dem Browser zu simulieren. Die unterschiedlichen
Browser unterstützen dies in unterschiedlicher Weise. Mehr Details zu den
unterschiedlichen Eigenheiten der Driver sind unter 
_[Eigenheiten der Driver]({{< ref "/driver_idiosyncrasies/_index.md" >}})_ 
zu finden.

Alle Browser haben ein gemeinsames Interface um den Browser zu steuern, 
sie unterscheiden sich etwas beim Erstellen einer Browsersession. Die 
Driver-Implementierungen werden von Dritten bereitgestellt, daher sind
diese nicht in Selenium enthalten.

Instanzierung des Drivers, Profilmanagement und verschiedene spezifische 
Browser Einstellungen sind Beispiele für Parameter die unterschiedliche Werte
haben können, abhängig von dem verwendeten Browser. Dieser Abschnitt beschreibt
die Anforderungen, die notwendig sind, um die unterschiedlichen Browser zu starten.

### Hinzufügen der ausführbaren Programme zum PATH
Die meisten Driver benötigen ein eignes ausführbares Programm für Selenium
um mit dem Browser zu kommunizieren. Eine Möglichkeit ist es, manuell festzulegen
in welchem Ordner sich die ausführbaren Driver-Dateien befinden bevor der
WebDriver gestartet wird, allerdings schränkt das die Portabilität ein, da
sich die Dateien dann auf jedem Rechner im gleichen Ordner befinden müssen.
Alternativ kann man die diese Dateien auch im Repository des Testcodes ablegen.

Wird der Ordner der die WebDriver Programme enthält dem Systempfad
hinzugefügt, ist es für Selenium möglich die Driver Dateien zu finden
ohne das im Testcode der exakte Ordner angegeben werden muss.

* Erstelle ein Verzeichnis in dem die ausführbaren Dateien (executeables)
abgelegt werden wie z.B.
_C:\WebDriver\bin_ oder _/opt/WebDriver/bin_
* Füge das Verzeichnis der PATH - Variable hinzu:
  * Unter Windows - Öffne die Eingabeaufforderung als Administrator
    und führe folgenden Befehl aus um den Ordnerpfad dauerhaft 
    der PATH Variable für alle Benutzer des Rechners hinzuzufügen:

```shell
setx /m path "%path%;C:\WebDriver\bin\"
```
  * Unter macOS und Linux führe in einem Terminal folgenden Befehl aus:

```shell
export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
```
* Nun können die Änderungen getestet werden. Schließe alle
  geöffneten Eingabebeaufforderungen bzw. Terminals und öffnen 
  dieses erneut. Tippe nun den Namen eines Drivers, der zuvor in
  dem Ordner abgelegt wurden z.B.: 

  ```shell
  chromedriver
  ```
* Wenn der 'PATH' korrekt konfiguriert ist, dann sollte eine 
ähnliche Meldung auf der Konsole wie folgt aussehen:

```text
Starting ChromeDriver 2.25.426935 (820a95b0b81d33e42712f9198c215f703412e1a1) on port 9515
Only local connections are allowed.
```
Mit Hilfe von <kbd>Ctrl+C</kbd> beendest Du den Driver und erlangst wieder
Kontrolle über das Terminal.

### Referenzen

| Browser | Unterstützte Betriebssysteme | Wartung | Download | Fehlerticketsystem |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Downloads](//chromedriver.storage.googleapis.com/index.html) | [Issues](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [Downloads](//github.com/mozilla/geckodriver/releases) | [Issues](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows 10 | Microsoft | [Downloads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&amp;q=webdriver) |
| Internet Explorer | Windows | Selenium Project | [Downloads](//selenium-release.storage.googleapis.com/index.html) | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS El Capitan und neuere | Apple | Integriert | [Issues](//bugreport.apple.com/logon) |
| Opera | Windows/macOS/Linux | Opera | [Downloads](//github.com/operasoftware/operachromiumdriver/releases) | [Issues](//github.com/operasoftware/operachromiumdriver/issues) |


### Chromium/Chrome
Um Chrome oder Chromium zu steuern, muss der 
[chromedriver](//sites.google.com/a/chromium.org/chromedriver/downloads)
downgeloaded werden und in einem Ordner gespeichert werden der sich im
Systempfad befindet.

Unter Linux oder macOS, muss die `PATH` Umgebungsvariable angepasst werden.
Mit folgendem Kommando können die alle Ordner die der PATH-Variable
bereits hinzugefügt wurden angezeigt werden (getrennt durch Beistriche):

```shell
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```
Um den chromedriver dem PATH hinzuzufügen, falls dies noch nicht
geschehen ist, beachte das der Ordner dem PATH hinzugefügt werden muss.
Der folgende Befehl fügt der `PATH` Umgebungsvariable den aktuellen
Ordnerpfad hinzu (nach dem Doppelpunkt):

```shell
$ export PATH="$PATH:/path/to/chromedriver"
```
Nachdem der chromedriver dem PATH hinzugefügt wurde ist es möglich
das _chromedriver_ Programm von jedem Ordner des Rechners zu starten.
Um eine Chrome/Chromium Session zu instanzieren können folgende Codezeilen
genutzt werden:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

WebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Chrome

driver = Chrome()

from selenium.webdriver import Chrome

with Chrome() as driver:
    #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

IWebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :chrome
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
    let driver = await new Builder().forBrowser('chrome').build();
    //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

val driver: WebDriver = ChromeDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Zur Erinnerung: es ist notwendig chromedriver dem PATH hinzuzufügen,
dies geschiet mit folgendem Befehl:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Chrome(executable_path='/path/to/chromedriver')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new ChromeDriver("/path/to/chromedriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Chrome.driver_path = "/path/to/chromedriver"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
chrome.setDefaultService(new chrome.ServiceBuilder('path/to/chromedriver').build());
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver")
  {{< / code-panel >}}
{{< / code-tab >}}

Der chromedriver ist implementiert als WebDriver Remote Server,
somit wird dem Browsers mitgeteilt welche Aktionen auszuführen sind,
indem die interne Automationsschnittstelle bereitgestellt wird.

### Firefox

Als Selenium 3 gestartet ist, hat Mozilla die Implementierung des
Firefox Drivers mit dem [geckodriver](//github.com/mozilla/geckodriver) 
übernommen. Der neue Driver für Firefox wird geckodriver genannt
und funktioniert ab Firefox Version 48. Da der Firefox WebDriver
noch immer in Entwicklung ist, wird er laufend verbessert.

Die aktuelle Standardmethode um Firefox zu starten ist mit Hilfe des
geckodrivers, daher erfolgt die Instanzierung gleich wie mit 
Selenium 2.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

WebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Firefox

driver = Firefox()
#Or use the context manager
from selenium.webdriver import Firefox

with Firefox() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

IWebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :firefox
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('firefox').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Firefox.FirefoxDriver

val driver: WebDriver = FirefoxDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Falls bevorzugt kann der Ordnerpfad statt mit Hilfe der 
PATH-Variable mit folgenden Programmzeilen definiert werden:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Firefox(executable_path='/path/to/geckodriver')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new FirefoxDriver("/path/to/geckodriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Firefox.driver_path = "/path/to/geckodriver"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const firefox = require('selenium-webdriver/firefox');

const serviceBuilder = new firefox.ServiceBuilder("/path/to/geckodriver");

(async function myFunction() {
    let driver = await new Builder()
        .forBrowser('firefox')
        .setFirefoxService(serviceBuilder)
        .build();
        //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver")
  {{< / code-panel >}}
{{< / code-tab >}}

Es ist auch möglich den Parameter via Kommandozeile zu übergeben:

```shell
mvn test -Dwebdriver.gecko.driver=/path/to/geckodriver
```
Aktuell ist es möglich den älteren Firefox Driver zu nutzen, dieser 
ist hat mehr Funktionen, dafür ist es notwendig eine ältere Version von 
Firefox mit der Version [47.0.1](//ftp.mozilla.org/pub/firefox/releases/47.0.1/)
oder [45 ESR](//ftp.mozilla.org/pub/firefox/releases/45.0esr/)
zu installieren. Weiters ist es notwendig die Eigenschaft **marionette**
auf **false** zu setzen. Neuere Versionen von Firefox sind
nicht kompatibel.

### Edge

Edge ist Microsoft's aktuellster Browser, der in Windows 10 und Server 2016
inkludiert ist. Updates für Edge sind in den Updates für Windows inkludiert,
somit ist es notwendig den entsprechenden Driver downzuloaden, der kompatibel ist
mit der installierten Windows Version (mit passender Buildnummer).
Die [Edge Entwickler Website](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)]
beinhaltet Links zu allen verfügbaren Edgedrivern. Bugs die den Edgedriver
betreffen können bei [Microsoft](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&q=webdriver)
eingemeldet werden.
Um Tests mit dem Edge laufen zu lassen ohne ein installiertes Windows 10
auf dem eigenen Rechner zu haben, bietet Microsoft freie VMs an, um den 
Edge Browser zu testen [Edge VM Website](//developer.microsoft.com/en-us/microsoft-edge/tools/vms/).

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

WebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Edge

driver = Edge()
#Or use the context manager
from selenium.webdriver import Edge

with Edge() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Edge;

IWebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :edge
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('MicrosoftEdge').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver

val driver: WebDriver = EdgeDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Falls der Edge Driver nicht via PATH verfügbar ist, kann der Pfad 
auch mit Hilfe des Programmcodes definiert werden:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Edge(executable_path='/path/to/MicrosoftWebDriver.exe')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new EdgeDriver("/path/to/MicrosoftWebDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Edge.driver_path = "C:/path/to/MicrosoftWebDriver.exe"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const edge = require('selenium-webdriver/edge');
let service = new edge.ServiceBuilder("/path/to/msedgedriver.exe");
(async function test() {
    let driver = await new Builder()
                .setEdgeService(service)
                .forBrowser('MicrosoftEdge')
                .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe")
  {{< / code-panel >}}
{{< / code-tab >}}

### Internet Explorer

Der Internet Explorer war Microsoft's Standardbrowser bis Windows 10,
trotzdem ist er ebenfalls in Windows 10 inkludiert. Der Internet Explorer Driver
ist der einzige Driver der vom Selenium Projekt mit Releases versorgt wird 
[Microsoft considers current](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer).
Ältere Releases können weiterhin funktionieren, werden jedoch offiziell nicht
weiter unterstützt.

Das Selenium-Projekt stellt die Driver für 32-bit und 64-bit Versionen des Internet
Explorer zur Verfügung, es gibt jedoch einige [Einschränkungen](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
mit dem Internet Explorer 10 & 11 mit dem 64-bit Driver, jeodch funktioniert
die 32-bit Version des Drivers weiterhin. Zu Beachten ist, dass die Einstellungen
im Benutzerprofil des aktuell eingeloggten Benutzers gespeichert werden. Weiters
sind ein paar [zusätzliche Einstellungen notwendig](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver#required-configuration).

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

WebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Ie

driver = Ie()
#Or use the context manager
from selenium.webdriver import Ie

with Ie() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

IWebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :internet_explorer
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('internet explorer').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.ie.InternetExplorerDriver

val driver: WebDriver = InternetExplorerDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Falls der Internet Explorer Driver nicht im PATH vorhanden ist, kann
dieser mittels Programmcode definiert werden:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Ie(executable_path='/path/to/IEDriverServer.exe')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new InternetExplorerDriver("C:/path/to/IEDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::IE.driver_path = "C:/path/to/IEDriver.exe"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const ie = require('selenium-webdriver/ie');
let service = new ie.ServiceBuilder("/path/to/IEDriverServer.exe");
(async function test() {
    let driver = await new Builder()
                .setIeService(service)
                .forBrowser('internet explorer')
                .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe")
  {{< / code-panel >}}
{{< / code-tab >}}

Microsoft bietet auch einen WebDriver für 
[Internet Explorer 11 für Windows 7 & 8.1](//www.microsoft.com/en-gb/download/details.aspx?id=44069)
an. Dieser wurde seit 2014 nicht mehr aktualisiert und basiert
auf einer Entwurfsversion der W3 Spezifikation. [Jim Evans](//jimevansmusic.blogspot.co.uk/2014/09/using-internet-explorer-webdriver.html)
hat eine exzellente Beschreibung über die Microsoft Implementierung verfasst.
 
### Opera

Aktuelle Versionen des Operabrowsers basieren auf der Chromiumengine
und WebDriver unterstützt fortan die nicht quelloffene Version des
[Opera Chromium Driver](//github.com/operasoftware/operachromiumdriver/releases),
die der [PATH Variable hinzugefügt](#adding-executables-to-your-path) werden
kann, oder als Umgebungsvariable.

Die Instanzierung funktioniert analog zu Firefox und Chromium:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

WebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Opera

driver = Opera()
#Or use the context manager
from selenium.webdriver import Opera

with Opera() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Opera;

IWebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :opera
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const opera = require('selenium-webdriver/opera');
(async function test() {
    let driver = await new Builder()
        .forBrowser('opera')
        .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.opera.OperaDriver

val driver: WebDriver = OperaDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

### Safari

High Sierra und neuer:
* Führe den folgenden Befehl im Terminal aus und authorisiere die 
Ausführung beim ersten Mal mit dem Passwort.

```shell
safaridriver --enable
```

El Capitan and Sierra:

* Aktiviere das Entwicklermenü in den Safari Einstellungen
* Prüfe die _Allow Remote Automation_ Option in dem Entwicklermenü
* Führe folgenden Befehl im Terminal einmalig aus und authorisiere
den WebDriver mit dem Paßwort

```shell
/usr/bin/safaridriver -p 1337</
```

Die Driver Session kann dann wie folgt gestartet werden:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

WebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Safari

driver = Safari()
#Or use the context manager
from selenium.webdriver import Safari

with Safari() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Safari;

IWebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :safari
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('safari').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.safari.SafariDriver

val driver: WebDriver = SafariDriver()
  {{< / code-panel >}}
{{< / code-tab >}}


Wenn eine Automation von Safari auf einem iOS Gerät gewünscht ist
dann sollte das [Appium Projekt](//appium.io/) näher betrachtet werden.
Während Safari früher auch auf Windows verfügbar war, hat Apple 
vor längerem den Support dafür eingestellt.


## Mock Browser


### HtmlUnit

HtmlUnit ist ein Browser für Java Programmierer ohne Oberfläche. Dieser 
interpretiert HTML Dokumente und stellt Schnittstellen zur Verfügung, die 
es ermöglichen Seiten aufzurufen, Formulare auszufüllen, Links zu klicken, etc.
Er unterstützt auch JavaScript und kann auch mit AJAX Bibliotheken arbeiten 
indem Chrome, Firefox oder Internet Explorer simuliert wird abhängig welche 
Konfiguration genutzt wird. Die neue Website ist zu finden unter [neuer Link](http://htmlunit.sourceforge.net/gettingStarted.html).
Der Programmcode wird mit SVN verwaltet.


### PhantomJS

PhantomJS ist ein sogenannter headless Browser (ohne grafischer Oberfläche) basierend
auf Webkit, eine viel ältere Version wurde von Google Chrome
und von Safari genutzt. Auch wenn es früher eine durchaus gute Möglichkeit war
sollte PhantomJS nicht mehr genutzt werden. Das Projekt wurde eingestellt und wird
nicht mehr gewartet [seit dem 5. August 2017](//groups.google.com/forum/#!topic/phantomjs/9aI5d-LDuNE).
Das Web entwickelt sich ständig weiter, jedoc wird PhantomJS nicht mehr aktualisiert. 
Die Einstellung erfolgte nachdem Google angekündigt hatte eine Möglichkeit zu schaffen Chrome
"headless" zu starten, die Funktionalität ist auch in Mozilla's Firefox enthalten.



