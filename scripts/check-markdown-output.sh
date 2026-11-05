#!/bin/bash
# Checks the Markdown version of each documentation page (index.md beside
# index.html):
#
# - Each documentation page has one. Print pages and alias redirects are skipped.
# - None has HTML that a shortcode's HTML template produced. Each shortcode used
#   in the documentation needs a <shortcode>.markdown.md template in
#   website_and_docs/layouts/shortcodes. Without one, Hugo uses the HTML template
#   for the Markdown output too, and the page's index.md gets HTML instead of
#   Markdown.
#
# Usage: scripts/check-markdown-output.sh [public dir]
# The public dir defaults to website_and_docs/public.
set -e

PUBLIC_DIR="${1:-website_and_docs/public}"
FAILED=0

if [[ ! -d "${PUBLIC_DIR}" ]]; then
  echo "No built site found at ${PUBLIC_DIR}. Build the site first." >&2
  exit 1
fi

MISSING=$(find "${PUBLIC_DIR}" -path '*/documentation/*' -not -path '*/_print/*' -name index.html \
  | while read -r page; do
      grep -qE 'http-equiv="?refresh' "${page}" && continue
      [[ -f "${page%.html}.md" ]] || echo "${page}"
    done)

if [[ -n "${MISSING}" ]]; then
  echo -e "\033[0;31mThese documentation pages have no Markdown version (index.md):\033[0m" >&2
  echo "${MISSING}" >&2
  echo "Check the [[cascade]] entries for the markdown output in website_and_docs/hugo.toml." >&2
  FAILED=1
fi

# Markers that only the HTML templates of the shortcodes write: Docsy's tabpane,
# card, alert and pageinfo, Hugo's highlight and figure, and this site's badges,
# alerts and gh-contributors.
MARKERS='class="(nav nav-tabs|tab-content|tab-pane|td-card|card-|alert |alert-|pageinfo|highlight|selenium-badge-|ghContributors)|<figure|<div class="highlight"'

WITH_HTML=$(find "${PUBLIC_DIR}" -path '*/documentation/*' -name index.md -print0 \
  | xargs -0 grep -lE "${MARKERS}" || true)

if [[ -n "${WITH_HTML}" ]]; then
  echo -e "\033[0;31mThese Markdown pages contain HTML from a shortcode's HTML template:\033[0m" >&2
  echo "${WITH_HTML}" >&2
  echo "Add a <shortcode>.markdown.md template in website_and_docs/layouts/shortcodes for each shortcode they use." >&2
  FAILED=1
fi

if [[ "${FAILED}" -ne 0 ]]; then
  exit 1
fi

echo -e "\033[0;32mMarkdown versions of documentation pages look clean.\033[0m"
