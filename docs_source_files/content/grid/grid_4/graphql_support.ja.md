---
title: "GraphQL querying support"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from
English to Japanese. Do you speak Japanese? Help us to translate
it by sending us pull requests!
{{% /notice %}}

GraphQL is a query language for APIs and a runtime for fulfilling those queries with your existing data. It gives users the power to ask for exactly what they need and nothing more.

## Enums
Enums represent possible sets of values for a field.

For example, the `Node` object has a field called `status`. The state is an enum (specifically, of type `Status`) because it may be `UP` , `DRAINING` or `UNAVAILABLE`.

## Scalars
Scalars are primitive values: `Int`, `Float`, `String`, `Boolean`, or `ID`.

When calling the GraphQL API, you must specify nested subfield until you return only scalars.


## Structure of the Schema
The structure of grid schema is as follows:

```shell
{
    grid: {
        uri,
        totalSlots,
        usedSlots,
        nodes : [
            {
                id,
                uri,
                status,
                maxSession,
                capabilities
            }
        ]
    }
}
```

## Querying GraphQL

The best way to query GraphQL is by using `curl` requests. GraphQL allows you to fetch only the data that you want, nothing more nothing less.

Some of the example GraphQL queries are given below. You can build your own queries as you like.

### Querying the number of `totalSlots` and `usedSlots` in the grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { totalSlots, usedSlots } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

Generally on local machine the `<LINK_TO_GRAPHQL_ENDPOINT>` would be `http://localhost:4444/graphql`

### Querying the capabilities of each node in the grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { nodes { capabilities } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Querying the status of each node in the grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { nodes { status } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### Querying the URI of each node and the grid :

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { nodes { uri }, uri } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```
