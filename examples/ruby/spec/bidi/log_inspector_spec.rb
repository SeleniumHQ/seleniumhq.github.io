# frozen_string_literal: true

require 'selenium-webdriver'

RSpec.describe 'Log Inspector' do
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 2) }
  let(:driver) do
    options = Selenium::WebDriver::Options.firefox
    options.add_option(:web_socket_url, true)
    Selenium::WebDriver.for :firefox, options: options
  end

  after do
    driver.quit
  end

  it 'test listen to console log' do
    log_entry = nil
    log_inspector = Selenium::WebDriver::BiDi::LogInspector.new(driver)
    log_inspector.on_console_entry { |log| log_entry = log }

    driver.get 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    driver.find_element(id: 'consoleLog').click
    wait.until { !log_entry.nil? }

    expect(log_entry).to have_attributes(
      text: 'Hello, world!',
      realm: nil,
      type: 'console',
      level: Selenium::WebDriver::BiDi::LogInspector::LOG_LEVEL[:INFO],
      method: 'log',
      stack_trace: nil
    )
    expect(log_entry.args.size).to eq(1)
  end

  it 'test listen to javascript log' do
    log_entry = nil
    log_inspector = Selenium::WebDriver::BiDi::LogInspector.new(driver)
    log_inspector.on_javascript_log { |log| log_entry = log }

    driver.get 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    driver.find_element(id: 'jsException').click
    wait.until { !log_entry.nil? }

    expect(log_entry).to have_attributes(
      text: 'Error: Not working',
      type: 'javascript',
      level: Selenium::WebDriver::BiDi::LogInspector::LOG_LEVEL[:ERROR]
    )
  end

  it 'test listen to javascript error log' do
    log_entry = nil
    log_inspector = Selenium::WebDriver::BiDi::LogInspector.new(driver)
    log_inspector.on_javascript_exception { |log| log_entry = log }

    driver.get 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    driver.find_element(id: 'jsException').click
    wait.until { !log_entry.nil? }

    expect(log_entry).to have_attributes(
      text: 'Error: Not working',
      type: 'javascript'
    )
  end

  it 'test retrieve stack trace for a log' do
    log_entry = nil
    log_inspector = Selenium::WebDriver::BiDi::LogInspector.new(driver)
    log_inspector.on_javascript_exception { |log| log_entry = log }

    driver.get 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    driver.find_element(id: 'jsException').click
    wait.until { !log_entry.nil? }

    stack_trace = log_entry.stack_trace

    expect(stack_trace).not_to be_nil
    expect(stack_trace['callFrames'].size).to eq(3)
  end

  it 'test listen to logs with multiple consumers' do
    log_entry1 = nil
    log_entry2 = nil
    log_inspector = Selenium::WebDriver::BiDi::LogInspector.new(driver)
    log_inspector.on_javascript_exception { |log| log_entry1 = log }
    log_inspector.on_javascript_exception { |log| log_entry2 = log }

    driver.get 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    driver.find_element(id: 'jsException').click
    wait.until { !log_entry1.nil? }
    wait.until { !log_entry2.nil? }

    expect(log_entry1).to have_attributes(
      text: 'Error: Not working',
      type: 'javascript'
    )

    expect(log_entry2).to have_attributes(
      text: 'Error: Not working',
      type: 'javascript'
    )
  end
end
