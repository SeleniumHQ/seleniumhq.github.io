---
title: "クイックツアー"
weight: 1
---

Seleniumは一つのツールやAPIではありません。たくさんのツールから構成されています。

## WebDriver

デスクトップのウェブサイトのテスト自動化をはじめるのなら、WebDriver APIを使いましょう。
_[WebDriver]({{< ref "/webdriver/_index.md" >}})_ はブラウザ自動化のAPIを使用します。このAPIは、ブラウザをコントロールしてテストを実行するためにブラウザベンダーによって提供されています。これは現実のユーザーがブラウザを操作するかのように動きます。
WebDriverのAPIはアプリケーションのコードと一緒にコンパイルする必要がありませんから、全く邪魔になりません。
これによって、あなたは本番環境と同じアプリケーションをテストすることができます。

## IDE

_[IDE](https://selenium.dev/selenium-ide)_ (Integrated Development Environment: 統合開発環境)はSeleniumのテストケースを開発するためのツールです。
これは利用しやすいChromeとFirefoxの拡張機能であり、テストケースを開発するための一般に最も効率的なツールです。
IDEはあなたのブラウザ上で、その要素で定義されたパラメーターと共にSeleniumのコマンドを使いユーザーの動作を記録します。
これは時間の節約だけでなく、Seleniumスクリプトのシンタックスを学ぶための優れた方法です。

## Grid

Selenium Grid allows you to run test cases in different 
machines across different platforms. The control of 
triggering the test cases is on the local end, and 
when the test cases are triggered, they are automatically 
executed by the remote end.

After the development of the WebDriver tests, you may face 
the need of running your tests on multiple browser and 
operating system combinations.
This is where _[Grid]({{< ref "/grid/_index.md" >}})_ comes into the picture.