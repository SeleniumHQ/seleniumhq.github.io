---
title: "GraphQL query support"
linkTitle: "GraphQL Support"
weight: 2
aliases: ["/documentation/en/grid/grid_4/graphql_support/"]
---

GraphQL is a query language for APIs and a runtime for fulfilling those queries 
with your existing data. It gives users the power to ask for exactly what they need and nothing more.

## Enums
Enums represent possible sets of values for a field.

For example, the `Node` object has a field called `status`. The state is an enum 
(specifically, of type `Status`) because it may be `UP` , `DRAINING` or `UNAVAILABLE`.

## Scalars
Scalars are primitive values: `Int`, `Float`, `String`, `Boolean`, or `ID`.

When calling the GraphQL API, you must specify nested subfield until you return only scalars.


## Structure of the Schema
The structure of grid schema is as follows:

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

## Querying GraphQL

The best way to query GraphQL is by using `curl` requests. The query is interpreted as JSON. Ensure double quotes are properly escaped to avoid unexpected errors.
GraphQL allows you to fetch only the data that you want, nothing more nothing less.

Some of the example GraphQL queries are given below. You can build your own 
queries as you like. 

### Querying the number of `maxSession` and `sessionCount` in the grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { maxSession, sessionCount } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

Generally on local machine the `<LINK_TO_GRAPHQL_ENDPOINT>` would be `http://localhost:4444/graphql`

### Querying all details for session, node and the Grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { uri, maxSession, sessionCount }, nodesInfo { nodes { id, uri, status, sessions { id, capabilities, startTime, uri, nodeId, nodeUri, sessionDurationMillis, slot { id, stereotype, lastStarted } }, slotCount, sessionCount }} }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Query for getting the current session count in the Grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { sessionCount } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Query for getting the max session count in the Grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { maxSession } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Query for getting all session details for all nodes in the Grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ sessionsInfo { sessions { id, capabilities, startTime, uri, nodeId, nodeId, sessionDurationMillis } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Query to get slot information for all sessions in each Node in the Grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ sessionsInfo { sessions { id, slot { id, stereotype, lastStarted } } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Query to get session information for a given session:

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ session (id: \"<session-id>\") { id, capabilities, startTime, uri, nodeId, nodeUri, sessionDurationMillis, slot { id, stereotype, lastStarted } } } "}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Querying the capabilities of each node in the grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ nodesInfo { nodes { stereotypes } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Querying the status of each node in the grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ nodesInfo { nodes { status } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Querying the URI of each node and the grid :

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
