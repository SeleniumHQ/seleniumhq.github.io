---
title: "Seleniumのサイトとドキュメントに貢献する"
linkTitle: "Seleniumのサイトとドキュメントに貢献する"
weight: 2
description: >-
    Seleniumのドキュメントとコード例を改善するための情報
aliases: 
        [
          "/documentation/ja/contributing/",
          "/documentation/ja/front_matter/typographical_conventions/"
        ]
---

Seleniumは大きなソフトウェアプロジェクトであり、そのサイトとドキュメントは、物事の仕組みを理解し、その可能性を活用する効果的な方法を学ぶための鍵となります。

このプロジェクトには、Seleniumのサイトとドキュメントの両方が含まれています。これは、Seleniumを効果的に使用する方法、Seleniumに参加する方法、およびSeleniumに貢献する方法に関する最新情報を提供するための継続的な取り組みです（特定のリリースを対象としていません）。

サイトおよびドキュメントへの貢献は、以下のセクションで説明されているプロセスに従います。

---

Seleniumプロジェクトは、皆様からのコントリビューションを歓迎します。
お手伝いをいただくには、いくつかの方法があります:

## イシュー報告

新しい問題を報告したり、既存の問題についてコメントしたりするときは、議論がSeleniumソフトウェア、そのサイトおよび/またはドキュメントに関する具体的な技術問題に関連していることを確認してください。

Seleniumのすべてのコンポーネントは、時間の経過とともに非常に速く変化するため、ドキュメントが古くなる可能性があります。
このようなケースを見つけた場合には、遠慮なくイシューを作成してください。
また、ドキュメントを最新の状態に更新する方法をご存知でしたら、関連する変更を含むプルリクエストを送ってしてください。

