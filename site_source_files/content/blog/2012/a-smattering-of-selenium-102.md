+++
Description = "<p>Apparently my body isn’t quite on left coast time… Butter Performance is a nice experience report on making a [big] html table behave. All sorts of headaches this would cause for automation. I’m now starting to think more about caching since some of that information is included in HAR files. So if you are automating […]</p>"
Title = "A Smattering of Selenium #102"
Date = 2012-07-17
Author = "adam goucher"
AuthorLink = "https://twitter.com/adamgoucher"
+++

<p>Apparently my body isn&#8217;t quite on left coast time&#8230;</p>
<ul>
<li><a href="http://blog.streak.com/2012/07/butter-performance.html?m=1">Butter Performance</a> is a nice experience report on making a [big] html table behave. All sorts of headaches this would cause for automation.</li>
<li>I&#8217;m now starting to think more about caching since some of that information is included in HAR files. So if you are automating against a Rails site, <a href="http://www.broadcastingadam.com/2012/07/advanced_caching_revised/">Advanced Caching in Rails: Revised</a> could be of values</li>
<li><a href="http://eranki.tumblr.com/post/27076431887/scaling-lessons-learned-at-dropbox-part-1">Scaling lessons learned at Dropbox, part 1</a> is another fun experience report including a bit of hints at Dropbox&#8217;s own version of Netflix&#8217;s Chaos Monkey</li>
<li><a href="http://veera-myseleniumblog.blogspot.ca/2012/01/finding-broken-links-in-webpage-using.html">Finding the broken links in a webpage using Selenium</a> is Se-RC, but the idea is sound. Notice how they are <i>not</i> using Se to follow the link. This is why Se-RC and WebDriver is soo much more powerful than Se-IDE.</li>
<li>Mozilla&#8217;s WebQA team launched a new blog, and then immediately posted a flurry of things. Not to see if they can keep up the pace!
<ul>
<li><a href="https://blog.mozilla.org/webqa/2012/07/12/webdrivers-implicit-wait-and-deleting-elements/">WebDriver’s implicit wait and deleting elements</a></li>
<li><a href="https://blog.mozilla.org/webqa/2012/07/12/webdriver-and-py-test-parametrize/">WebDriver and Py.Test parametrize</a></li>
<li><a href="https://blog.mozilla.org/webqa/2012/07/12/how-to-webdriverwait/">How to WebDriverWait</a></li>
</ul>
</li>
<li><a href="http://blogs.atlassian.com/2012/07/how-many-bamboo-build-agents/">How Many Build Agents Does My Project Need? (a.k.a. “The $16,000 Question”)</a> is Bamboo specific, but the idea is sound and should apply equally to other CI containers</li>
<li>I can not tell you how much I dislike JS Widgets. Here is how to use WebDriver with <a href="https://gist.github.com/3106277">DHTMLX ComboBox</a></li>
<li><a href="http://blog.davidchelimsky.net/2012/01/04/rspec-28-is-released/">RSpec-2.8 is released!</a> is about 6 months late to be &#8216;news&#8217;, but <i>&#8211;order random</i> should be an option on all runners</li>
<li><a href="http://www.dary.de/2012/07/speed-up-the-development-of-calabash-android-tests/">Speed Up the Development of Calabash-Android Tests</a> talks about how they extended irb to have a shell which will run things on their device. This seems like an stealable idea; run Se Server in a terminal, execute a script which doesn&#8217;t close the browser, run commands against it. Just thinking out loud&#8230;</li>
<li><a href="http://pypi.python.org/pypi/expecter">expecter</a> is now on my shortlist to play with. But what is the soft-assert version of an expect? A hope?</li>
</ul>

