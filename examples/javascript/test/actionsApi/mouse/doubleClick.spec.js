const { By } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');
const assert = require("assert");

suite(function(env) {
  describe('Double click', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(async() => await driver.quit());

    it('Double-click on an element', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');
      const clickable = driver.findElement(By.id("clickable"));
      const actions = driver.actions({ async: true });
      await actions.doubleClick(clickable).perform();

      await driver.sleep(500);
      const status = await driver.findElement(By.id('click-status')).getText();
      assert.deepStrictEqual(status, `double-clicked`)
    });
  });
});