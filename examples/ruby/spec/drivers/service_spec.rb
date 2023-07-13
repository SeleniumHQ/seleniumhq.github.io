# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Service' do
  let(:file_name) { File.expand_path('driver.log') }
  let(:driver_path) { "#{ENV.fetch('CHROMEWEBDRIVER', nil)}/chromedriver" }

  after { FileUtils.rm_f(file_name) }

  it 'has default service' do
    service = Selenium::WebDriver::Service.chrome
    @driver = Selenium::WebDriver.for :chrome, service: service
  end

  it 'specifies driver location' do
    service = Selenium::WebDriver::Service.chrome
    service.executable_path = driver_path

    @driver = Selenium::WebDriver.for :chrome, service: service
  end

  it 'specifies driver port' do
    service = Selenium::WebDriver::Service.chrome
    service.port = 1234

    @driver = Selenium::WebDriver.for :chrome, service: service
  end
end
