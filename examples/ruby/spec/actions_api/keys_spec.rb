# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Keys' do
  let(:driver) { start_session }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 2) }

  it 'key down' do
    driver.get 'https://www.selenium.dev/selenium/web/single_text_input.html'
    wait.until { driver.find_element(id: 'textInput').attribute('autofocus') }

    driver.action
          .key_down(:shift)
          .send_keys('a')
          .perform

    expect(driver.find_element(id: 'textInput').attribute('value')).to eq 'A'
  end

  it 'key up' do
    driver.get 'https://www.selenium.dev/selenium/web/single_text_input.html'
    wait.until { driver.find_element(id: 'textInput').attribute('autofocus') }

    driver.action
          .key_down(:shift)
          .send_keys('a')
          .key_up(:shift)
          .send_keys('b')
          .perform

    expect(driver.find_element(id: 'textInput').attribute('value')).to eq 'Ab'
  end

  it 'sends keys to active element' do
    driver.get 'https://www.selenium.dev/selenium/web/single_text_input.html'
    wait.until { driver.find_element(id: 'textInput').attribute('autofocus') }

    driver.action
          .send_keys('abc')
          .perform

    expect(driver.find_element(id: 'textInput').attribute('value')).to eq 'abc'
  end

  it 'sends keys to designated element' do
    driver.get 'https://www.selenium.dev/selenium/web/single_text_input.html'
    driver.find_element(tag_name: 'body').click
    wait.until { driver.find_element(id: 'textInput').attribute('autofocus') }

    text_field = driver.find_element(id: 'textInput')
    driver.action
          .send_keys(text_field, 'Selenium!')
          .perform

    expect(text_field.attribute('value')).to eq 'Selenium!'
  end

  it 'copy and paste' do
    driver.get 'https://www.selenium.dev/selenium/web/single_text_input.html'
    wait.until { driver.find_element(id: 'textInput').attribute('autofocus') }

    cmd_ctrl = driver.capabilities.platform_name.include?('mac') ? :command : :control
    driver.action
          .send_keys('Selenium!')
          .send_keys(:arrow_left)
          .key_down(:shift)
          .send_keys(:arrow_up)
          .key_up(:shift)
          .key_down(cmd_ctrl)
          .send_keys('xvv')
          .key_up(cmd_ctrl)
          .perform

    expect(driver.find_element(id: 'textInput').attribute('value')).to eq 'SeleniumSelenium!'
  end
end
