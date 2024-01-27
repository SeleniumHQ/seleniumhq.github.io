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
    driver.get('https://www.selenium.dev/selenium/web/xhtmlTest.html')
    element = driver.find_element(id: 'id1')

    key = driver.pin_script('return arguments;')
    arguments = driver.execute_script(key, 1, true, element)

    expect(arguments).to eq([1, true, element])
  end

  it 'gets mutated elements' do
    driver.get 'https://www.selenium.dev/selenium/web/dynamic.html'

    mutations = []
    driver.on_log_event(:mutation) { |mutation| mutations << mutation.element }

    driver.find_element(id: 'reveal').click
    Selenium::WebDriver::Wait.new(timeout: 30).until { mutations.any? }

    expect(mutations).to include(driver.find_element(id: 'revealed'))
  end

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

  it 'records network response' do
    content_type = []
    driver.intercept do |request, &continue|
      continue.call(request) do |response|
        content_type << response.headers['content-type']
      end
    end

    driver.get('https://www.selenium.dev/selenium/web/blank.html')
    expect(content_type.first).to eq('text/html; charset=utf-8')
  end

  it 'transforms network response' do
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
