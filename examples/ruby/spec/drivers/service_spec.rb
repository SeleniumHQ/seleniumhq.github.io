# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Service' do
  let(:file_name) { File.expand_path('driver.log') }
  let(:driver_path) { ENV.fetch('CHROMEDRIVER_BIN', nil) }
  let(:browser_path) { ENV.fetch('CHROME_BIN', nil) }

  before { driver_finder }
  after { FileUtils.rm_f(file_name) }

  it 'has default service' do
    service = Selenium::WebDriver::Service.chrome
    @driver = Selenium::WebDriver.for :chrome, service: service
  end

  it 'specifies driver location' do
    options = Selenium::WebDriver::Options.chrome(binary: browser_path)
    service = Selenium::WebDriver::Service.chrome

    service.executable_path = driver_path

    @driver = Selenium::WebDriver.for :chrome, service: service, options: options
  end

  it 'specifies driver port' do
    service = Selenium::WebDriver::Service.chrome
    service.port = 1234

    @driver = Selenium::WebDriver.for :chrome, service: service
  end

  def driver_finder
    options = Selenium::WebDriver::Options.chrome(browser_version: 'stable')
    service = Selenium::WebDriver::Service.chrome
    finder = Selenium::WebDriver::DriverFinder.new(options, service)
    ENV['CHROMEDRIVER_BIN'] = finder.driver_path
    ENV['CHROME_BIN'] = finder.browser_path
  end
end
