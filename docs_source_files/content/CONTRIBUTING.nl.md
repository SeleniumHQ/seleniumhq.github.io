---
title: Contributing to the Selenium Documentation
disableToc: true
---

Selenium is a big software project and documentation is key to
understanding how things work and learning effective ways to exploit
its potential.

Part of the documentation of Selenium is still served from our
[**www.seleniumhq.org** repository](https://github.com/SeleniumHQ/www.seleniumhq.org).
We are however phasing out this documentation which focuses too much
on Selenium RC and other antiquated pieces, in favour of this rewrite.

The new documentation is a project started to rewrite Selenium's
documentation from scratch. This is an ongoing effort (not targetted
at any specific release) to provide an updated handbook on how to use
Selenium effectively. We hope to bring over the pieces of the old
documentation that makes sense.

Contributions toward the new docs follow the process described in
the below section about contributions. You should spend some time
familiarising yourself with the documentation by reading
[more about it]({{< ref "/introduction/about_this_documentation.nl.md" >}}).

---

The Selenium project welcomes contributions from everyone. There are a
number of ways you can help:

## Report an issue

When reporting a new issues or commenting on existing issues please 
make sure discussions are related to concrete technical issues with the
Selenium software and/or its documentation.

All of the Selenium components change quite fast over time, so this
might cause the documentation to be out of date. If you find this to
be the case, as mentioned, don't doubt to create an issue for that.
It also might be possible that you know how to bring up to date the
documentation, so please send us a pull request with the related
changes.

If you are not sure about what you have found is an issue or not,
please ask first about it to the
[selenium-users@ mailing list](https://groups.google.com/forum/#!forum/selenium-users),
or join us in the `#selenium` channel 
on [irc.freenode.org](https://webchat.freenode.net/) or [Slack](https://seleniumhq.herokuapp.com/).

## Contributions

The Selenium project welcomes new contributors. Individuals making
significant and valuable contributions over time are made _Committers_
and given commit-access to the project.

This document will guide you through the contribution process.

### Step 1: Fork

Fork the project [on Github](https://github.com/seleniumhq/docs)
and check out your copy locally.

```shell
% git clone git@github.com:username/docs.git
% cd docs
% git remote add upstream git://github.com/seleniumhq/docs.git
```

https://gohugo.io/getting-started/installing/

#### Dependencies: Hugo

The docs use [Hugo](https://gohugo.io/) to build and render the site.
To verify everything locally before even commiting any changes, please
[install Hugo](https://gohugo.io/getting-started/installing/) and
[run the local server](https://gohugo.io/getting-started/usage/#livereload)
to render the site locally.

### Step 2: Branch

Create a feature branch and start hacking:

```shell
% git checkout -b my-feature-branch
```

We practice HEAD-based development, which means all changes are applied
directly on top of master.

### Step 3: Commit

First make sure git knows your name and email address:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**Writing good commit messages is important.** A commit message
should describe what changed, why, and reference issues fixed (if
any). Follow these guidelines when writing one:

1. The first line should be around 50 characters or less and contain a
    short description of the change.
2. Keep the second line blank.
3. Wrap all other lines at 72 columns.
4. Include `Fixes #N`, where _N_ is the issue number the commit
    fixes, if any.

A good commit message can look like this:

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

The first line must be meaningful as it's what people see when they
run `git shortlog` or `git log --oneline`.

### Step 4: Rebase

Use `git rebase` (not `git merge`) to sync your work from time to time.

```shell
% git fetch upstream
% git rebase upstream/master
```

### Step 5: Test

Always remember to [run the local server](https://gohugo.io/getting-started/usage/#livereload),
with this you can be safe that your changes have not broken anything.

### Step 6: Translations

If you are updating the docs, adding new ones, or deleting deprecated ones, please remember
to update the translations of it. Of course, it is possible that you do not speak all the
translated languages in the docs. For that, please create an 
[issue](https://github.com/SeleniumHQ/docs/issues) where you clearly describe that something
in the docs has changed and its translation needs to be updated. With that, someone who speaks
that needed language can chime in and help us to keep it up to date.

### Step 7: Sign the CLA

Before we can accept, we first ask people to sign a
[Contributor License Agreement](https://spreadsheets.google.com/spreadsheet/viewform?hl=en_US&formkey=dFFjXzBzM1VwekFlOWFWMjFFRjJMRFE6MQ#gid=0)
(or CLA). We ask this so that we know that contributors have the right
to donate the code.

When you open your pull request we ask that you indicate that you've
signed the CLA. This will reduce the time it takes for us to integrate
it.

### Step 8: Push

```shell
% git push origin my-feature-branch
```

Go to https://github.com/yourusername/docs.git and press the _Pull
Request_ and fill out the form. **Please indicate that you've signed
the CLA** (see Step 6).

Pull requests are usually reviewed within a few days. If there are
comments to address, apply your changes in new commits (preferably
[fixups](http://git-scm.com/docs/git-commit)) and push to the same
branch.

### Step 9: Integration

When code review is complete, a committer will take your PR and
integrate it on the docs's gh-pages branch. Because we like to keep a
linear history on the master branch, we will normally squash and rebase
your branch history.

## Communication

Selenium contributors frequent the `#selenium` channel on
[`irc.freenode.org`](https://webchat.freenode.net/) or on
or [Slack](https://seleniumhq.herokuapp.com/). You can also join
the [`selenium-developers@` mailing list](https://groups.google.com/forum/#!forum/selenium-developers).

