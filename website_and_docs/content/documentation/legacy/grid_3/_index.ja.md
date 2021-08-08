---
title: "Grid 3"
linkTitle: "Grid 3"
weight: 6
aliases: ["/documentation/ja/grid/grid_3/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Most of the documentation found in this section is still in English.
   Please note we are not accepting pull requests to translate this content
   as translating documentation of legacy components does not add value to
   the community nor the project.
</p>
{{% /pageinfo %}}

_Selenium Grid_ は、SeleniumテストがコマンドをリモートWebブラウザーインスタンスにルーティングできるようにする賢いプロキシサーバーです。
その目的は、複数のマシンで並行してテストを実行する簡単な方法を提供することです。

Selenium Gridでは、1つのサーバーが、JSON形式のテストコマンドを1つ以上の登録済みのグリッドノードにルーティングするハブとして機能します。
テストはハブに接続して、リモートブラウザーインスタンスへのアクセスを取得します。
ハブには、アクセスを提供する登録済みサーバーのリストがあり、これらのインスタンスを制御できます。

Selenium Gridを使用すると、複数のマシンで並行してテストを実行し、さまざまなブラウザーバージョンとブラウザー構成を（個々のテストではなく）一元的に管理できます。

Selenium Gridは特効薬ではありません。
一般的な委譲および配布の問題のサブセットを解決しますが、たとえばインフラストラクチャを管理せず、特定のニーズに適さない場合があります。
