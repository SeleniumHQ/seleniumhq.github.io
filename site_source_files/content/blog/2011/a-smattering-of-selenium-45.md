+++
Description = "<p>A Smattering of Selenium #45 So of course by now everyone has seen Selenium 2.0b3: The Next Gen Browser Release and upgraded their rigs. Expect Selenium IDE at some point in the next week with support for FF4 — if you just. can’t. wait. then you could try the bleeding edge for yourself. Logging any […]</p>"
Title = "A Smattering of Selenium #45"
Date = 2011-03-28
Author = "adam goucher"
AuthorLink = "https://twitter.com/adamgoucher"
tags = ["smattering"]
categories = ["general"]
+++
<p>A Smattering of Selenium #45</p>
<p>So of course by now everyone has seen <a href="https://seleniumhq.wordpress.com/2011/03/21/selenium-2-0b3-the-next-gen-browser-release/">Selenium 2.0b3: The Next Gen Browser Release</a> and upgraded their rigs. Expect Selenium IDE at some point in the next week with support for FF4 &#8212; if you just. can&#8217;t. wait. then you could try the bleeding edge</a> for yourself. <a href="http://code.google.com/p/selenium/issues/list">Logging any bugs</a> you find; of course.</p>
<p>Oh, and there is the whole <a href="http://www.seleniumconf.com/">Selenium Conference</a> next week.</p>
<p>But aside from that, here are the things I have collected.</p>
<ul>
<li><a href="http://computeristsolutions.com/blog/posts/2011/3/headless-dotnet-browser-testing-with-selenium2">headless dotnet browser testing with selenium2</a> using Jenkins and <a href="https://iain.cx/src/nssm/">NSSM</a> (the Non-Sucking Service Manager)</li>
<li><a href="http://ecomba.org/blog/2011/03/08/frameworks-are-evil/">Frameworks are evil</a> includes this gem: <i>The problem comes when you are blindly following what a framework gives you and you forget your better design skills (like OO or functional skills) to just follow blindly recipes from a given framework.</i>. Exactly.</li>
<li><a href="http://jackhq.tumblr.com/post/3728330919/testing-jquery-autocomplete-using-capybara">Testing jQuery Autocomplete using Capybara</a> or more accurately <i>Using Selenium + Capybara + jQuery to select an option from an AutoComplete</i></li>
<li>If you are wrapping your scripts in Cucumber or RobotFramework (or similar), then you owe it to your team to read <a href="http://antonymarcano.com/blog/2011/03/cucumber-pains/">Putting Cucumber where it’s not supposed to go will hurt!</a>. Especially the 4th adn 5th paragraphs.</li>
<li>Not new, but <a href="http://docs.codehaus.org/display/FEST/Selenium">FEST</a> appears to open up the Applet space to Se. Not that Applets are really in use much these days. I could have used this in 2007 though.</li>
<li><a href="https://github.com/idealistdev/alfajor/">Alfajor</a> seems to be a python based metaframework supporting Windmill, WebDriver and a few others.</li>
<li><a href="http://crawljax.com/">Crawljax</a> is an AJAX capable web crawler</li>
<li><a href="http://blog.browsermob.com/2011/03/selenium-tips-wait-with-waitforcondition/">Wait with WaitForCondition</a> discusses some advanced synchronization tricks</li>
<li><a href="http://opinionated-programmer.com/2011/02/capybara-and-selenium-with-rspec-and-rails-3/">Capybara (and Selenium) with RSpec &amp; Rails 3: quick tutorial</a> is a quick tutorial</li>
<li><a href="http://pypi.python.org/pypi/lettuce_webdriver">lettuce_webdriver</a> is, naturally, the Python port of Cucumber</li>
<li><a href="http://weblogs.mozillazine.org/stephend/archives/2011/03/i_gave_a_talk_a.html">An Update on Our Selenium-Automated AMO Tests (and Automation in General)</a> &#8212; AMO is addons.mozilla.org for the acronym impaired.</li>
<li><a href="http://trishkhoo.com/?p=298">A website appears before you! Adventures of a clicky thing</a> is not Se, but still interesting.</li>
<li><a href="http://www.eliasnogueira.info/arquivos_blog/selenium/desafio/">Desafios com o Selenium IDE</a> is in Portuguese, but from what I understand he is trying to build a koans style site for Se-IDE.</li>
<li>From PyCon is <a href="http://pycon.blip.tv/file/4878793/">API Design anti-patterns</a> and <a href="http://blip.tv/file/4883290">API Design: Lessons Learned</a></li>
<li><a href="http://avdi.org/devblog/exceptional-ruby/">Exceptional Ruby Notes</a> has some links to how ruby actually handles exceptions</li>
<li><a href="https://existentialtype.wordpress.com/2011/03/17/parallelism-is-not-concurrency/">Parallelism is not concurrency</a> &#8212; in case you were wondering</li>
<li>Se committer and IE WebDriver re-writer Jim Evans <a href="http://www.hanselminutes.com/default.aspx?showID=276">was on Hanselminutes</a></li>
<li><a href="http://blog.zkoss.org/index.php/2011/03/22/vision-test-for-ztl/">Vision Test for ZTL</a> shows how they do image comparison.</li>
<li><a href="https://github.com/cameronmcefee/Image-Diff-View-Modes/commit/8e95f70c9c47168305970e91021072673d7cdad8">and do does github!</a></li>
<li><a href="http://www.theautomatedtester.co.uk/blog/2011/selenium-test-day-webqa-29032011.html">Selenium Test Day with Mozilla WebQA</a> is <i>TOMORROW</i> &#8211; join in to learn / share some tricks with Mozilla</li>
<li><a href="http://blog.softwaretestingclub.com/2011/03/an-introduction-to-selenium-ide/">An Introduction To Selenium IDE</a> is the first in a series of articles that I might not normally link to but I read drafts of and know where they are heading. Either that or I am completely confused</li>
<li><a href="http://lizkeogh.com/2011/03/04/step-away-from-the-tools/">Step Away from the Tools</a> is an important reminder for those who tend to live in their tools.</li>
<li><a href="http://logician.free.fr/index.php/2011/03/23/selenium-ide-plugin-for-the-play-framework">Selenium IDE plugin for the Play! framework</a></li>
<li><a href="http://arstechnica.com/security/news/2011/03/how-the-comodo-certificate-fraud-calls-ca-trust-into-question.ars">How the Comodo certificate fraud calls CA trust into question</a> is interesting in itself, but also has a nice explanation of how SSL works. Again, if you are not using certificate-based authentication in your scripts, you do not need SSL turned on outside of production, but should you persist then you should understand what is happening.</li>
<li><a href="http://a-sisyphean-task.blogspot.com/2011/03/without-stabilisers-why-writing-your.html">Without stabilisers &#8211; why writing your own test harnesses really is an option</a> is another argument for writing your own harness. Yes, you should do this. (Or at least customize the heck out of an existing one)</li>
</ul>

