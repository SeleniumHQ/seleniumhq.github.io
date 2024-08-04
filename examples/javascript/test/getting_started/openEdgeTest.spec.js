const {Browser, Builder} = require('selenium-webdriver');
const edge = require('selenium-webdriver/edge');


describe('Open Edge', function () {
  let driver;

  before(async function () {
    let options = new edge.Options();
    driver = new Builder()
      .forBrowser(Browser.EDGE)
      .setEdgeOptions(options)
      .build();
  });

  after(async () => await driver.quit());

  it('Basic Edge test', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });
});
