const { Builder } = require('selenium-webdriver');

describe('Emulate geolocation', function() {
  let driver;

  before(async function() {
    driver = new Builder().forBrowser('chrome').build();
  });

  after(async () => await driver.quit());

  it('Emulate coordinates of Tokyo', async function() {
    const cdpConnection = await driver.createCDPConnection('page');

    // Latitude and longitude of Tokyo, Japan
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
  });
});