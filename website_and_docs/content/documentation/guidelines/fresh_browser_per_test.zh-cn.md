---
title: "每次测试都刷新浏览器"
linkTitle: "每次测试都刷新浏览器"
weight: 11
---
 

每次测试都从一个干净的已知状态开始. 
理想情况下, 为每次测试打开一个新的虚拟机. 
如果打开新虚拟机不切实际, 则至少应为每次测试启动一个新的WebDriver. 
对于Firefox, 请使用您已知的配置文件去启动WebDriver.

```java
FirefoxProfile profile = new FirefoxProfile(new File("pathToFirefoxProfile"));
WebDriver driver = new FirefoxDriver(profile);
```
