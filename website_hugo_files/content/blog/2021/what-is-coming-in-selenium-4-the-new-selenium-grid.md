---
title: "New Tricks in Selenium 4"
linkTitle: "New Tricks in Selenium 4"
date: 2021-02-05
tags: ["selenium"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Selenium Grid: a mechanism that allows you to distribute your tests across a fleet of machines.
---


>In the fourth and final post in his series, Simon Stewart continues talking about what's 
>coming in Selenium 4 and reviews what's new in the Selenium Grid. 

We’ve covered a lot of ground in the past few blog posts, including how to contribute to the project, and some 
details of what you can expect as a Selenium user. But there’s more to Selenium than just the APIs you use to write 
your tests, and one of the big features we’ve not covered yet is the refreshed Selenium Grid: a mechanism that 
allows you to distribute your tests across a fleet of machines.

Before going further, it’s always nice to acknowledge where we’ve come from, not only because it’s interesting, but 
also because it helps explain the “why” of the refreshed design.

Way back in the mists of time (2008), Jennifer Bevin and Jason Huggins worked on a system called the Selenium Farm 
at Google. This was a fleet of machines sitting in a cupboard somewhere that allowed you to run the original 
Selenium protocol. This was, of course, at Google scale, so there was more than one cupboard :)

This allowed people at Google to distribute their tests and scale out beyond their individual machines. 
This was such a great idea that when Jennifer talked about the Farm at a Selenium meetup, Philippe 
Haringou (then at ThoughtWorks) decided to write an Open Source implementation of the same thing, 
which he called “Selenium Grid”.

Selenium Grid was a lovely piece of technology, but it had one drawback: it only spoke the original 
Selenium RC protocol. That was fine, but WebDriver spoke a different wire protocol, which was called the 
JSON Wire Protocol, and folks wanted to be able to use both Selenium RC and WebDriver at the same time.

This is where Francois Reynaud enters the picture. He was working at eBay, reporting to Michael Palotas, 
and he had written something like the original Selenium Grid, but which also worked with the JSON Wire 
Protocol. They very kindly contributed that work to the Selenium project, and it formed the basis of the 
Selenium Grid 2. We decided at the time that the Selenium standalone server would effectively be a 
“grid of one”: it would have everything you needed in it to set up a Selenium Grid, as well as to work as 
a single standalone server. Merging the code and getting it stable took a while, but thanks to the efforts 
of Francois, Kristian Rosenvold, and many others, we merged Grid 2 into the main Selenium project and shipped 
Selenium 2 in 2011.

Even if 2011 doesn’t feel that long ago, the modern world has changed quite significantly. In 2011, we didn't 
have Docker. We didn't have Kubernetes, we didn't really have AWS. So, the Selenium Grid didn't know that those 
things were coming in, and wasn't written to take advantage of them. Fortunately, we did have virtual machines 
back then, and the Grid 2 had been designed to be able to support them.

This was the hook that an excellent project called Zalenium. Developed by Diego Molina, Zalenium added a really 
nice UI and support for Docker and Kubernetes—all built on top of the Grid 2. This has allowed the Selenium Grid to 
continue to be relevant and useful to this day, which is an amazing achievement.

But, like I said, it took us time to stabilise Grid 2: about six months of hard work, largely led by Kristian. 
That’s because while Grid 2 was sophisticated, the code was hard to read and hard to maintain—and very few people 
could do that. Worse, the merging of the Grid 2 and the original Selenium server was pretty crude; effectively 
there were two separate servers shipped in the same binary. This led to problems occurring in the Grid but not 
when run in standalone mode, and vice versa.

With Selenium 4 we’ve decided to bite the bullet and address these three concerns. Firstly, we want something that 
was easier to work on and maintain. Secondly, we want to merge the servers into a single unit. Thirdly, we want 
something that will take advantage of the world of modern infrastructure we have available to us now, not just in 
the form of Docker and Kubernetes, but also to be able to use emerging technologies such as distributed tracing.

To do this, we looked at the functionality the Grid provided, and to model each piece as a component that we can 
either run “in memory” (allowing us to have a single standalone server), or in a more distributed way, allowing 
the “hub and node” architecture that we’re familiar with from the original Selenium Grid, to a fully distributed 
design.

The first component is the “router”. It serves as the entry point to the Grid. You can expose it to the internet 
and it directs requests to the Grid. It’s designed to be stateless, and so you can add more to Grid as you find 
you need to.

When the router sees a new session request, it places it on a “session queue.” The session queue is read by a 
component called the “distributor,” which maintains a model of all the places in the Grid where a session could be 
run: we call them “slots”. The slots are hosted by components called “nodes,” and each node can have one or more 
slots. When the distributor pulls a new session request from the queue, it identifies the most appropriate slot 
to use, and forwards the request to the node which owns the slot. Once the node has started the session, the 
distributor puts the session ID and the URL of the Node that is running the test into the “session map”—you can 
think of the session map as being a simple map of a session ID to URL—and the new session response is sent 
back to the waiting test.

Requests for running sessions (that is, for most webdriver calls!) are handled slightly differently. The router 
will use the session map to look up which node to forward the request to, avoiding the need to involve the 
distributor entirely. This means that you can just keep adding nodes to the Grid, and there are fewer bottlenecks 
in the architecture to slow your requests down.

Conceptually, there are these five moving pieces within the Grid. But there's actually a sixth one, which is a 
message bus. The five Grid components communicate internally through the message bus, but the actual components 
that you need to think about when you're thinking about the Grid are the Router, Session Queue, Distributor, and 
Nodes.

When you run Selenium Grid 4 in “standalone” mode, you actually get a “grid of one”. We wire up all these 
components in a single process, but they’re all still there.

You can also run it in the traditional Hub and Node approach that you've seen with Selenium Grid 2, where you 
fire up a Hub and a Node and it registers. If you've used Selenium Grid recently, that's probably the architecture 
that you're familiar with. In this case, most of the components (the router, session queue, and distributor) are 
running in the Hub, and the Nodes run the sessions themselves.

What’s new with Grid 4 is that you can go into a fully distributed mode if you want to. Typically, you would want 
to use something like Kubernetes for this, and some of the key components are designed to store data using a 
database or Redis for better reliability and scalability.

Something to note is that when running a distributed Grid, it becomes really difficult to figure out what is 
happening, particularly when something goes wrong. To alleviate that problem, we have adopted Open Telemetry to 
bring observability into the Grid. What do we mean by observability? It simply means that we want to be able to 
see everything that happens.

Finally, we’d like to expose information about the running Grid in meaningful and useful ways. The original
Grid supported both JMX (a Java management API) and an HTML-based console. While nice, this didn’t make it easy
to query specific areas of the Grid that you might be interested in that weren’t surfaced by the UI (for example, 
how many slots are available, or to find out which node a particular session is running on) To provide more 
flexibility, we’ve chosen to provide a GraphQL endpoint for the Grid. To ensure that GraphQL endpoint is 
sufficiently flexible, we’re building the new Grid console using it. That should allow you to also extract useful 
metrics and information from the Grid for your monitoring needs.

Those are some highlights of the new Selenium Grid. What are you the most excited about? 

*This was originally posted at  https://saucelabs.com/blog/whats-coming-in-selenium-4-the-new-selenium-grid*
