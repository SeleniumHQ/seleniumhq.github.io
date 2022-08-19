const { Builder } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');
const edgedriver = require('@sitespeed.io/edgedriver');
const edge = require('selenium-webdriver/edge');

suite(function(env) {
    describe('Open Edge', function() {
        let driver;

        before(async function() {
            let options = new edge.Options();
            driver = await new Builder()
                .setEdgeOptions(options)
                .forBrowser('MicrosoftEdge')
                .setEdgeService(new edge.ServiceBuilder(edgedriver.binPath()))
                .build();
        });

        after(() => driver.quit());

        it('Basic Edge test', async function() {
            await driver.get('https://www.google.com');
        });

    });
});