const { By, Key } = require('selenium-webdriver')
const { suite } = require('selenium-webdriver/testing')
const assert = require('assert')
const { platform } = require('node:process')

suite(function(env) {
  describe('Keyboard Action - Keys test', function() {
    let driver

    before(async function() {
      driver = await env.builder().build()
    })

    after(() => driver.quit())

    it('KeyDown', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html')

      await driver.actions()
        .keyDown(Key.SHIFT)
        .sendKeys('a')
        .perform()

      const textField = driver.findElement(By.id('textInput'))
      assert.deepStrictEqual(await textField.getAttribute('value'), 'A')
    })

    it('KeyUp', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html')

      const textField = driver.findElement(By.id('textInput'))
      await textField.click()

      await driver.actions()
        .keyDown(Key.SHIFT)
        .sendKeys('a')
        .keyUp(Key.SHIFT)
        .sendKeys('b')
        .perform()

      assert.deepStrictEqual(await textField.getAttribute('value'), 'Ab')
    })

    it('sendKeys', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html')

      const textField = driver.findElement(By.id('textInput'))
      await textField.click()

      await driver.actions()
        .sendKeys('abc')
        .perform()

      assert.deepStrictEqual(await textField.getAttribute('value'), 'abc')
    })

    it.skip('Designated Element', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html')

      await driver.findElement(By.css('body')).click()
      const textField = await driver.findElement(By.id('textInput'))

      await driver.actions()
        .sendKeys(textField, 'abc')
        .perform()

      assert.deepStrictEqual(await textField.getAttribute('value'), 'abc')
    })

    it('Copy and Paste', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html')

      const textField = await driver.findElement(By.id('textInput'))

      const cmdCtrl = platform.includes('darwin') ? Key.COMMAND : Key.CONTROL

      await driver.actions()
        .click(textField)
        .sendKeys('Selenium!')
        .sendKeys(Key.ARROW_LEFT)
        .keyDown(Key.SHIFT)
        .sendKeys(Key.ARROW_UP)
        .keyUp(Key.SHIFT)
        .keyDown(cmdCtrl)
        .sendKeys('xvv')
        .keyUp(cmdCtrl)
        .perform()

      assert.deepStrictEqual(await textField.getAttribute('value'), 'SeleniumSelenium!')
    })
  })
})
