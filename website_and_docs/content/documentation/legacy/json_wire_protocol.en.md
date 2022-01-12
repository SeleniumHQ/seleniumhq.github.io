---
title: JSON Wire Protocol Specification
linkTitle: JSON Wire Protocol
weight: 10
description: >
  The endpoints and payloads for the now-obsolete open source protocol that was the precursor to the 
  [W3C specification](https://w3c.github.io/webdriver/).
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/JsonWireProtocol)

All implementations of WebDriver that communicate with the browser, or a RemoteWebDriver server shall use a common wire protocol. This wire protocol defines a [RESTful web service](http://www.google.com?q=RESTful+web+service) using [JSON](http://www.json.org) over HTTP.

The protocol will assume that the WebDriver API has been "flattened", but there is an expectation that client implementations will take a more Object-Oriented approach, as demonstrated in the existing Java API. The wire protocol is implemented in request/response pairs of "commands" and "responses".

## Terms and Concepts

### Client
The machine on which the WebDriver API is being used.<br><br>

### Session
The machine running the RemoteWebDriver. This term may also refer to a specific browser that implements the wire protocol directly, such as the FirefoxDriver or IPhoneDriver.<br><br>

The server should maintain one browser per session. Commands sent to a session will be directed to the corresponding browser.<br><br>

### WebElement
An object in the WebDriver API that represents a DOM element on the page.<br><br>

### WebElement JSON Object
The JSON representation of a WebElement for transmission over the wire. This object will have the following properties:<br><br>

| **Key**    | **Type**      |   **Description**                    |
|-------------|--------------------------|----------------------------|
| ELEMENT     | string          | The opaque ID assigned to the element by the server. This ID should be used in all subsequent commands issued against the element. |

### Capabilities JSON Object
Not all server implementations will support every WebDriver feature. Therefore, the client and server should use JSON objects with the properties listed below when describing which features a session supports.
<br><br>
<table><thead><th> <b>Key</b> </th><th> <b>Type</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> browserName </td><td> string      </td><td> The name of the browser being used; should be one of <code> {android, chrome, firefox, htmlunit, internet explorer, iPhone, iPad, opera, safari}</code>. </td></tr>
<tr><td> version    </td><td> string      </td><td> The browser version, or the empty string if unknown. </td></tr>
<tr><td> platform   </td><td> string      </td><td> A key specifying which platform the browser is running on. This value should be one of <code>{WINDOWS|XP|VISTA|MAC|LINUX|UNIX}</code>. When requesting a new session, the client may specify <code>ANY</code> to indicate any available platform may be used. </td></tr>
<tr><td> javascriptEnabled </td><td> boolean     </td><td> Whether the session supports executing user supplied JavaScript in the context of the current page. </td></tr>
<tr><td> takesScreenshot </td><td> boolean     </td><td> Whether the session supports taking screenshots of the current page. </td></tr>
<tr><td> handlesAlerts </td><td> boolean     </td><td> Whether the session can interact with modal popups, such as <code>window.alert</code> and <code>window.confirm</code>. </td></tr>
<tr><td> databaseEnabled </td><td> boolean     </td><td> Whether the session can interact database storage. </td></tr>
<tr><td> locationContextEnabled </td><td> boolean     </td><td> Whether the session can set and query the browser's location context. </td></tr>
<tr><td> applicationCacheEnabled </td><td> boolean     </td><td> Whether the session can interact with the application cache. </td></tr>
<tr><td> browserConnectionEnabled </td><td> boolean     </td><td> Whether the session can query for the browser's connectivity and disable it if desired. </td></tr>
<tr><td> cssSelectorsEnabled </td><td> boolean     </td><td> Whether the session supports CSS selectors when searching for elements. </td></tr>
<tr><td> webStorageEnabled </td><td> boolean     </td><td> Whether the session supports interactions with <a href='http://www.w3.org/TR/2009/WD-webstorage-20091029/'>storage objects</a>. </td></tr>
<tr><td> rotatable  </td><td> boolean     </td><td> Whether the session can rotate the current page's current layout between portrait and landscape orientations (only applies to mobile platforms). </td></tr>
<tr><td> acceptSslCerts </td><td> boolean     </td><td> Whether the session should accept all SSL certs by default. </td></tr>
<tr><td> nativeEvents </td><td> boolean     </td><td> Whether the session is capable of generating native events when simulating user input. </td></tr>
<tr><td> proxy      </td><td> proxy object </td><td> Details of any proxy to use. If no proxy is specified, whatever the system's current or default state is used. The format is specified under Proxy JSON Object. </td></tr>
<tr><td> unexpectedAlertBehaviour </td><td> string     </td><td> What the browser should do with an unhandled alert before throwing out the UnhandledAlertException. Possible values are "accept", "dismiss" and "ignore" </td></tr>
<tr><td> elementScrollBehavior      </td><td> integer </td><td> Allows the user to specify whether elements are scrolled into the viewport for interaction to align with the top (0) or bottom (1) of the viewport. The default value is to align with the top of the viewport. Supported in IE and Firefox (since 2.36) </td></tr></tbody></table>


### Desired Capabilities
A Capabilities JSON Object sent by the client describing the capabilities a new session created by the server should possess. Any omitted keys implicitly indicate the corresponding capability is irrelevant. More at <a href='DesiredCapabilities.md'>DesiredCapabilities</a>.
<br><br>

### Actual Capabilities
A Capabilities JSON Object returned by the server describing what features a session actually supports. 
Any omitted keys implicitly indicate the corresponding capability is not supported.
<br><br>

### Cookie JSON Object
A JSON object describing a Cookie.<br><br>

<table><thead><th> <b>Key</b> </th><th> <b>Type</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> name       </td><td> string      </td><td> The name of the cookie. </td></tr>
<tr><td> value      </td><td> string      </td><td> The cookie value.  </td></tr>
<tr><td> path       </td><td> string      </td><td> (Optional) The cookie path.<sup>1</sup> </td></tr>
<tr><td> domain     </td><td> string      </td><td> (Optional) The domain the cookie is visible to.<sup>1</sup> </td></tr>
<tr><td> secure     </td><td> boolean     </td><td> (Optional) Whether the cookie is a secure cookie.<sup>1</sup> </td></tr>
<tr><td> httpOnly   </td><td> boolean     </td><td> (Optional) Whether the cookie is an httpOnly cookie.<sup>1</sup> </td></tr>
<tr><td> expiry     </td><td> number      </td><td> (Optional) When the cookie expires, specified in seconds since midnight, January 1, 1970 UTC.<sup>1</sup> </td></tr></tbody></table>

<sup>1</sup> When returning Cookie objects, the server should only omit an optional field if it is incapable of providing the information.
<br><br>

### Log Entry JSON Object
A JSON object describing a log entry.
<br><br>
<table><thead><th> <b>Key</b> </th><th> <b>Type</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> timestamp  </td><td> number      </td><td> The timestamp of the entry. </td></tr>
<tr><td> level      </td><td> string      </td><td> The log level of the entry, for example, "INFO" (see <a href='#Log_Levels.md'>log levels</a>). </td></tr>
<tr><td> message    </td><td> string      </td><td> The log message.   </td></tr>
</tbody></table>

### Log Levels
Log levels in order, with finest level on top and coarsest level at the bottom.
<br><br>
<table><thead><th> <b>Level</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> ALL          </td><td> All log messages. Used for fetching of logs and configuration of logging. </td></tr>
<tr><td> DEBUG        </td><td> Messages for debugging. </td></tr>
<tr><td> INFO         </td><td> Messages with user information. </td></tr>
<tr><td> WARNING      </td><td> Messages corresponding to non-critical problems. </td></tr>
<tr><td> SEVERE       </td><td> Messages corresponding to critical errors. </td></tr>
<tr><td> OFF          </td><td> No log messages. Used for configuration of logging. </td></tr>
</tbody></table>

### Log Type
The table below lists common log types. Other log types, for instance, for performance logging may also be available.
<br><br>
<table><thead><th> <b>Log Type</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> client          </td><td> Logs from the client. </td></tr>
<tr><td> driver          </td><td> Logs from the webdriver. </td></tr>
<tr><td> browser         </td><td> Logs from the browser. </td></tr>
<tr><td> server          </td><td> Logs from the server. </td></tr>
</dd></tbody></table>

### Proxy JSON Object
A JSON object describing a Proxy configuration.
<br><br>
<table><thead><th> <b>Key</b> </th><th> <b>Type</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> proxyType  </td><td> string      </td><td> (Required) The type of proxy being used. Possible values are: <b>direct</b> - A direct connection - no proxy in use, <b>manual</b> - Manual proxy settings configured, e.g. setting a proxy for HTTP, a proxy for FTP, etc, <b>pac</b> - Proxy autoconfiguration from a URL, <b>autodetect</b> - Proxy autodetection, probably with WPAD, <b>system</b> - Use system settings </td></tr>
<tr><td> proxyAutoconfigUrl </td><td> string      </td><td> (Required if proxyType == <b>pac</b>, Ignored otherwise) Specifies the URL to be used for proxy autoconfiguration. Expected format example: <a href='http://hostname.com:1234/pacfile'>http://hostname.com:1234/pacfile</a> </td></tr>
<tr><td> ftpProxy, httpProxy, sslProxy, socksProxy </td><td> string      </td><td> (Optional, Ignored if proxyType != <b>manual</b>) Specifies the proxies to be used for FTP, HTTP, HTTPS and SOCKS requests respectively. Behaviour is undefined if a request is made, where the proxy for the particular protocol is undefined, if proxyType is <b>manual</b>. Expected format example: hostname.com:1234 </td></tr>
<tr><td> socksUsername </td><td> string      </td><td> (Optional, Ignored if proxyType != <b>manual</b> and socksProxy is not set) Specifies SOCKS proxy username. </td></tr>
<tr><td> socksPassword </td><td> string      </td><td> (Optional, Ignored if proxyType != <b>manual</b> and socksProxy is not set) Specifies SOCKS proxy password. </td></tr>
<tr><td> noProxy    </td><td> string      </td><td> (Optional, Ignored if proxyType != <b>manual</b>) Specifies proxy bypass addresses. Format is driver specific. </td></tr></tbody></table>

## Messages

### Commands

WebDriver command messages should conform to the [HTTP/1.1 request specification](http://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html#sec5). Although the server may be extended to respond to other content-types, the wire protocol dictates that all commands accept a content-type of `application/json;charset=UTF-8`. Likewise, the message bodies for POST and PUT request must use an `application/json;charset=UTF-8` content-type.

Each command in the WebDriver service will be mapped to an HTTP method at a specific path. Path segments prefixed with a colon (:) indicate that segment is a variable used to further identify the underlying resource. For example, consider an arbitrary resource mapped as:
```
GET /favorite/color/:name
```
Given this mapping, the server should respond to GET requests sent to "/favorite/color/Jack" and "/favorite/color/Jill", with the variable `:name` set to "Jack" and "Jill", respectively.

### Responses

Command responses shall be sent as [HTTP/1.1 response messages](http://www.w3.org/Protocols/rfc2616/rfc2616-sec6.html#sec6). If the remote server must return a 4xx response, the response body shall have a Content-Type of text/plain and the message body shall be a descriptive message of the bad request. For all other cases, if a response includes a message body, it must have a Content-Type of application/json;charset=UTF-8 and will be a JSON object with the following properties:

| **Key** | **Type** | **Description** |
|:--------|:---------|:----------------|
| sessionId | string|null | An opaque handle used by the server to determine where to route session-specific commands. This ID should be included in all future session-commands in place of the :sessionId path segment variable. |
| status  | number   | A status code summarizing the result of the command. A non-zero value indicates that the command failed. |
| value   | `*`      | The response JSON value. |

#### Response Status Codes

The wire protocol will inherit its status codes from those used by the InternetExplorerDriver:

| **Code** | **Summary** | **Detail** |
|:---------|:------------|:-----------|
| 0        | `Success`   | The command executed successfully. |
| 6        | `NoSuchDriver` | A session is either terminated or not started |
| 7        | `NoSuchElement` | An element could not be located on the page using the given search parameters. |
| 8        | `NoSuchFrame` | A request to switch to a frame could not be satisfied because the frame could not be found. |
| 9        | `UnknownCommand` | The requested resource could not be found, or a request was received using an HTTP method that is not supported by the mapped resource. |
| 10       | `StaleElementReference` | An element command failed because the referenced element is no longer attached to the DOM. |
| 11       | `ElementNotVisible` | An element command could not be completed because the element is not visible on the page. |
| 12       | `InvalidElementState` | An element command could not be completed because the element is in an invalid state (e.g. attempting to click a disabled element). |
| 13       | `UnknownError` | An unknown server-side error occurred while processing the command. |
| 15       | `ElementIsNotSelectable` | An attempt was made to select an element that cannot be selected. |
| 17       | `JavaScriptError` | An error occurred while executing user supplied JavaScript. |
| 19       | `XPathLookupError` | An error occurred while searching for an element by XPath. |
| 21       | `Timeout`   | An operation did not complete before its timeout expired. |
| 23       | `NoSuchWindow` | A request to switch to a different window could not be satisfied because the window could not be found. |
| 24       | `InvalidCookieDomain` | An illegal attempt was made to set a cookie under a different domain than the current page. |
| 25       | `UnableToSetCookie` | A request to set a cookie's value could not be satisfied. |
| 26       | `UnexpectedAlertOpen` | A modal dialog was open, blocking this operation |
| 27       | `NoAlertOpenError` | An attempt was made to operate on a modal dialog when one was not open. |
| 28       | `ScriptTimeout` | A script did not complete before its timeout expired. |
| 29       | `InvalidElementCoordinates` | The coordinates provided to an interactions operation are invalid. |
| 30       | `IMENotAvailable` | IME was not available. |
| 31       | `IMEEngineActivationFailed` | An IME engine could not be started. |
| 32       | `InvalidSelector` | Argument was an invalid selector (e.g. XPath/CSS). |
| 33       | `SessionNotCreatedException` | A new session could not be created. |
| 34       | `MoveTargetOutOfBounds` | Target provided for a move action is out of bounds. |

The client should interpret a 404 Not Found response from the server as an "Unknown command" response. All other 4xx and 5xx responses from the server that do not define a status field should be interpreted as "Unknown error" responses.

### Error Handling

There are two levels of error handling specified by the wire protocol: invalid requests and failed commands.

#### Invalid Requests

All invalid requests should result in the server returning a 4xx HTTP response. The response Content-Type should be set to text/plain and the message body should be a descriptive error message. The categories of invalid requests are as follows:

<dl>
<dt><b>Unknown Commands</b></dt>
<dd>If the server receives a command request whose path is not mapped to a resource in the REST service, it should respond with a <code>404 Not Found</code> message.<br>
<br>
</dd>
<dt><b>Unimplemented Commands</b></dt>
<dd>Every server implementing the WebDriver wire protocol must respond to every defined command. If an individual command has not been implemented on the server, the server should respond with a <code>501 Not Implemented</code> error message. Note this is the only error in the Invalid Request category that does not return a <code>4xx</code> status code.<br>
<br>
</dd>
<dt><b>Variable Resource Not Found</b></dt>
<dd>If a request path maps to a variable resource, but that resource does not exist, then the server should respond with a <code>404 Not Found</code>. For example, if ID <code>my-session</code> is not a valid session ID on the server, and a command is sent to <code>GET /session/my-session HTTP/1.1</code>, then the server should gracefully return a <code>404</code>.<br>
<br>
</dd>
<dt><b>Invalid Command Method</b></dt>
<dd>If a request path maps to a valid resource, but that resource does not respond to the request method, the server should respond with a <code>405 Method Not Allowed</code>. The response must include an Allows header with a list of the allowed methods for the requested resource.<br>
<br>
</dd>
<dt><b>Missing Command Parameters</b></dt>
<dd>If a POST/PUT command maps to a resource that expects a set of JSON parameters, and the response body does not include one of those parameters, the server should respond with a <code>400 Bad Request</code>. The response body should list the missing parameters.<br>
<br>
</dd>
</dl>

#### Failed Commands

If a request maps to a valid command and contains all of the expected parameters in the request body, yet fails to execute successfully, then the server should send a 500 Internal Server Error. This response should have a Content-Type of `application/json;charset=UTF-8` and the response body should be a well formed JSON response object.

The response status should be one of the defined status codes and the response value should be another JSON object with detailed information for the failing command:

| Key | Type | Description |
|:----|:-----|:------------|
| message | string | A descriptive message for the command failure. |
| screen | string | (Optional) If included, a screenshot of the current page as a base64 encoded string. |
| class | string | (Optional) If included, specifies the fully qualified class name for the exception that was thrown when the command failed. |
| stackTrace | array | (Optional) If included, specifies an array of JSON objects describing the stack trace for the exception that was thrown when the command failed. The zeroeth element of the array represents the top of the stack. |

Each JSON object in the stackTrace array must contain the following properties:

| **Key** | **Type** | **Description** |
|:--------|:---------|:----------------|
| fileName | string   | The name of the source file containing the line represented by this frame. |
| className | string   | The fully qualified class name for the class active in this frame. If the class name cannot be determined, or is not applicable for the language the server is implemented in, then this property should be set to the empty string. |
| methodName | string   | The name of the method active in this frame, or the empty string if unknown/not applicable. |
| lineNumber | number   | The line number in the original source file for the frame, or 0 if unknown. |

## Resource Mapping

Resources in the WebDriver REST service are mapped to individual URL patterns. Each resource may respond to one or more HTTP request methods. If a resource responds to a GET request, then it should also respond to HEAD requests. All resources should respond to OPTIONS requests with an `Allow` header field, whose value is a list of all methods that resource responds to.

If a resource is mapped to a URL containing a variable path segment name, that path segment should be used to further route the request. Variable path segments are indicated in the resource mapping by a colon-prefix. For example, consider the following:
```
/favorite/color/:person
```
A resource mapped to this URL should parse the value of the `:person` path segment to further determine how to respond to the request. If this resource received a request for `/favorite/color/Jack`, then it should return Jack's favorite color. Likewise, the server should return Jill's favorite color for any requests to `/favorite/color/Jill`.

Two resources may only be mapped to the same URL pattern if one of those resources' patterns contains variable path segments, and the other does not. In these cases, the server should always route requests to the resource whose path is the best match for the request. Consider the following two resource paths:

1. `/session/:sessionId/element/active`
1. `/session/:sessionId/element/:id`

Given these mappings, the server should always route requests whose final path segment is active to the first resource. All other requests should be routed to second.

## Command Reference

### Command Summary

| **HTTP Method** | **Path** | **Summary** |
|:----------------|:---------|:------------|
| GET             | [/status](#status) | Query the server's current status. |
| POST            | [/session](#session) | Create a new session. |
| GET             | [/sessions](#sessions) | Returns a list of the currently active sessions. |
| GET             | [/session/:sessionId](#sessionsessionid) | Retrieve the capabilities of the specified session. |
| DELETE          | [/session/:sessionId](#sessionsessionid) | Delete the session. |
| POST            | [/session/:sessionId/timeouts](#sessionsessionidtimeouts) | Configure the amount of time that a particular type of operation can execute for before they are aborted and a |Timeout| error is returned to the client. |
| POST            | [/session/:sessionId/timeouts/async\_script](#sessionsessionidtimeoutsasync_script) | Set the amount of time, in milliseconds, that asynchronous scripts executed by `/session/:sessionId/execute_async` are permitted to run before they are aborted and a |Timeout| error is returned to the client. |
| POST            | [/session/:sessionId/timeouts/implicit\_wait](#sessionsessionidtimeoutsimplicit_wait) | Set the amount of time the driver should wait when searching for elements. |
| GET             | [/session/:sessionId/window\_handle](#sessionsessionidwindow_handle) | Retrieve the current window handle. |
| GET             | [/session/:sessionId/window\_handles](#sessionsessionidwindow_handles) | Retrieve the list of all window handles available to the session. |
| GET             | [/session/:sessionId/url](#sessionsessionidurl) | Retrieve the URL of the current page. |
| POST            | [/session/:sessionId/url](#sessionsessionidurl) | Navigate to a new URL. |
| POST            | [/session/:sessionId/forward](#sessionsessionidforward) | Navigate forwards in the browser history, if possible. |
| POST            | [/session/:sessionId/back](#sessionsessionidback) | Navigate backwards in the browser history, if possible. |
| POST            | [/session/:sessionId/refresh](#sessionsessionidrefresh) | Refresh the current page. |
| POST            | [/session/:sessionId/execute](#sessionsessionidexecute) | Inject a snippet of JavaScript into the page for execution in the context of the currently selected frame. |
| POST            | [/session/:sessionId/execute\_async](#sessionsessionidexecute_async) | Inject a snippet of JavaScript into the page for execution in the context of the currently selected frame. |
| GET             | [/session/:sessionId/screenshot](#sessionsessionidscreenshot) | Take a screenshot of the current page. |
| GET             | [/session/:sessionId/ime/available\_engines](#sessionsessionidimeavailable_engines) | List all available engines on the machine. |
| GET             | [/session/:sessionId/ime/active\_engine](#sessionsessionidimeactive_engine) | Get the name of the active IME engine. |
| GET             | [/session/:sessionId/ime/activated](#sessionsessionidimeactivated) | Indicates whether IME input is active at the moment (not if it's available. |
| POST            | [/session/:sessionId/ime/deactivate](#sessionsessionidimedeactivate) | De-activates the currently-active IME engine. |
| POST            | [/session/:sessionId/ime/activate](#sessionsessionidimeactivate) | Make an engines that is available (appears on the listreturned by getAvailableEngines) active. |
| POST            | [/session/:sessionId/frame](#sessionsessionidframe) | Change focus to another frame on the page. |
| POST            | [/session/:sessionId/frame/parent](#sessionsessionidframeparent) | Change focus to the parent context. |
| POST            | [/session/:sessionId/window](#sessionsessionidwindow) | Change focus to another window. |
| DELETE          | [/session/:sessionId/window](#sessionsessionidwindow) | Close the current window. |
| POST            | [/session/:sessionId/window/:windowHandle/size](#sessionsessionidwindowwindowhandlesize) | Change the size of the specified window. |
| GET             | [/session/:sessionId/window/:windowHandle/size](#sessionsessionidwindowwindowhandlesize) | Get the size of the specified window. |
| POST            | [/session/:sessionId/window/:windowHandle/position](#sessionsessionidwindowwindowhandleposition) | Change the position of the specified window. |
| GET             | [/session/:sessionId/window/:windowHandle/position](#sessionsessionidwindowwindowhandleposition) | Get the position of the specified window. |
| POST            | [/session/:sessionId/window/:windowHandle/maximize](#sessionsessionidwindowwindowhandlemaximize) | Maximize the specified window if not already maximized. |
| GET             | [/session/:sessionId/cookie](#sessionsessionidcookie) | Retrieve all cookies visible to the current page. |
| POST            | [/session/:sessionId/cookie](#sessionsessionidcookie) | Set a cookie. |
| DELETE          | [/session/:sessionId/cookie](#sessionsessionidcookie) | Delete all cookies visible to the current page. |
| DELETE          | [/session/:sessionId/cookie/:name](#sessionsessionidcookiename) | Delete the cookie with the given name. |
| GET             | [/session/:sessionId/source](#sessionsessionidsource) | Get the current page source. |
| GET             | [/session/:sessionId/title](#sessionsessionidtitle) | Get the current page title. |
| POST            | [/session/:sessionId/element](#sessionsessionidelement) | Search for an element on the page, starting from the document root. |
| POST            | [/session/:sessionId/elements](#sessionsessionidelements) | Search for multiple elements on the page, starting from the document root. |
| POST            | [/session/:sessionId/element/active](#sessionsessionidelementactive) | Get the element on the page that currently has focus. |
| GET             | [/session/:sessionId/element/:id](#sessionsessionidelementid) | Describe the identified element. |
| POST            | [/session/:sessionId/element/:id/element](#sessionsessionidelementidelement) | Search for an element on the page, starting from the identified element. |
| POST            | [/session/:sessionId/element/:id/elements](#sessionsessionidelementidelements) | Search for multiple elements on the page, starting from the identified element. |
| POST            | [/session/:sessionId/element/:id/click](#sessionsessionidelementidclick) | Click on an element. |
| POST            | [/session/:sessionId/element/:id/submit](#sessionsessionidelementidsubmit) | Submit a `FORM` element. |
| GET             | [/session/:sessionId/element/:id/text](#sessionsessionidelementidtext) | Returns the visible text for the element. |
| POST            | [/session/:sessionId/element/:id/value](#sessionsessionidelementidvalue) | Send a sequence of key strokes to an element. |
| POST            | [/session/:sessionId/keys](#sessionsessionidkeys) | Send a sequence of key strokes to the active element. |
| GET             | [/session/:sessionId/element/:id/name](#sessionsessionidelementidname) | Query for an element's tag name. |
| POST            | [/session/:sessionId/element/:id/clear](#sessionsessionidelementidclear) | Clear a `TEXTAREA` or `text INPUT` element's value. |
| GET             | [/session/:sessionId/element/:id/selected](#sessionsessionidelementidselected) | Determine if an `OPTION` element, or an `INPUT` element of type `checkbox` or `radiobutton` is currently selected. |
| GET             | [/session/:sessionId/element/:id/enabled](#sessionsessionidelementidenabled) | Determine if an element is currently enabled. |
| GET             | [/session/:sessionId/element/:id/attribute/:name](#sessionsessionidelementidattribute/:name) | Get the value of an element's attribute. |
| GET             | [/session/:sessionId/element/:id/equals/:other](#sessionsessionidelementidequals/:other) | Test if two element IDs refer to the same DOM element. |
| GET             | [/session/:sessionId/element/:id/displayed](#sessionsessionidelementiddisplayed) | Determine if an element is currently displayed. |
| GET             | [/session/:sessionId/element/:id/location](#sessionsessionidelementidlocation) | Determine an element's location on the page. |
| GET             | [/session/:sessionId/element/:id/location\_in\_view](#sessionsessionidelementidlocation_in_view) | Determine an element's location on the screen once it has been scrolled into view. |
| GET             | [/session/:sessionId/element/:id/size](#sessionsessionidelementidsize) | Determine an element's size in pixels. |
| GET             | [/session/:sessionId/element/:id/css/:propertyName](#sessionsessionidelementidcss/:propertyName) | Query the value of an element's computed CSS property. |
| GET             | [/session/:sessionId/orientation](#sessionsessionidorientation) | Get the current browser orientation. |
| POST            | [/session/:sessionId/orientation](#sessionsessionidorientation) | Set the browser orientation. |
| GET             | [/session/:sessionId/alert\_text](#sessionsessionidalert_text) | Gets the text of the currently displayed JavaScript `alert()`, `confirm()`, or `prompt()` dialog. |
| POST            | [/session/:sessionId/alert\_text](#sessionsessionidalert_text) | Sends keystrokes to a JavaScript `prompt()` dialog. |
| POST            | [/session/:sessionId/accept\_alert](#sessionsessionidaccept_alert) | Accepts the currently displayed alert dialog. |
| POST            | [/session/:sessionId/dismiss\_alert](#sessionsessioniddismiss_alert) | Dismisses the currently displayed alert dialog. |
| POST            | [/session/:sessionId/moveto](#sessionsessionidmoveto) | Move the mouse by an offset of the specificed element. |
| POST            | [/session/:sessionId/click](#sessionsessionidclick) | Click any mouse button (at the coordinates set by the last moveto command). |
| POST            | [/session/:sessionId/buttondown](#sessionsessionidbuttondown) | Click and hold the left mouse button (at the coordinates set by the last moveto command). |
| POST            | [/session/:sessionId/buttonup](#sessionsessionidbuttonup) | Releases the mouse button previously held (where the mouse is currently at). |
| POST            | [/session/:sessionId/doubleclick](#sessionsessioniddoubleclick) | Double-clicks at the current mouse coordinates (set by moveto). |
| POST            | [/session/:sessionId/touch/click](#sessionsessionidtouchclick) | Single tap on the touch enabled device. |
| POST            | [/session/:sessionId/touch/down](#sessionsessionidtouchdown) | Finger down on the screen. |
| POST            | [/session/:sessionId/touch/up](#sessionsessionidtouchup) | Finger up on the screen. |
| POST            | [session/:sessionId/touch/move](#sessionsessionidtouchmove) | Finger move on the screen. |
| POST            | [session/:sessionId/touch/scroll](#sessionsessionidtouchscroll) | Scroll on the touch screen using finger based motion events. |
| POST            | [session/:sessionId/touch/scroll](#sessionsessionidtouchscroll) | Scroll on the touch screen using finger based motion events. |
| POST            | [session/:sessionId/touch/doubleclick](#sessionsessionidtouchdoubleclick) | Double tap on the touch screen using finger motion events. |
| POST            | [session/:sessionId/touch/longclick](#sessionsessionidtouchlongclick) | Long press on the touch screen using finger motion events. |
| POST            | [session/:sessionId/touch/flick](#sessionsessionidtouchflick) | Flick on the touch screen using finger motion events. |
| POST            | [session/:sessionId/touch/flick](#sessionsessionidtouchflick) | Flick on the touch screen using finger motion events. |
| GET             | [/session/:sessionId/location](#sessionsessionidlocation) | Get the current geo location. |
| POST            | [/session/:sessionId/location](#sessionsessionidlocation) | Set the current geo location. |
| GET             | [/session/:sessionId/local\_storage](#sessionsessionidlocal_storage) | Get all keys of the storage. |
| POST            | [/session/:sessionId/local\_storage](#sessionsessionidlocal_storage) | Set the storage item for the given key. |
| DELETE          | [/session/:sessionId/local\_storage](#sessionsessionidlocal_storage) | Clear the storage. |
| GET             | [/session/:sessionId/local\_storage/key/:key](#sessionsessionidlocal_storagekeykey) | Get the storage item for the given key. |
| DELETE          | [/session/:sessionId/local\_storage/key/:key](#sessionsessionidlocal_storagekeykey) | Remove the storage item for the given key. |
| GET             | [/session/:sessionId/local\_storage/size](#sessionsessionidlocal_storagesize) | Get the number of items in the storage. |
| GET             | [/session/:sessionId/session\_storage](#sessionsessionidsession_storage) | Get all keys of the storage. |
| POST            | [/session/:sessionId/session\_storage](#sessionsessionidsession_storage) | Set the storage item for the given key. |
| DELETE          | [/session/:sessionId/session\_storage](#sessionsessionidsession_storage) | Clear the storage. |
| GET             | [/session/:sessionId/session\_storage/key/:key](#sessionsessionidsession_storagekeykey) | Get the storage item for the given key. |
| DELETE          | [/session/:sessionId/session\_storage/key/:key](#sessionsessionidsession_storagekeykey) | Remove the storage item for the given key. |
| GET             | [/session/:sessionId/session\_storage/size](#sessionsessionidsession_storagesize) | Get the number of items in the storage. |
| POST            | [/session/:sessionId/log](#sessionsessionidlog) | Get the log for a given log type. |
| GET             | [/session/:sessionId/log/types](#sessionsessionidlogtypes) | Get available log types. |
| GET             | [/session/:sessionId/application\_cache/status](#sessionsessionidapplication_cachestatus) | Get the status of the html5 application cache. |


### Command Detail

#### /status

<dl>
<dd>
<h4>GET /status</h4>
</dd>
<dd>
<dl>
<dd>
Query the server's current status.  The server should respond with a general "HTTP 200 OK" response if it is alive and accepting commands. The response body should be a JSON object describing the state of the server. All server implementations should return two basic objects describing the server's current platform and when the server was built. All fields are optional; if omitted, the client should assume the value is uknown. Furthermore, server implementations may include additional fields not listed here.<br>
<br>
<table><thead><th> <b>Key</b> </th><th> <b>Type</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> build      </td><td> object      </td><td>                    </td></tr>
<tr><td> build.version </td><td> string      </td><td> A generic release label (i.e. "2.0rc3") </td></tr>
<tr><td> build.revision </td><td> string      </td><td> The revision of the local source control client from which the server was built </td></tr>
<tr><td> build.time </td><td> string      </td><td> A timestamp from when the server was built. </td></tr>
<tr><td> os         </td><td> object      </td><td>                    </td></tr>
<tr><td> os.arch    </td><td> string      </td><td> The current system architecture. </td></tr>
<tr><td> os.name    </td><td> string      </td><td> The name of the operating system the server is currently running on: "windows", "linux", etc. </td></tr>
<tr><td> os.version </td><td> string      </td><td> The operating system version. </td></tr></tbody></table>

</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{object}</code> An object describing the general status of the server.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session

<dl>
<dd>
<h4>POST /session</h4>
</dd>
<dd>
<dl>
<dd>
Create a new session. The server should attempt to create a session that most closely matches the desired and required capabilities. Required capabilities have higher priority than desired capabilities and must be set for the session to be created.</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>desiredCapabilities</code> - <code>{object}</code> An object describing the session's <a href='#Desired_Capabilities.md'>desired capabilities</a>.</dd>
<dd><code>requiredCapabilities</code> - <code>{object}</code> An object describing the session's <a href='#Desired_Capabilities.md'>required capabilities</a> (Optional).</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{object}</code> An object describing the session's <a href='#Actual_Capabilities.md'>capabilities</a>.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>SessionNotCreatedException</code> - If a required capability could not be set.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /sessions

<dl>
<dd>
<h4>GET /sessions</h4>
</dd>
<dd>
<dl>
<dd>
Returns a list of the currently active sessions. Each session will be returned as a list of JSON objects with the following keys:<br>
<br>
<table><thead><th> <b>Key</b> </th><th> <b>Type</b> </th><th> <b>Description</b></th></thead><tbody>
<tr><td> id         </td><td> string      </td><td> The session ID. </td></tr>
<tr><td> capabilities </td><td> object      </td><td> An object describing the session's <a href='#Actual_Capabilities.md'>capabilities</a>. </td></tr></tbody></table>

</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;Object&gt;}</code> A list of the currently active sessions.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId

<dl>
<dd>
<h4>GET /session/:sessionId</h4>
</dd>
<dd>
<dl>
<dd>Retrieve the capabilities of the specified session.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{object}</code> An object describing the session's <a href='#Actual_Capabilities.md'>capabilities</a>.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>DELETE /session/:sessionId</h4>
</dd>
<dd>
<dl>
<dd>Delete the session.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/timeouts

<dl>
<dd>
<h4>POST /session/:sessionId/timeouts</h4>
</dd>
<dd>
<dl>
<dd>
Configure the amount of time that a particular type of operation can execute for before they are aborted and a |Timeout| error is returned to the client.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>type</code> - <code>{string}</code> The type of operation to set the timeout for. Valid values are: "script" for script timeouts, "implicit" for modifying the implicit wait timeout and "page load" for setting a page load timeout.</dd>
<dd><code>ms</code> - <code>{number}</code> The amount of time, in milliseconds, that time-limited commands are permitted to run.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/timeouts/async\_script

<dl>
<dd>
<h4>POST /session/:sessionId/timeouts/async_script</h4>
</dd>
<dd>
<dl>
<dd>Set the amount of time, in milliseconds, that asynchronous scripts executed by <code>/session/:sessionId/execute_async</code> are permitted to run before they are aborted and a |Timeout| error is returned to the client.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>ms</code> - <code>{number}</code> The amount of time, in milliseconds, that time-limited commands are permitted to run.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/timeouts/implicit\_wait

<dl>
<dd>
<h4>POST /session/:sessionId/timeouts/implicit_wait</h4>
</dd>
<dd>
<dl>
<dd>Set the amount of time the driver should wait when searching for elements. When<br>
searching for a single element, the driver should poll the page until an element is found or<br>
the timeout expires, whichever occurs first. When searching for multiple elements, the driver<br>
should poll the page until at least one element is found or the timeout expires, at which point<br>
it should return an empty list.<br>
<br>
If this command is never sent, the driver should default to an implicit wait of 0ms.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>ms</code> - <code>{number}</code> The amount of time to wait, in milliseconds. This value has a lower bound of 0.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/window\_handle

<dl>
<dd>
<h4>GET /session/:sessionId/window_handle</h4>
</dd>
<dd>
<dl>
<dd>Retrieve the current window handle.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The current window handle.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/window\_handles

<dl>
<dd>
<h4>GET /session/:sessionId/window_handles</h4>
</dd>
<dd>
<dl>
<dd>Retrieve the list of all window handles available to the session.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;string&gt;}</code> A list of window handles.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/url

<dl>
<dd>
<h4>GET /session/:sessionId/url</h4>
</dd>
<dd>
<dl>
<dd>Retrieve the URL of the current page.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The current URL.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>POST /session/:sessionId/url</h4>
</dd>
<dd>
<dl>
<dd>Navigate to a new URL.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>url</code> - <code>{string}</code> The URL to navigate to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/forward

<dl>
<dd>
<h4>POST /session/:sessionId/forward</h4>
</dd>
<dd>
<dl>
<dd>Navigate forwards in the browser history, if possible.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/back

<dl>
<dd>
<h4>POST /session/:sessionId/back</h4>
</dd>
<dd>
<dl>
<dd>Navigate backwards in the browser history, if possible.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/refresh

<dl>
<dd>
<h4>POST /session/:sessionId/refresh</h4>
</dd>
<dd>
<dl>
<dd>Refresh the current page.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/execute

<dl>
<dd>
<h4>POST /session/:sessionId/execute</h4>
</dd>
<dd>
<dl>
<dd>
Inject a snippet of JavaScript into the page for execution in the context of the currently selected frame. The executed script is assumed to be synchronous and the result of evaluating the script is returned to the client.<br>
<br>
The <code>script</code> argument defines the script to execute in the form of a function body.  The value returned by that function will be returned to the client.  The function will be invoked with the provided <code>args</code> array and the values may be accessed via the <code>arguments</code> object in the order specified.<br>
<br>
Arguments may be any JSON-primitive, array, or JSON object.  JSON objects that define a <a href='#WebElement_JSON_Object.md'>WebElement reference</a> will be converted to the corresponding DOM element. Likewise, any WebElements in the script result will be returned to the client as <a href='#WebElement_JSON_Object.md'>WebElement JSON objects</a>.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>script</code> - <code>{string}</code> The script to execute.</dd>
<dd><code>args</code> - <code>{Array.&lt;*&gt;}</code> The script arguments.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{*}</code> The script result.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If one of the script arguments is a WebElement that is not attached to the page's DOM.</dd>
<dd><code>JavaScriptError</code> - If the script throws an Error.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/execute\_async

<dl>
<dd>
<h4>POST /session/:sessionId/execute_async</h4>
</dd>
<dd>
<dl>
<dd>
Inject a snippet of JavaScript into the page for execution in the context of the currently selected frame. The executed script is assumed to be asynchronous and must signal that is done by invoking the provided callback, which is always provided as the final argument to the function.  The value to this callback will be returned to the client.<br>
<br>
Asynchronous script commands may not span page loads.  If an <code>unload</code> event is fired while waiting for a script result, an error should be returned to the client.<br>
<br>
The <code>script</code> argument defines the script to execute in teh form of a function body.  The function will be invoked with the provided <code>args</code> array and the values may be accessed via the <code>arguments</code> object in the order specified. The final argument will always be a callback function that must be invoked to signal that the script has finished.<br>
<br>
Arguments may be any JSON-primitive, array, or JSON object.  JSON objects that define a <a href='#WebElement_JSON_Object.md'>WebElement reference</a> will be converted to the corresponding DOM element. Likewise, any WebElements in the script result will be returned to the client as <a href='#WebElement_JSON_Object.md'>WebElement JSON objects</a>.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>script</code> - <code>{string}</code> The script to execute.</dd>
<dd><code>args</code> - <code>{Array.&lt;*&gt;}</code> The script arguments.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{*}</code> The script result.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If one of the script arguments is a WebElement that is not attached to the page's DOM.</dd>
<dd><code>Timeout</code> - If the script callback is not invoked before the timout expires. Timeouts are controlled by the <code>/session/:sessionId/timeout/async_script</code> command.</dd>
<dd><code>JavaScriptError</code> - If the script throws an Error or if an <code>unload</code> event is fired while waiting for the script to finish.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/screenshot

<dl>
<dd>
<h4>GET /session/:sessionId/screenshot</h4>
</dd>
<dd>
<dl>
<dd>Take a screenshot of the current page.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The screenshot as a base64 encoded PNG.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/ime/available\_engines

<dl>
<dd>
<h4>GET /session/:sessionId/ime/available_engines</h4>
</dd>
<dd>
<dl>
<dd>List all available engines on the machine. To use an engine, it has to be present in this list.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;string&gt;}</code> A list of available engines</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>ImeNotAvailableException</code> - If the host does not support IME</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/ime/active\_engine

<dl>
<dd>
<h4>GET /session/:sessionId/ime/active_engine</h4>
</dd>
<dd>
<dl>
<dd>Get the name of the active IME engine. The name string is platform specific.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The name of the active IME engine.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>ImeNotAvailableException</code> - If the host does not support IME</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/ime/activated

<dl>
<dd>
<h4>GET /session/:sessionId/ime/activated</h4>
</dd>
<dd>
<dl>
<dd>Indicates whether IME input is active at the moment (not if it's available.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{boolean}</code> true if IME input is available and currently active, false otherwise</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>ImeNotAvailableException</code> - If the host does not support IME</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/ime/deactivate

<dl>
<dd>
<h4>POST /session/:sessionId/ime/deactivate</h4>
</dd>
<dd>
<dl>
<dd>De-activates the currently-active IME engine.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>ImeNotAvailableException</code> - If the host does not support IME</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/ime/activate

<dl>
<dd>
<h4>POST /session/:sessionId/ime/activate</h4>
</dd>
<dd>
<dl>
<dd>Make an engines that is available (appears on the list<br>
returned by getAvailableEngines) active. After this call, the engine will<br>
be added to the list of engines loaded in the IME daemon and the input sent<br>
using sendKeys will be converted by the active engine.<br>
Note that this is a platform-independent method of activating IME<br>
(the platform-specific way being using keyboard shortcuts</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>engine</code> - <code>{string}</code> Name of the engine to activate.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>ImeActivationFailedException</code> - If the engine is not available or if the activation fails for other reasons.</dd>
<dd><code>ImeNotAvailableException</code> - If the host does not support IME</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/frame

<dl>
<dd>
<h4>POST /session/:sessionId/frame</h4>
</dd>
<dd>
<dl>
<dd>Change focus to another frame on the page. If the frame <code>id</code> is <code>null</code>, the server<br>
should switch to the page's default content.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>id</code> - <code>{string|number|null|WebElement JSON Object}</code> Identifier for the frame to change focus to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>NoSuchFrame</code> - If the frame specified by <code>id</code> cannot be found.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/frame/parent

<dl>
<dd>
<h4>POST /session/:sessionId/frame/parent</h4>
</dd>
<dd>
<dl>
<dd>Change focus to the parent context. If the current context is the top level browsing context, the context remains unchanged.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/window

<dl>
<dd>
<h4>POST /session/:sessionId/window</h4>
</dd>
<dd>
<dl>
<dd>Change focus to another window. The window to change focus to may be specified by its<br>
server assigned window handle, or by the value of its <code>name</code> attribute.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>name</code> - <code>{string}</code> The window to change focus to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the window specified by <code>name</code> cannot be found.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>DELETE /session/:sessionId/window</h4>
</dd>
<dd>
<dl>
<dd>Close the current window.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window is already closed</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/window/:windowHandle/size

<dl>
<dd>
<h4>POST /session/:sessionId/window/:windowHandle/size</h4>
</dd>
<dd>
<dl>
<dd>Change the size of the specified window. If the :windowHandle URL parameter is "current", the currently active window will be resized.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>width</code> - <code>{number}</code> The new window width.</dd>
<dd><code>height</code> - <code>{number}</code> The new window height.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>GET /session/:sessionId/window/:windowHandle/size</h4>
</dd>
<dd>
<dl>
<dd>Get the size of the specified window. If the :windowHandle URL parameter is "current", the size of the currently active window will be returned.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{width: number, height: number}</code> The size of the window.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the specified window cannot be found.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/window/:windowHandle/position

<dl>
<dd>
<h4>POST /session/:sessionId/window/:windowHandle/position</h4>
</dd>
<dd>
<dl>
<dd>Change the position of the specified window. If the :windowHandle URL parameter is "current", the currently active window will be moved.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>x</code> - <code>{number}</code> The X coordinate to position the window at, relative to the upper left corner of the screen.</dd>
<dd><code>y</code> - <code>{number}</code> The Y coordinate to position the window at, relative to the upper left corner of the screen.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the specified window cannot be found.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>GET /session/:sessionId/window/:windowHandle/position</h4>
</dd>
<dd>
<dl>
<dd>Get the position of the specified window. If the :windowHandle URL       parameter is "current", the position of the currently active window will be returned.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{x: number, y: number}</code> The X and Y coordinates for the window, relative to the upper left corner of the screen.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the specified window cannot be found.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/window/:windowHandle/maximize

<dl>
<dd>
<h4>POST /session/:sessionId/window/:windowHandle/maximize</h4>
</dd>
<dd>
<dl>
<dd>Maximize the specified window if not already maximized. If the :windowHandle URL parameter is "current", the currently active window will be maximized.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the specified window cannot be found.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/cookie

<dl>
<dd>
<h4>GET /session/:sessionId/cookie</h4>
</dd>
<dd>
<dl>
<dd>Retrieve all cookies visible to the current page.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;object&gt;}</code> A list of <a href='#Cookie_JSON_Object.md'>cookies</a>.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>POST /session/:sessionId/cookie</h4>
</dd>
<dd>
<dl>
<dd>Set a cookie. If the <a href='#Cookie_JSON_Object.md'>cookie</a> path is not specified, it should be set to <code>"/"</code>. Likewise, if the domain is omitted, it should default to the current page's domain.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>cookie</code> - <code>{object}</code> A <a href='#Cookie_JSON_Object.md'>JSON object</a> defining the cookie to add.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>DELETE /session/:sessionId/cookie</h4>
</dd>
<dd>
<dl>
<dd>Delete all cookies visible to the current page.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>InvalidCookieDomain</code> - If the cookie's <code>domain</code> is not visible from the current page.</dd>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>UnableToSetCookie</code> - If attempting to set a cookie on a page that does not support cookies (e.g. pages with mime-type <code>text/plain</code>).</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/cookie/:name

<dl>
<dd>
<h4>DELETE /session/:sessionId/cookie/:name</h4>
</dd>
<dd>
<dl>
<dd>Delete the cookie with the given name. This command should be a no-op if there is no<br>
such cookie visible to the current page.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:name</code> - The name of the cookie to delete.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/source

<dl>
<dd>
<h4>GET /session/:sessionId/source</h4>
</dd>
<dd>
<dl>
<dd>Get the current page source.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The current page source.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/title

<dl>
<dd>
<h4>GET /session/:sessionId/title</h4>
</dd>
<dd>
<dl>
<dd>Get the current page title.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The current page title.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element

<dl>
<dd>
<h4>POST /session/:sessionId/element</h4>
</dd>
<dd>
<dl>
<dd>Search for an element on the page, starting from the document root. The located element will be returned as a WebElement JSON object. The table below lists the locator strategies that each server should support. Each locator must return the first matching element located in the DOM.<br>
<br>
<table><thead><th> <b>Strategy</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> class name      </td><td> Returns an element whose class name contains the search value; compound class names are not permitted. </td></tr>
<tr><td> css selector    </td><td> Returns an element matching a CSS selector. </td></tr>
<tr><td> id              </td><td> Returns an element whose ID attribute matches the search value. </td></tr>
<tr><td> name            </td><td> Returns an element whose NAME attribute matches the search value. </td></tr>
<tr><td> link text       </td><td> Returns an anchor element whose visible text matches the search value. </td></tr>
<tr><td> partial link text </td><td> Returns an anchor element whose visible text partially matches the search value. </td></tr>
<tr><td> tag name        </td><td> Returns an element whose tag name matches the search value. </td></tr>
<tr><td> xpath           </td><td> Returns an element matching an XPath expression. </td></tr></tbody></table>

</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>using</code> - <code>{string}</code> The locator strategy to use.</dd>
<dd><code>value</code> - <code>{string}</code> The The search target.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{ELEMENT:string}</code> A WebElement JSON object for the located element.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>NoSuchElement</code> - If the element cannot be found.</dd>
<dd><code>XPathLookupError</code> - If using XPath and the input expression is invalid.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/elements

<dl>
<dd>
<h4>POST /session/:sessionId/elements</h4>
</dd>
<dd>
<dl>
<dd>Search for multiple elements on the page, starting from the document root. The located elements will be returned as a WebElement JSON objects. The table below lists the locator strategies that each server should support. Elements should be returned in the order located in the DOM.<br>
<br>
<table><thead><th> <b>Strategy</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> class name      </td><td> Returns all elements whose class name contains the search value; compound class names are not permitted. </td></tr>
<tr><td> css selector    </td><td> Returns all elements matching a CSS selector. </td></tr>
<tr><td> id              </td><td> Returns all elements whose ID attribute matches the search value. </td></tr>
<tr><td> name            </td><td> Returns all elements whose NAME attribute matches the search value. </td></tr>
<tr><td> link text       </td><td> Returns all anchor elements whose visible text matches the search value. </td></tr>
<tr><td> partial link text </td><td> Returns all anchor elements whose visible text partially matches the search value. </td></tr>
<tr><td> tag name        </td><td> Returns all elements whose tag name matches the search value. </td></tr>
<tr><td> xpath           </td><td> Returns all elements matching an XPath expression. </td></tr></tbody></table>

</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>using</code> - <code>{string}</code> The locator strategy to use.</dd>
<dd><code>value</code> - <code>{string}</code> The The search target.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;{ELEMENT:string}&gt;}</code> A list of WebElement JSON objects for the located elements.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>XPathLookupError</code> - If using XPath and the input expression is invalid.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/active

<dl>
<dd>
<h4>POST /session/:sessionId/element/active</h4>
</dd>
<dd>
<dl>
<dd>Get the element on the page that currently has focus. The element will be returned as a WebElement JSON object.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{ELEMENT:string}</code> A WebElement JSON object for the active element.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id</h4>
</dd>
<dd>
<dl>
<dd>Describe the identified element.<br>
<br>
<b>Note:</b> This command is reserved for future use; its return type is currently undefined.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/element

<dl>
<dd>
<h4>POST /session/:sessionId/element/:id/element</h4>
</dd>
<dd>
<dl>
<dd>Search for an element on the page, starting from the identified element. The located element will be returned as a WebElement JSON object. The table below lists the locator strategies that each server should support. Each locator must return the first matching element located in the DOM.<br>
<br>
<table><thead><th> <b>Strategy</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> class name      </td><td> Returns an element whose class name contains the search value; compound class names are not permitted. </td></tr>
<tr><td> css selector    </td><td> Returns an element matching a CSS selector. </td></tr>
<tr><td> id              </td><td> Returns an element whose ID attribute matches the search value. </td></tr>
<tr><td> name            </td><td> Returns an element whose NAME attribute matches the search value. </td></tr>
<tr><td> link text       </td><td> Returns an anchor element whose visible text matches the search value. </td></tr>
<tr><td> partial link text </td><td> Returns an anchor element whose visible text partially matches the search value. </td></tr>
<tr><td> tag name        </td><td> Returns an element whose tag name matches the search value. </td></tr>
<tr><td> xpath           </td><td> Returns an element matching an XPath expression. The provided XPath expression must be applied to the server "as is"; if the expression is not relative to the element root, the server should not modify it. Consequently, an XPath query may return elements not contained in the root element's subtree. </td></tr></tbody></table>

</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>using</code> - <code>{string}</code> The locator strategy to use.</dd>
<dd><code>value</code> - <code>{string}</code> The The search target.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{ELEMENT:string}</code> A WebElement JSON object for the located element.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
<dd><code>NoSuchElement</code> - If the element cannot be found.</dd>
<dd><code>XPathLookupError</code> - If using XPath and the input expression is invalid.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/elements

<dl>
<dd>
<h4>POST /session/:sessionId/element/:id/elements</h4>
</dd>
<dd>
<dl>
<dd>Search for multiple elements on the page, starting from the identified element. The located elements will be returned as a WebElement JSON objects. The table below lists the locator strategies that each server should support. Elements should be returned in the order located in the DOM.<br>
<br>
<table><thead><th> <b>Strategy</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td> class name      </td><td> Returns all elements whose class name contains the search value; compound class names are not permitted. </td></tr>
<tr><td> css selector    </td><td> Returns all elements matching a CSS selector. </td></tr>
<tr><td> id              </td><td> Returns all elements whose ID attribute matches the search value. </td></tr>
<tr><td> name            </td><td> Returns all elements whose NAME attribute matches the search value. </td></tr>
<tr><td> link text       </td><td> Returns all anchor elements whose visible text matches the search value. </td></tr>
<tr><td> partial link text </td><td> Returns all anchor elements whose visible text partially matches the search value. </td></tr>
<tr><td> tag name        </td><td> Returns all elements whose tag name matches the search value. </td></tr>
<tr><td> xpath           </td><td> Returns all elements matching an XPath expression. The provided XPath expression must be applied to the server "as is"; if the expression is not relative to the element root, the server should not modify it. Consequently, an XPath query may return elements not contained in the root element's subtree. </td></tr></tbody></table>

</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>using</code> - <code>{string}</code> The locator strategy to use.</dd>
<dd><code>value</code> - <code>{string}</code> The The search target.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;{ELEMENT:string}&gt;}</code> A list of WebElement JSON objects for the located elements.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
<dd><code>XPathLookupError</code> - If using XPath and the input expression is invalid.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/click

<dl>
<dd>
<h4>POST /session/:sessionId/element/:id/click</h4>
</dd>
<dd>
<dl>
<dd>Click on an element.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
<dd><code>ElementNotVisible</code> - If the referenced element is not visible on the page (either is hidden by CSS, has 0-width, or has 0-height)</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/submit

<dl>
<dd>
<h4>POST /session/:sessionId/element/:id/submit</h4>
</dd>
<dd>
<dl>
<dd>Submit a <code>FORM</code> element. The submit command may also be applied to any element that is a descendant of a <code>FORM</code> element.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/text

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/text</h4>
</dd>
<dd>
<dl>
<dd>Returns the visible text for the element.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/value

<dl>
<dd>
<h4>POST /session/:sessionId/element/:id/value</h4>
</dd>
<dd>
<dl>
<dd>Send a sequence of key strokes to an element.<br>
<br>
Any UTF-8 character may be specified, however, if the server does not support native key events, it should simulate key strokes for a standard US keyboard layout. The Unicode <a href='http://unicode.org/faq/casemap_charprop.html#8'>Private Use Area</a> code points, 0xE000-0xF8FF, are used to represent pressable, non-text  keys (see table below).<br>
<br>
<br>
<table cellpadding='5' cellspacing='5'>
<tbody><tr><td valign='top'>
<table><thead><th> <b>Key</b> </th><th> <b>Code</b> </th></thead><tbody>
<tr><td> NULL       </td><td> U+E000      </td></tr>
<tr><td> Cancel     </td><td> U+E001      </td></tr>
<tr><td> Help       </td><td> U+E002      </td></tr>
<tr><td> Back space </td><td> U+E003      </td></tr>
<tr><td> Tab        </td><td> U+E004      </td></tr>
<tr><td> Clear      </td><td> U+E005      </td></tr>
<tr><td> Return<sup>1</sup> </td><td> U+E006      </td></tr>
<tr><td> Enter<sup>1</sup> </td><td> U+E007      </td></tr>
<tr><td> Shift      </td><td> U+E008      </td></tr>
<tr><td> Control    </td><td> U+E009      </td></tr>
<tr><td> Alt        </td><td> U+E00A      </td></tr>
<tr><td> Pause      </td><td> U+E00B      </td></tr>
<tr><td> Escape     </td><td> U+E00C      </td></tr></tbody></table>

</td><td valign='top'>
<table><thead><th> <b>Key</b> </th><th> <b>Code</b> </th></thead><tbody>
<tr><td> Space      </td><td> U+E00D      </td></tr>
<tr><td> Pageup     </td><td> U+E00E      </td></tr>
<tr><td> Pagedown   </td><td> U+E00F      </td></tr>
<tr><td> End        </td><td> U+E010      </td></tr>
<tr><td> Home       </td><td> U+E011      </td></tr>
<tr><td> Left arrow </td><td> U+E012      </td></tr>
<tr><td> Up arrow   </td><td> U+E013      </td></tr>
<tr><td> Right arrow </td><td> U+E014      </td></tr>
<tr><td> Down arrow </td><td> U+E015      </td></tr>
<tr><td> Insert     </td><td> U+E016      </td></tr>
<tr><td> Delete     </td><td> U+E017      </td></tr>
<tr><td> Semicolon  </td><td> U+E018      </td></tr>
<tr><td> Equals     </td><td> U+E019      </td></tr></tbody></table>

</td><td valign='top'>
<table><thead><th> <b>Key</b> </th><th> <b>Code</b> </th></thead><tbody>
<tr><td> Numpad 0   </td><td> U+E01A      </td></tr>
<tr><td> Numpad 1   </td><td> U+E01B      </td></tr>
<tr><td> Numpad 2   </td><td> U+E01C      </td></tr>
<tr><td> Numpad 3   </td><td> U+E01D      </td></tr>
<tr><td> Numpad 4   </td><td> U+E01E      </td></tr>
<tr><td> Numpad 5   </td><td> U+E01F      </td></tr>
<tr><td> Numpad 6   </td><td> U+E020      </td></tr>
<tr><td> Numpad 7   </td><td> U+E021      </td></tr>
<tr><td> Numpad 8   </td><td> U+E022      </td></tr>
<tr><td> Numpad 9   </td><td> U+E023      </td></tr></tbody></table>

</td><td valign='top'>
<table><thead><th> <b>Key</b> </th><th> <b>Code</b> </th></thead><tbody>
<tr><td> Multiply   </td><td> U+E024      </td></tr>
<tr><td> Add        </td><td> U+E025      </td></tr>
<tr><td> Separator  </td><td> U+E026      </td></tr>
<tr><td> Subtract   </td><td> U+E027      </td></tr>
<tr><td> Decimal    </td><td> U+E028      </td></tr>
<tr><td> Divide     </td><td> U+E029      </td></tr></tbody></table>

</td><td valign='top'>
<table><thead><th> <b>Key</b> </th><th> <b>Code</b> </th></thead><tbody>
<tr><td> F1         </td><td> U+E031      </td></tr>
<tr><td> F2         </td><td> U+E032      </td></tr>
<tr><td> F3         </td><td> U+E033      </td></tr>
<tr><td> F4         </td><td> U+E034      </td></tr>
<tr><td> F5         </td><td> U+E035      </td></tr>
<tr><td> F6         </td><td> U+E036      </td></tr>
<tr><td> F7         </td><td> U+E037      </td></tr>
<tr><td> F8         </td><td> U+E038      </td></tr>
<tr><td> F9         </td><td> U+E039      </td></tr>
<tr><td> F10        </td><td> U+E03A      </td></tr>
<tr><td> F11        </td><td> U+E03B      </td></tr>
<tr><td> F12        </td><td> U+E03C      </td></tr>
<tr><td> Command/Meta </td><td> U+E03D      </td></tr></tbody></table>

</td></tr>
<tr><td><sup>1</sup> The return key is <i>not the same</i> as the <a href='http://en.wikipedia.org/wiki/Enter_key'>enter key</a>.</td></tr></tbody></table>

The server must process the key sequence as follows:<br>
<ul><li>Each key that appears on the keyboard without requiring modifiers are sent as a keydown followed by a key up.<br>
</li><li>If the server does not support native events and must simulate key strokes with JavaScript, it must generate keydown, keypress, and keyup events, in that order. The keypress event should only be fired when the corresponding key is for a printable character.<br>
</li><li>If a key requires a modifier key (e.g. "!" on a standard US keyboard), the sequence is: <var>modifier</var> down, <var>key</var> down, <var>key</var> up, <var>modifier</var> up, where <var>key</var> is the ideal unmodified key value (using the previous example, a "1").<br>
</li><li>Modifier keys (Ctrl, Shift, Alt, and Command/Meta) are assumed to be "sticky"; each modifier should be held down (e.g. only a keydown event) until either the modifier is encountered again in the sequence, or the <code>NULL</code> (U+E000) key is encountered.<br>
</li><li>Each key sequence is terminated with an implicit <code>NULL</code> key. Subsequently, all depressed modifier keys must be released (with corresponding keyup events) at the end of the sequence.<br>
</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>value</code> - <code>{Array.&lt;string&gt;}</code> The sequence of keys to type. An array must be provided. The server should flatten the array items to a single string to be typed.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
<dd><code>ElementNotVisible</code> - If the referenced element is not visible on the page (either is hidden by CSS, has 0-width, or has 0-height)</dd>
</dl>
</dd>
</dl>
</dd>
</dl></li></ul>


---


#### /session/:sessionId/keys

<dl>
<dd>
<h4>POST /session/:sessionId/keys</h4>
</dd>
<dd>
<dl>
<dd>Send a sequence of key strokes to the active element. This command is similar to the <a href='JsonWireProtocol#/session/:sessionId/element/:id/value.md'>send keys</a> command in every aspect except the implicit termination: The modifiers are <b>not</b> released at the end of the call. Rather, the state of the modifier keys is kept between calls, so mouse interactions can be performed while modifier keys are depressed.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>value</code> - <code>{Array.&lt;string&gt;}</code> The keys sequence to be sent. The sequence is defined in the<a href='JsonWireProtocol#/session/:sessionId/element/:id/value.md'>send keys</a> command.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/name

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/name</h4>
</dd>
<dd>
<dl>
<dd>Query for an element's tag name.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The element's tag name, as a lowercase string.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/clear

<dl>
<dd>
<h4>POST /session/:sessionId/element/:id/clear</h4>
</dd>
<dd>
<dl>
<dd>Clear a <code>TEXTAREA</code> or <code>text INPUT</code> element's value.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
<dd><code>ElementNotVisible</code> - If the referenced element is not visible on the page (either is hidden by CSS, has 0-width, or has 0-height)</dd>
<dd><code>InvalidElementState</code> - If the referenced element is disabled.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/selected

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/selected</h4>
</dd>
<dd>
<dl>
<dd>Determine if an <code>OPTION</code> element, or an <code>INPUT</code> element of type <code>checkbox</code> or <code>radiobutton</code> is currently selected.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{boolean}</code> Whether the element is selected.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/enabled

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/enabled</h4>
</dd>
<dd>
<dl>
<dd>Determine if an element is currently enabled.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{boolean}</code> Whether the element is enabled.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/attribute/:name

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/attribute/:name</h4>
</dd>
<dd>
<dl>
<dd>Get the value of an element's attribute.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string|null}</code> The value of the attribute, or null if it is not set on the element.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/equals/:other

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/equals/:other</h4>
</dd>
<dd>
<dl>
<dd>Test if two element IDs refer to the same DOM element.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
<dd><code>:other</code> - ID of the element to compare against.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{boolean}</code> Whether the two IDs refer to the same element.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If either the element refered to by <code>:id</code> or <code>:other</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/displayed

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/displayed</h4>
</dd>
<dd>
<dl>
<dd>Determine if an element is currently displayed.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{boolean}</code> Whether the element is displayed.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/location

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/location</h4>
</dd>
<dd>
<dl>
<dd>Determine an element's location on the page. The point <code>(0, 0)</code> refers to the upper-left corner of the page. The element's coordinates are returned as a JSON object with <code>x</code> and <code>y</code> properties.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{x:number, y:number}</code> The X and Y coordinates for the element on the page.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/location\_in\_view

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/location_in_view</h4>
</dd>
<dd>
<dl>
<dd>Determine an element's location on the screen once it has been scrolled into view.<br>
<br>
<b>Note:</b> This is considered an internal command and should <b>only</b> be used to determine an element's<br>
location for correctly generating native events.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{x:number, y:number}</code> The X and Y coordinates for the element.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/size

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/size</h4>
</dd>
<dd>
<dl>
<dd>Determine an element's size in pixels. The size will be returned as a JSON object  with <code>width</code> and <code>height</code> properties.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{width:number, height:number}</code> The width and height of the element, in pixels.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/element/:id/css/:propertyName

<dl>
<dd>
<h4>GET /session/:sessionId/element/:id/css/:propertyName</h4>
</dd>
<dd>
<dl>
<dd>Query the value of an element's computed CSS property. The CSS property to query should be specified using the CSS property name, <b>not</b> the JavaScript property name (e.g. <code>background-color</code> instead of <code>backgroundColor</code>).</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:id</code> - ID of the element to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The value of the specified CSS property.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
<dd><code>StaleElementReference</code> - If the element referenced by <code>:id</code> is no longer attached to the page's DOM.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/orientation

<dl>
<dd>
<h4>GET /session/:sessionId/orientation</h4>
</dd>
<dd>
<dl>
<dd>Get the current browser orientation. The server should return a valid orientation value as defined in <a href='http://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/ScreenOrientation.html'>ScreenOrientation</a>: <code>{LANDSCAPE|PORTRAIT}</code>.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The current browser orientation corresponding to a value defined in <a href='http://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/ScreenOrientation.html'>ScreenOrientation</a>: <code>{LANDSCAPE|PORTRAIT}</code>.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>POST /session/:sessionId/orientation</h4>
</dd>
<dd>
<dl>
<dd>Set the browser orientation. The orientation should be specified as defined in <a href='http://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/ScreenOrientation.html'>ScreenOrientation</a>: <code>{LANDSCAPE|PORTRAIT}</code>.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>orientation</code> - <code>{string}</code> The new browser orientation as defined in <a href='http://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/ScreenOrientation.html'>ScreenOrientation</a>: <code>{LANDSCAPE|PORTRAIT}</code>.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/alert\_text

<dl>
<dd>
<h4>GET /session/:sessionId/alert_text</h4>
</dd>
<dd>
<dl>
<dd>Gets the text of the currently displayed JavaScript <code>alert()</code>, <code>confirm()</code>, or <code>prompt()</code> dialog.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{string}</code> The text of the currently displayed alert.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoAlertPresent</code> - If there is no alert displayed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>POST /session/:sessionId/alert_text</h4>
</dd>
<dd>
<dl>
<dd>Sends keystrokes to a JavaScript <code>prompt()</code> dialog.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>text</code> - <code>{string}</code> Keystrokes to send to the <code>prompt()</code> dialog.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoAlertPresent</code> - If there is no alert displayed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/accept\_alert

<dl>
<dd>
<h4>POST /session/:sessionId/accept_alert</h4>
</dd>
<dd>
<dl>
<dd>Accepts the currently displayed alert dialog. Usually, this is equivalent to clicking on the 'OK' button in the dialog.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoAlertPresent</code> - If there is no alert displayed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/dismiss\_alert

<dl>
<dd>
<h4>POST /session/:sessionId/dismiss_alert</h4>
</dd>
<dd>
<dl>
<dd>Dismisses the currently displayed alert dialog. For <code>confirm()</code> and <code>prompt()</code> dialogs, this is equivalent to clicking the 'Cancel' button. For <code>alert()</code> dialogs, this is equivalent to clicking the 'OK' button.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoAlertPresent</code> - If there is no alert displayed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/moveto

<dl>
<dd>
<h4>POST /session/:sessionId/moveto</h4>
</dd>
<dd>
<dl>
<dd>Move the mouse by an offset of the specificed element. If no element is specified, the move is relative to the current mouse cursor. If an element is provided but no offset, the mouse will be moved to the center of the element. If the element is not visible, it will be scrolled into view.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>element</code> - <code>{string}</code> Opaque ID assigned to the element to move to, as described in the WebElement JSON Object. If not specified or is null, the offset is relative to current position of the mouse.</dd>
<dd><code>xoffset</code> - <code>{number}</code> X offset to move to, relative to the top-left corner of the element. If not specified, the mouse will move to the middle of the element.</dd>
<dd><code>yoffset</code> - <code>{number}</code> Y offset to move to, relative to the top-left corner of the element. If not specified, the mouse will move to the middle of the element.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/click

<dl>
<dd>
<h4>POST /session/:sessionId/click</h4>
</dd>
<dd>
<dl>
<dd>Click any mouse button (at the coordinates set by the last moveto command). Note that calling this command after calling buttondown and before calling button up (or any out-of-order interactions sequence) will yield undefined behaviour).</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>button</code> - <code>{number}</code> Which button, enum: <code>{LEFT = 0, MIDDLE = 1 , RIGHT = 2}</code>. Defaults to the left mouse button if not specified.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/buttondown

<dl>
<dd>
<h4>POST /session/:sessionId/buttondown</h4>
</dd>
<dd>
<dl>
<dd>Click and hold the left mouse button (at the coordinates set by the last moveto command). Note that the next mouse-related command that should follow is buttonup . Any other mouse command (such as click or another call to buttondown) will yield undefined behaviour.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>button</code> - <code>{number}</code> Which button, enum: <code>{LEFT = 0, MIDDLE = 1 , RIGHT = 2}</code>. Defaults to the left mouse button if not specified.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/buttonup

<dl>
<dd>
<h4>POST /session/:sessionId/buttonup</h4>
</dd>
<dd>
<dl>
<dd>Releases the mouse button previously held (where the mouse is currently at). Must be called once for every buttondown command issued. See the note in click and buttondown about implications of out-of-order commands.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>button</code> - <code>{number}</code> Which button, enum: <code>{LEFT = 0, MIDDLE = 1 , RIGHT = 2}</code>. Defaults to the left mouse button if not specified.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/doubleclick

<dl>
<dd>
<h4>POST /session/:sessionId/doubleclick</h4>
</dd>
<dd>
<dl>
<dd>Double-clicks at the current mouse coordinates (set by moveto).</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/touch/click

<dl>
<dd>
<h4>POST /session/:sessionId/touch/click</h4>
</dd>
<dd>
<dl>
<dd>Single tap on the touch enabled device.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>element</code> - <code>{string}</code> ID of the element to single tap on.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/touch/down

<dl>
<dd>
<h4>POST /session/:sessionId/touch/down</h4>
</dd>
<dd>
<dl>
<dd>Finger down on the screen.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>x</code> - <code>{number}</code> X coordinate on the screen.</dd>
<dd><code>y</code> - <code>{number}</code> Y coordinate on the screen.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/touch/up

<dl>
<dd>
<h4>POST /session/:sessionId/touch/up</h4>
</dd>
<dd>
<dl>
<dd>Finger up on the screen.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>x</code> - <code>{number}</code> X coordinate on the screen.</dd>
<dd><code>y</code> - <code>{number}</code> Y coordinate on the screen.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### session/:sessionId/touch/move

<dl>
<dd>
<h4>POST session/:sessionId/touch/move</h4>
</dd>
<dd>
<dl>
<dd>Finger move on the screen.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>x</code> - <code>{number}</code> X coordinate on the screen.</dd>
<dd><code>y</code> - <code>{number}</code> Y coordinate on the screen.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### session/:sessionId/touch/scroll

<dl>
<dd>
<h4>POST session/:sessionId/touch/scroll</h4>
</dd>
<dd>
<dl>
<dd>Scroll on the touch screen using finger based motion events. Use this command to start scrolling at a particular screen location.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>element</code> - <code>{string}</code> ID of the element where the scroll starts.</dd>
<dd><code>xoffset</code> - <code>{number}</code> The x offset in pixels to scroll by.</dd>
<dd><code>yoffset</code> - <code>{number}</code> The y offset in pixels to scroll by.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### session/:sessionId/touch/scroll

<dl>
<dd>
<h4>POST session/:sessionId/touch/scroll</h4>
</dd>
<dd>
<dl>
<dd>Scroll on the touch screen using finger based motion events. Use this command if you don't care where the scroll starts on the screen.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>xoffset</code> - <code>{number}</code> The x offset in pixels to scrollby.</dd>
<dd><code>yoffset</code> - <code>{number}</code> The y offset in pixels to scrollby.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### session/:sessionId/touch/doubleclick

<dl>
<dd>
<h4>POST session/:sessionId/touch/doubleclick</h4>
</dd>
<dd>
<dl>
<dd>Double tap on the touch screen using finger motion events.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>element</code> - <code>{string}</code> ID of the element to double tap on.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### session/:sessionId/touch/longclick

<dl>
<dd>
<h4>POST session/:sessionId/touch/longclick</h4>
</dd>
<dd>
<dl>
<dd>Long press on the touch screen using finger motion events.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>element</code> - <code>{string}</code> ID of the element to long press on.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### session/:sessionId/touch/flick

<dl>
<dd>
<h4>POST session/:sessionId/touch/flick</h4>
</dd>
<dd>
<dl>
<dd>Flick on the touch screen using finger motion events. This flickcommand starts at a particulat screen location.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>element</code> - <code>{string}</code> ID of the element where the flick starts.</dd>
<dd><code>xoffset</code> - <code>{number}</code> The x offset in pixels to flick by.</dd>
<dd><code>yoffset</code> - <code>{number}</code> The y offset in pixels to flick by.</dd>
<dd><code>speed</code> - <code>{number}</code> The speed in pixels per seconds.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### session/:sessionId/touch/flick

<dl>
<dd>
<h4>POST session/:sessionId/touch/flick</h4>
</dd>
<dd>
<dl>
<dd>Flick on the touch screen using finger motion events. Use this flick command if you don't care where the flick starts on the screen.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>xspeed</code> - <code>{number}</code> The x speed in pixels per second.</dd>
<dd><code>yspeed</code> - <code>{number}</code> The y speed in pixels per second.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/location

<dl>
<dd>
<h4>GET /session/:sessionId/location</h4>
</dd>
<dd>
<dl>
<dd>Get the current geo location.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{latitude: number, longitude: number, altitude: number}</code> The current geo location.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>POST /session/:sessionId/location</h4>
</dd>
<dd>
<dl>
<dd>Set the current geo location.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>location</code> - <code>{latitude: number, longitude: number, altitude: number}</code> The new location.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/local\_storage

<dl>
<dd>
<h4>GET /session/:sessionId/local_storage</h4>
</dd>
<dd>
<dl>
<dd>Get all keys of the storage.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;string&gt;}</code> The list of keys.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>POST /session/:sessionId/local_storage</h4>
</dd>
<dd>
<dl>
<dd>Set the storage item for the given key.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>key</code> - <code>{string}</code> The key to set.</dd>
<dd><code>value</code> - <code>{string}</code> The value to set.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>DELETE /session/:sessionId/local_storage</h4>
</dd>
<dd>
<dl>
<dd>Clear the storage.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/local\_storage/key/:key

<dl>
<dd>
<h4>GET /session/:sessionId/local_storage/key/:key</h4>
</dd>
<dd>
<dl>
<dd>Get the storage item for the given key.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:key</code> - The key to get.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>DELETE /session/:sessionId/local_storage/key/:key</h4>
</dd>
<dd>
<dl>
<dd>Remove the storage item for the given key.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:key</code> - The key to remove.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/local\_storage/size

<dl>
<dd>
<h4>GET /session/:sessionId/local_storage/size</h4>
</dd>
<dd>
<dl>
<dd>Get the number of items in the storage.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{number}</code> The number of items in the storage.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/session\_storage

<dl>
<dd>
<h4>GET /session/:sessionId/session_storage</h4>
</dd>
<dd>
<dl>
<dd>Get all keys of the storage.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;string&gt;}</code> The list of keys.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>POST /session/:sessionId/session_storage</h4>
</dd>
<dd>
<dl>
<dd>Set the storage item for the given key.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>key</code> - <code>{string}</code> The key to set.</dd>
<dd><code>value</code> - <code>{string}</code> The value to set.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>DELETE /session/:sessionId/session_storage</h4>
</dd>
<dd>
<dl>
<dd>Clear the storage.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/session\_storage/key/:key

<dl>
<dd>
<h4>GET /session/:sessionId/session_storage/key/:key</h4>
</dd>
<dd>
<dl>
<dd>Get the storage item for the given key.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:key</code> - The key to get.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>

<dl>
<dd>
<h4>DELETE /session/:sessionId/session_storage/key/:key</h4>
</dd>
<dd>
<dl>
<dd>Remove the storage item for the given key.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
<dd><code>:key</code> - The key to remove.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/session\_storage/size

<dl>
<dd>
<h4>GET /session/:sessionId/session_storage/size</h4>
</dd>
<dd>
<dl>
<dd>Get the number of items in the storage.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{number}</code> The number of items in the storage.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Potential Errors:</b></dt>
<dd><code>NoSuchWindow</code> - If the currently selected window has been closed.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/log

<dl>
<dd>
<h4>POST /session/:sessionId/log</h4>
</dd>
<dd>
<dl>
<dd>Get the log for a given log type. Log buffer is reset after each request.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>JSON Parameters:</b></dt>
<dd><code>type</code> - <code>{string}</code> The <a href='#Log_Type.md'>log type</a>. This must be provided.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;object&gt;}</code> The list of <a href='#Log_Entry_JSON_Object.md'>log entries</a>.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/log/types

<dl>
<dd>
<h4>GET /session/:sessionId/log/types</h4>
</dd>
<dd>
<dl>
<dd>Get available log types.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{Array.&lt;string&gt;}</code> The list of available <a href='#Log_Type.md'>log types</a>.</dd>
</dl>
</dd>
</dl>
</dd>
</dl>


---


#### /session/:sessionId/application\_cache/status

<dl>
<dd>
<h4>GET /session/:sessionId/application_cache/status</h4>
</dd>
<dd>
<dl>
<dd>Get the status of the html5 application cache.</dd>
<dd>
<dl>
<dt><b>URL Parameters:</b></dt>
<dd><code>:sessionId</code> - ID of the session to route the command to.</dd>
</dl>
</dd>
<dd>
<dl>
<dt><b>Returns:</b></dt>
<dd><code>{number}</code> Status code for application cache: {UNCACHED = 0, IDLE = 1, CHECKING = 2, DOWNLOADING = 3, UPDATE_READY = 4, OBSOLETE = 5}</dd>
</dl>
</dd>
</dl>
</dd>
</dl>
