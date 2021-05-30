---
title: "드라이버 요구사항"
weight: 2
---

Selenium은 WebDriver를 이용하여 Chrom(ium), Firefox, Internet Explorer, Opera, Safari와 같은 시장의 모든 주요 브라우저들을 지원합니다. 
모든 브라우저가 원격 제어에 대한 공식적인 지원을 가지고 있는 것은 아니지만, WebDriver는 가능한 경우 브라우저에 내장된 자동화 지원을 이용하여 브라우저를 구동합니다. 

WebDriver의 목적은 브라우저와 실제 사용자의 상호작용을 최대한 모방하는 것이며, 이는 브라우저마다 다른 수준으로 진행될 수 있습니다. 다양한 드라이버 특성에 대한 자세한 내용은 _[드라이버 특성]({{< ref "/driver_idiosyncrasies/_index.md" >}})_을 참조하십시오.

브라우저 제어를 위한 사용자 대면 인터페이스는 모든 드라이버가 동일하지만, 브라우저 세션을 설정하는 방법에는 약간의 차이가 존재합니다.
드라이버 구현의 많은 부분들은 서드파티에 의해 제공되기 때문에 표준 Selenium 배포에 포함되지 않습니다.

브라우저에 따라 요구사항이 다른 매개변수의 예시에는 드라이버의 인스턴스화, 프로필 관리 및 다양한 브라우저별 설정 등의 작업 등이 있습니다. 
여기서는 다른 브라우저를 시작하기 위한 기초적인 요구사항을 안내합니다.

### PATH에 실행 파일을 추가합니다.
대부분의 드라이버들은 Selenium이 브라우저를 이용하여 통신하기 위한 추가 실행 파일을 필요로 합니다.
WebDriver를 시작하기 전에 실행 파일의 위치를 수동으로 지정할 수 있습니다. 그러나 실행 파일이 모든 시스템에서 동일한 위치에 있거나, 테스트 코드 저장소에 실행 파일이 포함되어야 하기 때문에 이식성이 떨어질 수 있습니다.

Selenium은 시스템 경로에 WebDriver의 바이너리가 들어 있는 폴더를 추가함으로써, 테스트 코드가 드라이버의 정확한 위치를 찾도록 요구하지 않아도 추가 바이너리를 찾도록 할 수 있습니다.

* 실행 파일을 넣을 디렉토리를 다음과 같이 만듭니다.
_C:\WebDriver\bin_ or _/opt/WebDriver/bin_
* PATH에 디렉토리를 추가합니다: 
  * Windows의 경우 - CMD를 관리자 권한으로 실행한 뒤, 
     다음 명령을 실행하여 컴퓨터의 모든 사용자에 대한 디렉터리를 경로에 영구적으로 추가합니다.

```shell
setx /m path "%path%;C:\WebDriver\bin\"
```
  * Mac OS와 Linux의 Bash 사용자의 경우 - terminal을 사용합니다: 

```shell
export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
```

* 이제 변경 사항을 테스트 할 준비가 되었습니다.
열려있는 모든 CMD를 닫고, 새 CMD를 실행한 뒤, 이전 단계에서 생성한 폴더에 바이너리 중 하나의 이름을 다음과 같이 입력합니다. 

  ```shell
  chromedriver
  ```
* 만약 `PATH`가 올바르게 구성되었다면,
다음과 같이 드라이버 시작과 관련된 일부 출력을 볼 수 있을 것입니다.:

```text
Starting ChromeDriver 2.25.426935 (820a95b0b81d33e42712f9198c215f703412e1a1) on port 9515
Only local connections are allowed.
```

이 상태에선 <kbd>Ctrl+C</kbd>를 눌러 CMD를 다시 제어할 수 있습니다.


### 빠른 참조

