const {Browser, Builder} = require('selenium-webdriver');
const firefox = require('selenium-webdriver/firefox');


describe('Open Firefox', function () {
  let driver;

  before(async function () {
    let options = new firefox.Options();
    driver = new Builder()
      .forBrowser(Browser.FIREFOX)
      .setFirefoxOptions(options)
      .build();
  });

  after(async () => await driver.quit());

  it('Basic Firefox test', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });
});