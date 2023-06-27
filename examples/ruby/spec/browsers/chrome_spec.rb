# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome' do
  describe 'Options' do
    it 'basic options' do
      options = Selenium::WebDriver::Options.chrome
      @driver = Selenium::WebDriver.for :chrome, options: options
    end

    it 'add arguments' do
      options = Selenium::WebDriver::Options.chrome
      options.args << '--maximize'

      @driver = Selenium::WebDriver.for :chrome, options: options
      @driver.get('https://www.google.com')
    end

    it 'keeps browser open' do
      options = Selenium::WebDriver::Options.chrome
      options.detach = true

      @driver = Selenium::WebDriver.for :chrome, options: options
      @driver.get('https://www.google.com')
    end

    it 'excludes switches' do
      options = Selenium::WebDriver::Options.chrome
      options.exclude_switches << 'enable-automation'

      @driver = Selenium::WebDriver.for :chrome, options: options
      @driver.get('https://www.google.com')
    end
  end

  describe 'Service' do
    let(:file_name) { File.expand_path('chromedriver.log') }

    after { FileUtils.rm_f(file_name) }

    it 'logs to file' do
      service = Selenium::WebDriver::Service.chrome
      service.log = file_name

      @driver = Selenium::WebDriver.for :chrome, service: service

      expect(File.readlines(file_name).first).to include('Starting ChromeDriver')
    end

    it 'logs to console' do
      service = Selenium::WebDriver::Service.chrome
      service.log = $stdout

      expect {
        @driver = Selenium::WebDriver.for :chrome, service: service
      }.to output(/Starting ChromeDriver/).to_stdout_from_any_process
    end

    it 'sets log level' do
      service = Selenium::WebDriver::Service.chrome
      service.log = file_name
      service.args << '--log-level=DEBUG'

      @driver = Selenium::WebDriver.for :chrome, service: service

      expect(File.readlines(file_name).grep(/\[DEBUG\]:/).any?).to eq true
    end

    it 'sets log features' do
      args = ["--log-path=#{file_name}", '--verbose']
      service = Selenium::WebDriver::Service.chrome(args: args)

      service.args << '--append-log'
      service.args << '--readable-timestamp'

      @driver = Selenium::WebDriver.for :chrome, service: service

      expect(File.readlines(file_name).grep(/\[\d\d-\d\d-\d\d\d\d/).any?).to eq true
    end

    it 'disables build checks' do
      service = Selenium::WebDriver::Service.chrome log: file_name, args: ['--verbose']

      service.args << '--disable-build-check'

      @driver = Selenium::WebDriver.for :chrome, service: service

      warning = /\[WARNING\]: You are using an unsupported command-line switch: --disable-build-check/
      expect(File.readlines(file_name).grep(warning).any?).to eq true
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
end
