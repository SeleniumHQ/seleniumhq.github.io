---
title: "Migrating WebDriverJS off the Promise Manager"
linkTitle: "WebDriverJS Promise Manager"
weight: 8
description: >
    How to move JavaScript tests from the WebDriverJS promise manager to async/await.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/WebDriverJs) \
The promise manager was removed in `selenium-webdriver` 4. The source links on this page point to
the [selenium-3.6.0](https://github.com/SeleniumHQ/selenium/tree/selenium-3.6.0/javascript/node/selenium-webdriver) tag.

## Introduction

The WebDriverJS library uses a promise manager to ease the pain of working with a purely 
asynchronous API. Rather than writing a long chain of promises, the promise manager allows 
you to write code as if WebDriverJS had a synchronous, blocking API (like all of the other 
Selenium language bindings). For instance, instead of

```js
const {Builder, By, until} = require('selenium-webdriver');
new Builder()
    .forBrowser('firefox')
    .build()
    .then(driver => {
      return driver.get('http://www.google.com/ncr')
        .then(_ => driver.findElement(By.name('q')).sendKeys('webdriver'))
        .then(_ => driver.findElement(By.name('btnK')).click())
        .then(_ => driver.wait(until.titleIs('webdriver - Google Search'), 1000))
        .then(_ => driver.quit());
    });
```

you can write

```js
const {Builder, By, until} = require('selenium-webdriver');

let driver = new Builder()
    .forBrowser('firefox')
    .build();

driver.get('http://www.google.com/ncr');
driver.findElement(By.name('q')).sendKeys('webdriver');
driver.findElement(By.name('btnK')).click();
driver.wait(until.titleIs('webdriver - Google Search'), 1000);
driver.quit();
```

Unfortunately, there is no such thing as a free lunch. With WebDriverJS, using the promise 
manager comes at the cost of increased complexity (for the Selenium maintainers--the 
[promise module](https://github.com/SeleniumHQ/selenium/blob/selenium-3.6.0/javascript/node/selenium-webdriver/lib/promise.js) 
is a 3300 line beast) and reduced debuggability. For debugging, suppose you inserted a 
`debugger` statement:

```js
driver.get('http://www.google.com/ncr');
debugger;
driver.findElement(By.name('q')).sendKeys('webdriver');
```

When is this script going to pause - after WebDriver loads google.com, or after it 
_schedules the command to load google.com_? Since the promise manager abstracts away the 
async nature of the API, it hides that you need to explicitly use a callback to break after 
a command _executes_:

```js
driver.get('http://www.google.com/ncr').then(() => debugger);
driver.findElement(By.name('q')).sendKeys('webdriver');
```

JavaScript has evolved in many ways since WebDriverJS was originally created. Not only did 
the community standardize the behavior and API for promises, but promises were added to the 
language itself. The next version of JavaScript, ES2017, adds support for 
[async functions](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/async_function), 
greatly simplifying the process of writing and maintaining asynchronous code. At this point, 
the benefits of the promise manager no longer outweigh its costs, so it will 
[soon be deprecated](https://github.com/SeleniumHQ/selenium/issues/2969) and removed from 
WebDriverJS. The remainder of this guide will explain how users can migrate off the promise 
manager and effectively use the async constructs available today.

## Moving to async/await

### Step 1: Disabling the Promise Manager

As outlined in [issue 2969](https://github.com/SeleniumHQ/selenium/issues/2969), 
WebDriverJS' promise manager will be deprecated and disabled by default with the first 
[Node LTS](https://github.com/nodejs/Release#release-schedule) release that includes native 
support for async functions. This feature is currently available in Node 7.x, hidden behind 
the `--harmony_async_await` flag.

Instead of waiting for the LTS, you can disable the promise manager today either by setting 
an environment variable, `SELENIUM_PROMISE_MANAGER=0`, or through the promise module's API 
(`promise.USE_PROMISE_MANAGER = false`).

When the promise manager is disabled, any attempt to create a managed promise will generate 
an error, so expect your scripts to fail the first time you try running with the promise manager.

### Step 2: Migrate Direct Usages of Managed Promises

Search your code for every instance where you create a `promise.Promise` object, either 
using the constructor, or the `resolve`/`reject` factories. Replace these calls with the 
equivalent listed in the table below. These functions will automatically switch from 
managed to native promises when the promise manager is disabled.

| Original | Replacement |
| -------- | ----------- |
| `new promise.Promise()` | `promise.createPromise()` |
| `promise.Promise.resolve()` | `promise.fulfilled()` |
| `promise.Promise.reject()` | `promise.rejected()` |

### Step 3: Migrate Off of the Promise Manager

At this point, you should be ready to migrate off of the promise manager. Unfortunately, 
there's no automated way to update your code; you basically have to disable the promise 
manager, see what fails, update some code, and try again. To understand how your code will 
fail, consider a test for our Google Search example:

```js
const {Builder, By, until} = require('selenium-webdriver');
const test = require('selenium-webdriver/testing');

describe('Google Search', function() {
  let driver;

  test.before(function() {
    driver = new Builder().forBrowser('firefox').build();
  });

  test.it('example', function theTestFunction() {
    driver.get('http://www.google.com/ncr');                          // (1)
    driver.findElement(By.name('q')).sendKeys('webdriver');           // (2)
    driver.findElement(By.name('btnK')).click();                      // (3)
    driver.wait(until.titleIs('webdriver - Google Search'), 1000);    // (4)
  });

  test.after(function() {
    driver.quit();
  });
});
```

Inside `theTestFunction`, when the promise manager is enabled, it will capture every 
WebDriver command and block its execution until those before it have completed. Thus, even 
though `driver.findElement()` on line `(2)` is immediately _scheduled_, it will not start 
execution until the command on line `(1)` completes.

When you disable the promise manager, every command will start executing as soon as it's 
scheduled. The exact behavior depends on the specific browser, but essentially, the command 
to find an element on line `(2)` will start executing before the page requested on line 
`(1)` has loaded.

You will need to update your code to explicitly link each action so it does not start until 
previous commands have finished. Presented below are three options for how to update your code.

#### Option 1: Use classic promise chaining

Your first option is to adopt classic promise chaining (yes, the very thing the promise 
manager was created to avoid). This will make your code far more verbose, but it will work 
with and without the promise manager and you won't need to use the 
`selenium-webdriver/testing` module for mocha-based tests.

```js
const {Builder, By, until} = require('selenium-webdriver');
const test = require('selenium-webdriver/testing');

describe('Google Search', function() {
  let driver;

  before(function() {
    return new Builder().forBrowser('firefox').build().then(d => {
      driver = d;
    });
  });

  it('example', function theTestFunction() {
    return driver.get('http://www.google.com/ncr')
        .then(_ => driver.findElement(By.name('q')))
        .then(q => q.sendKeys('webdriver'))
        .then(_ => driver.findElement(By.name('btnK')))
        .then(b => b.click())
        .then(_ => driver.wait(until.titleIs('webdriver - Google Search'), 1000));
  });

  after(function() {
    return driver.quit();
  });
});
```

#### Option 2: Migrate to Generators

Your second option is to update your code to use asynchronous generator functions. The 
`selenium-webdriver/testing` module already handles these out of the box. You can also use 
third-party libraries like task.js for the same effect. Basically, change each of your test 
functions to a generator, and `yield` on a promise. The generator wrapper will transparently 
wait for the promise to resolve before resuming the function. It's important to __note__, 
however, asynchronous generators are not currently supported natively in JavaScript, so you 
_will_ have to use `selenium-webdriver` or another library for this to work.

```js
const {Builder, By, until} = require('selenium-webdriver');
const test = require('selenium-webdriver/testing');

describe('Google Search', function() {
  let driver;

  test.before(function*() {
    driver = yield new Builder().forBrowser('firefox').build();
  });

  test.it('example', function* theTestFunction() {
    yield driver.get('http://www.google.com/ncr');                        // (1)
    yield driver.findElement(By.name('q')).sendKeys('webdriver');         // (2)
    yield driver.findElement(By.name('btnK')).click();                    // (3)
    yield driver.wait(until.titleIs('webdriver - Google Search'), 1000);  // (4)
  });

  test.after(function*() {
    yield driver.quit();
  });
});
```

The advantage to using generators with `selenium-webdriver/testing` is your code will work 
with and without the promise manager, so you can convert one test at a time. Another 
advantage to this approach is your code will work today with Node 6 & 7. When async/await 
support is added to Node (it's currently hidden behind a flag in Node 7), you can migrate 
from generators with find-and-replace, converting `function*()` to `async function()` and 
`yield` to `await`.

The [`selenium-webdriver/example`](https://github.com/SeleniumHQ/selenium/blob/selenium-3.6.0/javascript/node/selenium-webdriver/example/google_search_test.js) 
directory contains an example of our Google Search test written with and without generators 
so you can compare the two side-by-side.

#### Option 3: Migrate to async/await

Your final option is to switch to async/await. As mentioned above, these language features 
are currently hidden behind a flag in Node 7, so you will have to run with 
`--harmony_async_await` _or_ you will have to transpile your code with 
[Babel](https://babeljs.io/) (setting up Babel is left as an exercise for the reader).

Compared to generators, there is one more catch to using async/await: they 
[do not play well with the promise manager](https://github.com/SeleniumHQ/selenium/issues/3037), 
so you must ensure the promise manager is disabled. There is a complete working example of a 
test written using async/await provided in the 
[`selenium-webdriver/example`](https://github.com/SeleniumHQ/selenium/blob/selenium-3.6.0/javascript/node/selenium-webdriver/example/async_await_test.js) 
directory:

```js
const {Builder, By, until} = require('selenium-webdriver');

promise.USE_PROMISE_MANAGER = false;

describe('Google Search', function() {
  let driver;

  beforeEach(async function() {
    driver = await new Builder().forBrowser('firefox').build();
  });

  afterEach(async function() {
    await driver.quit();
  });

  it('example', async function() {
    await driver.get('https://www.google.com/ncr');

    await driver.findElement(By.name('q')).sendKeys('webdriver');
    await driver.findElement(By.name('btnK')).click();

    await driver.wait(until.titleIs('webdriver - Google Search'), 1000);
  });
});
```

This example disables the promise manager globally. In order to migrate tests bit-by-bit, 
you can selectively disable the promise manager in before/after blocks:

```js
promise.USE_PROMISE_MANAGER = false;

function legacySuite(name, fn) {
  describe(name, function() {
    before(() => promise.USE_PROMISE_MANAGER = true);
    after(() => promise.USE_PROMISE_MANAGER = false);

    fn();
  });
}

describe('Example', function() {
  legacySuite('legacy tests', function() {
    test.it('test 1', function() {
      // ...
    });
  });

  it('test 2', async function() {
    // ...
  });
});
```

### Miscellaneous Tips

#### Use Logging To Find Unmigrated Code

While the promise manager can be easily toggled through an environment variable, constantly 
running your code in the two modes can be tedious. Selenium's promise module provides 
logging to report every instance of unsynchronized code running through the promise manager 
(which, in code, is actually called the `ControlFlow`). You can enable this logging, then 
work through the messages to update each instance of commands that have not been properly 
chained (depending on the option you chose above).

Enabling logging is only two extra lines:

```js
const {Builder, By, logging, until} = require('selenium-webdriver');

logging.installConsoleHandler();
logging.getLogger('promise.ControlFlow').setLevel(logging.Level.ALL);

let driver = new Builder()
    .forBrowser('firefox')
    .build();

driver.get('http://www.google.com/ncr');
driver.findElement(By.name('q')).sendKeys('webdriver');
driver.findElement(By.name('btnK')).click();
driver.wait(until.titleIs('webdriver - Google Search'), 1000);
driver.quit();
```

With logging, the example above will produce messages like:

```
[2017-03-06T02:18:14Z] [WARNING] [promise.ControlFlow] Detected scheduling of an unchained task.
    When the promise manager is disabled, unchained tasks will not wait for
    previously scheduled tasks to finish before starting to execute.
    New task: Task: WebDriver.navigate().to(http://www.google.com/ncr)
        at thenableWebDriverProxy.schedule (/path/to/node_modules/selenium-webdriver/lib/webdriver.js:816:17)
        at Navigation.to (/path/to/node_modules/selenium-webdriver/lib/webdriver.js:1140:25)
        at thenableWebDriverProxy.get (/path/to/node_modules/selenium-webdriver/lib/webdriver.js:997:28)
        at Object.<anonymous> (/path/to/test/log.js:10:8)
        ...
    Previous task: Task: WebDriver.createSession()
        at Function.createSession (/path/to/node_modules/selenium-webdriver/lib/webdriver.js:777:24)
        at Function.createSession (/path/to/node_modules/selenium-webdriver/firefox/index.js:667:55)
        at createDriver (/path/to/node_modules/selenium-webdriver/index.js:167:33)
        at Builder.build (/path/to/node_modules/selenium-webdriver/index.js:642:16)
        at Object.<anonymous> (/path/to/test/log.js:8:6)
        ...
```
