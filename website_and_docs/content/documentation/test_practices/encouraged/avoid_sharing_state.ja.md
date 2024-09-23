---
title: "状態を共有しない"
linkTitle: "状態を共有しない"
weight: 8
aliases: [
"/documentation/ja/guidelines_and_recommendations/avoid_sharing_state/",
"/ja/documentation/guidelines/avoid_sharing_state/"
]
---

いくつかの場所で言及されていますが、再度言及する価値があります。
テストが互いに分離されていることを確認してください。

* テストデータを共有しないでください。
アクションを実行する1つを選択する前に、それぞれが有効な注文をデータベースに照会するいくつかのテストを想像してください。
2つのテストで同じ順序を選択すると、予期しない動作が発生する可能性があります。

* 別のテストで取得される可能性のあるアプリケーション内の古いデータを削除します。 例: 無効な注文レコード

* テストごとに新しいWebDriverインスタンスを作成します。
これにより、テストの分離が保証され、並列化がより簡単になります。

    * If you choose [pytest](https://pytest.org/) as your test runner, this can be 
    easily done by yielding your driver in a global fixture. This way each test gets its own 
    driver instance, and you can ensure that drivers always quit after a test is finished 
    (pass or fail).
