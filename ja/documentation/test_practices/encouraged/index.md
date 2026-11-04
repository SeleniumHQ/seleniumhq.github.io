# 推奨された行動

> Seleniumプロジェクトからのテストに関するいくつかのガイドラインと推奨事項

URL: https://www.selenium.dev/ja/documentation/test_practices/encouraged/

---

「ベストプラクティス」に関するメモ：このドキュメントでは、"ベストプラクティス"というフレーズを意図的に避けています。
すべての状況に有効なアプローチはありません。
"ガイドラインとレコメンデーション"というアイデアを好みます。
これらを一通り読み、特定の環境でどのアプローチが効果的かを慎重に決定することをお勧めします。

機能テストは、多くの理由で適切に行うのが困難です。
まるでアプリケーションの状態、複雑さ、および依存関係が、テストを十分に難しくしないと思えるほど、ブラウザ（特にクロスブラウザの非互換性）を扱うのは、良いテストの作成を難しくします。

Seleniumは、機能的なユーザーインタラクションを簡単にするツールを提供しますが、適切に設計されたテストスイートの作成には役立ちません。
この章では、機能的なWebページの自動化に取り組む方法に関するアドバイス、ガイドライン、および推奨事項を提供します。

この章では、長年にわたって成功を収めてきたSeleniumの多くのユーザーの間で人気のあるソフトウェア設計パターンを記録します。

---

Section pages:

- [ページオブジェクトモデル](https://www.selenium.dev/ja/documentation/test_practices/encouraged/page_object_models/index.md)
- [ドメイン固有言語（DSL）](https://www.selenium.dev/ja/documentation/test_practices/encouraged/domain_specific_language/index.md)
- [アプリケーション状態の生成](https://www.selenium.dev/ja/documentation/test_practices/encouraged/generating_application_state/index.md)
- [モック外部サービス](https://www.selenium.dev/ja/documentation/test_practices/encouraged/mock_external_services/index.md)
- [改善されたレポート](https://www.selenium.dev/ja/documentation/test_practices/encouraged/improved_reporting/index.md)
- [ロケータをうまく扱うTips](https://www.selenium.dev/ja/documentation/test_practices/encouraged/locators/index.md): どのロケータを指定すべきか、コード内でロケータをどう管理すると良いか。
- [状態を共有しない](https://www.selenium.dev/ja/documentation/test_practices/encouraged/avoid_sharing_state/index.md)
- [テストの独立性](https://www.selenium.dev/ja/documentation/test_practices/encouraged/test_independency/index.md)
- [Fluent APIの使用を検討する](https://www.selenium.dev/ja/documentation/test_practices/encouraged/consider_using_a_fluent_api/index.md)
- [テストごとに新しいブラウザを起動する](https://www.selenium.dev/ja/documentation/test_practices/encouraged/fresh_browser_per_test/index.md)
