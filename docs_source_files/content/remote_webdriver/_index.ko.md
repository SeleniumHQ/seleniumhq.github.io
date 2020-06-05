---
title: "Remote WebDriver"
chapter: true
weight: 6
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Korean. Do you speak Korean? Help us to translate
it by sending us pull requests!
{{% /notice %}}

# Remote WebDriver

You can use WebDriver remotely the same way you would use it
locally. The primary difference is that a remote WebDriver needs to be
configured so that it can run your tests on a separate machine.

A remote WebDriver is composed of two pieces: a client and a
server. The client is your WebDriver test and the server is simply a
Java servlet, which can be hosted in any modern JEE app server.