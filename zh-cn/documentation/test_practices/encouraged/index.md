# 指南和建议

> Selenium项目的一些测试指南和建议.

URL: https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/

---

关于"最佳实践"的注解：我们有意在本文档中避免使用"最佳实践"的说辞. 
没有一种方法可以适用于所有情况. 
我们更喜欢"指南和建议"的想法. 
我们鼓励您通读这些内容, 并仔细地确定哪种方法适用于您的特定环境. 

由于许多原因, 功能测试很难正确完成. 即便应用程序的状态, 复杂性, 依赖还不够让测试变得
足够复杂, 操作浏览器（特别是跨浏览器的兼容性测试）就已经使得写一个好的测试变成一种挑战. 

Selenium提供了一些工具使得功能测试用户更简单的操作浏览器, 但是这些工具并不能帮助你来写一个好的
架构的测试套件. 这章我们会针对怎么来做web页面的功能测试的自动化给出一些忠告, 指南和建议. 

这章记录了很多历年来成功的使用Selenium的用户的常用的软件设计模式.

---

Section pages:

- [页面对象模型](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/page_object_models/index.md)
- [领域特定语言](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/domain_specific_language/index.md)
- [生成应用程序状态](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/generating_application_state/index.md)
- [模拟外部服务](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/mock_external_services/index.md)
- [改善报告](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/improved_reporting/index.md)
- [避免共享状态](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/avoid_sharing_state/index.md)
- [使用定位器的提示](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/locators/index.md): 何时使用哪些定位器以及如何在代码中最好地管理它们.
- [测试的独立性](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/test_independency/index.md)
- [考虑使用Fluent API](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/consider_using_a_fluent_api/index.md)
- [每次测试都刷新浏览器](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/fresh_browser_per_test/index.md)
