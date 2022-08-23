const {By} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');
const assert = require('assert');

suite(function(env) {
  describe('Right click', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(async () => await driver.quit());

    it('Mouse move and right click on an element', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');
      const mouseTracker = driver.findElement(By.id("mouse-tracker"));
      const actions = driver.actions({async: true});
      await actions.move({x:8, y:0, origin: mouseTracker}).perform();

      await driver.sleep(500);
      let result = await driver.findElement(By.id('relative-location')).getText();
      result = result.split(', ');
      assert.deepStrictEqual((Math.abs(result[0] -100 -8)<2), true)
    });
  });
});