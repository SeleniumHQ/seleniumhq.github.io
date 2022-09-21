# frozen_string_literal: true

require 'spec_helper'
require 'webdrivers'

RSpec.describe 'Internet Explorer', exclusive: {platform: :windows} do
  it 'basic options' do
    options = Selenium::WebDriver::Options.internet_explorer(attach_to_edge_chrome: true,
                                                             edge_executable_path: ENV['EDGE_PATH'])
    @driver = Selenium::WebDriver.for :ie, options: options
  end
end
