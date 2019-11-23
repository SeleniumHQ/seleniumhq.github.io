"use strict";

function $(loc) {
  loc = String(loc);
  var els = document.querySelectorAll(loc);
  return els.length > 0 ? els : els[0];
}

var languages = [
  { pretty: "Java",		safe: "java", 		comment: "//"},
  { pretty: "Python", 	safe: "python", 	comment: "#"},
  { pretty: "C#", 		safe: "cs", 		comment: "//"},
  { pretty: "Ruby",		safe: "ruby", 		comment: "#"},
  { pretty: "JavaScript", safe: "javascript",	comment: "//"}
];

function chosenLanguage() {
  return localStorage.language ? localStorage.language : 'Java';
}

function showOnly(language, target) {
  localStorage.language = language;
  for (var i = 0; i < languages.length; i++) {
    var codeSamples = $('pre>code:not(.shell).' + languages[i].safe);
    for (var j = 0; j < codeSamples.length; j++) {
      if(language === languages[i].pretty || codeSamples[j].hasAttribute("always-show")) {
        codeSamples[j].style.display = 'block';
      } else {
        codeSamples[j].style.display = 'none';
      }
    }
  }
  var allLanguages = $('.languageSelect>span');
  for (i = 0; i < allLanguages.length; i++) {
    if(allLanguages[i].innerText === language) {
      allLanguages[i].classList.add('chosen');
    } else {
      allLanguages[i].classList.remove('chosen');
    }
  }
  if(target) {
    target.scrollIntoView();
  }
}

function collapseSnippets() {
  addLanguageSelect();
  showOnly(chosenLanguage(), null);
}

function containsLanguageBindings(pre) {
  for (var i = 0; i < languages.length; i++) {
    if(containsLanguage(pre, languages[i].safe)) {
      return true;
    }
  }
  return false;
}

function containsLanguage(pre, language) {
  return pre.querySelector('code:not([always-show]).' + language);
}

function addLanguageSelect() {
  var pre = $("pre");
  for (var i = 0; i < pre.length; i++) {
    var languagesDiv = document.createElement('div');
    languagesDiv.setAttribute('class', 'languageSelect');
    pre[i].insertBefore(languagesDiv, pre[i].childNodes[0]);
    if (containsLanguageBindings(pre[i])) {
      for (var j = 0; j < languages.length; j++) {
        var span = document.createElement('span');
        span.innerText = languages[j].pretty;
        span.classList.add(languages[j].safe);
        span.onclick = function() {
          showOnly(this.innerText, this.parentNode.parentNode);
        };
        if (!containsLanguage(pre[i], languages[j].safe)) {
          span.classList.add('missing');
          var code = document.createElement('code');
          code.classList.add(languages[j].safe);
          code.innerText= languages[j].comment
            + 'We don\'t have a ' + languages[j].pretty
            + ' code sample yet -  Help us out and raise a PR';
          pre[i].appendChild(code);
        }
        languagesDiv.appendChild(span);
      }
    }
  }
}

window.addEventListener("load", addStructure);
window.addEventListener("load", addAnchors);
window.addEventListener("load", addToc);
window.addEventListener("load", paginate);
window.addEventListener("load", insertHeader);
window.addEventListener("load", insertFooter);
window.addEventListener("load", populateHeaderYs);
window.addEventListener("load", populateTocEls);
window.addEventListener("load", highlightCode);
window.addEventListener("load", collapseSnippets);
window.addEventListener("scroll", trackHeaders);
window.addEventListener("scroll", moveToc);

function addStructure() {}

/**
 * Function that parses through the current page, and adds
 * anchors on the h1-h6 tags for TOC navigation
 */
function addAnchors() {
  var whitelist = "abcdefghijklmnopqrstuvwxyz0123456789 ";
  function sanitise(s) {
    if (!s)
      return;
    s = s.trim().toLowerCase();
    s = Array.prototype.filter.call(s, function(c) { return whitelist.indexOf(c) >= 0 }).join("");
    return s.replace(/\s/g, "_")
  }
  var hs = $("h1, h2, h3, h4, h5, h6");
  for (var i=0; i< hs.length; i++) {
    hs[i].id = sanitise(hs[i].textContent);
  }
}

/**
 * Add the table of contents to the DOM.
 */
function addToc() {
  var toc = document.createElement("nav");
  toc.id = "toc";
  toc.innerHTML = "<h1><a href=index.html>Table of Contents</a></h1>";
  var hs = $("h1, h2, h3, h4, h5, h6");
  for (var i=0; i< hs.length; i++) {
    var level = hs[i].tagName.substring(1);
    if (level == 1)
      continue;
    toc.innerHTML += "<a href=#" + hs[i].id + " class=level" + level + ">" + hs[i].textContent + "</a>"
  }
  document.body.insertBefore(toc, document.body.firstChild);
}

/**
 * Retrieve the current page, without an extension
 * @returns string "index" | "intro" | etc.
 */
