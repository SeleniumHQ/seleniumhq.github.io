from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time
import random

# ========= 設定 =========
FORM_URL = "https://docs.google.com/forms/d/e/XXXXXXXXXXXX/viewform"
TOTAL = 200

TEXT_ANSWERS = [
    "とても良いと思います",
    "特に問題ありません",
    "参考になりました",
    "満足しています",
    "今後も利用したいです"
]

CONSENT_KEYWORDS = ["同意", "承諾", "はい", "許可"]

# ========================

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

for i in range(TOTAL):
    driver.get(FORM_URL)
    time.sleep(random.uniform(2, 3))

    # ---------- テキスト（約5問） ----------
    text_inputs = driver.find_elements(By.CSS_SELECTOR, "input[type='text']")
    for inp in text_inputs:
        inp.send_keys(random.choice(TEXT_ANSWERS))
        time.sleep(0.2)

    # ---------- ラジオボタン（約20問） ----------
    radio_groups = driver.find_elements(By.CSS_SELECTOR, "div[role='radiogroup']")
    for group in radio_groups:
        radios = group.find_elements(By.CSS_SELECTOR, "div[role='radio']")
        chosen = False

        # 同意系を優先
        for r in radios:
            label = r.text
            if any(k in label for k in CONSENT_KEYWORDS):
                r.click()
                chosen = True
                break

        # なければランダム
        if not chosen and radios:
            random.choice(radios).click()

        time.sleep(0.2)

    # ---------- チェックボックス（約5問） ----------
    checkbox_groups = driver.find_elements(By.CSS_SELECTOR, "div[role='group']")
    for group in checkbox_groups:
        boxes = group.find_elements(By.CSS_SELECTOR, "div[role='checkbox']")
        for box in boxes:
            label = box.text
            if any(k in label for k in CONSENT_KEYWORDS):
                if box.get_attribute("aria-checked") == "false":
                    box.click()
            else:
                if random.random() < 0.4:
                    box.click()
        time.sleep(0.2)

    # ---------- 送信 ----------
    buttons = driver.find_elements(By.CSS_SELECTOR, "div[role='button']")
    for btn in buttons:
        if "送信" in btn.text:
            btn.click()
            break

    print(f"{i+1}/{TOTAL} 回答完了")
    time.sleep(random.uniform(3, 6))

driver.quit()
