---
title: "How to upgrade to Selenium 4"
linkTitle: "How to upgrade to Selenium 4"
weight: 3
description: >
  Selenium4에 관심있나요? 최신 배포판로 업그레이드하는 데 도움이 되는 이 가이드를 확인하십시오!
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Korean. Do you speak Korean? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}


공식적으로 지원되는 언어(Ruby, JavaScript, C#, Python 및 Java) 중 하나를 사용하는 경우 
Selenium 4로 업그레이드하는 작업은 간단합니다. 몇 가지 문제가 발생할 수 있는 경우가 있을 
수 있습니다. 이 안내서는 문제를 해결하는 데 도움이 될 것입니다. 프로젝트 종속성을 
업그레이드하고 버전 업그레이드로 인한 주요 사용 중지 및 변경 사항을 이해할 수 있을 겁니다.

다음은 Selenium4로  업그레이드하기 위해 수행할 단계입니다.

* 테스트 코드 준비
* 종속성 업그레이드
* 잠재적인 오류 및 사용 중지 메시지

참고: Selenium 3.x 버전이 개발되는 동안 W3C WebDriver 표준에 대한 지원이 구현되었습니다. 
이 새로운 프로토콜과 레거시 JSON 와이어 프로토콜이 모두 지원되었습니다. 버전 3.11 무렵, 
Selenium 코드는 레벨 W3C 1 규격을 준수하게 되었습니다. 최신 버전의 셀레늄 3의 W3C 
호환 코드는 셀레늄 4에서 예상한 대로 작동될 것입니다.

## 테스트 코드 준비

Selenium 4는 레거시 프로토콜에 대한 지원을 제거하고 후드 아래에 기본적으로 W3C 웹 
드라이버 표준을 사용합니다. 대부분의 경우, 이 구현은 최종 사용자에게 영향을 미치지 
않습니다. 주요 예외는 'Capabilities' 와 'Actions' 클래스입니다.

### Capabilities
테스트 기능이 W3C를 준수하도록 구조화되지 않은 경우 세션이 시작되지 않을 수 있습니다. 
다음은 W3C 웹 드라이버 표준 기능 목록입니다.

* `browserName`
* `browserVersion` (`version`을 대체)
* `platformName` (`platform`을 대체)
* `acceptInsecureCerts`
* `pageLoadStrategy`
* `proxy`
* `timeouts`
* `unhandledPromptBehavior`

표준 기능의 최신 목록은 [W3C WebDriver](https://www.w3.org/TR/webdriver1/#capabilities)에서 확인할 수 있습니다.

위 목록에 포함되지 않은 capability는  벤더 접두사를 포함해야 합니다. 이는 브라우저별 기능과
클라우드 벤더별 기능에 적용됩니다. 예를 들어, 클라우드 벤더가 테스트에 'build' 및 'name' 기능을 
사용하는 경우 'cloud:options' 블록으로 포장해야 합니다(클라우드 벤더에 적절한 접두사를 확인하
십시오).

#### Before 
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
DesiredCapabilities caps = DesiredCapabilities.firefox();
caps.setCapability("platform", "Windows 10");
caps.setCapability("version", "92");
caps.setCapability("build", myTestBuild);
caps.setCapability("name", myTestName);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), caps);
{{< /tab >}}
{{< tab header="JavaScript" >}}
caps = {};
caps['browserName'] = 'Firefox';
caps['platform'] = 'Windows 10';
caps['version'] = '92';
caps['build'] = myTestBuild;
caps['name'] = myTestName;
{{< /tab >}}
{{< tab header="CSharp" >}}
DesiredCapabilities caps = new DesiredCapabilities();
caps.SetCapability("browserName", "firefox");
caps.SetCapability("platform", "Windows 10");
caps.SetCapability("version", "92");
caps.SetCapability("build", myTestBuild);
caps.SetCapability("name", myTestName);
var driver = new RemoteWebDriver(new Uri(CloudURL), capabilities);
{{< /tab >}}
{{< tab header="Ruby" >}}
caps = Selenium::WebDriver::Remote::Capabilities.firefox
caps[:platform] = 'Windows 10'
caps[:version] = '92'
caps[:build] = my_test_build
caps[:name] = my_test_name
driver = Selenium::WebDriver.for :remote, url: cloud_url, desired_capabilities: caps
{{< /tab >}}
{{< tab header="Python" >}}
caps = {}
caps['browserName'] = 'firefox'
caps['platform'] = 'Windows 10'
caps['version'] = '92'
caps['build'] = my_test_build
caps['name'] = my_test_name
driver = webdriver.Remote(cloud_url, desired_capabilities=caps)
{{< /tab >}}
{{< /tabpane >}}

