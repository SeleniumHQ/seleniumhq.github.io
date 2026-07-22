const {By, Builder} = require('selenium-webdriver');

describe('Click and hold', function () {
  let driver;

  before(async function () {
    driver = new Builder().forBrowser('chrome').build();
  });

  after(async () => await driver.quit());

  it('Mouse move and mouseDown on an element', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');
    let clickable = driver.findElement(By.id("clickable"));
    const actions = driver.actions({async: true});
    await actions.move({origin: clickable}).press().perform();
  });
});
