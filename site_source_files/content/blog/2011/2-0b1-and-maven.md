+++
Description = "<p>If you’re using Maven and the 2.0b1 release of Selenium, you may be running into some problems getting maven to pick up your tests. The underlying problem is that selenium contains support classes for both JUnit and TestNG. The solution is to add this to your pom.xml <dependency> <groupId>org.seleniumhq.selenium</groupId> <artifactId>selenium</artifactId> <version>2.0b1</version> <exclusions> <exclusion> <groupId>org.testng</groupId> <artifactId>testng</artifactId> […]</p>"
Title = "2.0b1 and Maven"
Date = 2011-01-25
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
tags = ["maven","selenium"]
categories = ["general"]
+++

<p>If you&#8217;re using Maven and the 2.0b1 release of Selenium, you may be running into some problems getting maven to pick up your tests. The underlying problem is that selenium contains support classes for both JUnit and TestNG. The solution is to add this to your pom.xml</p>
<pre>&lt;dependency&gt;
  &lt;groupId&gt;org.seleniumhq.selenium&lt;/groupId&gt;
  &lt;artifactId&gt;selenium&lt;/artifactId&gt;
  &lt;version&gt;2.0b1&lt;/version&gt;
  &lt;exclusions&gt;
    &lt;exclusion&gt;
      &lt;groupId&gt;org.testng&lt;/groupId&gt;
      &lt;artifactId&gt;testng&lt;/artifactId&gt;
    &lt;/exclusion&gt;
  &lt;/exclusions&gt;
&lt;/dependency&gt;</pre>

