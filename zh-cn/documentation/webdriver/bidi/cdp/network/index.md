# Chrome DevTools Network Features

> Network features using CDP.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/cdp/network/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i>
>    Page being translated from
>    English to Chinese. Do you speak Chinese? Help us to translate
>    it by sending us pull requests!
> 

> While Selenium 4 provides direct access to the Chrome DevTools Protocol, these
> methods will eventually be removed when WebDriver BiDi implemented.

## Basic authentication

Some applications make use of browser authentication to secure pages.
It used to be common to handle them in the URL, but browsers stopped supporting this.
With this code you can insert the credentials into the header when necessary

**Java**

```java
Predicate<URI> uriPredicate = uri -> uri.toString().contains("herokuapp.com");
Supplier<Credentials> authentication = UsernameAndPassword.of("admin", "admin");
((HasAuthentication) driver).register(uriPredicate, authentication);
```

**Python**

```python
auth = {'authorization': 'Basic ' + credentials}
headers = connection.devtools.network.Headers(auth)
await connection.session.execute(connection.devtools.network.set_extra_http_headers(headers))
```

**CSharp**

```csharp
var handler = new NetworkAuthenticationHandler()
{
    UriMatcher = uri => uri.AbsoluteUri.Contains("herokuapp"),
    Credentials = new PasswordCredentials("admin", "admin")
};
var networkInterceptor = driver.Manage().Network;
networkInterceptor.AddAuthenticationHandler(handler);
await networkInterceptor.StartMonitoring();
```

**Ruby**

```ruby
driver.register(username: 'admin',
                password: 'admin',
                uri: /herokuapp/)
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

## Network Interception

Both requests and responses can be recorded or transformed.

#### Response information

**Java**

```java
try (NetworkInterceptor ignored =
    new NetworkInterceptor(
        driver,
        (Filter)
            next ->
                req -> {
                  HttpResponse res = next.execute(req);
                  contentType.add(res.getHeader("Content-Type"));
                  return res;
                })) {
```

**Python**

_Not available in this language._

**CSharp**

```csharp
INetwork networkInterceptor = driver.Manage().Network;
networkInterceptor.NetworkResponseReceived += (_, e) =>
{
    contentType.Add(e.ResponseHeaders["content-type"]);
};
await networkInterceptor.StartMonitoring();
```

**Ruby**

```ruby
driver.intercept do |request, &continue|
  continue.call(request) do |response|
    content_type << response.headers['content-type']
  end
end
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### Response transformation

**Java**

```java
try (NetworkInterceptor ignored =
    new NetworkInterceptor(
        driver,
        Route.matching(req -> true)
            .to(
                () ->
                    req ->
                        new HttpResponse()
                            .setStatus(200)
                            .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
                            .setContent(Contents.utf8String("Creamy, delicious cheese!"))))) {
```

**Python**

_Not available in this language._

**CSharp**

```csharp
var handler = new NetworkResponseHandler()
{
    ResponseMatcher = _ => true,
    ResponseTransformer = _ => new HttpResponseData
    {
        StatusCode = 200,
        Body = "Creamy, delicious cheese!"
    }
};
INetwork networkInterceptor = driver.Manage().Network;
networkInterceptor.AddResponseHandler(handler);
await networkInterceptor.StartMonitoring();
```

**Ruby**

```ruby
driver.intercept do |request, &continue|
  continue.call(request) do |response|
    response.body = 'Creamy, delicious cheese!' if request.url.include?('blank')
  end
end
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### Request interception

**Java**

```java
try (NetworkInterceptor ignored =
    new NetworkInterceptor(
        driver,
        (Filter)
            next ->
                req -> {
                  if (req.getUri().contains("one.js")) {
                    req =
                        new HttpRequest(
                            HttpMethod.GET, req.getUri().replace("one.js", "two.js"));
                  }
                  completed.set(true);
                  return next.execute(req);
                })) {
```

**Python**

_Not available in this language._

**CSharp**

```csharp
var handler = new NetworkRequestHandler
{
    RequestMatcher = request => request.Url.Contains("one.js"),
    RequestTransformer = request =>
    {
        request.Url = request.Url.Replace("one", "two");

        return request;
    }
};
INetwork networkInterceptor = driver.Manage().Network;
networkInterceptor.AddRequestHandler(handler);
await networkInterceptor.StartMonitoring();
```

**Ruby**

```ruby
driver.intercept do |request, &continue|
  uri = URI(request.url)
  request.url = uri.to_s.gsub('one', 'two') if uri.path&.end_with?('one.js')
  continue.call(request)
end
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Performance Metrics

**Java**

```java
devTools.send(Performance.enable(Optional.empty()));
List<Metric> metricList = devTools.send(Performance.getMetrics());
```

**Python**

```python
async with driver.bidi_connection() as connection:
    await connection.session.execute(connection.devtools.performance.enable())
    metric_list = await connection.session.execute(connection.devtools.performance.get_metrics())
```

**CSharp**

```csharp
await domains.Performance.Enable(new OpenQA.Selenium.DevTools.V156.Performance.EnableCommandSettings());
var metricsResponse =
    await session.SendCommand<GetMetricsCommandSettings, GetMetricsCommandResponse>(
        new GetMetricsCommandSettings()
    );
```

**Ruby**

```ruby
driver.devtools.performance.enable
metric_list = driver.devtools.performance.get_metrics.dig('result', 'metrics')
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Setting Cookies

**Java**

```java
devTools.send(
        Network.setCookie(
                "cheese",
                "gouda",
                Optional.<String>empty(),
                Optional.of("www.selenium.dev"),
                Optional.<String>empty(),
                Optional.of(true),
                Optional.<Boolean>empty(),
                Optional.<CookieSameSite>empty(),
                Optional.<TimeSinceEpoch>empty(),
                Optional.<CookiePriority>empty(),
                Optional.<CookieSourceScheme>empty(),
                Optional.<Integer>empty(),
                Optional.<CookiePartitionKey>empty()));
```

**Python**

```python
async with driver.bidi_connection() as connection:
    execution = connection.devtools.network.set_cookie(
        name="cheese",
        value="gouda",
        domain="www.selenium.dev",
        secure=True
    )
    await connection.session.execute(execution)
```

**CSharp**

```csharp
var cookieCommandSettings = new SetCookieCommandSettings
{
    Name = "cheese",
    Value = "gouda",
    Domain = "www.selenium.dev",
    Secure = true
};
await domains.Network.SetCookie(cookieCommandSettings);
```

**Ruby**

```ruby
                                   domain: 'www.selenium.dev',
                                   secure: true)

driver.get('https://www.selenium.dev')
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Waiting for Downloads

**Java**

```java
devTools.send(
        Browser.setDownloadBehavior(
                Browser.SetDownloadBehaviorBehavior.ALLOWANDNAME,
                Optional.empty(),
                Optional.of(""),
                Optional.of(true)));
```

**Python**

_Not available in this language._

**CSharp**

```csharp
await domains.Browser.SetDownloadBehavior(new SetDownloadBehaviorCommandSettings
{
    Behavior = "allowAndName",
    DownloadPath = downloadPath,
    EventsEnabled = true
});
```

**Ruby**

```ruby
                                              events_enabled: true)

driver.devtools.browser.on(:download_progress) do |progress|
  @completed = progress['state'] == 'completed'
end

driver.find_element(id: 'file-2').click
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._
