# frozen_string_literal: true

require 'spec_helper'
require 'webdrivers'

RSpec.describe 'Internet Explorer', exclusive: {platform: :windows} do
  it 'basic options' do
    options = Selenium::WebDriver::Options.ie(ignore_zoom_level: true)
    @driver = Selenium::WebDriver.for :ie, options: options
  end
end
