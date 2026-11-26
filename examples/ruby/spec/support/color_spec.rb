# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Color' do
  let(:driver) { start_session }

  include Selenium::WebDriver::Support

  it 'creates colors from different formats' do
    hex_colour = Color.from_string('#2F7ED8')
    rgb_colour = Color.from_string('rgb(255, 255, 255)')
    rgb_colour_percent = Color.from_string('rgb(40%, 20%, 40%)')
    rgba_colour = Color.from_string('rgba(255, 255, 255, 0.5)')
    rgba_colour_percent = Color.from_string('rgba(40%, 20%, 40%, 0.5)')
    hsl_colour = Color.from_string('hsl(100, 0%, 50%)')
    hsla_colour = Color.from_string('hsla(100, 0%, 50%, 0.5)')

    expect(hex_colour.hex).to eq('#2f7ed8')
    expect(rgb_colour.rgb).to eq('rgb(255, 255, 255)')
    expect(rgb_colour_percent.rgb).to eq('rgb(102, 51, 102)')
    expect(rgba_colour.rgba).to eq('rgba(255, 255, 255, 0.5)')
    expect(rgba_colour_percent.rgba).to eq('rgba(102, 51, 102, 0.5)')
    expect(hsl_colour.rgb).to eq('rgb(128, 128, 128)')
    expect(hsla_colour.rgba).to eq('rgba(128, 128, 128, 0.5)')
  end

  it 'queries, compares and converts element colors' do
    driver.get('https://www.selenium.dev/selenium/web/colorPage.html')
    green = Color.from_string('rgb(0, 128, 0)')

    element_colour = Color.from_string(driver.find_element(id: 'namedColor').css_value('color'))
    element_background_colour = Color.from_string(driver.find_element(id: 'namedColor').css_value('background-color'))

    expect(element_colour).to eq(Color.from_string('rgb(0, 0, 0)'))

    expect(element_background_colour).to eq(green)

    expect(element_background_colour.hex).to eq('#008000')
    expect(element_background_colour.rgba).to eq('rgba(0, 128, 0, 1)')
    expect(element_background_colour.rgb).to eq('rgb(0, 128, 0)')
  end
end
