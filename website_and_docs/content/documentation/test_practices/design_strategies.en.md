---
title: "Design patterns and development strategies"
linkTitle: "Design Strategies"
weight: 1
---
(previously located: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

## Overview
Over time, projects tend to accumulate large numbers of tests. As the total number of tests increases, 
it becomes harder to make changes to the codebase --- a single "simple" change 
may cause numerous tests to fail, even though the application still works properly. 
Sometimes these problems are unavoidable, but when they do occur you want to be up 
and running again as quickly as possible. The following design patterns and strategies 
have been used before with WebDriver to help make tests easier to write and maintain. 
They may help you too.

[DomainDrivenDesign]({{< ref "encouraged/domain_specific_language.md" >}}): Express your tests in the language of the end-user of the app.
[PageObjects]({{< ref "encouraged/page_object_models.md" >}}): A simple abstraction of the UI of your web app.
LoadableComponent: Modeling PageObjects as components.
BotStyleTests: Using a command-based approach to automating tests, rather than the object-based approach that PageObjects encourage

## Loadable Component

### What Is It?

The LoadableComponent is a base class that aims to make writing PageObjects less painful. 
It does this by providing a standard way of ensuring that pages are loaded and providing 
hooks to make debugging the failure of a page to load easier. You can use it to help 
reduce the amount of boilerplate code in your tests, which in turn makes maintaining 
your tests less tiresome.

There is currently an implementation in Java that ships as part of Selenium 2, but the approach used is simple enough to be implemented in any language.

### Simple Usage

As an example of a UI that we'd like to model, take a look at 
the [new issue](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+) page. From the point of view of a test author, 
this offers the service of being able to file a new issue. A basic Page Object -- we'll call this
simple version `EditIssueBasic`, before turning it into the `EditIssue` LoadableComponent below -- would look like:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L14-L76" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

In order to turn this into a LoadableComponent, all we need to do is to set that as the base type:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L162" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

This signature looks a little unusual, but all it means is that this class 
represents a LoadableComponent that loads the EditIssue page.

By extending this base class, we need to implement two new methods:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L188-L201" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

The `load` method is used to navigate to the page, whilst the `isLoaded` method is used to 
determine whether we are on the right page. Although the method looks like it should return 
a boolean, instead it performs a series of assertions using JUnit's Assert class. 
There can be as few or as many assertions as you like. By using these assertions 
it's possible to give users of the class clear information that can be used to debug tests.

With a little rework, our PageObject looks like:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L162-L247" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

That doesn't seem to have bought us much, right? One thing it has done is encapsulate 
the information about how to navigate to the page into the page itself, meaning that 
this information's not scattered through the code base. It also means that we can do this in our tests:

```java
EditIssue page = new EditIssue(driver).get();
```

This call will cause the driver to navigate to the page if that's necessary.

### Nested Components

LoadableComponents start to become more useful when they are used in conjunction 
with other LoadableComponents. Using our example, we could view the "edit issue" 
page as a component within a project's website (after all, we access it via a tab 
on that site). You also need to be logged in to file an issue. We could model this 
as a tree of nested components:

```
 + ProjectPage
 +---+ SecuredPage
     +---+ EditIssue
```

What would this look like in code? For a start, each logical component would 
have its own class. The "load" method in each of them would "get" the parent. 
The end result, in addition to the EditIssue class above is:

ProjectPage.java:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L98-L119" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

and SecuredPage.java:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L121-L160" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

The "load" method in EditIssue now looks like:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L188-L195" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

This shows that the components are all "nested" within each other. 
A call to `get()` in EditIssue will cause all its dependencies to 
load too. The example usage:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L277-L299" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

If you're using a library such as [Guiceberry](https://github.com/zorzella/guiceberry) in your tests, 
the preamble of setting up the PageObjects can be omitted leading to nice, clear, readable tests.


## Bot Pattern

(previously located: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

Although PageObjects are a useful way of reducing duplication in your tests, 
it's not always a pattern that teams feel comfortable following. 
An alternative approach is to follow a more "command-like" style of testing.

A "bot" is an action-oriented abstraction over the raw Selenium APIs. 
This means that if you find that commands aren't doing the Right Thing 
for your app, it's easy to change them. As an example:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L249-L275" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

Once these abstractions have been built and duplication in your tests identified, it's possible to layer PageObjects on top of bots.

## Example

{{< tabpane text=true >}}
{{< tab header="Python" >}}

An example of `python + pytest + selenium` which implemented "**Action Bot**, **Loadable Component** and **Page Object**".

A `pytest` fixture `chrome_driver`.

{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L6-L26" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Action Bot**" implementation.

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L28-L65" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Loadable Component** definition.

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L67-L80" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Loadable Component** and **Page Object**" implementation. 

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L82-L172" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

Test cases implementation.

{{< tabpane text=true >}}
{{< tab header="Python" >}}
Test cases implementation with `pytest`.

{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L174-L240" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
