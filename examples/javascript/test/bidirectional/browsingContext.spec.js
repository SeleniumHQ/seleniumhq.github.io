const { suite } = require('selenium-webdriver/testing');
const assert = require("assert");
const firefox = require('selenium-webdriver/firefox');
const BrowsingContext = require('selenium-webdriver/bidi/browsingContext');

suite(function(env) {
    describe('Browsing Context', function() {
        let driver

        beforeEach(async function () {
          driver = await env
              .builder()
              .setFirefoxOptions(new firefox.Options().enableBidi())
              .build()
        })

        afterEach(async function () {
          await driver.quit()
        })

        it('test create a browsing context for given id', async function () {
            const id = await driver.getWindowHandle()
            const browsingContext = await BrowsingContext(driver, {
              browsingContextId: id,
            })
            assert.equal(browsingContext.id, id)
        })

        it('test create a window', async function () {
            const browsingContext = await BrowsingContext(driver, {
              type: 'window',
            })
            assert.notEqual(browsingContext.id, null)
        })

        it('test create a window with a reference context', async function () {
            const browsingContext = await BrowsingContext(driver, {
              type: 'window',
              referenceContext: await driver.getWindowHandle(),
            })
            assert.notEqual(browsingContext.id, null)
        })

        it('test create a tab', async function () {
            const browsingContext = await BrowsingContext(driver, {
              type: 'tab',
            })
            assert.notEqual(browsingContext.id, null)
        })

        it('test create a tab with a reference context', async function () {
            const browsingContext = await BrowsingContext(driver, {
              type: 'tab',
              referenceContext: await driver.getWindowHandle(),
            })
            assert.notEqual(browsingContext.id, null)
        })

        it('test navigate to a url', async function () {
            const browsingContext = await BrowsingContext(driver, {
              type: 'tab',
            })
    
            let info = await browsingContext.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    
            assert.notEqual(browsingContext.id, null)
            assert.equal(info.navigationId, null)
            assert(info.url.includes('/bidi/logEntryAdded.html'))
        })

        it('test navigate to a url with readiness state', async function () {
          const browsingContext = await BrowsingContext(driver, {
            type: 'tab',
          })
  
          const info = await browsingContext.navigate(
            'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html',
            'complete'
          )
  
          assert.notEqual(browsingContext.id, null)
          assert.equal(info.navigationId, null)
          assert(info.url.includes('/bidi/logEntryAdded.html'))
        })

        it('test get tree with a child', async function () {
          const browsingContextId = await driver.getWindowHandle()
          const parentWindow = await BrowsingContext(driver, {
            browsingContextId: browsingContextId,
          })
          await parentWindow.navigate('https://www.selenium.dev/selenium/web/iframes.html', 'complete')
  
          const contextInfo = await parentWindow.getTree()
          assert.equal(contextInfo.children.length, 1)
          assert.equal(contextInfo.id, browsingContextId)
          assert(contextInfo.children[0]['url'].includes('formPage.html'))
        })

        it('test get tree with depth', async function () {
          const browsingContextId = await driver.getWindowHandle()
          const parentWindow = await BrowsingContext(driver, {
            browsingContextId: browsingContextId,
          })
          await parentWindow.navigate('https://www.selenium.dev/selenium/web/iframes.html', 'complete')
  
          const contextInfo = await parentWindow.getTree(0)
          assert.equal(contextInfo.children, null)
          assert.equal(contextInfo.id, browsingContextId)
        })

        it('test close a window', async function () {
          const window1 = await BrowsingContext(driver, { type: 'window' })
          const window2 = await BrowsingContext(driver, { type: 'window' })
  
          await window2.close()
  
          assert.doesNotThrow(async function () {
            await window1.getTree()
          })
          await assert.rejects(window2.getTree(), { message: 'no such frame' })
        })

        it('test close a tab', async function () {
          const tab1 = await BrowsingContext(driver, { type: 'tab' })
          const tab2 = await BrowsingContext(driver, { type: 'tab' })
  
          await tab2.close()
  
          assert.doesNotThrow(async function () {
            await tab1.getTree()
          })
          await assert.rejects(tab2.getTree(), { message: 'no such frame' })
        })
    })
}, { browsers: ['firefox'] })
