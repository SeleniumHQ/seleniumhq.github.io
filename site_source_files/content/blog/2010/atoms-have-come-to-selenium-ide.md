+++
Description = "<p>As mentioned in Simon’s Going Atomic: Why? and Going Atomic: How, part of the merging of Selenium and WebDriver is to share common code between the two. And as of the 1.0.10 release of Selenium IDE, this merged code, ‘atoms’ is now included. Some early users of this release are noticing that this the atom […]</p>"
Title = "Atoms have come to Selenium IDE"
Date = 2010-12-09
Author = "adam goucher"
AuthorLink = "https://twitter.com/adamgoucher"
+++

<p>As mentioned in Simon&#8217;s <a href="http://seleniumhq.wordpress.com/2010/08/16/going-atomic-why/">Going Atomic: Why?</a> and <a href="http://seleniumhq.wordpress.com/2010/09/05/going-atomic-how/">Going Atomic: How</a>, part of the merging of Selenium and WebDriver is to share common code between the two. And as of the 1.0.10 release of Selenium IDE, this merged code, &#8216;atoms&#8217; is now included.<br />
<br />
Some early users of this release are noticing that this the atom that drives assertAttribute and verifyAttribute is causing their scripts to break. This post explains the &#8216;why&#8217; and suggests how to fix them as well.<br />
<br />
The HTML spec has the notion of a <i>boolean attribute</i> which is one that does something just by being there rather than by its value; <i>checked</i> and <i>selected</i> are two such examples of this. Optionally, you could give these boolean attributes a value that is the same as their name (checked=&#8221;checked&#8221;) and your page would still validate, but really, the browsers only care that the attribute exist.<br />
<br />
Prior to 1.0.10, it was not uncommon for people to do something like<br />
</p>
<pre>assertAttribute | checkbox@checked | checked</pre>
<p>
which fetches the value of the checked attribute on the checkbox with the id or name of checkbox and fails the script is the value is not checked.<br />
<br />
In 1.0.10 though, that will fail <i>even if it is checked</i>. This is because checked is a boolean attribute and will now return either true or throw an exception saying the attribute doesn&#8217;t exist. Redoing the above step check that the boolean attribute is set results in:<br />
</p>
<pre>assertAttribute | checkbox@checked | true</pre>
<p>
Thankfully, most of the real-world interaction with boolean attributes is focused on checked, disabled, hidden and selected and in these cases using assertAttribute is not really the right thing to do. Instead, the Selenium API provides wrappers for you to use.<br />
</p>
<table>
<tr>
<td>checked</td>
<td>assertChecked(locator), assertNotChecked(locator), verifyChecked(locator), verifyNotChecked(locator)</td>
</tr>
<tr>
<td>disabled</td>
<td>assertEditable(locator), assertNotEditable(locator), verifyEditable(locator), verifyNotEditable(locator)</td>
</tr>
<tr>
<td>hidden</td>
<td>assertVisible(locator), assertNotVisible(locator), verifyVisible(locator), verifyNotVisible(locator)</td>
</tr>
<tr>
<td>selected</td>
<td>assertSelected*(locator, pattern), assertNotSelected*(locator, pattern), verifySelected*(locator, pattern), verifyNotSelected*(locator, pattern)</td>
</tr>
</table>
<p>
Checking for the absence of a boolean attribute that doesn&#8217;t have a nice wrapper is a bit of a pain though. In this example I am checking that the boolean attribute <i>multiple</i> is not on the select element with the name elephants.<br />
</p>
<pre>assertEval | this.browserbot.findElement("css=select[name=elephants]").getAttribute("multiple"); | null</pre>
<p>
If you find yourself doing this very often, I suggest you wrap it up in a user-extension inside a <a href="http://seleniumhq.org/projects/ide/plugins.html">plugin</a> as something like<br />
</p>
<pre>assertBooleanAttribute | css=select[name=elephants]@multiple | false
assertBooleanAttribute | css=select[name=elephants]@multiple | true</pre>
<p>
Hopefully this addresses some of the eventual &#8216;why the heck did my script break!?!?!&#8217; problems that will no double crop up once Selenium IDE 1.0.10 gets wider distribution.</p>

