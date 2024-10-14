# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'VirtualAuthenticatorOptions' do
  let(:options) { Selenium::WebDriver::VirtualAuthenticatorOptions.new }

  it "can set the various options" do
    options.user_verified = true
    options.user_verification = true
    options.user_consenting = true
    options.transport = :usb
    options.protocol = :u2f
    options.resident_key = false

    expect(options.user_verified).to eq(true)
    expect(options.user_verification).to eq(true)
    expect(options.transport).to eq(:usb)
    expect(options.protocol).to eq(:u2f)
    expect(options.resident_key).to eq(false)
  end

  it "sets isUserVerified correctly in as part of as_json" do
    options.user_verified = true
    expect(options.as_json["isUserVerified"]).to eq(true)
  end
end
