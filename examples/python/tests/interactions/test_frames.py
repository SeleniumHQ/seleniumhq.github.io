# Licensed to the Software Freedom Conservancy (SFC) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The SFC licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

from selenium import webdriver
from selenium.webdriver.common.by import By

#set chrome and launch web page
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/iframes.html")

# --- Switch to iframe using WebElement ---
iframe = driver.find_element(By.ID, "iframe1")
driver.switch_to.frame(iframe)
assert "We Leave From Here" in driver.page_source

email_element = driver.find_element(By.ID, "email")
email_element.send_keys("admin@selenium.dev")
email_element.clear()
driver.switch_to.default_content()

# --- Switch to iframe using name or ID ---
iframe1=driver.find_element(By.NAME, "iframe1-name")  # (This line doesn't switch, just locates)
driver.switch_to.frame(iframe1)
assert "We Leave From Here" in driver.page_source

email = driver.find_element(By.ID, "email")
email.send_keys("admin@selenium.dev")
email.clear()
driver.switch_to.default_content()

# --- Switch to iframe using index ---
driver.switch_to.frame(0)
assert "We Leave From Here" in driver.page_source

# --- Final page content check ---
driver.switch_to.default_content()
assert "This page has iframes" in driver.page_source

#quit the driver
driver.quit()

#demo code for conference
