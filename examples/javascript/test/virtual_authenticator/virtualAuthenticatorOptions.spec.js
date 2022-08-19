const { VirtualAuthenticatorOptions } = require("selenium-webdriver/lib/virtual_authenticator");
const { suite } = require('selenium-webdriver/testing');
const assert = require('assert')

suite(function() {
    describe('Virtual authenticator options', function() {

        let options;

        before(async function() {
            options = new VirtualAuthenticatorOptions();
        });

        it('Virtual options', async function() {
            options.setIsUserVerified(true);
            options.setHasUserVerification(true);
            options.setIsUserConsenting(true);
            options.setTransport(VirtualAuthenticatorOptions.Transport['USB']);
            options.setProtocol(VirtualAuthenticatorOptions.Protocol['U2F']);
            options.setHasResidentKey(false);

            assert(Object.keys(options).length == 6);
        });

        it('User verified', async function() {
            options.setIsUserVerified(true);

            assert(options.toDict()['isUserVerified']);
        });

    });
});