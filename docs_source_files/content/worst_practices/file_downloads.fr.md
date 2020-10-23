---
title: "Téléchargement de fichier"
weight: 2
---

Bien qu'il soit possible de démarrer un téléchargement
en cliquant sur un lien avec un navigateur 
sous le contrôle de Selenium,
l'API n'expose pas la progression du téléchargement,
ce qui n'est pas idéal pour tester les fichiers téléchargés.
En effet, le téléchargement de fichiers n'est pas 
considéré comme un aspect important
d'émuler l'interaction des utilisateurs avec la plate-forme Web.
À la place, recherchez le lien à l'aide de Selenium
(et tous les cookies requis)
et le passer à une bibliothèque de requêtes HTTP comme
[libcurl](//curl.haxx.se/libcurl/).