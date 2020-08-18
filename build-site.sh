#!/bin/bash
set -x

if [[ -z "${DEPLOY_PRIME_URL}" ]]; then
  USE_BASE_URL_SITE=""
  USE_BASE_URL_DOCS=""
else
  echo -e "\033[0;32mNetlify DEPLOY_PRIME_URL detected, this seems to be a PR, deployment happening at ${DEPLOY_PRIME_URL}...\033[0m"
  USE_BASE_URL_SITE="--baseURL ${DEPLOY_PRIME_URL}"
  USE_BASE_URL_DOCS="--baseURL ${DEPLOY_PRIME_URL}/documentation"
fi

echo -e "\033[0;32mDeleting Hugo previously generated directories for docs and main site...\033[0m"
rm -rf site_source_files/public && rm -rf docs_source_files/public

echo -e "\033[0;32mGenerating Hugo site for docs...\033[0m"
cd docs_source_files && hugo --minify ${USE_BASE_URL_DOCS} && cd ..

echo -e "\033[0;32mGenerating Hugo site for main website...\033[0m"
cd site_source_files && hugo --minify ${USE_BASE_URL_SITE} && cd ..

echo -e "\033[0;32mMerging both sites into a single one...\033[0m"
mv docs_source_files/public/* site_source_files/public/documentation

echo -e "\033[0;32mDone building site!\033[0m"
