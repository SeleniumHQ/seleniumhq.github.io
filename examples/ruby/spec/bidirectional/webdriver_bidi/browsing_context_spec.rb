# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Browsing Context' do
  it 'opens a window without browsing context'
  it 'opens a window with browsing context'
  it 'opens a tab without browsing context'
  it 'opens a tab with browsing context'
  it 'uses existing window'
  it 'navigates to url with readiness state'
  it 'navigates to url without readiness state'
  it 'gets browsing context tree with depth'
  it 'gets browsing context tree without depth'
  it 'gets all browsing contexts'
  it 'closes a tab'
  it 'closes a window'
end
