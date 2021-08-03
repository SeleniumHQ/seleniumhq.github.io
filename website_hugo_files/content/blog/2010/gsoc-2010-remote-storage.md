---
title: "GSoC 2010 – Remote Storage"
linkTitle: "GSoC 2010 – Remote Storage"
date: 2010-07-15
tags: ["stories"]
categories: ["general"]
author: Jérémy Hérault ([@jeremyherault](https://twitter.com/jeremyherault))
description: >
  What’s new for Selenium this summer ? The GSoC of course !!!!
---

What’s new for Selenium this summer ? The GSoC of course !!!!

I’m Jérémy, a french engineer and I work at [SERLI](http://www.serli.com/), a services company based in France.  
I’m working on Selenium since a year and this summer I’m mentoring Aleksejs for the GSoC 2010. I’m helped by [David Burns](http://www.theautomatedtester.co.uk/) when I’m on vacation or offline. His experience is really useful to me, and David is involved on student supervising regularly.

Aleksejs comes from Latvia in Europe (yes I know you know but perhaps Geography wasn’t your favorite subject at school ;)). His work was initiated by Patrick and myself. We thought to a remote system that allows Selenium users to save and get remote test cases, directly with Selenium, without any other installations like SVN.

What’s better than record a test case with Selenium IDE and save it on your remote storage? what’s better than share your test cases with colleagues without any other installations, just by using Selenium IDE or a Selenium Remote Storage client?

For myself I don’t know what’s better 🙂

Aleksejs has already coded the server side of the remote storage mechanism with all the necessary unit tests. He has also created a web interface to use it directly through the browser. With his work, you can put, get and delete a test case, and you can also browse the directory where test cases are stored. His work has been done in Java, and he used JSON, for data representation for the communication protocol. This system is really simply to use, based on REST technology, you only have to call URLs with the good parameters.

The next step of his work is to create a Se-IDE plugin based on the API done by Adam (thanks to him). This part has to be done in less than a month and I think it’s a hard task, but it’s so cool to write your own plugin. In this case, Aleksejs will use JavaScript, AJAX and XUL technologies. It’s really good to learn a lot of technologies for its own culture, it’s so trainer.

I hope you’re enthousiastic to get this new feature, but wait until October, when the work will be finalized 🙂

Enjoy guys, Aleksejs, David and I are ready to discuss on it if you have any questions. And good luck with the end of this adventure Aleksejs.

