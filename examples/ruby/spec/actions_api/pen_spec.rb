# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Pen' do
  let(:driver) { start_session }

  it 'uses a pen' do
    driver.get 'https://www.selenium.dev/selenium/web/pointerActionsPage.html'

    pointer_area = driver.find_element(id: 'pointerArea')
    driver.action(devices: :pen)
          .move_to(pointer_area)
          .pointer_down
          .move_by(2, 2)
          .pointer_up
          .perform

    moves = driver.find_elements(class: 'pointermove')
    move_to = properties(moves[0])
    down = properties(driver.find_element(class: 'pointerdown'))
    move_by = properties(moves[1])
    up = properties(driver.find_element(class: 'pointerup'))

    rect = pointer_area.rect
    center_x = rect.x + (rect.width / 2)
    center_y = rect.y + (rect.height / 2)

    expect(move_to).to include('button' => '-1',
                               'pointerType' => 'pen',
                               'pageX' => center_x.to_s,
                               'pageY' => center_y.floor.to_s)
    expect(down).to include('button' => '0',
                            'pointerType' => 'pen',
                            'pageX' => center_x.to_s,
                            'pageY' => center_y.floor.to_s)
    expect(move_by).to include('button' => '-1',
                               'pointerType' => 'pen',
                               'pageX' => (center_x + 2).to_s,
                               'pageY' => (center_y + 2).floor.to_s)
    expect(up).to include('button' => '0',
                          'pointerType' => 'pen',
                          'pageX' => (center_x + 2).to_s,
                          'pageY' => (center_y + 2).floor.to_s)
  end

  it 'sets pointer event attributes' do
    driver.get 'https://www.selenium.dev/selenium/web/pointerActionsPage.html'

    pointer_area = driver.find_element(id: 'pointerArea')
    driver.action(devices: :pen)
          .move_to(pointer_area)
          .pointer_down
          .move_by(2, 2, tilt_x: -72, tilt_y: 9, twist: 86)
          .pointer_up
          .perform

    moves = driver.find_elements(class: 'pointermove')
    move_to = properties(moves[0])
    down = properties(driver.find_element(class: 'pointerdown'))
    move_by = properties(moves[1])
    up = properties(driver.find_element(class: 'pointerup'))

    rect = pointer_area.rect
    center_x = rect.x + (rect.width / 2)
    center_y = rect.y + (rect.height / 2)

    expect(move_to).to include('button' => '-1',
                               'pointerType' => 'pen',
                               'pageX' => center_x.to_s,
                               'pageY' => center_y.floor.to_s)
    expect(down).to include('button' => '0',
                            'pointerType' => 'pen',
                            'pageX' => center_x.to_s,
                            'pageY' => center_y.floor.to_s)
    expect(move_by).to include('button' => '-1',
                               'pointerType' => 'pen',
                               'pageX' => (center_x + 2).to_s,
                               'pageY' => (center_y + 2).floor.to_s,
                               'tiltX' => -72.floor.to_s,
                               'tiltY' => 9.floor.to_s,
                               'twist' => 86.floor.to_s)
    expect(up).to include('button' => '0',
                          'pointerType' => 'pen',
                          'pageX' => (center_x + 2).to_s,
                          'pageY' => (center_y + 2).floor.to_s)
  end

  def properties(element)
    element.text.sub(/.*?\s/, '').split(',').to_h { |item| item.lstrip.split(/\s*:\s*/) }
  end
end
