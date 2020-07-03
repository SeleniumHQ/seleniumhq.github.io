---
title: "Componentes"
weight: 1
---

![Grid](/images/grid.png)

## Hub
* Ejerce como mediador y administrador
* Acepta peticiones para ejecutar los tests
* Recoge instrucciones de los clientes y las ejecuta de forma remota en los nodos
* Gestiona los hilos

El _Hub_ es un punto central donde se envian todos tus tests.
Cada _Selenium Grid_ consiste en exactamente un _hub_. El hub necesita ser
accesible desde la perspectiva de los clientes (ej. Servidor de la CI, maquina
del desarrollador)
El hub se conectará a uno o mas nodos a los que los tests serán delegados.

## Nodos

* Donde se ubican los navegadores
* Se registra a si mismo en el hub y le comunica sus capacidades
* Recibe las peticiones desde el hub las ejecuta

Los nodos son diferentes instancias de Selenium que ejecutarán los tests en
sistemas informáticos individuales.
Puedes haber muchos nodos en un grid.
Las maquinas que contienen los nodos no necesitan estar bajo el mismo sistema
operativo o disponer de la misma selección de navegadores que el hub u otros
nodos.
Un nodo en Windows podría tener la capacidad de ofrecer Internet Explorer como
opción del navegador mientras que esto no podría ser posible en Linux o Mac.
