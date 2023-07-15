---
title: "HTTP Client Configuration"
linkTitle: "Http Client"
weight: 3
---

One of the great things about WebDriver's design is that any programming language with an HTTP Library
can implement the protocol and be used to drive a browser. In Selenium, each of the bindings
has a default HTTP Library used to create an HTTP Client. 
This Client is responsible for sending network requests and receiving the associated responses to communicate
with a driver or the grid.


## Overview

Here's a brief explanation of the default setup in each language:

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
**Background**

The default client has changed several times (Apache http client; OkHttp; currently it is Async HTTP Library).
We are moving to the standard Java library, but the features Selenium requires were not added to the standard library until Java 11, 
so we can not make it the default until we set Java 11 as the minimum required version, which is planned at the end of September 2023.

We describe this here, but we should summarize it for the documentation rather than needing to point people to the blog post
because people care about the "what" more than they "why"
https://www.selenium.dev/blog/2022/using-java11-httpclient/

\
**Configuration**

Rather than having users adjust the http client settings with system properties, Java created a `ClientConfig` class
for Selenium 4.0.
{{% /tab %}}
{{% tab header="Python" %}}
Python switched from httplib to urllib3 to support asynchronous communication for CDP functionality. 
Settings affecting the http client have been supported in constructors of various classes as well as with class methods
in the `RemoteConnection()` class itself.
{{% /tab %}}
{{% tab header="CSharp" %}}
.NET switched from HttpWebRequest to the standard library HttpClient.
Configuration is only possible for the timeout value which can be set in the driver constructor.
{{% /tab %}}
{{% tab header="Ruby" %}}
Ruby has always used the standard http library.
Rather than passing in a configuration, Ruby allows users to change behaviors by subclassing a provided wrapper class. 
{{% /tab %}}
{{% tab header="JavaScript" %}}
Who can possibly understand the innerworkings of this language?
{{% /tab %}}
{{% tab header="Kotlin" %}}
Just do what Java does until told otherwise
{{% /tab %}}
{{< /tabpane >}}


## Default Client Usage

HTTP Clients are more important for Remote Server connections so these examples will show how to use them with
the Grid:

{{< tabpane langEqualsHeader=true >}}
{{% tab text=true header="Java" %}}
Java only supports setting cient config when using the RemoteWebDriverBuilder:
```java
ClientConfig config = ClientConfig.defaultConfig()

WebDriver driver = RemoteWebDriver
  .builder()
  .oneOf(new ChromeOptions())
  .config(config)
  .build();
```
{{% /tab %}}
{{< tab header="Python" >}}
config = ClientConfig()
driver = webdriver.Remote(client_config: config)
{{< /tab >}}
{{% tab text=true header="CSharp" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< tab header="Ruby" >}}
http_client = Selenium::WebDriver::Remote::Http::Default.new
options = Selenium::WebDriver::Options.chrome
driver = Selenium::WebDriver.for :remote, options: chrome, http_client: http_client
{{< /tab >}}
{{% tab text=true header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab text=true header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


## Keep Alive

The default in Selenium 4.0 for all bindings is `true`.
This setting can dramatically improve performance with SSL over remote connections
It is not recommended to change this.

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< /tabpane >}}

## Timeouts

Two types of timeouts can apply and many clients use the same setting for both
Open timeout (or connection timeout), and Read Timeout.

### Connection or Open
This is named different things in different bindings, but it only applies
occurs the first time the connection is negotiated. If keep-alive is true, then this only
applies the first time, if it is false it applies every time.
The default value is: ???

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Read
Read timeout applies to every single request and determines how long it should wait for the host to respond.
Other timeouts (such as Page Load timeout or any command timeout on the grid or a service provided) that are higher
than the Read timeout will never be encountered. For improved information about what is happening, the
Read timeout should always be higher than these other timeouts.
The default value in all bindings as of Selenium 4.11 is: 120 seconds

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

## Max Redirects
Some service providers manage session availability by sending redirect requests when a session isn't available yet.
This value represents how many of these redirects the client will allow before stopping.
The default value in all bindings as of Selenium 4.11 is: 20

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< /tabpane >}}


## Proxy
This is if there is a proxy on the client machine that is needed to connect to the driver, grid, or service provider.
For routing network traffic going into the browser, you must set a proxy in the Options class.

Note: the proxy required in Java is different from the proxy required in Options; does that make sense?

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


## Authentication
Most bindings you would just add the username and password to the proxy in use, but Java allows this to be
specified independently. Does this make sense?

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< /tabpane >}}


## Certificate Path
Python allows you to specify this. Do other bindings need it?

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< /tabpane >}}


### Filters
This is set in Java, but I have no idea what it does.

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< /tabpane >}}
