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

This identifies the operating system at the remote-end, 
fetching the `platformName` returns the OS name. 

In could-based testing(SauceLabs or BrowserStack), 
setting `platformName` sets the os at the remote-end 

## acceptInsecureCerts

This capability checks whether expired (or) 
invalid `TLS Certificate` are used while navigating 
during a session.

If the capability is set to `false`, an 
[insecure certificate error](//developer.mozilla.org/ja/docs/Web/WebDriver/Errors/InsecureCertificate) 
will be returned as navigation encounters any domain 
certificate problems. If set to `ture`, invalid certificate will be 
trusted by the browser.

All self-signed certificates will be trusted by this capability by default. 
Onc set, `acceptInsecureCerts` capability will have effect for entire session