#### After
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
FirefoxOptions browserOptions = new FirefoxOptions();
browserOptions.setPlatformName("Windows 10");
browserOptions.setBrowserVersion("92");
Map<String, Object> cloudOptions = new HashMap<>();
cloudOptions.put("build", myTestBuild);
cloudOptions.put("name", myTestName);
browserOptions.setCapability("cloud:options", cloudOptions);
WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), browserOptions);
{{< /tab >}}
{{< tab header="JavaScript" >}}
capabilities = {
  browserName: 'firefox',
  browserVersion: '92',
  platformName: 'Windows 10',
  'cloud:options': {
     build: myTestBuild,
     name: myTestName,
  }
}
{{< /tab >}}
{{< tab header="CSharp" >}}
var browserOptions = new FirefoxOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "92";
var cloudOptions = new Dictionary<string, object>();
cloudOptions.Add("build", myTestBuild);
cloudOptions.Add("name", myTestName);
browserOptions.AddAdditionalOption("cloud:options", cloudOptions);
var driver = new RemoteWebDriver(new Uri(CloudURL), options);
{{< /tab >}}
{{< tab header="Ruby" >}}
options = Selenium::WebDriver::Options.firefox
options.browser_version = 'latest'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options[:build] = my_test_build
cloud_options[:name] = my_test_name
options.add_option('cloud:options', cloud_options)
driver = Selenium::WebDriver.for :remote, url: cloud_url, capabilities: options
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options as FirefoxOptions
options = FirefoxOptions()
options.browser_version = '92'
options.platform_name = 'Windows 10'
cloud_options = {}
cloud_options['build'] = my_test_build
cloud_options['name'] = my_test_name
options.set_capability('cloud:options', cloud_options)
driver = webdriver.Remote(cloud_url, options=options)
{{< /tab >}}
{{< /tabpane >}}

### Java에서 요소 유틸리티 메서드 찾기

Java 바인딩('FindsBy' 인터페이스)에서 요소를 찾는 유틸리티 방법은 내부용으로만 
사용되었기 때문에 제거되었습니다. 다음의 예시 코드로 이해하는 것이 더 좋을 겁니다.

'findElements*'를 사용하여 단일 요소 찾기


