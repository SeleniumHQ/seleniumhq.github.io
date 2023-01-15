# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome' do
  let(:driver) { start_session }

  it 'basic options' do
    options = Selenium::WebDriver::Options.chrome
    @driver = Selenium::WebDriver.for :chrome, options: options
  end

  it 'add arguments' do
    options = Selenium::WebDriver::Options.chrome(args: ['--headless=chrome'])

    @driver = Selenium::WebDriver.for :chrome, options: options
    @driver.get('https://www.google.com')
  end

  it 'Keep browser open' do
    options = Selenium::WebDriver::Options.chrome(detach: true)

    @driver = Selenium::WebDriver.for :chrome, options: options
    @driver.get('https://www.google.com')
  end
end
