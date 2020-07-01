---
title: "Localiser des éléments"
weight: 3
---

### Localiser des éléments

Une des techniques fondamentales à maîtriser lorsque l'on utilise WebDriver 
consiste à chercher des éléments sur une page. 
WebDriver offre pour cela un ensemble pré-défini de type de selecteurs,
parmi lesquels la recherche d'une élément par son attribut ID:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("fromage"));  
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element(By.ID, "fromage")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("fromage"));  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const fromage = driver.findElement(By.id('fromage'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< / code-panel >}}
{{< / code-tab >}}

Comme démontré dans cet exemple, la localisation des éléments à l'aide de WebDriver
se fait via une instance de l'objet `WebDriver`. 
La méthode `findElement(By)` retourne un autre type d'objet fondamental, un `WebElement`.

* `WebDriver` represente la navigateur
* `WebElement` represente un noeud particulier du DOM
  (un lien, un champ texte, etc.)

Un fois que l'on a obtenu la référence de l'élément web qui a été "trouvé",
on peut encore réduire la portée de notre recherche
en utilisant le même appel de méthode sur l'instance de cet objet:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement fromage = driver.findElement(By.id("fromage"));
WebElement cheddar = fromage.findElement(By.id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
fromage = driver.find_element(By.ID, "fromage")
cheddar = fromage.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement fromage = driver.FindElement(By.Id("fromage"));
IWebElement cheddar = fromage.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
fromage = driver.find_element(id: 'fromage')
cheddar = fromage.find_element(id: 'cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const fromage = driver.findElement(By.id('fromage'));
const cheddar = fromage.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

Nous pouvons faire cela car les types _WebDriver_ et _WebElement_
implémentent tous deux l'interface [_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html).
Dans WebDriver, ce principe est connu sous le nom de _role-based interface_.
Les interfaces basées sur le rôle nous permettent de déterminer
si une implémentation particulière de driver supporte une fonctionnalité donnée.
Ces interfaces sont clairement définies et tente d'adhérer au principe de responsabilité unique.
Vous pouvez en lire plus sur le design de WebDriver et sur quels drivers supportent quels rôles dans le chapitre [Un Autre Chapitre Qui Aura Un Nom](#).
<!-- TODO: A new section needs to be created for the above.-->

Par conséquent, l'interface _By_ utilisée précédement fournit également
d'autres stratégies de localisation. Une recherche imbriquée peut ne pas 
être la startégie la plus adaptée pour trouver notre cheddar 
puisqu'elle nécessite que deux instructions séparées soient envoyées au navigateur ;
tout d'abord rechercher un élément ayant pour ID "fromage", 
puis une recherche pour "cheddar" dans ce contexte plus restreint.

Pour améliorer légèrement les performances, nous pourrions essayer
un sélecteur (une stratégie de localisation) plus spécifique : 
WebDriver supporte la localisation d'élément via sélecteur CSS,
nous permettant de combiner les deux sélecteurs précédents en un seul:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.findElement(By.cssSelector("#fromage #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheddar = driver.find_element_by_css_selector("#fromage #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.FindElement(By.CssSelector("#fromage #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(css: '#fromage #cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = driver.findElement(By.css('#fromage #cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

### Localiser plusieurs éléments

Il est possible que le document web sur lequel nous travaillons
dispose d'une liste ordonnée de nos fromages préférés:

```html
<ol id=fromage>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ul>
```
Puisque plus de fromage est sans conteste meilleur, et qu'il serait lourd
de devoir récupérer chaque item un par un, une technique supérieure est d'utiliser
la forme plurielle `findElements(By)`. Cette méthode retourne une collection
d'éléments web. Si un seul élement a été trouvé, la méthode renverra tout de même
une collection (d'un seul élément). Si aucun élément ne correspond au sélécteur,
la collection retournée sera alors vide.


{{< code-tab >}}
  {{< code-panel language="java" >}}
List<WebElement> pleinDeFromage = driver.findElements(By.cssSelector("#fromage li"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
plein_de_fromage = driver.find_elements_by_css_selector("#fromage li")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IReadOnlyList<IWebElement> pleinDeFromage = driver.FindElements(By.CssSelector(“#fromage li”));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
plein_de_fromage = driver.find_elements(css: '#fromage li')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const pleinDeFromage = driver.findElements(By.css('#fromage li'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< / code-panel >}}
{{< / code-tab >}}

### Stratégie de sélection des éléments

WebDriver possède huit stratégies de localisation pré-définies différentes:

| Sélecteur         | Description                                                                                            |
| ----------------- | ------------------------------------------------------------------------------------------------------ |
| class name        | Localise les éléments dont le nom de la classe contient la valeur recherchée (nom composés non permis) |
| css selector      | Localise les éléments correspondant à un sélecteur CSS                                                 |
| id                | Localise les éléments dont l'attribut ID correspond à la valeur recherchée                             |
| name              | Localise les éléments dont l'attribut NAME correspond à la valeur recherchée                           |
| link text         | Localise les éléments de type ancre (lien) dont le texte visible correspond à la valeur recherchée     |
| partial link text | Localise les éléments de type ancre (lien) dont le texte visible contient la valeur recherchée         |
| tag name          | Localise les éléments dont le nom de tag correspond à la valeur recherchée                             |
| xpath             | Localise les éléments correspondant à un chemin XPath                                                  |

### Astuces d'utilisation des sélecteurs

En règle général, si des ID HTML sont disponibles, uniques et prédictibles avec constance,
alors il est préférable d'utiliser cette stratégie pour la localisation d'élément sur une page.
Elle a tendance à être très rapide et évite les longs traitements liés à des traversées complexes du DOM.

Si des IDs uniques ne sont pas disponibles, un sélecteur CSS bien écrit
est la méthode de localisation la plus adaptée. Un sélecteur XPath marchera
aussi bien qu'un sélecteur CSS, cependant sa syntaxe est plus complexe, et souvent,
plus compliquée à débugguer. Même si les sélecteur XPath sont très flexibles,
ils sont rarement testés d'un point de vue performance par les fournisseurs de navigateur
et ont donc tendance à être assez lents.

Les stratégies basés sur _linkText_ et _partialLinkText_ sont
contraingnantes du fait qu'elles ne fonctionnent 
que sur des éléments de type lien hypertexte. De plus, elles
sont implémentées au sein de WebDriver via des sélecteurs XPath.

Le nom de tag est une façon dangereuse de localiser des éléments. 
Il y a fréquemment de multiples éléments ayant le même tag sur une page.
Cette stratégie est principalement utile lorsque utilisée avec la méthode
_findElements(By)_, renvoyant une collection des élements.

Au final, la recommendation est de garder ses sélecteurs aussi compacts et lisibles que possible.
Demander à WebDriver de traverser la structure du DOM est une opération très coûteuse,
de fait plus le scope de recherche sera restreint, meilleures seront les performances.

## Relative Locators

**Selenium 4** brings Relative Locators which are previously 
called as _Friendly Locators_. This functionality was 
added to help you locate elements that are nearby other elements.
The Available Relative Locators are:

* *above*
* *below*
* *toLeftOf*
* *toRightOf*
* *near*

_findElement_ method now accepts a new method `withTagName()` 
which returns a RelativeLocator. 

### How does it work

Selenium uses the JavaScript function 
[getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect)
to find the relative elements. This function returns 
properties of an element such as 
right, left, bottom, and top.

Let us consider the below example for understanding the relative locators.

![Relative Locators](/images/relative_locators.png?width=400px)

### above()

Returns the WebElement, which appears 
above to the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement passwordField= driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(withTagName("input")
                                                  .above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(with_tag_name("input").above(passwordField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;
IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
password_field= driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(withTagName("input").above(passwordField))
  {{< / code-panel >}}
{{< / code-tab >}}


### below()

Returns the WebElement, which appears 
below to the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement emailAddressField= driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(withTagName("input")
	                                          .below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(with_tag_name("input").below(emailAddressField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;  
IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(WithTagName("input")
                                               .Below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_field= driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(withTagName("input").below(emailAddressField))
  {{< / code-panel >}}
{{< / code-tab >}}


### toLeftOf()

Returns the WebElement, which appears 
to left of specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement submitButton= driver.findElement(By.id("submit"));
WebElement cancelButton= driver.findElement(withTagName("button")
                                            .toLeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(with_tag_name("button").
                                   to_left_of(submitButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;
IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(WithTagName("button")
                                              .LeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
submit_button= driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val submitButton= driver.findElement(By.id("submit"))
val cancelButton= driver.findElement(withTagName("button").toLeftOf(submitButton))
  {{< / code-panel >}}
{{< / code-tab >}}


### toRightOf()

Returns the WebElement, which appears 
to right of the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement cancelButton= driver.findElement(By.id("cancel"));
WebElement submitButton= driver.findElement(withTagName("button")
                                            .toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(with_tag_name("button").
                                   to_right_of(cancelButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;
IWebElement cancelButton = driver.FindElement(By.Id("cancel"));
IWebElement submitButton = driver.FindElement(WithTagName("button")
                                              .RightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cancel_button = driver.find_element(:id, "cancel")
submit_button = driver.find_element(relative: {tag_name: 'button', right:cancel_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cancelButton= driver.findElement(By.id("cancel"))
val submitButton= driver.findElement(withTagName("button").toRightOf(cancelButton))
  {{< / code-panel >}}
{{< / code-tab >}}

### near()

Returns the WebElement, which is
at most `50px` away from the specified element.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement emailAddressLabel= driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(withTagName("input")
                                                  .near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
emailAddressLabel = driver.find_element(By.ID, "lbl-email") 
emailAddressField = driver.find_element(with_tag_name("input").
                                       near(emailAddressLabel))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;
IWebElement emailAddressLabel = driver.FindElement(By.Id("lbl-email"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_label = driver.find_element(:id, "lbl-email")
email_address_field = driver.find_element(relative: {tag_name: 'input', near: email_address_label})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}