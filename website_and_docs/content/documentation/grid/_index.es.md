---
title: "Grid"
linkTitle: "Grid"
weight: 9
description: >
  Want to run tests in parallel across multiple machines? Then, Grid is for you.
aliases: 
        [
          "/documentation/es/selenium_installation/installing_standalone_server/",
          "/documentation/es/grid/",
          "/documentation/es/grid/grid_4/",
          "/documentation/es/grid/purposes_and_main_functionalities/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium Grid allows the execution of WebDriver scripts on remote machines (virtual
or real) by routing commands sent by the client to remote browser instances.
It aims to provide an easy way to run tests in parallel on multiple machines.

Selenium Grid allows us to run tests in parallel on multiple machines,
and to manage different browser versions and browser configurations centrally
(instead of in each individual test).

Selenium Grid is not a silver bullet.
It solves a subset of common delegation and distribution problems,
but will for example not manage your infrastructure,
and might not suit your specific needs.

## Objetivos y funcionalidades principales

* Punto de entrada centralizado para todos los tests
* Gestión y control de los nodos / entornos donde se ejecutan los navegadores
* Escalado
* Ejecución de los tests en paralelo
* Testing cruzado entre diferentes sistemas operativos
* Balanceo de carga

{{% alert title="Selenium Grid 4" color="primary" %}}
Grid 4 pretende sacar ventaja de un cierto número de nuevas tecnologías
para facilitar la escalada mientras que permiten ejecución en local.

Selenium Grid 4 es una nueva implementación y no comparte el código
base que versiones anteriores tenían.

Para tener todos los detalles de los componentes de Grid 4, entender como
funciona, y como instalar la tuya propia, por favor busca a través de las 
secciones a continuación.
{{% /alert %}}



