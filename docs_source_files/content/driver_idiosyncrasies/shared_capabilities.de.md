---
title: "Allgemeine capabilities"
weight: 1
---

### Strategien das Websitenladehaltens betreffend

Standarmässig wartet Selenium bis eine Seite vollständig geladen ist wenn 
zu einer neuen Seiten via URL navigiert wird. Dies funktioniert wunderbar
für Anfänger, kann jedoch lange Wartezeiten verursachen, für Webseiten die
mehrere Drittanbieter Ressourcen beinhalten. Dies kann in einem solchen Fall
 beschleunigt werden, wenn keine Standardstrategie verwendet wird. Jedoch 
 können dadurch die Tests instabil werden, da Elemente auf der Website sowohl
 die Position, als auch die Größe, während des Ladens verändern können. 
 
 Die definierte Wartestrategie prüft den 
 [document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
wie in der Tabelle angeführt:

| Strategie | Status  | Bemerkung |
| -------- | ----------- | ----- |
| normal | komplett | Standardwert, wartet bis alle Ressourcen geladen wurden |
| eager | interaktiv | DOM Zugriff ist bereit, allerdings können andere Elemente wie Bilder noch unvollständig geladen sein |
| none | beliebig | Blockiert den WebDriver gar nicht |