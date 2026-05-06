# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Input' do
  let(:driver) { start_bidi_session }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 5) }

  it 'sends keyboard input' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    input_field = driver.find_element(id: 'textInput')
    input_field.send_keys('Hello World')

    expect(input_field.attribute('value')).to eq('Hello World')
  end

  it 'sends key press' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    input_field = driver.find_element(id: 'textInput')
    input_field.send_keys('a')

    expect(input_field.attribute('value')).to include('a')
  end

  it 'clicks element' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    button = driver.find_element(id: 'consoleLog')
    button.click

    expect(button).not_to be_nil
  end

  it 'double clicks element' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    element = driver.find_element(tag_name: 'body')

    driver.action.double_click(element).perform
  end

  it 'right clicks element' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    element = driver.find_element(tag_name: 'body')

    driver.action.context_click(element).perform
  end

  it 'dispatches keyboard events' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    driver.execute_script(<<~SCRIPT)
      document.addEventListener('keydown', function(e) {
        console.log('Key pressed: ' + e.key);
      });
    SCRIPT

    body = driver.find_element(tag_name: 'body')
    body.send_keys('a')
  end

  it 'dispatches mouse events' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    button = driver.find_element(id: 'consoleLog')

    driver.execute_script(<<~SCRIPT, button)
      arguments[0].addEventListener('mouseover', function(e) {
        console.log('Mouse over');
      });
    SCRIPT

    driver.action.move_to(button).perform
  end

  it 'performs drag and drop' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    element = driver.find_element(tag_name: 'body')

    driver.action.drag_and_drop(element, element).perform
  end
end
