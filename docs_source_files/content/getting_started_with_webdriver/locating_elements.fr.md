---
title: "Localiser des éléments"
weight: 3
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to French. Do you speak French? Help us to translate
it by sending us pull requests!
{{% /notice %}}

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
driver.find_element_by_id("fromage")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("fromage"));  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(id: "fromage")  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const fromage = await driver.findElement(By.id('fromage'));
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
fromage = driver.find_element_by_id("fromage")
cheddar = fromage.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement fromage = driver.FindElement(By.Id("fromage"));
IWebElement cheddar = fromage.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
fromage = driver.find_element(id: "fromage")
cheddar = fromage.find_elements(id: "cheddar")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const fromage = await driver.findElement(By.id('fromage'));
const cheddar = await fromage.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

Nous pouvons faire cela car les types _WebDriver_ et _WebElement_
implémentent tous deux l'interface [_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html>SearchContext).
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
mucho_cheese = driver.find_elements(css: "#fromage #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = await driver.findElement(By.css('#fromage #cheddar'));
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
plein_de_fromage = driver.find_elements(css: "#fromage li")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const pleinDeFromage = await driver.findElements(By.css('#fromage li'));
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

Les stratégies basés sur link text et partial link text sont
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
