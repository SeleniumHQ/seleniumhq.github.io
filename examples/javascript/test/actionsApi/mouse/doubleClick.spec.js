const { By } = require('selenium-webdriver');
const { suite } = require('selenium-webdriver/testing');

suite(function(env) {
    describe('Double click', function() {
        let driver;

        before(async function() {
            driver = await env.builder().build();
        });

        after(() => driver.quit());

        it('Double-click on an element', async function() {
            // Navigate to Url
            await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');
            
            // Store 'Sign in' button web element
            let signIn = driver.findElement(By.tagName("button"));
            const actions = driver.actions({ async: true });
            
            // Perform double-click action on the element
            await actions.doubleClick(signIn).perform();
        });

    });
});