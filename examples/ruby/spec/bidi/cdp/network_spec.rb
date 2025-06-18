# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Network' do
  let(:driver) { start_session }

  it 'does basic authentication' do
    driver.register(username: 'admin',
                    password: 'admin',
                    uri: /herokuapp/)

    driver.get('https://the-internet.herokuapp.com/basic_auth')

    expect(driver.find_element(tag_name: 'p').text).to eq('Congratulations! You must have the proper credentials.')
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

  it 'sets cookie' do
    driver.devtools.network.set_cookie(name: 'cheese',
                                       value: 'gouda',
                                       domain: 'www.selenium.dev',
                                       secure: true)

    driver.get('https://www.selenium.dev')
    cheese = driver.manage.cookie_named('cheese')

    expect(cheese[:value]).to eq 'gouda'
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
