const {Builder, By} = require('selenium-webdriver');

(async function clickAndHold() {
    let driver = await new Builder().forBrowser('chrome').build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
        // Store 'google search' button web element
        let searchBtn = driver.findElement(By.linkText("Sign in"));
        const actions = driver.actions({async: true});
        // Perform mouseMove to element and mouseDown (press) action on the element
        await actions.move({origin:searchBtn}).press().perform();
    }
    finally {
        await driver.quit();
    }
})();

