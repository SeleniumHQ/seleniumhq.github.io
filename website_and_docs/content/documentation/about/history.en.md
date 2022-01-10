---
title: "Musings about how things came to be"
linkTitle: "History"
weight: 14
description: >
    Details mostly of interest to Selenium devs about how and why 
    certain parts of the project were created
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/History)

## Introduction

This is a work in progress.  Feel free to add things you know or remember.


### How did the Automation Atoms come about?

On 2012-04-04, jimevans asked on the #selenium IRC channel:
> "What I wanted to ask you about was the history of the automation atoms.  I seem to remember them springing fully formed, as if from the head of Zeus, and I'm sure that wasn't the case. Can you refresh my memory as to how the concept happened?"

simonstewart then proceeded to tell us a nice little story:
> Sure.  Are we sitting comfortably?  Then I'll begin.  (Brit joke, there)

> Imagine wavy lines as the screen dissolves and we're transported back to when selenium and webdriver were different projects.  Before the projects merged, there was an awful lot of congruent code in webdriver.  Congruent, but not shared.  The Firefox driver was in JS.  The IE driver was mostly C++.  The Chrome driver was mostly JS, but different JS from the Firefox driver. And HtmlUnit was unique.

> We then added Selenium Core to the mix.  Yet more JS that did basically the same thing.

> Within Google, I was becoming the TL of the browser automation team.  And was corralling a framework of our own into the mix.  Which was written in JS, and had once been based on Core before it span off on its own path.

> So: multiple codebases, lots of JS doing more or less the same thing.  And loads of bugs.  Weird mismatches of behaviour in edge-cases.

> `*shudder*`

> So I had a bit of a think. (Dangerous, I know) The idea was to extract the "best of breed" code from all three frameworks (Core, WebDriver and the Google tool).  Break them down into code that could be shared.  "The smallest, indivisible unit of browser automation" .

> Or "atoms" for short.

> These could be used as the basis the _everything_.  Consistent behaviour between browsers.  and apis.  The other important point was that the JS code in webdriver and core was grown organically.  Which is a polite way of saying "I'd rather never edit it again".  Which is a polite way of saying that it was of dubious quality .  In places.

> So: high quality was important.  And I wanted the code broken up into modules.  Because editing a 10k LOC file isn't a bright idea.

> Within Google we had a library called Closure.  Which not only allowed modularization, but "denormalization" of modules into a single file via compilation.  And I knew it was being open sourced.  So we started building the library in the google codebase.  (Where we had access to the unreleased library, code review tools and our amazing testing infrastructure).  Using Closure Library.

> "dom.js" was probably the first file I wrote.  (We can check).  Greg Dennis and Jason Leyba joined in the fun.  And the atoms have been growing ever since.

> Technically, we should be calling anything outside of "javascript/atoms" molecules.  But then we can't say that we have atomic drivers.  and use imagery from the 50s to describe them.

> `*sigh*`

jimevans replied: "molecular drivers?"

And simonstewart finished with:
> Indeed :)  The idea is that the atoms are the lowest level.  And we compose the atoms to conform to the WebDriver or RC apis in "javascript/{selenium,webdriver}-atoms" respecitively.  And then suck those in as necessary.

### A Story of Crazy-Fun

Simon Stewart :

