---
title: "Suporte a buscas em GraphQL"
linkTitle: "Suporte a buscas em GraphQL"
weight: 2
aliases: ["/documentation/pt-br/grid/grid_4/graphql_support/"]
---

GraphQL é uma linguagem de consulta para APIs e um runtime para atender a essas consultas com seus dados existentes. Ele dá aos usuários o poder de pedir exatamente o que precisam e nada mais.

## Enums
Enums representam possíveis conjuntos de valores para um campo.

Por exemplo, o objeto `Node` possui um campo chamado` status`. O estado é um enum (especificamente, do tipo `Status`) porque pode ser` UP`, `DRAINING` ou` UNAVAILABLE`.

## Escalares
Escalares são valores primitivos: `Int`,` Float`, `String`,` Boolean` ou `ID`.

Ao chamar a API GraphQL, você deve especificar o subcampo aninhado até retornar apenas escalares.


## Estrutura do Schema
A estrutura do esquema de grade é a seguinte:

```shell
{
    session(id: "<session-id>") : {
        id,
        capabilities,
        startTime,
        uri,
        nodeId,
        nodeUri,
        sessionDurationMillis
        slot : {
            id,
            stereotype,
            lastStarted
        }
    }
    grid: {
        uri,
        totalSlots,
        nodeCount,
        maxSession,
        sessionCount,
        version,
        sessionQueueSize
    }
    sessionsInfo: {
        sessionQueueRequests,
        sessions: [
            {
                id,
                capabilities,
                startTime,
                uri,
                nodeId,
                nodeUri,
                sessionDurationMillis
                slot : {
                    id,
                    stereotype,
                    lastStarted
                }
            }
        ]
    }
    nodesInfo: {
        nodes : [
            {
                id,
                uri,
                status,
                maxSession,
                slotCount,
                sessions: [
                    {
                        id,
                        capabilities,
                        startTime,
                        uri,
                        nodeId,
                        nodeUri,
                        sessionDurationMillis
                        slot : {
                            id,
                            stereotype,
                            lastStarted
                        }
                    }
                ],
                sessionCount,
                stereotypes,
                version,
                osInfo: {
                    arch,
                    name,
                    version
                }
            }
        ]
    }
}
```

## Consultando GraphQL

O melhor jeito de consultar GraphQL é utilizando requisições `curl`. GraphQL permite que você busque apenas os dados que você quer, nada mais, anda menos.

Alguns exemplos de buscas em GraphQL estão abaixo. Você pode montar as queries como quiser.

### Buscando o número total de slots (`maxSession`) e slots usados (`sessionCount`) na Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { maxSession, sessionCount } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

Geralmente na máquina local o `<LINK_TO_GRAPHQL_ENDPOINT>` será `http://localhost:4444/graphql`

### Buscando todos os detalhes da Sessão, Nó e Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { uri, maxSession, sessionCount }, nodesInfo { nodes { id, uri, status, sessions { id, capabilities, startTime, uri, nodeId, nodeUri, sessionDurationMillis, slot { id, stereotype, lastStarted } }, slotCount, sessionCount }} }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Buscando o número de sessões atual na Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { sessionCount } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Buscando a contagem máxima de sessões na Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { maxSession } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Buscando todos os detalhes de todas as sessões de todos os nós na Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ sessionsInfo { sessions { id, capabilities, startTime, uri, nodeId, nodeId, sessionDurationMillis } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Buscando informações dos slots de todas as sessões de cada Nó na Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ sessionsInfo { sessions { id, slot { id, stereotype, lastStarted } } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Buscando informação da sessão para uma sessão específica:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ session (id: \"<session-id>\") { id, capabilities, startTime, uri, nodeId, nodeUri, sessionDurationMillis, slot { id, stereotype, lastStarted } } } "}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Buscando os recursos de cada nó na Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ nodesInfo { nodes { stereotypes } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Buscando o status de cada Nó na Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ nodesInfo { nodes { status } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Buscando a URI de cada Nó e da Grid:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ nodesInfo { nodes { uri } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Query for getting the current requests in the New Session Queue:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ sessionsInfo { sessionQueueRequests } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Query for getting the New Session Queue size :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { sessionQueueSize } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```
