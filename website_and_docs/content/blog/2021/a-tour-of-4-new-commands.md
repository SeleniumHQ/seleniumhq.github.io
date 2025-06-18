---
title: "A Tour of 4: New Commands"
linkTitle: "A Tour of 4: New Commands"
date: 2021-10-13
tags: ["selenium", "webdriver"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  There's more to Selenium 4 than just the server
---

Before we ship Selenium 4, I thought it would be nice to introduce
some of the new features in-depth. Of course, all of the features
we're covering are in the main [WebDriver documentation][docs], so if
you're not one for reading blog posts, then feel free to dive in
there!

You may well be familiar with how to start a Selenium server: you just
do something like `java -jar selenium-server-4.0.0.jar standalone` and
away you go! But with Selenium 4, there are some more things you can
do with this jar.

Least obviously, the server jar is an exectuable on its own. If you're
using Linux or macOS, and have Java installed, you can make the jar
executable (eg. `chmod 755 selenium-server-4.0.0.jar`), rename it to
something like `selenium` and put it on your `$PATH`. That way, rather
than writing `java -jar ...` you can simply run `selenium`.

Now you've done that, if you run `selenium` with `selenium --help`
you'll get a handy list of the available sub-commands you can
execute. There are the old favourites, such as `standalone` (for
starting a standalone selenium server), and `hub` and `node` (for
starting a Selenium [Hub and Node][hub and node] setup), but there are
other interesting commands that are new to Selenium 4.

The first of these is `info`. We've added this to provide easy to find
and access documentation on common tasks when using the selenium
server. If you run `selenium info` on its own, you'll see something
like this:

```shell
Info
====

You can find out more about topics concerning Selenium Grid by running the
"info" command followed by: 

* config - Help on how to configure Selenium
* tracing - Help on how distributed tracing works with selenium
* security - Help on how how to secure communications to and within selenium
* sessionmap - Help on different types of sessionmap and how to start them.


Each topic will give you enough information to help you get started, and contains
some pointers on our site to provide more complete information. 
```

To get more information on a particular topic, just add the name of
the topic, for example `selenium info security`. The hope is that this
will make it less daunting to deal with Selenium, and give you a great
place to get started with some of the more complicated topics around
running a Selenium Grid.

Although it's incredibly flexible, the `selenium` command offers a
daunting list of command line flags. How are you supposed to remember
them all? You're not! Instead, let `selenium completion` take the
strain for you. If you're a zsh user, then just `source <(selenium
completion zsh)`, and now you have tab completion for the
sub-commands, as well as for all the flags, including examples of how
to use them.

And, finally, there are some hidden flags. We hid them because they
can be a little bit confusing to use or understand what they're meant
to do, but one that can be very useful when debugging how your Grid is
configured is the `--dump-config` flag. When run with a server command
such as `standalone` or `hub`, it will dump the configuration options
used by the Grid as a JSON blob. This can be helpful for tracking down
subtle issues with configurations, especially when using environment
properties for configuration.


[docs]: /documentation/webdriver/
[hub and node]: /documentation/grid/setting_up_your_own_grid/#hub-and-node
