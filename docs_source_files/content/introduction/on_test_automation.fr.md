---
title: "A propos du test automatisé"
weight: 2
---

Commencez par vous demander si vous avez vraiment besoin ou non d'un navigateur.
Il est probable qu'à un moment donné, si vous travaillez sur une application Web complexe,
vous devrez ouvrir un navigateur et le tester.

Les tests fonctionnels pour l'utilisateur final, tels que les tests au sélénium, sont cependant coûteux à exécuter.
En outre, ils nécessitent généralement une infrastructure substantielle
être en place pour fonctionner efficacement.
C'est une bonne règle de toujours se demander si ce que l'on veut tester
peut être fait en utilisant des approches de test plus légères telles que des tests unitaires
ou avec une approche de niveau inférieur.

Une fois que vous avez déterminé que vous êtes dans le domaine des tests de navigateurs Web,
et vous avez votre environnement Selenium prêt à commencer à écrire des tests,
vous effectuerez généralement une combinaison de trois étapes:

* Configurer les données
* Effectuer un ensemble discret d'actions
* Évaluez les résultats

Vous voudrez garder ces étapes aussi courtes que possible;
une ou deux opérations devraient suffire la plupart du temps.
L'automatisation des navigateurs a la réputation d'être "feuilletée",
mais en réalité, c'est parce que les utilisateurs en demandent souvent trop.
Dans les chapitres suivants, nous reviendrons sur les techniques que vous pouvez utiliser
pour atténuer les problèmes intermittents apparents dans les tests,
en particulier sur la façon de [surmonter les conditions de course]({{<ref "/webdriver/waits.fr.md">}})
entre le navigateur et WebDriver.

Garder vos tests courts
et en utilisant le navigateur Web uniquement lorsque vous n'avez absolument aucune alternative,
vous pouvez avoir de nombreuses tâches avec un flocon de neige minimal.

Un avantage distinct des tests au sélénium
est leur capacité inhérente à tester tous les composants de l'application,
du backend au frontend, du point de vue d'un utilisateur.
En d'autres termes, alors que les tests fonctionnels peuvent être coûteux à exécuter,
ils englobent également de grandes portions critiques pour l'entreprise à la fois.


### Exigences de test

Comme mentionné précédemment, les tests au sélénium peuvent être coûteux à exécuter.
Dans quelle mesure cela dépend du navigateur sur lequel vous exécutez les tests,
mais historiquement, le comportement des navigateurs a tellement varié qu'il a souvent
était un objectif déclaré de contre-tester avec plusieurs navigateurs.

Selenium vous permet d'exécuter les mêmes instructions sur plusieurs navigateurs
sur plusieurs systèmes d'exploitation,
mais l'énumération de tous les navigateurs possibles,
leurs différentes versions et les nombreux systèmes d'exploitation sur lesquels ils s'exécutent
deviendra rapidement une entreprise non triviale.

### Commençons par un exemple

Larry a écrit un site Web qui permet aux utilisateurs de commander leur
licornes personnalisées.

Le flux de travail général (ce que nous appellerons le "chemin heureux") est quelque chose
comme ça:

* Créer un compte
* Configurer la licorne
* Ajoutez-le au panier
* Vérifiez et payez
* Donnez votre avis sur la licorne


Il serait tentant d'écrire un grand script Selenium
pour effectuer toutes ces opérations, beaucoup essaieront.
**Résister à la tentation!**
Cela entraînera un test qui
a) prend beaucoup de temps,
b) sera soumis à des problèmes courants concernant les problèmes de synchronisation du rendu de page, et
c) est telle qu'en cas d'échec, il ne vous donnera pas une méthode concise et "lisible" pour diagnostiquer ce qui a mal tourné.

La stratégie préférée pour tester ce scénario serait
pour le décomposer en une série de tests indépendants et rapides,
chacun a une «raison» d'exister.

Imaginons que vous souhaitiez tester la deuxième étape:
Configuration de votre licorne.
Il effectuera les actions suivantes:

* Créer un compte
* Configurer une licorne

Notez que nous ignorons le reste de ces étapes,
nous testerons le reste du workflow dans d'autres petits cas de test discrets
après avoir fini avec celui-ci.

Pour commencer, vous devez créer un compte.
Vous avez ici quelques choix à faire:

* Voulez-vous utiliser un compte existant?
* Voulez-vous créer un nouveau compte?
* Y a-t-il des propriétés spéciales d'un tel utilisateur qui doivent être
 pris en compte avant le début de la configuration?

