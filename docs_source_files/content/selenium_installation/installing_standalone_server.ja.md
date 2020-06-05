---
title: "スタンドアロンサーバのインストール"
weight: 3
---

 [Grid]({{< ref "/grid/_index.md" >}})を使う予定なら、[selenium-server-standalone JAR](https://selenium.dev/downloads/)ファイルをダウンロードする必要があります。_selenium-server-standalone_ jarは決してアップロードされることはありませんが、[selenium-server](//repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-server/)を通して全てのコンポーネントが利用可能です。
 このスタンドアロンJARはリモートSeleniumサーバとクライアントサイドバインディングを含む全てが入っています。
 これはプロジェクトでselenium-server-standalone jarを使えば、selenium-javaやブラウザ固有のjarを追加しなくても良いということです。

 ```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-server</artifactId>
  <version>3.X</version>
</dependency>
```
