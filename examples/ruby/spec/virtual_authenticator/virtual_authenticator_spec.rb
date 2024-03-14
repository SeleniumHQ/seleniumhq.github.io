# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'VirtualAuthenticator' do
  let(:driver) { start_session }

  let(:private_key) {
    Base64.strict_encode64(OpenSSL::PKey::RSA.generate(2048).private_to_der)
  }

  let(:options) { Selenium::WebDriver::VirtualAuthenticatorOptions.new }

  it "Registers a new virtual authenticator" do
    options.protocol = :u2f
    options.resident_key = false

    authenticator = driver.add_virtual_authenticator(options)
    credential_list = authenticator.credentials

    expect(credential_list).to be_empty
  end

  it "Removes a virtual authenticator" do
    authenticator = driver.add_virtual_authenticator(options)

    authenticator.remove!

    expect {
      authenticator.credentials
    }.to raise_error(Selenium::WebDriver::Error::InvalidArgumentError)
  end

  it "Creates and adds a resident_key" do
    options.protocol = :ctap2
    options.resident_key = true
    options.user_verification = true
    options.user_verified = true

    authenticator = driver.add_virtual_authenticator(options)

    credential_id = [1,2,3,4] # byte array
    user_handle = [1] # byte array
    # decode from Base64, then turn into a byte array
    decoded_private_key = Base64.strict_decode64(private_key).bytes

    resident_credential = Selenium::WebDriver::Credential.resident(
      id: credential_id,
      private_key: decoded_private_key,
      rp_id: "localhost",
      user_handle: user_handle
    )

    authenticator.add_credential(resident_credential)

    credential_list = authenticator.credentials

    expect(credential_list.size).to eq(1)

    expect(credential_id).to eq(credential_list[0].id)
  end

  it "adding resident keys is not supported when the authenticator uses the U2F protocol" do
    options.protocol = :u2f
    options.resident_key = true

    authenticator = driver.add_virtual_authenticator(options)

    credential_id = [1,2,3,4] # byte array
    user_handle = [1] # byte array
    # decode from Base64, then turn into a byte array
    decoded_private_key = Base64.strict_decode64(private_key).bytes

    resident_credential = Selenium::WebDriver::Credential.resident(
      id: credential_id,
      private_key: decoded_private_key,
      rp_id: "localhost",
      user_handle: user_handle
    )

    expect {
      authenticator.add_credential(resident_credential)
    }.to raise_error(Selenium::WebDriver::Error::InvalidArgumentError)
  end

  it "Creates and adds a non-resident key" do
    options.protocol = :u2f
    options.resident_key = false

    authenticator = driver.add_virtual_authenticator(options)

    credential_id = [1,2,3,4] # byte array
    # decode from Base64, then turn into a byte array
    decoded_private_key = Base64.strict_decode64(private_key).bytes

    resident_credential = Selenium::WebDriver::Credential.non_resident(
      id: credential_id,
      private_key: decoded_private_key,
      rp_id: "localhost"
    )

    authenticator.add_credential(resident_credential)

    credential_list = authenticator.credentials

    expect(credential_list.size).to eq(1)

    expect(credential_id).to eq(credential_list[0].id)
  end

  it "Can get a credential" do
    options.protocol = :ctap2
    options.resident_key = true
    options.user_verification = true
    options.user_verified = true

    authenticator = driver.add_virtual_authenticator(options)

    credential_id = [1,2,3,4] # byte array
    user_handle = [1] # byte array
    # decode from Base64, then turn into a byte array
    decoded_private_key = Base64.strict_decode64(private_key).bytes

    resident_credential = Selenium::WebDriver::Credential.resident(
      id: credential_id,
      private_key: decoded_private_key,
      rp_id: "localhost",
      user_handle: user_handle
    )

    authenticator.add_credential(resident_credential)

    credential_list = authenticator.credentials

    expect(credential_list.size).to eq(1)

    expect(credential_id).to eq(credential_list[0].id)
    expect(private_key).to eq(credential_list[0].private_key)
  end

  it "Removes all credentials" do
    authenticator = driver.add_virtual_authenticator(options)

    credential_id = [1,2,3,4] # byte array
    # decode from Base64, then turn into a byte array
    decoded_private_key = Base64.strict_decode64(private_key).bytes

    resident_credential = Selenium::WebDriver::Credential.non_resident(
      id: credential_id,
      private_key: decoded_private_key,
      rp_id: "localhost"
    )

    authenticator.add_credential(resident_credential)
    authenticator.remove_all_credentials

    credential_list = authenticator.credentials

    expect(credential_list.size).to eq(0)
  end
end
