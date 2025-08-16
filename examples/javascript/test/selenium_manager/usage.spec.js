const Chrome = require('selenium-webdriver/chrome');
const {Browser, Builder} = require("selenium-webdriver");
const options = new Chrome.Options();

describe('Usage Test', function () {
  it('Creates driver wit Selenium Manager', async function () {

    let driver = new Builder()
        .forBrowser(Browser.CHROME)
        .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  // it('Creates driver with Selenium Manager', async function () {
  //   let driverPath = '/path/to/chromedriver';
  //   let browserPath = '/path/to/chrome';

  //   options.setChromeBinaryPath(browserPath)

  //   let service = new Chrome.ServiceBuilder().setPath(driverPath)

  //   let driver = new Builder()
  //       .forBrowser(Browser.CHROME)
  //       .setChromeService(service)
  //       .build();

  //   await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  //   await driver.quit();
  // });
});
