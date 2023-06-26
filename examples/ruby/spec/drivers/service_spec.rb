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

  it 'logs to file' do
    service = Selenium::WebDriver::Service.chrome
    service.log = file_name

    @driver = Selenium::WebDriver.for :chrome, service: service

    expect(File.readlines(file_name).size).to eq 4
  end

  it 'logs to stdout' do
    service = Selenium::WebDriver::Service.chrome
    service.log = $stdout

    expect {
      @driver = Selenium::WebDriver.for :chrome, service: service
    }.to output(/Starting ChromeDriver/).to_stdout_from_any_process
  end
end
