---
title: "共享的功能"
weight: 1
---
 
### 页面加载策略
通过URL导航到新页面时，默认情况下，Selenium将等待页面完全加载后再进行响应。这对于初学者来说效果很好，但是在加载大量第三方资源的页面上可能会导致较长的等待时间。在这种情况下，使用非默认策略可以使测试的执行速度更快，但是也可能导致不稳定，即页面上的元素随元素加载和大小变化而改变位置.

页面加载策略可以参考链接
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
如下述表格的描述:

| 策略 | 准备完成的状态 | 备注 |
| -------- | ----------- | ----- |
| normal | complete | 默认情况下使用，等待所有资源下载完成 |
| eager | interactive | DOM访问已准备就绪，但其他资源（如图像）可能仍在加载中 |
| none | Any | 完全不阻塞WebDriver|