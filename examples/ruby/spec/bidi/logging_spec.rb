require 'spec_helper'

RSpec.describe 'Logging' do
  let(:driver) { start_bidi_session }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 2) }

  it 'adds console message handler' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    log_entries = []

    driver.script.add_console_message_handler { |log| log_entries << log }

    driver.find_element(id: 'consoleLog').click
    wait.until { log_entries.any? }
    expect(log_entries.first&.text).to eq 'Hello, world!'
  end

  it 'removes console message handler' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    log_entries = []

    id = driver.script.add_console_message_handler { |log| log_entries << log }
    driver.script.remove_console_message_handler(id)

    driver.find_element(id: 'consoleLog').click
    expect(log_entries).to be_empty
  end

  it 'adds JavaScript error handler' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    log_entries = []

    driver.script.add_javascript_error_handler { |error| log_entries << error }

    driver.find_element(id: 'jsException').click
    wait.until { log_entries.any? }
    expect(log_entries.first&.text).to eq 'Error: Not working'
  end

  it 'removes JavaScript error handler' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    log_entries = []

    id = driver.script.add_javascript_error_handler { |error| log_entries << error }
    driver.script.remove_javascript_error_handler(id)

    driver.find_element(id: 'jsException').click
    expect(log_entries).to be_empty
  end
end
