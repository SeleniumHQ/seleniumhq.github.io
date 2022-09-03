---
title: "Seleniumのサイトとドキュメントに貢献する"
linkTitle: "Seleniumのサイトとドキュメントに貢献する"
weight: 2
requiresTranslation: true
description: >-
    Information on improving documentation and code examples for Selenium
aliases: 
        [
          "/documentation/ja/contributing/",
          "/documentation/ja/front_matter/typographical_conventions/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Japanese. Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

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

## 貢献

Seleniumプロジェクトは新しいコントリビュータを歓迎します。目立った価値ある貢献を継続的に行った個人は _コミッター_
として認められ、プロジェクトへのコミットアクセス件が与えられます。

本ガイドでは、貢献のプロセスについて説明します。

### ステップ 1: フォーク

[Github](https://github.com/seleniumhq/seleniumhq.github.io)上のプロジェクトをフォークし、コピーをローカルにチェックアウトしてください。

```shell
% git clone git@github.com:seleniumhq/seleniumhq.github.io.git
% cd seleniumhq.github.io
```

#### 依存関係: Hugo

We use [Hugo](https://gohugo.io/) and the [Docsy theme](https://www.docsy.dev/)
to build and render the site. You will need the “extended” 
Sass/SCSS version of the Hugo binary to work on this site. We recommend
to use Hugo 0.101.0 or higher.

Please follow the [Install Hugo](https://www.docsy.dev/docs/getting-started/#install-hugo) 
instructions from Docsy.

### ステップ 2: ブランチの作成

フィーチャーブランチを作成し、ハックを開始します。:

```shell
% git checkout -b my-feature-branch
```

我々はHEADベースの開発を行っています。つまり、全ての変更はtrunkブランチ上に直接適用されます。

### ステップ 3: 変更を加える

The repository contains the site and docs. Before jumping into
making changes, please initialize the submodules and install the
needed dependencies (see commands below). To make changes to the site, 
work on the `website_and_docs` directory. To see a live preview of 
your changes, run `hugo server` on the site's root directory.

```shell
% git submodule update --init --recursive
% cd website_and_docs
% hugo server
```

See [Style Guide]({{< ref "style.md" >}}) for more information on our conventions for contribution

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
