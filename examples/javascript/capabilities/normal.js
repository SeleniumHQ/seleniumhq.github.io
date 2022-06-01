const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();

// Set page load strategy to Normal
caps.setPageLoadStrategy("normal");

(async function normalPageLoad() {
    let driver = await new Builder().
    withCapabilities(caps).
    forBrowser('chrome').
    build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();