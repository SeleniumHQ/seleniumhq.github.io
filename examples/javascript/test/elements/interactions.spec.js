const {suite} = require('selenium-webdriver/testing');
const {By, Browser} = require('selenium-webdriver');
const assert = require("node:assert");

suite(function (env) {
  describe('Element Interactions', function () {
    let driver;

    before(async function () {
      driver = await env.builder().build();
    });

    after(async () => await driver.quit());

    it('should Clear input and send keys into input field', async function () {

      try {
        await driver.get('https://www.selenium.dev/selenium/web/inputs.html');
        let inputField = await driver.findElement(By.name('no_type'));
        await inputField.clear();
        await inputField.sendKeys('Selenium');
        assert.strictEqual(await inputField.getText(), "Selenium");
      } catch (e) {
        console.log(e)
      }
    });
  });
}, { browsers: [Browser.CHROME, Browser.FIREFOX]});