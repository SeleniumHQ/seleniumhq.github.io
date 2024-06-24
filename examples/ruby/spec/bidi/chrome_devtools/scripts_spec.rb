# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Scripts' do
  let(:driver) { start_session }

  it 'pins script' do
    driver.get('https://www.selenium.dev/selenium/web/xhtmlTest.html')
    element = driver.find_element(id: 'id1')

    key = driver.pin_script('return arguments;')
    arguments = driver.execute_script(key, 1, true, element)

    expect(arguments).to eq([1, true, element])
  end

  it 'gets mutated elements' do
    driver.get 'https://www.selenium.dev/selenium/web/dynamic.html'

    mutations = []
    driver.on_log_event(:mutation) { |mutation| mutations << mutation.element }

    driver.find_element(id: 'reveal').click
    Selenium::WebDriver::Wait.new(timeout: 30).until { mutations.any? }

    expect(mutations).to include(driver.find_element(id: 'revealed'))
  end
end
