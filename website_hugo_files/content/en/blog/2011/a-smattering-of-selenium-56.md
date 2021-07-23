---
title: "A Smattering of Selenium #56"
linkTitle: "A Smattering of Selenium #56"
date: 2011-07-28
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Safari is starting to whinge about how many tabs I have open which means it is time for another post.
---
Safari is starting to whinge about how many tabs I have open which means it is time for another post.

*   [Ripple-UI](https://github.com/blackberry/ripple-ui) is a cross-platform, mobile web application emulation environment. From RIM. Could be something interesting.
*   [Tips From Our Codebase To Help You Write Reliable Selenium Tests](http://saucelabs.com/blog/index.php/2011/07/tips-from-our-codebase-to-help-you-write-reliable-selenium-tests/) has nothing I don’t violently disagree with. And makes me think we should just add Implicit Waits to the Se Server and be done with it.
*   WebDriver does not support Sizzle’s extensions to the CSS standard. As it rightfully should not. But it you _really_ want to, you can do something like [Creating a Sizzle CSS Selector handler for Selenium2/WebDriver in Java](http://prototypic.net/blog/creating-a-sizzle-css-selector-handler-for-selenium2webdriver-in-java/).
*   One of the things that Sizzle adds is :nth. Instead, we should likely start to think about is :nth-child. [Useful :nth-child Recipes](http://css-tricks.com/9657-useful-nth-child-recipies/)
*   [Follow Up to Maintainable Automation](http://frazzleddad.blogspot.com/2011/07/follow-up-to-maintainable-automation.html) ends with _A long-term automation strategy isn’t just about writing great tests that help you deliver awesome software, it’s also about keeping your sanity as your software and tests evolve._
*   [On the PageObject Pattern](http://www.shino.de/2011/07/26/on-the-pageobject-pattern/) attempts to write up the Page Object Pattern in ‘proper’ Pattern format
*   CI systems are all about communication. And desktop monitoring apps can assist in that. And if you are using Jenkins then [Jenx](http://urbancoding.github.com/jenx/) seems neat
*   [How to use RobotFramework with the Selenium Library](http://www.wallix.org/2011/07/26/how-to-use-robotframework-with-the-selenium-library/) is a step-by-step tutorial for getting your first automated specification working.
*   [rsel](http://rubygems.org/gems/rsel) provides a Slim fixture for running Selenium tests, with step methods written in Ruby.
*   Slides from the latest SFSE..
    
    *   **[Testing at Yammer with FooUnit, Jellyfish, and Sauce Labs](http://www.slideshare.net/saucelabs/testing-at-yammer-with-foounit-jellyfish-and-selenium-8687788 "Testing at Yammer with FooUnit, Jellyfish, and Sauce Labs")**
        
    *   **[JavaScript Testing VIA Selenium](http://www.slideshare.net/adamchristian/javascript-testing-via-selenium "JavaScript Testing VIA Selenium")**
        
    
    The new Testing Pyramid is great.
    
*   Reliable tests with Selenium WebDriver
    *   **[Reliable tests with selenium web driver](http://www.slideshare.net/PawelPabich/reliable-tests-with-selenium-web-driver "Reliable tests with selenium web driver")**
        
    *   [Sample Code](https://github.com/pawelpabich/Reliable-e2e-tests-with-Selenium-Webdriver)
*   Slides from a webinar I gave yesterday.
    
    **[Selenium Page Objects101](http://www.slideshare.net/agoucher/page-objects101 "Selenium Page Objects101")**
    
*   I’ve seen a lot of keynotes. Most suck, this one doesn’t.  
    \[blip.tv [http://blip.tv/play/AYHluEYC%5D](http://blip.tv/play/AYHluEYC%5D)
*   [Improving developers enthusiasm for unit tests, using bubble charts](http://jawspeak.com/2011/07/16/improving-developers-enthusiasm-for-unit-tests-using-bubble-charts/) is just cool
*   [Google Chrome joins Simpletest Selenium framework for Drupal](http://ygerasimov.com/chrome-joins-simpletest-selenium-framework-drupal)
*   Have a hard time finding unique CSS Selectors? [CSSelectify Firefox plugin to help you locate unique CSS Selectors on a page](http://prototypic.net/blog/csselectify-firefox-plugin-to-help-you-locate-unique-css-selectors-on-a-page/) could help
*   [Don’t use IDs in CSS selectors?](http://oli.jp/2011/ids/) has some insight into how CSS _actually_ work.
*   An [Experience Report: Feature Toggling](http://sarahtaraporewalla.com/design/experience-report-feature-toggling/) — which of course you should be using to turn off all the 3rd party crap that slows down your site during runs.