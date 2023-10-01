# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'BiDi API' do
  let(:driver) { start_session }

  it 'does basic authentication' do
    driver.register(username: 'admin',
                    password: 'admin',
                    uri: /herokuapp/)

    driver.get('https://the-internet.herokuapp.com/basic_auth')

    expect(driver.find_element(tag_name: 'p').text).to eq('Congratulations! You must have the proper credentials.')
  end

  it 'pins script' do
    driver.get('https://www.selenium.dev/selenium/web/javascriptPage.html')
    is_displayed_script = Class.new.extend(Selenium::WebDriver::Atoms).atom_script(:isDisplayed)

    script = driver.pin_script(is_displayed_script)

    visible = driver.find_element(id: 'visibleSubElement')
    hidden = driver.find_element(id: 'hiddenlink')

    visible_displayed = driver.execute_script(script, visible)
    hidden_displayed = driver.execute_script(script, hidden)

    expect(visible_displayed).to eq true
    expect(hidden_displayed).to eq false
  end

  it 'notifies about DOM mutations' do
    driver.get 'https://www.selenium.dev/selenium/web/dynamic.html'

    mutations = []
    driver.on_log_event(:mutation) { |mutation| mutations << mutation }

    driver.find_element(id: 'reveal').click
    Selenium::WebDriver::Wait.new.until { mutations.any? }

    expect(mutations.first&.element).to eq(driver.find_element(id: 'revealed'))
  end

  it 'listens for console logs' do
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    logs = []
    driver.on_log_event(:console) { |log| logs << log }

    driver.find_element(id: 'consoleLog').click

    Selenium::WebDriver::Wait.new.until { logs.any? }
    expect(logs.first&.args).to include 'Hello, world!'
  end

  it 'listens for console error' do
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    logs = []
    driver.on_log_event(:console) { |log| logs << log }

    driver.find_element(id: 'consoleError').click

    Selenium::WebDriver::Wait.new.until { logs.any? }
    expect(logs.first&.args).to include 'I am console error'
  end

  it 'listens for js errors' do
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    errors = []
    driver.on_log_event(:exception) { |error| errors << error }

    driver.find_element(id: 'jsException').click

    Selenium::WebDriver::Wait.new.until { errors.any? }
    expect(errors.first&.description).to include 'Error: Not working'
  end

  it 'intercepts network response' do
    driver.intercept do |request, &continue|
      continue.call(request) do |response|
        response.body = 'Creamy, delicious cheese!' if request.url.include?('blank')
      end
    end

    driver.get('https://www.selenium.dev/selenium/web/blank.html')
    expect(driver.find_element(tag_name: 'body').text).to eq('Creamy, delicious cheese!')
  end

  it 'intercepts network request' do
    driver.intercept do |request, &continue|
      uri = URI(request.url)
      request.url = uri.to_s.gsub('one', 'two') if uri.path&.end_with?('one.js')
      continue.call(request)
    end

    driver.get('https://www.selenium.dev/selenium/web/devToolsRequestInterceptionTest.html')
    driver.find_element(tag_name: 'button').click
    expect(driver.find_element(id: 'result').text).to eq('two')
  end
end
