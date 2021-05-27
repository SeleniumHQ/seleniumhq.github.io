---
title: "테스트 자동화"
weight: 2
---

먼저 정말로 브라우저를 사용할 것인지 스스로 물어보는 것부터 시작합니다.
복잡한 웹 응용 프로그램에서 작업하는 경우 브라우저를 열고 실제로 테스트해야할 필요가 있습니다.


그러나 셀레니움 테스트와 같은 최종 사용자 테스트는 실행 비용이 많이 듭니다. 
게다가 일반적으로 효율적인 운영을 위해서는 상당한 인프라가 구현되어야 합니다.
즉, unit test와 같은 보다 가벼운 테스트 접근 방식을 사용하거나 낮은 수준의 접근 방식을 사용해서 테스트할 수 있는지 항상 자문하는 것이 좋습니다.

당신이 웹 브라우저 테스트 업무를 하고 있고 셀레니움 환경에서 테스트를 할 준비가 되어 있다면, 일반적으로 다음 세 단계의 조합을 수행할 것입니다.

* 데이터 설정
* 개별 작업 수행
* 결과 평가

아마 당신은 위 단계를 한 두번 내로 끝내기를 원할 겁니다. 브라우저 자동화는 "신뢰할 수 없는" 평판을 가지고 있지만 실제로 그것은 사용자가 너무 많은 것을 요구하기 때문입니다. 이 후 챕터에서는 테스트에서 드러나는 간헐적 문제를 완화하기 위해 사용할 수 있는 기술, 특히 브라우저와 웹 드라이버 간의 [경쟁상태 극복]({{< ref "/webdriver/waits.ko.md" >}})방법을 살펴보겠습니다.


대안이 전혀 없는 경우에 테스트를 짧게 유지하고 웹 브라우저만 사용함으로써 최소한의 Flake로 많은 테스트를 수행할 수 있습니다.

셀레니움 테스트의 고유한 장점은 백엔드부터 프런트엔드까지 애플리케이션의 모든 구성요소를 사용자의 관점에서 테스트할 수 있다는 것입니다. 즉, 기능 테스트를 실행하는 데 비용이 많이 들 수 있지만 동시에 업무상 중요한 많은 부분을 포괄하기도 한다는 것입니다.


### 테스트 요구사항

앞서 언급했듯이 셀레니움 테스트는 실행비용이 많이 듭니다. 테스트를 실행하는 브라우저에 따라 어느 정도가 달라지지만, 역사적으로 브라우저의 동작은 너무 다양하여 여러 브라우저에 대해 교차 테스트하는 것이 종종 명시되어 왔습니다.

셀레니움을 사용하면 여러 운영 체제에서 여러 브라우저에 대해 동일한 명령을 내릴 수 있지만, 서로 다른 버전의 가능한 모든 브라우저 및 이러한 브라우저가 실행되는 많은 운영 체제의 열거는 사소한 작업이 아닙니다.


### 예를 들면,

Larry는 사용자들이 그들의 커스텀 유니콘을 주문할 수 있는 웹사이트를 만들었습니다.

일반적인 workflow (이하 "happy path"이라 함)는 다음과 같습니다.

* 계정 만들기
* 유니콘 구성하기
* 장바구니에 추가
* 체크아웃 후 결제
* 유니콘에 대한 피드백 주기


많은 사람들은 셀레니움 스크립트을 하나 짜서 모든 작업을 수행하는 것이 구미가 당길 것입니다. 
**하지만 유혹을 뿌리쳐야 합니다!**
만일 스크립트를 작성해서 작업을 수행하면, a) 시간이 오래 걸리는 테스트, b) 페이지 렌더링 타이밍 문제와 관련된 몇 가지 일반적인 문제가 발생할 수 있으며 c) 테스트가 실패할 경우 무엇이 잘못되었는지 진단하는 간결하고 "일람 가능한" 방법이 제공되지 않을 수 있습니다

이 시나리오를 테스트하기 위한 선호 전략은 일련의 독립적이고 속도 높은 테스트로 세분화하는 것이고, 각각의 테스트는 하나의 "이유"가 존재합니다.

두 번째 단계인 유니콘 구성을 테스트해 보겠습니다. 다음 작업을 수행합니다.

* 계정 만들기
* 유니콘 구성하기

나머지 단계는 건너뛰고 이 단계를 완료한 후 다른 소규모 개별 테스트 사례에서 나머지 워크플로를 테스트합니다.

시작하려면 계정을 만들어야 합니다. 다음 몇 가지 선택 사항이 있습니다.

* 기존 계정을 사용하시겠습니까?
* 새 계정을 생성하시겠습니까?
* 구성을 시작하기 전에 사용자의 특수한 properties을 고려해야 합니까?