{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.findElementByClassName("className");
driver.findElementByCssSelector(".className");
driver.findElementById("elementId");
driver.findElementByLinkText("linkText");
driver.findElementByName("elementName");
driver.findElementByPartialLinkText("partialText");
driver.findElementByTagName("elementTagName");
driver.findElementByXPath("xPath");
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.findElement(By.className("className"));
driver.findElement(By.cssSelector(".className"));
driver.findElement(By.id("elementId"));
driver.findElement(By.linkText("linkText"));
driver.findElement(By.name("elementName"));
driver.findElement(By.partialLinkText("partialText"));
driver.findElement(By.tagName("elementTagName"));
driver.findElement(By.xpath("xPath"));
```
{{< /card >}}
{{< /cardpane >}}


'findElement*'를 사용하여 다수 요소 찾기

{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.findElementsByClassName("className");
driver.findElementsByCssSelector(".className");
driver.findElementsById("elementId");
driver.findElementsByLinkText("linkText");
driver.findElementsByName("elementName");
driver.findElementsByPartialLinkText("partialText");
driver.findElementsByTagName("elementTagName");
driver.findElementsByXPath("xPath");
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.findElements(By.className("className"));
driver.findElements(By.cssSelector(".className"));
driver.findElements(By.id("elementId"));
driver.findElements(By.linkText("linkText"));
driver.findElements(By.name("elementName"));
driver.findElements(By.partialLinkText("partialText"));
driver.findElements(By.tagName("elementTagName"));
driver.findElements(By.xpath("xPath"));
```
{{< /card >}}
{{< /cardpane >}}


## 종속성 업그레이드

Selenium 4를 설치하고 프로젝트 의존성을 업그레이드하려면 아래의 하위 섹션을 확인하십시오.

### Java

Selenium 업그레이드 프로세스는 사용 중인 빌드 도구에 따라 달라집니다. 우리는 Java에서 
가장 흔한 것인 [Maven](https://maven.apache.org/)과 [Gradle](https://gradle.org/)을 
다룰 것입니다. 필요한 최소 Java 버전은 8입니다.

#### Maven

{{< cardpane >}}
{{< card header="Before" >}}
```xml
<dependencies>
  <!-- more dependencies ... -->
  <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.141.59</version>
  </dependency>
  <!-- more dependencies ... -->
</dependencies>
```
{{< /card >}}
{{< card header="After" >}}
```xml
<dependencies>
    <!-- more dependencies ... -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.0.0</version>
    </dependency>
    <!-- more dependencies ... -->
</dependencies>
```
{{< /card >}}
{{< /cardpane >}}

변경한 후 'pom.xml' 파일이 있는 디렉터리에서 `mvn clean compile`을 실행할 수 있습니다.

#### Gradle

{{< cardpane >}}
{{< card header="Before" >}}
```jsonpath
plugins {
    id 'java'
}
group 'org.example'
version '1.0-SNAPSHOT'
repositories {
    mavenCentral()
}
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '3.141.59'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< card header="After" >}}
```jsonpath
plugins {
    id 'java'
}
group 'org.example'
version '1.0-SNAPSHOT'
repositories {
    mavenCentral()
}
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '4.0.0'
}
test {
    useJUnitPlatform()
}
```
{{< /card >}}
{{< /cardpane >}}

변경한 후 'build.gradle' 파일이 있는 디렉터리에서 './gradlew clean build'를 실행할 수 있습니다.

모든 Java 배포판를 확인하려면  [MVNRepository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java).로 이동하면 됩니다.

### C#

C#에서 Selenium 4의 업데이트를 받는 곳은 [NuGet](https://www.nuget.org/)입니다.
[`Selenium.WebDriver`](https://www.nuget.org/packages/Selenium.WebDriver/4.0.0) 
패키지에서 최신 버전으로 업데이트하기 위한 지침을 가져올 수 있습니다.
Visual Studio의 NuGet Package Manager를 통해 다음을 실행할 수 있습니다.

```shell
PM> Install-Package Selenium.WebDriver -Version 4.0.0
```

### Python

Python을 사용하기 위해 가장 중요한 변경 사항은 최소 필요 버전입니다. Selenium 4는 
최소 Python 3.7 이상이 필요합니다. 자세한 내용은 [Python Package Index](https://pypi.org/project/selenium/4.0.0/)에서 확인할 수 있습니다.
명령 선언을 통해 업그레이드하려면 다음을 입력합니다:

```shell
pip install selenium==4.0.0
```

### Ruby

Selenium 4의 업데이트 세부사항은 
RubyGems의 [selenium-webdriver](https://rubygems.org/gems/selenium-webdriver/versions/4.0.0)
에서 볼 수 있습니다. 최신 버전을 설치하려면 다음을 실행합니다:

```shell
gem install selenium-webdriver
```

당신의 Gemfile에 이를 추가하고 싶다면:

```shell
gem 'selenium-webdriver', '~> 4.0.0'
```

### JavaScript

Selenium-webdriver 패키지는 Node 패키지 매니저인 [npmjs](https://www.npmjs.com)에서 
찾을 수 있습니다. Selenium 4는 [여기](https://www.npmjs.com/package/selenium-webdriver/v/4.0.0)
에서 찾을 수 있습니다. 다음을 실행하여 설치할 수 있습니다.

```shell
npm install selenium-webdriver
```

또는, 당신의 package.json 파일을 업데이트한 후 ` npm install`를 실행시킵니다:

```json
{
  "name": "selenium-tests",
  "version": "1.0.0",
  "dependencies": {
    "selenium-webdriver": "^4.0.0"
  }
}
```

## 잠재적 오류 및 사용 중지 메시지

다음은 Selenium 4로 업그레이드한 후 발생할 수 있는 사용 중지 메시지를 해결하는 데
도움이 되는 코드의 예시입니다.

### Java

#### 대기 및 시간 초과

The parameters received in Timeout have switched from expecting  to 
expect .



시간 종료 후 수신된 변수가 `(long time, TimeUnit unit)`으로 예상되던 것에서
`(Duration duration)`으로 예상되도록 변경되었습니다.

{{< cardpane >}}
{{< card header="Before" >}}
```java
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().timeouts().setScriptTimeout(2, TimeUnit.MINUTES);
driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
```
{{< /card >}}
{{< card header="After" >}}
```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
```
{{< /card >}}
{{< /cardpane >}}


대기 중에도 다른 매개 변수가 필요합니다. `WebDriverWait`는 이제 몇 초, 몇 밀리 초 
단위로 시간이 초과되는 `long` 대신 `Duration`을 기대하고 있습니다. `FluentWait`의
`withTimeout`과 `pollingEvery` 유틸리티 방식은 `(long time, TimeUnit unit)`로 
예상되던 것이 `(Duration duration)`을 예상하는 것으로 변경되었습니다.

{{< cardpane >}}
{{< card header="Before" >}}
```java
new WebDriverWait(driver, 3)
.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));

Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(30, TimeUnit.SECONDS)
  .pollingEvery(5, TimeUnit.SECONDS)
  .ignoring(NoSuchElementException.class);
```
{{< /card >}}
{{< card header="After" >}}
```java
new WebDriverWait(driver, Duration.ofSeconds(3))
  .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));

  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);
