const {Browser} = require('selenium-webdriver');
const Firefox = require('selenium-webdriver/firefox');
const options = new Firefox.Options();
const path = require('path');
const {suite} = require("selenium-webdriver/testing");


suite(function (env) {
describe('Should be able to Test Command line arguments', function () {
  it('headless', async function () {
    let driver = await env.builder()
      .setFirefoxOptions(options.addArguments('--headless'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });

  it('Should be able to add extension', async function () {

    const xpiPath = path.resolve('./test/resources/extensions/selenium-example.xpi')
    let driver = await env.builder()
      .setFirefoxOptions(options.addExtensions(xpiPath))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });
});
}, { browsers: [Browser.FIREFOX]});