---
title: "Overview of Test Automation"
linkTitle: "Overview"
weight: 1
aliases: [
"/documentation/en/introduction/on_test_automation/",
"/documentation/guidelines/on_test_automation/"
]
---

First, start by asking yourself whether or not you really need to use a browser.
Odds are that, at some point, if you are working on a complex web application,
you will need to open a browser and actually test it.

Functional end-user tests such as Selenium tests are expensive to run, however.
Furthermore, they typically require substantial infrastructure
to be in place to be run effectively.
It is a good rule to always ask yourself if what you want to test
can be done using more lightweight test approaches such as unit tests
or with a lower-level approach.

Once you have made the determination that you are in the web browser testing business,
and you have your Selenium environment ready to begin writing tests,
you will generally perform some combination of three steps:

* Set up the data
* Perform a discrete set of actions
* Evaluate the results

You will want to keep these steps as short as possible;
one or two operations should be enough most of the time.
Browser automation has the reputation of being “flaky”,
but in reality, that is because users frequently demand too much of it.
In later chapters, we will return to techniques you can use
to mitigate apparent intermittent problems in tests,
in particular on how to [overcome race conditions]({{< ref "waits.md" >}})
between the browser and WebDriver.

By keeping your tests short
and using the web browser only when you have absolutely no alternative,
you can have many tests with minimal flake.

A distinct advantage of Selenium tests
is their inherent ability to test all components of the application,
from backend to frontend, from a user's perspective.
So in other words, whilst functional tests may be expensive to run,
they also encompass large business-critical portions at one time.


### Testing requirements

As mentioned before, Selenium tests can be expensive to run.
To what extent depends on the browser you are running the tests against,
but historically browsers' behaviour has varied so much that it has often
been a stated goal to cross-test against multiple browsers.

Selenium allows you to run the same instructions against multiple browsers
on multiple operating systems,
but the enumeration of all the possible browsers,
their different versions, and the many operating systems they run on
will quickly become a non-trivial undertaking.


### Let’s start with an example

Larry has written a web site which allows users to order their 
custom unicorns.

The general workflow (what we will call the “happy path”) is something
like this:

* Create an account
* Configure the unicorn
* Add it to the shopping cart
* Check out and pay
* Give feedback about the unicorn


It would be tempting to write one grand Selenium script
to perform all these operations–many will try.
**Resist the temptation!**
Doing so will result in a test that 
a) takes a long time,
b) will be subject to some common issues around page rendering timing issues, and
c) is such that if it fails, 
it will not give you a concise, “glanceable” method for diagnosing what went wrong.

The preferred strategy for testing this scenario would be
to break it down to a series of independent, speedy tests,
each of which has one “reason” to exist.

Let us pretend you want to test the second step:
Configuring your unicorn.
It will perform the following actions:

* Create an account
* Configure a unicorn

Note that we are skipping the rest of these steps, 
we will test the rest of the workflow in other small, discrete test cases 
after we are done with this one.

To start, you need to create an account.
Here you have some choices to make:

* Do you want to use an existing account?
* Do you want to create a new account?
* Are there any special properties of such a user that need to be
  taken into account before configuration begins?

Regardless of how you answer this question,
the solution is to make it part of the "set up the data" portion of the test.
If Larry has exposed an API that enables you (or anyone)
to create and update user accounts,
be sure to use that to answer this question.
If possible, you want to launch the browser only after you have a user "in hand",
whose credentials you can just log in with.

If each test for each workflow begins with the creation of a user account,
many seconds will be added to the execution of each test.
Calling an API and talking to a database are quick,
“headless” operations that don't require the expensive process of
opening a browser, navigating to the right pages,
clicking and waiting for the forms to be submitted, etc.

Ideally, you can address this set-up phase in one line of code,
which will execute before any browser is launched:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
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
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

As you can imagine, the `UserFactory` can be extended
to provide methods such as `createAdminUser()`, and `createUserWithPayment()`.
The point is, these two lines of code do not distract you from the ultimate purpose of this test:
configuring a unicorn.

The intricacies of the [Page Object model]({{< ref "page_object_models.md" >}})
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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
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
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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

  {{< /tab >}}
{{< /tabpane >}}

Now that you have configured your unicorn,
you need to move on to step 3: making sure it actually worked.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles));
  {{< /tab >}}
  {{< tab header="Python" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
assert unicorn_confirmation_page.exists(sparkles), "Sparkles should have been created, with all attributes intact"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
Assert.True(unicornConfirmationPage.Exists(sparkles), "Sparkles should have been created, with all attributes intact");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# The exists() method from UnicornConfirmationPage will take the Sparkles
# object--a specification of the attributes you want to see, and compare
# them with the fields on the page.
expect(unicorn_confirmation_page.exists?(sparkles)).to be, 'Sparkles should have been created, with all attributes intact'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
assert(unicornConfirmationPage.exists(sparkles), "Sparkles should have been created, with all attributes intact");

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles))
  {{< /tab >}}
{{< /tabpane >}}

Note that the tester still has not done anything but talk about unicorns in this code–
no buttons, no locators, no browser controls.
This method of _modelling_ the application
allows you to keep these test-level commands in place and unchanging,
even if Larry decides next week that he no longer likes Ruby-on-Rails
and decides to re-implement the entire site
in the latest Haskell bindings with a Fortran front-end.

Your page objects will require some small maintenance in order to 
conform to the site redesign,
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


### To automate or not to automate?

Is automation always advantageous? When should one decide to automate test
cases?

It is not always advantageous to automate test cases. There are times when
manual testing may be more appropriate. For instance, if the application’s user
interface will change considerably in the near future, then any automation
might need to be rewritten anyway. Also, sometimes there simply is not enough
time to build test automation. For the short term, manual testing may be more
effective. If an application has a very tight deadline, there is currently no
test automation available, and it’s imperative that the testing gets done within
that time frame, then manual testing is the best solution.
