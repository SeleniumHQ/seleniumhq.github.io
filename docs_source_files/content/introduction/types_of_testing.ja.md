---
title: "テストの種類"
weight: 3
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Japanese. Do you speak Japanese? Help us to translate
it by sending us pull requests!
{{% /notice %}}

### Acceptance testing
This type of testing is done to determine if a feature or system
meets the customer expectations and requirements. 
This type of testing generally involves the customer's 
cooperation or feedback, being a validation activity that
answers the question:
>Are we building the **_right_** product?.

For web applications, the automation of this testing can be done
directly with Selenium by simulating user expected behaviour.
This simulation could be done by record/playback or through the
different supported languages as explained in this documentation.
Note: Acceptance testing is a subtype of **_functional testing_**,
which some people might also refer to.
            
### Functional testing
This type of testing is done to determine if a
feature or system functions properly without issues. It checks
the system at different levels to ensure that all scenarios
are covered and that the system does _what's_ 
supposed to do. It's a verification activity that
answers the question:
>Are we building the product **_right?_**.
             
This generally includes: the tests work without errors 
(404, exceptions...), in a usable way (correct redirections),   
in an accessible way and matching its specifications 
(see **_acceptance testing_** above).

Webアプリケーションの場合、期待されるリターンをシミュレートすることにより、このテストの自動化をSeleniumで直接実行できます。
このシミュレーションは、このドキュメントで説明されているように、記録/再生またはサポートされているさまざまな言語で実行できます。

### パフォーマンステスト
その名前が示すように、パフォーマンステストは、アプリケーションのパフォーマンスを測定するために行われます。

パフォーマンステストには2つの主なサブタイプがあります。

#### ロードテスト
ロードテストは、定義されたさまざまな負荷（通常、特定の数のユーザーが同時に接続されている場合）でアプリケーションがどの程度機能するかを確認するために行われます。

#### ストレステスト
ストレステストは、ストレス下（またはサポートされている最大負荷以上）でアプリケーションがどの程度機能するかを確認するために行われます。

一般に、パフォーマンステストは、Seleniumで書かれたテストを実行して、さまざまなユーザーがWebアプリの特定の機能を押して、意味のある測定値を取得することをシミュレートして実行されます。

これは通常、メトリックを取得する他のツールによって行われます。
そのようなツールの1つが **_JMeter_** です。

Webアプリケーションの場合、測定する詳細には、スループット、待ち時間、データ損失、個々のコンポーネントの読み込み時間などが含まれます…

注1：すべてのブラウザには、開発者のツールセクションにパフォーマンスタブがあります（F12キーを押すとアクセス可能）

注2：これは一般に機能/機能ごとではなくシステムごとに測定されるため、 **_非機能テスト_** のサブタイプです。

### 回帰テスト
このテストは通常、変更、修正、または機能の追加後に行われます。

変更によって既存の機能が破壊されないようにするために、すでに実行されたいくつかのテストが再度実行されます。

再実行されるテストのセットは、完全または部分的なものにすることができ、アプリケーションおよび開発チームに応じて、いくつかの異なるタイプを含めることができます。

### テスト駆動開発 (TDD)
テストタイプそのものではなく、TDDはテストが機能の設計を推進する反復的な開発方法論です。

各サイクルは、機能がパスする単体テストのセットを作成することから始まります（最初に実行すると失敗します）。

この後、テストに合格するための開発が行われます。
別のサイクルを開始してテストが再度実行され、すべてのテストに合格するまでこのプロセスが続行されます。

これは、欠陥が発見されるほどコストが安くなるという事実に基づいて、アプリケーションの開発をスピードアップすることを目的としています。

### ビヘイビア駆動開発 (BDD)
BDDは、上記に基づいた反復開発方法論（TDD）でもあり、その目的は、アプリケーションの開発にすべての関係者を関与させることです。

各サイクルは、いくつかの仕様を作成することから始まります（これは失敗するはずです）。
次に、失敗する単体テスト（これも失敗するはずです）を作成し、開発を作成します。

このサイクルは、すべてのタイプのテストに合格するまで繰り返されます。

そのためには、仕様言語が使用されます。
すべての関係者が理解でき、単純で、標準的かつ明示的でなければなりません。
ほとんどのツールは、この言語として **_Gherkin_**  を使用します。

目標は、潜在的な受入エラーも対象とすることでTDDよりも多くのエラーを検出し、当事者間のコミュニケーションを円滑にすることです。

現在、仕様を記述し、 **_Cucumber_** や **_SpecFlow_** などのコード関数と一致させるための一連のツールが利用可能です。

Selenium上に一連のツールが構築されており、BDD仕様を実行可能コードに直接変換することにより、このプロセスをさらに高速化しています。 これらのいくつかは、 _**JBehave、Capybara、およびRobot Framework**_ です。
