using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.IdentityModel.Tokens;
using OpenQA.Selenium;
using OpenQA.Selenium.VirtualAuth;
using static OpenQA.Selenium.VirtualAuth.VirtualAuthenticatorOptions;
using System.Collections.Generic;
using System;

namespace SeleniumDocs.VirtualAuthentication
{
    [TestClass]
    public class VirtualAuthenticatorTest : BaseTest
    {
        //A pkcs#8 encoded encrypted RSA private key as a base64 string.
        private static string base64EncodedRSAPK =
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDbBOu5Lhs4vpowbCnmCyLUpIE7JM9sm9QXzye2G+jr+Kr"
              + "MsinWohEce47BFPJlTaDzHSvOW2eeunBO89ZcvvVc8RLz4qyQ8rO98xS1jtgqi1NcBPETDrtzthODu/gd0sjB2Tk3TLuB"
              + "GVoPXt54a+Oo4JbBJ6h3s0+5eAfGplCbSNq6hN3Jh9YOTw5ZA6GCEy5l8zBaOgjXytd2v2OdSVoEDNiNQRkjJd2rmS2oi"
              + "9AyQFR3B7BrPSiDlCcITZFOWgLF5C31Wp/PSHwQhlnh7/6YhnE2y9tzsUvzx0wJXrBADW13+oMxrneDK3WGbxTNYgIi1P"
              + "vSqXlqGjHtCK+R2QkXAgMBAAECggEAVc6bu7VAnP6v0gDOeX4razv4FX/adCao9ZsHZ+WPX8PQxtmWYqykH5CY4TSfsui"
              + "zAgyPuQ0+j4Vjssr9VODLqFoanspT6YXsvaKanncUYbasNgUJnfnLnw3an2XpU2XdmXTNYckCPRX9nsAAURWT3/n9ljc/"
              + "XYY22ecYxM8sDWnHu2uKZ1B7M3X60bQYL5T/lVXkKdD6xgSNLeP4AkRx0H4egaop68hoW8FIwmDPVWYVAvo8etzWCtib"
              + "RXz5FcNld9MgD/Ai7ycKy4Q1KhX5GBFI79MVVaHkSQfxPHpr7/XcmpQOEAr+BMPon4s4vnKqAGdGB3j/E3d/+4F2swyko"
              + "QKBgQD8hCsp6FIQ5umJlk9/j/nGsMl85LgLaNVYpWlPRKPc54YNumtvj5vx1BG+zMbT7qIE3nmUPTCHP7qb5ERZG4CdMC"
              + "S6S64/qzZEqijLCqepwj6j4fV5SyPWEcpxf6ehNdmcfgzVB3Wolfwh1ydhx/96L1jHJcTKchdJJzlfTvq8wwKBgQDeCnK"
              + "ws1t5GapfE1rmC/h4olL2qZTth9oQmbrXYohVnoqNFslDa43ePZwL9Jmd9kYb0axOTNMmyrP0NTj41uCfgDS0cJnNTc63"
              + "ojKjegxHIyYDKRZNVUR/dxAYB/vPfBYZUS7M89pO6LLsHhzS3qpu3/hppo/Uc/AM /r8PSflNHQKBgDnWgBh6OQncChPUl"
              + "OLv9FMZPR1ZOfqLCYrjYEqiuzGm6iKM13zXFO4AGAxu1P/IAd5BovFcTpg79Z8tWqZaUUwvscnl+cRlj+mMXAmdqCeO8V"
              + "ASOmqM1ml667axeZDIR867ZG8K5V029Wg+4qtX5uFypNAAi6GfHkxIKrD04yOHAoGACdh4wXESi0oiDdkz3KOHPwIjn6B"
              + "hZC7z8mx+pnJODU3cYukxv3WTctlUhAsyjJiQ/0bK1yX87ulqFVgO0Knmh+wNajrb9wiONAJTMICG7tiWJOm7fW5cfTJw"
              + "WkBwYADmkfTRmHDvqzQSSvoC2S7aa9QulbC3C/qgGFNrcWgcT9kCgYAZTa1P9bFCDU7hJc2mHwJwAW7/FQKEJg8SL33KI"
              + "NpLwcR8fqaYOdAHWWz636osVEqosRrHzJOGpf9x2RSWzQJ+dq8+6fACgfFZOVpN644+sAHfNPAI/gnNKU5OfUv+eav8fB"
              + "nzlf1A3y3GIkyMyzFN3DE7e0n/lyqxE4HBYGpI8g==";

        private static byte[] bytes = System.Convert.FromBase64String(base64EncodedRSAPK);
        private string base64EncodedPK = Base64UrlEncoder.Encode(bytes);

        // A pkcs#8 encoded unencrypted EC256 private key as a base64url string.
        private string base64EncodedEC256PK =
              "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQg8_zMDQDYAxlU-Q"
              + "hk1Dwkf0v18GZca1DMF3SaJ9HPdmShRANCAASNYX5lyVCOZLzFZzrIKmeZ2jwU"
              + "RmgsJYxGP__fWN_S-j5sN4tT15XEpN_7QZnt14YvI6uvAgO0uJEboFaZlOEB";


        [TestMethod]
        public void VirtualOptionsShouldAllowSettingOptions()
        {
            // Create virtual authenticator options
            VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
                .SetIsUserVerified(true)
                .SetHasUserVerification(true)
                .SetIsUserConsenting(true)
                .SetTransport(VirtualAuthenticatorOptions.Transport.USB)
                .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
                .SetHasResidentKey(false);

            Assert.AreEqual(6, options.ToDictionary().Count);
        }

