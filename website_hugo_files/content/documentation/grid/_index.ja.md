---
title: "Grid"
linkTitle: "Grid"
weight: 9
description: >
  Want to run tests in parallel across multiple machines? Then, Grid is for you.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Japanese. Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium Grid では、クライアントからリモートブラウザーインスタンスに送信されたコマンドを
ルーティングすることにより、（仮想または実）リモートマシン上でWebDriverスクリプトを実行できます。 
複数のマシンで並行してテストを実行する簡単な方法を提供することを目的としています。

Selenium Gridは、複数のマシンで並行してテストを実行し、異なるブラウザーバージョンと
ブラウザーの設定を（個別のテストではなく）一元的に管理することができます。

Selenium Gridは特効薬ではありません。 
一般的な委譲および配布の問題のサブセットを解決しますが、たとえばインフラストラクチャを管理せず、特定のニーズに適さない場合があります。

## 目的と主な機能

* すべてのテストの中央エントリポイント
* ブラウザーが実行されるノード・環境の管理と制御
* スケーリング
* テストを並列実行
* クロスプラットフォームでのテスト
* 負荷分散
