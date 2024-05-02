# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome' do
  describe 'Driver Options' do
    let(:chrome_location) { driver_finder && ENV.fetch('CHROME_BIN', nil) }
    let(:url) { 'https://www.selenium.dev/selenium/web/' }

    it 'page load strategy normal' do
      options = Selenium::WebDriver::Options.chrome
      options.page_load_strategy = :normal

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'page load strategy eager' do
      options = Selenium::WebDriver::Options.chrome
      options.page_load_strategy = :eager

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'page load strategy none' do
      options = Selenium::WebDriver::Options.chrome
      options.page_load_strategy = :none

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets remote capabilities', skip: 'this is example code that will not execute' do
      options = Selenium::WebDriver::Options.firefox
      options.platform_name = 'Windows 10'
      options.browser_version = 'latest'
      cloud_options = {}
      cloud_options[:build] = my_test_build
      cloud_options[:name] = my_test_name
      options.add_option('cloud:options', cloud_options)
      driver = Selenium::WebDriver.for :remote, capabilities: options
      driver.get(url)
      driver.quit
    end

    it 'accepts untrusted certificates' do
      options = Selenium::WebDriver::Options.chrome
      options.accept_insecure_certs = true

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets unhandled prompt behavior' do
      options = Selenium::WebDriver::Options.chrome
      options.unhandled_prompt_behavior = :accept

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets window rect' do
      options = Selenium::WebDriver::Options.firefox
      options.set_window_rect = true

      driver = Selenium::WebDriver.for :firefox, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets strict file interactability' do
      options = Selenium::WebDriver::Options.chrome
      options.strict_file_interactability = true

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets the proxy' do
      options = Selenium::WebDriver::Options.chrome
      options.proxy = Selenium::WebDriver::Proxy.new(http: 'myproxy.com:8080')

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets the implicit timeout' do
      options = Selenium::WebDriver::Options.chrome
      options.timeouts = {implicit: 1}

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets the page load timeout' do
      options = Selenium::WebDriver::Options.chrome
      options.timeouts = {page_load: 400_000}

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets the script timeout' do
      options = Selenium::WebDriver::Options.chrome
      options.timeouts = {script: 40_000}

      driver = Selenium::WebDriver.for :chrome, options: options
      driver.get(url)
      driver.quit
    end

    it 'sets capabilities in the pre-selenium 4 way', skip: 'this is example code that will not execute' do
      caps = Selenium::WebDriver::Remote::Capabilities.firefox
      caps[:platform] = 'Windows 10'
      caps[:version] = '92'
      caps[:build] = my_test_build
      caps[:name] = my_test_name
      driver = Selenium::WebDriver.for :remote, url: cloud_url, desired_capabilities: caps
      driver.get(url)
      driver.quit
    end
  end
end