```
{{< /card >}}
{{< /cardpane >}}

#### Merging capabilities is no longer changing the calling object

이전에는 다른 기능의 집합을 다른 집합으로 병합할 수 있었고, 호출 개체를 변이시켰습니다. 
지금은, 병합 작업의 결과를 할당해야 합니다. 병합 기능을 사용해도 호출 개체가 
더 이상 변경되지 않습니다.

{{< cardpane >}}
{{< card header="Before" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options.merge(capabilities);
```
As a result, the `options` object was getting modified.
{{< /card >}}
{{< card header="After" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("platformVersion", "Windows 10");
FirefoxOptions options = new FirefoxOptions();
options.setHeadless(true);
options = options.merge(capabilities);
```
The result of the `merge` call needs to be assigned to an object.
{{< /card >}}
{{< /cardpane >}}

#### Firefox Legacy

GeckoDriver가 존재하기 전, Selenium 프로젝트는 Firefox를 자동화하기 위한 드라이버 
구현을 가지고 있었습니다 (버전 < 48). 그러나 최신 버전의 Firefox에서는 작동하지 
않기 때문에 이 구현은 더 이상 필요하지 않습니다. Selenium 4로 업그레이드할 때 
큰 문제가 발생하지 않도록 `setLegacy` 옵션이 사용되지 않는 것으로 표시됩니다. 
권고사항은 이전의 구현체 사용을 중단하고 GeckoDriver에만 의존하라는 것입니다. 
다음 코드는 업그레이드 후 사용되지 않는 `setLegacy` 사항을 보여줍니다.

```java
FirefoxOptions options = new FirefoxOptions();
options.setLegacy(true);
```

#### '브라우저 유형`
`브라우저 유형` 인터페이스는 오랫동안 사용되어 왔지만, 새로운 브라우저 
인터페이스로 인해 더 이상 사용되지 않고 있습니다.

{{< cardpane >}}
{{< card header="Before" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("browserVersion", "92");
capabilities.setCapability("browserName", BrowserType.FIREFOX);
```
{{< /card >}}
{{< card header="After" >}}
```java
MutableCapabilities capabilities = new MutableCapabilities();
capabilities.setCapability("browserVersion", "92");
capabilities.setCapability("browserName", Browser.FIREFOX);
```
{{< /card >}}
{{< /cardpane >}}

### C#

#### `AddAdditionalCapability` 는 더 이상 사용되지 않습니다.

대신 `AddAdditionalOption`이 권장됩니다. 여기에 이를 보여주는 예가 있습니다.

{{< cardpane >}}
{{< card header="Before" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalCapability("cloud:options", cloudOptions, true);
```
{{< /card >}}
{{< card header="After" >}}
```cs
var browserOptions = new ChromeOptions();
browserOptions.PlatformName = "Windows 10";
browserOptions.BrowserVersion = "latest";
var cloudOptions = new Dictionary<string, object>();
browserOptions.AddAdditionalOption("cloud:options", cloudOptions);
```
{{< /card >}}
{{< /cardpane >}}

### Python

#### `executable_path 가 더 이상 사용되지 않습니다. 서비스 개체를 전달하십시오.`

Selenium 4에서는 서비스 개체에서 드라이버의 ``executable_path``를 설정하여 더 이상 
사용되지 않도록 해야 합니다. 또는 경로를 설정하지 말고 필요한 드라이버가 
시스템 PATH에 있는지 확인하십시오.

{{< cardpane >}}
{{< card header="Before" >}}
```python
from selenium import webdriver
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-automation"])
options.add_experimental_option("useAutomationExtension", False)
driver = webdriver.Chrome(executable_path=CHROMEDRIVER_PATH, options=options)
```
{{< /card >}}
{{< card header="After" >}}
```python
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-automation"])
options.add_experimental_option("useAutomationExtension", False)
service = ChromeService(executable_path=CHROMEDRIVER_PATH)
driver = webdriver.Chrome(service=service, options=options)
```
{{< /card >}}
{{< /cardpane >}}

## 요약

Selenium 4로 업그레이드할 때 고려해야 할 주요 변경 사항을 검토했습니다. 
새 버전의 Selenium을 사용할 때 나타날 수 있는 잠재적인 문제를 예방하는 방법에 
대한 제안을 포함하여 업그레이드를 위해 테스트 코드가 준비될 때 다룰 다양한 측면을 
다룹니다. 마지막으로 업그레이드 후 발생할 수 있는 일련의 문제에 대해서도 설명했으며 
이러한 문제에 대한 잠재적인 수정 사항을 공유했습니다.


*최초 게시 URl :   https://saucelabs.com/resources/articles/how-to-upgrade-to-selenium-4*
