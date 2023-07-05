---
title: "Componentes"
linkTitle: "Componentes"
weight: 6
description: >
  Compreender como usar os componentes da Grid
aliases: [
"/documentation/pt-br/grid/grid_4/components_of_a_grid/",
"/pt-br/documentation/grid/components_of_a_grid/"
]
---

A Selenium Grid 4 é uma re-escrita completa das versões anteriores. Além dos melhoramentos ao desempenho
e aos cumprimentos dos standards, várias funcionalidades da Grid foram separadas para reflectir uma
nova era de computação e desenvolvimento de software. Criada de raíz para containerização e
escalabilidade cloud, Selenium Grid 4 é uma nova solução para a era moderna.


![Selenium Grid 4 Components](/images/documentation/grid/components.png)

## Router

O **Router** é o ponto de entrada da Grid, recebendo todos os pedidos externos, reenviando para o componente correcto.

Se o **Router** recebe um novo pedido de sessão, este será enviado para **New Session Queue**.

Se o pedido recebido pertence a uma sessão existente, o **Router** irá inquirir o **Session Map** para obter
o **Node** ID onde esta sessão está em execução, enviando de seguida o pedido directamente para o **Node**.

O **Router** balança a carga na Grid ao enviar o pedido ao componente que está em condições de o receber,
sem sobrecarregar qualquer outro componente que não faz parte do processo.

## Distributor

O **Distributor** tem duas responsabilidades:

#### Resgistar e manter uma lista de todos os Nodes e as suas capacidades

Um **Node** regista-se no **Distributor** enviando um evento de registo **Node** através do
**Event Bus**. O **Distributor** lê o pedido e tenta contactar o **Node** através de HTTP
para confirmar a sua existência. Se obtiver resposta com sucesso, o **Distributor** regista
o Node e mantém uma lista de todas as capacidades **Nodes** através do **GridModel**.

#### Processar algum pedido pendente que tenha sido criado na New Session Queue

Quando um novo pedido de sessão é enviado para o **Router**, ele é reenviado para a **New Session Queue**,
onde ficará na fila em espera. O **Distributor** irá processar pedidos pendentes que existam na **New Session Queue**, 
encontrando um **Node** com as capacidades certas onde a sessão possa ser criada. Após esta nova
sessão ter sido criada, o **Distributor** guarda na **Session Map** a relação entre o id da sessão e 
o **Node** onde a sessão está em execução.

## Session Map

A **Session Map** é uma data store que mantém a relação entre o id da sessão e o **Node** 
onde a sessão está em execução. Apoia o **Router** no processo de envio do pedido para um **Node**
onde a sessão está em execução. O **Router** irá pedir ao **Session Map** qual o **Node** associado
ao id de sessão.

## New Session Queue

A **New Session Queue** contém todos os pedidos de novas sessões em ordem FIFO. Existem parametros
configuráveis para o timeout de sessão e para o número de tentativas.

O **Router** adiciona um novo pedido de sessão em **New Session Queue** e aguarda uma resposta.
A **New Session Queue** verifica regularmente se algum pedido deu timeout e em caso afirmativo,
rejeita e remove o pedido da queue.

O **Distributor** verifica regularmente se existe um slot disponível. Em caso afirmativo, o **Distributor**
obtém um pedido a partir de **New Session Queue** e tenta criar uma nova sessão.

Assim que existam capacidades pedidas capazes de serem servidas por um dos **Node** que estejam livres,
o **Distributor** tenta obter o slot disponível. Caso todos os slots estejam ocupados, o **Distributor**
envia o pedido de volta para a queue. Se o pedido tiver timeout ao ser readicionado à queue, será rejeitado.

Após um id de sessão ser criado, o **Distributor** envia a informação se sessão para **New Session Queue**,
que por sua vez irá enviar para o **Router** que finalmente entrega ao cliente.

## Node

A Grid pode ter múltiplos **Nodes**. Cada **Node** gere os slots de disponibilidade para os navegadores existentes
na máquina onde está a executar.

O **Node** regista-se no **Distributor** através do **Event Bus**, sendo que a sua configuração é enviada
como parte da mensagem de registo.

Por omissão, o **Node** regista automaticamente todos os navegadores que estejam disponíveis no PATH da máquina onde
executa. Cria também um slot de execução por cada CPU para os navegadores Chrome e Firefox. Para Safari,
apenas é criado um slot. Usando uma [configuração]({{< ref "/configuration" >}}) específica, é também
possível executar sessões em containers Docker.

O **Node** apenas executa os comandos que recebe, não avalia, faz julgamentos ou controla mais nada que não seja
o fluxo de comandos e respostas. A máquina onde o **Node** está a executar não necessita de ter o mesmo sistema
operativo do que os restantes componentes. Por exemplo, um Windows **Node** pode ter a capacidade de oferecer 
IE Mode no Edge como opção de navegador, coisa que não é possível em Linux ou Mac. A Grid pode ter múltiplos 
**Node** configurados com Windows, Mac ou Linux.

## Event Bus

O **Event Bus** serve de canal de comunicações entre **Nodes**, **Distributor**, **New Session Queue**, 
e **Session Map**. A Grid usa mensagens para a maioria das comunicações internas. evitando chamadas HTTP.
Quando iniciar a Grid em modo distribuido, deverá iniciar o **Event Bus** antes dos restantes componentes.

{{% alert title="Configurando a sua Grid" color="primary" %}}
Se pretende usar todos estes componentes para criar a sua Grid, 
visite a secção ["configurando a sua"]({{< ref "getting_started.md" >}})
para entender como ligar as peças todas.
{{% /alert %}}
