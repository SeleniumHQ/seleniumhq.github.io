---
title: Contributing to the Selenium Documentation
disableToc: true
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Dutch. Do you speak Dutch? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Selenium is a big software project, its site and documentation are key
to understanding how things work and learning effective ways to exploit
its potential.

This project contains both Selenium's site and documentation. This is
an ongoing effort (not targeted at any specific release) to provide
updated information on how to use Selenium effectively, how to get
involved and how to contribute to Selenium.

Contributions toward the site and docs follow the process described in
the below section about contributions. You should spend some time
familiarising yourself with the documentation by reading
[more about it]({{< ref "/introduction/about_this_documentation.nl.md" >}}).

---

The Selenium project welcomes contributions from everyone. There are a
number of ways you can help:

## Report an issue

When reporting a new issues or commenting on existing issues please 
make sure discussions are related to concrete technical issues with the
Selenium software, its site and/or documentation.

All of the Selenium components change quite fast over time, so this
might cause the documentation to be out of date. If you find this to
be the case, as mentioned, don't doubt to create an issue for that.
It also might be possible that you know how to bring up to date the
documentation, so please send us a pull request with the related
changes.

If you are not sure about what you have found is an issue or not,
If you are not sure about what you have found is an issue or not,
please ask through the communication channels described at 
https://selenium.dev/support.

## Contributions

The Selenium project welcomes new contributors. Individuals making
significant and valuable contributions over time are made _Committers_
and given commit-access to the project.

This guide will guide you through the contribution process.

### Step 1: Fork

Fork the project [on Github](https://github.com/seleniumhq/seleniumhq.github.io)
and check out your copy locally.

```shell
% git clone git@github.com:seleniumhq/seleniumhq.github.io.git
% cd seleniumhq.github.io
```

#### Dependencies: Hugo

We use [Hugo](https://gohugo.io/) to build and render the site and docs.
To verify everything locally before even committing any changes, please
[install Hugo](https://gohugo.io/getting-started/installing/), get familiar
with it and [run the local server](https://gohugo.io/getting-started/usage/#livereload)
to render the site locally (detailed instructions can be found in the 
next steps).

### Step 2: Branch

Create a feature branch and start hacking:

```shell
% git checkout -b my-feature-branch
```

We practice HEAD-based development, which means all changes are applied
directly on top of master.

### Step 3: Make changes

The repository contains the site and docs, which are two separate Hugo 
projects. If you want to make changes to the site, work on the
`site_source_files` directory. To see a live preview of your changes,
run `hugo server` on the site's root directory.

```shell
% cd site_source_files
% hugo server
```

To make changes to the docs, switch to the `docs_source_files` directory. 

```shell
% cd docs_source_files
% hugo server
```

The docs are translated into several languages, and translations are based on
the English content. When you are changing a file, **be sure** to make your
changes in all the other translated files as well. This might differ depending
on the change, for example:
 
* If you add a code example to the `browser_manipulation.en.md` file,
also add it to `browser_manipulation.es.md`, `browser_manipulation.ef.md`, 
`browser_manipulation.ja.md`, and all other translated files.
* If you find a translation that can be improved, only change the translated
file.
* If you are adding a new language translation, add the new files with the
appropriate suffix. There is no need to have everything translated to submit a
PR, it can be done iteratively. Don't forget to check some needed configuration
values in the `config.toml` file.
* If you make text changes in the English version, replace the same section in
the translated files with your change (yes, in English), and add the following
notice at the top of the file. 

```
{{%/* notice info */%}}
<i class="fas fa-language"></i> Page being translated from 
English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
it by sending us pull requests!
{{%/* /notice */%}}
```

### Step 4: Commit

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

### Step 5: Rebase

Use `git rebase` (not `git merge`) to sync your work from time to time.

```shell
% git fetch upstream
% git rebase upstream/master
```

### Step 6: Test

Always remember to [run the local server](https://gohugo.io/getting-started/usage/#livereload),
with this you can be safe that your changes have not broken anything.

### Step 7: Push

```shell
% git push origin my-feature-branch
```

Go to https://github.com/yourusername/seleniumhq.github.io.git and
press the _Pull Request_ and fill out the form. **Please indicate
that you've signed the CLA** (see Step 7).

Pull requests are usually reviewed within a few days. If there are
comments to address, apply your changes in new commits (preferably
[fixups](http://git-scm.com/docs/git-commit)) and push to the same
branch.

### Step 8: Integration

When code review is complete, a committer will take your PR and
integrate it on the repository's master branch. Because we like to keep a
linear history on the master branch, we will normally squash and rebase
your branch history.

## Communication

All details on how to communicate with the project contributors
and the community overall can be found at https://selenium.dev/support
