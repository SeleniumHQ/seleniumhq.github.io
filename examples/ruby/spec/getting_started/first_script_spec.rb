# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'First Script' do
  it 'uses eight components' do
    driver = Selenium::WebDriver.for :chrome

    driver.get('https://www.selenium.dev/selenium/web/web-form.html')

    title = driver.title
    expect(title).to eq('Web form')

    driver.manage.timeouts.implicit_wait = 500

    text_box = driver.find_element(name: 'my-text')
    submit_button = driver.find_element(tag_name: 'button')

    text_box.send_keys('Selenium')
    submit_button.click

    message = driver.find_element(id: 'message')
    value = message.text
    expect(value).to eq('Received!')

    driver.quit
  end
end
