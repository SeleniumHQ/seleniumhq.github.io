---
title: "Driver Sessions"
linkTitle: "Drivers"
weight: 3
---

Starting and stopping a session is for opening and closing a browser.

## Creating Sessions

Creating a new session corresponds with the W3C command for [New session](https://w3c.github.io/webdriver/#new-session)

The session is created automatically by initializing a new Driver class object.

Each language allows a session to be created with arguments from one of these classes (or equivalent):

* [Options]({{< ref "options.md" >}}) to describe the kind of session you want; default values are used for local, but this is required for remote
* Some form of [HTTP Client configuration]({{< ref "http_client.md" >}}) (the implementation varies between languages)
* [Listeners]({{< ref "listeners.md" >}})

### Local Driver

The primary unique argument for starting a local driver includes information about starting the required driver service
on the local machine.

* [Service]({{< ref "service.md" >}}) object applies only to local drivers and provides information about the browser driver

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Remote Driver

The primary unique argument for starting a remote driver includes information about where to execute the code.
Read the details in the [Remote Driver Section]({{< ref "remote_webdriver.md" >}})


## Quitting Sessions

Quitting a session corresponds to W3C command for [Deleting a Session](https://w3c.github.io/webdriver/#delete-session).

Important note: the `quit` method is different from the `close` method, 
and it is recommended to always use `quit` to end the session

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
