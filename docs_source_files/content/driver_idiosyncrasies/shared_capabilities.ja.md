---
title: "共有機能"
weight: 1
---

In-order to create a new session by Selenium WebDriver, 
local end should provide the basic capabilities to remote end. 
The remote end uses the same set of capabilities to 
create a session and describes the current session features. 

WebDriver provides capabilities that each remote 
end will/should support the implementation. 
Following are the capabilities that WebDriver supports:

## browserName:

This capability is used to set the `browserName` for a given session. 
If the specified browser is not installed at the 
remote end, the session creation will fail

## browserVersion: 

This capability is optional, this is used to 
set the available browser version at remote end. 
For Example, if ask for Chrome version 75 on a system that 
only has 80 installed, the session creation will fail

### ページロード戦略
URLを介して新しいページに移動する場合、デフォルトでは、Seleniumは応答する前にページが完全にロードされるまで待機します。
これは初心者には効果的ですが、多数のサードパーティリソースをロードするページで長い待ち時間を引き起こす可能性があります。
デフォルト以外の戦略を使用すると、このような場合にテストの実行を高速化できますが、ページの要素がロードされてサイズが変更されると、ページ上の要素の位置が変化する不安定さを引き起こします。

次の表で説明するように、ページロード戦略は [document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState) を問い合わせます。

| 戦略 | Ready State | 注釈 |
| -------- | ----------- | ----- |
| normal | complete | デフォルトで使用され、すべてのリソースがダウンロードされるまで待機します |
| eager | interactive | DOMアクセスの準備はできていますが、画像などの他のリソースがまだ読み込まれている可能性があります |
| none | Any | WebDriverをまったくブロックしません |
