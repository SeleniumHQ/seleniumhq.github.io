---
title: "Building Selenium"
linkTitle: "How We Build Selenium"
date: 2023-06-12
tags: ["selenium"]
categories: ["releases"]
author: Simon Mavi Stewart ([@shs96c@hachyderm.io](https://hachyderm.io/@shs96c))
description: >
  How does the Selenium team build Selenium itself, and why did we chose the tools we chose?
---

One of the things that we knew from the very beginning of the Selenium
project was that people like to code in more than one language. Some
people love a bit of JS, others Ruby, and still others prefer C# or
Java.

To complicate matters, there’s plenty of pieces we want to share
between the language bindings you’ll be using. Examples include the
“atoms” (re-usable pieces of javascript that perform common functions
– such as “isDisplayed” or “getAttribute” – that we want to work the same
way no matter which language you prefer to write tests in), things
like our CDP support, which uses shared files that describe all the
available functions we can call, and the new Selenium Manager, which
is written in Rust, but we bundle with each of the language bindings.

The process of converting the source code and other artefacts (such as
the atoms) together into the artefacts we distribute (such as the
Selenium Server, or the language bindings) is called the
“build”. There are plenty of build tools out there. If you’re a java
developer, you’ve probably come across Maven or Gradle. If you’re a JS
hacker, then perhaps something like npm or yarn is something you’ve
used. If you’re a C developer (there are still plenty out there!) then
you’re likely using make or CMake.

The problem with many build tools is that they’re focused on one
language. Npm is great, but it’s a terrible choice for a Java
project. Gradle is fine, but not if you’re working in Ruby.

Why is this a problem? Because in the Selenium codebase we want to
support multiple different languages, and we want to be able to
“stitch” them together into a single cohesive whole. For example, the
Selenium jars contain a fairly large amount of JS. The Ruby gems do
too.

What we want is a single build tool that can cope with lots of
different languages, so we can weave our build into something where we
only need that one tool.

Enter [Bazel](https://bazel.build). This was a build tool originally
developed by Google, but which is now Open Source and increasingly
widely used. Bazel itself is relatively limited in what it can do, but
it can be extended easily using “rulesets” that allow it to support
everything we need, and more!

Bazel is one of a new generation of build tools, and focuses on
exposing how each part of the build process relates to the other
parts. You could imagine drawing a chart, with each thing we need to
compile (eg. Selenium Manager, or the atoms, or one of the jars we
ship) connected by lines to other parts that they depend upon. In
Computer Science, that chart is called a “graph”, and because each
line has a direction (“this thing depends upon that thing”) we call it
a directed graph. Because we can’t depend on something that depends on
itself, we can introduce a “cycle”. Bazel is a build tool designed to
work with these “directed acyclic graphs”.

One nice thing about these graphs is that there are well-known ways to
figure out which parts of the build can be performed in parallel. A
modern computer has a CPU with many (4, 8, 16!) cores, plenty of
memory, and fast SSDs: it can comfortably perform many tasks at the
same time. So Bazel takes advantage of this, running as many parts of
the build at the same time as it can. This makes our builds
significantly faster than they used to be!

Better yet, Bazel makes us list all the things that each part of the
build depends on. Not just the source code, but which versions of
which tools we’re using. That makes it a lot easier for a developer
new to the project to get started: they just need to clone [our
repo](https://github.com/seleniumhq/selenium), make sure they have
Bazel installed, and the build process will take care of making sure
that they have everything they need (although that first build may be
very slow as everything that’s needed will be downloaded from the
Net). That’s not just nice for people new to the project, it’s nice
for the existing developers. They no longer need to know how to
install and set up toolchains that they may not be familiar with –
they just run the build.

Using the “build graph”, Bazel is able to tell which pieces of code in
the Selenium source code depend on which other parts. This means that
although we can tell Bazel to “run all our tests” when we make a
change, it’s smart enough to know that it only needs to run those
tests which are actually affected by a change. You can [see this in
action in this video](https://www.youtube.com/watch?v=lqqXHEBvU0Y),
but needless to say, this can save us a huge amount of time! We can
also ask Bazel to re-run flaky tests

But there’s another advantage of describing all the things we need for
a build. Since we’ve described everything we need to Bazel, and how
all the pieces fit together, there’s no need to just run the build on
our own machines. We’re working with
[EngFlow](https://www.engflow.com) to make use of their build
grid. Rather than just running a small number of things at the same
time on our machines, we can run many times that on their build
grid. Our builds there are incredibly fast!

So, that’s why we use Bazel on our project: it supports all the
languages we want to use in a single tool, allows us to not think
about how to set up our development machines, runs builds incredibly
quickly, and we can make use of build grids to build things even
faster.
