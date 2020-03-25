---
title: "Grid"
chapter: true
weight: 9
---

# Grid

_Selenium Grid_ es un servidor inteligente que efectúa de proxy que permite a los
tests de Selenium enrutar sus comandos hacia instancias remotas de navegadores
web.
La intención de esto es proporcionar una forma sencilla de ejecutar los tests en
paralelo en múltiple maquinas.


Con _Selenium Grid_ un servidor actúa como el centro de actividad (_hub_) 
encargado de enrutar los comandos de los tests en formato JSON hacia uno o mas
nodos registrados en el _Grid_.
Los tests contactan con el hub para obtener acceso a las instancias remotas de
los navegadores.

_Selenium Grid_ te permite ejecutar los tests en paralelo en múltiples maquinas
y también te permite gestionar diferentes versiones de navegadores y diferentes
configuraciones de navegadores de manera centralizada (en lugar de hacerlo de
manera individual en cada test)

_Selenium Grid_ no es una solución mágica para todos tus problemas.
Permite resolver un subconjunto de problemas comunes de delegación y distribución,
pero, por ejemplo, no administrará su infraestructura y podría no satisfacer sus
necesidades personales.