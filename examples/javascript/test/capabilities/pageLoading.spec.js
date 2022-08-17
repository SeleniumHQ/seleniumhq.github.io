const { Capabilities } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');

suite(function(env) {
    describe('Page loading strategies', function() {
        it('Navigate using eager page loading strategy', async function() {
            let caps = new Capabilities();
            caps.setPageLoadStrategy("eager");
            let driver = await env.builder().withCapabilities(caps).build();

            await driver.get('https://www.google.com');

            driver.quit();
        });

        it('Navigate using none page loading strategy', async function() {
            let caps = new Capabilities();
            caps.setPageLoadStrategy("none");
            let driver = await env.builder().withCapabilities(caps).build();

            await driver.get('https://www.google.com');

            driver.quit();
        });

        it('Navigate using normal page loading strategy', async function() {
            let caps = new Capabilities();
            caps.setPageLoadStrategy("normal");
            let driver = await env.builder().withCapabilities(caps).build();

            await driver.get('https://www.google.com');

            driver.quit();
        });

    });
});