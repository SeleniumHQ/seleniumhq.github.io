
const assert = require("assert");
const firefox = require('selenium-webdriver/firefox');
const {until, Builder} = require("selenium-webdriver");

let driver

beforeEach(async function () {
  driver = new Builder()
    .setFirefoxOptions(new firefox.Options().enableBidi())
    .build()
})

afterEach(async function () {
  await driver.quit()
})

function delay(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms))
}

describe('BiDi Logging', function () {
  it('can listen to console log', async function () {
    let log = null
    const handler = await driver.script().addConsoleMessageHandler((logEntry) => {
      log = logEntry
    })

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    await driver.findElement({ id: 'consoleLog' }).click()

    await delay(3000)

    assert.equal(log.text, 'Hello, world!')
    await driver.script().removeConsoleMessageHandler(handler)
  })

  it('can remove console log handler', async function () {
    let log = null
    const handler = await driver.script().addConsoleMessageHandler((logEntry) => {
      log = logEntry
    })

    await driver.script().removeConsoleMessageHandler(handler)

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    await driver.findElement({ id: 'consoleLog' }).click()

    await delay(3000)

    assert.equal(log, null)
  })

  it('can listen to javascript error', async function () {
    let log = null
    const handler = await driver.script().addJavaScriptErrorHandler((logEntry) => {
      log = logEntry
    })

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    await driver.findElement({ id: 'jsException' }).click()

    await delay(3000)

    assert.equal(log.text, 'Error: Not working')
    assert.equal(log.type, 'javascript')
    assert.equal(log.level, 'error')

    await driver.script().removeJavaScriptErrorHandler(handler)
  })

  it('can remove to javascript error handler', async function () {
    let log = null
    const handler = await driver.script().addJavaScriptErrorHandler((logEntry) => {
      log = logEntry
    })

    await driver.script().removeJavaScriptErrorHandler(handler)

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    await driver.findElement({ id: 'jsException' }).click()

    await delay(3000)

    assert.equal(log, null)
  })
})


