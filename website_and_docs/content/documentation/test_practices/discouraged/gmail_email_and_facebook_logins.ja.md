---
title: "Gmail、Eメール、Facebookログイン"
linkTitle: "Gmail、Eメール、Facebook"
weight: 4
aliases: [
"/documentation/ja/worst_practices/gmail_email_and_facebook_logins/",
"/ja/documentation/worst_practices/gmail_email_and_facebook_logins/"
] 
---


複数の理由から、WebDriverを使用してGmailやFacebookなどのサイトにログインすることはお勧めしません。
これらのサイトの使用条件（アカウントがシャットダウンされるリスクがある）に違反することは別として、それは遅く、信頼性がありません。

理想的なプラクティスは、メールプロバイダーが提供するAPIを使用すること、またはFacebookの場合、テストアカウントや友人などを作成するためのAPIを公開する開発者ツールサービスを使用することです。
APIの使用は少し大変な作業のように思えるかもしれませんが、速度、信頼性、および安定性に見返りがあります。
また、APIが変更されることはほとんどありませんが、WebページとHTMLロケーターは頻繁に変更され、テストフレームワークを更新する必要があります。

テストの任意の時点でWebDriverを使用してサードパーティのサイトにログインすると、テストが長くなるため、テストが失敗するリスクが高くなります。
一般的な経験則として、テストが長くなるほど脆弱で信頼性が低くなります。

[W3C準拠](//w3c.github.io/webdriver/webdriver-spec.html) のWebDriver実装は、サービス拒否攻撃を軽減できるように、`navigator`オブジェクトに`WebDriver`プロパティで注釈を付けます。