> So, let's go back to the very beginning of the project<br>
<blockquote>When it was me, on my own<br>
(the webdriver project, that is, not selenium itself)<br>
I knew that I wanted to cover multiple different languages, and so wanted a build tool that could work with all of them<br>
That is, that didn't have a built in preference for one that made working with other languages painful<br>
ant is java biased. As is maven.<br>
nant and msbuild are .net biased<br>
rake, otoh, supports nothing very well<br>
But, and this is key, any valid rake script is also a valid ruby program<br>
It's possible to extend rake to build <i>anything</i><br>
So: rake it was<br>
The initial rake file was pretty small and manageable<br>
But as the project grew, so did the Rakefile<br>
Until there was only person who could deal with it (me), and even then it was pretty shaky<br>
So, rather than have a project that couldn't be built, I extracted some helper methods to do some of the heavy lifting<br>
Which made the Rakefile comprehensible again<br>
But they project kept. getting. bigger<br>
And the Rakefile got harder and harder to grok<br>
At the time, I was working at Google, who have a wonderful build system<br>
Google's system is declarative and works across multiple different languages consistently<br>
And, most important, it breaks up the build from a single file into little fragments<br>
I asked the OSS chaps at Google if it was okay to open source the build grammar, and they gave it the green light<br>
So we layered that build grammar into the selenium codebase<br>
With one minor change (we handle dictionary args)<br>
But that grammar sits on top of rake<br>
still, after all this time<br>
And there's a problem<br>
And that's that rake is single threaded<br>
So our builds are constrained to run serially<br>
We could use "multitask" types to improve things, but when I've tried that things got very messy, very fast<br>
So, our next hurdle is that crazyfun.rb is slow: we need to go faster<br>
Which implies a rewrite of crazyfun<br>
I'm most comfortable in java<br>
So, I've spiked a new version in java that handles the java and js compilation<br>
It's significantly faster<br>
But, and this is also important, it's a spike<br>
The code was designed to be disposable.<br>
Now that things have been proved out, I'd really like to do a clean implementation<br>
But I'm torn<br>
Do I "finish" the new, very fast crazyfun java enough to replace the ruby version?<br></blockquote>

### A story of driver executeables

<blockquote><br>
    jimevans<br>
noob_einsteinsfo: alright, story time, then. are we sitting comfortably? then we'll begin.<br>
noob_einsteinsfo: back when i first started working on the project (circa 2010), the drivers for all of the browsers were built and maintained by the project.<br>
at the time, that meant IE, firefox, and chrome.<br>
all of those drivers were packaged as part of the selenium standalone server, and were also packaged in with the various language bindings.<br>
this was a conscious decision, so that if one were running locally, there would be no need for the java runtime on the machine just to automate a given browser.<br>
there were two factors that led to the development of browser drivers as separate executables.<br>
as a quick aside, remember that the webdriver philosophy is to automate the browser using the "best-fit" mechanism for that particular browser.<br>
for IE, that means using the COM interfaces; for firefox at the time, that meant using a browser extension; for chrome, it also meant a browser extension.<br>
so that meant that the IE driver was developed as a DLL in C++ that was loaded by the language bindings, and communicated with via whatever native-code mechanism was provided by the language (JNI for java, P/Invoke for .NET, ctypes for python, etc.).<br>
it also meant that the firefox driver was developed as a browser extension that was packaged inside the various language bindings, and extracted, and used in a profile in firefox.<br>
as i said, the IE driver was implemented as a DLL, loaded and communicated with using different mechanisms for different language bindings.<br>
the problem is that each of those language-specific mechanisms had different load/unload semantics.<br>
ruby, for example, would never call the windows FreeLibrary API after loading the DLL into memory, making multiple instances really challenging.<br>
*process* semantics, however, as in, starting, stopping, and managing the lifetime of a process on the OS, whatever the OS, are remarkably similar across all languages.<br>
so when the IE driver rewrite was completed in 2010, the development team (me) decided to make it a separate executable, so that the load/unload semantics could be consistent no matter what language bindings one was using.<br>
concurrently with this, the chromium team made the decision to follow opera's lead and provide a driver implementation for chrome.<br>
an implementation that they would develop, enhance, and maintain going forward, relieving the selenium project of the burden of maintaining a chrome driver.<br>
<br>
    XgizmoX<br>
and that driver is part of the browser?<br>
<br>
    jimevans<br>
