const {By} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');

suite(function(env) {
  describe('Move to element', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(() => driver.quit());

    it('Mouse move into an element', async function() {
        // Navigate to Url
        await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');

        // Store 'Gmail' anchor web element
        let gmailLink = driver.findElement(By.tagName("button"));
        const actions = driver.actions({ async: true });

        // Performs mouse move action onto the element
        await actions.move({ origin: gmailLink }).perform();
    });

  });
});