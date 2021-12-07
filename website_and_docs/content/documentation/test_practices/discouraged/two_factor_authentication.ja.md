---
title: "二要素認証"
linkTitle: "二要素認証"
weight: 8
aliases: [
"/documentation/ja/worst_practices/two_factor_authentication/",
"/ja/documentation/worst_practices/two_factor_authentication/"
] 
---

_2FA_ として知られている2要素認証は、"Google Authenticator" 、
"Microsoft Authenticator" などの"Authenticator" モバイルアプリを使用して、
またはSMS、電子メールで認証することにより、
ワンタイムパスワード（OTP）を生成する認証メカニズムです。 
これをシームレスかつ一貫して自動化することは、Seleniumの大きな課題です。
このプロセスを自動化する方法はいくつかあります。
しかし、これはSeleniumテストの上にある別のレイヤーであり、また安全でもありません。
したがって、2FAの自動化を回避したほうがいいです。

2FAチェックを回避するいくつかの選択肢があります。

* テスト環境で特定のユーザーの2FAを無効にして、
それらのユーザー資格情報を自動化で使用できるようにします。
* テスト環境で2FAを無効にします。
* 特定のIPからログインする場合は、2FAを無効にします。
そうすれば、テストマシンのIPを設定してこれを回避できます。
