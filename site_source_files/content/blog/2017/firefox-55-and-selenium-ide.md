+++
Description = "The bad news: from Firefox 55 onwards, Selenium IDE will no longer work. The reasons for this are complex, but boil down to two main causes: Browsers are complicated pieces of software that are constantly evolving. Mozilla has been working hard to make Firefox faster and more stable, while still retaining the flexibility and ease […]"
Title = "Firefox 55 and Selenium IDE"
Date = 2017-08-09
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
+++

<p><span style="font-weight:400;">The bad news:<strong> from Firefox 55 onwards, Selenium IDE will no longer work</strong>. </span></p>
<p><span style="font-weight:400;">The reasons for this are complex, but boil down to two main causes:</span></p>
<ol>
<li style="font-weight:400;"><span style="font-weight:400;">Browsers are complicated pieces of software that are constantly evolving. Mozilla has been working hard to make Firefox <a href="https://blog.mozilla.org/blog/2017/06/13/faster-better-firefox/">faster and more stable</a>, while still retaining the flexibility and ease of extension that we’ve come to know and love. As part of that process, Firefox is switching extensions from the original “<a href="https://blog.mozilla.org/addons/2016/11/23/add-ons-in-2017/">XPI</a>” format, to a newer, more widely adopted “<a href="https://developer.mozilla.org/en-US/Add-ons/WebExtensions">Web Extension</a>” mechanism.  </span></li>
<li style="font-weight:400;"><span style="font-weight:400;">The Selenium project lacks someone with the time and energy to move the IDE forwards to take advantage of the new technologies.</span></li>
</ol>
<p><span style="font-weight:400;"><a href="http://www.seleniumhq.org/">Selenium</a> is one of the most widely used pieces of testing software there is. Despite this, the team of people regularly contributing is small: since the start of the year, there are only 11 people who have made more than 10 commits, with two people accounting for more than half of those. Since 2016, only one person has been maintaining the IDE. </span></p>
<p><span style="font-weight:400;">Selenium is an Open Source project. None of the core contributors &#8212; not the IDE maintainer, not the language binding owners &#8212; are paid to work on work on it. They do it because they love working on the code, and they typically do it in their “copious free time”. The IDE maintainer has had almost none of that to spare. We should all be thanking that committer for his time and effort. Thank you, Samit!</span></p>
<p><span style="font-weight:400;">So what can we do to move forward? The first thing is that there are now a wealth of tools that are stepping up to fill the gap. You should go and have a look at them. The second thing is that there is an effort to rebuild IDE using modern APIs, to be usable across more than just Firefox. The fine people at <a href="https://applitools.com/">Applitools</a> are helping with this effort.</span></p>
<p><span style="font-weight:400;">The third thing? That’s you. <strong>You could help us</strong>.</span></p>
<p><span style="font-weight:400;">If you believe that a friendly UI for quickly recording and playing back tests is a useful Open Source tool, then please come and join us! The main technical discussions are happening on the #selenium IRC channel. If you’d prefer <a href="https://seleniumhq.herokuapp.com/">Slack</a>, you can join us on that too. Or there’s the ever useful <a href="https://groups.google.com/forum/#!forum/selenium-developers">selenium-developers</a> mailing list. Come onboard. We’d love your help, and IDE is a wonderful thing to contribute to!</span></p>

