---
title: 为 Selenium 文档做贡献
disableToc: true
---

Selenium 是一个大型软件项目，文档是了解事物如何运作和学习有效利用其潜力的关键。

Selenium 的部分文档仍然在我们的 [**www.seleniumhq.org** 存储库](https://github.com/SeleniumHQ/www.seleniumhq.org) 中提供。
然而，我们正在逐步淘汰这个过分关注 Selenium RC 和其他陈旧作品的文档，以重写。

新的文档是一个从头开始重写 Selenium 项目的文档。这是一项正在进行的工作（不针对任何特定的发行版），目的是提供一个关于如何有效使用 Selenium 的更新手册。我们希望带回来一些有意义的旧文档。

对新文档的贡献遵循以下关于贡献的部分中描述的过程。
您应该花一些时间通过阅读 [更多相关信息]({{< ref "/introduction/about_this_documentation.zh-cn.md" >}}) 来熟悉文档。

---

Selenium 项目欢迎来自每个人的贡献。有很多方法可以帮助您：

## 报告问题

在报告新问题或评论现有问题时，请确保讨论涉及使用 Selenium 软件或其文档的具体技术问题。

所有 Selenium 组件随着时间的推移变化非常快，因此这可能导致文档过时。
如果您发现情况确实如此，就像上面提到的那样，不要怀疑要为此创建一个问题。
您也可能知道如何更新文档，因此请向我们发送带有相关更改的请求。

如果您不确定您发现的是否有问题，
请先向 [selenium-users@ mailing list](https://groups.google.com/forum/#!forum/selenium-users) 咨询，
或者加入我们在 [irc.freenode.org](https://webchat.freenode.net/) 或 [Slack](https://seleniumhq.herokuapp.com/) 的 `#selenium` 频道。

## 贡献

Selenium 项目欢迎新的贡献者。
随着时间的推移，作出重要和有价值贡献的个人成为提交者，并获得对项目的提交访问权。

本文将指导您完成贡献流程。

### Step 1: Fork

在 [Github](https://github.com/seleniumhq/docs) 上 Fork 项目，并在本地检查您的副本。

```shell
% git clone git@github.com:username/docs.git
% cd docs
% git remote add upstream git://github.com/seleniumhq/docs.git
```

#### 依赖关系：Hugo

文档使用 [Hugo](https://gohugo.io/) 来建立和渲染网站。
要在执行任何更改之前在本地验证所有内容，请 [安装 Hugo](https://gohugo.io/getting-started/installing/) 并 [运行本地服务器](https://gohugo.io/getting-started/usage/#livereload) 以在本地呈现站点。

### Step 2: Branch

创建一个分支并开始工作：

```shell
% git checkout -b my-feature-branch
```

我们实行基于 HEAD-based 的开发，这意味着所有的变更都直接应用于 master 之上。

### Step 3: Commit

首先确保 git 知道您的名字和电子邮件地址：

First make sure git knows your name and email address:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**编写好的提交消息很重要。** 提交的信息应该描述改变什么，为什么要改变，和引用问题（如果有的话）。
写作时遵循以下准则：

1. 第一行应该大约 50 个字符或更少，并包含对更改的简短描述。
2. 保持第二行为空白。
3. 72 个字符后应该换行。
4. 包括 `Fixes #N`, 其中 N 是提交修复的问题编号，如果有的话。

一个好的提交消息可能如下所示：

```text
用一行解释规范地提交

提交消息的主体是几行文本，更详细地解释事情，可能提供有关修复问题的一些背景，等等。

提交消息的主体可以是几个段落，请进行适当的换行，并将列的长度控制在 72 个字符左右。
这样，“git log” 即使缩进也能很好地显示内容。

Fixes #141
```

第一行必须是有意义的，因为这是人们在运行 `git shortlog` 或 `git log --oneline` 时看到的内容。

### Step 4: Rebase

使用 `git rebase` （不是 `git merge`) 时不时地同步您的工作。

```shell
% git fetch upstream
% git rebase upstream/master
```

### Step 5: Test

始终记住[运行本地服务器](https://gohugo.io/getting-started/usage/#livereload)，有了它，您就可以安全地确保您的更改没有破坏任何东西。

### Step 6: Translations

如果您正在更新文档、添加新文档或删除不推荐的文档，请记住更新文档的翻译。
当然，您可能不会在文档中更新所有的翻译语言。
为此，请创建一个[issue](https://github.com/SeleniumHQ/docs/issues)，清楚地描述文档中的某些内容发生了变化，需要更新其翻译。
有了这些信息，那些熟悉所需语言的人就可以帮助我们保持语言的更新。

### Step 7: Sign the CLA

在我们可以接受之前，我们首先要求人们签署[贡献者许可协议 CLA](https://spreadsheets.google.com/spreadsheet/viewform?hl=en_US&formkey=dFFjXzBzM1VwekFlOWFWMjFFRjJMRFE6MQ#gid=0)。
我们这样问，是为了让我们知道贡献者有权提交代码。

当您打开您的拉请求时，我们要求您表明您已经签署了 CLA。 这将减少我们整合它所需的时间。

### Step 8: Push

```shell
% git push origin my-feature-branch
```

去 <https://github.com/yourusername/site.git> 并按下 _Pull
Request_ 并填写表单。
**请注明您已签署了 CLA** （见 Step 7）。

Pull requests 通常会在几天内被审查。
如果有需要处理的注释，在新的提交中应用您的更改（最好是 [fixups](http://git-scm.com/docs/git-commit)) 并推送到同一个分支。

### Step 9: Integration

当代码审查完成后，提交者会将您的 PR 整合到文档的 gh-pages 分支上。
因为我们喜欢在主分支上保持一个线性历史，所以我们通常会压缩并重建分支历史。

## Communication

Selenium 贡献者经常在 [`irc.freenode.org`](https://webchat.freenode.net/) 或 [Slack](https://seleniumhq.herokuapp.com/) 上的 `#selenium` 频道。
您还可以加入 [`selenium-developers@` mailing list](https://groups.google.com/forum/#!forum/selenium-developers)。
