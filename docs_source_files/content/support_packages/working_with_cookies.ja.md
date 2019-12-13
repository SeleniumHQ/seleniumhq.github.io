---
title: "Working with cookies"
weight: 6
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Japanese. Do you speak Japanese? Help us to translate
it by sending us pull requests!
{{% /notice %}}

First of all, you need to be on the domain that the cookie will be
valid for. If you are trying to preset cookies before
you start interacting with a site and your homepage is large / takes a while to load
an alternative is to find a smaller page on the site (typically the 404 page is small, 
e.g. http://example.com/some404page)

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Go to the correct domain
driver.get("http://www.example.com");

// Now set the cookie. This one's valid for the entire domain
Cookie cookie = new Cookie("key", "value");
driver.manage().addCookie(cookie);

// And now output all the available cookies for the current URL
Set<Cookie> allCookies = driver.manage().getCookies();
for (Cookie loadedCookie : allCookies) {
    System.out.println(String.format("%s -> %s", loadedCookie.getName(), loadedCookie.getValue()));
}

// You can delete cookies in 3 ways
// By name
driver.manage().deleteCookieNamed("CookieName");
// By Cookie
driver.manage().deleteCookie(loadedCookie);
// Or all of them
driver.manage().deleteAllCookies();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Go to the correct domain
driver.get("http://www.example.com")

# Now set the cookie. Here's one for the entire domain
# the cookie name here is 'key' and its value is 'value'
driver.add_cookie({'name':'key', 'value':'value', 'path':'/'})
# additional keys that can be passed in are:
# 'domain' -> String, 
# 'secure' -> Boolean, 
# 'expiry' -> Milliseconds since the Epoch it should expire.

# And now output all the available cookies for the current URL
for cookie in driver.get_cookies():
    print "%s -> %s" % (cookie['name'], cookie['value'])

# You can delete cookies in 2 ways
# By name
driver.delete_cookie("CookieName")
# Or all of them
driver.delete_all_cookies()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Go to the correct domain
driver.Url ="http://www.example.com";

// Now set the cookie. This one's valid for the entire domain
var cookie = new Cookie("key", "value");
driver.Manage().Cookies.AddCookie(cookie);

// And now output all the available cookies for the current URL
IReadOnlyCollection<Cookie> allCookies = driver.Manage().Cookies.AllCookies;
foreach (Cookie loadedCookie in allCookies) {
    Console.WriteLine("{0} -> {1}", loadedCookie.Name, loadedCookie.Value);
}

// You can delete cookies in 3 ways
// By name
driver.Manage().Cookies.DeleteCookieNamed("CookieName");
// By Cookie
driver.Manage().Cookies.DeleteCookie(cookie);
// Or all of them
driver.Manage().Cookies.DeleteAllCookies();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Go to the correct domain
driver.get "http://www.example.com"

# Now set the cookie. Here's one for the entire domain
# the cookie name here is 'key' and its value is 'value'
driver.manage.add_cookie(:name => 'key', :value => 'value')
# additional keys that can be passed in are:
# :path => String, :secure -> Boolean, :expires -> Time, DateTime, or seconds since epoch

# And now output all the available cookies for the current URL
driver.manage.all_cookies.each { |cookie| 
    puts "#{cookie[:name]} => #{cookie[:value]}" 
}

# You can delete cookies in 2 ways
# By name
driver.manage.delete_cookie "CookieName"
# Or all of them
driver.manage.delete_all_cookies
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Go to the correct domain
await driver.get('http://www.example.com');

// Now set the basic cookie. Here's one for the entire domain
// the cookie name here is 'key' and its value is 'value'
await driver.manage().addCookie({name: 'cookie-1', value: 'cookieValue'});

// And now output all the available cookies for the current URL
driver.manage().getCookies().then( (loadedCookies) =>{
    for (let cookie in loadedCookies) {
    console.log('printing Cookies loaded : '+cookie);
    }
});
// You can delete cookies in 2 ways
// By name
await driver.manage().deleteCookie('cookie-1');
// Or all of them
await driver.manage().deleteAllCookies();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// We don't have a Kotlin code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}

