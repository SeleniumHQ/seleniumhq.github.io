# 线程守卫

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/thread_guard/

---

> <p class="lead">
>   此类仅在Java中可用
> 

ThreadGuard检查是否仅从创建驱动程序的同一线程中调用了驱动程序. 
线程问题 (尤其是在Parallel中运行测试时)
可能遇到神秘并且难以诊断错误. 
使用此包装器可以防止此类错误,  
并且在发生此类情况时会抛出异常.

以下的示例模拟一种线程冲突的情况:

```java
WebDriver protectedDriver = ThreadGuard.protect(startChromeDriver());

Throwable[] caughtOnOtherThread = new Throwable[1];
Runnable callFromOtherThread = () -> {
  try {
    protectedDriver.get("https://www.selenium.dev");
  } catch (Throwable t) {
    caughtOnOtherThread[0] = t;
  }
};
Thread otherThread = new Thread(callFromOtherThread);
otherThread.start();
otherThread.join();
```

结果如下所示:

```text
Exception in thread "Thread-1" org.openqa.selenium.WebDriverException:
Thread safety error; this instance of WebDriver was constructed
on thread main (id 1)and is being accessed by thread Thread-1 (id 24)
This is not permitted and *will* cause undefined behaviour

```
正如示例所示:

 * `protectedDriver` 将在主线程中创建
 *  我们使用Java的 `Runnable` 启动一个新进程,  并使用一个新的 `Thread` 运行该进程
 *  这两个 `Thread` 都会发生冲突,  因为主线程的内存中没有 `protectedDriver`
 * `ThreadGuard.protect` 会抛出异常

#### 注意:

这不能代替并发运行时使用 `ThreadLocal` 管理驱动程序的需求.
