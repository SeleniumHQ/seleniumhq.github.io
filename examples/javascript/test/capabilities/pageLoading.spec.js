const Chrome = require('selenium-webdriver/chrome');
const {suite} = require('selenium-webdriver/testing');
const {Browser} = require("selenium-webdriver");
const options = new Chrome.Options()

suite(function (env) {
  describe('Page loading strategies', function () {
    it('Navigate using eager page loading strategy', async function () {
      let driver = await env
        .builder()
        .setChromeOptions(options.setPageLoadStrategy('eager'))
        .build();

      await driver.get('https://www.selenium.dev/selenium/web/blank.html');
      await driver.quit();
    });

    it('Navigate using none page loading strategy', async function () {
      let driver = await env
        .builder()
        .setChromeOptions(options.setPageLoadStrategy('none'))
        .build();

      await driver.get('https://www.selenium.dev/selenium/web/blank.html');
      await driver.quit();
    });

    it('Navigate using normal page loading strategy', async function () {
      let driver = await env
        .builder()
        .setChromeOptions(options.setPageLoadStrategy('normal'))
        .build();

      await driver.get('https://www.selenium.dev/selenium/web/blank.html');
      await driver.quit();
    });
  });
}, { browsers: [Browser.CHROME, Browser.FIREFOX]});