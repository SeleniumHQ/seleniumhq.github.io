const {By} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');
const assert = require('assert');

suite(function(env) {
  describe('Drag and Drop', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(async () => await driver.quit());

    it('By Offset', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');
      const draggable = driver.findElement(By.id("draggable"));
      let start = await draggable.getRect();
      let finish = await driver.findElement(By.id("droppable")).getRect();
      const actions = driver.actions({async: true});
      await actions.dragAndDrop(draggable, {x: finish.x - start.x, y: finish.y-start.y}).perform();

      let result = await driver.findElement(By.id("drop-status")).getText();
      assert.deepStrictEqual('dropped', result)
    });

    it('Onto Element', async function() {
      await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');
      const draggable = driver.findElement(By.id("draggable"));
      const droppable = await driver.findElement(By.id("droppable"));
      const actions = driver.actions({async: true});
      await actions.dragAndDrop(draggable, droppable).perform();

      let result = await driver.findElement(By.id("drop-status")).getText();
      assert.deepStrictEqual('dropped', result)
    });
  });
});