# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'File Upload' do
  let(:driver) { start_session }

  it 'uploads' do
    driver.get('https://the-internet.herokuapp.com/upload')
    upload_file = File.expand_path('../spec_support/selenium-snapshot.png', __dir__)

    file_input = driver.find_element(css: 'input[type=file]')
    file_input.send_keys(upload_file)
    driver.find_element(id: 'file-submit').click

    file_name = driver.find_element(id: 'uploaded-files')
    expect(file_name.text).to eq 'selenium-snapshot.png'
  end
end
