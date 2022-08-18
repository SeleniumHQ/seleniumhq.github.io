import pytest
from base64 import urlsafe_b64decode, urlsafe_b64encode

from selenium.common.exceptions import InvalidArgumentException
from selenium.webdriver.chrome.webdriver import WebDriver
from selenium.webdriver.common.virtual_authenticator import (
    Credential,
    VirtualAuthenticatorOptions,
)


BASE64__ENCODED_PK = '''
MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDbBOu5Lhs4vpowbCnmCyLUpIE7JM9sm9QXzye2G+jr+Kr
MsinWohEce47BFPJlTaDzHSvOW2eeunBO89ZcvvVc8RLz4qyQ8rO98xS1jtgqi1NcBPETDrtzthODu/gd0sjB2Tk3TLuBGV
oPXt54a+Oo4JbBJ6h3s0+5eAfGplCbSNq6hN3Jh9YOTw5ZA6GCEy5l8zBaOgjXytd2v2OdSVoEDNiNQRkjJd2rmS2oi9AyQ
FR3B7BrPSiDlCcITZFOWgLF5C31Wp/PSHwQhlnh7/6YhnE2y9tzsUvzx0wJXrBADW13+oMxrneDK3WGbxTNYgIi1PvSqXlq
GjHtCK+R2QkXAgMBAAECggEAVc6bu7VAnP6v0gDOeX4razv4FX/adCao9ZsHZ+WPX8PQxtmWYqykH5CY4TSfsuizAgyPuQ0
+j4Vjssr9VODLqFoanspT6YXsvaKanncUYbasNgUJnfnLnw3an2XpU2XdmXTNYckCPRX9nsAAURWT3/n9ljc/XYY22ecYxM
8sDWnHu2uKZ1B7M3X60bQYL5T/lVXkKdD6xgSNLeP4AkRx0H4egaop68hoW8FIwmDPVWYVAvo8etzWCtibRXz5FcNld9MgD
/Ai7ycKy4Q1KhX5GBFI79MVVaHkSQfxPHpr7/XcmpQOEAr+BMPon4s4vnKqAGdGB3j/E3d/+4F2swykoQKBgQD8hCsp6FIQ
5umJlk9/j/nGsMl85LgLaNVYpWlPRKPc54YNumtvj5vx1BG+zMbT7qIE3nmUPTCHP7qb5ERZG4CdMCS6S64/qzZEqijLCqe
pwj6j4fV5SyPWEcpxf6ehNdmcfgzVB3Wolfwh1ydhx/96L1jHJcTKchdJJzlfTvq8wwKBgQDeCnKws1t5GapfE1rmC/h4ol
L2qZTth9oQmbrXYohVnoqNFslDa43ePZwL9Jmd9kYb0axOTNMmyrP0NTj41uCfgDS0cJnNTc63ojKjegxHIyYDKRZNVUR/d
xAYB/vPfBYZUS7M89pO6LLsHhzS3qpu3/hppo/Uc/AM/r8PSflNHQKBgDnWgBh6OQncChPUlOLv9FMZPR1ZOfqLCYrjYEqi
uzGm6iKM13zXFO4AGAxu1P/IAd5BovFcTpg79Z8tWqZaUUwvscnl+cRlj+mMXAmdqCeO8VASOmqM1ml667axeZDIR867ZG8
K5V029Wg+4qtX5uFypNAAi6GfHkxIKrD04yOHAoGACdh4wXESi0oiDdkz3KOHPwIjn6BhZC7z8mx+pnJODU3cYukxv3WTct
lUhAsyjJiQ/0bK1yX87ulqFVgO0Knmh+wNajrb9wiONAJTMICG7tiWJOm7fW5cfTJwWkBwYADmkfTRmHDvqzQSSvoC2S7aa
9QulbC3C/qgGFNrcWgcT9kCgYAZTa1P9bFCDU7hJc2mHwJwAW7/FQKEJg8SL33KINpLwcR8fqaYOdAHWWz636osVEqosRrH
zJOGpf9x2RSWzQJ+dq8+6fACgfFZOVpN644+sAHfNPAI/gnNKU5OfUv+eav8fBnzlf1A3y3GIkyMyzFN3DE7e0n/lyqxE4H
BYGpI8g==
'''


@pytest.fixture(scope="module", autouse=True)
def driver():
    yield WebDriver()


def test_virtual_authenticator_options():
    options = VirtualAuthenticatorOptions()
    options.is_user_verified = True
    options.has_user_verification = True
    options.is_user_consenting = True
    options.transport = VirtualAuthenticatorOptions.Transport.USB
    options.protocol = VirtualAuthenticatorOptions.Protocol.U2F
    options.has_resident_key = False

    assert len(options.to_dict()) == 6


def test_create_authenticator(driver):
    # Create virtual authenticator options
    options = VirtualAuthenticatorOptions()
    options.protocol = VirtualAuthenticatorOptions.Protocol.U2F
    options.has_resident_key = False

    # Register a virtual authenticator
    driver.add_virtual_authenticator(options)

    # Get list of credentials
    credential_list = driver.get_credentials()

    assert len(credential_list) == 0


def test_remove_authenticator(driver):
    # Create default virtual authenticator option
    options = VirtualAuthenticatorOptions()

    # Register a virtual authenticator
    driver.add_virtual_authenticator(options)

    # Remove virtual authenticator
    driver.remove_virtual_authenticator()
    assert driver.virtual_authenticator_id is None


