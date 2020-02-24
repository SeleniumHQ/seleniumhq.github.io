+++
Description = "<p>Its been around six months and three releases since I took the reins of Selenium-IDE. The fundamental change in that time has been the adoption of a plugin model like the fine folks over that Firebug. (We’re also self-hosting it which is big from a project internals perspective.) Right now, you can add custom user-extensions […]</p>"
Title = "Selenium-IDE – Looking back and looking forward"
Date = 2010-07-09
Author = "adam goucher"
AuthorLink = "https://twitter.com/adamgoucher"
+++

<p>Its been around six months and three releases since I took the reins of Selenium-IDE. The fundamental change in that time has been the adoption of a plugin model like the fine folks over that Firebug. (We&#8217;re also self-hosting it which is big from a project internals perspective.)<br />
<br />
Right now, you can add custom user-extensions and formatters, but in the very near future you will be able to add location strategies as well.<br />
<br />
So what does the future look like for Se-IDE?</p>
<ul>
<li><i>1.0.8</i> &#8211; A whack of bug fixes, and maybe the location strategies via plugins</li>
<li><i>1.0.9</i> &#8211; If location strategies doesn&#8217;t get into 1.0.8, it will be here. And likely bug fixes.</li>
<li><i>1.0.10</i> &#8211; Remove UI-Element from Se-IDE and make it a plugin</li>
</ul>
<p>
There is also work on reworking the internals of Se-IDE from a synchronous model to an asynchronous through Google Summer of Code. If that is successful and we decide to use it, that will trigger a bump to 1.1.0.<br />
<br />
Timelines you ask? Well, when I took over Se-IDE the plan was to do it monthly. That was either naive or overly optimistic so they are starting to stretch a bit. 1.0.8 is going to stretch it some more too. Look for a beta of 1.0.8 towards the end of the month with a final version by mid-August.<br />
<br />
I know you are likely wondering what cool, superdidooper features will you be seeing in Se-IDE over the next while? Ummm, well, actually none. At least not from the core Se-IDE. There will be significant pushback on any new &#8216;feature&#8217; or change that adds something to Se-IDE. If you cannot accomplish your goal through a plugin, then I would consider than an omission in terms of the API.<br />
<br />
Thats where we are from the maintainer perspective, but end-users also have sway over this as well to some degree. We&#8217;re now using the <a href="http://code.google.com/p/selenium/issues/list">Google Code Issue Tracker</a> for managing the project. If you bug is not there, there is a significantly smaller chance of it being addressed. And if an issue has a number of stars on it, that is also an indicator we should be looking at it.<br />
<br />
Now to hack on locators&#8230;</p>

