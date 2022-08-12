# frozen_string_literal: true

RSpec.describe 'Install Drivers' do
  it 'chrome session' do
    require 'webdrivers'

    driver = Selenium::WebDriver.for :chrome

    driver.quit
  end

  it 'edge session' do
    skip('Due to error: Default location not yet known')

    require 'webdrivers'

    driver = Selenium::WebDriver.for :edge

    driver.quit
  end

  it 'firefox session' do
    require 'webdrivers'

    driver = Selenium::WebDriver.for :firefox

    driver.quit
  end

  it 'IE session' do
    skip('Only runs on Windows')

    require 'webdrivers/ie'

    driver = Selenium::WebDriver.for :ie

    driver.quit
  end
end
