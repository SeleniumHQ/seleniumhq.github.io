---
title: "Componentes"
linkTitle: "Grid Components"
weight: 6
description: >
    Description of Hub and Nodes for Grid 3.
aliases: ["/documentation/pt-br/grid/grid_3/components_of_a_grid/"]
---

{{< figure src="/images/documentation/legacy/grid_3/grid.png" class="img-responsive text-center" alt="Grid 3 Components">}}

## Hub
* Intermediário e gerente
* Aceita solicitações para executar testes
* Recebe instruções do cliente e as executa remotamente nos nós
* Gerencia tópicos

Um _Hub_ é um ponto central para onde todos os seus testes são enviados.
Cada Selenium Grid consiste em exatamente um hub. O hub precisa estar acessível
dos respectivos clientes (ou seja, servidor de CI, máquina do desenvolvedor etc.)
O hub irá conectar um ou mais nós
aos quais os testes serão delegados.

## Nós

* Onde vivem os navegadores
* Registra-se no hub e comunica seus recursos
* Recebe solicitações do hub e as executa

_Nodes_ são diferentes instâncias do Selenium
que executarão testes em sistemas de computador individuais.
Pode haver muitos nós em uma grade.
As máquinas que são nós não precisam ser da mesma plataforma
ou ter a mesma seleção de navegador do hub ou de outros nós.
Um nó no Windows pode ter a capacidade de
oferecer o Internet Explorer como uma opção de navegador,
considerando que isso não seria possível no Linux ou Mac.

