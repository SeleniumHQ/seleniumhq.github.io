# frozen_string_literal: true

require 'spec_helper'
require 'webdrivers'

RSpec.describe 'Safari', exclusive: {platform: :macosx} do
  it 'basic options' do
    options = Selenium::WebDriver::Options.safari
    @driver = Selenium::WebDriver.for :safari, options: options
  end
end
