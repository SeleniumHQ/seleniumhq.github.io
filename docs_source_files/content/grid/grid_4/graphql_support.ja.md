---
title: "GraphQLクエリのサポート"
weight: 3
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

## GraphQLで照会する

GraphQLをクエリする最良の方法は、 `curl` リクエストを使用することです。 
GraphQLを使用すると、必要なデータのみをフェッチできます。それ以上でもそれ以下でもありません。

Some of the example GraphQL queries are given below. You can build your own queries as you like.

### グリッド内の `totalSlots`と` usedSlots`の数を照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { totalSlots, usedSlots } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

通常、ローカルマシンでは、 `<LINK_TO_GRAPHQL_ENDPOINT>` は `http://localhost:4444/graphql` になります。

### グリッド内の各ノードのcapabilityを照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { nodes { capabilities } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### グリッド内の各ノードのステータスを照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { nodes { status } } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```

### 各ノードとグリッドのURIを照会する

```shell
curl -X POST -H "Content-Type: application/json" --data '{"query": "{ grid { nodes { uri }, uri } }"}' -s <LINK_TO_GRAPHQL_ENDPOINT>
```
