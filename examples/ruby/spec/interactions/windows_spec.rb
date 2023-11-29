# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Windows' do
  let(:driver) { start_session }

  it 'opens new tab' do
    driver.get 'https://www.selenium.dev/documentation/webdriver/interactions/'

    driver.switch_to.new_window(:tab)
    driver.get 'https://www.selenium.dev/documentation/webdriver/interactions/windows/#create-new-window-or-new-tab-and-switch'

    expect(driver.window_handles.size).to eq 2
  end

  it 'opens new window' do
    driver.get 'https://www.selenium.dev/documentation/webdriver/interactions/'

    driver.switch_to.new_window(:window)
    driver.get 'https://www.selenium.dev/documentation/webdriver/interactions/windows/#create-new-window-or-new-tab-and-switch'

    expect(driver.window_handles.size).to eq 2
  end
end
