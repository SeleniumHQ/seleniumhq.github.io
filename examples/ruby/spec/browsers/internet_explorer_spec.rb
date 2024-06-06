# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Internet Explorer', exclusive: { platform: :windows } do
  describe 'Options' do
    let(:edge_location) { ENV.fetch('EDGE_BIN', nil) }
    let(:url) { 'https://www.selenium.dev/selenium/web/' }

    before do
      @options = Selenium::WebDriver::IE::Options.new
      @options.attach_to_edge_chrome = true
      @options.edge_executable_path = edge_location
    end

    it 'basic options Win10' do
      options = Selenium::WebDriver::IE::Options.new
      options.attach_to_edge_chrome = true
      options.edge_executable_path = edge_location
      @driver = Selenium::WebDriver.for :ie, options: options
    end

    it 'basic options Win11' do
      options = Selenium::WebDriver::Options.ie
      @driver = Selenium::WebDriver.for :ie, options: options
    end

    it 'sets the file upload dialog timeout' do
      @options.file_upload_dialog_timeout = 2000
      driver = Selenium::WebDriver.for(:ie, options: @options)
      driver.quit
    end

    it 'ensures a clean session' do
      @options.ensure_clean_session = true
      driver = Selenium::WebDriver.for(:ie, options: @options)
      driver.quit
    end

    it 'ignores the zoom setting' do
      @options.ignore_zoom_level = true
      driver = Selenium::WebDriver.for(:ie, options: @options)
      driver.quit
    end

    it 'ignores the protected mode settings' do
      @options.ignore_protected_mode_settings = true
      driver = Selenium::WebDriver.for(:ie, options: @options)
      driver.quit
    end

    it 'adds the silent option' do
      @options.add_option('silent', {silent: true})
      Selenium::WebDriver.for(:ie, options: @options)
      expect(@options.instance_variable_get(:@options)['silent']).to eq({silent: true})
    end

    it 'sets the command line options' do
      @options.add_argument('-k')
      Selenium::WebDriver.for(:ie, options: @options)
    end

    it 'launches ie with the create process api' do
      @options.force_create_process_api = true
      Selenium::WebDriver.for(:ie, options: @options)
      expect(@options.instance_variable_get(:@options)['force_create_process_api'])
        .to eq({force_create_process_api: true})
    end
  end

  describe 'Service' do
    let(:file_name) { Tempfile.new('iedriver').path }
    let(:root_directory) { Dir.mktmpdir }

    after do
      FileUtils.rm_f(file_name)
      FileUtils.remove_entry root_directory
    end

    it 'logs to file' do
      service = Selenium::WebDriver::Service.ie

      service.log = file_name

      @driver = Selenium::WebDriver.for :ie, service: service
      expect(File.readlines(file_name).first).to include('Started InternetExplorerDriver server')
    end

    it 'logs to console' do
      service = Selenium::WebDriver::Service.ie

      service.log = $stdout

      expect {
        @driver = Selenium::WebDriver.for :ie, service: service
      }.to output(/Started InternetExplorerDriver server/).to_stdout_from_any_process
    end

    it 'sets log level' do
      service = Selenium::WebDriver::Service.ie
      service.log = $stdout

      service.args << '-log-level=WARN'

      expect {
        @driver = Selenium::WebDriver.for :ie, service: service
      }.to output(/Invalid capability setting: timeouts is type null/).to_stdout_from_any_process
    end

    it 'sets location for supporting files' do
      service = Selenium::WebDriver::Service.ie

      service.args << "–extract-path=#{root_directory}"

      @driver = Selenium::WebDriver.for :ie, service: service
    end
  end
end
