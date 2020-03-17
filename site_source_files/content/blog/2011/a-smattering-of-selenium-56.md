+++
Description = "<p>Safari is starting to whinge about how many tabs I have open which means it is time for another post. Ripple-UI is a cross-platform, mobile web application emulation environment. From RIM. Could be something interesting. Tips From Our Codebase To Help You Write Reliable Selenium Tests has nothing I don’t violently disagree with. And makes […]</p>"
Title = "A Smattering of Selenium #56"
Date = 2011-07-28
Author = "adam goucher"
AuthorLink = "https://twitter.com/adamgoucher"
+++

<p>Safari is starting to whinge about how many tabs I have open which means it is time for another post.</p>
<ul>
<li><a href="https://github.com/blackberry/ripple-ui">Ripple-UI</a> is a cross-platform, mobile web application emulation environment. From RIM. Could be something interesting.</li>
<li><a href="http://saucelabs.com/blog/index.php/2011/07/tips-from-our-codebase-to-help-you-write-reliable-selenium-tests/">Tips From Our Codebase To Help You Write Reliable Selenium Tests</a> has nothing I don&#8217;t violently disagree with. And makes me think we should just add Implicit Waits to the Se Server and be done with it.</li>
<li>WebDriver does not support Sizzle&#8217;s extensions to the CSS standard. As it rightfully should not. But it you <i>really</i> want to, you can do something like <a href="http://prototypic.net/blog/creating-a-sizzle-css-selector-handler-for-selenium2webdriver-in-java/">Creating a Sizzle CSS Selector handler for Selenium2/WebDriver in Java</a>.</li>
<li>One of the things that Sizzle adds is :nth. Instead, we should likely start to think about is :nth-child. <a href="http://css-tricks.com/9657-useful-nth-child-recipies/">Useful :nth-child Recipes</a></li>
<li><a href="http://frazzleddad.blogspot.com/2011/07/follow-up-to-maintainable-automation.html">Follow Up to Maintainable Automation</a> ends with <i>A long-term automation strategy isn’t just about writing great tests that help you deliver awesome software, it’s also about keeping your sanity as your software and tests evolve.</i></li>
<li><a href="http://www.shino.de/2011/07/26/on-the-pageobject-pattern/">On the PageObject Pattern</a> attempts to write up the Page Object Pattern in &#8216;proper&#8217; Pattern format</li>
<li>CI systems are all about communication. And desktop monitoring apps can assist in that. And if you are using Jenkins then <a href="http://urbancoding.github.com/jenx/">Jenx</a> seems neat</li>
<li><a href="http://www.wallix.org/2011/07/26/how-to-use-robotframework-with-the-selenium-library/">How to use RobotFramework with the Selenium Library</a> is a step-by-step tutorial for getting your first automated specification working.</li>
<li><a href="http://rubygems.org/gems/rsel">rsel</a> provides a Slim fixture for running Selenium tests, with step methods written in Ruby.</li>
<li>Slides from the latest SFSE..
<ul>
<li>
<div style="width:425px;" id="__ss_8687788"> <strong><a href="http://www.slideshare.net/saucelabs/testing-at-yammer-with-foounit-jellyfish-and-selenium-8687788" title="Testing at Yammer with FooUnit, Jellyfish, and Sauce Labs" target="_blank">Testing at Yammer with FooUnit, Jellyfish, and Sauce Labs</a></strong> <iframe src='https://www.slideshare.net/slideshow/embed_code/8687788' width='425' height='348' scrolling='no' allowfullscreen webkitallowfullscreen mozallowfullscreen></iframe></div>
</li>
<li>
<div style="width:425px;" id="__ss_8659891"> <strong><a href="http://www.slideshare.net/adamchristian/javascript-testing-via-selenium" title="JavaScript Testing VIA Selenium" target="_blank">JavaScript Testing VIA Selenium</a></strong> <iframe src='https://www.slideshare.net/slideshow/embed_code/8659891' width='425' height='348' scrolling='no' allowfullscreen webkitallowfullscreen mozallowfullscreen></iframe></div>
</ul>
<p>  The new Testing Pyramid is great.</li>
<li>Reliable tests with Selenium WebDriver
<ul>
<li>
<div style="width:425px;" id="__ss_8692631"> <strong><a href="http://www.slideshare.net/PawelPabich/reliable-tests-with-selenium-web-driver" title="Reliable tests with selenium web driver" target="_blank">Reliable tests with selenium web driver</a></strong> <iframe src='https://www.slideshare.net/slideshow/embed_code/8692631' width='425' height='348' scrolling='no' allowfullscreen webkitallowfullscreen mozallowfullscreen></iframe></div>
</li>
<li><a href="https://github.com/pawelpabich/Reliable-e2e-tests-with-Selenium-Webdriver">Sample Code</a></li>
</ul>
</li>
<li>Slides from a webinar I gave yesterday.
<div style="width:425px;" id="__ss_8703396"> <strong><a href="http://www.slideshare.net/agoucher/page-objects101" title="Selenium Page Objects101" target="_blank">Selenium Page Objects101</a></strong> <iframe src='https://www.slideshare.net/slideshow/embed_code/8703396' width='425' height='348' scrolling='no' allowfullscreen webkitallowfullscreen mozallowfullscreen></iframe></div>
</li>
<li>I&#8217;ve seen a lot of keynotes. Most suck, this one doesn&#8217;t.<br />
[blip.tv <a href="http://blip.tv/play/AYHluEYC%5D" rel="nofollow">http://blip.tv/play/AYHluEYC%5D</a></li>
<li><a href="http://jawspeak.com/2011/07/16/improving-developers-enthusiasm-for-unit-tests-using-bubble-charts/">Improving developers enthusiasm for unit tests, using bubble charts</a> is just cool</li>
<li><a href="http://ygerasimov.com/chrome-joins-simpletest-selenium-framework-drupal">Google Chrome joins Simpletest Selenium framework for Drupal</a></li>
<li>Have a hard time finding unique CSS Selectors? <a href="http://prototypic.net/blog/csselectify-firefox-plugin-to-help-you-locate-unique-css-selectors-on-a-page/">CSSelectify Firefox plugin to help you locate unique CSS Selectors on a page</a> could help</li>
<li><a href="http://oli.jp/2011/ids/">Don’t use IDs in CSS selectors?</a> has some insight into how CSS <i>actually</i> work.</li>
<li>An <a href="http://sarahtaraporewalla.com/design/experience-report-feature-toggling/">Experience Report: Feature Toggling</a> &#8212; which of course you should be using to turn off all the 3rd party crap that slows down your site during runs.</li>
</ul>

