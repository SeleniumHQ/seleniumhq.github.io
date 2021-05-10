---
title: "Recherche de liens"
weight: 7
---

L'utilisation de WebDriver pour parcourir des liens
n'est pas une pratique recommandée ; non pas parce que 
cela ne peut pas être fait, mais parce que ce n'est 
certainement pas l'outil le plus idéal.
WebDriver a besoin de temps pour démarrer,
et peut prendre plusieurs secondes jusqu'à une minute
en fonction de la façon dont votre test est écrit,
juste pour accéder à la page et parcourir le DOM.

Au lieu d'utiliser WebDriver pour cela,
vous pourriez économiser un temps considérable
en exécutant une commande [curl](//curl.haxx.se/),
ou en utilisant une bibliothèque telle que BeautifulSoup,
puisque ces méthodes ne reposent pas
sur la création d'un navigateur et la 
navigation vers une page. Ainsi, vous gagnez un 
temps considérable en n'utilisant pas WebDriver pour
cette tâche.

