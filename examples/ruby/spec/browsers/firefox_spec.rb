# frozen_string_literal: true

require 'spec_helper'
require 'webdrivers'

RSpec.describe 'Firefox' do
  it 'basic options' do
    options = Selenium::WebDriver::Options.firefox
    @driver = Selenium::WebDriver.for :firefox, options: options
  end
end
