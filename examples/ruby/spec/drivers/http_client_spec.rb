require 'spec_helper'

RSpec.describe 'HTTP Client' do
  let(:url) { 'https://www.selenium.dev/selenium/web/' }

  it 'sets client configuration' do
    client = Selenium::WebDriver::Remote::Http::Default.new(open_timeout: 30, read_timeout: 30)
    expect(client.open_timeout).to eq 30
  end

  it 'uses the custom http client' do
    client = Selenium::WebDriver::Remote::Http::Default.new
    driver = Selenium::WebDriver.for :chrome, http_client: client
    driver.get(url)
    driver.quit
  end
end
