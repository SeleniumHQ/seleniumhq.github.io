---
title: "Selenium release notes"
linkTitle: "Release notes"
weight: 13
description: >
  What changed in each recent Selenium release, newest first, with links to the
  announcement and to the full changelog of every binding.
---

Selenium releases all language bindings and the Grid together, roughly once a month.
This page lists the highlights of each recent release, newest first.
The full announcement for every release, including contributor credits and the
Docker Selenium changes, is on the [blog](/blog/), and the complete,
line-by-line history of each binding lives in its changelog on GitHub:
[Java][java-changelog], [Python][python-changelog], [.NET][dotnet-changelog],
[Ruby][ruby-changelog] and [JavaScript][javascript-changelog].
Releases older than those listed here are covered by their
[blog posts](/categories/releases/).

## Selenium 4.51

Released 9 October 2026.
[Announcement](/blog/2026/selenium-4-51-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.51.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- Java no longer depends on Guava, so `ExpectedCondition` no longer implements Guava's
  `Function` interface; see the [migration guide](/blog/2026/expectedcondition-drops-guava/)
  if your code relied on it. `RemoteWebDriver.getClientFactory()` was added and the removal of
  the deprecated `HttpCommandExecutor` fields was postponed to 4.53.
- JavaScript publishes the browser atoms, now written in TypeScript, as a standalone
  `@selenium/atoms` npm package, and can install and uninstall web extensions through BiDi
  with a fallback to the classic commands.
- Python added support for Python 3.15.
- BiDi code generation grew again: Java gained a BiDi code generator, JavaScript can generate
  vendor specific classes, and the Firefox vendor CDDL is pinned from the Firefox repository.
- Selenium Manager now degrades to empty metadata when its cache cannot be read instead of
  failing, and Java artifacts are signed in-process during the release.

## Selenium 4.50

Released 30 September 2026.
[Announcement](/blog/2026/selenium-4-50-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.50.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- Relative locators in Java and .NET can be anchored to an element or a shadow root, not
  only to the driver.
- The deprecated Firefox profile extension and certificate methods were removed from every
  binding; Java, Python and .NET now declare the Firefox default preferences in the binding
  itself.
- BiDi in .NET added screenshot image size, a screencast destination folder,
  `Emulation.SetTextLayoutModeOverride` and activity source monitoring; Ruby can install and
  uninstall web extensions through BiDi; Java parses each BiDi command response once.
- Java removed the deprecated methods from the `HttpClient` interface and a
  `RemoteWebDriver` now uses a single `HttpClient.Factory`.
- Python applies the `ClientConfig` `user_agent` and `extra_headers` per connection.
- Releases are built on remote build execution and Selenium Manager can be cross-compiled for
  every platform from Linux or macOS.

## Selenium 4.49

Released 9 September 2026.
[Announcement](/blog/2026/selenium-4-49-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.49.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- .NET fixed a run of BiDi wire-format bugs (missing DTO fields, mismatched proxy, realm and
  `NamespaceUri` JSON property names, an oversized command-descriptor allocation) and gained
  browsing context screencast support.
- Python raises typed WebDriver errors for BiDi wire error codes and runs its BiDi suite
  against the Grid; JavaScript, Python and Ruby derive BiDi field names and enum value types
  from the shared schema.
- Grid fixed a 500 error when downloading a file whose name contains spaces.
- Java removed the deprecated `GET /session/{sessionId}/se/files/{fileName}` endpoint; jars
  now ship with LICENSE and NOTICE files and smaller javadocs.
- Selenium Manager fixed Windows architecture detection under WOW64 and added Chrome
  arm64-on-Linux support; every binding ships a prebuilt Linux arm64 binary.

## Selenium 4.48

Released 27 August 2026.
[Announcement](/blog/2026/selenium-4-48-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.48.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- The shared, binding-neutral BiDi layer got stricter: Ruby rejects invalid inbound values,
  .NET throws on an unknown discriminator, and JavaScript gained a serialization and domain
  layer plus connection-level event subscriptions.
- Ruby fixed a silent hang when receiving an oversized WebSocket frame.
- Grid no longer forwards `se:remoteUrl` past the Node that consumes it, and file upload and
  download now work for Kubernetes, Docker and relay sessions.
- .NET added the `SetMediaFeaturesOverride` BiDi command to the Emulation module.
- Python fixed W3C capabilities built from a list of options (`create_matches`); JavaScript
  normalizes empty custom locator results and no longer exposes BiDi on `Driver`.

## Selenium 4.47

Released 10 August 2026.
[Announcement](/blog/2026/selenium-4-47-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.47.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- Ruby now generates its BiDi domain type accessors, union variant factories and typed errors
  from the shared CDDL-derived schema, as JavaScript already did, and supports BiDi on Safari
  Preview.
- Firefox blocks CDP access in .NET, Python and Ruby, reinforcing BiDi as the cross-browser
  protocol.
- Java fixed BiDi not initializing for a `RemoteWebDriver` built via the builder, and fixed
  `By.className()`/`By.id()` misescaping non-ASCII leading digits and a `By.name()` bug on
  names containing `%`.
- .NET BiDi transport factories are composable, the optional command timeout was removed from
  command options, and `SE_*DRIVER` environment variables can set driver locations.
- Dynamic Grid videos are stored in a per-session subfolder via `SE_VIDEO_SESSION_SUBFOLDER`,
  including on Kubernetes.
- Selenium Manager honours `--browser-version` for Electron, finds Chrome and Edge in known
  install directories, and no longer caches an empty driver version.

## Selenium 4.46

Released 11 July 2026.
[Announcement](/blog/2026/selenium-4-46-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.46.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- Security: Selenium Manager guards against path traversal when extracting tar and pkg
  archives.
- Java hardened its JSON parser for RFC 8259 compliance and marked all current BiDi classes
  as beta ahead of further changes; BiDi creation moved to `RemoteWebDriver`, BiDi is enabled
  for Safari options, and event subscriptions are tracked by subscription ID.
- The shared binding-neutral BiDi schema is now adopted by JavaScript and Ruby; Ruby also
  gained `ClientConfig` for HTTP client customization.
- Python routes Safari, Safari Technology Preview and WebView2 to their own handlers.
- Selenium Manager fixed Firefox binary discovery on Linux and supports macOS `.pkg`/pbzx
  payloads; Grid fixed classpath packaging for the Redis-backed `SessionQueue` from 4.45.

## Selenium 4.45

Released 16 June 2026.
[Announcement](/blog/2026/selenium-4-45-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.45.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- Java can drive Electron apps through `ElectronOptions` and `ElectronDriver`, and publishes a
  `selenium-devtools-latest` artifact.
- The shared atoms (`isDisplayed`, `getAttribute`, `find-elements`) continued moving from
  Closure to TypeScript and are used by JavaScript, Ruby, Java and .NET.
- Grid hardened its WebSocket proxy (pre-handshake race, TCP backpressure, fast-path framing)
  and added Redis-backed `SessionQueue` support next to the bundled Redis `SessionMap`.
- Python gained high-level BiDi network APIs for request, response and authentication
  handling plus extra headers, and aligned its BiDi script module with the cross-binding
  design. A request handler looks like this:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_network_commands.py#L46-L49" >}}
{{< /tab >}}
{{< /tabpane >}}

- Ruby deprecated the `curb` HTTP client and the `Chromium Profile` classes, and split
  responsibilities between `Service`, `DriverFinder` and `Options`.
- Selenium Manager prunes cache entries older than 30 days and switched its TLS backend to
  `ring`; Bazel moved to 9.1 and the release pipeline is rerun-safe.

## Selenium 4.44

Released 12 May 2026.
[Announcement](/blog/2026/selenium-4-44-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.44.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- A new Selenium CLI tool, an AI-assisted contribution policy, and Rust commands to install
  `skills.md`/`rules.md` files for agent-based contributors.
- .NET statically declares BiDi commands and events and streams additional events, with
  breaking changes: obsolete members were removed and `UnhandledPromptBehavior` accepts a
  string or a map.
- Java continued JSpecify nullability work in the `remote` package, deprecated the native
  `HttpClient` methods, and fixed a `NoSuchElementException` regression for custom `By`
  locators.
- Grid added a Redis-backed Distributor as a built-in option and fixed latent WebSocket proxy
  bugs.
- Python generates its BiDi files instead of hand-curating them and added a CDDL 2 generator;
  JavaScript converted the `getAttribute` and `isDisplayed` atoms to TypeScript.

## Selenium 4.43

Released 10 April 2026.
[Announcement](/blog/2026/selenium-4-43-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.43.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

A quick follow-up to 4.42 that fixes one Grid regression.

- Grid: a mobile slot's stereotype `browserName` no longer leaks into merged session
  capabilities when an app-related capability is present.

## Selenium 4.42

Released 9 April 2026.
[Announcement](/blog/2026/selenium-4-42-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.42.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- .NET made a large BiDi consistency pass: unified `*EventArgs` types, BiDi exposed through
  interfaces, thread-safe event registration, close to zero allocation per command or event,
  and any `WebDriver` can be disposed asynchronously.
- Java implemented the BiDi `speculation` module, extended JSpecify nullability coverage, and
  fixed several `Keys` and `ScriptKey` correctness issues.
- Grid hardened the WebSocket router with a transparent TCP tunnel bypass path, handling for
  dropped close frames and idle disconnects, and a `NodeCommandInterceptor` for pluggable
  command interception via `--ext`.
- Python expanded BiDi test coverage and added type stubs for lazily imported classes.
- JavaScript gained a `Color` class; the build moved to Bazel 9.

## Selenium 4.41

Released 20 February 2026.
[Announcement](/blog/2026/selenium-4-41-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.41.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- .NET expanded the BiDi Emulation module (`SetTouchOverride`, `SetNetworkConditions`, a
  wider `SetViewport`), added a `Speculation` module, and dispatches commands asynchronously;
  as a breaking change, Selenium Manager and the driver service start and stop
  asynchronously.
- Java continued JSpecify nullability coverage across the BiDi packages and fixed JSON parsing
  of numbers with an exponent.
- Grid fixed a Distributor deadlock, WebSocket connection counter leaks and thread exhaustion
  in health checks, and added Kubernetes cluster and basic-auth support for Dynamic Grid.
- Python added the BiDi `set_screen_settings_override` emulation command and enabled Edge
  BiDi tests.
- Ruby outputs driver logs by default when debug is enabled and no longer stores generated
  atoms in the repository.
- The legacy Rake/CrazyFun build system was retired after a sweeping release and CI overhaul.

## Selenium 4.40

Released 18 January 2026.
[Announcement](/blog/2026/selenium-4-40-released/) ·
[GitHub release](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.40.0) ·
Changelogs: [Java][java-changelog], [Python][python-changelog],
[.NET][dotnet-changelog], [Ruby][ruby-changelog], [JavaScript][javascript-changelog]

- Chrome DevTools support covers versions 144, 143 and 142.
- Java expanded BiDi with `setScreenOrientationOverride` and `setNetworkConditions`, and
  deprecated its unused logging and Guava collection classes.
- Python introduced the `LocalWebDriver` base class and removed the deprecated `FirefoxBinary`
  and FTP proxy support.
- .NET added `SetScreenSettingsOverrideAsync` and `FileDialogOpened`, exposed the input
  module, and reuses buffers in BiDi communication.
- Grid improved Dynamic Grid container naming and labels, concurrent session handling, and
  fixed race conditions.

[java-changelog]: https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG
[python-changelog]: https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES
[dotnet-changelog]: https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG
[ruby-changelog]: https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES
[javascript-changelog]: https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/selenium-webdriver/CHANGES.md
