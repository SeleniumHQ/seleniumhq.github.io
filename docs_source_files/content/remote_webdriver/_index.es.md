---
title: "WebDriver remoto"
chapter: true
weight: 6
---

# WebDriver remoto

Puede usar WebDriver de forma remota de la misma manera que lo usarías
localmente. La principal diferencia es que un WebDriver remoto debe ser
configurado para que pueda ejecutar tus pruebas en una máquina diferente.

Un WebDriver remoto se compone de dos piezas: un cliente y un
servidor. El cliente es tu prueba de WebDriver y el servidor es 
simplemente un servlet Java, que se puede alojar en cualquier 
servidor moderno de aplicaciones JEE.