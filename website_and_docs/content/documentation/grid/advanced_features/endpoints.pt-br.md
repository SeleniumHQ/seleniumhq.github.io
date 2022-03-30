---
title: "Rotas da Grid"
linkTitle: "Rotas da Grid"
weight: 3
aliases: [
"/documentation/pt-br/grid/grid_4/grid_endpoints/",
"/pt-br/documentation/grid/advanced_features/grid_endpoints/"
]
---

## Grid 

### Status da Grid

O status da Grid fornece o estado atual da grid. Consiste em detalhes sobre cada nó registrado.
Para cada nó, o status inclui informações sobre a disponibilidade, sessões e slots do nó.

```shell
cURL GET 'http://localhost:4444/status'
```

No modo Standalone, o URL da Grid é o endereço do servidor Standalone.

No modo Hub-Node, a URL da Grid é o endereço do servidor Hub.

No modo totalmente distribuído, a URL da Grid é o endereço do servidor do roteador.

A URL padrão para todos os modos acima é http://localhost:4444.

## Distribuidor

### Remover Nó

Para remover o Nó da Grid, use o comando cURL listado abaixo.
Ele não interrompe nenhuma sessão em andamento em execução nesse nó.
O Node continua rodando como está, a menos que seja explicitamente eliminado.
O Distribuidor não está mais ciente do Nó e, portanto, qualquer solicitação de nova sessão correspondente
não será encaminhado para esse Nó.

No modo Standalone, a URL do distribuidor é o endereço do servidor Standalone. 

No modo Hub-Node, a URL do Distribuidor é o endereço do servidor Hub.  
```shell
cURL --request DELETE 'http://localhost:4444/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret> '
```
No modo totalmente distribuído, a URL é o endereço do servidor Distribuidor.
```shell
cURL --request DELETE 'http://localhost:5553/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
Se nenhum segredo de registro foi configurado durante a configuração da Grid, use 
```shell
cURL --request DELETE 'http://<Distributor-URL>/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET;'
```

### Drenar Nó

O comando de drenagem de nó é para desligamento normal de nó.
A drenagem para o Node após a conclusão de todas as sessões em andamento.
No entanto, ele não aceita novas solicitações de sessão.

No modo Standalone, a URL do distribuidor é o endereço do servidor Standalone. 

No modo Hub-Node, a URL do Distribuidor é o endereço do servidor Hub. 
```shell
cURL --request POST 'http://localhost:4444/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret> '
```
No modo totalmente distribuído, a URL é o endereço do servidor Distribuidor. 
```shell
cURL --request POST 'http://localhost:5553/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```
Se nenhum segredo de registro foi configurado durante a configuração da Grid, use  
```shell
cURL --request POST 'http://<Distributor-URL>/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET;'
```

## Nó

Os terminais nesta seção são aplicáveis ao modo Hub-Node e ao modo Grid totalmente distribuída, onde o Nó é executado de forma independente.
A URL do Nó padrão é http://localhost:5555 no caso de um Nó.
No caso de vários Nós, use [Grid status](#grid-status) para obter todos os detalhes do Nó e localizar o endereço do Nó.

### Status 

O status do Nó é essencialmente uma verificação de integridade do Nó.
O distribuidor executa ping no status do Nó em intervalos regulares e atualiza o modelo de Grid de acordo.
O status inclui informações sobre disponibilidade, sessões e slots.

```shell
cURL --request GET 'http://localhost:5555/status'
```

### Drenagem

O Distribuidor passa o comando [drain](# drain-node) para o Nó apropriado identificado pelo ID do Nó.
Para drenar o Nó diretamente, use o comando cuRL listado abaixo.
Ambos as rotas são válidas e produzem o mesmo resultado. Drenar termina as sessões em andamento antes de interromper o Nó.

```shell
cURL --request POST 'http://localhost:5555/se/grid/node/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```
Se nenhum segredo de registro foi configurado durante a configuração da Grid, use 
```shell
cURL --request POST 'http://<node-URL>/se/grid/node/drain' --header 'X-REGISTRATION-SECRET;'
```

### Checar dono da sessão

Para verificar se uma sessão pertence a um Nó, use o comando cURL listado abaixo. 

```shell
cURL --request GET 'http://localhost:5555/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
Se nenhum segredo de registro foi configurado durante a configuração da Grid, use 
```shell
cURL --request GET 'http://<node-URL>/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

Ele retornará true se a sessão pertencer ao Nó, caso contrário, retornará false.

### Deletar sessão

A exclusão da sessão encerra a sessão do WebDriver, fecha o driver e o remove do mapa de sessões ativas.
Qualquer solicitação usando o id de sessão removido ou reutilizando a instância do driver gerará um erro.

```shell
cURL --request DELETE 'http://localhost:5555/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
Se nenhum segredo de registro foi configurado durante a configuração da Grid, use 
```shell
cURL --request DELETE 'http://<node-URL>/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

## Fila de Sessão

### Limpar a Fila de Sessão

A Fila de Sessão contém as novas solicitações de sessão.
Para limpar a fila, use o comando cURL listado abaixo.
Limpar a fila rejeita todas as solicitações na fila. Para cada solicitação, o servidor retorna uma resposta de erro ao respectivo cliente.
O resultado do comando clear é o número total de solicitações excluídas.

No modo Standalone, a URL Queue é o endereço do servidor Standalone.

No modo Hub-Node, a URL do enfileirador é o endereço do servidor Hub.

```shell
cURL --request DELETE 'http://localhost:4444/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

No modo totalmente distribuído, a URL do enfileirador é o endereço do servidor do Enfileirador de Sessões.
```shell
cURL --request DELETE 'http://localhost:5559/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

If no registration secret has been configured while setting up the Grid, then use 
```shell
cURL --request DELETE 'http://<URL>/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET;'
```

### Obter novos pedidos da Fila de Sessão

Novos pedidos da Fila de Sessão contém os novos pedidos de sessão.
Para obter os pedidos na Fila, utiliza o comando cURL listado abaixo.
É retornado o número total de pedidos na Fila.

No modo Standalone, a URL é a do servidor, em modo Grid, a URL será a do HUB.

```shell
cURL --request GET 'http://localhost:4444/se/grid/newsessionqueue/queue'
```

No modo totalmente distribuido, a URL da Fila é a porta do servidor de Fila.
```shell
cURL --request GET 'http://localhost:5559/se/grid/newsessionqueue/queue'
