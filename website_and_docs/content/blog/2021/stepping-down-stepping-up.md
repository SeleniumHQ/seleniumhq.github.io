---
title: "Stepping Down, Stepping Up"
linkTitle: "Stepping Down, Stepping Up"
date: 2021-10-27
tags: []
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Simon is stepping down as lead of the Selenium project. What happens next?
---

The short version is that I'm stepping down as the Selenium project
lead, and will concentrating on pushing WebDriver Bidi
forwards. Fortunately, the [Selenium TLC][tlc] have already been
steering the project for a while now, so it's in excellent hands. With
Selenium 4 now shipped, the project has a new energy and enthusiasm
about it. The future is bright, and I'm looking forward to seeing what
it holds.

The longer version? Well, that's more personal. Let me start at the
beginning....

On the 3rd January, 2007 I pushed the [initial commit][] of
"WebDriver" to a public repo. I helped with the Selenium 1.0 release,
and at the same time made sure that WebDriver emulated the original
Selenium RC APIs (in code that lives on to this day). In 2009, [Jason
Huggins][hugs] and I [merged the Selenium and WebDriver
projects][knife fight], and I took over the leadership of the
project. I've been the lead for every version of Selenium since then,
often being the person who tagged each release in source control, all
the way up to the recent Selenium 4 release.

By the numbers, that's
 * 14 years of WebDriver
 * 12 years of Selenium for me
   * Though the first Selenium commits were in 2004
* 1 [10 year birthday celebration][ten years]
 * Approximately 130 Selenium releases.
 * I've landed just over 5100 commits
    * Which is about 20% of all the changes made to Selenium over the
      years.
 * 3 different source control systems
 * 5 build systems
 * 14 Selenium conferences
 * 696 different people contributing to the project.

In a way, I blame Michael C for this. He and I worked together at
ThoughtWorks in Australia, and had been working with
[HttpUnit][httpunit]. My assertion was that its API was fine. His was
that it should be more [Object-Oriented][oo]. To explore the idea, I
started writing an OO wrapper around HttpUnit, and (of course!)
Michael was right.

Or maybe it's [Joe Walnes][joejoejoe] who I should be talking
about. At about the same, he'd been exploring a similar kind of
design, but where my API had a `Browser`, his had the far better
sounding `WebDriver`, and he was generous enough to allow me to use
the same name in my next iteration of the idea.

Or, perhaps, it's [Paul Hammant][paul] and [ThoughtWorks][tw]. Paul was the
one who suggested Open Sourcing what was then "WebDriver" under the
Apache 2 license, and ThoughtWorks supported the decision to make the
code available to the world.

In any case, on that day in 2007, I was sitting on the sofa with a
glass of red wine, creating the project on [Google Code][google code],
and thinking (if I was thinking deeply at all) that it'd be nice to
get the code out there, and in six months it'll probably be
over. There was absolutely no way I could dream that the project would
grow far beyond me, emerging from the [Cambrian explosion][explosion]
of the early days of web testing to become first an industry-wide de
facto standard, and then, through the work done in the W3C, an [actual
standard][standard]. It's the only browser automation API that all the
browser vendors support.

During these past 14 years, Selenium has been one of the constants of
my life. I joke sometimes that I've known it longer than my wife and
my son, but is it a joke if it's true?

Why spend that much time and effort on Open Source? On something that
you give away for free? Because it's just been so incredibly
rewarding. I've had a chance to meet people from all over the globe,
work with amazing people from a range of companies I'd never have a
chance of working with normally, learn and practice skills I wouldn't
have had a chance to exercise, and to write code that makes people's
lives just a little bit easier.

Most importantly, Selenium has been the way that I've met some of the
best friends I have in my life. I've been very lucky.

I've also been lucky to have met and been part of the Selenium
community. The conferences and meetups have been a fantastic way to
hear about how people are using the tool, and I am constantly
impressed by how inventive and thoughtful people can be. The
conversations I've had at those events have changed how I think about
all sorts of things, and not just code. The IRC and Slack channels are
vibrant and interesting places to be. There are folks who are there
regularly who I've never met in person, but who I feel I could count
as friends.

Who knew that Open Source could be the source of so much that makes a
life bright?

But today, I'm stepping down as the project lead, and will be away
from the project until summer, though (in the true spirit of this
project), I’m not sure which year.

Why, if all I said above is true, am I stepping down?

The major reason is that the things I like to do, and that I'm good
at, are not the things that the project needs right now. It would be
hubris to believe that no-one else could do as good a job,
particularly when I know how amazing so many people on the project
are.

The other reason is that I'm tired. For the past 14 years, much of my
free time, as well as hours within work, has been spent working on
Selenium. There are other projects and interests I'd like to pour some
energy into (just ask me how my Turkish lessons are going!)

In my absence, the project's "[Technical Leadership Committee][tlc]"
will continue to be setting the direction of the project. I've been
listening to the discussions about where the project can go: it's
going to be brilliant.

That is, while I'm stepping down, the rest of the TLC will be stepping
up. They've already been running the project for a while. They’ll
continue the work we’ve started and carry it forwards and upwards. If
you're reading this, and want to get involved, step in and [join the
fun][slack]. Now's the perfect time.

When I took over as lead of the Selenium project, Paul joked that he
had thrown me the keys and run in the opposite direction. I guess it’s
my turn to do that now!

Before finishing, I'd just like to say a heartfelt "thank you" to
everyone who's been involved in the Selenium project. To everyone
who's contributed in some way, or used the code we've written, thank
you. To everyone who's filed a bug, thank you too. To all the people
who I've had the pleasure of so many conversations with, that I would
never have met without Selenium, thank you. To my family, who’ve
supported me and understood with endless patience that Selenium is a
part of our lives, thank you. And to my friends on the project, an
extra meaningful thank you.

I won't be here, but I won't be far. See you all soon.

[explosion]: https://www.nhm.ac.uk/discover/news/2019/february/the-cambrian-explosion-was-far-shorter-than-thought.html
[google code]: https://code.google.com/archive/
[httpunit]: http://httpunit.sourceforge.net
[hugs]: https://twitter.com/hugs
[initial commit]: https://github.com/SeleniumHQ/selenium/commit/29393a759063a0ea7a2d05c9592d687c4b6438a9
[joejoejoe]: https://github.com/joewalnes
[knife fight]: https://youtu.be/Vlz-WmcrBL8
[oo]: https://en.wikipedia.org/wiki/Object-oriented_programming
[paul]: https://paulhammant.com
[slack]: /support/#ChatRoom
[standard]: https://w3c.github.io/webdriver/
[ten years]: https://www.thoughtworks.com/en-gb/insights/blog/happy-10th-birthday-selenium 
[tlc]: /project/structure/#tlc
[tw]: https://www.thoughtworks.com