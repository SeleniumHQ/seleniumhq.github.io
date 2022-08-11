# frozen_string_literal: true

require 'selenium-webdriver'

RSpec.describe 'Virtual Authenticator' do
  let(:encoded_private_key) do
    'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDbBOu5Lhs4vpowbCnmCyLUpIE7JM9sm9QXzye2G+jr+Kr' \
          'MsinWohEce47BFPJlTaDzHSvOW2eeunBO89ZcvvVc8RLz4qyQ8rO98xS1jtgqi1NcBPETDrtzthODu/gd0sjB2Tk3TLuBGV' \
          'oPXt54a+Oo4JbBJ6h3s0+5eAfGplCbSNq6hN3Jh9YOTw5ZA6GCEy5l8zBaOgjXytd2v2OdSVoEDNiNQRkjJd2rmS2oi9AyQ' \
          'FR3B7BrPSiDlCcITZFOWgLF5C31Wp/PSHwQhlnh7/6YhnE2y9tzsUvzx0wJXrBADW13+oMxrneDK3WGbxTNYgIi1PvSqXlq' \
          'GjHtCK+R2QkXAgMBAAECggEAVc6bu7VAnP6v0gDOeX4razv4FX/adCao9ZsHZ+WPX8PQxtmWYqykH5CY4TSfsuizAgyPuQ0' \
          '+j4Vjssr9VODLqFoanspT6YXsvaKanncUYbasNgUJnfnLnw3an2XpU2XdmXTNYckCPRX9nsAAURWT3/n9ljc/XYY22ecYxM' \
          '8sDWnHu2uKZ1B7M3X60bQYL5T/lVXkKdD6xgSNLeP4AkRx0H4egaop68hoW8FIwmDPVWYVAvo8etzWCtibRXz5FcNld9MgD' \
          '/Ai7ycKy4Q1KhX5GBFI79MVVaHkSQfxPHpr7/XcmpQOEAr+BMPon4s4vnKqAGdGB3j/E3d/+4F2swykoQKBgQD8hCsp6FIQ' \
          '5umJlk9/j/nGsMl85LgLaNVYpWlPRKPc54YNumtvj5vx1BG+zMbT7qIE3nmUPTCHP7qb5ERZG4CdMCS6S64/qzZEqijLCqe' \
          'pwj6j4fV5SyPWEcpxf6ehNdmcfgzVB3Wolfwh1ydhx/96L1jHJcTKchdJJzlfTvq8wwKBgQDeCnKws1t5GapfE1rmC/h4ol' \
          'L2qZTth9oQmbrXYohVnoqNFslDa43ePZwL9Jmd9kYb0axOTNMmyrP0NTj41uCfgDS0cJnNTc63ojKjegxHIyYDKRZNVUR/d' \
          'xAYB/vPfBYZUS7M89pO6LLsHhzS3qpu3/hppo/Uc/AM/r8PSflNHQKBgDnWgBh6OQncChPUlOLv9FMZPR1ZOfqLCYrjYEqi' \
          'uzGm6iKM13zXFO4AGAxu1P/IAd5BovFcTpg79Z8tWqZaUUwvscnl+cRlj+mMXAmdqCeO8VASOmqM1ml667axeZDIR867ZG8' \
          'K5V029Wg+4qtX5uFypNAAi6GfHkxIKrD04yOHAoGACdh4wXESi0oiDdkz3KOHPwIjn6BhZC7z8mx+pnJODU3cYukxv3WTct' \
          'lUhAsyjJiQ/0bK1yX87ulqFVgO0Knmh+wNajrb9wiONAJTMICG7tiWJOm7fW5cfTJwWkBwYADmkfTRmHDvqzQSSvoC2S7aa' \
          '9QulbC3C/qgGFNrcWgcT9kCgYAZTa1P9bFCDU7hJc2mHwJwAW7/FQKEJg8SL33KINpLwcR8fqaYOdAHWWz636osVEqosRrH' \
          'zJOGpf9x2RSWzQJ+dq8+6fACgfFZOVpN644+sAHfNPAI/gnNKU5OfUv+eav8fBnzlf1A3y3GIkyMyzFN3DE7e0n/lyqxE4H' \
          'BYGpI8g=='
  end

  let(:pkcs8_private_key) do
    'MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQg8_zMDQDYAxlU-Q' \
          'hk1Dwkf0v18GZca1DMF3SaJ9HPdmShRANCAASNYX5lyVCOZLzFZzrIKmeZ2jwU' \
          'RmgsJYxGP__fWN_S-j5sN4tT15XEpN_7QZnt14YvI6uvAgO0uJEboFaZlOEB'
  end

  it 'should create virtual authenticator options' do
    options = Selenium::WebDriver::VirtualAuthenticatorOptions.new(protocol: :u2f, transport: :usb, resident_key: false,
                                                                   user_consenting: true, user_verification: true,
                                                                   user_verified: true)
    expect(options.instance_variables.length).to eq 6
  end

  it 'should create virtual authenticator' do
    options = Selenium::WebDriver::VirtualAuthenticatorOptions.new(protocol: :u2f, resident_key: false)
    driver = Selenium::WebDriver.for :chrome
    @authenticator = driver.add_virtual_authenticator(options)

    expect(@authenticator.valid?).to eq true

    driver.quit
  end

  it 'should remove virtual authenticator' do
    options = Selenium::WebDriver::VirtualAuthenticatorOptions.new
    driver = Selenium::WebDriver.for :chrome
    @authenticator = driver.add_virtual_authenticator(options)
    @authenticator.remove!

    expect(@authenticator.valid?).to eq false

    driver.quit
  end

  it 'should create and add resident credential' do
    options = Selenium::WebDriver::VirtualAuthenticatorOptions.new(protocol: :ctap2, resident_key: true,
                                                                   user_verification: true, user_verified: true)
    driver = Selenium::WebDriver.for :chrome
    @authenticator = driver.add_virtual_authenticator(options)

    resident_credential = Selenium::WebDriver::Credential.resident(id: [1, 2, 3, 4],
                                                                   rp_id: 'localhost',
                                                                   user_handle: [1],
                                                                   private_key: Selenium::WebDriver::Credential
                                                                                  .decode(encoded_private_key),
                                                                   sign_count: 0)
    @authenticator.add_credential(resident_credential)
    credential_list = @authenticator.credentials
    expect(credential_list.length).to eq 1

    credential_id = credential_list.first.id
    expect(credential_id).to eq [1, 2, 3, 4]

    driver.quit
  end

  it 'should not support resident credential when authenticator uses u2f protocol' do
    options = Selenium::WebDriver::VirtualAuthenticatorOptions.new(protocol: :u2f, resident_key: true)
    driver = Selenium::WebDriver.for :chrome
    @authenticator = driver.add_virtual_authenticator(options)

    resident_credential = Selenium::WebDriver::Credential.resident(id: [1, 2, 3, 4],
                                                                   rp_id: 'localhost',
                                                                   user_handle: [1],
                                                                   private_key: Selenium::WebDriver::Credential
                                                                                  .decode(pkcs8_private_key),
                                                                   sign_count: 0)

    expect do
      @authenticator.add_credential(resident_credential)
    end.to raise_error(Selenium::WebDriver::Error::InvalidArgumentError)

    driver.quit
  end

  it 'should create and add non-resident credential' do
    options = Selenium::WebDriver::VirtualAuthenticatorOptions.new(protocol: :u2f, resident_key: false)
    driver = Selenium::WebDriver.for :chrome
    @authenticator = driver.add_virtual_authenticator(options)

    non_resident_credential = Selenium::WebDriver::Credential.non_resident(id: [1, 2, 3, 4],
                                                                           rp_id: 'localhost',
                                                                           private_key: Selenium::WebDriver::Credential
                                                                                          .decode(pkcs8_private_key),
                                                                           sign_count: 0)
    @authenticator.add_credential(non_resident_credential)
    credentials = @authenticator.credentials

    expect(credentials.length).to eq 1
    expect(credentials.first.id).to eq [1, 2, 3, 4]

    driver.quit
  end

  it 'should get all credentials' do
    options = Selenium::WebDriver::VirtualAuthenticatorOptions.new(protocol: :ctap2, resident_key: true,
                                                                   user_verification: true, user_verified: true)
    driver = Selenium::WebDriver.for :chrome
    @authenticator = driver.add_virtual_authenticator(options)

    resident_credential = Selenium::WebDriver::Credential.resident(id: [1, 2, 3, 4],
                                                                   rp_id: 'localhost',
                                                                   user_handle: [1],
                                                                   private_key: Selenium::WebDriver::Credential
                                                                                  .decode(encoded_private_key),
                                                                   sign_count: 0)
    non_resident_credential = Selenium::WebDriver::Credential.non_resident(id: [1, 2, 3, 4, 5],
                                                                           rp_id: 'localhost',
                                                                           private_key: Selenium::WebDriver::Credential
                                                                                          .decode(encoded_private_key),
                                                                           sign_count: 0)
    @authenticator.add_credential(resident_credential)
    @authenticator.add_credential(non_resident_credential)

    credentials = @authenticator.credentials
    expect(credentials.length).to eq 2
    expect(credentials.first.id).to eq [1, 2, 3, 4]

    driver.quit
  end

  it 'should remove all credentials' do
    options = Selenium::WebDriver::VirtualAuthenticatorOptions.new
    driver = Selenium::WebDriver.for :chrome
    @authenticator = driver.add_virtual_authenticator(options)

    non_resident_credential = Selenium::WebDriver::Credential.non_resident(id: [1, 2, 3, 4],
                                                                           rp_id: 'localhost',
                                                                           private_key: Selenium::WebDriver::Credential
                                                                                          .decode(encoded_private_key),
                                                                           sign_count: 0)
    @authenticator.add_credential(non_resident_credential)
    expect(@authenticator.credentials.length).to eq 1

    @authenticator.remove_all_credentials
    expect(@authenticator.credentials).to be_empty

    driver.quit
  end
end
