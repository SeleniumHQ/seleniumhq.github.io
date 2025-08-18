const fs = require('fs');
const os = require('os');
const path = require('path');
const Chrome = require('selenium-webdriver/chrome');
const {Browser, Builder} = require("selenium-webdriver");
const {getBinaryPaths} = require("selenium-webdriver/common/driverFinder");

describe('Service Test', function () {
  let driver;
  let userDataDir;
  let service;
  let options;

  afterEach(async function () {
    if (driver) {
      await driver.quit();
      driver = null;
    }
    if (userDataDir) {
      fs.rmSync(userDataDir, { recursive: true, force: true });
      userDataDir = null;
    }
  });

  it('Default service', async function () {
    service = new Chrome.ServiceBuilder();
    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeService(service)
      .build();
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });

  it('Set Driver Location', async function () {
    options = new Chrome.Options();
    options.setBrowserVersion("stable");
    let paths = getBinaryPaths(options);
    let driverPath = paths.driverPath;
    let browserPath = paths.browserPath;
    options.setChromeBinaryPath(browserPath);
    userDataDir = fs.mkdtempSync(path.join(os.tmpdir(), 'chrome-profile-'));
    options.addArguments(`--user-data-dir=${userDataDir}`);
    options.addArguments('--no-sandbox');
    options.addArguments('--disable-dev-shm-usage');
    service = new Chrome.ServiceBuilder(driverPath);
    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeOptions(options)
      .setChromeService(service)
      .build();
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });

  it('Set port', async function () {
    service = new Chrome.ServiceBuilder().setPort(1234);
    driver = new Builder()
      .forBrowser(Browser.CHROME)
      .setChromeService(service)
      .build();
    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
  });
});
