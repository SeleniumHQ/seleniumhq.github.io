#!/bin/bash
# Print commands
set -x
# Exit on error
set -e

SELENIUM_EXAMPLES_BRANCH=$(git branch | sed -n -e 's/^\* \(.*\)/\1/p')
echo -e "\033[0;32mInitial SELENIUM_EXAMPLES_BRANCH ${SELENIUM_EXAMPLES_BRANCH}...\033[0m"

if [[ "${CI}" = "true" ]]; then
  SELENIUM_EXAMPLES_BRANCH=${GITHUB_HEAD_REF}
  echo -e "\033[0;32mRunning inside a CI, SELENIUM_EXAMPLES_BRANCH ${SELENIUM_EXAMPLES_BRANCH}...\033[0m"
fi

if [[ -z "${DEPLOY_PRIME_URL}" ]]; then
  USE_BASE_URL_SITE=""
else
  echo -e "\033[0;32mNetlify DEPLOY_PRIME_URL detected, this seems to be a PR, deployment happening at ${DEPLOY_PRIME_URL}...\033[0m"
  USE_BASE_URL_SITE="--baseURL ${DEPLOY_PRIME_URL}"
  git ls-remote --heads origin ${HEAD}
  if git ls-remote --heads origin ${HEAD} > /dev/null; then    
    echo -e "\033[0;32mPull request comes from a fork. Using trunk branch to render code examples pulled from GitHub.\033[0m"
    echo -e "\033[0;32mCreate a new branch in the upstream repo, merge this PR into that branch, and create a new PR from a protected branch.\033[0m"
    SELENIUM_EXAMPLES_BRANCH=trunk
    echo -e "\033[0;Using trunk... SELENIUM_EXAMPLES_BRANCH ${SELENIUM_EXAMPLES_BRANCH}...\033[0m"
  else 
    SELENIUM_EXAMPLES_BRANCH=${HEAD}
    echo -e "\033[0;32mTrusted branch found, SELENIUM_EXAMPLES_BRANCH ${SELENIUM_EXAMPLES_BRANCH}...\033[0m"
  fi
fi

echo -e "\033[0;32mDeleting Hugo previously generated directories...\033[0m"
rm -rf website_and_docs/public

echo -e "\033[0;32mGit init for Docsy...\033[0m"
git submodule update -f --init --recursive

echo -e "\033[0;32mSwitching to Docsy theme directory...\033[0m"
cd website_and_docs && npm install

echo -e "\033[0;32mGenerating Hugo site for website...\033[0m"
echo -e "\033[0;32mSELENIUM_EXAMPLES_BRANCH=${SELENIUM_EXAMPLES_BRANCH} hugo --minify ${USE_BASE_URL_SITE}...\033[0m"
SELENIUM_EXAMPLES_BRANCH=${SELENIUM_EXAMPLES_BRANCH} hugo --minify ${USE_BASE_URL_SITE}

echo -e "\033[0;32mDone building site!\033[0m"
