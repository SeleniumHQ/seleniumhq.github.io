---
title: "Remote WebDriver"
chapter: true
weight: 6
---

{{% notice info %}}
<i class="fas fa-language"></i> 页面需要从英语翻译为简体中文。
您熟悉英语与简体中文吗？帮助我们翻译它，通过 pull requests 给我们！
{{% /notice %}}

# Remote WebDriver

You can use WebDriver remotely the same way you would use it
locally. The primary difference is that a remote WebDriver needs to be
configured so that it can run your tests on a separate machine.

A remote WebDriver is composed of two pieces: a client and a
server. The client is your WebDriver test and the server is simply a
Java servlet, which can be hosted in any modern JEE app server.