XgizmoX: not really, but i believe there may be some smarts built into chrome itself that knows when it's being automated via chromedriver. one of the googlers would be a better person to ask about that.<br>
anyway, knowing the different in shared library (.dll/.so/.dynlib) loading semantics, the chromium team (with my encouragement) decided to release their chromedriver implementation as a separate executable.<br>
fast-forward a couple of years, and you begin to see the effort to make webdriver a w3c standard.<br>
a working group with the w3c created a specification (still in progress, but getting close to finished with the first version), which codified the behavior of webdriver, and how a browser should react to its methods. furthermore, it standardized the protocol used to communicate between language bindings and a driver for a particular browser.<br>
i can't emphasize how important and groundbreaking this was.<br>
because the w3c and the webdriver working group within it are made up of representatives from the browser vendors themselves, it ensures that the solution will be supported directly by the browser vendors.<br>
mozilla created their webdriver implementation (geckodriver) for firefox.<br>
the most efficient mechanism for distribution of that browser driver, while maintaining the proper semantics for the language bindings, was to ship as a separate executable.<br>
note, this is a gross oversimplification of the geckodriver architecture; the actual executable acts as a relatively thin shim, translating from the wire protocol of the spec to their internal marionette protocol<br>
but the point still stands.<br>
anyway, the landscape is currently evolving regarding browser-vendor-provided driver implementation. microsoft has one for edge, apple has one for safari (10 and above), the chromium team (largely staffed by googlers) has one for chrome, and now mozilla has one for firefox.<br>
given the limited utility of the legacy firefox driver going forward, breaking it out into a separate executable would be wasted effort.<br>
this is particularly so, since all of the communication bits that are normally handled by the executable (listening for and responding to http requests from the language bindings) are handled entirely by the browser extension. \<br>
there's literally no need for the legacy firefox driver to be a separate executable.<br>
moreover, making it independent of a language runtime would be a significant portion of work<br>
(because a .NET shop might reasonably balk at being required to install, say, the java runtime just to automate firefox)<br>
so historically speaking, noob-einsteinsfo, that's the general reason for why separate executables have become the norm, and why that paradigm wasn't extended to include the legacy firefox driver.<br>
does that make sense?<br>
okay.<br>
now.<br>
about geckodriver.<br>
the tale of geckodriver is intimately bound with the status of the aforementioned w3c webdriver spec.<br>
level 1 of the specification is mostly done, though it took a number of years of effort to get there.<br>
it took a large effort from some very smart people (AutomatedTester among them) to mold the initial documentation of what the webdriver open source software (OSS) project did into proper specification language that could be interpreted and turned into actionable stuff by a browser vendor or other implementor.<br>
when beginning the geckodriver (nee marionette) project, mozilla decided to base their implementation on the spec, and only the spec, not following the OSS implementation.<br>
this created something of a chicken-and-egg problem, in that while the spec language wasn't completed, it couldn't be implemented.<br>
it's only been in the last six months or so that the language concerning the advanced user interactions api (the Actions class in java and .NET) has been made robust enough to actually implement.<br>
accordingly, that's the single biggest missing chunk of functionality in geckodriver at present. it wasn't implementable via the spec, so it hasn't been implemented.<br>
i do know that it's a very high priority for AutomatedTester and his team to get that implementation done and available.<br>
as for why geckodriver is mandatory, and the default implementation for automating firefox in 3.x, that also comes down to some decisions made by mozilla.<br>
<br>
    TheSchaf<br>
so i guess there is no other choice than to use the old FF as long as required features are missing<br>
WhereIsMySpoon<br>
TheSchaf: if you need those features, yes<br>
or use another browser<br>
TheSchaf<br>
well, moveTo and sendKeys should be pretty basic :p<br>
<br>
    jimevans<br>