이 문제의 답변에 관계없이 해결책은 이 질문을 테스트의 "데이터 설정" 부분에 포함시키는 것입니다. Larry가 사용자 계정을 만들고 업데이트할 수 있는 API를 노출한 경우, 이 질문에 답변하기 위해 해당 API를 사용해야 합니다. 가능한 경우, 신원 확인이 된 로그인할 수 있는 사용자가 있는 경우에만 브라우저를 시작합니다.

각 워크플로에 대한 각 테스트가 사용자 계정 생성으로 시작되면 각 테스트 실행에 몇 초가 추가됩니다. API를 호출하고 데이터베이스와 통신하는 것은 빠르고 브라우저를 열고, 올바른 페이지로 이동하고, 양식이 제출 될 때까지 클릭하고 대기하는 등의 비용이 많이 드는 프로세스가 필요없는 "headless"작업입니다.

브라우저가 시작되기 전에 실행되는 코드 한 줄에서 이 설정 단계를 해결하는 것이 이상적입니다 :

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
User user = UserFactory.createCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
AccountPage accountPage = loginAs(user.getEmail(), user.getPassword());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Create a user who has read-only permissions--they can configure a unicorn,
# but they do not have payment information set up, nor do they have
# administrative privileges. At the time the user is created, its email
# address and password are randomly generated--you don't even need to
# know them.
user = user_factory.create_common_user() #This method is defined elsewhere.

# Log in as this user.
# Logging in on this site takes you to your personal "My Account" page, so the
# AccountPage object is returned by the loginAs method, allowing you to then
# perform actions from the AccountPage.
account_page = login_as(user.get_email(), user.get_password())
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
User user = UserFactory.CreateCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
AccountPage accountPage = LoginAs(user.Email, user.Password);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Create a user who has read-only permissions--they can configure a unicorn,
# but they do not have payment information set up, nor do they have
# administrative privileges. At the time the user is created, its email
# address and password are randomly generated--you don't even need to
# know them.
user = UserFactory.create_common_user #This method is defined elsewhere.

# Log in as this user.
# Logging in on this site takes you to your personal "My Account" page, so the
# AccountPage object is returned by the loginAs method, allowing you to then
# perform actions from the AccountPage.
account_page = login_as(user.email, user.password)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
var user = userFactory.createCommonUser(); //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
var accountPage = loginAs(user.email, user.password);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
val user = UserFactory.createCommonUser() //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
val accountPage = loginAs(user.getEmail(), user.getPassword())
  {{< / code-panel >}}
{{< / code-tab >}}

생각하신 대로 UserFactory를 확장하여 createAdminUser() 및 createUserWithPayment()와 같은 메소드를 제공할 수 있습니다. 요점은, 이 두 줄의 코드가 이 테스트의 궁극적인 목적, 즉 유니콘을 구성하는 것을 방해하지 않는다는 것입니다.

[페이지 오브젝트 모델]({{< ref "/guidelines_and_recommendations/page_object_models.ko.md" >}})의 복잡성은 뒤에서 설명하겠지만, 여기서는 개념만 소개하겠습니다.


테스트는 사이트의 페이지 컨텍스트 내에서 사용자의 관점에서 수행되는 작업으로 구성되어야 합니다. 이러한 페이지는 개체로 저장되며, 여기에는 웹 페이지의 구성 방법과 수행 방법에 대한 특정 정보가 포함됩니다. 이 중 대부분은 테스터와 관련이 없습니다.

어떤 유니콘을 원하십니까? 당신은 분홍색을 원할지 모르지만 꼭 그렇지는 않습니다. 보라색은 최근에 꽤 인기가 있습니다. 그녀가 선글라스가 필요합니까? 별 타투? 이러한 선택은 어렵기는 하지만 검사자로서 가장 중요한 관심사입니다. 주문 이행 센터에서 적절한 유니콘을 적절한 사람에게 발송할 수 있도록 해야 합니다. 이러한 선택에서부터 시작합니다.

이 단락 어디에서도 buttons, fields, drop-downs, radio buttons 또는 웹 양식에 대해 이야기하지 않습니다.
**테스트도 마찬가지입니다!**
사용자가 문제를 해결하려는 것처럼 코드를 작성하려고 합니다. 다음은 이 작업을 수행하는 한 가지 방법입니다(이전 예시에서 이어집니다) :

