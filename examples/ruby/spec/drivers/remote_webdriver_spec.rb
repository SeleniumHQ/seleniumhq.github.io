# frozen_string_literal: true

require 'spec_helper'
require 'selenium/server'

RSpec.describe 'Remote WebDriver' do
  let(:target_directory) { File.join(Dir.tmpdir, SecureRandom.uuid) }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 2) }
  let(:server) do
    Selenium::Server.get(:latest,
                         background: true,
                         args: %w[--selenium-manager true --enable-managed-downloads true])
  end
  let(:grid_url) { server.webdriver_url }

  before { server.start }
  after { server.stop }

  it 'starts remotely' do
    options = Selenium::WebDriver::Options.chrome
    driver = Selenium::WebDriver.for :remote, url: grid_url, options: options

    expect { driver.session_id }.not_to raise_exception
  end

  it 'uploads' do
    options = Selenium::WebDriver::Options.chrome
    driver = Selenium::WebDriver.for :remote, url: server.webdriver_url, options: options

    driver.get('https://the-internet.herokuapp.com/upload')
    upload_file = File.expand_path('../spec_support/selenium-snapshot.png', __dir__)

    driver.file_detector = ->((filename, *)) { filename.include?('selenium') && filename }
    file_input = driver.find_element(css: 'input[type=file]')
    file_input.send_keys(upload_file)
    driver.find_element(id: 'file-submit').click

    file_name = driver.find_element(id: 'uploaded-files')
    expect(file_name.text).to eq 'selenium-snapshot.png'
  end

  it 'downloads' do
    options = Selenium::WebDriver::Options.chrome(enable_downloads: true)
    driver = Selenium::WebDriver.for :remote, url: grid_url, options: options

    file_names = %w[file_1.txt file_2.jpg]
    driver.get('https://www.selenium.dev/selenium/web/downloads/download.html')
    driver.find_element(id: 'file-1').click
    driver.find_element(id: 'file-2').click
    wait.until { driver.downloadable_files.include? 'file_2.jpg' }

    files = driver.downloadable_files

    expect(files.sort).to eq file_names.sort
    downloadable_file = files.first

    driver.download_file(downloadable_file, target_directory)

    file_content = File.read("#{target_directory}/#{downloadable_file}").strip
    expect(file_content).to eq('Hello, World!')

    driver.delete_downloadable_files

    expect(driver.downloadable_files).to be_empty
  end
end
