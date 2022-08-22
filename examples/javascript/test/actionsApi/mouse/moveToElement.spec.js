const {By} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");

suite(function(env) {
  describe('Move to element', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(async () => await driver.quit());

    it('Mouse move into an element', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');
      const hoverable = driver.findElement(By.id("hover"));
      const actions = driver.actions({ async: true });
      await actions.move({ origin: hoverable }).perform();

      const status = await driver.findElement(By.id('move-status')).getText();
      assert.deepStrictEqual(status, `hovered`)
    });
  });
});