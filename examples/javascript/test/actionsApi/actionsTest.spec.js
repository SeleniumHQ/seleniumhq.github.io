const { By, Key } = require('selenium-webdriver')
const { suite } = require('selenium-webdriver/testing')
const assert = require('assert')

suite(function(env) {
  describe('Actions API - Pause and Release All Actions', function() {
    let driver

    before(async function() {
      driver = await env.builder().build()
    })

    after(() => driver.quit())

    it('Pause', async function() {
      await driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

      const start = Date.now()

      const clickable = await driver.findElement(By.id('clickable'))
      await driver.actions()
        .move({ origin: clickable })
        .pause(1000)
        .press()
        .pause(1000)
        .sendKeys('abc')
        .perform()

      const end = Date.now() - start
      assert.ok(end > 2000)
      assert.ok(end < 4000)
    })

    it('Clear', async function() {
      await driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

      const clickable = driver.findElement(By.id('clickable'))
      await driver.actions()
        .click(clickable)
        .keyDown(Key.SHIFT)
        .sendKeys('a')
        .perform()

      await driver.actions().clear()
      await driver.actions().sendKeys('a').perform()

      const value = await clickable.getAttribute('value')
      assert.deepStrictEqual('A', value.substring(0, 1))
      assert.deepStrictEqual('a', value.substring(1, 2))
    })
  })
})
