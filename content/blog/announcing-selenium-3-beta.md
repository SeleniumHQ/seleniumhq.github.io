+++
Description = "At SeleniumConf in 2013, we announced that a new major version of Selenium would be released “by Christmas”. Fortunately, we never said which Christmas, as it has taken us a while to make all the changes we wanted to make! We’re excited to announce the release of the first beta — Selenium 3.0.0-beta1."
Title = "Announcing Selenium 3.0-beta1"
Date = 2016-07-29
year = "2016"
+++

At SeleniumConf in 2013, we announced that a new major version of Selenium would be released “by Christmas”. Fortunately, we never said which Christmas, as it has taken us a while to make all the changes we wanted to make! We’re excited to announce the release of the first beta — Selenium 3.0.0-beta1.

We’d love you to try it out on your projects, and provide us with feedback on where the rough edges are before we ship the 3.0 itself! Please remember that this is a beta release, so your feedback is incredibly helpful and valuable in order to help us smooth any rough edges.

For the last six years we’ve been advising users to switch to the newer WebDriver APIs and to stop using the original RC APIs. With Selenium 3.0, the original implementation of RC has been removed, replaced by one that sits on top of WebDriver. For many users, this change will go completely unnoticed, as they’re no longer using the RC APIs. For those of you who still are, we’ve done our best to make the change as smooth as possible, but we welcome high quality bug reports to help us fix any problems that occur. Maven users will need to add a dependency on the new “leg-rc” package to access the old RC APIs.

There are some other changes that you might need to be aware of:

- You’ll need to be running Java 8 to use the Java pieces of Selenium. This is the oldest version of Java officially supported by Oracle, so hopefully you’re using it already!
- Support for Firefox is via Mozilla’s geckodriver.
- Support for Safari is provided on macOS (Sierra or later) via Apple’s own safaridriver.
- Support for Edge is provided by MS through their webdriver server.
- Only versions 9 or above of IE are supported. Earlier versions may work, but are no longer supported as MS no longer supports them.

We’ll be posting more information about Selenium 3.0 to this blog soon, but until then if you’re interested in learning more then a recent webinar by Simon is a great place to start.
