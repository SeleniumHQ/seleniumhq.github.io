const {Browser} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');
const edge = require('selenium-webdriver/edge');
const options = new edge.Options();

suite(function (env) {
  describe('Should be able to Test Command line arguments', function () {
    it('headless', async function () {
      let driver = await env
        .builder()
        .setEdgeOptions(options.addArguments('--headless=new'))
        .build();

      await driver.get('https://www.selenium.dev/selenium/web/blank.html');
      await driver.quit();
    });

    it('exclude switches', async function () {
      let driver = await env
        .builder()
        .setEdgeOptions(options.excludeSwitches('enable-automation'))
        .build();

      await driver.get('https://www.selenium.dev/selenium/web/blank.html');
      await driver.quit();
    });

    it('Keep browser open - set detach to true ', async function () {
      let driver = await env
        .builder()
        .setEdgeOptions(options.detachDriver(true))
        .build();

      await driver.get('https://www.selenium.dev/selenium/web/blank.html');

      // As tests runs in ci, quitting the driver instance to avoid any failures
      await driver.quit();
    });

    it('Basic edge test', async function () {
      const Options = new edge.Options();
      let driver = await env
        .builder()
        .setEdgeOptions(Options)
        .build();

      await driver.get('https://www.selenium.dev/selenium/web/blank.html');
      await driver.quit();
    });
  });
}, { browsers: [Browser.EDGE]});