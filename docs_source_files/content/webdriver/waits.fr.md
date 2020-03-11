---
title: "Waits"
weight: 4
---

WebDriver peut généralement être considéré comme ayant une API de blocage.
Parce que c'est une bibliothèque hors processus qui
_instruit_ le navigateur à faire,
et parce que la plate-forme Web a une nature intrinsèquement asynchrone,
WebDriver ne suit pas l'état actif en temps réel du DOM.
Cela s'accompagne de quelques défis que nous aborderons ici.

De l'expérience,
la plupart des intermittents résultant de l'utilisation de Selenium et de WebDriver
sont liés aux conditions de concurrence qui se produisent entre
le navigateur et les instructions de l'utilisateur.
Un exemple pourrait être que l'utilisateur demande 
au navigateur de naviguer vers une page,
obtient alors une erreur **no such element**
lorsque vous essayez de trouver un élément.

Considérez le document suivant:

```html
<!doctype html>
<meta charset=utf-8>
<title>Race Condition Example</title>

<script>
  var initialised = false;
  window.addEventListener("load", function() {
    var newElement = document.createElement("p");
    newElement.textContent = "Hello from JavaScript!";
    document.body.appendChild(newElement);
    initialised = true;
  });
</script>
```

Les instructions WebDriver peuvent sembler assez innocentes:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.get("file:///race_condition.html");
WebElement element = driver.findElement(By.tagName("p"));
assertEquals(element.getText(), "Hello from JavaScript!");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.navigate("file:///race_condition.html")
el = driver.find_element_by_tag_name("p")
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.Navigate().GoToUrl("file:///race_condition.html");
IWebElement element = driver.FindElement(By.TagName("p"));
assertEquals(element.Text, "Hello from JavaScript!");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navigate to URL
  driver.get 'file:///race_condition.html'

  # Get and store Paragraph Text
  search_form = driver.find_element(:css,'p').text

  "Hello from JavaScript!".eql? search_form
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
await driver.get('file:///race_condition.html');
const element = driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("file:///race_condition.html")
val element = driver.findElement(By.tagName("p"))
assert(element.text == "Hello from JavaScript!")
  {{< / code-panel >}}
{{< / code-tab >}}

Le problème ici est que la valeur par défaut
[stratégie de chargement de page]({{<ref "/webdriver/page_loading_strategy.fr.md">}})
utilisé dans WebDriver écoute le `document.readyState`
pour passer à `"complete"` avant de revenir de l'appel à _navigate_.
Parce que l'élément `p` est
ajouté après le chargement du document,
ce script WebDriver _peut_ être intermittent.
Elle "pourrait" être intermittente car aucune garantie ne peut être apportée
sur les éléments ou événements qui se déclenchent de manière asynchrone
sans attendre ou bloquer explicitement ces événements.

Heureusement, en utilisant le jeu d'instructions normal disponible sur
l'interface [_WebElement_] ({{<ref "/webdriver/web_element.fr.md">}}) - telle
comme _WebElement.click_ et _WebElement.sendKeys_ — sont
garantie d'être synchrone,
en ce que les appels de fonction ne reviendront pas
(ou le rappel ne se déclenchera pas dans les langues de style rappel)
jusqu'à ce que la commande soit terminée dans le navigateur.
Les API d'interaction utilisateur avancées,
[_Keyboard_]({{<ref "/webdriver/keyboard.fr.md">}})
et [_Mouse_]({{<ref "/support_packages/mouse_and_keyboard_actions_in_detail.fr.md">}}),
sont des exceptions car ils sont explicitement destinés à
"Faire ce que je dis", commandes asynchrones.

L'attente, c'est l'exécution automatisée des tâches
s'est écoulé un certain temps avant de passer à l'étape suivante.

