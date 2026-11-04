#!/bin/bash
# Checks the Markdown version of each documentation page (index.md beside
# index.html) for HTML that a shortcode's HTML template produced.
#
# Each shortcode used in the documentation needs a <shortcode>.markdown.md
# template in website_and_docs/layouts/shortcodes. Without one, Hugo uses the
# HTML template for the Markdown output too, and the page's index.md gets HTML
# instead of Markdown. This script finds those pages.
#
# Usage: scripts/check-markdown-output.sh [public dir]
# The public dir defaults to website_and_docs/public.
set -e

PUBLIC_DIR="${1:-website_and_docs/public}"

if [[ ! -d "${PUBLIC_DIR}" ]]; then
  echo "No built site found at ${PUBLIC_DIR}. Build the site first." >&2
  exit 1
fi

# Markers that only the HTML templates of the shortcodes write: Docsy's tabpane,
# card, alert and pageinfo, Hugo's highlight and figure, and this site's badges,
# alerts and gh-contributors.
MARKERS='class="(nav nav-tabs|tab-content|tab-pane|td-card|card-|alert |alert-|pageinfo|highlight|selenium-badge-|ghContributors)|<figure|<div class="highlight"'

FILES=$(find "${PUBLIC_DIR}" -path '*/documentation/*' -name index.md -print0 \
  | xargs -0 grep -lE "${MARKERS}" || true)

if [[ -n "${FILES}" ]]; then
  echo -e "\033[0;31mThese Markdown pages contain HTML from a shortcode's HTML template:\033[0m" >&2
  echo "${FILES}" >&2
  echo "Add a <shortcode>.markdown.md template in website_and_docs/layouts/shortcodes for each shortcode they use." >&2
  exit 1
fi

echo -e "\033[0;32mMarkdown versions of documentation pages look clean.\033[0m"
