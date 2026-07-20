# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Network', exclusive: {bidi: true, reason: 'only executed when bidi is enabled'},
                          only: {browser: %i[chrome edge firefox]} do
  let(:driver) { start_bidi_session }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 2) }

  it 'adds an auth handler', skip: 'Do not execute BiDi test' do
    driver.network.add_authentication_handler('test', 'test')
    driver.navigate.to url_for('basicAuth')
    expect(driver.find_element(tag_name: 'h1').text).to eq('authorized')
  end
end