        [TestMethod]
        public void ShouldBeAbleToCreateAuthenticator()
        {
            // Create virtual authenticator options
            VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
                .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
                .SetHasResidentKey(false);

            // Register a virtual authenticator
            ((WebDriver)driver).AddVirtualAuthenticator(options);

            List<Credential> credentialList = ((WebDriver)driver).GetCredentials();

            Assert.AreEqual(0, credentialList.Count);
        }


        [TestMethod]
        public void ShouldBeAbleToRemoveAuthenticator()
        {
            VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
                .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
                .SetHasResidentKey(false);

            String virtualAuthenticatorId = ((WebDriver)driver).AddVirtualAuthenticator(options);

            ((WebDriver)driver).RemoveVirtualAuthenticator(virtualAuthenticatorId);

            // Since the authenticator was removed, any operation using it will throw an error
            Assert.ThrowsException<WebDriverArgumentException>(() => ((WebDriver)driver).GetCredentials());
        }

        [TestMethod]
        public void ShouldBeAbleToCreateAndAddResidentialKey()
        {
            VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
                .SetProtocol(Protocol.CTAP2)
                .SetHasResidentKey(true)
                .SetHasUserVerification(true)
                .SetIsUserVerified(true);

            ((WebDriver)driver).AddVirtualAuthenticator(options);

            byte[] credentialId = { 1, 2, 3, 4 };
            byte[] userHandle = { 1 };

            Credential residentCredential = Credential.CreateResidentCredential(
              credentialId, "localhost", base64EncodedPK, userHandle, 0);

            ((WebDriver)driver).AddCredential(residentCredential);

            List<Credential> credentialList = ((WebDriver)driver).GetCredentials();
            Assert.AreEqual(1, credentialList.Count);

            Credential credential = credentialList[0];
            CollectionAssert.AreEqual(credentialId, credential.Id);
        }

        [TestMethod]
        public void ShouldNotAddResidentCredentialWhenAuthenticatorUsesU2FProtocol()
        {
            VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
                .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
                .SetHasResidentKey(true);

            ((WebDriver)driver).AddVirtualAuthenticator(options);

            byte[] credentialId = { 1, 2, 3, 4 };
            byte[] userHandle = { 1 };

            Credential credential = Credential.CreateResidentCredential(
              credentialId, "localhost", base64EncodedEC256PK, userHandle, 0);

            Assert.ThrowsException<WebDriverArgumentException>(() => ((WebDriver)driver).AddCredential(credential));
        }

        [TestMethod]
        public void ShouldBeAbleToCreateAndAddNonResidentKey()
        {
            VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
                .SetProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
                .SetHasResidentKey(false);

            ((WebDriver)driver).AddVirtualAuthenticator(options);

            byte[] credentialId = { 1, 2, 3, 4 };

            Credential nonResidentCredential = Credential.CreateNonResidentCredential(
              credentialId, "localhost", base64EncodedEC256PK, 0);

            ((WebDriver)driver).AddCredential(nonResidentCredential);

            List<Credential> credentialList = ((WebDriver)driver).GetCredentials();
            Assert.AreEqual(1, credentialList.Count);

            Credential credential = credentialList[0];
            CollectionAssert.AreEqual(credentialId, nonResidentCredential.Id);
        }

        [TestMethod]
        public void ShouldBeAbleToGetCredential()
        {
            VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
                .SetProtocol(Protocol.CTAP2)
                .SetHasResidentKey(true)
                .SetHasUserVerification(true)
                .SetIsUserVerified(true);

            ((WebDriver)driver).AddVirtualAuthenticator(options);

            byte[] credentialId = { 1, 2, 3, 4 };
            byte[] userHandle = { 1 };

            Credential residentCredential = Credential.CreateResidentCredential(
              credentialId, "localhost", base64EncodedPK, userHandle, 0);

            ((WebDriver)driver).AddCredential(residentCredential);

            List<Credential> credentialList = ((WebDriver)driver).GetCredentials();
            Assert.AreEqual(1, credentialList.Count);

            Credential credential = credentialList[0];
            CollectionAssert.AreEqual(credentialId, residentCredential.Id);
            Assert.AreEqual(base64EncodedPK, credential.PrivateKey);
        }

        [TestMethod]
        public void ShouldBeAbleToRemoveCredential()
        {
            ((WebDriver)driver).AddVirtualAuthenticator(new VirtualAuthenticatorOptions());

            byte[] credentialId = { 1, 2, 3, 4 };

            Credential nonResidentCredential = Credential.CreateNonResidentCredential(
              credentialId, "localhost", base64EncodedEC256PK, 0);

            ((WebDriver)driver).AddCredential(nonResidentCredential);

            ((WebDriver)driver).RemoveCredential(credentialId);

            Assert.AreEqual(0, ((WebDriver)driver).GetCredentials().Count);
        }


        [TestMethod]
        public void ShouldBeAbleToRemoveAllCredentias()
        {
            ((WebDriver)driver).AddVirtualAuthenticator(new VirtualAuthenticatorOptions());

            byte[] credentialId = { 1, 2, 3, 4 };

            Credential nonResidentCredential = Credential.CreateNonResidentCredential(
              credentialId, "localhost", base64EncodedEC256PK, 0);

            ((WebDriver)driver).AddCredential(nonResidentCredential);

            ((WebDriver)driver).RemoveAllCredentials();

            Assert.AreEqual(0, ((WebDriver)driver).GetCredentials().Count);
        }

        [TestMethod]
        public void ShouldBeSetVerifiedOption()
        {
            VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
                .SetIsUserVerified(true);

            Assert.IsTrue((bool)options.ToDictionary()["isUserVerified"]);
        }
    }
}