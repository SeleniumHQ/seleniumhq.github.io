#!/bin/bash
# Source this from website_and_docs (after `npm install`/`npm ci`) before
# invoking hugo, so its Dart Sass transpiler can find a working "sass"
# binary on $PATH.
#
# Two gaps otherwise block it:
# - npm only installs binaries into node_modules/.bin, which isn't on
#   $PATH by default outside of npm-run scripts.
# - The sass-embedded package (Docsy's Dart Sass compiler) and its own
#   optional "sass" fallback dependency both ship a bin named "sass".
#   npm's install order lets the fallback claim node_modules/.bin/sass,
#   and that fallback refuses embedded-protocol mode ("sass --embedded
#   is unavailable in pure JS mode"), which hugo needs. Point PATH at
#   sass-embedded's own script directly, ahead of node_modules/.bin.
set -e

rm -rf .hugo-sass-bin
mkdir .hugo-sass-bin
ln -s "$(pwd)/node_modules/sass-embedded/dist/bin/sass.js" .hugo-sass-bin/sass
chmod +x node_modules/sass-embedded/dist/bin/sass.js
export PATH="$(pwd)/.hugo-sass-bin:$(pwd)/node_modules/.bin:${PATH}"
