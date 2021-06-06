---
title: "Les pré-requis de WebDriver"
weight: 2
---

Grâce à WebDriver, Selenium prend en charge tous les principaux navigateurs du marché
tels que Chrom(ium), Firefox, Internet Explorer, Opera et Safari.
Dans la mesure du possible, WebDriver pilote le navigateur
en utilisant la prise en charge intégrée du navigateur pour l'automatisation,
bien que tous les navigateurs n'aient pas de support officiel pour la télécommande.

L'objectif de WebDriver est d'émuler l'interaction d'un utilisateur réel
avec le navigateur le plus près possible.
Cela est possible à différents niveaux dans différents navigateurs.
Pour plus de détails sur les différentes particularités des pilotes,
veuillez consulter _[Idiosyncrasies des pilotes]({{<ref "/driver_idiosyncrasies/_index.md">}})_.

Même si tous les pilotes partagent une seule interface utilisateur
pour contrôler le navigateur,
ils ont des façons légèrement différentes de configurer des sessions de navigateur.
Étant donné que de nombreuses implémentations de pilotes sont fournies par des tiers,
ils ne sont pas inclus dans la distribution standard de sélénium.

Instanciation du pilote, gestion des profils et divers paramètres spécifiques au navigateur
sont des exemples de paramètres qui ont des exigences différentes selon le navigateur.
Cette section explique les exigences de base
pour vous familiariser avec les différents navigateurs.

### Ajout d'exécutables à votre PATH

La plupart des pilotes nécessitent un exécutable supplémentaire pour que Selenium puisse communiquer
avec le navigateur. Vous pouvez spécifier manuellement l'emplacement de l'exécutable
avant de démarrer WebDriver, mais cela peut rendre vos tests moins portables,
car les exécutables devront être au même endroit sur chaque machine,
ou inclus dans votre référentiel de code de test.

En ajoutant un dossier contenant les binaires de WebDriver à votre système
chemin d'accès, Selenium pourra localiser les binaires supplémentaires sans
nécessitant votre code de test pour localiser l'emplacement exact du pilote.

* Créez un répertoire dans lequel placer les exécutables, comme 
_C:\WebDriver\bin_ or _/opt/WebDriver/bin_
* Ajoutez le répertoire à votre CHEMIN:
  * Sous Windows - Ouvrez une invite de commande en tant qu'administrateur
     et exécutez la commande suivante
     pour ajouter en permanence le répertoire à votre chemin
     pour tous les utilisateurs de votre machine:

    ```shell
    setx /m path "%path%;C:\WebDriver\bin\"
    ```
  * Utilisateurs Bash sur macOS et Linux - Dans un terminal:

    ```shell
    export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
    ```

* Vous êtes maintenant prêt à tester vos modifications. Fermez toutes 
les invites de commande ouvertes et ouvrez-en une nouvelle.
Tapez le nom de l'un des binaires
dans le dossier que vous avez créé à l'étape précédente,
par exemple:

  ```shell
  chromedriver
  ```
* Si votre `PATH` est configuré correctement,
  vous verrez une sortie relative au démarrage du pilote:

    ```text
    Starting ChromeDriver 2.25.426935 (820a95b0b81d33e42712f9198c215f703412e1a1) on port 9515
    Only local connections are allowed.
    ```

Vous pouvez reprendre le contrôle de votre invite de commande en appuyant sur <kbd> Ctrl + C </kbd>

### Référence rapide

