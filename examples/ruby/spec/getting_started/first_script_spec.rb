# frozen_string_literal: true

RSpec.describe 'First Script' do
  it 'uses eight components' do
    driver = Selenium::WebDriver.for :chrome

    driver.get('https://google.com')

    expect(driver.title).to eq('Google')

    driver.manage.timeouts.implicit_wait = 500

    search_box = driver.find_element(name: 'q')
    search_button = driver.find_element(name: 'btnK')

    search_box.send_keys('Selenium')
    search_button.click

    search_box = driver.find_element(name: 'q')
    expect(attribute('value')).to eq('Selenium')

    driver.quit
  end
end
