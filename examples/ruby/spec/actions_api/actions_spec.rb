# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Actions' do
  let(:driver) { start_session }

  it 'pauses' do
    driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'
    start = Time.now

    clickable = driver.find_element(id: 'clickable')
    driver.action
          .move_to(clickable)
          .pause(duration: 1)
          .click_and_hold
          .pause(duration: 1)
          .send_keys('abc')
          .perform

    duration = Time.now - start
    expect(duration).to be > 2
    expect(duration).to be < 3
  end

  it 'releases all' do
    driver.get 'https://www.selenium.dev/selenium/web/mouse_interaction.html'

    clickable = driver.find_element(id: 'clickable')
    action = driver.action
                   .click_and_hold(clickable)
                   .key_down(:shift)
                   .key_down('a')
    action.perform

    driver.action.release_actions

    action.key_down('a').perform
    expect(clickable.attribute('value')[0]).to eq 'A'
    expect(clickable.attribute('value')[-1]).to eq 'a'
  end
end