Quelle que soit la façon dont vous répondez à cette question,
la solution consiste à l'intégrer à la partie "configurer les données" du test.
Si Larry a exposé une API qui vous permet (ou n'importe qui)
pour créer et mettre à jour des comptes utilisateurs,
assurez-vous de l'utiliser pour répondre à cette question.
Si possible, vous souhaitez lancer le navigateur uniquement après avoir un utilisateur "en main",
dont vous pouvez simplement vous connecter avec les informations d'identification.

Si chaque test pour chaque workflow commence par la création d'un compte utilisateur,
plusieurs secondes seront ajoutées à l'exécution de chaque test.
Appeler une API et parler à une base de données sont rapides,
Opérations "sans tête" qui ne nécessitent pas le processus coûteux de
ouvrir un navigateur, naviguer vers les bonnes pages,
cliquer et attendre que les formulaires soient soumis, etc.

Idéalement, vous pouvez traiter cette phase de configuration en une seule ligne de code,
qui s'exécutera avant le lancement de tout navigateur:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
User user = UserFactory.createCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
AccountPage accountPage = loginAs(user.getEmail(), user.getPassword());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Create a user who has read-only permissions--they can configure a unicorn,
# but they do not have payment information set up, nor do they have
# administrative privileges. At the time the user is created, its email
# address and password are randomly generated--you don't even need to
# know them.
user = user_factory.create_common_user() #This method is defined elsewhere.

# Log in as this user.
# Logging in on this site takes you to your personal "My Account" page, so the
# AccountPage object is returned by the loginAs method, allowing you to then
# perform actions from the AccountPage.
account_page = login_as(user.get_email(), user.get_password())
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
User user = UserFactory.CreateCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
AccountPage accountPage = LoginAs(user.Email, user.Password);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Create a user who has read-only permissions--they can configure a unicorn,
# but they do not have payment information set up, nor do they have
# administrative privileges. At the time the user is created, its email
# address and password are randomly generated--you don't even need to
# know them.
user = UserFactory.create_common_user #This method is defined elsewhere.

# Log in as this user.
# Logging in on this site takes you to your personal "My Account" page, so the
# AccountPage object is returned by the loginAs method, allowing you to then
# perform actions from the AccountPage.
account_page = login_as(user.email, user.password)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
var user = userFactory.createCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
var accountPage = loginAs(user.email, user.password);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
val user = UserFactory.createCommonUser() //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
val accountPage = loginAs(user.getEmail(), user.getPassword())
  {{< / code-panel >}}
{{< / code-tab >}}

Comme vous pouvez l'imaginer, la `UserFactory` peut être étendue
pour fournir des méthodes telles que `createAdminUser ()` et `createUserWithPayment ()`.
Le fait est que ces deux lignes de code ne vous distraient pas du but ultime de ce test:
configurer une licorne.

Les subtilités du [modèle d'objet de page]({{<ref "/guidelines_and_recommendations/page_object_models.fr.md">}})
sera discuté dans les chapitres suivants, mais nous présenterons le concept ici:

Vos tests doivent être composés d'actions,
réalisée du point de vue de l'utilisateur,
dans le cadre des pages du site.
Ces pages sont stockées en tant qu'objets,
qui contiendra des informations spécifiques sur la composition de la page Web
et comment les actions sont effectuées -
dont très peu devrait vous concerner en tant que testeur.

Quel genre de licorne veux-tu?
Vous voudrez peut-être du rose, mais pas nécessairement.
Le violet a été assez populaire ces derniers temps.
A-t-elle besoin de lunettes de soleil? Tatouages ​​étoiles?
Ces choix, bien que difficiles, sont votre principale préoccupation en tant que testeur -
vous devez vous assurer que votre centre de traitement des commandes
envoie la bonne licorne à la bonne personne,
et cela commence par ces choix.

Notez que nulle part dans ce paragraphe nous ne parlons de boutons,
des champs, des listes déroulantes, des boutons radio ou des formulaires Web.
**Vos tests ne devraient pas non plus!**
Vous voulez écrire votre code comme l'utilisateur essayant de résoudre son problème.
Voici une façon de procéder (à partir de l'exemple précédent):

{{< code-tab >}}
  {{< code-panel language="java" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
AddUnicornPage addUnicornPage = accountPage.addUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# The Unicorn is a top-level Object--it has attributes, which are set here.
# This only stores the values; it does not fill out any web forms or interact
# with the browser in any way.
sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Since we're already "on" the account page, we have to use it to get to the
# actual place where you configure unicorns. Calling the "Add Unicorn" method
# takes us there.
add_unicorn_page = account_page.add_unicorn()

# Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
# its createUnicorn() method. This method will take Sparkles' attributes,
# fill out the form, and click submit.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.Purple, UnicornAccessories.Sunglasses, UnicornAdornments.StarTattoos);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
AddUnicornPage addUnicornPage = accountPage.AddUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.CreateUnicorn(sparkles);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# The Unicorn is a top-level Object--it has attributes, which are set here.
# This only stores the values; it does not fill out any web forms or interact
# with the browser in any way.
sparkles = Unicorn.new('Sparkles', UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Since we're already "on" the account page, we have to use it to get to the
# actual place where you configure unicorns. Calling the "Add Unicorn" method
# takes us there.
add_unicorn_page = account_page.add_unicorn

# Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
# its createUnicorn() method. This method will take Sparkles' attributes,
# fill out the form, and click submit.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here.
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
var sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.

var addUnicornPage = accountPage.addUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
var unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);

  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
val sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
val addUnicornPage = accountPage.addUnicorn()

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles)

  {{< / code-panel >}}
{{< / code-tab >}}

Maintenant que vous avez configuré votre licorne,
vous devez passer à l'étape 3: vous assurer que cela a réellement fonctionné.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
assert unicorn_confirmation_page.exists(sparkles), "Sparkles should have been created, with all attributes intact"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.True(unicornConfirmationPage.Exists(sparkles), "Sparkles should have been created, with all attributes intact");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
expect(unicorn_confirmation_page.exists?(sparkles)).to be, 'Sparkles should have been created, with all attributes intact'
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
assert(unicornConfirmationPage.exists(sparkles), "Sparkles should have been created, with all attributes intact");

  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles))
  {{< / code-panel >}}
{{< / code-tab >}}

Notez que le testeur n'a toujours rien fait d'autre que parler des licornes dans ce code -
pas de boutons, pas de localisateurs, pas de commandes de navigateur.
Cette méthode de _modélisation_ de l'application
vous permet de garder ces commandes de niveau test en place et immuables,
même si Larry décide la semaine prochaine qu'il n'aime plus Ruby-on-Rails
et décide de réimplémenter l'ensemble du site
dans les dernières fixations Haskell avec un frontal Fortran.

Vos objets de page nécessiteront une petite maintenance afin de
se conformer à la refonte du site,
mais ces tests resteront les mêmes.
En prenant cette conception de base,
vous souhaiterez continuer à parcourir vos workflows avec le moins d'étapes possibles face au navigateur.
Votre prochain workflow impliquera l'ajout d'une licorne au panier d'achat.
Vous voudrez probablement de nombreuses itérations de ce test afin de vous assurer que le chariot conserve correctement son état:
Y a-t-il plus d'une licorne dans le panier avant de commencer?
Combien peuvent tenir dans le panier?
Si vous en créez plusieurs avec le même nom et / ou les mêmes fonctionnalités, est-ce que cela cassera?
Gardera-t-il seulement celui existant ou en ajoutera-t-il un autre?

Chaque fois que vous vous déplacez dans le flux de travail,
vous voulez éviter d'avoir à créer un compte,
connectez-vous en tant qu'utilisateur et configurez la licorne.
Idéalement, vous pourrez créer un compte
et pré-configurer une licorne via l'API ou la base de données.
Il vous suffit ensuite de vous connecter en tant qu'utilisateur, de localiser Sparkles,
et l'ajouter au panier.

### Automatiser ou ne pas automatiser?

L'automatisation est-elle toujours avantageuse? Quand faut-il décider d'automatiser le test
cas?

Il n'est pas toujours avantageux d'automatiser les cas de test. Il y a des moments où
des tests manuels peuvent être plus appropriés. Par exemple, si l'utilisateur de l'application
l'interface changera considérablement dans un avenir proche, alors toute automatisation
pourrait avoir besoin d'être réécrit de toute façon. De plus, parfois, il n'y a tout simplement pas assez
le temps de construire l'automatisation des tests. À court terme, les tests manuels peuvent être plus
efficace. Si une demande a un délai très serré, il n’existe actuellement aucun
l'automatisation des tests est disponible, et il est impératif que les tests soient effectués dans les
ce délai, puis le test manuel est la meilleure solution.
