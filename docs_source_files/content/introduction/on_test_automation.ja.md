---
title: "On test automation"
weight: 2
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

First, start by asking yourself whether or not you really need to use a browser.
Odds are good that, at some point, if you're working on a complex web application,
you will need to open a browser and actually test it.

Functional end-user tests such as Selenium tests are expensive to run, however.
Furthermore, they typically require substantial infrastructure
to be in place to be run effectively.
It's a good rule to always ask yourself if what you want to test
can be done using more lightweight test approaches such as unit tests
or with a lower-level approach.

Once you have made the determination that you're in the web browser testing business,
and you have your Selenium environment ready to begin writing tests,
you will generally perform some combination of three steps:

* Set up the data
* Perform a discrete set of actions
* Evaluate the results

You will want to keep these steps as short as possible;
one to two operations should be enough much of the time.
Browser automation has the reputation of being “flaky”,
but in reality that is because users frequently demand too much of it.
In later chapters, we will return to techniques you can use
to mitigate apparent intermittent problems in tests,
in particular on how to [overcome race conditions]({{< ref "/webdriver/waits.ja.md" >}})
between the browser and WebDriver.

By keeping your tests short
and using the web browser only when you have absolutely no alternative,
you can have many tests with minimal flake.

A distinct advantage of Selenium tests
are their inherent ability to test all components of the application,
from backend to frontend, from a user's perspective.
So in other words, whilst functional tests may be expensive to run,
they also encompass large business-critical portions at one time.


### Testing requirements

As mentioned before, Selenium tests can be expensive to run.
To what extent depends on the browser you're running the tests against,
but historically browsers' behaviour has varied so much that it has often
been a stated goal to cross-test against multiple browsers.

Selenium allows you to run the same instructions against multiple browsers
on multiple operating systems,
but the enumeration of all the possible browsers,
their different versions, and the many operating systems they run on
will quickly become a non-trivial undertaking.


### Let’s start with an example

Larry has written a web site which allows users to order their own
custom unicorns.

The general workflow (what we'll call the “happy path”) is something
like this:

* Create an account
* Configure their unicorn
* Add her to the shopping cart
* Check out and pay
* Give feedback about their unicorn


It would be tempting to write one grand Selenium script
to perform all these operations–many will try.
**Resist the temptation!**
Doing so will result in a test that
a) takes a long time,
b) will be subject to some common issues around page rendering timing issues, and
c) is such that if it fails,
it won't give you a concise, “glanceable” method for diagnosing what went wrong.

The preferred strategy for testing this scenario would be
to break it down to a series of independent, speedy tests,
each of which has one “reason” to exist.

Let's pretend you want to test the second step:
Configuring your unicorn.
It will perform the following actions:

* Create an account
* Configure a unicorn

Note that we're skipping the rest of these steps,
we will test the rest of the workflow in other small, discrete test cases,
after we're done with this one.

To start off, you need to create an account.
Here you have some choices to make:

* Do you want to use an existing account?
* Do you want to create a new account?
* Are there any special properties of such a user that need to be
  taken into account before configuration begins?

Regardless of how you answer this question,
the solution is to make it part of the "set up the data" portion of the test–
if Larry has exposed an API which enables you (or anyone)
to create and update user accounts,
be sure to use that to answer this question–
if possible, you want to launch the browser only after you have a user "in hand",
whose credentials you can just log in with.

If each test for each workflow begins with the creation of a user account,
many seconds will be added to the execution of each test.
Calling an API and talking to a database are quick,
“headless” operations that don't require the expensive process of
opening a browser, navigating to the right pages,
clicking and waiting for the forms to be submitted, etc.

Ideally, you can address this set-up phase in one line of code,
which will execute before any browser is launched:

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
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
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

As you can imagine, the `UserFactory` can be extended
to provide methods such as `createAdminUser()`, and `createUserWithPayment()`.
The point is, these two lines of code do not distract you from the ultimate purpose of this test:
configuring a unicorn.

The intricacies of the [Page Object model]({{< ref "/guidelines_and_recommendations/page_object_models.ja.md" >}})
will be discussed in later chapters, but we will introduce the concept here:

Your tests should be composed of actions,
performed from the user's point of view,
within the context of pages in the site.
These pages are stored as objects,
which will contain specific information about how the web page is composed
and how actions are performed–
very little of which should concern you as a tester.

What kind of unicorn do you want?
You might want pink, but not necessarily.
Purple has been quite popular lately.
Does she need sunglasses? Star tattoos?
These choices, while difficult, are your primary concern as a tester–
you need to ensure that your order fulfillment center
sends out the right unicorn to the right person,
and that starts with these choices.

Notice that nowhere in that paragraph do we talk about buttons,
fields, drop-downs, radio buttons, or web forms.
**Neither should your tests!**
You want to write your code like the user trying to solve their problem.
Here is one way of doing this (continuing from the previous example):

{{< code-tab >}}
  {{< code-panel language="java" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here.
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Since we're already "on" the account page, we have to use it to get to the
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
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
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

Now that you've configured your unicorn,
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
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
//CHECK Boris
assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles))
  {{< / code-panel >}}
{{< / code-tab >}}

Note that the tester still has not done anything but talk about unicorns in this code–
no buttons, no locators, no browser controls.
This method of _modelling_ the application
allows you to keep these test-level commands in place and unchanging,
even if Larry decides next week that he no longer likes Ruby-on-Rails
and decides to re-implement the entire site
in the latest Haskell bindings with a Fortran front-end.

Your page objects will require some small maintenance
in order to conform to the site redesign,
but these tests will remain the same.
Taking this basic design,
you will want to keep going through your workflows with the fewest browser-facing steps possible.
Your next workflow will involve adding a unicorn to the shopping cart.
You will probably want many iterations of this test in order to make sure the cart is keeping its state properly:
Is there more than one unicorn in the cart before you start?
How many can fit in the shopping cart?
If you create more than one with the same name and/or features, will it break?
Will it only keep the existing one or will it add another?

Each time you move through the workflow,
you want to try to avoid having to create an account,
login as the user, and configure the unicorn.
Ideally, you will be able to create an account
and pre-configure a unicorn via the API or database.
Then all you have to do is log in as the user, locate Sparkles,
and add her to the cart.
