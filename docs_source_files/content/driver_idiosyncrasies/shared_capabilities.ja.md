---
title: "共有Capabilities"
weight: 1
---

Selenium WebDriverで新しいセッションを作成するには、ローカルエンドがリモートエンドに基本的なCapabilities（ブラウザの設定情報）を提供する必要があります。
リモートエンドは、一連の同じCapabilitiesを使用してセッションを作成し、現在のセッション機能を描きます。

WebDriverは、各リモートエンドがCapabilitiesをサポートする/すべきCapabilitiesを提供します。
WebDriverがサポートするCapabilitiesは次のとおりです。

## browserName:

このCapabilityは、特定のセッションの `browserName` を設定するために使います。
指定されたブラウザがリモートエンドにインストールされていない場合、セッションの作成は失敗します。

## browserVersion: 

このCapabilityはオプションです。
これは、リモートエンドで使用可能なブラウザーバージョンを設定するために使います。
たとえば、Chromeバージョン80のみがインストールされているシステムでバージョン75を要求すると、セッションの作成は失敗します。

### ページロード戦略
URLを介して新しいページに移動する場合、デフォルトでは、Seleniumは応答する前にページが完全にロードされるまで待機します。
これは初心者には効果的ですが、多数のサードパーティリソースをロードするページで長い待ち時間を引き起こす可能性があります。
デフォルト以外の戦略を使用すると、このような場合にテストの実行を高速化できますが、ページの要素がロードされてサイズが変更されると、ページ上の要素の位置が変化する不安定さを引き起こします。

次の表で説明するように、ページロード戦略は [document.readyState](//developer.mozilla.org/ja/docs/Web/API/Document/readyState) を問い合わせます。

| 戦略 | Ready State | 注釈 |
| -------- | ----------- | ----- |
| normal | complete | デフォルトで使用され、すべてのリソースがダウンロードされるまで待機します |
| eager | interactive | DOMアクセスの準備はできていますが、画像などの他のリソースがまだ読み込まれている可能性があります |
| none | Any | WebDriverをまったくブロックしません |

## platformName

これにより、リモートエンドのオペレーティングシステムが識別され、 `platformName` を取得するとOS名が返されます。

クラウドベースのプロバイダーでは、 `platformName` を設定すると、リモートエンドのOSが設定されます。

## acceptInsecureCerts

この機能は、セッション中のナビゲーション中に、期限切れ（または）無効な `TLS証明書` が使用されているかどうかを確認します。

機能が `false` に設定されている場合、ナビゲーションでドメイン証明書の問題が発生すると、 
[insecure certificate error](//developer.mozilla.org/ja/docs/Web/WebDriver/Errors/InsecureCertificate)  が返されます。
`true` に設定すると、無効な証明書はブラウザーによって信頼されます。

すべての自己署名証明書は、デフォルトでこの機能によって信頼されます。
一度設定すると、 `acceptInsecureCerts` Capabilityはセッション全体に影響します。

## Session timeouts

A WebDriver `session` is imposed with a certain `session timeout`
interval, during which the user can control the behaviour
of executing scripts or retrieving information from the browser.

Each session timeout is configured with
combination of different `timeouts` as described below:

### Script Timeout:
Specifies when to interrupt an executing script in
a current browsing context. The default timeout **30,000**
is imposed when a new session is created by WebDriver.

### Page Load Timeout:
Specifies the time interval in which web page
needs to be loaded in a current browsing context.
The default timeout **300,000** is imposed when a
new session is created by WebDriver. If page load limits
a given/default time frame, the script will be stopped by
_TimeoutException_.

### Implicit Wait Timeout
This specifies the time to wait for the
implicit element location strategy when
locating elements. The default timeout **0**
is imposed when a new session is created by WebDriver.

## unhandledPromptBehavior

Specifies the state of current session's `user prompt handler`. 
Defaults to **dismiss and notify state**

### User Prompt Handler

This defines what action must take when a 
user prompt encounters at remote-end. This is defined by 
`unhandledPromptBehavior` capability and has the following states:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore
