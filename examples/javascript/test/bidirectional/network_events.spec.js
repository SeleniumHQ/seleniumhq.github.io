const assert = require("assert");
const firefox = require('selenium-webdriver/firefox');
const { Network } = require("selenium-webdriver/bidi/network");
const {until, Builder} = require("selenium-webdriver");


describe('Network events', function () {
  let driver


  beforeEach(async function () {
    driver = new Builder()
      .forBrowser('firefox')
      .setFirefoxOptions(new firefox.Options().enableBidi())
      .build()
  })

  afterEach(async function () {
    await driver.quit()
  })

  it('can listen to event before request is sent', async function () {
    let beforeRequestEvent = []
    const network = await Network(driver)
    await network.beforeRequestSent(function (event) {
      beforeRequestEvent.push(event)
    })

    await driver.get('https://www.selenium.dev/selenium/web/blank.html')

    const currentUrl = await driver.getCurrentUrl()
    const currentUrlFound = beforeRequestEvent.some(event => event.request.url.includes(currentUrl))
    assert(currentUrlFound, `${currentUrl} was not requested`)
  })

  it('can request cookies', async function () {
    const network = await Network(driver)
    let beforeRequestEvent = null
    await network.beforeRequestSent(function (event) {
      beforeRequestEvent = event
    })

    await driver.get('https://www.selenium.dev/selenium/web/blank.html')
    await driver.manage().addCookie({
      name: 'north',
      value: 'biryani',
    })
    await driver.navigate().refresh()

    assert.equal(beforeRequestEvent.request.method, 'GET')
    assert.equal(beforeRequestEvent.request.cookies[0].name, 'north')
    assert.equal(beforeRequestEvent.request.cookies[0].value.value, 'biryani')

    await driver.manage().addCookie({
      name: 'south',
      value: 'dosa',
    })
    await driver.navigate().refresh()

    assert.equal(beforeRequestEvent.request.cookies[1].name, 'south')
    assert.equal(beforeRequestEvent.request.cookies[1].value.value, 'dosa')
  })

  it('can redirect http equiv', async function () {
    let beforeRequestEvent = []
    const network = await Network(driver)
    await network.beforeRequestSent(function (event) {
      beforeRequestEvent.push(event)
    })

    await driver.get('https://www.selenium.dev/selenium/web/bidi/redirected_http_equiv.html')
    await driver.wait(until.urlContains('redirected.html'), 1000)

    assert.equal(beforeRequestEvent[0].request.method, 'GET')
    let redirectedFound = beforeRequestEvent.some(event => event.request.url.includes('redirected.html'))
    assert(redirectedFound, 'redirected.html was not requested')
    redirectedFound = beforeRequestEvent.some(event => event.request.url.includes('redirected_http_equiv.html'))
    assert(redirectedFound, 'redirected_http_equiv.html was not requested')
  })

  it('can subscribe to response started', async function () {
    let onResponseStarted = []
    const network = await Network(driver)
    await network.responseStarted(function (event) {
      onResponseStarted.push(event)
    })

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    assert.equal(onResponseStarted[0].request.method, 'GET')
    assert.equal(onResponseStarted[0].request.url, await driver.getCurrentUrl())
    assert.equal(onResponseStarted[0].response.url, await driver.getCurrentUrl())
  })

  it('can subscribe to response completed', async function () {
    let onResponseCompleted = []
    const network = await Network(driver)
    await network.responseCompleted(function (event) {
      onResponseCompleted.push(event)
    })

    await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    assert.equal(onResponseCompleted[0].request.method, 'GET')
    assert.equal(onResponseCompleted[0].request.url, await driver.getCurrentUrl())
    assert.equal(onResponseCompleted[0].response.fromCache, false)
    assert.equal(onResponseCompleted[0].response.status, 200)
  })
})
