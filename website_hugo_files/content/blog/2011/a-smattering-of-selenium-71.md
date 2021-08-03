---
title: "A Smattering of Selenium #71"
linkTitle: "A Smattering of Selenium #71"
date: 2011-11-28
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Looking like there might also be one later in the week too…
---

Looking like there might also be one later in the week too…

*   As we start to transistion the web over the HTML5, I keep thinking about incorporating some ‘static’ checks into Se scripts. [HTML5 Accessibility Chops: using nested figure elements](http://www.paciellogroup.com/blog/2011/11/html5-accessibility-chops-using-nested-figure-elements/) explains some good (and bad) ways to use <figure>
*   Jim took my nudging the organizing tests post I linked to last time and expanded on [Organize Your Tests, Part II: Use Metadata!](http://blogs.telerik.com/jimholmes/posts/11-11-23/organize-your-tests-part-ii-use-metadata.aspx). I really like the separation of _execution_ and _organization_ which is not something I had explicitly thought about before.
*   A quick screencast on using the IPhoneDriver and jQuery Mobile.  
    {{< youtube 51E3FWMKkig >}}
*   [A bash script to set up Quickstart as a continuous integration appliance.](https://gist.github.com/1306186) is kinda trick, but I personally would use something like Puppet or Chef to do this.
*   [How I Replaced Cucumber With 65 Lines of Python](http://gfxmonk.net/2011/01/26/how-i-replaced-cucumber.html) is full of build-a-better-lightsaber goodness.
*   [Is there a way to perform a mouseover (hover over an element) using Selenium and Python bindings?](http://stackoverflow.com/questions/8252558/is-there-a-way-to-perform-a-mouseover-hover-over-an-element-using-selenium-and) illustrates one of the lesser documented parts of the Python bindings: ActionChains.
*   [Ebselen](https://github.com/Ardesco/Ebselen) is another _Mavenised Selenium test framework_
*   Not sure if this applies to how people run Se scripts with Visual Studio, but [Fixing Parallel Test Execution in Visual Studio 2010](http://www.bryancook.net/2011/11/fixing-parallel-test-execution-in.html) seems useful enough to link to.
*   The [snowday](https://github.com/mikepack/snowday) formatter for RSpec is so unbelievably awesome. Maybe my frameworks needs to grow this for the holiday season. Hmmm…
*   [junitparams](http://code.google.com/p/junitparams/) is an alternate runner for JUnit to enable more readable parameterized scripts.
