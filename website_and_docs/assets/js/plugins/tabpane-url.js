// Selects the language tab named by the `?tab=KEY` query parameter, so links
// such as /documentation/?tab=python land readers in their language. Interim
// until Docsy ships this natively (docsy/docsy#1202); then delete this file and
// its hugo.toml entry.
window.addEventListener('DOMContentLoaded', () => {
  const key = new URLSearchParams(location.search)
    .get('tab')
    ?.trim()
    .toLowerCase();
  if (!key) return;

  const tabs = document.querySelectorAll(
    `[data-td-tp-persist="${CSS.escape(key)}"]`
  );
  if (!tabs.length) return;

  // The click goes through Docsy's tabpane-persist handler, which records the
  // choice and switches every tabpane with this key; show() covers the
  // no-localStorage case, where that handler is never registered.
  tabs[0].click();
  tabs.forEach((tab) => bootstrap.Tab.getOrCreateInstance(tab).show());
});
