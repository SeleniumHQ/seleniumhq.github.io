#!/bin/bash

if git --no-pager log -1 --oneline | grep -i "\\[deploy site\\]"; then
  echo -e "\033[0;32mDeploying Selenium site to GitHub...\033[0m"

  if [ -n "$GITHUB_AUTH_SECRET" ]
  then
      echo -e "\033[0;32mAdding Selenium CI Bot credentials...\033[0m"
      touch ~/.git-credentials
      chmod 0600 ~/.git-credentials
      echo $GITHUB_AUTH_SECRET > ~/.git-credentials
      git config credential.helper store
      git config user.email "selenium-ci@users.noreply.github.com"
      git config user.name "Selenium CI Bot"
  fi

  git remote add github "https://selenium-ci:${GITHUB_AUTH_SECRET}@github.com/SeleniumHQ/seleniumhq.github.io.git"
  git fetch github
  # git stash save || true
  git checkout -t github/master -b github/master
  git pull
  chmod +x "$TRAVIS_BUILD_DIR"/build-site.sh && "$TRAVIS_BUILD_DIR"/build-site.sh
  git add .
  git commit -m "Deploying on $(date), commit ${TRAVIS_COMMIT}, [skip ci]" || true
  git push github HEAD:master
else
  echo -e "\033[0;32mCommit to master did not trigger deployment, add [deploy site] to the commit message if you intend to deploy...\033[0m"
fi
