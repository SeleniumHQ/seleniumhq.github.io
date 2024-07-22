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
      options = Selenium::WebDriver::Options.edge

      options.add_extension(extension_file_path)

      @driver = Selenium::WebDriver.for :edge, options: options
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

  describe 'Special Features' do
    it 'casts' do
      @driver = Selenium::WebDriver.for :edge
      sinks = @driver.cast_sinks
      unless sinks.empty?
        device_name = sinks.first['name']
        @driver.start_cast_tab_mirroring(device_name)
        expect { @driver.stop_casting(device_name) }.not_to raise_exception
      end
    end

    it 'gets and sets network conditions' do
      @driver = Selenium::WebDriver.for :edge
      @driver.network_conditions = {offline: false, latency: 100, throughput: 200}
      expect(@driver.network_conditions).to eq(
        'offline' => false,
        'latency' => 100,
        'download_throughput' => 200,
        'upload_throughput' => 200)
    end

    it 'gets the browser logs' do
      @driver = Selenium::WebDriver.for :edge
      @driver.navigate.to 'https://www.selenium.dev/selenium/web/'
      sleep 1
      logs = @driver.logs.get(:browser)

      expect(logs.first.message).to include 'Failed to load resource'
    end

    it 'sets permissions' do
      @driver = Selenium::WebDriver.for :edge
      @driver.navigate.to 'https://www.selenium.dev/selenium/web/'
      @driver.add_permission('camera', 'denied')
      @driver.add_permissions('clipboard-read' => 'denied', 'clipboard-write' => 'prompt')
      expect(permission('camera')).to eq('denied')
      expect(permission('clipboard-read')).to eq('denied')
      expect(permission('clipboard-write')).to eq('prompt')
    end
  end

  def driver_finder
    options = Selenium::WebDriver::Options.edge(browser_version: 'stable')
    service = Selenium::WebDriver::Service.edge
    finder = Selenium::WebDriver::DriverFinder.new(options, service)
    ENV['EDGEDRIVER_BIN'] = finder.driver_path
    ENV['EDGE_BIN'] = finder.browser_path
  end

  def permission(name)
    @driver.execute_async_script('callback = arguments[arguments.length - 1];' \
                                   'callback(navigator.permissions.query({name: arguments[0]}));', name)['state']
  end
end
