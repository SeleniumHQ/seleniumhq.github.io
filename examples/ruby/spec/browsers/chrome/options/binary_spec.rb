# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome Options' do
  it 'sets location of binary' do
    options = default_chrome_options

    options.binary = chrome_location

    @driver = Selenium::WebDriver.for :chrome, options: options
  end

  private

  def chrome_location
    options = default_chrome_options
    service = Selenium::WebDriver::Service.chrome
    finder = Selenium::WebDriver::DriverFinder.new(options, service)
    ENV['CHROMEDRIVER_BIN'] = finder.driver_path
    ENV['CHROME_BIN'] = finder.browser_path
  end
end
