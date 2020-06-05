+++
Description = "<p>In the beginning there was Se1, and it was good. But it could have been better — in ways that WebDriver was starting to be good at. Thus the brilliant idea was hatched to merge the two projects. And then the confusion began. Let’s see if I can start to address some of it via […]</p>"
Title = "The Current Status of Selenium 1 and Selenium 2"
Date = 2010-07-21
Author = "adam goucher"
AuthorLink = "https://twitter.com/adamgoucher"
tags = ["selenium","status"]
categories = ["general"]
+++

<p>In the beginning there was Se1, and it was good. But it could have been better &#8212; in ways that WebDriver was starting to be good at. Thus the brilliant idea was hatched to merge the two projects.</p>
<p>And then the confusion began. Let&#8217;s see if I can start to address some of it via a ficticious conversation that consolidates the Se-user list and #selenium irc channel.</p>
<dl>
<dt><em>There are a couple annoying bugs in Se-RC 1.03; when is the 1.0.4 release?</em></dt>
<dd>Se-RC 1.0.4 is planned for sometime towards the end of July 2010</dd>
<dt><em>I&#8217;ve heard rumours that 1.0.4 the to be the final release?</em></dt>
<dd>Yes. 1.0.4 is <em>planned</em> on being the final 1.x release</dd>
<dt><em>That&#8217;s crazy talk! I can&#8217;t use a .0 or &#8216;alpha&#8217; release for my mission critical application</em></dt>
<dd>Actually, its not all <em>that</em> crazy &#8212; and needs a bit more explanation. Se2 is truly a merger of the two projects, in fact 2.0a1 was literally the Se code from the OpenQA repository and the WebDriver code its Google Code repository merged into a <a href="http://code.google.com/p/selenium">new one</a>. This meant that from the first release of the 2.x series, it has contained 100% of the 1.x code which means 100% backwards compatibility. Later releases in the 2.x series have been driven primarily by evolutions of the code that came from WebDriver, <em>not from Se 1.x</em>.</dd>
<dt><em>OK, so 100% of Se 1.x is in 2.x; I get that. But how are you making sure that fixes to one get into the other?</em></dt>
<dd>Here is another &#8216;secret&#8217; &#8212; don&#8217;t tell anyone, but there hasn&#8217;t been any pure 1.x development since the merging of the codebases. Every 1.x release since the merger has really been a 2.x release &#8212; but all packaged up to make it look like a 1.x release. This is why observant people have noticed a log message that looks something like <em>11:09:37.507 INFO &#8211; v2.0 [a4], with Core v2.0 [a4]</em> when they start up their 1.x server.</dd>
<dt><em>So you&#8217;ve been releasing <em>alpha</em> code disguised as a stable release? Jerks!</em></dt>
<dd>Woah! Relax! Recall what I said above about it being backwards compatible by default. The &#8216;alpha&#8217; tag is there because the API for the new code is still being developed and features flushed out. The 1.x code is however, still stable and still production quality.</dd>
<dt><em>Alright, I take back calling you folks jerks, but I really don&#8217;t like the alpha tag. When will it be out of &#8216;alpha&#8217;?</em></dt>
<dd>There is only one or two more features to implement (like handling alerts) in the WebDriver code and some cleanup before the betas start. But hope for a 2.0.0 final by the end of the year. And while we&#8217;re on the topic of &#8216;alpha&#8217; vs. &#8216;beta&#8217;, this the team&#8217;s working definitions of each.</p>
<ul>
<li><em>Alpha</em> &#8211; APIs can, and likely will change. Possibly in dramatic ways.</li>
<li><em>Beta</em> &#8211; With the APIs set, make sure they work with the major browsers</li>
</ul>
</dd>
<dt><em>&#8216;Major Browsers&#8217; eh, what exactly does that mean?</em></dt>
<dd>Right now it means Firefox, Internet Explorer and at least one WebKit based one (Safari or Chrome)</dd>
</dl>
<p>In short&#8230; it is a requirement of Se2 that the server be backwards compatible with Se-RC 1.x and that has already been accomplished by building the code from a common source repository. This means that if you are using Se-RC, you can switch out the server for a 2.x one and have no impact on the execution of the scripts. <em>Plus</em> you can start to experiment with the new stuff that came over from WebDriver.</p>

