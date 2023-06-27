# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Safari', exclusive: {platform: :macosx} do
  describe 'Options' do
    it 'basic options' do
      options = Selenium::WebDriver::Options.safari
      @driver = Selenium::WebDriver.for :safari, options: options
    end
  end

  describe 'Service' do
    let(:directory) { "#{Dir.home}/Library/Logs/com.apple.WebDriver/*" }

    it 'enable logs' do
      original_count = Dir[directory].length
      service = Selenium::WebDriver::Service.safari

      service.args << '--diagnose'

      @driver = Selenium::WebDriver.for :safari, service: service
      expect(Dir[directory].length - original_count).to eq 1
    end

    it 'does not set log output' do
      service = Selenium::WebDriver::Service.safari

      expect {
        service.log = $stdout
      }.to raise_error(Selenium::WebDriver::Error::WebDriverError, /Safari Service does not support setting log output/)
    end
  end
end
