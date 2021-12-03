---
title: "Elemente lokalisieren"
linkTitle: "Elemente lokalisieren"
weight: 3
aliases: ["/documentation/de/webdriver/locating_elements/"]
---

### Locating one element

Eine der grundlegendsten Techniken, die bei der Verwendung des WebDriver
erlernt werden müssen, ist wie man Elemente auf der Webseite findet. WebDriver
bietet eine Reihe von verschiedenen Möglichkeiten um Elemente zu finden,
darunter die Suche nach einem Element anhand des ID-Attributs:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.find_element(By.ID, "cheese")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< /tab >}}
{{< /tabpane >}}

Wie das Beispiel zeigt, wird die Lokalisierung der Elemente
mit dem WebDriver direkt an einer Instanz des `WebDriver` Objektes
durchgeführt. Die `findElement(By)` Methode liefert ein Objekt des
Types `ẀebElement`.

* `WebDriver` repräsentiert den Browser
* `WebElement` repräsentiert einen bestimmten DOM Knoten
  (z.B. einen Link, ein Eingabefeld, etc.)

Ab dem Zeitpunkt, ab dem eine Referenz zu einem WebElement "gefunden"
wurde, kann der Suchumfang auf dieses Element eingeschränkt werden. Es
können weitere eingegrenzte Suchen auf Basis des ausgewählten Elements
durchgeführt werden, indem die gleiche Methode angewandt wird:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< /tab >}}
  {{< tab header="Python" >}}