Pour surmonter le problème des conditions de course
entre le navigateur et votre script WebDriver,
la plupart des clients Selenium sont livrés avec un package _wait_.
Lorsque vous utilisez une attente,
vous utilisez ce qui est communément appelé
en tant que [_explicit wait_](#explicit-wait).

## Explicit wait

_Explicit Wait_ sont disponibles pour les clients Selenium
pour les langages procéduraux impératifs.
Ils permettent à votre code d'arrêter l'exécution du programme,
ou geler le fil,
jusqu'à ce que le _condition_ que vous passez, il résout.
La condition est appelée avec une certaine fréquence
jusqu'à ce que le délai d'attente soit écoulé.
Cela signifie que tant que la condition renvoie une valeur fausse,
il continuera d'essayer et d'attendre.

Étant donné que les attentes explicites vous permettent 
d'attendre qu'une condition se produise,
ils font un bon ajustement pour synchroniser 
l'état entre le navigateur et son DOM,
et votre script WebDriver.

Pour remédier à notre jeu d'instructions de buggy antérieur,
nous pourrions utiliser une attente pour avoir l'appel _findElement_
attendre que l'élément ajouté dynamiquement du script
a été ajouté au DOM:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new ChromeDriver();
driver.get("https://google.com/ncr");
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
// Initialize and wait till element(link) became clickable - timeout in 10 seconds
WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
// Print the first result
System.out.println(firstResult.getText());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.ui import WebDriverWait
def document_initialised(driver):
    return driver.execute_script("return initialised")

driver.navigate("file:///race_condition.html")
WebDriverWait(driver).until(document_initialised)
el = driver.find_element_by_tag_name("p")
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver = new ChromeDriver();
driver.Url = "https://www.google.com/ncr";
driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);
            
WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
IWebElement firstResult = wait.Until(e => e.FindElement(By.XPath("//a/h3")));

Console.WriteLine(firstResult.Text);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
wait = Selenium::WebDriver::Wait.new(:timeout => 10)

def document_initialised(driver)
  driver.execute_script('return initialised')
end

begin
  driver.get 'file:///race_condition.html'
  wait.until{document_initialised driver}
  search_form = driver.find_element(:css,'p').text
  "Hello from JavaScript!".eql? search_form
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const documentInitialised = () =>
    driver.executeScript('return initialised');

await driver.get('file:///race_condition.html');
await driver.wait(() => documentInitialised(), 10000);
const element = driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("https://google.com/ncr")
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
// Initialize and wait till element(link) became clickable - timeout in 10 seconds
val firstResult = WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
// Print the first result
println(firstResult.text)
  {{< / code-panel >}}
{{< / code-tab >}}

On passe la condition comme référence de fonction
que le _wait_ s'exécutera à plusieurs reprises jusqu'à ce 
que sa valeur de retour soit véridique.
Une valeur de retour "véridique" est tout 
ce qui est évalué comme booléen true
dans la langue utilisée, comme une chaîne, 
un nombre, un booléen,
un objet (dont un _WebElement_),
ou une séquence ou une liste remplie (non vide).
Cela signifie qu'une liste vide est 
évaluée comme fausse. Lorsque la condition 
est vraie et que l'attente de blocage est abandonnée,
la valeur de retour de la condition 
devient la valeur de retour de l'attente.

Avec cette connaissance,
et parce que l'utilitaire d'attente n'ignore
pas une telle erreur d'élément par défaut,
nous pouvons refactoriser nos 
instructions pour être plus concis:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
          .until(driver -> driver.findElement(By.name("q")));
assertEquals(foo.getText(), "Hello from JavaScript!"); 
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.ui import WebDriverWait

driver.navigate("file:///race_condition.html")
el = WebDriverWait(driver).until(lambda d: d.find_element_by_tag_name("p"))
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
 using (var driver = new FirefoxDriver())
 {
        var foo = new WebDriverWait(driver, TimeSpan.FromSeconds(3))
                        .Until(drv => drv.FindElement(By.Name("q")));
        Debug.Assert(foo.Text.Equals("Hello from JavaScript!"));
} 
{{< / code-panel >}}
  {{< code-panel language="ruby" >}}
  driver.get 'file:///race_condition.html'
  wait = Selenium::WebDriver::Wait.new(:timeout => 10)
  ele = wait.until { driver.find_element(css: 'p')}
  foo = ele.text
  assert_match foo, 'Hello from JavaScript' 
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let ele = await driver.wait(until.elementLocated(By.css('p')),10000);
let foo = await ele.getText();
assert(foo == "Hello from JavaScript");
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("file:///race_condition.html")
val ele = WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")))
assert(ele.text == "Hello from JavaScript!")
  {{< / code-panel >}}
{{< / code-tab >}}

