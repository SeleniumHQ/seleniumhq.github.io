---
title: "ファイルダウンロード"
weight: 2
---


Seleniumの管理下にあるブラウザーでリンクをクリックしてダウンロードを開始することは可能ですが、APIはダウンロードの進行状況を公開しないため、ダウンロードしたファイルのテストには理想的ではありません。
これは、ファイルのダウンロードは、Webプラットフォームとのユーザーインタラクションをエミュレートする重要な側面とは見なされないためです。
代わりに、Selenium（および必要なCookie）を使用してリンクを見つけ、 [libcurl](//curl.haxx.se/libcurl/) などのHTTPリクエストライブラリに渡します。

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Japanese. Do you speak Japanese? Help us to translate
it by sending us pull requests!
{{% /notice %}}

The [HtmlUnit driver](https://github.com/SeleniumHQ/htmlunit-driver) can download attachments by accessing them as input streams by implementing the [AttachmentHandler](https://htmlunit.sourceforge.io/apidocs/com/gargoylesoftware/htmlunit/attachment/AttachmentHandler.html) interface. The AttachmentHandler can the be added to the [HtmlUnit](https://htmlunit.sourceforge.io/) WebClient.
