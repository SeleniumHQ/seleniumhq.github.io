# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Waits' do
  let(:driver) { start_session }

  it 'fails' do
    driver.get 'https://www.selenium.dev/selenium/web/dynamic.html'
    driver.find_element(id: 'adder').click

    expect {
      driver.find_element(id: 'box0')
    }.to raise_error(Selenium::WebDriver::Error::NoSuchElementError)
  end

  it 'sleeps' do
    driver.get 'https://www.selenium.dev/selenium/web/dynamic.html'
    driver.find_element(id: 'adder').click

    sleep 1
    added = driver.find_element(id: 'box0')

    expect(added.dom_attribute(:class)).to eq('redbox')
  end

  it 'implicit' do
    driver.manage.timeouts.implicit_wait = 2
    driver.get 'https://www.selenium.dev/selenium/web/dynamic.html'
    driver.find_element(id: 'adder').click

    added = driver.find_element(id: 'box0')

    expect(added.dom_attribute(:class)).to eq('redbox')
  end

  it 'explicit' do
    driver.get 'https://www.selenium.dev/selenium/web/dynamic.html'
    revealed = driver.find_element(id: 'revealed')
    wait = Selenium::WebDriver::Wait.new

    driver.find_element(id: 'reveal').click
    wait.until { revealed.displayed? }

    revealed.send_keys('Displayed')
    expect(revealed.property(:value)).to eq('Displayed')
  end

  it 'options with explicit' do
    driver.get 'https://www.selenium.dev/selenium/web/dynamic.html'
    revealed = driver.find_element(id: 'revealed')
    errors = [Selenium::WebDriver::Error::NoSuchElementError,
              Selenium::WebDriver::Error::ElementNotInteractableError]
    wait = Selenium::WebDriver::Wait.new(timeout: 2,
                                         interval: 0.3,
                                         ignore: errors)

    driver.find_element(id: 'reveal').click
    wait.until { revealed.send_keys('Displayed') || true }

    expect(revealed.property(:value)).to eq('Displayed')
  end
end
