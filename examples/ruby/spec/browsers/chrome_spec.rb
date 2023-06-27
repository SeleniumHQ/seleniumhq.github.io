# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome' do
  let(:driver) { start_session }

  it 'basic options' do
    options = Selenium::WebDriver::Options.chrome
    @driver = Selenium::WebDriver.for :chrome, options: options
  end

  it 'add arguments' do
    options = Selenium::WebDriver::Options.chrome(args: ['--headless=new'])

    @driver = Selenium::WebDriver.for :chrome, options: options
    @driver.get('https://www.selenium.dev')
  end

  it 'Keep browser open' do
    options = Selenium::WebDriver::Options.chrome(detach: true)

    @driver = Selenium::WebDriver.for :chrome, options: options
    @driver.get('https://www.selenium.dev')
  end

  it 'Exclude switches' do
    options = Selenium::WebDriver::Options.chrome(exclude_switches: ['enable-automation'])

    @driver = Selenium::WebDriver.for :chrome, options: options
    @driver.get('https://www.selenium.dev')
  end

  it 'Add extensions' do
    extension_file_path = File.expand_path('../extensions/webextensions-selenium-example.crx', __dir__)
    options = Selenium::WebDriver::Options.chrome
    options.add_extension(extension_file_path)

    @driver = Selenium::WebDriver.for :chrome, options: options
    @driver.get("https://www.selenium.dev/selenium/web/blank.html");
    injected = @driver.find_element(:id, 'webextensions-selenium-example')
    expect(injected.text).to eq 'Content injected by webextensions-selenium-example'
  end
end
