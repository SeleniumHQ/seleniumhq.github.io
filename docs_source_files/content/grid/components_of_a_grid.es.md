---
title: "Componentes de un Grid"
weight: 2
---


![Grid](/images/grid.png)


## Concentrador o _Hub_
* Intermediario y administrador
* Acepta solicitudes para ejecutar pruebas
* Toma instrucciones del cliente y las ejecuta de forma remota en los nodos
* Administra hilos

Un hub es un punto central al que se envían todas sus pruebas. Cada Selenium Grid consta de un centro exactamente. El concentrador debe ser accesible desde los respectivos clientes (es decir, servidor CI (_Continuos Integration_), máquina del desarrollador, etc.) El concentrador conectará uno o más nodos a los que se delegarán las pruebas.

## Nodos

* Aquí es donde viven los navegadores
* Se registra en el centro y comunica sus capacidades
* Recibe solicitudes del hub y las ejecuta

Los "nodos" son instancias diferentes de Selenium que ejecutarán pruebas en sistemas informáticos individuales. Puede haber muchos nodos en una grid. Las máquinas que son nodos no necesitan ser de la misma plataforma o tener la misma selección de navegador que la del concentrador u otros nodos. Un nodo en Windows podría tener la capacidad de ofrecer Internet Explorer como una opción de navegador, mientras que esto no sería posible en Linux o Mac.

