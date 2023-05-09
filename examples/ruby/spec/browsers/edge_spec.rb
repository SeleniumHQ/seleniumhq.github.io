# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Edge' do
  let(:driver) { start_session }

  it 'basic options' do
    options = Selenium::WebDriver::Options.edge
    @driver = Selenium::WebDriver.for :edge, options: options
  end

  it 'add arguments' do
    options = Selenium::WebDriver::Options.edge(args: ['--headless=new'])
    @driver = Selenium::WebDriver.for :edge, options: options
    @driver.get('https://www.google.com')
  end
end
