# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Execute CDP' do
  let(:driver) { start_session }

  it 'sets cookie' do
    cookie = {name: 'cheese',
              value: 'gouda',
              domain: 'www.selenium.dev',
              secure: true}

    driver.execute_cdp('Network.setCookie', **cookie)

    driver.get('https://www.selenium.dev')
    cheese = driver.manage.cookie_named(cookie[:name])

    expect(cheese[:value]).to eq 'gouda'
  end

  it 'gets performance metrics' do
    driver.get('https://www.selenium.dev/selenium/web/frameset.html')

    driver.execute_cdp('Performance.enable')

    metric_list = driver.execute_cdp('Performance.getMetrics')['metrics']

    metrics = metric_list.each_with_object({}) do |metric, hash|
      hash[metric['name']] = metric['value']
    end

    expect(metrics['DevToolsCommandDuration']).to be > 0
    expect(metrics['Frames']).to eq 12
  end

  it 'sets basic authentication' do
    driver.execute_cdp('Network.enable')

    credentials = Base64.strict_encode64('admin:admin')
    headers = {authorization: "Basic #{credentials}"}

    driver.execute_cdp('Network.setExtraHTTPHeaders', headers: headers)

    driver.get('https://the-internet.herokuapp.com/basic_auth')

    expect(driver.find_element(tag_name: 'p').text).to eq('Congratulations! You must have the proper credentials.')
  end
end
