const { suite } = require('selenium-webdriver/testing');

suite(function(env) {
    describe('Cookies', function() {
        let driver;

        before(async function() {
            driver = await env.builder().build();
        });

        after(() => driver.quit());

        it('Create a cookie', async function() {
            await driver.get('https://www.example.com');

            // set a cookie on the current domain
            await driver.manage().addCookie({ name: 'key', value: 'value' });
        });

        it('Create cookies with sameSite', async function() {
            await driver.get('https://www.example.com');

            // set a cookie on the current domain with sameSite 'Strict' (or) 'Lax'
            await driver.manage().addCookie({ name: 'key', value: 'value', sameSite: 'Strict' });
            await driver.manage().addCookie({ name: 'key', value: 'value', sameSite: 'Lax' });
        });

        it('Read cookie', async function() {
            await driver.get('https://www.example.com');

            // set a cookie on the current domain
            await driver.manage().addCookie({ name: 'foo', value: 'bar' });

            // Get cookie details with named cookie 'foo'
            await driver.manage().getCookie('foo').then(function(cookie) {
                console.log('cookie details => ', cookie);
            });
        });

        it('Read all cookies', async function() {
            await driver.get('https://www.example.com');

            // Add few cookies
            await driver.manage().addCookie({ name: 'test1', value: 'cookie1' });
            await driver.manage().addCookie({ name: 'test2', value: 'cookie2' });

            // Get all Available cookies
            await driver.manage().getCookies().then(function(cookies) {
                console.log('cookie details => ', cookies);
            });
        });

        it('Delete a cookie', async function() {
            await driver.get('https://www.example.com');

            // Add few cookies
            await driver.manage().addCookie({ name: 'test1', value: 'cookie1' });
            await driver.manage().addCookie({ name: 'test2', value: 'cookie2' });

            // Delete a cookie with name 'test1'
            await driver.manage().deleteCookie('test1');

            // Get all Available cookies
            await driver.manage().getCookies().then(function(cookies) {
                console.log('cookie details => ', cookies);
            });
        });

        it('Delete all cookies', async function() {
            await driver.get('https://www.example.com');

            // Add few cookies
            await driver.manage().addCookie({ name: 'test1', value: 'cookie1' });
            await driver.manage().addCookie({ name: 'test2', value: 'cookie2' });

            // Delete all cookies
            await driver.manage().deleteAllCookies();
        });

    });
});