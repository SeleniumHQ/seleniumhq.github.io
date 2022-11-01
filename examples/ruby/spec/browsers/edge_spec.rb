# frozen_string_literal: true

require 'spec_helper'
require 'webdrivers'

RSpec.describe 'Edge' do
  it 'basic options' do
    options = Selenium::WebDriver::Options.edge
    @driver = Selenium::WebDriver.for :edge, options: options
  end
end
