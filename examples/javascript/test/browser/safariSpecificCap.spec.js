const safari = require('selenium-webdriver/safari');
const {Browser} = require("selenium-webdriver");
const { suite } = require('selenium-webdriver/testing')
const options = new safari.Options();
const process  =  require('node:process');

suite(function(env) {
  describe('Should be able to Test Command line arguments', function () {
    (process.platform === 'darwin' ? it : it.skip)('headless', async function () {
      let driver = await env.builder()
      .setSafariOptions(options)
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
    });
  });
}, { browsers: [Browser.SAFARI]});