# Using AI coding agents with Selenium

> How to get coding agents and large language models to write Selenium code that is current, correct, and not flaky.

URL: https://www.selenium.dev/documentation/ai_agents/
Site index: https://www.selenium.dev/llms.txt

---

## Why agents write old Selenium

A large share of Selenium code is now written with the help of a coding agent,
and much of it is wrong in the same handful of ways.
The reason is the training data.
Models learned Selenium from more than a decade of blog posts, forum answers, and tutorials,
and most of that material describes Selenium 2 and Selenium 3, whose APIs were removed years ago.
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
The blog post
[Your Agent Learned Selenium From My Old Blog Posts](/blog/2026/using-ai-agents-with-selenium/)
tells the story behind this page;
the rest of the page shows how to do that, and what specifically to correct.

## Set up your agent

An agent that can read the current docs stops guessing.
Point it at these, in this order:

| Resource | Use it for |
| --- | --- |
| [llms.txt](https://www.selenium.dev/llms.txt) | A curated index of this site, for an agent to orient itself. Start here. |
| [The quickstart](https://www.selenium.dev/index.md) | The install command and a tested first script for each language, as Markdown. |
| [This documentation](https://www.selenium.dev/documentation/index.md) | The current behaviour of every feature, in every binding. |
| [The examples directory](https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples) | Real, runnable code. |
| API docs for [Java](https://seleniumhq.github.io/selenium/docs/api/java/index.html), [Python](https://seleniumhq.github.io/selenium/docs/api/py/index.html), [.NET](https://seleniumhq.github.io/selenium/docs/api/dotnet/index.html), [Ruby](https://seleniumhq.github.io/selenium/docs/api/rb/index.html), and [JavaScript](https://seleniumhq.github.io/selenium/docs/api/javascript/index.html) | Checking whether a method actually exists, and what it takes. |
| [The Selenium changelogs](https://github.com/SeleniumHQ/selenium/tree/trunk/java) | What changed between your version and whatever the model remembers. |

[`llms.txt`](https://www.selenium.dev/llms.txt) follows the
[llmstxt.org](https://llmstxt.org) convention:
a single plain-text file listing the documentation in a sensible reading order,
so an agent can find the right page without crawling the site or guessing at URLs.
It is worth pointing at explicitly, because it is curated rather than exhaustive.
The Selenium 2 and 3 era [legacy documentation](https://www.selenium.dev/documentation/legacy/index.md)
and the [CDP pages](https://www.selenium.dev/documentation/webdriver/bidi/cdp/index.md) are deliberately
kept out of the main index —
both are accurate, and both are the worst possible input for a model about to write new code.

Every documentation page also has a Markdown version,
with the code examples for every language inline:
add `index.md` to the page URL, as in
`https://www.selenium.dev/documentation/webdriver/waits/index.md`.
It carries none of the site navigation, so it is the cheapest way for an agent to read a page.
When an agent gives a person a link instead, it can add `?lang=` with
`java`, `python`, `csharp`, `ruby`, `javascript`, or `kotlin`,
and the page opens with the examples in that language.

The examples directory deserves particular attention.
Every code tab on this site is a link into that repository,
and the code in it is executed against current Selenium releases.
It is the single most reliable body of Selenium example code to point an agent at,
because unlike a blog post it cannot silently rot.

A useful instruction to give an agent is a negative one:
if an API cannot be found in the current documentation or API reference, it does not exist,
however familiar it looks.

Each coding tool reads project instructions from a different file.
Create the file your tool reads, and add the matching snippet below.
Each one points the tool at the index and the examples;
the full rules to paste after it are in [Rules file](#rules-file).

### Claude Code

Claude Code reads `CLAUDE.md` in the project root.

```markdown
## Selenium

- Documentation index: https://www.selenium.dev/llms.txt
- Examples: https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples
- Fetch the relevant documentation page before using a Selenium API you are not certain about.
- If an API is not in the current documentation or API reference, it does not exist.
```

### Cursor

Cursor reads rule files from `.cursor/rules/`. Create `.cursor/rules/selenium.mdc`:

```markdown
---
description: Selenium browser automation conventions. Use when writing or changing Selenium tests.
alwaysApply: false
---

- Documentation index: https://www.selenium.dev/llms.txt
- Examples: https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples
- Fetch the relevant documentation page before using a Selenium API you are not certain about.
- If an API is not in the current documentation or API reference, it does not exist.
```

### VS Code Copilot

GitHub Copilot reads `.github/copilot-instructions.md`.

```markdown
## Selenium

- Documentation index: https://www.selenium.dev/llms.txt
- Examples: https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples
- Fetch the relevant documentation page before using a Selenium API you are not certain about.
- If an API is not in the current documentation or API reference, it does not exist.
```

### Codex

Codex reads `AGENTS.md` in the project root.

```markdown
## Selenium

- Documentation index: https://www.selenium.dev/llms.txt
- Examples: https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples
- Fetch the relevant documentation page before using a Selenium API you are not certain about.
- If an API is not in the current documentation or API reference, it does not exist.
```

### Other tools

`AGENTS.md` is read by a growing number of tools.
If yours has its own rules file, put the same text there.
If it has none, paste the text at the start of the conversation.

## Rules file

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

**Java**

- Timeouts and waits take a `java.time.Duration`, not a `(long, TimeUnit)` pair.

- The `findElementBy*` and `findElementsBy*` helpers were removed.
Use `driver.findElement(By.id("..."))`.

- `DesiredCapabilities` is replaced by `ChromeOptions`, `FirefoxOptions`, and so on.

- `merge` returns a new object rather than mutating the caller; assign the result.

- `setHeadless(true)` was removed. Use `addArguments("--headless=new")`.

- Use the `Browser` enum rather than the older `BrowserType` interface.

- Do not add WebDriverManager. Selenium Manager is built in.

**Python**

- `find_element_by_id` and every other `find_element_by_*` method was removed in 4.3.
Use `driver.find_element(By.ID, "...")`.

- The `executable_path` and `desired_capabilities` keyword arguments were removed in 4.10.
Pass `service=` and `options=` instead.

- `options.headless = True` was removed. Use `options.add_argument("--headless=new")`.

- `driver.switch_to_alert()` was removed. Use `driver.switch_to.alert`.

- Do not install `webdriver-manager` or call `ChromeDriverManager().install()`.

**CSharp**

- `AddAdditionalCapability` is replaced by `AddAdditionalOption`.

- `DesiredCapabilities` is replaced by `ChromeOptions`, `FirefoxOptions`, and so on.

- Do not pass a driver directory to the `ChromeDriver` constructor.

- `Close()` closes one window; `Quit()` ends the session. Teardown needs `Quit()`.

**Ruby**

- `:desired_capabilities` was removed. Pass `options:` instead.

- Setting `Selenium::WebDriver::Chrome.driver_path=` is unnecessary.

- Headless is an argument: `options.add_argument('--headless=new')`.

- Do not add the `webdrivers` gem. Selenium Manager is built in.

**JavaScript**

- Every command returns a promise. Await all of them, including `driver.quit()`.

- Build the driver with `new Builder().forBrowser(Browser.CHROME).build()`.

- Explicit waits are `driver.wait(until.elementLocated(...), timeout)`.

- The `webdriver-manager` npm package is a Protractor-era tool and is unrelated to Selenium.
Do not install it.

**Kotlin**

- Kotlin uses the Java bindings, so every Java rule applies.

- Timeouts and waits take a `java.time.Duration`.

- The `findElementBy*` helpers were removed. Use `driver.findElement(By.id("..."))`.

- `DesiredCapabilities` is replaced by `ChromeOptions`, `FirefoxOptions`, and so on.

- `setHeadless(true)` was removed. Use `addArguments("--headless=new")`.

### What to correct, and why

The rules above are short on purpose.
This section is the reasoning behind them,
and it is worth reading so you can recognise the bad output when you see it.

#### Nobody downloads drivers any more

The most common single artefact of stale training data is driver management:
a third-party driver manager, a hard-coded path to `chromedriver`, or a `PATH` setup step.
Since 4.6, [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/index.md) discovers the installed browser,
resolves the matching driver, downloads it, and caches it —
and since 4.11 it will download the browser too, if there isn't one.
Starting a session takes no path and no extra dependency.

When an agent adds a driver manager, it isn't adding redundancy.
It is adding a second, slower, less reliable source of truth for which driver version to use.

#### Configuration lives in Options

`DesiredCapabilities` and free-form capability maps predate the W3C WebDriver standard.
Non-standard capabilities now have to be vendor-prefixed, and unprefixed ones can fail
the session outright.
The [browser Options classes](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md) produce a valid
payload by construction, which is why they are the only supported route.
The [upgrade guide](https://www.selenium.dev/documentation/webdriver/troubleshooting/upgrade_to_selenium_4/index.md)
has before-and-after code for every binding.

#### Sleeping is not waiting

Sleeping is the most-repeated pattern in the training data and the most damaging.
A fixed sleep is either too short, and the test fails, or too long, and the suite crawls.
It is the main cause of flaky Selenium tests.

Use an [explicit wait](https://www.selenium.dev/documentation/webdriver/waits/index.md) for the condition that the next line
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

#### Locators have to survive a deploy

Agents are fond of absolute XPath and generated class names,
partly because so much tutorial code contains them.
Both break on the next front-end change.
Prefer `id` and `name`, then CSS on a stable attribute.
See [Locator strategies](https://www.selenium.dev/documentation/webdriver/elements/locators/index.md) and the
[locator suggestions](https://www.selenium.dev/documentation/test_practices/encouraged/locators/index.md)
in our encouraged test practices.

This is also the place where an agent is most likely to invent something.
It cannot see your application, so unless it has actually loaded the page it is pattern-matching
on what a login form usually looks like.
Giving it a way to check — see below — matters more here than anywhere else.

#### BiDi, not CDP

For console logs, JavaScript errors, and network interception,
models reach for the Chrome DevTools Protocol, because that is what the last few years of
examples used.
CDP is Chromium-only and has no stable API between browser versions.
[WebDriver BiDi](https://www.selenium.dev/documentation/webdriver/bidi/index.md) is the W3C standard that replaces it,
and it works across browsers.
CDP support in Selenium exists as a stopgap, not as the recommended path.

#### Grid 4 is not Grid 3

Agents frequently produce Grid 3 invocations —
`-role hub`, `-role node`, and a JSON node config file.
Grid 4 uses subcommands:
`java -jar selenium-server-<version>.jar standalone`, or `hub` and `node`.
See [Grid getting started](https://www.selenium.dev/documentation/grid/getting_started/index.md).

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

### Let the agent read the failure

Selenium's exceptions are specific, and an agent that is handed the real exception —
rather than "the test failed" — will usually fix the right thing.

* Our [common errors](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md) pages
  describe the likely cause and the possible solutions for
  `NoSuchElementException`, `StaleElementReferenceException`,
  `ElementClickInterceptedException`, `SessionNotCreatedException`, and the rest.
  Pointing an agent at the page for the exception it just hit is a short path to a correct fix.
* [Logging](https://www.selenium.dev/documentation/webdriver/troubleshooting/logging/index.md) can be turned up so the agent can
  see the actual commands sent to the driver rather than reasoning about what it thinks happened.
* A screenshot taken at the moment of failure tells an agent more about an
  `ElementClickInterceptedException` — a cookie banner, an overlay — than any stack trace.

## Reviewing agent-written tests

Review the diff for the patterns above, the way you would review a new starter's.
Sleeps, driver paths, and absolute XPath are the ones that survive review most often,
because they work on the machine where they were written.

Do not accept a sleep or a longer timeout as the fix for a flaky test.
That hides the race rather than removing it.
Ask the agent which condition was not yet true, and wait for that condition.

### A workflow that works

1. Have the agent open the feature under test and propose locators, either with a throwaway
   script or an MCP server. Review the locators before any test is written.
2. Let it write the test, following the project rules, fetching documentation pages as needed.
3. Have it run that one test, not the suite, and iterate until it passes.
4. Run the test a few times before you believe it. A test that passes once has not been shown
   to be free of races.
5. Review the diff for the patterns above. Sleeps, driver paths, and absolute XPath are the ones
   that survive review most often, because they work on the machine where they were written.

## Outdated patterns

[Outdated Selenium patterns and their current replacements](/documentation/ai_agents/outdated_patterns/index.md)
shows each pattern an agent is likely to reproduce from old training data,
when it stopped working, and what to write instead.

## Next steps

* [Getting started](https://www.selenium.dev/documentation/webdriver/getting_started/index.md) —
  what a first script looks like in each binding.
* [Upgrade to Selenium 4](https://www.selenium.dev/documentation/webdriver/troubleshooting/upgrade_to_selenium_4/index.md) —
  the before-and-after reference for almost everything an agent gets wrong.
* [Waiting strategies](https://www.selenium.dev/documentation/webdriver/waits/index.md) —
  why sleeping fails and what to do instead.
* [Encouraged test practices](https://www.selenium.dev/documentation/test_practices/encouraged/index.md) —
  the conventions worth putting in your rules file.
* [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/index.md) —
  what replaced driver management.

---

Section pages:

- [Outdated Selenium patterns and their current replacements](https://www.selenium.dev/documentation/ai_agents/outdated_patterns/index.md): Selenium 2 and 3 idioms that coding agents and old tutorials still produce, with the current form of each.
