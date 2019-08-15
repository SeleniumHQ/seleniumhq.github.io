---
title: "Débuter avec WebDriver"
chapter: true
weight: 4
---

# Débuter avec WebDriver

Selenium permet l'automatisation des navigateurs les plus courants du marché 
à travers l'utilisation de _WebDriver_.
WebDriver est une API et un protocole définissant une interface agnostique 
(non orientée vers un langage spécifique) 
permettant de controller le comportement des navigateurs web.
Chaque navigateur est appuyé par une implémentation spécifique de WebDriver,
appelée un *driver*.
Ce driver est le composant responsable de la délégation vers le navigateur,
et gère la communication entre Selenium et le navigateur.

Cette séparation fait partie d'un effor conscient afin de responsabiliser
les fournisseurs de navigateurs quant à l'implémentation de ceux-ci.
Selenium tire parti de drivers tierces lorsque c'est possible,
mais fournit également ces propres drivers, maintenus par le projet,
lorsque nécessaire.

Le framework Selenium relie toutes ces pièces ensemnle via une interface
user-friendly qui permet d'utiliser différents navigateurs de manière transparente,
permettant ainsi l'automatisation cross-platform et cross-browser.

Plus de détail à propos des driver peut être trouvé au chapitre
[Singularités du Driver]({{< ref "/driver_idiosyncrasies/_index.md" >}}).
