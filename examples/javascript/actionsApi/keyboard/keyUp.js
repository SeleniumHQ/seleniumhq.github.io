const {Builder, By, Key} = require('selenium-webdriver');
(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');

        // Store google search box WebElement
        let search = driver.findElement(By.name('q'));

        // Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
        await driver.actions().click(search).keyDown(Key.SHIFT).sendKeys("qwerty").keyUp(Key.SHIFT).sendKeys("qwerty").perform();
    }
    finally {
        await driver.quit();
    }
})();
