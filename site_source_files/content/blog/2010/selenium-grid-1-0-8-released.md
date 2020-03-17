+++
Description = "<p>We’re pleased to announce the release of Selenium Grid 1.0.8. This release fixes multithreaded issues with the Selenium Grid hub that appeared randomly under heavy load. If you’ve ever seen a log message about HttpClient being accessed by multiple threads, you definitely want to upgrade. Even if you haven’t, this release is highly recommended for […]</p>"
Title = "Selenium Grid 1.0.8 Released"
Date = 2010-06-10
Author = "Kevin Menard"
AuthorLink = "https://twitter.com/nirvdrum"
+++

<p>We&#8217;re pleased to announce the release of Selenium Grid 1.0.8.  This release fixes multithreaded issues with the Selenium Grid hub that appeared randomly under heavy load.  If you&#8217;ve ever seen a log message about HttpClient being accessed by multiple threads, you definitely want to upgrade.  Even if you haven&#8217;t, this release is highly recommended for all.</p>
<p>You can <a href="http://release.seleniumhq.org/selenium-grid/selenium-grid-1.0.8-bin.zip">download it now</a> or <a href="http://github.com/nirvdrum/selenium-grid/blob/master/ChangeLog">view the changelog</a>.  The list of changes is:</p>
<ul>
<li>Multi-threaded issue with access to HttpClient has been resolved, fixing random crashes on a heavily loaded grids</li>
</ul>
<p>Many thanks go out to Chris Gulley for identifying and fixing the problem.  As always, patches and bug reports are appreciated:</p>
<ul>
<li><a href="http://github.com/nirvdrum/selenium-grid/">Code repository</a></li>
<li><a href="http://code.google.com/p/selenium/issues/list">Issue tracker</a></li>
</ul>
<p>I mentioned in the release notes for 1.0.7 that barring any major issues, 1.1 would be the next release.  This fix was major enough to warrant an intermediate release.  We are still planning to put out 1.1 as the next feature release.</p>
<p>If you have any questions about Selenium Grid, please use either the user or the developer list, as is most appropriate for the nature of your question.  Ongoing discussion about grid development should take place on the developer list.</p>

