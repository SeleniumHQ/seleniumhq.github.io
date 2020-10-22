+++
Description = "<p>Last week we released Se-IDE 1.1.0 which now features WebDriver formats and Se 2.1.0 was released about an hour ago. Simon will post something shortly-ish on what’s changed. I haven’t messed around with HTML5 goodies yes, but fake-html5 seems like it could be interesting. The Yii framework has grown WebDriver support. Now if only there […]</p>"
Title = "A Smattering of Selenium #55"
Date = 2011-07-18
Author = "adam goucher"
AuthorLink = "https://twitter.com/adamgoucher"
+++

<p>Last week we released Se-IDE 1.1.0 which now features WebDriver formats and Se 2.1.0 was released about an hour ago. Simon will post something shortly-ish on what&#8217;s changed.</p>
<ul>
<li>I haven&#8217;t messed around with HTML5 goodies yes, but <a href="http://code.google.com/p/fake-html5/">fake-html5</a> seems like it could be interesting.</li>
<li>The Yii framework <a href="http://www.yiiframework.com/extension/webdriver-test/">has grown WebDriver support</a>. Now if only there were not two competing PHP implementations for them to have to choose from&#8230;</li>
<li>If you are using Python&#8217;s native packaging system to share your framework, then <a href="http://blog.codekills.net/2011/07/15/lies,-more-lies-and-python-packaging-documentation-on--package_data-">Lies, More Lies and Python Packaging Documentation on `package_data`</a> could spare you some headache</li>
<li>This presentation kinda needs someone in front of it to make it fully understandable, but they chose great photos so I&#8217;m including it.
<div style="width:425px;" id="__ss_8534681"> <strong><a href="http://www.slideshare.net/fabio.fabbrucci/selenium-for-designers" title="Selenium for Designers" target="_blank">Selenium for Designers</a></strong> <iframe src='https://www.slideshare.net/slideshow/embed_code/8534681' width='425' height='348' scrolling='no' allowfullscreen webkitallowfullscreen mozallowfullscreen></iframe> </p>
<div style="padding:5px 0 12px;"> View more <a href="http://www.slideshare.net/" target="_blank">presentations</a> from <a href="http://www.slideshare.net/fabio.fabbrucci" target="_blank">Fabio Fabbrucci</a> </div>
</p></div>
</li>
<li><a href="http://mubbashir11.blogspot.com/2011/07/sikuli-on-selenium-demonstration-of.html">Sikuli on Selenium- A demonstration of automation using selenium and Sikuli (such as flash uploader)</a> uses Sikuli where one might normally have used AutoIT but can&#8217;t get a handle onto the window. &#8230;.And with that use case for Sikuli arrives.</li>
<li>Ever wondered what you get when you cross alcohol and Se? Wonder no more.<br />
<span class="embed-youtube" style="text-align:center; display: block;"><iframe class='youtube-player' type='text/html' width='425' height='349' src='https://www.youtube.com/embed/Eft3qGFoqwE?version=3&#038;rel=1&#038;fs=1&#038;autohide=2&#038;showsearch=0&#038;showinfo=1&#038;iv_load_policy=1&#038;wmode=transparent' allowfullscreen='true' style='border:0;'></iframe></span></li>
<li>Visual Studio seems like overkill for Python work, but if thats your cup o&#8217; tea then <a href="http://pytools.codeplex.com/">Python Tools for Visual Studio</a> is for you.</li>
<li>Creating users for the duration of a run is a problem a lot of systems have. But not with Facebook which has a <a href="https://developers.facebook.com/docs/test_users/">Test Users</a> API it seems. Don&#8217;t forget that you can use this idea internally in your apps too.</li>
<li>With the release of Selenium 2, the project is focuses not on being a browser <i>test</i> platform, but a browser <i>automation</i> one. The difference can be subtle but one area of big difference is in terms of network information details. Selenium RC has support for it, but Selenium WebDriver does not. And of course the latter is the future of the project. 99% of the time, you really <i>don&#8217;t</i> need the network information, but in that other 1% the official response to the problem is use something like <a href="https://github.com/lightbody/browsermob-proxy">the BrowserMob Proxy</a> which also had <a href="https://github.com/jarib/browsermob-proxy-rb">Ruby bindings</a> released this week.</li>
</ul>

