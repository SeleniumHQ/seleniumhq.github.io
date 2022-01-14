# frozen_string_literal: true

RSpec.describe 'Install Drivers' do
  it 'chrome session' do
    require 'webdrivers/chrome'

    driver = Selenium::WebDriver.for :chrome

    driver.quit
  end

  it 'edge session' do
    require 'webdrivers/edge'

    driver = Selenium::WebDriver.for :edge

    driver.quit
  end

  it 'firefox session' do
    require 'webdrivers/firefox'

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
