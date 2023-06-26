const {Builder, Browser} = require('selenium-webdriver');
const Firefox = require('selenium-webdriver/firefox');
const options = new Firefox.Options();

describe('Should be able to Test Command line arguments', function () {
  it('headless', async function () {
    let driver = new Builder()
      .setFirefoxOptions(options.addArguments('--headless'))
      .build();

    await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    await driver.quit();
  });
}, { browsers: [Browser.FIREFOX]});