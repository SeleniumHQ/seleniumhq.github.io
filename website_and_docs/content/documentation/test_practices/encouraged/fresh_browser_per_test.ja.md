---
title: "テストごとに新しいブラウザを起動する"
linkTitle: "テストごとに新しいブラウザを起動する"
weight: 11
aliases: [
"/documentation/ja/guidelines_and_recommendations/fresh_browser_per_test/",
"/ja/documentation/guidelines/fresh_browser_per_test/"
]
---


各テストは、クリーンで既知の状態から開始します。
理想的には、テストごとに新しい仮想マシンを起動してください。
新しい仮想マシンを起動することが現実的でない場合は、
少なくともテストごとに新しい WebDriver を起動してください。
GeckoDriver や ChromeDriver など、ほとんどのブラウザドライバーはデフォルトで、
新しいユーザープロファイルを使用したクリーンで既知の状態で起動します。
```java
WebDriver driver = new FirefoxDriver();
```
