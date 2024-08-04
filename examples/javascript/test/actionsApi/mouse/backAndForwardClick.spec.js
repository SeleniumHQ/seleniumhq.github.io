const {By, Button, Browser, Builder} = require('selenium-webdriver');
const assert = require('assert');

describe('Should be able to perform BACK click and FORWARD click', function () {
  let driver;

  before(async function () {
    driver = new Builder().forBrowser('chrome').build();
  });

  after(async () => await driver.quit());

  it('Back click', async function () {
    await driver.get('https://selenium.dev/selenium/web/mouse_interaction.html');
    await driver.findElement(By.id("click")).click();

    assert.deepStrictEqual(await driver.getTitle(), `We Arrive Here`)

    const actions = driver.actions({async: true});
    await actions.press(Button.BACK).release(Button.BACK).perform()

    assert.deepStrictEqual(await driver.getTitle(), `BasicMouseInterfaceTest`)
  });

  it('Forward click', async function () {
    await driver.get('https://selenium.dev/selenium/web/mouse_interaction.html');
    await driver.findElement(By.id("click")).click();
    await driver.navigate().back();

    assert.deepStrictEqual(await driver.getTitle(), `BasicMouseInterfaceTest`)

    const actions = driver.actions({async: true});
    await actions.press(Button.FORWARD).release(Button.FORWARD).perform()

    assert.deepStrictEqual(await driver.getTitle(), `We Arrive Here`)
  });
});
