---
title: "リンクスパイダー"
linkTitle: "リンクスパイダー"
weight: 7
aliases: [
"/documentation/ja/worst_practices/link_spidering/",
"/ja/documentation/worst_practices/link_spidering/"
] 
---


WebDriverを使用してリンクをスパイダーすることは、実行できないためではなく、最も理想的なツールではないため明らかに推奨される方法ではありません。
WebDriverの起動には時間が必要であり、テストの記述方法によっては、ページに到達してDOMを通過するために数秒から1分かかる場合があります。

このためにWebDriverを使用する代わりに、[curl](//curl.haxx.se/) コマンドを実行するか、BeautifulSoupなどのライブラリを使用することにより、これらの方法はブラウザーの作成やページへの移動に依存しないため、時間を大幅に節約できます。
このタスクにWebDriverを使用しないことで、時間を大幅に節約できます。
