const {Browser} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');
const edge = require('selenium-webdriver/edge');

suite(function (env) {
  describe('Open Edge', function () {
    let driver;

    before(async function () {
      let options = new edge.Options();
      driver = await env.builder()
        .setEdgeOptions(options)
        .build();
    });

    after(async () => await driver.quit());

    it('Basic Edge test', async function () {
      await driver.get('https://www.selenium.dev/selenium/web/blank.html');
    });
  });
}, { browsers: [Browser.EDGE]});
