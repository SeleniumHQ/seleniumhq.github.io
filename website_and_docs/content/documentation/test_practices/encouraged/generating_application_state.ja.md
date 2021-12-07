---
title: "アプリケーション状態の生成"
linkTitle: "アプリケーション状態の生成"
weight: 5
aliases: [
"/documentation/ja/guidelines_and_recommendations/generating_application_state/",
"/ja/documentation/guidelines/generating_application_state/"
]
---

Seleniumはテストケースの準備に使用しないでください。
テストケースのすべての反復アクションと準備は、他の方法で行う必要があります。
たとえば、ほとんどのWeb UIには認証があります（ログインフォームなど）。
すべてのテストの前にWebブラウザーからのログインをなくすことで、テストの速度と安定性の両方が向上します。
AUT* にアクセスするためのメソッドを作成する必要があります（APIを使用してログインし、Cookieを設定するなど）。
また、テスト用にデータをプリロードするメソッドの作成は、Seleniumを使用して実行しないほうがいいです。
前述のように、AUT* のデータを作成するには、既存のAPIを活用する必要があります。

***AUT**: Application under test（テスト対象アプリケーション）
