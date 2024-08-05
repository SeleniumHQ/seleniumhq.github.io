const Chrome = require('selenium-webdriver/chrome');
const { Browser, Builder } = require("selenium-webdriver");
const options = new Chrome.Options();

describe('Should be able to Test Command line arguments', function () {
  it('headless', async function () {
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.addArguments('--headless=new'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('exclude switches', async function () {
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.excludeSwitches('enable-automation'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Keep browser open - set detach to true ', async function () {
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.detachDriver(true))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');

    // As tests runs in ci, quitting the driver instance to avoid any failures
    await driver.quit();
  });

  xit('Start browser from specified location ', async function () {
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.setChromeBinaryPath(`Path to chrome binary`))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Basic Chrome test', async function () {
    const Options = new Chrome.Options();
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(Options)
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Add Extension', async function () {
    const options = new Chrome.Options();
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.addExtensions(['./test/resources/extensions/webextensions-selenium-example.crx']))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });
});