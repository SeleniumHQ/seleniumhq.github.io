---
title: "Gmail, email und Facebook Einloggen"
linkTitle: "Gmail, email und Facebook"
weight: 4
aliases: ["/documentation/de/worst_practices/gmail_email_and_facebook_logins/"]
---

Aus mehreren Gründen wird die Anmeldung auf Websites wie Google Mail 
und Facebook mit WebDriver nicht empfohlen. Abgesehen davon, dass Sie 
gegen die Nutzungsbedingungen dieser Websites verstoßen (wo Sie riskieren, 
dass der Account gebannt wird), ist es langsam und unzuverlässig.

Die optimale Weise ist, die APIs zu verwenden, die von E-Mail-Anbietern 
angeboten werden, oder im Falle von Facebook den Entwicklertools-Service, 
der eine API für die Erstellung von Test-Accounts, Freunden und so weiter 
macht, zu nutzen. Obwohl die Verwendung einer API wie etwas zusätzliche 
harte Arbeit erscheinen mag, werden Sie sich in Geschwindigkeit, 
Zuverlässigkeit und Stabilität zurückzahlen. Auch die API wird sich 
wahrscheinlich nicht ändern, während Websites und HTML-Lokatoren sich 
häufig ändern und Sie Ihr Testframework aktualisieren müssen.

Wenn Sie sich bei Websites Dritter mit WebDriver an jedem Punkt Ihres 
Tests anmelden, erhöht sich das Risiko, dass Ihr Test fehlschlägt, weil 
es Ihren Test verlängert. Eine allgemeine Faustregel lautet, dass längere 
Tests fragiler und unzuverlässiger sind.

[W3C-konforme](//w3c.github.io/webdriver/webdriver-spec.html) WebDriver-Implementierungen 
versehen das Navigator-Objekt mit einer WebDriver-Eigenschaft, sodass 
Denial-of-Service-Angriffe abgeschwächt werden können.
