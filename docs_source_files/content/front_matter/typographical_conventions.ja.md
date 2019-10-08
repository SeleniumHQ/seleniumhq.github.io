---
title: "Typographical conventions"
weight: 2
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

## Capitalisation of titles

One should avoid title capitalisation,
such as _A Very Fine Heading_,
and instead go for _A very fine heading_.
Gratutious capitalisation, or title case,
often show a misunderstanding of – or a disregard for –
orthographic conventions.
We prefer what is known as _sentence case_,
with a single initial capital to start headers.

## Line length

When editing the documentation’s source,
which is written in plain HTML,
limit your line lengths to around 72 characters.

Some of us take this one step further
and use what is called
[_semantic linefeeds_](//rhodesmill.org/brandon/2012/one-sentence-per-line),
which is a technique whereby the HTML source lines,
which are not read by the public,
are split at ‘natural breaks’ in the prose.
In other words, sentences are split
at natural breaks between clauses.
Instead of fussing with the lines of each paragraph
so that they all end near the right margin,
linefeeds can be added anywhere
that there is a break between ideas.

This can make diffs very easy to read
when collaborating through git,
but it is not something we enforce contributors to use.
