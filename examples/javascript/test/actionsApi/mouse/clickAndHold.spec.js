const {By} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');

suite(function(env) {
  describe('Cick and hold', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(() => driver.quit());

    it('Mouse move and mouseDown on an element', async function() {
        // Navigate to Url
        await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');
        
        // Store 'SignIn' button web element
        let signInBtn = driver.findElement(By.tagName("button"));
        const actions = driver.actions({async: true});
        
        // Perform mouseMove to element and mouseDown (press) action on the element
        await actions.move({origin:signInBtn}).press().perform();
    });

  });
});