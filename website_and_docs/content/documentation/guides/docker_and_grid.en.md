---
title: "Running in Docker and Grid"
linkTitle: "Docker and Grid"
weight: 11
description: >
  Moving the browser off the machine that runs the tests, with Selenium Grid and the project's
  Docker images, so a suite can run in parallel and against several browsers.
---

A suite that takes an hour because every test starts its own browser on the same machine.
A requirement to test on a browser that is not installed where the tests run. A build server
that must not have a browser on it at all. In each case the browser needs to be somewhere
other than the test process.

## Recommended approach

Selenium Grid is a server that accepts WebDriver sessions over HTTP and routes them to
browsers it manages. Your test code changes in exactly one place: instead of constructing a
local driver, construct a remote one with the Grid URL and the browser options. Everything
else stays the same, so a suite can run locally during development and on a Grid in CI with a
single configuration switch.

**Standalone** mode runs the whole Grid in one process and is the right starting point. It
needs a Java runtime and the Selenium Server jar, listens on `http://localhost:4444`, and can
use Selenium Manager to fetch drivers for the browsers it finds. **Hub and Node** spreads
browsers across several machines, and **distributed** mode separates each Grid component for
large installations.

The quickest way to get a Grid, and the only way to get one with no browser on the host, is
the project's own Docker images in the
[docker-selenium](https://github.com/SeleniumHQ/docker-selenium) repository. They package the
Grid, a browser and its driver, and are published for each Selenium release. A single
`docker run` gives you a standalone Grid with Chrome, Firefox or Edge; the repository also has
Docker Compose and Kubernetes examples, video recording, and VNC for watching a session.

When the browser is remote, files to upload must reach the node first, through a file
detector, and downloads must be enabled on the Grid and fetched back through the client. Both
are covered on the remote WebDriver page.

Point a test at a Grid:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L38-L39" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_remote_webdriver.py#L13-L14" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L28-L29" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/drivers/remote_webdriver_spec.rb#L20-L21" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/drivers/remote_webdriver.spec.js#L43-L44" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [SessionNotCreatedException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#sessionnotcreatedexception" >}}):
  no node on the Grid can satisfy the requested browser and version. Check the Grid's status
  page at the Grid URL for the capabilities it offers.
* [InvalidArgumentException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#invalidargumentexception" >}}):
  a local file path was sent to a remote browser. Set a file detector so the file is uploaded.
* [WebDriverException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#webdriverexception" >}})
  with a connection error: the Grid URL is wrong, the server is not running, or a firewall is
  between the client and the Grid.

## Reference

* [Grid getting started]({{< ref "/documentation/grid/getting_started.md" >}}): standalone, hub and node, distributed
* [Grid configuration]({{< ref "/documentation/grid/configuration/_index.md" >}}) and
  [advanced features]({{< ref "/documentation/grid/advanced_features/_index.md" >}})
* [Remote WebDriver]({{< ref "/documentation/webdriver/drivers/remote_webdriver.md" >}}): file uploads and downloads with a Grid
* [docker-selenium](https://github.com/SeleniumHQ/docker-selenium): the project's Docker images and deployment examples
