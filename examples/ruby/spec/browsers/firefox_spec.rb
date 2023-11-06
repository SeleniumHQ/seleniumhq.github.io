# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Firefox' do
  describe 'Options' do
    let(:firefox_location) { driver_finder && ENV.fetch('FIREFOX_BIN', nil) }

    it 'basic options' do
      options = Selenium::WebDriver::Options.firefox
      @driver = Selenium::WebDriver.for :firefox, options: options
    end

    it 'add arguments' do
      options = Selenium::WebDriver::Options.firefox

      options.args << '-headless'

      @driver = Selenium::WebDriver.for :firefox, options: options
    end

    it 'sets location of binary' do
      options = Selenium::WebDriver::Options.firefox

      options.binary = firefox_location

      @driver = Selenium::WebDriver.for :firefox, options: options
    end
  end

  describe 'Service' do
    let(:file_name) { Tempfile.new('geckodriver').path }
    let(:root_directory) { Dir.mktmpdir }

    after do
      FileUtils.rm_f(file_name)
      FileUtils.rm_rf(root_directory)
    end

    it 'logs to file' do
      service = Selenium::WebDriver::Service.firefox

      service.log = file_name

      @driver = Selenium::WebDriver.for :firefox, service: service
      expect(File.readlines(file_name).first).to include("geckodriver\tINFO\tListening on")
    end

    it 'logs to console' do
      service = Selenium::WebDriver::Service.firefox

      service.log = $stdout

      expect {
        @driver = Selenium::WebDriver.for :firefox, service: service
      }.to output(/geckodriver	INFO	Listening on/).to_stdout_from_any_process
    end

    it 'sets log level' do
      service = Selenium::WebDriver::Service.firefox
      service.log = file_name

      service.args += %w[--log debug]

      @driver = Selenium::WebDriver.for :firefox, service: service
      expect(File.readlines(file_name).grep(/Marionette	DEBUG/).any?).to eq true
    end

    it 'stops truncating log lines' do
      service = Selenium::WebDriver::Service.firefox(log: file_name, args: %w[--log debug])

      service.args << '--log-no-truncate'

      @driver = Selenium::WebDriver.for :firefox, service: service
      expect(File.readlines(file_name).grep(/ \.\.\. /).any?).to eq false
    end

    it 'sets default profile location' do
      service = Selenium::WebDriver::Service.firefox

      service.args += ['--profile-root', root_directory]

      @driver = Selenium::WebDriver.for :firefox, service: service
      profile_location = Dir.new(@driver.capabilities['moz:profile'])
      expect(profile_location.path.gsub('\\', '/')).to include(root_directory)
    end
  end

  describe 'Features' do
    let(:driver) { start_firefox }

    it 'installs addon' do
      extension_file_path = File.expand_path('../spec_support/extensions/webextensions-selenium-example.xpi', __dir__)

      driver.install_addon(extension_file_path)

      driver.get 'https://www.selenium.dev/selenium/web/blank.html'
      injected = driver.find_element(id: 'webextensions-selenium-example')
      expect(injected.text).to eq 'Content injected by webextensions-selenium-example'
    end

    it 'uninstalls addon' do
      extension_file_path = File.expand_path('../spec_support/extensions/webextensions-selenium-example.xpi', __dir__)
      extension_id = driver.install_addon(extension_file_path)

      driver.uninstall_addon(extension_id)

      driver.get 'https://www.selenium.dev/selenium/web/blank.html'
      expect(driver.find_elements(id: 'webextensions-selenium-example')).to be_empty
    end

    it 'installs unsigned addon' do
      extension_dir_path = File.expand_path('../spec_support/extensions/webextensions-selenium-example/', __dir__)

      driver.install_addon(extension_dir_path, true)

      driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
      injected = driver.find_element(id: 'webextensions-selenium-example')
      expect(injected.text).to eq 'Content injected by webextensions-selenium-example'
    end
  end

  def driver_finder
    options = Selenium::WebDriver::Options.firefox(browser_version: 'stable')
    ENV['GECKODRIVER_BIN'] = Selenium::WebDriver::DriverFinder.path(options, Selenium::WebDriver::Firefox::Service)
    ENV['FIREFOX_BIN'] = options.binary
  end
end
