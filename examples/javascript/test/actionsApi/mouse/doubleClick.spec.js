const {By, Builder} = require('selenium-webdriver');

const assert = require("assert");


  describe('Double click', function () {
    let driver;

    before(async function () {
      driver = new Builder().forBrowser('chrome').build();
    });

    after(async () => await driver.quit());

    it('Double-click on an element', async function () {
      await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');
      const clickable = driver.findElement(By.id("clickable"));
      const actions = driver.actions({async: true});
      await actions.doubleClick(clickable).perform();

      await driver.sleep(500);
      const status = await driver.findElement(By.id('click-status')).getText();
      assert.deepStrictEqual(status, `double-clicked`)
    });
  });
