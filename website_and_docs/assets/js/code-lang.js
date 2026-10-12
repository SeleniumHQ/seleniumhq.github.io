// Site-wide code language selector (layouts/partials/code-lang-selector.html).
//
// Shares its storage with Docsy's tabpane-persist plugin: that plugin keeps
// one localStorage entry per tab key, `td-tp-persist:<key>`, whose value is
// an ever-increasing selection counter (`td-tp-persist-count`), and on load
// activates the tabs whose key was selected most recently. Writing the same
// entries here means the dropdown and the tab headers stay in sync, on this
// page and on every other page.
//
// The <html data-code-lang> attribute is set as early as possible by the
// inline snippet in layouts/partials/hooks/head-end.html, so the stylesheet
// (assets/scss/_tabs.scss) can show the chosen pane before this script runs.
(function () {
  'use strict';

  const KEY_BASE = 'td-tp-persist';
  const COUNT_KEY = KEY_BASE + '-count';
  const LANGS = ['java', 'python', 'csharp', 'ruby', 'javascript', 'kotlin'];
  const LABELS = {
    java: 'Java',
    python: 'Python',
    csharp: 'C#',
    ruby: 'Ruby',
    javascript: 'JavaScript',
    kotlin: 'Kotlin',
  };

  const hasStorage = () => {
    try {
      return typeof localStorage !== 'undefined';
    } catch (e) {
      return false;
    }
  };

  function persist(key) {
    if (!hasStorage()) return;
    try {
      const count = (parseInt(localStorage.getItem(COUNT_KEY)) || 0) + 1;
      localStorage.setItem(COUNT_KEY, String(count));
      localStorage.setItem(KEY_BASE + ':' + key, String(count));
    } catch (e) {
      console.error('code-lang: unable to write localStorage: ', e);
    }
  }

  function setCurrent(key) {
    if (!LANGS.includes(key)) return;
    document.documentElement.setAttribute('data-code-lang', key);
    document.querySelectorAll('.td-code-lang__label').forEach((el) => {
      el.textContent = LABELS[key];
    });
    document.querySelectorAll('.td-code-lang [data-code-lang]').forEach((item) => {
      item.classList.toggle('active', item.getAttribute('data-code-lang') === key);
    });
  }

  function activateTabs(key) {
    if (typeof bootstrap === 'undefined' || !bootstrap.Tab) return;
    document
      .querySelectorAll('[data-td-tp-persist="' + key + '"]')
      .forEach((tab) => bootstrap.Tab.getOrCreateInstance(tab).show());
  }

  function select(key) {
    if (!LANGS.includes(key)) return;
    persist(key);
    setCurrent(key);
    activateTabs(key);
  }

  window.addEventListener('DOMContentLoaded', () => {
    const current = document.documentElement.getAttribute('data-code-lang');
    if (current) {
      setCurrent(current);
      activateTabs(current);
    }

    document.querySelectorAll('.td-code-lang [data-code-lang]').forEach((item) => {
      item.addEventListener('click', (event) => {
        event.preventDefault();
        select(item.getAttribute('data-code-lang'));
      });
    });

    // A click on a tab header changes the language too; Docsy's plugin
    // persists it and switches the other tabpanes, we follow with the
    // dropdown label and the html attribute.
    document.addEventListener('shown.bs.tab', (event) => {
      const key = event.target && event.target.getAttribute('data-td-tp-persist');
      if (key && LANGS.includes(key)) setCurrent(key);
    });
  });
})();