def test_create_and_add_resident_key(driver):
    # Create virtual authenticator options
    options = VirtualAuthenticatorOptions()
    options.protocol = VirtualAuthenticatorOptions.Protocol.CTAP2
    options.has_resident_key = True
    options.has_user_verification = True
    options.is_user_verified = True

    # Register a virtual authenticator
    driver.add_virtual_authenticator(options)

    # parameters for Resident Credential
    credential_id = bytearray({1, 2, 3, 4})
    rp_id = "localhost"
    user_handle = bytearray({1})
    privatekey = urlsafe_b64decode(BASE64__ENCODED_PK)
    sign_count = 0

    # create a  resident credential using above parameters
    resident_credential = Credential.create_resident_credential(credential_id, rp_id, user_handle, privatekey, sign_count)

    # add the credential created to virtual authenticator
    driver.add_credential(resident_credential)

    # get list of all the registered credentials 
    credential_list = driver.get_credentials()
    assert len(credential_list) == 1


def test_add_resident_credential_not_supported_when_authenticator_uses_u2f_protocol(driver):
    # Create virtual authenticator options
    options = VirtualAuthenticatorOptions()
    options.protocol = VirtualAuthenticatorOptions.Protocol.U2F
    options.has_resident_key = False

    # Register a virtual authenticator
    driver.add_virtual_authenticator(options)

    # parameters for Resident Credential
    credential_id = bytearray({1, 2, 3, 4})
    rp_id = "localhost"
    user_handle = bytearray({1})
    privatekey = urlsafe_b64decode(BASE64__ENCODED_PK)
    sign_count = 0

    # create a  resident credential using above parameters
    credential = Credential.create_resident_credential(credential_id, rp_id, user_handle, privatekey, sign_count)

    # Expect InvalidArgumentException 
    with pytest.raises(InvalidArgumentException):
        driver.add_credential(credential)


def test_create_and_add_non_resident_key(driver):
    # Create virtual authenticator options
    options = VirtualAuthenticatorOptions()
    options.protocol = VirtualAuthenticatorOptions.Protocol.U2F
    options.has_resident_key = False

    # Register a virtual authenticator
    driver.add_virtual_authenticator(options)

    # parameters for Non Resident Credential
    credential_id = bytearray({1, 2, 3, 4})
    rp_id = "localhost"
    privatekey = urlsafe_b64decode(BASE64__ENCODED_PK)
    sign_count = 0

    # create a non resident credential using above parameters
    credential = Credential.create_non_resident_credential(credential_id, rp_id, privatekey, sign_count)

    # add the credential created to virtual authenticator
    driver.add_credential(credential)
    
    # get list of all the registered credentials 
    credential_list = driver.get_credentials()
    assert len(credential_list) == 1



def test_get_credential(driver):
    # Create virtual authenticator options
    options = VirtualAuthenticatorOptions()
    options.protocol = VirtualAuthenticatorOptions.Protocol.CTAP2
    options.has_resident_key = True
    options.has_user_verfied = True
    options.is_user_verified = True

    # Register a virtual authenticator
    driver.add_virtual_authenticator(options)

    # parameters for Resident Credential
    credential_id = bytearray({1, 2, 3, 4})
    rp_id = "localhost"
    user_handle = bytearray({1})
    privatekey = urlsafe_b64decode(BASE64__ENCODED_PK)
    sign_count = 0

    # create a resident credential using above parameters
    credential = Credential.create_resident_credential(credential_id, rp_id, user_handle, privatekey, sign_count)
    
    # add the credential created to virtual authenticator
    driver.add_credential(credential)

    # get list of all the registered credentials 
    credential_list = driver.get_credentials()

    assert len(credential_list) == 1
    assert credential_list[0].id == urlsafe_b64encode(credential_id).decode()


def test_remove_credential(driver):
    # Create default virtual authenticator options
    options = VirtualAuthenticatorOptions()

    # Register a virtual authenticator
    driver.add_virtual_authenticator(options)

    # parameters for Non Resident Credential
    credential_id = bytearray({1, 2, 3, 4})
    rp_id = "localhost"
    privatekey = urlsafe_b64decode(BASE64__ENCODED_PK)
    sign_count = 0

    # create a non resident credential using above parameters
    credential = Credential.create_non_resident_credential(credential_id, rp_id, privatekey, sign_count)

    # add the credential created to virtual authenticator
    driver.add_credential(credential)

    # remove the credential created from virtual authenticator
    driver.remove_credential(credential.id)

    # credential can also be removed using Byte Array
    # driver.remove_credential(credential_id) 

    assert len(driver.get_credentials()) == 0


def test_remove_all_credentials(driver):
    # Create default virtual authenticator options
    options = VirtualAuthenticatorOptions()
    options.has_resident_key = True

    # Register a virtual authenticator
    driver.add_virtual_authenticator(options)

    # parameters for Resident Credential
    credential_id = bytearray({1, 2, 3, 4})
    rp_id = "localhost"
    user_handle = bytearray({1})
    privatekey = urlsafe_b64decode(BASE64__ENCODED_PK)
    sign_count = 0

    # create a resident credential using above parameters
    resident_credential = Credential.create_resident_credential(credential_id, rp_id, user_handle, privatekey, sign_count)
    
    # add the credential created to virtual authenticator
    driver.add_credential(resident_credential)

    # remove all credentials in virtual authenticator
    driver.remove_all_credentials()

    assert len(driver.get_credentials()) == 0


def test_set_user_verified():
    # Create virtual authenticator options
    options = VirtualAuthenticatorOptions()
    options.is_user_verified = True

    assert options.to_dict().get("isUserVerified") is True