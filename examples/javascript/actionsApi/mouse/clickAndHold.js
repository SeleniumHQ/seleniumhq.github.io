const {Builder, By} = require('selenium-webdriver');

(async function clickAndHold() {
    let driver = await new Builder().forBrowser('chrome').build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');
        // Store 'SignIn' button web element
        let signInBtn = driver.findElement(By.linkText("Sign in"));
        const actions = driver.actions({async: true});
        // Perform mouseMove to element and mouseDown (press) action on the element
        await actions.move({origin:signInBtn}).press().perform();
    }
    finally {
        await driver.quit();
    }
})();

