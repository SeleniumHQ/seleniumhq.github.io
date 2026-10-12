---
title: "Files: upload and download"
linkTitle: "Files"
weight: 4
description: >
  Uploading a file through a form without touching the operating system's file dialog, and why
  downloading through the browser is not the right way to test a download.
---

Clicking a file input opens the operating system's file chooser, which is outside the browser
and invisible to WebDriver. Clicking a download link starts a download the API cannot watch:
there is no event for "finished", no progress, and no portable way to find the file afterwards.

## Recommended approach

**Uploads.** Do not click the input. Send the absolute path of the file to the
`<input type="file">` element with `sendKeys`, then submit the form as usual. The browser
attaches the file exactly as if it had been chosen through the dialog. The path must exist on
the machine where the _browser_ is running; when the browser is remote, on a Grid, set a local
file detector so the client uploads the file to the node first.

**Downloads.** Testing a download through the browser is discouraged. The browser's download
behaviour is configurable per browser and per platform, the file lands in a directory you then
have to poll, and none of this is what a user actually cares about. Instead, use Selenium to
get to the point where the download link exists, read its `href` (and any cookies the request
needs), and fetch it with an HTTP client in your test language. That gives you the bytes
directly, with a proper status code, and no polling.

If you are running on Selenium Grid and really need the browser to perform the download, Grid
can be configured to collect downloaded files from the node and the client can retrieve them;
see the remote WebDriver page below.

Upload a file by sending its path to the input:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FileUploadTest.java#L17-L19" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_file_upload.py#L12-L14" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FileUploadTest.cs#L21-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/file_upload_spec.rb#L12-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/elements/fileUpload.spec.js#L26-L27" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [InvalidArgumentException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#invalidargumentexception" >}}):
  the path does not exist on the machine running the browser. Use an absolute path, and a file
  detector when the browser is remote.
* [ElementNotInteractableException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#elementnotinteractableexception" >}}):
  the file input is hidden behind a styled button. Sending keys to a hidden file input is
  allowed in most browsers; if it is refused, check the `strictFileInteractability` capability.

## Reference

* [File upload]({{< ref "/documentation/webdriver/elements/file_upload.md" >}})
* [File downloads are discouraged]({{< ref "/documentation/test_practices/discouraged/file_downloads.md" >}})
* [Remote WebDriver]({{< ref "/documentation/webdriver/drivers/remote_webdriver.md" >}}): uploads and downloads through a Grid
* [strictFileInteractability]({{< ref "/documentation/webdriver/drivers/options.md#strictfileinteractability" >}})