TheSchaf: element.sendKeys works just fine. it's Actions.sendKeys that would be broken.<br>
in firefox version fortysomething (i misremember the exact version), there was a feature added that blocked browser extensions that hadn't been signed by the mozilla security team.<br>
remember that the legacy firefox driver was built as a browser extension? well, with that feature of the browser enabled, the legacy driver couldn't be loaded by the browser.<br>
now, for several versions of firefox, it was possible to disable this feature of the browser, and allow unsigned extensions to continue to be loaded.<br>
and selenium did this, by virtue of the settings used in the anonymous profile the bindings created when launching firefox.<br>
until firefox 48, at which point, it was no longer possible to disable loading of unsigned extensions.<br>
at that point, geckodriver was the only way forward for that.<br>
now, two more slight points, then i'll be done with story time.<br>
first, by nature of what the legacy driver extension does, it's not possible to get it to pass the certification process of the mozilla security team.<br>
we asked, were denied, and were told it wouldn't happen ever, full stop.<br>
and that's perfectly reasonable, since what that extension does is a security hole big enough to drive a whole fleet of lorries through.<br>
second, it turns out there may, in fact, be a way to privately sign the legacy extension so that it can be loaded and used privately by versions of firefox 48 and higher.<br>
that's still a less-than-ideal approach, because there's no way that our merry band of open source developers can know how to automate firefox better than the development teams at mozilla, who create the browser in the first place.<br>
i totally get the frustration that geckodriver doesn't have the full feature parity of the legacy implementation, especially when it feels like one is being forced to move to it.<br>
raging at the selenium project about that decision is directing one's ire in entirely the wrong direction.<br>
however, before going off and saying horrible things about mozilla's decisions, do know that mozilla has several people who are constantly engaged in the project, a few of them right here in this very channel (AutomatedTester, davehunt, to name two).<br>
i'm sure i've glossed over or mischaracterized some of the historical details of these things, and i'm happy to be corrected. i'm old, after all, and the memory isn't what it used to be.<br>
but that, my friends, is the (not so very) short history of why we have separate executables for drivers, and why geckodriver is the way forward, and why a move to it was necessary when the move was made even though some functionality was lacking.<br>
<br>
jimevans feels like he's become an unofficial historian of the webdriver project<br>
<br>
<br>
</blockquote>

transcript: https://botbot.me/freenode/selenium/2016-12-21/?msg=78265715&page=6



<h2>An informal naming of our releases (by channel topic in IRC)</h2>

* Selenium 2 beta 3 'the next generation browser release' now available - <a href='http://bit.ly/i9bkC2'>http://bit.ly/i9bkC2</a>
* Selenium 2 RC1 'the grid release' now available - <a href='http://bit.ly/jgZxW8'>http://bit.ly/jgZxW8</a>
* Selenium 2 RC2 the 'works better release' now available - <a href='http://bit.ly/mJJX1z'>http://bit.ly/mJJX1z</a>
* Selenium RC3 - "the next one is the 'big' one" release - <a href='http://bit.ly/kpiACx'>http://bit.ly/kpiACx</a>
* Selenium 2.0 Final unleashed upon the unspecting masses
* Selenium 2.1.0 now available (yes, even for maven users now)
* Selenium 2.2.0 now available (in nuget .. and yes, even maven)
* Selenium 2.3.0 available now. A new tradition!
* Selenium 2.4.0 is out -- stuff changed, but there is no blog post yet
* Selenium 2.5.0. mmmm. bacon.
* Selenium 2.6.0 is now available. Switch and save 15% or more on car insurance
* Ruby bindings for Selenium 2.7.0 first out of the gate (on twitter at any rate). Jari is a machine...
* Selenium 2.8.0 is out now -- day old bacon is still bacon

* sadly we are missing IRC logs...

* Selenium 2.22: The month long weekly release is finally here!
* Selenium 2.23: "Now with awesome!" Wait. What? Now?!
* Selenium 2.24: Now with more, erm, stuff?
* Selenium 2.25: Tracking nicely
* 2.26 is out!
* Selenium 2.27 has been released with fixes for Firefox 17. Get it while it's hot!
* (there was no 2.28 topic update) code.google.com/p/selenium mirrored on github.com/seleniumHQ/selenium - we're on git now!
* 2.29.0 is out now! First git release with FF18 support!
* BOOM! 2.31 is released with native event support for Firefox 19 even.
* "correlation does not imply causation" 2.32.0 released with Firefox 20 support.
* the US government is open again! Let's celebrate with 2.36 newly released, with FF24 support

* 2.40 is wow much automate so fixes such awe
* 2.41 - the last ie6 "supported" release
*
* 2.45.0 - released w/ FF36 support
* 2.46.0 - released w/ FF38 support
* 2.47.0 - released w/ Edge support
* 2.48.0 - released w/ Marionette support in all languages
* 2.49.0 Released - w/ FF 43 support
* 2.50.0 Released - "It's all bloody edge cases!" - D.W-H
* 2.51.0 Released - "It's all bloody edge cases!" - D.W-H
* 2.52.0 Released - Now you can disable "all bloody edge cases!"
* 2.53.0 The FINAL RC RELEASE

* 3.0 The Christmas Release! FF48 now requires GeckoDriver
* 3.6 The "Not Released On A Friday" Release
