#!/bin/bash
# Exit on error
set -e

SELENIUM_GITHUB_API_PULLS_URL=https://api.github.com/repos/SeleniumHQ/seleniumhq.github.io/pulls
SELENIUM_ACCEPT_HEADER="Accept: application/vnd.github+json"
SELENIUM_AUTH_HEADER="Authorization: Bearer ${SELENIUM_CI_TOKEN}"
SELENIUM_EXAMPLES_BRANCH=trunk
SELENIUM_EXAMPLES_REPO=seleniumhq.github.io
SELENIUM_EXAMPLES_ORG=SeleniumHQ

if [[ "${GITHUB_ACTIONS}" = "true" ]]; then
  SELENIUM_EXAMPLES_BRANCH=${GITHUB_HEAD_REF}
fi

USE_BASE_URL_SITE=""
if [[ "${NETLIFY}" = "true" ]]; then
  echo -e "\033[0;32mNetlify detected, deployment happening at ${DEPLOY_PRIME_URL}...\033[0m"
  USE_BASE_URL_SITE="--baseURL ${DEPLOY_PRIME_URL}"
  if [[ "${PULL_REQUEST}" = "true" ]]; then
    echo -e "\033[0;32mDeploying a preview for a pull request in Netlify...\033[0m"
    # Getting repo information from the GitHub API
    # Useful to get the code examples from the right org and repo
    REPO_INFO=$(curl -s -H ${SELENIUM_ACCEPT_HEADER} -H ${SELENIUM_AUTH_HEADER} ${SELENIUM_GITHUB_API_PULLS_URL}/${REVIEW_ID})
    SELENIUM_EXAMPLES_BRANCH=${HEAD}
    SELENIUM_EXAMPLES_REPO=$(echo $REPO_INFO | jq -r .head.repo.name)
    SELENIUM_EXAMPLES_ORG=$(echo $REPO_INFO | jq -r .head.repo.owner.login)
  fi
fi

echo -e "\033[0;32mDeleting Hugo previously generated directories...\033[0m"
rm -rf website_and_docs/public

echo -e "\033[0;32mGit init for Docsy...\033[0m"
git submodule update -f --init --recursive

echo -e "\033[0;32mSwitching to Docsy theme directory...\033[0m"
cd website_and_docs && npm install

echo -e "\033[0;32mGenerating Hugo site for website...\033[0m"
echo -e "\033[0;32mUsing SELENIUM_EXAMPLES_REPO=${SELENIUM_EXAMPLES_REPO} SELENIUM_EXAMPLES_ORG=${SELENIUM_EXAMPLES_ORG} SELENIUM_EXAMPLES_BRANCH=${SELENIUM_EXAMPLES_BRANCH} hugo --minify ${USE_BASE_URL_SITE}...\033[0m"
SELENIUM_EXAMPLES_REPO=${SELENIUM_EXAMPLES_REPO} SELENIUM_EXAMPLES_ORG=${SELENIUM_EXAMPLES_ORG} SELENIUM_EXAMPLES_BRANCH=${SELENIUM_EXAMPLES_BRANCH} hugo --minify ${USE_BASE_URL_SITE}

echo -e "\033[0;32mDone building site!\033[0m"