Dans cet exemple, nous passons une fonction anonyme
(mais nous pourrions également le définir explicitement
comme nous l'avons fait précédemment afin qu'il puisse être réutilisé).
Le premier et le seul argument qui est passé à notre condition
est toujours une référence à notre objet pilote, _WebDriver_
(appelé `d` dans l'exemple).
Dans un environnement multi-thread, vous devez être prudent
pour opérer sur la référence du conducteur transmise à la condition
plutôt que la référence au pilote dans la portée externe.

Parce que l'attente n'avalera pas de telles erreurs d'élément
qui sont levées lorsque l'élément n'est pas trouvé,
la condition réessayera jusqu'à ce que l'élément soit trouvé.
Ensuite, il prendra la valeur de retour, en tant qu'élément Web,
et le transmettre à notre script.

Si la condition échoue,
par exemple. une valeur de retour véridique de la condition n'est jamais atteinte,
l'attente générera / déclenchera une erreur / exception appelée erreur de temporisation.

### Options

La condition d'attente peut être personnalisée 
pour répondre à vos besoins. Parfois, il n'est pas 
nécessaire d'attendre toute l'étendue du délai d'expiration par défaut,
car la pénalité pour ne pas avoir réussi peut être chère.

L'attente vous permet de passer un argument pour remplacer le délai:

{{< code-tab >}}
  {{< code-panel language="java" >}}
new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
WebDriverWait(driver, timeout=3).until(some_condition)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new WebDriverWait(driver, TimeSpan.FromSeconds(3)).Until(ExpectedConditions.ElementToBeClickable(By.XPath("//a/h3")));  
{{< / code-panel >}}
  {{< code-panel language="ruby" >}}
wait = Selenium::WebDriver::Wait.new(:timeout => 10)

wait.until { driver.find_element(:id, 'message').displayed? }
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  await driver.wait(until.elementLocated(By.id('foo')), 30000);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
  {{< / code-panel >}}
{{< / code-tab >}}

### Expected conditions

Parce que c'est assez courant
d'avoir à synchroniser le DOM et vos instructions,
la plupart des clients sont également livrés avec 
un ensemble de _expected conditions_ prédéfinies.
Comme son nom l'indique, ce sont des conditions 
prédéfinies pour les opérations d'attente fréquentes.

Les conditions disponibles dans les différentes 
liaisons linguistiques varient, mais ceci est une 
liste non exhaustive de quelques-uns:

<!-- TODO(ato): Fill in -->
* alert is present
* element exists
* element is visible
* title contains
* title is
* element staleness
* visible text

Vous pouvez vous référer à la documentation 
de l'API pour chaque liaison client
pour trouver une liste exhaustive 
des conditions attendues:

* Java's [org.openqa.selenium.support.ui.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) class
* Python's [selenium.webdriver.support.expected_conditions](//seleniumhq.github.io/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html?highlight=expected) class
* .NET's [OpenQA.Selenium.Support.UI.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/dotnet/html/T_OpenQA_Selenium_Support_UI_ExpectedConditions.htm) type

## Implicit wait

Il existe un deuxième type d'attente distinct de
[attente explicite](#explicit-wait) appelée _implicit wait_.
En attendant implicitement, WebDriver interroge le DOM
pendant une certaine durée lors de la recherche de l'élément _any_.
Cela peut être utile lorsque certains éléments de la page Web
ne sont pas disponibles immédiatement et nécessitent un 
certain temps de chargement.

Attendre implicitement que les éléments apparaissent 
est désactivé par défaut et devra être 
activé manuellement sur une base par session.
Mélange [attentes explicites](#explicit-wait) et attentes implicites
entraînera des conséquences inattendues, à 
savoir les attentes de sommeil pour le maximum
même si l'élément est disponible ou 
si la condition est vraie.

*avertissement:*
Ne mélangez pas les attentes implicites et explicites.
Cela peut entraîner des temps d'attente imprévisibles.
Par exemple, définir une attente implicite de 10 secondes
et une attente explicite de 15 secondes
pourrait entraîner un délai d'attente après 20 secondes.

Une attente implicite consiste à dire à 
WebDriver d'interroger le DOM
pendant un certain temps lorsque vous 
essayez de trouver un élément ou des éléments
s'ils ne sont pas immédiatement disponibles.
Le paramètre par défaut est 0, ce qui signifie désactivé.
Une fois définie, l'attente implicite est 
définie pour la durée de vie de la session.

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get("http://somedomain/url_that_delays_loading");
WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver = Firefox()
driver.implicitly_wait(10)
driver.get("http://somedomain/url_that_delays_loading")
my_dynamic_element = driver.find_element_by_id("myDynamicElement")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new ChromeDriver();
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
driver.Url = "http://somedomain/url_that_delays_loading";
IWebElement dynamicElement = driver.FindElement(By.Name("dynamicElement"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
driver.manage.timeouts.implicit_wait = 10

begin
  driver.get 'http://somedomain/url_that_delays_loading'
  search_form = driver.find_element(:id,'dynamic_element')
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
(async function(){

// Apply timeout for 10 seconds
await driver.manage().setTimeouts( { implicit: 10000 } );

// Navigate to url
await driver.get('http://somedomain/url_that_delays_loading');

let webElement = driver.findElement(By.id("myDynamicElement"));

}()); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = FirefoxDriver()
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
driver.get("http://somedomain/url_that_delays_loading")
val myDynamicElement = driver.findElement(By.id("myDynamicElement"))
  {{< / code-panel >}}
{{< / code-tab >}}

## FluentWait

L'instance FluentWait définit la durée 
maximale d'attente d'une condition,
ainsi que la fréquence à laquelle vérifier l'état.

Les utilisateurs peuvent configurer 
l'attente pour ignorer des types spécifiques 
d'exceptions en attendant,
comme `NoSuchElementException` lors 
de la recherche d'un élément sur la page.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Waiting 30 seconds for an element to be present on the page, checking
// for its presence once every 5 seconds.
Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);

WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
  public WebElement apply(WebDriver driver) {
    return driver.findElement(By.id("foo"));
  }
});
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver = Firefox()
driver.get("http://somedomain/url_that_delays_loading")
wait = WebDriverWait(driver, 10, poll_frequency=1, ignored_exceptions=[ElementNotVisibleException, ElementNotSelectableException])
element = wait.until(EC.element_to_be_clickable((By.XPATH, "//div")))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using (var driver = new FirefoxDriver())
{
  WebDriverWait wait = new WebDriverWait(driver, timeout: TimeSpan.FromSeconds(30))
  {
      PollingInterval = TimeSpan.FromSeconds(5),
  };
  wait.IgnoreExceptionTypes(typeof(NoSuchElementException));

  var foo = wait.Until(drv => drv.FindElement(By.Id("foo")));
}
{{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
exception = Selenium::WebDriver::Error::NoSuchElementError

begin
  driver.get 'http://somedomain/url_that_delays_loading'
  wait = Selenium::WebDriver::Wait.new(timeout: 30, interval: 5, message: 'Timed out after 30 sec', ignore: exception)
  foo = wait.until { driver.find_element(id: 'foo')}
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, until} = require('selenium-webdriver');

(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    await driver.get('http://somedomain/url_that_delays_loading');
    // Waiting 30 seconds for an element to be present on the page, checking
    // for its presence once every 5 seconds.
    let foo = await driver.wait(until.elementLocated(By.id('foo')), 30000, 'Timed out after 30 seconds', 5000);
})(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val wait = FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(3))
        .ignoring(NoSuchElementException::class.java)

val foo = wait.until {it.findElement(By.id("foo")) }
  {{< / code-panel >}}
{{< / code-tab >}}

