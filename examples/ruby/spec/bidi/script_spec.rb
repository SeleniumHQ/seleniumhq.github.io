# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Script' do
  let(:driver) { start_bidi_session }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 5) }

  it 'calls a function' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    result = driver.script.call_function(
      'function(a, b) { return a + b; }',
      args: [
        {type: 'number', value: 2},
        {type: 'number', value: 3}
      ]
    )

    expect(result['type']).to eq('number')
    expect(result['value']).to eq(5)
  end

  it 'evaluates a script' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    result = driver.script.evaluate('2 + 2')

    expect(result['type']).to eq('number')
    expect(result['value']).to eq(4)
  end

  it 'disowns a value' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    result = driver.script.evaluate('({x: 1})')
    handle = result['handle']

    expect { driver.script.disown(handles: [handle]) }.not_to raise_error
  end

  it 'calls function with element arguments' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    element = driver.find_element(id: 'consoleLog')

    result = driver.script.call_function(
      'function(elem) { return elem.tagName; }',
      args: [
        {type: 'HTMLElement', handle: element}
      ]
    )

    expect(result['value']).to eq('BUTTON')
  end

  it 'gets realms' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    realms = driver.script.get_realms

    expect(realms).not_to be_empty
    expect(realms.first).to have_key('realm')
  end

  it 'evaluates in specific realm' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    realms = driver.script.get_realms
    realm_id = realms.first['realm']

    result = driver.script.evaluate('1 + 1', realm: realm_id)

    expect(result['type']).to eq('number')
    expect(result['value']).to eq(2)
  end

  it 'adds dom mutation handler' do
    mutation_events = []

    driver.script.add_dom_mutation_handler do |event|
      mutation_events << event
    end

    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    driver.execute_script(<<~SCRIPT)
      const div = document.createElement('div');
      div.textContent = 'Hello';
      document.body.appendChild(div);
    SCRIPT

    wait.until { mutation_events.any? }

    expect(mutation_events).not_to be_empty
  end
end
