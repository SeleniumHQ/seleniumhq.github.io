# frozen_string_literal: true
require 'selenium-webdriver'

RSpec.describe 'Using Selenium' do
  before do
    @driver = Selenium::WebDriver.for :chrome
  end

  after do
    @driver.quit
  end

  it 'uses eight components' do
    @driver.get('https://www.selenium.dev/selenium/web/web-form.html')

    title = @driver.title
    expect(title).to eq('Web form')

    @driver.manage.timeouts.implicit_wait = 500

    text_box = @driver.find_element(name: 'my-text')
    submit_button = @driver.find_element(tag_name: 'button')

    text_box.send_keys('Selenium')
    submit_button.click

    message = @driver.find_element(id: 'message')
    value = message.text
    expect(value).to eq('Received!')
  end
end
