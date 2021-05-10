---
title: "Authentication à double facteur"
weight: 1
---

L'authentification à double facteur (A2F) est un mécanisme d'autorisation dans
lequel un mot de passe unique, basé sur le temps (One Time Password - OTP),
est généré par un programme externe accessible (souvent
mobile) comme "Google Authenticator", "Microsoft Authenticator", etc. ; ou par
la réception d'un SMS ou courriel pour permettre l'authentification.

Automatiser ce mécanisme harmonieusement et de manière fluide relève du
défi avec Selenium. Il existe quelques moyens pour automatiser ce
processus, mais cela impliquerait d'ajouter une couche supplémentaire aux
tests Selenium et ce ne serait pas sécurisé. Il vaut mieux donc éviter
l'automatisation de l'authentification à double facteur.

Voici quelques solutions pour contouner la vérification A2F :

* Désactiver la A2F pour certains utilisateurs, dans l'environnement de
test, afin de pouvoir utiliser leurs identifiants dans l'automatisation.
* Désactiver la A2F dans l'environnement de test.
* Désactiver la A2F si la connexion s'effectue depuis certaines adresses
IP. De cette manière, en renseignant Selenium avec ces adresses IP, il
pourra à exclure la A2F.
