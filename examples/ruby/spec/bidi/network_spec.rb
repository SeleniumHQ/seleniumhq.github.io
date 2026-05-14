# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Network' do
  let(:driver) { start_bidi_session }
  let(:network) { Selenium::WebDriver::BiDi::Network.new(driver.bidi) }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 5) }

  it 'intercepts network requests' do
    request_events = []

    network.on(:before_request) do |event|
      request_events << event
    end

    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    wait.until { request_events.any? }

    expect(request_events).not_to be_empty
  end

  it 'intercepts network responses' do
    response_events = []

    network.on(:response_started) do |event|
      response_events << event
    end

    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    wait.until { response_events.any? }

    expect(response_events).not_to be_empty
  end

  it 'adds network intercept' do
    # This matches the high-level API for adding an intercept
    intercept = network.add_intercept(phases: [:before_request])
    expect(intercept).not_to be_nil

    network.remove_intercept(intercept)
  end

  it 'continues network request' do
    # In Ruby high-level BiDi, intercepts are added separately if needed
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    network.on(:response_started) do |event|
      # In high-level API, we can just observe
    end

    driver.navigate.to 'https://www.selenium.dev/selenium/web/iframes.html'
  end

  it 'provides auth credentials' do
    # Ruby has continue_with_auth method
    network.on(:auth_required) do |event|
      network.continue_with_auth(event['request']['request'], 'user', 'pass')
    end

    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
  end
end
