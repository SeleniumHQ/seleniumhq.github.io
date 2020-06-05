#!/bin/bash

if git --no-pager log -1 --oneline | grep -i "\\[deploy site\\]"; then
  echo -e "\033[0;32mDeploying Selenium site to GitHub Pages...\033[0m"

  if [ -n "$SELENIUM_CI_TOKEN" ]
  then
      echo -e "\033[0;32mAdding Selenium CI Bot credentials...\033[0m"
      touch ~/.git-credentials
      chmod 0600 ~/.git-credentials
      echo $SELENIUM_CI_TOKEN > ~/.git-credentials
      git config credential.helper store
      git config user.email "selenium-ci@users.noreply.github.com"
      git config user.name "Selenium CI Bot"
  fi

  git remote add github "https://selenium-ci:${SELENIUM_CI_TOKEN}@github.com/SeleniumHQ/seleniumhq.github.io.git"
  git fetch github
  git checkout -t github/master -b github/master
  git pull
  chmod +x build-site.sh && ./build-site.sh
  git add .
  git commit -m "Deploying on $(date), commit ${GITHUB_SHA}, [skip ci]" || true
  git push github HEAD:master
else
  echo -e "\033[0;32mCommit to master did not trigger deployment, add [deploy site] to the commit message if you intend to deploy...\033[0m"
fi
