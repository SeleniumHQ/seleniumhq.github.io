const {Builder} = require('selenium-webdriver');

(async function openChromeTest() {
  try {
    let driver = await new Builder().forBrowser('chrome').build();
    
    const cdpConnection = await driver.createCDPConnection('page');
    //Latitude and longitude of Tokyo, Japan
    const coordinates = {
      latitude: 35.689487,
      longitude: 139.691706,
      accuracy: 100,
    };
    await cdpConnection.execute(
      "Emulation.setGeolocationOverride",
      coordinates
    );
    await driver.get("https://kawasaki-india.com/dealer-locator/");
    await driver.quit();
  } catch (error) {
    console.log(error)
  }
})();