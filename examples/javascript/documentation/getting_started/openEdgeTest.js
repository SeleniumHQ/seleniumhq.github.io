const {Builder} = require('selenium-webdriver');
const edge = require('selenium-webdriver/edge');

(async function openEdgeTest() {
  try {
    let options = new edge.Options();
    let driver = await new Builder()
                .setChromeOptions(options)
                .forBrowser('edge')
                .build();
    await driver.get('https://www.google.com');
    await driver.quit();
  } catch (error) {
    console.log(error)
  }
})();