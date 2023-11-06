# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome DevTools' do
  let(:driver) { start_session }

  it 'sets cookie' do
    driver.devtools.network.set_cookie(name: 'cheese',
                                       value: 'gouda',
                                       domain: 'www.selenium.dev',
                                       secure: true)

    driver.get('https://www.selenium.dev')
    cheese = driver.manage.cookie_named('cheese')

    expect(cheese[:value]).to eq 'gouda'
  end

  it 'gets performance metrics' do
    driver.get('https://www.selenium.dev/selenium/web/frameset.html')

    driver.devtools.performance.enable

    metric_list = driver.devtools.performance.get_metrics.dig('result', 'metrics')

    metrics = metric_list.each_with_object({}) do |metric, hash|
      hash[metric['name']] = metric['value']
    end

    expect(metrics['DevToolsCommandDuration']).to be > 0
    expect(metrics['Frames']).to eq 12
  end

  it 'does basic authentication' do
    driver.devtools.network.enable

    credentials = Base64.strict_encode64('admin:admin')

    driver.devtools.network.set_extra_http_headers(headers: {authorization: "Basic #{credentials}"})

    driver.get('https://the-internet.herokuapp.com/basic_auth')

    expect(driver.find_element(tag_name: 'p').text).to eq('Congratulations! You must have the proper credentials.')
  end

  it 'listens for console logs' do
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    driver.devtools.runtime.enable

    logs = []
    driver.devtools.runtime.on(:console_api_called) do |params|
      logs << params['args'].first['value']
    end

    driver.find_element(id: 'consoleLog').click

    Selenium::WebDriver::Wait.new.until { logs.any? }
    expect(logs).to include 'Hello, world!'
  end

  it 'waits for downloads', except: {platform: :windows} do
    driver.get('https://www.selenium.dev/selenium/web/downloads/download.html')

    driver.devtools.browser.set_download_behavior(behavior: 'allow',
                                                  download_path: '',
                                                  events_enabled: true)

    driver.devtools.browser.on(:download_progress) do |progress|
      @completed = progress['state'] == 'completed'
    end

    driver.find_element(id: 'file-2').click

    expect { Selenium::WebDriver::Wait.new.until { @completed } }.not_to raise_exception
  end
end
