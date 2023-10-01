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
    driver.devtools.runtime.enable

    messages = []
    driver.devtools.runtime.on(:console_api_called) do |params|
      messages << params['args'].first['value']
    end

    driver.execute_script("console.log('I love cheese')")
    expect(messages).to include 'I love cheese'
  end

  it 'waits for downloads' do
    driver.get('https://www.selenium.dev/selenium/web/downloads/download.html')

    driver.devtools.browser.set_download_behavior(behavior: 'allow',
                                                  download_path: '',
                                                  events_enabled: true)

    driver.devtools.browser.on(:download_progress) do |progress|
      @completed ||= progress['state'] == 'completed'
    end

    driver.find_element(id: 'file-2').click

    expect { Selenium::WebDriver::Wait.new.until { @completed } }.not_to raise_exception
  end

  it 'waits for page to load' do
    driver.devtools.network.enable
    requests = {}
    driver.devtools.network.on(:request_will_be_sent) { |r| requests[r['requestId']] = r }
    driver.devtools.network.on(:response_received) { |r| requests.delete(r['requestId']) }

    driver.get 'https://www.nytimes.com'

    start_time = quiet_time = Time.now

    while (Time.now - quiet_time) < 2 && (Time.now - start_time) < 10
      quiet_time = requests.empty? ? sleep(0.1) && quiet_time : Time.now
    end
  end
end