見つかったものが問題であるかどうかわからない場合、[https://selenium.dev/support](https://selenium.dev/support)に記載されているコミュニケーション手段にて質問してください。


## 何を手伝うか

### 例の作成

追加が必要な例には、次のマークが付いています: {{% badge-code %}}

すべてのコード例をCIで実行できるようにし、サイト上のすべてのコードをコピー＆ペーストして実行できることを確認したいと考えています。そのため、コードを[examplesディレクトリ](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/)の適切な場所に配置します。
ドキュメントの各ページは各言語のテストファイルに関連しており、命名規則に従う必要があります。
例えば、このページ（https://www.selenium.dev/documentation/webdriver/browsers/chrome/）の例は以下のファイルに追加されています:
* `"/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java"`
* `"/examples/python/tests/browsers/test_chrome.py"`
* `"/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs"`
* `"/examples/ruby/spec/browsers/chrome_spec.rb"`
* `"/examples/javascript/test/browser/chromeSpecificCaps.spec.js"`

各例はそれぞれ独自のテストが必要です。理想的には、各テストにはコードが意図したとおりに動作することを確認するアサーションが含まれています。
コードを適切なファイル内の独自のテストにコピーしたら、Markdownファイルで参照する必要があります。

例えば、Rubyのtabは次のようになります:

        {{</* tab header="Ruby" */>}}
        {{</* gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L8-L9" */>}}
        {{</* /tab */>}}

末尾の行番号は、実際に表示される項目を表すコードの行のみを表します。
ユーザーがより多くのコンテキストを必要とする場合、GitHubページへのリンクをクリックすると完全なコンテキストが表示されます。

ページにテストを追加する場合は、Markdownファイル内の他のすべての行番号が正しいことを確認してください。
ページの先頭にテストを追加すると、そのファイルの行番号を持つドキュメント内のすべての参照が更新されます。

コード例では、シナリオを示すために関連するWebサイトやWebページが必要になる場合があります。
例が安定して動作するように、https://www.selenium.dev/selenium/web/ で利用できるテスト用Webページを使用することを推奨します。

最後に、CIでテストがパスすることを確認してください。


### 例の移動

移動が必要な例には、次のマークが付いています: {{% badge-examples %}}

[例の作成](#例の作成)セクションのすべてが適用されますが、1つ追加があります。

tabには`text=true`を含めてください。デフォルトではtabはコード用にフォーマットされるため、Markdownや他のショートコードステートメント（`gh-codeblock`など）を使用するには、`text=true`を宣言する必要があります。
ほとんどの例では、`tabpane`が`text=true`を宣言しますが、tabの一部にコード例が含まれている場合、`tabpane`はそれを指定できず、自動コードフォーマットが不要なtabでは指定する必要があります。


## 貢献

Seleniumプロジェクトは新しいコントリビュータを歓迎します。目立った価値ある貢献を継続的に行った個人は _コミッター_
として認められ、プロジェクトへのコミットアクセス件が与えられます。

本ガイドでは、貢献のプロセスについて説明します。

### ステップ 1: フォーク

[GitHub](https://github.com/seleniumhq/seleniumhq.github.io)上のプロジェクトをフォークし、コピーをローカルにチェックアウトしてください。

```shell
% git clone git@github.com:seleniumhq/seleniumhq.github.io.git
% cd seleniumhq.github.io
```

#### 依存関係: Hugo

[Hugo](https://gohugo.io/)と[Docsyテーマ](https://www.docsy.dev/)を使用してサイトの構築とレンダリングをしています。このサイトの作業をするには、Hugoバイナリの“拡張”Sass/SCSSバージョンが必要です。Hugo 0.148.2の使用を推奨します。

[Docsyのインストール手順](https://www.docsy.dev/docs/getting-started/#install-hugo)に従ってください。

### ステップ 2: ブランチの作成

フィーチャーブランチを作成し、ハックを開始します。:

```shell
% git checkout -b my-feature-branch
```

我々はHEADベースの開発を行っています。つまり、全ての変更はtrunkブランチ上に直接適用されます。

### ステップ 3: 変更を加える

リポジトリにはサイトとドキュメントが含まれています。 変更を加える前に、submoduleを初期化し、必要な依存関係をインストールしてください（以下のコマンドを参照）。サイトに変更を加えるには、`website_and_docs` ディレクトリで作業してください。変更のライブプレビューを確認するには、サイトのルートディレクトリで `hugo server`を実行してください。

```shell
% git submodule update --init --recursive
% cd website_and_docs
% hugo server
```

寄稿に関する規約の詳細については、 [スタイルガイド]({{< ref "style.md" >}}) をご覧ください。

### ステップ 4: コミット

まず、gitがあなたの名前とメールアドレスを知っていることを確認してください:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**適切なコミットメッセージを書くことは重要です。**コミットメッセージには、変更された内容、理由、修正されたイシューへの参照（ある場合）を記述する必要があります。作成するときは、次のガイドラインに従ってください。:

1. 最初の行は約50文字以下で、変更の簡単な説明を含める必要があります。
2. 2行目は空白のままにします。
3. 他のすべての行を72列で折り返します。
4. `Fixes #N`を含めてください。ここでは _N_ がこのコミットで修正したイシュー番号です（存在する場合）。

適切なコミットメッセージは次のようになります:

```text
explain commit normatively in one line

Body of commit message is a few lines of text, explaining things
in more detail, possibly giving some background about the issue
being fixed, etc.

The body of the commit message can be several paragraphs, and
please do proper word-wrap and keep columns shorter than about
72 characters or so. That way `git log` will show things
nicely even when it is indented.

Fixes #141
```

最初の行は`git shortlog`や`git log --oneline`を実行した際に他人が最初に目にするため、意味のあるものでなければなりません。

### ステップ 5: リベース

あなたの作業を同期するため、(`git merge`ではなく)`git rebase`を時々実行してください。

```shell
% git fetch origin
% git rebase origin/trunk
```

### ステップ 6: テスト

あなたの変更が何も問題を起こしていないことを確認するため、常に忘れずに[ローカルサーバーの実行](https://gohugo.io/getting-started/usage/#livereload)を行ってください。

### ステップ 7: プッシュ

```shell
% git push origin my-feature-branch
```

https://github.com/yourusername/seleniumhq.github.io.git を開き、_Pull Request_を押し、フォームを入力してください。 **CLAに署名したことを示してください** (ステップ7を参照)

プルリクエストは通常数日以内にレビューされます。対応すべきコメントがある場合は、新しく(できれば
[fixups](http://git-scm.com/docs/git-commit)で)コミットし、同じブランチにプッシュしてください。

### ステップ 8: 統合

コードレビューが完了すると、コミッターがPRを取得し、リポジトリのtrunkブランチに統合します。
マスターブランチで履歴を線形に保持するのが好きなので、通常はブランチの履歴をスカッシュしてリベースします。

## コミュニケーション

プロジェクトのコントリビューターおよびコミュニティ全体と交流する方法については、全て https://selenium.dev/support で詳細が記載されています。
