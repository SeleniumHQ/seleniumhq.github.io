---
title: "Grid"
chapter: true
weight: 9
---


# Grid

_Selenium Grid_ es un servidor proxy inteligente que permite que las pruebas de Selenium enruten comandos a instancias remotas del navegador web. Su objetivo es proporcionar una manera fácil de ejecutar pruebas en paralelo en múltiples máquinas.

Con Selenium Grid, un servidor actúa como el centro que enruta los comandos de prueba con formato JSON a uno o más nodos de Grid registrados. Las pruebas se ponen en contacto con el concentrador (o _hub_) para obtener acceso a instancias de explorador remoto. El concentrador tiene una lista de servidores registrados a los que proporciona acceso y nos permite controlar estas instancias.

Selenium Grid nos permite ejecutar pruebas en paralelo en múltiples máquinas, y administrar diferentes versiones y configuraciones de navegador de forma centralizada (en lugar de en cada prueba individual).

Selenium Grid no es una solución mágica para todas las situaciones. Resuelve un subconjunto de problemas comunes de delegación y distribución, pero, por ejemplo, no administrará su infraestructura y podría no satisfacer algunas de sus necesidades específicas.

