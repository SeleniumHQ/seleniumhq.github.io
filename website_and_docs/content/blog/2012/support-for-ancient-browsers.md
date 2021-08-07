---
title: "Support for Ancient Browsers"
linkTitle: "Support for Ancient Browsers"
date: 2012-02-24
tags: ["selenium"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  The first code checked into the Selenium project’s public repository was in November, 2004.
---

The first code checked into the Selenium project’s public repository was in November, 2004. We’re now in 2012. In the intervening years there have been many browsers released. The last browser we officially stopped supporting was Firefox 2.0, and it’s time to review the list of browsers again.

We periodically review the list of supported browsers as the more changes there are between the oldest version of a browser that we support and the most recent, the harder it is for us to add new features and maintain those that already exist. Balanced against the cost of maintaining the selenium code base itself are your tests; we know that your users might not be updating their browsers to the latest and greatest, and we know that you’ve still got to prove your app works on all the browsers that are important to you. That’s why what’s below is just our plan, and we’re talking about it now to let you have your say.

Looking at the market share of the browsers out there helps us make an informed choice about what it makes sense to support. This will most likely mean:

**Firefox**: the Firefox market appears to be split between those on 3.6 and those on the new rapid release schedule. Given this, we are thinking of officially supporting Firefox 3.6, and the last, latest and next release of Firefox (currently Firefox 9-11) as well as any ESR releases. The market share for Firefox versions 3.0 and 3.5 is tiny, and the effort to keep them working with selenium is disproportionately high.

**Internet Explorer**: [Despite Microsoft’s efforts](http://www.ie6countdown.com/), IE 6 is still a popular browser, particularly in the workplace. We will continue to support IE versions 6 and up.

**Safari**: Safari 3 is now ancient and has been superseded by newer releases. We plan on only supporting Safari 4 and 5.

**iOS**: We’ll continue to target the most recent iOS release.

**Android**: Due to some technical limitations in previous Android releases, we are targeting Ice Cream Sandwich and onwards. We will continue to make available the testing framework for Froyo, but will not be making any changes to it.

**These are only our plans**. If you really need those browsers, and (better!) can help us maintain support for them, then please let us know.

You’ll notice that Opera and Chrome are not listed above. Since Opera and Google now maintain the drivers for those browsers, they are best placed to decide which are the supported versions, but in summary, Google support the major Chrome release channels (stable, beta, dev and canary) and Opera suggest using Opera 11.6+.