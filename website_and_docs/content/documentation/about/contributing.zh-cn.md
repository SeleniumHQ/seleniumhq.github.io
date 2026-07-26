---
title: 为 Selenium 站点和文档做贡献
linkTitle: 贡献
weight: 2
description: >-
    有关改进 Selenium 文档和代码示例的信息
aliases: [
  "/documentation/zh-cn/contributing/",
  "/documentation/zh-cn/front_matter/typographical_conventions/"
]
---

Selenium 是一个大型软件项目，
其网站和文档是了解事情如何工作以及学习有效利用其潜力的关键。

该项目包含 Selenium 的网站和文档。
这是一项持续的工作（不针对任何特定版本），
用于提供有效使用 Selenium、
如何参与以及如何为 Selenium 做出贡献的更新信息。

对网站和文档的贡献遵循以下部分中有关贡献的描述。

---

Selenium 项目欢迎每一个人的贡献。
您可以通过多种方式提供帮助：

## 上报问题

在报告新问题或评论现有问题时，
请确保讨论与 Selenium 的软件、
其站点与文档的具体技术问题相关。

随着时间的推移，所有 Selenium 组件的变化都非常快，
因此这可能会导致文档过时。
如前所述，如果您遇到这种情况，请不要犹豫，为此创建一个 issue。
您也可能知道如何更新文档，因此请向我们发送包含相关更改的 Pull Request。

如果不确定所发现的问题是否存在，请通过
https://selenium.dev/support 中描述的沟通渠道进行询问。


## 帮助事项

### 创建示例

需要添加的示例会用：{{% badge-code %}} 标记

我们希望能够在 CI 中运行所有代码示例，以确保人们可以复制和粘贴并
执行网站上的所有内容。因此，我们将代码放在
[示例目录](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/)中。
文档中的每个页面对应每种语言的一个测试文件，并应遵循命名约定。
例如，此页面 https://www.selenium.dev/documentation/webdriver/browsers/chrome/ 的示例会添加到这些
文件中：
* `"/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java"`
* `"/examples/python/tests/browsers/test_chrome.py"`
* `"/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs"`
* `"/examples/ruby/spec/browsers/chrome_spec.rb"`
* `"/examples/javascript/test/browser/chromeSpecificCaps.spec.js"`

每个示例应有自己的测试。理想情况下，每个测试都有一个断言来验证代码按预期工作。
一旦代码被复制到正确文件中的测试中，就需要在 markdown 文件中引用它。

例如，Ruby 中的 tab 看起来像这样：

        {{</* tab header="Ruby" */>}}
        {{</* gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L8-L9" */>}}
        {{</* /tab */>}}

末尾的行号仅代表实际显示项的代码行。
如果用户需要更多上下文，可以点击链接到 GitHub 页面，该页面将显示完整上下文。

确保如果在页面中添加测试，markdown 文件中的所有其他行号仍然
正确。在页面顶部添加测试意味着更新文档中所有引用该文件行号的地方。

代码示例可能需要相关的网站或网页来演示场景。为确保示例始终有效，
建议使用 https://www.selenium.dev/selenium/web/ 中提供的测试网页。

最后，确保测试在 CI 中通过。


### 迁移示例

需要迁移的示例会用：{{% badge-examples %}} 标记

[创建示例](#创建示例) 部分的所有内容都适用，但有一处补充。

确保 tab 包含 `text=true`。默认情况下，tab 会格式化为
代码，因此要使用 markdown 或其他 shortcode 语句（如 `gh-codeblock`），需要将其声明为文本。
对于大多数示例，`tabpane` 会声明 `text=true`，但如果某些 tab 包含代码示例，则 `tabpane`
不能指定它，而必须在不需要自动代码格式化的 tab 中指定。


## 贡献机制

Selenium 项目欢迎新的贡献者。
随时间做出重大且有价值的贡献的个人将成为 _提交者_，并获得对该项目的提交权限。

本指南将指导您完成贡献的过程。

### 步骤 1：Fork

在 [GitHub](https://github.com/seleniumhq/seleniumhq.github.io) 上 Fork 本项目，
并检出到您的本地


```shell
% git clone git@github.com:seleniumhq/seleniumhq.github.io.git
% cd seleniumhq.github.io
```

#### 依赖：Hugo

我们使用 [Hugo](https://gohugo.io/) 和 [Docsy theme](https://www.docsy.dev/)
用于构建和渲染本网站。
您需要 Hugo "extended" 扩展的 Sass/SCSS 版本用于这个网站。
我们推荐使用 Hugo 0.148.2。

请参考来自 Docsy 的说明
[安装 Hugo](https://www.docsy.dev/docs/getting-started/#install-hugo)。

### 步骤 2：分支

创建一个功能分支并开始工作：

```shell
% git checkout -b my-feature-branch
```

我们实践基于 HEAD 的开发模式，这意味着所有更改都直接应用在 `dev` 之上。

### 步骤 3：做出改变

本仓库包含站点和文档。
要对网站进行更改，
请使用 `website_and_docs` 目录。
要查看更改的实时预览，
请在站点的根目录上运行 `hugo server`。

```shell
% cd website_and_docs
% hugo server
```

项目会从 GitHub 加载代码，如果代码已更新但
未在预览中反映出来，您可以不使用缓存运行 hugo：`hugo server --ignoreCache`

请参阅 [样式指南]({{< ref "style.md" >}})，
以了解更多关于我们约定的信息

### 步骤 4：提交

首先确保 git 知道您的姓名和电子邮件地址：

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**编写良好的提交信息很重要。**
提交信息应描述更改的内容，
原因以及引用已修复的问题（如果有）。
撰写时应遵循以下规则：

1. 第一行应大约 50 个字符或更少，并包含对该更改的简短说明。
2. 保持第二行空白。
3. 在所有 72 列处换行。
4. 包括 `Fixes #N`，其中 _N_ 是提交修复的问题编号（如果有）。

一个好的提交信息可能看起来像这样：

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

第一行必须有意义，因为这是人们在运行 `git shortlog` 或 `git log --oneline` 时看到的内容。

### 步骤 5：Rebase

使用 `git rebase`（而非 `git merge`）不时地同步您的工作。

```shell
% git fetch origin
% git rebase origin/trunk
```

### 步骤 6：测试

永远记住要[运行本地服务](https://gohugo.io/getting-started/usage/#livereload)，
这样做可以确保您的更改没有破坏任何事情。

### 步骤 7：Push

```shell
% git push origin my-feature-branch
```

访问 https://github.com/yourusername/seleniumhq.github.io.git
并点击 _Pull Request_ 以及填写表格。
**请明确您已经签署了 CLA**。要签署 CLA，请访问
https://cla-assistant.io/SeleniumHQ/seleniumhq.github.io 并点击页面上的
"Sign in with GitHub to agree" 按钮。

Pull Request 通常会在几天内进行审核。
如果有评论要解决，请在新提交（最好是[修正](http://git-scm.com/docs/git-commit)）中应用您的更改，
然后 push 到同一分支。

### 步骤 8：集成

代码审查完成后，提交者将获取您的 PR 并将其集成到项目的 trunk 分支中。
因为我们希望在 trunk 分支上保持线性历史记录，
所以我们通常会 squash 并 rebase 您的分支历史记录。

## 沟通

有关如何与项目贡献者和整个社区进行沟通的所有详细信息，
请访问以下网址 https://selenium.dev/support
