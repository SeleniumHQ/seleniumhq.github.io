const {By} = require('selenium-webdriver');
const {suite} = require('selenium-webdriver/testing');

suite(function(env) {
  describe('Click and hold', function() {
    let driver;

    before(async function() {
      driver = await env.builder().build();
    });

    after(() => driver.quit());

    it('Mouse move and mouseDown on an element', async function() {
        await driver.get('https://www.selenium.dev/selenium/web/mouse_interaction.html');        
        let clickable = driver.findElement(By.id("clickable"));
        const actions = driver.actions({async: true});        
        await actions.move({origin:clickable}).press().perform();
    });

  });
});