const { By } = require('selenium-webdriver')
const { suite } = require('selenium-webdriver/testing')
const assert = require('assert')

suite(function (env) {
  describe('Actions API - Wheel Tests', function () {
    let driver

    before(async function () {
      driver = await env.builder().build()
    })

    after(async() => await driver.quit())

    it('Scroll to element', async function () {
      await driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

      const iframe = await driver.findElement(By.css("iframe"))

      await driver.actions()
        .scroll(0, 0, 0, 0, iframe)
        .perform()

      assert.ok(await inViewport(iframe))
    })

    it('Scroll by given amount', async function () {
      await driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

      const footer = await driver.findElement(By.css("footer"))
      const deltaY = (await footer.getRect()).y

      await driver.actions()
        .scroll(0, 0, 0, deltaY)
        .perform()

      await driver.sleep(500)

      assert.ok(await inViewport(footer))
    })

    it('Scroll from an element by a given amount', async function () {
      await driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

      const iframe = await driver.findElement(By.css("iframe"))

      await driver.actions()
        .scroll(0, 0, 0, 200, iframe)
        .perform()

      await driver.sleep(500)

      await driver.switchTo().frame(iframe)
      const checkbox = await driver.findElement(By.name('scroll_checkbox'))
      assert.ok(await inViewport(checkbox))
    })

    it('Scroll from an element with an offset', async function () {
      await driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

      const iframe = await driver.findElement(By.css("iframe"))
      const footer = await driver.findElement(By.css("footer"))

      await driver.actions()
        .scroll(0, -50, 0, 200, footer)
        .perform()

      await driver.sleep(500)

      await driver.switchTo().frame(iframe)
      const checkbox = await driver.findElement(By.name('scroll_checkbox'))
      assert.ok(await inViewport(checkbox))
    })

    it('Scroll from an offset of origin (element) by given amount', async function () {
      await driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame.html")
  
      const iframe = await driver.findElement(By.css("iframe"))
  
      await driver.actions()
        .scroll(10, 10, 0, 200)
        .perform()
  
      await driver.sleep(500)
  
      await driver.switchTo().frame(iframe)
      const checkbox = await driver.findElement(By.name('scroll_checkbox'))
      assert.ok(await inViewport(checkbox))
    })

    function inViewport(element) {
      return driver.executeScript("for(var e=arguments[0],f=e.offsetTop,t=e.offsetLeft,o=e.offsetWidth,n=e.offsetHeight;\ne.offsetParent;)f+=(e=e.offsetParent).offsetTop,t+=e.offsetLeft;\nreturn f<window.pageYOffset+window.innerHeight&&t<window.pageXOffset+window.innerWidth&&f+n>\nwindow.pageYOffset&&t+o>window.pageXOffset", element)
    }
  })
})