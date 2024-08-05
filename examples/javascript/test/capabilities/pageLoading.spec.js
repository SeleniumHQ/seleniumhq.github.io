const Chrome = require('selenium-webdriver/chrome');
const {Browser, Builder} = require("selenium-webdriver");
const options = new Chrome.Options()


describe('Page loading strategies', function () {
  it('Navigate using eager page loading strategy', async function () {
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.setPageLoadStrategy('eager'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Navigate using none page loading strategy', async function () {
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.setPageLoadStrategy('none'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Navigate using normal page loading strategy', async function () {
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.setPageLoadStrategy('normal'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Should be able to accept certs', async function () {
    let driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options.setAcceptInsecureCerts(true))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });
});