# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Network' do
  let(:driver) { start_bidi_session }

  it 'adds auth handler' do
    driver.network.add_authentication_handler('user', 'pass')
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
  end
end
