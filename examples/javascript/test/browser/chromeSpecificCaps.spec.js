const Chrome = require('selenium-webdriver/chrome');
const {suite} = require('selenium-webdriver/testing');
const options = new Chrome.Options();

suite(function (env) {
  describe('Should be able to Test Command line arguments', function () {
    it('headless', async function () {
      let driver = await env
        .builder()
        .setChromeOptions(options.addArguments('--headless=new'))
        .build();

      await driver.get('https://www.google.com');
      await driver.quit();
    });

    it('exclude switches', async function () {
      let driver = await env
        .builder()
        .setChromeOptions(options.excludeSwitches('enable-automation'))
        .build();

      await driver.get('https://www.google.com');
      await driver.quit();
    });

    it('Keep browser open - set detach to true ', async function () {
      let driver = await env
        .builder()
        .setChromeOptions(options.detachDriver(true))
        .build();

      await driver.get('https://www.google.com');

      // As tests runs in ci, quitting the driver instance to avoid any failures
      await driver.quit();
    });

    xit('Start browser from specified location ', async function () {
      let driver = await env
        .builder()
        .setChromeOptions(options.setChromeBinaryPath(`Path to chrome binary`))
        .build();

      await driver.get('https://www.google.com');
      await driver.quit();
    });
  });
});