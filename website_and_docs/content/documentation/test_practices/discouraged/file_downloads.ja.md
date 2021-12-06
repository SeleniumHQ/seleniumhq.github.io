---
title: "ファイルダウンロード"
linkTitle: "ファイルダウンロード"
weight: 2
aliases: [
"/documentation/ja/worst_practices/file_downloads/",
"/ja/documentation/worst_practices/file_downloads/"
] 
---


Seleniumの管理下にあるブラウザーでリンクをクリックしてダウンロードを開始することは可能ですが、APIはダウンロードの進行状況を公開しないため、ダウンロードしたファイルのテストには理想的ではありません。
これは、ファイルのダウンロードは、Webプラットフォームとのユーザーインタラクションをエミュレートする重要な側面とは見なされないためです。
代わりに、Selenium（および必要なCookie）を使用してリンクを見つけ、 [libcurl](//curl.haxx.se/libcurl/) などのHTTPリクエストライブラリに渡します。

[HtmlUnitドライバー](https://github.com/SeleniumHQ/htmlunit-driver)は、
[AttachmentHandler](https://htmlunit.sourceforge.io/apidocs/com/gargoylesoftware/htmlunit/attachment/AttachmentHandler.html) インターフェイスを実装することで、
入力ストリームとして添付ファイルにアクセスすることによって、添付ファイルをダウンロードできます。 
AttachmentHandlerは、[HtmlUnit](https://htmlunit.sourceforge.io/) に追加できます。
