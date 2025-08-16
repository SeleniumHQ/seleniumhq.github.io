# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Windows' do
  let(:driver) { start_session }

  it 'opens new tab' do
    driver.switch_to.new_window(:tab)

    expect(driver.window_handles.size).to eq 2
  end

  it 'opens new window' do
    driver.switch_to.new_window(:window)

    expect(driver.window_handles.size).to eq 2
  end
end