function getCurrentPage() {
  var paths = location.href.split('/');
  return paths[paths.length-1].split('.')[0];
}

/**
 * Adds a pagination nav to the page
 */
function paginate() {
  var prev = document.querySelector("link[rel=prev]");
  var next = document.querySelector("link[rel=next]");
  var nav = document.createElement("nav");
  nav.id = "pagination";
  if (prev)
    nav.innerHTML = "<a class=prev href=" + prev.href + ">" + prev.title + "</a>";
  if (next)
    nav.innerHTML += "<a class=next href=" + next.href + ">" + next.title + "</a>";
  if (prev || next)
    document.body.appendChild(nav);
}

/**
 * Listener function that will put the header into the DOM as the first child in <body>
 */
function insertHeader() {
  var header = document.createElement("header");
  header.innerHTML = "<h1>Selenium Documentation</h1>";

  if (getCurrentPage() != 'index' || window.location.pathname === '/docs' || window.location.pathname === '/docs/')
    header.innerHTML += "<a id='home_link' href='/documentation'>back to index</a>";

  document.body.insertBefore(header, document.body.firstChild);
}

/**
 * Listener function that will put the footer into the DOM as the last child in <body>
 */
// TODO(ato): Warning, this is unsafe.
function insertFooter() {
  var footer = document.createElement("footer");
  var path = window.location.pathname;
  if (path === "/docs" || path === "/docs/")
    path = "/docs/index.html";
  var file = path.substring(path.lastIndexOf("/") + 1);

  footer.innerHTML = "<div class=links>" +
    " </div>" +
    "<div class=meta>" +
    " <p>&copy; 2013-" + new Date().getFullYear() + ", <a href=attr.html>Software Freedom Conservancy</a>." +
    " <p>Last updated " + document.lastModified +
    " <p>" +
    "  <ul>" +
    "   <li><a href=//github.com/seleniumhq/docs/issues>Report a bug</a>" +
    "   <li><a href=//github.com/seleniumhq/docs/edit/gh-pages/" + file + ">Edit</a>" +
    "  </ul>" +
    "</div>";

  document.body.appendChild(footer);
}

var hs = [];
var headerYs = {};
var curHeader = null;
var tocEls = {};

/**
 * Function that stores the `y` coords of each html header
 */
function populateHeaderYs() {
  var hdrs = $("h1[id], h2[id], h3[id], h4[id], h5[id], h6[id]");
  for (var i=0; i< hdrs.length; i++) {
    hs.push(hdrs[i]);
    headerYs[hdrs[i].offsetTop] = hdrs[i].id;
  };
}

/**
 * Function that populates the elements within the Table of Contents floaty
 */
function populateTocEls() {
  var els = $("nav#toc > a");
  for (var i=0; i< els.length; i++) {
    var anchor = els[i].href.substring(els[i].href.indexOf("#"));
    tocEls[anchor] = els[i];
  }
}

/**
 * Function that highlights pre code blocks using https://highlightjs.org/usage/
 * It dynamically loads required css and js without changing all html files.
 */
function highlightCode() {
  var link = document.createElement("link");
  link.rel = "stylesheet";
  link.type = "text/css";
  link.href = "github.css";
  link.onload = function() {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "highlight.pack.js";
    script.onload = function() {
      var blocks = $("pre code");
      for (var i = 0; i < blocks.length; i++) {
        hljs.highlightBlock(blocks[i]);
      }
    }
    document.head.appendChild(script);
  }
  document.head.appendChild(link);
}

/**
 * Listener function that observes the html headers, and will determine whether they have disappeared
 * @param ev the scroll event
 */
function trackHeaders(ev) {
  var pageY = ev.pageY;
  var cur = hs[0].id;
  var gone = Object.keys(headerYs).filter(function(y, h) { return y <= pageY });
  if (gone.length > 0) {
    var curY = gone[gone.length - 1];
    cur = headerYs[curY];
  }
  updateToc(cur);
}

/**
 * Update the table of contents with where the user is on the page
 * @param id the id of the header element
 */
function updateToc(id) {
  if (curHeader == id) {
    return;
  }
  Object.keys(tocEls).map(function(id) { tocEls[id].classList.remove("current") });
  var sel = "#" + id;
  if (!(sel in tocEls))
    return;
  curHeader = tocEls[sel];
  curHeader.classList.add("current");
}

/**
 * Listener event that moves the table of contents with the user's viewport
 * @param ev the scroll event
 */
function moveToc(ev) {
  var toc = $("nav#toc")[0];
  var firstEl = $("nav + h1")[0];
  var firstElSt = firstEl.getBoundingClientRect();
  var offset = document.body.classList.contains("front") ? 300 : 200;

  var firstElRealTop = firstElSt.bottom - (firstElSt.height - offset);
  if (firstElRealTop - 50 < 0) {
    toc.style.top = "50px";
  } else {
    toc.style.top = firstElSt.y + offset + "px";
  }
}
