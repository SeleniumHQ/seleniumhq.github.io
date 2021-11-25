---
title: "Overview"
linkTitle: "Overview"
weight: 1
description: >
  Is Selenium for you? See an overview of the different project components.
aliases: ["/documentation/de/introduction/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Diese Seite wird von Englisch 
   auf Deutsch übersetzt. Sprichst Du Deutsch? Hilf uns die Seite 
   zu übersetzen indem Du uns einen Pull Reqeust schickst!
</p>
{{% /pageinfo %}}


Selenium ist nicht ein einzelnes Tool oder eine API, es besteht aus mehreren Tools

## WebDriver

Startest Du mit dem automatisieren von Websiten, dann wirst Du die WebDriver APIs verwenden.
[WebDriver]({{< ref "/webdriver.md" >}}) verwendet die von den Browsern
Herstellern zur Verfügung gestellten APIs um den Browser zu steuern und Test
auszuführen. Die Ausführung simuliert einen echten Benutzer. Da der WebDriver eigenständig
ist und nicht mit der Applikation kompiliert werden muss, handelt es sich um eine minimal 
invasive Methode. Es kann exakt die Applikation getestet werden die später veröffentlicht wird.  

## IDE

[IDE](https://selenium.dev/selenium-ide) (Integrated Development Environment = Entwicklungsumgebung)
ist ein Werkzeug um Seleniumtests zu erstellen. Es ist eine einfach zu handhabende 
Erweiterung für Chrome und Firefox und in der Regel ist dies die effizienteste Weg
um Testfälle zu erstellen. Benutzerinteraktionen werden aufgezeichnet mit Hilfe von 
bestehenden Selenium Befehlen. Diese werden mit den entsprechenden Parametern versehen.
Das ist nicht nur zeitsparend sondern auch ein guter Weg um sich mit der Seleniumsyntax 
vertraut zu machen.

## Grid

Mit Selenium Grid können Tests auf verschiedenen Maschinen auf 
unterschiedlichen Plattformen ausgeführt werden. 
Das Starten der Tests findet lokal statt, 
die automatische Ausführung serverseitig. 

Sollen erstellte WebDriver-Tests in mehreren 
Browsern und Betriebssystemen ausgeführt werden, 
kann dafür [Grid]({{< ref "/grid.md" >}}) genutzt werden.
