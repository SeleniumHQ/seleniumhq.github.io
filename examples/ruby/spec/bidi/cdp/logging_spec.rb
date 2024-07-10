# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Logging' do
  let(:driver) { start_session }

  it 'listens for console logs' do
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    logs = []
    driver.on_log_event(:console) { |log| logs << log.args.first }

    driver.find_element(id: 'consoleLog').click
    driver.find_element(id: 'consoleError').click

    Selenium::WebDriver::Wait.new.until { logs.size > 1 }
    expect(logs).to include 'Hello, world!'
    expect(logs).to include 'I am console error'
  end

  it 'listens for js exception' do
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    exceptions = []
    driver.on_log_event(:exception) { |exception| exceptions << exception }

    driver.find_element(id: 'jsException').click

    Selenium::WebDriver::Wait.new.until { exceptions.any? }
    expect(exceptions.first&.description).to include 'Error: Not working'
  end
end
