const { Builder } = require("selenium-webdriver");
const { Credential, VirtualAuthenticatorOptions } = require("selenium-webdriver/lib/virtual_authenticator");
const { suite } = require('selenium-webdriver/testing');
const assert = require('assert')
const { InvalidArgumentError } = require("selenium-webdriver/lib/error");

suite(function(env) {
    describe('Virtual authenticator', function() {
        const BASE64_ENCODED_PK =
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDbBOu5Lhs4vpowbCnmCyLUpIE7JM9sm9QXzye2G+jr+Kr" +
            "MsinWohEce47BFPJlTaDzHSvOW2eeunBO89ZcvvVc8RLz4qyQ8rO98xS1jtgqi1NcBPETDrtzthODu/gd0sjB2Tk3TLuBGV" +
            "oPXt54a+Oo4JbBJ6h3s0+5eAfGplCbSNq6hN3Jh9YOTw5ZA6GCEy5l8zBaOgjXytd2v2OdSVoEDNiNQRkjJd2rmS2oi9AyQ" +
            "FR3B7BrPSiDlCcITZFOWgLF5C31Wp/PSHwQhlnh7/6YhnE2y9tzsUvzx0wJXrBADW13+oMxrneDK3WGbxTNYgIi1PvSqXlq" +
            "GjHtCK+R2QkXAgMBAAECggEAVc6bu7VAnP6v0gDOeX4razv4FX/adCao9ZsHZ+WPX8PQxtmWYqykH5CY4TSfsuizAgyPuQ0" +
            "+j4Vjssr9VODLqFoanspT6YXsvaKanncUYbasNgUJnfnLnw3an2XpU2XdmXTNYckCPRX9nsAAURWT3/n9ljc/XYY22ecYxM" +
            "8sDWnHu2uKZ1B7M3X60bQYL5T/lVXkKdD6xgSNLeP4AkRx0H4egaop68hoW8FIwmDPVWYVAvo8etzWCtibRXz5FcNld9MgD" +
            "/Ai7ycKy4Q1KhX5GBFI79MVVaHkSQfxPHpr7/XcmpQOEAr+BMPon4s4vnKqAGdGB3j/E3d/+4F2swykoQKBgQD8hCsp6FIQ" +
            "5umJlk9/j/nGsMl85LgLaNVYpWlPRKPc54YNumtvj5vx1BG+zMbT7qIE3nmUPTCHP7qb5ERZG4CdMCS6S64/qzZEqijLCqe" +
            "pwj6j4fV5SyPWEcpxf6ehNdmcfgzVB3Wolfwh1ydhx/96L1jHJcTKchdJJzlfTvq8wwKBgQDeCnKws1t5GapfE1rmC/h4ol" +
            "L2qZTth9oQmbrXYohVnoqNFslDa43ePZwL9Jmd9kYb0axOTNMmyrP0NTj41uCfgDS0cJnNTc63ojKjegxHIyYDKRZNVUR/d" +
            "xAYB/vPfBYZUS7M89pO6LLsHhzS3qpu3/hppo/Uc/AM/r8PSflNHQKBgDnWgBh6OQncChPUlOLv9FMZPR1ZOfqLCYrjYEqi" +
            "uzGm6iKM13zXFO4AGAxu1P/IAd5BovFcTpg79Z8tWqZaUUwvscnl+cRlj+mMXAmdqCeO8VASOmqM1ml667axeZDIR867ZG8" +
            "K5V029Wg+4qtX5uFypNAAi6GfHkxIKrD04yOHAoGACdh4wXESi0oiDdkz3KOHPwIjn6BhZC7z8mx+pnJODU3cYukxv3WTct" +
            "lUhAsyjJiQ/0bK1yX87ulqFVgO0Knmh+wNajrb9wiONAJTMICG7tiWJOm7fW5cfTJwWkBwYADmkfTRmHDvqzQSSvoC2S7aa" +
            "9QulbC3C/qgGFNrcWgcT9kCgYAZTa1P9bFCDU7hJc2mHwJwAW7/FQKEJg8SL33KINpLwcR8fqaYOdAHWWz636osVEqosRrH" +
            "zJOGpf9x2RSWzQJ+dq8+6fACgfFZOVpN644+sAHfNPAI/gnNKU5OfUv+eav8fBnzlf1A3y3GIkyMyzFN3DE7e0n/lyqxE4H" +
            "BYGpI8g==";

        const base64EncodedPK =
            "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQg8_zMDQDYAxlU-Q" +
            "hk1Dwkf0v18GZca1DMF3SaJ9HPdmShRANCAASNYX5lyVCOZLzFZzrIKmeZ2jwU" +
            "RmgsJYxGP__fWN_S-j5sN4tT15XEpN_7QZnt14YvI6uvAgO0uJEboFaZlOEB";

        let options;
        let driver;

        before(async function() {
            options = new VirtualAuthenticatorOptions();
            driver = await new Builder().forBrowser('chrome').build();
        });

        after(() => driver.quit());

        function arraysEqual(array1, array2) {
            return (array1.length == array2.length &&
                array1.every((item) => array2.includes(item)) &&
                array2.every((item) => array1.includes(item)));
        }

        it('Register a virtual authenticator', async function() {
            options.setProtocol(VirtualAuthenticatorOptions.Protocol['U2F']);
            options.setHasResidentKey(false);

            // Register a virtual authenticator
            await driver.addVirtualAuthenticator(options);
            let credentialList = await driver.getCredentials();

            assert.equal(0, credentialList.length);
        });

        it('Remove authenticator', async function() {
            await driver.addVirtualAuthenticator(options);
            await driver.removeVirtualAuthenticator();

            // Since the authenticator was removed, any operation using it will throw an error
            try {
                await driver.getCredentials()
            }
            catch (e) {
                if (e instanceof InvalidArgumentError) {
                    assert(true)
                }
                else {
                    assert(false)
                }
            }
        });

        it('Createa and add residential key', async function() {
            options.setProtocol(VirtualAuthenticatorOptions.Protocol['CTAP2']);
            options.setHasResidentKey(true);
            options.setHasUserVerification(true);
            options.setIsUserVerified(true);

            await driver.addVirtualAuthenticator(options);

            let residentCredential = new Credential().createResidentCredential(
                new Uint8Array([1, 2, 3, 4]),
                'localhost',
                new Uint8Array([1]),
                Buffer.from(BASE64_ENCODED_PK, 'base64').toString('binary'),
                0);

            await driver.addCredential(residentCredential);
            let credentialList = await driver.getCredentials();
            assert.equal(1, credentialList.length);

            let credential_id = credentialList[0].id();
            let test_id = new Uint8Array([1, 2, 3, 4]);

            assert(arraysEqual(credential_id, test_id));
        });

        it('Add resident credential not supported whenauthenticator uses U2F protocol', async function() {
            options.setProtocol(VirtualAuthenticatorOptions.Protocol['U2F']);
            options.setHasResidentKey(true);

            await driver.addVirtualAuthenticator(options);

            let credential = new Credential().createResidentCredential(
                new Uint8Array([1, 2, 3, 4]),
                'localhost',
                new Uint8Array([1]),
                Buffer.from(base64EncodedPK, 'base64').toString('binary'),
                0);

            try {
                await driver.addCredential(credential)
            }
            catch (e) {
                if (e instanceof InvalidArgumentError) {
                    assert(true)
                }
                else {
                    assert(false)
                }
            }
        });

        it('Create and add non residential key', async function() {
            options.setProtocol(VirtualAuthenticatorOptions.Protocol['U2F']);
            options.setHasResidentKey(false);

            await driver.addVirtualAuthenticator(options);

            let nonResidentCredential = new Credential().createNonResidentCredential(
                new Uint8Array([1, 2, 3, 4]),
                'localhost',
                Buffer.from(base64EncodedPK, 'base64').toString('binary'),
                0);

            await driver.addCredential(nonResidentCredential);

            let credentialList = await driver.getCredentials();
            assert.equal(1, credentialList.length);

            let credential_id = credentialList[0].id();
            let test_id = new Uint8Array([1, 2, 3, 4]);

            assert(arraysEqual(credential_id, test_id));
        });

        it('Get credential', async function() {
            options.setProtocol(VirtualAuthenticatorOptions.Protocol['CTAP2']);
            options.setHasResidentKey(true);
            options.setHasUserVerification(true);
            options.setIsUserVerified(true);

            await driver.addVirtualAuthenticator(options);

            let residentCredential = new Credential().createResidentCredential(
                new Uint8Array([1, 2, 3, 4]),
                'localhost',
                new Uint8Array([1]),
                Buffer.from(BASE64_ENCODED_PK, 'base64').toString('binary'),
                0);

            await driver.addCredential(residentCredential);

            let credentialList = await driver.getCredentials();
            assert.equal(1, credentialList.length);

            let credential_id = credentialList[0].id();
            let test_id = new Uint8Array([1, 2, 3, 4]);

            assert(arraysEqual(credential_id, test_id));
            assert.equal(BASE64_ENCODED_PK, Buffer.from(credentialList[0].privateKey(), 'binary').toString('base64'));
        });

        it('Remove all credentials', async function() {
            await driver.addVirtualAuthenticator(options);

            let nonResidentCredential = new Credential().createNonResidentCredential(
                new Uint8Array([1, 2, 3, 4]),
                'localhost',
                Buffer.from(BASE64_ENCODED_PK, 'base64').toString('binary'),
                0);

            await driver.addCredential(nonResidentCredential);
            driver.removeAllCredentials();

            let credentialList = await driver.getCredentials();
            assert.equal(0, credentialList.length);
        });

    });
});