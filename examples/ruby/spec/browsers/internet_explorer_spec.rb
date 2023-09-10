# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Internet Explorer', exclusive: {platform: :windows} do
  describe 'Options' do
    let(:edge_location) { ENV.fetch('EDGE_BIN', nil) }

    it 'basic options Win10' do
      options = Selenium::WebDriver::Options.ie
      options.attach_to_edge_chrome = true
      options.edge_executable_path = edge_location
      @driver = Selenium::WebDriver.for :ie, options: options
    end

    it 'basic options Win11' do
      options = Selenium::WebDriver::Options.ie
      @driver = Selenium::WebDriver.for :ie, options: options
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
