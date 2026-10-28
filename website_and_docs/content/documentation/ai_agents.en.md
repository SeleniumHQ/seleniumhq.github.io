---
title: "Using AI coding agents with Selenium"
linkTitle: "AI Agents"
weight: 11
description: >-
  How to get coding agents and large language models to write Selenium code that is current,
  correct, and not flaky.
---

A large share of Selenium code is now written with the help of a coding agent,
and much of it is wrong in the same handful of ways.

The reason is the training data.
Models learned Selenium from more than a decade of blog posts, forum answers, and tutorials,
and most of that material describes Selenium 2 and Selenium 3.
Those APIs were removed years ago.
Worse, some of the most-repeated patterns in that material —
sleeping to wait for the page,
downloading driver binaries by hand,
copying XPath out of DevTools —
were never good practice even when they compiled.
An agent will reproduce all of it, confidently, and with a plausible explanation attached.

None of this is a reason to keep agents away from your test suite.
It is a reason to give an agent what you would give a new team member:
the version you are on,
the place the current documentation lives,
and the conventions the project expects.
This page shows how to do that, and what specifically to correct.

## Give the agent the current documentation

An agent that can read the current docs stops guessing.
Point it at these, in this order:

| Resource | Use it for |
| --- | --- |
| [llms.txt](https://www.selenium.dev/llms.txt) | A curated index of this site, for an agent to orient itself. Start here. |
| [This documentation](https://www.selenium.dev/documentation/) | The current behaviour of every feature, in every binding. |
| [The examples directory](https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples) | Real, runnable code. |
| API docs for [Java](https://seleniumhq.github.io/selenium/docs/api/java/index.html), [Python](https://seleniumhq.github.io/selenium/docs/api/py/index.html), [.NET](https://seleniumhq.github.io/selenium/docs/api/dotnet/index.html), [Ruby](https://seleniumhq.github.io/selenium/docs/api/rb/index.html), and [JavaScript](https://seleniumhq.github.io/selenium/docs/api/javascript/index.html) | Checking whether a method actually exists, and what it takes. |
| [The Selenium changelogs](https://github.com/SeleniumHQ/selenium/tree/trunk/java) | What changed between your version and whatever the model remembers. |

[`llms.txt`](https://www.selenium.dev/llms.txt) follows the
[llmstxt.org](https://llmstxt.org) convention:
a single plain-text file listing the documentation in a sensible reading order,
so an agent can find the right page without crawling the site or guessing at URLs.
It is worth pointing at explicitly, because it is curated rather than exhaustive.
The Selenium 2 and 3 era [legacy documentation]({{< ref "/documentation/legacy/" >}})
and the [CDP pages]({{< ref "/documentation/webdriver/bidi/cdp/" >}}) are deliberately
kept out of the main index —
both are accurate, and both are the worst possible input for a model about to write new code.

The examples directory deserves particular attention.
Every code tab on this site is a link into that repository,
and the code in it is executed against current Selenium releases.
It is the single most reliable body of Selenium example code to point an agent at,
because unlike a blog post it cannot silently rot.

A useful instruction to give an agent is a negative one:
if an API cannot be found in the current documentation or API reference, it does not exist,
however familiar it looks.

## Write the project rules down

Agents follow conventions far more reliably when the conventions are written in a file
rather than repeated in chat.
Add something like the following to your project's `AGENTS.md`, `CLAUDE.md`, or rules file,
and adjust the version and commands to match your project.

```markdown
## Browser automation

This project uses Selenium 4.49.0.

### Docs
- Index: https://www.selenium.dev/llms.txt
- Reference: https://www.selenium.dev/documentation/
- Examples: https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples
- Fetch the relevant page before using an API you are not certain about.
- Do not use the legacy or CDP pages as a basis for new code.
- Do not use APIs from Selenium 3 or earlier. If an API is not in the
  current documentation or API reference, it does not exist.

### Drivers and browsers
- Selenium Manager downloads and caches the drivers. Do not add a
  driver-manager dependency, do not download drivers, and do not set
  a path to a driver binary.
- Browser configuration goes in that browser's Options class. Do not
  use DesiredCapabilities and do not pass raw capability maps.
- Headless is a browser argument: `--headless=new`.

### Waiting
- Never sleep in a test.
- Use an explicit wait, and wait for the condition the next line
  actually depends on.
- Do not set an implicit wait and also use explicit waits in the
  same session.
- When a test is flaky, find the condition that was not yet true.
  Do not increase a timeout.

### Locators
- Prefer `id` and `name`, then a CSS selector on a stable attribute
  such as `data-test`.
- Do not write absolute XPath and do not use generated class names.
- Declare locators separately from the code that finds the element.

### Sessions
- One fresh session per test. Always quit the driver in teardown -
  `quit`, not `close`.
- Do not share a driver between tests or hold one in a global.

### Events and network
- Use WebDriver BiDi for console logs, JavaScript errors, and network
  interception. Do not use the Chrome DevTools Protocol.

### Running tests
- All tests: <your command>
- A single test: <your command>
- If you are unsure whether a locator or a flow works, write a
  throwaway script and run it against the application instead of
  guessing.
```

Then add the rules for your binding.
These are the removals and replacements that models get wrong most often.

{{< tabpane text=true >}}
{{% tab header="Java" %}}

* Timeouts and waits take a `java.time.Duration`, not a `(long, TimeUnit)` pair.
* The `findElementBy*` and `findElementsBy*` helpers were removed.
  Use `driver.findElement(By.id("..."))`.
* `DesiredCapabilities` is replaced by `ChromeOptions`, `FirefoxOptions`, and so on.
* `merge` returns a new object rather than mutating the caller; assign the result.
* `setHeadless(true)` was removed. Use `addArguments("--headless=new")`.
* Use the `Browser` enum rather than the older `BrowserType` interface.
* Do not add WebDriverManager. Selenium Manager is built in.
{{% /tab %}}
{{% tab header="Python" %}}

* `find_element_by_id` and every other `find_element_by_*` method was removed in 4.3.
  Use `driver.find_element(By.ID, "...")`.
* The `executable_path` and `desired_capabilities` keyword arguments were removed in 4.10.
  Pass `service=` and `options=` instead.
* `options.headless = True` was removed. Use `options.add_argument("--headless=new")`.
* `driver.switch_to_alert()` was removed. Use `driver.switch_to.alert`.
* Do not install `webdriver-manager` or call `ChromeDriverManager().install()`.
{{% /tab %}}
{{% tab header="CSharp" %}}

* `AddAdditionalCapability` is replaced by `AddAdditionalOption`.
* `DesiredCapabilities` is replaced by `ChromeOptions`, `FirefoxOptions`, and so on.
* Do not pass a driver directory to the `ChromeDriver` constructor.
* `Close()` closes one window; `Quit()` ends the session. Teardown needs `Quit()`.
{{% /tab %}}
{{% tab header="Ruby" %}}

* `:desired_capabilities` was removed. Pass `options:` instead.
* Setting `Selenium::WebDriver::Chrome.driver_path=` is unnecessary.
* Headless is an argument: `options.add_argument('--headless=new')`.
* Do not add the `webdrivers` gem. Selenium Manager is built in.
{{% /tab %}}
{{% tab header="JavaScript" %}}

* Every command returns a promise. Await all of them, including `driver.quit()`.
* Build the driver with `new Builder().forBrowser(Browser.CHROME).build()`.
* Explicit waits are `driver.wait(until.elementLocated(...), timeout)`.
* The `webdriver-manager` npm package is a Protractor-era tool and is unrelated to Selenium.
  Do not install it.
{{% /tab %}}
{{% tab header="Kotlin" %}}

* Kotlin uses the Java bindings, so every Java rule applies.
* Timeouts and waits take a `java.time.Duration`.
* The `findElementBy*` helpers were removed. Use `driver.findElement(By.id("..."))`.
* `DesiredCapabilities` is replaced by `ChromeOptions`, `FirefoxOptions`, and so on.
* `setHeadless(true)` was removed. Use `addArguments("--headless=new")`.
{{% /tab %}}
{{< /tabpane >}}

## What to correct, and why

The rules above are short on purpose.
This section is the reasoning behind them,
and it is worth reading so you can recognise the bad output when you see it.

### Nobody downloads drivers any more

The most common single artefact of stale training data is driver management:
a third-party driver manager, a hard-coded path to `chromedriver`, or a `PATH` setup step.
Since 4.6, [Selenium Manager]({{< ref "/documentation/selenium_manager.md" >}}) discovers the installed browser,
resolves the matching driver, downloads it, and caches it —
and since 4.11 it will download the browser too, if there isn't one.
Starting a session takes no path and no extra dependency.

When an agent adds a driver manager, it isn't adding redundancy.
It is adding a second, slower, less reliable source of truth for which driver version to use.

### Configuration lives in Options

`DesiredCapabilities` and free-form capability maps predate the W3C WebDriver standard.
Non-standard capabilities now have to be vendor-prefixed, and unprefixed ones can fail
the session outright.
The [browser Options classes]({{< ref "/documentation/webdriver/drivers/options.md" >}}) produce a valid
payload by construction, which is why they are the only supported route.
The [upgrade guide]({{< ref "/documentation/webdriver/troubleshooting/upgrade_to_selenium_4.md" >}})
has before-and-after code for every binding.

### Sleeping is not waiting

Sleeping is the most-repeated pattern in the training data and the most damaging.
A fixed sleep is either too short, and the test fails, or too long, and the suite crawls.
It is the main cause of flaky Selenium tests.

Use an [explicit wait]({{< ref "/documentation/webdriver/waits.md" >}}) for the condition that the next line
depends on — element clickable, text present, spinner gone — not for a duration.
And do not mix implicit and explicit waits in the same session:
as the waits documentation explains,
the two combine unpredictably,
so a 10-second implicit wait and a 15-second explicit wait can time out after 20.

Watch for the second-order version of this mistake:
when a test fails intermittently,
an agent will very often "fix" it by raising a timeout or adding a sleep.
That hides the race rather than removing it.
Insist that it identify which condition was not yet true.

### Locators have to survive a deploy

Agents are fond of absolute XPath and generated class names,
partly because so much tutorial code contains them.
Both break on the next front-end change.
Prefer `id` and `name`, then CSS on a stable attribute.
See [Locator strategies]({{< ref "/documentation/webdriver/elements/locators.md" >}}) and the
[locator suggestions]({{< ref "/documentation/test_practices/encouraged/locators.md" >}})
in our encouraged test practices.

This is also the place where an agent is most likely to invent something.
It cannot see your application, so unless it has actually loaded the page it is pattern-matching
on what a login form usually looks like.
Giving it a way to check — see below — matters more here than anywhere else.

### BiDi, not CDP

For console logs, JavaScript errors, and network interception,
models reach for the Chrome DevTools Protocol, because that is what the last few years of
examples used.
CDP is Chromium-only and has no stable API between browser versions.
[WebDriver BiDi]({{< ref "/documentation/webdriver/bidi/" >}}) is the W3C standard that replaces it,
and it works across browsers.
CDP support in Selenium exists as a stopgap, not as the recommended path.

### Grid 4 is not Grid 3

Agents frequently produce Grid 3 invocations —
`-role hub`, `-role node`, and a JSON node config file.
Grid 4 uses subcommands:
`java -jar selenium-server-<version>.jar standalone`, or `hub` and `node`.
See [Grid getting started]({{< ref "/documentation/grid/getting_started.md" >}}).

## Let the agent drive a real browser

An agent that can only write code is guessing about your application.
An agent that can open it can check.

The lightest-weight option needs nothing new:
tell the agent it may write a throwaway Selenium script, run it, and print what it finds.
This is genuinely effective —
it can navigate to the page, dump the candidate elements, try a locator, and report back —
and it exercises the same stack the real test will use,
which no external tool can claim.

There are also community-maintained Model Context Protocol servers that expose Selenium as a set
of tools an agent can call directly, so it can open a browser, click, type, and take screenshots
inside a conversation.
These are third-party projects rather than part of the Selenium project,
so evaluate them as you would any other dependency.

Either way, the rule to write down is the same:
verify locators against the running application instead of inferring them.

## Let the agent read the failure

Selenium's exceptions are specific, and an agent that is handed the real exception —
rather than "the test failed" — will usually fix the right thing.

* Our [common errors]({{< ref "/documentation/webdriver/troubleshooting/errors/" >}}) pages
  describe the likely cause and the possible solutions for
  `NoSuchElementException`, `StaleElementReferenceException`,
  `ElementClickInterceptedException`, `SessionNotCreatedException`, and the rest.
  Pointing an agent at the page for the exception it just hit is a short path to a correct fix.
* [Logging]({{< ref "/documentation/webdriver/troubleshooting/logging.md" >}}) can be turned up so the agent can
  see the actual commands sent to the driver rather than reasoning about what it thinks happened.
* A screenshot taken at the moment of failure tells an agent more about an
  `ElementClickInterceptedException` — a cookie banner, an overlay — than any stack trace.

## A workflow that works

1. Have the agent open the feature under test and propose locators, either with a throwaway
   script or an MCP server. Review the locators before any test is written.
2. Let it write the test, following the project rules, fetching documentation pages as needed.
3. Have it run that one test, not the suite, and iterate until it passes.
4. Run the test a few times before you believe it. A test that passes once has not been shown
   to be free of races.
5. Review the diff for the patterns above. Sleeps, driver paths, and absolute XPath are the ones
   that survive review most often, because they work on the machine where they were written.

## Next steps

* [Getting started]({{< ref "/documentation/webdriver/getting_started/" >}}) —
  what a first script looks like in each binding.
* [Upgrade to Selenium 4]({{< ref "/documentation/webdriver/troubleshooting/upgrade_to_selenium_4.md" >}}) —
  the before-and-after reference for almost everything an agent gets wrong.
* [Waiting strategies]({{< ref "/documentation/webdriver/waits.md" >}}) —
  why sleeping fails and what to do instead.
* [Encouraged test practices]({{< ref "/documentation/test_practices/encouraged/" >}}) —
  the conventions worth putting in your rules file.
* [Selenium Manager]({{< ref "/documentation/selenium_manager.md" >}}) —
  what replaced driver management.
