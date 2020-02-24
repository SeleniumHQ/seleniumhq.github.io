+++
Description = "<p>Hey look! All caught up — only took a month… My opinions on Continuous Deployment are pretty widely known, but the IMVU folks certainly have a lot of neat tricks to ‘borrow’. Such as Buildbot and Intermittent Tests Dealing with an API that returns XML? Your scripts don’t care about the readibility, but it helps […]</p>"
Title = "A Smattering of Selenium #39"
Date = 2011-01-31
Author = "adam goucher"
AuthorLink = "https://twitter.com/adamgoucher"
+++

<p>Hey look! All caught up &#8212; only took a month&#8230;</p>
<ul>
<li>My opinions on Continuous Deployment are pretty widely known, but the IMVU folks certainly have a lot of neat tricks to &#8216;borrow&#8217;. Such as <a href="http://engineering.imvu.com/2011/01/19/buildbot-and-intermittent-tests/">Buildbot and Intermittent Tests</a></li>
<li>Dealing with an API that returns XML? Your scripts don&#8217;t care about the readibility, but it helps you as the human if it is formatted pretty. <a href="http://www.shell-tools.net/index.php?op=xml%5Fformat">xml formatter</a> is a glorious time save in that case.</li>
<li>Who would have predicted this&#8230; Perl stuff
<ul>
<li><a href="http://onyxneon.com/books/modern_perl/">Modern Perl</a> is a free Creative Commons book on Perl (with a dead-tree version also available)</li>
<li>I had thought the <a href="http://search.cpan.org/dist/Test-WWW-Selenium/">Se-RC style bindings</a> had been abandoned, but heard <a href="http://twitter.com/davehodg/statuses/30312545696878592">second hand</a>, they are alive and well.</li>
<li><a href="https://github.com/aivaturi/Selenium-Remote-Driver">Webdriver Remote Driver</a> is the start of work on a Se2 driver. Now to get the two projects working together.</li>
<li>And the reason for the Perl stuff is <a href="http://www.davehodgkinson.com/blog/2011/01/hudson-and-selenium/">Hudson and Selenium</a></li>
</ul>
<li>Since Se is using Sizzle now for locators, comes <a href="http://twitter.com/envatowebdev/statuses/30833435383701504">a tip</a> &#8211; <i>never do things like $(&#8216;form *&#8217;). This is crazy costly, because Sizzle works from right to left. Will grab all elems first.</i>. Not sure of the accuracy, but it makes sense.</li>
<li>Part of the debate when doing BDD and TDD is the overlap that [naturally] occurs; <a href="http://gojko.net/2011/01/28/duplication-between-bdd-and-unit-tests/">Duplication between BDD and Unit tests</a> addresses it, partly be reframing the question.</li>
<li><a href="http://code.google.com/p/webkitdriver/">webkitdriver</a> is a project that <i>aims to provide a WebDriver implementation for a light-weight in memory Web Browser</i></li>
<li>This week&#8217;s Selenium <i>killer</i> is <a href="http://ariya.blogspot.com/2011/01/phantomjs-minimalistic-headless-webkit.html">PhantomJS</a></li>
<li>Achievement parodies are always amusing; here is on for <a href="http://blog.whiletrue.com/2011/01/what-if-visual-studio-had-achievements/">Visual Studio</a> &#8212; what would the Selenium ones look like?</li>
<li>Koans are a trendy way to learn / practice a language. Here is a <a href="http://www.rubygeek.com/2011/01/22/koan-a-copia/">Koan-a-copia of them</a></li>
<li>Want onto the speaking circuit? The 2011 <a href="http://www.verifyati.com/index.php?option=com_jforms&amp;view=form&amp;id=1&amp;Itemid=85">Verify/ATI Conference is asking for presentations</a>
</ul>

