---
title: Seleniumドキュメンテーションに貢献
disableToc: true
---

Seleniumは大きなソフトウェアプロジェクトです、そしてドキュメンテーションはどのように機能しているかを理解し、ソフトウェアの持ち得る可能性の効果的な利用方法を学ぶための鍵です。

ドキュメンテーションの一部はまだ[**www.seleniumhq.org** リポジトリ](https://github.com/SeleniumHQ/www.seleniumhq.org)から配信されています。
このドキュメンテーションはSelenium RCと他の旧式の機能にあまりにも重点が置かれているので私達は段階的に廃止し、新しく書き直しをしていきます。

この新しいドキュメンテーションプロジェクトはSeleniumのドキュメンテーションを一から書き直すことから始まりました。これは更新されたSeleniumを効果的に使用する為のハンドブックを提供する為の継続的な努力です（特定なリリースを目標するのではない）。私達は古いドキュメンテーションのうちまだ有効なものを移行したいと思っています。

新しいドキュメンテーションの貢献には下記の貢献についてのセクションに書いてある手順をもとにしてます。多少の時間をかけて読むことによってドキュメンテーションに馴染んでください。
[もっと詳しく読む]({{< ref "/introduction/about_this_documentation.ja.md" >}})

---

Seleniumプロジェクトはすべての人からの貢献を歓迎します。手伝うにはいくつかの方法があります:

## 課題を報告する

新たに問題を報告する時や、既存の課題についてコメントする時にはSeleniumソフトウェアそのものかそれに関わるドキュメンテーションの具体的な技術問題に付いてのみ討議するようにしてください。

すべてのSeleniumのコンポーネントは時間と共にかなり早く更新されます、なのでこれがドキュメンテーションを形骸化を招くかもしれません。もしそのようなことを発見した場合には、迷わず問題を報告してください。または、ひょっとしてドキュメンテーションの更新方法を知っているならば、修正点を含むプルリクエストを提出してください。

もし、見つけたものが問題かどうか確信がないときは[selenium-users@ メーリングリスト](https://groups.google.com/forum/#!forum/selenium-users)で質問してください,または[irc.freenode.org](https://webchat.freenode.net/)か[Slack](https://seleniumhq.herokuapp.com/)の`#selenium`チャンネルに参加してください。

## 貢献

Seleniumプロジェクトは新たな貢献者を歓迎します。 重要かつ価値のある貢献をある程度の期間して頂いた方には_コミッター_(_Committers_)になっていただきプロジェクトへのcommit-accessの権限が譲与されます。

このドキュメンテーションは貢献プロセスを案内します。

### ステップ　１： フォーク (_Fork_)

[Github](https://github.com/seleniumhq/docs)のプロジェクトをフォークして
ローカル環境にチェックアウトしてください。

```shell
% git clone git@github.com:username/docs.git
% cd docs
% git remote add upstream git://github.com/seleniumhq/docs.git
```

https://gohugo.io/getting-started/installing/

#### 依存関係： ヒューゴ (Hugo)

ドキュメンテーションは[ヒューゴ (Hugo)](https://gohugo.io/) を用いてサイトの生成とレンダーしています。 
変更点をコミットする前にすべてをローカル環境で確認するために、
[ヒューゴ (Hugo)をインストール](https://gohugo.io/getting-started/installing/) して
サイトをローカル環境でレンダーするために[ローカルサーバーを実行](https://gohugo.io/getting-started/usage/#livereload)してください。

### ステップ　２：ブランチ (_Branch_)

フィーチャーブランチを作成して、取り組み始める:

```shell
% git checkout -b my-feature-branch
```

私達はHEAD-based開発を実践しています、すなわち全ての変更はmaster上に直接反映されます。

### ステップ　３：コミット(_Commit_)

初めにgitにあなたの名前とメールアドレスを登録済みか確認してください:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**良質なコミットメッセージを書くことは重要です。**コミットメッセージには変更点、理由、修正したissueへの参照(もし存在するならば)を記述すべきです。コミットメッセージを書く時には次のガイドラインに従ってください:

1. １行目は５０字以内で、変更内容の要約を含める。
2. ２行目は空行にする。
3. 各行を７２列で改行する。
4. もしあるならば`Fixes #N`を含める, 但し_N_はこのコミットが修正するissueナンバーです。

良質なコミットメッセージとはこんな感じです:

```text
一行でコミットを規範的に説明する

コミットメッセージの本文は２〜３行でより詳しく説明し、修正されるissueに
ついての背景など可能な限り入れます。

コミットメッセージいくつかのパラグラフになる場合があります、そして正し
く改行して、一行が７２文字以内になるよう努めてください。そうすると
`git log`がインデントされていてもきれいに表示されます。

Fixes #141
```

一行目は意味のあるものではないといけません、というのは`git shortlog`又は`git log --oneline`を実行したときに人に見えるものだからです。

### ステップ　４: リーベース(_Rebase_)

 (`git merge`ではなく)`git rebase`を使用してコードの同期化時々します。

```shell
% git fetch upstream
% git rebase upstream/master
```

### ステップ　５: 検証

いつも[ローカルサーバーを実行すること](https://gohugo.io/getting-started/usage/#livereload)を覚えてくださいそうすることで変更点が他の物を壊さない確証が得られます。

### ステップ　６: 翻訳

もし新しいものを付け加えたり、いらなくなったものを削除したりして、ドキュメンテーションを更新するならば翻訳も忘れずに更新してください。もちろんすべての翻訳言語が話せるとは限りませんです。そのような場合には[課題(issue)](https://github.com/SeleniumHQ/docs/issues)を作成し、ドキュメンテーション上どこが変更されたのかと翻訳が必要であること明記してください。それによって、その必要な言語が話せるどなたかが立候補してドキュメンテーションが更新され続ける事を手助けしてくれます。

### ステップ　７：CLAに署名する

受け入れができる前に、私達は最初に[貢献者ライセンス同意書(_Contributor License Agreement_)](https://spreadsheets.google.com/spreadsheet/viewform?hl=en_US&formkey=dFFjXzBzM1VwekFlOWFWMjFFRjJMRFE6MQ#gid=0)(又はCLA)に署名をお願いしています。私達がお願いするのには貢献者がコードを寄付する権利を有することを私達が知るためです。

プルリクエストを提出する時にCLA貢献者ライセンス同意書に署名したことを提示してください。そうすると統合させるまでの時間を減らせます。

### ステップ　８: プッシュ(_Push_)

```shell
% git push origin my-feature-branch
```

https://github.com/yourusername/docs.git へ行き _PullRequest_ を押して、フォームに記入します。**CLAに署名したことを提示してください** (ステップ 6参照)。

通常プルリクエストは２−３日中にレビューされます。もし処理しなくてはいけないコメントがあるならば、新たなコミット(_commit_)に修正箇所を含めます。(なるべく[フィックスアップ(_fixups_)](http://git-scm.com/docs/git-commit)を使用する)そして同じブランチ(_branch_)にプッシュ(_push_)します。

### ステップ　9: 統合

コードレビュー終了した時に、_コミッター_(_Committers_)がPR（プルリクエスト）を受取り、ドキュメントのgh-pagesブランチと統合させます。masterブランチの履歴を一本線で維持したいので、スクワッシュ(_squash_)とリーベース(_rebase_)をあなたのブランチに対して通常行います。

## コミュニケーション

Seleniumの貢献者は頻繁に[`irc.freenode.org`](https://webchat.freenode.net/)や[Slack](https://seleniumhq.herokuapp.com/)の`#selenium`チャンネルに集まります。[`selenium-developers@` メーリングリスト](https://groups.google.com/forum/#!forum/selenium-developers)にも参加することができます。
