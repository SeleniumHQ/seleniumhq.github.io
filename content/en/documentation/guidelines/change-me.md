def wholeProcess(browser):
 browser.get(url)
 try:
 btn=browser.find_element_by_xpath("/html/body/div[4]/div[2]/div[2]/dl/input") #找到登录的按钮，如果没找到证明还没到开放时间/系统在崩溃
 except:
 return 1 #1说明预约还没到时候
 '''执行到这里说明打开啦'''
 try:
 btn.click()
 username=browser.find_element_by_id("un")
 password=browser.find_element_by_id("pd")#找到账号密码
 username.send_keys(studentNumber)
 password.send_keys(loginPassword)
 btn=browser.find_element_by_xpath('//*[@id="index_login_btn"]/input')#找到登录按键
 btn.click()
 js=generateJsCode(startTime,endTime)#使用js代码来预约
 res=browser.execute_script(js)
 return 0
 except:
 return 2 #2说明打开了网页，但是遇到了其它问题
