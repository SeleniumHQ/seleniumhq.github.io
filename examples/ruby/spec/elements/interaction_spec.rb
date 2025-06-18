# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Element Interaction' do
  let(:driver) { start_session }

  before { driver.get 'https://www.selenium.dev/selenium/web/inputs.html' }

  it 'clicks an element' do
    driver.find_element(name: 'color_input').click
  end

  it 'clears and send keys to an element' do
    driver.find_element(name: 'email_input').clear
    driver.find_element(name: 'email_input').send_keys 'admin@localhost.dev'
  end
end
