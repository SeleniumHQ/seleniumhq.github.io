const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();

// Set page load strategy to eager
caps.setPageLoadStrategy("eager");
(async function example() {
    let driver = await new Builder().
    withCapabilities(caps).
    forBrowser('chrome').
    build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
    } finally {
        await driver.quit();
    }
})();