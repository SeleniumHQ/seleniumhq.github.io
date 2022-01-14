---
title: "グリッドのコンポーネント"
linkTitle: "グリッドのコンポーネント"
weight: 6
description: >
    Description of Hub and Nodes for Grid 3.
aliases: ["/documentation/ja/grid/grid_3/components_of_a_grid/"]
---

{{< figure src="/images/documentation/legacy/grid_3/grid.png" class="img-responsive text-center" alt="Grid 3 Components">}}

## ハブ
* 仲介者およびマネージャー
* テストを実行する要求を受け入れます
* クライアントから命令を受け取り、ノード上でリモートで実行します
* スレッドを管理します

_ハブ_ は、すべてのテストが送信される中心点です。
各Selenium Gridは、ちょうど1つのハブで構成されます。
ハブは、それぞれのクライアント（CIサーバー、開発者マシンなど）から到達可能である必要があります。
ハブは、テストが委任される1つ以上のノードを接続します。

## ノード

* ブラウザが存在する場所
* ハブに自分自身を登録し、その機能を伝えます
* ハブからリクエストを受信して実行します

_ノード_ は、個々のコンピューターシステムでテストを実行するさまざまなSeleniumインスタンスです。
グリッドには多くのノードが存在する場合があります。
ノードであるマシンは、ハブまたは他のノードと同じプラットフォームであったり、同じブラウザーを選定する必要はありません。
Windows上のノードは、Internet Explorerをブラウザーオプションとして提供する機能を備えている場合がありますが、これはLinuxまたはMacでは不可能です。
