---
title: "グリッドのコンポーネント"
linkTitle: "グリッドのコンポーネント"
weight: 1
aliases: ["/documentation/ja/grid/grid_4/components_of_a_grid/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Japanese. Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

{{< figure src="/images/documentation/grid/components.png" class="img-responsive text-center" alt="Grid">}}

## ルーター

ルーターがリクエストを正しいコンポーネントに転送します。

これはGridのエントリポイントであり、すべての外部リクエストはGridによって受信されます。
ルーターの動作は、リクエストによって異なります。
新しいセッション要求の場合、ルーターはそれをディストリビュータに転送します（新しいセッションの作成が処理されます）。
リクエストが既存のセッションに属している場合、ルーターはセッションIDをセッションマップに送信し、
セッションマップはセッションが実行されているノードを返します。
この後、ルーターはリクエストをノードに転送します。

ルーターは、プロセスで不要なコンポーネントをオーバーロードすることなく、
より適切に処理できるコンポーネントにリクエストを送信することにより、
Grid内の負荷のバランスをとることを目的としています。


## ディストリビューター

ディストリビューターは、すべてのノードとそのケイパビリティを認識しています。
その主な役割は、新しいセッション要求を受け取り、セッションを作成できる適切なノードを見つけることです。
セッションが作成されると、ディストリビューターは、
セッションIDとセッションが実行されているノードとの関係をセッションマップに格納します。

## ノード

ノードはGrid内に数回存在することができます。
各ノードは、それが実行されているマシンの利用可能なブラウザのスロットを管理します。

ノードは、イベントバスを介して自身をディストリビューターに登録し、その構成は登録メッセージの一部として送信されます。

デフォルトでは、ノードは、実行されているマシンのパスで使用可能なすべてのブラウザードライバーを自動登録します。
また、ChromiumベースのブラウザーとFirefoxで使用可能なCPUごとに1つのスロットを作成します。
SafariおよびInternet Explorerの場合、作成されるスロットは1つだけです。
特定の構成を通じて、Dockerコンテナーでセッションを実行できます。
次の[セクション]({{< ref "setting_up_your_own_grid.md" >}})で構成の詳細を確認できます。

ノードは受信したコマンドを実行するだけで、評価、判断、制御は行いません。
ノードが実行されているマシンは、他のコンポーネントと同じオペレーティングシステムを持つ必要はありません。
たとえば、WindowsノードにはInternet Explorerをブラウザーオプションとして提供する機能がありますが、
これはLinuxまたはMacでは不可能です。

## Session Map

The Session Map is a data store that keeps the information of the session id and the Node 
where the session is running. It serves as a support for the Router in the process of 
forwarding a request to the Node. The Router will ask the Session Map for the Node 
associated to a session id.

## New Session Queuer, New Session Queue

The New Session Queuer is the only
component which can communicate with the New Session Queue. It handles all queue operations like
add to manipulate the queue. It has configurable parameters for setting 
the request timeout and request retry interval.

The New Session Queuer receives the new session request from the Router and adds it to the queue. 
The queuer waits until it receives the response for the request. 
If the request times out, the request is rejected immediately and not added to the queue. 

Upon successfully adding the request to the queue, Event Bus triggers an event. 
The Distributor picks up this event and polls the queue. It now attempts to create a session.

If the requested capabilities do not exist in any of the registered Nodes, then the request is rejected
immediately and the client receives a response.

If the requested capabilities match the capabilities of any of Node slots, Distributor attempts to get the
available slot. If all the slots are busy, the Distributor will ask the queuer to add the request 
to the front of the queue. The Distributor receives the request again after the request retry interval. 
It will attempt retries until the request is successful or has timed out. 
If request times out while retrying or adding to the front of the queue its rejected.

After getting an available slot and session creation, the Distributor passes the new session response 
to the New Session Queuer via the Event Bus. The New Session Queuer will respond to the client when it
receives the event.

## Event Bus

The Event Bus serves as a communication path between the Nodes, Distributor, New Session Queuer, and Session Map. 
The Grid does most of its internal communication through messages, avoiding expensive HTTP calls. 
When starting the Grid in its fully distributed mode, the Event Bus is the first component that should be started. 

## Gridの役割

Grid3では、コンポーネントはハブとノードであり、スタンドアロンモードでGridを起動することでそれらを一緒に実行することが可能でした。 
同じ概念がGrid4でも利用可能であり、上記のコンポーネントのいくつかをグループ化することでハブを実行することが可能です。
また、スタンドアロンモードですべてのコンポーネントを一緒に実行することも可能です。

### ハブ

ハブは、次のコンポーネントの結合です。

* ルーター
* ディストリビューター
* セッションマップ
* イベントバス

従来のハブとノードのセットアップを可能にします。

### スタンドアロン

前述のように、スタンドアロンはすべてのコンポーネントの結合であり、ユーザーの目には、それらは1つのコンポーネントとして実行されます。 
これには、ハブの一部であるすべてのコンポーネントと1つのノードが含まれます。 
スタンドアロンモードで起動すると、1つの完全に機能するGridを使用できます。