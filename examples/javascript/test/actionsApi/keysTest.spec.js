const {By, Key} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");

suite(function(env) {
  describe('Keyboard Action - Keys test', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(() => driver.quit());

    it('KeyDown', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html');

      await driver.actions()
        .keyDown(Key.SHIFT)
        .sendKeys('a')
        .perform();

      const textField = driver.findElement(By.id("textInput"));
      assert.deepStrictEqual('A', await textField.getAttribute('value'))
    });

    it('KeyUp', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html');

      const textField = driver.findElement(By.id("textInput"));
      await textField.click();

      await driver.actions()
        .keyDown(Key.SHIFT)
        .sendKeys('a')
        .keyUp(Key.SHIFT)
        .sendKeys("b")
        .perform();

      assert.deepStrictEqual('Ab', await textField.getAttribute('value'))
    });

    it('sendKeys', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html');

      const textField = driver.findElement(By.id("textInput"));
      await textField.click();

      await driver.actions()
        .sendKeys('abc')
        .perform();

      assert.deepStrictEqual('abc', await textField.getAttribute('value'))
    });

    it.skip('Designated Element', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/single_text_input.html');
      await driver.findElement(By.tagName("body")).click();

      const textField = await driver.findElement(By.id("textInput"));
      await driver.actions()
        .sendKeys(textField, 'Selenium!')
        .perform();

      assert.deepStrictEqual('Selenium!', await textField.getAttribute('value'))
    });
  });
});

