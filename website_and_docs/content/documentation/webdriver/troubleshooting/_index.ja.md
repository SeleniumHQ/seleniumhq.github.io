---
title: "トラブルシューティング"
linkTitle: "トラブルシューティング"
weight: 20
description: >
  WebDriver の問題を解決する方法。
---

Selenium のエラーの根本原因が、常に明らかであるとは限りません。

1. Selenium 関連で最もよくあるエラーは、不十分な同期が原因です。
[待機戦略]({{< ref path="../waits" lang="en" >}})を参照してください。同期が原因かどうか確信が持てない場合は、
問題が発生している箇所に *一時的に* 長い待機時間をハードコードしてみてください。
明示的な待機を追加することで解決できるかどうかを判断できます。

2. プロジェクトに報告されるエラーの多くは、実際には Selenium がコマンドを送信する基盤となる
ドライバーの問題によるものです。同じコマンドを複数の[ブラウザ]({{< ref path="../browsers/" lang="en" >}})で
実行することで、ドライバーに問題があるかどうかを切り分けられます。

3. 操作方法について質問がある場合は、支援を受ける方法として[サポートオプション](/support/)を
確認してください。

4. Selenium のコードに問題を見つけたと思う場合は、GitHub で
[バグ報告](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+)
を作成してください。