| 브라우저 | 지원하는 OS | 유지 및 관리 | 다운로드 | 이슈 트래커 |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Downloads](//chromedriver.storage.googleapis.com/index.html) | [Issues](//bugs.chromium.org/p/chromedriver/Issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [DownLoads](//github.com/mozilla/geckodriver/releases) | [Issues](//github.com/mozilla/geckodriver/Issues) |
| Edge | Windows 10 | Microsoft | [DownLoads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](//developer.microsoft.com/en-us/microsoft-edge/platform/Issues/?page=1&amp;q=webdriver) |
| Internet Explorer | Windows | Selenium Project | [DownLoads](//selenium-release.storage.googleapis.com/index.html) | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS El Capitan and newer | Apple | Built in | [Issues](//bugreport.apple.com/logon) |
| Opera | Windows/macOS/Linux | Opera | [DownLoads](//github.com/operasoftware/operachromiumdriver/releases) | [Issues](//github.com/operasoftware/operachromiumdriver/Issues) |


### Chromium/Chrome

Chrome 또는 Chromium에서 구동하기 위해 
[chromedriver](//sites.google.com/a/chromium.org/chromedriver/DownLoads)
를 다운받은 뒤, 시스템 경로에 넣어야 합니다.

Linux 또는 MacOS에서 이는 `PATH` 환경 변수 수정을 의미하므로, 다음 명령을 실행하여 콜론(:)으로 구분된 디렉토리를 시스템 경로를 구성할 수 있습니다:

```shell
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```

경로에 chromedriver를 포함할 때, chromedriver 바이너리의 상위 디렉토리를 포함하는지 확인 해야 합니다. 다음 줄은 `PATH` 환경 변수의 현재 내용과 콜론(:) 뒤에 추가 경로를 덧붙여야 합니다:

```shell
$ export PATH="$PATH:/path/to/chromedriver"
```

경로에서 chromedriver를 사용할 수 있는 경우, 모든 디렉토리에서 chromedriver 실행 파일을 실행할 수 있어야 합니다.

다음을 이용하여 Chrome/Chromium 세션을 인스턴스화 할 수 있습니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

WebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Chrome

driver = Chrome()

#Or use the context manager
from selenium.webdriver import Chrome

with Chrome() as driver:
    #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

IWebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :chrome
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
    let driver = await new Builder().forBrowser('chrome').build();
    //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

val driver: WebDriver = ChromeDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

chromedriver 실행 파일의 경로 설정은 다음과 같이 가능합니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Chrome(executable_path='/path/to/chromedriver')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new ChromeDriver("/path/to/chromedriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Chrome.driver_path = "/path/to/chromedriver"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
chrome.setDefaultService(new chrome.ServiceBuilder('path/to/chromedriver').build());
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver")
  {{< / code-panel >}}
{{< / code-tab >}}

chromedriver는 Chrome의 내부 자동화 프록시 인터페이스를 노출하여 브라우저에게 수행할 작업을 지시하는 웹 드라이버 원격 서버로 구현됩니다. 


### Firefox

Mozilla는 Selenium 3을 시작으로 Firefox 드라이버 [geckodriver](//github.com/mozilla/geckodriver)의 구현을 이어받았으며, geckodriver라고 불립니다. 이는 Firefox 48 이상 버전에서 작동하고, Firefox WebDriver가 꾸준히 개발중이기 때문에 새로운 Firefox 버전이 나올때마다 더 나은 지원을 제공합니다.

geckodriver은 Firefox를 시작하는 기본적인 방법이기 때문에 Selenium 2와 같은 방법으로 Firefox를 인스턴스화 할 수 있습니다: 

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

WebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Firefox

driver = Firefox()
#Or use the context manager
from selenium.webdriver import Firefox

with Firefox() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

IWebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :firefox
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('firefox').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Firefox.FirefoxDriver

val driver: WebDriver = FirefoxDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

PATH를 사용하지 않고 geckodriver의 위치를 설정하려면, 프로그램적으로 geckodriver의 바이너리 위치를 설정해야 합니다: 

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Firefox(executable_path='/path/to/geckodriver')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new FirefoxDriver("/path/to/geckodriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Firefox.driver_path = "/path/to/geckodriver"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const firefox = require('selenium-webdriver/firefox');

const serviceBuilder = new firefox.ServiceBuilder("/path/to/geckodriver");

(async function myFunction() {
    let driver = await new Builder()
        .forBrowser('firefox')
        .setFirefoxService(serviceBuilder)
        .build();
        //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver")
  {{< / code-panel >}}
{{< / code-tab >}}

런타임의 속성을 설정하는 것 또한 가능합니다:

```shell
mvn test -Dwebdriver.gecko.driver=/path/to/geckodriver
```

현재 Firefox [47.0.1](//ftp.mozilla.org/pub/firefox/releases/47.0.1/) 또는 [45 ESR](//ftp.mozilla.org/pub/firefox/releases/45.0esr/))을 설치하고 원하는 **marionette** 기능을 **false**로 지정함으로써 이전의, 보다 완전한 Firefox 드라이버로 되돌릴 수 있습니다. 그러나 이후 출시된 Firefox는 더 이상 호환이 불가능합니다.

### Edge

Edge는 Windows 10과 Server 2016에 포함된 microsoft의 최신 브라우저입니다.

Edge의 업데이트는 주요 Windows 업데이트와 함께 번들로 제공되기 때문에 바이너리를 다운로드 할 때는 현재 설치된 Windows 빌드 번호와 바이너리의 빌드 번호가 일치하는지 확인이 필요합니다.

[Edge Developer sites](///developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)는 사용 가능한 모든 바이너리 파일에 대한 링크를 포함합니다.
EdgeDriver 구현에 대한 버그는 [Microsoft](//developer.microsoft.com/en-us/microsoft-edge/platform/Issues/?page=1&q=webdriver)에 올라올 수 있습니다.
microsoft는 Windows 10을 실행하지 않고 Edge에 대해 테스트를 하려는 테스터를 위해  다음 사이트에서 무료 VM을 제공합니다. [Edge Developer sites](//developer.microsoft.com/en-us/microsoft-edge/tools/vms/)

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

WebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Edge

driver = Edge()
#Or use the context manager
from selenium.webdriver import Edge

with Edge() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Edge;

IWebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :edge
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('MicrosoftEdge').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver

val driver: WebDriver = EdgeDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

경로에 Edge 드라이버가 없는 경우 다음과 같이 경로를 설정할 수 있습니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Edge(executable_path='/path/to/MicrosoftWebDriver.exe')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new EdgeDriver("/path/to/MicrosoftWebDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Edge.driver_path = "C:/path/to/MicrosoftWebDriver.exe"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const edge = require('selenium-webdriver/edge');
let service = new edge.ServiceBuilder("/path/to/msedgedriver.exe");
(async function test() {
    let driver = await new Builder()
                .setEdgeService(service)
                .forBrowser('MicrosoftEdge')
                .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe")
  {{< / code-panel >}}
{{< / code-tab >}}

### Internet Explorer
Internet Explorer는 windows 10에 포함되며, windows 10까지 microsoft의 기본 브라우저였습니다. Internet Explorer 드라이버는 Selenium 프로젝트와 동일한 릴리즈를 지원하는 것을 목표로 하는 유일한 드라이버입니다.[Microsoft considers current](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer) 또한 이전 릴리즈들은 작동할 수 있지만 지원은 되지 않을 예정입니다.

Selenium 프로젝트는 32-bit와 64-bit 버전의 Internet Explorer에 모두 바이너리를 제공합니다. 32-bit 드라이버를 사용하는 것은 계속해서 잘 작동하는 반면 64-bit 드라이버가 있는 Internet Explorer 10 & 11에는 몇가지 제한이 존재합니다. [limitations](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)

Internet Explorer은 로그인한 사용자 계정에 대해 설정이 저장되므로 일부 추가 설정 [additional setup is required](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver#required-configuration)이 필요하다는 점을 유의해야 합니다.


{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

WebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Ie

driver = Ie()
#Or use the context manager
from selenium.webdriver import Ie

with Ie() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

IWebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :internet_explorer
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('internet explorer').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.ie.InternetExplorerDriver

val driver: WebDriver = InternetExplorerDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

경로에 Internet Explorer 드라이버가 없는 경우 다음과 같이 경로를 설정할 수 있습니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Ie(executable_path='/path/to/IEDriverServer.exe')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new InternetExplorerDriver("C:/path/to/IEDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::IE.driver_path = "C:/path/to/IEDriver.exe"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const ie = require('selenium-webdriver/ie');
let service = new ie.ServiceBuilder("/path/to/IEDriverServer.exe");
(async function test() {
    let driver = await new Builder()
                .setIeService(service)
                .forBrowser('internet explorer')
                .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe")
  {{< / code-panel >}}
{{< / code-tab >}}

microsoft는 또한 [Internet Explorer 11 on Windows 7 & 8.1](//www.microsoft.com/en-gb/download/details.aspx?id=44069)에서 Windows 7 & 8.1에 존재하는 Internet Explorer 11을 위한 WebDriver 바이너리를 제공합니다.
이는 2014년 이후 업데이트되지 않았지만, W3 규격 초안을 기반으로 하고 있습니다.

 [Jim Evans](//jimevansmusic.blogspot.co.uk/2014/09/using-internet-explorer-webdriver.html)는 microsoft의 구현에 대한 훌륭한 기록을 가지고 있습니다.


### Opera

현재 출시된 Opera는 Chromium 엔진 위에 구축되어 있으며, WebDriver는 이제 closed-source [Opera Chromium Driver](//github.com/operasoftware/operachromiumdriver/releases)를 통해 PATH에 추가 [added to your PATH](#adding-executables-to-your-path) 또는 시스템 속성으로 지원됩니다.

드라이버 세션을 인스턴스화하는 것은 Firefox, Chromium과 유사합니다:


{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

WebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Opera

driver = Opera()
#Or use the context manager
from selenium.webdriver import Opera

with Opera() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Opera;

IWebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :opera
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const opera = require('selenium-webdriver/opera');
(async function test() {
    let driver = await new Builder()
        .forBrowser('opera')
        .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.opera.OperaDriver

val driver: WebDriver = OperaDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

### Safari

High Sierra 이상 버전:

* 터미널에서 다음 명령을 한 뒤, WebDriver를 승인하기 위해 프롬프트에 암호를 입력합니다.
```shell
safaridriver --enable
```

El Capitan과 Sierra:

* Safari 기본 설정에서 개발자 메뉴를 사용합니다.
* 개발 메뉴에서 '원격 자동화 허용' 옵션을 확인합니다.
*  터미널에서 다음 명령을 한 뒤, WebDriver를 승인하기 위해 프롬프트에 암호를 입력합니다.

```shell
/usr/bin/safaridriver -p 1337</
```

이후 아래를 사용하여 드라이버 세션을 시작할 수 있습니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

WebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Safari

driver = Safari()
#Or use the context manager
from selenium.webdriver import Safari

with Safari() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Safari;

IWebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :safari
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('safari').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.safari.SafariDriver

val driver: WebDriver = SafariDriver()
  {{< / code-panel >}}
{{< / code-tab >}}


iOS에서 Safari 자동화를 원하는 사용자는 [Appium project](///appium.io/)를 참조해야 합니다. 이전에는 Windows용 Safari를 사용할 수 있었지만, 오랫동안 중단된 Apple의 지원으로 인해 Safari의 시험용 플랫폼으로서의 가치가 낮아졌습니다.


## Mock browsers


### HtmlUnit

HTMLUnit은 "Java 프로그램을 위한 GUI-Less 브라우저"입니다. 이는 HTML 문서를 모델링하고 페이지 호출, 양식 작성, 클릭링크 등이 가능한 API를 제공합니다. 또한 JavaScript 지원 기능이 있으며, 사용되는 구성에 따라 Chrome, Firefox 또는 Internet Explorer를 시뮬레이션하여 AJAX 라이브러리와 작업할 수 있습니다. 이것은 [new location](http://htmlunit.sourceforge.net/gettingStarted.html))로 옮겨졌으며, 소스는 svn으로 유지됩니다.


### PhantomJS

PhantomJS는 Google Chrome이나 Safari가 사용하는 것 보다 훨씬 오래된 버전의 Webkit을 기반으로 한 헤드리스 브라우저입니다. 역사적으로 대중적인 선택이었지만, 이제 이 프로젝트는 유지되지 않으므로 PhantomJS를 피하는 것이 현명할 것입니다. [since the 5th of August 2017](//groups.google.com/forum/#!topic/phantomjs/9aI5d-LDuNE), 따라서 웹이 계속 바뀌는 동안, PhantomJS는 업데이트되지 않을 것입니다. 

프로젝트의 유지 중단은 Google이 Chrome을 헤드리스로 실행할 수 있는 능력을 발표한 후였는데, 지금은 Mozilla의 Firefox도 이 기능을 제공하고 있습니다.




