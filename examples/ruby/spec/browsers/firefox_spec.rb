# frozen_string_literal: true

require 'spec_helper'
require 'webdrivers'

RSpec.describe 'Firefox' do
  let(:driver) { start_firefox }

  it 'basic options' do
    options = Selenium::WebDriver::Options.firefox
    @driver = Selenium::WebDriver.for :firefox, options: options
  end

  it 'installs addon' do
    extension_file_path = File.expand_path('../extensions/webextensions-selenium-example.xpi', __dir__)
    driver.install_addon(extension_file_path)

    driver.get 'https://www.selenium.dev/selenium/web/blank.html'
    injected = driver.find_element(id: 'webextensions-selenium-example')
    expect(injected.text).to eq 'Content injected by webextensions-selenium-example'
  end

  it 'uninstalls addon' do
    extension_file_path = File.expand_path('../extensions/webextensions-selenium-example.xpi', __dir__)
    extension_id = driver.install_addon(extension_file_path)
    driver.uninstall_addon(extension_id)

    driver.get 'https://www.selenium.dev/selenium/web/blank.html'
    expect(driver.find_elements(id: 'webextensions-selenium-example')).to be_empty
  end

  it 'installs unsigned addon' do
    extension_dir_path = File.expand_path('../extensions/webextensions-selenium-example/', __dir__)
    driver.install_addon(extension_dir_path, true)

    driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    injected = driver.find_element(id: 'webextensions-selenium-example')
    expect(injected.text).to eq 'Content injected by webextensions-selenium-example'
  end
end