cheese = driver.find_element(By.ID, "cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< /tab >}}
{{< /tabpane >}}

Dies wird ermöglicht weil sowohl der _WebDriver_ als auch das
_WebElement_ das Interface [_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html)
implementieren. Wir verstehen dies im WebDriver als _role-based interface_
(rollenbasiertes Interface). Dieses Interface ermöglicht um herauszufinden ob eine
driver Implementierung ein bestimmtes Feature unterstützt oder nicht.
Diese Schnittstellen (Interface) sind klar definiert und versuchen daran festzuhalten, dass
es nur eine Verantwortlichkeit dafür gibt. Mehr über den Aufbau und die
Verantwortlichkeiten der Driver können hier nachgelesen werden [Link zu einer Sektion die noch definiert werden muss](#)
<!--TODO Eine neue Sektion muss erstellt werden. (see English version) -->

Folglich untersützt das _By_ Interface zahlreich zusätzliche Suchstrategien.
Eine verschachtelte Suche ist nicht die effektivste Methode um die den gewünschten
Käse zu finden. Es werden zwei getrennte Befehle an den Browser gesendet. Der
erste der den gesamten DOM nach dem Element mit der ID "cheese" sucht, gefolgt
von der Suche nach "cheddar" mit einem eingeschränkten Kontext.

Um die Effektivität zu erhöhen sollte ein präziserer Locator (Identifizierungsstrategie)
gewählt werden; WebDriver unterstützt die Suche nach Elementen auch mit Hilfe eines
CSS-locators, mit dem es auch möglich ist Kombinationen in einer einzelnen Suche
durchzuführen:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< /tab >}}
  {{< tab header="Python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< /tab >}}
{{< /tabpane >}}

### Finden mehrerer Elemente

Angenommen das Dokument in dem wir suchen wollen beinhaltet eine sortierte
Liste mit Käsesorten die uns am besten schmecken:

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ol>
```

Es steht außer Frage, je mehr Käse desto besser, es wäre aber umständlich
jedes Element einzeln abrufen zu müssen. Daher gibt es die Möglichkeit
mit `findElements(By)` mehrere Elemente gleichzeitig zu finden. Diese Methode liefert
eine Sammlung (Collection) von WebElementen. Wird nur ein Element gefunden, wird trotzdem
eine Sammlung (mit einem Element) retourniert. Wird kein Element gefunden
ist die Liste leer.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< /tab >}}
  {{< tab header="Python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector("#cheese li"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
mucho_cheese = driver.find_elements(css: '#cheese li')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< /tab >}}
{{< /tabpane >}}

### Strategien der Elementsuche

Im WebDriver existieren acht unterschiedliche Möglichkeiten um Elemente zu lokalisieren:

| Lokator/Suchmethode (locator) | Beschreibung |
| -------- | ---------- |
| class name | Lokalisiert Elemente mit dem gewünschten Klassennamen (Zusammengesetzte Klassennamen sind nicht erlaubt) |
| css selector | Lokalisiert Elemente die dem CSS-Selektor entsprechen |
| id | Lokalisiert Elemente deren ID dem Suchwert entsprechen |
| name | Lokalisiert Elemente die den entsprechenden Wert im NAME Attribut haben |
| link text | Lokalisiert Link-Elemente deren sichtbarer Text dem Suchwert entsprechen |
| partial link text | Lokalisiert Link-Elemente die den Suchwert im sichtbaren Text vorkommt |
| tag name | Lokalisiert Elemente mit den entsprechenden HTML-Tags |
| xpath | Lokalisiert Elemente die auf dem xpath-Selektor entsprechen |

### Tips zur Verwendung von Selektoren

Die bevorzugte Methode um Elemente zu identifizieren ist mit Sicherheit
mit Hilfe der HTML IDs. Diese sind eindeutig, konsitent und vorhersehbar,
weiters arbeitet diese Methode sehr schnell, da hierbei auf komplizierte
DOM Verarbeitungen verzichtet wird.

Wenn eindeutige IDs nicht verfügbar sind, ist ein gut definierter
CSS selector die bevorzugte Methode um Elemente zu lokalisieren.
XPath-Suchen funktionieren gleich dem CSS-Selektoren, allerdings ist die
Syntax komplizierter und schwieriger zu debuggen. Obwohl XPath-Selektoren
sehr flexibel sind, sind sie in der Regel nicht von den Browser-Herstellern
auf Leistung getestet und sind tendenziell recht langsam.

Selektorstrategien die _linkText_ oder _partialLinkText_ verwenden
haben den Nachteil das diese nur für Link-Elemente angewandt werden
können. Weiters werden diese Selektoren intern im WebDriver als
XPath-Selektoren aufgelöst.

Den HTML-Tag als Identifizierungsmerkmal zu verwenden kann gefährlich
sein. Meistens sind viele Elemente mit dem gleichen HTML-Tag auf einer
Webseite. Eine sinnvolle Verwendung könnte sein, diese Strategie mit der
_findElements(By)_ Methode zu verwenden, die eine Sammlung von WebElementen
retourniert.

Empfohlen wird die Suchen so kompackt und einfach lesbar wie möglich zu halten.
Den DOM abzufragen ist eine aufwändige Operation für den WebDriver,
und je präziser der Suchbegriff ist, desto besser.



## Relative Suchstrategien
**Selenium 4** führt relative Locators ein, die zuvor als
_Friendly Locators_ bekannt waren. Diese Funktionalität wurde
hinzugefügt um Elemente zu finden, die sicht in der Nähe zu anderen
Elementen befinden.
Folgende relative Locators sind verfügbar:

* `above` (oberhalb)
* `below` (unterhalb)
* `toLeftOf` (links)
* `toRightOf` (rechts)
* `near` (nahe/nächst)

Die `findElement` Methode akzeptiert nun eine weitere Möglichkeit
`with(By)` die einen relativen Locator liefert.

### Wie funktionieren die relativen Suchemethoden
Selenium verwendet folgende JavaScript Funktion
[getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect)
um das entsprechende Element zu finden. Diese
Funktion retourniert Eigenschaften eines Elements
wie z.B right, left, bottom und top (links, rechts, oben, unten)

Betrachten wir das folgende Beispiel um die
Funktionalität der relativen Locators besser zu verstehen:

{{< figure src="/images/documentation/webdriver/relative_locators.png" class="img-responsive text-center" alt="Relative Locators">}}

### above() - oberhalb

Liefert das WebElement, welches sich über dem spezifiziertem Element befindet.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement passwordField = driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input"))
.above(passwordField));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(locate_with(By.TAG_NAME, "input").above(passwordField))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(RelativeBy.WithLocator(By.TagName("input")).Above(passwordField));
{{< /tab >}}
{{< tab header="Ruby" >}}
password_field= driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let passwordField = driver.findElement(By.id('password'));
let emailAddressField = await driver.findElement(locateWith(By.tagName('input')).above(passwordField));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(with(By.tagName("input")).above(passwordField))
{{< /tab >}}
{{< /tabpane >}}


### below() - unterhalb
Findet das WebElement, welches sich unter dem
spezifiziertem Element befindet.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement emailAddressField = driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(with(By.tagName("input"))
.below(emailAddressField));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(locate_with(By.TAG_NAME, "input").below(emailAddressField))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(RelativeBy.WithLocator(By.TagName("input")).Below(emailAddressField));
{{< /tab >}}
{{< tab header="Ruby" >}}
email_address_field = driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailAddressField = driver.findElement(By.id('email'));
let passwordField = await driver.findElement(locateWith(By.tagName('input')).below(emailAddressField));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(with(By.tagName("input")).below(emailAddressField))
{{< /tab >}}
{{< /tabpane >}}


### toLeftOf() - links davon
Liefert das WebElement, welches sich links vom spezifizierten Element
befindet.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement submitButton = driver.findElement(By.id("submit"));
WebElement cancelButton = driver.findElement(with(By.tagName("button"))
.toLeftOf(submitButton));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(locate_with(By.TAG_NAME, "button").
to_left_of(submitButton))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(RelativeBy.WithLocator(By.TagName("button")).LeftOf(submitButton));
{{< /tab >}}
{{< tab header="Ruby" >}}
submit_button= driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let submitButton = driver.findElement(By.id('submit'));
let cancelButton = await driver.findElement(locateWith(By.tagName('button')).toLeftOf(submitButton));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val submitButton = driver.findElement(By.id("submit"))
val cancelButton = driver.findElement(with(By.tagName("button")).toLeftOf(submitButton))
{{< /tab >}}
{{< /tabpane >}}


### toRightOf() - rechts davon

Liefert das WebElement, das sich rechts des spezifierten Elements befindet.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement cancelButton = driver.findElement(By.id("cancel"));
WebElement submitButton = driver.findElement(with(By.tagName("button")).toRightOf(cancelButton));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(locate_with(By.TAG_NAME, "button").
to_right_of(cancelButton))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement cancelButton = driver.FindElement(By.Id("cancel"));
IWebElement submitButton = driver.FindElement(RelativeBy.WithLocator(By.TagName("button")).RightOf(cancelButton));
{{< /tab >}}
{{< tab header="Ruby" >}}
cancel_button = driver.find_element(:id, "cancel")
submit_button = driver.find_element(relative: {tag_name: 'button', right:cancel_button})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let cancelButton = driver.findElement(By.id('cancel'));
let submitButton = await driver.findElement(locateWith(By.tagName('button')).toRightOf(cancelButton));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val cancelButton = driver.findElement(By.id("cancel"))
val submitButton = driver.findElement(with(By.tagName("button")).toRightOf(cancelButton))
{{< /tab >}}
{{< /tabpane >}}

### near() - in der Nähe von

Liefert das WebElement, welches maximal `50px` vom spezifizierten
Element entfernt ist.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement emailAddressLabel = driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

emailAddressLabel = driver.find_element(By.ID, "lbl-email")
emailAddressField = driver.find_element(locate_with(By.TAG_NAME, "input").
near(emailAddressLabel))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressLabel = driver.FindElement(By.Id("lbl-email"));
IWebElement emailAddressField = driver.FindElement(RelativeBy.WithLocator(By.TagName("input")).Near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Ruby" >}}
email_address_label = driver.find_element(:id, "lbl-email")
email_address_field = driver.find_element(relative: {tag_name: 'input', near: email_address_label})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailAddressLabel = driver.findElement(By.id("lbl-email"));
let emailAddressField = await driver.findElement(locateWith(By.tagName("input")).near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailAddressLabel = driver.findElement(By.id("lbl-email"))
val emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel))
{{< /tab >}}
{{< /tabpane >}}
