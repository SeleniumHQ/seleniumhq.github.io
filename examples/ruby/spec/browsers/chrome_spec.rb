# frozen_string_literal: true

require 'spec_helper'
require 'webdrivers'

RSpec.describe 'Chrome' do
  it 'basic options' do
    options = Selenium::WebDriver::Options.chrome
    @driver = Selenium::WebDriver.for :chrome, options: options
  end
end
