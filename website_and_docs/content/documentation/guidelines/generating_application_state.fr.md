---
title: "Generer l'état de l'application"
linkTitle: "Generer l'état de l'application"
weight: 5
aliases: ["/documentation/fr/guidelines_and_recommendations/generating_application_state/"]  
---

Le sélénium ne doit pas être utilisé pour préparer un cas de test. Tous répétitifs
les actions et les préparatifs pour un test élémentaire devraient être effectués par
méthodes. Par exemple, la plupart des interfaces utilisateur 
Web ont une authentification (par exemple, une connexion
forme). Éliminer la connexion via un navigateur Web avant chaque test
améliorer à la fois la vitesse et la stabilité du test. Une méthode devrait être
créé pour accéder à l'AUT * (par exemple, en utilisant une API pour vous connecter et définir un
biscuit). En outre, la création de méthodes de préchargement des données pour
les tests ne doivent pas être effectués avec du sélénium. Comme mentionné précédemment,
les API existantes doivent être exploitées pour créer des données pour l'AUT *.

***AUT**: Application under test
