---
title: "Migrating from RC to WebDriver"
weight: 2
---

## Comment migrer vers Selenium WebDriver


Une question fréquente lors de l'adoption de Selenium 2 est 
la bonne chose à faire lors de l'ajout de nouveaux tests à un 
ensemble de tests existant? Utilisateurs nouveaux dans le 
Le framework peut commencer par utiliser les nouvelles API 
WebDriver pour écrire leurs tests. Mais qu'en est-il des utilisateurs 
qui ont déjà des suites de tests existants? Ce guide est conçu pour 
montrer comment migrer vos tests existants vers les nouvelles API,
permettant à tous les nouveaux tests d'être écrits en utilisant 
les nouvelles fonctionnalités offertes par WebDriver.

La méthode présentée ici décrit une migration fragmentaire 
vers le WebDriver API sans avoir à tout retravailler en 
une seule poussée massive. Ça signifie que vous pouvez 
accorder plus de temps pour la migration de vos tests 
existants, qui peut vous aider à décider où dépenser vos efforts. 

Ce guide est écrit en utilisant Java, car il a le meilleur support pour
faire la migration. Comme nous fournissons de meilleurs outils pour d'autres langues,
ce guide sera élargi pour inclure ces langues.


## Pourquoi migrer vers WebDriver


Le déplacement d'une suite de tests d'une API à une autre 
API nécessite une énorme quantité d'effort. Pourquoi voudriez-vous, 
vous et votre équipe, envisager cette démarche? Voici quelques 
raisons pour lesquelles vous devriez envisager de migrer vos 
tests de Selenium pour utiliser WebDriver.

* API plus petite et compacte. L'API de WebDriver est plus orientée objet que le
API d'origine Selenium RC. Cela peut faciliter le travail avec.
* Meilleure émulation des interactions utilisateur. Dans la mesure du possible, WebDriver fait
utilisation d'événements natifs pour interagir avec une page Web. Cela de plus près
imite la façon dont vos utilisateurs travaillent avec votre site et vos applications. En plus,
WebDriver propose les API d'interaction utilisateur avancées qui vous permettent de
modélisez des interactions complexes avec votre site.
* Prise en charge par les fournisseurs de navigateurs. 
Opera, Mozilla et Google sont tous actifs  participants au développement 
de WebDriver, et chacun a des ingénieurs travaillant pour 
améliorer le cadre. Souvent, cela signifie que la prise en charge de WebDriver 
est intégré dans le navigateur lui-même: vos tests 
s'exécutent aussi rapidement et aussi stable que possible.


## Avant de commencer


Afin de rendre le processus de migration aussi indolore que possible, faites
assurez-vous que tous vos tests fonctionnent correctement avec 
la dernière version de Selenium. Cela peut sembler évident, 
mais il vaut mieux le dire!


## Commencer


La première étape lors du démarrage de la migration 
consiste à modifier la façon dont vous obtenez votre 
instance de Selenium. Lorsque vous utilisez Selenium RC, 
cela se fait comme suit:

```java
Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.yoursite.com");
selenium.start();
```

Cela devrait être remplacé comme suit:

```java
WebDriver driver = new FirefoxDriver();
Selenium selenium = new WebDriverBackedSelenium(driver, "http://www.yoursite.com");
```

## Prochaines étapes


Une fois vos tests exécutés sans erreur, la prochaine étape consiste à migrer
le code de test réel pour utiliser les API WebDriver. Selon la façon dont bien
abstrait votre code est, cela peut être un processus court ou long.
Dans les deux cas, l'approche est la même et peut se résumer simplement:
modifier le code pour utiliser la nouvelle API lorsque vous venez de le modifier.

Si vous devez extraire l'implémentation WebDriver sous-jacente de
l'instance de Selenium, vous pouvez simplement la lancer sur WrapsDriver:

```java
WebDriver driver = ((WrapsDriver) selenium).getWrappedDriver();
```

Cela vous permet de continuer à transmettre l'instance Selenium comme
normal, mais pour déballer l'instance WebDriver comme requis.

À un moment donné, votre base de code utilisera principalement les nouvelles API.
À ce stade, vous pouvez inverser la relation en utilisant WebDriver
et instancier une instance de Selenium à la demande:

```java
Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
```

## Problèmes communs


Heureusement, vous n'êtes pas la première personne 
à traverser cette migration, voici donc quelques problèmes 
communs que d'autres ont vus, et comment les résoudre.


### Cliquer et taper est plus complet


Un schéma courant dans un test Selenium RC 
est de voir quelque chose comme:

```java
selenium.type("name", "exciting tex");
selenium.keyDown("name", "t");
selenium.keyPress("name", "t");
selenium.keyUp("name", "t");
```
    
Cela repose sur le fait que "type" remplace simplement le contenu du
élément identifié sans déclencher également tous les événements qui normalement
être renvoyé si un utilisateur interagit avec la page. Les invocations directes finales
de "clé*" provoque le déclenchement des gestionnaires JS comme prévu.

Lors de l'utilisation de WebDriverBackedSelenium, le résultat du remplissage du formulaire
serait "texttt excitant": pas ce que vous attendez! La raison de cela
est que WebDriver émule plus précisément le comportement des utilisateurs, et aura donc
a tiré des événements tout au long.

