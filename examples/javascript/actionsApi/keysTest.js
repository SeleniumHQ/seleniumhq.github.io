
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
  });
});

