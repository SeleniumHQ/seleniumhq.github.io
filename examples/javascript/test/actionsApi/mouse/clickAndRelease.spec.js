const {By,Builder} = require('selenium-webdriver');

describe('Click and release', function () {
  let driver;

  before(async function () {
    driver = new Builder().forBrowser('chrome').build();
  });

  after(() => driver.quit());

  it('Mouse move and click on an element', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');
    let click = driver.findElement(By.id("click"));
    const actions = driver.actions({async: true});
    await actions.move({origin: click}).click().perform();
  });
});
