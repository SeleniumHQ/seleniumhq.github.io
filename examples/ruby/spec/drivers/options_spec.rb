# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome' do
  describe 'Driver Options' do
    let(:chrome_location) { driver_finder && ENV.fetch('CHROME_BIN', nil) }

  it 'page load strategy normal' do
      options = Selenium::WebDriver::Options.chrome
      options.page_load_strategy = :normal

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get('https://www.google.com')
      driver.quit
  end

  it 'page load strategy eager' do
       options = Selenium::WebDriver::Options.chrome
       options.page_load_strategy = :eager

       driver = Selenium::WebDriver.for :chrome, options: options
       driver.get('https://www.google.com')
       driver.quit
  end

  it 'page load strategy none' do
      options = Selenium::WebDriver::Options.chrome
      options.page_load_strategy = :none

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get('https://www.google.com')
      driver.quit
  end
  end
end
