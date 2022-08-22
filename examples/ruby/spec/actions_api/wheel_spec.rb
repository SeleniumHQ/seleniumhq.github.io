# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Scrolling' do
  let(:driver) { start_session }

  it 'scrolls to element' do
    driver.get('https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html')

    iframe = driver.find_element(tag_name: 'iframe')
    driver.action
          .scroll_to(iframe)
          .perform

    expect(in_viewport?(iframe)).to eq true
  end

  it 'scrolls by given amount' do
    driver.get('https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html')

    footer = driver.find_element(tag_name: 'footer')
    delta_y = footer.rect.y
    driver.action
          .scroll_by(0, delta_y)
          .perform

    expect(in_viewport?(footer)).to eq true
  end

  it 'scrolls from element by given amount' do
    driver.get('https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html')

    iframe = driver.find_element(tag_name: 'iframe')
    scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.element(iframe)
    driver.action
          .scroll_from(scroll_origin, 0, 200)
          .perform

    driver.switch_to.frame(iframe)
    checkbox = driver.find_element(name: 'scroll_checkbox')
    expect(in_viewport?(checkbox)).to eq true
  end

  it 'scrolls from element by given amount with offset' do
    driver.get('https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html')

    footer = driver.find_element(tag_name: 'footer')
    scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.element(footer, 0, -50)
    driver.action
          .scroll_from(scroll_origin, 0, 200)
          .perform

    iframe = driver.find_element(tag_name: 'iframe')
    driver.switch_to.frame(iframe)
    checkbox = driver.find_element(name: 'scroll_checkbox')
    expect(in_viewport?(checkbox)).to eq true
  end

  it 'scrolls by given amount with offset' do
    driver.get('https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame.html')

    scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.viewport(10, 10)
    driver.action
          .scroll_from(scroll_origin, 0, 200)
          .perform

    iframe = driver.find_element(tag_name: 'iframe')
    driver.switch_to.frame(iframe)
    checkbox = driver.find_element(name: 'scroll_checkbox')
    expect(in_viewport?(checkbox)).to eq true
  end
end

def in_viewport?(element)
  in_viewport = <<~IN_VIEWPORT
    for(var e=arguments[0],f=e.offsetTop,t=e.offsetLeft,o=e.offsetWidth,n=e.offsetHeight;
    e.offsetParent;)f+=(e=e.offsetParent).offsetTop,t+=e.offsetLeft;
    return f<window.pageYOffset+window.innerHeight&&t<window.pageXOffset+window.innerWidth&&f+n>
    window.pageYOffset&&t+o>window.pageXOffset
  IN_VIEWPORT

  driver.execute_script(in_viewport, element)
end
