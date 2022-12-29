# frozen_string_literal: true

require 'selenium-webdriver'

RSpec.describe 'Integration Tests' do
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 2) }
  let(:driver) do
    options = Selenium::WebDriver::Options.firefox
    options.add_option(:web_socket_url, true)
    Selenium::WebDriver.for :firefox, options: options
  end

  after do
    driver.quit
  end

  it 'test navigate and listen to errors' do
    log_entry = nil
    log_inspector = Selenium::WebDriver::BiDi::LogInspector.new(driver)
    log_inspector.on_javascript_exception { |log| log_entry = log }

    id = driver.window_handle
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, browsing_context_id: id)

    info = browsing_context.navigate url: 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    expect(browsing_context.id).not_to be_nil
    expect(info.navigation_id).to be_nil
    expect(info.url).to include('/bidi/logEntryAdded.html')

    wait.until { driver.find_element(id: 'jsException').displayed? }
    driver.find_element(id: 'jsException').click
    wait.until { !log_entry.nil? }

    expect(log_entry).to have_attributes(
      text: 'Error: Not working',
      type: 'javascript',
      level: Selenium::WebDriver::BiDi::LogInspector::LOG_LEVEL[:ERROR]
    )
  end
end
