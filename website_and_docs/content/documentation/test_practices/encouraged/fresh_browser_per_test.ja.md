---
title: "テストごとに新しいブラウザを起動する"
linkTitle: "テストごとに新しいブラウザを起動する"
weight: 9
aliases: [
"/documentation/ja/guidelines_and_recommendations/fresh_browser_per_test/",
"/ja/documentation/guidelines/fresh_browser_per_test/"
]
---


クリーンな既知の状態から各テストを開始します。
理想的には、テストごとに新しい仮想マシンを起動します。
新しい仮想マシンの起動が実用的でない場合は、少なくともテストごとに新しいWebDriverを起動してください。
Firefoxの場合、既知のプロファイルでWebDriverを起動します。

```java
FirefoxProfile profile = new FirefoxProfile(new File("pathToFirefoxProfile"));
WebDriver driver = new FirefoxDriver(profile);
```