| Browser           | Supported OS               | Maintained by    | Download                                                                     | Issue Tracker                                                                                    |
| ----------------- | -------------------------- | ---------------- | ---------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| Chromium/Chrome   | Windows/macOS/Linux        | Google           | [Downloads](//chromedriver.storage.googleapis.com/index.html)                | [Issues](//bugs.chromium.org/p/chromedriver/issues/list)                                         |
| Firefox           | Windows/macOS/Linux        | Mozilla          | [Downloads](//github.com/mozilla/geckodriver/releases)                       | [Issues](//github.com/mozilla/geckodriver/issues)                                                |
| Edge              | Windows 10                 | Microsoft        | [Downloads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&amp;q=webdriver) |
| Internet Explorer | Windows                    | Selenium Project | [Downloads](//selenium-release.storage.googleapis.com/index.html)            | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE)                                           |
| Safari            | macOS El Capitan and newer | Apple            | Built in                                                                     | [Issues](//bugreport.apple.com/logon)                                                            |
| Opera             | Windows/macOS/Linux        | Opera            | [Downloads](//github.com/operasoftware/operachromiumdriver/releases)           | [Issues](//github.com/operasoftware/operachromiumdriver/issues)                                  |


### Chromium/Chrome

Pour conduire Chrome ou Chromium, vous devez télécharger
[chromedriver](//sites.google.com/a/chromium.org/chromedriver/downloads)
et placez-le dans un dossier qui se trouve sur le chemin de votre système.

Sous Linux ou macOS, cela signifie modifier
la variable d'environnement `PATH`.
Vous pouvez voir quels répertoires, séparés par deux points,
créez le chemin de votre système en exécutant la commande suivante:

```shell
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```

Pour inclure chromedriver sur le chemin si ce n'est pas déjà fait,
assurez-vous d'inclure le répertoire parent du binaire chromedriver.
La ligne suivante définira la variable d'environnement `PATH`
son contenu actuel, plus un chemin supplémentaire ajouté après les deux points:

```shell
$ export PATH="$PATH:/path/to/chromedriver"
```

Lorsque chromedriver est disponible sur votre chemin,
vous devriez pouvoir exécuter l'exécutable _chromedriver_ à partir de n'importe quel répertoire.

Pour instancier une session Chrome / Chromium, vous pouvez procéder comme suit:

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

#Or use the context manager
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

N'oubliez pas que vous devez définir le chemin d'accès à l'exécutable chromedriver.
Ceci est possible en utilisant la ligne suivante:

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

Le chromedriver est implémenté en tant que serveur distant WebDriver
qu'en exposant l'interface proxy d'automatisation interne de Chrome
indique au navigateur quoi faire.


### Firefox

Depuis Selenium 3, Mozilla a pris en charge la mise en œuvre de
Pilote Firefox, avec [geckodriver](//github.com/mozilla/geckodriver).
Le nouveau pilote pour Firefox s'appelle geckodriver et fonctionne avec Firefox
48 et plus récent. Étant donné que Firefox WebDriver est en cours de développement, le
plus la version Firefox est récente, meilleur est le support.

Comme geckodriver est la nouvelle façon par défaut de lancer Firefox, vous pouvez
instancier Firefox de la même manière que Selenium 2:

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

Si vous préférez ne pas définir l'emplacement du geckodriver à l'aide de PATH,
définissez l'emplacement binaire de geckodriver par programme:

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

Il est également possible de définir la propriété au moment de l'exécution:

```shell
mvn test -Dwebdriver.gecko.driver=/path/to/geckodriver
```

Il est actuellement possible de revenir à l'ancienne, plus de fonctionnalités complètes
Pilote Firefox, en installant Firefox [47.0.1](//ftp.mozilla.org/pub/firefox/releases/47.0.1/)
ou [45 ESR](//ftp.mozilla.org/pub/firefox/releases/45.0esr/)
et en spécifiant une capacité souhaitée de **marionnette** comme
**false**. Les versions ultérieures de Firefox ne sont plus compatibles.

### Edge

Edge est le plus récent navigateur de Microsoft, inclus avec Windows 10 et Server 2016.
Les mises à jour d'Edge sont regroupées avec les principales mises à jour Windows,
vous devrez donc télécharger un fichier binaire qui correspond au numéro de version de votre
version de Windows actuellement installée.
Le [site des développeurs Edge](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)
contient des liens vers tous les binaires disponibles. Bugs contre EdgeDriver
la mise en œuvre peut être augmentée avec
[Microsoft](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&q=webdriver).
Si vous souhaitez exécuter des tests sur Edge, mais que vous n'exécutez pas Windows 10, Microsoft
offrir des machines virtuelles gratuites aux testeurs sur le [site des développeurs Edge](//developer.microsoft.com/en-us/microsoft-edge/tools/vms/).

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

Si le pilote Edge n'est pas présent dans votre chemin, vous pouvez définir le chemin à l'aide de
la ligne suivante:

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

Internet Explorer était le navigateur par défaut de Microsoft jusqu'à Windows 10, bien qu'il
est toujours inclus dans Windows 10. Le pilote Internet Explorer est le seul pilote
Le projet Selenium vise à supporter les mêmes versions
[Microsoft considère actuel](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer).
Les versions plus anciennes peuvent fonctionner, mais ne seront pas prises en charge.

Alors que le projet Selenium fournit des binaires pour les 32 bits et 64 bits
versions d'Internet Explorer, il y en a
[limitations](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
avec Internet Explorer 10 et 11 avec le pilote 64 bits, mais en utilisant le 32 bits
le conducteur continue de bien fonctionner. Il convient de noter que, comme Internet Explorer
les préférences sont enregistrées sur le compte de l'utilisateur connecté, certaines
[une configuration supplémentaire est requise](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver#required-configuration).

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

Si le pilote Internet Explorer n'est pas présent dans votre chemin, vous pouvez définir le chemin
en utilisant la ligne suivante:

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

Microsoft propose également un binaire WebDriver pour
[Internet Explorer 11 sur Windows 7 et 8.1](//www.microsoft.com/en-gb/download/details.aspx?id=44069).
Il n'a pas été mis à jour depuis 2014 et est basé sur une version provisoire du
Spécifications W3. [Jim Evans](//jimevansmusic.blogspot.co.uk/2014/09/using-internet-explorer-webdriver.html)
a une excellente synthèse sur la mise en œuvre de Microsoft.

### Opera

Les versions actuelles d'Opera sont construites sur le moteur Chromium,
et WebDriver est désormais pris en charge via la source fermée
[Pilote Opera Chromium](//github.com/operasoftware/operachromiumdriver/releases),
qui peut être [ajouté à votre CHEMIN](#ajout-dexécutables-à-votre-path) ou en tant que
propriété système.

L'instanciation d'une session de pilote est similaire à Firefox et Chromium:

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

High Sierra et plus tard:
* Exécutez la commande suivante à partir du terminal pour la première
l'heure et tapez votre mot de passe à l'invite pour autoriser WebDriver
```shell
safaridriver --enable
```

El Capitan and Sierra:

* Activer le menu développeur à partir des préférences de Safari
* Cochez l'option _Allow Remote Automation_ à partir de avec
le menu Développer
* Exécutez la commande suivante à partir du terminal pour la première
l'heure et tapez votre mot de passe à l'invite pour autoriser WebDriver

```shell
/usr/bin/safaridriver -p 1337</
```

Vous pouvez ensuite démarrer une session de pilote en utilisant:

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


Ceux qui cherchent à automatiser Safari sur iOS devraient regarder
[Projet Appium](//appium.io/). Alors que Safari était auparavant
disponible pour Windows, Apple a depuis longtemps abandonné le support, ce qui en fait
un mauvais choix de plateforme de test.


## Navigateur simulé


### HtmlUnit

HtmlUnit est un "navigateur sans interface graphique pour les programmes Java". Il modélise des documents HTML
et fournit une API qui vous permet d'appeler des pages, de remplir des formulaires, de cliquer sur
liens, etc. Il prend en charge JavaScript et est capable de travailler avec les bibliothèques AJAX,
simulation de Chrome, Firefox ou Internet Explorer selon la configuration
utilisé. Il a été déplacé vers un
[nouvel emplacement](http://htmlunit.sourceforge.net/gettingStarted.html).
La source est maintenue sur svn.

### PhantomJS

PhantomJS est un navigateur sans tête basé sur Webkit, bien qu'une version beaucoup plus ancienne
que celle utilisée par Google Chrome ou Safari. Bien qu'historiquement populaire
choix, il serait désormais sage d'éviter PhantomJS. Le projet a été
non entretenu
[depuis le 5 août](//groups.google.com/forum/#!topic/phantomjs/9aI5d-LDuNE),
Ainsi, même si le Web continuera de changer, PhantomJS ne sera pas mis à jour.
Ce fut après que Google a annoncé la possibilité d'exécuter Chrome sans tête,
quelque chose aussi maintenant offert par Firefox de Mozilla.


