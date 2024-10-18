require 'selenium-webdriver'

def setup_without_selenium_manager
  service = Selenium::WebDriver::Chrome::Service.new(path: '/path/to/chromedriver')
  driver = Selenium::WebDriver.for(:chrome, service: service)
  driver.get("https://www.selenium.dev/documentation/selenium_manager/")
  driver.quit
end

def setup_with_selenium_manager
  driver = Selenium::WebDriver.for(:chrome) # Selenium Manager handles the driver automatically
  driver.get("https://www.selenium.dev/documentation/selenium_manager/")
  driver.quit
end