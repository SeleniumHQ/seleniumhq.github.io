---
title: "Selenium IDE – Looking back and looking forward"
linkTitle: "Selenium IDE – Looking back and looking forward"
date: 2010-07-09
tags: ["selenium","ide"]
categories: ["releases"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Its been around six months and three releases since I took the reins of Selenium-IDE.
---


Its been around six months and three releases since I took the reins of Selenium-IDE. The fundamental change in that time has been the adoption of a plugin model like the fine folks over that Firebug. (We’re also self-hosting it which is big from a project internals perspective.)  
  
Right now, you can add custom user-extensions and formatters, but in the very near future you will be able to add location strategies as well.  
  
So what does the future look like for Se-IDE?

*   _1.0.8_ – A whack of bug fixes, and maybe the location strategies via plugins
*   _1.0.9_ – If location strategies doesn’t get into 1.0.8, it will be here. And likely bug fixes.
*   _1.0.10_ – Remove UI-Element from Se-IDE and make it a plugin

There is also work on reworking the internals of Se-IDE from a synchronous model to an asynchronous through Google Summer of Code. If that is successful and we decide to use it, that will trigger a bump to 1.1.0.  
  
Timelines you ask? Well, when I took over Se-IDE the plan was to do it monthly. That was either naive or overly optimistic so they are starting to stretch a bit. 1.0.8 is going to stretch it some more too. Look for a beta of 1.0.8 towards the end of the month with a final version by mid-August.  
  
I know you are likely wondering what cool, superdidooper features will you be seeing in Se-IDE over the next while? Ummm, well, actually none. At least not from the core Se-IDE. There will be significant pushback on any new ‘feature’ or change that adds something to Se-IDE. If you cannot accomplish your goal through a plugin, then I would consider than an omission in terms of the API.  
  
Thats where we are from the maintainer perspective, but end-users also have sway over this as well to some degree. We’re now using the [Google Code Issue Tracker](http://code.google.com/p/selenium/issues/list) for managing the project. If you bug is not there, there is a significantly smaller chance of it being addressed. And if an issue has a number of stars on it, that is also an indicator we should be looking at it.  
  
Now to hack on locators…