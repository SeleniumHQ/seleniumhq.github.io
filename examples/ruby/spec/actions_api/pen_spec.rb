# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Pen' do
  it 'uses a pen' do
    @driver.get 'https://www.selenium.dev/selenium/web/pointerActionsPage.html'

    pointer_area = @driver.find_element(id: 'pointerArea')
    actions = @driver.action
    actions.add_pointer_input(:pen, 'default pen')
    actions.move_to(pointer_area, device: 'default pen')
           .pointer_down(:left, device: 'default pen')
           .move_by(2, 2, device: 'default pen')
           .pointer_up(:left, device: 'default pen')
    actions.perform

    moves = @driver.find_elements(class: 'pointermove')
    move_to = properties(moves[0])
    down = properties(@driver.find_element(class: 'pointerdown'))
    move_by = properties(moves[1])
    up = properties(@driver.find_element(class: 'pointerup'))

    rect = pointer_area.rect
    center_x = rect.x + rect.width / 2
    center_y = rect.y + rect.height / 2

    expect(move_to).to include("button" => "-1",
                               "pageX" => (center_x).to_s,
                               "pageY" => (center_y).floor.to_s)
    expect(down).to include("button" => "0",
                            "pageX" => (center_x).to_s,
                            "pageY" => (center_y).floor.to_s)
    expect(move_by).to include("button" => "-1",
                               "pageX" => (center_x + 2).to_s,
                               "pageY" => (center_y + 2).floor.to_s)
    expect(up).to include("button" => "0",
                          "pageX" => (center_x + 2).to_s,
                          "pageY" => (center_y + 2).floor.to_s)
  end

  def properties(element)
    element.text.sub(/.*?\s/, '').split(',').map { |item| item.lstrip.split /\s*:\s*/ }.to_h
  end
end
