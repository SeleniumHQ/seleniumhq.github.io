const {Builder, Capabilities} = require('selenium-webdriver');
const caps = new Capabilities();

// Set page load strategy to None
caps.setPageLoadStrategy("none");
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