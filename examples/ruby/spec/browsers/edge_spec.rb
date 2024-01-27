# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Edge' do
  describe 'Options' do
    let(:edge_location) { driver_finder && ENV.fetch('EDGE_BIN', nil) }

    it 'basic options' do
      options = Selenium::WebDriver::Options.edge
      @driver = Selenium::WebDriver.for :edge, options: options
    end

    it 'add arguments' do
      options = Selenium::WebDriver::Options.edge

      options.args << '--start-maximized'

      @driver = Selenium::WebDriver.for :edge, options: options
    end

    it 'sets location of binary' do
      options = Selenium::WebDriver::Options.edge

      options.binary = edge_location

      @driver = Selenium::WebDriver.for :edge, options: options
    end

    it 'add extensions' do
      extension_file_path = File.expand_path('../spec_support/extensions/webextensions-selenium-example.crx', __dir__)
      options = Selenium::WebDriver::Options.chrome

      options.add_extension(extension_file_path)

      @driver = Selenium::WebDriver.for :chrome, options: options
      @driver.get('https://www.selenium.dev/selenium/web/blank.html')
      injected = @driver.find_element(:id, 'webextensions-selenium-example')
      expect(injected.text).to eq 'Content injected by webextensions-selenium-example'
    end

    it 'keeps browser open' do
      options = Selenium::WebDriver::Options.edge

      options.detach = true

      @driver = Selenium::WebDriver.for :edge, options: options
    end

    it 'excludes switches' do
      options = Selenium::WebDriver::Options.edge

      options.exclude_switches << 'disable-popup-blocking'

      @driver = Selenium::WebDriver.for :edge, options: options
    end
  end

  describe 'Service' do
    let(:file_name) { File.expand_path('msedgedriver.log') }

    after { FileUtils.rm_f(file_name) }

    it 'logs to file' do
      service = Selenium::WebDriver::Service.edge

      service.log = file_name

      @driver = Selenium::WebDriver.for :edge, service: service
      expect(File.readlines(file_name).first).to include('Starting Microsoft Edge WebDriver')
    end

    it 'logs to console' do
      service = Selenium::WebDriver::Service.edge

      service.log = $stdout

      expect {
        @driver = Selenium::WebDriver.for :edge, service: service
      }.to output(/Starting Microsoft Edge WebDriver/).to_stdout_from_any_process
    end

    it 'sets log level' do
      service = Selenium::WebDriver::Service.edge
      service.log = file_name

      service.args << '--log-level=DEBUG'

      @driver = Selenium::WebDriver.for :edge, service: service
      expect(File.readlines(file_name).grep(/\[DEBUG\]:/).any?).to eq true
    end

    it 'sets log features' do
      args = ["--log-path=#{file_name}", '--verbose']
      service = Selenium::WebDriver::Service.edge(args: args)

      service.args << '--append-log'
      service.args << '--readable-timestamp'

      @driver = Selenium::WebDriver.for :edge, service: service

      expect(File.readlines(file_name).grep(/\[\d\d-\d\d-\d\d\d\d/).any?).to eq true
    end

    it 'disables build checks' do
      service = Selenium::WebDriver::Service.edge log: file_name, args: ['--verbose']

      service.args << '--disable-build-check'

      @driver = Selenium::WebDriver.for :edge, service: service
      warning = /\[WARNING\]: You are using an unsupported command-line switch: --disable-build-check/
      expect(File.readlines(file_name).grep(warning).any?).to eq true
    end
  end

  def driver_finder
    options = Selenium::WebDriver::Options.edge(browser_version: 'stable')
    ENV['EDGEDRIVER_BIN'] = Selenium::WebDriver::DriverFinder.path(options, Selenium::WebDriver::Edge::Service)
    ENV['EDGE_BIN'] = options.binary
  end
end
