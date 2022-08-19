const { By, Key } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');
const assert = require("assert");

suite(function(env) {
    describe('Emulate geolocation', function() {
        let driver;

        before(async function() {
            driver = await env.builder().build();
        });

        after(() => driver.quit());

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
});