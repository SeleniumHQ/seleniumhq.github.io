# frozen_string_literal: true
require 'selenium-webdriver'

RSpec.describe 'Open Browser' do
  it 'chrome session' do
    options = Selenium::WebDriver::Options.chrome
    driver = Selenium::WebDriver.for :chrome, options: options

    driver.quit
  end

  it 'edge session' do
    options = Selenium::WebDriver::Options.edge
    driver = Selenium::WebDriver.for :edge, options: options

    driver.quit
  end

  it 'firefox session' do
    options = Selenium::WebDriver::Options.firefox
    driver = Selenium::WebDriver.for :firefox, options: options

    driver.quit
  end

  it 'IE session' do
    skip('Only runs on Windows')

    options = Selenium::WebDriver::Options.ie
    driver = Selenium::WebDriver.for :ie, options: options

    driver.quit
  end

  it 'IE compatibility session' do
    skip('Only runs on Windows')

    options = Selenium::WebDriver::Options.ie
    options.attach_to_edge_chrome = true
    options.edge_executable_path = "/path/to/edge/browser"
    driver = Selenium::WebDriver.for :ie, options: options

    driver.quit
  end

  it 'opera session' do
    skip('Non-standard browser')

    options = Selenium::WebDriver::Options.chrome
    options.binary = '/path/to/opera/browser'
    driver = Selenium::WebDriver.for :chrome, options: options

    driver.quit
  end

  it 'safari session' do
    skip('Only runs on Mac')

    options = Selenium::WebDriver::Options.safari
    driver = Selenium::WebDriver.for :safari, options: options

    driver.quit
  end
end
