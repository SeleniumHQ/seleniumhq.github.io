---
title: "GraphQL querying support"
weight: 1
---

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
                capabilities,
                activeSessionIDs
            }
        ]
    }
}
```

## Querying GraphQL

Work in Progress
