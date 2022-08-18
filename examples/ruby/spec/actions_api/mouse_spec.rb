# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Mouse' do
  let(:driver) { start_session }

  it 'click and hold' do
    driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

    clickable = driver.find_element(id: 'clickable')
    driver.action
          .click_and_hold(clickable)
          .perform

    expect(driver.find_element(id: 'click-status').text).to eq 'focused'
  end

  it 'click and release' do
    driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

    clickable = driver.find_element(id: 'click')
    driver.action
          .click(clickable)
          .perform

    expect(driver.current_url).to include 'resultPage.html'
  end

  describe 'alternate button clicks' do
    it 'right click' do
      driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

      clickable = driver.find_element(id: 'clickable')
      driver.action
            .context_click(clickable)
            .perform

      expect(driver.find_element(id: 'click-status').text).to eq 'context-clicked'
    end

    it 'back click' do
      driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'
      driver.find_element(id: 'click').click
      expect(driver.title).to eq('We Arrive Here')

      driver.action
            .pointer_down(:back)
            .pointer_up(:back)
            .perform

      expect(driver.title).to eq('BasicMouseInterfaceTest')
    end

    it 'forward click' do
      driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'
      driver.find_element(id: 'click').click
      driver.navigate.back
      expect(driver.title).to eq('BasicMouseInterfaceTest')

      driver.action
            .pointer_down(:forward)
            .pointer_up(:forward)
            .perform

      expect(driver.title).to eq('We Arrive Here')
    end
  end

  it 'double click' do
    driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

    clickable = driver.find_element(id: 'clickable')
    driver.action
          .double_click(clickable)
          .perform

    expect(driver.find_element(id: 'click-status').text).to eq 'double-clicked'
  end

  it 'hovers' do
    driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

    hoverable = driver.find_element(id: 'hover')
    driver.action
          .move_to(hoverable)
          .perform

    expect(driver.find_element(id: 'move-status').text).to eq 'hovered'
  end

  describe 'move by offset' do
    it 'offset from element' do
      driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'
      driver.action.scroll_to(driver.find_element(id: 'bottom')).perform

      mouse_tracker = driver.find_element(id: 'mouse-tracker')
      driver.action
            .move_to(mouse_tracker, 8, 11)
            .perform

      rect = mouse_tracker.rect
      center_x = rect.width/2
      center_y = rect.height/2
      x_coord, y_coord = driver.find_element(id: 'relative-location').text.split(',').map(&:to_i)

      expect(x_coord).to be_within(1).of(center_x + 8)
      expect(y_coord).to be_within(1).of(center_y + 11)
    end

    it 'offset from viewport' do
      driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

      driver.action
            .move_to_location(8, 12)
            .perform

      x_coord, y_coord = driver.find_element(id: 'absolute-location').text.split(',').map(&:to_i)
      expect(x_coord).to be_within(1).of(8)
      expect(y_coord).to be_within(1).of(12)
    end

    it 'offset from current pointer location' do
      driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

      driver.action.move_to_location(8, 11).perform

      driver.action
            .move_by(13, 15)
            .perform

      x_coord, y_coord = driver.find_element(id: 'absolute-location').text.split(',').map(&:to_i)
      expect(x_coord).to be_within(1).of(8 + 13)
      expect(y_coord).to be_within(1).of(11 + 15)
    end
  end

  it 'drags to another element' do
    driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

    draggable = driver.find_element(id: 'draggable')
    droppable = driver.find_element(id: 'droppable')
    driver.action
          .drag_and_drop(draggable, droppable)
          .perform

    expect(driver.find_element(id: 'drop-status').text).to include('dropped')
  end

  it 'drags by an offset' do
    driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

    draggable = driver.find_element(id: 'draggable')
    start = draggable.rect
    finish = driver.find_element(id: 'droppable').rect
    driver.action
          .drag_and_drop_by(draggable, finish.x - start.x, finish.y - start.y)
          .perform

    expect(driver.find_element(id: 'drop-status').text).to include('dropped')
  end
end
