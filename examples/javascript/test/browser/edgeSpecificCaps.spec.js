const {Browser, By, Builder } = require('selenium-webdriver');
const edge = require('selenium-webdriver/edge');
const options = new edge.Options();
const assert = require("assert");


describe('Should be able to Test Command line arguments', function () {
  it('headless', async function () {
    let driver = new Builder()
      .forBrowser(Browser.EDGE)
      .setEdgeOptions(options.addArguments('--headless=new'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('exclude switches', async function () {
    let driver = new Builder()
      .forBrowser(Browser.EDGE)
      .setEdgeOptions(options.excludeSwitches('enable-automation'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Keep browser open - set detach to true ', async function () {
    let driver = new Builder()
      .forBrowser(Browser.EDGE)
      .setEdgeOptions(options.detachDriver(true))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');

    // As tests runs in ci, quitting the driver instance to avoid any failures
    await driver.quit();
  });

  it('Basic edge test', async function () {
    const Options = new edge.Options();
    let driver = new Builder()
      .forBrowser(Browser.EDGE)
      .setEdgeOptions(Options)
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Add Extension', async function () {
    let driver = new Builder()
      .forBrowser(Browser.EDGE)
      .setEdgeOptions(options.addExtensions(['./test/resources/extensions/webextensions-selenium-example.crx']))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    let injected = await driver.findElement(By.id('webextensions-selenium-example'));
    assert.equal(await injected.getText(), `Content injected by webextensions-selenium-example`)
    await driver.quit();
  });
});