---
title: "Remote WebDriver"
chapter: true
weight: 6
---

# Remote WebDriver

Vous pouvez utiliser WebDriver à distance de la même manière que vous l'utiliseriez
localement. La principale différence est qu’un WebDriver distant doit être
configuré pour qu'il puisse exécuter vos tests sur une machine distincte.

Un WebDriver distant est composé de deux éléments: un client et un
serveur. Le client est votre test WebDriver et le serveur est simplement un
Servlet Java, qui peut être hébergé sur n'importe quel serveur d'application JEE moderne.