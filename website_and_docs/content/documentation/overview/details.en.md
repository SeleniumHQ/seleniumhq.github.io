---
title: "A deeper look at Selenium"
linkTitle: "Details"
weight: 2
description: >
  Selenium is an umbrella project for a range of tools and libraries that enable
  and support the automation of web browsers. 
aliases: [
"/documentation/en/introduction/the_selenium_project_and_tools/",
"/documentation/overview/a_deeper_look"
]
---

### Selenium controls web browsers

_Selenium_ is many things 
but at its core, it is a toolset for web browser automation
that uses the best techniques available
to remotely control browser instances
and emulate a user's interaction with the browser.

Selenium allows users to simulate common activities performed by end-users;
entering text into fields,
selecting drop-down values and checking boxes,
and clicking links in documents.
It also provides many other controls such as mouse movement,
arbitrary JavaScript execution, and much more.

Although used primarily for front-end testing of websites,
Selenium is, at its core, a browser user agent _library_.
The interfaces are ubiquitous to their application,
encouraging composition with other libraries to suit your purpose.


### One interface to rule them all

One of the project's guiding principles
is to support a common interface for all (major) browser technologies.
Web browsers are incredibly complex, highly engineered applications,
performing their operations in entirely different ways
but which frequently look the same while doing so.
Even though the text is rendered in the same fonts,
the images are displayed in the same place
, and the links take you to the same destination.
What is happening underneath is as different as night and day.
Selenium “abstracts” these differences,
hiding their details and intricacies from the person writing the code.
This allows you to write several lines of code to perform a complicated workflow,
but these same lines will execute on Firefox,
Internet Explorer, Chrome, and all other supported browsers.


### Tools and support

Selenium's minimalist design approach gives it the 
versatility to be included as a component in bigger applications.
The surrounding infrastructure provided under the Selenium umbrella
gives you the tools to put together
your [grid of browsers](/documentation/grid)
so tests can be run on different browsers and multiple operating systems
across a range of machines.

Imagine a bank of computers in your server room or data center 
all firing up browsers at the same time
hitting your site's links, forms,
and tables&mdash;testing your application 24 hours a day.
Through the simple programming interface
provided for the most common languages,
these tests will run tirelessly in parallel,
reporting back to you when errors occur.

It is an aim to help make this a reality for you,
by providing users with tools and documentation to not only control browsers 
but to make it easy to scale and deploy such grids.


### Who uses Selenium

Many of the most important companies in the world
have adopted Selenium for their browser-based testing,
often replacing years-long efforts involving other proprietary tools.
As it has grown in popularity, so have its requirements and challenges multiplied.

As the web becomes more complicated
and new technologies are added to websites,
it's the mission of this project to keep up with them where possible.
Being an open-source project,
this support is provided through the generous donation of time from many volunteers,
every one of which has a “day job.”

Another mission of the project is to encourage
more volunteers to partake in this effort,
and build a strong community
so that the project can continue to keep up with emerging technologies
and remain a dominant platform for functional test automation.

