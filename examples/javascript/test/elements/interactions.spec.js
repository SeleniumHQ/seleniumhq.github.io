
const {By, Browser, Builder} = require('selenium-webdriver');
const assert = require("node:assert");


describe('Element Interactions', function () {
  let driver;

  before(async function () {
    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .build();
  });

  after(async () => await driver.quit());

  it('should Clear input and send keys into input field', async function () {

    try {
      await driver.get('https://www.selenium.dev/selenium/web/inputs.html');
      let inputField = await driver.findElement(By.name('no_type'));
      await inputField.clear();
      await inputField.sendKeys('Selenium');
      const text = await inputField.getAttribute('value');
      assert.strictEqual(text, "Selenium");
    } catch (e) {
      console.log(e)
    }
  });
});