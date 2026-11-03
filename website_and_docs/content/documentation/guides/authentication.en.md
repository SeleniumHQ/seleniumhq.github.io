---
title: "Authentication and sessions"
linkTitle: "Authentication"
weight: 3
description: >
  Getting past a login once, answering HTTP basic authentication prompts, and reusing a signed-in
  state across tests without driving the login form every time.
---

Almost every test starts behind a login. Driving the login form in each test is slow, it makes
every test depend on the login page working, and some forms (two-factor prompts, third-party
identity providers, CAPTCHAs) cannot be automated at all. HTTP basic authentication is a
different problem again: the browser shows a native dialog that WebDriver cannot see.

## Recommended approach

Log in through the user interface in exactly one test, the one that tests the login form.
Everywhere else, establish the session some other way and start the test already signed in.

For cookie-based sessions, navigate to the application's domain, add the session cookie with
WebDriver's cookie API, and reload. The cookie must be added while the browser is on a page in
the domain the cookie belongs to, otherwise the browser rejects it. Where the cookie comes from
is up to you: an API call that logs in, a fixture created on the server, or a value captured
from a single interactive login.

For HTTP basic authentication, use the network module of WebDriver BiDi. Register a handler
for the `authRequired` phase and answer it with the credentials; the browser never shows the
dialog. Embedding credentials in the URL (`https://user:pass@host/`) is blocked by modern
browsers and should not be relied on.

Keep tests independent: each test should set up the state it needs rather than inheriting it
from the one before, and a fresh browser per test is the simplest way to guarantee that.

Add a cookie to the current browsing context:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/CookiesTest.java#L42-L44" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_cookies.py#L5-L9" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/CookiesTest.cs#L32-L34" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/cookies_spec.rb#L9-L11" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/cookies.spec.js#L18-L21" >}}
{{< /tab >}}
{{< /tabpane >}}

Answer a basic authentication challenge with BiDi:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L55-L60" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_network_commands.py#L82" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_commands.spec.js#L43-L45" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [InvalidCookieDomainException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#invalidcookiedomainexception" >}}):
  the browser is not on a page in the cookie's domain. Navigate there first, then add the cookie.
* [UnableToSetCookieException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#unabletosetcookieexception" >}}):
  the cookie attributes were rejected, for example a `Secure` cookie on an `http://` page.
* [UnhandledAlertException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#unhandledalertexception" >}}):
  a basic authentication dialog is open and blocking the session. Handle it with BiDi before navigating.

## Reference

* [Working with cookies]({{< ref "/documentation/webdriver/interactions/cookies.md" >}})
* [BiDi network]({{< ref "/documentation/webdriver/bidi/w3c/network.md" >}}): intercepts and the `authRequired` phase
* [Virtual authenticator]({{< ref "/documentation/webdriver/interactions/virtual_authenticator.md" >}}): testing WebAuthn and passkeys
* [Two-factor authentication]({{< ref "/documentation/test_practices/discouraged/two_factor_authentication.md" >}}) and
  [third-party logins]({{< ref "/documentation/test_practices/discouraged/gmail_email_and_facebook_logins.md" >}}): what not to automate
* [Avoid sharing state]({{< ref "/documentation/test_practices/encouraged/avoid_sharing_state.md" >}}) and
  [fresh browser per test]({{< ref "/documentation/test_practices/encouraged/fresh_browser_per_test.md" >}})
