const Chrome = require('selenium-webdriver/chrome');
const {Browser, Builder} = require("selenium-webdriver");
const {getBinaryPaths} = require("selenium-webdriver/common/driverFinder");
const options = new Chrome.Options();

describe('Service Test', function () {
  it('Default service', async function () {
    let service = new Chrome.ServiceBuilder()

    let driver = new Builder()
        .forBrowser(Browser.CHROME)
        .setChromeService(service)
        .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Set Driver Location', async function () {

    let options = new Chrome.Options();
    options.setBrowserVersion("stable")

    let paths = getBinaryPaths(options)
    let driverPath = paths.driverPath;
    let browserPath = paths.browserPath;

    options.setChromeBinaryPath(browserPath)

    let service = new Chrome.ServiceBuilder().setPath(driverPath)

    let driver = new Builder()
        .forBrowser(Browser.CHROME)
        .setChromeOptions(options)
        .setChromeService(service)
        .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Set port', async function () {
    let service = new Chrome.ServiceBuilder().setPort(1234)

    let driver = new Builder()
        .forBrowser(Browser.CHROME)
        .setChromeService(service)
        .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });
});