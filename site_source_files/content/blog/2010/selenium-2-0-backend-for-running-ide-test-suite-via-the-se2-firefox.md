+++
Description = "<p>The GSoC 2010, it’s a great adventure. I’m Raynatou, I come from Burkina and do my internship at SERLI. Like all interns in this company the topic of my internship focuses on advanced topics like the integration of WebDriver as backend for Selenium IDE. Selenium IDE is currently built upon Selenium Core for interacting with […]</p>"
Title = "Selenium 2.0 backend for running IDE test suite via the Se2 Firefox"
Date = 2010-08-09
Author = "raynatou"
AuthorLink = "https://twitter.com/RaynatouD"
+++

<p>The  GSoC 2010, it’s a great adventure.<br />
<br />
I’m  Raynatou, I come from Burkina and do my internship at  <a href="http://www.serli.com/">SERLI</a>. Like all interns in this company the topic of my internship focuses on advanced topics like the integration of WebDriver as backend for Selenium IDE. Selenium IDE is currently built upon Selenium Core for interacting with  web pages. Selenium Core has several major limitations, including the  inability to fire native events. To avoid these limitations and produce more robust tests, I am investigating Selenium IDE to using WebDriver in  place of Selenium Core.<br />
<br />
I’m  mentored by  Eric Allen from <a href="http://saucelabs.com/">SauceLabs</a>, my lead mentor, and by Jérémy Hérault from SERLI, his second but not least ;).<br />
<br />
Eric has already done most of this integration earlier this year. The  goal of his work is mostly to show what&#8217;s possible with  Selenium IDE  and the Selenium 2.0 Firefox driver. It is based on an  asynchronous  communication between the Firefox WebDriver extension and  Selenium IDE,  Se-IDE waiting for that WebDriver says to him that the  current command  has been passed with success (or not) to call the next  command of the  current test case (use of callback function). The goal of my mission is  to finalize this integration.  However, Eric being a crazy man, and he  has done it quickly and together  we though to another way to do this  merge.<br />
<br />
Our new  thinking is based on a synchronous communication  between both of them.  To do it, I get the FirefoxDriver instance and  play with it like a  normal object, by calling its methods. But the behaviour of  FirefoxDriver isn’t as simple as I was  thinking. JavaScript being a  powerfull language (yes, it can make you  shiver), I&#8217;ve had just to  replace some objects (like Response object)  and to use a Proxy (and a  bit of IoC-like code) to simplify my life and  the code to do. You can  see my work on <a href="http://github.com/epall/selenium/commits/webdriver-synchronous">GitHub</a> and follow it.<br />
<br />
Currently, I think that more than 95% of the Selenium API  commands are usable  with WebDriver as backend in Selenium IDE.  The process was basically to go  through the seleniumemulation package and port all of  the Java classes into JavaScript methods on the WebDriver class.<br />
<br />
Now, I’m working on Unit tests to cover any of the 2.0 work  I&#8217;ve done. I managed to get all of the Selenium Core tests to run  through IDE. I’ve  some refactorings to do to optimize the code and add more comments for a bigger comprehension of my work. I have almost finished.<br />
<br />
I hope  you enjoy what I’m doing and we hope (me and my  mentors) that this work  can be included in the wave of  Selenium 2.</p>

