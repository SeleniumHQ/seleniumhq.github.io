# 期望状态的等待

> 本文档描述了一系列类, 这些类用于明确指定在测试中需要等待的各种条件.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/expected_conditions/

---

期望状态与 [显示等待](https://www.selenium.dev/zh-cn/documentation/webdriver/waits/index.md#explicit-waits) 一起使用.
与其定义要使用 _lambda_ 执行的代码块, 
不如使用 _lambda_ 执行可以创建 Conditions 方法来表示等待的常见事物.
有些方法将定位器作为参数, 有些方法将元素作为参数.

这些方法可以包括以下条件:

* 元素存在
* 元素已过期
* 元素可见
* 文本可见
* 标题包含特定值

**Java**

[Expected Conditions Documentation](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)
_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
wait = WebDriverWait(driver, timeout=2)
wait.until(EC.visibility_of_element_located((By.ID, "revealed")))
```

**CSharp**

.NET stopped supporting Expected Conditions in Selenium 4 to minimize maintenance hassle and redundancy.

**Ruby**

Ruby makes frequent use of blocks, procs and lambdas and does not need Expected Conditions classes

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._
