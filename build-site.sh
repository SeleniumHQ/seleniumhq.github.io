#!/bin/bash
# Print commands
set -x
# Exit on error
set -e

SELENIUM_EXAMPLES_BRANCH=$(git branch | sed -n -e 's/^\* \(.*\)/\1/p')

if [[ "${CI}" = "true" ]]; then
  SELENIUM_EXAMPLES_BRANCH=${GITHUB_HEAD_REF}
fi

if [[ -z "${DEPLOY_PRIME_URL}" ]]; then
  USE_BASE_URL_SITE=""
else
  echo -e "\033[0;32mNetlify DEPLOY_PRIME_URL detected, this seems to be a PR, deployment happening at ${DEPLOY_PRIME_URL}...\033[0m"
  USE_BASE_URL_SITE="--baseURL ${DEPLOY_PRIME_URL}"
  if git rev-parse --quiet --verify ${HEAD} > /dev/null; then    
    SELENIUM_EXAMPLES_BRANCH=${HEAD}
  else 
    echo -e "\033[0;32mPull request comes from a fork. Using trunk branch to render code examples pulled from GitHub.\033[0m"
    echo -e "\033[0;32mCreate a new branch in the upstream repo, merge this PR into that branch, and create a new PR from a protected branch.\033[0m"
    SELENIUM_EXAMPLES_BRANCH=trunk
  fi
fi

echo -e "\033[0;32mDeleting Hugo previously generated directories...\033[0m"
rm -rf website_and_docs/public

echo -e "\033[0;32mGit init for Docsy...\033[0m"
git submodule update -f --init --recursive

echo -e "\033[0;32mSwitching to Docsy theme directory...\033[0m"
cd website_and_docs && npm install

echo -e "\033[0;32mGenerating Hugo site for website...\033[0m"
SELENIUM_EXAMPLES_BRANCH=${SELENIUM_EXAMPLES_BRANCH} hugo --minify ${USE_BASE_URL_SITE}

echo -e "\033[0;32mDone building site!\033[0m"