{{< code-tab >}}
  {{< code-panel language="java" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
AddUnicornPage addUnicornPage = accountPage.addUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# The Unicorn is a top-level Object--it has attributes, which are set here.
# This only stores the values; it does not fill out any web forms or interact
# with the browser in any way.
sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Since we're already "on" the account page, we have to use it to get to the
# actual place where you configure unicorns. Calling the "Add Unicorn" method
# takes us there.
add_unicorn_page = account_page.add_unicorn()

# Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
# its createUnicorn() method. This method will take Sparkles' attributes,
# fill out the form, and click submit.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.Purple, UnicornAccessories.Sunglasses, UnicornAdornments.StarTattoos);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
AddUnicornPage addUnicornPage = accountPage.AddUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.CreateUnicorn(sparkles);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# The Unicorn is a top-level Object--it has attributes, which are set here.
# This only stores the values; it does not fill out any web forms or interact
# with the browser in any way.
sparkles = Unicorn.new('Sparkles', UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Since we're already "on" the account page, we have to use it to get to the
# actual place where you configure unicorns. Calling the "Add Unicorn" method
# takes us there.
add_unicorn_page = account_page.add_unicorn

# Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
# its createUnicorn() method. This method will take Sparkles' attributes,
# fill out the form, and click submit.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here.
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
var sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.

var addUnicornPage = accountPage.addUnicorn();

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
var unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);

  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
val sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
val addUnicornPage = accountPage.addUnicorn()

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles)

  {{< / code-panel >}}
{{< / code-tab >}}

Now that you have configured your unicorn,
you need to move on to step 3: making sure it actually worked.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
assert unicorn_confirmation_page.exists(sparkles), "Sparkles should have been created, with all attributes intact"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.True(unicornConfirmationPage.Exists(sparkles), "Sparkles should have been created, with all attributes intact");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
expect(unicorn_confirmation_page.exists?(sparkles)).to be, 'Sparkles should have been created, with all attributes intact'
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
assert(unicornConfirmationPage.exists(sparkles), "Sparkles should have been created, with all attributes intact");

  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles))
  {{< / code-panel >}}
{{< / code-tab >}}

테스터는 여전히 이 코드에서 유니콘에 대해 아무것도 하지 않고 있습니다. buttons, locators, 브라우저 컨트롤도 없습니다. 애플리케이션을 모델링하는 이 방법을 사용하면 다음 주에 Larry가 Ruby-on-Rails를 더 이상 좋아하지 않는다고 결정하고 Fortran 프런트 엔드로 최신 Haskell 바인딩에서 전체 사이트를 다시 구현하기로 결정하더라도 테스트 레벨 명령을 그대로 유지할 수 있습니다.

페이지 객체는 사이트 재설계를 준수하기 위해 약간의 유지보수가 필요하지만, 이러한 테스트는 그대로 유지됩니다. 이 기본 설계를 사용하면 브라우저 지향 단계를 최소화하면서 workflows를 계속 진행할 수 있습니다. 다음 workflows에는 쇼핑 카트에 유니콘을 추가하는 작업이 포함됩니다. 카트의 상태를 올바르게 유지하기 위해 이 테스트를 여러 번 반복해야 할 것입니다. 시작하기 전에 카트에 유니콘이 한 개 이상 있습니까? 쇼핑 카트에 몇 개나 들어갈 수 있나요? 이름 및/또는 기능이 같은 두 개 이상 생성될 경우 중단됩니까? 기존 항목만 유지할 것입니까, 아니면 다른 항목만 추가할 것입니까?

Workflow를 이동할 때마다 계정을 생성하고 사용자로 로그인하고 유니콘을 구성할 필요가 없습니다. 이상적으로 계정을 생성하고 API 또는 데이터베이스를 통해 유니콘을 미리 구성할 수 있습니다. 그런 다음 사용자로 로그인하여 스파클을 찾은 다음 카트에 추가하기만 하면 됩니다.


### 자동화를 할까요, 말까요?

자동화가 항상 유리할까요? 언제 테스트 케이스를 자동화하기로 결정해야 할까요?

테스트 사례를 자동화하는 것이 항상 유리한 것은 아닙니다. 수동 테스트가 더 적절할 수 있는 경우가 있습니다. 예를 들어, 애플리케이션의 사용자 인터페이스가 가까운 시일에 크게 변화할 경우, 모든 자동화를 다시 작성해야 할 수 있습니다. 또한 테스트 자동화를 구축하기 위한 시간이 충분하지 않은 경우도 있습니다. 단기적으로는 수동 시험이 더 효과적일 수 있습니다. 애플리케이션의 마감 시간이 매우 빠듯할 경우, 현재 사용 가능한 테스트 자동화가 없으며, 반드시 해당 기간 내에 테스트를 수행해야 합니다. 따라서 이 때는 수동 테스트가 가장 적합한 솔루션입니다.
