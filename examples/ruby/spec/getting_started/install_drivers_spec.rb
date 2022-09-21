# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Install Drivers' do
  it 'chrome session' do
    require 'webdrivers'

    driver = Selenium::WebDriver.for :chrome

    driver.quit
  end

  it 'edge session' do
    require 'webdrivers'

    driver = Selenium::WebDriver.for :edge

    driver.quit
  end

  it 'firefox session' do
    require 'webdrivers'

    driver = Selenium::WebDriver.for :firefox

    driver.quit
  end

  it 'IE session', exclusive: {platform: :windows} do
    require 'webdrivers'

    driver = Selenium::WebDriver.for :ie

    driver.quit
  end
end
