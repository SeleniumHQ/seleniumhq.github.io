const {Browser} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');
const firefox = require('selenium-webdriver/firefox');

suite(function (env) {
  describe('Open Firefox', function () {
    let driver;

    before(async function () {
      let options = new firefox.Options();
      driver = await env.builder()
        .setFirefoxOptions(options)
        .build();
    });

    after(async () => await driver.quit());

    it('Basic Firefox test', async function () {
      await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    });
  });
}, { browsers: [Browser.FIREFOX]});