
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

describe('BiDi Script', function () {

  it('can listen to dom mutations', async function () {
    let message = null
    await driver.script().addDomMutationHandler((m) => {
      message = m
    })

    await driver.get('https://www.selenium.dev/selenium/web/dynamic')

    let element = driver.findElement({ id: 'reveal' })
    await element.click()
    let revealed = driver.findElement({ id: 'revealed' })
    await driver.wait(until.elementIsVisible(revealed), 5000)

    assert.strictEqual(message['attribute_name'], 'style')
    assert.strictEqual(message['current_value'], '')
    assert.strictEqual(message['old_value'], 'display:none;')
  })

  it('can remove to dom mutation handler', async function () {
    let message = null
    let id = await driver.script().addDomMutationHandler((m) => {
      message = m
    })

    await driver.script().removeDomMutationHandler(id)

    await driver.get('https://www.selenium.dev/selenium/web/dynamic')

    let element = driver.findElement({ id: 'reveal' })
    await element.click()
    let revealed = driver.findElement({ id: 'revealed' })
    await driver.wait(until.elementIsVisible(revealed), 5000)

    assert.strictEqual(message, null)
  })

  it('can pin script', async function () {
    await driver.script().pin("() => { console.log('Hello!'); }")
    let log

    await driver.script().addConsoleMessageHandler((logEntry) => {
      log = logEntry
    })

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    await delay(3000)

    assert.equal(log.text, 'Hello!')
  })

  it('can unpin script', async function () {
    const id = await driver.script().pin("() => { console.log('Hello!'); }")

    let count = 0
    await driver.script().addConsoleMessageHandler((logEntry) => {
      count++
    })

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    await driver.script().unpin(id)

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    assert.equal(count, 1)
  })
})