Ce même fait peut parfois déclencher un chargement de page plus tôt qu'il ne le ferait
faire dans un test de sélénium 1. Vous pouvez dire que cela s'est produit si un
"StaleElementException" est levé par WebDriver.


### WaitForPageToLoad revient trop tôt

Découvrir quand un chargement de page est terminé est 
une entreprise délicate. Voulons-nous "lorsque l'événement de 
chargement se déclenche", "lorsque toutes les requêtes AJAX 
sont terminées", "lorsque il n'y a pas de trafic réseau "," 
lorsque document.readyState a changé "ou quelque chose
sinon entièrement?

WebDriver tente de simuler le comportement d'origine de Selenium, 
mais cela ne fonctionnent toujours parfaitement pour diverses raisons. 
La raison la plus courante est que c'est difficile de faire la 
différence entre un chargement de page n'ayant pas encore commencé et un 
chargement de page terminé entre les appels de méthode. Cela signifie parfois que
le contrôle revient à votre test avant que la page ne soit 
terminée (ou même commencée!) chargement.

La solution est d'attendre quelque chose de spécifique. 
Généralement, cela pourrait être pour l'élément avec lequel 
vous souhaitez interagir ensuite, ou pour une variable 
Javascript à définir sur une valeur spécifique. Un exemple serait:

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
WebElement element= wait.until(visibilityOfElementLocated(By.id("some_id")));
```

Où "visibilityOfElementLocated" est implémenté comme:

```java
public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
  return new ExpectedCondition<WebElement>() {
    public WebElement apply(WebDriver driver) {
      WebElement toReturn = driver.findElement(locator);
      if (toReturn.isDisplayed()) {
        return toReturn;
      }
      return null;
    }
  };
}
```

Cela peut sembler complexe, mais c'est presque tout le code de la plaque de la chaudière. Le seul
bit intéressant est que le "ExpectedCondition" sera évalué à plusieurs reprises
jusqu'à ce que la méthode "apply" renvoie quelque chose qui n'est ni "null"
ni Boolean.FALSE.

Bien sûr, l'ajout de tous ces appels "d'attente" peut 
encombrer votre code. Si c'est le cas, et vos besoins 
sont simples, pensez à utiliser les attentes implicites:

```java
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
```

En faisant cela, chaque fois qu'un élément est localisé, 
si l'élément n'est pas présent, l'emplacement est réessayé jusqu'à 
ce qu'il soit présent ou jusqu'à 30 secondes
passé.

### Trouver par XPath ou sélecteurs CSS ne fonctionne pas toujours, mais cela fonctionne avec Selenium 1

Dans Selenium 1, il était courant que xpath utilise une 
bibliothèque groupée plutôt que les capacités du navigateur 
lui-même. WebDriver utilisera toujours le natif méthodes de 
navigateur, sauf s'il n'y a pas d'autre alternative. Cela signifie 
que xpath complexe les expressions peuvent se briser sur certains navigateurs.

Les sélecteurs CSS dans Selenium 1 ont été implémentés à l'aide de 
la bibliothèque Sizzle. Cette implémente un surensemble de la 
spécification CSS Selector, et il n'est pas toujours clair où vous 
avez franchi la ligne. Si vous utilisez WebDriverBackedSelenium et 
utilisez un Sizzle locator au lieu d'un CSS Selector pour trouver des 
éléments, un avertissement être connecté à la console. Cela vaut la 
peine de prendre le temps de les chercher, en particulier si les tests 
échouent faute de pouvoir trouver des éléments.

### Il n'y a pas de Browserbot

Selenium RC était basé sur Selenium Core, et donc lorsque vous avez exécuté
JavaScript, vous pouvez accéder à des morceaux de Selenium Core pour faciliter les choses.
Comme WebDriver n'est pas basé sur Selenium Core, cela n'est plus possible.
Comment savoir si vous utilisez Selenium Core? Facile! Regardez juste pour voir
si vos appels "getEval" ou similaires utilisent "Selenium" ou "browserbot"
dans le Javascript évalué.

Vous utilisez peut-être le navigateur pour obtenir un descripteur 
de la fenêtre actuelle ou document du test. Heureusement, WebDriver 
évalue toujours JS dans le contexte de la fenêtre en cours, vous pouvez 
donc utiliser directement "fenêtre" ou "document".

Vous pouvez également utiliser le navigateur pour localiser des éléments.
Dans WebDriver, l'idiome pour ce faire est de localiser d'abord l'élément,
puis passez cela comme argument au Javascript. Donc:

```java
String name = selenium.getEval(
    "selenium.browserbot.findElement('id=foo', browserbot.getCurrentWindow()).tagName");
```

devient:

```java
WebElement element = driver.findElement(By.id("foo"));
String name = (String) ((JavascriptExecutor) driver).executeScript(
    "return arguments[0].tagName", element);
```
        
Remarquez comment la variable "element" passée 
apparaît comme premier élément dans le tableau 
"arguments" standard de JS.       


### Executing Javascript Doesn't Return Anything


JavascriptExecutor de WebDriver encapsulera tous les 
JS et l'évaluera comme une expression anonyme. Cela 
signifie que vous devez utiliser le mot-clé "retour":

```java
String title = selenium.getEval("browserbot.getCurrentWindow().document.title");
```

devient:

```java
((JavascriptExecutor) driver).executeScript("return document.title;");
```
    
