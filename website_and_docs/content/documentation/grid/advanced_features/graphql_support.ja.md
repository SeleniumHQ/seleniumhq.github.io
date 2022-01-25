---
title: "GraphQLクエリのサポート"
linkTitle: "GraphQLクエリのサポート"
weight: 2
aliases: ["/documentation/ja/grid/grid_4/graphql_support/"]
---

GraphQLは、APIのクエリ言語であり、既存のデータでこれらのクエリを実行するためのランタイムです。
これにより、ユーザーは必要なものだけを正確に要求することができます。

## 列挙型(Enum)
列挙型は、フィールドの可能な値のセットを表します。

たとえば、 `Node` オブジェクトには `status` というフィールドがあります。
`UP` 、 `DRAINING` 、または `UNAVAILABLE` の可能性があるため、状態は、 列挙型（具体的には、`Status` タイプ）です。

## スカラー
スカラーはプリミティブ値です： `Int` 、 `Float` 、 `String` 、 `Boolean` 、または `ID` 。

GraphQL APIを呼び出すときは、スカラーのみを返すまでネストされたサブフィールドを指定する必要があります。

## スキーマの構造
グリッドスキーマの構造は次のとおりです。

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
## GraphQLで照会する

GraphQLをクエリする最良の方法は、 `curl` リクエストを使用することです。
GraphQLを使用すると、必要なデータのみをフェッチできます。それ以上でもそれ以下でもありません。

GraphQLクエリの例のいくつかを以下に示します。 必要に応じて独自のクエリを作成できます。

### グリッド内の `maxSession`と` sessionCount`の数を照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { maxSession, sessionCount } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

通常、ローカルマシンでは、 `<LINK_TO_GRAPHQL_ENDPOINT>` は `http://localhost:4444/graphql` になります。

### セッション、ノード、グリッドのすべての詳細を照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { uri, maxSession, sessionCount }, nodesInfo { nodes { id, uri, status, sessions { id, capabilities, startTime, uri, nodeId, nodeUri, sessionDurationMillis, slot { id, stereotype, lastStarted } }, slotCount, sessionCount }} }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### グリッドで現在のセッション数を取得するためのクエリ

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { sessionCount } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### グリッドで最大セッション数を取得するためのクエリ

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { maxSession } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### グリッド内のすべてのノードのすべてのセッションの詳細を取得するためのクエリ

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ sessionsInfo { sessions { id, capabilities, startTime, uri, nodeId, nodeId, sessionDurationMillis } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### グリッド内の各ノードのすべてのセッションのスロット情報を取得するためのクエリ

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ sessionsInfo { sessions { id, slot { id, stereotype, lastStarted } } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### 特定のセッションのセッション情報を取得するためのクエリ

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ session (id: \"<session-id>\") { id, capabilities, startTime, uri, nodeId, nodeUri, sessionDurationMillis, slot { id, stereotype, lastStarted } } } "}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### グリッド内の各ノードのcapabilityを照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ nodesInfo { nodes { stereotypes } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### グリッド内の各ノードのステータスを照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ nodesInfo { nodes { status } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### 各ノードとグリッドのURIを照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ nodesInfo { nodes { uri } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### 新しいセッションキューで現在のリクエストを取得するためのクエリ

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ sessionsInfo { sessionQueueRequests } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### 新しいセッションキューのサイズを取得するためのクエリ

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query":"{ grid { sessionQueueSize } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```
