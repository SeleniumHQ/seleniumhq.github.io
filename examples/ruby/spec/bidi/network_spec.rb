# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Network', exclusive: {bidi: true, reason: 'only executed when bidi is enabled'},
                          only: {browser: %i[chrome edge firefox]} do
  let(:driver) { start_bidi_session }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 2) }

  it 'adds an auth handler', skip: 'Do not execute BiDi test' do
    driver.network.add_authentication_handler('test', 'test')
    driver.navigate.to url_for('basicAuth')
    expect(driver.find_element(tag_name: 'h1').text).to eq('authorized')
  end

  it 'intercepts network requests' do
    request_events = []
    
    driver.bidi_connection.add_network_request_listener do |event|
      request_events << event
    end
    
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    
    wait.until { request_events.any? }
    
    expect(request_events).not_to be_empty
  end

  it 'intercepts network responses' do
    response_events = []
    
    driver.bidi_connection.add_network_response_listener do |event|
      response_events << event
    end
    
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    
    wait.until { response_events.any? }
    
    expect(response_events).not_to be_empty
  end

  it 'continues network request' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    
    driver.bidi_connection.add_network_response_listener do |event|
      request_id = event['request']['request']
      driver.bidi_connection.bidi_session.network.continue_response(request: request_id)
    end
    
    driver.navigate.to 'https://www.selenium.dev/selenium/web/iframes.html'
  end
end
